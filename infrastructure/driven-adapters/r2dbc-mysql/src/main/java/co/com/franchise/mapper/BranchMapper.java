package co.com.franchise.mapper;

import co.com.franchise.model.BranchData;
import co.com.franchise.model.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper MAPPER = Mappers.getMapper(BranchMapper.class);

    BranchData modelToData(Branch branch);

    Branch dataToModel(BranchData branchData);
}
