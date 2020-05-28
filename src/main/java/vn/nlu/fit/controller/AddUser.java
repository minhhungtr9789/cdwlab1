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

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("enter add user servlet");
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String password = request.getParameter("password") == null ? "" : request.getParameter("password");
        String firstName = request.getParameter("firstName") == null ? "" : request.getParameter("firstName");
        String lastName = request.getParameter("lastName") == null ? "" : request.getParameter("lastName");
        String jobTitle = request.getParameter("jobTitle") == null ? "" : request.getParameter("jobTitle");
        String company = request.getParameter("company") == null ? "" : request.getParameter("company");
        String telephone = request.getParameter("telephone") == null ? "" : request.getParameter("telephone");

        String industryString = request.getParameter("industry") == null ? "" : request.getParameter("industry");
        String cityString = request.getParameter("city") == null ? "" : request.getParameter("city");
        String platformString = request.getParameter("platform") == null ? "" : request.getParameter("platform");
        int industry = -1, city = -1, platform = -1;

        if (industryString != null && !"".equals(industryString)) {
            industry = Integer.parseInt(industryString);
        }
        if (cityString != null && !"".equals(cityString)) {
            city = Integer.parseInt(cityString);
        }
        if (platformString != null && !"".equals(platformString)) {
            platform = Integer.parseInt(platformString);
        }
//        System.out.println(request.getParameterValues("favorite").length);
        int rs = 0;
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "INSERT INTO `user` (email,pass,firstname,lastname,jobtitle,company,telephone,desired_platform,city,industry) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, password);
            pr.setString(3, firstName);
            pr.setString(4, lastName);
            pr.setString(5, jobTitle);
            pr.setString(6, company);
            pr.setString(7, telephone);
            pr.setInt(8, platform);
            pr.setInt(9, city);
            pr.setInt(10, industry);

            rs = pr.executeUpdate();
            System.out.println("Số dòng insert: " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean result = false;
        if (rs > 0) {
            result = true;
        }

        String json = "{\"result\":\"" + result + "\"}";
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
