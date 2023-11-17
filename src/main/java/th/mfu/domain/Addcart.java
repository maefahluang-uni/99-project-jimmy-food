package th.mfu.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Addcart {
    public static void main(String[] args) {
        try {
            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/your_database";
            String username = "your_username";
            String password = "your_password";
            Connection con = DriverManager.getConnection(url, username, password);

            // Start session (if needed)
            // No direct equivalent to session_start() in Java, as it depends on the web framework being used

            int foodid = Integer.parseInt(args[0]); // Assuming foodid is passed as a command line argument
            // Assuming uid is available in the session (replace it with the actual way you retrieve the user id)
            int uid = 1; // Replace 1 with the actual way you retrieve the user id

            // Query to select food based on foodid
            String query = "SELECT * FROM food WHERE foodid=? ORDER BY foodid ASC";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, foodid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String foodname = resultSet.getString("foodname");
                double price = resultSet.getDouble("price");

                // Query to insert into cart
                String sql1 = "INSERT INTO cart(cname, cprice, memid) VALUES (?, ?, ?)";
                PreparedStatement statement1 = con.prepareStatement(sql1);
                statement1.setString(1, foodname);
                statement1.setDouble(2, price);
                statement1.setInt(3, uid);

                int rowsInserted = statement1.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Successfully added to cart");
                    // Assuming the equivalent of window.history.back() in Java is navigating back
                    // This might vary depending on the framework or environment you are using
                } else {
                    System.out.println("Error adding to cart");
                }
            } else {
                System.out.println("Food not found");
            }

            // Close the database connection
            con.close();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
