package task_7.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_7.model.Order;

public class OrderDao extends DaoBase<Integer, Order> {

    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory, Order.class);
    }

    public OrderDao() {
        super(MysqlSessionFactory.getInstance(), Order.class);
    }
}
