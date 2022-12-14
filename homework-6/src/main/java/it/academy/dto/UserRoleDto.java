package it.academy.dto;

import it.academy.model.Role;
import lombok.Value;

@Value
public class UserRoleDto {
    Long id;
    String username;
    Role role;
}
