package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {


}
