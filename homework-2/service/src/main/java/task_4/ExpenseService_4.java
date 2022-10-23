package task_4;

import my.DataSource.MySQLDataSource;

import java.sql.*;

public class ExpenseService_4 {
    private MySQLDataSource dataSource;

    {
        try {
            dataSource = new MySQLDataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void create(String[] args) {
        if (args.length == 3) {
            try {
                final Connection connection = dataSource.getConnection();
                final Statement statement = connection.createStatement();
                String query1 = "INSERT INTO vitaly_savilov.receivers (name) " +
                        "SELECT * FROM (SELECT '" + args[1] + "' AS name) AS temp " +
                        "WHERE NOT EXISTS (SELECT name FROM vitaly_savilov.receivers WHERE name = '" +
                        args[1] + "') LIMIT 1";
                statement.executeUpdate(query1);
                String query2 = "INSERT INTO vitaly_savilov.expenses (paydate, receiver, value) " +
                        "SELECT '" + args[0] + "', num, " + args[2] + " FROM vitaly_savilov.receivers " +
                        "WHERE name='" + args[1] + "'";
                statement.executeUpdate(query2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Неверно переданы данные");
        }
    }

    public void readAll() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "SELECT e.num, paydate, name, value " +
                    "FROM vitaly_savilov.expenses as e, vitaly_savilov.receivers as r " +
                    "WHERE receiver=r.num " +
                    "ORDER BY num";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Таблица расходов:");
            while (result.next()) {
                System.out.printf("%-5s %-12s %-30s %-15s\n", result.getString("num"),
                        result.getString("paydate"), result.getString("name"),
                        result.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
