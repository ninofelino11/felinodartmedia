
package com.cware.back.service.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * 시스템객체상태조회 Service class
 *
 * @version 1.0, 2008/05/09
 */
public class ObjectStatusCheckSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public ObjectStatusCheckSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
             
        sb.append(" SELECT OBJECT_NAME,  \n");
        sb.append("        OWNER OBJECT_OWNER, \n");
        sb.append("        STATUS,               \n");
        sb.append("        OBJECT_TYPE,          \n");
        sb.append("        TO_CHAR(CREATED, 'YYYY/MM/DD HH24:MI') CREATED,           \n");
        sb.append("        TO_CHAR(LAST_DDL_TIME, 'YYYY/MM/DD HH24:MI') LAST_DDL_TIME          \n");
        sb.append("   FROM ALL_OBJECTS   \n");
        sb.append("  WHERE OWNER IN ( '" + Construct.DB_WUSERNAME.toUpperCase() + "', '" + Construct.DB_SWUSERNAME.toUpperCase() + "')  \n");
        sb.append("    AND OBJECT_TYPE LIKE ? || '%'   \n");
        sb.append("    AND STATUS LIKE  ? || '%'             \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("object_owner  : " + retrieve.getString("object_owner"));
            log.debug("object_type  : " + retrieve.getString("object_type"));
            log.debug("status  : " + retrieve.getString("status"));
        }
        
        return sb.toString();
    }
    
    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow = 0;
        
        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
            
            //= Modify
            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        if (log.isDebugEnabled()) {
            if(hmSheet != null){
                Collection c=hmSheet.keySet();
                Iterator i=c.iterator();
                while(i.hasNext()){
                    Object key=i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));
            
            int index =1;
            pstmt.setString(index++, retrieve.getString("object_type"));
            pstmt.setString(index++, retrieve.getString("status"));
         
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));
            
        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}
