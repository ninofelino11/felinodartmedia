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
public class koneksi
{

    /**
     *
     * @return
     */
    public static SQLContainer getConnection() 
    {
       String sqlSyntax="select * from ad_menu";
       String sqlKey="AD_MENU_ID";
        try {
            SimpleJDBCConnectionPool conn = null;
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                    "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
                    "dartmedia", "dartmedia",2,5);
             
            SQLContainer container;
            container = new SQLContainer(new FreeformQuery(
                    sqlSyntax,connectionPool,sqlKey));
      
            return container;
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}