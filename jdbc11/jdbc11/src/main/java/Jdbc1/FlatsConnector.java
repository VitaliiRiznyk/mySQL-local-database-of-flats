package Jdbc1;

import java.sql.*;
import java.util.Scanner;

public class FlatsConnector {
    static String user = "root";
    static String password = "Vital12#";
    static String url = "jdbc:mysql://localhost:3306/flats";

    static Connection cn;

    public static void showAll(String urlDB, String user, String password) throws SQLException {
        try {
            cn = DriverManager.getConnection(urlDB, user, password);
            try (Statement statement = cn.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM flats");
                ResultSetMetaData rsm = rs.getMetaData();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    System.out.print(rsm.getColumnName(i) + "\t\t");
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            }
        } finally {
            cn.close();
        }
    }

    public static void selectFlatDisctict() throws SQLException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter district");
            String district = sc.nextLine();
            cn = DriverManager.getConnection(url, user, password);
            PreparedStatement st = cn.prepareStatement("SELECT * FROM FLATS WHERE DISTRICT = ?");
            try {
                st.setString(1, district);
                st.execute();
                ResultSet rs = st.executeQuery();
                try {
                    ResultSetMetaData rsm = rs.getMetaData();
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        System.out.print(rsm.getColumnName(i) + "\t\t");
                    }
                    System.out.println();
                    while (rs.next()) {
                        for (int i = 1; i <= rsm.getColumnCount(); i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
                } finally {
                    rs.close();
                }
            } finally {
                cn.close();
                st.close();
            }
        }
    }

    public static void selectFlatRooms() throws SQLException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number of rooms");
            int i = sc.nextInt();
            cn = DriverManager.getConnection(url, user, password);
            try {
                PreparedStatement ps = cn.prepareStatement("SELECT * FROM FLATS WHERE ROOMS = ?");
                try {
                    ps.setInt(1, i);
                    ps.execute();
                    ResultSet rs = ps.executeQuery();
                    try {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        for (int k = 1; k <= rsmd.getColumnCount(); k++)
                            System.out.print(rsmd.getColumnName(k) + "\t\t");
                        System.out.println();
                        while (rs.next()) {
                            for (int j = 1; j <= rsmd.getColumnCount(); j++)
                                System.out.print(rs.getString(j) + "\t\t");
                            System.out.println();
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    ps.close();
                }
            } finally {
                cn.close();
            }
        }
    }
}