
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
import com.cware.back.entity.table.Tmd;
import com.cware.back.entity.table.Tmdlink;

/**
 * Register MD Service class
 *
 * @version 1.0, 2006/07/13
 */
public class MdSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MdSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.MD_CODE,     \n");
        sb.append("        A.MD_NAME,     \n");
        sb.append("        A.USE_YN     \n");
//        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n");
//        sb.append("        A.INSERT_ID,   \n");
//        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n");
//        sb.append("        A.MODIFY_ID    \n");
        sb.append("   FROM TMD A \n");
        sb.append("  WHERE A.MD_CODE LIKE ?||'%' \n");
//        sb.append("    AND A.MD_NAME LIKE ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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

        sb.append(" SELECT A.USER_ID,     \n");
        sb.append("        B.USER_NAME,   \n");
        sb.append("        A.MD_CODE,     \n");
        sb.append("        TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,   \n");
        sb.append("        A.MAIN_YN,     \n");
        sb.append("        A.USE_YN,     \n");
//        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n");
//        sb.append("        A.INSERT_ID,  \n");
//        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n");
//        sb.append("        A.MODIFY_ID,   \n");
        sb.append("        A.RATE         \n");
        sb.append("   FROM TMDLINK A,     \n");
        sb.append("        TUSER   B      \n");
        sb.append("  WHERE A.USER_ID = B.USER_ID \n");
        sb.append("    AND A.MD_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Print
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPrint( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.MD_CODE,     \n");
        sb.append("        C.MD_NAME,   \n");
        sb.append("        A.USER_ID,     \n");
        sb.append("        B.USER_NAME,     \n");
        sb.append("        TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,   \n");
        sb.append("        A.MODIFY_ID,   \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n");
        sb.append("        A.RATE, A.MAIN_YN, A.USE_YN         \n");
        sb.append("   FROM TMDLINK A,     \n");
        sb.append("        TUSER   B,     \n");
        sb.append("        TMD     C      \n");
        sb.append("  WHERE A.USER_ID = B.USER_ID \n");
        sb.append("    AND A.MD_CODE = C.MD_CODE \n");
        sb.append("    AND A.MD_CODE LIKE ? || '%'  \n");
        sb.append("  ORDER BY A.MD_CODE, B.USER_ID \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TMD )
    * </PRE>
    * @param   Tmd
    * @return  String
    */
    private String makeSqlInsert(Tmd tmd) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TMD ( \n ");
        sb.append("          MD_CODE,  \n ");
        sb.append("          MD_NAME,  \n ");
        sb.append("          USE_YN,   \n ");
        sb.append("          INSERT_DATE, \n ");
        sb.append("          INSERT_ID,   \n ");
        sb.append("          MODIFY_DATE, \n ");
        sb.append("          MODIFY_ID )   \n ");
        sb.append("  VALUES (   \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TMD )
    * </PRE>
    * @param   Tmd
    * @return  String
    */
    private String makeSqlUpdate(Tmd tmd) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TMD SET \n ");
        sb.append("         MD_NAME = ?, \n ");
        sb.append("         USE_YN  = ?, \n ");
        sb.append("         MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID   = ?  \n ");
        sb.append("   WHERE MD_CODE     = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TMDLINK )
    * </PRE>
    * @param   Tmdlink
    * @return  String
    */
//    private final String insertSqlTmdlink =  " INSERT INTO TMDLINK ( \n "
//                                             +"        MD_CODE, \n "
//                                             +"        USER_ID, \n "
//                                             +"        RATE,    \n "
//                                             +"        START_DATE,  \n "
//                                             +"        END_DATE,    \n "
//                                             +"        INSERT_DATE, \n "
//                                             +"        INSERT_ID,   \n "
//                                             +"        MODIFY_DATE, \n "
//                                             +"        MODIFY_ID)   \n "
//                                             +"VALUES (   \n "
//                                             +"        ?, \n "
//                                             +"        ?, \n "
//                                             +"        ?, \n "
//                                             +"        TO_DATE(?,'YYYY/MM/DD'), \n "
//                                             +"        TO_DATE(?,'YYYY/MM/DD'), \n "
//                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
//                                             +"        ?, \n "
//                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
//                                             +"        ?) \n ";

//    private int insertSqlLogTmdlink =  0;

    private String makeSqlInsert(Tmdlink tmdlink) throws StoreException{
    	StringBuffer sb=new StringBuffer();
    	sb.append(" INSERT INTO TMDLINK ( \n ");
    	sb.append("        MD_CODE, \n ");
    	sb.append("        USER_ID, \n ");
    	sb.append("        RATE,    \n ");
    	sb.append("        START_DATE,  \n ");
    	sb.append("        END_DATE,    \n ");
    	sb.append("        MAIN_YN,    \n ");
    	sb.append("        USE_YN,    \n ");
    	sb.append("        INSERT_DATE, \n ");
    	sb.append("        INSERT_ID,   \n ");
    	sb.append("        MODIFY_DATE, \n ");
    	sb.append("        MODIFY_ID)   \n ");
    	sb.append("VALUES (   \n ");
    	sb.append("        ?, \n ");
    	sb.append("        ?, \n ");
    	sb.append("        ?, \n ");
    	sb.append("        TO_DATE(?,'YYYY/MM/DD'), \n ");
    	sb.append("        TO_DATE(?,'YYYY/MM/DD'), \n ");
    	sb.append("        ?, \n ");
    	sb.append("        ?, \n ");
    	sb.append("        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
    	sb.append("        ?, \n ");
    	sb.append("        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
    	sb.append("        ?) \n ");
        //= log SQL -------------------------------------------------
//        if (insertSqlLogTmdlink == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(sb.toString());
            }
//            insertSqlLogTmdlink = 1;
//        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TMDLINK )
    * </PRE>
    * @param   Tmdlink
    * @return  String
    */
//    private final String updateSqlTmdlink = " UPDATE TMDLINK SET \n "
//                                          + "        RATE        = ?, \n "
//                                          + "        START_DATE  = TO_DATE(?,'YYYY/MM/DD')?, \n "
//                                          + "        END_DATE    = TO_DATE(?,'YYYY/MM/DD'), \n "
//                                          + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
//                                          + "        MODIFY_ID   = ? \n "
//                                          + "  WHERE MD_CODE     = ? \n "
//                                          + "    AND USER_ID     = ? \n ";
//
//    private int updateSqlLogTmdlink =  0;

    private String makeSqlUpdate(Tmdlink tmdlink) throws StoreException{
    	StringBuffer sb=new StringBuffer();
    	sb.append(" UPDATE TMDLINK SET \n ");
    	sb.append("        RATE        = ?, \n ");
    	sb.append("        START_DATE  = TO_DATE(?,'YYYY/MM/DD'), \n ");
    	sb.append("        END_DATE    = TO_DATE(?,'YYYY/MM/DD'), \n ");
    	sb.append("        MAIN_YN        = ?, \n ");
    	sb.append("        USE_YN        = ?, \n ");
    	sb.append("        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
    	sb.append("        MODIFY_ID   = ? \n ");
    	sb.append("  WHERE MD_CODE     = ? \n ");
    	sb.append("    AND USER_ID     = ? \n ");
        //= log SQL -------------------------------------------------
//        if (updateSqlLogTmdlink == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(sb.toString());
            }
//            updateSqlLogTmdlink = 1;
//        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tmdlink 사용자ID  중복 체크 )
     * </PRE>
     * @param   Tcode
     * @return  String
     */
     public String makeSqlDupCheck(Tmdlink tmdlink) throws StoreException{

         StringBuffer sb = new StringBuffer();


         sb.append("SELECT COUNT(USER_ID) 				\n ");
		 sb.append("  FROM TMDLINK 						\n ");
		 sb.append(" WHERE MD_CODE = ? AND USER_ID = ?  \n ");


         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result : Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve SQL ; Master
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
            pstmt.setString(index++,retrieve.getString("md_code"));
//            pstmt.setString(index++,retrieve.getString("md_name"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[MdSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[MdSvc.retrieve() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,retrieve.getString("md_code"));

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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ; Print
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrievePrint(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPrint(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("md_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[MdSvc.retrievePrint() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[MdSvc.retrievePrint() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TMD
    * </PRE>
    * @param   Connection
    * @param   Tmd
    * @return  int
    */
    public int insert(Connection con, Tmd tmd) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;
        try {
            pstmt = con.prepareStatement(makeSqlInsert(tmd));
            int index = 1;
            pstmt.setString(index++,tmd.getMd_code());
            pstmt.setString(index++,tmd.getMd_name());
            pstmt.setString(index++,tmd.getUse_yn());
            pstmt.setString(index++,tmd.getInsert_date());
            pstmt.setString(index++,tmd.getInsert_id());
            pstmt.setString(index++,tmd.getModify_date());
            pstmt.setString(index++,tmd.getModify_id());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmd.getMd_code()      ); logString.append( "/" );
            logString.append( tmd.getMd_name()      ); logString.append( "/" );
            logString.append( tmd.getUse_yn()       ); logString.append( "/" );
            logString.append( tmd.getInsert_date()  ); logString.append( "/" );
            logString.append( tmd.getInsert_id()    ); logString.append( "/" );
            logString.append( tmd.getModify_date()  ); logString.append( "/" );
            logString.append( tmd.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[MdSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MdSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TMD
    * </PRE>
    * @param   Connection
    * @param   Tmd
    * @return  int
    */
    public int update(Connection con, Tmd tmd) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tmd));
            int index = 1;
            pstmt.setString(index++,tmd.getMd_name());
            pstmt.setString(index++,tmd.getUse_yn());
            pstmt.setString(index++,tmd.getModify_date());
            pstmt.setString(index++,tmd.getModify_id());
            pstmt.setString(index++,tmd.getMd_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmd.getMd_name()      ); logString.append( "/" );
            logString.append( tmd.getUse_yn()       ); logString.append( "/" );
            logString.append( tmd.getModify_date()  ); logString.append( "/" );
            logString.append( tmd.getModify_id()    ); logString.append( "/" );
            logString.append( tmd.getMd_code()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MdSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MdSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TMDLINK
    * </PRE>
    * @param   Connection
    * @param   Tmdlink
    * @return  int
    */
    public int insert(Connection con, Tmdlink tmdlink) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;
        try {
            pstmt = con.prepareStatement(makeSqlInsert(tmdlink));
            int index = 1;
            pstmt.setString(index++,tmdlink.getMd_code());
            pstmt.setString(index++,tmdlink.getUser_id());
            pstmt.setDouble(index++,tmdlink.getRate());
            pstmt.setString(index++,tmdlink.getStart_date());
            pstmt.setString(index++,tmdlink.getEnd_date());
            pstmt.setString(index++,tmdlink.getMain_yn());
            pstmt.setString(index++,tmdlink.getUse_yn());
            pstmt.setString(index++,tmdlink.getInsert_date());
            pstmt.setString(index++,tmdlink.getInsert_id());
            pstmt.setString(index++,tmdlink.getModify_date());
            pstmt.setString(index++,tmdlink.getModify_id());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmdlink.getMd_code()      ); logString.append( "/" );
            logString.append( tmdlink.getUser_id()      ); logString.append( "/" );
            logString.append( tmdlink.getRate()         ); logString.append( "/" );
            logString.append( tmdlink.getStart_date()   ); logString.append( "/" );
            logString.append( tmdlink.getEnd_date()     ); logString.append( "/" );
            logString.append( tmdlink.getMain_yn()         ); logString.append( "/" );
            logString.append( tmdlink.getUse_yn()         ); logString.append( "/" );
            logString.append( tmdlink.getInsert_date()  ); logString.append( "/" );
            logString.append( tmdlink.getInsert_id()    ); logString.append( "/" );
            logString.append( tmdlink.getModify_date()  ); logString.append( "/" );
            logString.append( tmdlink.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[MdSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MdSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TMDLINK
    * </PRE>
    * @param   Connection
    * @param   Tmdlink
    * @return  int
    */
    public int update(Connection con, Tmdlink tmdlink) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tmdlink));
            int index = 1;
            pstmt.setDouble(index++,tmdlink.getRate());
            pstmt.setString(index++,tmdlink.getStart_date());
            pstmt.setString(index++,tmdlink.getEnd_date());
            pstmt.setString(index++,tmdlink.getMain_yn());
            pstmt.setString(index++,tmdlink.getUse_yn());
            pstmt.setString(index++,tmdlink.getModify_date());
            pstmt.setString(index++,tmdlink.getModify_id());
            pstmt.setString(index++,tmdlink.getMd_code());
            pstmt.setString(index++,tmdlink.getUser_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmdlink.getRate()        ); logString.append( "/" );
            logString.append( tmdlink.getStart_date()  ); logString.append( "/" );
            logString.append( tmdlink.getEnd_date()    ); logString.append( "/" );
            logString.append( tmdlink.getMain_yn()         ); logString.append( "/" );
            logString.append( tmdlink.getUse_yn()         ); logString.append( "/" );
            logString.append( tmdlink.getModify_date() ); logString.append( "/" );
            logString.append( tmdlink.getModify_id()   ); logString.append( "/" );
            logString.append( tmdlink.getMd_code()     ); logString.append( "/" );
            logString.append( tmdlink.getUser_id()     ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MdSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MdSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tmdlink
    * </PRE>
    * @param   Connection
    * @param   Tdescribecode
    * @return  int
    */
    public int checkDup(Connection con, Tmdlink tmdlink) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tmdlink));
            pstmt.setString(1, tmdlink.getMd_code());
            pstmt.setString(2, tmdlink.getUser_id());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[CodeInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CodeInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}
