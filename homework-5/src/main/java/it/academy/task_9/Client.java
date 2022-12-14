package it.academy.task_9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Getter
@Setter
@RequiredArgsConstructor
public class Client {
    private final String name;
    private final String surname;
    @Autowired
    @Qualifier("homeAddress")
    private IAddress address;
}
