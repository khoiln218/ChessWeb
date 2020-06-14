/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webLib;

/**
 *
 * @author NINH XUAN HAI
 */
public class MyLib {
    public static String getParameter(String name,javax.servlet.http.HttpServletRequest request){
        String value = request.getParameter(name);
        if (value == null) {
                value = "";
        }
        return value;
    }    
}
