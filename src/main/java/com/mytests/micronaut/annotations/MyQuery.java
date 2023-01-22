package com.mytests.micronaut.annotations;

/**
 * *
 * <p>Created by irina on 1/20/2023.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */

import io.micronaut.data.mongodb.annotation.MongoFilter;
import io.micronaut.data.mongodb.annotation.MongoProjection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
@MongoFilter("{firstName: 'pavel'}")
@MongoProjection("{_id: 0, surname: 1, name: 1, year: 1, title: 1}")
public @interface MyQuery {
}
