package com.example.impl;

import com.example.model.Person;
import com.example.service.PersonService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class PersonServiceImpl implements PersonService {

    Map<Integer, Person> persons = new ConcurrentHashMap<>();

    private void fillPersons(){
        Person person = Person.builder()
                .firstName("Lil")
                .lastName("Mamy")
                .build();
        persons.put(1, person);
    }
    @Override
    public List<Person> getPersonById(Integer id) {
        if (persons.containsKey(id)) {
            return Arrays.asList(persons.get(id));
        }
        return new ArrayList<>();
    }

    @Override
    public void deletePersonById(Integer id) {
        if (persons.containsKey(id)) {
            persons.remove(id);
        }
    }

    @Override
    public void updatePerson(Integer id, Person person) {
        persons.put(persons.containsKey(id) ? id : generateNewId(), person);
    }

    @Override
    public void createPerson(Person person) {
        persons.put(generateNewId(), person);
    }

    private Integer generateNewId() {
        return ThreadLocalRandom.current().nextInt(1000000, 10000000);
    }
}
