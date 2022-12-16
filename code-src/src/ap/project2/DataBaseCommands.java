package ap.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableView; 

public class DataBaseCommands {

    Connection conn;  // declaration of conn variable
    final String userName = "hamad_saif"; // username varibale 
    final String userPassword = "hamad_saifDB"; // userPassword virable
    private String DB_URL = "jdbc:mysql://localhost:3306/studentsdb_allaaboun_alharbi"; // database url with DB name

    public DataBaseCommands() {

        try {
            this.conn = DriverManager.getConnection(DB_URL, userName, userPassword); //try to connect then save the connection in this.conn 
            System.out.println("connection has been successful"); // print successful connection

        } catch (SQLException e) {
            System.out.println("bad connection: " + e.getMessage()); // print bad connection
            System.exit(0); //exit(0) : Generally used to indicate successful termination.

        }

    }

    public boolean InsertInto(String FullName, String DateOFBirth, Float GPA) {
        try {
            String sql = String.format("insert into studentstbl_hamad_saif (fullname,dateofbirth,gpa) values ('%s','%s',%f)", FullName, DateOFBirth, GPA); // format the sql commands to parse it 
            PreparedStatement pStmt = conn.prepareStatement(sql); // create PreparedStatement and parse sql commands
            int result = pStmt.executeUpdate(sql); // do the sql command 
            return true; // return true for seccessfully added
        } catch (SQLException e) { // cache the error
            return false;// return false for failure added
        }
    }

    public void getAllStudentsRecord(TableView table , Label tableErrorText) {
        String sql = "select * from studentstbl_hamad_saif "; // sql command for get all studentds from the table
        try {
            ResultSet result = preStatmentQuery(sql); // do the sql command  then return the result
            if (result == null) {
                return; // if result is null , that`s mean there is an error  get out
            }
            pullRecords(result, table,tableErrorText); // add Query result to table view
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // print error for dev
        }

    }

    public void searchForAStudent(String name, TableView table,Label tableErrorText) {

        String sql = "select * from studentstbl_hamad_saif where fullname like '%" + name + "%'"; // sql command for select a spesific search value

        try {
            ResultSet result = preStatmentQuery(sql); // parse the sql command to db then return the result
            if (result == null) {
                return; // if result is null , that`s mean there is an error  get out
            }
            pullRecords(result, table,tableErrorText); // add Query result to list view
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // print error for dev
        }

    }

    private ResultSet preStatmentQuery(String sql) {
        try {
            PreparedStatement pStmt = conn.prepareStatement(sql); // create PreparedStatement for sql command
            ResultSet result = pStmt.executeQuery(sql); // do the sql command 
            return result; // return the result if there is no error
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // // print error for dev
            return null; // return null to handle the error
        }
    }

    public void pullRecords(ResultSet result, TableView table, Label tableErrorText) throws SQLException { // print format
        int counter = 0;
        while (result.next()) { // loop to print the Query result
            table.getItems().add(new Student(result.getInt(1), result.getString(2), result.getDate(3) + "", result.getFloat(4))); // assign values to table view
            counter++; // counter will be number of row
        }
        if (counter ==0) { // if counter = 0 that`s mean no data found
            tableErrorText.setText("no records available for this search criteri"); // set unsuccessful data error
        }

    }

    public void disconnect() { //disconnected function
        try {
            conn.close(); // try to close the connection 
            System.out.println("connection has been closed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());// print error for dev
        }
    }
}
