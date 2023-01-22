package com.mytests.micronaut.services;

import com.mytests.micronaut.model.Issue;
import com.mytests.micronaut.repositories.IssueRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * *
 * <p>Created by irina on 9/12/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@Singleton
public class IssueService {
    @Inject
    IssueRepository repository;


    public List<Issue> findByCreatedAfter(){
        return repository.findByCreatedAfter();
    }
    public List<Issue> findByCreatedBefore(){
        return repository.findByCreatedBefore();
    }

}
