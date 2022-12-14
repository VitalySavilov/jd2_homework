package it.academy.service;

import it.academy.dto.UserRoleDto;
import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ChangeRoleServiceTest {
    @Autowired
    ChangeRoleService targetObject;
    @Autowired
    Connection connection;
    IDatabaseConnection iDatabaseConnection;

    @Before
    public void setUp() throws Exception {
        iDatabaseConnection = new MySqlConnection(connection, "homework_6_test");
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void changeAdminRole() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(ChangeRoleServiceTest.class.getResourceAsStream("ServiceTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        ResultSet rs = connection
                .createStatement()
                .executeQuery("select ROLE from t_user WHERE ID=102;");
        rs.next();
        String role = rs.getString("ROLE");
        assertEquals("USER", role);

        //When
        UserRoleDto userRoleDto = targetObject.changeAdminRole(102L);

        //Then
        rs = connection
                .createStatement()
                .executeQuery("select ROLE from t_user WHERE ID=102;");
        rs.next();
        role = rs.getString("ROLE");
        assertEquals("ADMIN", role);
        assertEquals("ADMIN", userRoleDto.getRole().name());

        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void changeUserRole() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(ChangeRoleServiceTest.class.getResourceAsStream("ServiceTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        ResultSet rs = connection
                .createStatement()
                .executeQuery("select ROLE from t_user WHERE ID=101;");
        rs.next();
        String role = rs.getString("ROLE");
        assertEquals("ADMIN", role);

        //When
        UserRoleDto userRoleDto = targetObject.changeUserRole(101L);

        //Then
        rs = connection
                .createStatement()
                .executeQuery("select ROLE from t_user WHERE ID=101;");
        rs.next();
        role = rs.getString("ROLE");
        assertEquals("USER", role);
        assertEquals("USER", userRoleDto.getRole().name());

        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }
}