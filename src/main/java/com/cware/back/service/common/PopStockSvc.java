
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

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * 상품재고 Service class
 *
 * @version 1.0, 2006/08/31
 * @author  commerceware.co.kr
 */
public class PopStockSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PopStockSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
      sb.append("   SELECT B.GOODS_CODE AS SGOODS_CODE,                                                                                                 \n");
      sb.append("          C.GOODS_NAME,                                                                                                                \n");
      sb.append("          B.GOODSDT_CODE,                                                                                                              \n");
      sb.append("          D.GOODSDT_INFO,                                                                                                              \n");
      sb.append("          B.ORDER_QTY,                                                                                                                 \n");
      sb.append("          B.OUT_PLAN_QTY,                                                                                                              \n");
      sb.append("          A.WH_CODE,                                                                                                                   \n");
      sb.append("          V.CREATE_QTY,                                                                                                                \n");
      sb.append("          A.BALJU_QTY,                                                                                                                 \n");
      sb.append("          A.AQTY,                                                                                                                      \n");
      sb.append("          A.BQTY,                                                                                                                      \n");
      sb.append("          C.MD_CODE,                                                                                                                   \n");
      sb.append("          E.MD_NAME,                                                                                                                   \n");
      sb.append("          C.ENTP_CODE,                                                                                                                 \n");
      sb.append("          F.ENTP_NAME,                                                                                                                 \n");
      sb.append("          DECODE(A.WH_CODE, C.WH_CODE, FUN_GET_TOTSALEQTY(A.GOODS_CODE, A.GOODSDT_CODE), NULL, FUN_GET_TOTSALEQTY(A.GOODS_CODE, A.GOODSDT_CODE), 0) TOT_SALE_QTY  \n");
      sb.append("     FROM TORDERSTOCK B,                                                                                                               \n");
      sb.append("          TSTOCK      A,                                                                                                               \n");
      sb.append("          TGOODS      C,                                                                                                               \n");
      sb.append("          TGOODSDT    D,                                                                                                               \n");
      sb.append("          TMD         E,                                                                                                               \n");
      sb.append("          TENTERPRISE F,                                                                                                               \n");
      sb.append("          TSLIPCREATE V                                                                                                                \n");
      sb.append("    WHERE B.GOODS_CODE    = A.GOODS_CODE (+)                                                                                           \n");
      sb.append("      AND B.GOODSDT_CODE  = A.GOODSDT_CODE (+)                                                                                         \n");
      sb.append("      AND B.WH_CODE       = A.WH_CODE (+)                                                                                              \n");
      sb.append("      AND B.GOODS_CODE    = C.GOODS_CODE                                                                                               \n");
      sb.append("      AND B.GOODS_CODE    = D.GOODS_CODE                                                                                               \n");
      sb.append("      AND B.GOODSDT_CODE  = D.GOODSDT_CODE                                                                                             \n");
      sb.append("      AND C.MD_CODE       = E.MD_CODE                                                                                                  \n");
      sb.append("      AND C.ENTP_CODE     = F.ENTP_CODE                                                                                                \n");
      sb.append("      AND A.WH_CODE       = V.WH_CODE   (+)                                                                                            \n");
      sb.append("      AND A.GOODS_CODE    = V.GOODS_CODE (+)                                                                                           \n");
      sb.append("      AND A.GOODSDT_CODE  = V.GOODSDT_CODE (+)                                                                                         \n");
      sb.append("      AND B.GOODSDT_CODE  >= DECODE(C.STOCK_CHK_PLACE, '2', '001', '000') /* 재고주체 상품,복합시 미지정보이도록 2006.4.28 */                 \n");
      sb.append("      AND B.ORDER_QTY + B.OUT_PLAN_QTY + NVL(A.BALJU_QTY, 0) + NVL(A.AQTY, 0) + NVL(A.BQTY, 0) + NVL(V.CREATE_QTY, 0)                  \n");
      sb.append("          + DECODE(A.WH_CODE, C.WH_CODE, B.TOT_SALE_QTY, NULL, B.TOT_SALE_QTY, 0) > 0 /* 재고없어도 총판매있으면 보이도록 2006.4.28 */        \n");
      sb.append("      AND B.GOODS_CODE    = ?                                                                                                          \n");
      sb.append("      AND B.WH_CODE    LIKE ? || '%'                                                                                                   \n");
      sb.append(" ORDER BY LPAD(B.GOODS_CODE, 10, '0'),                                                                                                                \n");
      sb.append("          B.GOODSDT_CODE,                                                                                                              \n");
      sb.append("          A.WH_CODE                                                                                                                    \n");       
        
    
     //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("goods_code : " + retrieve.getString("goods_code"));
            log.debug("wh_code    : " + retrieve.getString("wh_code"));
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
        long         order_qty = 0;
        long         out_plan_qty = 0;
        long         aqty = 0;
        long         bqty = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
           // max_sale_qty  = ComUtils.objToLong(hmSheet.get("MAX_SALE_QTY"),  0);
           // tot_sale_qty  = ComUtils.objToLong(hmSheet.get("TOT_SALE_QTY"),  0);
           // counsel_qty   = ComUtils.objToLong(hmSheet.get("COUNSEL_QTY"),  0);

           // calc_cur_qty = max_sale_qty - tot_sale_qty - counsel_qty;
            
            //= Put 
           // hmSheet.put("CALC_CUR_QTY", Long.toString(calc_cur_qty)); 

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
        sb.append("   SELECT B.GOODS_CODE AS SGOODS_CODE,       \n");
        sb.append("          C.GOODS_NAME,                      \n");
        sb.append("          B.GOODSDT_CODE,                    \n");
        sb.append("          D.GOODSDT_INFO,                    \n");
        sb.append("          B.ORDER_QTY,                       \n");
        sb.append("          B.OUT_PLAN_QTY,                    \n");
        sb.append("          A.WH_CODE,                         \n");
        sb.append("          V.CREATE_QTY,                      \n");
        sb.append("          A.BALJU_QTY,                       \n");
        sb.append("          A.AQTY,                            \n");
        sb.append("          A.BQTY,                            \n");
        sb.append("          C.MD_CODE,                         \n");
        sb.append("          E.MD_NAME,                         \n");
        sb.append("          C.ENTP_CODE,                       \n");
        sb.append("          F.ENTP_NAME,                       \n");
        sb.append("          DECODE(A.WH_CODE, C.WH_CODE, B.TOT_SALE_QTY, NULL, B.TOT_SALE_QTY, 0) TOT_SALE_QTY   \n");
        sb.append("     FROM TORDERSTOCK B,                     \n");
        sb.append("          TSTOCK      A,                     \n");
        sb.append("          TGOODS      C,                     \n");
        sb.append("          TGOODSDT    D,                     \n");
        sb.append("          TMD         E,                     \n");
        sb.append("          TENTERPRISE F,                     \n");
        sb.append("          TSLIPCREATE V                      \n");
        sb.append("    WHERE B.GOODS_CODE    = A.GOODS_CODE (+) \n");
        sb.append("      AND B.GOODSDT_CODE  = A.GOODSDT_CODE (+)  \n");
        sb.append("      AND B.WH_CODE       = A.WH_CODE (+)    \n");
        sb.append("      AND B.GOODS_CODE    = C.GOODS_CODE     \n");
        sb.append("      AND B.GOODS_CODE    = D.GOODS_CODE     \n");
        sb.append("      AND B.GOODSDT_CODE  = D.GOODSDT_CODE   \n");
        sb.append("      AND C.MD_CODE       = E.MD_CODE        \n");
        sb.append("      AND C.ENTP_CODE     = F.ENTP_CODE      \n");
        sb.append("      AND A.WH_CODE       = V.WH_CODE   (+)  \n");
        sb.append("      AND A.GOODS_CODE    = V.GOODS_CODE (+) \n");
        sb.append("      AND A.GOODSDT_CODE  = V.GOODSDT_CODE (+)  \n");
        sb.append("      AND B.GOODSDT_CODE >= DECODE(C.STOCK_CHK_PLACE, '2', '001', '000') /* 재고주체 상품,복합시 미지정보이도록 2006.4.28 */          \n");
        sb.append("      AND B.ORDER_QTY + B.OUT_PLAN_QTY + NVL(A.BALJU_QTY, 0) + NVL(A.AQTY, 0) + NVL(A.BQTY, 0) + NVL(V.CREATE_QTY, 0)                  \n");
        sb.append("          + DECODE(A.WH_CODE, C.WH_CODE, B.TOT_SALE_QTY, NULL, B.TOT_SALE_QTY, 0) > 0 /* 재고없어도 총판매있으면 보이도록 2006.4.28 */ \n");
        sb.append("      AND B.GOODS_CODE   IN ( SELECT A.IN_GOODS_CODE  FROM TSETGOODS A, TGOODS B WHERE A.IN_GOODS_CODE  = B.GOODS_CODE AND A.GOODS_CODE = ? )   \n");
        sb.append("      AND B.WH_CODE    LIKE ? || '%'         \n");
        sb.append(" ORDER BY LPAD(B.GOODS_CODE, 10, '0'),       \n");
        sb.append("          B.GOODSDT_CODE,                    \n");
        sb.append("          A.WH_CODE                          \n");


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

