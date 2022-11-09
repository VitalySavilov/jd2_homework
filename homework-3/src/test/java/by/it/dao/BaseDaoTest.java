package by.it.dao;

import by.it.MysqlJdbcDataSource;
import by.it.pojos.Person;
import by.it.pojos.PersonUpdate;
import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;

public class BaseDaoTest {

    // JDBC data source
    static MysqlJdbcDataSource testMysqlJdbcDataSource;
    // DBUnit connection
    static IDatabaseConnection iDatabaseConnection;
    //Hibernate session factory
    static SessionFactory testSessionFactory;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("vitaly_savilov_test.jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "vitaly_savilov_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_test.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(PersonUpdate.class)
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }

}
