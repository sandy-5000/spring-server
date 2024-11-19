package com.darkube.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    public String _id;

    @Indexed(unique = true)
    public String username;

    @Indexed(unique = true)
    public String email;

    public String name;
    public String passwd;

}
