
package com.cware.back.service.common;

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

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * 주문재고현황 Service class
 *
 * @version 1.0, 2006/08/31
 * @author  commerceware.co.kr
 */
public class PopOrderstockSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PopOrderstockSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSelect(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT B.WH_CODE, \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         A.GOODS_NAME,  \n");
        sb.append("         A.GOODSDT_CODE,  \n");
        sb.append("         A.GOODSDT_INFO,  \n");
        sb.append("         NVL(D.COUNSEL_QTY, 0)  AS COUNSEL_QTY,  \n");
        sb.append("         NVL(B.ORDER_QTY, 0)    AS ORDER_QTY,  \n");
        sb.append("         NVL(B.OUT_PLAN_QTY, 0) AS OUT_PLAN_QTY,  \n");
        sb.append("         NVL(C.BALJU_QTY, 0)    AS BALJU_QTY,  \n");
        sb.append("         NVL(C.AQTY, 0)         AS AQTY,  \n");
        sb.append("         NVL(FUN_GET_TOTSALEQTY(A.GOODS_CODE, A.GOODSDT_CODE), 0) AS TOT_SALE_QTY,  \n");
        sb.append("         NVL(E.MAX_SALE_QTY, 0) AS MAX_SALE_QTY,  \n");
        sb.append("         A.SALE_GB  \n");
        sb.append("    FROM TGOODSDT     A,  \n");
        sb.append("         TORDERSTOCK  B,  \n");
        sb.append("         TGOODS       G,  \n");
        sb.append("         ( SELECT WH_CODE,  \n");
        sb.append("                  GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  SUM(BALJU_QTY) AS BALJU_QTY,  \n");
        sb.append("                  SUM(AQTY) AS AQTY  \n");
        sb.append("             FROM VSTOCK  \n");
        sb.append("            WHERE GOODS_CODE    = ?  \n");
        sb.append("            GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE ) C,  \n");
        sb.append("         ( SELECT WH_CODE,  \n");
        sb.append("                  GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  SUM(COUNSEL_QTY) AS COUNSEL_QTY  \n");
        sb.append("             FROM TCOUNSEL  \n");
        sb.append("            WHERE GOODS_CODE    = ?  \n");
        sb.append("            GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE   ) D,  \n");
        sb.append("         ( SELECT GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  MAX_SALE_QTY  \n");
        sb.append("             FROM TINPLANQTY E  \n");
        sb.append("            WHERE GOODS_CODE    = ?  \n");
        sb.append("              AND E.START_DATE <= trunc(SYSDATE)  \n");
        sb.append("              AND E.END_DATE   >= trunc(SYSDATE) ) E  \n");
        sb.append("   WHERE A.GOODS_CODE   = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODSDT_CODE = B.GOODSDT_CODE  \n");
        sb.append("     AND B.GOODS_CODE   = C.GOODS_CODE(+)  \n");
        sb.append("     AND B.GOODSDT_CODE = C.GOODSDT_CODE(+)  \n");
        sb.append("     AND B.WH_CODE      = C.WH_CODE(+)  \n");
        sb.append("     AND B.GOODS_CODE   = D.GOODS_CODE(+)  \n");
        sb.append("     AND B.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND B.WH_CODE      = D.WH_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE   = E.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = E.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE   = G.GOODS_CODE   \n");
        sb.append("     AND A.GOODS_CODE   = ?  \n");
        sb.append("     AND B.WH_CODE LIKE ? || '%'  \n");


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
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        long         calc_cur_qty = 0;
        long         max_sale_qty = 0;
        long         tot_sale_qty = 0;
        long		 aqty	= 0;
        long         counsel_qty = 0;
        long		 order_qty	= 0;
        long		 out_plan_qty	= 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
//			max_sale_qty  = ComUtils.objToLong(hmSheet.get("MAX_SALE_QTY"),  0);
//			tot_sale_qty  = ComUtils.objToLong(hmSheet.get("TOT_SALE_QTY"),  0);
			counsel_qty   = ComUtils.objToLong(hmSheet.get("COUNSEL_QTY"),   0);
			order_qty	  = ComUtils.objToLong(hmSheet.get("ORDER_QTY"),   0);
			out_plan_qty  = ComUtils.objToLong(hmSheet.get("OUT_PLAN_QTY"),   0);
			aqty		  = ComUtils.objToLong(hmSheet.get("AQTY"),   0);

//            calc_cur_qty = max_sale_qty - tot_sale_qty - counsel_qty;
			calc_cur_qty = aqty - counsel_qty - order_qty - out_plan_qty;

            //= Put
            hmSheet.put("CALC_CUR_QTY", Long.toString(calc_cur_qty));

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
            pstmt = con.prepareStatement(makeSqlSelect(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("wh_code"   ));

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




    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSelectSet(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT B.WH_CODE, \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         A.GOODS_NAME,  \n");
        sb.append("         A.GOODSDT_CODE,  \n");
        sb.append("         A.GOODSDT_INFO,  \n");
        sb.append("         NVL(D.COUNSEL_QTY, 0) AS COUNSEL_QTY,  \n");
        sb.append("         NVL(B.ORDER_QTY, 0) AS ORDER_QTY,  \n");
        sb.append("         NVL(B.OUT_PLAN_QTY, 0) AS OUT_PLAN_QTY,  \n");
        sb.append("         NVL(C.BALJU_QTY, 0) AS BALJU_QTY,  \n");
        sb.append("         NVL(C.AQTY, 0) AS AQTY,  \n");
        sb.append("         NVL(FUN_GET_TOTSALEQTY(A.GOODS_CODE, A.GOODSDT_CODE), 0) AS TOT_SALE_QTY,  \n");
        sb.append("         NVL(E.MAX_SALE_QTY, 0) AS MAX_SALE_QTY,  \n");
        sb.append("         A.SALE_GB  \n");
        sb.append("    FROM TGOODSDT     A,  \n");
        sb.append("         TORDERSTOCK  B,  \n");
        sb.append("         TGOODS       G,  \n");
        sb.append("         ( SELECT WH_CODE,  \n");
        sb.append("                  GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  SUM(BALJU_QTY) AS BALJU_QTY,  \n");
        sb.append("                  SUM(AQTY) AS AQTY  \n");
        sb.append("             FROM VSTOCK  \n");
        sb.append("            WHERE GOODS_CODE    IN ( SELECT A.IN_GOODS_CODE  FROM TSETGOODS A, TGOODS B WHERE A.IN_GOODS_CODE  = B.GOODS_CODE AND A.GOODS_CODE = ? )  \n");
        sb.append("            GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE ) C,  \n");
        sb.append("         ( SELECT WH_CODE,  \n");
        sb.append("                  GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  SUM(COUNSEL_QTY) AS COUNSEL_QTY  \n");
        sb.append("             FROM TCOUNSEL  \n");
        sb.append("            WHERE GOODS_CODE    IN ( SELECT A.IN_GOODS_CODE  FROM TSETGOODS A, TGOODS B WHERE A.IN_GOODS_CODE  = B.GOODS_CODE AND A.GOODS_CODE = ? )  \n");
        sb.append("            GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE   ) D,  \n");
        sb.append("         ( SELECT GOODS_CODE,  \n");
        sb.append("                  GOODSDT_CODE,  \n");
        sb.append("                  MAX_SALE_QTY  \n");
        sb.append("             FROM TINPLANQTY E  \n");
        sb.append("            WHERE GOODS_CODE    IN ( SELECT A.IN_GOODS_CODE  FROM TSETGOODS A, TGOODS B WHERE A.IN_GOODS_CODE  = B.GOODS_CODE AND A.GOODS_CODE = ? )  \n");
        sb.append("              AND E.START_DATE <= trunc(SYSDATE)  \n");
        sb.append("              AND E.END_DATE   >= trunc(SYSDATE) ) E  \n");
        sb.append("   WHERE A.GOODS_CODE   = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODSDT_CODE = B.GOODSDT_CODE  \n");
        sb.append("     AND B.GOODS_CODE   = C.GOODS_CODE(+)  \n");
        sb.append("     AND B.GOODSDT_CODE = C.GOODSDT_CODE(+)  \n");
        sb.append("     AND B.WH_CODE      = C.WH_CODE(+)  \n");
        sb.append("     AND B.GOODS_CODE   = D.GOODS_CODE(+)  \n");
        sb.append("     AND B.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND B.WH_CODE      = D.WH_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE   = E.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = E.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.GOODS_CODE   = G.GOODS_CODE   \n");
        sb.append("     AND A.GOODS_CODE   IN ( SELECT A.IN_GOODS_CODE  FROM TSETGOODS A, TGOODS B WHERE A.IN_GOODS_CODE  = B.GOODS_CODE AND A.GOODS_CODE = ? )  \n");
        sb.append("     AND B.WH_CODE LIKE ? || '%'   \n");



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
    public RetrieveModel retrieveSet(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSelectSet(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("wh_code"   ));

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






}

