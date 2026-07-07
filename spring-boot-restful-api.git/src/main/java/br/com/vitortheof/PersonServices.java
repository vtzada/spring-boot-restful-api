package br.com.vitortheof;

import br.com.vitortheof.controllers.PersonController;
import br.com.vitortheof.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    public Person findById(String id) {
        logger.info("Finding person with id: " + id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Vitor");
        person.setLastName("Theodoro");
        person.setAddress("Rio das Ostras - RJ");
        person.setGender("Male");

        return person;

    }

    public List<Person> findByAll(){
        logger.info("Finding all persons");
        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person){
        logger.info("Creating person: " + person);


        return person;
    }

    public Person update(Person person){
        logger.info("Updating person: " + person);
        return person;
    }

    public void delete(String id){
        logger.info("Deleting person: " + id);
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName :" + i);
        person.setLastName("LastName :" + i);
        person.setAddress("Some address in brazil");
        person.setGender("Male: " + i);

        return person;
    }
}
