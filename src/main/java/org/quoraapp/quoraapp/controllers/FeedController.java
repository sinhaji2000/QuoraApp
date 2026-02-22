package org.quoraapp.quoraapp.controllers;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.dto.QuestionResponseDTO;
import org.quoraapp.quoraapp.model.Question;
import org.quoraapp.quoraapp.service.FeedService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/{userId}")
    public Flux<QuestionResponseDTO> getFeed(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        return feedService.getFeed(userId, pageable);
    }
}
