package by.it.dao;

import by.it.MysqlSessionFactory;
import by.it.pojos.Person;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDaoImpl implements PersonDao {

    private final SessionFactory sessionFactory;
    private final Logger log = LoggerFactory.getLogger(PersonDaoImpl.class);

    public PersonDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Person person) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(person);
            log.info(person.toString());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Error in DAO " + e);
        }
    }

    @Override
    public Person getById(long id) {
        Person person = null;
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            person = sess.get(Person.class, id);
            System.out.println("Person ID: " + person.getId());
            log.info(person.toString());
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
        return person;
    }

    @Override
    public void delete(Person person) {
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            sess.delete(person);
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
    }

    @Override
    public Person loadById(long id) {
        Person person = null;
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            person = sess.load(Person.class, id);
            System.out.println("Person ID: " + person.getId());
            log.info(person.toString());
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
        return person;
    }

    @Override
    public void flush(long id, String name) {
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            Person person = sess.get(Person.class, id);
            log.info(person.toString());
            person.setName(name);
            log.info(person.toString());
            sess.flush();
            log.info(person.toString());
            sess.refresh(person);
            log.info(person.toString());
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
    }

    @Override
    public void refresh(long id, String name) {
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            Person person = sess.get(Person.class, id);
            log.info(person.toString());
            person.setName(name);
            log.info(person.toString());
            sess.refresh(person);
            log.info(person.toString());
            sess.getTransaction().commit();
            sess.lock(person, LockMode.NONE);
        } catch (Exception e) {
            log.error("Error in DAO " + e);
        }
    }

    @Override
    public void delete() {
        delete(create());
    }

    @Override
    public Person create() {
        Person person = Person.builder().build();
        create(person);
        return person;
    }
}
