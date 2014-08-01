
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
 * Retrive Serve Entp Account List 생성 Service class
 *
 * @version 1.0, 2006/09/01
 */
public class ServeEntpAccountListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public ServeEntpAccountListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT  A.ENTP_CODE,   \n");
		 sb.append("         B.ENTP_NAME,   \n");
		 sb.append("         TO_CHAR(A.PURCHASE_FR,'YYYY/MM') AS PURCHASE_MONTH, \n");
		 sb.append("         A.PROCESS_DATE, \n");
		 sb.append("         A.PURCHASE_NO, \n");
		 sb.append("         A.PREV_YOUBO,  \n");
		 sb.append("         C.BUY_QTY,    \n");
		 sb.append("         C.BUY_COST,    \n");
		 sb.append("         C.BUY_VAT,     \n");
		 sb.append("         C.BUY_COST + C.BUY_VAT  AS BUY_PRICE, \n");
		 sb.append("         C.RETURN_QTY, \n");
		 sb.append("         C.RETURN_COST, \n");
		 sb.append("         C.RETURN_VAT,  \n");
		 sb.append("         C.RETURN_COST + C.RETURN_VAT  AS RETURN_PRICE,  \n");
		 sb.append("         A.SALE_VAT,	\n");
		 sb.append("         A.SALE_TAX_AMT + A.SALE_VAT + A.SALE_NOTAX_AMT - A.PREV_YOUBO  AS MONTHLY_ACCOUNT_AMT, \n");
		 sb.append("         A.YOUBO_AMT,	\n");
		 sb.append("         A.KONGJE_AMT,	\n");
		 sb.append("         A.TOT_PURCHASE_AMT	\n");
		 sb.append("    FROM TPURCHASE_M  A,	\n");
		 sb.append("         TENTERPRISE  B,	\n");
		 sb.append("         ( SELECT  D.PURCHASE_NO,	\n");
		 sb.append("                   SUM(D.BUY_COST)                    AS BUY_COST,	\n");
		 sb.append("                   SUM(D.BUY_VAT)                     AS BUY_VAT,	\n");
		 sb.append("                   SUM(D.BUY_QTY)					  AS BUY_QTY,	\n");
		 sb.append("                   SUM(D.RETURN_COST)                 AS RETURN_COST,	\n");
		 sb.append("                   SUM(D.RETURN_VAT)                  AS RETURN_VAT,	\n");
		 sb.append("                   SUM(D.RETURN_QTY)				  AS RETURN_QTY	\n");
		 sb.append("             FROM  TPURCHASE_DT D 	\n");
		 sb.append("         GROUP BY  D.PURCHASE_NO   ) C 	\n");
		 sb.append("   WHERE A.ENTP_CODE   = B.ENTP_CODE	\n");
		 sb.append("     AND A.PURCHASE_NO = C.PURCHASE_NO(+) \n");
		 sb.append("     AND A.PURCHASE_FR >= TO_DATE(?,'YYYY/MM')	\n");
		 sb.append("     AND A.PURCHASE_TO <  LAST_DAY(TO_DATE(?,'YYYY/MM')) + 1 	\n");
		 sb.append("     AND A.ENTP_CODE   LIKE ? ||'%'	\n");
		 sb.append("ORDER BY A.ENTP_CODE,  B.ENTP_NAME, A.PURCHASE_FR,  A.PURCHASE_NO	\n");
//		sb.append(" SELECT  A.ENTP_CODE                                      AS ENTP_CODE,           \n");
//        sb.append("         B.ENTP_NAME                                      AS ENTP_NAME,           \n");
//        sb.append("         A.PROCESS_DATE                                   AS PROCESS_DATE,        \n");
//        sb.append("         A.PURCHASE_NO                                    AS PURCHASE_NO,         \n");
//        sb.append("         A.PREV_YOUBO                                     AS PREV_YOUBO,          \n");
//        sb.append("         A.PREV_CREDIT_AMT                                AS PREV_CREDIT_AMT,     \n");
//        sb.append("         SUM(C.BUY_AMT)                                   AS BUY_AMT,             \n");
//        sb.append("         SUM(C.CANCEL_AMT)                                AS CANCEL_AMT,          \n");
//        sb.append("         A.BUY_VAT                                        AS BUY_VAT,             \n");
//        sb.append("         SUM(C.BUY_AMT) - SUM(C.OUT_AMT)                  AS MONTHLY_BUY_AMT,     \n");
//        sb.append("         SUM(C.SALE_AMT)                                  AS SALE_AMT,            \n");
//        sb.append("         SUM(C.OUT_AMT)                                   AS OUT_AMT,             \n");
//        sb.append("         SUM(C.SALE_AMT) - SUM(C.CANCEL_AMT)              AS MONTHLY_SALE_AMT,    \n");
//        sb.append("         A.SALE_VAT                                       AS SALE_VAT,            \n");
//        sb.append("         A.PREV_CREDIT_AMT + SUM(C.BUY_AMT) - SUM(C.OUT_AMT) + A.BUY_VAT - SUM(C.SALE_AMT) + SUM(C.CANCEL_AMT) + A.SALE_VAT AS REAL_CREDIT_PURCHASE, \n");
//        sb.append("         SUM(C.SALE_AMT) - SUM(C.CANCEL_AMT) + A.SALE_VAT AS MONTHLY_ACCOUNT_AMT, \n");
//        sb.append("         A.YOUBO_AMT                                      AS YOUBO_AMT,           \n");
//        sb.append("         A.KONGJE_AMT                                     AS KONGJE_AMT,          \n");
//        sb.append("         A.TOT_PURCHASE_AMT                               AS TOT_PURCHASE_AMT     \n");
//        sb.append("    FROM TPURCHASE_M  A,                                                          \n");
//        sb.append("         TENTERPRISE  B,                                                          \n");
//        sb.append("         TPURCHASE_DT C                                                           \n");
//        sb.append("   WHERE A.ENTP_CODE   = B.ENTP_CODE                                              \n");
//        sb.append("     AND A.PURCHASE_NO = C.PURCHASE_NO(+)                                         \n");
//        sb.append("     AND A.PURCHASE_FR >= TO_DATE( ? ,'YYYY/MM')                                  \n");
//        sb.append("     AND A.PURCHASE_TO <  LAST_DAY(TO_DATE( ? ,'YYYY/MM')) + 1                    \n");
//        sb.append("     AND A.ENTP_CODE   LIKE ?||'%'                                                \n");
//        sb.append("GROUP BY A.ENTP_CODE,  B.ENTP_NAME,       A.PROCESS_DATE,  A.PURCHASE_NO,         \n");
//        sb.append("         A.PREV_YOUBO, A.PREV_CREDIT_AMT, A.BUY_VAT,       A.SALE_VAT,            \n");
//        sb.append("         A.YOUBO_AMT,  A.KONGJE_AMT,      TOT_PURCHASE_AMT                        \n");
//        sb.append("ORDER BY A.ENTP_CODE,  B.ENTP_NAME,       A.PROCESS_DATE,  A.PURCHASE_NO,         \n");
//        sb.append("         A.PREV_YOUBO, A.PREV_CREDIT_AMT, A.BUY_VAT,       A.SALE_VAT,            \n");
//        sb.append("         A.YOUBO_AMT,  A.KONGJE_AMT,      TOT_PURCHASE_AMT                        \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("fromDate     : " + retrieve.getString("fromDate"));
            log.debug("toDate       : " + retrieve.getString("toDate"));
            log.debug("entp_code    : " + retrieve.getString("entp_code"));
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
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

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

            pstmt.setString(1,retrieve.getString("fromDate" ));
            pstmt.setString(2,retrieve.getString("toDate"   ));
            pstmt.setString(3,retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

}

