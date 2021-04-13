package core.utils;

import core.CourseLevel;
import core.Degree;
import core.Student;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cxo   {
    private static Connection connection;
    private static ResultSet resultSet;
    private static String sqlQuery;
    private static Statement stm;
    private static String cred = "jdbc:mysql://127.0.0.1:3306/esihdb?serverTimezone=UTC";


    public Cxo() {
    }

    public static void pickBaseParameter(String param){
        sqlQuery ="select * from" + " "+param;
    }
    public static void initConnection() {
        // loading driver
        try {
        // new Driver name : com.mysql.cj.jdbc.Driver
        // old Driver name : com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        // Establishing a new connection
        try {
            connection= DriverManager.
                    getConnection(cred,"nebel","nebel1984");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void fechtFromDB() {
        try {
            resultSet = stm.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDBSchema(){
        // Creating esihdb schema
        try {
            // There is no needs for a new statement
            // because MyBatis wrapper is taking care of that part.
            //stm= connection.createStatement();
            System.out.println("Connection established......");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            //Creating a reader object
            Reader reader = new BufferedReader(
                    new FileReader("/home/schatz/IdeaProjects" +
                            "/ESIH_App/src/db/sampleScript.sql"));
            //Running the script
            sr.runScript(reader);
            System.out.println("Hello");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    *   This section is dedicated to all operations
    * that involve student including :
    * 1. add student
    * 2. fetch a student
    * 3. check student id
    * */

    // function to add student
    public static void insertData(Student student){
        try {
            String sqlString = "call esihdb.addStudent(?,?,?,?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setString(1,student.getFirstname());
            pr.setString(2,student.getLastname());
            pr.setString(3,student.getStudentGender());
            pr.setInt(4,student.getYear());
             pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    // function to check student id validity
    public static boolean isStudentIDValid(Long id){
        try {
            String sqlString = "call esihdb.isIDValid(?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setInt(1,id.intValue());
            resultSet = pr.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("status") == 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
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

/*
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
*/


}
