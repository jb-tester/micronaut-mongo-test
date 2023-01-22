package com.mytests.micronaut.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MappedEntity
public class Card {
    @Id
    @GeneratedValue
    ObjectId id;

    String number;
    Date valid;
    @MappedProperty
    Person owner;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getValid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Card(String number, Date valid, Person owner) {
        this.number = number;
        this.valid = valid;
        this.owner = owner;
    }
}
