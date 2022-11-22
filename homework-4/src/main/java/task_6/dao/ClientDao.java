package task_6.dao;

import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_6.dao.printable.DaoPrintable;
import task_6.model.Client;

public class ClientDao extends DaoPrintable<Integer, Client> {

    public ClientDao(SessionFactory sessionFactory) {
        super(sessionFactory, Client.class);
    }

    public ClientDao() {
        super(MysqlSessionFactory.getInstance(), Client.class);
    }
}
