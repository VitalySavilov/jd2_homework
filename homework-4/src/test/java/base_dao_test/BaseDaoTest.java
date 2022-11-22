package base_dao_test;

import datasource.MysqlJdbcDataSource;
import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;
import task_6.model.Client;
import task_6.model.Course;
import task_6.model.Payment;
import task_7.model.Order;
import task_8_joined.model.Product;
import task_8_joined.model.Provider;
import task_8_joined.model.Sale;
import task_8_single_table.model.Employee;
import task_8_single_table.model.Person;
import task_8_single_table.model.Student;
import task_8_table_per_class.model.Manager;
import task_8_table_per_class.model.Programmer;
import task_8_table_per_class.model.User;

public class BaseDaoTest {

    // JDBC data source
    public static MysqlJdbcDataSource testMysqlJdbcDataSource;
    // DBUnit connection
    public static IDatabaseConnection iDatabaseConnection;
    //Hibernate session factory
    public static SessionFactory testSessionFactory;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("vitaly_savilov_test.jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "vitaly_savilov_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_test.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Manager.class)
                .addAnnotatedClass(Programmer.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Sale.class)
                .addAnnotatedClass(Provider.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Order.class)
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }
}
