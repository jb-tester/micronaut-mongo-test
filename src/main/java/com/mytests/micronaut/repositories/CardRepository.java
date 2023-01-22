package com.mytests.micronaut.repositories;

import com.mytests.micronaut.model.Card;

import io.micronaut.data.mongodb.annotation.*;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MongoRepository
@MongoProjection("{_id: 0, number: 1, valid: 1, 'owner': 1 }")
@MongoSort("{number: -1}")
public interface CardRepository extends CrudRepository<Card, ObjectId> {

    String FNAME = "\"petrov\"";
    String NUM = "\"700000000\"";

    @MongoFindQuery(value = "{}", sort = "{'owner.title': -1}")
    List<Card> findSorted(String number);

    @MongoFindQuery(filter = "{'owner.name': :name }")
    List<Card> findByOwnerFirstName(String name);

    @MongoFindQuery(filter = "{\"owner.surname\": " + FNAME + ", \"number\": {$gt: " + NUM + "} }")
    List<Card> findByFewCriteria();

    @MongoFindQuery("{$nor: [{'owner.name': 'ivan'},{'owner.name': 'dasha'},{'owner.name': 'daria'} ]}")
    List<Card> nor();


}
