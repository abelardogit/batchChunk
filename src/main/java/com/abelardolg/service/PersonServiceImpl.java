package com.abelardolg.service;

import com.abelardolg.entities.Person;
import com.abelardolg.persistence.IPersonDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{

    private final IPersonDAO personDAO;

    public PersonServiceImpl(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public Iterable<Person> saveAll(List<Person> personList) {
        return this.personDAO.saveAll(personList);
    }
}
