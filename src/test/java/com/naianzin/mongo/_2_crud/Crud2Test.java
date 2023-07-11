package com.naianzin.mongo._2_crud;

import com.naianzin.mongo.enity.mapping.Person;
import com.naianzin.mongo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Crud2Test {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void clean() {
        personRepository.deleteAll();
    }

    @Test
    void selectTest() {
        mongoTemplate.find(new BasicQuery(""), Person.class);




    }


}
