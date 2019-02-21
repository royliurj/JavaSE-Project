//package com.roy.javase;
//
//import com.roy.javase.mapstruct.EmpMapper;
//import com.roy.javase.mapstruct.Employee;
//import com.roy.javase.mapstruct.EmployeeDTO;
//import org.junit.runner.RunWith;
//import org.mapstruct.factory.Mappers;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration
//public class Test {
//
//    @org.junit.Test
//    public void test(){
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setLastName("roy");
//        employee.setEmail("adsf@asd.com");
//        employee.setGender("1");
//
//
//
//        EmpMapper INSTANCE = Mappers.getMapper(EmpMapper.class);
//
//
//        EmployeeDTO employeeDTO = INSTANCE.entityToDTO(employee);
//
//        System.out.println(employeeDTO);
//
//        Employee employee1 = new Employee();
//
//        INSTANCE.updateEntityFromDto(employeeDTO,employee1);
//        System.out.println(employee1);
//    }
//
//    @org.junit.Test
//    public void test1(){
//        List<Employee> list = new ArrayList<>();
//        list.add(new Employee(1,"1","1","1"));
//        list.add(new Employee(2,"2","2","2"));
//        list.add(new Employee(3,"3","3","3"));
//
//        EmpMapper INSTANCE = Mappers.getMapper(EmpMapper.class);
//        List<EmployeeDTO> aaa = INSTANCE.aaa(list);
//        System.out.println(aaa);
//
//    }
//}
