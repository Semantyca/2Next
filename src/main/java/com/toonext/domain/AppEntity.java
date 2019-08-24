package com.toonext.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.toonext.domain.user.IUser;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.ZonedDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "regDate", "wasRead", "url", "authorId", "editable", "isNew"})
public abstract class AppEntity<K> implements IAppEntity<K> {

    protected K id;

    protected String identifier;

    protected long author;

    @ColumnName("reg_date")
    private ZonedDateTime regDate;

    @ColumnName("last_mod_date")
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    @ColumnName("last_mod_user")
    private Long lastModifier;

    private boolean editable = true;

    private boolean wasRead = true;

    private boolean deleted;

    private String title;

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setId(K id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        if (identifier != null) {
            return identifier.toString();
        } else {
            return id.toString();
        }
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    @Override
    public long getAuthor() {
        return author;
    }

    @JsonIgnore
    @Override
    public void setAuthor(IUser user) {
        /*User u = new User();
        u.setId(user.getId());*/
       // author = u;
        regDate = ZonedDateTime.now();
    }

    @Override
    @JsonGetter
    public ZonedDateTime getRegDate() {
        return regDate;

    }

    @JsonIgnore
    public void setRegDate(ZonedDateTime regDate) {
        this.regDate = regDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    @JsonIgnore
    @Override
    public void setLastModifier(IUser user) {
        lastModifier = user.getId();
        lastModifiedDate = ZonedDateTime.now();
    }

    @JsonIgnore
    @Override
    public String toString() {
        if (getId() == null) {
            return "id is null," + this.getClass().getName();
        }
        UUID id = UUID.fromString(getId().toString());
        return id.toString() + "," + this.getEntityType();
    }


    public String getURL() {
        return getIdentifier();
    }
    
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    @JsonProperty("isNew")
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        @SuppressWarnings("unchecked")
        AppEntity<UUID> tmp = (AppEntity<UUID>) obj;

        return id != null && id.equals(tmp.id);
    }

}
