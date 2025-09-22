package co.com.franchise.api;

import co.com.franchise.api.dto.FranchiseDTO;
import co.com.franchise.api.mapper.FranchiseMapper;
import co.com.franchise.usecase.franchise.FranchiseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {

    private final FranchiseUseCase franchiseUseCase;

    public Mono<ServerResponse> createFranchise(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FranchiseDTO.class)
                .map(FranchiseMapper.MAPPER::toModel)
                .flatMap(franchiseUseCase::create)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> updateFranchise(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FranchiseDTO.class)
                .map(FranchiseMapper.MAPPER::toModel)
                .flatMap(franchiseUseCase::update)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise))
                .onErrorResume(IllegalArgumentException.class, error -> ServerResponse.notFound().build());
    }
}
