package co.com.franchise.api.mapper;

import co.com.franchise.api.dto.BranchDTO;
import co.com.franchise.model.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper MAPPER = Mappers.getMapper(BranchMapper.class);

    Branch toModel(BranchDTO branchDTO);
}
