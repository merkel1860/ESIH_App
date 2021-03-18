package core.utils;

import core.CourseLevel;
import core.Degree;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cxo {
    private static Connection connection;
    private static ResultSet resultSet;
    private static String sqlQuery;
    private static Statement stm;
    private static String cred = "jdbc:mysql://127.0.0.1:3306/esihdb";

    public static void pickBaseParameter(String param){
        sqlQuery ="select * from" + " "+param;
    }
    public static void init() {

        try {
        // new Driver name : com.mysql.cj.jdbc.Driver
        // old Driver name : com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        try {
            connection= DriverManager.
                    getConnection(cred,"nebel","nebel1984");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stm= connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = stm.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Degree> fetchDegreeFromDB(){
        List<Degree> degreeList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Degree a = new Degree();
                a.setDegreeName(resultSet.getString("degree_name"));
                a.setIdDegree(resultSet.getInt("PK_IdDegree"));
                a.setLength(resultSet.getInt("length"));
                degreeList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degreeList;
    }

    public static List<CourseLevel> fetchCourseLevelFromDB(){
        List<CourseLevel> courseLevels = new ArrayList<>();
        try {
            while(resultSet.next()) {
                CourseLevel a = new CourseLevel();
                a.setLevelName(resultSet.getString("level_name"));
                a.setDescription(resultSet.getString("description"));
                a.setObjectifs(resultSet.getString("objectifs"));
                a.setIdLevel(resultSet.getInt("PK_idLevel"));
                courseLevels.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseLevels;
    }


}
