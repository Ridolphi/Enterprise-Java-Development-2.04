package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void testSetAgeThrowsExceptionForNegativeAge() {
        Person person = new Person(1, "John Doe", 25, "Developer");
        assertThrows(IllegalArgumentException.class, () -> {
            person.setAge(-5);
        });
    }

    @Test
    public void testFindByNameWithProperlyFormattedName() {
        PersonsList personsList = new PersonsList();
        Person person = new Person(1, "John Doe", 25, "Developer");
        personsList.addPerson(person);

        Person found = personsList.findByName("John Doe");
        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }

    @Test
    public void testFindByNameThrowsExceptionForImproperFormat() {
        PersonsList personsList = new PersonsList();
        assertThrows(IllegalArgumentException.class, () -> {
            personsList.findByName("John");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            personsList.findByName("John Doe Smith");
        });
    }

    @Test
    public void testCloneCreatesNewPersonWithSamePropertiesButNewId() {
        PersonsList personsList = new PersonsList();
        Person original = new Person(1, "John Doe", 25, "Developer");
        personsList.addPerson(original);

        Person clone = personsList.clone(original);

        assertNotEquals(original.getId(), clone.getId());
        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getAge(), clone.getAge());
        assertEquals(original.getOccupation(), clone.getOccupation());
        assertTrue(original.equals(clone));
    }

    @Test
    public void testWriteToFile() {
        PersonsList personsList = new PersonsList();
        Person person = new Person(1, "John Doe", 25, "Developer");

        // This should not throw an exception
        assertDoesNotThrow(() -> {
            personsList.writeToFile(person);
        });
    }
}