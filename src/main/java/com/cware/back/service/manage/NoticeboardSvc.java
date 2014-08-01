
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
import com.cware.back.entity.table.Tboardinfo;

/**
 * Noticeboard Service class
 *
 * @version 1.0, 2006/12/04
 */
public class NoticeboardSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public NoticeboardSvc() {}

    
    
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
            pstmt = con.prepareStatement(makeSql());

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[NoticeboardSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[NoticeboardSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
    
    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL 
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.BOARD_CODE, \n ");
        sb.append("         A.BOARD_DESC, \n ");
        sb.append("         A.DISPLAY_YN  \n ");
        sb.append("    FROM TBOARDINFO A  \n ");
        sb.append("ORDER BY A.BOARD_CODE  \n ");
        

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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

    
    /* insert Tboardinfo
     *   
     */  
    public int insert(Connection con, Tboardinfo boardinfo) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;
            
            pstmt.setString(index++,boardinfo.getBoard_code());
            pstmt.setString(index++,boardinfo.getBoard_desc());
            pstmt.setString(index++,boardinfo.getDisplay_yn());
            pstmt.setString(index++,boardinfo.getInsert_id());
            pstmt.setString(index++,boardinfo.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( boardinfo.getBoard_code()); logString.append( "/" );   
            logString.append( boardinfo.getBoard_desc()); logString.append( "/" );
            logString.append( boardinfo.getDisplay_yn()); logString.append( "/" );   
            logString.append( boardinfo.getInsert_id());  logString.append( "/" );
            logString.append( boardinfo.getModify_id());  logString.append( "/" ); 
             
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[NoticeboardSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[NoticeboardSvc.insert()) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
    
    /* insert Tboardinfo
     *   
     */
    private String makeSqlInsert() throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TBOARDINFO ( \n ");
        sb.append("         BOARD_CODE,  \n ");
        sb.append("         BOARD_DESC,  \n ");
        sb.append("         DISPLAY_YN,  \n ");
        sb.append("         INSERT_DATE, \n ");
        sb.append("         INSERT_ID,   \n ");
        sb.append("         MODIFY_DATE, \n ");
        sb.append("         MODIFY_ID)   \n ");
        sb.append(" VALUES ( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ?) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }        

        return sb.toString();
    }
    
    /* update Tboardinfo
     *   
     */
    public int update(Connection con, Tboardinfo boardinfo) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate());
            int index = 1;
            
            pstmt.setString(index++,boardinfo.getBoard_desc());
            pstmt.setString(index++,boardinfo.getDisplay_yn());
            pstmt.setString(index++,boardinfo.getModify_id());
            pstmt.setString(index++,boardinfo.getBoard_code());
                       
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( boardinfo.getBoard_desc()); logString.append( "/" );
            logString.append( boardinfo.getDisplay_yn()); logString.append( "/" );   
            logString.append( boardinfo.getModify_id() ); logString.append( "/" );
            logString.append( boardinfo.getBoard_code()); logString.append( "/" );   
           
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[NoticeboardSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[NoticeboardSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    
    /* update Tboardinfo
     *   
     */
    private String makeSqlUpdate() throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE TBOARDINFO SET \n ");
        sb.append("        BOARD_DESC  = ?, \n ");
        sb.append("        DISPLAY_YN  = ?, \n ");
        sb.append("        MODIFY_DATE = SYSDATE, \n ");
        sb.append("        MODIFY_ID   = ?  \n ");
        sb.append("  WHERE BOARD_CODE  = ?  \n ");
 
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }        

        return sb.toString();
    }
    
}
