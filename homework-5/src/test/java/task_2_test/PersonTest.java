package task_2_test;

import it.academy.task_2.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonTest {

    @Test
    public void testCreatePerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task_2.beans.xml");
        Person person = context.getBean("person", Person.class);
        assertNotNull(person);
        assertEquals("Ivan", person.getName());
        assertEquals("Ivanov", person.getSurname());
        context.close();
    }
}
