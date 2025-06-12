package com.masprog.services;


import com.masprog.exceptions.ResourceNotFoundException;
import com.masprog.models.Person;
import com.masprog.repositories.PersonRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private final PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        logger.info("Finding all persons");
        return personRepository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding on Person!");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

    }

    public Person create(Person person) {
        logger.info("Creating one Person!");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one Person!");

        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {

        logger.info("Deleting one Person!");
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);

    }

//    private Person mockPerson(int i) {
//        Person person = new Person();
//        person.setId(counter.incrementAndGet());
//        person.setFirstName("FirstName " + i);
//        person.setLastName("LastName " + i);
//        person.setAddress("Some address in Angola");
//        person.setGender("Male");
//
//        return person;
//    }
}
