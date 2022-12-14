package it.academy.task_8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Contact {
    private final String city;
    private final String email;
    private final String telephone;
}
