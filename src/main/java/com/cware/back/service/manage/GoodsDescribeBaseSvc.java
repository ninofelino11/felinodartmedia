
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
import com.cware.back.entity.table.Tdescribecode;


/**
 * Tdescribecode Service class
 *
 * @version 1.0, 2006/09/05
 * @author commerceware.co.kr
 */
public class GoodsDescribeBaseSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsDescribeBaseSvc() {}

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

        sb.append(" SELECT A.DESCRIBE_CODE,                                             \n");
        sb.append("        A.DESCRIBE_TITLE,                                            \n");
        sb.append("        A.WEB_FLAG,                                                  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE, \n");
        sb.append("        A.INSERT_ID,                                                 \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE, \n");
        sb.append("        A.MODIFY_ID ,                                                \n");
        sb.append("        A.SORT_SEQ,                                                   \n");
        sb.append("        A.USE_YN	                                                    \n");
        sb.append("   FROM TDESCRIBECODE A                                              \n");
        sb.append("  WHERE A.DESCRIBE_CODE LIKE ?||'%'                                  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TDESCRIBECODE )
    * </PRE>
    * @param   tdescribecode
    * @return  String
    */
    private final String insertSql = " INSERT INTO TDESCRIBECODE      \n"
                                    +"             ( DESCRIBE_CODE,   \n"
                                    +"               DESCRIBE_TITLE,  \n"
                                    +"               WEB_FLAG,        \n"
                                    +"               SORT_SEQ,        \n"
                                    +"               USE_YN,        \n"
                                    +"               INSERT_DATE,     \n"
                                    +"               INSERT_ID,       \n"
                                    +"               MODIFY_DATE,     \n"
                                    +"               MODIFY_ID)       \n"
                                    +"      VALUES ( ?,               \n"
                                    +"               ?,               \n"
                                    +"               ?,               \n"
                                    +"               ?,               \n"
                                    +"               ?,               \n"
                                    +"               SYSDATE,         \n"
                                    +"               ?,               \n"
                                    +"               SYSDATE,         \n"
                                    +"               ? )              \n";

    private int insertSqlLog =  0;

    public String makeSqlInsert(Tdescribecode tdescribecode) throws StoreException{
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
    * Desc : Make SQL ( Update TDESCRIBECODE )
    * </PRE>
    * @param   tdescribecode
    * @return  String
    */
    private final String updateSql =  " UPDATE TDESCRIBECODE SET      \n "
                                     +"        WEB_FLAG = ?,          \n "
                                     +"        USE_YN = ?,          \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ?          \n "
                                     +"  WHERE DESCRIBE_CODE = ?      \n "
                                     +"    AND MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') \n ";

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tdescribecode tdescribecode) throws StoreException{
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
    * Desc : Make SQL ( 상품기초코드  중복 체크 )
    * </PRE>
    * @param   Tdescribecode
    * @return  String
    */
    public String makeSqlDupCheck(Tdescribecode tdescribecode) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT COUNT(DESCRIBE_CODE) \n ");
        sb.append("  FROM TDESCRIBECODE \n ");
        sb.append(" WHERE DESCRIBE_CODE = ? \n ");

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
            pstmt.setString(index++,retrieve.getString("describe_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsDescribeBaseSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsDescribeBaseSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public int insert(Connection con, Tdescribecode tdescribecode) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tdescribecode));
            int index = 1;
            pstmt.setString(index++,tdescribecode.getDescribe_code());
            pstmt.setString(index++,tdescribecode.getDescribe_title());
            pstmt.setString(index++,tdescribecode.getWeb_flag());
            pstmt.setString(index++,tdescribecode.getSort_seq());
            pstmt.setString(index++,tdescribecode.getUse_yn());
            pstmt.setString(index++,tdescribecode.getInsert_id());
            pstmt.setString(index++,tdescribecode.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdescribecode.getDescribe_code() ); logString.append( "/" );
            logString.append( tdescribecode.getDescribe_title()); logString.append( "/" );
            logString.append( tdescribecode.getWeb_flag()      ); logString.append( "/" );
            logString.append( tdescribecode.getSort_seq()      ); logString.append( "/" );
            logString.append( tdescribecode.getUse_yn()        ); logString.append( "/" );
            logString.append( tdescribecode.getInsert_id()     ); logString.append( "/" );
            logString.append( tdescribecode.getModify_id()     ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsDescribeBaseSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsDescribeBaseSvc.insert() Exception : ERR-"+e.getMessage());
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
    public int update(Connection con, Tdescribecode tdescribecode) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tdescribecode));
            int index = 1;
            pstmt.setString(index++,tdescribecode.getWeb_flag());
            pstmt.setString(index++,tdescribecode.getUse_yn());
            pstmt.setString(index++,tdescribecode.getModify_id());
            pstmt.setString(index++,tdescribecode.getDescribe_code());
            pstmt.setString(index++,tdescribecode.getModify_date());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdescribecode.getWeb_flag()  ); logString.append( "/" );
            logString.append( tdescribecode.getUse_yn()  	); logString.append( "/" );
            logString.append( tdescribecode.getModify_id() ); logString.append( "/" );
            logString.append( tdescribecode.getDescribe_code() ); logString.append( "/" );
            logString.append( tdescribecode.getModify_date()); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsDescribeBaseSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsDescribeBaseSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tdescribecode
    * </PRE>
    * @param   Connection
    * @param   Tdescribecode
    * @return  int
    */
    public int checkDup(Connection con, Tdescribecode tdescribecode) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tdescribecode));
            pstmt.setString(1, tdescribecode.getDescribe_code());
            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[WhCodeSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[WhCodeSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}


