package by.it.dao;

import by.it.MysqlSessionFactory;
import by.it.pojos.PersonUpdate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PersonUpdateDaoImpl implements PersonUpdateDao {
    private final SessionFactory sessionFactory;
    private final Logger log = LoggerFactory.getLogger(PersonUpdateDaoImpl.class);

    public PersonUpdateDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public PersonUpdateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PersonUpdate> findAll() {
        try (Session sess = sessionFactory.openSession()) {
            return sess.createQuery("select u from PersonUpdate u", PersonUpdate.class).list();
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
        return null;
    }
}
