package task_6.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_6.model.Payment;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class PaymentDaoTest extends BaseDaoTest {
    PaymentDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new PaymentDao(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_payment;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Payment payment1 = Payment.builder()
                .clientName("Denis")
                .date("19.11.2022")
                .build();
        Payment payment2 = Payment.builder()
                .clientName("Fedor")
                .date("20.11.2022")
                .build();
        Payment payment3 = Payment.builder()
                .clientName("Ivan")
                .date("21.11.2022")
                .build();

        //When
        targetObject.saveOrUpdate(payment1);
        targetObject.saveOrUpdate(payment2);
        targetObject.saveOrUpdate(payment3);

        //Then
        assertTrue(payment1.getId() + 1 == payment2.getId()
                && payment2.getId() + 1 == payment3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from t_payment;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table t_payment;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PaymentDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Payment payment = targetObject.findById(601);
        assertNotNull(payment);

        //Then
        assertEquals("Stepan", payment.getClientName());
        assertEquals("22.11.2022", payment.getDate());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PaymentDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_payment;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(601));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_payment;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}