
package com.cware.back.service.manage;

import java.io.StringReader;
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
import com.cware.back.entity.table.Tgoodscomment;
import com.cware.back.entity.table.Tsaveget;


/**
 * 상품평관리 Service class
 *
 * @version 1.0, 2006/06/21
 */
public class GoodsCritSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsCritSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.GOODS_CODE,          \n ");
        sb.append("         A.COMMENT_SEQ,         \n ");
        sb.append("         A.COMMENT_TITLE,       \n ");
        sb.append("         A.COMMENT_SCORE,       \n ");
        sb.append("         C.CUST_NO	,          \n ");
        sb.append("         A.DISPLAY_YN,          \n ");
        sb.append("         A.DISPLAY_PRIORITY,    \n ");
        sb.append("         A.DISPLAY_YN,          \n ");
        sb.append("         A.SAVEAMT_YN,          \n ");
        sb.append("         A.DELETE_YN,           \n ");
        sb.append("         A.SEARCH_CNT,          \n ");
        sb.append("         A.RECO_CNT,            \n ");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,         \n ");
        sb.append("         A.INSERT_ID,           \n ");
        sb.append("         A.MODIFY_DATE,         \n ");
        sb.append("         A.MODIFY_ID,           \n ");
        sb.append("         A.PROC_DATE,           \n ");
        sb.append("         A.PROC_ID,             \n ");
        sb.append("         A.COMMENT_SCORE1,      \n ");
        sb.append("         A.COMMENT_SCORE2,      \n ");
        sb.append("         A.COMMENT_SCORE3,      \n ");
        sb.append("         A.COMMENT_SCORE4,      \n ");
        sb.append("         A.COMMENT_SCORE5,      \n ");
        sb.append("         A.COMMENT_SCORE6,      \n ");
        sb.append("         A.COMMENT_SCORE7,      \n ");
        sb.append("         A.COMMENT_CONTENT,     \n ");
        sb.append("         A.SPECIAL_DISPLAY_YN,  \n ");
        sb.append("         B.GOODS_NAME,          \n ");
        sb.append("         (SELECT COUNT(*)       \n ");
        sb.append("            FROM TORDERDT D,    \n ");
        sb.append("                 TCUSTOMER E    \n ");
        sb.append("           WHERE A.INSERT_ID  =   E.MEM_ID              \n ");
        sb.append("             AND A.GOODS_CODE =   D.GOODS_CODE          \n ");
        sb.append("             AND E.CUST_NO    =   D.CUST_NO             \n ");
        sb.append("             AND ROWNUM       =   1)  AS SALE_YN        \n ");
        sb.append("     FROM TGOODSCOMMENT A,                              \n ");
        sb.append("          TGOODS B,                                      \n ");
        sb.append("          TCUSTOMER C                                   \n ");
        sb.append("    WHERE A.GOODS_CODE    =   B.GOODS_CODE(+)           \n ");
        sb.append("      AND A.INSERT_ID     =   C.MEM_ID			       \n ");
        sb.append("      AND A.INSERT_DATE   >= TO_DATE(?,'YYYY/MM/DD')    \n ");
        sb.append("      AND A.INSERT_DATE   <  TO_DATE(?,'YYYY/MM/DD') + 1   \n ");
        sb.append("      AND B.MD_CODE       LIKE    ? || '%'              \n ");
        sb.append("      AND A.GOODS_CODE    LIKE    ? || '%'              \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlTcode() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT REMARK1            							\n");
        sb.append(" FROM  TCODE    	           							\n");
        sb.append(" WHERE CODE_LGROUP=?                 				\n");
		sb.append("	AND   CODE_MGROUP=?	  								\n");
	         //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
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
										          + "    ( CUST_NO, \n"
										          + "      SAVEAMT_SEQ, \n"
										          + "      PROC_GB, \n"
										          + "      PROC_YN, \n"
										          + "      SAVEAMT_GB, \n"
										          + "      SAVEAMT_CODE, \n"
										          + "      SAVE_NOTE, \n"
										          + "      SAVEAMT, \n"
										          + "      PROC_ID, \n"
										          + "      PROC_DATE, \n"
		                                          + "      USABLE_AMT,   \n"
		                                          + "      EXPIRE_FLAG , \n"
										          + "      DUE_DATE )   \n "
										          + "VALUES   (?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n"
		                                          + "      ?,  \n "
		                                          + "      ?,  \n "
										          + "      TO_DATE(?, 'YYYY/MM/DD') + TCODE_GROUP('C026', ?) )   \n "  ;

    private int insertSqlTsavegetLog =  0;

    private String makeSqlInsert(Tsaveget tsaveget) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlTsavegetLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug("\n" + insertSqlTsaveget);
            }
            insertSqlTsavegetLog = 1;
        }
        return insertSqlTsaveget;
    }


    //= TCUSTSYSTEM -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Update Tcustsystem
    * </PRE>
    * @param   Tcustsystem
    * @return  String
    */

//    public String makeSqlUpdateSaveamt() throws Exception {
//        StringBuffer sb = new StringBuffer();
//        sb.append("UPDATE TCUSTSYSTEM         \n");
//        sb.append("   SET USABLE_SAVEAMT = NVL(USABLE_SAVEAMT,0) + ?,	          \n");
//        sb.append("       TOT_SAVEAMT    = NVL(TOT_SAVEAMT,0) + ?,	          \n");
//        sb.append("       MODIFY_DATE    = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n");
//        sb.append("       MODIFY_ID      = ?  \n");
//        sb.append(" WHERE CUST_NO        = ?  \n");
//
//        //= log SQL -------------------------------------------------
//        if(logSave.isDebugEnabled()){
//            logSave.debug("\n" + sb.toString());
//        }
//        return sb.toString();
//    }


    /**
    * <PRE>
    * Desc : Make SQL ( Tgoodscomment 변경 )
    * </PRE>
    * @param   Tgoodscomment
    * @return  String
    */
    public String makeSqlUpdate(Tgoodscomment tgoodscomment) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODSCOMMENT         \n ");
        sb.append("     SET DISPLAY_YN       = ?, \n ");
        sb.append("         DISPLAY_PRIORITY = ?, \n ");
        sb.append("         DELETE_YN        = ?, \n ");
        sb.append("         COMMENT_CONTENT  = ?, \n ");
        sb.append("         MODIFY_DATE      = SYSDATE,  \n ");
        sb.append("         MODIFY_ID        = ?,  \n ");
        sb.append("         SAVEAMT_YN       = ?,  \n ");
        sb.append("         SPECIAL_DISPLAY_YN  = ?  \n ");
        sb.append("   WHERE COMMENT_SEQ      = ?  \n ");

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
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));
            pstmt.setString(index++,retrieve.getString("md_code"));
            pstmt.setString(index++,retrieve.getString("goods_code"));

    //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("fromDate")        ); logString.append( "/" );
            logString.append( retrieve.getString("toDate")          ); logString.append( "/" );
            logString.append( retrieve.getString("md_code")         ); logString.append( "/" );
            logString.append( retrieve.getString("goods_code")      ); logString.append( "/" );
            log.info(logString.toString());

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

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

//  = Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public Double retrieveTcode(Connection con) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        double            remark1     =0;
        try {
        	pstmt = con.prepareStatement(makeSqlTcode());
            int index = 1;
            pstmt.setString(index++, "C026");
            pstmt.setString(index++, "501");
        	rset  = pstmt.executeQuery();
        	rset.next();
            remark1 = rset.getDouble(1);
            logSave.info(remark1);

        }catch(SQLException se){
        	 log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	 log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return remark1;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoodscomment
    * </PRE>
    * @param   Connection
    * @param   Tgoodscomment
    * @return  int
    */
    public int update(Connection con, Tgoodscomment tgoodscomment) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodscomment));
            int index = 1;
            pstmt.setString(index++,tgoodscomment.getDisplay_yn());
            pstmt.setString(index++,tgoodscomment.getDisplay_priority());
            pstmt.setString(index++,tgoodscomment.getDelete_yn());
            pstmt.setCharacterStream(index++, new StringReader(tgoodscomment.getComment_content()), tgoodscomment.getComment_content().length());

//            pstmt.setString(index++,tgoodscomment.getComment_content());
            pstmt.setString(index++,tgoodscomment.getModify_id());
            pstmt.setString(index++,tgoodscomment.getSaveamt_yn());
            pstmt.setString(index++,tgoodscomment.getSpecial_display_yn());
            pstmt.setString(index++,tgoodscomment.getComment_seq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodscomment.getDisplay_yn()      ); logString.append( "/" );
            logString.append( tgoodscomment.getDisplay_priority()); logString.append( "/" );
            logString.append( tgoodscomment.getDelete_yn()       ); logString.append( "/" );
            logString.append( tgoodscomment.getComment_content() ); logString.append( "/" );
            logString.append( tgoodscomment.getModify_id()       ); logString.append( "/" );
            logString.append( tgoodscomment.getSaveamt_yn()      ); logString.append( "/" );
            logString.append( tgoodscomment.getSpecial_display_yn() ); logString.append( "/" );
            logString.append( tgoodscomment.getComment_seq()     ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsCritSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsCritSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
     * <PRE>
     * Desc : Insert TSAVEGET
     * </PRE>
     * @param   Connection
     * @param   Tsaveget
     * @return  int
     */

     public int insert(Connection con, Tsaveget tsaveget) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
             pstmt = con.prepareStatement(makeSqlInsert(tsaveget));
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
             pstmt.setString(index++, tsaveget.getProc_date()     );
             pstmt.setDouble(index++, tsaveget.getUsable_amt()    );
             pstmt.setString(index++, tsaveget.getExpire_flag()   );
             pstmt.setString(index++, tsaveget.getDue_date()      );
             pstmt.setString(index++, tsaveget.getSaveamt_code()  );
//             pstmt.setLong  (index++, tsaveget.getLimit_day()   );

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
             logString.append( tsaveget.getProc_date()      ); logString.append( "/" );
             logString.append( tsaveget.getUsable_amt()     ); logString.append( "/" );
             logString.append( tsaveget.getExpire_flag()    ); logString.append( "/" );
             logString.append( tsaveget.getDue_date()       ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt_code()   ); logString.append( "/" );
//             logString.append( tsaveget.getLimit_day()    ); logString.append( "/" );

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
      * Desc : Update Tcustsystem
      * </PRE>
      * @param   Connection
      * @param   Tcustsystem
      * @return  int
      */

//      public int update(Connection con, Tcustsystem tcustsystem) throws StoreException{
//          PreparedStatement pstmt       = null;
//          ResultSet         rset        = null;
//          int executedRtn = 0;
//
//          try {
//
//              pstmt = con.prepareStatement(makeSqlUpdateSaveamt());
//              int index = 1;
//
//              pstmt.setDouble(index++, tcustsystem.getUsable_saveamt());
//              pstmt.setDouble(index++, tcustsystem.getTot_saveamt());
//              pstmt.setString(index++, tcustsystem.getModify_date()   );
//              pstmt.setString(index++, tcustsystem.getModify_id()     );
//              pstmt.setString(index++, tcustsystem.getCust_no()       );
//
//              //= log Save Data ---------------------
//              StringBuffer logString = new StringBuffer();
//              logString.append( tcustsystem.getUsable_saveamt() ); logString.append( "/" );
//              logString.append( tcustsystem.getTot_saveamt()    ); logString.append( "/" );
//              logString.append( tcustsystem.getModify_date()    ); logString.append( "/" );
//              logString.append( tcustsystem.getModify_id()      ); logString.append( "/" );
//              logString.append( tcustsystem.getCust_no()        ); logString.append( "/" );
//              logSave.info(logString.toString());
//
//              executedRtn = pstmt.executeUpdate();
//          }catch(SQLException se){
//              logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//              throw new StoreException(se.getMessage());
//          }catch(Exception e){
//              logSave.error("Exception : ERR-"+e.getMessage());
//              throw new StoreException(e.getMessage());
//          }finally {
//              DBUtils.freeConnection(null, pstmt, rset);
//          }
//          return executedRtn;
//      }

}