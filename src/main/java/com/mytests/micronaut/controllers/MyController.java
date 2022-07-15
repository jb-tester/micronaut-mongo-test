package com.mytests.micronaut.controllers;

import com.mytests.micronaut.model.Person;
import com.mytests.micronaut.services.PersonService;
import com.mytests.micronaut.services.SetupDB;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

import java.util.List;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@Controller
public class MyController {

    @Inject
    SetupDB setupDBService;
    @Inject
    PersonService personService;
    @Post("/setup")
    public void setup() {
        setupDBService.setUp();
    }

    @Get("/all")
    public List<Person> listAll() {
        return personService.listAll();
    }

    @Get("/personByLastName")
    public List<Person> personByLastName() {
        return personService.findByLastNameRegex();
    }

    @Get("/ivans")
    public List<Person> personByConstantName() {
        return personService.testSearchByName();
    }

    @Get("/aggregate")
    public List<Person> personByLastNameAggregated() {
        return personService.aggregationTest();
    }

    @Get("/update")
    public List<Person> updatePersonsTitle() {
        return personService.updateTest();
    }

    @Get("/filterByName")
    public List<Person> filteredByName() {
        return personService.testFilter();
    }
}
