package it.academy.task_10;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Department {
    private String depName;
    private String location;
}
