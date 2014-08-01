
package com.cware.back.service.manage;

import java.sql.CallableStatement;
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
import com.cware.back.entity.table.Tcouponissue;
import com.cware.back.entity.table.Tlotterypromom;
import com.cware.back.entity.table.Tlotterypromoprize;
import com.cware.back.entity.table.Tsaveget;

/**
 * Service class
 *
 * @version 1.0, 2006/11/14
 */
public class LotteryPromoSelectSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public LotteryPromoSelectSvc() {}


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
        sb.append("        A.MEDIA_CODE_ALL_YN,  \n");
        sb.append("        A.MEDIA_CODE,  \n");
        sb.append("        A.USE_CODE,  \n");
        sb.append("        A.END_YN,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS END_DATE,  \n");
        sb.append("        A.PROMO_NOTE,  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        A.MODIFY_ID,  \n");
        //2011.02.10 추가
        sb.append("        A.VALID_DAYS,  \n");
        sb.append("        A.APPLY_YN,  \n");
        sb.append("        A.GOODS_CODE,  \n");
        sb.append("        A.GOODSDT_CODE,  \n");
        sb.append("        A.DO_AMT,  \n");
        sb.append("        A.COUPON_PROMO_NO,  \n");
        sb.append("        A.TAX_RCV_FLAG  \n");
        sb.append("    FROM TLOTTERYPROMOM A  \n");
        sb.append("   WHERE A.LOTTERY_PROMO_NO = ?  \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
         sb.append("         CONFIRM_CNT      = ?, \n ");
         sb.append("         END_YN           = ?, \n ");
         sb.append("         END_DATE         = TO_DATE(?,'YYYY/MM/DD'), \n ");
         sb.append("         MODIFY_DATE      = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
         sb.append("         MODIFY_ID        = ?  \n ");
         sb.append("   WHERE LOTTERY_PROMO_NO = ?  \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }


    /**
     * <PRE>
     * Desc : Make SQL ; TLOTTERYPROMOCUST List
     * </PRE>
     * @param   RetrieveModel
     * @return  String
     */
     private String makeSqlCust( RetrieveModel retrieve ) throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT A.SEQ,  					\n");
         sb.append("        A.LOTTERY_PROMO_NO,  		\n");
         sb.append("        A.CUST_NO,  				\n");
         sb.append("        A.ORDER_NO,  				\n");
         sb.append("        A.PROMO_NO  				\n");
         sb.append("   FROM TLOTTERYPROMOCUST A 		\n");
         sb.append("  WHERE A.LOTTERY_PROMO_NO = ?  	\n");
         sb.append("    AND A.CANCEL_YN = '0'  			\n");
         sb.append("    AND NOT EXISTS ( SELECT 'X' 	\n");
         sb.append("                       FROM TLOTTERYPROMOPRIZE LPP,  		\n");
         sb.append("                            TLOTTERYPROMOM  LP1,			\n");
         sb.append("                            TLOTTERYPROMOM  LP2				\n");
         sb.append("                      WHERE LPP.CUST_NO                  = A.CUST_NO					\n");
         sb.append("                        AND LPP.LOTTERY_PROMO_NO         = LP1.LOTTERY_PROMO_NO			\n");
         sb.append("                        AND LP1.PARENT_LOTTERY_PROMO_NO  = LP2.PARENT_LOTTERY_PROMO_NO	\n");
         sb.append("                        AND LP2.LOTTERY_PROMO_NO         = ?   ) 						\n");
         sb.append("    AND NOT EXISTS ( SELECT 'X' 	\n");
         sb.append("                       FROM TLOTTERYPROMOPRIZE LPP,  		\n");
         sb.append("                            TLOTTERYPROMOM  LP2				\n");
         sb.append("                      WHERE LPP.CUST_NO                  = A.CUST_NO					\n");
         sb.append("                        AND LPP.LOTTERY_PROMO_NO         = LP2.PARENT_LOTTERY_PROMO_NO	\n");
         sb.append("                        AND LP2.LOTTERY_PROMO_NO         = ?   ) 						\n");

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
          sb.append("         A.TEL AS TEL_NU,  \n");
          sb.append("         FUN_ADD_POSTADDR(A.RECEIVER_POST,A.RECEIVER_POST_SEQ,A.RECEIVER_ADDR) AS ADDR,  \n");
          sb.append("		  E.DO_TYPE, \n");
          sb.append("         E.CONFIRM_CNT, \n");
          sb.append("         E.PROVIDE_CNT, \n");
          sb.append("         E.END_YN, \n");
          sb.append("         TO_CHAR(E.END_DATE, 'YYYY/MM/DD') AS END_DATE,  \n");
          sb.append("         E.PROMO_NOTE, \n");
          sb.append("         E.APPLY_YN, \n");
          sb.append("         C.PROMO_ORDER_NO, \n");
          sb.append("         C.TAX_RCV_YN, \n");
          sb.append("         C.DO_AMT \n");
          sb.append("    FROM TRECEIVER A,  \n");
          sb.append("         TCUSTOMER B,  \n");
          sb.append("         TLOTTERYPROMOPRIZE C,  \n");
          sb.append("         TLOTTERYPROMOM E  \n");
          sb.append("   WHERE A.CUST_NO           = B.CUST_NO(+)  \n");
          sb.append("     AND A.CUST_NO           = C.CUST_NO  \n");
          sb.append("     AND C.LOTTERY_PROMO_NO  = E.LOTTERY_PROMO_NO  \n");
          sb.append("     AND A.DEFAULT_YN        = '1'  \n");
          sb.append("     AND C.LOTTERY_PROMO_NO  = ?  \n");

          //= log SQL -------------------------------------------------
          if (log.isDebugEnabled()) {
              log.debug(sb.toString());
          }
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Make SQL ; TLOTTERYPROMOCUST List
       * </PRE>
       * @param   RetrieveModel
       * @return  String
       */
       private String makeSqlApplicant() throws StoreException{

           StringBuffer sb = new StringBuffer();

           sb.append(" SELECT A.SEQ,  \n");
           sb.append("        A.CUST_NO,   \n");
           sb.append("        B.CUST_NAME, \n");
           sb.append("        A.ORDER_NO,  \n");
           sb.append("        A.CANCEL_YN, \n");
           sb.append("        TO_CHAR(A.CANCEL_DATE,'YYYY/MM/DD') AS CANCEL_DATE, \n");
           sb.append("        TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE  \n");
           sb.append("   FROM TLOTTERYPROMOCUST A, \n");
           sb.append("        TCUSTOMER  B  \n");
           sb.append("  WHERE A.CUST_NO = B.CUST_NO  \n");
           sb.append("    AND A.LOTTERY_PROMO_NO = ? \n");

           //= log SQL -------------------------------------------------
           if (log.isDebugEnabled()) {
               log.debug(sb.toString());
           }
           return sb.toString();
       }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TLOTTERYPROMOPRIZE )
    * </PRE>
    * @param   Tlotterypromoprize
    * @return  String
    */

    private String makeSqlInsert(Tlotterypromoprize tlotterypromoprize) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TLOTTERYPROMOPRIZE (  \n");
        sb.append("        LOTTERY_PROMO_NO,  \n");
        sb.append("        SEQ,  \n");
        sb.append("        CUST_NO,  \n");
        sb.append("        PROMO_ORDER_NO,  \n");
        sb.append("        CANCEL_YN,  \n");
        sb.append("        INSERT_DATE,  \n");
        sb.append("        INSERT_ID,  \n");
        sb.append("        DO_AMT,  \n");
        sb.append("        TAX_RCV_YN,  \n");
        sb.append("        TAX_RCV_DATE,  \n");
        sb.append("        TAX_RCV_AMT,  \n");
        sb.append("        TAX_RCV_ID,  \n");
        sb.append("        RESIDENT_NO, \n");
        sb.append("        MODIFY_DATE,  \n");
        sb.append("        MODIFY_ID )  \n");
        sb.append(" VALUES ( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         NULL, \n ");
        sb.append("         0, \n ");
        sb.append("         NULL, \n ");
        sb.append("         NULL, \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ? ) \n ");

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

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LotterPromoSelectSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoSelectSvc.retrieve() Exception : ERR-"+e.getMessage());
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
     public RetrieveModel retrieveCust(Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;

         try {
             pstmt = con.prepareStatement(makeSqlCust(retrieve));

             int index = 1;
             pstmt.setString(index++,retrieve.getString("lottery_promo_no"));
             pstmt.setString(index++,retrieve.getString("lottery_promo_no"));
             pstmt.setString(index++,retrieve.getString("lottery_promo_no"));

             rset  = pstmt.executeQuery();

             retrieve.put("RESULT_CUST",makeSheet(rset));

         }catch(SQLException se){
             log.error("[LotterPromoSelectSvc.retrieveCust() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[LotterPromoSelectSvc.retrieveCust() Exception : ERR-"+e.getMessage());
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
              log.error("[LotterPromoSelectSvc.retrievePrize() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              log.error("[LotterPromoSelectSvc.retrievePrize() Exception : ERR-"+e.getMessage());
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
       public RetrieveModel retrieveApplicant(Connection con, RetrieveModel retrieve) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;

           try {
               pstmt = con.prepareStatement(makeSqlApplicant());

               int index = 1;
               pstmt.setString(index++,retrieve.getString("lottery_promo_no"));

               rset  = pstmt.executeQuery();

               retrieve.put("RESULT_APPLICANT", makeSheet(rset));

           }catch(SQLException se){
               log.error("[LotterPromoSelectSvc.retrieveApplicant() SQLException : ERR-"+se.getErrorCode()+":"+se);
               throw new StoreException(se.getMessage());
           }catch(Exception e){
               log.error("[LotterPromoSelectSvc.retrieveApplicant() Exception : ERR-"+e.getMessage());
               throw new StoreException(e.getMessage());
           }finally {
               DBUtils.freeConnection(null, pstmt, rset);
           }
           return retrieve;
       }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TLOTTERYPROMOPRIZE
    * </PRE>
    * @param   Connection
    * @param   TlotterypromoPrize
    * @return  int
    */
    public int insert(Connection con, Tlotterypromoprize tlotterypromoprize) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tlotterypromoprize));
            int index = 1;

            pstmt.setString(index++,tlotterypromoprize.getLottery_promo_no());
            pstmt.setString(index++,tlotterypromoprize.getSeq());
            pstmt.setString(index++,tlotterypromoprize.getCust_no());
            pstmt.setString(index++,tlotterypromoprize.getPromo_order_no());
            pstmt.setString(index++,tlotterypromoprize.getCancel_yn());
            pstmt.setString(index++,tlotterypromoprize.getInsert_date());
            pstmt.setString(index++,tlotterypromoprize.getInsert_id());
            pstmt.setLong(index++,  tlotterypromoprize.getDo_amt());
            pstmt.setString(index++,tlotterypromoprize.getTax_rcv_yn());
            pstmt.setString(index++,tlotterypromoprize.getModify_date());
            pstmt.setString(index++,tlotterypromoprize.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tlotterypromoprize.getLottery_promo_no()            ); logString.append( "/" );
            logString.append( tlotterypromoprize.getSeq()                         ); logString.append( "/" );
            logString.append( tlotterypromoprize.getCust_no()                     ); logString.append( "/" );
            logString.append( tlotterypromoprize.getPromo_order_no()              ); logString.append( "/" );
            logString.append( tlotterypromoprize.getCancel_yn()                   ); logString.append( "/" );
            logString.append( tlotterypromoprize.getInsert_date()                 ); logString.append( "/" );
            logString.append( tlotterypromoprize.getInsert_id()                   ); logString.append( "/" );
            logString.append( tlotterypromoprize.getDo_amt()                      ); logString.append( "/" );
            logString.append( tlotterypromoprize.getTax_rcv_yn()                  ); logString.append( "/" );
            logString.append( tlotterypromoprize.getModify_date()                 ); logString.append( "/" );
            logString.append( tlotterypromoprize.getModify_id()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotterPromoSelectSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotterPromoSelectSvc.insert() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,tlotterypromom.getConfirm_cnt());
            pstmt.setString(index++,tlotterypromom.getEnd_yn());
            pstmt.setString(index++,tlotterypromom.getEnd_date());
            pstmt.setString(index++,tlotterypromom.getModify_date());
            pstmt.setString(index++,tlotterypromom.getModify_id());
            pstmt.setString(index++,tlotterypromom.getLottery_promo_no());



            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tlotterypromom.getConfirm_cnt()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getEnd_yn()                      ); logString.append( "/" );
            logString.append( tlotterypromom.getEnd_date()                    ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_date()                 ); logString.append( "/" );
            logString.append( tlotterypromom.getModify_id()                   ); logString.append( "/" );
            logString.append( tlotterypromom.getLottery_promo_no()            ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotterPromoSelecttSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotterPromoSelecttSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
     * <PRE>
     * Desc : Retrieve
     * </PRE>
     * @param   Connection
     * @param   String goodsCode
     * @return  long
     */
	public long retrieveGoodsPrice(Connection con, String goodsCode) throws StoreException {
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        long  goodsPrice = 0;
        try {
            pstmt = con.prepareStatement(makeSqlGoodsPrice());

            int index = 1;
            pstmt.setString(index++,goodsCode);

            rset  = pstmt.executeQuery();

            if(rset.next()) {
            	goodsPrice = rset.getLong(1);
            }

        }catch(SQLException se){
            log.error("[LotterPromoSelectSvc.retrieveGoodsPrice() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoSelectSvc.retrieveGoodsPrice() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return goodsPrice;
	}

	//= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TGOODSPRICE CUST_PRICE
    * </PRE>
    * @param
    * @return  String
    */
    private String makeSqlGoodsPrice() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT C.CUST_PRICE                                               \n");
        sb.append("   FROM TGOODSPRICE C                                              \n");
        sb.append("  WHERE C.GOODS_CODE = ?                                           \n");
        sb.append("    AND C.APPLY_DATE = ( SELECT MAX(Z.APPLY_DATE)                  \n");
        sb.append("                           FROM TGOODSPRICE Z                      \n");
        sb.append("                          WHERE Z.GOODS_CODE = C.GOODS_CODE        \n");
        sb.append("                            AND Z.APPLY_DATE <= SYSDATE ) \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /* insert tlotterypromocust
    *
    */
   public int insertTcouponissue(Connection con, Tcouponissue coupon) throws StoreException{
       PreparedStatement pstmt       = null;
       ResultSet         rset        = null;
       int executedRtn = 0;

       try {
           pstmt = con.prepareStatement(makeSqlInsertTcouponissue());
           int index = 1;

           pstmt.setString(index++,coupon.getSeq());
           pstmt.setString(index++,coupon.getPromo_no());
           pstmt.setString(index++,coupon.getCust_no());
           pstmt.setString(index++,coupon.getGet_order_no());
           pstmt.setString(index++,coupon.getUse_start_date());
           pstmt.setString(index++,coupon.getUse_end_date());
           pstmt.setString(index++,coupon.getInsert_id());
           pstmt.setString(index++,coupon.getInsert_id());

           //= log Save Data ---------------------
           StringBuffer logString = new StringBuffer();
           logString.append( coupon.getSeq()      ); logString.append( "/" );
           logString.append( coupon.getPromo_no() ); logString.append( "/" );
           logString.append( coupon.getCust_no()  ); logString.append( "/" );
           logString.append( coupon.getGet_order_no()  ); logString.append( "/" );
           logString.append( coupon.getUse_start_date()); logString.append( "/" );
           logString.append( coupon.getUse_end_date()); logString.append( "/" );
           logString.append( coupon.getInsert_id()); logString.append( "/" );

           logSave.info(logString.toString());

           executedRtn = pstmt.executeUpdate();

       }catch(SQLException se){
           logSave.error("[LotterPromoSelectSvc.insertTcouponissue() SQLException : ERR-"+se.getErrorCode()+":"+se);
           throw new StoreException(se.getMessage());
       }catch(Exception e){
           logSave.error("[LotterPromoSelectSvc.insertTcouponissue()) Exception : ERR-"+e.getMessage());
           throw new StoreException(e.getMessage());
       }finally {
           DBUtils.freeConnection(null, pstmt, rset);
       }
       return executedRtn;
   }

   /* insert TCOUPONISSUE
    *
    */
   private String makeSqlInsertTcouponissue() throws StoreException{
       StringBuffer sb = new StringBuffer();

       sb.append("  INSERT INTO TCOUPONISSUE ( \n ");
       sb.append("         SEQ, \n ");
       sb.append("         PROMO_NO, \n ");
       sb.append("         CUST_NO, \n ");
       sb.append("         GET_ORDER_NO, \n ");
       sb.append("         USE_START_DATE, \n ");
       sb.append("         USE_END_DATE, \n ");
       sb.append("         INSERT_DATE, \n ");
       sb.append("         INSERT_ID, \n ");
       sb.append("         MODIFY_DATE, \n ");
       sb.append("         MODIFY_ID) \n ");
       sb.append(" VALUES ( \n ");
       sb.append("         ?, \n ");
       sb.append("         ?, \n ");
       sb.append("         ?, \n ");
       sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
       sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
       sb.append("         SYSDATE, \n ");
       sb.append("         ?, \n ");
       sb.append("         SYSDATE, \n ");
       sb.append("         ?) \n ");

       //= log SQL -------------------------------------------------
       if (logSave.isDebugEnabled()) {
           logSave.debug(sb.toString());
       }

       return sb.toString();
   }
   //= TSAVEAMT -------------------------------------------------
   /**
   * <PRE>
   * Desc : Make SQL ; Insert TSAVEGET
   * </PRE>
   * @param   Tsaveget
   * @return  String
   */

   private final String insertSqlTsaveget = " INSERT INTO TSAVEGET \n"
                                          + "       ( CUST_NO,      \n"
                                          + "         SAVEAMT_SEQ,  \n"
                                          + "         PROC_GB,      \n"
                                          + "         PROC_YN,      \n"
                                          + "         SAVEAMT_GB,   \n"
                                          + "         SAVEAMT_CODE, \n"
                                          + "         SAVE_NOTE,    \n"
                                          + "         SAVEAMT,      \n"
                                          + "         PROC_ID,      \n"
                                          + "         USABLE_AMT,   \n"
                                          + "         EXPIRE_FLAG , \n"
                                          + "         PROC_DATE,    \n"
                                          + "         DUE_DATE )    \n"
                                          + " VALUES ( ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          ?, \n"
                                          + "          TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n"
                                          + "          TO_DATE(?, 'YYYY/MM/DD')  )" ;

   private int insertSqlTsavegetLog =  0;

   private String makeSqlInsertTsaveget(Tsaveget tsaveget) throws StoreException{
       //= log SQL -------------------------------------------------
       if (insertSqlTsavegetLog == 0) {
           if (logSave.isDebugEnabled()) {
               logSave.debug("\n" + insertSqlTsaveget);
           }
           insertSqlTsavegetLog = 1;
       }
       return insertSqlTsaveget;
   }
   /**
    * <PRE>
    * Desc : Insert TSAVEGET
    * </PRE>
    * @param   Connection
    * @param   Tsaveget
    * @return  int
    */

    public int insertTsaveget(Connection con, Tsaveget tsaveget) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertTsaveget(tsaveget));
            int index = 1;

            pstmt.setString(index++, tsaveget.getCust_no()       );
            pstmt.setString(index++, tsaveget.getSaveamt_seq()   );
            pstmt.setString(index++, tsaveget.getProc_gb()       );
            pstmt.setString(index++, tsaveget.getProc_yn()       );
            pstmt.setString(index++, tsaveget.getSaveamt_gb()    );
            pstmt.setString(index++, tsaveget.getSaveamt_code()  );
            pstmt.setString(index++, tsaveget.getSave_note()     );
            pstmt.setDouble(index++, tsaveget.getSaveamt()       );
            pstmt.setString(index++, tsaveget.getProc_id()       );
            pstmt.setDouble(index++, tsaveget.getUsable_amt()    ); //= 사용금액
            pstmt.setString(index++, tsaveget.getExpire_flag()   ); //= 진행
            pstmt.setString(index++, tsaveget.getProc_date()     );
            pstmt.setString(index++, tsaveget.getDue_date()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsaveget.getCust_no()        ); logString.append( "/" );
            logString.append( tsaveget.getSaveamt_seq()    ); logString.append( "/" );
            logString.append( tsaveget.getProc_gb()        ); logString.append( "/" );
            logString.append( tsaveget.getProc_yn()        ); logString.append( "/" );
            logString.append( tsaveget.getSaveamt_gb()     ); logString.append( "/" );
            logString.append( tsaveget.getSaveamt_code()   ); logString.append( "/" );
            logString.append( tsaveget.getSave_note()      ); logString.append( "/" );
            logString.append( tsaveget.getSaveamt()        ); logString.append( "/" );
            logString.append( tsaveget.getProc_id()        ); logString.append( "/" );
            logString.append( tsaveget.getUsable_amt()     ); logString.append( "/" );
            logString.append( tsaveget.getExpire_flag()    ); logString.append( "/" );
            logString.append( tsaveget.getProc_date()      ); logString.append( "/" );
            logString.append( tsaveget.getDue_date()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotterPromoSelectSvc.insertTsaveget() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotterPromoSelectSvc.insertTsaveget() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
        	DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TLOTTERYPROMOM List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPromom( RetrieveModel retrieve ) throws StoreException{
        StringBuffer sb = new StringBuffer();
		sb.append("  SELECT PROMO_NO,                                              			\n");
		sb.append("         TO_CHAR(PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS PROMO_BDATE,   \n");
		sb.append("         TO_CHAR(PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS PROMO_EDATE,   \n");
		sb.append("         NVL(COUPON_USE_FIX_YN, '1') AS COUPON_USE_FIX_YN,      			\n");
		sb.append("         NVL(VALID_DAYS, 0) AS VALID_DAYS,                       		\n");
		sb.append("         NVL(COUPON_USE_DAY, 0) AS COUPON_USE_DAY               			\n");
		sb.append("    FROM TPROMOM                                                			\n");
		sb.append("   WHERE PROMO_NO = ?                        	            			\n");
		sb.append("     AND COUPON_YN = '1'                                        			\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
    public RetrieveModel retrievePromom(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPromom(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("coupon_promo_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PROMOM",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LotterPromoSelectSvc.retrievePromom() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LotterPromoSelectSvc.retrievePromom() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /* execute sp_create_gift_order
    *
    */
   public Tlotterypromoprize createGiftOrder(Connection con, Tlotterypromoprize tlotterypromoprize) throws StoreException{
	   CallableStatement cstmt = null;
       try {
    	   cstmt = con.prepareCall(makeSqlExecute());
           int index = 1;

           cstmt.setString(index++,tlotterypromoprize.getLottery_promo_no());
           cstmt.setString(index++,tlotterypromoprize.getCust_no());
           cstmt.setString(index++,tlotterypromoprize.getInsert_id());

           cstmt.registerOutParameter(index++, java.sql.Types.NUMERIC);
           cstmt.registerOutParameter(index++, java.sql.Types.VARCHAR);

           //= log Save Data ---------------------
           StringBuffer logString = new StringBuffer();
           logString.append( tlotterypromoprize.getLottery_promo_no()   ); logString.append( "/" );
           logString.append( tlotterypromoprize.getCust_no()    		); logString.append( "/" );
           logString.append( tlotterypromoprize.getInsert_id()      	); logString.append( "/" );

           logSave.info(logString.toString());

           cstmt.executeUpdate();

           tlotterypromoprize.setRtnCode(cstmt.getString(4));
           tlotterypromoprize.setRtnMsg(cstmt.getString(5));

       }catch(SQLException se){
           logSave.error("[LotterPromoSelectSvc.createGiftOrder() SQLException : ERR-"+se.getErrorCode()+":"+se);
           throw new StoreException(se.getMessage());
       }catch(Exception e){
           logSave.error("[LotterPromoSelectSvc.createGiftOrder()) Exception : ERR-"+e.getMessage());
           throw new StoreException(e.getMessage());
       }finally {
    	   DBUtils.freeConnection(null, null, null, cstmt, null, null);
       }
       return tlotterypromoprize;
   }

   //= SP_CREATE_GIFT_ORDER -------------------------------------------------
   /**
   * <PRE>
   * Desc : Make SQL ; Execute SP_CREATE_GIFT_ORDER
   * </PRE>
   * @param   Tsaveget
   * @return  String
   */

   private final String insertSqlCreateGiftOrder = " begin SP_CREATE_GIFT_ORDER(?, ?, ?, ?, ?); end; " ;

   private int insertSqlCreateGiftOrderLog =  0;

   private String makeSqlExecute() throws StoreException{
       //= log SQL -------------------------------------------------
       if (insertSqlCreateGiftOrderLog == 0) {
           if (logSave.isDebugEnabled()) {
               logSave.debug("\n" + insertSqlCreateGiftOrder);
           }
           insertSqlCreateGiftOrderLog = 1;
       }
       return insertSqlCreateGiftOrder;
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

   public int updateApply(Connection con, Tlotterypromom tlotterypromom) throws StoreException{
       PreparedStatement pstmt       = null;
       int executedRtn = 0;

       try {

           pstmt = con.prepareStatement(makeSqlUpdateApply(tlotterypromom));
           int index = 1;
           pstmt.setString(index++,tlotterypromom.getModify_date());
           pstmt.setString(index++,tlotterypromom.getModify_id());
           pstmt.setString(index++,tlotterypromom.getLottery_promo_no());

           //= log Save Data ---------------------
           StringBuffer logString = new StringBuffer();
           logString.append( tlotterypromom.getModify_date()                 ); logString.append( "/" );
           logString.append( tlotterypromom.getModify_id()                   ); logString.append( "/" );
           logString.append( tlotterypromom.getLottery_promo_no()            ); logString.append( "/" );
           logSave.info(logString.toString());

           executedRtn = pstmt.executeUpdate();

       }catch(SQLException se){
           logSave.error("[LotterPromoSelecttSvc.updateApply() SQLException : ERR-"+se.getErrorCode()+":"+se);
           throw new StoreException(se.getMessage());
       }catch(Exception e){
           logSave.error("[LotterPromoSelecttSvc.updateApply() Exception : ERR-"+e.getMessage());
           throw new StoreException(e.getMessage());
       }finally {
           DBUtils.freeConnection(null, pstmt, null);
       }
       return executedRtn;
   }

   /**
    * <PRE>
    * Desc : Make SQL ( Update TLOTTERYPROMOM )
    * </PRE>
    * @param   Tlotterypromom
    * @return  String
    */

    public String makeSqlUpdateApply(Tlotterypromom tlotterypromom) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TLOTTERYPROMOM SET    \n ");
        sb.append("         APPLY_YN           = '1', \n ");
        sb.append("         MODIFY_DATE      = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID        = ?  \n ");
        sb.append("   WHERE LOTTERY_PROMO_NO = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }
}
