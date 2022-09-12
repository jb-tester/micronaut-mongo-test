package com.mytests.micronaut.repositories;

import com.mytests.micronaut.model.Card;
import com.mytests.micronaut.model.Issue;
import io.micronaut.data.mongodb.annotation.MongoFindQuery;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * *
 * <p>Created by irina on 9/12/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@MongoRepository
public interface IssueRepository extends CrudRepository<Issue, ObjectId> {

    @MongoFindQuery("{created: {$gt: ISODate('2020-04-30T00:00:00.000Z')}}")
    List<Issue> findByCreatedAfter();

    // for completion testing:
    /*@MongoFindQuery("{}")
    List<Issue> findByTitlePattern();*/
}
