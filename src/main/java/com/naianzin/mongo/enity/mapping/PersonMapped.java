package com.naianzin.mongo.enity.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

import static java.lang.System.currentTimeMillis;

@Data
@NoArgsConstructor
@Document("Person")
@AllArgsConstructor
public class PersonMapped {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @DocumentReference
    private Address address;
    @DocumentReference
    private List<Email> emails;

    public static PersonMapped generate(long salt, Address address, List<Email> emails) {
        var now = currentTimeMillis();
        return new PersonMapped(
                null,
                "first_name_" + salt,
                "last_name_" + salt,
                address,
                emails);
    }
}
