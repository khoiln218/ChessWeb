package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import jdbc.MyConnectUnit;

/**
 *
 * @author Khoi
 */
public class Account {

    private String name;
    private String username;
    private String pw;
    static MyConnectUnit connect;
    static ResultSet result;

    public Account(String hoten, String username, String password) {
        this.name = hoten;
        this.username = username;
        this.pw = password;
    }

    public Account(String username, String password) {
        this.name = "";
        this.username = username;
        this.pw = password;
    }

    public static void Open() {
        connect = new MyConnectUnit("localhost", 3306, "", "", "test");
    }

    static boolean Find(String username, String pw) {
        try {
            if (pw == null) {
                result = connect.Select("tb_taikhoan", "UserName='" + username + "'");
            } else {
                result = connect.Select("tb_taikhoan", "UserName='" + username + "' and Password='" + pw + "'");
            }
            if (result.next()) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    static boolean Find(String username) {
        return Find(username, null);
    }

    static int CountRow() {
        try {
            int count = 0;
            result = connect.Select("tb_taikhoan");
            while (result.next()) {
                count++;
            }
            return count;
        } catch (Exception ex) {
        }
        return 0;
    }

    void Insert() {
        try {
            HashMap<String, Object> insertValues = new HashMap<String, Object>();
            insertValues.put("Name", getName());
            insertValues.put("Username", getUsername());
            insertValues.put("Password", getPw());
            connect.Insert("tb_taikhoan", insertValues);
        } catch (Exception ex) {
        }
    }

    static boolean Delete(String username) {
        try {
            return connect.Delete("tb_taikhoan", "UserName='" + username + "'");
        } catch (Exception ex) {
        }
        return false;
    }

    static boolean Update(String name, String user, String pass) {
        try {
            HashMap<String, Object> insertValues = new HashMap<String, Object>();
            insertValues.put("name", name);
            insertValues.put("Password", pass);
            return connect.Update("tb_taikhoan", insertValues, "UserName='" + user + "'");
        } catch (Exception ex) {
        }
        return false;
    }

    public static void Close() {
        try {
            connect.Close();
            result.close();
        } catch (SQLException ex) {
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pw
     */
    public String getPw() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    public void setPw(String pw) {
        this.pw = pw;
    }
}
