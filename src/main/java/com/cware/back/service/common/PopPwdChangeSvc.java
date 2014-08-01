
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tuser;

/**
 * PopDbPlan Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class PopPwdChangeSvc {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    public PopPwdChangeSvc() {}

    /**
     * <PRE>
     * Desc : Make SQL ; update DB PLAN
     * </PRE>
     * @return  String
     */
     private String makeSqlUpdate(Tuser user) throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("UPDATE TUSER       \n ");
         sb.append("   SET PASSWD = ?  \n");
         sb.append(" WHERE USER_ID = ? \n ");
 	        	 
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug(sb.toString());
         }
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : Make SQL ; SELECT
    * </PRE>
    * @return  String
    */
    private String makeSql() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT USER_ID   \n ");
        sb.append("  FROM TUSER     \n ");
        sb.append(" WHERE USER_ID = ?   \n "); 
        sb.append("   AND PASSWD  = ?   \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }
    
//  = update -------------------------------------------------
    /**
    * <PRE>
    * Desc : update 
    * </PRE>
    * @param   Connection
    * @param   retrieve
    * @return  int
    */
    public int update(Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(user));

            pstmt.setString(1, user.getPasswd());
            pstmt.setString(2, user.getUser_id());
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user.getPasswd()); logString.append( "/" );
            logString.append( user.getUser_id()); logString.append( "/" );
            log.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
        	log.error("[PopPwdChangeSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	log.error("[PopPwdChangeSvc.update() Exception : ERR-"+e.getMessage());
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
   public String retrieve (Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;  
        String rtnMsg = "000000";
        HashMap      hmSheet    = null;
        long         retRow     = 0;
        
        try {            
                        
            pstmt = con.prepareStatement(makeSql());
         
            pstmt.setString(1, user.getUser_id());
            pstmt.setString(2, user.getRemark());

//          = log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user.getUser_id()); logString.append( "/" );
            logString.append( user.getRemark()); logString.append( "/" );
            log.info(logString.toString());
            
            rset  = pstmt.executeQuery();    
            
            while (rset!=null && rset.next()){
                hmSheet = new HashMap();
                hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
                retRow++;
            }
            
            if (retRow == 0){
            	rtnMsg = ComUtils.getMessage("msg.check_pass");
            }
            
        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return rtnMsg;
    }


}
