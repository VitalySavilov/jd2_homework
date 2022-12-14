package it.academy.dao;


import it.academy.model.User;

public interface UserDao {
    User saveOrUpdate(User user);
    User getById(Long id);
    void delete(User user);
}
