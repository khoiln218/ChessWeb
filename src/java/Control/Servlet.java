/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ChineseChess.Board;
import ChineseChess.State;
import ChineseChess._AI;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author conrongchautien
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    final int SELECT = 1;
    final int MOVE = 2;
    final int AI = 3;
    private HttpSession session;
    HashMap<String, StateBoard> room = new HashMap<String, StateBoard>();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String tb, url = null, yc = request.getParameter("yc");
            if (yc.equals("dangky")) {
                tb = DangKy(request, response);
                url = "/dangky.jsp?tb=" + tb;
            } else if (yc.equals("dangnhap")) {
                tb = DangNhap(request, response);
                url = "/dangnhap.jsp?tb=" + tb;
            } else if (yc.equals("msg")) {
                Chat(request, response);
            } else if (yc.equals("lobby")) {
                tb = Lobby(request, response);
                if (tb.equals("start")) {
                    url = "/chess.jsp";
                } else {
                    url = "/dangnhap.jsp?tb=" + tb;
                }
            } else if (yc.equals("chess")) {
                Chess(request, response);
            }
            if (url != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String DangKy(HttpServletRequest request, HttpServletResponse response) {
        String tb = "";
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            String cpassword = request.getParameter("cpassword").trim();
            String submit = request.getParameter("btAction");
            if (submit.equals("Bỏ qua")) {
                tb = "Đăng ký thành công";
                session = request.getSession();
                session.setAttribute("user", "Khách");
                return tb;
            }
            if (username.equals("")) {
                tb = "Bạn phải nhập Tài khoản";
                return tb;
            }
            if (username.length() < 6 || username.length() > 24) {
                tb = "Tên đăng nhập không hợp lệ";
                return tb;
            }
            if (password.length() < 6 || password.length() > 24) {
                tb = "Vui lòng nhập mật khẩu từ 6 đến 32 ký tự";
                return tb;
            }
            if (!password.equals(cpassword)) {
                tb = "Nhập lại mật khẩu không giống";
                return tb;
            }
            Account.Open();
            if (Account.Find(username)) {
                tb = "Tài khoản này đã tồn tại";
            } else {
                Account tk = new Account(username, password);
                tk.Insert();
                tb = "Đăng ký thành công";
                session = request.getSession();
                session.setAttribute("user", username);
            }
            Account.Close();
        } catch (Exception ex) {
            tb = "Đăng kí có lỗi!<br>" + ex;
        } finally {
        }
        return tb;
    }

    private String DangNhap(HttpServletRequest request, HttpServletResponse response) {
        String tb = "";
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String submit = request.getParameter("btAction");
            if (submit.equals("Bỏ qua")) {
                tb = "Đăng nhập thành công";
                session = request.getSession();
                session.setAttribute("user", "Khách");
                return tb;
            }
            if (username.equals("") || password.equals("")) {
                tb = "Vui lòng nhập Tài khoản và Mật khẩu!";
                return tb;
            }
            Account.Open();
            if (Account.Find(username, password)) {
                tb = "Đăng nhập thành công";
                session = request.getSession();
                session.setAttribute("user", username);
            } else {
                tb = "Tài khoản hoặc mật khẩu không đúng !";
            }
            Account.Close();
        } catch (Exception ex) {
            tb = "Đăng nhập lỗi!<br>" + ex;
        } finally {
        }
        return tb;
    }

    private String Lobby(HttpServletRequest request, HttpServletResponse response) {
        String tb = "start";
        return tb;
    }

    private void Chat(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String msg = request.getParameter("msg");
            session = request.getSession();
            String name = (String) session.getAttribute("user");
            if (msg.length() > 28) {
                msg = "Bạn gửi quá nhiều kí tự.";
                name = "";
            }
            JSONObject obj = new JSONObject();
            obj.put("msg", msg);
            obj.put("name", name);
            response.getWriter().println(obj);
        } catch (Exception ex) {
        }
    }

    private void Chess(HttpServletRequest request, HttpServletResponse response) {
        int ctrl = Integer.parseInt(request.getParameter("ctrl"));
        if (ctrl == SELECT) {
            Select(request, response);
        } else if (ctrl == MOVE) {
            Move(request, response);
        } else if (ctrl == AI) {
            AI(request, response);
        }
    }

    private void Move(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean red = Boolean.parseBoolean(request.getParameter("red"));
            int prex = Integer.parseInt(request.getParameter("prex"));
            int prey = Integer.parseInt(request.getParameter("prey"));
            int x = Integer.parseInt(request.getParameter("x"));
            int y = Integer.parseInt(request.getParameter("y"));
            byte[][] b = toArray(request.getParameter("cell"));

            Board board = new Board(b);
            board.moveTo(new Point(prex, prey), new Point(x, y));
            boolean over = board.IsGameOver(red);

            JSONArray cell = new JSONArray();
            for (int i = 0; i <= 9; i++) {
                JSONArray array = new JSONArray();
                for (int j = 0; j <= 8; j++) {
                    array.add(board.cell[i][j]);
                }
                cell.add(array);
            }

            JSONObject obj = new JSONObject();
            obj.put("cell", cell);
            obj.put("over", over);
            response.getWriter().println(obj);
        } catch (IOException ex) {
        }
    }

    private void Select(HttpServletRequest request, HttpServletResponse response) {
        try {
            int x = Integer.parseInt(request.getParameter("x"));
            int y = Integer.parseInt(request.getParameter("y"));
            byte[][] b = toArray(request.getParameter("cell"));

            Board board = new Board(b);
            ArrayList<State> AllMove = board.select(new Point(x, y));

            JSONArray allMove = new JSONArray();
            if (AllMove != null) {
                for (State state : AllMove) {
                    JSONArray array = new JSONArray();
                    array.add(state.curr.y);
                    array.add(state.curr.x);
                    allMove.add(array);
                }
            }

            JSONObject obj = new JSONObject();
            obj.put("allMove", allMove);
            response.getWriter().println(obj);
        } catch (IOException ex) {
        }
    }

    private void AI(HttpServletRequest request, HttpServletResponse response) {
        byte[][] b = toArray(request.getParameter("cell"));

        Board board = new Board(b);
        _AI _ai = new _AI(board);
        State state = _ai.GenerateMove(true);
        if (state != null) {
            board.moveTo(state.prev, state.curr);
        }
    }

    private byte[][] toArray(String data) {
        String[] s = data.split(",");
        byte[][] b = new byte[10][9];
        int n = s.length;
        for (int k = 0; k < n; k++) {
            int i = k / 9;
            int j = k % 9;
            b[i][j] = Byte.parseByte(s[k]);
        }
        return b;
    }
}
