package co.com.franchise.adapter;

import co.com.franchise.mapper.FranchiseMapper;
import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.repository.MyFranchiseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FranchiseAdapter implements FranchiseRepository {

    private final MyFranchiseRepository franchiseRepository;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return franchiseRepository.save(FranchiseMapper.MAPPER.modelToData(franchise))
                .map(FranchiseMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Save Franchise :: ERROR", error))
                .doOnSuccess(success -> log.info("Save Franchise :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Save Franchise :: Request {}", franchise));
    }

    @Override
    public Mono<Franchise> getById(Long id) {
        return franchiseRepository.findById(id)
                .map(FranchiseMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Get Franchise :: ERROR", error))
                .doOnSuccess(success -> log.info("Get Franchise :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Get Franchise :: Request {}", id));
    }
}
