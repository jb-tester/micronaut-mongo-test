package com.mytests.micronaut.repositories;

import com.mytests.micronaut.model.Card;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MongoRepository
public interface CardRepository extends CrudRepository<Card, ObjectId> {
}
