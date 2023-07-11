package com.naianzin.mongo._2_crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CrudTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Document("Book")
    record Book(@Id String id, String title, String isbn) {
    }

    @BeforeEach
    void clean() {
        mongoTemplate.remove(Book.class).all();
    }

    @Test
    void crudTest() {
        // Save
        var id = mongoTemplate.save(new Book(null, "Mastering MongoDB", "101")).id;

        // Find by id
        var bookById = mongoTemplate.findById(id, Book.class);
        assertThat(bookById).isEqualTo(new Book(id, "Mastering MongoDB", "101"));

        // Find by title and isbn
        BasicQuery query = new BasicQuery("{ title : { $eq : 'Mastering MongoDB' }, isbn : { $eq : '101' } }");
        var booksByTitle = mongoTemplate.find(query, Book.class);
        assertThat(booksByTitle).contains(new Book(id, "Mastering MongoDB", "101"));

        // Update
        mongoTemplate.update(Book.class)
                .matching(query)
                .apply(new BasicUpdate("{ $set: { isbn: '102' } }"))
                .all();
        assertThat(mongoTemplate.findAll(Book.class)).contains(new Book(id, "Mastering MongoDB", "102"));

        // Remove
        var removeQuery = new BasicQuery("{ title: { $eq: 'Mastering MongoDB' } }");
        mongoTemplate.remove(Book.class)
                .matching(removeQuery)
                .all();
        assertThat(mongoTemplate.find(removeQuery, Book.class)).isEmpty();
    }

    @Test
    void testBulkInsert() {
        var books = Stream.iterate(0, i -> ++i)
                .limit(10_000)
                .map(i -> new Book(null, "name_" + i, "ISBN: " + i))
                .toList();
        mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Book.class)
                .insert(books)
                .execute();
        assertThat(mongoTemplate.findAll(Book.class)).hasSize(10_000);
    }
}
