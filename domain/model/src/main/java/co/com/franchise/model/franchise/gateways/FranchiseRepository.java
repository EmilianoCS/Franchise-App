package co.com.franchise.model.franchise.gateways;

import co.com.franchise.model.franchise.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {

    Mono<Franchise> save(Franchise franchise);

    Mono<Franchise> getById(Long id);
}
