package co.com.franchise.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class FranchiseDTO {

    Long id;
    String name;
}
