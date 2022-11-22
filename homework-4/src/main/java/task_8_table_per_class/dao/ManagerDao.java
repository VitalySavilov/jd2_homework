package task_8_table_per_class.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_table_per_class.model.Manager;

public class ManagerDao extends DaoBase<Integer, Manager> {

    public ManagerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Manager.class);
    }

    public ManagerDao() {
        super(MysqlSessionFactory.getInstance(), Manager.class);
    }
}
