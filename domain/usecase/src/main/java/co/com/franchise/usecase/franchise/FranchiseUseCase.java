package co.com.franchise.usecase.franchise;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Random;

@RequiredArgsConstructor
public class FranchiseUseCase {

    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> create(String franchiseName) {
        return franchiseRepository.save(Franchise.builder()
                .id(generateFranchiseId())
                .name(franchiseName)
                .build());
    }

    public Mono<Franchise> update(Franchise franchise) {
        return franchiseRepository.update(franchise);
    }

    private String generateFranchiseId() {
        long timestamp = Instant.now().getEpochSecond();
        int security = new Random().nextInt(90) + 10;

        return timestamp + "-" + security;
    }
}
