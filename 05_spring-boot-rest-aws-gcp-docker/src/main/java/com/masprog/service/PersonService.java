package com.masprog.service;


import com.masprog.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }



    public Person findById(String id){
        logger.info("Finding on Person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mauro");
        person.setLastName("Manuel");
        person.setAddress("UberLândi - Minas Gerais - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one Person!");
        return person;
    }

    public Person update(Person person) {

        logger.info("Updating one Person!");

        return person;
    }

    public void delete(String id) {

        logger.info("Deleting one Person!");

    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Some address in Angola");
        person.setGender("Male");

        return person;
    }
}
