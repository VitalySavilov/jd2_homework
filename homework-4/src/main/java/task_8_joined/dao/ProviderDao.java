package task_8_joined.dao;

import base.dao.DaoBase;
import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_8_joined.model.Provider;

public class ProviderDao extends DaoBase<Integer, Provider> {

    public ProviderDao(SessionFactory sessionFactory) {
        super(sessionFactory, Provider.class);
    }

    public ProviderDao() {
        super(MysqlSessionFactory.getInstance(), Provider.class);
    }
}
