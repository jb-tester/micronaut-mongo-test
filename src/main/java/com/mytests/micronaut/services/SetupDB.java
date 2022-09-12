package com.mytests.micronaut.services;

import com.mongodb.client.MongoClient;
import com.mytests.micronaut.model.Card;
import com.mytests.micronaut.model.Issue;
import com.mytests.micronaut.model.Person;
import com.mytests.micronaut.model.State;
import com.mytests.micronaut.repositories.CardRepository;
import com.mytests.micronaut.repositories.IssueRepository;
import com.mytests.micronaut.repositories.PersonRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * *
 * <p>Created by irina on 3/10/2022.</p>
 * <p>Project: micronaut-mongo-test</p>
 * *
 */
@Singleton
public class SetupDB {

    @Inject
    PersonRepository personRepo;
    @Inject
    CardRepository cardRepo;
    @Inject
    IssueRepository issueRepo;

    @Inject
    MongoClient client;

    public void addSinglePerson(String name, String surname, int year){
        createPerson(name, surname, year, 1, "just_added");
    }
    public void setUp(){
        client.getDatabase("micronaut1").drop();
        createPerson("ivan", "ivanov", 1950, 2, "no");
        createPerson("maria", "ivanova", 1950, 1,"no");
        createPerson("ivan", "petrov", 1970, 4,"no");
        createPerson("ivan", "durnov", 2000, 10,"no");
        createPerson("daria", "petrova", 1970, 2,"no");
        createPerson("ekaterina", "sidorova", 1999, 1,"no");
        createPerson("pavel", "pavlov", 1959, 1,"no");
        createPerson("petr", "sidorenko", 1937, 1,"no");
        createIssues();
    }

    private void createPerson(String firstName, String lastName, int year, int cardsAmount, String title) {
        Person person1 = new Person(firstName, lastName, year, title);
        String pId = personRepo.save(person1).getId();
        Set<Card> cards = createCards(person1, cardsAmount);
        cardRepo.saveAll(cards);
        personRepo.update(pId, cards);
    }

    private Set<Card> createCards(Person person1, int cardsAmount) {
        Set<Card> cards = new HashSet<>();
        int num = new Random().nextInt(999999999 - 100000000) + 100000000;
        String cardNum = String.valueOf(num);
        LocalDate end = LocalDate.of(2030, Month.DECEMBER, 31);
        LocalDate start = LocalDate.now();
        LocalDate date = RandomDates.between(start, end);
        Date validDate = Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());;
        for (int i = 0; i < cardsAmount; i++) {
            Card card = new Card(cardNum, validDate, person1);
            cards.add(card);
        }

        return cards;
    }

    private void createIssues(){
        Issue i1 = new Issue("First Bug","Application has bugs!!!", State.DECLINED, Date.from(LocalDate.of(2020, Month.JANUARY, 11).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i2 = new Issue("Second Bug","Application has bugs!!!", State.DECLINED, Date.from(LocalDate.of(2020, Month.JANUARY, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i3 = new Issue("First Feature","Provide completion", State.OPENED, Date.from(LocalDate.of(2020, Month.MARCH, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i4 = new Issue("Second Feature","Provide Dark theme", State.SUBMITTED, Date.from(LocalDate.of(2020, Month.MAY, 20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i5 = new Issue("Some Bug","Can't start on MacOSX", State.FIXED, Date.from(LocalDate.of(2020, Month.MAY, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i6 = new Issue("Crash on start","Application fails to start", State.OPENED, Date.from(LocalDate.of(2020, Month.JANUARY, 11).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i7 = new Issue("Toolbar is too complex","It is enough to have 3 actions on the toolbar", State.OPENED, Date.from(LocalDate.of(2020, Month.JUNE, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        Issue i8 = new Issue("No Cut/Copy/Paste actions on the toolbar","please add clipboard operations to toolbar", State.SUBMITTED, Date.from(LocalDate.of(2020, Month.JULY, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        i1.setClosed(i2.getCreated());
        i1.setResolution("please specify the bugs");
        i2.setClosed(i2.getCreated());
        i2.setResolution("duplicates the first bug");
        i5.setClosed(Date.from(LocalDate.of(2021, Month.AUGUST, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        i5.setResolution("works now");
        List<Issue> issues = Arrays.asList(i1,i2,i3,i4,i5,i6,i7,i8);
        issueRepo.saveAll(issues);
    }

    static class RandomDates {

        public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
            long startEpochDay = startInclusive.toEpochDay();
            long endEpochDay = endExclusive.toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

            return LocalDate.ofEpochDay(randomDay);
        }

        public  LocalDate date() {
            int hundredYears = 100 * 365;
            return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears));
        }
    }

}
