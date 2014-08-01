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
 * AccountPaymentDetailList Service class
 *
 * @version 1.0, 2010/12/23
 */
public class AccountPaymentDetailListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    //private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public AccountPaymentDetailListSvc() {}

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
            pstmt.setString(index++, retrieve.getString("pay_yymm"));
            if (!"".equals(retrieve.getString("pay_times"))) {
            	pstmt.setString(index++, retrieve.getString("pay_times"));
            }
            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("pay_yymm"));
            if (!"".equals(retrieve.getString("pay_times"))) {
            	pstmt.setString(index++, retrieve.getString("pay_times"));
            }
            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("pay_yymm"));
            if (!"".equals(retrieve.getString("pay_times"))) {
            	pstmt.setString(index++, retrieve.getString("pay_times"));
            }
            pstmt.setString(index++, retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[AccountPaymentDetailListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AccountPaymentDetailListSvc.retrieve() Exception : ERR-"+e.getMessage());
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


		sb.append("  SELECT A.ENTP_CODE, A.ENTP_NAME, TO_CHAR(TO_DATE(A.PAY_YYMM, 'YYYYMM'), 'YYYY/MM') AS PAY_YYMM, A.PAY_TIMES,                                       \n");
		sb.append("         LAST_HOLD_AMT,                                                                                                                              \n");
		sb.append("         CURR_AMT,                                                                                                                                   \n");
		sb.append("         CURR_HOLD_AMT,                                                                                                                              \n");
		sb.append("         CURR_DEDUCT_AMT,                                                                                                                            \n");
		sb.append("         CURR_PAY_AMT,                                                                                                                               \n");
		sb.append("         CONF_YN,                                                                                                                                    \n");
		sb.append("         TO_CHAR(PAY_DATE, 'YYYY/MM/DD') AS PAY_DATE,                                                                                                \n");
		sb.append("         B.*                                                                                                                                         \n");
		sb.append("    FROM (                                                                                                                                           \n");
		sb.append("          SELECT A.ENTP_CODE,                                                                                                                        \n");
		sb.append("                 B.ENTP_NAME,                                                                                                                        \n");
		sb.append("                 PAY_YYMM,                                                                                                                           \n");
		sb.append("                 PAY_TIMES,                                                                                                                          \n");
		sb.append("                 LAST_HOLD_AMT,                                                                                                                      \n");
		sb.append("                 CURR_AMT,                                                                                                                           \n");
		sb.append("                 CURR_HOLD_AMT,                                                                                                                      \n");
		sb.append("                 CURR_DEDUCT_AMT,                                                                                                                    \n");
		sb.append("                 CURR_PAY_AMT,                                                                                                                       \n");
		sb.append("                 CONF_YN,                                                                                                                            \n");
		sb.append("                 PAY_DATE,                                                                                                                           \n");
		sb.append("                 ROW_NUMBER() OVER(PARTITION BY A.ENTP_CODE, A.PAY_YYMM, A.PAY_TIMES ORDER BY A.ENTP_CODE, A.PAY_YYMM, A.PAY_TIMES) AS RN            \n");
		sb.append("            FROM TACCTPAYM A, TENTERPRISE B                                                                                                          \n");
		sb.append("           WHERE A.ENTP_CODE = B.ENTP_CODE                                                                                                           \n");
		sb.append("             AND A.PAY_YYMM    = TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM')                                                                            \n");
		if (!"".equals(retrieve.getString("pay_times"))) {
			sb.append("             AND A.PAY_TIMES   = ?                                                                                                                   \n");
		}
		sb.append("             AND A.ENTP_CODE   LIKE ? || '%'                                                                                                         \n");
		sb.append("      ) A FULL OUTER JOIN (                                                                                                                          \n");
		sb.append("          SELECT NVL(B.PAY_YYMM, C.PAY_YYMM) AS PAY_YYMM,                                                                                            \n");
		sb.append("                 NVL(B.PAY_TIMES, C.PAY_TIMES) AS PAY_TIMES,                                                                                         \n");
		sb.append("                 NVL(B.ENTP_CODE, C.ENTP_CODE) AS ENTP_CODE,                                                                                         \n");
		sb.append("                 B.HO_DE_CODE AS HO_DE_CODE1,                                                                                                        \n");
		sb.append("                 B.HO_DE_NAME AS HO_DE_NAME1,                                                                                                        \n");
		sb.append("                 B.HO_DE_AMT AS HO_DE_AMT1,                                                                                                          \n");
		sb.append("                 C.HO_DE_CODE AS HO_DE_CODE2,                                                                                                        \n");
		sb.append("                 C.HO_DE_NAME AS HO_DE_NAME2,                                                                                                        \n");
		sb.append("                 C.HO_DE_AMT AS HO_DE_AMT2,                                                                                                          \n");
		sb.append("                 ROW_NUMBER() OVER(PARTITION BY NVL(B.ENTP_CODE, C.ENTP_CODE), NVL(B.PAY_YYMM, C.PAY_YYMM), NVL(B.PAY_TIMES, C.PAY_TIMES)            \n");
		sb.append("                                       ORDER BY NVL(B.ENTP_CODE, C.ENTP_CODE), NVL(B.PAY_YYMM, C.PAY_YYMM), NVL(B.PAY_TIMES, C.PAY_TIMES)) AS RN     \n");
		sb.append("            FROM (                                                                                                                                   \n");
		sb.append("              SELECT PD.PAY_YYMM,                                                                                                                    \n");
		sb.append("                     PD.PAY_TIMES,                                                                                                                   \n");
		sb.append("                     PD.ENTP_CODE,                                                                                                                   \n");
		sb.append("                     PD.HO_DE_CODE,                                                                                                                  \n");
		sb.append("                     CC.CODE_NAME AS HO_DE_NAME,                                                                                                     \n");
		sb.append("                     PD.HO_DE_FLAG,                                                                                                                  \n");
		sb.append("                     PD.HO_DE_AMT,                                                                                                                   \n");
		sb.append("                     PD.REMARK,                                                                                                                      \n");
		sb.append("                     ROW_NUMBER() OVER(PARTITION BY ENTP_CODE, PAY_YYMM, PAY_TIMES ORDER BY ENTP_CODE, PAY_YYMM, PAY_TIMES) AS RN                    \n");
		sb.append("                FROM TACCTPAYDT PD, TCODE CC                                                                                                         \n");
		sb.append("               WHERE PD.HO_DE_CODE  = CC.CODE_MGROUP                                                                                                 \n");
		sb.append("                 AND CC.CODE_LGROUP = 'A002'                                                                                                         \n");
		sb.append("                 AND PD.PAY_YYMM    = TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM')                                                                       \n");
		if (!"".equals(retrieve.getString("pay_times"))) {
			sb.append("                 AND PD.PAY_TIMES   = ?                                                                                                              \n");
		}
		sb.append("                 AND PD.ENTP_CODE   LIKE ? || '%'                                                                                                    \n");
		sb.append("                 AND PD.HO_DE_FLAG  = '1'                                                                                                            \n");
		sb.append("              ) B FULL OUTER JOIN (                                                                                                                  \n");
		sb.append("              SELECT PD.PAY_YYMM,                                                                                                                    \n");
		sb.append("                     PD.PAY_TIMES,                                                                                                                   \n");
		sb.append("                     PD.ENTP_CODE,                                                                                                                   \n");
		sb.append("                     PD.HO_DE_CODE,                                                                                                                  \n");
		sb.append("                     CC.CODE_NAME AS HO_DE_NAME,                                                                                                     \n");
		sb.append("                     PD.HO_DE_FLAG,                                                                                                                  \n");
		sb.append("                     PD.HO_DE_AMT,                                                                                                                   \n");
		sb.append("                     PD.REMARK,                                                                                                                      \n");
		sb.append("                     ROW_NUMBER() OVER(PARTITION BY ENTP_CODE, PAY_YYMM, PAY_TIMES ORDER BY ENTP_CODE, PAY_YYMM, PAY_TIMES) AS RN                    \n");
		sb.append("                FROM TACCTPAYDT PD, TCODE CC                                                                                                         \n");
		sb.append("               WHERE PD.HO_DE_CODE  = CC.CODE_MGROUP                                                                                                 \n");
		sb.append("                 AND CC.CODE_LGROUP = 'A002'                                                                                                         \n");
		sb.append("                 AND PD.PAY_YYMM    = TO_CHAR(TO_DATE(?, 'YYYY/MM'), 'YYYYMM')                                                                       \n");
		if (!"".equals(retrieve.getString("pay_times"))) {
			sb.append("                 AND PD.PAY_TIMES   = ?                                                                                                          \n");
		}
		sb.append("                 AND PD.ENTP_CODE   LIKE ? || '%'                                                                                                    \n");
		sb.append("                 AND PD.HO_DE_FLAG  = '2'                                                                                                            \n");
		sb.append("              ) C ON (B.PAY_YYMM = C.PAY_YYMM AND B.PAY_TIMES = C.PAY_TIMES AND B.ENTP_CODE = C.ENTP_CODE AND B.RN = C.RN)                           \n");
		sb.append("      ) B ON (B.PAY_YYMM = A.PAY_YYMM AND B.PAY_TIMES = A.PAY_TIMES AND B.ENTP_CODE = A.ENTP_CODE AND B.RN = A.RN)                                   \n");
		sb.append("       ORDER BY NVL(A.PAY_YYMM, B.PAY_YYMM)                                                                                                          \n");
		sb.append("      , NVL(A.PAY_TIMES, B.PAY_TIMES)                                                                                                                \n");
		sb.append("      , NVL(A.ENTP_CODE, B.ENTP_CODE)                                                                                                                \n");
		sb.append("      , NVL(A.RN, B.RN)                                                                                                                              \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("pay_yymm: "     + retrieve.getString("pay_yymm"));
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
