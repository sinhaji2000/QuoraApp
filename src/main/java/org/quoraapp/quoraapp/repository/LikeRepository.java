package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository  extends ReactiveMongoRepository<Like, String> {
}
