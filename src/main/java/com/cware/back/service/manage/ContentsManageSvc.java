
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
import com.cware.back.entity.table.Tboard;


/**
 * Register notice Service class
 *
 * @version 1.0, 2006/07/14
 */
public class ContentsManageSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ContentsManageSvc() {}

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
        
        sb.append("  SELECT BOARD_SEQ,    \n ");
        sb.append("         BOARD_TITLE,    \n ");
        sb.append("         BOARD_SHORT_CONTENT,    \n ");
        sb.append("         BOARD_CONTENT,    \n ");
        sb.append("         SEARCH_CNT,    \n ");
        sb.append("         RECO_CNT,    \n ");
        sb.append("         BOARD_REF,    \n ");
        sb.append("         BOARD_STEP,    \n ");
        sb.append("         BOARD_LEVEL,    \n ");
        sb.append("         IP_ADDR,    \n ");
        sb.append("         CUST_NO,    \n ");
        sb.append("         BOARD_LKINDS,    \n ");
        sb.append("         BOARD_MKINDS,    \n ");
        sb.append("         EMAIL_ADDR,    \n ");
        sb.append("         EMAIL_RET_YN,    \n ");
        sb.append("         REPLY_YN,    \n ");
        sb.append("         STATE_FLAG,    \n ");
        sb.append("         SIGN_YN,    \n ");
        sb.append("         TO_CHAR(SIGN_DATE, 'YYYY/MM/DD') AS SIGN_DATE,    \n ");
        sb.append("         SIGN_ID,    \n ");
        sb.append("         DELETE_YN,    \n ");
        sb.append("         TO_CHAR(INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,    \n ");
        sb.append("         INSERT_ID,    \n ");
        sb.append("         TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD') AS MODIFY_DATE,    \n ");
        sb.append("         MODIFY_ID    \n ");
        sb.append("    FROM TBOARD    \n ");
        sb.append("   WHERE INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')  \n");
        sb.append("     AND INSERT_DATE <  TO_DATE(?, 'YYYY/MM/DD') + 1  \n");
        sb.append("     AND BOARD_LKINDS   LIKE ? || '%'  \n");
        
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("fromDate     : " + retrieve.getString("fromDate"));
            log.debug("toDate       : " + retrieve.getString("toDate"));
            log.debug("board_lkinds : " + retrieve.getString("board_lkinds"));
        }

        return sb.toString();
    }
    
    
    /**
    * <PRE>
    * Desc : Make SQL ( Tboardcontent 에 등록 )
    * </PRE>
    * @param   Tboard
    * @return  String
    */
    public String makeSqlInsert(Tboard board) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TBOARD (   \n ");
        sb.append("              BOARD_SEQ,   \n ");
        sb.append("              BOARD_LKINDS,   \n ");
        sb.append("              DELETE_YN,   \n ");
        sb.append("              BOARD_TITLE,   \n ");
        sb.append("              BOARD_SHORT_CONTENT,   \n ");
        sb.append("              BOARD_CONTENT,   \n ");
        sb.append("              SIGN_DATE,   \n ");
        sb.append("              SIGN_ID,   \n ");
        sb.append("              INSERT_DATE,   \n ");
        sb.append("              INSERT_ID,   \n ");
        sb.append("              MODIFY_DATE,   \n ");
        sb.append("              MODIFY_ID )   \n ");
        sb.append("      VALUES (   \n ");
        sb.append("              (SELECT TO_CHAR(NVL(MAX(TO_NUMBER(BOARD_SEQ)), 0) + 1 ) FROM TBOARD),   \n ");
        sb.append("              ?,   \n ");
        sb.append("              ?,   \n ");
        sb.append("              ?,   \n ");
        sb.append("              ?,   \n ");
        sb.append("              ?,   \n ");
        sb.append("              SYSDATE,   \n ");
        sb.append("              '1',   \n ");
        sb.append("              SYSDATE,   \n ");
        sb.append("              ?,   \n ");
        sb.append("              SYSDATE,   \n ");
        sb.append("              ? )   \n ");

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
    public String makeSqlUpdate(Tboard board) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TBOARD SET \n ");
        sb.append("         BOARD_LKINDS        = ?, \n ");
        sb.append("         DELETE_YN           = ?, \n ");
        sb.append("         BOARD_TITLE         = ?, \n ");
        sb.append("         BOARD_SHORT_CONTENT = ?, \n ");
        sb.append("         BOARD_CONTENT       = ?, \n ");
        sb.append("         MODIFY_DATE         = SYSDATE, \n ");
        sb.append("         MODIFY_ID           = ?  \n ");
        sb.append("   WHERE BOARD_SEQ           = ?  \n ");

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
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));            
            pstmt.setString(index++,retrieve.getString("board_lkinds"));

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
    
    
    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TBOARD
    * </PRE>
    * @param   Connection
    * @param   Tboard
    * @return  int
    */
    public int insert(Connection con, Tboard board) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(board));
            int index = 1;
            
            pstmt.setString(index++,board.getBoard_lkinds());
            pstmt.setString(index++,board.getDelete_yn());
            pstmt.setString(index++,board.getBoard_title());
            pstmt.setString(index++,board.getBoard_short_content());
            pstmt.setString(index++,board.getBoard_content());
            pstmt.setString(index++,board.getInsert_id());
            pstmt.setString(index++,board.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( board.getBoard_seq());            logString.append( "/" );
            logString.append( board.getBoard_lkinds());         logString.append( "/" );
            logString.append( board.getDelete_yn());            logString.append( "/" );
            logString.append( board.getBoard_title());          logString.append( "/" );
            logString.append( board.getBoard_short_content());  logString.append( "/" );
            logString.append( board.getBoard_content());        logString.append( "/" );
            logString.append( board.getInsert_id());            logString.append( "/" );
            logString.append( board.getModify_id());            logString.append( "/" );

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
    * @param   Tboard
    * @return  int
    */
    public int update(Connection con, Tboard board) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(board));
            int index = 1;
            
            pstmt.setString(index++,board.getBoard_lkinds());
            pstmt.setString(index++,board.getDelete_yn());
            pstmt.setString(index++,board.getBoard_title());
            pstmt.setString(index++,board.getBoard_short_content());
            pstmt.setString(index++,board.getBoard_content());
            pstmt.setString(index++,board.getModify_id());
            pstmt.setLong  (index++,board.getBoard_seq());
            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( board.getBoard_lkinds());         logString.append( "/" );
            logString.append( board.getDelete_yn());            logString.append( "/" );
            logString.append( board.getBoard_title());          logString.append( "/" );
            logString.append( board.getBoard_short_content());  logString.append( "/" );
            logString.append( board.getBoard_content());        logString.append( "/" );
            logString.append( board.getModify_id());            logString.append( "/" );
            logString.append( board.getBoard_seq());            logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
