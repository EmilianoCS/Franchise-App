package co.com.franchise.usecase.franchise;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FranchiseUseCase {

    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> create(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Mono<Franchise> update(Franchise franchise) {
        return franchiseRepository.getById(franchise.getId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("NOT FOUND")))
                .map(franchiseFound -> franchiseFound.toBuilder()
                        .name(franchise.getName())
                        .build())
                .flatMap(franchiseRepository::save);
    }
}
