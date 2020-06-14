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
public class MySQLConnect extends BaseSQLConnect {


    // Hàm khởi tạo và truyền thông tin của DataBase Server.
    public MySQLConnect(String Host, int Port,
            String UserName,
            String Password,
            String DataBase){
        this.DataBase = DataBase;
        this.Host = Host;
        this.Port = Port;
        this.Password = Password;
        this.UserName = UserName;
    }

    public MySQLConnect(String Host,
            String UserName,
            String Password,
            String DataBase){
        this.DataBase = DataBase;
        this.Host = Host;
        this.Password = Password;
        this.UserName = UserName;
    }

    // Hàm kiểm tra xem Driver connect MySQL đã sẵn sàng hay chưa.
    protected void driverTest () throws Exception{
        try {
            //Kiểm tra Class Name.
            Class.forName("org.gjt.mm.mysql.Driver");
        }
        // Nếu chưa tồng tại thì mém lỗi ra ngoài.
        catch (java.lang.ClassNotFoundException e) {
                throw new Exception("MySQL JDBC Driver not found ... ");
        } 
    }

    // Hàm lấy Connecttion
    protected Connection getConnect() throws Exception {
       // Nếu connetion null thì khởi tạo mới.
        if(this.connect==null){
            // Kiểm tra Driver
           driverTest();

            // Tạo Url kết nối đền DataBase Server
             String url = "jdbc:mysql://" + this.Host + ":"+this.Port+"/" + this.DataBase+"?useUnicode=yes&characterEncoding=UTF-8";
            try {
                // Tạo Connection thông qua Url
                this.connect = DriverManager.getConnection(url, this.UserName, this.Password);
            }
            // Nếu không thành công ném lỗi ra ngoài.
            catch (java.sql.SQLException e) {
                throw new Exception("Không thể kết nối đến DataBase Server: " + e.getMessage());
            }
       }
        // Trả connection ra ngoài.
        return this.connect;
    }
}

