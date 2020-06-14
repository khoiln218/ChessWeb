/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor+
 */
package jdbc;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author CiOne+com+vn
 */
public class SQLConnectUnit {

    //Biến kết nối cơ bản
//    private MySQLConnect connect;
    private MSSQLConnect connect;

    // Hàm hỗ trợ Select CSDL
    // SELECT * FORM TableName WHERE Condition ORDER BY OrderBy;
    public ResultSet Select(String TableName, String Condition, String OrderBy, String Limit) throws Exception {
        // Khai báo biến StringBuilder để tạo chuỗi Select.
        StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
        // Đưa câu lệnh điều kiện vào trong câu query
        this.AddCondition(query, Condition);
        // Đưa câu lệnh Order vào câu query
        this.AddOrderBy(query, OrderBy);
        // Đưa câu lệnh Limit vào câu query
        this.AddLimit(query, Limit);
        // Chèn ký tự ';' vào cuối dòng lệnh của để cách các câu lệnh.
        query.append(";");
        // Thực thi câu query và trả kết quả ra ngoài
        return this.connect.excuteQuery(query.toString());
    }

    // Hàm over load Select giảm OdrderBy parameter
    public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
        return this.Select(TableName, Condition, OrderBy, null);
    }

    // Hàm over load Select giảm OdrderBy parameter
    public ResultSet Select(String TableName, String Condition) throws Exception {
        return this.Select(TableName, Condition, null);
    }

    // Hàm over load Select giảm OdrderBy parameter
    public ResultSet SelectL(String TableName, String Limit) throws Exception {
        return this.Select(TableName, null, null, Limit);
    }

    // Hàm over load Select giảm OdrderBy parameter
    public ResultSet SelectL(String TableName, String Condition, String Limit) throws Exception {
        return this.Select(TableName, Condition, null, Limit);
    }

    // Hàm over load Select giảm Condition paramter
    public ResultSet Select(String TableName) throws Exception {
        return this.Select(TableName, null);
    }

    // Hàm thêm điều kiện vào query
    private void AddCondition(StringBuilder query, String Condition) {
        // Kiểm tra nếu condiotn khác null
        if (Condition != null) {
            query.append(" WHERE ").append(Condition);
        }
    }

    // Hàm thêm OrderBy vào query
    private void AddOrderBy(StringBuilder query, String OrderBy) {
        // Kiểm tra OrderBy khác null
        if (OrderBy != null) {
            query.append(" ORDER BY ").append(OrderBy);
        }
    }

    private void AddLimit(StringBuilder query, String Limit) {
        // Kiểm tra Limit khác null
        if (Limit != null) {
            //query.append(" LIMIT ").append(Limit);
        }
    }

    // Hàm hỗ trợ Insert xuống CSDL.
    // INSERT INTO TableName (columnName...) VALUES (column Values...);
    public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
        // Khai báo biến StringBuilder để tạo chuỗi Select.
        StringBuilder query = new StringBuilder("INSERT INTO " + TableName);
        // Khai báo biến StringBuilder để tạo chuỗi Column Values.
        StringBuilder valueInsert = new StringBuilder();

        query.append("(");
        // Duyệt và đưa thông tin tên cột và giá trị của cột vào câu query
        for (String key : ColumnValues.keySet()) {
            query.append(key).append(",");
            StringBuilder append = valueInsert.append("'").append(ColumnValues.get(key).toString()).append("' ,");
        }
        // Cắt bớt ký tự ',' cuối câu
        query = query.delete(query.length() - 1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());

        // Đưa giá trị của cột vào câu query
        query.append(") VALUES(").append(valueInsert.toString()).append(")");
        // Chèn ký tự ';' vào cuối dòng lệnh của để cách các câu lệnh.
        query.append(";");
        // Thực thi câu query và trả kết quả ra ngoài
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    // Hàm hỗ trợ Update CSDL
    // UPDATE TableName SET ColumnName = ColumnValue WHERE Condiotion;
    public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
        // Khai báo biến StringBuilder để tạo chuỗi Select.
        StringBuilder query = new StringBuilder("UPDATE " + TableName + " SET ");

        // Duyệt và đưa thông tin tên cột và giá trị của cột vào câu query
        for (String key : ColumnValues.keySet()) {
            query.append(key).append(" = '").append(ColumnValues.get(key).toString()).append("',");
        }
        // Cắt bớt ký tự ',' cuối câu
        query = query.delete(query.length() - 1, query.length());
        // Đưa câu lệnh điều kiện vào trong câu query
        this.AddCondition(query, Condition);
        // Chèn ký tự ';' vào cuối dòng lệnh của để cách các câu lệnh.
        query.append(";");
        // Thực thi câu query và trả kết quả ra ngoài
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    // Hàm hỗ trợ DELETE trong CSDL
    // DELETE FROM TableName WHERE Condition.
    public boolean Delete(String TableName, String Condition) throws Exception {
        // Khai báo biến StringBuilder để tạo chuỗi Select.
        StringBuilder query = new StringBuilder("DELETE FROM " + TableName);
        // Đưa câu lệnh điều kiện vào trong câu query
        this.AddCondition(query, Condition);
        // Chèn ký tự ';' vào cuối dòng lệnh của để cách các câu lệnh.
        query.append(";");
        // Thực thi câu query và trả kết quả ra ngoài
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    // Đếm số cột trong Result select từ CSDL.
    public static int getColumnCount(ResultSet result) throws SQLException {
        // Lất số lượng cợt từ MetaData của Result.
        return result.getMetaData().getColumnCount();
    }

    // Hàm lấy danh sách tên cột trong Result select từ CSDL.
    public static String[] getColumnName(ResultSet result) throws SQLException {
        // Lấy ResultSetMetaDate từ Result
        ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
        // Lấy số lượng cột trong Result.
        int ColumnCount = rsMetaData.getColumnCount();
        // Khai báo danh sách các cột.
        String[] list = new String[ColumnCount];
        // Duyệt các cột
        for (int i = 0; i < ColumnCount; i++) // Lấy ten đưa vào danh sách
        {
            list[i] = rsMetaData.getColumnName(i);
        }
        // Trả danh sách ra ngoài.
        return list;
    }

    // Đóng kết nối.
    public void Close() throws SQLException {
        this.connect.Close();
    }

    // Hàm khởi tạo với các thông số kết nối DataBase Server.
//    public MyConnectUnit(String host, int port, String user, String pass, String db) {
//        this.connect = new MySQLConnect(host, port, user, pass, db);
//    }
    public SQLConnectUnit(String host, int port, String user, String pass, String db) {
        this.connect = new MSSQLConnect(host, port, user, pass, db);
    }
}
