package com.darkube.server.services.collections;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.darkube.server.models.User;
import com.darkube.server.repositories.UserRepository;
import com.darkube.server.services.PasswordService;
import com.darkube.server.types.LoginBody;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> get(String username) {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.fields().exclude("passwd");
        User user = mongoTemplate.findOne(query, User.class);
        return Optional.ofNullable(user);

    }

    public User save(User user) {

        final String hashed = PasswordService.hashPassword(
                user.getPasswd(),
                user.getUsername());
        user.setPasswd(hashed);
        userRepository.save(user);
        user.setPasswd(null);
        return user;

    }

    public Optional<User> login(LoginBody body) {

        Optional<User> user = userRepository.login(body.handler, body.handler);
        if (user.isPresent()
                && PasswordService.verifyPassword(body.passwd,
                        user.get().getUsername(),
                        user.get().getPasswd())) {
            user.get().setPasswd(null);
        } else {
            user = Optional.empty();
        }
        return user;

    }

}
