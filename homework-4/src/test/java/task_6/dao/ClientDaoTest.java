package task_6.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_6.model.Client;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class ClientDaoTest extends BaseDaoTest {
    ClientDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new ClientDao(testSessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void saveOrUpdate() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_client;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Client client1 = Client.builder()
                .name("Denis")
                .surname("Denisov")
                .build();
        Client client2 = Client.builder()
                .name("Fedor")
                .surname("Fedorov")
                .build();
        Client client3 = Client.builder()
                .name("Ivan")
                .surname("Ivanov")
                .build();

        //When
        targetObject.saveOrUpdate(client1);
        targetObject.saveOrUpdate(client2);
        targetObject.saveOrUpdate(client3);

        //Then
        assertTrue(client1.getId() + 1 == client2.getId()
                && client2.getId() + 1 == client3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from t_client;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table t_client;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ClientDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Client client = targetObject.findById(401);
        assertNotNull(client);

        //Then
        assertEquals("Stepan", client.getName());
        assertEquals("Stepanov", client.getSurname());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ClientDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_client;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(401));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_client;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}