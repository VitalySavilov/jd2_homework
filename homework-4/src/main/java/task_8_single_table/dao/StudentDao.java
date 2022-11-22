package task_8_single_table.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_single_table.model.Student;

public class StudentDao extends DaoBase<Integer, Student> {

    public StudentDao(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }

    public StudentDao() {
        super(MysqlSessionFactory.getInstance(), Student.class);
    }
}
