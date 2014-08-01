
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
import com.cware.back.entity.table.Tpurchase_m;

/**
 * Register ServeEntpEnd Service class
 *
 * @version 1.0, 2006/08/12
 */
public class ServeEntpEndSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ServeEntpEndSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   entp_code                    : entp_code
    * @param   account_date                 : account_date
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT TO_CHAR(A.PURCHASE_FR, 'YYYY/MM')     AS PURCHASE_MONTH,     \n");
        sb.append("         A.ENTP_CODE,                                                 \n");
        sb.append("         B.ENTP_NAME,                                                 \n");
        sb.append("         A.PURCHASE_NO,                                               \n");
        sb.append("         A.PREV_YOUBO,                                                \n");
        sb.append("         A.PAY_AMT1 + A.PAY_AMT2 + A.PAY_AMT3  AS PAY_AMT,            \n");
        sb.append("         A.YOUBO_AMT,                                                 \n");
        sb.append("         A.KONGJE_AMT,                                                \n");
        sb.append("         A.TOT_PURCHASE_AMT,                                          \n");
        sb.append("         A.FLAG,                                                      \n");
        sb.append("         TO_CHAR( A.END_DATE, 'YYYY/MM') AS END_DATE  ,               \n");
        sb.append("         A.END_ID                                                     \n");
        sb.append("    FROM TPURCHASE_M   A,                                             \n");
        sb.append("         TENTERPRISE   B                                              \n");
        sb.append("   WHERE A.ENTP_CODE = B.ENTP_CODE                                    \n");
        sb.append("     AND A.FLAG = '0'                                                 \n");
        sb.append("     AND A.PURCHASE_FR >= TO_DATE( ? ,'YYYY/MM/DD')                   \n");
        sb.append("     AND A.PURCHASE_TO <  TO_DATE( ? ,'YYYY/MM/DD') + 1               \n");
        sb.append("     AND A.ENTP_CODE LIKE ?||'%'                                      \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("fromDate  : " + retrieve.getString("fromDate"));
            log.debug("toDate  : " + retrieve.getString("toDate"));
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Tpurchase_m 에 등록 )
    * </PRE>
    * @param   Tpurchase_m
    * @return  String
    */
    private final String updateSqltpurchase_m   =  " UPDATE TPURCHASE_M  SET \n "
                                              +"            FLAG  = ?  , \n "
                                              +"            END_DATE  = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n "
                                              +"            END_ID  = ?            \n "
                                              +"     WHERE  PURCHASE_NO = ?     \n ";
    private int updateSqlLogtpurchase_m =  0;

    private String makeSqlUpdate(Tpurchase_m tpurchase_m) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogtpurchase_m == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqltpurchase_m);
            }
            updateSqlLogtpurchase_m = 1;
        }
        return updateSqltpurchase_m;
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

            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpEndSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpEndSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }



    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpurchase_m
    * </PRE>
    * @param   Connection
    * @param   Tpurchase_m
    * @return  int
    */

    public int update(Connection con, Tpurchase_m tpurchase_m) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpurchase_m));

            int index = 1;
            pstmt.setString(index++, tpurchase_m.getFlag   ());
            pstmt.setString(index++, tpurchase_m.getEnd_date   ());
            pstmt.setString(index++, tpurchase_m.getEnd_id   ());
            pstmt.setString(index++, tpurchase_m.getPurchase_no   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpurchase_m.getFlag       ()); logString.append( "/" );
            logString.append( tpurchase_m.getEnd_date        ()); logString.append( "/" );
            logString.append( tpurchase_m.getEnd_id         ()); logString.append( "/" );
            logString.append( tpurchase_m.getPurchase_no         ()); logString.append( "/" );
            logSave.info("\n" + logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ServeEntpEndSvc.update(Tpurchase_m) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ServeEntpEndSvc.update(Tpurchase_m) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}


