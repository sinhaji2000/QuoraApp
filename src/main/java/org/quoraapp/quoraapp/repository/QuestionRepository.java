package org.quoraapp.quoraapp.repository;

import org.quoraapp.quoraapp.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

    Flux<Question>findByAuthorId(String authorId);
    Mono<Long> countByAuthorId(String authorId);


    @Query("{'$or' :  [{'title' :  {$regex:  ?0 , $options :  'i'}} , {'content' :   {$regex:  ?0 , $options :  'i'}}]}")
    Flux<Question>findByTitleOrContentContainingIgnoreCase(String  searchTerm, Pageable pageable);

    Flux<Question>findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursor , Pageable pageable );

    Flux<Question>findTop10ByOrderByCreatedAtAsc();
//    Flux<Question>findFeedForUser(String userId, int page, int size);
    @Query("{ 'authorId': { $in: ?0 } }")
    Flux<Question> findFeedForUser(List<String> userIds, Pageable pageable);


}
