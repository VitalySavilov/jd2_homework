package task_8_table_per_class.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_8_table_per_class.model.Manager;
import task_8_table_per_class.model.Programmer;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProgrammerDaoTest extends BaseDaoTest {
    ProgrammerDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new ProgrammerDao(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_programmer;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Programmer programmer = Programmer.builder()
                .company("Yandex")
                .language("Java")
                .build();

        //When
        targetObject.saveOrUpdate(programmer);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_programmer;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_programmer;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProgrammerDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Programmer programmer = targetObject.findById(102);
        assertNotNull(programmer);

        //Then
        assertEquals("Google", programmer.getCompany());
        assertEquals("Java", programmer.getLanguage());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProgrammerDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_programmer;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(102));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_programmer;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
