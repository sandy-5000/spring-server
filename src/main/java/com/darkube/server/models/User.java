package com.darkube.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String _id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String name;
    private String passwd;

    public static void createIndexes(MongoTemplate mongoTemplate) {

        Index[] indexes = {
                new Index().on("email", Direction.ASC).unique(),
                new Index().on("username", Direction.ASC).unique(),
                new Index().on("name", Direction.ASC),
        };

        for (Index index : indexes) {
            mongoTemplate.indexOps(User.class).ensureIndex(index);
        }

    }

}
