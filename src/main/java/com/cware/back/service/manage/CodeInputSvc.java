
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
import com.cware.back.entity.table.Tcode;


/**
 * TCode Service class
 *
 * @version 1.0, 2006/07/14
 * @author commerceware.co.kr
 */
public class CodeInputSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public CodeInputSvc() {}

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

        sb.append("SELECT CODE_MGROUP,     \n");
        sb.append("       CODE_NAME,     \n");
        sb.append("       CODE_GROUP,    \n");
        sb.append("       REMARK        \n");
        sb.append("  FROM TCODE          \n");
        sb.append(" WHERE CODE_MGROUP LIKE UPPER(?)||'%'    \n");
        sb.append("   AND CODE_LGROUP = '0000'                  \n");
        if(!retrieve.getString("user_id").equals("SYSTEM")){
            sb.append("   AND CODE_MGROUP <> '0000'                 \n");
            sb.append("   AND USE_YN='1'                            \n");
            sb.append(" 	ORDER BY CODE_MGROUP ASC                  \n");
        }

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TCODE )
    * </PRE>
    * @param   tcode
    * @return  String
    */
    private final String insertSql = " INSERT INTO TCODE ( \n "
                                    +"         CODE_LGROUP, \n "
                                    +"         CODE_MGROUP, \n "
                                    +"         CODE_NAME, \n "
                                    +"         CODE_SNAME, \n "
                                    +"         USE_YN, \n"
                                    +"         REMARK, \n"
                                    +"         REMARK1, \n"
                                    +"         REMARK2, \n"
                                    +"         CONTENT, \n"
                                    +"         CODE_GROUP, \n"
                                    +"         INSERT_DATE, \n "
                                    +"         INSERT_ID, \n "
                                    +"         MODIFY_DATE, \n "
                                    +"         MODIFY_ID ) \n "
                                    +" VALUES( \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
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

    public String makeSqlInsert(Tcode tcode) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSql);
            }
            insertSqlLog = 1;
        }
        return insertSql;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TCODE )
    * </PRE>
    * @param   Tcode
    * @return  String
    */
    private final String updateSql =  " UPDATE TCODE SET \n "
                                     +"        CODE_NAME = ?, \n "
                                     +"        CODE_SNAME = ?, \n "
                                     +"        USE_YN = ?, \n "
                                     +"        REMARK = ?, \n "
                                     +"        REMARK1 = ?, \n "
                                     +"        REMARK2 = ?, \n "
                                     +"        CONTENT = ?, \n "
                                     +"        CODE_GROUP = ?, \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ? \n "
                                     +"  WHERE CODE_LGROUP = ? \n "
                                     +"    AND CODE_MGROUP = ? \n "
                                     +"    AND MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') \n ";

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tcode tcode) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSql);
            }
            updateSqlLog = 1;
        }
        return updateSql;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 기초코드  중복 체크 )
     * </PRE>
     * @param   Tcode
     * @return  String
     */
     public String makeSqlDupCheck(Tcode tcode) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT COUNT(CODE_MGROUP) \n ");
         sb.append("  FROM TCODE \n ");
         sb.append(" WHERE CODE_LGROUP = ? AND CODE_MGROUP = ? \n ");

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
            pstmt.setString(index++,retrieve.getString("code_lgroup"));

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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ; Detail
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDetail(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("code_mgroup"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[MdSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[MdSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Detail
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDetail(ResultSet rset)  throws Exception {
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

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("        SELECT A.CODE_LGROUP,       \n");
        sb.append("        A.CODE_MGROUP,              \n");
        sb.append("        A.CODE_NAME,                \n");
        sb.append("        A.CODE_SNAME,               \n");
        sb.append("        A.REMARK,                   \n");
        sb.append("        A.REMARK1,                  \n");
        sb.append("        A.REMARK2,                  \n");
        sb.append("        A.USE_YN,                   \n");
        sb.append("        A.CODE_GROUP,                   \n");
        sb.append("        A.CODE_GROUP AS CODE_GROUP_CHECK,                   \n");
        sb.append("        A.CONTENT,                                                     \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE,   \n");
        sb.append("        A.INSERT_ID,                                                   \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE,    \n");
        sb.append("        A.MODIFY_ID                                                     \n");
        sb.append("   FROM TCODE A                                                         \n");
        sb.append("  WHERE A.CODE_LGROUP = ?      \n");
        sb.append("  ORDER BY CODE_MGROUP              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCODE
    * </PRE>
    * @param   Connection
    * @param   Tcode
    * @return  int
    */
    public int insert(Connection con, Tcode tcode) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tcode));
            int index = 1;
            pstmt.setString(index++,tcode.getCode_lgroup());
            pstmt.setString(index++,tcode.getCode_mgroup());
            pstmt.setString(index++,tcode.getCode_name());
            pstmt.setString(index++,tcode.getCode_sname());
            pstmt.setString(index++,tcode.getUse_yn());
            pstmt.setString(index++,tcode.getRemark());
            pstmt.setString(index++,tcode.getRemark1());
            pstmt.setString(index++,tcode.getRemark2());
            pstmt.setString(index++,tcode.getContent());
            pstmt.setString(index++,tcode.getCode_group());
            pstmt.setString(index++,tcode.getInsert_id());
            pstmt.setString(index++,tcode.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcode.getCode_lgroup()); logString.append( "/" );
            logString.append( tcode.getCode_mgroup()); logString.append( "/" );
            logString.append( tcode.getCode_name()  ); logString.append( "/" );
            logString.append( tcode.getCode_sname() ); logString.append( "/" );
            logString.append( tcode.getUse_yn()     ); logString.append( "/" );
            logString.append( tcode.getRemark()    ); logString.append( "/" );
            logString.append( tcode.getRemark1()    ); logString.append( "/" );
            logString.append( tcode.getRemark2()    ); logString.append( "/" );
            logString.append( tcode.getContent()    ); logString.append( "/" );
            logString.append( tcode.getCode_group()    ); logString.append( "/" );
            logString.append( tcode.getInsert_id()  ); logString.append( "/" );
            logString.append( tcode.getModify_id()  ); logString.append( "/" );
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


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update tcode
    * </PRE>
    * @param   Connection
    * @param   tcode
    * @return  int
    */
    public int update(Connection con, Tcode tcode) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tcode));
            int index = 1;
            pstmt.setString(index++,tcode.getCode_name());
            pstmt.setString(index++,tcode.getCode_sname());
            pstmt.setString(index++,tcode.getUse_yn());
            pstmt.setString(index++,tcode.getRemark());
            pstmt.setString(index++,tcode.getRemark1());
            pstmt.setString(index++,tcode.getRemark2());
            pstmt.setString(index++,tcode.getContent());
            pstmt.setString(index++,tcode.getCode_group());
            pstmt.setString(index++,tcode.getModify_id());
            pstmt.setString(index++,tcode.getCode_lgroup());
            pstmt.setString(index++,tcode.getCode_mgroup());
            pstmt.setString(index++,tcode.getModify_date());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcode.getCode_name()  ); logString.append( "/" );
            logString.append( tcode.getCode_sname() ); logString.append( "/" );
            logString.append( tcode.getUse_yn()     ); logString.append( "/" );
            logString.append( tcode.getRemark()    ); logString.append( "/" );
            logString.append( tcode.getRemark1()    ); logString.append( "/" );
            logString.append( tcode.getRemark2()    ); logString.append( "/" );
            logString.append( tcode.getContent()    ); logString.append( "/" );
            logString.append( tcode.getCode_group()    ); logString.append( "/" );
            logString.append( tcode.getModify_id()  ); logString.append( "/" );
            logString.append( tcode.getCode_lgroup()); logString.append( "/" );
            logString.append( tcode.getCode_mgroup()); logString.append( "/" );
            logString.append( tcode.getModify_date()); logString.append( "/" );
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


    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tcode
    * </PRE>
    * @param   Connection
    * @param   Tcode
    * @return  int
    */
    public int checkDup(Connection con, Tcode tcode) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tcode));
            pstmt.setString(1, tcode.getCode_lgroup());
            pstmt.setString(2, tcode.getCode_mgroup());
            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

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

}


