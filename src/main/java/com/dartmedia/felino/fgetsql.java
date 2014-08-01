package com.dartmedia.felino;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fgetsql {

String db = "jdbc:oracle:thin:@localhost:1521/XE";
String user = "dartmedia";
String pass = "dartmedia";
Connection c = null;
Statement s = null;

public fgetsql()
{
    System.out.println("Connect to Database");
try {Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
    
}
try {
c = DriverManager.getConnection(db,user,pass);
s = c.createStatement();
} catch (SQLException e) {
    System.out.println("cLASS NOT FOUND");
}
}

public ResultSet listmenu() {
    ResultSet executeQuery = null;
    
    try {
      
        executeQuery = s.executeQuery("select * from AD_MENU");
        return executeQuery;
    } catch (SQLException ex) {
        Logger.getLogger(fgetsql.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("FAIL"+fgetsql.class.getName());
         System.out.println("FAIL");
    }
    return  executeQuery;
           
           }



}


    
