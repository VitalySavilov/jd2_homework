package it.academy.task_10;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    private String name;
    private String surname;
    private Department department;
}
