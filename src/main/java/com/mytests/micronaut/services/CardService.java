package com.mytests.micronaut.services;

import com.mytests.micronaut.model.Card;
import com.mytests.micronaut.repositories.CardRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * *
 * <p>Created by irina on 1/22/2023.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@Singleton
public class CardService {
    @Inject
    CardRepository cardRepository;

    public List<Card> cardsSorted(){
        return cardRepository.findSorted("967578018");
    }
    public List<Card> byAuthorName(){
        return cardRepository.findByOwnerFirstName("ivan");
    }

    public List<Card> withConstants(){
        return cardRepository.findByFewCriteria();
    }
}
