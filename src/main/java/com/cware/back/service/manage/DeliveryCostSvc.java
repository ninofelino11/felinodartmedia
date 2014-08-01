
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



/**
 * 택배비내역조회 Service class
 *
 * @version 1.0, 2007/01/22
 */
public class DeliveryCostSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public DeliveryCostSvc() {}

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
        
        sb.append("  SELECT B.DELY_GB,                                                                      \n");
        sb.append("         B.AREA_GB,                                                                      \n");
        sb.append("         B.SHPFEE_CODE,                                                                  \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '1', B.DELY_CNT, 0)), 0)  AS ORDER_OUT1,            \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '1', B.JOIN_CNT, 0)), 0)  AS ORDER_OUT2,            \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '1', B.JOIN_DET_CNT,  0)), 0)  AS ORDER_OUT3,       \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '2', B.DELY_CNT, 0)), 0)  AS CHANGE_OUT1,           \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '2', B.JOIN_CNT, 0)), 0)  AS CHANGE_OUT2,           \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '2', B.JOIN_DET_CNT,  0)), 0)  AS CHANGE_OUT3,      \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '5', B.DELY_CNT, 0)), 0)  AS CLAIM_RETURN1,         \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '5', B.JOIN_CNT, 0)), 0)  AS CLAIM_RETURN2,         \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '5', B.JOIN_DET_CNT,  0)), 0)  AS CLAIM_OUT3,       \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '6', B.DELY_CNT, 0)), 0)  AS CHANGE_RETURN1,        \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '6', B.JOIN_CNT, 0)), 0)  AS CHANGE_RETURN2,        \n");
        sb.append("         NVL(SUM (DECODE (B.SLIP_GB, '6', B.JOIN_DET_CNT,  0)), 0)  AS CHANGE_RETURN3    \n");  
        sb.append("    FROM (                                                                               \n");
        sb.append("          SELECT A.DELY_GB,                                                              \n");
        sb.append("                 DECODE(A.SLIP_GB,  '101', '1',                                          \n");
        sb.append("                                    '102', '1',                                          \n");
        sb.append("                                    '103', '1',                                          \n");
        sb.append("                                    '104', '2',                                          \n");
        sb.append("                                    '105', '3',                                          \n");
        sb.append("                                    '107', '4',                                          \n");
        sb.append("                                    '201', '5',                                          \n");
        sb.append("                                    '203', '6',                                          \n");
        sb.append("                                    '205', '7' ) AS SLIP_GB,                             \n");
        sb.append("                 A.AREA_GB,                                                              \n");
        sb.append("                 A.SHPFEE_CODE,                                                          \n");
        sb.append("                 A.DELY_CNT,                                                             \n");
        sb.append("                 A.JOIN_CNT,                                                             \n");
        sb.append("                 A.JOIN_DET_CNT,                                                         \n");
        sb.append("                 A.DELY_UNIT_COST,                                                       \n");
        sb.append("                 A.DELY_COST                                                             \n");
        sb.append("            FROM TDELYCLOSE A                                                            \n");
        sb.append("           WHERE A.OUT_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                  \n");
        sb.append("             AND A.OUT_DATE - 1 < TO_DATE(?, 'YYYY/MM/DD')                               \n");
        sb.append("             AND A.WH_CODE LIKE ? || '%'                                                 \n");
        sb.append("             AND A.DELY_GB LIKE ? || '%'                                                 \n");
        sb.append("             AND A.SHPFEE_CODE LIKE ? || '%'                                             \n");
        sb.append("         ) B                                                                             \n");
        sb.append("   GROUP BY B.DELY_GB,                                                                   \n");
        sb.append("            B.AREA_GB,                                                                   \n");
        sb.append("            B.SHPFEE_CODE                                                                \n");
        sb.append("   ORDER BY B.DELY_GB,                                                                   \n");
        sb.append("            B.AREA_GB,                                                                   \n");
        sb.append("            B.SHPFEE_CODE                                                                \n");
    
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("fromDate     : " + retrieve.getString("fromDate"     ));  
            log.debug("toDate       : " + retrieve.getString("toDate"       ));
            log.debug("wh_code      : " + retrieve.getString("wh_code"      ));
            log.debug("dely_gb      : " + retrieve.getString("dely_gb"      ));
            log.debug("shpfee_code  : " + retrieve.getString("shpfee_code"       ));
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
            pstmt.setString(index++, retrieve.getString("wh_code"));       
            pstmt.setString(index++, retrieve.getString("dely_gb"));   
            pstmt.setString(index++, retrieve.getString("shpfee_code"));          
            	
            rset  = pstmt.executeQuery();                             
                                                                      
            retrieve.put("RESULT",makeSheet(rset));                                  
                                                                      
        }catch(SQLException se){                                      
            log.error("[DeliveryCostSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);           
            throw new StoreException(se.getMessage());                
        }catch(Exception e){                                          
            log.error("[DeliveryCostSvc.retrieve() Exception : ERR-"+e.getMessage());            
            throw new StoreException(e.getMessage());                 
        }finally {                                                    
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    } 
   
    
}
