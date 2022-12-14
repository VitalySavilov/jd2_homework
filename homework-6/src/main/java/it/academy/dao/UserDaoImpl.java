package it.academy.dao;

import it.academy.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Override
    public User saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

    @Override
    public User getById(Long id) {
        User user;
        user = sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

}
