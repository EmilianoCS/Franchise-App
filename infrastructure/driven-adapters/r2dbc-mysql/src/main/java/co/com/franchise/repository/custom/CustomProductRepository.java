package co.com.franchise.repository.custom;

import co.com.franchise.model.ProductData;
import reactor.core.publisher.Flux;

public interface CustomProductRepository {

    Flux<ProductData> findMaxStockByIdFranchise(Long idFranchise);
}
