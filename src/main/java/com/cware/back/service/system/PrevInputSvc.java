
package com.cware.back.service.system;

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
import com.cware.back.entity.table.Tprev;

/**
 * Prev Service class
 * inner notice (for staff)
 *
 * @version 1.0, 2006/06/21
 */
public class PrevInputSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PrevInputSvc() {}

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

        sb.append("  SELECT PREV_NO,  \n ");
        sb.append("         PREV_GB,  \n ");
        sb.append("         TO_CHAR(PREV_BDATE, 'YYYY/MM/DD HH24:MI') AS PREV_BDATE,  \n ");
        sb.append("         TO_CHAR(PREV_EDATE, 'YYYY/MM/DD HH24:MI') AS PREV_EDATE,  \n ");
        sb.append("         PREV_TITLE,  \n ");
        sb.append("         DISPLAY_YN,  \n ");
        sb.append("         PREV_NOTE,  \n ");
        sb.append("         TO_CHAR(INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,  \n ");
        sb.append("         INSERT_ID,  \n ");
        sb.append("         TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD') AS MODIFY_DATE,  \n ");
        sb.append("         MODIFY_ID  \n ");
        sb.append("    FROM TPREV  \n ");
        sb.append("   WHERE PREV_GB LIKE ? || '%'  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("prev_gb"));
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

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL for main
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlMain( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT PREV_NO,  \n ");
        sb.append("         PREV_GB,  \n ");
        sb.append("         TO_CHAR(PREV_BDATE, 'YYYY/MM/DD') AS PREV_BDATE,  \n ");
        sb.append("         TO_CHAR(PREV_EDATE, 'YYYY/MM/DD') AS PREV_EDATE,  \n ");
        sb.append("         PREV_TITLE,  \n ");
        sb.append("         DISPLAY_YN,  \n ");
        sb.append("         PREV_NOTE,  \n ");
        sb.append("         TO_CHAR(INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,  \n ");
        sb.append("         INSERT_ID,  \n ");
        sb.append("         TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD') AS MODIFY_DATE,  \n ");
        sb.append("         MODIFY_ID  \n ");
        sb.append("    FROM TPREV  \n ");
        sb.append("   WHERE PREV_GB LIKE ? || '%'  \n ");
        sb.append("     AND DISPLAY_YN = '1' \n ");
        sb.append("     AND PREV_BDATE <= SYSDATE  \n ");
        sb.append("     AND PREV_EDATE >= SYSDATE  \n ");
        sb.append("   ORDER BY PREV_NO DESC \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    
    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL for main
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveMain(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlMain(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("prev_gb"));
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
    * Desc : Make SQL ( Tpreve insert )
    * </PRE>
    * @param   Tprev
    * @return  String
    */
    public String makeSqlInsert(Tprev tprev) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TPREV (  \n ");
        sb.append("         PREV_NO,  \n ");
        sb.append("         PREV_GB,  \n ");
        sb.append("         PREV_BDATE,  \n ");
        sb.append("         PREV_EDATE,  \n ");
        sb.append("         PREV_TITLE,  \n ");
        sb.append("         PREV_NOTE,  \n ");
        sb.append("         DISPLAY_YN,  \n ");
        sb.append("         INSERT_ID,  \n ");
        sb.append("         INSERT_DATE,  \n ");
        sb.append("         MODIFY_ID,  \n ");
        sb.append("         MODIFY_DATE)  \n ");
        sb.append("  VALUES (  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         SYSDATE,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         SYSDATE )  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tprev
    * </PRE>
    * @param   Connection
    * @param   Tprev
    * @return  int
    */
    public int insert(Connection con, Tprev tprev) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tprev));
            int index = 1;

            pstmt.setString(index++,tprev.getPrev_no()        );
            pstmt.setString(index++,tprev.getPrev_gb()        );
            pstmt.setString(index++,tprev.getPrev_bdate()     );
            pstmt.setString(index++,tprev.getPrev_edate()     );
            pstmt.setString(index++,tprev.getPrev_title()     );
            pstmt.setString(index++,tprev.getPrev_note()      );
            pstmt.setString(index++,tprev.getDisplay_yn()     );
            pstmt.setString(index++,tprev.getInsert_id()      );
            pstmt.setString(index++,tprev.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tprev.getPrev_no()        ); logString.append( "/" );
            logString.append( tprev.getPrev_gb()        ); logString.append( "/" );
            logString.append( tprev.getPrev_bdate()     ); logString.append( "/" );
            logString.append( tprev.getPrev_edate()     ); logString.append( "/" );
            logString.append( tprev.getPrev_title()     ); logString.append( "/" );
            logString.append( tprev.getPrev_note()      ); logString.append( "/" );
            logString.append( tprev.getDisplay_yn()     ); logString.append( "/" );
            logString.append( tprev.getInsert_id()      ); logString.append( "/" );
            logString.append( tprev.getModify_id()      ); logString.append( "/" );
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
    * Desc : Make SQL ( Tprev update )
    * </PRE>
    * @param   Tprev
    * @return  String
    */
    public String makeSqlUpdate(Tprev tprev) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TPREV   \n ");
        sb.append("     SET PREV_GB     = ?,  \n ");
        sb.append("         PREV_BDATE  = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         PREV_EDATE  = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         PREV_TITLE  = ?,  \n ");
        sb.append("         PREV_NOTE   = ?,  \n ");
        sb.append("         DISPLAY_YN  = ?,  \n ");
        sb.append("         MODIFY_ID   = ?,  \n ");
        sb.append("         MODIFY_DATE = SYSDATE  \n ");
        sb.append("   WHERE PREV_NO     = ?  \n ");
            
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tprev
    * </PRE>
    * @param   Connection
    * @param   Tprev
    * @return  int
    */
    public int update(Connection con, Tprev tprev) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tprev));
            int index = 1;
            pstmt.setString(index++,tprev.getPrev_gb()        );
            pstmt.setString(index++,tprev.getPrev_bdate()     );
            pstmt.setString(index++,tprev.getPrev_edate()     );
            pstmt.setString(index++,tprev.getPrev_title()     );
            pstmt.setString(index++,tprev.getPrev_note()      );
            pstmt.setString(index++,tprev.getDisplay_yn()     );
            pstmt.setString(index++,tprev.getModify_id()      );
            pstmt.setString(index++,tprev.getPrev_no()        );
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tprev.getPrev_gb()        ); logString.append( "/" );
            logString.append( tprev.getPrev_bdate()     ); logString.append( "/" );
            logString.append( tprev.getPrev_edate()     ); logString.append( "/" );
            logString.append( tprev.getPrev_title()     ); logString.append( "/" );
            logString.append( tprev.getPrev_note()      ); logString.append( "/" );
            logString.append( tprev.getDisplay_yn()     ); logString.append( "/" );
            logString.append( tprev.getModify_id()      ); logString.append( "/" );
            logString.append( tprev.getPrev_no()        ); logString.append( "/" );
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