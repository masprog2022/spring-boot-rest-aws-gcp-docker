package com.masprog.services;


import com.masprog.dto.v1.PersonDTO;
import com.masprog.dto.v2.PersonDTOv2;
import com.masprog.exceptions.ResourceNotFoundException;
import static com.masprog.mapper.ObjectMapper.parseListObjects;
import static com.masprog.mapper.ObjectMapper.parseObject;

import com.masprog.mapper.custom.PersonMapper;
import com.masprog.models.Person;
import com.masprog.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all persons");
        return parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding on Person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return parseObject(entity, PersonDTO.class);

    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);
        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTOv2 createv2(PersonDTOv2 person) {
        logger.info("Creating one Person!!");

        var entity = personMapper.convertDTOtoEntity(person);
        return personMapper.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person!");

        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
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
