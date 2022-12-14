package it.academy.task_10;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDepartmentMapper {
    public Employee mapFrom(EmployeeDepartmentDto dto) {
        return Employee.builder()
                .name(dto.getEmployeeName())
                .surname(dto.getEmployeeSurname())
                .department(Department.builder()
                        .depName(dto.getDepartmentName())
                        .location(dto.getDepartmentLocation())
                        .build())
                .build();
    }
}
