package de.alexanderwodarz.code.jamf.now.model;

import de.alexanderwodarz.code.jamf.now.JamfNow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;

@AllArgsConstructor
@Getter
public class Model {

    private final JamfNow jamf;
    private final JSONObject obj;

    public Object get(JSONObject obj, String get) {
        return obj.has(get) ? obj.get(get) : "";
    }

    public Object get(String get) {
        return get(getObj(), get);
    }

    public String getString(JSONObject obj, String get) {
        return get(obj, get).toString();
    }

    public String getString(String get) {
        return getString(getObj(), get);
    }

    public boolean getBoolean(JSONObject obj, String get) {
        return Boolean.parseBoolean(getString(obj, get));
    }

    public boolean getBoolean(String get) {
        return getBoolean(getObj(), get);
    }

    public int getInt(JSONObject obj, String get) {
        return Integer.parseInt(getString(obj, get));
    }

    public int getInt(String get) {
        return getInt(getObj(), get);
    }

    public JSONObject getJSONObject(String get) {
        return obj.has(get) ? obj.getJSONObject(get) : new JSONObject();
    }

}
