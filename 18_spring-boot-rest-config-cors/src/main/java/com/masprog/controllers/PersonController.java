package com.masprog.controllers;

import com.masprog.controllers.docs.PersonControllerDocs;
import com.masprog.dto.PersonDTO;
import com.masprog.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
    })
    @Override
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

   // @CrossOrigin( origins = {"http://localhost:8080","https://www.erudio.com.br/"})
    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    //@CrossOrigin(origins = {"http://localhost:8080","https://www.erudio.com.br/"})
    @Override
    public PersonDTO save(@RequestBody PersonDTO person) {
        return personService.create(person);
  }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }


    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
