package com.ali.personapi.service;

import com.ali.personapi.dto.request.PersonDTO;
import com.ali.personapi.dto.response.MessageResponseDTO;
import com.ali.personapi.entity.Person;
import com.ali.personapi.exception.PersonNotFoundException;
import com.ali.personapi.mapper.PersonMapper;
import com.ali.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(@Valid PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savePerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        //Optional<Person> optionalPerson = personRepository.findById(id);

//        if (optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
        return personMapper.toDTO(person);

    }
}
