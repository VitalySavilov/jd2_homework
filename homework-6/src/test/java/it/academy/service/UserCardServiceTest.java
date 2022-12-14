package it.academy.service;

import it.academy.dto.CardDto;
import it.academy.model.CardType;
import it.academy.model.PaymentCard;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class UserCardServiceTest {
    @Autowired
    UserCardService targetObject;
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
    public void addCard() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(ChangeRoleServiceTest.class.getResourceAsStream("ServiceTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        ResultSet rs = connection
                .createStatement()
                .executeQuery("select count(*) from t_payment_card WHERE USER_ID=101;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);

        //When
        PaymentCard paymentCard = PaymentCard.builder()
                .cardType(CardType.MAESTRO)
                .cardNum("563219874")
                .build();
        List<CardDto> cards = targetObject.addCard(101L, paymentCard).getPaymentCards();

        //Then
        rs = connection.createStatement()
                .executeQuery("select count(*) from t_payment_card WHERE USER_ID=101;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(2, actualSize);
        assertEquals(2, cards.size());
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table t_payment_card;");
        connection.createStatement().executeUpdate("truncate table t_user;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
    }

    @Test
    @SneakyThrows
    public void deleteCard() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(ChangeRoleServiceTest.class.getResourceAsStream("ServiceTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        ResultSet rs = connection
                .createStatement()
                .executeQuery("select count(*) from t_payment_card WHERE USER_ID=102;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(2, actualSize);

        //When
        List<CardDto> cards = targetObject.deleteCard(202L).getPaymentCards();

        //Then
        rs = connection.createStatement()
                .executeQuery("select count(*) from t_payment_card WHERE USER_ID=102;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        assertEquals(1, cards.size());
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("truncate table t_payment_card;");
        connection.createStatement().executeUpdate("truncate table t_user;");
        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
    }
}