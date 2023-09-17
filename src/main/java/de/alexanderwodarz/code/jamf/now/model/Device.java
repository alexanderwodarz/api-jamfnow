package de.alexanderwodarz.code.jamf.now.model;

import de.alexanderwodarz.code.jamf.now.JamfNow;
import de.alexanderwodarz.code.rest.ClientThread;
import org.json.JSONObject;

public class Device extends Model {
    public Device(JamfNow jamf, JSONObject obj) {
        super(jamf, obj);
    }

    public String getFullName() {
        return getString("fullName");
    }

    public boolean isDepEnrolled() {
        return getBoolean("depEnrolled");
    }

    public String getDeviceId() {
        return getString("deviceId");
    }

    public String getManagementStatus() {
        return getString("managementStatus");
    }

    public String getInventoryName() {
        return getString("inventoryName");
    }

    public String getOsVersion() {
        return getString("osVersion");
    }

    public boolean isSupervised() {
        return getBoolean("supervised");
    }

    public String getModelIdentifier() {
        return getString("modelIdentifier");
    }

    public String getOsBuild() {
        return getString("osBuild");
    }

    public String getSerialNumber() {
        return getString("serialNumber");
    }

    public String getBlueprintName() {
        return getString(getJSONObject("blueprint"), "name");
    }

    public String getBlueprintId() {
        return getString(getJSONObject("blueprint"), "blueprintId");
    }

    public Blueprint getBlueprint() {
        return getJamf().getBlueprint(getBlueprintId());
    }

    public boolean lock(String message, String phonenumber) {
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/lock", ClientThread.RequestMethod.POST, new JSONObject().put("message", message).put("phonenumber", phonenumber)).getStatus() == 200;
    }

    public boolean shutdown() {
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/shutdown", ClientThread.RequestMethod.POST).getStatus() == 200;
    }

    public boolean restart() {
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/restart", ClientThread.RequestMethod.POST).getStatus() == 200;
    }

    public boolean sendSyncRequest(){
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/sync/inventory", ClientThread.RequestMethod.POST).getStatus() == 200;
    }

    public boolean removePasscode() {
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/removepasscode", ClientThread.RequestMethod.POST).getStatus() == 200;
    }

    public boolean deleteAllData() {
        return getJamf().request("frontend/rest/devices/" + getDeviceId() + "/deletealldata", ClientThread.RequestMethod.POST).getStatus() == 200;
    }

}
