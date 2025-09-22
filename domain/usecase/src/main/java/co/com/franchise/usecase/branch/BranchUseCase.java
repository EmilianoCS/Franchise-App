package co.com.franchise.usecase.branch;

import co.com.franchise.model.branch.Branch;
import co.com.franchise.model.branch.gateways.BranchRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BranchUseCase {

    private final BranchRepository branchRepository;

    public Mono<Branch> save(Branch branch) {
        return branchRepository.save(branch);
    }

    public Mono<Branch> update(Branch branch) {
        return branchRepository.getById(branch.getId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("NOT FOUND")))
                .map(branchFound -> branchFound.toBuilder()
                        .name(branch.getName())
                        .build())
                .flatMap(branchRepository::save);
    }
}
