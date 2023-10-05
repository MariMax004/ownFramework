package com.example.person;

import com.example.annotation.InjectByType;
import com.example.model.Person;
import com.example.service.Announcer;
import com.example.service.PersonService;

import java.util.List;

public class PersonCrud {
    @InjectByType
    private PersonService personService;
    @InjectByType
    private Announcer announcer;

    public void createPerson(Person person) {
        announcer.announce("Create Person");
        personService.createPerson(person);
    }

    public void updatePerson(Integer id, Person person) {
        announcer.announce("Update Person");
        personService.updatePerson(id, person);
    }

    public List<Person> getPersonById(Integer id) {
        announcer.announce("Get Person");
        return personService.getPersonById(id);
    }
    public void deletePersonById(Integer id) {
        announcer.announce("Delete Person");
        personService.deletePersonById(id);
    }

}
