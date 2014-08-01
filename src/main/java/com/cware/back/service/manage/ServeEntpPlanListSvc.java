
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
 * Register ServeEntpEndList Service class
 *
 * @version 1.0, 2009/06/16
 */
public class ServeEntpPlanListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ServeEntpPlanListSvc() {}

    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()) {
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
            collection.add(hmSheet);
            retRow++;
        }

        if (log.isDebugEnabled()) {
            if(hmSheet != null) {
                Collection c=hmSheet.keySet();
                Iterator i=c.iterator();
                while(i.hasNext()) {
                    Object key=i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    public String makeSqlSub01(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.ENTP_CODE																						\n");
        sb.append("     , B.ENTP_NAME																						\n");
        sb.append("     , SUM(A.BUY_COST) 	                     AS BUY_COST												\n");
        sb.append("     , SUM(A.BUY_VAT)  	                     AS BUY_VAT													\n");
        sb.append("     , SUM(A.BUY_COST) + SUM(A.BUY_VAT)       AS BUY_PRICE												\n");
        sb.append("     , SUM(A.RETURN_COST)                     AS RETURN_COST												\n");
        sb.append("     , SUM(A.RETURN_VAT)                      AS RETURN_VAT												\n");
        sb.append("     , SUM(A.RETURN_COST) + SUM(A.RETURN_VAT) AS RETURN_PRICE											\n");
        sb.append("     , SUM(A.BUY_COST) - SUM(A.RETURN_COST)   AS PURCHASE_COST											\n");
        sb.append("     , SUM(A.BUY_VAT) - SUM(A.RETURN_VAT)     AS PURCHASE_VAT											\n");
        sb.append("     , (SUM(A.BUY_COST) + SUM(A.BUY_VAT)) - (SUM(A.RETURN_COST) + SUM(A.RETURN_VAT)) AS PURCHASE_PRICE	\n");
        sb.append("  FROM MSDPURCHASE A																						\n");
        sb.append("     , TENTERPRISE B																						\n");
        sb.append(" WHERE A.ENTP_CODE  = B.ENTP_CODE																		\n");
        sb.append("   AND A.BUY_MED    LIKE ? || '%'																		\n");
        sb.append("   AND A.ENTP_CODE  LIKE ? || '%'																		\n");
        sb.append("   AND A.GATHER_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE(?, 'YYYY/MM/DD')						\n");
        sb.append(" GROUP BY A.ENTP_CODE, B.ENTP_NAME																		\n");

        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
            log.debug("entp_code  : " + retrieve.getString("buy_med"));
            log.debug("fromDate   : " + retrieve.getString("from_Date"));
            log.debug("toDate     : " + retrieve.getString("to_Date"));

        }
        return sb.toString();
    }

    public String makeSqlSub02(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.ENTP_CODE																						\n");
        sb.append("     , B.ENTP_NAME																						\n");
        sb.append("     , A.GOODS_CODE																						\n");
        sb.append("     , C.GOODS_NAME																						\n");
        sb.append("     , A.BUY_MED																						\n");
        sb.append("     , SUM(A.BUY_QTY) 	                     AS BUY_QTY 												\n");
        sb.append("     , SUM(A.BUY_COST) 	                     AS BUY_COST												\n");
        sb.append("     , SUM(A.BUY_VAT)  	                     AS BUY_VAT													\n");
        sb.append("     , SUM(A.BUY_COST) + SUM(A.BUY_VAT)       AS BUY_PRICE												\n");
        sb.append("     , SUM(A.RETURN_QTY) 	                 AS RETURN_QTY												\n");
        sb.append("     , SUM(A.RETURN_COST)                     AS RETURN_COST												\n");
        sb.append("     , SUM(A.RETURN_VAT)                      AS RETURN_VAT												\n");
        sb.append("     , SUM(A.RETURN_COST) + SUM(A.RETURN_VAT) AS RETURN_PRICE											\n");
        sb.append("     , SUM(A.BUY_QTY) - SUM(A.RETURN_QTY)     AS PURCHASE_QTY											\n");
        sb.append("     , SUM(A.BUY_COST) - SUM(A.RETURN_COST)   AS PURCHASE_COST											\n");
        sb.append("     , SUM(A.BUY_VAT) - SUM(A.RETURN_VAT)     AS PURCHASE_VAT											\n");
        sb.append("     , (SUM(A.BUY_COST) + SUM(A.BUY_VAT)) - (SUM(A.RETURN_COST) + SUM(A.RETURN_VAT)) AS PURCHASE_PRICE	\n");
        sb.append("  FROM MSDPURCHASE A																						\n");
        sb.append("     , TENTERPRISE B																						\n");
        sb.append("     , TGOODS      C																						\n");
        sb.append(" WHERE A.ENTP_CODE  = B.ENTP_CODE																		\n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE																		\n");
        sb.append("   AND A.BUY_MED    LIKE ? || '%'																		\n");
        sb.append("   AND A.ENTP_CODE  LIKE ? || '%'																		\n");
        sb.append("   AND A.GATHER_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE(?, 'YYYY/MM/DD')						\n");
        sb.append(" GROUP BY A.ENTP_CODE, B.ENTP_NAME, A.GOODS_CODE, C.GOODS_NAME, A.BUY_MED								\n");

        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
            log.debug("entp_code  : " + retrieve.getString("buy_med"));
            log.debug("fromDate   : " + retrieve.getString("from_Date"));
            log.debug("toDate     : " + retrieve.getString("to_Date"));

        }
        return sb.toString();
    }

    public RetrieveModel retrieveSub01(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSub01(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("buy_med"));
            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("from_Date"));
            pstmt.setString(index++, retrieve.getString("to_Date"));


            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpPlanListSvc.retrieveSub01() SQLException : ERR-" + se.getErrorCode() + ":" + se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpPlanListSvc.retrieveSub01() Exception : ERR-" + e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    public RetrieveModel retrieveSub02(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSub02(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("buy_med"));
            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("from_Date"));
            pstmt.setString(index++, retrieve.getString("to_Date"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpPlanListSvc.retrieveSub02() SQLException : ERR-" + se.getErrorCode() + ":" + se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpPlanListSvc.retrieveSub02() Exception : ERR-" + e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}


