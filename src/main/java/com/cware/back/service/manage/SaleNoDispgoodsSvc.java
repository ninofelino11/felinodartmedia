
package com.cware.back.service.manage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * SaleNoDispgoods List Service class
 *
 * @version 1.0, 2006/12/07
 */
public class SaleNoDispgoodsSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private String logTemp      = "";
    
    public SaleNoDispgoodsSvc() {}
        
    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
     public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{

         Statement stmt       = null;
         ResultSet rset       = null;

         try {
             stmt = con.createStatement();

             rset  = stmt.executeQuery(makeSql(retrieve));

             retrieve.put("RESULT",makeSheet(rset));

         }catch(SQLException se){
             log.error("[SaleNoDispgoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[SaleNoDispgoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(stmt, null, rset);
         }
         return retrieve;
     }
     
    /**
    * <PRE>
    * Desc : GoodsDisplay List select
    * </PRE>
    * @param   poolName                           : Database pool name
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        
        StringBuffer sb   = new StringBuffer();
        String sbModSql   = "";


        sbModSql = " SELECT A.CATEGORY_CODE, \n" 
                 + "        B.CATEGORY_NAME, \n"
                 + "        A.GOODS_CODE, \n" 
                 + "        C.GOODS_NAME, \n" 
                 + "        C.SALE_GB, \n" 
                 + "        TO_CHAR(A.DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE, \n"
                 + "        TO_CHAR(A.DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE, \n" 
                 + "        A.INSERT_ID, \n" 
                 + "        A.MODIFY_ID \n" ;
        
        sbModSql = sbModSql 
                 + "        FROM TCATEGORYGOODS A, \n" 
                 + "        TCATEGORY   B, \n" ; 
        
        sbModSql = sbModSql 
                 + "        TGOODS      C \n" 
                 + "        WHERE A.CATEGORY_CODE = B.CATEGORY_CODE \n" 
                 + "        AND A.GOODS_CODE    = C.GOODS_CODE \n" 
                 + "        AND A.DISPLAY_YN    = '1' \n" 
                 + "        AND A.DISPLAY_END_DATE > SYSDATE  \n" 
                 + "        AND C.SALE_GB       > '00'  \n" ;
        
        sb.append( sbModSql );
  
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

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
    
}
