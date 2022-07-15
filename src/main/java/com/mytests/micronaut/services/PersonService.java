package com.mytests.micronaut.services;

import com.mytests.micronaut.model.Person;
import com.mytests.micronaut.repositories.PersonRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * *
 * <p>Created by irina on 3/11/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@Singleton
public class PersonService {

    @Inject
    PersonRepository repository;

    public List<Person> listAll(){
        return (List<Person>) repository.findAll();
    }

    public List<Person> findByLastNameRegex(){
        return repository.customFindByLastName("ivanov");
    }

    public List<Person> aggregationTest(){
        return repository.customAggregateAndProject("petrov");
    }

    public List<Person> updateTest(){
        return repository.customUpdateTitleByBirthYear(1960);
    }

    public List<Person> testFilter(){
        return repository.findByBirthYearLessThan(1971, "ivan");
    }

    public List<Person> testSearchByName(){ return repository.customFindByConstantName();}
}
