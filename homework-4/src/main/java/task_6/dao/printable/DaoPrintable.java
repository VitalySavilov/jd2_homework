package task_6.dao.printable;

import base.dao.DaoBase;
import base.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public abstract class DaoPrintable<K extends Serializable, E extends BaseEntity<K>>
        extends DaoBase<K, E> implements Printable<K, E> {

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    public DaoPrintable(SessionFactory sessionFactory, Class<E> clazz) {
        super(sessionFactory, clazz);
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public void savePrintId(E entity) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(entity);
            sess.flush();
            System.out.println("Entity class: " + clazz.getName());
            System.out.println("Persistent entity ID: " + sess.getIdentifier(entity));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
