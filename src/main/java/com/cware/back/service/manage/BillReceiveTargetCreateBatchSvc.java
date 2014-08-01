
package com.cware.back.service.manage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;

/**
 * Excute BillReceiveTargetCreateBatch Service class
 *
 * @version 1.0, 2010/12/28
 */
public class BillReceiveTargetCreateBatchSvc {


    //private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BillReceiveTargetCreateBatchSvc() {}


    //= Excute Procedure-----------------------------------------------
    /**
    * <PRE>
    * Desc : Excute Procedure SP_CALCULATE_PAYMENT
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel execProcedure(Connection con, RetrieveModel retrieve) throws StoreException{
        CallableStatement cstmt     = null;

        try {
            cstmt = con.prepareCall("begin SP_TSMACCTINVOICE(?, ?, ?, ?, ?); end;");

            int index = 1;
            cstmt.registerOutParameter(index++, java.sql.Types.NUMERIC);
            cstmt.registerOutParameter(index++, java.sql.Types.VARCHAR);
            cstmt.setString(index++,retrieve.getString("user_id"));
            cstmt.setString(index++,retrieve.getString("pay_yymm"));
            cstmt.setString(index++,retrieve.getString("entp_code"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("pay_yymm")  ); logString.append( "/" );
            logString.append( retrieve.getString("entp_code")   ); logString.append( "/" );
            logString.append( retrieve.getString("user_id")   ); logString.append( "/" );

            logSave.info(logString.toString());

            //== 프로시져를 실행합니다.
            cstmt.executeUpdate();

            retrieve.put("outrtn",         cstmt.getInt(1));
            retrieve.put("outrtnmsg",      cstmt.getString(2));

        }catch(SQLException se){
            logSave.error("[BillReceiveTargetCreateBatchSvc.execProcedure() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[BillReceiveTargetCreateBatchSvc.execProcedure() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, null, null, cstmt, null, null);
        }
        return retrieve;
    }

}
