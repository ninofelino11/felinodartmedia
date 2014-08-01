
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
import com.cware.back.entity.table.Tmembgb;


/**
 * Membgb Service class
 *
 * @version 1.0, 2006/11/21
 * @author commerceware.co.kr
 */
public class MembgbSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MembgbSvc() {}

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

        sb.append("  SELECT A.MEMB_GB,  \n");
        sb.append("         A.SEQ,      \n");
        sb.append("         A.TYPE,     \n");
        sb.append("         A.VAL1,     \n");
        sb.append("         A.VAL2,     \n");
        sb.append("         A.VAL3,     \n");
        sb.append("         A.CONTENT,  \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS')  AS INSERT_DATE,   \n");
        sb.append("         A.INSERT_ID,   \n");
        sb.append("         TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS')  AS MODIFY_DATE,   \n");
        sb.append("         A.MODIFY_ID  \n");
        sb.append("    FROM TMEMBGB A, \n");
        sb.append("         TCODE B    \n");
        sb.append("   WHERE A.MEMB_GB LIKE ?||'%'  \n");
        sb.append("     AND A.TYPE LIKE ?||'%'  \n");
        sb.append("     AND B.CODE_LGROUP = 'C011'  \n");
        sb.append("     AND B.USE_YN = '1'  \n");
        sb.append("     AND A.TYPE = B.CODE_MGROUP  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TDCRATE )
    * </PRE>
    * @param   tmembgb
    * @return  String
    */
    private final String insertSql = " INSERT INTO TMEMBGB ( \n "
    								+"         MEMB_GB, \n "
    								+"         SEQ, \n "
                                    +"         TYPE, \n "
                                    +"         VAL1, \n "
                                    +"         VAL2, \n "
                                    +"         VAL3, \n "
                                    +"         CONTENT, \n "
                                    +"         INSERT_DATE, \n"
                                    +"         INSERT_ID, \n"
                                    +"         MODIFY_DATE, \n "
                                    +"         MODIFY_ID ) \n "
                                    +"  SELECT ?,  \n "
                                    +"         TRIM(TO_CHAR(TO_NUMBER(NVL(MAX(SEQ + 1), '000')), '000')), \n "
                                    +"         ?, \n "
                                    +"         NVL(?,'0'), \n "
                                    +"         NVL(?,'0'), \n "
                                    +"         NVL(?,'0'), \n "
                                    +"         ?, \n "
    								+"         SYSDATE, \n "
    								+"         ?, \n "
    								+"         SYSDATE, \n "
    								+"         ? \n "
    								+"    FROM TMEMBGB \n "
    								+"   WHERE MEMB_GB   = ? \n ";
    private int insertSqlLog =  0;

    public String makeSqlInsert(Tmembgb tmembgb) {
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
    * Desc : Make SQL ( Update Tmembgb )
    * </PRE>
    * @param   Tmembgb
    * @return  String
    */
    private final String updateSql =  " UPDATE TMEMBGB SET \n "
                                     +"        TYPE = ?, \n "
                                     +"        VAL1 = NVL(?,'0'), \n "
                                     +"        VAL2 = NVL(?,'0'), \n "
                                     +"        VAL3 = NVL(?,'0'), \n "
                                     +"        CONTENT = ?, \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ? \n "
                                     +"  WHERE MEMB_GB = ? \n "
                                     +"    AND SEQ     = ? \n ";

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tmembgb tmembgb) {
        //= log SQL -------------------------------------------------
        if (updateSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSql);
            }
            updateSqlLog = 1;
        }
        return updateSql;
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
            pstmt.setString(index++,retrieve.getString("memb_gb"));
            pstmt.setString(index++,retrieve.getString("type"));

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
    * Desc : Insert TMEMBGB
    * </PRE>
    * @param   Connection
    * @param   tmembgb
    * @return  int
    */
    public int insert(Connection con, Tmembgb tmembgb) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tmembgb));
            int index = 1;
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
    * Desc : Update tMembgb
    * </PRE>
    * @param   Connection
    * @param   tmembgb
    * @return  int
    */
    public int update(Connection con, Tmembgb tmembgb) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tmembgb));
            int index = 1;
    //        logSave.info(logString.toString());

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


