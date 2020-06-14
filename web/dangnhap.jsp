<%-- 
    Document   : login
    Created on : Aug 29, 2013, 2:49:06 PM
    Author     : conrongchautien
--%>

<%@page import="webLib.MyLib"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/registry.css">
        <script src="js/registry.js"></script>
        <title>Đăng nhập</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            String username = MyLib.getParameter("username", request);
            String password = MyLib.getParameter("password", request);
            String tb = MyLib.getParameter("tb", request);
            String yc = MyLib.getParameter("yc", request);
            if (tb.equals("Đăng nhập thành công")) {
                String url = "/lobby.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            if (yc.equals("thoat")) {
                session.removeAttribute("user");
                tb = "Đăng xuất thành công!";
            }
        %>
        <div class="boxy-wrapper boxy-modal" style>
            <div class="boxy login_boxy" style="left: 460px; top: 100px;">            
                <div class="boxy-container lbx_widmid">
                    <div class="boxy-title" style="">Đăng Nhập</div>
                    <div class="boxy-body">
                        <div class="boxy-content">
                            <form id="frmLogin" name="frmLogin" action="Servlet" method="post">
                                <input type="hidden" name="yc" value="dangnhap">
                                <div class="wg_titlogin wg_rowlogin">Bạn hãy nhập tài khoản và mật khẩu để đăng nhập trên Game cờ tướng</div>
                                <div class="wg_rowlogin">
                                    <div class="wg_strowlogin">Tài khoản: </div>
                                    <div class="wg_ndrowlogin"><input class="wg_input" onblur="" type="text" name="username" value="<%=username%>"maxlength="25" tabindex="1"></div>
                                </div>    
                                <div class="wg_rowlogin notelog">Từ 6 đến 24 ký tự</div>
                                <div class="wg_rowlogin notelog txtfail" style="display:block" id="reg_agree_error"><%=tb%></div>
                                <div class="wg_rowlogin">
                                    <div class="wg_strowlogin">Mật khẩu: </div>
                                    <div class="wg_ndrowlogin"><input class="wg_input" onblur="" type="password" name="password" value="<%=password%>" maxlength="33" tabindex="2"></div>
                                </div>
                                <div class="wg_rowlogin notelog">Từ 6 đến 32 ký tự</div>
                                <div class="rem_login_box">
                                    <p class="rem_login_row">
                                        <input name="longtime" id="remember" type="checkbox" value="1" checked="" class="rem_login">
                                        Ghi nhớ đăng nhập<em></em>
                                        <a href="dangky.jsp" id="link_register"><strong>Đăng ký </strong></a>
                                    </p>
                                </div>
                                <div class="boxy-footer">
                                    <label class="uiButton"><input class="btn_L1" type="submit" value="Đồng ý" name="btAction"></label>
                                    <label class="uiButton"><input class="btn_L1" type="submit" value="Bỏ qua" name="btAction"></label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
