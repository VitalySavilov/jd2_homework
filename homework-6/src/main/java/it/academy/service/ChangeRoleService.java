package it.academy.service;

import it.academy.dao.UserDao;
import it.academy.dto.UserRoleDto;
import it.academy.mapper.Mapper;
import it.academy.model.Role;
import it.academy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeRoleService {
    private final UserDao userDao;
    private final Mapper<User, UserRoleDto> mapper;

    public UserRoleDto changeAdminRole(Long id) {
        User user = userDao.getById(id);
        user.setRole(Role.ADMIN);
        return mapper.mapFrom(userDao.saveOrUpdate(user));
    }

    public UserRoleDto changeUserRole(Long id) {
        User user = userDao.getById(id);
        user.setRole(Role.USER);
        return mapper.mapFrom(userDao.saveOrUpdate(user));
    }
}
