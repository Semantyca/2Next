package com.toonext.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.toonext.domain.IAppEntity;
import com.toonext.domain.ISecureAppEntity;
import com.toonext.domain.user.AnonymousUser;
import com.toonext.domain.user.SuperUser;
import com.toonext.domain.user.UndefinedUser;
import com.toonext.util.TimeUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonPropertyOrder({"kind", "readers", "editors"})
public class ACL {

    public Map<Long, Object> readers = new HashMap<>();
    public Map<Long, Object> editors = new HashMap<>();



    public ACL(IAppEntity<UUID> e) {
        ISecureAppEntity<UUID> entity = (ISecureAppEntity<UUID>) e;

        Map<Long, Reader> readerMap = entity.getReaders();
        if (readerMap != null) {
            for (Map.Entry<Long, Reader> reader : readerMap.entrySet()) {
                Date readingTime = reader.getValue().getReadingTime();
                if (readingTime != null) {
                    readers.put(reader.getKey(), getUserName(reader.getKey()) + " - " + TimeUtil.dateTimeToStringSilently(readingTime));
                } else {
                    readers.put(reader.getKey(), getUserName(reader.getKey()));
                }
            }
        }

        for (Long id : entity.getEditors()) {
            editors.put(id, getUserName(id));
        }
    }


    private String getUserName(long id) {
        if (id > 0) {
            return UndefinedUser.USER_NAME;
        } else if (id == 0) {
            return AnonymousUser.USER_NAME;
        } else if (id == -1) {
            return SuperUser.USER_NAME;
        } else {
            return UndefinedUser.USER_NAME;
        }
    }
}
