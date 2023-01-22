package com.mytests.micronaut.repositories;

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
    // no fields except id can be completed here: https://youtrack.jetbrains.com/issue/IDEA-301688

    // parsing errors in case of ISODate using: https://youtrack.jetbrains.com/issue/IDEA-301689
    @MongoFindQuery("{created: {$gt: ISODate('2020-04-30T00:00:00.000Z')}}")
    List<Issue> findByCreatedAfter();

    // same or new Date():
    @MongoFindQuery("{created: {$lt: new Date()}}")
    List<Issue> findByCreatedBefore();

    // false inspection violations in case of incorrect method signature but present annotation:
    // https://youtrack.jetbrains.com/issue/IDEA-301664
    @MongoFindQuery("{title: {$regex: :pattern} }")
    List<Issue> findByTitlePattern(String pattern);
}
