package com.naianzin.mongo._1_modelling_data;

import com.naianzin.mongo.enity.mapping.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmbeddedTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void saveEmbeddedTest() {
        var salt = System.currentTimeMillis();
        mongoTemplate.save(Person.generate(salt, 37));
    }
}
