package com.toonext.core.api;


import com.toonext.EnvConst;
import com.toonext.core.api.constants.VisibilityMode;
import com.toonext.domain.SimpleReferenceEntity;
import com.toonext.localization.constants.LanguageCode;
import com.toonext.util.ReflectionUtil;

import java.util.Map;

public class Application extends SimpleReferenceEntity {

    private Map<LanguageCode, String> localizedDescr;

    private boolean isOn;

    private boolean allowCORS;

    private String restURL = EnvConst.REST_PREFIX;

    private String iconUrl;

    public boolean isAllowCORS() {
        return allowCORS;
    }

    public void setAllowCORS(boolean allowCORS) {
        this.allowCORS = allowCORS;
    }

    public Map<LanguageCode, String> getLocalizedDescr() {
        return localizedDescr;
    }

    public void setLocalizedDescr(Map<LanguageCode, String> localizedDescr) {
        this.localizedDescr = localizedDescr;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public String getRestURL() {
        return restURL;
    }

    public void setRestURL(String restURL) {
        this.restURL = restURL;
    }


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        boolean isValid = iconUrl != null && !iconUrl.trim().isEmpty() && !iconUrl.contains("/api/");

        if (isValid) {
            this.iconUrl = iconUrl.trim();
        } else {
            this.iconUrl = null;
        }
    }

    public String[] getAvailableThemes() {
        return (String[]) ReflectionUtil.getAppConstValue(getIdentifier(), "AVAILABLE_THEME");
    }

    public String[] getAvailableRoles() {
        return (String[]) ReflectionUtil.getAppConstValue(getIdentifier(), "ROLES");
    }

    public String getVisibility() {
        Enum t = ((VisibilityMode) ReflectionUtil.getAppConstValue(getIdentifier(), "VISIBILITY"));
        if (t == null) {
            return null;
        } else {
            return t.name();
        }
    }

    public String getDefaultPage() {
        return (String) ReflectionUtil.getAppConstValue(getIdentifier(), "DEFAULT_PAGE");
    }


}
