package com.mytests.micronaut.repositories;

import com.mytests.micronaut.model.Card;
import com.mytests.micronaut.model.Person;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.mongodb.annotation.*;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


@MongoCollation("{ locale: 'en_US', numericOrdering: true}")
@MongoSort("{year: 1}")
@MongoRepository
public interface PersonRepository extends CrudRepository<Person, String> {

    void update(@Id String id, Set<Card> cards);

    List<Person> findByFirstName(String firstName);

    @MongoFindQuery("{name: :firstName}")
    //@MongoFindQuery("{firstName: :firstName}") - INCORRECT
    List<Person> findByFirstName_custom(String firstName);

    @MongoFindQuery(filter = "{surname:{$regex: :surname}}", sort = "{name: 1}")
    List<Person> customFindByLastName(String surname);

    @MongoFindQuery(filter = "{name: :fname}", sort = "{surname: 1}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> customFindByFirstName(String fname);

    @MongoFindQuery(filter = "{year:1950}", sort = "{surname:1}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> customFindByConstantYear();

    @MongoAggregateQuery("[{$match: {surname:{$regex: :surname}}}, {$sort: {year: 1}}, {$project: {surname: 1,name: 1,year: 1, title: 1}}]")
    List<Person> customAggregateAndProject(String surname);

    @MongoUpdateQuery(filter = "{year:{$lt: :year}}", update = "{$set:{title: 'senior'}}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> customUpdateTitleByBirthYear(int year);

    @MongoDeleteQuery(filter = "{title:{$regex: :title}}", collation = "{locale:'en_US', numericOrdering: true}")
    void customDeleteByTitle(String title);

    // for some reasons filter is ignored here, but projection works
    @MongoFilter("{name: {$regex: :name}}") @MongoProjection("{surname: 1, name: 1, year: 1, title: 1}")
    List<Person> findByBirthYearLessThan(Integer birthYear, String name);
}
