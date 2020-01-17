package com.semantyca.entity.transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named @RequestScoped
public class Payload extends AbstractPayload {
    private String orgLogo = "/Workspace/img/f.jpg";
    private String about = "<div class=\\\"tpl-page\\\">\\n    <h2 class=\\\"page-header\\\">some about info</h2>\\n</div>\\n";
    private String orgName = "Semantyca Ltd";
    private String orgWallpaper = "";
    private String orgColor = "#FFFFFF";
    private String serverVersion = "3.3.4";
    private Date build = new Date();
    private Map<String, String> availableLanguages = new LinkedHashMap<>();
    private List<String> uiThemes = new ArrayList<>();

    @PostConstruct
    private void init() {
        availableLanguages.put("ENG","English");
        availableLanguages.put("RUS","Руccкий");
        availableLanguages.put("POR","Português");

        uiThemes.addAll(Arrays.asList(new String[]{"azul", "cinzento", "branco", "preto"}));

    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(final String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(final String about) {
        this.about = about;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    public String getOrgWallpaper() {
        return orgWallpaper;
    }

    public void setOrgWallpaper(final String orgWallpaper) {
        this.orgWallpaper = orgWallpaper;
    }

    public String getOrgColor() {
        return orgColor;
    }

    public void setOrgColor(final String orgColor) {
        this.orgColor = orgColor;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(final String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public Date getBuild() {
        return build;
    }

    public void setBuild(final Date build) {
        this.build = build;
    }

    public Map<String, String> getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(final Map<String, String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public List<String> getUiThemes() {
        return uiThemes;
    }

    public void setUiThemes(final List<String> uiThemes) {
        this.uiThemes = uiThemes;
    }

}
