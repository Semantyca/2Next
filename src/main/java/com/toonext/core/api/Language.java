package com.toonext.core.api;


import com.toonext.adapter.SimpleReferenceEntity;
import com.toonext.constants.LanguageCode;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Language extends SimpleReferenceEntity {

    private LanguageCode code = LanguageCode.UNKNOWN;

    @ColumnName("is_on")
    private boolean isOn;

    private int stance;

    @ColumnName("is_cyr")
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
