package com.naianzin.mongo._1_modelling_data;

import com.naianzin.mongo.enity.datatypes.DataTypesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DataTypesTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void saveDataTypesTest() {
        mongoTemplate.save(DataTypesEntity.generate());
    }

}