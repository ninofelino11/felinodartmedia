
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tsms;

/**
 * PopDbPlan Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class PopSmsSvc {

//    private static Log log     = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave = LogFactory.getLog(Construct.LOG_SAVE);

    public PopSmsSvc() {}

    /**
     * <PRE>
     * Desc : Make SQL ; insert DB PLAN
     * </PRE>
     * @return  String
     */
     private String makeSqlInsert() throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("INSERT INTO TSMS \n ");
         sb.append("   		( SMS_FLAG, \n ");
         sb.append("	 	  CUST_NO, \n ");
         sb.append("	 	  CUST_NAME, \n ");
         sb.append("	 	  HP_NO, \n ");
         sb.append("	 	  REMARK1, \n ");
         sb.append("	 	  INSERT_DATE ) \n");
         sb.append(" VALUES ( ?,  \n ");
         sb.append(" 		  ?,  \n ");
         sb.append(" 		  ?,  \n ");
         sb.append(" 		  ?,  \n ");
         sb.append(" 		  ?,  \n ");
         sb.append(" 		  SYSDATE ) \n ");
 	        	 
         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) logSave.debug(sb.toString());
         return sb.toString();
     }

//  = insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : insert 
    * </PRE>
    * @param   Connection
    * @param   retrieve
    * @return  int
    */
    public int insert(Connection con, Tsms sms) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;
            pstmt.setString(index++, sms.getSms_flag());
            pstmt.setString(index++, sms.getCust_no());
            pstmt.setString(index++, sms.getCust_name());
            pstmt.setString(index++, sms.getHp_no());
            pstmt.setString(index++, sms.getRemark1());
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sms.getSms_flag()); logString.append( "/" );
            logString.append( sms.getCust_no()); logString.append( "/" );
            logString.append( sms.getCust_name()); logString.append( "/" );
            logString.append( sms.getHp_no()); logString.append( "/" );
            logString.append( sms.getRemark1()); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
        	logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
    
}
