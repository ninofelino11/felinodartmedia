
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
 * UserProgramConsent Service class
 *
 * @version 1.0, 2006/07/21
 * @author commerceware.co.kr
 */
public class UserProgramConsentSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public UserProgramConsentSvc() {}


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

        sb.append(" SELECT DISTINCT  \n");
        sb.append("        '1' AS DEPTH, \n");
        sb.append("        LMENU_ID,  \n");
        sb.append("        LMENU_NAME,  \n");
        sb.append("        LMENU_ID AS ID, \n");
        sb.append("        LMENU_NAME AS NAME \n");
        sb.append("   FROM TSECMENU  \n");
        sb.append("  WHERE USE_YN ='1'  \n");
        sb.append("  UNION \n");
        sb.append(" SELECT DISTINCT \n");
        sb.append("        '2' AS DEPTH, \n");
        sb.append("        LMENU_ID,  \n");
        sb.append("        LMENU_NAME,  \n");
        sb.append("        MMENU_ID AS ID, \n");
        sb.append("        MMENU_NAME AS NAME \n");
        sb.append("   FROM TSECMENU  \n");
        sb.append("  WHERE USE_YN ='1'  \n");
        sb.append("  ORDER BY 2,1,4 ASC  \n");

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
    * Desc : Make SQL (권한없는 프로그램 리스트)
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlNoSecList(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT TSECPROGRAM.LMENU_ID, \n");
        sb.append("        TSECPROGRAM.MMENU_ID, \n");
        sb.append("        TSECPROGRAM.PROGRAM_ID, \n");
        sb.append("        TSECPROGRAM.PROGRAM_NAME, \n");
        sb.append("        ? AS USER_ID \n");
        sb.append("   FROM TSECPROGRAM   \n");
        sb.append("  WHERE TSECPROGRAM.LMENU_ID = ? \n");
        sb.append("    AND TSECPROGRAM.MMENU_ID = ? \n");
        sb.append("    AND TSECPROGRAM.USE_YN   = '1' \n");
        sb.append("    AND TSECPROGRAM.PROGRAM_ID NOT IN (SELECT TSECPERPROGRAM.PROGRAM_ID \n");
        sb.append("                                         FROM TSECPERPROGRAM \n");
        sb.append("                                        WHERE TSECPERPROGRAM.LMENU_ID = ? \n");
        sb.append("                                          AND TSECPERPROGRAM.MMENU_ID = ? \n");
        sb.append("                                          AND TSECPERPROGRAM.USER_ID = ?) \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (권한없는 프로그램 리스트)
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
            pstmt.setString(index++,retrieve.getString("user_id"));
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("user_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("lmenu_id")  ); logString.append( "/" );
            logString.append( retrieve.getString("mmenu_id") ); logString.append( "/" );
            logString.append( retrieve.getString("user_id") ); logString.append( "/" );

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
    * Desc : Make SQL (권한있는 프로그램 리스트 )
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlSecList(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT TSECPERPROGRAM.LMENU_ID , \n");
        sb.append("        TSECPERPROGRAM.MMENU_ID , \n");
        sb.append("        TSECPERPROGRAM.PROGRAM_ID, \n");
        sb.append("        TSECPROGRAM.PROGRAM_NAME, \n");
        sb.append("        TSECPERPROGRAM.INSERT_DATE, \n");
        sb.append("        TSECPERPROGRAM.INSERT_ID, \n");
        sb.append("        TSECPERPROGRAM.MODIFY_DATE, \n");
        sb.append("        TSECPERPROGRAM.MODIFY_ID, \n");
        sb.append("        TSECPERPROGRAM.USER_ID \n");
        sb.append("   FROM TSECPERPROGRAM, \n");
        sb.append("        TSECPROGRAM   \n");
        sb.append("  WHERE TSECPERPROGRAM.LMENU_ID   = ? \n");
        sb.append("    AND TSECPERPROGRAM.MMENU_ID   = ? \n");
        sb.append("    AND TSECPERPROGRAM.USER_ID    = ? \n");
        sb.append("    AND TSECPERPROGRAM.PROGRAM_ID = TSECPROGRAM.PROGRAM_ID  \n");
        sb.append("    AND TSECPERPROGRAM.LMENU_ID   = TSECPROGRAM.LMENU_ID  \n");
        sb.append("    AND TSECPERPROGRAM.MMENU_ID   = TSECPROGRAM.MMENU_ID  \n");
        sb.append("    AND TSECPROGRAM.USE_YN        = '1'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL (권한있는 프로그램 리스트 )
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
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("user_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("lmenu_id") ); logString.append( "/" );
            logString.append( retrieve.getString("mmenu_id") ); logString.append( "/" );
            logString.append( retrieve.getString("user_id")  ); logString.append( "/" );
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
    * Desc : Make SQL ( Insert Tsecperprogram, 특정 user_id 권한복사 )
    * </PRE>
    * @param   Tsecperprogram
    * @return  String
    *
    */

    // EXCEL_YN 컬럼 위치 변경. - 20091117
    private final String insertSqlAll = " INSERT INTO TSECPERPROGRAM  \n " +
    									" ( USER_ID, LMENU_ID, MMENU_ID, PROGRAM_ID, QUERY_YN, INSERT_YN, DELETE_YN, SAVE_YN, PRINT_YN, EXCEL_YN, ETC_YN, ADD_YN, INSERT_ID, INSERT_DATE, MODIFY_ID, MODIFY_DATE ) \n"
                                       +"         SELECT ?, \n "
                                       +"                LMENU_ID, \n "
                                       +"                MMENU_ID, \n "
                                       +"                PROGRAM_ID, \n "
                                       +"                QUERY_YN, \n "
                                       +"                INSERT_YN, \n "
                                       +"                DELETE_YN, \n "
                                       +"                SAVE_YN, \n "
                                       +"                PRINT_YN, \n "
                                       +"                EXCEL_YN, \n "
                                       +"                ETC_YN, \n "
                                       +"                ADD_YN, \n "
                                       +"                ?, \n "
                                       +"                SYSDATE, \n "
                                       +"                ?, \n "
                                       +"                SYSDATE \n "
                                       +"           FROM TSECPERPROGRAM \n "
                                       +"          WHERE USER_ID = ? \n ";

    private int insertSqlAllLog =  0;

    public String makeSqlInsert(String user_id_to, String insert_id, String user_id_fr) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlAllLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlAll);
            }
            insertSqlAllLog = 1;
        }
        return insertSqlAll;
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
    public int insert(Connection con, String user_id_to, String insert_id, String user_id_fr) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(user_id_to, insert_id, user_id_fr));
            int index = 1;
            pstmt.setString(index++,user_id_to);
            pstmt.setString(index++,insert_id);
            pstmt.setString(index++,insert_id);
            pstmt.setString(index++,user_id_fr);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user_id_to ); logString.append( "/" );
            logString.append( insert_id  ); logString.append( "/" );
            logString.append( insert_id  ); logString.append( "/" );
            logString.append( user_id_fr ); logString.append( "/" );
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


    /**
    * <PRE>
    * Desc : Make SQL ( Delete TSECPERPROGRAM , 특정 user_id 모두 )
    * </PRE>
    * @param   Tsecperprogram
    * @return  String
    */
    private final String deleteSqlAll =  " DELETE FROM TSECPERPROGRAM \n "
                                        +"  WHERE USER_ID = ? \n " ;

    private int deleteSqlAllLog =  0;

    public String makeSqlDelete(String user_id) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlAllLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlAll);
            }
            deleteSqlLog = 1;
        }
        return deleteSqlAll;
    }

    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete TSECPERPROGRAM ( 특정 user_id 모두 )
    * </PRE>
    * @param   Connection
    * @param   Tsecperprogram
    * @return  int
    */
    public int delete(Connection con, String user_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(user_id));
            int index = 1;
            pstmt.setString(index++,user_id);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user_id ); logString.append( "/" );

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


