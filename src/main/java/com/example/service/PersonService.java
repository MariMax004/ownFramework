package com.example.service;

import com.example.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getPersonById (Integer id);
    void deletePersonById(Integer id);

    void updatePerson(Integer id, Person person);

    void createPerson(Person person);
}
