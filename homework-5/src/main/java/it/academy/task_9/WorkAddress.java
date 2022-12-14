package it.academy.task_9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class WorkAddress implements IAddress {
    private final String street;
    private final Integer homeNumber;
    private final List<String> phoneNumbers;
}
