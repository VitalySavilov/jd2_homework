package it.academy.task_10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {
    @Autowired
    EmployeeDepartmentMapper mapper;

    public List<Employee> createEmployee(List<EmployeeDepartmentDto> list) {
        return list.stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }
}
