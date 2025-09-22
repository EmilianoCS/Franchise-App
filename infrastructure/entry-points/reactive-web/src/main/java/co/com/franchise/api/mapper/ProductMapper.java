package co.com.franchise.api.mapper;

import co.com.franchise.api.dto.ProductDTO;
import co.com.franchise.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDTO productDTO);
}
