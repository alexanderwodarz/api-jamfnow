package de.alexanderwodarz.code.jamf.now.model;

import de.alexanderwodarz.code.jamf.now.JamfNow;
import org.json.JSONObject;

public class Blueprint extends Model {

    public Blueprint(JamfNow jamf, JSONObject obj) {
        super(jamf, obj);
    }

    public String getTenantId() {
        return getString("tenantId");
    }

    public String getId() {
        return getString("blueprintId");
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public String getOpenEnrollmentSlug() {
        return getString("openEnrollmentSlug");
    }

    public int getDeviceCount() {
        return getInt("deviceCount");
    }

    public int getUnmanagedDepDeviceCount() {
        return getInt("unmanagedDepDeviceCount");
    }

    public boolean requiredEmail() {
        return getBoolean("requiresEmail");
    }

    public boolean requiresEmailUsername() {
        return getBoolean("requiresEmailUsername");
    }

    public boolean isGlobalDefault() {
        return getBoolean("globalDefault");
    }

    public boolean isUsedAsDepDefault() {
        return getBoolean("usedAsDepDefault");
    }

    public String getIndustryBlueprintId() {
        return getString("industryBlueprintId");
    }

    public String getSelfServiceAppsCount() {
        return getString("selfServiceAppsCount");
    }

    public boolean isPrivate() {
        return getBoolean("private");
    }

}
