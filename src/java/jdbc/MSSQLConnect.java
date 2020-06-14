/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.*;

/**
 *
 * @author CiOne.com.vn
 */
public class MSSQLConnect extends BaseSQLConnect {

    // Hàm khởi tạo và truyền thông tin của DataBase Server.
    public MSSQLConnect(String Host, int Port,
            String UserName,
            String Password,
            String DataBase) {
        this.DataBase = DataBase;
        this.Host = Host;
        this.Port = Port;
        this.Password = Password;
        this.UserName = UserName;
    }

    public MSSQLConnect(String Host,
            String UserName,
            String Password,
            String DataBase) {
        this.DataBase = DataBase;
        this.Host = Host;
        this.Password = Password;
        this.UserName = UserName;
    }

    // Hàm kiểm tra xem Driver connect MySQL đã sẵn sàng hay chưa.
    protected void driverTest() throws Exception {
        try {
            // Kiểm tra Class Name.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } // Nếu chưa tồng tại thì mém lỗi ra ngoài.
        catch (java.lang.ClassNotFoundException e) {
            throw new Exception("MSSQL JDBC Driver not found ... ");
        }
    }

    // Hàm lấy Connecttion
    public Connection getConnect() {
        Connection con = null;
        try {
            // load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                    + "Database=test;User=sa;Password=123;");
            if (con != null) {
                System.out.print("Ket noi thanh cong");
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        // Trả connection ra ngoài.
        return this.connect;
    }
}
