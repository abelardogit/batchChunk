package com.abelardolg.service;

import com.abelardolg.entities.Person;

import java.util.List;

public interface IPersonService {

    Iterable<Person> saveAll(List<Person> personList);
}
