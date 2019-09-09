package com.toonext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.domain.embedded.Reader;
import com.toonext.domain.user.IUser;

import java.util.*;

public abstract class SecureAppEntity extends AppEntity<UUID> {

    private Set<Long> editors = new HashSet<>();

    private Map<Long, Reader> readers = new HashMap<>();

    @JsonIgnore
    public Set<Long> getEditors() {
        return editors;
    }

    @JsonIgnore
    public void setEditors(Set<Long> editors) {
        this.editors = editors;
    }

    public void addReaderEditor(IUser user) {
        long id = user.getId();
        addReaderEditor(id);
    }

    public void addReaderEditors(List<Long> users) {
        for (Long reader : users) {
            addReader(reader);
        }
        addEditors(users);
    }

    public void addReaderEditor(Long userId) {
        if (userId != 0) {
            editors.add(userId);
            addReader(userId);
        }
    }

    public void addReadersList(List<Long> readers) {
        for (Long reader : readers) {
            addReader(reader);
        }
    }

    @JsonIgnore
    public Map<Long, Reader> getReaders() {
        return readers;
    }

    @JsonIgnore
    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    public void setReaders(Set<Long> r) {
        readers.clear();
        for (Long reader : r) {
            addReader(reader);
        }
    }

    public void addReader(IUser user) {
        long id = user.getId();
        addReader(id);
    }

    public void withdrawEditor(IUser user) {
        long id = user.getId();
        editors.remove(id);
        addReader(id);
    }

    public void addReader(Long userId) {
        if (userId > 0 && !readers.containsKey(userId)) {
            Reader reader = new Reader();
            reader.setReader(userId);
            readers.put(userId, reader);
        }
    }

    public void addReaders(Map<Long, Reader> r) {
        for (Map.Entry<Long, Reader> reader : r.entrySet()) {
            addReader(reader.getKey());
        }
    }

    public void addEditors(List<Long> r) {
        addReadersList(r);
        editors.addAll(r);
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }
}
