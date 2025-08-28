package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonsList {
    private List<Person> persons;

    public PersonsList() {
        this.persons = new ArrayList<>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public Person findByName(String name) {
        // Validate name format
        if (!name.matches("^[a-zA-Z]+ [a-zA-Z]+$")) {
            throw new IllegalArgumentException("Name must be in format 'firstName lastName'");
        }

        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null; // or throw exception if not found
    }

    public Person clone(Person original) {
        // Generate new ID (you can use a better ID generation strategy)
        int newId = persons.size() + 1;
        return new Person(newId, original.getName(), original.getAge(), original.getOccupation());
    }

    public void writeToFile(Person person) {
        try (FileWriter writer = new FileWriter("person_" + person.getId() + ".txt")) {
            writer.write(person.toString());
            System.out.println("Person information written to file successfully");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}