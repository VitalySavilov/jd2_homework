package task_8_table_per_class.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_table_per_class.model.Programmer;

public class ProgrammerDao extends DaoBase<Integer, Programmer> {

    public ProgrammerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Programmer.class);
    }

    public ProgrammerDao() {
        super(MysqlSessionFactory.getInstance(), Programmer.class);
    }
}
