package com.mytests.micronaut.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * *
 * <p>Created by irina on 9/12/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MappedEntity
//@Serdeable.Deserializable  // necessary if you remove @MappedEntity
public class Issue {
    @Id
    @GeneratedValue
    ObjectId id;   // no navigation by gutter icon - https://youtrack.jetbrains.com/issue/IDEA-301690

    String title;
    String description;
    State state;
    Date created;
    Date closed;
    String resolution;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Issue(String title, String description, State state, Date created) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.created = created;
    }
}
