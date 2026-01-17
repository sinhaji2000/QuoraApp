package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
