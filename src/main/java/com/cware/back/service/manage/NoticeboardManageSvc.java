
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
import com.cware.back.entity.table.Tboardcontent;


/**
 * Register notice Service class
 *
 * @version 1.0, 2006/07/14
 */
public class NoticeboardManageSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public NoticeboardManageSvc() {}

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

        sb.append("  SELECT A.BOARD_NO,                                 \n ");
        sb.append("         A.BOARD_CODE,                               \n ");
        sb.append("         A.BOARD_TITLE,                              \n ");
        sb.append("         A.BOARD_CONTENT,                            \n ");
        sb.append("         NVL(A.SEARCH_CNT,0)  SEARCH_CNT,            \n ");
        sb.append("         A.DELETE_YN,                                \n ");
        sb.append("         A.INSERT_DATE,                              \n ");
        sb.append("         A.INSERT_ID,                                \n ");
        sb.append("         A.MODIFY_DATE,                              \n ");
        sb.append("         A.MODIFY_ID,                                \n ");
        sb.append("         B.BOARD_DESC,                               \n ");
        sb.append("         A.TOPDISPLAY_YN,                            \n ");
        sb.append("         TO_CHAR(A.DISPLAY_START_DATE,'yyyy/mm/dd hh24:mi:ss') AS DISPLAY_START_DATE, \n ");
        sb.append("         TO_CHAR(A.DISPLAY_END_DATE, 'yyyy/mm/dd hh24:mi:ss' ) AS DISPLAY_END_DATE, \n ");
        sb.append("         A.TARGET_URL,                               \n ");
        sb.append("         A.BOARD_KINDS,                              \n ");
        sb.append("         B.BOARD_DESC                                \n ");
        sb.append("    FROM TBOARDCONTENT A, TBOARDINFO B               \n ");
        sb.append("   WHERE A.BOARD_CODE = B.BOARD_CODE                 \n ");
        sb.append("     AND A.BOARD_CODE LIKE ? || '%'                  \n ");
        sb.append("     AND A.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')   \n ");
        sb.append("     AND A.INSERT_DATE <  TO_DATE(?, 'YYYY/MM/DD') + 1 \n ");
        sb.append("   ORDER BY LENGTH(BOARD_NO) DESC, BOARD_NO DESC 	\n ");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("board_code : " + retrieve.getString("board_code"));
            log.debug("fromDate   : " + retrieve.getString("fromDate"));
            log.debug("toDate     : " + retrieve.getString("toDate"));
        }

        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL (HEAD의 DEFAULT board_code, board_desc)
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlBoardDesc( String board_code ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT BOARD_DESC     \n ");
        sb.append("  FROM TBOARDINFO     \n ");
        sb.append(" WHERE BOARD_CODE = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("board_code : " + board_code);
        }

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Tboardcontent 에 등록 )
    * </PRE>
    * @param   Tboardcontent
    * @return  String
    */
    public String makeSqlInsert(Tboardcontent content) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TBOARDCONTENT ( \n ");
        sb.append("          BOARD_NO,           \n ");
        sb.append("          BOARD_CODE,         \n ");
        sb.append("          BOARD_TITLE,        \n ");
        sb.append("          BOARD_CONTENT,      \n ");
        sb.append("          DELETE_YN,          \n ");
        sb.append("          INSERT_DATE,        \n ");
        sb.append("          INSERT_ID,          \n ");
        sb.append("          MODIFY_DATE,        \n ");
        sb.append("          MODIFY_ID,          \n ");
        sb.append("          BOARD_KINDS,        \n ");
        sb.append("          TOPDISPLAY_YN,      \n ");
        sb.append("          DISPLAY_START_DATE, \n ");
        sb.append("          DISPLAY_END_DATE,   \n ");
        sb.append("          TARGET_URL)         \n ");
        sb.append("  VALUES (                    \n ");
        sb.append("          (SELECT TO_CHAR(NVL(MAX(TO_NUMBER(BOARD_NO)), 0) + 1 ) FROM TBOARDCONTENT), \n ");
        sb.append("          ?,                  \n ");
        sb.append("          ?,                  \n ");
        sb.append("          ?,                  \n ");
        sb.append("          '0',                \n ");
        sb.append("          SYSDATE,            \n ");
        sb.append("          ?,                  \n ");
        sb.append("          SYSDATE,            \n ");
        sb.append("          ?,                  \n ");
        sb.append("          ?,                  \n ");
        sb.append("          '1',                \n ");
        sb.append("          TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),   \n ");
        sb.append("          TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),   \n ");
        sb.append("          ? )                 \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug("\n" + sb.toString());
        }

        return sb.toString();
    }






    /**
     * <PRE>
     * Desc : Make SQL ( Tboardcontent 에 등록 )
     * </PRE>
     * @param   Tboardcontent
     * @return  String
     */
     public String makeSqlInsertTboardcontentd(Tboardcontent content) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("INSERT INTO TBOARDCONTENTD ( \n");
         sb.append("                 BOARD_NO,   \n");
         sb.append("            CATEGORY_CODE,   \n");
         sb.append("              INSERT_DATE,   \n");
         sb.append("                INSERT_ID )  \n");
         sb.append("      VALUES (               \n");
         sb.append("               (SELECT TO_CHAR(NVL(MAX(TO_NUMBER(BOARD_NO)), 0) + 1 ) FROM TBOARDCONTENTD), \n ");
         sb.append("                0,           \n");
         sb.append("                SYSDATE,     \n");
         sb.append("                ?            \n");
         sb.append("             )               \n");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug("\n" + sb.toString());
         }

         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : Make SQL ( Tboardcontent 변경 )
    * </PRE>
    * @param   Tboardcontent
    * @return  String
    */
    public String makeSqlUpdate(Tboardcontent content) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TBOARDCONTENT SET \n ");
        sb.append("         BOARD_TITLE         = ?, \n ");
        sb.append("         BOARD_CONTENT       = ?, \n ");
        sb.append("         DELETE_YN           = ?, \n ");
        sb.append("         MODIFY_DATE         = SYSDATE, \n ");
        sb.append("         MODIFY_ID           = ?, \n ");
        sb.append("         BOARD_KINDS         = ?, \n ");
        sb.append("         TOPDISPLAY_YN       = '1', \n ");
        sb.append("         DISPLAY_START_DATE  = TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'), \n ");
        sb.append("         DISPLAY_END_DATE    = TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'), \n ");
        sb.append("         TARGET_URL          = ?  \n ");
        sb.append("   WHERE BOARD_NO = ?     \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug("\n" + sb.toString());
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
            log.debug("NoticeboardManageSvc Retrieve Row : " + retRow);
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
            pstmt.setString(index++,retrieve.getString("board_code"));
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("NoticeboardManageSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("NoticeboardManageSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (HEAD의 DEFAULT board_code, board_desc)
    * </PRE>
    * @param   Connection
    * @param   String
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveBoardDesc(Connection con, String board_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        RetrieveModel retrieve = new RetrieveModel();

        try {

            pstmt = con.prepareStatement(makeSqlBoardDesc(board_code));
            int index = 1;
            pstmt.setString(index++,board_code);

            rset = pstmt.executeQuery();
            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("NoticeboardManageSvc.retrieveBoardDesc() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("NoticeboardManageSvc.retrieveBoardDesc() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TBOARDCONTENT
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int insert(Connection con, Tboardcontent content) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(content));
            int index = 1;

            pstmt.setString(index++,content.getBoard_code());
            pstmt.setString(index++,content.getBoard_title());
            pstmt.setString(index++,content.getBoard_content());
            pstmt.setString(index++,content.getInsert_id());
            pstmt.setString(index++,content.getModify_id());
            pstmt.setString(index++,content.getBoard_kinds());
            pstmt.setString(index++,content.getDisplay_start_date());
            pstmt.setString(index++,content.getDisplay_end_date());
            pstmt.setString(index++,content.getTarget_url());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( content.getBoard_code());         logString.append( "/" );
            logString.append( content.getBoard_title());        logString.append( "/" );
            logString.append( content.getBoard_content());      logString.append( "/" );
            logString.append( content.getInsert_id());          logString.append( "/" );
            logString.append( content.getModify_id());          logString.append( "/" );
            logString.append( content.getBoard_kinds());        logString.append( "/" );
            logString.append( content.getDisplay_start_date()); logString.append( "/" );
            logString.append( content.getDisplay_end_date());   logString.append( "/" );
            logString.append( content.getTarget_url());         logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("NoticeboardManageSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("NoticeboardManageSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TBOARDCONTENTD
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int insertTboardcontentd(Connection con, Tboardcontent content) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsertTboardcontentd(content));
            int index = 1;

            pstmt.setString(index++,content.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( content.getInsert_id());          logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("NoticeboardManageSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("NoticeboardManageSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }



    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TUSER
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int update(Connection con, Tboardcontent content) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(content));
            int index = 1;

            pstmt.setString(index++,content.getBoard_title());
            pstmt.setString(index++,content.getBoard_content());
            pstmt.setString(index++,content.getDelete_yn());
            pstmt.setString(index++,content.getModify_id());
            pstmt.setString(index++,content.getBoard_kinds());
          //  pstmt.setString(index++,content.getTopdisplay_yn());
            pstmt.setString(index++,content.getDisplay_start_date());
            pstmt.setString(index++,content.getDisplay_end_date());
            pstmt.setString(index++,content.getTarget_url());
            pstmt.setString(index++,content.getBoard_no());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( content.getBoard_title());        logString.append( "/" );
            logString.append( content.getBoard_content());      logString.append( "/" );
            logString.append( content.getDelete_yn());          logString.append( "/" );
            logString.append( content.getModify_id());          logString.append( "/" );
            logString.append( content.getBoard_kinds());        logString.append( "/" );
          //  logString.append( content.getTopdisplay_yn());      logString.append( "/" );
            logString.append( content.getDisplay_start_date()); logString.append( "/" );
            logString.append( content.getDisplay_end_date());   logString.append( "/" );
            logString.append( content.getTarget_url());         logString.append( "/" );
            logString.append( content.getBoard_no());           logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("NoticeboardManageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("NoticeboardManageSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
