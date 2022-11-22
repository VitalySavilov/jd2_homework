package datasource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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

import java.util.HashMap;
import java.util.Map;

public final class MysqlSessionFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        return getInstance(DataConfig.JDBC_PROPERTIES_FILE_NAME,
                DataConfig.HIBERNATE_PROPERTIES_FILE_NAME);
    }

    public static SessionFactory getInstance(String jdbcPropertiesFileName,
                                             String hibernatePropertiesFileName) {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySettings(buildSettings(jdbcPropertiesFileName,
                        hibernatePropertiesFileName))
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

        sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }

    private static Map<String, String> buildSettings(String jdbcPropertiesFileName,
                                                     String hibernatePropertiesFileName) {
        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("driver"));
        settings.put("hibernate.connection.url",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("url"));
        settings.put("hibernate.connection.username",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("username"));
        settings.put("hibernate.connection.password",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("password"));

        settings.put("dialect",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("dialect"));
        settings.put("hibernate.current_session_context_class",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.current_session_context_class"));
        settings.put("hibernate.show_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.show_sql"));
        settings.put("hibernate.format_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.format_sql"));

        return settings;
    }

}
