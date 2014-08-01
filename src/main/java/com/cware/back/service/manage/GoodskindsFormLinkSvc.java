
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
import com.cware.back.entity.table.Tform;

/**
 * Register Goodkinds Form Link Service class
 *
 * @version 1.0, 2006/08/17
 */
public class GoodskindsFormLinkSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodskindsFormLinkSvc() {}

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

        sb.append("  SELECT LGROUP,  \n");
        sb.append("         MGROUP,  \n");
        sb.append("         SGROUP,  \n");
        sb.append("         DGROUP,  \n");
        sb.append("         LGROUP_NAME,  \n");
        sb.append("         MGROUP_NAME,  \n");
        sb.append("         SGROUP_NAME,  \n");
        sb.append("         DGROUP_NAME  \n");
        sb.append("    FROM TGOODSKINDS  \n");
        sb.append("   WHERE LGROUP LIKE ?  \n");
        sb.append("     AND MGROUP LIKE ?  \n");
        sb.append("     AND SGROUP LIKE ?  \n");
        sb.append("     AND DGROUP LIKE ?  \n");

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

        sb.append("  SELECT LGROUP,  \n");
        sb.append("         MGROUP,  \n");
        sb.append("         SGROUP,  \n");
        sb.append("         DGROUP,  \n");
        sb.append("         FORM_CODE,  \n");
        sb.append("         FORM_NAME,  \n");
        sb.append("         TO_CHAR(INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("         INSERT_ID,  \n");
        sb.append("         TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("         MODIFY_ID  \n");
        sb.append("    FROM TFORM      \n");
        sb.append("   WHERE LGROUP = ?  \n");
        sb.append("     AND MGROUP = ?  \n");
        sb.append("     AND SGROUP = ?  \n");
        sb.append("     AND DGROUP = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TFORM )
    * </PRE>
    * @param   Tform
    * @return  String
    */
    private final String insertSqlTform =  "  INSERT INTO TFORM ( \n " 
                                          +"         LGROUP, \n "
                                          +"         MGROUP, \n "
                                          +"         SGROUP, \n "
                                          +"         DGROUP, \n "
                                          +"         FORM_CODE, \n "
                                          +"         FORM_NAME,   \n "
                                          +"         INSERT_DATE, \n "
                                          +"         INSERT_ID,   \n "
                                          +"         MODIFY_DATE, \n "
                                          +"         MODIFY_ID)   \n "
                                          +" VALUES (   \n "
                                          +"         ?, \n "
                                          +"         ?, \n "
                                          +"         ?, \n "
                                          +"         ?, \n "
                                          +"         ?, \n "
                                          +"         ?, \n "
                                          +"         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                          +"         ?, \n "
                                          +"         TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                          +"         ?) \n ";
                                     
    private int insertSqlLogTform =  0;
    
    private String makeSqlInsert(Tform tform) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTform == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTform);
            }            
            insertSqlLogTform= 1;   
        }   
        return insertSqlTform;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TFORM )
    * </PRE>
    * @param   Tform
    * @return  String
    */
    private final String updateSqlTform = " UPDATE TFORM SET \n " 
                                        + "        FORM_NAME   = ?, \n " 
                                        + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n " 
                                        + "        MODIFY_ID   = ? \n " 
                                        + "  WHERE LGROUP      = ? \n " 
                                        + "    AND MGROUP      = ? \n " 
                                        + "    AND SGROUP      = ? \n " 
                                        + "    AND DGROUP      = ? \n " 
                                        + "    AND FORM_CODE   = ? \n " ;
                                     
    private int updateSqlLogTform =  0;
    
    private String makeSqlUpdate(Tform tform) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTform == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTform);
            }            
            updateSqlLogTform = 1;   
        }   
        return updateSqlTform;
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
            pstmt.setString(index++,retrieve.getString("lgroup"));
            pstmt.setString(index++,retrieve.getString("mgroup"));
            pstmt.setString(index++,retrieve.getString("sgroup"));
            pstmt.setString(index++,retrieve.getString("dgroup"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodkindsFormLinkSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodkindsFormLinkSvc.retrieve() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,retrieve.getString("lgroup"));
            pstmt.setString(index++,retrieve.getString("mgroup"));
            pstmt.setString(index++,retrieve.getString("sgroup"));
            pstmt.setString(index++,retrieve.getString("dgroup"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[GoodkindsFormLinkSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodkindsFormLinkSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TFORM
    * </PRE>
    * @param   Connection
    * @param   Tform
    * @return  int
    */
    public int insert(Connection con, Tform tform) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tform));
            int index = 1;

            pstmt.setString(index++,tform.getLgroup());
            pstmt.setString(index++,tform.getMgroup());
            pstmt.setString(index++,tform.getSgroup());
            pstmt.setString(index++,tform.getDgroup());
            pstmt.setString(index++,tform.getForm_code());
            pstmt.setString(index++,tform.getForm_name());
            pstmt.setString(index++,tform.getInsert_date());
            pstmt.setString(index++,tform.getInsert_id());
            pstmt.setString(index++,tform.getModify_date());
            pstmt.setString(index++,tform.getModify_id());             

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tform.getLgroup()       ); logString.append( "/" );   
            logString.append( tform.getMgroup()       ); logString.append( "/" );   
            logString.append( tform.getSgroup()       ); logString.append( "/" );   
            logString.append( tform.getDgroup()       ); logString.append( "/" );   
            logString.append( tform.getForm_code()    ); logString.append( "/" );   
            logString.append( tform.getForm_name()    ); logString.append( "/" );
            logString.append( tform.getInsert_date()  ); logString.append( "/" );
            logString.append( tform.getInsert_id()    ); logString.append( "/" );
            logString.append( tform.getModify_date()  ); logString.append( "/" );
            logString.append( tform.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodkindsFormLinkSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodkindsFormLinkSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TFORM
    * </PRE>
    * @param   Connection
    * @param   Tform
    * @return  int
    */
    public int update(Connection con, Tform tform) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tform));
            int index = 1;
            pstmt.setString(index++,tform.getForm_name());
            pstmt.setString(index++,tform.getModify_date());
            pstmt.setString(index++,tform.getModify_id()); 
            pstmt.setString(index++,tform.getLgroup());
            pstmt.setString(index++,tform.getMgroup());
            pstmt.setString(index++,tform.getSgroup());
            pstmt.setString(index++,tform.getDgroup());
            pstmt.setString(index++,tform.getForm_code());
            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer(); 
            logString.append( tform.getForm_name()      ); logString.append( "/" );
            logString.append( tform.getModify_date()    ); logString.append( "/" );
            logString.append( tform.getModify_id()      ); logString.append( "/" );
            logString.append( tform.getLgroup()         ); logString.append( "/" );   
            logString.append( tform.getMgroup()         ); logString.append( "/" );   
            logString.append( tform.getSgroup()         ); logString.append( "/" );   
            logString.append( tform.getDgroup()         ); logString.append( "/" );   
            logString.append( tform.getForm_code()      ); logString.append( "/" );   
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodkindsFormLinkSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodkindsFormLinkSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


}
