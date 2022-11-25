package com.mytests.micronaut.controllers;

import com.mytests.micronaut.model.Issue;
import com.mytests.micronaut.model.Person;
import com.mytests.micronaut.services.IssueService;
import com.mytests.micronaut.services.PersonService;
import com.mytests.micronaut.services.SetupDB;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.annotation.*;
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
    @Inject
    IssueService issueService;

    @Post("/setup")
    public void setup() {
        setupDBService.setUp();
    }

    @Post("/add/{fname}/{sname}/{year}")
    public void addPerson(@PathVariable String fname, @PathVariable String sname, @PathVariable String year) {
        setupDBService.addSinglePerson(fname, sname, Integer.parseInt(year));
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
    public List<Person> personByName() {
        return personService.testSearchByName();
    }

    @Get("/byYear")
    public List<Person> personByConstantYear() {
        return personService.testSearchByYear();
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

    @Get("/byName1")
    public List<Person> findByName1() {
        return personService.testFindByNameStandard();
    }

    @Get("/byName2")
    public List<Person> findByName2() {
        return personService.testFindByNameCustom();
    }

    @Get("/byCards1")
    public List<Person> findByCardNumberPattern() {
        return personService.testFindByCards();
    }

    @Get("/issuesAfter")
    public List<Issue> getIssuesCreatedAfter() {
        return issueService.findByCreatedAfter();
    }

}
