package task_6.dao;

import datasource.MysqlSessionFactory;
import org.hibernate.SessionFactory;
import task_6.dao.printable.DaoPrintable;
import task_6.model.Payment;

public class PaymentDao extends DaoPrintable<Integer, Payment> {

    public PaymentDao(SessionFactory sessionFactory) {
        super(sessionFactory, Payment.class);
    }

    public PaymentDao() {
        super(MysqlSessionFactory.getInstance(), Payment.class);
    }
}
