
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
 * GoodsDisplay List Service class
 *
 * @version 1.0, 2006/12/07
 */
public class GoodsDisplaySvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public GoodsDisplaySvc() {}
        
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
             log.error("[GoodsDisplaySvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[GoodsDisplaySvc.retrieve() Exception : ERR-"+e.getMessage());
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

        String frDate        = retrieve.getString("frDate");
        String toDate        = retrieve.getString("toDate");
        String goods_code    = retrieve.getString("goods_code");
        String md_code       = retrieve.getString("md_code");
        String entp_code     = retrieve.getString("entp_code");
        String lgroup        = retrieve.getString("lgroup");
        String mgroup        = retrieve.getString("mgroup");
        String brand_code    = retrieve.getString("brand_code");
        String fr_sale_price = retrieve.getString("fr_sale_price");
        String to_sale_price = retrieve.getString("to_sale_price");
        String sale_gb       = retrieve.getString("sale_gb");

        sbModSql = "SELECT   A.GOODS_CODE, \n"
                 + "         A.GOODS_NAME, \n"
                 + "         A.SALE_GB, \n"
                 + "         PR.BUY_COST   AS BUY_COST, \n"
                 + "         PR.CUST_PRICE AS CUST_PRICE, \n"
                 + "         PR.SALE_PRICE AS SALE_PRICE, \n"
                 + "         H.L_CODE, \n"
                 + "         H.L_NAME, \n"
                 + "         H.M_CODE, \n"
                 + "         H.M_NAME, \n"
                 + "         F.CATEGORY_CODE, \n"
                 + "         H.CATEGORY_NAME, \n"
                 + "         F.PRESENTATION_KINDS, \n"
                 + "         I.CODE_NAME AS KINDS_NAME, \n"
                 + "         F.DISPLAY_PRIORITY, \n"
                 + "         '1'   AS DISPLAY_YN, \n"
                 + "         J.MD_NAME \n"
                 + "    FROM TGOODS A, \n"
                 + "         TGOODSPRICE PR, \n"
                 + "         (SELECT CATEGORY_CODE, \n"
                 + "                 GOODS_CODE, \n"
                 + "                 DISPLAY_PRIORITY, \n"
                 + "                 NULL AS PRESENTATION_KINDS \n"
                 + "            FROM TCATEGORYGOODS \n"
                 + "           WHERE DISPLAY_YN = '1' \n"
                 + "             AND DISPLAY_START_DATE <= SYSDATE \n"
                 + "             AND DISPLAY_END_DATE > SYSDATE \n"
                 + "          UNION ALL \n"
                 + "          SELECT CATEGORY_CODE, \n"
                 + "                 GOODS_CODE, \n"
                 + "                 DISPLAY_PRIORITY, \n"
                 + "                 PRESENTATION_KINDS \n"
                 + "            FROM TPRESENTATION \n"
                 + "           WHERE DISPLAY_YN = '1' \n"
                 + "             AND DISPLAY_START_DATE <= SYSDATE \n"
                 + "             AND DISPLAY_END_DATE > SYSDATE \n"
                 + "           ) F, \n"
                 + "         (  SELECT XA.CATEGORY_CODE, \n"
                 + "                   XA.CATEGORY_NAME, \n"
                 + "                   XA.P_CATEGORY_CODE, \n"
                 + "                   XB.L_CODE, \n"
                 + "                   XB.L_NAME, \n"
                 + "                   XB.M_CODE, \n"
                 + "                   XB.M_NAME, \n"
                 + "                   XA.LEVEL1 \n"
                 + "              FROM ( SELECT CATEGORY_CODE, \n"
                 + "                            CATEGORY_NAME, \n"
                 + "                            P_CATEGORY_CODE, \n"
                 + "                            LEVEL AS LEVEL1 \n"
                 + "                       FROM TCATEGORY \n"
                 + "                      START WITH CATEGORY_CODE = '0' \n"
                 + "                    CONNECT BY PRIOR CATEGORY_CODE = P_CATEGORY_CODE ) XA, \n"
                 + "                   VCATEGORY XB \n"
                 + "             WHERE XA.CATEGORY_CODE = XB.S_CODE(+) ) H, \n"
                 + "         TCODE I, \n"
                 + "         TMD J \n"
                 + "   WHERE A.GOODS_CODE = PR.GOODS_CODE \n"
                 + "     AND PR.APPLY_DATE = \n"
                 + "                ( SELECT MAX(PR1.APPLY_DATE) \n"
                 + "                    FROM TGOODSPRICE PR1  \n"
                 + "                   WHERE PR1.GOODS_CODE = PR.GOODS_CODE \n" 
                 + "                     AND PR1.APPLY_DATE <= SYSDATE  \n"
                 + "                 )  \n"
                 + "     AND A.GOODS_CODE = F.GOODS_CODE \n"
                 + "     AND F.CATEGORY_CODE = H.CATEGORY_CODE \n"
                 + "     AND I.CODE_LGROUP = DECODE(SUBSTR(H.CATEGORY_CODE, 1, 1), '2', DECODE(H.LEVEL1, 4, 'B114', 3, 'B116', 'B117'), DECODE(H.LEVEL1, 5, 'B114', 4, 'B113', 3, 'B112', 'B111')) \n"
                 + "     AND I.CODE_MGROUP = NVL(F.PRESENTATION_KINDS, '99') \n"
                 + "     AND A.MD_CODE = J.MD_CODE \n"
                 + " AND A.INSERT_DATE >= TO_DATE('"+ frDate + "', 'YYYY/MM/DD') \n"
                 + " AND A.INSERT_DATE <  TO_DATE('"+ toDate + "', 'YYYY/MM/DD') + 1 \n" ;
        
        if(!goods_code.equals(""))    { sbModSql = sbModSql + " AND A.GOODS_CODE = '"  + goods_code    + "' \n"; }
        if(!md_code.equals(""))       { sbModSql = sbModSql + " AND A.MD_CODE = '"     + md_code       + "' \n"; }
        if(!lgroup.equals(""))        { sbModSql = sbModSql + " AND A.LGROUP = '"      + lgroup        + "' \n"; }
        if(!mgroup.equals(""))        { sbModSql = sbModSql + " AND A.MGROUP = '"      + mgroup        + "' \n"; }
        if(!brand_code.equals(""))    { sbModSql = sbModSql + " AND A.BRAND_CODE = '"  + brand_code    + "' \n"; }
        if(!fr_sale_price.equals("")) { sbModSql = sbModSql + " AND PR.SALE_PRICE >= " + fr_sale_price + "  \n"; }
        if(!to_sale_price.equals("")) { sbModSql = sbModSql + " AND PR.SALE_PRICE <= " + to_sale_price + "  \n"; }
        if(!sale_gb.equals(""))       { sbModSql = sbModSql + " AND A.SALE_GB = '"     + sale_gb       + "' \n"; }
        if(!entp_code.equals(""))     { sbModSql = sbModSql + " AND A.ENTP_CODE = '"   + entp_code     + "' \n"; }
        
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
