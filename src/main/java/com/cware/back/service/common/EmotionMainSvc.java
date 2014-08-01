
package com.cware.back.service.common;

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
* 총괄현황 Service Bean
*
* @version 1.0, 2007/03/08
*/

public class EmotionMainSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public EmotionMainSvc() {}

    /**
    * <PRE>
    * Desc : Make Sql AnalysisSellingTotal ; Day General Report
    * </PRE>
    * @param   RetrieveModel
    * @return  String : SQL
    */

    public String makeSql(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT AA.GATHER_DATE,                                                                                                         \n");
        sb.append("          SUM(AA.ORDER_QTY) AS ORDER_QTY,                                                                                         \n");
        sb.append("          SUM(AA.ORDER_AMT) AS ORDER_AMT,                                                                                         \n");
        sb.append("          SUM(AA.CANCEL_QTY) AS CANCEL_QTY,                                                                                       \n");
        sb.append("          SUM(AA.CANCEL_AMT) AS CANCEL_AMT,                                                                                       \n");
        sb.append("          SUM(AA.CLAIM_QTY - AA.CLAIM_CAN_QTY) AS CLAIM_QTY,                                                                       \n");
        sb.append("          SUM(AA.CLAIM_AMT - AA.CLAIM_CAN_AMT) AS CLAIM_AMT ,                                                                    \n");
        sb.append("          NVL(SUM(AA.ORDER_QTY - AA.CANCEL_QTY - AA.CLAIM_QTY + AA.CLAIM_CAN_QTY),0) AS SALE_QTY,                                       \n");
        sb.append("          NVL(SUM(AA.ORDER_AMT - AA.CANCEL_AMT - AA.CLAIM_AMT + AA.CLAIM_CAN_AMT),0) AS SALE_AMT                                        \n");
        sb.append("     FROM (                                                                                                                       \n");
        sb.append("           /* 주문접수 */                                                                                                         \n");
        sb.append("           SELECT TRUNC(SYSDATE) AS GATHER_DATE ,                                                                                 \n");
        sb.append("                  SUM(DECODE(ORDER_D_SEQ, '001', ORDER_QTY, 0)) AS ORDER_QTY,                                                     \n");
        sb.append("                  SUM(RSALE_AMT) AS ORDER_AMT,                                                                                    \n");
        sb.append("                  0 AS CANCEL_QTY,                                                                                                \n");
        sb.append("                  0 AS CANCEL_AMT,                                                                                                \n");
        sb.append("                  0 AS CLAIM_QTY,                                                                                                 \n");
        sb.append("                  0 AS CLAIM_AMT ,                                                                                                \n");
        sb.append("                  0 AS CLAIM_CAN_QTY,                                                                                             \n");
        sb.append("                  0 AS CLAIM_CAN_AMT                                                                                              \n");
        sb.append("           FROM   TORDERDT O                                                                                                      \n");
        sb.append("           WHERE  O.ORDER_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                                                       \n");
        sb.append("           AND    O.ORDER_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1                                                            \n");
        sb.append("           UNION ALL                                                                                                              \n");
        sb.append("           /* 일일취소현황 */                                                                                                      \n");
        sb.append("           SELECT TRUNC(SYSDATE) AS GATHER_DATE ,                                                                                  \n");
        sb.append("                  0 AS ORDER_QTY,                                                                                                  \n");
        sb.append("                  0 AS ORDER_AMT,                                                                                                  \n");
        sb.append("                  SUM(DECODE(D.ORDER_D_SEQ, '001', D.CANCEL_QTY, 0)) AS CANCEL_QTY,                                                \n");
        sb.append("                  SUM(D.RSALE_AMT) AS CANCEL_AMT,                                                                                  \n");
        sb.append("                  0 AS CLAIM_QTY,                                                                                                  \n");
        sb.append("                  0 AS CLAIM_AMT,                                                                                                  \n");
        sb.append("                  0 AS CLAIM_CAN_QTY,                                                                                              \n");
        sb.append("                  0 AS CLAIM_CAN_AMT                                                                                               \n");
        sb.append("           FROM   TCANCELDT D                                                                                                      \n");
        sb.append("           WHERE  D.CANCEL_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                                                       \n");
        sb.append("            AND   D.CANCEL_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1                         	                                           \n");
        sb.append("                                                                                                                                   \n");
        sb.append("           UNION ALL                                                                                                               \n");
        sb.append("           /* 일일반품현황 */                                                                                                      \n");
        sb.append("           SELECT TRUNC(SYSDATE) AS GATHER_DATE ,                                                                                  \n");
        sb.append("                  0 AS ORDER_QTY,                                                                                                  \n");
        sb.append("                  0 AS ORDER_AMT,                                                                                                  \n");
        sb.append("                  0 AS CANCEL_QTY,                                                                                                 \n");
        sb.append("                  0 AS CANCEL_AMT,                                                                                                 \n");
        sb.append("                  SUM(DECODE(D.CLAIM_GB, '30', DECODE(D.ORDER_D_SEQ, '001', D.CLAIM_QTY, 0), 0)) AS CLAIM_QTY,                     \n");
        sb.append("                  SUM(DECODE(D.CLAIM_GB, '30', D.RSALE_AMT, 0)) AS CLAIM_AMT,                                                      \n");
        sb.append("                  SUM(DECODE(D.CLAIM_GB, '31', DECODE(D.ORDER_D_SEQ, '001', D.CLAIM_QTY, 0), 0)) AS CLAIM_CAN_QTY,                 \n");
        sb.append("                  SUM(DECODE(D.CLAIM_GB, '31', D.RSALE_AMT, 0)) AS CLAIM_CAN_AMT                                                    \n");
        sb.append("           FROM   TCLAIMDT D                                                                                                        \n");
        sb.append("           WHERE  D.CLAIM_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                                                         \n");
        sb.append("            AND   D.CLAIM_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1                                                                         \n");
        sb.append("            AND   D.CLAIM_GB  IN ('30','31')                                                                                        \n");
        sb.append("                                                                                                                                    \n");
        sb.append("               ) AA                                                                                                                 \n");
        sb.append("   GROUP BY AA.GATHER_DATE                                                                                                          \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("fromDate    : " + retrieve.getString("fromDate"));
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make Sql AnalysisSellingTotal ; Week General Report
     * </PRE>
     * @param   RetrieveModel
     * @return  String : SQL
     */

     public String makeSqlWeeklySale(RetrieveModel retrieve) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT AA.GATHER_DATE,                                                                                                  \n");
         sb.append("          SUM(AA.ORDER_QTY) AS ORDER_QTY,                                                                               \n");
         sb.append("          SUM(AA.ORDER_AMT) AS ORDER_AMT,                                                                               \n");
         sb.append("          SUM(AA.CANCEL_QTY) AS CANCEL_QTY,                                                                             \n");
         sb.append("          SUM(AA.CANCEL_AMT) AS CANCEL_AMT,                                                                             \n");
         sb.append("          SUM(AA.CLAIM_QTY - AA.CLAIM_CAN_QTY) AS CLAIM_QTY,                                                            \n");
         sb.append("          SUM(AA.CLAIM_AMT - AA.CLAIM_CAN_AMT) AS CLAIM_AMT ,                                                           \n");
         sb.append("          NVL(SUM(AA.ORDER_QTY - AA.CANCEL_QTY - AA.CLAIM_QTY + AA.CLAIM_CAN_QTY),0) AS SALE_QTY,                              \n");
         sb.append("          NVL(SUM(AA.ORDER_AMT - AA.CANCEL_AMT - AA.CLAIM_AMT + AA.CLAIM_CAN_AMT),0) AS SALE_AMT                               \n");
         sb.append("     FROM (                                                                                                             \n");
         sb.append("           /* 주간주문접수 */                                                                                           \n");
         sb.append("           SELECT TRUNC(sysdate, 'iw') AS GATHER_DATE ,                                                                 \n");
         sb.append("                  SUM(DECODE(ORDER_D_SEQ, '001', ORDER_QTY, 0)) AS ORDER_QTY,                                           \n");
         sb.append("                  SUM(RSALE_AMT) AS ORDER_AMT,                                                                          \n");
         sb.append("                  0 AS CANCEL_QTY,                                                                                      \n");
         sb.append("                  0 AS CANCEL_AMT,                                                                                      \n");
         sb.append("                  0 AS CLAIM_QTY,                                                                                       \n");
         sb.append("                  0 AS CLAIM_AMT ,                                                                                      \n");
         sb.append("                  0 AS CLAIM_CAN_QTY,                                                                                   \n");
         sb.append("                  0 AS CLAIM_CAN_AMT                                                                                    \n");
         sb.append("           FROM   TORDERDT O                                                                                            \n");
         sb.append("           WHERE  O.ORDER_DATE >= TRUNC(sysdate, 'iw')                                                                  \n");
         sb.append("           AND    O.ORDER_DATE <= TRUNC(next_day(sysdate,2)-1)                                                          \n");
         sb.append("           UNION ALL                                                                                                    \n");
         sb.append("           /* 주간취소현황 */                                                                                           \n");
         sb.append("           SELECT TRUNC(sysdate, 'iw') AS GATHER_DATE ,                                                                 \n");
         sb.append("                  0 AS ORDER_QTY,                                                                                       \n");
         sb.append("                  0 AS ORDER_AMT,                                                                                       \n");
         sb.append("                  SUM(DECODE(D.ORDER_D_SEQ, '001', D.CANCEL_QTY, 0)) AS CANCEL_QTY,                                     \n");
         sb.append("                  SUM(D.RSALE_AMT) AS CANCEL_AMT,                                                                       \n");
         sb.append("                  0 AS CLAIM_QTY,                                                                                       \n");
         sb.append("                  0 AS CLAIM_AMT,                                                                                       \n");
         sb.append("                  0 AS CLAIM_CAN_QTY,                                                                                   \n");
         sb.append("                  0 AS CLAIM_CAN_AMT                                                                                    \n");
         sb.append("           FROM   TCANCELDT D                                                                                           \n");
         sb.append("           WHERE  D.CANCEL_DATE >= TRUNC(sysdate, 'iw')                                                                 \n");
         sb.append("            AND   D.CANCEL_DATE < TRUNC(next_day(sysdate,2)-1)                	                                         \n");
         sb.append("                                                                                                                        \n");
         sb.append("           UNION ALL                                                                                                    \n");
         sb.append("           /* 주간반품현황 */                                                                                           \n");
         sb.append("           SELECT TRUNC(sysdate, 'iw') AS GATHER_DATE ,                                                                 \n");
         sb.append("                  0 AS ORDER_QTY,                                                                                       \n");
         sb.append("                  0 AS ORDER_AMT,                                                                                       \n");
         sb.append("                  0 AS CANCEL_QTY,                                                                                      \n");
         sb.append("                  0 AS CANCEL_AMT,                                                                                      \n");
         sb.append("                  SUM(DECODE(D.CLAIM_GB, '30', DECODE(D.ORDER_D_SEQ, '001', D.CLAIM_QTY, 0), 0)) AS CLAIM_QTY,          \n");
         sb.append("                  SUM(DECODE(D.CLAIM_GB, '30', D.RSALE_AMT, 0)) AS CLAIM_AMT,                                           \n");
         sb.append("                  SUM(DECODE(D.CLAIM_GB, '31', DECODE(D.ORDER_D_SEQ, '001', D.CLAIM_QTY, 0), 0)) AS CLAIM_CAN_QTY,      \n");
         sb.append("                  SUM(DECODE(D.CLAIM_GB, '31', D.RSALE_AMT, 0)) AS CLAIM_CAN_AMT                                        \n");
         sb.append("           FROM   TCLAIMDT D                                                                                            \n");
         sb.append("           WHERE  D.CLAIM_DATE >= TRUNC(sysdate, 'iw')                                                                  \n");
         sb.append("            AND   D.CLAIM_DATE < TRUNC(next_day(sysdate,2)-1)                                                           \n");
         sb.append("            AND   D.CLAIM_GB  IN ('30','31')                                                                            \n");
         sb.append("               ) AA                                                                                                     \n");
         sb.append("   GROUP BY AA.GATHER_DATE                                                                                              \n");

         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
         }

         return sb.toString();
     }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : 일자별 매출추이 select
    * </PRE>
    * @param   poolName                           : Database pool name
    * @param   date_fr                            : 마감일자 from
    * @param   date_to                            : 마감일자 to
    * @param   media_code                         : 매체코드
    * @param   lgroup                             : 대분류
    * @param   media_gb                           : 광고구분
    * @param   order_media                        : 주문매체
    */

    public String makeSqlGraph(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();


        sb.append("    SELECT SUM(ORDER_QTY)  AS ORDER_QTY,                                                             \n");
        sb.append("               SUM(ORDER_AMT) AS ORDER_AMT,                                                          \n");
        sb.append("               SUM(CANCEL_QTY) AS CANCEL_QTY,                                                        \n");
        sb.append("               SUM(CANCEL_AMT) AS CANCEL_AMT,                                                        \n");
        sb.append("               SUM(CLAIM_QTY)  AS CLAIM_QTY,                                                         \n");
        sb.append("               SUM(CLAIM_AMT) AS CLAIM_AMT,                                                          \n");
        sb.append("               DAY_DD                                                                                \n");
        sb.append("      FROM (                                                                                         \n");
        sb.append("        SELECT SUM(A.ORDER_QTY)  AS ORDER_QTY,    					                                \n");
        sb.append("               SUM(A.ORDER_AMT) AS ORDER_AMT,                                                        \n");
        sb.append("               SUM(A.CANCEL_QTY) AS CANCEL_QTY,                                                      \n");
        sb.append("               SUM(A.CANCEL_AMT) AS CANCEL_AMT,                                                      \n");
        sb.append("               SUM(A.CLAIM_QTY - A.CLAIM_CAN_QTY)  AS CLAIM_QTY,                                     \n");
        sb.append("               SUM(CLAIM_AMT - CLAIM_CAN_AMT) AS CLAIM_AMT,                                          \n");
        sb.append("               TO_CHAR(A.GATHER_DATE,'YYYY/MM/DD') AS DAY_DD    	                                    \n");
        sb.append("          FROM VSDORDERGOODS A  									                                    \n");
        sb.append("         WHERE A.GATHER_DATE >= TO_DATE(?, 'yyyy/mm/dd') -7   		                        		\n");
        sb.append("           AND A.GATHER_DATE <  TO_DATE(?,'yyyy/mm/dd') + 1   		                        		\n");
        sb.append("      GROUP BY TO_CHAR(A.GATHER_DATE,'YYYY/MM/DD')    				                                \n");
        sb.append("      UNION                                                                                          \n");
        sb.append("        SELECT 0,0,0,0,0,0,                                                                          \n");
        sb.append("               TO_CHAR(TO_DATE(?, 'YYYY/MM/DD') + ROWNUM -7, 'YYYY/MM/DD') AS DAY_DD      			\n");
        sb.append("          FROM TCODE                                                                                 \n");
        sb.append("         WHERE ROWNUM <= 7                                                                           \n");
        sb.append("         )                                                                                           \n");
        sb.append("         GROUP BY DAY_DD                                                                             \n");
        sb.append("         ORDER BY DAY_DD          		                                                            \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("sale_flag		  : " + retrieve.getString("from_date"));
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

    /**
    * <PRE>
    * Desc :  AnalysisSellingTotal ; Day General Report
    * </PRE>
    * @param   poolName                            : Database pool name
    * @param   fromDate                            : date
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve (Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));

            rset  = pstmt.executeQuery();
            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[EmotionMainSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            return retrieve;
        }catch(Exception e){
            log.error("[EmotionMainSvc.retrieve() Exception : ERR-"+e.getMessage());
            return retrieve;
        }finally {
            DBUtils.freeConnection(null, null, pstmt, null, null, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc :  AnalysisSellingTotal ; week General Report
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
     public RetrieveModel retrieveWeeklySale (Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;

         try {
             pstmt = con.prepareStatement(makeSqlWeeklySale(retrieve));

             rset  = pstmt.executeQuery();
             retrieve.put("RESULT_WEEK", makeSheet(rset));

         }catch(SQLException se){
             log.error("[EmotionMainSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             return retrieve;
         }catch(Exception e){
             log.error("[EmotionMainSvc.retrieve() Exception : ERR-"+e.getMessage());
             return retrieve;
         }finally {
             DBUtils.freeConnection(null, null, pstmt, null, null, rset);
         }
         return retrieve;
     }

    /**
     * <PRE>
     * Desc :  AnalysisSellingTotal ; Day General Report
     * </PRE>
     * @param   poolName                            : Database pool name
     * @param   fromDate                            : date
     * @return  RetrieveModel
     */
     public RetrieveModel retrieveGraph (Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;

         try {
             pstmt = con.prepareStatement(makeSqlGraph(retrieve));

             int index = 1;
             pstmt.setString(index++, retrieve.getString("fromDate"));
             pstmt.setString(index++, retrieve.getString("fromDate"));
             pstmt.setString(index++, retrieve.getString("fromDate"));

             rset  = pstmt.executeQuery();
             retrieve.put("RESULT", makeSheet(rset));

         }catch(SQLException se){
             log.error("[EmotionMainSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             return retrieve;
         }catch(Exception e){
             log.error("[EmotionMainSvc.retrieve() Exception : ERR-"+e.getMessage());
             return retrieve;
         }finally {
             DBUtils.freeConnection(null, null, pstmt, null, null, rset);
         }
         return retrieve;
     }

    /**
	* <PRE>
	* Desc : Make Sql Customer Report
	* </PRE>
	* @param   RetrieveModel
	* @return  String : SQL
	*/

    public String makeSqlCustomer(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("    SELECT SUM(AA.TELEPHONE) AS TELEPHONE_NEW_CUST,                     \n");
        sb.append("           SUM(AA.WEB) AS WEB_NEW_CUST,                                 \n");
        sb.append("           SUM(AA.WITHDRAWAL) AS WITHDRAWAL,                            \n");
        sb.append("           SUM(AA.TELEPHONE)+ SUM(AA.WEB) AS TOTAL_NEW_CUST             \n");
        sb.append("      FROM (                                                            \n");
        sb.append("            SELECT SUM(DECODE(INSERT_ID, 'WEB', 0, 1)) AS TELEPHONE,    \n");
        sb.append("                   SUM(DECODE(INSERT_ID, 'WEB', 1, 0)) AS WEB,          \n");
        sb.append("                   0 AS WITHDRAWAL                                      \n");
        sb.append("              FROM TCUSTOMER                                            \n");
        sb.append("             WHERE INSERT_DATE >= TRUNC(sysdate)                        \n");
        sb.append("               AND INSERT_DATE <  TRUNC(sysdate) + 1                    \n");
        sb.append("                                                                        \n");
        sb.append("            UNION ALL                                                   \n");
        sb.append("                                                                        \n");
        sb.append("            SELECT 0 AS TELEPHONE,                                      \n");
        sb.append("                   0 AS WEB,                                            \n");
        sb.append("                   COUNT(D.CUST_NO) AS WITHDRAWAL                       \n");
        sb.append("              FROM TCUSTOMER D                                          \n");
        sb.append("             WHERE D.WITHDRAWAL_YN = '1'                                \n");
        sb.append("               AND D.WITHDRAWAL_DATE >= TRUNC(sysdate)                  \n");
        sb.append("               AND D.WITHDRAWAL_DATE < TRUNC(sysdate)  + 1              \n");
        sb.append("            ) AA                                                        \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
        }

         return sb.toString();
     }

    /**
     * <PRE>
     * Desc :   Customer Report
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
     public RetrieveModel retrieveCustomer (Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;

         try {
             pstmt = con.prepareStatement(makeSqlCustomer(retrieve));

             rset  = pstmt.executeQuery();
             retrieve.put("RESULT_CUSTOMER", makeSheet(rset));

         }catch(SQLException se){
             log.error("[AnalysisSellingTotalSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             return retrieve;
         }catch(Exception e){
             log.error("[AnalysisSellingTotalSvc.retrieve() Exception : ERR-"+e.getMessage());
             return retrieve;
         }finally {
             DBUtils.freeConnection(null, null, pstmt, null, null, rset);
         }
         return retrieve;
     }

     /**
      * <PRE>
      * Desc : Make Sql Goods Report
      * </PRE>
      * @param   RetrieveModel
      * @return  String : SQL
      */

      public String makeSqlGoods(RetrieveModel retrieve) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("    SELECT SUM(AA.NEW_GOODS) 			AS NEW_GOODS,                                     \n");
          sb.append("           SUM(AA.SALE_GOODS) 			AS SALE_GOODS,                                    \n");
          sb.append("           SUM(AA.NO_SALE_GOODS) 		AS NO_SALE_GOODS,                               \n");
          sb.append("           SUM(AA.SING_GOODS) 		AS SING_GOODS                                       \n");
          sb.append("      FROM (                                                                         \n");
          sb.append("            /*상품등록*/                                                             \n");
          sb.append("            SELECT COUNT(A.GOODS_CODE) AS NEW_GOODS,                                 \n");
          sb.append("                   0 AS SALE_GOODS,                                                  \n");
          sb.append("                   0 AS NO_SALE_GOODS,                                               \n");
          sb.append("                   0 AS SING_GOODS                                                   \n");
          sb.append("              FROM TGOODS A                                                          \n");
          sb.append("             WHERE A.GIFT_YN = '0'                                                   \n");
          sb.append("               AND A.INSERT_DATE >= TRUNC(sysdate)            			                  \n");
          sb.append("               AND A.INSERT_DATE < TRUNC(sysdate) + 1        	    			            \n");
          sb.append("            UNION ALL                                                                \n");
          sb.append("            /*판매중*/                                                               \n");
          sb.append("            SELECT 0 AS NEW_GOODS,                                                   \n");
          sb.append("                   COUNT(B.GOODS_CODE) AS SALE_GOODS,                                \n");
          sb.append("                   0 AS NO_SALE_GOODS,                                               \n");
          sb.append("                   0 AS SING_GOODS                                                   \n");
          sb.append("              FROM TGOODS B                                                          \n");
          sb.append("             WHERE B.SALE_GB = '00'                                                  \n");
          sb.append("               AND B.SIGN_GB = '80'                                                  \n");
          sb.append("               AND B.GIFT_YN = '0'                                                   \n");
          sb.append("            UNION ALL                                                                \n");
          sb.append("            /*판매중단*/                                                             \n");
          sb.append("            SELECT 0 AS NEW_GOODS,                                                   \n");
          sb.append("                   0 AS SALE_GOODS,                                                  \n");
          sb.append("                   COUNT(distinct(C.GOODS_CODE)) AS NO_SALE_GOODS,                   \n");
          sb.append("                   0 AS SING_GOODS                                                   \n");
          sb.append("              FROM TSALENOGOODS C,                                                   \n");
          sb.append("                   TGOODS B                                                          \n");
          sb.append("             WHERE C.GOODS_CODE = B.GOODS_CODE                                       \n");
          sb.append("               AND B.SALE_GB != '00'                                                 \n");
          sb.append("               AND B.GIFT_YN = '0'                                                   \n");
          sb.append("               AND C.SALE_GB != '00'                                                 \n");
          sb.append("               AND C.INSERT_DATE >= TRUNC(sysdate)            			                  \n");
          sb.append("               AND C.INSERT_DATE < TRUNC(sysdate) + 1 		                            \n");
          sb.append("            UNION ALL                                                                \n");
          sb.append("            /*결제처리*/                                                             \n");
          sb.append("            SELECT 0 AS NEW_GOODS,                                                   \n");
          sb.append("                   0 AS SALE_GOODS,                                                  \n");
          sb.append("                   0 AS NO_SALE_GOODS,                                               \n");
          sb.append("                   COUNT(DISTINCT(A.GOODS_CODE)) AS SING_GOODS                       \n");
          sb.append("              FROM TGOODSSIGN A,                                                      \n");
          sb.append("                   TGOODS B                                                          \n");
          sb.append("             WHERE A.SIGN_GB = '80'                                                  \n");
          sb.append("             	AND A.GOODS_CODE = B.GOODS_CODE                                       \n");
          sb.append("               AND B.GIFT_YN = '0'                                                   \n");
          sb.append("               AND A.INSERT_DATE >= TRUNC(sysdate)            			                  \n");
          sb.append("               AND A.INSERT_DATE < TRUNC(sysdate) + 1        ) AA                    \n");


          //= log SQL -------------------------------------------------
          if (log.isDebugEnabled()) {
              log.debug("\n" + sb.toString());
              log.debug("fromDate    : " + retrieve.getString("fromDate"));
          }

          return sb.toString();
      }

     /**
      * <PRE>
      * Desc :  Goods Report
      * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
      */
      public RetrieveModel retrieveGoods (Connection con, RetrieveModel retrieve) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;

          try {
              pstmt = con.prepareStatement(makeSqlGoods(retrieve));
              rset  = pstmt.executeQuery();
              retrieve.put("RESULT_GOODS", makeSheet(rset));

          }catch(SQLException se){
              log.error("[AnalysisSellingTotalSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
              return retrieve;
          }catch(Exception e){
              log.error("[AnalysisSellingTotalSvc.retrieve() Exception : ERR-"+e.getMessage());
              return retrieve;
          }finally {
              DBUtils.freeConnection(null, null, pstmt, null, null, rset);
          }
          return retrieve;
      }

      /**
       * <PRE>
       * Desc : Make Sql AnalysisSellingTotal ; Day General Report
       * </PRE>
       * @param   RetrieveModel
       * @return  String : SQL
       */

       public String makeSqlPromo(RetrieveModel retrieve) throws StoreException{

           StringBuffer sb = new StringBuffer();

           sb.append("    SELECT SUM(AA.NEW_PROMO) AS NEW_PROMO,                                                                                   \n");
           sb.append("           SUM(AA.PROC_PROMO) AS PROC_PROMO,                                                                                 \n");
           sb.append("           SUM(AA.END_PLAN_PROMO) AS END_PLAN_PROMO                                                                          \n");
           sb.append("      FROM (                                                                                                                 \n");
           sb.append("              SELECT COUNT(A.PROMO_NO) AS NEW_PROMO,                                                                         \n");
           sb.append("                     0 AS PROC_PROMO,                                                                                        \n");
           sb.append("                     0 AS END_PLAN_PROMO                                                                                     \n");
           sb.append("                FROM TPROMOM A                                                                                               \n");
           sb.append("               WHERE A.USE_CODE = '00'                                                                                       \n");
           sb.append("                 AND A.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                                               \n");
           sb.append("                 AND A.INSERT_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1                                                            \n");
           sb.append("                                                                                                                             \n");
           sb.append("              UNION ALL                                                                                                      \n");
           sb.append("                                                                                                                             \n");
           sb.append("              SELECT 0 AS NEW_PROMO,                                                                                         \n");
           sb.append("                     COUNT(B.PROMO_NO) AS PROC_PROMO,                                                                        \n");
           sb.append("                     0 AS END_PLAN_PROMO                                                                                     \n");
           sb.append("                FROM TPROMOM B                                                                                               \n");
           sb.append("               WHERE B.USE_CODE = '00'                                                                                       \n");
           sb.append("                 AND ( ( B.PROMO_BDATE <= TO_DATE(? || ' 00:00:00', 'YYYY/MM/DD HH24:MI:SS')                                 \n");
           sb.append("                       AND B.PROMO_EDATE >= TO_DATE(? || ' 00:00:00', 'YYYY/MM/DD HH24:MI:SS'))                              \n");
           sb.append("                        OR ( TO_DATE(? || ' 00:00:00', 'YYYY/MM/DD HH24:MI:SS') BETWEEN B.PROMO_BDATE AND B.PROMO_EDATE))    \n");
           sb.append("                                                                                                                             \n");
           sb.append("              UNION ALL                                                                                                      \n");
           sb.append("                                                                                                                             \n");
           sb.append("              SELECT 0 AS NEW_PROMO,                                                                                         \n");
           sb.append("                     0 AS PROC_PROMO,                                                                                        \n");
           sb.append("                     COUNT(C.PROMO_NO) AS END_PLAN_PROMO                                                                     \n");
           sb.append("                FROM TPROMOM C                                                                                               \n");
           sb.append("               WHERE C.USE_CODE = '00'                                                                                       \n");
           sb.append("                 AND (C.PROMO_EDATE BETWEEN TO_DATE(? || ' 00:00:00', 'YYYY/MM/DD HH24:MI:SS')                               \n");
           sb.append("                     AND TO_DATE(? || ' 00:00:00', 'YYYY/MM/DD HH24:MI:SS') + 3)) AA                                         \n");


           //= log SQL -------------------------------------------------
           if (log.isDebugEnabled()) {
               log.debug("\n" + sb.toString());
               log.debug("fromDate    : " + retrieve.getString("fromDate"));
           }

           return sb.toString();
       }

      /**
       * <PRE>
       * Desc :  AnalysisSellingTotal ; Day General Report
       * </PRE>
       * @param   poolName                            : Database pool name
       * @param   fromDate                            : date
       * @return  RetrieveModel
       */
       public RetrieveModel retrievePromo (Connection con, RetrieveModel retrieve) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;

           try {
               pstmt = con.prepareStatement(makeSqlPromo(retrieve));

               int index = 1;
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));
               pstmt.setString(index++, retrieve.getString("fromDate"));

               rset  = pstmt.executeQuery();
               retrieve.put("RESULT_PROMO", makeSheet(rset));

           }catch(SQLException se){
               log.error("[AnalysisSellingTotalSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
               return retrieve;
           }catch(Exception e){
               log.error("[AnalysisSellingTotalSvc.retrieve() Exception : ERR-"+e.getMessage());
               return retrieve;
           }finally {
               DBUtils.freeConnection(null, null, pstmt, null, null, rset);
           }
           return retrieve;
       }

       /**
        * <PRE>
        * Desc : Make Sql AnalysisSellingTotal ; Day General Report
        * </PRE>
        * @param   RetrieveModel
        * @return  String : SQL
        */

        public String makeSqlDelivery(RetrieveModel retrieve) throws StoreException{

            StringBuffer sb = new StringBuffer();

    		sb.append("SELECT SUM(AA.SLIP_ORDER) AS SLIP_ORDER,                                    \n");
    		sb.append("       SUM(AA.DELIVERY_OUT) AS DELIVERY_OUT,                               \n");
    		sb.append("       SUM(AA.DELIVERY_END) AS DELIVERY_END,                                \n");
    		sb.append("       SUM(AA.RECOVERY_OK) AS RECOVERY_OK                                                                  \n");
    		sb.append("  FROM (                                                                                                   \n");
    		sb.append("               SELECT COUNT( DISTINCT DECODE(OP.DO_FLAG,'30',M.SLIP_I_NO,'')  ) AS SLIP_ORDER,                  \n");
    		sb.append("                      COUNT( DISTINCT DECODE(OP.DO_FLAG,'40',M.SLIP_I_NO,'')  ) AS DELIVERY_OUT,                \n");
    		sb.append("                      COUNT( DISTINCT DECODE(OP.DO_FLAG,'80',M.SLIP_I_NO,'')  )  AS DELIVERY_END,               \n");
    		sb.append("                      0  AS RECOVERY_OK                                                                         \n");
    		sb.append("                 FROM TORDERPROC OP,                                                                            \n");
    		sb.append("                      ( SELECT  SM.SLIP_I_NO, SD.ORDER_NO, SD.ORDER_G_SEQ, SD.ORDER_D_SEQ, SD.ORDER_W_SEQ       \n");
    		sb.append("                        FROM   TSLIPM     SM,                                                                   \n");
    		sb.append("                               TSLIPDT    SD                                                                    \n");
    		sb.append("                        WHERE  SM.SLIP_I_NO = SD.SLIP_I_NO                                                      \n");
    		sb.append("                        AND    SM.SLIP_FLAG = '1'                                                               \n");
    		sb.append("                        AND    SM.REDELY_YN = '0'                                                               \n");
    		sb.append("                        AND    SM.SLIP_GB < '104'                                                               \n");
    		sb.append("                        AND    SD.DELY_QTY  > 0                                                                 \n");
    		sb.append("                        ) M                                                                                     \n");
    		sb.append("                WHERE OP.DO_FLAG IN ('30','40','80')                                                            \n");
    		sb.append("                  AND OP.PROC_DATE >= TRUNC(SYSDATE)                                                            \n");
    		sb.append("                  AND OP.PROC_DATE < TRUNC(SYSDATE) + 1                                                         \n");
    		sb.append("                  AND M.ORDER_NO (+)    = OP.ORDER_NO   													       \n");
    		sb.append("                  AND M.ORDER_G_SEQ(+)  = OP.ORDER_G_SEQ   													   \n");
    		sb.append("                  AND M.ORDER_D_SEQ(+)  = OP.ORDER_D_SEQ   													   \n");
    		sb.append("                  AND M.ORDER_W_SEQ(+)  = OP.ORDER_W_SEQ                                                        \n");
    		sb.append("                                                                                                                \n");
    		sb.append("               UNION ALL                                                                                        \n");
    		sb.append("                                                                                                                \n");
    		sb.append("               SELECT 0 AS SLIP_ORDER,                                                                          \n");
    		sb.append("                      0 AS DELIVERY_OUT,                                                                        \n");
    		sb.append("                      0  AS DELIVERY_END,                                                                       \n");
    		sb.append("                      COUNT( DISTINCT SLIP_I_NO  ) AS RECOVERY_OK                                               \n");
    		sb.append("                   FROM ( SELECT  SM.SLIP_I_NO, SD.ORDER_NO, SD.ORDER_G_SEQ, SD.ORDER_D_SEQ, SD.ORDER_W_SEQ     \n");
    		sb.append("                          FROM   TSLIPM     SM,                                                                 \n");
    		sb.append("                                 TSLIPDT    SD                                                                  \n");
    		sb.append("                          WHERE  SM.SLIP_I_NO = SD.SLIP_I_NO                                                    \n");
    		sb.append("                          AND    SM.SLIP_FLAG = '2'                                                             \n");
    		sb.append("                          AND    SM.REDELY_YN = '0'                                                             \n");
    		sb.append("                          AND    SD.DELY_QTY + SD.RETURN_QTY > 0                                                \n");
    		sb.append("                          ) M ,                                                                                 \n");
    		sb.append("                        TORDERPROC OP                                                                           \n");
    		sb.append("                  WHERE M.ORDER_NO(+)           = OP.ORDER_NO                                                   \n");
    		sb.append("                    AND M.ORDER_G_SEQ(+)        = OP.ORDER_G_SEQ                                                \n");
    		sb.append("                    AND M.ORDER_D_SEQ(+)        = OP.ORDER_D_SEQ                                                \n");
    		sb.append("                    AND M.ORDER_W_SEQ(+)        = OP.ORDER_W_SEQ                                                \n");
    		sb.append("                    AND OP.DO_FLAG            = '60'                                                            \n");
    		sb.append("                    AND OP.PROC_DATE          >= TRUNC(SYSDATE)                                                 \n");
    		sb.append("                    AND OP.PROC_DATE          <  TRUNC(SYSDATE)+ 1                                              \n");
    		sb.append("              ) AA                                                                                              \n");

            //= log SQL -------------------------------------------------
            if (log.isDebugEnabled()) {
                log.debug("\n" + sb.toString());
                log.debug("fromDate    : " + retrieve.getString("fromDate"));
            }

            return sb.toString();
        }

       /**
        * <PRE>
        * Desc :  AnalysisSellingTotal ; Day General Report
        * </PRE>
        * @param   poolName                            : Database pool name
        * @param   fromDate                            : date
        * @return  RetrieveModel
        */
        public RetrieveModel retrieveDelivery (Connection con, RetrieveModel retrieve) throws StoreException{
            PreparedStatement pstmt       = null;
            ResultSet         rset        = null;

            try {
                pstmt = con.prepareStatement(makeSqlDelivery(retrieve));

                rset  = pstmt.executeQuery();
                retrieve.put("RESULT_DELIVERY", makeSheet(rset));

            }catch(SQLException se){
                log.error("[AnalysisSellingTotalSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
                return retrieve;
            }catch(Exception e){
                log.error("[AnalysisSellingTotalSvc.retrieve() Exception : ERR-"+e.getMessage());
                return retrieve;
            }finally {
                DBUtils.freeConnection(null, null, pstmt, null, null, rset);
            }
            return retrieve;
        }
}