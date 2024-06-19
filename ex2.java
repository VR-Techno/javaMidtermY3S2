import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ex2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://192.168.224.129:5432/javaExamY3S2M";
        String user = "postgres";
        String password = "pg_strong_pwd";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver

            // Connect to the database
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            statement = connection.createStatement();

            // Execute a query to retrieve all products
            String sql = "SELECT id, name, price_per_unit, active_for_sell FROM Product";
            resultSet = statement.executeQuery(sql);

            // Process the result set
            System.out.println("ID\tName\tPrice Per Unit\tActive for Sell");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double pricePerUnit = resultSet.getDouble("price_per_unit");
                boolean activeForSell = resultSet.getBoolean("active_for_sell");

                System.out.printf("%d\t%s\t%.2f\t\t%b%n", id, name, pricePerUnit, activeForSell);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the result set, statement, and connection
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

