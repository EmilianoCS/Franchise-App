package co.com.franchise.api.mapper;

import co.com.franchise.api.dto.FranchiseDTO;
import co.com.franchise.model.franchise.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FranchiseMapper {

    FranchiseMapper MAPPER = Mappers.getMapper(FranchiseMapper.class);

    Franchise toModel(FranchiseDTO franchiseDTO);
}
