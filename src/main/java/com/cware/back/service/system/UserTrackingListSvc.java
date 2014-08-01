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

public class UserTrackingListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public UserTrackingListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL 
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        
        StringBuffer  sb = new StringBuffer();
        sb.append("SELECT A.USER_ID, ");
        sb.append("       A.IP_ADDR, ");
        sb.append("       A.SESSION_ID, ");
        sb.append("       A.COMMAND, ");
        sb.append("       A.PROGRAM_ID, ");
        sb.append("       TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, ");
        sb.append("       B.PROGRAM_NAME ");
        sb.append("FROM   TUSERTRACKING A, ");
//        sb.append("       (SELECT REPLACE(INITCAP(REPLACE(SUBSTR(PROGRAM_ID, 3), '_', ' ')), ' ') ");
        sb.append("       (SELECT PROGRAM_ID, ");
        sb.append("               PROGRAM_NAME ");
        sb.append("        FROM   TSECPROGRAM) B ");
        sb.append("WHERE  A.PROGRAM_ID = B.PROGRAM_ID(+) ");
        sb.append("       AND A.USER_ID LIKE ? ");
        sb.append("       AND TRUNC(A.INSERT_DATE) >= TO_DATE(?, 'YYYY/MM/DD') ");
        sb.append("       AND TRUNC(A.INSERT_DATE) <= TO_DATE(?, 'YYYY/MM/DD') ");
        sb.append("       AND A.IP_ADDR LIKE ? ");
        sb.append("       AND A.SESSION_ID LIKE ? ");
        
        if(retrieve.getString("gubun").equals("0")){
            sb.append("ORDER  BY A.INSERT_DATE DESC ");
        }else{
            sb.append("ORDER  BY A.SESSION_ID, ");
            sb.append("          A.INSERT_DATE  DESC");
        }


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
            pstmt.setString(index++,retrieve.getString("user_id"));
            pstmt.setString(index++,retrieve.getString("from_date"));
            pstmt.setString(index++,retrieve.getString("to_date"));
            pstmt.setString(index++,retrieve.getString("ip_addr"));
            pstmt.setString(index++,retrieve.getString("session_id"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("user_id")   ); logString.append( "/" );
            logString.append( retrieve.getString("from_date")   ); logString.append( "/" );
            logString.append( retrieve.getString("to_date")   ); logString.append( "/" );
            logString.append( retrieve.getString("ip_addr") ); logString.append( "/" );
            logString.append( retrieve.getString("session_id") ); logString.append( "/" );

            log.debug(logString.toString());


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

}
