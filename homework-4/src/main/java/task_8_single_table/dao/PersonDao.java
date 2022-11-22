package task_8_single_table.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_single_table.model.Person;

public class PersonDao extends DaoBase<Integer, Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory, Person.class);
    }

    public PersonDao() {
        super(MysqlSessionFactory.getInstance(), Person.class);
    }
}
