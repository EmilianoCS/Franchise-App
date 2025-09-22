package co.com.franchise.api;

import co.com.franchise.api.dto.ProductDTO;
import co.com.franchise.api.mapper.ProductMapper;
import co.com.franchise.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductUseCase productUseCase;

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDTO.class)
                .map(ProductMapper.MAPPER::toModel)
                .flatMap(productUseCase::save)
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDTO.class)
                .map(ProductMapper.MAPPER::toModel)
                .flatMap(productUseCase::update)
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .onErrorResume(IllegalArgumentException.class, error -> ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return productUseCase.delete(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(IllegalArgumentException.class, error -> ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getMaxStockByBranch(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return productUseCase.getMaxStock(id)
                .flatMap(products -> ServerResponse.ok().bodyValue(products));
    }
}
