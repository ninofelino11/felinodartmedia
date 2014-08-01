
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
import com.cware.back.entity.table.Tskintestdt;
import com.cware.back.entity.table.Tskintestm;

/**
 * Register Skintest Service class
 *
 * @version 1.0, 2007/02/20
 */
public class SkintestSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public SkintestSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   skintest_no                      : Skintest Number
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SKINTEST_NO,          \n");
        sb.append("        A.SKINTEST_NAME,        \n");
        sb.append("        A.INSERT_ID,            \n");
        sb.append("        A.INSERT_DATE           \n");
        sb.append("   FROM TSKINTESTM A            \n");
        sb.append("  WHERE A.SKINTEST_NO LIKE ? || '%'  \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("skintest_no   : " + retrieve.getString("skintest_no"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   skintest_no                      : Skintest Number
    * @return  String
    */
    public String makeSqlQ( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SKINTEST_NO,         \n");
        sb.append("        A.QUESTION_NO,         \n");
        sb.append("        A.QUESTION,            \n");
        sb.append("        A.QUESTION_IMAGE,      \n");
        sb.append("        '' AS SOURCE_IMAGE,    \n");
        sb.append("        A.INSERT_ID,           \n");
        sb.append("        A.INSERT_DATE          \n");
        sb.append("   FROM TSKINTESTM1 A          \n");
        sb.append("  WHERE A.SKINTEST_NO LIKE ? || '%' \n");
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("skintest_no   : " + retrieve.getString("skintest_no"));
         }
         return sb.toString();
     }
    
    
    public String makeSqlA( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SKINTEST_NO,         \n");  
        sb.append("        A.QUESTION_NO,         \n");
        sb.append("        A.ANSWER_NO,           \n");
        sb.append("        A.ANSWER,              \n");
        sb.append("        A.ANSWER_TYPE,         \n");
        sb.append("        A.LINK_QUESTION_NO,    \n");
        sb.append("        A.LINK_ANSWER_NO,      \n");
        sb.append("        A.INSERT_DATE,         \n");
        sb.append("        A.INSERT_ID            \n");
        sb.append("   FROM TSKINTESTDT A          \n");
        sb.append("  WHERE A.SKINTEST_NO = ? \n");
        sb.append("    AND A.QUESTION_NO = ?      \n");
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("skintest_no   : " + retrieve.getString("skintest_no"));
             log.debug("question_no   : " + retrieve.getString("question_no"));
         }
         return sb.toString();
     }
      
    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tskintestm )
     * </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlSkintestm = "   INSERT INTO TSKINTESTM ( \n "
     								+"                    SKINTEST_NO,     \n "
     								+"                    SKINTEST_NAME,   \n "     								
     								+"                    INSERT_DATE,     \n "
     								+"                    INSERT_ID)       \n "
     								+"             VALUES (   \n "
     								+"                    ?, \n "
     								+"                    ?, \n "
     								+"                    SYSDATE, \n "
     								+"                    ?) \n " ;
     private int insertSqlSkintestmLog =  0;     

   
     private final String updateSqlTskintestm = " UPDATE TSKINTESTM SET \n "
                                             + "         SKINTEST_NAME  = ?  \n "
                                             + "   WHERE SKINTEST_NO    = ?  \n ";
     private int updateSqlTskintestmLog =  0;
    
     private String makeSqlUpdate(Tskintestm tskintestm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTskintestmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTskintestm);
     		}
     	    updateSqlTskintestmLog = 1;
     	}
     	return updateSqlTskintestm;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( Delete Tskintestm 삭제 )
       * </PRE>
       * @param   Tskintestm
       * @return  String
       */
     private final String deleteSqlTskintestm = " DELETE FROM TSKINTESTM \n "
                                              + "  WHERE SKINTEST_NO   = ?  \n " ;
 	private int deleteSqlTskintestmLog =  0;
 	private String makeSqlDelete(Tskintestm tskintestm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTskintestmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTskintestm);
     		}
     	    deleteSqlTskintestmLog = 1;
     	}
     	return deleteSqlTskintestm;
     }

    /**
     * <PRE>
     * Desc : Make SQL ( Delete Tskintestm1 삭제 )
     * </PRE>
     * @param   Tskintestm
     * @return  String
     */
   private final String deleteSqlTskintestQ = " DELETE FROM TSKINTESTM1 \n "
                                            + "  WHERE SKINTEST_NO   = ?  \n "; 
   											
   
	private int deleteSqlTskintestQLog =  0;
	private String makeSqlDeleteQ(Tskintestm tskintestm) throws StoreException{
       //= log SQL -------------------------------------------------
   	if (deleteSqlTskintestQLog == 0) {
   	    if (logSave.isDebugEnabled()) {
   		    logSave.debug(deleteSqlTskintestQ);
   		}
   	    deleteSqlTskintestQLog = 1;
   	}
   	return deleteSqlTskintestQ;
   }
	
    /**
     * <PRE>
     * Desc : Make SQL ( Delete Tskintestmdt 삭제 )
     * </PRE>
     * @param   Tskintestm
     * @return  String
     */
   private final String deleteSqlTskintestA = " DELETE FROM TSKINTESTDT \n "
                                            + "  WHERE SKINTEST_NO   = ?  \n "; 

   
	private int deleteSqlTskintestALog =  0;
	private String makeSqlDeleteA(Tskintestm tskintestm) throws StoreException{
       //= log SQL -------------------------------------------------
   	if (deleteSqlTskintestALog == 0) {
   	    if (logSave.isDebugEnabled()) {
   		    logSave.debug(deleteSqlTskintestA);
   		}
   	    deleteSqlTskintestALog = 1;
   	}
   	return deleteSqlTskintestA;
   } 		

    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tskintestm1 )
     * </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlSkintestm1 = "INSERT INTO TSKINTESTM1 ( \n "
     								+"                  SKINTEST_NO,     \n "
     								+"                  QUESTION_NO,   \n "
     								+"                  QUESTION,     \n "
     								+"                  QUESTION_IMAGE,   \n "								
     								+"                  INSERT_DATE,     \n "
     								+"                  INSERT_ID)       \n "
     								+"           VALUES (   \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "	     								
     								+"                  SYSDATE, \n "
     								+"                  ?) \n " ;
     private int insertSqlSkintestm1Log =  0;     

    /**
     *
     * @param tskintestm1
     * @return
     * @throws StoreException
     */
     private final String updateSqlTskintestm1 = " UPDATE  TSKINTESTM1 SET \n "
                                               + "         QUESTION        = ?,  \n "
                                               + "         QUESTION_IMAGE  = ?  \n "                                               
                                               + "   WHERE SKINTEST_NO     = ?  \n "
     										   + "     AND QUESTION_NO     = ?  \n ";     
     private int updateSqlTskintestm1Log =  0;
      /**
       * <PRE>
       * Desc : Make SQL ( Delete TskintestM1 삭제 )
       * </PRE>
       * @param   Tskintestm
       * @return  String
       */
     private final String deleteSqlTskintestm1 = " DELETE FROM TSKINTESTM1 \n "
                                               + "  WHERE SKINTEST_NO   = ?  \n " 
     										   + "    AND QUESTION_NO   = ?  \n " ;
 	private int deleteSqlTskintestm1Log =  0;
 	
    /**
     * <PRE>
     * Desc : Make SQL ( Delete TskintestAnswer 삭제 )
     * </PRE>
     * @param   Tskintestm
     * @return  String
     */
   private final String deleteSqlTskintestm1A = " DELETE FROM TSKINTESTDT \n "
                                             + "  WHERE SKINTEST_NO   = ?  \n " 
   										     + "    AND QUESTION_NO   = ?  \n " ;
	private int deleteSqlTskintestm1ALog =  0;
	
  
     private final String insertSqlTskintestdt = "   INSERT INTO TSKINTESTDT ( \n "
     							              	+"          SKINTEST_NO,     \n "
     								            +"          QUESTION_NO,   \n "
     							 	            +"          ANSWER_NO,     \n "
     								            +"          ANSWER,   \n "
     								            +"          ANSWER_TYPE,     \n "
     								            +"          LINK_QUESTION_NO,   \n "
     								            +"          LINK_ANSWER_NO,     \n "     								
     								            +"          INSERT_DATE,     \n "
     								            +"          INSERT_ID)       \n "
     								            +"  VALUES (   \n "
     								            +"          ?, \n "
     								            +"          ?, \n "
     								            +"          ?, \n "
     								            +"          ?, \n "
     								            +"          ?, \n "
     								            +"          ?, \n "
     								            +"          ?, \n "     								            
     								            +"          SYSDATE, \n "
     								            +"          ?) \n " ;
     private int insertSqlTskintestdtLog =  0;     

     public String makeSqlInsert(Tskintestdt tskintestdt) throws StoreException{
 	   	//= log SQL -------------------------------------------------
 		if (insertSqlTskintestdtLog == 0) {
 			if (logSave.isDebugEnabled()) {
 				logSave.debug(insertSqlTskintestdt);
 			}
 			insertSqlTskintestdtLog = 1;
 		}
 		return insertSqlTskintestdt;
 	}
    
     /**
      * <PRE>
      * Desc : Make SQL ( Tskintestdt 변경 )
      * </PRE>
      * @param   Tskintestm
      * @return  String
      */
     private final String updateSqlTskintestdt = " UPDATE TSKINTESTDT SET \n "
                                               + "        ANSWER       = ?,  \n "
                                               + "        ANSWER_TYPE  = ?  \n "                                             
                                               + "  WHERE SKINTEST_NO  = ?   \n "
     										   + "    AND QUESTION_NO  = ?   \n "
     										   + "    AND ANSWER_NO    = ?   \n ";
     private int updateSqlTskintestdtLog =  0;
     private String makeSqlUpdate(Tskintestdt tskintestdt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTskintestdtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTskintestdt);
     		}
     	    updateSqlTskintestdtLog = 1;
     	}
     	return updateSqlTskintestdt;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( Tskintestdt 삭제 )
       * </PRE>
       * @param   Tskintestdt
       * @return  String
       */
     private final String deleteSqlTskintestdt = " DELETE FROM TSKINTESTDT \n "
                                               + "  WHERE SKINTEST_NO   = ?  \n " 
     										   + "    AND QUESTION_NO   = ?  \n " 
     										   + "    AND ANSWER_NO     = ?  \n " ;
 	private int deleteSqlTskintestdtLog =  0;
 	private String makeSqlDelete(Tskintestdt Tskintestdt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTskintestdtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTskintestdt);
     		}
     	    deleteSqlTskintestdtLog = 1;
     	}
     	return deleteSqlTskintestdt;
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
            pstmt.setString(index++, retrieve.getString("skintest_no"));


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
            pstmt.setString(index++, retrieve.getString("skintest_no"));

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
    
    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveA(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlA(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("skintest_no"));
            pstmt.setString(index++, retrieve.getString("question_no"));            

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTA", makeSheet(rset));

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
    * Desc : Insert Tskintestm1
    * </PRE>
    * @param   Connection
    * @param   Tskintestm1
    * @return  int
    */
    public int insert(Connection con, Tskintestm tskintestm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tskintestm));
            
            int index = 1;

            pstmt.setString(index++, tskintestm.getSkintest_no  ());
            pstmt.setString(index++, tskintestm.getSkintest_name());
            pstmt.setString(index++, tskintestm.getInsert_id());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestm.getSkintest_no    ()); logString.append( "/" );
            logString.append( tskintestm.getSkintest_name  ()); logString.append( "/" );
            logString.append( tskintestm.getInsert_id   ()); logString.append( "/" );

            
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
    * Desc : Update Tskintestm
    * </PRE>
    * @param   Connection
    * @param   Tskintestm
    * @return  int
    */
    public int update(Connection con, Tskintestm tskintestm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tskintestm));
                      
            int index = 1;

            pstmt.setString(index++, tskintestm.getSkintest_name  ());
            pstmt.setString(index++, tskintestm.getSkintest_no  ());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestm.getSkintest_name  ()); logString.append( "/" );
            logString.append( tskintestm.getSkintest_no  ()); logString.append( "/" );            

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
    * Desc : Delete Tskintestm
    * </PRE>
    * @param   Connection
    * @param   Tskintestm
    * @return  int
    */
    public int delete(Connection con, Tskintestm tskintestm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tskintestm));
            int index = 1;

            pstmt.setString(index++, tskintestm.getSkintest_no   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestm.getSkintest_no  ()); logString.append( "/" );

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
    * Desc : Delete Tskintestm1
    * </PRE>
    * @param   Connection
    * @param   Tskintestm
    * @return  int
    */
    public int deleteQ(Connection con, Tskintestm tskintestm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteQ(tskintestm));
            int index = 1;

            pstmt.setString(index++, tskintestm.getSkintest_no   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestm.getSkintest_no  ()); logString.append( "/" );

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
    
 
    public int insert(Connection con, Tskintestdt tskintestdt) {
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tskintestdt));
            
            int index = 1;

            pstmt.setString(index++, tskintestdt.getSkintest_no       ());
            pstmt.setString(index++, tskintestdt.getQuestion_no       ());
            pstmt.setString(index++, tskintestdt.getAnswer_no         ());
            pstmt.setString(index++, tskintestdt.getAnswer            ());
            pstmt.setString(index++, tskintestdt.getAnswer_type       ());
            pstmt.setString(index++, tskintestdt.getLink_question_no  ());
            pstmt.setString(index++, tskintestdt.getLink_answer_no    ());            
            pstmt.setString(index++, tskintestdt.getInsert_id         ());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestdt.getSkintest_no       ()); logString.append( "/" );
            logString.append( tskintestdt.getQuestion_no       ()); logString.append( "/" );
            logString.append( tskintestdt.getAnswer_no         ()); logString.append( "/" );
            logString.append( tskintestdt.getAnswer            ()); logString.append( "/" );
            logString.append( tskintestdt.getAnswer_type       ()); logString.append( "/" );
            logString.append( tskintestdt.getLink_question_no  ()); logString.append( "/" );
            logString.append( tskintestdt.getLink_answer_no    ()); logString.append( "/" );
            logString.append( tskintestdt.getInsert_id         ()); logString.append( "/" );
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
       //     throw new StoreException(e.getMessage());
        }finally {
    //        DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tskintestdt
    * </PRE>
    * @param   Connection
    * @param   Tskintestdt
    * @return  int
    */
    public int update(Connection con, Tskintestdt tskintestdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tskintestdt));
            int index = 1;

            pstmt.setString(index++, tskintestdt.getAnswer       ());
            pstmt.setString(index++, tskintestdt.getAnswer_type  ());
            pstmt.setString(index++, tskintestdt.getSkintest_no  ());
            pstmt.setString(index++, tskintestdt.getQuestion_no  ());            
            pstmt.setString(index++, tskintestdt.getAnswer_no    ());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestdt.getAnswer       ()); logString.append( "/" );
            logString.append( tskintestdt.getAnswer_type  ()); logString.append( "/" );
            logString.append( tskintestdt.getSkintest_no  ()); logString.append( "/" );
            logString.append( tskintestdt.getQuestion_no  ()); logString.append( "/" );
            logString.append( tskintestdt.getAnswer_no    ()); logString.append( "/" );            

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
    * Desc : Delete Tskintestdt
    * </PRE>
    * @param   Connection
    * @param   Tskintestdt
    * @return  int
    */
    public int delete(Connection con, Tskintestdt tskintestdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tskintestdt));
            int index = 1;

            pstmt.setString(index++, tskintestdt.getSkintest_no   ());
            pstmt.setString(index++, tskintestdt.getQuestion_no   ());
            pstmt.setString(index++, tskintestdt.getAnswer_no     ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tskintestdt.getAnswer_no  ()); logString.append( "/" );

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

    private String makeSqlInsert(Tskintestm tskintestm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
