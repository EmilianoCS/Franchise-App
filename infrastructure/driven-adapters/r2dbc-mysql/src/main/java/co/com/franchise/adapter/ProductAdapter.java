package co.com.franchise.adapter;

import co.com.franchise.mapper.ProductMapper;
import co.com.franchise.model.product.Product;
import co.com.franchise.model.product.gateways.ProductRepository;
import co.com.franchise.repository.MyProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductRepository {

    private final MyProductRepository productRepository;

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(ProductMapper.MAPPER.modelToData(product))
                .map(ProductMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Save Product :: ERROR", error))
                .doOnSuccess(success -> log.info("Save Product :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Save Product :: Request {}", product));
    }

    @Override
    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Get Product :: ERROR", error))
                .doOnSuccess(success -> log.info("Get Product :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Get Product :: Request {}", id));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productRepository.deleteById(id)
                .doOnError(error -> log.error("Get Product :: ERROR", error))
                .doOnSuccess(success -> log.info("Delete Product :: Response {}", success))
                .doOnSubscribe(subscription -> log.info("Delete Product :: Request {}", id));
    }

    @Override
    public Flux<Product> getMaxStockByFranchise(Long idFranchise) {
        return productRepository.findMaxStockByIdFranchise(idFranchise)
                .map(ProductMapper.MAPPER::dataToModel)
                .doOnError(error -> log.error("Get Max Stock :: ERROR", error))
                .doOnSubscribe(subscription -> log.info("Get Max Stock :: Request {}", idFranchise));
    }
}
