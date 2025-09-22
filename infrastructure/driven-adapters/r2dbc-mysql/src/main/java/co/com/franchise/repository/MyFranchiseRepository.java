package co.com.franchise.repository;

import co.com.franchise.model.FranchiseData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MyFranchiseRepository extends ReactiveCrudRepository<FranchiseData, Long> {
}
