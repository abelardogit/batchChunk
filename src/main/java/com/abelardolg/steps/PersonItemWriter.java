package com.abelardolg.steps;

import com.abelardolg.entities.Person;
import com.abelardolg.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class PersonItemWriter implements ItemWriter<Person> {
    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        chunk.forEach( person -> log.info(person.toString()));
        this.personService.saveAll((List<Person>) chunk);
    }
}
