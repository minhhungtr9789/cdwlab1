package vn.nlu.fit.utils;

import vn.nlu.fit.connections.DBConnection;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {
    public static String fullPath(String path) {
        return "http://localhost:8080/lab1_war_exploded/" + path;
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt(""));
    }
}
