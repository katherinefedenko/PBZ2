package main;

import modelP.Patient;
import ui.MainWindow;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.createMainWindow();
        /*String url = "jdbc:mysql://localhost/test_schema?useUnicode=true&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(url, username, password);

            String command1 = "CREATE TABLE transfers (id int, room int, date text, telephone text, FOREIGN KEY (id) REFERENCES patients (id))";

            String command = "CREATE TABLE patients (id int PRIMARY KEY AUTO_INCREMENT, surname text, name text, " +
                    "secondName text, sex text, age text, " +
                    "diagnosis text, howArrive text, dateOfArrival text, " +
                    "growth text, hairColor text, specialSings text, " +
                    "possibleAge text, transfer text, dateOfDischarge text, causeOfDischarge text, room int, FOREIGN KEY (room) REFERENCES rooms (room))";

            PreparedStatement preparedStatement = connection.prepareStatement(command1);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            //numberOfRoom text, telephoneNumber text
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
    }
}
