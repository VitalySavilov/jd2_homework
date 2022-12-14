package task_9_test;

import it.academy.task_9.Client;
import it.academy.task_9.IAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:task_9.beans.xml"})
public class ClientTest {
    @Autowired
    Client client;

    @Autowired
    @Qualifier("workAddress")
    IAddress address;

    @Test
    public void testCreatePerson() {
        assertNotNull(client);
        assertEquals("Denis", client.getName());
        assertEquals("Denisov", client.getSurname());
        assertEquals("Pushkina", client.getAddress().getStreet());
        assertEquals(12, (int) client.getAddress().getHomeNumber());
        assertEquals(1, client.getAddress().getPhoneNumbers().size());

        client.setAddress(address);

        assertEquals("Esenina", client.getAddress().getStreet());
        assertEquals(55, (int) client.getAddress().getHomeNumber());
        assertEquals(2, client.getAddress().getPhoneNumbers().size());
    }
}
