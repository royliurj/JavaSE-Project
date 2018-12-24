package com.roy.javase.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Mappings({})
    EmployeeDTO entityToDTO(Employee entity);

    @Mappings({})
    List<EmployeeDTO> aaa(List<Employee> list);

    @Mappings({})
    void updateEntityFromDto(EmployeeDTO peopleDTO, @MappingTarget Employee entity);
}
