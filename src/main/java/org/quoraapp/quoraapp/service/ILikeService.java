package org.quoraapp.quoraapp.service;

import org.quoraapp.quoraapp.dto.LikeRequestDTO;
import org.quoraapp.quoraapp.dto.LikeResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {

    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO>countLikesByTargetIdAndTargetType(String targetId, String targetType);
    Mono<LikeResponseDTO>countDislikesByTargetIdAndTargetType(String targetId, String targetType);
    Mono<LikeResponseDTO> toggleLike(String targetId , String targetType);


}
