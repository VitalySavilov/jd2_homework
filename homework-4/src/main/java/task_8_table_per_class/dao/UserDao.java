package task_8_table_per_class.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_table_per_class.model.User;

public class UserDao extends DaoBase<Integer, User> {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    public UserDao() {
        super(MysqlSessionFactory.getInstance(), User.class);
    }
}
