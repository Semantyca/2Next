package com.toonext.domain;

import com.toonext.domain.user.IUser;
import com.toonext.dto.Reader;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISecureAppEntity<K> extends IAppEntity<K> {

    void addReader(IUser user);

    void resetEditors();

    void resetReadersEditors();

    void addReaderEditor(IUser user);

    void addReaderEditors(List<Long> substitutes);

    Map<Long, Reader> getReaders();

    Set<Long> getEditors();


}
