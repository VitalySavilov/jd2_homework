package task_6.dao;

import base_dao_test.BaseDaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task_6.model.Course;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class CourseDaoTest extends BaseDaoTest {
    CourseDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new CourseDao(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_course;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Course course1 = Course.builder()
                .courseName("Java")
                .price(500)
                .build();
        Course course2 = Course.builder()
                .courseName("HTML")
                .price(200)
                .build();
        Course course3 = Course.builder()
                .courseName("Computer science")
                .price(300)
                .build();

        //When
        targetObject.saveOrUpdate(course1);
        targetObject.saveOrUpdate(course2);
        targetObject.saveOrUpdate(course3);

        //Then
        assertTrue(course1.getId() + 1 == course2.getId()
                && course2.getId() + 1 == course3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from t_course;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table t_course;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/CourseDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Course course = targetObject.findById(501);
        assertNotNull(course);

        //Then
        assertEquals("Java Enterprise", course.getCourseName());
        assertEquals(900, course.getPrice(), 0.0);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/CourseDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_course;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(501));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_course;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}