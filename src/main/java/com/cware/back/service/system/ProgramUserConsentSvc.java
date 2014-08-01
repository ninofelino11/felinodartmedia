
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
import com.cware.back.entity.table.Tsecperprogram;


/**
 * ProgramUserConsent Service class
 *
 * @version 1.0, 2007/10/27
 * @author commerceware.co.kr
 */
public class ProgramUserConsentSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ProgramUserConsentSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL (트리메뉴)
    * </PRE>
    * @param
    * @return  String
    */
    public String makeSql() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT B.DEPT,     \n");
        sb.append("          A.USER_GB,  \n");
        sb.append("          C.CODE_NAME       \n");
        sb.append("     FROM TUSER A,     \n");
        sb.append("          (SELECT '1' AS DEPT     \n");
        sb.append("             FROM TUSER) B,  \n");
        sb.append("          TCODE C  \n");
        sb.append("    WHERE A.USER_GB = C.CODE_MGROUP  \n");
        sb.append("      AND C.CODE_LGROUP = 'B040'          \n");
        sb.append("   GROUP BY B.DEPT,A.USER_GB,C.CODE_NAME        \n");
        sb.append("   ORDER BY 1,2 ASC     \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (트리메뉴)
    * </PRE>
    * @param   Connection
    * @param
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        RetrieveModel retrieve = new RetrieveModel();

        try {
            pstmt = con.prepareStatement(makeSql());

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
    * Desc : Make SQL (권한없는 사용자 리스트)
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlNoSecList(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("        SELECT A.USER_ID,           \n");
        sb.append("               A.USER_NAME,         \n");
        sb.append("               A.USER_GB,           \n");
        sb.append("               ? AS PROGRAM_ID      \n");
        sb.append("          FROM TUSER A              \n");
        sb.append("         WHERE A.USER_GB    = ?     \n");
        sb.append("        MINUS                       \n");
        sb.append("        SELECT A.USER_ID,           \n");
        sb.append("               A.USER_NAME,         \n");
        sb.append("               A.USER_GB,            \n");
        sb.append("               ? AS PROGRAM_ID      \n");
        sb.append("          FROM TUSER A, TSECPERPROGRAM B \n");
        sb.append("         WHERE A.USER_ID    = B.USER_ID  \n");
        sb.append("           AND A.USER_GB    = ?          \n");
        sb.append("           AND B.LMENU_ID   = ?          \n");
        sb.append("           AND B.MMENU_ID   = ?          \n");
        sb.append("           AND B.PROGRAM_ID = ?          \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (권한없는 사용자 리스트)
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveNoSecList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlNoSecList(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("program_id"));
            pstmt.setString(index++,retrieve.getString("user_gb"));
            pstmt.setString(index++,retrieve.getString("program_id"));
            pstmt.setString(index++,retrieve.getString("user_gb"));
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("program_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("user_gb")    ); logString.append( "/" );
            logString.append( retrieve.getString("user_gb")    ); logString.append( "/" );
            logString.append( retrieve.getString("lmenu_id")   ); logString.append( "/" );
            logString.append( retrieve.getString("mmenu_id")   ); logString.append( "/" );
            logString.append( retrieve.getString("program_id") ); logString.append( "/" );

            log.debug(logString.toString());


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
    * Desc : Make SQL (권한있는 사용자 리스트 )
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlSecList(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT B.LMENU_ID,      \n");
        sb.append("         B.MMENU_ID,      \n");
        sb.append("         B.PROGRAM_ID,    \n");
        sb.append("         A.USER_ID,       \n");
        sb.append("         A.USER_NAME,     \n");
        sb.append("         A.USER_GB        \n");
        sb.append("    FROM TUSER A, TSECPERPROGRAM B  \n");
        sb.append("   WHERE A.USER_ID = B.USER_ID      \n");
        sb.append("     AND A.USER_GB = ?    \n");
        sb.append("     AND B.PROGRAM_ID = ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (권한있는 사용자 리스트 )
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveSecList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSecList(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("user_gb"));
            pstmt.setString(index++,retrieve.getString("program_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("user_gb")  ); logString.append( "/" );
            logString.append( retrieve.getString("program_id") ); logString.append( "/" );
            log.debug(logString.toString());

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
    * Desc : Make SQL ( 특정 user_id가 특정 프로그램에 대해 권한있는지 체크 )
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlCheckSec(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(*) AS cnt \n");
        sb.append("   FROM TSECPERPROGRAM \n");
        sb.append("  WHERE USER_ID    = ? \n");
        sb.append("    AND LMENU_ID   = ? \n");
        sb.append("    AND MMENU_ID   = ? \n");
        sb.append("    AND PROGRAM_ID = ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ( 특정 user_id가 특정 프로그램에 대해 권한있는지 체크 )
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  String
    */
    public RetrieveModel checkSec(Connection con, Tsecperprogram tsecperprogram) throws StoreException{
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        RetrieveModel retrieve = new RetrieveModel();

        try {
            pstmt = con.prepareStatement(makeSqlCheckSec(retrieve));

            int index = 1;
            pstmt.setString(index++,tsecperprogram.getUser_id());
            pstmt.setString(index++,tsecperprogram.getLmenu_id());
            pstmt.setString(index++,tsecperprogram.getMmenu_id());
            pstmt.setString(index++,tsecperprogram.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecperprogram.getUser_id()    ); logString.append( "/" );
            logString.append( tsecperprogram.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getProgram_id() ); logString.append( "/" );
            log.debug(logString.toString());

            rset   = pstmt.executeQuery();

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
    * Desc : Make SQL ( Insert Tsecperprogram )
    * </PRE>
    * @param   Tsecperprogram
    * @return  String
    */

    private final String insertSql = "  INSERT INTO TSECPERPROGRAM ( \n "
                                    +"         USER_ID, \n "
                                    +"         LMENU_ID, \n "
                                    +"         MMENU_ID, \n "
                                    +"         PROGRAM_ID, \n "
                                    +"         INSERT_DATE, \n "
                                    +"         INSERT_ID, \n "
                                    +"         MODIFY_DATE, \n "
                                    +"         MODIFY_ID ) \n "
                                    +" VALUES( \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ? ) ";

    private int insertSqlLog =  0;

    public String makeSqlInsert(Tsecperprogram tsecperprogram) throws StoreException{
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
    * Desc : Insert Tsecperprogram
    * </PRE>
    * @param   Connection
    * @param   Tsecperprogram
    * @return  int
    */
    public int insert(Connection con, Tsecperprogram tsecperprogram) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tsecperprogram));
            int index = 1;
            pstmt.setString(index++,tsecperprogram.getUser_id());
            pstmt.setString(index++,tsecperprogram.getLmenu_id());
            pstmt.setString(index++,tsecperprogram.getMmenu_id());
            pstmt.setString(index++,tsecperprogram.getProgram_id());
            pstmt.setString(index++,tsecperprogram.getInsert_id());
            pstmt.setString(index++,tsecperprogram.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecperprogram.getUser_id()    ); logString.append( "/" );
            logString.append( tsecperprogram.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getProgram_id() ); logString.append( "/" );
            logString.append( tsecperprogram.getInsert_id()  ); logString.append( "/" );
            logString.append( tsecperprogram.getModify_id()  ); logString.append( "/" );
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
    * Desc : Make SQL ( Update TSECPROGRAM )
    * </PRE>
    * @param   Tsecperprogram
    * @return  String
    */
    private final String updateSql =  " UPDATE TSECPROGRAM SET \n "
                                     +"        NEW_YN = '0' \n "
                                     +"  WHERE LMENU_ID = ? \n "
                                     +"    AND MMENU_ID = ? \n "
                                     +"    AND PROGRAM_ID = ? \n ";

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tsecperprogram tsecperprogram) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSql);
            }
            updateSqlLog = 1;
        }
        return updateSql;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TSECPROGRAM
    * </PRE>
    * @param   Connection
    * @param   Tsecperprogram
    * @return  int
    */
    public int update(Connection con, Tsecperprogram tsecperprogram) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tsecperprogram));
            int index = 1;
            pstmt.setString(index++,tsecperprogram.getLmenu_id());
            pstmt.setString(index++,tsecperprogram.getMmenu_id());
            pstmt.setString(index++,tsecperprogram.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecperprogram.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getProgram_id() ); logString.append( "/" );
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


    /**
    * <PRE>
    * Desc : Make SQL ( Delete TSECPERPROGRAM )
    * </PRE>
    * @param   Tsecperprogram
    * @return  String
    */
    private final String deleteSql =  " DELETE FROM TSECPERPROGRAM \n "
                                     +"  WHERE USER_ID    = ? \n "
                                     +"    AND LMENU_ID   = ? \n "
                                     +"    AND MMENU_ID   = ? \n "
                                     +"    AND PROGRAM_ID = ? \n ";

    private int deleteSqlLog =  0;

    public String makeSqlDelete(Tsecperprogram tsecperprogram) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSql);
            }
            deleteSqlLog = 1;
        }
        return deleteSql;
    }

    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete TSECPERPROGRAM
    * </PRE>
    * @param   Connection
    * @param   Tsecperprogram
    * @return  int
    */
    public int delete(Connection con, Tsecperprogram tsecperprogram) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tsecperprogram));
            int index = 1;
            pstmt.setString(index++,tsecperprogram.getUser_id());
            pstmt.setString(index++,tsecperprogram.getLmenu_id());
            pstmt.setString(index++,tsecperprogram.getMmenu_id());
            pstmt.setString(index++,tsecperprogram.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecperprogram.getUser_id()   ); logString.append( "/" );
            logString.append( tsecperprogram.getLmenu_id()  ); logString.append( "/" );
            logString.append( tsecperprogram.getMmenu_id()  ); logString.append( "/" );
            logString.append( tsecperprogram.getProgram_id()); logString.append( "/" );
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

}


