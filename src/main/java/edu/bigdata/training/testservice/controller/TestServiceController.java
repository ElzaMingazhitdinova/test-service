package edu.bigdata.training.testservice.controller;

import edu.bigdata.training.testservice.controller.model.Person;
import edu.bigdata.training.testservice.model.PersonEntity;
import edu.bigdata.training.testservice.service.TestBusinessLogicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("person")
public class TestServiceController {

    private TestBusinessLogicService testBusinessLogicService;

    public TestServiceController(TestBusinessLogicService testBusinessLogicService) {
        this.testBusinessLogicService = testBusinessLogicService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonEntity> createPerson(@RequestBody Person person) {
        PersonEntity personEntity = testBusinessLogicService.processCreate(person);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }

    @GetMapping(path = {"{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonEntity> getPerson(@PathVariable String id) {
        PersonEntity personEntity = testBusinessLogicService.processGet(id);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonEntity>> getAll() {
        List<PersonEntity> personEntities = testBusinessLogicService.processGetAll();
        return new ResponseEntity<>(personEntities, HttpStatus.OK);
    }

    @PutMapping(path = {"{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable String id, @RequestBody Person person) {
        PersonEntity personEntity = testBusinessLogicService.processUpdate(id, person);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }

    @DeleteMapping(path = {"{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delPerson(@PathVariable String id) {
        testBusinessLogicService.processDelete(id);
    }

}