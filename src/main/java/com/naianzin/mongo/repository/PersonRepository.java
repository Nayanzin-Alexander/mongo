package com.naianzin.mongo.repository;

import com.naianzin.mongo.enity.mapping.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
