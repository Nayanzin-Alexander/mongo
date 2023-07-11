package com.naianzin.mongo.enity.datatypes;

import com.naianzin.mongo.MongoApplication;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.*;

import static java.lang.System.currentTimeMillis;
import static org.springframework.data.mongodb.core.mapping.FieldType.*;

@Document("DataTypes")
public record DataTypesEntity (
        @Id @Field(targetType = STRING) String id,
        @Field(targetType = DOUBLE) double doubleField,
        @Field(targetType = STRING) String stringField,
        @Field(targetType = IMPLICIT) Object objectField,
        @Field(targetType = ARRAY) int[] arrayField,
        @Field(targetType = BINARY) byte[] binaryDataField,
        @Field(targetType = BOOLEAN) boolean booleanField,
        @Field(targetType = STRING) LocalDate localDateField,
        @Field(targetType = DATE_TIME) LocalDateTime localDateTimeField,
        @Field(targetType = PATTERN) String regexFieldField,
        @Field(targetType = SCRIPT) String javaScriptField,
        @Field(targetType = INT32) int integerField,
        @Field(targetType = TIMESTAMP) long timestampField,
        @Field(targetType = INT64) long longField,
        @Field(targetType = DECIMAL128) BigDecimal decimal128Field,
        @CreatedDate LocalDateTime createdDate,
        @LastModifiedDate LocalDateTime lastModifiedDate,
        @CreatedBy String createdByUser,
        @LastModifiedBy String modifiedByUser
){
    public static DataTypesEntity generate() {
        var now = currentTimeMillis();
        return new DataTypesEntity(
                null,
                now * 0.5D,
                "string_" + now,
                new MongoApplication(),
                new int[] {1, 2, 3},
                String.valueOf(now).getBytes(StandardCharsets.UTF_8),
                now % 2 == 0,
                LocalDate.now(),
                LocalDateTime.now(),
                ".*",
                "???",
                (int) now,
                now,
                now,
                new BigDecimal(now / 1000.3333D),
                null,
                null,
                null,
                null
        );
    }
}
