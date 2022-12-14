package it.academy.task_8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@RequiredArgsConstructor
public class User {
    private final String name;
    private final String surname;
    @Autowired
    private Contact contact;
}
