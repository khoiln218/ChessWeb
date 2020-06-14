<%-- 
    Document   : lobby
    Created on : Aug 29, 2013, 2:46:09 PM
    Author     : conrongchautien
--%>

<%@page import="webLib.MyLib"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lobby</title>
        <link rel="stylesheet" type="text/css" href="css/lobby.css">
        <script src="js/jquery.js"></script>
        <script src="js/ajax.js"></script>
        <script>            
            function server(i)
            {
                document.getElementById("server1").style.border="3px solid darkolivegreen";    
                document.getElementById("server1").style.background="gray";
                document.getElementById("server2").style.border="3px solid darkolivegreen";    
                document.getElementById("server2").style.background="gray";
                document.getElementById("server3").style.border="3px solid darkolivegreen";     
                document.getElementById("server3").style.background="gray";   
                document.getElementById("server"+i).style.border="3px solid orange";    
                document.getElementById("server"+i).style.background="darkslategray";
                document.getElementById("img1").style.border="1px solid darkolivegreen";
                document.getElementById("img2").style.border="1px solid darkolivegreen";
                document.getElementById("img3").style.border="1px solid darkolivegreen";    
                document.getElementById("img"+i).style.border="1px solid orange";
            }
        </script>
    </head>
    <body onload="server(1);">
        <%
            request.setCharacterEncoding("UTF-8");
            String user = null;
            String user_short = null;
            try {
                user = (String) session.getAttribute("user");
            } catch (Exception ex) {
            }
            if (user == null || user == "") {
                response.setContentType("text/html;charset=UTF-8");
                String tb = "Bạn chưa đăng nhập";
                String url = "/dangnhap.jsp?tb=" + tb;
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
                return;
            }
            if (user.length() > 10) {
                user_short = user.substring(0, 10) + " ...";
            } else {
                user_short = user;
            }
        %>
        <div class="context">
            <div class="status">
                <span class="status_menu"><b>Chào <%=user%>,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><i><a href="dangnhap.jsp?yc=thoat" style="color: graytext;">Thoát</a>&nbsp;&nbsp;</i></span>
            </div>

            <div class="board">
                <div class="sever1" id="server1" onclick="server(1)">
                    <img id="img1" src="images/level_tapsu.jpg" alt="Server1"><br>
                    <span class="list">Tập sự</span>
                </div>
                <div class="sever2" id="server2" onclick="server(2)">
                    <img id="img2" src="images/level_nangcao.jpg" alt="Server2"><br>
                    <span class="list">Nâng cao</span>
                </div>
                <div class="sever3" id="server3" onclick="server(3)">
                    <img id="img3" src="images/level_caothu.jpg" alt="Server3"><br>
                    <span class="list">Cao thủ</span>
                </div>
            </div>            

            <div class="info"><br>
                <div class="list"><b><%=user_short%></b></div><br>
                <img src="images/opponent.jpg" alt="opponent">
                <div class="list"><b>Danh hiệu: Cao thủ</b></div>
                <div class="list"><b>Tỉ lệ thắng: 73%</b></div>
            </div>

            <div class="friend"><br>
                <div class="list"><b>Friend List</b></div>
                <div class="desc"> - <a href="#">HaiHomHinh</a></div>
                <div class="desc"> - <a href="#">LinhLieuLinh</a></div>
                <div class="desc"> - <a href="#">ThieuThatTha</a></div>
                <div class="desc"> - <a href="#">Nobita</a></div>
                <div class="desc"> - <a href="#">PhucPhuPhang</a></div>
            </div>
                
            <form action="Servlet" method="POST">
                <input type="hidden" name="yc" value="lobby">
                <input class="control" type="submit" value="Luyện tập">
            </form>
                
            <div class="audio">
                <audio controls autoplay loop>
                    <source src="audios/myPlayCoTuong.mp3" type="audio/mp3">
                </audio>
            </div>
        </div>
    </body>
</html>
