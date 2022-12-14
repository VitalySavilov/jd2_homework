package it.academy.mapper;

import it.academy.dto.UserRoleDto;
import it.academy.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements Mapper<User, UserRoleDto> {

    @Override
    public UserRoleDto mapFrom(User object) {
        return new UserRoleDto(object.getId(),
                object.getUsername(),
                object.getRole());
    }
}
