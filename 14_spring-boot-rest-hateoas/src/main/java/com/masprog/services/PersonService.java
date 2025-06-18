package com.masprog.services;


import com.masprog.controllers.PersonController;
import com.masprog.dto.PersonDTO;
import com.masprog.exceptions.RequiredObjectIsNullException;
import com.masprog.exceptions.ResourceNotFoundException;
import static com.masprog.mapper.ObjectMapper.parseListObjects;
import static com.masprog.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.masprog.models.Person;
import com.masprog.repositories.PersonRepository;
//import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonService {

   // private final AtomicLong counter = new AtomicLong();

    private final PersonRepository personRepository;


    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all persons");
        var persons = parseListObjects(personRepository.findAll(), PersonDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding on Person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

       var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
       return dto;

    }

    public PersonDTO create(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);
        var dto = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one Person!");

        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one Person!");
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);

    }



    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).save(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
