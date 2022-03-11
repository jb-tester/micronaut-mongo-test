package com.mytests.micronaut.model;

import io.micronaut.data.annotation.*;

import java.util.Set;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MappedEntity()
public class Person {
    @GeneratedValue @Id
    String id;
    @MappedProperty("name")
    String firstName;
    @MappedProperty("surname")
    String lastName;
    String title;
    @MappedProperty("year")
    Integer birthYear;


    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "owner")
    private Set<Card> cards;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person(String firstName, String lastName, Integer birthYear, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", birthYear=" + birthYear +
                ", cards=" + cards +
                '}';
    }
}
