package vn.nlu.fit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.nlu.fit.connections.DBConnection;
import vn.nlu.fit.model.City;
import vn.nlu.fit.model.Favorite;
import vn.nlu.fit.model.Industry;
import vn.nlu.fit.model.Platform;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showRegisterForm")
public class ShowRegisterForm extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Industry> industry = new ArrayList<>();
        List<City> city = new ArrayList<>();
        List<Favorite> favorites = new ArrayList<>();
        List<Platform> platform = new ArrayList<>();

        // get list industry
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "SELECT * FROM `industry`";
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                industry.add(new Industry(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get list city
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "SELECT * FROM `city`";
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                city.add(new City(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get list favorites
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "SELECT * FROM `favorite`";
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                favorites.add(new Favorite(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get list platform
        try {
            Connection con = DBConnection.getMySQLConnection();
            String sql = "SELECT * FROM `desired_platform`";
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                platform.add(new Platform(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        setAtribute into request
        for (int i = 0; i < city.size(); i++) {
            System.out.println(city.get(i));
        }
        request.setAttribute("industry", industry);
        request.setAttribute("city", city);
        request.setAttribute("favorites", favorites);
        request.setAttribute("platform", platform);

//        forward to page register form
        request.getRequestDispatcher("register-form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
