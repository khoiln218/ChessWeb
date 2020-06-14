<%-- 
    Document   : index
    Created on : Dec 2, 2013, 12:25:13 PM
    Author     : conrongchautien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String url = "/dangnhap.jsp?tb";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        %>
    </body>
</html>
