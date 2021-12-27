package com.exarep.customer.individual;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface CustomerIndividualMapper {

    CustomerIndividualEntity toEntity(CustomerIndividual domain);

    void toEntity(CustomerIndividual domain, @MappingTarget CustomerIndividualEntity entity);

    CustomerIndividual toDomain(CustomerIndividualEntity entity);

    void toDomain(CustomerIndividualEntity entity, @MappingTarget CustomerIndividual domain);

}
