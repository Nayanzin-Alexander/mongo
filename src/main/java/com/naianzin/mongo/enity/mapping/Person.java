package com.naianzin.mongo.enity.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static java.lang.System.currentTimeMillis;

@Document("Person")
public record Person(
        @Id String id,
        String firstName,
        String lastName,
        int age,
        Address address,
        List<Email> emails) {

    public static Person generate(long salt, int age) {
        var now = currentTimeMillis();
        return new Person(
                null,
                "first_name_" + salt,
                "last_name_" + salt,
                age,
                Address.generate(salt),
                List.of(Email.generate(1), Email.generate(2), Email.generate(3)));
    }
}
