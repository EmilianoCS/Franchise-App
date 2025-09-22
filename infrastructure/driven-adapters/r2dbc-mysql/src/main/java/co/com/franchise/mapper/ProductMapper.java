package co.com.franchise.mapper;

import co.com.franchise.model.ProductData;
import co.com.franchise.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductData modelToData(Product product);

    Product dataToModel(ProductData productData);
}
