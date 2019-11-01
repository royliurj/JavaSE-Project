package com.roy.javase.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-31T13:21:29+0800",
    comments = "version: 1.3.0.Beta1, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class EmpMapperImpl implements EmpMapper {

    @Override
    public EmployeeDTO entityToDTO(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( entity.getId() );
        employeeDTO.setLastName( entity.getLastName() );
        employeeDTO.setGender( entity.getGender() );
        employeeDTO.setEmail( entity.getEmail() );

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> aaa(List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeDTO> list1 = new ArrayList<EmployeeDTO>( list.size() );
        for ( Employee employee : list ) {
            list1.add( entityToDTO( employee ) );
        }

        return list1;
    }

    @Override
    public void updateEntityFromDto(EmployeeDTO peopleDTO, Employee entity) {
        if ( peopleDTO == null ) {
            return;
        }

        entity.setId( peopleDTO.getId() );
        entity.setLastName( peopleDTO.getLastName() );
        entity.setGender( peopleDTO.getGender() );
        entity.setEmail( peopleDTO.getEmail() );
    }
}
