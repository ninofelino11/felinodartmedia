
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
 *  Service class
 *
 * @version 1.0, 2006/11/16
 */
public class LotteryPromoCustSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public LotteryPromoCustSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TLOTTERYPROMOM List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.LOTTERY_PROMO_NO,  \n");
        sb.append("        A.LOTTERY_PROMO_NAME,  \n");
        sb.append("        TO_CHAR(A.LOTTERY_PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS LOTTERY_PROMO_BDATE,  \n");
        sb.append("        TO_CHAR(A.LOTTERY_PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS LOTTERY_PROMO_EDATE,  \n");
        sb.append("        A.DO_TYPE,  \n");
        sb.append("        TCODE_NAME('B007', A.DO_TYPE) AS DO_TYPE_NAME,  \n");
        sb.append("        A.LIMIT_YN,  \n");
        sb.append("        A.LIMIT_QTY,  \n");
        sb.append("        A.CONFIRM_CNT,  \n");
        sb.append("        A.PROVIDE_CNT,  \n");
        sb.append("        A.ORDER_MEDIA_ALL_YN,  \n");
        sb.append("        A.ORDER_MEDIA,  \n");
        sb.append("        A.MEDIA_CODE_ALL_YN,  \n");
        sb.append("        A.MEDIA_CODE,  \n");
        sb.append("        A.USE_CODE,  \n");
        sb.append("        A.END_YN,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS END_DATE,  \n");
        sb.append("        A.PROMO_NOTE,  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        A.MODIFY_ID  \n");
        sb.append("    FROM TLOTTERYPROMOM A  \n");
        sb.append("   WHERE A.LOTTERY_PROMO_NO = ?  \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TLOTTERYPROMOM List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCgPrint( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A_CUST_NAME||'   ' AS A_CUST_NAME,  \n");
        sb.append("         A_ADDR||'        ' AS A_ADDR,  \n");
        sb.append("         B_CUST_NAME||'   ' AS B_CUST_NAME,  \n");
        sb.append("         B_ADDR             AS B_ADDR \n");
        sb.append("   FROM ( SELECT (A_ROW + 1) / 2 A_ROW, CUST_NAME A_CUST_NAME, ADDR A_ADDR  \n");
        sb.append("            FROM ( SELECT ROWNUM A_ROW, CP.RECEIVER AS CUST_NAME,  \n");
        sb.append("                          FUN_ADD_POSTADDR(CP.RECEIVER_POST,CP.RECEIVER_POST_SEQ,CP.RECEIVER_ADDR) AS ADDR  \n");
        sb.append("                     FROM TRECEIVER CP,  \n");
        sb.append("                          TLOTTERYPROMOPRIZE PR  \n");
        sb.append("                    WHERE PR.CUST_NO = CP.CUST_NO  \n");
        sb.append("                      AND CP.DEFAULT_YN = '1'  \n");
        sb.append("                      AND PR.LOTTERY_PROMO_NO = ? )  \n");
        sb.append("            WHERE ROUND((A_ROW + 1 ) / 2 ) = (A_ROW + 1) / 2 ),  \n");
        sb.append("         ( SELECT A_ROW / 2 B_ROW, CUST_NAME B_CUST_NAME, ADDR B_ADDR  \n");
        sb.append("             FROM ( SELECT ROWNUM A_ROW, CP.RECEIVER AS CUST_NAME,  \n");
        sb.append("                           FUN_ADD_POSTADDR(CP.RECEIVER_POST,CP.RECEIVER_POST_SEQ,CP.RECEIVER_ADDR) AS ADDR  \n");
        sb.append("                      FROM TRECEIVER CP,  \n");
        sb.append("                           TLOTTERYPROMOPRIZE PR  \n");
        sb.append("                     WHERE PR.CUST_NO = CP.CUST_NO  \n");
        sb.append("                       AND CP.DEFAULT_YN = '1'  \n");
        sb.append("                       AND PR.LOTTERY_PROMO_NO = ? )  \n");
        sb.append("            WHERE ROUND(A_ROW / 2 ) = A_ROW / 2 )  \n");
        sb.append("  WHERE A_ROW = B_ROW (+)  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }



     /**
      * <PRE>
      * Desc : Make SQL ; TLOTTERYPROMOPRIZE List
      * </PRE>
      * @param   RetrieveModel
      * @return  String
      */
      private String makeSqlPrize( RetrieveModel retrieve ) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("  SELECT A.RECEIVER AS CUST_NAME,  \n");
          sb.append("         A.CUST_NO   AS CUST_NO,  \n");
          sb.append("         B.MEMB_NO   AS MEMB_NO,  \n");
          sb.append("         A.TEL       AS TEL_NU,  \n");
          sb.append("         FUN_ADD_POSTADDR(A.RECEIVER_POST,A.RECEIVER_POST_SEQ,A.RECEIVER_ADDR) AS ADDR  \n");
          sb.append("    FROM TRECEIVER A,  \n");
          sb.append("         TCUSTOMER B,  \n");
          sb.append("         TLOTTERYPROMOPRIZE C  \n");
          sb.append("   WHERE A.CUST_NO           = B.CUST_NO(+)  \n");
          sb.append("     AND A.CUST_NO           = C.CUST_NO  \n");
          sb.append("     AND A.DEFAULT_YN        = '1'  \n");
          sb.append("     AND C.LOTTERY_PROMO_NO  = ?  \n");

          //= log SQL -------------------------------------------------
          if (log.isDebugEnabled()) {
              log.debug(sb.toString());
          }
          return sb.toString();
      }


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; List
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve
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
            pstmt.setString(index++,retrieve.getString("lottery_promo_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LotterPromoCustSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoCustSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

     /**
      * <PRE>
      * Desc : Retrieve
      * </PRE>
      * @param   Connection
      * @param   RetrieveModel
      * @return  RetrieveModel
      */
      public RetrieveModel retrievePrize(Connection con, RetrieveModel retrieve) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;

          try {
              pstmt = con.prepareStatement(makeSqlPrize(retrieve));

              int index = 1;
              pstmt.setString(index++,retrieve.getString("lottery_promo_no"));

              rset  = pstmt.executeQuery();

              retrieve.put("RESULT_PRIZE",makeSheet(rset));

          }catch(SQLException se){
              log.error("[LotterPromoCustSvc.retrievePrize() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              log.error("[LotterPromoCustSvc.retrievePrize() Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return retrieve;
      }

      /**
       * <PRE>
       * Desc : Retrieve
       * </PRE>
       * @param   Connection
       * @param   RetrieveModel
       * @return  RetrieveModel
       */
       public RetrieveModel retrieveCgPrint(Connection con, RetrieveModel retrieve) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;

           try {
               pstmt = con.prepareStatement(makeSqlCgPrint(retrieve));

               int index = 1;
               pstmt.setString(index++,retrieve.getString("lottery_promo_no"));
               pstmt.setString(index++,retrieve.getString("lottery_promo_no"));

               rset  = pstmt.executeQuery();

               retrieve.put("RESULT_CGPRINT",makeSheet(rset));

           }catch(SQLException se){
               log.error("[LotterPromoCustSvc.retrieveCgPrint() SQLException : ERR-"+se.getErrorCode()+":"+se);
               throw new StoreException(se.getMessage());
           }catch(Exception e){
               log.error("[LotterPromoCustSvc.retrieveCgPrint() Exception : ERR-"+e.getMessage());
               throw new StoreException(e.getMessage());
           }finally {
               DBUtils.freeConnection(null, pstmt, rset);
           }
           return retrieve;
       }

}
