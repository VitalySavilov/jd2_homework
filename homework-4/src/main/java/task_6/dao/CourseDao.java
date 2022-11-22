package task_6.dao;

import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_6.dao.printable.DaoPrintable;
import task_6.model.Course;

public class CourseDao extends DaoPrintable<Integer, Course> {

    public CourseDao(SessionFactory sessionFactory) {
        super(sessionFactory, Course.class);
    }

    public CourseDao() {
        super(MysqlSessionFactory.getInstance(), Course.class);
    }
}
