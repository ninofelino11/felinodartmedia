
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
import com.cware.back.entity.table.Tsecprogram;


/**
 * ProgramCompetence Service class
 *
 * @version 1.0, 2007/01/23
 * @author commerceware.co.kr
 */
public class ProgramCompetenceSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ProgramCompetenceSvc() {}

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

        sb.append(" SELECT A.LMENU_ID,                 \n");
        sb.append("        A.LMENU_NAME,               \n");
        sb.append("        A.MMENU_ID,                 \n");
        sb.append("        A.MMENU_NAME,               \n");
        sb.append("        A.SMENU_ID,                 \n");
        sb.append("        A.SMENU_NAME,              \n");
        sb.append("        B.PROGRAM_ID,              \n");
        sb.append("        B.PROGRAM_NAME,            \n");
        sb.append("        B.USE_YN,                  \n");
        sb.append("        B.REMARK                  \n");
        sb.append("   FROM TSECMENU     A,            \n");
        sb.append("        TSECPROGRAM  B             \n");
        sb.append("  WHERE A.LMENU_ID  =  B.LMENU_ID  \n");
        sb.append("    AND A.MMENU_ID  =  B.MMENU_ID  \n");
        sb.append("    AND A.SMENU_ID  =  B.SMENU_ID  \n");
        sb.append("    AND A.LMENU_ID like ?|| '%'    \n");
        sb.append("    AND A.MMENU_ID like ?|| '%'    \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("lmenu_id     : " + retrieve.getString("lmenu_id"));
            log.debug("mmenu_id     : " + retrieve.getString("mmenu_id"));            
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Tsecprogram )
    * </PRE>
    * @param   Tsecprogram
    * @return  String
    */
    private final String updateSql =  " UPDATE TSECPROGRAM SET \n "
                                     +"        USE_YN = ?, \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ? \n "
                                     +"  WHERE LMENU_ID = ? \n "
                                     +"    AND MMENU_ID = ? \n "
                                     +"    AND SMENU_ID = ? \n "
                                     +"    AND PROGRAM_ID = ? \n " ;

    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tsecprogram Tsecprogram) throws StoreException{
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
            pstmt.setString(index++,retrieve.getString("lmenu_id"));
            pstmt.setString(index++,retrieve.getString("mmenu_id"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[ProgramCompetenceSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ProgramCompetenceSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tsecprogram
    * </PRE>
    * @param   Connection
    * @param   Tsecprogram
    * @return  int
    */
    public int update(Connection con, Tsecprogram Tsecprogram) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(Tsecprogram));
            int index = 1;
            pstmt.setString(index++,Tsecprogram.getUse_yn());
            pstmt.setString(index++,Tsecprogram.getModify_id());
            pstmt.setString(index++,Tsecprogram.getLmenu_id());
            pstmt.setString(index++,Tsecprogram.getMmenu_id());
            pstmt.setString(index++,Tsecprogram.getSmenu_id());
            pstmt.setString(index++,Tsecprogram.getProgram_id());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( Tsecprogram.getUse_yn()     ); logString.append( "/" );
            logString.append( Tsecprogram.getModify_id()  ); logString.append( "/" );  
            logString.append( Tsecprogram.getLmenu_id()   ); logString.append( "/" );
            logString.append( Tsecprogram.getMmenu_id()   ); logString.append( "/" );
            logString.append( Tsecprogram.getSmenu_id()   ); logString.append( "/" );                      
            logString.append( Tsecprogram.getProgram_id() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ProgramCompetenceSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ProgramCompetenceSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}


