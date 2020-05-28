package vn.nlu.fit.connections;

import java.sql.*;

public class DBConnection {

    private static String USER = "root";
    private static String PASS = "";

    private static String hostName = "localhost";
    private static String dbName = "cdweblab1";
    private static String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

    public static Connection getMySQLConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(connectionURL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "SELECT * FROM `user`";
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
