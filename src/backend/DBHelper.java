package backend;

import java.sql.*;

public class DBHelper {
    private static Connection koneksi;

public static void bukaKoneksi() {
    if (koneksi == null) {
        try {
            String url = "jdbc:postgresql://localhost:5432/mydatabase";
            String user = "admin";
            String password = "admin123";

            Class.forName("org.postgresql.Driver");

            koneksi = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi PostgreSQL berhasil!");
        } catch (SQLException t) {
            System.out.println("Error koneksi: " + t.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL tidak ditemukan!");
        }
    }
}



    public static int insertQueryGetId(String query) {
        bukaKoneksi();
        int num = 0;
        int result = -1;

        try {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }

        return result;
    }

    public static boolean executeQuery(String query) {
        bukaKoneksi();
        boolean result = false;

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);
            result = true;
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ResultSet selectQuery(String query) {
        bukaKoneksi();
        ResultSet rs = null;

        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
