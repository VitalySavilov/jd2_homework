package by.it.dao;

import by.it.pojos.Person;

public interface PersonDao {
    void create(Person person);

    Person getById(long id);

    void delete(Person person);

    Person loadById(long id);

    void flush(long id, String name);

    void refresh(long id, String name);

    void delete();

    Person create();
}
