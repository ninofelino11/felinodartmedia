
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
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tusertracking;

/**
 * Register tracking Service class
 *
 * @version 1.0, 2006/06/21
 */
public class SystemSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public SystemSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */

    /**
    * <PRE>
    * Desc : Make SQL ( TUSERTRACKING 에 등록 )
    * </PRE>
    * @param   Ttracking
    * @return  String
    */
    public String makeSqlInsert(Tusertracking tracking) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TUSERTRACKING ( \n ");
        sb.append("          USER_ID, \n ");
        sb.append("          IP_ADDR, \n ");
        sb.append("          SESSION_ID, \n ");
        sb.append("          COMMAND, \n ");
        sb.append("          PROGRAM_ID, \n ");
        sb.append("          INSERT_DATE ) \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          SYSDATE) \n ");

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


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tusertracking
    * </PRE>
    * @param   Connection
    * @param   Tusertracking
    * @return  int
    */
    public int insert(Connection con, Tusertracking tracking) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tracking));
            int index = 1;
            pstmt.setString(index++,tracking.getUser_id()     );
            pstmt.setString(index++,tracking.getIp_addr()   );
            pstmt.setString(index++,tracking.getSession_id()      );
            pstmt.setString(index++,tracking.getCommand()   );
            pstmt.setString(index++,tracking.getProgram_id()       );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tracking.getUser_id()      ); logString.append( "/" );
            logString.append( tracking.getIp_addr()    ); logString.append( "/" );
            logString.append( tracking.getSession_id()       ); logString.append( "/" );
            logString.append( tracking.getCommand()    ); logString.append( "/" );
            logString.append( tracking.getProgram_id()        ); logString.append( "/" );
            logSave.info(logString.toString());

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
}
