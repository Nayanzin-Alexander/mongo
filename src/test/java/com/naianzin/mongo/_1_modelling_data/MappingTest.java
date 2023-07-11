package com.naianzin.mongo._1_modelling_data;

import com.naianzin.mongo.enity.mapping.Address;
import com.naianzin.mongo.enity.mapping.Email;
import com.naianzin.mongo.enity.mapping.PersonMapped;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MappingTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void saveMappedTest() {
        var salt = System.currentTimeMillis();

        // Save address
        var address = mongoTemplate.save(Address.generate(salt));

        // Save emails
        var emails = List.of(
                mongoTemplate.save(Email.generate(1)),
                mongoTemplate.save(Email.generate(2)),
                mongoTemplate.save(Email.generate(3)));

        // Save person
        mongoTemplate.save(PersonMapped.generate(salt, address, emails));
    }
}
