package br.com.vitortheof.services;

import br.com.vitortheof.data.dto.v1.PersonDTO;
import br.com.vitortheof.data.dto.v2.PersonDTOV2;
import br.com.vitortheof.exception.ResourceNotFoundException;
import br.com.vitortheof.mapper.custom.PersonMapper;
import br.com.vitortheof.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.vitortheof.repository.PersonRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.vitortheof.mapper.ObjectMapper.parseListObject;
import static br.com.vitortheof.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonDTO findById(Long id) {
        logger.info("Finding person with id: " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        return parseObject(entity, PersonDTO.class);

    }

    public List<PersonDTO> findByAll(){
        logger.info("Finding all persons");

        return parseListObject(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating person: " + person);

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person){

        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting person: " + id);
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("Creating person V2: " + person);

        var entity = mapper.convertDtoToEntity(person);

        return mapper.convertEntityToDto((repository.save(entity)));
    }
}
