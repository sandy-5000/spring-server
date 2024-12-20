package com.darkube.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.darkube.server.models.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    @Bean
    MongoClient mongoClient() {
        String mongoUrl = System.getProperty("mongo");
        if (mongoUrl == null || mongoUrl.isEmpty()) {
            throw new IllegalArgumentException("System property 'mongo' is not set.");
        }
        return MongoClients.create(mongoUrl);
    }

    @Bean
    MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "darkube_x");
    }

    @Bean
    boolean createIndexes(MongoTemplate mongoTemplate) {

        User.createIndexes(mongoTemplate);
        return true;

    }

}
