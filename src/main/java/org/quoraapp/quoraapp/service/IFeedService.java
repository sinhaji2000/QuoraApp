package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface IFeedService {

    public Flux<QuestionResponseDTO> getFeed(String userId, Pageable pageable) ;
}
