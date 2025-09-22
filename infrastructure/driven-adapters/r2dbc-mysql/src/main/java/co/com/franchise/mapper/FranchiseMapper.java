package co.com.franchise.mapper;

import co.com.franchise.model.FranchiseData;
import co.com.franchise.model.franchise.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FranchiseMapper {

    FranchiseMapper MAPPER = Mappers.getMapper(FranchiseMapper.class);

    FranchiseData modelToData(Franchise franchise);

    Franchise dataToModel(FranchiseData franchiseData);
}
