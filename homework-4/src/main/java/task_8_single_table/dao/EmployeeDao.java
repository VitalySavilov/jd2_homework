package task_8_single_table.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_single_table.model.Employee;

public class EmployeeDao extends DaoBase<Integer, Employee> {

    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory, Employee.class);
    }

    public EmployeeDao() {
        super(MysqlSessionFactory.getInstance(), Employee.class);
    }
}
