package core.utils;

import core.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cxo {
    private static Connection connection;
    private static ResultSet resultSet;
    private static String sqlQuery;
    private static Statement stm;
    private static String cred = "jdbc:mysql://127.0.0.1:3306/esihdb?serverTimezone=UTC";


    public Cxo() {
    }

    public static void pickBaseParameter(String param) {
        sqlQuery = "select * from" + " " + param;
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
            connection = DriverManager.
                    getConnection(cred, "nebel", "nebel1984");
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

    public static void createDBSchema() {
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
    public static void insertData(Student student) {
        try {
            String sqlString = "call esihdb.addStudent(?,?,?,?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setString(1, student.getFirstname());
            pr.setString(2, student.getLastname());
            pr.setString(3, student.getStudentGender());
            pr.setInt(4, student.getYear());
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

        }
    }

    // function to check student id validity
    public static boolean isStudentIDValid(Long id) {
        try {
            String sqlString = "call esihdb.isIDValid(?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setInt(1, id.intValue());
            resultSet = pr.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("status") == 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

        }
        // TODO check this out
        return true;
    }

    // Fetching all degree program directly from database
    public static List<Degree> fetchDegreeFromDB() {
        List<Degree> degreeList = new ArrayList<>();
        try {
            String sqlString = "call esihdb.getDegreeListFromDB();";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Degree a = new Degree();
                a.setDegreeName(resultSet.getString("degree_name"));
                a.setIdDegree(resultSet.getInt("PK_IdDegree"));
                a.setLength(resultSet.getInt("length"));
                degreeList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

        }
        return degreeList;
    }

    // Fetching all students from database
    public static void fetchStudentListFromDB() {
        initConnection();
        try {
            String sqlString = "call esihdb.getStudents()";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent((long) resultSet.getInt("PK_idStudent"));
                student.setFname(resultSet.getString("fname"));
                student.setLname(resultSet.getString("lname"));
                student.setYear(resultSet.getInt("yob"));

                DAO.getSingletonObjetDAO().
                        getStudentList().add(student);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

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

    public static boolean isInsertFeasible(Object o){
        boolean testStatus = false;
        if(o instanceof Degree){
            Degree degree = (Degree) o;
            String sqlString = "call esihdb.isDegreeExisted(?,?)";
            try {
                PreparedStatement pr = connection.prepareStatement(sqlString);
                pr.setString(1, degree.getDegreeName());
                pr.setInt(2, 0);
                resultSet = pr.executeQuery();
                if(resultSet.next()){
                    if(resultSet.getInt("status")==0){
                        testStatus = true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println(throwables.toString());
                }

            }
        }

        return testStatus;
    }

    public static boolean insertData(Degree degree) {
        boolean statusInsertion = false;
        if(isInsertFeasible(degree)){
            try {
                String sqlString = "call esihdb.insertDegree(?,?)";
                PreparedStatement pr = connection.prepareStatement(sqlString);
                pr.setString(1, degree.getDegreeName());
                pr.setInt(2, degree.getLength());
                pr.execute();
                statusInsertion = true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }finally {
                try {
                    connection.close();
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println(throwables.toString());
                }

            }
        }
        return statusInsertion;
    }

    public static int fetchDegreeInfo(String degreeName) {
        int id = 0;
        try {
            String sqlString = "call esihdb.getDegreeByName(?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setString(1, degreeName);

            resultSet = pr.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt("PK_idDegree");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

        }
        return id;
    }

    public static List<DegreeLevel> fetchLevelListByDegree(
            String DegreeInfos) {
        DegreeInfos = DegreeInfos.substring(0,DegreeInfos.lastIndexOf(" "));
        List<DegreeLevel> degreeLevels = new ArrayList<>();
        DegreeLevel  level;
        try {
            System.out.println("Parameter :"+DegreeInfos);
            String sqlString = "call esihdb.getLevelListbyDegree(?)";
            PreparedStatement pr = connection.prepareStatement(sqlString);
            pr.setString(1, DegreeInfos);
            System.out.println("SQL "+pr.toString());
            resultSet = pr.executeQuery();
            System.out.println("Before getting into..."+connection.getWarnings());
            while(resultSet.next()){
                level = new DegreeLevel();
                level.setLevelName(
                        resultSet.getString("level_name"));
                System.out.println("Loading :"+
                        level.getLevelName());
                degreeLevels.add(level);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.toString());
            }

        }
        System.out.println("How many level:"+degreeLevels.size());
        return degreeLevels;
    }
}
