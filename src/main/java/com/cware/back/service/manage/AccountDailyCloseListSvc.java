package com.cware.back.service.manage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



/**
 * AccountDailyCloseList Service class
 *
 * @version 1.0, 2010/12/23
 */
public class AccountDailyCloseListSvc {

    public AccountDailyCloseListSvc() {}

    public String makeSql() {

        StringBuffer sb = new StringBuffer();
        return null;
   /**     if ("DATE".equals(retrieve.getString("unit_retrieve"))) {
    		sb.append("SELECT TO_CHAR(A.GATHER_DATE, 'YYYY/MM/DD') AS GATHER_DATE,                             \n");
    		sb.append("       A.ENTP_CODE,                                                                     \n");
    		sb.append("       B.ENTP_NAME,                                                                     \n");
    		sb.append("       A.GOODS_CODE,                                                                    \n");
    		sb.append("       C.GOODS_NAME,                                                                    \n");
    		sb.append("       A.TAX_YN,                                                                        \n");
    		sb.append("       A.BUY_QTY,                                                                       \n");
    		sb.append("       A.BUY_COST,                                                                      \n");
    		sb.append("       A.BUY_VAT,                                                                       \n");
    		sb.append("       A.BUY_COST + A.BUY_VAT AS BUY_AMT,                                               \n");
    		sb.append("       A.RETURN_QTY,                                                                    \n");
    		sb.append("       A.RETURN_COST,                                                                   \n");
    		sb.append("       A.RETURN_VAT,                                                                    \n");
    		sb.append("       A.RETURN_COST + A.RETURN_VAT AS RETURN_AMT,                                      \n");
    		sb.append("       A.BUY_QTY - A.RETURN_QTY AS ACCOUNT_QTY,                                         \n");
    		sb.append("       A.BUY_COST - A.RETURN_COST AS ACCOUNT_COST,                                      \n");
    		sb.append("       A.BUY_VAT - A.RETURN_VAT AS ACCOUNT_VAT,                                         \n");
    		sb.append("       (A.BUY_COST + A.BUY_VAT) - (A.RETURN_COST + A.RETURN_VAT) AS ACCOUNT_AMT         \n");
    		sb.append("  FROM TSDSTATEMENT A, TENTERPRISE B, TGOODS C                                          \n");
    		sb.append(" WHERE A.ENTP_CODE = B.ENTP_CODE                                                        \n");
    		sb.append("   AND A.GOODS_CODE = C.GOODS_CODE                                                      \n");
    		sb.append("   AND A.GATHER_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE(?, 'YYYY/MM/DD')      \n");
    		sb.append("   AND A.DEL_YN = '0'                                                                   \n");
    		sb.append("   AND A.GOODS_CODE LIKE ? || '%'                                                       \n");
    		sb.append("   AND A.ENTP_CODE LIKE ? || '%'                                                        \n");
    		sb.append("   AND C.MD_CODE LIKE ? || '%'                                                          \n");
    		sb.append(" ORDER BY A.GATHER_DATE DESC, B.ENTP_NAME, C.GOODS_NAME                                 \n");
        } else {
    		sb.append("SELECT TO_CHAR(A.GATHER_DATE, 'YYYY/MM') AS GATHER_DATE,	                               \n");
    		sb.append("       A.ENTP_CODE,                                                                     \n");
    		sb.append("       B.ENTP_NAME,                                                                     \n");
    		sb.append("       A.GOODS_CODE,                                                                    \n");
    		sb.append("       C.GOODS_NAME,                                                                    \n");
    		sb.append("       A.TAX_YN,                                                                        \n");
    		sb.append("       NVL(SUM(A.BUY_QTY), 0) AS BUY_QTY,                                               \n");
    		sb.append("       NVL(SUM(A.BUY_COST), 0) AS BUY_COST,                                             \n");
    		sb.append("       NVL(SUM(A.BUY_VAT), 0) AS BUY_VAT,                                               \n");
    		sb.append("       NVL(SUM(A.BUY_COST + A.BUY_VAT), 0) AS BUY_AMT,                                  \n");
    		sb.append("       NVL(SUM(A.RETURN_QTY), 0) AS RETURN_QTY,                                         \n");
    		sb.append("       NVL(SUM(A.RETURN_COST), 0) AS RETURN_COST,                                       \n");
    		sb.append("       NVL(SUM(A.RETURN_VAT), 0) AS RETURN_VAT,                                         \n");
    		sb.append("       NVL(SUM(A.RETURN_COST + A.RETURN_VAT), 0) AS  RETURN_AMT,                        \n");
    		sb.append("       NVL(SUM(A.BUY_QTY - A.RETURN_QTY), 0) AS  ACCOUNT_QTY,                           \n");
    		sb.append("       NVL(SUM(A.BUY_COST - A.RETURN_COST), 0) AS  ACCOUNT_COST,                        \n");
    		sb.append("       NVL(SUM(A.BUY_VAT - A.RETURN_VAT), 0) AS  ACCOUNT_VAT,                           \n");
    		sb.append("       NVL(SUM((A.BUY_COST + A.BUY_VAT) - (A.RETURN_COST + A.RETURN_VAT)), 0) AS  ACCOUNT_AMT         \n");
    		sb.append("  FROM TSDSTATEMENT A, TENTERPRISE B, TGOODS C                                          \n");
    		sb.append(" WHERE A.ENTP_CODE = B.ENTP_CODE                                                        \n");
    		sb.append("   AND A.GOODS_CODE = C.GOODS_CODE                                                      \n");
        	sb.append("   AND A.GATHER_DATE BETWEEN TO_DATE(?, 'YYYY/MM') AND LAST_DAY(TO_DATE(?, 'YYYY/MM'))  \n");
    		sb.append("   AND A.DEL_YN = '0'                                                                   \n");
    		sb.append("   AND A.GOODS_CODE LIKE ? || '%'                                                       \n");
    		sb.append("   AND A.ENTP_CODE LIKE ? || '%'                                                        \n");
    		sb.append("   AND C.MD_CODE LIKE ? || '%'                                                          \n");
    		sb.append(" GROUP BY TO_CHAR(A.GATHER_DATE, 'YYYY/MM'), A.ENTP_CODE, B.ENTP_NAME, A.GOODS_CODE, C.GOODS_NAME, A.TAX_YN \n");
    		sb.append(" ORDER BY GATHER_DATE DESC, B.ENTP_NAME, C.GOODS_NAME                                 \n");
        }
        return sb.toString();
    }

    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
   ///         hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
      
        return (HashMap[])collection.toArray(new HashMap[0]);
        **/
    }
}
