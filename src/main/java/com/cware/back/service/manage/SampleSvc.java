
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
import com.cware.back.entity.table.Tsampledt;
import com.cware.back.entity.table.Tsamplem;

/**
 * Register Sample Service class
 *
 * @version 1.0, 2006/08/12
 */
public class SampleSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public SampleSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   sample_no                      : Sample Number
    * @param   sample_type                    : Type of Sample
    * @param   start_date                     : Sample give start date
    * @param   end_date                       : Sample give end date
    * @param   memb_gb                        : Type of Member grade
    * @param   sex                            : Type of Sex
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SAMPLE_NO,  \n");
        sb.append("        A.SAMPLE_NAME,  \n");
        sb.append("        A.SAMPLE_TYPE,  \n");
        sb.append("        TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,  \n");
        sb.append("        TO_CHAR(A.END_DATE,   'YYYY/MM/DD') AS END_DATE,  \n");
        sb.append("        A.MEMB_GB,  \n");
        sb.append("        A.SEX,  \n");
        sb.append("        A.REQUEST_QTY,  \n");
        sb.append("        A.MONTH_DUP_QTY,  \n");
        sb.append("        A.CHOICE_QTY  \n");
        sb.append("   FROM TSAMPLEM A  \n");
        sb.append("  WHERE A.SAMPLE_NO   LIKE ? || '%'  \n");    // sample_no
        sb.append("    AND A.SAMPLE_TYPE LIKE ? || '%'  \n");    // sample_type
        sb.append("    AND ( (   A.START_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE(?, 'YYYY/MM/DD')    \n");    // start_date, end_date
        sb.append("           OR A.END_DATE   BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE(?, 'YYYY/MM/DD') )  \n");    // start_date, end_date
        sb.append("       OR (   TO_DATE(?, 'YYYY/MM/DD') BETWEEN A.START_DATE AND A.END_DATE      \n");     // start_date
        sb.append("           OR TO_DATE(?, 'YYYY/MM/DD') BETWEEN A.START_DATE AND A.END_DATE ) )  \n");     // end_date
        sb.append("    AND NVL(A.MEMB_GB, ' ') LIKE ? || '%'  \n");        // memb_gb
        sb.append("    AND NVL(A.SEX    , ' ') LIKE ? || '%'  \n");        // sex
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("sample_no   : " + retrieve.getString("sample_no"));
            log.debug("sample_type : " + retrieve.getString("sample_type"));
            log.debug("start_date  : " + retrieve.getString("start_date"));
            log.debug("end_date    : " + retrieve.getString("end_date"));
            log.debug("memb_gb     : " + retrieve.getString("memb_gb"));
            log.debug("sex         : " + retrieve.getString("sex"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   sample_no                      : Sample Number
    * @return  String
    */
    public String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SAMPLE_NO,  \n");
        sb.append("        A.GOODS_CODE,  \n");
        sb.append("        B.GOODS_NAME,  \n");
        sb.append("        A.BASE_YN  \n");
        sb.append("   FROM TSAMPLEDT A, TGOODS B  \n");
        sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("    AND A.SAMPLE_NO  = ? \n");    // sample_no
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("sample_no   : " + retrieve.getString("sample_no"));
         }
         return sb.toString();
     }
    
    /**
    * <PRE>
    * Desc : Make SQL ( Tsamplem 에 등록 )
    * </PRE>
    * @param   Tsamplem
    * @return  String
    */
    public String makeSqlInsert(Tsamplem samplem) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TSAMPLEM(  \n ");
        sb.append("         SAMPLE_NO,     \n ");
        sb.append("         SAMPLE_NAME,   \n ");
        sb.append("         SAMPLE_TYPE,   \n ");
        sb.append("         START_DATE,    \n ");
        sb.append("         END_DATE,      \n ");
        sb.append("         MEMB_GB,       \n ");
        sb.append("         SEX,           \n ");
        sb.append("         REQUEST_QTY,   \n ");
        sb.append("         MONTH_DUP_QTY, \n ");
        sb.append("         CHOICE_QTY,    \n ");
        sb.append("         INSERT_DATE,   \n ");
        sb.append("         INSERT_ID,     \n ");
        sb.append("         MODIFY_DATE,   \n ");
        sb.append("         MODIFY_ID )    \n ");
        sb.append(" VALUES (   \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ?, \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tsamplem 변경 )
    * </PRE>
    * @param   Tsamplem
    * @return  String
    */
    public String makeSqlUpdate(Tsamplem samplem) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TSAMPLEM SET \n ");
        sb.append("         SAMPLE_NAME   = ?, \n ");
        sb.append("         SAMPLE_TYPE   = ?, \n ");
        sb.append("         START_DATE    = TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("         END_DATE      = TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("         MEMB_GB       = ?, \n ");
        sb.append("         SEX           = ?, \n ");
        sb.append("         REQUEST_QTY   = ?, \n ");
        sb.append("         MONTH_DUP_QTY = ?, \n ");
        sb.append("         CHOICE_QTY    = ?, \n ");
        sb.append("         MODIFY_DATE   = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID     = ?  \n ");
        sb.append("   WHERE SAMPLE_NO     = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tsampledt 에 등록 )
     * </PRE>
     * @param   Tsampledt
     * @return  String
     */
    private final String insertSqlTsampledt = " INSERT INTO TSAMPLEDT(  \n "
                                            + "             SAMPLE_NO,  \n "
                                            + "             GOODS_CODE,  \n "
                                            + "             BASE_YN,  \n "
                                            + "             INSERT_DATE,  \n "
                                            + "             INSERT_ID,  \n "
                                            + "             MODIFY_DATE,  \n "
                                            + "             MODIFY_ID )  \n "
                                            + "    VALUES ( ?,  \n "
                                            + "             ?,  \n "
                                            + "             ?,  \n "
                                            + "             TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),  \n "
                                            + "             ?,  \n "
                                            + "             TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),  \n "
                                            + "             ? )  \n " ;
	private int insertSqlTsampledtLog =  0;
	private String makeSqlInsert(Tsampledt sampledt) throws StoreException{
        //= log SQL -------------------------------------------------
    	if (insertSqlTsampledtLog == 0) {
    	    if (logSave.isDebugEnabled()) {
    		    logSave.debug(insertSqlTsampledt);
    		}
    	   insertSqlTsampledtLog = 1;
    	}
    	return insertSqlTsampledt;
    }

     /**
     * <PRE>
     * Desc : Make SQL ( Tsampledt 변경 )
     * </PRE>
     * @param   Tsampledt
     * @return  String
     */
    private final String updateSqlTsampledt = " UPDATE TSAMPLEDT SET \n "
                                            + "        BASE_YN     = ?,  \n "
                                            + "        MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                            + "        MODIFY_ID   = ?  \n "
                                            + "  WHERE SAMPLE_NO   = ?  \n "
                                            + "    AND GOODS_CODE  = ?  \n ";
    private int updateSqlTsampledtLog =  0;
    private String makeSqlUpdate(Tsampledt sampledt) throws StoreException{
        //= log SQL -------------------------------------------------
    	if (updateSqlTsampledtLog == 0) {
    	    if (logSave.isDebugEnabled()) {
    		    logSave.debug(updateSqlTsampledt);
    		}
    	    updateSqlTsampledtLog = 1;
    	}
    	return updateSqlTsampledt;
     }

     /**
      * <PRE>
      * Desc : Make SQL ( Tsampledt 삭제 )
      * </PRE>
      * @param   Tsampledt
      * @return  String
      */
    private final String deleteSqlTsampledt = " DELETE FROM TSAMPLEDT \n "
                                            + "  WHERE SAMPLE_NO   = ?  \n "
                                            + "    AND GOODS_CODE  = ?  \n ";
	private int deleteSqlTsampledtLog =  0;
	private String makeSqlDelete(Tsampledt sampledt) throws StoreException{
        //= log SQL -------------------------------------------------
    	if (deleteSqlTsampledtLog == 0) {
    	    if (logSave.isDebugEnabled()) {
    		    logSave.debug(deleteSqlTsampledt);
    		}
    	    deleteSqlTsampledtLog = 1;
    	}
    	return deleteSqlTsampledt;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tsampledt all delete )
     * </PRE>
     * @param   sample_no
     * @return  String
     */
   private final String deleteSqlTsampledtAll = " DELETE FROM TSAMPLEDT \n "
                                              + "  WHERE SAMPLE_NO   = ?  \n ";
	private int deleteSqlTsampledtAllLog =  0;
	private String makeSqlDeleteAll(String sample_no) throws StoreException{
       //= log SQL -------------------------------------------------
   	if (deleteSqlTsampledtAllLog == 0) {
   	    if (logSave.isDebugEnabled()) {
   		    logSave.debug(deleteSqlTsampledtAll);
   		}
   	    deleteSqlTsampledtAllLog = 1;
   	}
   	return deleteSqlTsampledtAll;
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
            pstmt.setString(index++, retrieve.getString("sample_no"));
            pstmt.setString(index++, retrieve.getString("sample_type"));
            pstmt.setString(index++, retrieve.getString("start_date"));
            pstmt.setString(index++, retrieve.getString("end_date"));
            pstmt.setString(index++, retrieve.getString("start_date"));
            pstmt.setString(index++, retrieve.getString("end_date"));
            pstmt.setString(index++, retrieve.getString("start_date"));
            pstmt.setString(index++, retrieve.getString("end_date"));
            pstmt.setString(index++, retrieve.getString("memb_gb"));
            pstmt.setString(index++, retrieve.getString("sex"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[SampleSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SampleSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDetail(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("sample_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTDT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[SampleSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SampleSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tsamplem
    * </PRE>
    * @param   Connection
    * @param   Tsamplem
    * @return  int
    */
    public int insert(Connection con, Tsamplem samplem) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(samplem));
            
            int index = 1;

            pstmt.setString(index++, samplem.getSample_no  ());
            pstmt.setString(index++, samplem.getSample_name());
            pstmt.setString(index++, samplem.getSample_type());
            pstmt.setString(index++, samplem.getStart_date ());
            pstmt.setString(index++, samplem.getEnd_date   ());
            pstmt.setString(index++, samplem.getMemb_gb    ());
            pstmt.setString(index++, samplem.getSex        ());
            pstmt.setLong(index++, samplem.getRequest_qty  ());
            pstmt.setLong(index++, samplem.getMonth_dup_qty());
            pstmt.setLong(index++, samplem.getChoice_qty   ());
            pstmt.setString(index++, samplem.getInsert_date());
            pstmt.setString(index++, samplem.getInsert_id  ());
            pstmt.setString(index++, samplem.getModify_date());
            pstmt.setString(index++, samplem.getModify_id  ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( samplem.getSample_no    ()); logString.append( "/" );
            logString.append( samplem.getSample_name  ()); logString.append( "/" );
            logString.append( samplem.getSample_type  ()); logString.append( "/" );
            logString.append( samplem.getStart_date   ()); logString.append( "/" );
            logString.append( samplem.getEnd_date     ()); logString.append( "/" );
            logString.append( samplem.getMemb_gb      ()); logString.append( "/" );
            logString.append( samplem.getSex          ()); logString.append( "/" );
            logString.append( samplem.getRequest_qty  ()); logString.append( "/" );
            logString.append( samplem.getMonth_dup_qty()); logString.append( "/" );
            logString.append( samplem.getChoice_qty   ()); logString.append( "/" );
            logString.append( samplem.getInsert_date  ()); logString.append( "/" );
            logString.append( samplem.getInsert_id    ()); logString.append( "/" );
            logString.append( samplem.getModify_date  ()); logString.append( "/" );
            logString.append( samplem.getModify_id    ()); logString.append( "/" );
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.insert(samplem) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.insert(samplem) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tsamplem
    * </PRE>
    * @param   Connection
    * @param   Tsamplem
    * @return  int
    */
    public int update(Connection con, Tsamplem samplem) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(samplem));
            int index = 1;

            pstmt.setString(index++, samplem.getSample_name  ());
            pstmt.setString(index++, samplem.getSample_type  ());
            pstmt.setString(index++, samplem.getStart_date   ());
            pstmt.setString(index++, samplem.getEnd_date     ());
            pstmt.setString(index++, samplem.getMemb_gb      ());
            pstmt.setString(index++, samplem.getSex          ());
            pstmt.setLong(index++, samplem.getRequest_qty    ());
            pstmt.setLong(index++, samplem.getMonth_dup_qty  ());
            pstmt.setLong(index++, samplem.getChoice_qty     ());
            pstmt.setString(index++, samplem.getModify_date  ());
            pstmt.setString(index++, samplem.getModify_id    ());
            pstmt.setString(index++, samplem.getSample_no    ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( samplem.getSample_name  ()); logString.append( "/" );
            logString.append( samplem.getSample_type  ()); logString.append( "/" );
            logString.append( samplem.getStart_date   ()); logString.append( "/" );
            logString.append( samplem.getEnd_date     ()); logString.append( "/" );
            logString.append( samplem.getMemb_gb      ()); logString.append( "/" );
            logString.append( samplem.getSex          ()); logString.append( "/" );
            logString.append( samplem.getRequest_qty  ()); logString.append( "/" );
            logString.append( samplem.getMonth_dup_qty()); logString.append( "/" );
            logString.append( samplem.getChoice_qty   ()); logString.append( "/" );
            logString.append( samplem.getModify_date  ()); logString.append( "/" );
            logString.append( samplem.getModify_id    ()); logString.append( "/" );
            logString.append( samplem.getSample_no    ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.update(samplem) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.update(samplem) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tsampledt
    * </PRE>
    * @param   Connection
    * @param   Tsampledt
    * @return  int
    */
    public int insert(Connection con, Tsampledt sampledt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(sampledt));
            
            int index = 1;

            pstmt.setString(index++, sampledt.getSample_no  ());
            pstmt.setString(index++, sampledt.getGoods_code ());
            pstmt.setString(index++, sampledt.getBase_yn    ());
            pstmt.setString(index++, sampledt.getInsert_date());
            pstmt.setString(index++, sampledt.getInsert_id  ());
            pstmt.setString(index++, sampledt.getModify_date());
            pstmt.setString(index++, sampledt.getModify_id  ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sampledt.getSample_no   ()); logString.append( "/" );
            logString.append( sampledt.getGoods_code  ()); logString.append( "/" );
            logString.append( sampledt.getBase_yn     ()); logString.append( "/" );
            logString.append( sampledt.getInsert_date ()); logString.append( "/" );
            logString.append( sampledt.getInsert_id   ()); logString.append( "/" );
            logString.append( sampledt.getModify_date ()); logString.append( "/" );
            logString.append( sampledt.getModify_id   ()); logString.append( "/" );
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.insert(sampledt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.insert(sampledt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tsampledt
    * </PRE>
    * @param   Connection
    * @param   Tsampledt
    * @return  int
    */
    public int update(Connection con, Tsampledt sampledt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(sampledt));
            int index = 1;

            pstmt.setString(index++, sampledt.getBase_yn     ());
            pstmt.setString(index++, sampledt.getModify_date ());
            pstmt.setString(index++, sampledt.getModify_id   ());
            pstmt.setString(index++, sampledt.getSample_no   ());
            pstmt.setString(index++, sampledt.getGoods_code  ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sampledt.getBase_yn    ()); logString.append( "/" );
            logString.append( sampledt.getModify_date()); logString.append( "/" );
            logString.append( sampledt.getModify_id  ()); logString.append( "/" );
            logString.append( sampledt.getSample_no  ()); logString.append( "/" );
            logString.append( sampledt.getGoods_code ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.update(sampledt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.update(sampledt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tsampledt
    * </PRE>
    * @param   Connection
    * @param   Tsampledt
    * @return  int
    */
    public int delete(Connection con, Tsampledt sampledt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(sampledt));
            int index = 1;

            pstmt.setString(index++, sampledt.getSample_no   ());
            pstmt.setString(index++, sampledt.getGoods_code  ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sampledt.getSample_no  ()); logString.append( "/" );
            logString.append( sampledt.getGoods_code ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.delete(sampledt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.delete(sampledt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    
    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tsampledt
    * </PRE>
    * @param   Connection
    * @param   Tsampledt
    * @return  int
    */
    public int deleteDtAll(Connection con, String sample_no) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteAll(sample_no));
            int index = 1;

            pstmt.setString(index++, sample_no);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sample_no); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleSvc.delete(sampledt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleSvc.delete(sampledt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
