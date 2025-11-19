package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

    Flux<Question>findByAuthorId(String authorId);
    Mono<Long> countByAuthorId(String authorId);


    @Query("{'$or' :  [{'title' :  {$regex:  ?0 , $option :  'i'}} , {'content' :   {$regex:  ?0 , $option :  'i'}}]}")
    Flux<Question>findByTitleOrContentContainingIgnoreCase(String  searchTerm, Pageable pageable);
}
