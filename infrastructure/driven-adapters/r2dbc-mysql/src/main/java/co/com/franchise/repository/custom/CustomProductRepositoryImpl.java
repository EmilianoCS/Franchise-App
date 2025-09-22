package co.com.franchise.repository.custom;

import co.com.franchise.model.ProductData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final DatabaseClient client;

    @Override
    public Flux<ProductData> findMaxStockByIdFranchise(Long idFranchise) {
        String sql = """
                SELECT p.*
                FROM products p
                JOIN (
                    SELECT ID_BRANCH, MAX(STOCK) AS MAX_STOCK
                    FROM products
                    GROUP BY ID_BRANCH
                ) AS maxp ON p.ID_BRANCH = maxp.ID_BRANCH AND p.STOCK = maxp.MAX_STOCK
                JOIN branches b ON p.ID_BRANCH = b.id
                WHERE b.ID_FRANCHISE = :id_franchise;
                """;

        return client.sql(sql)
                .bind("id_franchise", idFranchise)
                .map(row -> ProductData.builder()
                        .id(row.get("ID", Long.class))
                        .name(row.get("NAME", String.class))
                        .stock(row.get("STOCK", Integer.class))
                        .idBranch(row.get("ID_BRANCH", Long.class))
                        .build())
                .all();
    }
}
