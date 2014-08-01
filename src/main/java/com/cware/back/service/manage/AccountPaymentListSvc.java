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
public class AccountPaymentListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    //private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public AccountPaymentListSvc() {}

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
            pstmt.setString(index++, retrieve.getString("toDate"));
            if (!"".equals(retrieve.getString("pay_times"))) {
            	pstmt.setString(index++, retrieve.getString("pay_times"));
            }
            pstmt.setString(index++, retrieve.getString("entp_code"));
            
            
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[AccountPaymentListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AccountPaymentListSvc.retrieve() Exception : ERR-"+e.getMessage());
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

		sb.append("  SELECT A.ENTP_CODE,                                                                                             \n");
		sb.append("         B.ENTP_NAME,                                                                                             \n");
		sb.append("         TO_CHAR(TO_DATE(PAY_YYMM, 'YYYYMM'), 'YYYY/MM') AS PAY_YYMM,                                             \n");
		sb.append("         PAY_TIMES,                                                                                               \n");
		sb.append("         LAST_HOLD_AMT,                                                                                           \n");
		sb.append("         CURR_AMT,                                                                                                \n");
		sb.append("         CURR_HOLD_AMT,                                                                                           \n");
		sb.append("         CURR_DEDUCT_AMT,                                                                                         \n");
		sb.append("         CURR_PAY_AMT,                                                                                            \n");
		sb.append("         CONF_YN,                                                                                                 \n");
		sb.append("         TO_CHAR(PAY_DATE, 'YYYY/MM/DD') AS PAY_DATE                                                              \n");
		sb.append("    FROM TACCTPAYM A, TENTERPRISE B                                                                               \n");
		sb.append("   WHERE A.ENTP_CODE = B.ENTP_CODE                                                                                \n");
		sb.append("     AND PAY_YYMM BETWEEN TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM') AND TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM')   \n");
		if (!"".equals(retrieve.getString("pay_times"))) {
			sb.append("     AND PAY_TIMES = ?                                                                                        \n");
		}
		sb.append("     AND A.ENTP_CODE LIKE ? || '%'                                                                                \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("fromDate: "     + retrieve.getString("fromDate"));
            log.debug("fromDate: "     + retrieve.getString("toDate"));
            log.debug("pay_times: " + retrieve.getString("pay_times"));
            log.debug("entp_code: " + retrieve.getString("entp_code"));
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
