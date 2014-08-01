
package com.cware.back.service.manage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;

/**
 *  Service class
 *
 * @version 1.0, 2006/11/30
 */
public class PromoSegmentSvc {

    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PromoSegmentSvc() {}


    //= Excute Procedure-----------------------------------------------
    /**
    * <PRE>
    * Desc : Excute Procedure SP_INSERT_CUST_FROM_SEG
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel execProcedure(Connection con, RetrieveModel retrieve) throws StoreException{
        CallableStatement cstmt     = null;
        ResultSet         rset      = null;

        try {

            cstmt = con.prepareCall("begin SP_INSERT_CUST_FROM_SEG(?, ?, ?, '', ?, ?, ?, ?, ?); end;");

            int index = 1;
            cstmt.setString(index++,retrieve.getString("flag"));
            cstmt.setString(index++,retrieve.getString("promo_no"));
            cstmt.setString(index++,retrieve.getString("segment_code"));
            cstmt.setString(index++,retrieve.getString("proc_date"));
            cstmt.setString(index++,retrieve.getString("user_id"));
            cstmt.registerOutParameter(index++, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(index++, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(index++, java.sql.Types.VARCHAR);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("flag")  ); logString.append( "/" );   
            logString.append( retrieve.getString("promo_no")   ); logString.append( "/" );
            logString.append( retrieve.getString("segment_code")); logString.append( "/" );
            logString.append( retrieve.getString("proc_date")); logString.append( "/" );
            logString.append( retrieve.getString("user_id")   ); logString.append( "/" );
            logSave.info(logString.toString());

            //== 프로시져를 실행합니다.
            cstmt.executeUpdate();
            
            retrieve.put("out_proc_cnt", cstmt.getInt(6));
            retrieve.put("out_rtn",      cstmt.getInt(7));
            retrieve.put("out_rtn_msg",  ComUtils.NVL(cstmt.getString(8),""));
 
        }catch(SQLException se){
            logSave.error("[PromoSegmentSvc.execProcedure() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PromoSegmentSvc.execProcedure() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, null, null, cstmt, null, rset);
        }
        return retrieve;
    }

}
