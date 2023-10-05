package com.example;

import com.example.config.ApplicationContext;
import com.example.model.Person;
import com.example.model.Room;
import com.example.person.CoronaDesinfector;
import com.example.person.PersonCrud;
import com.example.service.Policeman;
import com.example.impl.PolicemanImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.example", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
//        PersonCrud personCrud = context.getObject(PersonCrud.class);
//
//        Person person = Person.builder()
//                .firstName("Mike")
//                .lastName("Harry")
//                .build();
//        personCrud.createPerson(person);
//        personCrud.updatePerson(1, person);
//        personCrud.getPersonById(1);
//        personCrud.deletePersonById(1);
    }
}
