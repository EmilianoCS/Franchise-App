package co.com.franchise.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(FranchiseHandler franchiseHandler,
                                                         BranchHandler branchHandler,
                                                         ProductHandler productHandler) {
        return route()
                .POST("/api/v1/franchise", franchiseHandler::createFranchise)
                .PUT("/api/v1/franchise", franchiseHandler::updateFranchise)
                .POST("/api/v1/branch", branchHandler::createBranch)
                .PUT("/api/v1/branch", branchHandler::updateBranch)
                .POST("/api/v1/product", productHandler::createProduct)
                .PUT("/api/v1/product", productHandler::updateProduct)
                .DELETE("/api/v1/product/{id}", productHandler::deleteProduct)
                .GET("/api/v1/product/{id}", productHandler::getMaxStockByBranch)
                .build();
    }
}
