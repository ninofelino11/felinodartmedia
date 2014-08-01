package com.dartmedia.felino;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ni
 */
public class gSqlContainer
{

    /**
     *
     * @return
     */
    public  SimpleJDBCConnectionPool getConnection() 
    {     SimpleJDBCConnectionPool conn = null;
        try {
          
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                    "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
                    "dartmedia", "dartmedia",2,5);
             
            SQLContainer container;
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(gSqlContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
        
    }
}