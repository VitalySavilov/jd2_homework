package task_8_joined.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_8_joined.model.Provider;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProviderDaoTest extends BaseDaoTest {
    ProviderDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new ProviderDao(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_provider;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_product;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Provider provider = Provider.builder()
                .providerName("Coca-Cola Company")
                .productName("Coca-Cola")
                .price(1560.45)
                .build();

        //When
        targetObject.saveOrUpdate(provider);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_provider;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_product;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().executeUpdate("truncate table t_provider;");
        conn.createStatement().executeUpdate("truncate table t_product;");
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProviderDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Provider provider = targetObject.findById(302);
        assertNotNull(provider);

        //Then
        assertEquals("Pepsi Corporation", provider.getProviderName());
        assertEquals("Pepsi", provider.getProductName());
        assertEquals(586.35, provider.getPrice(), 0.0);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProviderDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_provider;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_product;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(302));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_provider;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_product;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }
}