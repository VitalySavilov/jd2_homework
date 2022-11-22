package task_7.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_7.model.Address;
import task_7.model.ClientInfo;
import task_7.model.Order;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderDaoTest extends BaseDaoTest {
    OrderDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new OrderDao(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_order;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Order order = Order.builder()
                .sum(158.35)
                .clientInfo(ClientInfo.builder()
                        .clientName("Igor")
                        .clientTelNumber("+375296101011")
                        .build())
                .address(Address.builder()
                        .city("Minsk")
                        .street("Skryganova")
                        .houseNumber(11)
                        .build())
                .build();

        //When
        targetObject.saveOrUpdate(order);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_order;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_order;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/OrderDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Order order = targetObject.findById(701);
        assertNotNull(order);

        //Then
        assertEquals(145.17, order.getSum(), 0.0);
        assertEquals("Vasily", order.getClientInfo().getClientName());
        assertEquals("+375291234567", order.getClientInfo().getClientTelNumber());
        assertEquals("Minsk", order.getAddress().getCity());
        assertEquals("Pushkina", order.getAddress().getStreet());
        assertEquals(18, order.getAddress().getHouseNumber());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/OrderDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_order;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(701));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_order;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}