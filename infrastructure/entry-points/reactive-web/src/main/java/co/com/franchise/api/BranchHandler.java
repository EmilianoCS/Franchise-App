package co.com.franchise.api;

import co.com.franchise.api.dto.BranchDTO;
import co.com.franchise.api.mapper.BranchMapper;
import co.com.franchise.usecase.branch.BranchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchHandler {

    private final BranchUseCase branchUseCase;

    public Mono<ServerResponse> createBranch(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BranchDTO.class)
                .map(BranchMapper.MAPPER::toModel)
                .flatMap(branchUseCase::save)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> updateBranch(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BranchDTO.class)
                .map(BranchMapper.MAPPER::toModel)
                .flatMap(branchUseCase::update)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise))
                .onErrorResume(IllegalArgumentException.class, error -> ServerResponse.notFound().build());
    }
}
