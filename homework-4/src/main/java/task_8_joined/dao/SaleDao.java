package task_8_joined.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_joined.model.Sale;

public class SaleDao extends DaoBase<Integer, Sale> {

    public SaleDao(SessionFactory sessionFactory) {
        super(sessionFactory, Sale.class);
    }

    public SaleDao() {
        super(MysqlSessionFactory.getInstance(), Sale.class);
    }
}
