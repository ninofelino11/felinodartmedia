
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
import com.cware.back.entity.table.Tgoodslink;


/**
 * 상품내역조회 Service class
 *
 * @version 1.0, 2006/06/30
 */
public class LinkGoodsSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public LinkGoodsSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.LINK_GB,                           \n");
        sb.append("          A.GOODS_CODE ,                       \n");
        sb.append("          B.GOODS_NAME AS GOODS_NAME,          \n");
        sb.append("          A.LINK_GOODS_CODE ,                  \n");
        sb.append("          C.GOODS_NAME AS LINK_GOODS_NAME,     \n");
        sb.append("          A.LINK_NOTE,                         \n");
        sb.append("          A.INSERT_DATE,                       \n");
        sb.append("          A.INSERT_ID,                         \n");
        sb.append("          A.MODIFY_DATE,                       \n");
        sb.append("          A.MODIFY_ID,                         \n");
        sb.append(" 		 A.DISPLAY_PRIORITY                   \n");
        sb.append("     FROM TGOODSLINK A,                        \n");
        sb.append("          TGOODS B,                            \n");
        sb.append("          TGOODS C                             \n");
        sb.append("    WHERE A.GOODS_CODE = B.GOODS_CODE          \n");
        sb.append("      AND A.LINK_GOODS_CODE = C.GOODS_CODE     \n");
        sb.append("      AND A.LINK_GB LIKE ? || '%'              \n");
        sb.append("      AND A.GOODS_CODE LIKE ? || '%'           \n");
        sb.append("      AND B.LGROUP LIKE ? || '%'               \n");
        sb.append("      AND B.MGROUP LIKE ? || '%'               \n");
        sb.append("      AND B.SGROUP LIKE ? || '%'               \n");
        sb.append("      AND B.DGROUP LIKE ? || '%'               \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug(retrieve);
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

            int index = 1;

            pstmt.setString(index++,retrieve.getString("link_gb"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("lgroup"));
            pstmt.setString(index++,retrieve.getString("mgroup"));
            pstmt.setString(index++,retrieve.getString("sgroup"));
            pstmt.setString(index++,retrieve.getString("dgroup"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tgoodslink )
     * </PRE>
     * @param   Tgoodslink
     * @return  String
     */
     private final String insertSql = " INSERT INTO TGOODSLINK ( \n "
                                     +"         LINK_GB, \n "
                                     +"         GOODS_CODE, \n "
                                     +"         LINK_GOODS_CODE, \n "
                                     +"         LINK_NOTE, \n "
                                     +"         DISPLAY_PRIORITY, \n"
                                     +"         INSERT_DATE, \n"
                                     +"         INSERT_ID, \n"
                                     +"         MODIFY_DATE, \n"
                                     +"         MODIFY_ID ) \n "
                                     +" VALUES( \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         SYSDATE, \n "
                                     +"         ?, \n "
                                     +"         SYSDATE, \n "
                                     +"         ? ) \n ";
     private int insertSqlLog =  0;

     public String makeSqlInsert(Tgoodslink Tgoodslink) throws StoreException{
         //= log SQL -------------------------------------------------
         if (insertSqlLog == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(insertSql);
             }
             insertSqlLog = 1;
         }
         return insertSql;
     }

     //= Insert -------------------------------------------------
     /**
     * <PRE>
     * Desc : Insert Tgoodslink
     * </PRE>
     * @param   Connection
     * @param   Tgoodslink
     * @return  int
     */
     public int insert(Connection con, Tgoodslink Tgoodslink) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
             pstmt = con.prepareStatement(makeSqlInsert(Tgoodslink));
             int index = 1;
             pstmt.setString(index++,Tgoodslink.getLink_gb());
             pstmt.setString(index++,Tgoodslink.getGoods_code());
             pstmt.setString(index++,Tgoodslink.getLink_goods_code());
             pstmt.setString(index++,Tgoodslink.getLink_note());
             pstmt.setString(index++,Tgoodslink.getDisplay_priority());
             pstmt.setString(index++,Tgoodslink.getInsert_id());
             pstmt.setString(index++,Tgoodslink.getModify_id());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( Tgoodslink.getLink_gb());           logString.append( "/" );
             logString.append( Tgoodslink.getGoods_code());        logString.append( "/" );
             logString.append( Tgoodslink.getLink_goods_code()  ); logString.append( "/" );
             logString.append( Tgoodslink.getLink_note() );        logString.append( "/" );
             logString.append( Tgoodslink.getDisplay_priority() ); logString.append( "/" );
             logString.append( Tgoodslink.getInsert_id()     );    logString.append( "/" );
             logString.append( Tgoodslink.getModify_id()    );     logString.append( "/" );

             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return executedRtn;
     }

     /**
      * <PRE>
      * Desc : Make SQL ( update Tgoodslink )
      * </PRE>
      * @param   Tgoodslink
      * @return  String
      */
     private final String updateSql = " UPDATE TGOODSLINK  \n "
                                    + "   SET LINK_NOTE         = ?,  \n "
                                    + "       DISPLAY_PRIORITY  = ?,  \n "
                                    + "       MODIFY_DATE       = SYSDATE,  \n "
                                    + "       MODIFY_ID         = ?  \n "
                                    + " WHERE LINK_GB           = ?  \n "
                                    + "   AND GOODS_CODE        = ?  \n "
                                    + "   AND LINK_GOODS_CODE   = ?  \n ";
      private int updateSqlLog =  0;

      public String makeSqlUpdate(Tgoodslink Tgoodslink) throws StoreException{
          //= log SQL -------------------------------------------------
          if (updateSqlLog == 0) {
              if (logSave.isDebugEnabled()) {
                  logSave.debug(updateSql);
              }
              updateSqlLog = 1;
          }
          return updateSql;
      }

      //= Insert -------------------------------------------------
      /**
      * <PRE>
      * Desc : Insert Tgoodslink
      * </PRE>
      * @param   Connection
      * @param   Tgoodslink
      * @return  int
      */
      public int update(Connection con, Tgoodslink Tgoodslink) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;
          int executedRtn = 0;

          try {
              pstmt = con.prepareStatement(makeSqlUpdate(Tgoodslink));
              int index = 1;
              pstmt.setString(index++,Tgoodslink.getLink_note());
              pstmt.setString(index++,Tgoodslink.getDisplay_priority());
              pstmt.setString(index++,Tgoodslink.getModify_id());
              pstmt.setString(index++,Tgoodslink.getLink_gb());
              pstmt.setString(index++,Tgoodslink.getGoods_code());
              pstmt.setString(index++,Tgoodslink.getLink_goods_code());

              //= log Save Data ---------------------
              StringBuffer logString = new StringBuffer();
              logString.append( Tgoodslink.getLink_note() );        logString.append( "/" );
              logString.append( Tgoodslink.getDisplay_priority() ); logString.append( "/" );
              logString.append( Tgoodslink.getModify_id()    );     logString.append( "/" );
              logString.append( Tgoodslink.getLink_gb());           logString.append( "/" );
              logString.append( Tgoodslink.getGoods_code());        logString.append( "/" );
              logString.append( Tgoodslink.getLink_goods_code()  ); logString.append( "/" );

              logSave.info(logString.toString());

              executedRtn = pstmt.executeUpdate();

          }catch(SQLException se){
              logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              logSave.error("Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return executedRtn;
      }


      /**
       * <PRE>
       * Desc : Make SQL ( update Tgoodslink )
       * </PRE>
       * @param   Tgoodslink
       * @return  String
       */
      private final String deleteSql = " DELETE TGOODSLINK  \n "
                                     + " WHERE LINK_GB           = ?  \n "
                                     + "   AND GOODS_CODE        = ?  \n "
                                     + "   AND LINK_GOODS_CODE   = ?  \n ";
       private int deleteSqlLog =  0;

       public String makeSqlDelete(Tgoodslink Tgoodslink) throws StoreException{
           //= log SQL -------------------------------------------------
           if (deleteSqlLog == 0) {
               if (logSave.isDebugEnabled()) {
                   logSave.debug(deleteSql);
               }
               deleteSqlLog = 1;
           }
           return deleteSql;
       }

       //= Insert -------------------------------------------------
       /**
       * <PRE>
       * Desc : Insert Tgoodslink
       * </PRE>
       * @param   Connection
       * @param   Tgoodslink
       * @return  int
       */
       public int delete(Connection con, Tgoodslink Tgoodslink) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;
           int executedRtn = 0;

           try {
               pstmt = con.prepareStatement(makeSqlDelete(Tgoodslink));
               int index = 1;
               pstmt.setString(index++,Tgoodslink.getLink_gb());
               pstmt.setString(index++,Tgoodslink.getGoods_code());
               pstmt.setString(index++,Tgoodslink.getLink_goods_code());

               //= log Save Data ---------------------
               StringBuffer logString = new StringBuffer();
               logString.append( Tgoodslink.getLink_gb());           logString.append( "/" );
               logString.append( Tgoodslink.getGoods_code());        logString.append( "/" );
               logString.append( Tgoodslink.getLink_goods_code()  ); logString.append( "/" );

               logSave.info(logString.toString());

               executedRtn = pstmt.executeUpdate();

           }catch(SQLException se){
               logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
               throw new StoreException(se.getMessage());
           }catch(Exception e){
               logSave.error("Exception : ERR-"+e.getMessage());
               throw new StoreException(e.getMessage());
           }finally {
               DBUtils.freeConnection(null, pstmt, rset);
           }
           return executedRtn;
       }

       /**
        * <PRE>
        * Desc : Make SQL ( 중복 체크 )
        * </PRE>
        * @param   Tgoodslink
        * @return  String
        */
        public String makeSqlDupCheck(Tgoodslink tgoodslink) throws StoreException{

            StringBuffer sb = new StringBuffer();

            sb.append("  SELECT COUNT(*) AS CNT 		\n");
            sb.append("    FROM TGOODSLINK A  			\n");
            sb.append("   WHERE A.GOODS_CODE = ?  		\n");
            sb.append("     AND A.LINK_GOODS_CODE = ?  	\n");
            sb.append("     AND A.LINK_GB = ?  			\n");

            //= log SQL -------------------------------------------------
            if (logSave.isDebugEnabled()) {
                logSave.debug(sb.toString());
            }

            return sb.toString();
        }

       //= Check Dup -------------------------------------------------
       /**
       * <PRE>
       * Desc : Check Duplication of Tdescribecode
       * </PRE>
       * @param   Connection
       * @param   Tgoodslink
       * @return  int
       */
       public int checkDup(Connection con, Tgoodslink tgoodslink) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;
           int executedRtn = 0;

           try {
               pstmt = con.prepareStatement(makeSqlDupCheck(tgoodslink));
               int index = 1;
               pstmt.setString(index++,  tgoodslink.getGoods_code());
               pstmt.setString(index++,  tgoodslink.getLink_goods_code());
               pstmt.setString(index++,  tgoodslink.getLink_gb());
               rset  = pstmt.executeQuery();
               executedRtn = DBUtils.executeQueryI(rset, "");

           }catch(SQLException se){
               logSave.error("[WhCodeSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
               throw new StoreException(se.getMessage());
           }catch(Exception e){
               logSave.error("[WhCodeSvc.insert() Exception : ERR-"+e.getMessage());
               throw new StoreException(e.getMessage());
           }finally {
               DBUtils.freeConnection(null, pstmt, rset);
           }
           return executedRtn;
       }


}
