<%-- 
    Document   : signup
    Created on : Aug 29, 2013, 2:49:30 PM
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
        <title>Đăng ký</title>
    </head>
    <body>
        <%
            String username = MyLib.getParameter("username", request);
            String password = MyLib.getParameter("password", request);
            String cpassword = MyLib.getParameter("cpassword", request);
            String tb = MyLib.getParameter("tb", request);
            if (tb.equals("Đăng ký thành công")) {
                String url = "/lobby.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
                return;
            }
            if (tb != "") {
        %>
    <body onload="submit_signup(true)"></body>
    <%}%>
    <div class="boxy-wrapper boxy-modal">
        <div class="boxy reg_boxy" style="left: 460px; top: 100px;">
            <div class="boxy-container lbx_widmid">
                <div class="boxy-title" style="">Đăng ký  tài khoản Game cờ tướng</div>
                <div class="boxy-body">
                    <div class="boxy-content">
                        <form id="frmLogin" name="frmLogin" action="Servlet" method="post">
                            <input type="hidden" name="yc" value="dangky">
                            <div class="wg_txtintro">
                                Nếu bạn đã có tài khoản <strong>Game cờ tướng</strong>, vui lòng 
                                <a class="wg" href="dangnhap.jsp" id="req_login">Đăng nhập</a>
                            </div>	
                            <div class="wg_rowlogin">
                                <div class="wg_strowlogin">Tài khoản: </div>
                                <div class="wg_ndrowlogin"><input class="wg_input" type="text" value="<%=username%>" name="username" maxlength="25" tabindex="1"></div>
                                <div class="wg_rdrowlogin" style="display: block"><a class="btn_L3" onclick="submit_signup(false)" href="#"><em>Kiểm tra</em></a></div>
                                <div class="wg_rdrowlogin" id="chk_acc_ok" style="display: none"><span class="wg_checkok"></span></div>
                            </div>
                            <div class="wg_rowlogin notelog txtfail" id="reg_account_error" style="display: none"">Tên đăng nhập không hợp lệ</div>
                            <div class="wg_rowlogin notelog">Từ 6 đến 24 ký tự</div>
                            <div class="wg_rowlogin">
                                <div class="wg_strowlogin">Mật khẩu: </div>
                                <div class="wg_ndrowlogin"><input class="wg_input" type="password" value="<%=password%>" id="reg_pwd" name="password" maxlength="33" tabindex="2"></div>

                            </div>
                            <div class="wg_rowlogin notelog txtfail" id="reg_pwd_error" style="display: none">Vui lòng nhập mật khẩu từ 6 đến 32 ký tự</div>
                            <div class="wg_rowlogin notelog">Từ 6 đến 32 ký tự</div>
                            <div class="wg_rowlogin">
                                <div class="wg_strowlogin">Nhập lại mật khẩu: </div>
                                <div class="wg_ndrowlogin"><input class="wg_input" type="password" value="<%=cpassword%>" id="reg_cpwd" name="cpassword" maxlength="33" tabindex="3"></div>
                            </div>
                            <div class="wg_rowlogin notelog txtfail" id="reg_cpwd_error" style="display: none">Nhập lại mật khẩu không giống</div>
                            <div class="wg_rowlogin">
                                <div class="wg_strowlogin">&nbsp;</div>
                                <div class="wg_ndrowlogin">
                                    <input tabindex="5" type="checkbox" class="wg_checkbox" value="1" id="ragree" checked="checked">
                                    Tôi đồng ý với  <a class="wg" href="licence.html" target="_blank">thỏa thuận sử dụng</a></div>
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
