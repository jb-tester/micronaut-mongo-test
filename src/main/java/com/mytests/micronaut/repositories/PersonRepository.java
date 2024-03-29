package com.mytests.micronaut.repositories;

import com.mytests.micronaut.annotations.MyQuery;
import com.mytests.micronaut.model.Card;
import com.mytests.micronaut.model.Person;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
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
    //@MongoFindQuery("{firstName: :firstName}") //- INCORRECT
    List<Person> findByFirstName_custom(String firstName);

    @MongoFindQuery(filter = "{surname:{$regex: :surname}}", sort = "{name: 1}")
    List<Person> customFindByLastName(String surname);

    @MongoFindQuery(filter = "{name: :fname}", sort = "{surname: 1}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> customFindByFirstName(String fname);

    // false inspection violations in case of incorrect method signature but present annotation:
    // https://youtrack.jetbrains.com/issue/IDEA-301664

    // parser error in case of missing space before the not-quoted value - https://youtrack.jetbrains.com/issue/IDEA-298049
    @MongoFindQuery(filter = "{year:1950}", sort = "{surname:1}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> findByConstantYearCustom();

    @MongoAggregateQuery("[{$match: {surname:{$regex: :surname}}}, {$sort: {year: 1}}, {$project: {surname: 1,name: 1,year: 1, title: 1}}]")
    List<Person> findBySurnameAndAggregateAndProject(String surname);

    @MongoAggregateQuery("[{$match: {name:'ivan', year:{$gt: 1940}}},  {$project: {surname: 1,name: 1,year: 1, title: 1}}]")
    List<Person> findByFirstNameAndBirthYear();

    @MongoUpdateQuery(filter = "{year:{$lt: :year}}", update = "{$set:{title: 'senior'}}", collation = "{locale:'en_US', numericOrdering: true}")
    List<Person> updateTitleByBirthYears(int year);

    @MongoDeleteQuery(filter = "{title:{$regex: :title}}", collation = "{locale:'en_US', numericOrdering: true}")
    void deleteByTitleCustom(String title);

    @MongoFindQuery(filter = "{year:{$lt: 1960}}")
    List<Person> findByBirthYearLessThan();

    // for some reasons filter is ignored here, but projection works
    //@MongoFilter("{name: {$regex: :name}}")
    @MongoProjection("{_id: 0, surname: 1, name: 1, year: 1, title: 1}")
    //@MyQuery
    List<Person> findByBirthYearLessThan(Integer birthYear, String name);

    @MongoFindQuery("{'cards.number': {$regex: :pattern }}") @Join("card")
    List<Person> findByCards(String pattern);
}
