package co.com.franchise.usecase.product;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductRepository productRepository;

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> update(Product product) {
        return productRepository.getById(product.getId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("NOT FOUND")))
                .map(productFound -> productFound.toBuilder()
                        .name(product.getName())
                        .stock(product.getStock())
                        .build())
                .flatMap(productRepository::save);
    }

    public Mono<Void> delete(Long id) {
        return productRepository.getById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("NOT FOUND")))
                .flatMap(product -> productRepository.deleteById(id));
    }

    public Mono<List<Product>> getMaxStock(Long idFranchise) {
        return productRepository.getMaxStockByFranchise(idFranchise)
                .collectList();
    }
}
