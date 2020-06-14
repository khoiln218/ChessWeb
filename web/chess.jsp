<%-- 
    Document   : index
    Created on : Aug 19, 2013, 7:47:57 PM
    Author     : conrongchautien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chineses Chess</title>
        <link rel="stylesheet" type="text/css" href="css/chess.css">
        <script src="js/jquery.js"></script>
        <script src="js/draw.js"></script>
        <script src="js/event.js"></script>
        <script src="js/ajax.js"></script>
    </head>
    <body onload="draw(cellStartup);">
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
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
                return;
            } else if (user.length() > 10) {
                user_short = user.substring(0, 10) + " ...";
            } else {
                user_short = user;
            }
        %>
        <div class="context">
            <div class="wapper">
                <div class="status">
                    <span class="status_menu"><b>Chào <%=user%>,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><i><a href="dangnhap.jsp?yc=thoat" style="color: graytext;">Thoát</a>&nbsp;&nbsp;</i></span>
                </div>

                <div class="board" id="board">
                    <img id="scream" src="images/Cboard.jpg" alt="Board">
                    <canvas id="canvas" id="canvas" width="540" height="600"></canvas>
                    <div class="alert" id="alert" style="display: none">
                        <span class="w-l" id="w-l">You win</span>
                        <span class="continue" id="continue" onclick="reset()">Continue</span>
                    </div>
                </div>

                <div class="center">
                    <div class="opponent">
                        <a href="#"><img src="images/opponent.jpg" alt="opponent"></a>
                        <div>                        
                            <div class="timer1" id="timer1">---</div>
                            <div class="clock1" id="clock1">--:--</div>
                        </div><br>
                        <div class="desc"><b>Computer</b></div>
                    </div>

                    <div class="user">
                        <a href="#"><img src="images/user.jpg" alt="user"></a>
                        <div>
                            <div class="timer2" id="timer2">---</div>
                            <div class="clock2" id="clock2">--:--</div>
                        </div><br>
                        <div class="desc"><b><%=user_short%></b></div>
                    </div>

                    <div class="score">
                        <div class="score_title">Score</div>
                        <div class="score_element">
                            <span class="p1" id="sc">0</span>
                            <span class="p3">:</span>
                            <span class="p2" id="su">0</span>
                        </div>
                    </div>

                    <form name="formMain">
                        <div class="msg">
                            <div class="msg_chat">
                                <div class="result" id="result1">Chào mừng bạn đến với game cờ tướng. Chúc vui vẻ!</div>
                                <div class="result" id="result2"></div>
                            </div>
                            <div class="msg_send">
                                <div class="msg_send_text"><input id="send" type="text" onkeydown="enter(event)" onscroll="" autocomplete="off" name="msg"></div>
                                <div class="msg_send_button"><input onclick='getChat("Servlet")' type="button" name="send" value="Gửi"></div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="audio">
                    <audio controls autoplay loop>
                        <source src="audios/myPlayCoTuong.mp3" type="audio/mp3">
                    </audio>
                </div>
            </div>
        </div>
    </body>
</html>
