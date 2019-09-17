package com.toonext.dataengine;

import com.toonext.api.IAppEntity;
import com.toonext.api.IUser;

import java.util.List;

public interface IDatabase {



    String getInfo();

    double checkSpeed();

    List<String[]> getCountsOfRec();

    long getCount();

    int getRegNum(String key);

    int postRegNum(int num, String key);

    int markAsRead(IUser user, IAppEntity entity);

    String getVersion(String url);

    String getVersion();

}
