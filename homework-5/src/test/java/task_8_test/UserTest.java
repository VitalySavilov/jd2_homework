package task_8_test;

import it.academy.task_8.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:task_8.beans.xml"})
public class UserTest {
    @Autowired
    User user;

    @Test
    public void testCreatePerson() {
        assertNotNull(user);
        assertEquals("Petr", user.getName());
        assertEquals("Petrov", user.getSurname());
        assertEquals("Minsk", user.getContact().getCity());
        assertEquals("petr@gmail.com", user.getContact().getEmail());
        assertEquals("+375293857649", user.getContact().getTelephone());
    }
}
