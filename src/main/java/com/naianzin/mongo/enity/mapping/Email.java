package com.naianzin.mongo.enity.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Email")
public record Email(
        @Id String id,
        String email) {

    public static Email generate(long salt) {
        return new Email(null,"email_" + salt);
    }
}
