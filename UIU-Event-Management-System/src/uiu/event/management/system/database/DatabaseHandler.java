/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiu.event.management.system.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
     private static DatabaseHandler handler;
     private static final String DB_URL = "jdbc:derby:database;create=true";
     private static Connection conn = null;
     private static Statement stmt = null;
     
     
     public DatabaseHandler(){
         createConnection();
         setupBookTable();
     }
     
    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    void setupBookTable() {
            String TABLE_NAME = "EVENT";
            try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm= conn.getMetaData();
            ResultSet tables = dbm.getTables (null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table" + TABLE_NAME + "already exists. Ready for go!"); 
            }else{
                stmt.execute("CREATE TABLE" + TABLE_NAME + " ("
                    +"slot varchar(100)primary key, \n" 
                    +"assign varchar (200), \n"
                    +"purpose varchar(200), \n"
                    +"atime varchar(100), \n" 
                    +"adate varchar(100), \n"
                    +"isAvail boolean default true"
                    +"}");
            }
            } catch (SQLException e) {
            System.err.println(e.getMessage() +"...setupDatabase");
            } finally {
            }
            }
    
        public ResultSet execQuery(String query) {
        ResultSet result = null;
        try {
                stmt = conn.createStatement();
                result = stmt.executeQuery(query);
        } catch (SQLException ex) {
                System.out.println("Exception at execQuery: dataHandler" +ex.getLocalizedMessage()); 
        return null;
        } finally {
        return result;
        }
    
/*public boolean execAction(String qu) {
    try {
        Statement statement = conn.createStatement();
        statement.execute(qu);
        return true;
    } catch (SQLException ex) {
        String errorMessage = "Error: " + ex.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "Error Occurred", JOptionPane.ERROR_MESSAGE);
        System.out.println("Exception at execAction: " + ex.getMessage());
        return false;
    }*/
}
