
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
import com.cware.back.entity.table.Tshop;


/**
 * Register warehouse Service class
 *
 * @version 1.0, 2006/07/10
 */
public class ShopSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ShopSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   shop_code                       : shop코드
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.SHOP_CODE, \n");
        sb.append("       A.SHOP_NAME, \n");
        sb.append("       A.WH_CODE, \n");
        sb.append("       C.WH_NAME, \n");
        sb.append("       A.SHOP_CUST_NO, \n");
        sb.append("       B.CUST_NAME, \n");
        sb.append("       A.POST_NO, \n");
        sb.append("       A.POST_SEQ, \n");
        sb.append("       FUN_DISPLAY_POSTADDR(D.CITY_NAME, D.GU_NAME, D.DONG_NAME) AS DONG_NAME, \n");
        sb.append("       A.ADDR, \n");
        sb.append("       A.ORDER_MEDIA, \n");
        sb.append("       A.USER_GB, \n");
        sb.append("       A.DELY_GB, \n");
        sb.append("       A.RECEIVER_GB, \n");
        sb.append("       A.BANK_CODE, \n");
        sb.append("       E.BANK_NAME, \n");
        sb.append("       A.BANK_SEQ, \n");
        sb.append("       TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') INSERT_DATE, \n");
        sb.append("       A.INSERT_ID, \n");
        sb.append("       TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD') MODIFY_DATE, \n");
        sb.append("       A.MODIFY_ID \n");
        sb.append("  FROM TSHOP A, \n");
        sb.append("       TCUSTOMER B, \n");
        sb.append("       TWAREHOUSE C, \n");
        sb.append("       TPOST D, \n");
        sb.append("       TBANK E \n");
        sb.append(" WHERE A.SHOP_CUST_NO = B.CUST_NO \n");
        sb.append("   AND A.WH_CODE = C.WH_CODE \n");
        sb.append("   AND A.POST_NO = D.POST_NO \n");
        sb.append("   AND A.BANK_CODE = E.BANK_CODE \n");
        sb.append("   AND SHOP_CODE LIKE ? || '%' \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("shop_code    : " + retrieve.getString("shop_code"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( 샵코드 중복 체크 )
    * </PRE>
    * @param   Tshop
    * @return  String
    */
    public String makeSqlDupCheck(Tshop shop) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(SHOP_CODE)   \n ");
        sb.append("   FROM TSHOP   \n ");
        sb.append("  WHERE SHOP_CODE = ?    \n ");
        

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        
        return sb.toString();
    }
    
    /**
     * <PRE>
     * Desc : Make SQL ( 샵명 중복 체크 )
     * </PRE>
     * @param   Tshop
     * @return  String
     */
     public String makeSqlDupCheck1(Tshop shop) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append(" SELECT COUNT(SHOP_NAME)   \n ");
         sb.append("   FROM TSHOP   \n ");
         sb.append("  WHERE SHOP_NAME = ?    \n ");
         

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }
         
         return sb.toString();
     }
     
     /**
      * <PRE>
      * Desc : Make SQL ( 창고코드 중복 체크 )
      * </PRE>
      * @param   Tshop
      * @return  String
      */
      public String makeSqlDupCheck2(Tshop shop) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append(" SELECT COUNT(WH_CODE)   \n ");
          sb.append("   FROM TSHOP   \n ");
          sb.append("  WHERE WH_CODE = ?    \n ");
          

          //= log SQL -------------------------------------------------
          if (logSave.isDebugEnabled()) {
              logSave.debug(sb.toString());
          }
          
          return sb.toString();
      }
      
      /**
       * <PRE>
       * Desc : Make SQL ( 대표고객번호 중복 체크 )
       * </PRE>
       * @param   Tshop
       * @return  String
       */
       public String makeSqlDupCheck3(Tshop shop) throws StoreException{

           StringBuffer sb = new StringBuffer();

           sb.append(" SELECT COUNT(SHOP_CUST_NO)   \n ");
           sb.append("   FROM TSHOP   \n ");
           sb.append("  WHERE SHOP_CUST_NO = ?    \n ");
           

           //= log SQL -------------------------------------------------
           if (logSave.isDebugEnabled()) {
               logSave.debug(sb.toString());
           }
           
           return sb.toString();
       }
       
       /**
        * <PRE>
        * Desc : Make SQL ( 주문매체 중복 체크 )
        * </PRE>
        * @param   Tshop
        * @return  String
        */
        public String makeSqlDupCheck4(Tshop shop) throws StoreException{

            StringBuffer sb = new StringBuffer();

            sb.append(" SELECT COUNT(ORDER_MEDIA)   \n ");
            sb.append("   FROM TSHOP   \n ");
            sb.append("  WHERE ORDER_MEDIA = ?    \n ");
            

            //= log SQL -------------------------------------------------
            if (logSave.isDebugEnabled()) {
                logSave.debug(sb.toString());
            }
            
            return sb.toString();
        }
        
        /**
         * <PRE>
         * Desc : Make SQL ( 사용자 중복 체크 )
         * </PRE>
         * @param   Tshop
         * @return  String
         */
         public String makeSqlDupCheck5(Tshop shop) throws StoreException{

             StringBuffer sb = new StringBuffer();

             sb.append(" SELECT COUNT(USER_GB)   \n ");
             sb.append("   FROM TSHOP   \n ");
             sb.append("  WHERE USER_GB = ?    \n ");
             

             //= log SQL -------------------------------------------------
             if (logSave.isDebugEnabled()) {
                 logSave.debug(sb.toString());
             }
             
             return sb.toString();
         }
         
         /**
          * <PRE>
          * Desc : Make SQL ( 사용자 중복 체크 )
          * </PRE>
          * @param   Tshop
          * @return  String
          */
          public String makeSqlDupCheck6(Tshop shop) throws StoreException{

              StringBuffer sb = new StringBuffer();

              sb.append(" SELECT COUNT(DELY_GB)   \n ");
              sb.append("   FROM TSHOP   \n ");
              sb.append("  WHERE DELY_GB = ?    \n ");
              

              //= log SQL -------------------------------------------------
              if (logSave.isDebugEnabled()) {
                  logSave.debug(sb.toString());
              }
              
              return sb.toString();
          }
         
         /**
          * <PRE>
          * Desc : Make SQL ( 배달지 중복 체크 )
          * </PRE>
          * @param   Tshop
          * @return  String
          */
          public String makeSqlDupCheck7(Tshop shop) throws StoreException{

              StringBuffer sb = new StringBuffer();

              sb.append(" SELECT COUNT(RECEIVER_GB)   \n ");
              sb.append("   FROM TSHOP   \n ");
              sb.append("  WHERE RECEIVER_GB = ?    \n ");
              

              //= log SQL -------------------------------------------------
              if (logSave.isDebugEnabled()) {
                  logSave.debug(sb.toString());
              }
              
              return sb.toString();
          }
          
          /**
           * <PRE>
           * Desc : Make SQL ( 은행코드 중복 체크 )
           * </PRE>
           * @param   Tshop
           * @return  String
           */
           public String makeSqlDupCheck8(Tshop shop) throws StoreException{

               StringBuffer sb = new StringBuffer();

               sb.append(" SELECT COUNT(BANK_CODE)   \n ");
               sb.append("   FROM TSHOP   \n ");
               sb.append("  WHERE BANK_CODE = ?    \n ");
               

               //= log SQL -------------------------------------------------
               if (logSave.isDebugEnabled()) {
                   logSave.debug(sb.toString());
               }
               
               return sb.toString();
           }
  
    /**
    * <PRE>
    * Desc : Make SQL ( Tshop 에 등록 )
    * </PRE>
    * @param   Tshop
    * @return  String
    */
    public String makeSqlInsert(Tshop shop) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("INSERT INTO TSHOP ( \n ");
        sb.append("       SHOP_CODE, \n ");              
        sb.append("       SHOP_NAME, \n ");              
        sb.append("       WH_CODE, \n ");           
        sb.append("       SHOP_CUST_NO, \n ");       
        sb.append("       POST_NO, \n ");         
        sb.append("       POST_SEQ, \n ");              
        sb.append("       ADDR, \n ");          
        sb.append("       ORDER_MEDIA, \n ");              
        sb.append("       USER_GB, \n ");               
        sb.append("       DELY_GB, \n ");              
        sb.append("       RECEIVER_GB, \n ");              
        sb.append("       BANK_CODE, \n ");           
        sb.append("       BANK_SEQ, \n ");              
        sb.append("       INSERT_ID, \n ");          
        sb.append("       INSERT_DATE, \n ");            
        sb.append("       MODIFY_ID, \n ");          
        sb.append("       MODIFY_DATE ) \n ");
        sb.append("VALUES ( \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        SYSDATE, \n ");
        sb.append("        ?, \n ");
        sb.append("        SYSDATE ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TSHOP 변경 )
    * </PRE>
    * @param   Tshop
    * @return  String
    */
    public String makeSqlUpdate(Tshop shop) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("UPDATE TSHOP SET \n ");
        sb.append(   "    SHOP_CODE        = ?, \n ");
        sb.append(   "    SHOP_NAME     = ?, \n ");
        sb.append(   "    WH_CODE = ?, \n ");
        sb.append(   "    SHOP_CUST_NO   = ?, \n ");
        sb.append(   "    POST_NO        = ?, \n ");
        sb.append(   "    POST_SEQ    = ?, \n ");
        sb.append(   "    ADDR        = ?, \n ");
        sb.append(   "    ORDER_MEDIA         = ?, \n ");
        sb.append(   "    USER_GB        = ?, \n ");
        sb.append(   "    DELY_GB        = ?, \n ");
        sb.append(   "    RECEIVER_GB     = ?, \n ");
        sb.append(   "    BANK_CODE        = ?, \n ");
        sb.append(   "    BANK_SEQ        = ?, \n ");
        sb.append(   "    MODIFY_DATE    = SYSDATE, \n ");
        sb.append(   "    MODIFY_ID      = ? \n ");       
        sb.append(" WHERE SHOP_CODE     = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
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
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @param   shop_code                       : shop코드
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("shop_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ShopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ShopSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int insert(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(shop));
            
            int index = 1;
            pstmt.setString(index++, shop.getShop_code   ());
            pstmt.setString(index++, shop.getShop_name   ());
            pstmt.setString(index++, shop.getWh_code     ());
            pstmt.setString(index++, shop.getShop_cust_no());
            pstmt.setString(index++, shop.getPost_no     ());
            pstmt.setString(index++, shop.getPost_seq    ());
            pstmt.setString(index++, shop.getAddr        ());
            pstmt.setString(index++, shop.getOrder_media ());
            pstmt.setString(index++, shop.getUser_gb     ());
            pstmt.setString(index++, shop.getDely_gb     ());
            pstmt.setString(index++, shop.getReceiver_gb ());
            pstmt.setString(index++, shop.getBank_code   ());
            pstmt.setString(index++, shop.getBank_seq    ());
            pstmt.setString(index++, shop.getInsert_id   ());
            pstmt.setString(index++, shop.getModify_id   ());
                                            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( shop.getShop_code    ()); logString.append( "/" );
            logString.append( shop.getShop_name    ()); logString.append( "/" );
            logString.append( shop.getWh_code      ()); logString.append( "/" );
            logString.append( shop.getShop_cust_no ()); logString.append( "/" );
            logString.append( shop.getPost_no      ()); logString.append( "/" );
            logString.append( shop.getPost_seq     ()); logString.append( "/" );
            logString.append( shop.getAddr         ()); logString.append( "/" );
            logString.append( shop.getOrder_media  ()); logString.append( "/" );
            logString.append( shop.getUser_gb      ()); logString.append( "/" );
            logString.append( shop.getDely_gb      ()); logString.append( "/" );
            logString.append( shop.getReceiver_gb  ()); logString.append( "/" );
            logString.append( shop.getBank_code    ()); logString.append( "/" );
            logString.append( shop.getBank_seq     ()); logString.append( "/" );
            logString.append( shop.getInsert_id    ()); logString.append( "/" );
            logString.append( shop.getModify_id    ()); logString.append( "/" );              
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int update(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(shop));
            int index = 1;
            pstmt.setString(index++, shop.getShop_code   ());
            pstmt.setString(index++, shop.getShop_name   ());
            pstmt.setString(index++, shop.getWh_code     ());
            pstmt.setString(index++, shop.getShop_cust_no());
            pstmt.setString(index++, shop.getPost_no     ());
            pstmt.setString(index++, shop.getPost_seq    ());
            pstmt.setString(index++, shop.getAddr        ());
            pstmt.setString(index++, shop.getOrder_media ());
            pstmt.setString(index++, shop.getUser_gb     ());
            pstmt.setString(index++, shop.getDely_gb     ());
            pstmt.setString(index++, shop.getReceiver_gb ());
            pstmt.setString(index++, shop.getBank_code   ());
            pstmt.setString(index++, shop.getBank_seq    ());
            pstmt.setString(index++, shop.getModify_id   ());
            pstmt.setString(index++, shop.getShop_code   ());
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( shop.getShop_code    ()); logString.append( "/" );
            logString.append( shop.getShop_name    ()); logString.append( "/" );
            logString.append( shop.getWh_code      ()); logString.append( "/" );
            logString.append( shop.getShop_cust_no ()); logString.append( "/" );
            logString.append( shop.getPost_no      ()); logString.append( "/" );
            logString.append( shop.getPost_seq     ()); logString.append( "/" );
            logString.append( shop.getAddr         ()); logString.append( "/" );
            logString.append( shop.getOrder_media  ()); logString.append( "/" );
            logString.append( shop.getUser_gb      ()); logString.append( "/" );
            logString.append( shop.getDely_gb      ()); logString.append( "/" );
            logString.append( shop.getReceiver_gb  ()); logString.append( "/" );
            logString.append( shop.getBank_code    ()); logString.append( "/" );
            logString.append( shop.getBank_seq     ()); logString.append( "/" );
            logString.append( shop.getModify_id    ()); logString.append( "/" );
            logString.append( shop.getShop_code    ()); logString.append( "/" );
            
            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ShopSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(shop));
            pstmt.setString(1, shop.getShop_code());
            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup1(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn1 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck1(shop));
            pstmt.setString(1, shop.getShop_name());
            rset  = pstmt.executeQuery();
            executedRtn1 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn1;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup2(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn2 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck2(shop));
            pstmt.setString(1, shop.getWh_code());
            rset  = pstmt.executeQuery();
            executedRtn2 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn2;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup3(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn3 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck3(shop));
            pstmt.setString(1, shop.getShop_cust_no());
            rset  = pstmt.executeQuery();
            executedRtn3 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn3;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup4(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn4 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck4(shop));
            pstmt.setString(1, shop.getOrder_media());
            rset  = pstmt.executeQuery();
            executedRtn4 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn4;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup5(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn5 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck5(shop));
            pstmt.setString(1, shop.getUser_gb());
            rset  = pstmt.executeQuery();
            executedRtn5 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn5;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup6(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn6 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck6(shop));
            pstmt.setString(1, shop.getDely_gb());
            rset  = pstmt.executeQuery();
            executedRtn6 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn6;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup7(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn7 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck7(shop));
            pstmt.setString(1, shop.getReceiver_gb());
            rset  = pstmt.executeQuery();
            executedRtn7 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn7;
    }
    
//  = Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tshop
    * </PRE>
    * @param   Connection
    * @param   Tshop
    * @return  int
    */
    public int checkDup8(Connection con, Tshop shop) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn8 = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck8(shop));
            pstmt.setString(1, shop.getBank_code());
            rset  = pstmt.executeQuery();
            executedRtn8 = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[ShopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ShopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn8;
    }
}
