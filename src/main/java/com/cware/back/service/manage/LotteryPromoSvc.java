
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
import com.cware.back.entity.table.Tlotterypromocust;
import com.cware.back.entity.table.Tlotterypromom;
import com.cware.back.entity.table.Tpromom;

/**
 * Register promotion Service class
 *
 * @version 1.0, 2006/09/25
 */
public class LotteryPromoSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public LotteryPromoSvc() {}


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
        sb.append("        A.LIMIT_YN,  \n");
        sb.append("        A.LIMIT_QTY,  \n");
        sb.append("        A.CONFIRM_CNT,  \n");
        sb.append("        A.PROVIDE_CNT,  \n");
        sb.append("        A.ORDER_MEDIA_ALL_YN,  \n");
        sb.append("        A.ORDER_MEDIA,  \n");
        sb.append("        A.VALID_DAYS,  \n");
        sb.append("        A.COUPON_PROMO_NO,  \n");
        sb.append("        C.PROMO_NO AS COUPON_PROMO_NAME,  \n");
        sb.append("        A.GOODS_CODE AS GIFT_GOODS_CODE,  \n");
        sb.append("        D.GOODS_NAME,  \n");
        sb.append("        A.GOODSDT_CODE,  \n");
        sb.append("        D.GOODSDT_INFO,  \n");
        sb.append("        A.OWN_COST,  \n");
        sb.append("        A.ENTP_COST,  \n");
        sb.append("        A.ENTP_CODE,  \n");
        sb.append("        E.ENTP_NAME,  \n");
        sb.append("        A.TAX_RCV_FLAG,  \n");
        sb.append("        A.DO_AMT,  \n");
        sb.append("        FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME, \n");
        sb.append("        A.MEDIA_CODE_ALL_YN,  \n");
        sb.append("        A.MEDIA_CODE,  \n");
        sb.append("        FUN_GET_MEDIA_NAME(A.MEDIA_CODE) AS MEDIA_NAME,  \n");
        sb.append("        A.USE_CODE,  \n");
        sb.append("        A.END_YN,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,  \n");
        sb.append("        A.PROMO_NOTE,  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        A.MODIFY_ID,  \n");
        sb.append("        A.PARENT_LOTTERY_PROMO_NO,  \n");
        sb.append("        B.LOTTERY_PROMO_NAME AS PARENT_LOTTERY_PROMO_NAME \n");
        sb.append("    FROM TLOTTERYPROMOM A,  \n");
        sb.append("    		TLOTTERYPROMOM B,   \n");
        sb.append("    		TPROMOM C,   \n");
        sb.append("    		TGOODSDT D,   \n");
        sb.append("    		TENTERPRISE E,   \n");
        sb.append("    		TGOODSPRICE F   \n");
        sb.append("   WHERE A.COUPON_PROMO_NO = C.PROMO_NO(+)  \n");
        sb.append("     AND A.GOODS_CODE = D.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.ENTP_CODE = E.ENTP_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE = F.GOODS_CODE(+)  \n");
        sb.append("     AND A.LOTTERY_PROMO_NO LIKE ?||'%'  \n");
        sb.append("     AND ( ( A.LOTTERY_PROMO_BDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n");
        sb.append("                                       AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n");
        sb.append("          OR A.LOTTERY_PROMO_EDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n");
        sb.append("                                       AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')) \n");
        sb.append("      OR (   TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  BETWEEN A.LOTTERY_PROMO_BDATE AND A.LOTTERY_PROMO_EDATE      \n");
        sb.append("          OR TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  BETWEEN A.LOTTERY_PROMO_BDATE AND A.LOTTERY_PROMO_EDATE ) )  \n");
        sb.append("     AND A.USE_CODE LIKE ?||'%'  \n ");
        sb.append("     AND A.DO_TYPE LIKE ?||'%'  \n ");
        sb.append("     AND A.PARENT_LOTTERY_PROMO_NO = B.LOTTERY_PROMO_NO(+)  \n ");

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
    private String makeSqlSub( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.LOTTERY_PROMO_NO,  \n");
        sb.append("        A.LOTTERY_PROMO_NAME,  \n");
        sb.append("        TO_CHAR(A.LOTTERY_PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS LOTTERY_PROMO_BDATE,  \n");
        sb.append("        TO_CHAR(A.LOTTERY_PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS LOTTERY_PROMO_EDATE,  \n");
        sb.append("        A.DO_TYPE,  \n");
        sb.append("        A.LIMIT_YN,  \n");
        sb.append("        A.LIMIT_QTY,  \n");
        sb.append("        A.CONFIRM_CNT,  \n");
        sb.append("        A.PROVIDE_CNT,  \n");
        sb.append("        A.ORDER_MEDIA_ALL_YN,  \n");
        sb.append("        A.ORDER_MEDIA,  \n");
        sb.append("        FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME,  \n");
        sb.append("        A.MEDIA_CODE_ALL_YN,  \n");
        sb.append("        A.MEDIA_CODE,  \n");
        sb.append("        FUN_GET_MEDIA_NAME(A.MEDIA_CODE) AS MEDIA_NAME,  \n");
        sb.append("        A.USE_CODE,  \n");
        sb.append("        A.VALID_DAYS,  \n");
        sb.append("        A.COUPON_PROMO_NO,  \n");
        sb.append("        C.PROMO_NO AS COUPON_PROMO_NAME,  \n");
        sb.append("        A.GOODS_CODE AS GIFT_GOODS_CODE,  \n");
        sb.append("        D.GOODS_NAME,  \n");
        sb.append("        A.GOODSDT_CODE,  \n");
        sb.append("        D.GOODSDT_INFO,  \n");
        sb.append("        A.OWN_COST,  \n");
        sb.append("        A.ENTP_COST,  \n");
        sb.append("        A.ENTP_CODE,  \n");
        sb.append("        E.ENTP_NAME,  \n");
        sb.append("        A.TAX_RCV_FLAG,  \n");
        sb.append("        A.DO_AMT,  \n");
        sb.append("        A.END_YN,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,  \n");
        sb.append("        A.PROMO_NOTE,  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        A.MODIFY_ID,  \n");
        sb.append("        A.PARENT_LOTTERY_PROMO_NO,  \n");
        sb.append("        B.LOTTERY_PROMO_NAME AS PARENT_LOTTERY_PROMO_NAME \n");
        sb.append("    FROM TLOTTERYPROMOM A,  \n");
        sb.append("    		TLOTTERYPROMOM B,   \n");
        sb.append("    		TPROMOM C,   \n");
        sb.append("    		TGOODSDT D,   \n");
        sb.append("    		TENTERPRISE E,   \n");
        sb.append("    		TGOODSPRICE F   \n");
        sb.append("   WHERE A.COUPON_PROMO_NO = C.PROMO_NO(+)  \n");
        sb.append("     AND A.GOODS_CODE = D.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.ENTP_CODE = E.ENTP_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE = F.GOODS_CODE(+)  \n");
        sb.append("     AND A.LOTTERY_PROMO_NO LIKE ?||'%'  \n");
        sb.append("     AND A.PARENT_LOTTERY_PROMO_NO = B.LOTTERY_PROMO_NO(+)  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ;  Select, check TLOTTERYPROMOCUST
     * </PRE>
     * @param   tlottetypromocust
     * @return  String
     */
     private String makeSqlLotterypromocust(Tlotterypromocust tlottetypromocust) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT COUNT(A.LOTTERY_PROMO_NO) 			\n");
         sb.append("  FROM TLOTTERYPROMOCUST A        			\n");
         sb.append(" WHERE A.LOTTERY_PROMO_NO = ? 		    	\n");
         sb.append("   AND ROWNUM = 1  							\n");

         StringBuffer logString = new StringBuffer();
         logString.append( tlottetypromocust.getLottery_promo_no()); logString.append( "/" );

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ;  Select, check Tpromom
      * </PRE>
      * @param   tlottetypromocust
      * @return  String
      */
      private String makeSqlPromo(Tpromom tpromom) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("SELECT COUNT(A.LOTTERY_PROMO_NO) 	\n");
          sb.append("  FROM TPROMOM A        			\n");
          sb.append(" WHERE A.LOTTERY_PROMO_NO = ? 		\n");
          sb.append("   AND ROWNUM = 1  				\n");

          StringBuffer logString = new StringBuffer();
          logString.append( tpromom.getLottery_promo_no()); logString.append( "/" );

          //= log SQL -------------------------------------------------
          if (logSave.isDebugEnabled()) {
              logSave.debug(sb.toString());
          }

          return sb.toString();
      }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TLOTTERYPROMOM )
    * </PRE>
    * @param   Tlotterypromom
    * @return  String
    */

    private String makeSqlInsert(Tlotterypromom tlotterypromom) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TLOTTERYPROMOM (  \n");
        sb.append("        LOTTERY_PROMO_NO,  \n");
        sb.append("        LOTTERY_PROMO_NAME,  \n");
        sb.append("        LOTTERY_PROMO_BDATE,  \n");
        sb.append("        LOTTERY_PROMO_EDATE,  \n");
        sb.append("        DO_TYPE,  \n");
        sb.append("        LIMIT_YN,  \n");
        sb.append("        LIMIT_QTY,  \n");
        sb.append("        CONFIRM_CNT,  \n");
        sb.append("        PROVIDE_CNT,  \n");
        sb.append("        ORDER_MEDIA_ALL_YN,  \n");
        sb.append("        ORDER_MEDIA,  \n");
        sb.append("        MEDIA_CODE_ALL_YN,  \n");
        sb.append("        MEDIA_CODE,  \n");
        sb.append("        USE_CODE,  \n");
        sb.append("        END_YN,  \n");
        sb.append("        END_DATE,  \n");
        sb.append("        PROMO_NOTE,  \n");
        sb.append("        VALID_DAYS,  \n");
        sb.append("        COUPON_PROMO_NO,  \n");
        sb.append("        GOODS_CODE,  \n");
        sb.append("        GOODSDT_CODE,  \n");
        sb.append("        OWN_COST,  \n");
        sb.append("        ENTP_COST,  \n");
        sb.append("        ENTP_CODE,  \n");
        sb.append("        TAX_RCV_FLAG,  \n");
        sb.append("        DO_AMT,  \n");
        sb.append("        APPLY_YN,  \n");
        sb.append("        INSERT_DATE,  \n");
        sb.append("        INSERT_ID,  \n");
        sb.append("        MODIFY_DATE,  \n");
        sb.append("        MODIFY_ID,	\n");
        sb.append("        PARENT_LOTTERY_PROMO_NO) \n");
        sb.append(" VALUES ( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD'), \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         '0', \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TLOTTERYPROMOM )
    * </PRE>
    * @param   Tlotterypromom
    * @return  String
    */

    public String makeSqlUpdate(Tlotterypromom tlotterypromom) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TLOTTERYPROMOM SET    \n ");
        sb.append("         LOTTERY_PROMO_NAME  = ?, \n ");
        sb.append("         LOTTERY_PROMO_BDATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         LOTTERY_PROMO_EDATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         DO_TYPE             = ?, \n ");
        sb.append("         LIMIT_YN            = ?, \n ");
        sb.append("         LIMIT_QTY           = ?, \n ");
        sb.append("         PROVIDE_CNT         = ?, \n ");
        sb.append("         ORDER_MEDIA_ALL_YN  = ?, \n ");
        sb.append("         ORDER_MEDIA         = ?, \n ");
        sb.append("         MEDIA_CODE_ALL_YN   = ?, \n ");
        sb.append("         MEDIA_CODE          = ?, \n ");
        sb.append("         USE_CODE            = ?, \n ");
        sb.append("         PROMO_NOTE          = ?, \n ");
        sb.append("         VALID_DAYS          = ?,  \n");
        sb.append("         COUPON_PROMO_NO     = ?,  \n");
        sb.append("         GOODS_CODE     = ?,  \n");
        sb.append("         GOODSDT_CODE        = ?,  \n");
        sb.append("         OWN_COST         	= ?,  \n");
        sb.append("         ENTP_COST           = ?,  \n");
        sb.append("         ENTP_CODE           = ?,  \n");
        sb.append("         TAX_RCV_FLAG        = ?,  \n");
        sb.append("         DO_AMT        		= ?,  \n");
        sb.append("         MODIFY_DATE         = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID           = ?, \n ");
        sb.append("         PARENT_LOTTERY_PROMO_NO    = ?  \n ");
        sb.append("   WHERE LOTTERY_PROMO_NO    = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Update TPROMOM )
     * </PRE>
     * @param   Tpromom
     * @return  String
     */

     public String makeSqlUpdate(Tpromom tpromom) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("  UPDATE TPROMOM SET    \n ");
         sb.append("         USE_CODE            = ?, \n ");
         sb.append("         MODIFY_DATE         = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
         sb.append("         MODIFY_ID           = ?  \n ");
         sb.append("   WHERE LOTTERY_PROMO_NO    = ?  \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
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
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("use_code"));
            pstmt.setString(index++,retrieve.getString("h_do_type"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LotterPromoSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : RetrieveSub
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveSub(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSub(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("lottery_promo_no"));
//            pstmt.setString(index++,retrieve.getString("begin"));
//            pstmt.setString(index++,retrieve.getString("end"));
//            pstmt.setString(index++,retrieve.getString("begin"));
//            pstmt.setString(index++,retrieve.getString("end"));
//            pstmt.setString(index++,retrieve.getString("begin"));
//            pstmt.setString(index++,retrieve.getString("end"));
//            pstmt.setString(index++,retrieve.getString("use_code"));
//            pstmt.setString(index++,retrieve.getString("h_do_type"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_MASTER",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LotterPromoSvc.retrieveSub() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoSvc.retrieveSub() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc :  select existed lotterypromocust
     * </PRE>
     * @param   Connection
     * @param   Tcustomer
     * @return  int
     */
     public int isExistlottery(Connection con, Tlotterypromocust tlotterypromocust) throws StoreException{
         PreparedStatement pstmt = null;
         ResultSet         rset  = null;
         int               existedLotteypromocust = 0;

         try {
             pstmt = con.prepareStatement(makeSqlLotterypromocust(tlotterypromocust));
             int index = 1;
             pstmt.setString(index++, tlotterypromocust.getLottery_promo_no());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tlotterypromocust.getLottery_promo_no() ); logString.append( "/" );

             logSave.info(logString.toString());

             rset  = pstmt.executeQuery();

             if (rset!=null && rset.next()) existedLotteypromocust = rset.getInt(1);


         }catch(SQLException se){
             logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return existedLotteypromocust;
     }
     /**
      * <PRE>
      * Desc :  select existed Lotteypromo
      * </PRE>
      * @param   Connection
      * @param   Tpromom
      * @return  int
      */

      public int isExistPromo(Connection con, Tpromom tpromom) throws StoreException{
          PreparedStatement pstmt = null;
          ResultSet         rset  = null;
          int               existedLotteypromo = 0;

          try {
              pstmt = con.prepareStatement(makeSqlPromo(tpromom));
              int index = 1;
              pstmt.setString(index++, tpromom.getLottery_promo_no());

              //= log Save Data ---------------------
              StringBuffer logString = new StringBuffer();
              logString.append( tpromom.getLottery_promo_no() ); logString.append( "/" );

              logSave.info(logString.toString());

              rset  = pstmt.executeQuery();

              if (rset!=null && rset.next()) existedLotteypromo = rset.getInt(1);


          }catch(SQLException se){
              logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              logSave.error("Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return existedLotteypromo;
      }
    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TLOTTERYPROMOM
    * </PRE>
    * @param   Connection
    * @param   Tpromom
    * @return  int
    */
    public int insert(Connection con, Tlotterypromom tlotterypromom) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tlotterypromom));
            int index = 1;

            pstmt.setString(index++,tlotterypromom.getLottery_promo_no());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_name());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_bdate());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_edate());
            pstmt.setString(index++,tlotterypromom.getDo_type());
            pstmt.setString(index++,tlotterypromom.getLimit_yn());
            pstmt.setString(index++,tlotterypromom.getLimit_qty());
            pstmt.setString(index++,tlotterypromom.getConfirm_cnt());
            pstmt.setString(index++,tlotterypromom.getProvide_cnt());
            pstmt.setString(index++,tlotterypromom.getOrder_media_all_yn());

            pstmt.setString(index++,tlotterypromom.getOrder_media());
            pstmt.setString(index++,tlotterypromom.getMedia_code_all_yn());
            pstmt.setString(index++,tlotterypromom.getMedia_code());
            pstmt.setString(index++,tlotterypromom.getUse_code());
            pstmt.setString(index++,tlotterypromom.getEnd_yn());
            pstmt.setString(index++,tlotterypromom.getEnd_date());
            pstmt.setString(index++,tlotterypromom.getPromo_note());
            
            pstmt.setString(index++,tlotterypromom.getValid_days());
            pstmt.setString(index++,tlotterypromom.getCoupon_promo_no());
            pstmt.setString(index++,tlotterypromom.getGoods_code());
            pstmt.setString(index++,tlotterypromom.getGoodsdt_code());
            pstmt.setString(index++,tlotterypromom.getOwn_cost());
            pstmt.setString(index++,tlotterypromom.getEntp_cost());
            pstmt.setString(index++,tlotterypromom.getEntp_code());
            pstmt.setString(index++,tlotterypromom.getTax_rcv_flag());
            pstmt.setString(index++,tlotterypromom.getDo_amt());
            pstmt.setString(index++,tlotterypromom.getInsert_date());
            pstmt.setString(index++,tlotterypromom.getInsert_id());
            pstmt.setString(index++,tlotterypromom.getModify_date());
            pstmt.setString(index++,tlotterypromom.getModify_id());
            pstmt.setString(index++,tlotterypromom.getParent_lottery_promo_no());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tlotterypromom.getLottery_promo_no()            ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_name()          ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_bdate()         ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_edate()         ); logString.append( "/" );
            logString.append( tlotterypromom.getDo_type()                     ); logString.append( "/" );
            logString.append( tlotterypromom.getConfirm_cnt()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getProvide_cnt()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getOrder_media_all_yn()          ); logString.append( "/" );
            logString.append( tlotterypromom.getOrder_media()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getMedia_code_all_yn()           ); logString.append( "/" );
            logString.append( tlotterypromom.getMedia_code()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getUse_code()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getEnd_yn()                      ); logString.append( "/" );
            logString.append( tlotterypromom.getEnd_date()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getPromo_note()                  ); logString.append( "/" );

            logString.append( tlotterypromom.getValid_days()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getCoupon_promo_no()             ); logString.append( "/" );
            logString.append( tlotterypromom.getGoods_code()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getGoodsdt_code()                ); logString.append( "/" );
            logString.append( tlotterypromom.getOwn_cost()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getEntp_cost()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getEntp_code()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getTax_rcv_flag()                ); logString.append( "/" );
            logString.append( tlotterypromom.getDo_amt()                	  ); logString.append( "/" );
            logString.append( tlotterypromom.getInsert_date()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getInsert_id()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_date()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_id()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getParent_lottery_promo_no()     ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotterPromoSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotterPromoSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tlotterypromom
    * </PRE>
    * @param   Connection
    * @param   Tlotterypromom
    * @return  int
    */

    public int update(Connection con, Tlotterypromom tlotterypromom) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(tlotterypromom));
            int index = 1;
            pstmt.setString(index++,tlotterypromom.getLottery_promo_name());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_bdate());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_edate());
            pstmt.setString(index++,tlotterypromom.getDo_type());
            pstmt.setString(index++,tlotterypromom.getLimit_yn());
            pstmt.setString(index++,tlotterypromom.getLimit_qty());
            pstmt.setString(index++,tlotterypromom.getProvide_cnt());
            pstmt.setString(index++,tlotterypromom.getOrder_media_all_yn());
            pstmt.setString(index++,tlotterypromom.getOrder_media());
            pstmt.setString(index++,tlotterypromom.getMedia_code_all_yn());
            pstmt.setString(index++,tlotterypromom.getMedia_code());
            pstmt.setString(index++,tlotterypromom.getUse_code());
            pstmt.setString(index++,tlotterypromom.getPromo_note());
            pstmt.setString(index++,tlotterypromom.getValid_days());
            pstmt.setString(index++,tlotterypromom.getCoupon_promo_no());
            pstmt.setString(index++,tlotterypromom.getGoods_code());
            pstmt.setString(index++,tlotterypromom.getGoodsdt_code());
            pstmt.setString(index++,tlotterypromom.getOwn_cost());
            pstmt.setString(index++,tlotterypromom.getEntp_cost());
            pstmt.setString(index++,tlotterypromom.getEntp_code());
            pstmt.setString(index++,tlotterypromom.getTax_rcv_flag());
            pstmt.setString(index++,tlotterypromom.getDo_amt());
            pstmt.setString(index++,tlotterypromom.getModify_date());
            pstmt.setString(index++,tlotterypromom.getModify_id());
            pstmt.setString(index++,tlotterypromom.getParent_lottery_promo_no());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_no());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tlotterypromom.getLottery_promo_name()          ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_bdate()         ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_edate()         ); logString.append( "/" );
            logString.append( tlotterypromom.getDo_type()                     ); logString.append( "/" );
            logString.append( tlotterypromom.getLimit_yn()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getLimit_qty()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getProvide_cnt()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getOrder_media_all_yn()          ); logString.append( "/" );
            logString.append( tlotterypromom.getOrder_media()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getMedia_code_all_yn()           ); logString.append( "/" );
            logString.append( tlotterypromom.getMedia_code()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getUse_code()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getPromo_note()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getValid_days()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getCoupon_promo_no()             ); logString.append( "/" );
            logString.append( tlotterypromom.getGoods_code()                  ); logString.append( "/" );
            logString.append( tlotterypromom.getGoodsdt_code()                ); logString.append( "/" );
            logString.append( tlotterypromom.getOwn_cost()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getEntp_cost()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getEntp_code()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getTax_rcv_flag()                ); logString.append( "/" );
            logString.append( tlotterypromom.getDo_amt()                	  ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_date()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_id()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getParent_lottery_promo_no()     ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_no()            ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotterPromoSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotterPromoSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
     * <PRE>
     * Desc : Update Tpromom
     * </PRE>
     * @param   Connection
     * @param   Tpromom
     * @return  int
     */

     public int update(Connection con, Tpromom tpromom) throws StoreException{
         PreparedStatement pstmt       = null;
         int executedRtn = 0;

         try {

             pstmt = con.prepareStatement(makeSqlUpdate(tpromom));
             int index = 1;
             pstmt.setString(index++,tpromom.getUse_code());
             pstmt.setString(index++,tpromom.getModify_date());
             pstmt.setString(index++,tpromom.getModify_id());
             pstmt.setString(index++,tpromom.getLottery_promo_no());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tpromom.getUse_code()                    ); logString.append( "/" );
             logString.append( tpromom.getModify_date()                 ); logString.append( "/" );
             logString.append( tpromom.getModify_id()                   ); logString.append( "/" );
             logString.append( tpromom.getLottery_promo_no()            ); logString.append( "/" );
             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("[LotterPromoSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[LotterPromoSvc.update() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }

}
