package co.com.franchise.repository;

import co.com.franchise.model.ProductData;
import co.com.franchise.repository.custom.CustomProductRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MyProductRepository extends ReactiveCrudRepository<ProductData, Long>, CustomProductRepository {
}
