/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author XPMUser
 */
public abstract class BaseSQLConnect {
    String DataBase = "";
    String Host = "";
    String Password = "";
    // Thông tin Host của DataBase Server mình connect tới.
    int Port = 3306; // Default port MySQL
    String UserName = "";
    Connection connect = null;
    ResultSet result = null;
    Statement statement = null;

    public BaseSQLConnect() {
    }

    public void Close() throws SQLException {
        // Nếu Result chưa đóng. Đóng result
        if (this.result != null) {
            if (!this.result.isClosed()) {
                this.result.close();
            }
            this.result = null;
        }
        if (this.statement != null) {
            if (!this.statement.isClosed()) {
                this.statement.close();
            }
            this.statement = null;
        }
        if (this.connect != null) {
            if (!this.connect.isClosed()) {
                this.connect.close();
            }
            this.connect = null;
        }
    }

    protected abstract void driverTest() throws Exception;

    public ResultSet excuteQuery(String Query) throws Exception {
        try {
            // Thực thi câu lệnh.
            this.result = getStatement().executeQuery(Query);
        } // Nếu không thành công ném lỗi ra ngoài.
        catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage() + " - " + Query);
        }
        return this.result;
    }

    public int executeUpdate(String Query) throws Exception {
        // Khai báo biến int lưu trữ kết quả tình trạng thực thi câu lệnh Query.
        int res = Integer.MIN_VALUE;
        try {
            // Thực thi câu lệnh.
            res = getStatement().executeUpdate(Query);
        } // Nếu không thành công ném lỗi ra ngoài.
        catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage() + " - " + Query);
        } finally {
            // Đóng kết nối.
            this.Close();
        }
        return res;
    }

    protected abstract Connection getConnect() throws Exception;

    protected Statement getStatement() throws Exception {
        // Kiểm tra statement nếu = null hoặc đã đóng.
        if (this.statement == null ? true : this.statement.isClosed()) {
            // Khởi tạo một statement mới.
            this.statement = this.getConnect().createStatement();
        }
        return this.statement;
    }

}
