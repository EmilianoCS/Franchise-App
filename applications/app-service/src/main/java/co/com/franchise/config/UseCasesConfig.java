package co.com.franchise.config;

import co.com.franchise.model.branch.gateways.BranchRepository;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.model.product.gateways.ProductRepository;
import co.com.franchise.usecase.branch.BranchUseCase;
import co.com.franchise.usecase.franchise.FranchiseUseCase;
import co.com.franchise.usecase.product.ProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public FranchiseUseCase franchiseUseCase(FranchiseRepository franchiseRepository) {
        return new FranchiseUseCase(franchiseRepository);
    }

    @Bean
    public BranchUseCase branchUseCase(BranchRepository branchRepository) {
        return new BranchUseCase(branchRepository);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCase(productRepository);
    }
}
