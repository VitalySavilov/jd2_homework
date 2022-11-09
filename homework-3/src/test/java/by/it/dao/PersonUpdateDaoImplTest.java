package by.it.dao;

import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;

import static org.junit.Assert.*;

public class PersonUpdateDaoImplTest extends BaseDaoTest {

    PersonUpdateDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new PersonUpdateDaoImpl(testSessionFactory);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("truncate table t_person_update;");
        conn.close();
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void findAll() {
        //Given
        assertEquals(0, targetObject.findAll().size());

        //When
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PersonDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("UPDATE t_person SET FIRSTNAME='Stepan' WHERE ID=101;");
        conn.close();

        //Then
        assertEquals(1, targetObject.findAll().size());
    }
}