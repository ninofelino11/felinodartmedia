
package com.cware.back.service.common;

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
 * PopDbPlan Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class PopDbPlanSvc {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    public PopDbPlanSvc() {}

    /**
     * <PRE>
     * Desc : Make SQL ; INSERT DB PLAN
     * </PRE>
     * @return  String
     */
     private String makeSqlInsert(RetrieveModel retrieve) throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("EXPLAIN PLAN   \n ");
         sb.append("    SET STATEMENT_ID = '");
         sb.append( retrieve.getString("ID") );
         sb.append("'\n ");
         sb.append("    FOR \n "); 
         sb.append( retrieve.getString("SQL") );
 	        	 
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug(sb.toString());
         }
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : Make SQL ; SELECT DB PLAN
    * </PRE>
    * @return  String
    */
    private String makeSql() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT CARDINALITY AS ID,   \n ");
        sb.append("       LPAD('  ', LEVEL-1) || OPERATION ||''||OPTIONS||''||OBJECT_NAME AS DISCRIPTION,  \n ");
        sb.append("       OBJECT_OWNER, \n "); 
        sb.append("       OBJECT_NAME,  \n ");
        sb.append("       COST,         \n ");
        sb.append("       BYTES         \n ");
        sb.append("  FROM PLAN_TABLE    \n ");
        sb.append("  CONNECT BY PRIOR ID = PARENT_ID           \n "); 
        sb.append("      AND PRIOR STATEMENT_ID = STATEMENT_ID \n ");
        sb.append("    START WITH ID = 0                       \n ");
        sb.append("      AND STATEMENT_ID = ?                  \n ");
        sb.append("    ORDER BY ID                             \n ");  

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

//  = Edit Result-----------------------------------------------
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
        long         retRow     = 0;

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
    
//  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert PLAN_TABLE
    * </PRE>
    * @param   Connection
    * @param   retrieve
    * @return  int
    */
    public int insert(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(retrieve));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("sql")); logString.append( "/" );
            logString.append( retrieve.getString("ID")); logString.append( "/" );
            log.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
        	log.error("[PopDbPlanSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	log.error("[PopDbPlanSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            //DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
    
    /**
    * <PRE>
    * Desc : SELECT DB PLAN
    * </PRE>
    * @param   poolName
    * @param   String
    * @return  HashMap
    */
   public RetrieveModel retrieve (Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;      

        try {            
                        
            pstmt = con.prepareStatement(makeSql());
         
            pstmt.setString(1, retrieve.getString("ID"));

//          = log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("sql")); logString.append( "/" );
            logString.append( retrieve.getString("ID")); logString.append( "/" );
            log.info(logString.toString());
            
            rset  = pstmt.executeQuery();            
            
            retrieve.put("RESULT",makeSheet(rset));                       
            
        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


}
