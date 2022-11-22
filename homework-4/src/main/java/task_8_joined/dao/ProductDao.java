package task_8_joined.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_joined.model.Product;

public class ProductDao extends DaoBase<Integer, Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory, Product.class);
    }
    public ProductDao() {
        super(MysqlSessionFactory.getInstance(), Product.class);
    }
}
