
package com.cware.back.service.manage;

import com.cware.back.common.RetrieveModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;





/**
 * Register promotion Service class
 *
 * @version 1.0, 2006/11/24
 */
public class CouponIssueSvc {
log log=new log();


    public CouponIssueSvc() {}

    /* retrieve lottery promotion by promotion number
     *
     */
    public RetrieveModel retrievePromo(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPromo());

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_LIST",makeSheetList(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
          //  DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /* sql for retrievePromo
     *
     */
    private String makeSqlPromo() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.PROMO_NO,   \n ");
        sb.append("        A.PROMO_NAME, \n ");
        sb.append("        TO_CHAR(A.PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS BDATE,  \n ");
        sb.append("        TO_CHAR(A.PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS EDATE,  \n ");
        sb.append("        DECODE(A.USE_CODE, '00', '0', '1')  AS STOP_YN,   \n ");
        sb.append("        D.CODE_NAME AS APP_TYPE,  \n ");
        sb.append("        B.CODE_NAME AS DO_TYPE,   \n ");
        sb.append("        C.CODE_NAME AS USE_CODE,  \n ");
        sb.append("        A.REMARK   \n ");
        sb.append("   FROM TPROMOM A, \n ");
        sb.append("        TCODE  B,  \n ");
        sb.append("        TCODE  C,  \n ");
        sb.append("        TCODE  D   \n ");
        sb.append("  WHERE A.DO_TYPE  = B.CODE_MGROUP   \n ");
        sb.append("    AND A.USE_CODE = C.CODE_MGROUP   \n ");
        sb.append("    AND A.APP_TYPE = D.CODE_MGROUP   \n ");
        sb.append("    AND A.PROMO_NO = ?  \n ");
        sb.append("    AND A.COUPON_YN = '1' \n ");
        sb.append("    AND B.CODE_LGROUP = 'B007' \n ");
        sb.append("    AND C.CODE_LGROUP = 'B064' \n ");
        sb.append("    AND D.CODE_LGROUP = 'B008' \n ");
        sb.append("  ORDER BY A.PROMO_NO DESC \n ");

        //= log SQL -------------------------------------------------
        
        return sb.toString();
    }

    /* retrieve lottery promotion by promotion number
     *
     */
    public RetrieveModel retrieveList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlList());

            int index = 1;
            pstmt.setString(index++,retrieve.getString("frDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));
            pstmt.setString(index++,retrieve.getString("app_type"));
            pstmt.setString(index++,retrieve.getString("do_type"));
            pstmt.setString(index++,retrieve.getString("use_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_LIST",makeSheetList(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /* sql for retrieveList
     *
     */
    private String makeSqlList() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.PROMO_NO,   \n ");
        sb.append("        A.PROMO_NAME, \n ");
        sb.append("        TO_CHAR(A.PROMO_BDATE, 'YYYY/MM/DD HH24:MI:SS') AS BDATE,  \n ");
        sb.append("        TO_CHAR(A.PROMO_EDATE, 'YYYY/MM/DD HH24:MI:SS') AS EDATE,  \n ");
        sb.append("        DECODE(A.USE_CODE, '00', '0', '1')  AS STOP_YN,   \n ");
        sb.append("        D.CODE_NAME AS APP_TYPE,  \n ");
        sb.append("        B.CODE_NAME AS DO_TYPE,   \n ");
        sb.append("        C.CODE_NAME AS USE_CODE,  \n ");
        sb.append("        A.REMARK   \n ");
        sb.append("   FROM TPROMOM A, \n ");
        sb.append("        TCODE  B,  \n ");
        sb.append("        TCODE  C,  \n ");
        sb.append("        TCODE  D   \n ");
        sb.append("  WHERE A.DO_TYPE  = B.CODE_MGROUP   \n ");
        sb.append("    AND A.USE_CODE = C.CODE_MGROUP   \n ");
        sb.append("    AND A.APP_TYPE = D.CODE_MGROUP   \n ");
        sb.append("    AND A.PROMO_EDATE >= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')  \n ");
        sb.append("    AND A.PROMO_BDATE <= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')+1 \n ");
        sb.append("    AND A.APP_TYPE LIKE ?||'%'  \n ");
        sb.append("    AND A.DO_TYPE  LIKE ?||'%'  \n ");
        sb.append("    AND A.USE_CODE LIKE ?||'%'  \n ");
        sb.append("    AND A.COUPON_YN = '1' \n ");
        sb.append("    AND B.CODE_LGROUP = 'B007' \n ");
        sb.append("    AND C.CODE_LGROUP = 'B064' \n ");
        sb.append("    AND D.CODE_LGROUP = 'B008' \n ");
        sb.append("  ORDER BY A.PROMO_NO DESC \n ");

        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }


    /* retrieve lottery promotion customer count
     *
     */
    public RetrieveModel retrieveCount(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlCount());

            int index = 1;

            pstmt.setString(index++,retrieve.getString("promo_no"));

            rset  = pstmt.executeQuery();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("promo_no") ); logString.append( "/" );

            retrieve.put("RESULT_COUNT", makeSheetCount(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieveCount() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieveCount() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /* retrieve lottery promotion customer count
    *
    */
   public RetrieveModel retrieveCancelCount(Connection con, RetrieveModel retrieve) throws StoreException{
       PreparedStatement pstmt       = null;
       ResultSet         rset        = null;

       try {
           pstmt = con.prepareStatement(makeSqlCancelCount());

           int index = 1;

           pstmt.setString(index++,retrieve.getString("promo_no"));

           rset  = pstmt.executeQuery();

           //= log Save Data ---------------------
           StringBuffer logString = new StringBuffer();
           logString.append( retrieve.getString("promo_no") ); logString.append( "/" );

           retrieve.put("RESULT_CANCLE_COUNT", makeSheetCount(rset));

       }catch(SQLException se){
           log.error("[SearchSvc.retrieveCount() SQLException : ERR-"+se.getErrorCode()+":"+se);
           throw new StoreException(se.getMessage());
       }catch(Exception e){
           log.error("[SearchSvc.retrieveCount() Exception : ERR-"+e.getMessage());
           throw new StoreException(e.getMessage());
       }finally {
           DBUtils.freeConnection(null, pstmt, rset);
       }
       return retrieve;
   }


   /**
    * <PRE>
    * Desc : Make SQL ( sqc result count )
    * </PRE>
    * @param   tgoods
    * @return  String
    */
   private String makeSqlCancelCount() throws StoreException{
       StringBuffer sb = new StringBuffer();
       sb.append("  SELECT COUNT(*) as CNT          \n ");
       sb.append("    FROM TCOUPONISSUE A,     		\n ");
       sb.append("         TCUSTOMER B              \n ");
       sb.append("   WHERE A.CUST_NO = B.CUST_NO    \n ");
       sb.append("     AND A.PROMO_NO = ?   		\n ");
       sb.append("     AND A.CANCEL_YN = '1'   	 	\n ");
       //= log SQL -------------------------------------------------
      

       return sb.toString();
   }

    /**
     * <PRE>
     * Desc : Make SQL ( sqc result count )
     * </PRE>
     * @param   PROMO_NO
     * @return  String
     */
    private String makeSqlCount() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT COUNT(*) as CNT          \n ");
        sb.append("    FROM TCOUPONISSUE A,     \n ");
        sb.append("         TCUSTOMER B              \n ");
        sb.append("   WHERE A.CUST_NO = B.CUST_NO    \n ");
        sb.append("     AND A.PROMO_NO = ?   \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
        	log.debug(sb.toString());
        }

        return sb.toString();
    }

    private HashMap[] makeSheetCount(ResultSet rset)  throws Exception {
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
                Collection c = hmSheet.keySet();
                Iterator i   = c.iterator();
                while(i.hasNext()){
                    Object key = i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }


    /* make HashMap for sheet
     *
     */
    private HashMap[] makeSheetList(ResultSet rset)  throws Exception {
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


    /* retrieve lottery promotion customer
     *
     */
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDetail());

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DETAIL", makeSheetDetail(rset));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("promo_no") ); logString.append( "/" );

        }catch(SQLException se){
            log.error("[SearchSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /* retrieve lottery promotion customer
     *
     */
    private String makeSqlDetail() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SEQ,       \n ");
        sb.append("        A.CUST_NO,   \n ");
        sb.append("        B.CUST_NAME, \n ");
        sb.append("        A.USE_YN, 	\n ");
        sb.append("        A.CANCEL_YN , \n ");
        sb.append("        A.PROMO_NO    \n ");
        sb.append("   FROM TCOUPONISSUE A,   \n ");
        sb.append("        TCUSTOMER B            \n ");
        sb.append("  WHERE A.CUST_NO = B.CUST_NO  \n ");
        sb.append("    AND A.PROMO_NO = ? \n ");
        sb.append("  ORDER BY A.SEQ     \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /* make HashMap for detail sheet
     *
     */
    private HashMap[] makeSheetDetail(ResultSet rset)  throws Exception {
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
                Collection c = hmSheet.keySet();
                Iterator i   = c.iterator();
                while(i.hasNext()){
                    Object key = i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    /* insert tlotterypromocust
     *
     */
    public int insert() throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;
    return 0;
/**
        try {
            pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;

            pstmt.setString(index++,coupon.getSeq());
            pstmt.setString(index++,coupon.getPromo_no());
            pstmt.setString(index++,coupon.getCust_no());
            pstmt.setString(index++,coupon.getCancel_yn());
            pstmt.setString(index++,coupon.getInsert_id());
            pstmt.setString(index++,coupon.getInsert_id());
            pstmt.setString(index++,coupon.getUse_start_date());
            pstmt.setString(index++,coupon.getUse_end_date());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( coupon.getSeq()      ); logString.append( "/" );
            logString.append( coupon.getPromo_no() ); logString.append( "/" );
            logString.append( coupon.getCust_no()  ); logString.append( "/" );
            logString.append( coupon.getCancel_yn()); logString.append( "/" );
            logString.append( coupon.getInsert_id()); logString.append( "/" );
            logString.append( coupon.getUse_start_date()); logString.append( "/" );
            logString.append( coupon.getUse_end_date()); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[LotteryCustSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotteryCustSvc.insert()) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
        */
    }

  
     
        
    private String makeSqlInsert() {
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TCOUPONISSUE ( \n ");
        sb.append("         SEQ, \n ");
        sb.append("         PROMO_NO, \n ");
        sb.append("         CUST_NO, \n ");
        sb.append("         CANCEL_YN, \n ");
        sb.append("         INSERT_DATE, \n ");
        sb.append("         INSERT_ID, \n ");
        sb.append("         MODIFY_DATE, \n ");
        sb.append("         MODIFY_ID, \n ");
        sb.append("         USE_START_DATE, \n ");
        sb.append("         USE_END_DATE) \n ");
        sb.append(" VALUES ( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /* update tlotterypromocust
     *
     */
    public int update() throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
       //     pstmt = con.prepareStatement(makeSqlUpdate());
            int index = 1;

         //   pstmt.setString(index++,coupon.getCancel_yn());
         //   pstmt.setString(index++,coupon.getModify_date());
         //   pstmt.setString(index++,coupon.getModify_id());
         //   pstmt.setString(index++,coupon.getSeq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
          //  logString.append( coupon.getCancel_yn()  ); logString.append( "/" );
          //  logString.append( coupon.getModify_date()); logString.append( "/" );
          //  logString.append( coupon.getModify_id()  ); logString.append( "/" );
         //   logString.append( coupon.getSeq()        ); logString.append( "/" );

           // logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
           // logSave.error("[LotteryCustSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[LotteryCustSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /* update tlotterypromocust
     *
     */
    private String makeSqlUpdate() throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TCOUPONISSUE SET \n ");
        sb.append("         CANCEL_YN   = ?, \n ");
        sb.append("         MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID   = ?  \n ");
        sb.append("   WHERE SEQ         = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

}
