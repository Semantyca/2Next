package com.toonext.domain;

import com.fasterxml.jackson.annotation.*;
import com.toonext.domain.user.IUser;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@JsonPropertyOrder({"entityType", "identifier", "title", "regDate", "author", "editable", "isNew", "isWasRead"})
public abstract class AppEntity<K> implements IAppEntity<K> {

    @JsonIgnore
    protected K id;

    private String identifier;

    protected long author;

    @ColumnName("reg_date")
    @JsonSetter("reg_date")
    private ZonedDateTime regDate;

    @ColumnName("last_mod_date")
    @JsonSetter("last_mod_date")
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    @ColumnName("last_mod_user")
    @JsonSetter("last_mod_user")
    private Long lastModifier;

    private boolean editable = true;

    private boolean wasRead;

    private boolean deleted;

    private String title = "";

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setId(K id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        if (identifier != null) {
            this.identifier = identifier;
        }else{
            this.identifier = id.toString();
        }
    }

    public String getIdentifier() {
         return identifier;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    @Override
    public long getAuthor() {
        return author;
    }

    @Override
    public ZonedDateTime getRegDate() {
        return regDate;
    }

    @JsonIgnore
    public void setRegDate(ZonedDateTime regDate) {
        this.regDate = regDate;
    }

    @JsonIgnore
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    @JsonIgnore
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

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(int isEditable) {
        if (isEditable == 1) {
            editable = true;
        }
        editable = false;
    }

    @JsonProperty("isWasRead")
    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(Timestamp readTime) {
        if (readTime != null) {
            wasRead = true;
        }
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
