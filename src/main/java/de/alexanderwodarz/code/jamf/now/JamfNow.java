package de.alexanderwodarz.code.jamf.now;

import de.alexanderwodarz.code.jamf.now.model.Blueprint;
import de.alexanderwodarz.code.jamf.now.model.Device;
import de.alexanderwodarz.code.rest.ClientThread;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.NewCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JamfNow {

    private String session;

    public JamfNow(String email, String password) {
        Result result = request("login/auth", ClientThread.RequestMethod.POST, new FormData("username", email), new FormData("password", password), new FormData("lang", "en-US"));
        for (NewCookie cookie : result.getClientResponse().getCookies()) {
            if (!cookie.getName().equals("SESSION"))
                continue;
            session = cookie.getValue();
        }
        if (session != null)
            startHoldingSession();
    }

    public void startHoldingSession() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                holdSession();
            }
        };
        timer.schedule(task, 5000, 5000);
    }

    public void holdSession() {
        request("frontend/rest/authorize", ClientThread.RequestMethod.GET);
    }

    public Device getDevice(String deviceId) {
        Result result = request("frontend/rest/devices/"+deviceId, ClientThread.RequestMethod.GET);
        if (result.getStatus() != 200)
            return null;
        return new Device(this, new JSONObject(result.getResponse()));
    }

    public List<Device> getDevices() {
        Result result = request("device-status/devices", ClientThread.RequestMethod.GET);
        List<Device> devices = new ArrayList<>();
        JSONArray arr = new JSONArray(result.getResponse());
        for (int i = 0; i < arr.length(); i++)
            devices.add(new Device(this, arr.getJSONObject(i)));
        return devices;
    }

    public List<Blueprint> getBlueprints() {
        Result result = request("frontend/rest/blueprints", ClientThread.RequestMethod.GET);
        List<Blueprint> blueprints = new ArrayList<>();
        JSONArray arr = new JSONArray(result.getResponse());
        for (int i = 0; i < arr.length(); i++)
            blueprints.add(new Blueprint(this, arr.getJSONObject(i)));
        return blueprints;
    }

    public Blueprint getBlueprint(String blueprintId) {
        return new Blueprint(this, new JSONObject(request("frontend/rest/blueprints/" + blueprintId, ClientThread.RequestMethod.GET).getResponse()));
    }

    public Result request(String url, ClientThread.RequestMethod method, FormData... data) {
        ClientThread thread = new ClientThread("https://api.services.jamfnow.com/" + url, method);
        if (session != null)
            thread.addCookie("SESSION", session);
        if (data != null) {
            for (FormData form : data) {
                thread.addForm(form.getKey(), form.getValue());
            }
        }
        thread.run();
        while (thread.isAlive()) {
        }
        return new Result(thread.getClientResponse(), thread.getResponse(), thread.getStatus());
    }

    public Result request(String url, ClientThread.RequestMethod method, JSONObject body) {
        ClientThread thread = new ClientThread("https://api.services.jamfnow.com/" + url, method);
        if (session != null)
            thread.addCookie("SESSION", session);
        if (body != null) {
            thread.setBody(body);
        }
        thread.run();
        while (thread.isAlive()) {
        }
        return new Result(thread.getClientResponse(), thread.getResponse(), thread.getStatus());
    }

}
