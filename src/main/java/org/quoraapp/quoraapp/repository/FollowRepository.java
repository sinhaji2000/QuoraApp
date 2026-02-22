package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.Follow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FollowRepository extends ReactiveMongoRepository<Follow , String> {

    Flux<Follow> findByFollowerId(String followerId);
}
