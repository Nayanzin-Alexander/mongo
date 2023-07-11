package com.naianzin.mongo.enity.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Address")
public record Address (
        @Id String id,
        String address,
        String city,
        String province,
        String country,
        String zip
){
    public static Address generate(long salt) {
        return new Address(null,
                "King St N " + salt,
                "Waterloo_" + salt,
                "ON",
                "Canada",
                "N2D 1K5");
    }
}
