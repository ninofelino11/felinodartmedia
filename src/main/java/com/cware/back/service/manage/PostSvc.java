
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
import com.cware.back.entity.table.Tpost;


/**
 * Zip Code Service class
 *
 * @version 1.0, 2006/06/29
 * @author commerceware.co.kr
 */
public class PostSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PostSvc() {}

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

        sb.append("  SELECT A.POST_NO,   \n");
        sb.append("         A.POST_SEQ,   \n");
        sb.append("         A.CITY_NAME,   \n");
        sb.append("         A.GU_NAME,   \n");
        sb.append("         A.DONG_NAME,   \n");
        sb.append("         A.AREA_GB,   \n");
        sb.append("         A.POST_GB,   \n");
        sb.append("         A.DDD,   \n");
        sb.append("			A.DELIVERY_POINT_COUNT,   \n");
        sb.append("         A.USE_YN,   \n");
        sb.append("         A.NOTE,   \n");
        sb.append("         A.INSERT_DATE,   \n");
        sb.append("         A.INSERT_ID,   \n");
        sb.append("         A.MODIFY_DATE,   \n");
        sb.append("         A.MODIFY_ID   \n");
        sb.append("    FROM TPOST A   \n");
        sb.append("   WHERE A.POST_NO >= ?   \n");
        sb.append("     AND A.POST_NO <= ?   \n");
        sb.append("     AND A.USE_YN LIKE ? || '%'   \n");
        sb.append("   ORDER BY A.POST_NO,  A.POST_SEQ  ASC   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Selct Max.+1 Value on TPOST )
    * </PRE>
    * @param   Tpost
    * @return  String
    */

    private final String selectSqlPostSeq = "  SELECT LPAD(NVL(MAX(POST_SEQ),00)+1,2,'0') AS POST_SEQ \n "
                                           +"    FROM TPOST \n "
                                           +"   WHERE POST_NO = ? \n ";
    private int selectSqlPostSeqLog =  0;

    public String makeSqlPostSeq(Tpost tpost) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlPostSeqLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(selectSqlPostSeq);
            }
            selectSqlPostSeqLog = 1;
        }
        return selectSqlPostSeq;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TPOST )
    * </PRE>
    * @param   Tpost
    * @return  String
    */
    private final String insertSql = " INSERT INTO TPOST ( \n "
                                    +"         POST_NO, \n "
                                    +"         POST_SEQ, \n "
                                    +"         CITY_NAME, \n "
                                    +"         GU_NAME, \n "
                                    +"         DONG_NAME, \n "
                                    +"         DDD, \n "
                                    +"         AREA_GB, \n "
                                    +"         POST_GB, \n "
                                    +"         USE_YN, \n "
                                    +"         DELIVERY_POINT_COUNT, \n "
                                    +"         INSERT_DATE, \n "
                                    +"         INSERT_ID, \n "
                                    +"         MODIFY_DATE, \n "
                                    +"         MODIFY_ID,  \n "
                                    +"         POST_NO2,  \n "
                                    +"         POST_TOWN, NOTE ) \n "
                                    +" VALUES( \n "
                                    +"         UPPER(?), \n "
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
                                    +"         ?, \n "
                                    +"         UPPER(REPLACE(?, ' ', '')), \n "
    								+"         ?, ? ) \n ";

    private int insertSqlLog =  0;

    public String makeSqlInsert(Tpost tpost) throws StoreException{
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
    * Desc : Make SQL ( Update TPOST )
    * </PRE>
    * @param   Tpost
    * @return  String
    */
    private final String updateSql =  " UPDATE TPOST SET \n "
                                     +"        CITY_NAME = ?, \n "
                                     +"        GU_NAME = ?, \n "
                                     +"        DONG_NAME = ?, \n "
                                     +"        DDD = ?, \n "
                                     +"        AREA_GB = ?, \n "
                                     +"        POST_GB = ?, \n "
                                     +"        USE_YN = ?, \n "
                                     +"        DELIVERY_POINT_COUNT = ?, \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ?, \n "
                                     +"        POST_TOWN = ?, \n "
                                     +"        NOTE = ? \n "
                                     +"  WHERE POST_NO = ? \n "
                                     +"    AND POST_SEQ = ? \n ";
    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tpost tpost) throws StoreException{
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
            pstmt.setString(index++,retrieve.getString("post_no_from"));
            pstmt.setString(index++,retrieve.getString("post_no_to"));
            pstmt.setString(index++,retrieve.getString("use_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[CashListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CashListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TPOST
    * </PRE>
    * @param   Connection
    * @param   Tpost
    * @return  int
    */
    public int insert(Connection con, Tpost tpost) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpost));
            int index = 1;
            pstmt.setString(index++,tpost.getPost_no());
            pstmt.setString(index++,tpost.getPost_seq());
            pstmt.setString(index++,tpost.getCity_name());
            pstmt.setString(index++,tpost.getGu_name());
            pstmt.setString(index++,tpost.getDong_name());
            pstmt.setString(index++,tpost.getDdd());
            pstmt.setString(index++,tpost.getArea_gb());
            pstmt.setString(index++,tpost.getPost_gb());
            pstmt.setString(index++,tpost.getUse_yn());
            pstmt.setString(index++,tpost.getDelivery_point_count());
            pstmt.setString(index++,tpost.getInsert_id());
            pstmt.setString(index++,tpost.getModify_id());
            pstmt.setString(index++,tpost.getPost_no());
            pstmt.setString(index++,tpost.getCity_name());
            pstmt.setString(index++,tpost.getNote());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpost.getPost_no()    ); logString.append( "/" );
            logString.append( tpost.getPost_seq()   ); logString.append( "/" );
            logString.append( tpost.getCity_name()  ); logString.append( "/" );
            logString.append( tpost.getGu_name()    ); logString.append( "/" );
            logString.append( tpost.getDong_name()  ); logString.append( "/" );
            logString.append( tpost.getDdd()        ); logString.append( "/" );
            logString.append( tpost.getArea_gb()    ); logString.append( "/" );
            logString.append( tpost.getPost_gb()    ); logString.append( "/" );
            logString.append( tpost.getUse_yn()     ); logString.append( "/" );
            logString.append( tpost.getDelivery_point_count()     ); logString.append( "/" );
            logString.append( tpost.getInsert_id()  ); logString.append( "/" );
            logString.append( tpost.getModify_id()  ); logString.append( "/" );
            logString.append( tpost.getPost_no()    ); logString.append( "/" );
            logString.append( tpost.getCity_name()  ); logString.append( "/" );
            logString.append( tpost.getNote()  ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PostSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PostSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TPOST
    * </PRE>
    * @param   Connection
    * @param   Tpost
    * @return  int
    */
    public int update(Connection con, Tpost tpost) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpost));
            int index = 1;
            pstmt.setString(index++,tpost.getCity_name());
            pstmt.setString(index++,tpost.getGu_name());
            pstmt.setString(index++,tpost.getDong_name());
            pstmt.setString(index++,tpost.getDdd());
            pstmt.setString(index++,tpost.getArea_gb());
            pstmt.setString(index++,tpost.getPost_gb());
            pstmt.setString(index++,tpost.getUse_yn());
            pstmt.setString(index++,tpost.getDelivery_point_count());
            pstmt.setString(index++,tpost.getModify_id());
            pstmt.setString(index++,tpost.getCity_name());
            pstmt.setString(index++,tpost.getNote());
            pstmt.setString(index++,tpost.getPost_no());
            pstmt.setString(index++,tpost.getPost_seq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpost.getCity_name() ); logString.append( "/" );
            logString.append( tpost.getGu_name()   ); logString.append( "/" );
            logString.append( tpost.getDong_name() ); logString.append( "/" );
            logString.append( tpost.getDdd()       ); logString.append( "/" );
            logString.append( tpost.getArea_gb()   ); logString.append( "/" );
            logString.append( tpost.getPost_gb()   ); logString.append( "/" );
            logString.append( tpost.getUse_yn()    ); logString.append( "/" );
            logString.append( tpost.getDelivery_point_count()    ); logString.append( "/" );
            logString.append( tpost.getModify_id() ); logString.append( "/" );
            logString.append( tpost.getCity_name() ); logString.append( "/" );
            logString.append( tpost.getNote() ); logString.append( "/" );
            logString.append( tpost.getPost_no()   ); logString.append( "/" );
            logString.append( tpost.getPost_seq()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PostSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PostSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Get TPOST.POST_SEQ MAX VALUE -------------------------------------------------
    /**
    * <PRE>
    * Desc : GET TPOST.POST_SEQ MAX VALUE
    * </PRE>
    * @param   Connection
    * @param   Tpost
    * @return  String
    */
    public String getPostSeq(Connection con, Tpost tpost) throws StoreException{
        PreparedStatement pstmt     = null;
        ResultSet         rset      = null;
        String            post_seq  = "";

        try {
            pstmt = con.prepareStatement(makeSqlPostSeq(tpost));
            pstmt.setString(1,tpost.getPost_no());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpost.getPost_no() ); logString.append( "/" );
            logSave.info(logString.toString());

            rset  = pstmt.executeQuery();
            post_seq = DBUtils.executeQueryS(rset, "");

        }catch(SQLException se){
            logSave.error("[PostSvc.checkDup() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PostSvc.checkDup() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return post_seq;
    }
}
