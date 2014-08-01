
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
import com.cware.back.entity.table.Tgoodspolldt;
import com.cware.back.entity.table.Tgoodspollm;

/**
 * Register Goodspoll Service class
 *
 * @version 1.0, 2007/2/21
 */
public class GoodspollSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodspollSvc() {}

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

        sb.append(" SELECT A.POLL_NO,         \n");
        sb.append("        A.POLL_TITLE,      \n");
        sb.append("        A.POLL_IMAGE,      \n");
        sb.append("        TO_CHAR(A.START_DATE,'YYYY/MM/DD HH24:MI:SS') AS START_DATE,      \n");
        sb.append("        TO_CHAR(A.END_DATE,'YYYY/MM/DD HH24:MI:SS') AS END_DATE,        \n");
        sb.append("        A.DISPLAY_YN,      \n");
        sb.append("        A.INSERT_DATE,     \n");
        sb.append("        A.INSERT_ID,       \n");
        sb.append("        A.MODIFY_DATE,     \n");
        sb.append("        A.MODIFY_ID,       \n");
        sb.append("        A.POINT_AMT       \n");
        sb.append("   FROM TGOODSPOLLM A      \n");
        sb.append("  WHERE A.END_DATE      >= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')     \n ");
        sb.append("    AND A.START_DATE    <= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')     \n ");
        sb.append("    AND A.POLL_NO LIKE ? || '%'  \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("sum_fromDate   : " + retrieve.getString("sum_fromDate"));
            log.debug("sum_toDate     : " + retrieve.getString("sum_toDate"));
            log.debug("poll_no  : " + retrieve.getString("poll_no"));            
            
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
    public String makeSqlQ( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
 
        sb.append("SELECT B.POLL_NO,                  \n");
        sb.append("       B.QUESTION_NO,              \n");
        sb.append("       B.QUESTION,                 \n");
        sb.append("       B.QUESTION_IMAGE,           \n");
        sb.append("       B.GOODS_CODE,               \n");
        sb.append("       B.GOODSDT_CODE,             \n");
        sb.append("       B.INSERT_DATE,              \n");
        sb.append("       B.INSERT_ID,                \n");
        sb.append("       C.GOODS_NAME,               \n");
        sb.append("       NVL(B.POLL_CNT, 0) AS POLL_CNT  \n");
        sb.append("  FROM TGOODSPOLLM A,              \n");
        sb.append("       TGOODSPOLLDT B,             \n");
        sb.append("       TGOODS C                    \n");
        sb.append(" WHERE A.POLL_NO = B.POLL_NO       \n");
        sb.append("   AND B.GOODS_CODE = C.GOODS_CODE \n");
        sb.append("   AND A.POLL_NO = ?               \n");        
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("poll_no  : " + retrieve.getString("poll_no"));
         }
         return sb.toString();
     }    
    
    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tgoodspollm )
     * </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlGoodspollm = "   INSERT INTO TGOODSPOLLM ( \n "
     								+"                    POLL_NO,       \n "
     								+"                    POLL_TITLE,    \n "     								
     								+"                    POLL_IMAGE,    \n "
     								+"                    START_DATE,    \n "
     								+"                    END_DATE,      \n "     								
     								+"                    DISPLAY_YN,    \n "
     								+"                    POINT_AMT,     \n "     								
     								+"                    INSERT_DATE,   \n "     								
     								+"                    INSERT_ID,     \n "   								
     								+"                    MODIFY_DATE,   \n "
     								+"                    MODIFY_ID)     \n "       								
     								+"             VALUES (              \n "
     								+"                    ?,             \n "
     								+"                    ?,             \n "
     								+"                    ?,             \n "
     								+"                    TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n "
     								+"                    TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n "
     								+"                    ?,             \n "
     								+"                    ?,             \n "
     								+"                    SYSDATE,       \n "     								
     								+"                    ?,             \n "     								
     								+"                    SYSDATE,       \n " 
									+"                    ?)             \n " ;     
     
     
     private int insertSqlTgoodspollmLog =  0;

     public String makeSqlInsert(Tgoodspollm tgoodspollm) throws StoreException{
 	   	//= log SQL -------------------------------------------------
 		if (insertSqlTgoodspollmLog == 0) {
 			if (logSave.isDebugEnabled()) {
 				logSave.debug(insertSqlGoodspollm);
 			}
 			insertSqlTgoodspollmLog = 1;
 		}
 		return insertSqlGoodspollm;
 	}
     
     /**
      * <PRE>
      * Desc : Make SQL ( Update Tgoodspollm)
      * </PRE>
      * @param   Tgoodspolldt
      * @return  String
      */
     private final String updateSqlTgoodspollm = " UPDATE  TGOODSPOLLM SET \n "
                                               + "         POLL_TITLE      = ?,  \n "
                                               + "         POLL_IMAGE      = ?,  \n "
                                               + "         START_DATE      = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n "
                                               + "         END_DATE        = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n "
                                               + "         DISPLAY_YN      = ?,  \n "
                                               + "         POINT_AMT       = ?  \n "                                                  
                                               + "   WHERE POLL_NO         = ?  \n ";
        
     private int updateSqlTgoodspollmLog =  0;
     private String makeSqlUpdate(Tgoodspollm tgoodspollm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTgoodspollmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTgoodspollm);
     		}
     	   updateSqlTgoodspollmLog = 1;
     	}
     	return updateSqlTgoodspollm;
      }     

      /**
       * <PRE>
       * Desc : Make SQL ( Delete Tgoodspollm )
       * </PRE>
       * @param   Tgoodspollm
       * @return  String
       */
     private final String deleteSqlTgoodspollm = " DELETE FROM TGOODSPOLLM \n "
                                               + "  WHERE POLL_NO = ?  \n " ;
 	private int deleteSqlTgoodspollmLog =  0;
 	private String makeSqlDelete(Tgoodspollm tgoodspollm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTgoodspollmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTgoodspollm);
     		}
     	   deleteSqlTgoodspollmLog = 1;
     	}
     	return deleteSqlTgoodspollm;
     }

    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tgoodspolldt )
     * </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlGoodspolldt = "INSERT INTO TGOODSPOLLDT ( \n "
     				 				+"                  POLL_NO,           \n "
     								+"                  QUESTION_NO,       \n "
     								+"                  QUESTION,          \n "
     								+"                  QUESTION_IMAGE,    \n "								
     								+"                  GOODS_CODE,        \n "
     								+"                  GOODSDT_CODE,      \n "
     								+"                  POLL_CNT,          \n "								
     								+"                  INSERT_ID,         \n "
     								+"                  INSERT_DATE)       \n "     								
     								+"           VALUES (   \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "	     								
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "	     								
     								+"                  ?, \n "     								
     								+"                  SYSDATE) \n " ;
     
     	private int insertSqlGoodspolldtLog =  0;     

     public String makeSqlInsert(Tgoodspolldt tgoodspolldt) throws StoreException{
 	   	//= log SQL -------------------------------------------------
 		if (insertSqlGoodspolldtLog == 0) {
 			if (logSave.isDebugEnabled()) {
 				logSave.debug(insertSqlGoodspolldt);
 			}
 			insertSqlGoodspolldtLog = 1;
 		}
 		return insertSqlGoodspolldt;
 	}   
     
     /**
      * <PRE>
      * Desc : Make SQL ( Update Tgoodspolldt )
      * </PRE>
      * @param   Tgoodspolldt
      * @return  String
      */
     private final String updateSqlTgoodspolldt = " UPDATE TGOODSPOLLDT SET \n "
                                               + "         QUESTION        = ?,  \n "
                                               + "         QUESTION_IMAGE  = ?,  \n "
                                               + "         GOODS_CODE      = ?  \n "                                                       
                                               + "   WHERE QUESTION_NO     = ?  \n "
     										   + "     AND POLL_NO         = ?  \n ";     
     private int updateSqlTgoodspolldtLog =  0;
     private String makeSqlUpdate(Tgoodspolldt tgoodspolldt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTgoodspolldtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTgoodspolldt);
     		}
     	   updateSqlTgoodspolldtLog = 1;
     	}
     	return updateSqlTgoodspolldt;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( Delete Tgoodspolldt )
       * </PRE>
       * @param   Tgoodspolldt
       * @return  String
       */
     private final String deleteSqlTgoodspolldt = " DELETE FROM TGOODSPOLLDT \n "
                                                + "  WHERE QUESTION_NO   = ?  \n " 
     											+ "    AND POLL_NO       = ?  \n " ;
 	private int deleteSqlTgoodspolldtLog =  0;
 	private String makeSqlDelete(Tgoodspolldt tgoodspolldt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTgoodspolldtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTgoodspolldt);
     		}
     	   deleteSqlTgoodspolldtLog = 1;
     	}
     	return deleteSqlTgoodspolldt;
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
            pstmt.setString(index++, retrieve.getString("sum_fromDate"));
            pstmt.setString(index++, retrieve.getString("sum_toDate"));               
            pstmt.setString(index++, retrieve.getString("poll_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

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
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveQ(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlQ(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("poll_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTQ", makeSheet(rset));

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
    * Desc : Insert Tgoodspollm
    * </PRE>
    * @param   Connection
    * @param   Tgoodspollm
    * @return  int
    */
    public int insert(Connection con, Tgoodspollm tgoodspollm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodspollm));
            
            int index = 1;

            pstmt.setString(index++, tgoodspollm.getPoll_no  ());
            pstmt.setString(index++, tgoodspollm.getPoll_title());
            pstmt.setString(index++, tgoodspollm.getPoll_image());
            pstmt.setString(index++, tgoodspollm.getStart_date  ());
            pstmt.setString(index++, tgoodspollm.getEnd_date());
            pstmt.setString(index++, tgoodspollm.getDisplay_yn());
            pstmt.setString(index++, tgoodspollm.getPoint_amt  ());
            pstmt.setString(index++, tgoodspollm.getInsert_id());   
            pstmt.setString(index++, tgoodspollm.getModify_id());                    
                        
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspollm.getPoll_no       ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoll_title    ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoll_image    ()); logString.append( "/" );
            logString.append( tgoodspollm.getStart_date    ()); logString.append( "/" );
            logString.append( tgoodspollm.getEnd_date      ()); logString.append( "/" );
            logString.append( tgoodspollm.getDisplay_yn    ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoint_amt     ()); logString.append( "/" );
            logString.append( tgoodspollm.getInsert_id     ()); logString.append( "/" );
            logString.append( tgoodspollm.getModify_id     ()); logString.append( "/" );           
            
            logSave.info("\n" + logString.toString());
                              
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
    * Desc : Update tgoodspolldt
    * </PRE>
    * @param   Connection
    * @param   Tgoodspolldt
    * @return  int
    */
    public int update(Connection con, Tgoodspollm tgoodspollm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodspollm));
            int index = 1;

            pstmt.setString(index++, tgoodspollm.getPoll_title    ());
            pstmt.setString(index++, tgoodspollm.getPoll_image   ());
            pstmt.setString(index++, tgoodspollm.getStart_date   ());            
            pstmt.setString(index++, tgoodspollm.getEnd_date     ());
            pstmt.setString(index++, tgoodspollm.getDisplay_yn   ());
            pstmt.setString(index++, tgoodspollm.getPoint_amt    ());            
            pstmt.setString(index++, tgoodspollm.getPoll_no      ());                        

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspollm.getPoll_title    ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoll_image    ()); logString.append( "/" );
            logString.append( tgoodspollm.getStart_date    ()); logString.append( "/" );
            logString.append( tgoodspollm.getEnd_date      ()); logString.append( "/" );
            logString.append( tgoodspollm.getDisplay_yn    ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoint_amt     ()); logString.append( "/" );
            logString.append( tgoodspollm.getPoll_no       ()); logString.append( "/" );            
                 
            logSave.info("\n" + logString.toString());

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
    
    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodspollm
    * </PRE>
    * @param   Connection
    * @param   Tgoodspollm
    * @return  int
    */
    public int delete(Connection con, Tgoodspollm tgoodspollm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tgoodspollm));
            int index = 1;

            pstmt.setString(index++, tgoodspollm.getPoll_no   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspollm.getPoll_no  ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

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
    
    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoodspolldt
    * </PRE>
    * @param   Connection
    * @param   Tgoodspolldt
    * @return  int
    */
    public int insert(Connection con, Tgoodspolldt tgoodspolldt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodspolldt));
            
            int index = 1;

            pstmt.setString(index++, tgoodspolldt.getPoll_no  ());
            pstmt.setString(index++, tgoodspolldt.getQuestion_no());
            pstmt.setString(index++, tgoodspolldt.getQuestion  ());
            pstmt.setString(index++, tgoodspolldt.getQuestion_image());
            pstmt.setString(index++, tgoodspolldt.getGoods_code());
            pstmt.setString(index++, tgoodspolldt.getGoodsdt_code  ());
            pstmt.setString(index++, tgoodspolldt.getPoll_cnt());                        
            pstmt.setString(index++, tgoodspolldt.getInsert_id());                        
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspolldt.getPoll_no         ()); logString.append( "/" );
            logString.append( tgoodspolldt.getQuestion_no     ()); logString.append( "/" );
            logString.append( tgoodspolldt.getQuestion        ()); logString.append( "/" );
            logString.append( tgoodspolldt.getQuestion_image  ()); logString.append( "/" );
            logString.append( tgoodspolldt.getGoods_code      ()); logString.append( "/" );
            logString.append( tgoodspolldt.getGoodsdt_code    ()); logString.append( "/" );
            logString.append( tgoodspolldt.getPoll_cnt        ()); logString.append( "/" );
            logString.append( tgoodspolldt.getInsert_id       ()); logString.append( "/" );                           
            
            logSave.info("\n" + logString.toString());
                              
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
    * Desc : Update tgoodspolldt
    * </PRE>
    * @param   Connection
    * @param   Tgoodspolldt
    * @return  int
    */
    public int update(Connection con, Tgoodspolldt tgoodspolldt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodspolldt));
            int index = 1;

            pstmt.setString(index++, tgoodspolldt.getQuestion       ());
            pstmt.setString(index++, tgoodspolldt.getQuestion_image ());
            pstmt.setString(index++, tgoodspolldt.getGoods_code     ());            
            pstmt.setString(index++, tgoodspolldt.getQuestion_no    ());
            pstmt.setString(index++, tgoodspolldt.getPoll_no        ());    
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspolldt.getQuestion        ()); logString.append( "/" );
            logString.append( tgoodspolldt.getQuestion_image  ()); logString.append( "/" );
            logString.append( tgoodspolldt.getGoods_code      ()); logString.append( "/" );
            logString.append( tgoodspolldt.getQuestion_no     ()); logString.append( "/" );
            logString.append( tgoodspolldt.getPoll_no         ()); logString.append( "/" );
                 
            logSave.info("\n" + logString.toString());

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
    
    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete tgoodspolldt
    * </PRE>
    * @param   Connection
    * @param   tgoodspolldt
    * @return  int
    */
    public int delete(Connection con, Tgoodspolldt tgoodspolldt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tgoodspolldt));
            int index = 1;

            pstmt.setString(index++, tgoodspolldt.getQuestion_no   ());
            pstmt.setString(index++, tgoodspolldt.getPoll_no       ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodspolldt.getQuestion_no  ()); logString.append( "/" );
            logString.append( tgoodspolldt.getPoll_no      ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

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