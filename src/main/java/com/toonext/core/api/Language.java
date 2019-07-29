package com.toonext.core.api;


import com.toonext.domain.SimpleReferenceEntity;
import com.toonext.localization.constants.LanguageCode;

public class Language extends SimpleReferenceEntity {

    private LanguageCode code = LanguageCode.UNKNOWN;


    private boolean isOn;

    private int stance;

    private boolean isCyrillic;

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public LanguageCode getCode() {
        return code;
    }

    public void setCode(LanguageCode code) {
        this.code = code;
    }

    public void setLanguageCode(String id) {
        this.code = LanguageCode.valueOf(id);
    }

    public int getStance() {
        return stance;
    }

    public void setStance(int stance) {
        this.stance = stance;
    }

    public boolean isCyrillic() {
        return isCyrillic;
    }

    public void setIsCyrillic(boolean cyrillic) {
        isCyrillic = cyrillic;
    }


}
