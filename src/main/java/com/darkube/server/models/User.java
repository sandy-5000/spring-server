package com.darkube.server.models;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

enum UserRole {
    USER, ADMIN, SUPER_ADMIN
}

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

    @Field(name = "user_role", targetType = FieldType.STRING)
    private UserRole userRole = UserRole.USER;

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

    public HashMap<String, Object> entries() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("_id", get_id());
        map.put("username", getUsername());
        map.put("email", getEmail());
        map.put("name", getName());
        map.put("user_role", getUserRole().toString());
        return map;
    }

}
