package co.com.franchise.adapter;

import co.com.franchise.mapper.BranchMapper;
import co.com.franchise.model.branch.Branch;
import co.com.franchise.model.branch.gateways.BranchRepository;
import co.com.franchise.repository.MyBranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BranchAdapter implements BranchRepository {

    private final MyBranchRepository branchRepository;

    @Override
    public Mono<Branch> save(Branch branch) {
        return branchRepository.save(BranchMapper.MAPPER.modelToData(branch))
                .map(BranchMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Save Branch :: ERROR", error))
                .doOnSuccess(success -> log.info("Save Branch :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Save Branch :: Request {}", branch));
    }

    @Override
    public Mono<Branch> getById(Long id) {
        return branchRepository.findById(id)
                .map(BranchMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Get Branch :: ERROR", error))
                .doOnSuccess(success -> log.info("Get Branch :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Get Branch :: Request {}", id));
    }
}
