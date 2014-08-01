package com.cware.back.service.manage;

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
 * AccountDailyCloseList Service class
 *
 * @version 1.0, 2010/12/23
 */
public class BillReceiveListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    //private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BillReceiveListSvc() {}

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

            int index = 1;
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("rcv_yn"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[BillReceiveListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[BillReceiveListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
		sb.append(" SELECT TO_CHAR(TO_DATE(PAY_YYMM, 'YYYYMM'), 'YYYY/MM') AS PAY_YYMM,    \n");
		sb.append("        A.ENTP_CODE,                                                    \n");
		sb.append("        B.ENTP_NAME,                                                    \n");
		sb.append("        LAST_HOLD_AMT,                                                  \n");
		sb.append("        CURR_AMT,                                                       \n");
		sb.append("        BILL_COST,                                                      \n");
		sb.append("        BILL_VAT,                                                       \n");
		sb.append("        BILL_AMT,                                                       \n");
		sb.append("        LAST_HOLD_AMT + CURR_AMT - BILL_AMT AS DIFF_AMT,                \n");
		sb.append("        CUT_AMT,                                                        \n");
		sb.append("        CURR_HOLD_AMT,                                                  \n");
		sb.append("        TO_CHAR(RCV_DATE, 'YYYY/MM/DD') AS RCV_DATE,                    \n");
		sb.append("        REMARK                                                          \n");
		sb.append("   FROM TACCTINVOICE A, TENTERPRISE B                                   \n");
		sb.append("  WHERE A.ENTP_CODE = B.ENTP_CODE                                       \n");
		sb.append("    AND PAY_YYMM = TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM')             \n");
		sb.append("    AND A.ENTP_CODE LIKE ? || '%'                                       \n");
		sb.append("    AND RCV_YN LIKE ? || '%'                                            \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("fromDate: "     + retrieve.getString("fromDate"));
            log.debug("entp_code: " + retrieve.getString("entp_code"));
            log.debug("rcv_yn: " + retrieve.getString("rcv_yn"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Edit retrieval result
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
}
