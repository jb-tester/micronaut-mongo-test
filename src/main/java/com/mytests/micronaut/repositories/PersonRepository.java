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


    @MongoFindQuery(filter = "{surname:{$regex: :surname}}", sort = "{name: 1}")
    List<Person> customFindByLastName(String surname);

    @MongoAggregateQuery("[{$match: {surname:{$regex: :surname}}}, {$sort: {surname: 1}}, {$project: {surname: 1,name: 1,year: 1, title: 1}}]")
    List<Person> customAggregateAndProject(String surname);

    @MongoUpdateQuery(filter = "{year:{$lt: :year}}", update = "{$set:{title: 'senior'}}")
    List<Person> customUpdateTitleByBirthYear(int year);

    @MongoDeleteQuery(filter = "{title:{$regex: :title}}", collation = "{locale:'en_US', numericOrdering:true}")
    void customDeleteByTitle(String title);

    // for some reasons filter is ignored here, but projection works
    @MongoFilter("{name: {$regex: :name}}") @MongoProjection("{surname: 1,name: 1,year: 1, title: 1}")
    List<Person> findByBirthYearLessThan(Integer birthYear, String name);
}
