package org.quoraapp.quoraapp.service;


import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.mapper.QuestionMapper;

import org.quoraapp.quoraapp.repository.QuestionRepository;
import org.quoraapp.quoraapp.repository.UserRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FeedService implements IFeedService {

    private final RedisTemplate<String , Object> redisTemplate;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;


    public Flux<QuestionResponseDTO>  getFeed(String userId, Pageable pageable) {
        String key = "feed:user:" + userId + ":page:" + pageable.getPageNumber();

        Object cached = redisTemplate.opsForValue().get(key);
        if (cached != null) {
            return Flux.fromIterable((List<QuestionResponseDTO>) cached);
        }

        System.out.println("CACHE MISS ❌ Fetching from Mongo...");

        return userRepository.findById(userId)
                .flatMapMany(user -> {

                    List<String> followingIds = user.getFollwing();

                    if (followingIds == null || followingIds.isEmpty()) {
                        return Flux.empty();
                    }

                    return questionRepository
                            .findFeedForUser(
                                    followingIds,
                                    pageable
                            );
                })
                .map(QuestionMapper::toQuestionResponseDTO)
                .collectList()
                .doOnNext(list -> redisTemplate.opsForValue()
                        .set(key, list, Duration.ofMinutes(5)))
                .flatMapMany(Flux::fromIterable);
    }

}


