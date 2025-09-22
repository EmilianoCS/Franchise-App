package co.com.franchise.repository;

import co.com.franchise.model.BranchData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MyBranchRepository extends ReactiveCrudRepository<BranchData, Long> {
}
