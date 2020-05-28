package vn.nlu.fit.controller;

import vn.nlu.fit.connections.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/checkEmailExist")
public class CheckEmailExist extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("email: " + email);
        boolean result = true;
        if (email != null && !email.equals("")) {
            try {
                Connection con = DBConnection.getMySQLConnection();
                String sql = "SELECT email FROM `user`";
                PreparedStatement pr = con.prepareStatement(sql);
                ResultSet rs = pr.executeQuery();
                while (rs.next()) {
                    if (email.equals(rs.getString("email"))) {
                        result = false;
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String json = "{\"result\":\"" + result + "\"}";
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
