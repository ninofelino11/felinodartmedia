
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
import com.cware.back.entity.table.Tsecusergroup;


/**
 * 프로그램사용자그룹관리 Service class
 *
 * @version 1.0, 2009/04/13
 * @author commerceware.co.kr
 */
public class ProgramUserGroupConsentSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ProgramUserGroupConsentSvc() {}


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

        sb.append("	SELECT 	A.CODE_MGROUP AS USER_GB,      \n");
        sb.append("			A.CODE_NAME,                   \n");
        sb.append("			? AS PROGRAM_ID                \n");
        sb.append("	  FROM 	TCODE A                        \n");
        sb.append("	 WHERE 	A.CODE_LGROUP = 'B040'         \n");
        sb.append("	MINUS                                  \n");
        sb.append("	SELECT 	B.USER_GB,                     \n");
        sb.append("			A.CODE_NAME,                   \n");
        sb.append("			B.PROGRAM_ID                   \n");
        sb.append("	  FROM 	TCODE A, TSECUSERGROUP B       \n");
        sb.append("	 WHERE 	A.CODE_LGROUP = 'B040'         \n");
        sb.append("	   AND 	B.LMENU_ID   = ?               \n");
        sb.append("	   AND 	B.MMENU_ID   = ?               \n");
        sb.append("	   AND 	A.CODE_MGROUP = B.USER_GB      \n");
        sb.append("	   AND 	B.PROGRAM_ID = ?               \n");

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
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("program_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
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

        sb.append("	SELECT 	B.LMENU_ID,                      \n");
        sb.append("			B.MMENU_ID,                      \n");
        sb.append("			A.CODE_NAME,                     \n");
        sb.append("			B.USER_GB,                       \n");
        sb.append("			B.PROGRAM_ID                     \n");
        sb.append("	  FROM 	TCODE A, TSECUSERGROUP B         \n");
        sb.append("	 WHERE 	A.CODE_LGROUP = 'B040'           \n");
        sb.append("	   AND 	B.LMENU_ID   = ?                 \n");
        sb.append("	   AND 	B.MMENU_ID   = ?                 \n");
        sb.append("       AND 	A.CODE_MGROUP = B.USER_GB    \n");
        sb.append("       AND 	B.PROGRAM_ID = ?             \n");

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
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));
            pstmt.setString(index++,retrieve.getString("program_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
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
    * Desc : Make SQL ( 특정 user_gb가 특정 프로그램에 대해 권한있는지 체크 )
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlCheckSec(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(*) AS cnt \n");
        sb.append("   FROM TSECUSERGROUP \n");
        sb.append("  WHERE USER_GB    = ? \n");
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
    * Desc : Retrieve SQL ( 특정 user_gb가 특정 프로그램에 대해 권한있는지 체크 )
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  String
    */
    public RetrieveModel checkSec(Connection con, Tsecusergroup tsecusergroup) throws StoreException{
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        RetrieveModel retrieve = new RetrieveModel();

        try {
            pstmt = con.prepareStatement(makeSqlCheckSec(retrieve));

            int index = 1;
            pstmt.setString(index++,tsecusergroup.getUser_gb());
            pstmt.setString(index++,tsecusergroup.getLmenu_id());
            pstmt.setString(index++,tsecusergroup.getMmenu_id());
            pstmt.setString(index++,tsecusergroup.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecusergroup.getUser_gb()    ); logString.append( "/" );
            logString.append( tsecusergroup.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getProgram_id() ); logString.append( "/" );
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
    * Desc : Make SQL ( Insert Tsecusergroup )
    * </PRE>
    * @param   Tsecusergroup
    * @return  String
    */

    private final String insertSql = "  INSERT INTO TSECUSERGROUP ( \n "
                                    +"         USER_GB, \n "
                                    +"         LMENU_ID, \n "
                                    +"         MMENU_ID, \n "
                                    +"         PROGRAM_ID, \n "
                                    +"         INSERT_DATE, \n "
                                    +"         INSERT_ID ) \n "
                                    +" VALUES( \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ? ) ";

    private int insertSqlLog =  0;

    public String makeSqlInsert(Tsecusergroup tsecusergroup) throws StoreException{
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
    * Desc : Insert TSECUSERGROUP
    * </PRE>
    * @param   Connection
    * @param   Tsecusergroup
    * @return  int
    */
    public int insert(Connection con, Tsecusergroup tsecusergroup) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tsecusergroup));
            int index = 1;
            pstmt.setString(index++,tsecusergroup.getUser_gb());
            pstmt.setString(index++,tsecusergroup.getLmenu_id());
            pstmt.setString(index++,tsecusergroup.getMmenu_id());
            pstmt.setString(index++,tsecusergroup.getProgram_id());
            pstmt.setString(index++,tsecusergroup.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecusergroup.getUser_gb()    ); logString.append( "/" );
            logString.append( tsecusergroup.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getProgram_id() ); logString.append( "/" );
            logString.append( tsecusergroup.getInsert_id()  ); logString.append( "/" );
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
    * @param   Tsecusergroup
    * @return  String
    */
    private final String updateSql =  " UPDATE TSECPROGRAM SET \n "
                                     +"        NEW_YN = '0' \n "
                                     +"  WHERE LMENU_ID = ? \n "
                                     +"    AND MMENU_ID = ? \n "
                                     +"    AND PROGRAM_ID = ? \n ";

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tsecusergroup tsecusergroup) throws StoreException{
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
    * @param   Tsecusergroup
    * @return  int
    */
    public int update(Connection con, Tsecusergroup tsecusergroup) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tsecusergroup));
            int index = 1;
            pstmt.setString(index++,tsecusergroup.getLmenu_id());
            pstmt.setString(index++,tsecusergroup.getMmenu_id());
            pstmt.setString(index++,tsecusergroup.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecusergroup.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getProgram_id() ); logString.append( "/" );
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
    * Desc : Make SQL ( Delete TSECUSERGROUP )
    * </PRE>
    * @param   Tsecusergroup
    * @return  String
    */
    private final String deleteSql =  " DELETE FROM TSECUSERGROUP \n "
                                     +"  WHERE USER_GB    = ? \n "
                                     +"    AND LMENU_ID   = ? \n "
                                     +"    AND MMENU_ID   = ? \n "
                                     +"    AND PROGRAM_ID = ? \n ";

    private int deleteSqlLog =  0;

    public String makeSqlDelete(Tsecusergroup tsecusergroup) throws StoreException{
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
    * Desc : Delete TSECUSERGROUP
    * </PRE>
    * @param   Connection
    * @param   Tsecusergroup
    * @return  int
    */
    public int delete(Connection con, Tsecusergroup tsecusergroup) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tsecusergroup));
            int index = 1;
            pstmt.setString(index++,tsecusergroup.getUser_gb());
            pstmt.setString(index++,tsecusergroup.getLmenu_id());
            pstmt.setString(index++,tsecusergroup.getMmenu_id());
            pstmt.setString(index++,tsecusergroup.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecusergroup.getUser_gb()   ); logString.append( "/" );
            logString.append( tsecusergroup.getLmenu_id()  ); logString.append( "/" );
            logString.append( tsecusergroup.getMmenu_id()  ); logString.append( "/" );
            logString.append( tsecusergroup.getProgram_id()); logString.append( "/" );
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


