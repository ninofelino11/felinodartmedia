
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * Popup Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class PopupSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PopupSvc() {}

    /**
    * <PRE>
    * Desc : Tcode 의 code_lgroup 에 따른 정보를 얻는다
    * </PRE>
    * @return  Query
    */
    public String makeSqlCodeMgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_MGROUP, \n ");
        sb.append("        CODE_NAME, \n ");
        sb.append("        CODE_GROUP, \n ");
        sb.append("        REMARK \n ");
        sb.append("   FROM TCODE  \n ");
        sb.append("  WHERE CODE_MGROUP LIKE UPPER('"+retrieve.getString("CODE_MGROUP")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY CODE_MGROUP ASC \n ");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Tcode 의 code_lgroup 에 따른 정보를 얻는다(Multi Selection)
     * </PRE>
     * @return  Query
     */
     public String makeSqlCodeMgroupMulti(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT CODE_MGROUP, \n ");
         sb.append("        CODE_NAME, \n ");
         sb.append("        CODE_GROUP, \n ");
         sb.append("        REMARK, \n ");
         sb.append("        '' AS SELECTION \n ");
         sb.append("   FROM TCODE  \n ");
         sb.append("  WHERE CODE_MGROUP LIKE UPPER('"+retrieve.getString("CODE_MGROUP")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append("  ORDER BY CODE_MGROUP ASC \n ");
         return sb.toString();
     }

    /**
     * <PRE>
     * Desc : Tcode 의 code_name 에 따른 정보를 얻는다
     * </PRE>
     * @return  Query
     */
     public String makeSqlCodeName(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT CODE_MGROUP, \n ");
         sb.append("        CODE_NAME, \n ");
         sb.append("        CODE_GROUP, \n ");
         sb.append("        REMARK \n ");
         sb.append("   FROM TCODE  \n ");
         sb.append("  WHERE UPPER(CODE_NAME) LIKE '%'||UPPER('"+retrieve.getString("CODE_NAME")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append("  ORDER BY CODE_MGROUP ASC \n ");
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : User ID 정보를 얻는다
      * </PRE>
      * @return  Query
      */
      public String makeSqlUserId(RetrieveModel retrieve) throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append("SELECT A.USER_ID,     \n");
          sb.append("       A.USER_NAME,   \n");
          sb.append("       A.USER_GB      \n");
          sb.append("  FROM TUSER A        \n");
          sb.append(" WHERE A.USER_ID LIKE '"+retrieve.getString("USER_ID")+"'||'%' \n ");
          sb.append(retrieve.getString("MOD_QUERY"));
          sb.append(" ORDER BY A.USER_ID ASC \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Employee ID 정보를 얻는다
       * </PRE>
       * @return  Query
       */
       public String makeSqlEmployeeId(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append("SELECT A.EMPLOYEE_ID,     \n");
           sb.append("       A.EMPLOYEE_NAME,   \n");
           sb.append("       A.USER_GB      \n");
           sb.append("  FROM TEMPLOYEE A        \n");
           sb.append(" WHERE A.EMPLOYEE_ID LIKE '"+retrieve.getString("EMPLOYEE_ID")+"'||'%' \n ");
           sb.append(retrieve.getString("MOD_QUERY"));
           sb.append(" ORDER BY A.EMPLOYEE_ID ASC \n");
           return sb.toString();
       }

    /**
    * <PRE>
    * Desc : 판매구분 관계없이 상품코드 SELECT
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeAll(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,   \n");
        sb.append("       A.ENTP_CODE,   \n");
        sb.append("       A.TAX_YN,  \n");
        sb.append("       A.DELY_TYPE,   \n");
        sb.append("       A.SALE_GB,  \n");
        sb.append("       A.MD_CODE,   \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A   \n");
        sb.append(" WHERE A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 판매구분 관계없이 상품이름 SELECT
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsNameAll(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
         sb.append("       A.GOODS_NAME,   \n");
         sb.append("       A.ENTP_CODE,   \n");
         sb.append("       A.TAX_YN,  \n");
         sb.append("       A.DELY_TYPE,   \n");
         sb.append("       A.SALE_GB,  \n");
         sb.append("       A.MD_CODE,   \n");
         sb.append("       A.STOCK_CHK_PLACE  \n");
         sb.append("  FROM TGOODS A   \n");
         sb.append(" WHERE UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }


    /**
     * <PRE>
     * Desc : 판매구분이 영구중단이 아닌경우만 상품코드 SELECT
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsCode(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
         sb.append("       A.GOODS_NAME,   \n");
         sb.append("       A.ENTP_CODE,   \n");
         sb.append("       A.TAX_YN,  \n");
         sb.append("       A.DELY_TYPE,   \n");
         sb.append("       A.SALE_GB,  \n");
         sb.append("       A.MD_CODE,   \n");
         sb.append("       A.STOCK_CHK_PLACE  \n");
         sb.append("  FROM TGOODS A   \n");
         sb.append(" WHERE A.SALE_GB   != '19'  \n");
         sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
         sb.append("   AND A.SIGN_GB   = '80' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

     /** 2006.10.25 add
    * <PRE>
    * Desc : 상품명 (판매구분이 영구중단이 아닌경우만 상품코드 SELECT)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsName(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,   \n");
        sb.append("       A.ENTP_CODE,   \n");
        sb.append("       A.TAX_YN,  \n");
        sb.append("       A.DELY_TYPE,   \n");
        sb.append("       A.SALE_GB,  \n");
        sb.append("       A.MD_CODE,   \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A   \n");
        sb.append(" WHERE A.SALE_GB   != '19'  \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append("   AND A.SIGN_GB   = '80' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }
    /**
     * <PRE>
     * Desc : 판매구분이 영구중단이 아닌경우만 상품코드 SELECT
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsCodeMd(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
         sb.append("       A.GOODS_NAME,   \n");
         sb.append("       A.ENTP_CODE,   \n");
         sb.append("       A.TAX_YN,  \n");
         sb.append("       A.DELY_TYPE,   \n");
         sb.append("       A.SALE_GB,  \n");
         sb.append("       A.MD_CODE,   \n");
         sb.append("       B.MD_NAME,   \n");
         sb.append("       A.STOCK_CHK_PLACE  \n");
         sb.append("  FROM TGOODS A,   \n");
         sb.append("  	   TMD B   \n");
         sb.append(" WHERE A.MD_CODE = B.MD_CODE	\n");
         sb.append("   AND A.SALE_GB   != '19'  \n");
         sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

     /** 2006.10.25 add
    * <PRE>
    * Desc : 상품명 (판매구분이 영구중단이 아닌경우만 상품코드 SELECT)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameMd(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,   \n");
        sb.append("       A.ENTP_CODE,   \n");
        sb.append("       A.TAX_YN,  \n");
        sb.append("       A.DELY_TYPE,   \n");
        sb.append("       A.SALE_GB,  \n");
        sb.append("       A.MD_CODE,   \n");
        sb.append("       B.MD_NAME,   \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,   \n");
        sb.append("  	  TMD B   \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE	\n");
        sb.append("   AND A.SALE_GB   != '19'  \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 판매구분이 영구중단이 아닌경우만 상품코드 SELECT
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCopyCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,   \n");
        sb.append("       A.ENTP_CODE,   \n");
        sb.append("       A.DELY_TYPE    \n");
        sb.append("  FROM TGOODS A   \n");
        sb.append(" WHERE A.SET_YN = '0'  \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 판매구분이 영구중단이 아닌경우만 상품코드 SELECT
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsControlName(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT /*+RULE*/                     \n");
        sb.append("        A.CODE_MGROUP,                \n");
        sb.append("        A.CODE_GROUP,                 \n");
        sb.append("        A.CODE_NAME                   \n");
        sb.append("  FROM TCODE A                        \n");
        sb.append(" WHERE A.CODE_LGROUP = 'L010'         \n");
        sb.append("   AND A.USE_YN = '1'                 \n ");
        sb.append("   AND A.CODE_GROUP LIKE '"+retrieve.getString("CODE_GROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));

        log.debug(sb.toString());
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품코드(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeD(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE > '000'   \n");
        sb.append("   AND A.BUY_MED IN ( '11', '13','21' )  \n");
        sb.append("   AND A.GOODS_CODE > ' ' \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY LPAD(A.GOODS_CODE, 10, '0')  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품코드에 종속되는 단품만을 가져옴.(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeUniqueDt(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE > '000'   \n");
        sb.append("   AND A.BUY_MED IN ( '11', '13','21' )  \n");
        sb.append("   AND A.GOODS_CODE > ' ' \n");
        sb.append("   AND A.GOODS_CODE = '"+retrieve.getString("GOODS_CODE")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY LPAD(A.GOODS_CODE, 10, '0')  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품명(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameD(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE > '000'   \n");
        sb.append("   AND A.BUY_MED IN ( '11', '13','21' )  \n");
        sb.append("   AND A.GOODS_CODE > ' ' \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY LPAD(A.GOODS_CODE, 10, '0'), B.GOODSDT_CODE \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품코드(단품까지, 실재고 > 반품요청수량)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeDS(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       C.AQTY,  \n");
        sb.append("       C.BQTY,  \n");
        sb.append("       C.EOUT_QUEST_AQTY,  \n");
        sb.append("       C.EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B,  \n");
        sb.append("       TSTOCK C  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
        sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
        sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /** 2006.10.25 add
    * <PRE>
    * Desc : 상품명(단품까지, 실재고 > 반품요청수량)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameDS(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       C.AQTY,  \n");
        sb.append("       C.BQTY,  \n");
        sb.append("       C.EOUT_QUEST_AQTY,  \n");
        sb.append("       C.EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B,  \n");
        sb.append("       TSTOCK C  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
        sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
        sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품코드(재고가 있는상품)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeDS1(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("    SELECT /*+RULE*/                                                                       \n");
        sb.append("    C.GOODS_CODE,            		                                                      \n");
        sb.append("    C.GOODSDT_CODE,                                                                        \n");
        sb.append("    C.GOODS_NAME,            		                                                      \n");
        sb.append("    C.GOODSDT_INFO,              	                                                      \n");
        sb.append("    NVL(F.AQTY, 0)-NVL(F.ORDER_QTY, 0)-NVL(F.OUT_PLAN_QTY, 0) AS AQTY,  /*수정한부분*/       \n");
        sb.append("    NVL(F.BQTY, 0) AS BQTY,  		                                                      \n");
        sb.append("    NVL(F.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,                                         \n");
        sb.append("    NVL(F.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,                                         \n");
        sb.append("    C.STOCK_CHK_PLACE,                                                                     \n");
        sb.append("    F.WH_CODE        		                                                              \n");
        sb.append("    FROM (                                                                                 \n");
        sb.append("          SELECT A.GOODS_CODE,                                                             \n");
        sb.append("                 B.GOODSDT_CODE,                                                           \n");
        sb.append("                 A.GOODS_NAME,                                                             \n");
        sb.append("                 B.GOODSDT_INFO,                                                           \n");
        sb.append("                 A.STOCK_CHK_PLACE,                                                        \n");
        sb.append("                 A.WH_CODE                                                                 \n");
        sb.append("            FROM TGOODS A,                                                                 \n");
        sb.append("                 TGOODSDT B        	                                                      \n");
        sb.append("           WHERE A.GOODS_CODE = B.GOODS_CODE) C,     		                              \n");
        sb.append("           (                                                                               \n");
        sb.append("           SELECT D.WH_CODE,                                                               \n");
        sb.append("                  D.GOODS_CODE,                                                            \n");
        sb.append("                  D.GOODSDT_CODE,                                                          \n");
        sb.append("                  D.AQTY,                                                                  \n");
        sb.append("                  D.BQTY,                                                                  \n");
        sb.append("                  D.EOUT_QUEST_AQTY,                                                       \n");
        sb.append("                  D.EOUT_QUEST_BQTY,                                                       \n");
        sb.append("                  NVL(E.ORDER_QTY, 0) AS ORDER_QTY,                                        \n");
        sb.append("                  NVL(E.OUT_PLAN_QTY, 0) AS OUT_PLAN_QTY                                   \n");
        sb.append("             FROM TSTOCK D,                  						                      \n");
        sb.append("                  TORDERSTOCK E                                                            \n");
        sb.append("            WHERE D.WH_CODE = E.WH_CODE(+)                                                 \n");
        sb.append("              AND D.GOODS_CODE = E.GOODS_CODE(+)                                           \n");
        sb.append("              AND D.GOODSDT_CODE = E.GOODSDT_CODE(+) ) F                                   \n");
        sb.append("    WHERE F.GOODS_CODE = C.GOODS_CODE                                                      \n");
        sb.append("      AND F.GOODSDT_CODE = C.GOODSDT_CODE                                                  \n");
        sb.append("      AND C.GOODSDT_CODE > ' '               											  \n");
        sb.append("      AND F.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%'  				  \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();

        //= 2009.07.09 JSY DELETE
//        sb.append("  SELECT /*+RULE*/               \n");
//        sb.append("  C.GOODS_CODE,            		\n");
//        sb.append("  C.GOODSDT_CODE,                \n");
//        sb.append("  C.GOODS_NAME,            		\n");
//        sb.append("  C.GOODSDT_INFO,              	\n");
//        sb.append("  NVL(D.AQTY, 0)-NVL(E.ORDER_QTY, 0)-NVL(E.OUT_PLAN_QTY, 0) AS AQTY,  /*수정한부분*/  \n");
//        sb.append("  NVL(D.BQTY, 0) AS BQTY,  		\n");
//        sb.append("  NVL(D.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,       \n");
//        sb.append("  NVL(D.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,       \n");
//        sb.append("  C.STOCK_CHK_PLACE        		\n");
//        sb.append("  FROM (                         \n");
//        sb.append("        SELECT A.GOODS_CODE,     \n");
//        sb.append("                 B.GOODSDT_CODE, \n");
//        sb.append("             A.GOODS_NAME,       \n");
//        sb.append("                 B.GOODSDT_INFO, \n");
//        sb.append("                 A.STOCK_CHK_PLACE,    \n");
//        sb.append("                 A.WH_CODE             \n");
//        sb.append("          FROM TGOODS A,               \n");
//        sb.append("               TGOODSDT B        	  \n");
//        sb.append("         WHERE A.GOODS_CODE = B.GOODS_CODE) C,     		\n");
//        sb.append("         TSTOCK D,                  						\n");
//        sb.append("         TORDERSTOCK E                   /*수정한부분*/    \n");
//        sb.append("  WHERE D.GOODS_CODE   = C.GOODS_CODE    /*수정한부분*/	\n");
//        sb.append("    AND D.GOODSDT_CODE = C.GOODSDT_CODE  /*수정한부분*/    \n");
////        sb.append("    AND D.WH_CODE      = C.WH_CODE       /*수정한부분*/    \n");
//        sb.append("    AND E.WH_CODE 	  = D.WH_CODE       /*수정한부분*/    \n");
//        sb.append("    AND E.GOODS_CODE   = D.GOODS_CODE    /*수정한부분*/    \n");
//        sb.append("    AND E.GOODS_CODE   = C.GOODS_CODE    /*수정한부분*/    \n");
//        sb.append("    AND E.GOODSDT_CODE = C.GOODSDT_CODE  /*수정한부분*/    \n");
//
//        sb.append("    AND C.GOODSDT_CODE > ' '               \n");
//        sb.append("    AND C.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%'  \n ");
//        sb.append(retrieve.getString("MOD_QUERY"));


    }

    /**
     * <PRE>
     * Desc : 상품명(재고가 있는상품)
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsNameDS1(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("    SELECT /*+RULE*/                                                                       \n");
        sb.append("    C.GOODS_CODE,            		                                                      \n");
        sb.append("    C.GOODSDT_CODE,                                                                        \n");
        sb.append("    C.GOODS_NAME,            		                                                      \n");
        sb.append("    C.GOODSDT_INFO,              	                                                      \n");
        sb.append("    NVL(F.AQTY, 0)-NVL(F.ORDER_QTY, 0)-NVL(F.OUT_PLAN_QTY, 0) AS AQTY,  /*수정한부분*/       \n");
        sb.append("    NVL(F.BQTY, 0) AS BQTY,  		                                                      \n");
        sb.append("    NVL(F.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,                                         \n");
        sb.append("    NVL(F.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,                                         \n");
        sb.append("    C.STOCK_CHK_PLACE,                                                                     \n");
        sb.append("    F.WH_CODE        		                                                              \n");
        sb.append("    FROM (                                                                                 \n");
        sb.append("          SELECT A.GOODS_CODE,                                                             \n");
        sb.append("                 B.GOODSDT_CODE,                                                           \n");
        sb.append("                 A.GOODS_NAME,                                                             \n");
        sb.append("                 B.GOODSDT_INFO,                                                           \n");
        sb.append("                 A.STOCK_CHK_PLACE,                                                        \n");
        sb.append("                 A.WH_CODE                                                                 \n");
        sb.append("            FROM TGOODS A,                                                                 \n");
        sb.append("                 TGOODSDT B        	                                                      \n");
        sb.append("           WHERE A.GOODS_CODE = B.GOODS_CODE) C,     		                              \n");
        sb.append("           (                                                                               \n");
        sb.append("           SELECT D.WH_CODE,                                                               \n");
        sb.append("                  D.GOODS_CODE,                                                            \n");
        sb.append("                  D.GOODSDT_CODE,                                                          \n");
        sb.append("                  D.AQTY,                                                                  \n");
        sb.append("                  D.BQTY,                                                                  \n");
        sb.append("                  D.EOUT_QUEST_AQTY,                                                       \n");
        sb.append("                  D.EOUT_QUEST_BQTY,                                                       \n");
        sb.append("                  NVL(E.ORDER_QTY, 0) AS ORDER_QTY,                                        \n");
        sb.append("                  NVL(E.OUT_PLAN_QTY, 0) AS OUT_PLAN_QTY                                   \n");
        sb.append("             FROM TSTOCK D,                  						                      \n");
        sb.append("                  TORDERSTOCK E                                                            \n");
        sb.append("            WHERE D.WH_CODE = E.WH_CODE(+)                                                 \n");
        sb.append("              AND D.GOODS_CODE = E.GOODS_CODE(+)                                           \n");
        sb.append("              AND D.GOODSDT_CODE = E.GOODSDT_CODE(+) ) F                                   \n");
        sb.append("    WHERE F.GOODS_CODE = C.GOODS_CODE                                                      \n");
        sb.append("      AND F.GOODSDT_CODE = C.GOODSDT_CODE                                                  \n");
        sb.append("      AND C.GOODSDT_CODE > ' '               											  \n");
        sb.append("      AND UPPER(C.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' 	\n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();

        //= 2009.07.09 JSY DELETE
//        sb.append("  SELECT /*+RULE*/  			\n");
// 		sb.append("  A.GOODS_CODE,  			\n");
// 		sb.append("  B.GOODSDT_CODE,  			\n");
// 		sb.append("  A.GOODS_NAME,  			\n");
// 		sb.append("  B.GOODSDT_INFO,  			\n");
// 		sb.append("  NVL(C.AQTY, 0) AS AQTY,	\n");
// 		sb.append("  NVL(C.BQTY, 0) AS BQTY, 	\n");
// 		sb.append("  NVL(C.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,		\n");
// 		sb.append("  NVL(C.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,		\n");
// 		sb.append("  A.STOCK_CHK_PLACE 			\n");
// 		sb.append("  FROM TGOODS   A,				\n");
// 		sb.append("       TGOODSDT B,				\n");
// 		sb.append("       TSTOCK C					\n");
// 		sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE			\n");
// 		sb.append("    AND A.GOODS_CODE = C.GOODS_CODE 			\n");
// 		sb.append("    AND B.GOODSDT_CODE = C.GOODSDT_CODE		\n");
// 		sb.append("    AND A.WH_CODE = C.WH_CODE				\n");
// 		sb.append("    AND B.GOODSDT_CODE > ' ' 				\n");
// 		sb.append("    AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' 	\n ");
//        sb.append(retrieve.getString("MOD_QUERY"));

     }

    /**
    * <PRE>
    * Desc : 상품코드(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeUnit(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("  AND  B.GOODSDT_CODE > '000'   \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0'), B.GOODSDT_CODE ASC  \n");

        return sb.toString();
    }

    /** 2006.10.25 add
    * <PRE>
    * Desc : 상품명(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameUnit(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("  AND  B.GOODSDT_CODE > '000'   \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.GOODS_NAME, B.GOODSDT_CODE ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품코드(단품까지)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsDtCodeD(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       0 AQTY,  \n");
        sb.append("       0 BQTY,  \n");
        sb.append("       0 EOUT_QUEST_AQTY,  \n");
        sb.append("       0 EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("  AND  B.GOODSDT_CODE > '000'  \n");
        sb.append("   AND B.GOODSDT_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0'), B.GOODSDT_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품코드(상품+MD)
    * </PRE>
    * @return  Query
    */
    public String makeSqlSGoodsCodeMd(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       A.MD_CODE,  \n");
        sb.append("       B.MD_NAME   \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TMD B  \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE   \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0'), A.MD_CODE ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 단품코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsDtCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODSDT_CODE,  \n");
        sb.append("       A.BARCODE,  \n");
        sb.append("       A.GOODSDT_INFO   \n");
        sb.append("  FROM TGOODSDT A  \n");
        sb.append(" WHERE A.GOODSDT_CODE > '000' \n");
        sb.append("   AND A.GOODSDT_CODE LIKE '"+retrieve.getString("GOODSDT_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0'), A.GODSDT_CODE ASC  \n");

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품코드(가격)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodePrice(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       A.ENTP_CODE, \n");
        sb.append("       C.ENTP_NAME,  \n");
        sb.append("       A.DELY_TYPE, \n");
        sb.append("       B.BUY_COST,  \n");
        sb.append("       B.BUY_VAT,  \n");
        sb.append("       B.BUY_PRICE,  \n");
        sb.append("       B.SALE_PRICE,  \n");
        sb.append("       B.CUST_PRICE, \n");
        sb.append("       FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'8') AS MARGIN_RATE, \n");
        sb.append("       A.MAKECO_CODE, \n");
        sb.append("       D.MAKECO_NAME,  \n");
        sb.append("       E.BRAND_NAME, \n");
        sb.append("       A.SET_YN,  \n");
        sb.append("       A.TAX_YN  \n");
        sb.append("  FROM TGOODS A, \n");
        sb.append("       TGOODSPRICE B, \n");
        sb.append("       TENTERPRISE C, \n");
        sb.append("       TMAKECOMP D, \n");
        sb.append("       TBRAND E \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE)   \n");
        sb.append("                          FROM TGOODSPRICE C   \n");
        sb.append("                         WHERE A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("                           AND C.APPLY_DATE <= SYSDATE )   \n");
        sb.append("   AND A.ENTP_CODE   = C.ENTP_CODE  \n");
        sb.append("   AND A.MAKECO_CODE = D.MAKECO_CODE \n");
        sb.append("   AND A.BRAND_CODE  = E.BRAND_CODE \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0') ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품명(가격)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNamePrice(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       A.ENTP_CODE, \n");
        sb.append("       C.ENTP_NAME,  \n");
        sb.append("       A.DELY_TYPE, \n");
        sb.append("       B.BUY_COST,  \n");
        sb.append("       B.BUY_VAT,  \n");
        sb.append("       B.SALE_PRICE,  \n");
        sb.append("       B.CUST_PRICE, \n");
        sb.append("       FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'8') AS MARGIN_RATE, \n");
        sb.append("       A.MAKECO_CODE, \n");
        sb.append("       D.MAKECO_NAME,  \n");
        sb.append("       E.BRAND_NAME, \n");
        sb.append("       A.SET_YN,  \n");
        sb.append("       A.TAX_YN  \n");
        sb.append("  FROM TGOODS A, \n");
        sb.append("       TGOODSPRICE B, \n");
        sb.append("       TENTERPRISE C, \n");
        sb.append("       TMAKECOMP D, \n");
        sb.append("       TBRAND E \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE)   \n");
        sb.append("                          FROM TGOODSPRICE C   \n");
        sb.append("                         WHERE A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("                           AND C.APPLY_DATE <= SYSDATE )   \n");
        sb.append("   AND A.ENTP_CODE = C.ENTP_CODE  \n");
        sb.append("   AND A.MAKECO_CODE = D.MAKECO_CODE \n");
        sb.append("   AND A.BRAND_CODE = E.BRAND_CODE \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY LPAD(A.GOODS_CODE, 10, '0') ASC  \n");
        return sb.toString();
    }


    /** 2007.01.12 add
     * <PRE>
     * Desc : 고정랙 상품)
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsCodeTreat(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.GOODS_CODE, \n");
         sb.append("       B.GOODSDT_CODE, \n");
         sb.append("       A.GOODS_NAME, \n");
         sb.append("       B.GOODSDT_INFO, \n");
         sb.append("       B.FIX_RACK_CODE, \n");
         sb.append("       NVL(C.RACK_QTY, 0) AS RACK_QTY, \n");
         sb.append("       (SELECT COUNT(*) FROM TMANUALGOODS WHERE GOODS_CODE = A.GOODS_CODE AND GOODSDT_CODE = B.GOODSDT_CODE) AS MANUAL_CNT \n");
         sb.append("  FROM TGOODS   A, \n");
         sb.append("       TGOODSDT B, \n");
         sb.append("       TRACK    C \n");
         sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE \n");
         sb.append("   AND B.GOODSDT_CODE = '001' \n");
         sb.append("   AND B.GOODS_CODE = C.GOODS_CODE(+) \n");
         sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE(+) \n");
         sb.append("   AND B.FIX_RACK_CODE = C.RACK_CODE(+) \n");
         sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));

         return sb.toString();
     }

     /** 2007.01.12 add
     * <PRE>
     * Desc : 고정랙 상품
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsNameTreat(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.GOODS_CODE, \n");
         sb.append("       B.GOODSDT_CODE, \n");
         sb.append("       A.GOODS_NAME, \n");
         sb.append("       B.GOODSDT_INFO, \n");
         sb.append("       B.FIX_RACK_CODE, \n");
         sb.append("       NVL(C.RACK_QTY, 0) AS RACK_QTY, \n");
         sb.append("       (SELECT COUNT(*) FROM TMANUALGOODS WHERE GOODS_CODE = A.GOODS_CODE AND GOODSDT_CODE = B.GOODSDT_CODE) AS MANUAL_CNT \n");
         sb.append("  FROM TGOODS   A, \n");
         sb.append("       TGOODSDT B, \n");
         sb.append("       TRACK    C \n");
         sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE \n");
         sb.append("   AND B.GOODSDT_CODE = '001' \n");
         sb.append("   AND B.GOODS_CODE = C.GOODS_CODE(+) \n");
         sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE(+) \n");
         sb.append("   AND B.FIX_RACK_CODE = C.RACK_CODE(+) \n");
         sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));

         return sb.toString();
     }


    /**
    * <PRE>
    * Desc : 상품분류 : 대분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlLgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT A.LGROUP, \n ");
        sb.append("        A.LGROUP_NAME \n ");
        sb.append("   FROM TGOODSKINDS A \n ");
        sb.append("  WHERE A.LGROUP LIKE  '"+retrieve.getString("LGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY A.LGROUP ASC \n ");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품분류 : 중분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlMgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT A.LGROUP, \n");
        sb.append("       A.MGROUP, \n");
        sb.append("       A.LGROUP_NAME,  \n");
        sb.append("       A.MGROUP_NAME  \n");
        sb.append("  FROM TGOODSKINDS A  \n");
        sb.append(" WHERE A.MGROUP LIKE '"+retrieve.getString("MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.LGROUP ASC, A.MGROUP ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품분류 : 소분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlSgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT A.LGROUP,\n");
        sb.append("       A.MGROUP, \n");
        sb.append("       A.SGROUP, \n");
        sb.append("       A.LGROUP_NAME, \n");
        sb.append("       A.MGROUP_NAME, \n");
        sb.append("       A.SGROUP_NAME  \n");
        sb.append("  FROM TGOODSKINDS A \n");
        sb.append(" WHERE A.SGROUP LIKE '"+retrieve.getString("SGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.LGROUP ASC, A.MGROUP ASC, A.SGROUP ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품분류 : 세분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlDgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.LGROUP, \n");
        sb.append("       A.MGROUP, \n");
        sb.append("       A.SGROUP, \n");
        sb.append("       A.DGROUP, \n");
        sb.append("       A.LGROUP_NAME, \n");
        sb.append("       A.MGROUP_NAME, \n");
        sb.append("       A.SGROUP_NAME, \n");
        sb.append("       A.DGROUP_NAME, \n");
        sb.append("       A.QC_LGROUP, \n");
        sb.append("       A.QC_MGROUP, \n");
//        sb.append("       A.SIZE_LGROUP, \n");
        sb.append("       A.DEF_VAT_RATE \n");
        sb.append("  FROM TGOODSKINDS A \n");
        sb.append(" WHERE A.DGROUP LIKE '"+retrieve.getString("DGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.LGROUP ASC, A.MGROUP ASC, A.SGROUP, A.DGROUP ASC  \n");
        return sb.toString();

        /* delete QC table
        sb.append("SELECT A.LGROUP, \n");
        sb.append("       A.MGROUP, \n");
        sb.append("       A.SGROUP, \n");
        sb.append("       A.DGROUP, \n");
        sb.append("       A.LGROUP_NAME, \n");
        sb.append("       A.MGROUP_NAME, \n");
        sb.append("       A.SGROUP_NAME, \n");
        sb.append("       A.DGROUP_NAME, \n");
        sb.append("       A.QC_LGROUP, \n");
        sb.append("       B.QC_LGROUP_NAME, \n");
        sb.append("       A.QC_MGROUP, \n");
        sb.append("       B.QC_MGROUP_NAME, \n");
        sb.append("       A.SIZE_LGROUP, \n");
        sb.append("       A.DEF_VAT_RATE \n");
        sb.append("  FROM TGOODSKINDS A,  \n");
        sb.append("       TQCKINDS B      \n");
        sb.append(" WHERE A.DGROUP LIKE '"+retrieve.getString("DGROUP")+"'||'%' \n ");
        sb.append("   AND A.QC_LGROUP = B.QC_LGROUP(+) \n ");
        sb.append("   AND A.QC_MGROUP = B.QC_MGROUP(+) \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.LGROUP ASC, A.MGROUP ASC, A.SGROUP, A.DGROUP ASC  \n");
        */

    }

    /**
    * <PRE>
    * Desc : 색상코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlColorCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CSPF_GROUP, 		\n");
        sb.append(" 	   A.CSPF_CODE, 		\n");
        sb.append(" 	   A.CSPF_NAME			\n");
        sb.append("   FROM TGOODSINFODT A 		\n");
        sb.append("  WHERE A.CSPF_GROUP LIKE UPPER('C000')	 \n ");
        sb.append("    AND A.USE_YN = '1'  		\n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY A.CSPF_CODE ASC  	\n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 크기코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlSizeCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CSPF_GROUP, 		\n");
        sb.append(" 	   A.CSPF_CODE, 		\n");
        sb.append(" 	   A.CSPF_NAME			\n");
        sb.append("   FROM TGOODSINFODT A 		\n");
        sb.append("  WHERE A.CSPF_GROUP LIKE '"+retrieve.getString("SIZE_LGROUP")+"' || '%' \n ");
        sb.append("    AND A.USE_YN = '1'  		\n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY A.CSPF_CODE ASC  	\n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 무늬코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlPatternCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CSPF_GROUP, 		\n");
        sb.append(" 	   A.CSPF_CODE, 		\n");
        sb.append(" 	   A.CSPF_NAME			\n");
        sb.append("   FROM TGOODSINFODT A 		\n");
        sb.append("  WHERE A.CSPF_GROUP LIKE UPPER('P000')	 \n ");
        sb.append("    AND A.USE_YN = '1'  		\n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY A.CSPF_CODE ASC  	\n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 형태코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlFormCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CSPF_GROUP, 		\n");
        sb.append(" 	   A.CSPF_CODE, 		\n");
        sb.append(" 	   A.CSPF_NAME			\n");
        sb.append("   FROM TGOODSINFODT A 		\n");
        sb.append("  WHERE A.CSPF_GROUP LIKE 'F%' 		\n");
        sb.append("	   AND A.CSPF_GROUP LIKE '"+retrieve.getString("FORM_LGROUP")+"' || '%' \n ");
        sb.append("    AND A.USE_YN = '1'  		\n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY A.CSPF_GROUP, A.CSPF_CODE ASC  	\n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 공급업체코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlEntpCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.ENTP_CODE, \n");
        sb.append("       A.ENTP_NAME, \n");
        sb.append("       A.S_IDNO, \n");
        sb.append("       A.OWNER_NAME \n");
        sb.append("  FROM TENTERPRISE A \n");
        sb.append(" WHERE A.ENTP_CODE LIKE '"+retrieve.getString("ENTP_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.ENTP_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 공급업체 담당자 SEQ
    * </PRE>
    * @return  Query
    */
    public String makeSqlEntpManSeq(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.ENTP_CODE, \n");
        sb.append("       A.ENTP_NAME, \n");
        sb.append("       A.S_IDNO, \n");
        sb.append("       A.OWNER_NAME, \n");
        sb.append("       ( SELECT D.ENTP_MAN_SEQ  \n");
        sb.append("           FROM TENTPUSER D \n");
        sb.append("          WHERE D.ENTP_CODE = A.ENTP_CODE  \n");
        sb.append("       	   AND D.DEFAULT_YN = '1' \n");
        sb.append("       	   AND D.USE_YN = '1' \n");
        sb.append("       	   AND ROWNUM = 1)  AS ENTP_MAN_SEQ  \n");
        sb.append("  FROM TENTERPRISE A \n");
        sb.append(" WHERE A.ENTP_CODE LIKE '"+retrieve.getString("ENTP_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.ENTP_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : MD 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlMdCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT A.MD_CODE, \n");
        sb.append("       A.MD_NAME  \n");
        sb.append("  FROM TMD A, \n");
        sb.append("       TMDLINK B \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE(+) \n ");
        sb.append("   AND A.MD_CODE LIKE '"+retrieve.getString("MD_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.MD_CODE ASC \n");
        return sb.toString();

/*      //= 2009.9.1 JSY 수정
        sb.append("SELECT A.MD_CODE, \n");
        sb.append("       A.MD_NAME  \n");
        sb.append("  FROM TMD A \n");
        sb.append(" WHERE A.MD_CODE LIKE '"+retrieve.getString("MD_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.MD_CODE ASC \n");
*/

    }

    /**
     * <PRE>
     * Desc : DM 코드
     * </PRE>
     * @return  Query
     */
     public String makeSqlDmCode(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.DM_CODE, \n");
         sb.append("       A.DM_NOTE  \n");
         sb.append("  FROM TDMSENDM A \n");
         sb.append(" WHERE A.DM_CODE LIKE '"+retrieve.getString("DM_CODE")+"'||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append(" ORDER BY A.DM_CODE ASC \n");
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : 상품기술서 기본정보 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlDescribeBaseCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.DESCRIBE_CODE, \n");
        sb.append("       A.DESCRIBE_TITLE \n");
        sb.append("  FROM TDESCRIBECODE A \n");
        sb.append(" WHERE A.DESCRIBE_CODE LIKE '"+retrieve.getString("DESCRIBE_BASE_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.DESCRIBE_CODE ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 우편번호
    * </PRE>
    * @return  Query
    */
    public String makeSqlPostNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.POST_NO,  \n");
        sb.append("       A.POST_SEQ,  \n");
        sb.append("       A.CITY_NAME,  \n");
        sb.append("       A.GU_NAME,  \n");
        sb.append("       A.DONG_NAME,  \n");
        sb.append("		  FUN_DISPLAY_POSTADDR(A.CITY_NAME, A.GU_NAME, A.DONG_NAME) AS COMP_ADDR, \n");
        sb.append("       A.DDD,  \n");
        sb.append("		  A.DELIVERY_POINT_COUNT,  \n");
        sb.append("		  FUN_GET_SHIPCOST(SYSDATE,A.AREA_GB,'1','0') AS OUT_SHIPCOST,  \n");
        sb.append("		  FUN_GET_SHIPCOST(SYSDATE,A.AREA_GB,'2','0') AS RETURN_SHIPCOST  \n");
        sb.append("  FROM TPOST A  \n");
        sb.append(" WHERE A.USE_YN = '1' \n");
        sb.append("   AND A.POST_NO LIKE '"+retrieve.getString("POST_NO")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.POST_NO ASC, A.POST_SEQ ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 우편번호 : 동명
    * </PRE>
    * @return  Query
    */
    public String makeSqlDongName(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.POST_NO, \n");
        sb.append("       A.POST_SEQ,  \n");
        sb.append("       A.CITY_NAME,  \n");
        sb.append("       A.GU_NAME,  \n");
        sb.append("       A.DONG_NAME,  \n");
        sb.append("		  FUN_DISPLAY_POSTADDR(A.CITY_NAME, A.GU_NAME, A.DONG_NAME) AS COMP_ADDR, \n");
        sb.append("       A.DDD,  \n");
        sb.append("		  A.DELIVERY_POINT_COUNT,  \n");
        sb.append("		  FUN_GET_SHIPCOST(SYSDATE,A.AREA_GB,'1','0') AS OUT_SHIPCOST,  \n");
        sb.append("		  FUN_GET_SHIPCOST(SYSDATE,A.AREA_GB,'2','0') AS RETURN_SHIPCOST  \n");
        sb.append("  FROM TPOST A \n");
        sb.append(" WHERE A.USE_YN = '1' \n");
        sb.append("   AND UPPER(A.DONG_NAME) LIKE '%'||UPPER('"+retrieve.getString("DONG_NAME")+"')||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CITY_NAME, A.GU_NAME, A.DONG_NAME  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 전국구분코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlAreaGb(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT AREA_GB ,			\n");
        sb.append("       AREA_NAME		 	\n");
        sb.append("  FROM TAREA_ZONE 		\n");
        sb.append(" WHERE AREA_GB like  '"+retrieve.getString("AREA_GB")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY AREA_GB ASC  \n");

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 시,구명
    * </PRE>
    * @return  Query
    */
    public String makeSqlPostArea(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  DISTINCT SUBSTR(POST_NO, 1, 3) POST_NO, \n");
        sb.append("         CITY_NAME,  \n");
        sb.append("         GU_NAME \n");
        sb.append("   FROM  TPOST \n");
        sb.append("  WHERE  USE_YN = '1' \n");
        sb.append("    AND  POST_NO LIKE '"+retrieve.getString("POST_NO")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY POST_NO \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 제조업체코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlMakeCompCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.MAKECO_CODE, \n");
        sb.append("     A.MAKECO_NAME \n");
        sb.append("  FROM TMAKECOMP A \n");
        sb.append(" WHERE A.MAKECO_CODE LIKE '"+retrieve.getString("MAKECO_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.MAKECO_CODE ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 매체코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlMediaCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT B.CODE_MGROUP, \n");
        sb.append("        B.CODE_NAME, \n");
        sb.append("        A.MEDIA_CODE, \n");
        sb.append("        A.MEDIA_NAME, \n");
        sb.append("        A.MEDIA_YEAR \n");
        sb.append("   FROM TMEDIA A, \n");
        sb.append("        TCODE B \n");
        sb.append("  WHERE B.CODE_LGROUP = 'B009' \n");
        sb.append("    AND B.CODE_MGROUP = A.MEDIA_GB \n");
        sb.append("    AND A.MEDIA_CODE LIKE '"+retrieve.getString("MEDIA_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY B.CODE_MGROUP, A.MEDIA_CODE ASC  \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 매체코드(Multi Selection)
     * </PRE>
     * @return  Query
     */
     public String makeSqlMediaCodeMulti(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT B.CODE_MGROUP, \n");
         sb.append("        B.CODE_NAME, \n");
         sb.append("        A.MEDIA_CODE, \n");
         sb.append("        A.MEDIA_NAME, \n");
         sb.append("        A.MEDIA_YEAR, \n");
         sb.append("        '' AS SELECTION \n");
         sb.append("   FROM TMEDIA A, \n");
         sb.append("        TCODE B \n");
         sb.append("  WHERE B.CODE_LGROUP = 'B009' \n");
         sb.append("    AND B.CODE_MGROUP = A.MEDIA_GB \n");
         sb.append("    AND A.MEDIA_CODE LIKE '"+retrieve.getString("MEDIA_CODE")+"'||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append("  ORDER BY B.CODE_MGROUP, A.MEDIA_CODE ASC  \n");
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : 위치코드정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlRackCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.RACK_CODE, \n");
        sb.append("        A.RACK_GRADE, \n");
        sb.append("        B.CODE_NAME AS GRADE, \n");
        sb.append("        A.RACK_GB, \n");
        sb.append("        C.CODE_NAME AS GB, \n");
        sb.append("        A.RACK_NOTE,  \n");
        sb.append("        A.CAPACITY_QTY  \n");
        sb.append("  FROM TRACKCODE A, \n");
        sb.append("       TCODE B, \n");
        sb.append("       TCODE C  \n");
        sb.append(" WHERE  A.RACK_CODE LIKE '"+retrieve.getString("RACK_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("   AND A.USE_YN = '1' \n");
        sb.append("   AND A.RACK_GRADE = B.CODE_MGROUP \n");
        sb.append("   AND B.CODE_LGROUP = 'L039' \n");
        sb.append("   AND A.RACK_GB    = C.CODE_MGROUP \n");
        sb.append("   AND C.CODE_LGROUP = 'L038' \n");
        sb.append(" ORDER BY A.RACK_CODE ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품단품의 위치코드정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlRackCodeGoods(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.RACK_CODE,                      \n");
        sb.append("        A.RACK_GRADE,                     \n");
        sb.append("        A.RACK_GB,                        \n");
        sb.append("        A.RACK_NOTE,                      \n");
        sb.append("        B.RACK_QTY,                       \n");
        sb.append("        B.PICKING_QTY,                    \n");
        sb.append("        B.GOODS_CODE,                     \n");
        sb.append("        B.GOODSDT_CODE,                    \n");
        sb.append("        DECODE(A.RACK_GRADE, '1' , (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_ARACK'), '2', (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_BRACK')) AS OUT_RACK \n");
        sb.append("   FROM TRACKCODE A,                      \n");
        sb.append("        (SELECT Z.RACK_CODE,              \n");
        sb.append("                Z.GOODS_CODE,             \n");
        sb.append("                Z.GOODSDT_CODE,           \n");
        sb.append("                Z.RACK_QTY,               \n");
        sb.append("                Z.PICKING_QTY             \n");
        sb.append("           FROM TRACK Z                   \n");
        sb.append("          WHERE Z.GOODS_CODE   = '" + retrieve.getString("GOODS_CODE") + "' \n ");
        sb.append("            AND Z.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE") + "' )B \n ");
        sb.append("  WHERE A.RACK_CODE LIKE '" + retrieve.getString("RACK_CODE") + "'||'%' \n ");
        sb.append("    AND A.RACK_CODE = B.RACK_CODE(+)      \n");
        sb.append("    AND A.USE_YN = '1'                    \n");
        sb.append("    AND A.RACK_GRADE LIKE '" + retrieve.getString("RACK_GRADE") + "'||'%' \n ");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품단품의 위치코드정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlRackCodeGrade(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RACK_CODE,                      \n");
        sb.append("        A.RACK_GRADE,                     \n");
        sb.append("        A.RACK_GB,                        \n");
        sb.append("        A.RACK_NOTE,                      \n");
        sb.append("        B.RACK_QTY,                       \n");
        sb.append("        B.PICKING_QTY,                    \n");
        sb.append("        B.GOODS_CODE,                     \n");
        sb.append("        B.GOODSDT_CODE,                    \n");
        sb.append("        DECODE(A.RACK_GRADE, '1' , (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_ARACK'), '2', (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_BRACK')) AS OUT_RACK \n");
        sb.append("   FROM TRACKCODE A, \n");
        sb.append("        (SELECT Z.RACK_CODE,  \n");
        sb.append("                     Z.GOODS_CODE, \n");
        sb.append("                     Z.GOODSDT_CODE,  \n");
        sb.append("                     Z.RACK_QTY,  \n");
        sb.append("                     Z.PICKING_QTY  \n");
        sb.append("              FROM TRACK Z  \n");
        sb.append("             WHERE Z.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("               AND Z.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
        sb.append("            AND Z.WH_CODE = '" + retrieve.getString("WH_CODE")+ "' \n");
        sb.append("        )B  \n");
        sb.append("  WHERE A.RACK_CODE = B.RACK_CODE(+)  \n");
        sb.append("    AND A.WH_CODE = '" + retrieve.getString("WH_CODE") + "' \n");
        sb.append("    AND A.RACK_CODE LIKE '" + retrieve.getString("RACK_CODE") + "'||'%' \n ");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }


    public String makeSqlRackCodeFix(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RACK_CODE,  \n");
        sb.append("        A.RACK_GRADE,  \n");
        sb.append("        A.RACK_GB, \n");
        sb.append("        A.RACK_NOTE, \n");
        sb.append("        NVL(A.CAPACITY_QTY, 0) 	CAPACITY_QTY, \n");
        sb.append("        NVL(B.RACK_QTY, 0)		RACK_QTY,\n");
        sb.append("        NVL(B.PICKING_QTY, 0)	PICKING_QTY, \n");
        sb.append("        B.GOODS_CODE, \n");
        sb.append("        B.GOODSDT_CODE, \n");
        sb.append("        DECODE(RACK_QTY, NULL, '1', 0, '1', '0') GUBUN   \n");
        sb.append("   FROM TRACKCODE A, \n");
        sb.append("        (SELECT Z.RACK_CODE, \n");
        sb.append("                Z.GOODS_CODE, \n");
        sb.append("                Z.GOODSDT_CODE, \n");
        sb.append("                Z.RACK_QTY, \n");
        sb.append("                Z.PICKING_QTY \n");
        sb.append("           FROM TRACK Z \n");
        sb.append("          WHERE Z.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n ");
        sb.append("            AND Z.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE") + "' )B \n");
        sb.append("  WHERE A.RACK_CODE = B.RACK_CODE(+) \n");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append("    AND A.RACK_GRADE   LIKE '" + retrieve.getString("RACK_GRADE") + "'||'%' \n ");
        sb.append("    AND A.RACK_CODE    LIKE '"    + retrieve.getString("RACK_CODE")  + "'||'%'     \n ");
        //sb.append("    AND A.WH_CODE    = '"    + retrieve.getString("RACK_CODE")  + "'      \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        /*
        if (retrieve.getString("RACK_GRADE").equals("1")){
            sb.append("    AND A.RACK_CODE IN (SELECT T.FIX_RACK_CODE \n");
            sb.append("                          FROM TGOODSDT T \n");
            sb.append("                         WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n ");
            sb.append("                           AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
            sb.append("                         UNION ALL \n");
            sb.append("                        SELECT T.FREE_RACK_CODE \n");
            sb.append("                          FROM TGOODSDT T \n");
            sb.append("                         WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n ");
            sb.append("                           AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
            sb.append("                         UNION ALL \n");
            sb.append("                        SELECT T.REP_FIX_RACK_CODE \n");
            sb.append("                          FROM TGOODSDT T \n");
            sb.append("                         WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n ");
            sb.append("                           AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
            sb.append("                         UNION ALL \n");
            sb.append("                        SELECT T.REP_FREE_RACK_CODE \n");
            sb.append("                          FROM TGOODSDT T \n");
            sb.append("                         WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n ");
            sb.append("                           AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
            sb.append("                       ) \n");
        }
		*/
        return sb.toString();
    }


    public String makeSqlRackCodeEtc(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RACK_CODE,   \n");
        sb.append("          A.RACK_GRADE,  \n");
        sb.append("          A.RACK_GB,  \n");
        sb.append("          A.RACK_NOTE,  \n");
        sb.append("          A.CAPACITY_QTY,  \n");
        sb.append("          B.RACK_QTY,  \n");
        sb.append("          B.PICKING_QTY,  \n");
        sb.append("          B.GOODS_CODE,  \n");
        sb.append("          B.GOODSDT_CODE \n");
        sb.append("   FROM TRACKCODE A, \n");
        sb.append("        (SELECT Z.RACK_CODE,  \n");
        sb.append("                     Z.GOODS_CODE, \n");
        sb.append("                     Z.GOODSDT_CODE,  \n");
        sb.append("                     Z.RACK_QTY,  \n");
        sb.append("                     Z.PICKING_QTY  \n");
        sb.append("              FROM TRACK Z  \n");
        sb.append("             WHERE Z.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("               AND Z.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
        sb.append("            AND Z.WH_CODE = '" + retrieve.getString("WH_CODE")+ "' \n");
        sb.append("        )B  \n");
        sb.append("  WHERE A.RACK_CODE = B.RACK_CODE(+)  \n");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append("    AND ( A.RACK_CODE IN (SELECT T.FIX_RACK_CODE  \n");
        sb.append("                               FROM TGOODSDT T  \n");
        sb.append("                              WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("                                 AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
        sb.append("                              UNION ALL  \n");
        sb.append("                             SELECT T.FREE_RACK_CODE  \n");
        sb.append("                               FROM TGOODSDT T  \n");
        sb.append("                              WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("                                 AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
        sb.append("                              UNION ALL  \n");
        sb.append("                             SELECT T.REP_FIX_RACK_CODE  \n");
        sb.append("                               FROM TGOODSDT T  \n");
        sb.append("                              WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("                                 AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "' \n");
        sb.append("                              UNION ALL  \n");
        sb.append("                             SELECT T.REP_FREE_RACK_CODE  \n");
        sb.append("                               FROM TGOODSDT T  \n");
        sb.append("                              WHERE T.GOODS_CODE = '" + retrieve.getString("GOODS_CODE") + "' \n");
        sb.append("                                 AND T.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE")+ "') \n");
        sb.append("          OR A.RACK_GB = '90') \n");
        sb.append("    AND A.WH_CODE  = '" + retrieve.getString("WH_CODE")+ "' \n");
        sb.append("    AND A.RACK_GRADE = '" + retrieve.getString("RACK_GRADE")+ "' \n");

        return sb.toString();
    }


    public String makeSqlRackCodeNotReg(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RACK_CODE, \n");
        sb.append("        B.RACK_GRADE, \n");
        sb.append("        B.RACK_GB \n");
        sb.append("   FROM (SELECT RACK_CODE \n");
        sb.append("           FROM TRACKCODE \n");
        sb.append("          MINUS \n");
        sb.append("        (SELECT FIX_RACK_CODE \n");
        sb.append("           FROM TGOODSDT \n");
        sb.append("          UNION \n");
        sb.append("         SELECT FREE_RACK_CODE \n");
        sb.append("           FROM TGOODSDT \n");
        sb.append("          UNION \n");
        sb.append("         SELECT REP_FIX_RACK_CODE \n");
        sb.append("           FROM TGOODSDT \n");
        sb.append("          UNION \n");
        sb.append("         SELECT REP_FREE_RACK_CODE \n");
        sb.append("           FROM TGOODSDT)) A, \n");
        sb.append("        TRACKCODE B \n");
        sb.append("  WHERE A.RACK_CODE = B.RACK_CODE \n");
        sb.append("    AND A.RACK_CODE <> '0019999999' \n");
        sb.append("    AND B.WH_CODE = '001' \n");

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 실사코드정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlCheckCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CHECK_CODE, \n");
        sb.append("        A.CHECK_GB, \n");
        sb.append("        A.STOCK_CHECK_NOTE, \n");
        sb.append("        TO_CHAR(A.CHECK_DATE, 'YYYY/MM/DD') AS CHECK_DATE \n");
        sb.append("   FROM TSTOCKCHECKCODE A \n");
        sb.append("  WHERE A.CHECK_CODE LIKE '"+retrieve.getString("CHECK_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CHECK_DATE ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : QC대분류
    * </PRE>
    * @return  Query
    */
    public String makeSqlQcLgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT A.QC_LGROUP,\n");
        sb.append("       A.QC_LGROUP_NAME \n");
        sb.append("  FROM TQCKINDS A \n");
        sb.append(" WHERE A.QC_LGROUP LIKE '"+retrieve.getString("QC_LGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.QC_LGROUP ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : QC대분류/QC중분류
    * </PRE>
    * @return  Query
    */
    public String makeSqlQcMgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT A.QC_LGROUP, \n");
        sb.append("       A.QC_LGROUP_NAME, \n");
        sb.append("       A.QC_MGROUP, \n");
        sb.append("       A.QC_MGROUP_NAME \n");
        sb.append("  FROM TQCKINDS A \n");
        sb.append(" WHERE A.QC_MGROUP LIKE '"+retrieve.getString("QC_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.QC_LGROUP, A.QC_MGROUP ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 사은품프로모션번호
    * </PRE>
    * @return  Query
    */
    public String makeSqlPromoNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.PROMO_NO, \n");
        sb.append("       A.PROMO_NAME \n");
        sb.append("  FROM TPROMOM A \n");
        sb.append(" WHERE A.PROMO_NO LIKE '"+retrieve.getString("PROMO_NO")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.PROMO_NO DESC  \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 추첨프로모션번호
     * </PRE>
     * @return  Query
     */
     public String makeSqlLotteryPromoNo(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.LOTTERY_PROMO_NO, \n");
         sb.append("       A.LOTTERY_PROMO_NAME, \n");
         sb.append("       A.PROMO_NOTE,    \n");
         sb.append("       A.PROVIDE_CNT,   \n");
         sb.append("       A.END_YN,        \n");
         sb.append("       TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,      \n");
         sb.append("       A.CONFIRM_CNT,   \n");
         sb.append("       A.DO_TYPE,        \n");
         sb.append("       (SELECT COUNT(*) AS CNT       \n");
         sb.append("          FROM TLOTTERYPROMOCUST B, TCUSTOMER C        \n");
         sb.append("         WHERE B.CUST_NO = C.CUST_NO        \n");
         sb.append("           AND B.LOTTERY_PROMO_NO = A.LOTTERY_PROMO_NO  \n");
         sb.append("           AND B.CANCEL_YN = '0') CNT,        \n");
         sb.append("       A.APPLY_YN        \n");
         sb.append("  FROM TLOTTERYPROMOM A \n");
         sb.append(" WHERE A.LOTTERY_PROMO_NO LIKE '"+retrieve.getString("LOTTERY_PROMO_NO")+"' ||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append(" ORDER BY A.LOTTERY_PROMO_NO DESC  \n");

         return sb.toString();
     }


    /**
    * <PRE>
    * Desc : 원산지코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlOriginCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_MGROUP, \n");
        sb.append("       A.CODE_NAME \n");
        sb.append("  FROM TCODE A \n");
        sb.append(" WHERE A.CODE_LGROUP = 'B023' \n");
        sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품코드 : 상품기술서와 JOIN
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeDescribe(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE, \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       A.ENTP_CODE,  \n");
        sb.append("       A.TAX_YN,  \n");
        sb.append("       A.DELY_TYPE,  \n");
        sb.append("       A.SALE_GB, \n");
        sb.append("       A.MD_CODE,  \n");
        sb.append("       B.MD_NAME, \n");
        sb.append("       A.STOCK_CHK_PLACE \n");
        sb.append("  FROM TGOODS A, TMD B \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE \n");
        sb.append("   AND EXISTS ( SELECT C.GOODS_CODE \n");
        sb.append("                  FROM TDESCRIBE C \n");
        sb.append("                 WHERE C.GOODS_CODE = A.GOODS_CODE \n");
        sb.append("                   AND ROWNUM = 1 )  \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Slip Search
    * </PRE>
    * @return  Query
    */
    public String makeSqlSlipSearch(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT B.ORDER_NO, \n");
        sb.append("       B.ORDER_G_SEQ, \n");
        sb.append("       B.ORDER_D_SEQ, \n");
        sb.append("       B.ORDER_W_SEQ, \n");
        sb.append("       B.GOODS_CODE AS GOODS_CODE, \n");
        sb.append("       D.GOODS_NAME, \n");
        sb.append("       D.GOODSDT_INFO, \n");
        sb.append("       A.DELY_GB, \n");
        sb.append("       A.SLIP_NO, \n");
        sb.append("       C.RECEIVER, \n");
        sb.append("       TO_CHAR(A.OUT_CLOSE_DATE, 'YYYY/MM/DD') AS OUT_CLOSE_DATE, \n");
        sb.append("       A.SLIP_I_NO \n");
        sb.append("  FROM TSLIPM A, \n");
        sb.append("       TSLIPDT B, \n");
        sb.append("       TRECEIVER C, \n");
        sb.append("       TGOODSDT D \n");
        sb.append(" WHERE A.SLIP_I_NO = B.SLIP_I_NO \n");
        sb.append("   AND A.CUST_NO = C.CUST_NO \n");
        sb.append("   AND A.RECEIVER_SEQ = C.RECEIVER_SEQ \n");
        sb.append("   AND B.GOODS_CODE = D.GOODS_CODE \n");
        sb.append("   AND B.GOODSDT_CODE = D.GOODSDT_CODE \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 카드코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlCardCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT CARD_CODE, \n");
        sb.append("       CARD_NAME \n");
        sb.append("  FROM TCARDCODE \n");
        sb.append("   WHERE CARD_CODE LIKE '"+retrieve.getString("CARD_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY CARD_CODE  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 단순 상품코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.GOODS_CODE, \n");
        sb.append("       A.GOODS_NAME, \n");
        sb.append("       B.SALE_PRICE \n");
        sb.append("  FROM TGOODS       A, \n");
        sb.append("       TGOODSPRICE  B \n");
        sb.append(" WHERE A.GOODS_CODE    =  B.GOODS_CODE \n");
        sb.append("   AND A.GIFT_YN       = '0' \n");
        sb.append("   AND A.SET_GOODS_YN  = '0' \n");
        sb.append("   AND A.SALE_GB       < '20' \n");
        sb.append("   AND A.GOODS_CODE  LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append("   AND B.APPLY_DATE  IN \n");
        sb.append("      (SELECT MAX(APPLY_DATE) \n");
        sb.append("         FROM TGOODSPRICE \n");
        sb.append("        WHERE APPLY_DATE <=  SYSDATE \n");
        sb.append("          AND GOODS_CODE   =  A.GOODS_CODE) \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 창고코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlWhCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT WH_CODE, \n");
        sb.append("       WH_NAME \n");
        sb.append("  FROM TWAREHOUSE \n");
        sb.append(" WHERE WH_CODE LIKE '"+retrieve.getString("WH_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY WH_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상담대분류
    * </PRE>
    * @return  Query
    */
    public String makeSqlCounselLgroupCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_MGROUP, \n");
        sb.append("       A.CODE_NAME, \n");
        sb.append("       A.REMARK  \n");
        sb.append("  FROM TCODE A  \n");
        sb.append(" WHERE A.CODE_LGROUP = 'C031' \n");
        sb.append("   AND A.USE_YN      = '1' \n");
        sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CODE_MGROUP \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상담중분류
    * </PRE>
    * @return  Query
    */
    public String makeSqlCounselMgroupCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_MGROUP, \n");
        sb.append("       A.CODE_NAME \n");
        sb.append("  FROM TCODE A \n");
        sb.append(" WHERE A.CODE_LGROUP = '"+retrieve.getString("CODE_LGROUP")+"'  \n ");
        sb.append("   AND A.USE_YN      = '1' \n");
        sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CODE_MGROUP \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담중분류
     * </PRE>
     * @return  Query
     */
     public String makeSqlCounselGroup(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();

         sb.append("SELECT A.CODE_MGROUP LGROUP, \n");
         sb.append("       A.CODE_NAME   LGROUP_NAME, \n");
         sb.append("       A.REMARK   REMARK, \n");
         sb.append("       B.CODE_MGROUP MGROUP, \n");
         sb.append("       B.CODE_NAME   MGROUP_NAME\n");
         sb.append("  FROM TCODE A, \n");
         sb.append("       TCODE B \n");
         sb.append(" WHERE A.CODE_LGROUP = 'C031' \n");
         sb.append("   AND A.USE_YN      = '1' \n");
         sb.append("   AND A.REMARK      = B.CODE_LGROUP \n");
         sb.append("   AND B.USE_YN      = '1' \n");
         sb.append("   AND A.REMARK LIKE '"+retrieve.getString("CODE_LGROUP")+"'||'%'  \n ");
         sb.append("   AND B.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append(" ORDER BY A.CODE_MGROUP, B.CODE_MGROUP \n");
         return sb.toString();
     }


    /**
    * <PRE>
    * Desc : OB_SEQ
    * </PRE>
    * @return  Query
    */
    public String makeSqlObSeq(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.OB_GB, \n");
        sb.append("       A.OB_SEQ, \n");
        sb.append("       A.OB_TITLE, \n");
        sb.append("       TO_CHAR(A.SELECT_DATE,'YYYY/MM/DD HH24:MI:SS') AS SELECT_DATE, \n");
        sb.append("       A.SELECT_ID, \n");
        sb.append("       B.USER_NAME AS SELECT_NAME, \n");
        sb.append("       A.SELECT_CNT, \n");
        sb.append("       A.OB_NOTE \n");
        sb.append("  FROM TOBCODE A, \n");
        sb.append("  	  TUSER B    \n");
        sb.append(" WHERE A.SELECT_ID = B.USER_ID \n ");
        sb.append("   AND A.OB_SEQ >= '"+retrieve.getString("OB_SEQ")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.OB_GB, A.OB_SEQ ASC  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 브랜드 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlBrandCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT BRAND_CODE, \n");
        sb.append("       BRAND_NAME  \n");
        sb.append("  FROM TBRAND  \n");
        sb.append(" WHERE BRAND_CODE LIKE '"+retrieve.getString("BRAND_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY BRAND_CODE ASC \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 브랜드 명
     * </PRE>
     * @return  Query
     */
     public String makeSqlBrandName(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT BRAND_CODE, \n");
         sb.append("       BRAND_NAME  \n");
         sb.append("  FROM TBRAND  \n");
         sb.append(" WHERE UPPER(BRAND_NAME) LIKE '%'||UPPER('"+retrieve.getString("BRAND_NAME")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append(" ORDER BY BRAND_NAME ASC \n");
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : 공지사항
    * </PRE>
    * @return  Query
    */
    public String makeSqlBoardCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT BOARD_CODE,  \n");
        sb.append("       BOARD_DESC   \n");
        sb.append("  FROM TBOARDINFO  \n");
        sb.append(" WHERE BOARD_CODE LIKE '"+retrieve.getString("BOARD_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY BOARD_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 카테고리코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlCategoryCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT CATEGORY_CODE,  \n");
        sb.append("       CATEGORY_NAME   \n");
        sb.append("  FROM TCATEGORY  \n");
        sb.append(" WHERE CATEGORY_CODE LIKE '"+retrieve.getString("CATEGORY_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY CATEGORY_CODE ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : PD코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlSNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.USER_ID,   \n");
        sb.append("       A.USER_NAME, \n");
        sb.append("       A.USER_GB    \n");
        sb.append("  FROM TUSER A  \n");
        sb.append(" WHERE A.USER_ID LIKE '%'||'"+retrieve.getString("USER_ID")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("   AND A.USER_GB = '05'  \n");
        sb.append(" ORDER BY A.USER_ID \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : SHOWHOST코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlShowHostId(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.USER_ID,   \n");
        sb.append("       A.USER_NAME, \n");
        sb.append("       A.USER_GB    \n");
        sb.append("  FROM TUSER A, TSHOWHOST B   \n");
        sb.append(" WHERE A.USER_ID = B.SHOWHOST_ID  \n");
        sb.append("   AND A.USER_ID LIKE '%'||'"+retrieve.getString("USER_ID")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("   AND A.USER_GB = '06'  \n");
        sb.append(" ORDER BY A.USER_ID \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 방송코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlProgCodeReal(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.PROG_CODE, \n");
        sb.append("       A.PROG_NAME \n");
        sb.append("  FROM TPROGRAM A \n");
        sb.append(" WHERE A.PROG_CODE LIKE '"+retrieve.getString("PROG_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.PROG_CODE DESC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : MD코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlMdCodeUser(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.MD_CODE, \n");
        sb.append("       A.MD_NAME \n");
        sb.append("  FROM TMD A \n");
        sb.append(" WHERE A.MD_CODE IN \n");
        sb.append("       ( SELECT DISTINCT B.MD_CODE \n");
        sb.append("           FROM TMDLINK B  \n");
        sb.append("          WHERE B.MD_CODE LIKE '"+retrieve.getString("MD_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.MD_CODE ASC \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 상품코드(영구중단이 아닌 상품만)
    * </PRE>
    * @return  Query
    */
    public String makeSqlLinkGoodsCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,   \n");
        sb.append("       A.ENTP_CODE,   \n");
        sb.append("       A.TAX_YN,   \n");
        sb.append("       A.DELY_TYPE,   \n");
        sb.append("       A.SALE_GB,  \n");
        sb.append("       A.MD_CODE   \n");
        sb.append("  FROM TGOODS A  \n");
        sb.append(" WHERE A.SALE_GB <> '19'  \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 제휴업체 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlWithCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.WITH_CODE, \n");
        sb.append("       A.WITH_NAME \n");
        sb.append("  FROM TWITH A \n");
        sb.append(" WHERE A.WITH_CODE LIKE '"+retrieve.getString("WITH_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.WITH_CODE ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 오늘의세일 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlSaleCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.SALE_CODE,  \n");
        sb.append("       A.SALE_NAME  \n");
        sb.append("  FROM TSPECIALSALEM A  \n");
        sb.append(" WHERE A.SALE_CODE LIKE '%'||'"+retrieve.getString("SALE_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.SALE_CODE DESC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 이벤트번호
    * </PRE>
    * @return  Query
    */
    public String makeSqlEventNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.EVENT_NO,  \n");
        sb.append("       A.EVENT_TITLE  \n");
        sb.append("  FROM TEVENTM A  \n");
        sb.append(" WHERE A.EVENT_NO LIKE '%'||'"+retrieve.getString("EVENT_NO")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.EVENT_NO DESC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : FAQ 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlFaqKinds(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT CODE_MGROUP, \n");
        sb.append("       REMARK1 AS CODE_NAME \n");
        sb.append("  FROM TCODE \n");
        sb.append(" WHERE CODE_LGROUP='B830'  \n");
        sb.append("   AND CODE_GROUP='01'  \n");
        sb.append("   AND CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY CODE_MGROUP ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : FAQ분류 코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlFaqCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT XA.CODE_MGROUP AS CODE_MGROUP,  \n");
        sb.append("       XA.REMARK1 AS CODE_NAME,  \n");
        sb.append("       XB.CODE_MGROUP AS S_CODE_MGROUP, \n");
        sb.append("       XB.CODE_NAME  AS S_CODE_NAME \n");
        sb.append("  FROM (SELECT CODE_LGROUP , CODE_MGROUP , REMARK1 , REMARK  \n");
        sb.append("          FROM TCODE   \n");
        sb.append("         WHERE CODE_LGROUP='B830'  \n");
        sb.append("           AND USE_YN='1'  \n");
        sb.append("           AND CODE_GROUP='01' ) XA , TCODE XB  \n");
        sb.append("  WHERE XA.CODE_LGROUP = 'B830'  \n");
        sb.append("    AND XB.CODE_LGROUP = XA.REMARK  \n");
        sb.append("    AND XB.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("  ORDER BY XB.CODE_LGROUP ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : BOARD 대분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlBoardLkinds(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_MGROUP, \n");
        sb.append("       A.CODE_NAME, \n");
        sb.append("       A.REMARK  \n");
        sb.append("  FROM TCODE A \n");
        sb.append(" WHERE A.CODE_LGROUP = 'F012' \n");
        sb.append("   AND A.USE_YN      = '1'  \n");
        sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CODE_MGROUP \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : BOARD 중분류코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlBoardMkinds(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.CODE_MGROUP LGROUP, 					\n");
        sb.append("       A.CODE_NAME   LGROUP_NAME, 			\n");
        sb.append("       A.REMARK   REMARK,              \n");
        sb.append("       B.CODE_MGROUP MGROUP,           \n");
        sb.append("       B.CODE_NAME   MGROUP_NAME       \n");
        sb.append("  FROM TCODE A,                        \n");
        sb.append("       TCODE B                         \n");
        sb.append(" WHERE A.CODE_LGROUP = 'F012'          \n");
        sb.append("   AND A.USE_YN      = '1'             \n");
        sb.append("   AND A.REMARK      = B.CODE_LGROUP   \n");
        sb.append("   AND B.USE_YN      = '1'             \n");
        sb.append("   AND B.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%'      \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CODE_MGROUP, B.CODE_MGROUP \n");



//        sb.append("SELECT A.CODE_MGROUP, \n");
//        sb.append("       A.CODE_NAME \n");
//        sb.append("  FROM TCODE A, \n");
//        sb.append("       TCODE B \n");
//        sb.append(" WHERE A.CODE_LGROUP = B.REMARK \n");
//        sb.append("   AND B.CODE_LGROUP = 'F012' \n");
//        sb.append("   AND A.USE_YN      = '1' \n");
//        sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
//        sb.append(retrieve.getString("MOD_QUERY"));
//        sb.append(" ORDER BY A.CODE_MGROUP \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 설문번호
    * </PRE>
    * @return  Query
    */
    public String makeSqlResearchNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.RESEARCH_NO,  \n");
        sb.append("       A.RESEARCH_TITLE  \n");
        sb.append("  FROM TRESEARCHM A  \n");
        sb.append(" WHERE A.RESEARCH_NO LIKE '"+retrieve.getString("RESEARCH_NO")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.RESEARCH_NO ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 입금은행
    * </PRE>
    * @return  Query
    */
    public String makeSqlReceiptsBank(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT BANK_CODE,  \n");
        sb.append("         BANK_SEQ,  \n");
        sb.append("         BANK_NAME,  \n");
        sb.append("         BANK_DEPOSIT_NO  \n");
        sb.append("    FROM TRECEIPTSBANK A   \n");
        sb.append("   WHERE USE_YN = '1'   \n");
        sb.append("     AND BANK_CODE LIKE '"+retrieve.getString("BANK_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("ORDER BY BANK_CODE, BANK_SEQ   \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 결제은행코드
    * </PRE>
    * @return  Query
    */
    public String makeSqlBank(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT BANK_CODE,    \n");
        sb.append("         BANK_NAME     \n");
        sb.append("    FROM TBANK         \n");
        sb.append("   WHERE USE_YN = '1'  \n");
        sb.append("     AND BANK_CODE LIKE '"+retrieve.getString("BANK_CODE")+"'||'%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("ORDER BY BANK_CODE     \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 사은품 상품의 재고수량까지  SELECT (상품코드)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeGift(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE, \n");
        sb.append("       A.GOODS_NAME, \n");
        sb.append("       A.ENTP_CODE, \n");
        sb.append("       A.TAX_YN, \n");
        sb.append("       A.DELY_TYPE, \n");
        sb.append("       A.SALE_GB, \n");
        sb.append("       A.MD_CODE, \n");
        sb.append("       B.MD_NAME, \n");
        sb.append("       A.STOCK_CHK_PLACE, \n");
        sb.append("       C.BUY_PRICE 		\n");
        sb.append("  FROM TGOODS A, TMD B ,TGOODSPRICE C \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n");
        sb.append("   AND A.SALE_GB = '00' \n");
        sb.append("   AND A.SIGN_GB = '80' \n");
        sb.append("   AND A.SQC_GB LIKE '1%' \n");
        sb.append("   AND (A.GIFT_YN = '1'  \n");
        sb.append("   OR A.SAMPLE_YN = '1')  \n");
        sb.append("   AND A.SET_GOODS_YN = '0' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }
    /**
    * <PRE>
    * Desc : 사은품 상품의 재고수량까지  SELECT (상품명)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameGift(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE, \n");
        sb.append("       A.GOODS_NAME, \n");
        sb.append("       A.ENTP_CODE, \n");
        sb.append("       A.TAX_YN, \n");
        sb.append("       A.DELY_TYPE, \n");
        sb.append("       A.SALE_GB, \n");
        sb.append("       A.MD_CODE, \n");
        sb.append("       B.MD_NAME, \n");
        sb.append("       A.STOCK_CHK_PLACE \n");
        sb.append("  FROM TGOODS A, TMD B \n");
        sb.append(" WHERE A.MD_CODE = B.MD_CODE \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n");
        sb.append("   AND A.SALE_GB = '00' \n");
        sb.append("   AND A.SIGN_GB = '80' \n");
        sb.append("   AND A.SQC_GB LIKE '1%' \n");
        sb.append("   AND (A.GIFT_YN = '1'  \n");
        sb.append("   OR A.SAMPLE_YN = '1')  \n");
        sb.append("   AND A.SET_GOODS_YN = '0' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }
    /**
    * <PRE>
    * Desc : 상품기술서 Copy Pop
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeDescribeCopy(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT B.DESCRIBE_CODE,  \n");
        sb.append("         B.DESCRIBE_TITLE,  \n");
        sb.append("         A.DESCRIBE_EXT,  \n");
        sb.append("         A.WEB_FLAG,  \n");
        sb.append("         B.SORT_SEQ,  \n");
        sb.append("         '1' COMP_CHECK,  \n");
        sb.append("         B.SORT_SEQ,  \n");
        sb.append("         A.INSERT_DATE,  \n");
        sb.append("         A.INSERT_ID,  \n");
        sb.append("         A.MODIFY_DATE,  \n");
        sb.append("         A.MODIFY_ID  \n");
        sb.append("    FROM TDESCRIBE A,  \n");
        sb.append("         TDESCRIBECODE B  \n");
        sb.append("   WHERE A.DESCRIBE_CODE = B.DESCRIBE_CODE  \n");
        sb.append("     AND A.GOODS_CODE    = '"+retrieve.getString("GOODS_CODE")+"' \n");
        sb.append("     AND B.USE_YN           = '1'		 \n");
        sb.append("ORDER BY A.DESCRIBE_CODE ASC  		 	 \n");
        return sb.toString();
    }
    /**
    * <PRE>
    * Desc : 고객카드 Popup
    * </PRE>
    * @return  Query
    */
    public String makeSqlCustCard(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT A.CARD_BANK_CODE,  \n");
        sb.append("          B.CARD_NAME,  \n");
        sb.append("          CWARE_ENC_DEC(A.CARD_NO, 'd') AS CARD_NO,  \n");
        sb.append("          A.VALID_DATE,  \n");
        sb.append("          A.DEPOSITOR,  \n");
        sb.append("          A.FAMILY_GB,  \n");
        sb.append("          A.SETTLE_SEQ,  \n");
        sb.append("          TO_CHAR(A.MODIFY_DATE, 'yyyy/mm/dd hh24:mi:ss') AS MODIFY_DATE,  \n");
        sb.append("          A.DEPO_RESI_NO,  \n");
        sb.append("          A.CVV,  \n");
        sb.append("          A.ISSUE_NUMBER,  \n");
        sb.append("          '1' AS CUST_CARD,  \n");
        sb.append("          B.USE_YN  \n");
        sb.append("     FROM TCUSTSETTLE  A,  \n");
        sb.append("          TCARDCODE    B  \n");
        sb.append("    WHERE A.CARD_BANK_CODE = B.CARD_CODE  \n");
        sb.append("      AND A.CUST_NO   = '"+retrieve.getString("CUST_NO")+"' \n");
        sb.append("      AND A.SETTLE_GB = '01'  \n");
        sb.append("      AND A.USE_YN = '1'  \n");
        sb.append("    UNION ALL  \n");
        sb.append("   SELECT B.CARD_CODE,  \n");
        sb.append("          B.CARD_NAME,  \n");
        sb.append("          '' AS CARD_NO,  \n");
        sb.append("          '' AS VALID_DATE,  \n");
        sb.append("          '' AS DEPOSITOR,  \n");
        sb.append("          '' AS FAMILY_GB,  \n");
        sb.append("          '' AS SETTLE_SEQ,  \n");
        sb.append("          '' AS MODIFY_DATE,  \n");
        sb.append("          '' AS DEPO_RESI_NO,  \n");
        sb.append("          '' AS CVV,  \n");
        sb.append("          '' AS ISSUE_NUMBER,  \n");
        sb.append("          '0' AS CUST_CARD,  \n");
        sb.append("          B.USE_YN  \n");
        sb.append("     FROM TCARDCODE B  \n");
        sb.append("    WHERE B.USE_YN = '1'  \n");
        sb.append(" ORDER BY CUST_CARD DESC, CARD_BANK_CODE \n");
        return sb.toString();
    }
    /**
    * <PRE>
    * Desc : 고객 입금은행 Popup
    * </PRE>
    * @return  Query
    */
    public String makeSqlCustReceiptsbank(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  A.BANK_CODE,  \n");
        sb.append("         B.BANK_SEQ,  \n");
        sb.append("         C.BANK_NAME,  \n");
        sb.append("         DECODE(A.VACCOUNT_YN,'0',CWARE_ENC_DEC(B.CARD_NO, 'd'), 'Virtual Account') AS BANK_DEPOSIT_NO,  \n");
        sb.append("         TO_CHAR(B.MODIFY_DATE,'YYYY/MM/DD') AS MODIFY_DATE,  \n");
        sb.append("         A.VACCOUNT_YN,  \n");
        sb.append("         '1' CHK_SW  \n");
        sb.append("   FROM  TRECEIPTSBANK  A,  \n");
        sb.append("         TCUSTSETTLE    B,  \n");
        sb.append("         TBANK          C  \n");
        sb.append("  WHERE  A.BANK_CODE  = B.CARD_BANK_CODE  \n");
        sb.append("    AND  A.BANK_SEQ   = B.BANK_SEQ  \n");
        sb.append("    AND  A.BANK_CODE  = C.BANK_CODE  \n");
        sb.append("    AND  B.SETTLE_GB  = '02'  \n");
        sb.append("    AND  B.USE_YN     = '1'  \n");
        sb.append("    AND  A.USE_YN     = '1'  \n");
        sb.append("    AND  B.CUST_NO    = '"+retrieve.getString("CUST_NO")+"'  \n");
        sb.append("    AND  A.TYPE       = DECODE('"+retrieve.getString("SHOP_CODE")+"', '', '00', '10')  \n");
        sb.append(" UNION ALL  \n");
        sb.append(" SELECT  A.BANK_CODE,  \n");
        sb.append("         A.BANK_SEQ,  \n");
        sb.append("         C.BANK_NAME,  \n");
        sb.append("         DECODE(A.VACCOUNT_YN,'0',A.BANK_DEPOSIT_NO, 'Virtual Account'),  \n");
        sb.append("         ''   MODIFY_DATE,  \n");
        sb.append("         A.VACCOUNT_YN,  \n");
        sb.append("         '0' CHK_SW  \n");
        sb.append("   FROM  TRECEIPTSBANK A,  \n");
        sb.append("         TBANK         C  \n");
        sb.append("  WHERE  A.BANK_CODE  = C.BANK_CODE  \n");
        sb.append("    AND  A.USE_YN     = '1'          \n");
        sb.append("    AND  A.TYPE       = DECODE('"+retrieve.getString("SHOP_CODE")+"', '', '00', '10')  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 고객환불은행 Popup
    * </PRE>
    * @return  Query
    */
    public String makeSqlCustBank(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.CARD_BANK_CODE AS  CARD_BANK_CODE,\n");
        sb.append("        B.BANK_NAME,                        \n");
        sb.append("        CWARE_ENC_DEC(A.CARD_NO, 'd') AS CARD_NO, \n");
        sb.append("        A.DEPOSITOR,                        \n");
        sb.append("        A.FAMILY_GB,                        \n");
        sb.append("        A.SETTLE_SEQ,                       \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD') AS MODIFY_DATE, \n");
        sb.append("        '1' AS CHK_SW                       \n");
        sb.append("   FROM TCUSTSETTLE A,                      \n");
        sb.append("        TBANK       B                       \n");
        sb.append("  WHERE A.CARD_BANK_CODE = B.BANK_CODE      \n");
        sb.append("    AND A.SETTLE_GB = '52'                  \n");
        sb.append("    AND A.USE_YN = '1'                      \n");
        sb.append("    AND A.CUST_NO    = '"+retrieve.getString("CUST_NO")+"' \n");
        sb.append("                                            \n");
        sb.append(" UNION                                       \n");
        sb.append("                                            \n");
        sb.append(" SELECT A.BANK_CODE AS CARD_BANK_CODE,      \n");
        sb.append("        A.BANK_NAME,                        \n");
        sb.append("        '',                                 \n");
        sb.append("        '',                                 \n");
        sb.append("        '',                                 \n");
        sb.append("        '',                                 \n");
        sb.append("        '' MODIFY_DATE,                     \n");
        sb.append("        '2' AS CHK_SW                       \n");
        sb.append("   FROM TBANK	A                          \n");
        sb.append("  WHERE A.USE_YN = '1'                      \n");
        //sb.append("    AND A.BANK_CODE < '900'                 \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 발주용 상품,단품조회 Popup
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeBalju(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT /*+RULE*/       \n");
        sb.append("        A.GOODS_CODE,   \n");
        sb.append("        B.GOODSDT_CODE, \n");
        sb.append("        A.GOODS_NAME,   \n");
        sb.append("        B.GOODSDT_INFO, \n");
        sb.append("        A.SQC_GB,       \n");
        sb.append("        C.BUY_COST      \n");
        sb.append("   FROM TGOODS      A,  \n");
        sb.append("        TGOODSDT    B,  \n");
        sb.append("        TGOODSPRICE C   \n");
        sb.append("  WHERE A.GOODS_CODE      = B.GOODS_CODE \n");
        sb.append("    AND A.GOODS_CODE      = C.GOODS_CODE \n");
        sb.append("    AND B.GOODSDT_CODE    > '000' \n");
        sb.append("    AND A.BUY_MED        IN ( '11', '13','21' ) \n");
        sb.append("    AND A.SIGN_GB         = '80' \n");
        sb.append("    AND B.SALE_GB        <> '19' \n");
        sb.append("    AND A.SET_YN          = '0' \n");
        sb.append("    AND A.GOODS_CODE      > ' ' \n");
        sb.append("    AND A.GOODS_CODE   LIKE '" + retrieve.getString("GOODS_CODE")   +"' || '%'\n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("    AND A.MD_CODE      = '"    + retrieve.getString("MD_CODE")      + "' \n");
        sb.append("    AND A.ENTP_CODE    = '"    + retrieve.getString("ENTP_CODE")    + "' \n");
        sb.append("    AND A.WH_CODE      = '"    + retrieve.getString("WH_CODE")      + "' \n");
        sb.append("    AND A.BUY_MED      = '"    + retrieve.getString("BUY_MED")      + "' \n");
        sb.append("    AND A.SQC_GB > '15'  \n");
        sb.append("    AND C.APPLY_DATE   = \n");
        sb.append("                 ( SELECT MAX(Z.APPLY_DATE) \n");
        sb.append("                     FROM TGOODSPRICE Z \n");
        sb.append("                    WHERE Z.GOODS_CODE  = A.GOODS_CODE \n");
        sb.append("                      AND Z.APPLY_DATE <= SYSDATE ) \n");

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품명(발주용 상품,단품조회 Popup)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameBalju(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT /*+RULE*/       \n");
        sb.append("        A.GOODS_CODE,   \n");
        sb.append("        B.GOODSDT_CODE, \n");
        sb.append("        A.GOODS_NAME,   \n");
        sb.append("        B.GOODSDT_INFO, \n");
        sb.append("        A.SQC_GB,       \n");
        sb.append("        C.BUY_COST      \n");
        sb.append("   FROM TGOODS      A,  \n");
        sb.append("        TGOODSDT    B,  \n");
        sb.append("        TGOODSPRICE C   \n");
        sb.append("  WHERE A.GOODS_CODE      = B.GOODS_CODE \n");
        sb.append("    AND A.GOODS_CODE      = C.GOODS_CODE \n");
        sb.append("    AND B.GOODSDT_CODE    > '000' \n");
        sb.append("    AND A.BUY_MED        IN ( '11', '13','21' ) \n");
        sb.append("    AND A.SIGN_GB         = '80' \n");
        sb.append("    AND B.SALE_GB        <> '19' \n");
        sb.append("    AND A.SET_YN          = '0' \n");
        sb.append("    AND A.GOODS_CODE      > ' ' \n");
        sb.append("    AND UPPER(A.GOODS_NAME)   LIKE '%'||UPPER('" + retrieve.getString("GOODS_NAME")   +"') || '%'\n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("    AND A.MD_CODE      = '"    + retrieve.getString("MD_CODE")      + "' \n");
        sb.append("    AND A.ENTP_CODE    = '"    + retrieve.getString("ENTP_CODE")    + "' \n");
        sb.append("    AND A.WH_CODE      = '"    + retrieve.getString("WH_CODE")      + "' \n");
//        sb.append("    AND A.ENTP_MAN_SEQ = '"    + retrieve.getString("ENTP_MAN_SEQ") + "' \n");
        sb.append("    AND A.BUY_MED      = '"    + retrieve.getString("BUY_MED")      + "' \n");
        sb.append("    AND C.APPLY_DATE   = \n");
        sb.append("                 ( SELECT MAX(Z.APPLY_DATE) \n");
        sb.append("                     FROM TGOODSPRICE Z \n");
        sb.append("                    WHERE Z.GOODS_CODE  = A.GOODS_CODE \n");
        sb.append("                      AND Z.APPLY_DATE <= SYSDATE ) \n");

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 업체반출용 상품코드(상품,단품조회 Popup)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeEntpTakeout(RetrieveModel retrieve) throws StoreException{
    	StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       C.AQTY,  \n");
        sb.append("       C.BQTY,  \n");
        sb.append("       C.EOUT_QUEST_AQTY,  \n");
        sb.append("       C.EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE,  \n");
        sb.append("     	 ( SELECT D.ENTP_MAN_SEQ  \n");
        sb.append("      		FROM TENTPUSER D  \n");
        sb.append("      		WHERE D.ENTP_CODE = A.ENTP_CODE  \n");
        sb.append("       		  AND D.DEFAULT_YN = '1'  \n");
        sb.append("       		  AND D.USE_YN = '1'  \n");
        sb.append("       		  AND ROWNUM = 1)  AS ENTP_MAN_SEQ  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B,  \n");
        sb.append("       TSTOCK C  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
        sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
        sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")	+"'||'%' \n ");
        sb.append("   AND A.BUY_MED 	= '"+retrieve.getString("BUY_MED")		+ "' \n");
        sb.append("   AND A.MD_CODE 	= '"+retrieve.getString("MD_CODE")		+ "' \n");
        sb.append("   AND A.ENTP_CODE	= '"+retrieve.getString("ENTP_CODE")	+ "' \n");
        sb.append("   AND C.WH_CODE 	= '"+retrieve.getString("WH_CODE")		+ "' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();

        //sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        //sb.append("       AND C.WH_CODE = '001'  \n");
        //sb.append("       AND A.BUY_MED = '11'   \n");
        //sb.append(retrieve.getString("MOD_QUERY"));
        //return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 입고용 상품코드(상품,단품조회 Popup)
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsCodeWarehousingInput(RetrieveModel retrieve) throws StoreException{

     	StringBuffer sb = new StringBuffer();
     	sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */  A.GOODS_CODE								\n ");
        sb.append("     , A.GOODS_NAME  															\n ");
        sb.append("     , B.GOODSDT_CODE  															\n ");
        sb.append("     , B.GOODSDT_INFO  															\n ");
        sb.append("     , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE, '6') AS SALE_PRICE				\n ");
        sb.append("	    , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE, '1') AS SLIP_IN_PRICE   		\n ");
        sb.append("     , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE, '2') AS RATE 					\n ");
        sb.append("     , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'10') AS TAX_RATE	   			\n ");
        sb.append("  FROM TGOODS   A																\n ");
        sb.append("     , TGOODSDT B  																\n ");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE												\n ");
        sb.append("   AND A.WH_CODE =  '" + retrieve.getString("WH_CODE")     + "'				 	\n ");
        sb.append("   AND A.ENTP_CODE =  '" + retrieve.getString("ENTP_CODE") + "'				 	\n ");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '" + retrieve.getString("GOODS_NAME") + "'||'%' 	\n ");
        sb.append("   AND UPPER(A.GOODS_CODE) LIKE '" + retrieve.getString("GOODS_CODE") + "'||'%' 	\n ");
        sb.append("	  AND A.SIGN_GB      = '80'											 			\n ");
        sb.append("	  AND A.BUY_MED      =  '" + retrieve.getString("BUY_MED") + "'					\n ");
        sb.append("	  AND A.SET_YN       = '0'														\n ");
        sb.append(retrieve.getString("MOD_QUERY"));

        return sb.toString();
    }

    /** 2006.10.25 add
    * <PRE>
    * Desc : 업체반출용 상품명 (상품,단품조회 Popup)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameEntpTakeout(RetrieveModel retrieve) throws StoreException{
    	StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       B.GOODSDT_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       B.GOODSDT_INFO,  \n");
        sb.append("       C.AQTY,  \n");
        sb.append("       C.BQTY,  \n");
        sb.append("       C.EOUT_QUEST_AQTY,  \n");
        sb.append("       C.EOUT_QUEST_BQTY,  \n");
        sb.append("       A.STOCK_CHK_PLACE,  \n");
        sb.append("     	 ( SELECT D.ENTP_MAN_SEQ  \n");
        sb.append("      		FROM TENTPUSER D  \n");
        sb.append("      		WHERE D.ENTP_CODE = A.ENTP_CODE  \n");
        sb.append("       		  AND D.DEFAULT_YN = '1'  \n");
        sb.append("       		  AND D.USE_YN = '1'  \n");
        sb.append("       		  AND ROWNUM = 1)  AS ENTP_MAN_SEQ  \n");
        sb.append("  FROM TGOODS A,  \n");
        sb.append("       TGOODSDT B,  \n");
        sb.append("       TSTOCK C  \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
        sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
        sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
        sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")	+"')||'%' \n ");
        sb.append("   AND A.BUY_MED 	= '"+retrieve.getString("BUY_MED")		+ "' \n");
        sb.append("   AND A.MD_CODE 	= '"+retrieve.getString("MD_CODE")		+ "' \n");
        sb.append("   AND A.ENTP_CODE	= '"+retrieve.getString("ENTP_CODE")	+ "' \n");
        sb.append("   AND C.WH_CODE 	= '"+retrieve.getString("WH_CODE")		+ "' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();

        //sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
        //sb.append("       AND C.WH_CODE = '001'  \n");
        //sb.append("       AND A.BUY_MED = '11'   \n");
        //sb.append(retrieve.getString("MOD_QUERY"));
        //return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 기타입출고용 상품,단품조회 Popup
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodeEtcInout(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT E.GOODS_CODE   AS GOODS_CODE,  \n");
        sb.append("         E.GOODS_NAME   AS GOODS_NAME,  \n");
        sb.append("         A.GOODSDT_CODE AS GOODSDT_CODE,  \n");
        sb.append("         A.GOODSDT_INFO AS GOODSDT_INFO,  \n");
        sb.append("         NVL(B.AQTY, 0) AS AQTY,  \n");
        sb.append("         NVL(B.BQTY, 0) AS BQTY,  \n");
        sb.append("         NVL(C.RACK_QTY, 0) AS RACK_AQTY,  \n");
        sb.append("         NVL(D.RACK_QTY, 0) AS RACK_BQTY  \n");
        sb.append("    FROM TGOODSDT A,  \n");
        sb.append("         TSTOCK   B,  \n");
        sb.append("         TRACK    C,  \n");
        sb.append("         TRACK    D,  \n");
        sb.append("         TGOODS   E  \n");
        sb.append("   WHERE A.GOODS_CODE   = E.GOODS_CODE  \n");
        sb.append("     AND A.GOODS_CODE   = B.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = B.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE > '000'  \n");
        sb.append("     AND B.WH_CODE(+)   = '" + retrieve.getString("wh_code")   + "' \n");
        sb.append("     AND A.GOODS_CODE   = C.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = C.GOODSDT_CODE(+)  \n");
        sb.append("     AND C.RACK_CODE(+) = '" + retrieve.getString("wh_code")   + "'||'" + retrieve.getString("aRack")   + "' \n");
        sb.append("     AND A.GOODS_CODE   = D.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND D.RACK_CODE(+) = '" + retrieve.getString("wh_code")   + "'||'" + retrieve.getString("bRack")   + "' \n");
        sb.append("     AND E.GOODS_CODE   LIKE '" + retrieve.getString("goods_code")   +"' || '%'\n ");
        sb.append(retrieve.getString("MOD_QUERY"));


        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품명 (기타입출고용 상품,단품조회 Popup)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsNameEtcInout(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT E.GOODS_CODE   AS GOODS_CODE,  \n");
        sb.append("         E.GOODS_NAME   AS GOODS_NAME,  \n");
        sb.append("         A.GOODSDT_CODE AS GOODSDT_CODE,  \n");
        sb.append("         A.GOODSDT_INFO AS GOODSDT_INFO,  \n");
        sb.append("         NVL(B.AQTY, 0) AS AQTY,  \n");
        sb.append("         NVL(B.BQTY, 0) AS BQTY,  \n");
        sb.append("         NVL(C.RACK_QTY, 0) AS RACK_AQTY,  \n");
        sb.append("         NVL(D.RACK_QTY, 0) AS RACK_BQTY  \n");
        sb.append("    FROM TGOODSDT A,  \n");
        sb.append("         TSTOCK   B,  \n");
        sb.append("         TRACK    C,  \n");
        sb.append("         TRACK    D,  \n");
        sb.append("         TGOODS   E  \n");
        sb.append("   WHERE A.GOODS_CODE   = E.GOODS_CODE  \n");
        sb.append("     AND A.GOODS_CODE   = B.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = B.GOODSDT_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE > '000'  \n");
        sb.append("     AND B.WH_CODE(+)   = '" + retrieve.getString("wh_code")   + "' \n");
        sb.append("     AND A.GOODS_CODE   = C.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = C.GOODSDT_CODE(+)  \n");
        sb.append("     AND C.RACK_CODE(+) = '" + retrieve.getString("wh_code")   + "'||'" + retrieve.getString("aRack")   + "' \n");
        sb.append("     AND A.GOODS_CODE   = D.GOODS_CODE(+)  \n");
        sb.append("     AND A.GOODSDT_CODE = D.GOODSDT_CODE(+)  \n");
        sb.append("     AND D.RACK_CODE(+) = '" + retrieve.getString("wh_code")   + "'||'" + retrieve.getString("bRack")   + "' \n");
        sb.append("     AND UPPER(E.GOODS_NAME)   LIKE '%'||UPPER('" + retrieve.getString("goods_name")   +"') || '%'\n ");
        sb.append(retrieve.getString("MOD_QUERY"));


        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 업체별 정산번호 조회
    * </PRE>
    * @return  Query
    */
    public String makeSqlEntpPurchaseNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.ENTP_CODE,    \n");
        sb.append("        B.ENTP_NAME,    \n");
        sb.append("        TO_CHAR(A.PURCHASE_FR, 'YYYY/MM')  AS PURCHASE_MON, \n");
        sb.append("        A.PURCHASE_NO   \n");
        sb.append("   FROM TPURCHASE_M  A, \n");
        sb.append("        TENTERPRISE  B  \n");
        sb.append("  WHERE A.ENTP_CODE = B.ENTP_CODE  \n");
        sb.append("    AND A.ENTP_CODE like '"+retrieve.getString("ENTP_CODE")+"' || '%'\n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.ENTP_CODE ASC \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 기술
    * </PRE>
    * @return  Query
    */
    public String makeSqlDescribeCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.DESCRIBE_CODE, \n");
        sb.append("       A.DESCRIBE_TITLE \n");
        sb.append("  FROM TDESCRIBECODE A \n");
        sb.append(" WHERE A.DESCRIBE_CODE LIKE '"+retrieve.getString("DESCRIBE_CODE")+"'||'%' \n ");
        sb.append(" ORDER BY A.DESCRIBE_CODE ASC  \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 주문매체
     * </PRE>
     * @return  Query
     */
     public String makeSqlOrderMedia(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.CODE_MGROUP, \n");
         sb.append("       A.CODE_NAME \n");
         sb.append("  FROM TCODE A \n");
         sb.append(" WHERE A.CODE_LGROUP = 'J001' \n ");
         sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("ORDER_MEDIA")+"'||'%' \n ");
         sb.append("   AND A.USE_YN  = '1'  \n");
         sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : SEGMENT코드
      * </PRE>
      * @return  Query
      */
      public String makeSqlSegCode(RetrieveModel retrieve) throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append("SELECT A.SEGMENT_CODE, \n");
          sb.append("       A.SEGMENT_NAME, \n");
          sb.append("       A.TARGET_QTY, \n");
          sb.append("       A.MEMO \n");
          sb.append("  FROM TSEGMENT A \n");
          sb.append(" WHERE A.SEGMENT_CODE LIKE '"+retrieve.getString("SEG_CODE")+"'||'%' \n ");
          sb.append(retrieve.getString("MOD_QUERY"));
          sb.append(" ORDER BY A.SEGMENT_CODE DESC  \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Common Tcode
       * </PRE>
       * @return  Query
       */
       public String makeSqlCodeLgroup(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append("SELECT A.CODE_MGROUP, \n");
           sb.append("       A.CODE_NAME, \n");
           sb.append("       A.REMARK \n");
           sb.append("  FROM TCODE A \n");
           sb.append(" WHERE A.CODE_LGROUP = '"+retrieve.getString("CODE_LGROUP")+"' \n ");
           sb.append("   AND A.CODE_MGROUP like '"+retrieve.getString("CODE_MGROUP")+"'||'%' \n ");
           sb.append("   AND A.USE_YN  = '1'  \n");
           sb.append(retrieve.getString("MOD_QUERY"));
           sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");

           return sb.toString();
       }

       /**
        * <PRE>
        * Desc : 판매구분이 영구중단이 아닌경우만 대표상품코드 SELECT
        * </PRE>
        * @return  Query
        */
        public String makeSqlMasterCode(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
            sb.append("       A.GOODS_NAME,    \n");
            sb.append("       A.MASTER_CODE,   \n");
            sb.append("       A.ENTP_CODE,   \n");
            sb.append("       A.TAX_YN,  \n");
            sb.append("       A.DELY_TYPE,   \n");
            sb.append("       A.SALE_GB,  \n");
            sb.append("       A.MD_CODE,   \n");
            sb.append("       A.STOCK_CHK_PLACE  \n");
            sb.append("  FROM TGOODS A   \n");
            sb.append(" WHERE A.SALE_GB   != '19'  \n");
            sb.append("   AND A.MASTER_CODE LIKE '%' || '"+retrieve.getString("GOODS_CODE")+"' || '%' \n ");
            sb.append("   AND A.GOODS_CODE = A.MASTER_CODE   \n");
            sb.append(retrieve.getString("MOD_QUERY"));
            return sb.toString();
        }


        /** 2006.10.25 add
       * <PRE>
       * Desc : 상품명 (판매구분이 영구중단이 아닌경우만 대표상품코드 SELECT)
       * </PRE>
       * @return  Query
       */
       public String makeSqlMasterName(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append(" SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,   \n");
           sb.append("        A.GOODS_NAME,  \n");
           sb.append(" 		  A.MASTER_CODE, \n");
           sb.append("        A.ENTP_CODE,  \n");
           sb.append("        A.TAX_YN,   \n");
           sb.append("        A.DELY_TYPE,    \n");
           sb.append("        A.SALE_GB,  \n");
           sb.append("        A.MD_CODE,    \n");
           sb.append("        A.STOCK_CHK_PLACE  \n");
           sb.append("  FROM TGOODS A  \n");
           sb.append(" WHERE A.SALE_GB <> '19'  \n");
           sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
           sb.append(" 	 AND A.GOODS_CODE = A.MASTER_CODE   \n");
           sb.append(retrieve.getString("MOD_QUERY"));
           return sb.toString();
       }

       /** 2007.01.19 add
        * <PRE>
        * Desc : SAMPLE QUERY
        * </PRE>
        * @return  Query
        */
        public String makeSqlSampleNo(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT A.SAMPLE_NO,  \n");
            sb.append("       A.SAMPLE_NAME \n");
            sb.append("  FROM TSAMPLEM A    \n");
            sb.append(" WHERE A.SAMPLE_NO LIKE '" + retrieve.getString("SAMPLE_NO") + "'||'%' \n");
            sb.append(retrieve.getString("MOD_QUERY"));

            return sb.toString();
        }

        /** 2007.01.24 add
         * <PRE>
         * Desc : MENU PROGRAM QUERY
         * </PRE>
         * @return  Query
         */
         public String makeSqlMenuprogram(RetrieveModel retrieve) throws StoreException{
             StringBuffer sb = new StringBuffer();
             sb.append(" SELECT B.SMENU_ID, \n"                                           );
             sb.append(       " B.SMENU_NAME, \n"                                         );
             sb.append(       " A.PROGRAM_ID, \n"                                         );
             sb.append(       " A.PROGRAM_NAME, \n"                                       );
             sb.append(       " B.LMENU_NAME, \n"                                         );
             sb.append(       " B.MMENU_NAME  \n"                                         );
             sb.append(  " FROM TSECPROGRAM A, TSECMENU B \n"                             );
             sb.append( " WHERE A.LMENU_ID = '" + retrieve.getString("LMENU_ID") + "' \n" );
             sb.append(   " AND A.MMENU_ID = '" + retrieve.getString("MMENU_ID") + "' \n" );
             sb.append(   " AND A.LMENU_ID = B.LMENU_ID \n"                               );
             sb.append(   " AND A.MMENU_ID = B.MMENU_ID \n"                               );
             sb.append(   " AND A.SMENU_ID = B.SMENU_ID \n"                               );
             sb.append(retrieve.getString("MOD_QUERY"));

             return sb.toString();
         }

         /** 2007.01.24 add
          * <PRE>
          * Desc : MENU QUERY
          * </PRE>
          * @return  Query
          */
          public String makeSqlMmenu(RetrieveModel retrieve) throws StoreException{
              StringBuffer sb = new StringBuffer();
              sb.append(" SELECT A.LMENU_ID, \n"                                                  );
              sb.append(       " A.LMENU_NAME, \n"                                                );
              sb.append(       " A.MMENU_ID, \n"                                                  );
              sb.append(       " A.MMENU_NAME \n"                                                 );
              sb.append(  " FROM TSECMENU A \n"                                                   );
              sb.append( " WHERE A.MMENU_ID LIKE '" + retrieve.getString("MMENU_ID") + "'||'%' \n");
              sb.append(retrieve.getString("MOD_QUERY"));
              sb.append(" GROUP BY A.LMENU_ID, A.LMENU_NAME, A.MMENU_ID, A.MMENU_NAME \n"         );
              sb.append(" ORDER BY A.LMENU_ID, A.LMENU_NAME, A.MMENU_ID, A.MMENU_NAME \n"         );

              return sb.toString();
          }

          /** 2007.01.25 add
           * <PRE>
           * Desc : 발송순번 QUERY
           * </PRE>
           * @return  Query
           */
           public String makeSqlSendSeq(RetrieveModel retrieve) throws StoreException{
               StringBuffer sb = new StringBuffer();
               sb.append(" SELECT A.SEND_SEQ,       \n");
               sb.append("        A.DM_YEAR,        \n");
               sb.append("        A.DM_CODE        \n");
               sb.append("   FROM TDMSENDM A       \n");
               sb.append(retrieve.getString("MOD_QUERY"));
               sb.append(retrieve.getString("MOD_QUERY1"));

               return sb.toString();
           }
           /** 2007.02.07 add
           * <PRE>
           * Desc : 피부진단번호
           * </PRE>
           * @return  Query
           */
           public String makeSqlSkinTest(RetrieveModel retrieve) throws StoreException{
               StringBuffer sb = new StringBuffer();
               sb.append("SELECT A.SKINTEST_NO, \n");
               sb.append("       A.SKINTEST_NAME \n");
               sb.append("  FROM TSKINTESTM A \n");
               sb.append(" WHERE A.SKINTEST_NO LIKE '"+retrieve.getString("SKINTEST_NO")+"'||'%' \n ");
               sb.append(retrieve.getString("MOD_QUERY"));
               sb.append(" ORDER BY A.SKINTEST_NO DESC  \n");

               return sb.toString();
           }

           /** 2007.02.09 add
            * <PRE>
            * Desc : 설문번호
            * </PRE>
            * @return  Query
            */
            public String makeSqlResearch(RetrieveModel retrieve) throws StoreException{
                StringBuffer sb = new StringBuffer();
                sb.append("SELECT A.RESEARCH_NO, \n");
                sb.append("       A.RESEARCH_TITLE \n");
                sb.append("  FROM TRESEARCHM A \n");
                sb.append(" WHERE A.RESEARCH_NO LIKE '"+retrieve.getString("RESEARCH_NO")+"'||'%' \n ");
                sb.append(retrieve.getString("MOD_QUERY"));
                sb.append(" ORDER BY A.RESEARCH_NO ASC  \n");

                return sb.toString();
            }

            /** 2007.02.12 add
             * <PRE>
             * Desc : 투표번호
             * </PRE>
             * @return  Query
             */
             public String makeSqlGoodsPoll(RetrieveModel retrieve) throws StoreException{
                 StringBuffer sb = new StringBuffer();
                 sb.append("SELECT A.POLL_NO, \n");
                 sb.append("       A.POLL_TITLE \n");
                 sb.append("  FROM TGOODSPOLLM A \n");
                 sb.append(" WHERE A.POLL_NO LIKE '"+retrieve.getString("POLL_NO")+"'||'%' \n ");
                 sb.append(retrieve.getString("MOD_QUERY"));
                 sb.append(" ORDER BY A.POLL_NO ASC  \n");

                 return sb.toString();
             }

			 /** 2007.02.06 add
			 * <PRE>
			 * Desc : 테마별 상품번호 QUERY
			 * </PRE>
			 * @return  Query
			 */
			 public String makeSqlBeautyBoardCode(RetrieveModel retrieve) throws StoreException{
			     StringBuffer sb = new StringBuffer();
			     sb.append(" SELECT  A.BEAUTY_NO,		 \n"										    );
			     sb.append("         A.BEAUTY_TITLE,      \n"										    );
			     sb.append("         A.BEAUTY_GUBUN       \n"										    );
			     sb.append("    FROM TBEAUTYBOARD A       \n"										    );
			     sb.append("   WHERE A.BEAUTY_NO LIKE '" + retrieve.getString("BEAUTY_NO") + "'||'%' \n");
			     sb.append(" ORDER BY A.BEAUTY_NO ASC \n"												);

			     return sb.toString();
			 }


			  /** 2007.02.08 add
			   * <PRE>
			   * Desc : 매거진번호 / 제목 / Month  QUERY
			   * </PRE>
			   * @return  Query
			   */
			   public String makeSqlMagazineNo(RetrieveModel retrieve) throws StoreException{
			       StringBuffer sb = new StringBuffer();
			       sb.append("SELECT   A.MAGAZINE_NO, \n"										    	       );
			       sb.append("		  A.MAGAZINE_TITLE,\n"											           );
			       sb.append("		  TO_CHAR(A.MAGAZINE_MONTH,'YYYY/MM') AS MAGAZINE_MONTH \n"			      );
			       sb.append("FROM     TMAGAZINEM A \n"										    		   );
			       sb.append("WHERE    A.MAGAZINE_NO LIKE '" + retrieve.getString("MAGAZINE_NO") + "'||'%' \n"  );
			       sb.append(" ORDER BY A.MAGAZINE_NO ASC \n"												 	 );

			       return sb.toString();
			   }

			    /**
			    * <PRE>
			    * Desc : 창고코드
			    * </PRE>
			    * @return  Query
			    */
			    public String makeSqlShop(RetrieveModel retrieve) throws StoreException{
			        StringBuffer sb = new StringBuffer();
			        sb.append("SELECT SHOP_CODE, \n");
			        sb.append("       SHOP_NAME \n");
			        sb.append("  FROM TSHOP \n");
			        sb.append(" WHERE SHOP_CODE LIKE '"+retrieve.getString("SHOP_CODE")+"'||'%' \n ");
			        sb.append(retrieve.getString("MOD_QUERY"));
			        sb.append(" ORDER BY SHOP_CODE ASC \n");
			        return sb.toString();
			    }

			    /**
			    * <PRE>
			    * Desc : 창고코드
			    * </PRE>
			    * @return  Query
			    */
			    public String makeSqlProgram(RetrieveModel retrieve) throws StoreException{
			        StringBuffer sb = new StringBuffer();
			        sb.append("  SELECT A.LMENU_ID, \n");
			        sb.append("         A.MMENU_ID, \n");
			        sb.append("         A.PROGRAM_ID, \n");
			        sb.append("         A.PROGRAM_NAME  \n");
			        sb.append("    FROM TSECPROGRAM A \n");
			        sb.append("   WHERE A.PROGRAM_ID LIKE '"+retrieve.getString("PROGRAM_ID")+"' || '%' \n");
			        sb.append(retrieve.getString("MOD_QUERY"));
			        sb.append("ORDER BY 1,2 ASC \n");
			        return sb.toString();
			    }

			    /**
			     * <PRE>
			     * Desc : 이벤트 중분류 이름
			     * </PRE>
			     * @return  Query
			     */
			     public String makeSqlEventM(RetrieveModel retrieve) throws StoreException{
			         StringBuffer sb = new StringBuffer();
			         sb.append("SELECT A.REMARK, \n");
			         sb.append("	   A.CODE_MGROUP, \n");
			         sb.append("       A.CODE_NAME \n");
			         sb.append("  FROM TCODE A \n");
			         sb.append(" WHERE A.CODE_LGROUP = 'B836' \n ");
			         sb.append(retrieve.getString("MOD_QUERY"));
				     //sb.append("   AND A.REMARK  LIKE '"+retrieve.getString("lgroup")+"' || '%' \n");
			         //sb.append( "  AND A.CODE_MGROUP LIKE '" + retrieve.getString("CODE_MGROUP") + "'||'%' \n");
		             sb.append("   AND A.USE_YN  = '1'  \n");
			         sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");
			         return sb.toString();
			     }

    /**
     * 편성프로그램선택
     * @param retrieve
     * @return
     * @throws StoreException
     */
    public String makeSqlFrame(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT TO_CHAR(A.BD_DATE,'YYYY/MM/DD') AS BD_DATE, \n");
        sb.append("          A.PROG_CODE, \n");
        sb.append("          B.PROG_NAME, \n");
        sb.append("          TO_CHAR(A.BD_BTIME,'YYYY/MM/DD HH24:MI:SS') AS BD_BTIME_ORG, \n");
        sb.append("          TO_CHAR(A.BD_ETIME,'YYYY/MM/DD HH24:MI:SS') AS BD_ETIME_ORG, \n");
        sb.append("          TO_CHAR(A.BD_BTIME,'HH24:MI') AS BD_BTIME, \n");
        sb.append("          TO_CHAR(A.BD_ETIME,'HH24:MI') AS BD_ETIME, \n");
        sb.append("          A.SNO, \n");
        sb.append("          A.SNO1, \n");
        sb.append("          C.USER_NAME, \n");
        sb.append("          D.USER_NAME AS USER_NAME1, \n");
        sb.append("          FUN_SHOWHOST_NAME(A.BD_DATE, A.BD_BTIME, A.PROG_CODE)  SHOWHOST, \n");
        sb.append("          A.PLAN_AMT, \n");
        sb.append("          E.TAPE_CODE, \n");
        sb.append("          E.GOODS_CODE, \n");
        sb.append("          F.GOODS_NAME \n");
        sb.append("     FROM TFRAMESCHE A, \n");
        sb.append("          TPROGRAM B, \n");
        sb.append("          TUSER C, \n");
        sb.append("          TUSER D, \n");
        sb.append("          TDTBROAD E, \n");
        sb.append("          TGOODS F \n");
        sb.append("    WHERE A.PROG_CODE = B.PROG_CODE \n");
        sb.append("      AND A.PROG_CODE = E.PROG_CODE(+) \n");
        sb.append("      AND A.BD_DATE = E.BD_DATE(+) \n");
        sb.append("      AND A.BD_BTIME = E.BD_BTIME(+) \n");
        sb.append("      AND NVL(E.SPECIAL_YN,'1') = '1' \n");
        sb.append("      AND E.GOODS_CODE = F.GOODS_CODE(+) \n");
        sb.append("      AND A.SNO = C.USER_ID(+) \n");
        sb.append("      AND A.SNO1 = D.USER_ID(+) \n");
        sb.append("      AND A.BD_DATE = TO_DATE('"+retrieve.getString("bd_date")+"','YYYY/MM/DD') \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("    ORDER BY A.BD_BTIME , A.PROG_CODE DESC   \n");

        return sb.toString();
    }

    /**
     * 편성프로그램선택
     * @param retrieve
     * @return
     * @throws StoreException
     */
    public String makeSqlMultiFrame(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT TO_CHAR(C.BD_DATE, 'YYYY/MM/DD') AS BD_DATE,\n");
        sb.append("        TO_CHAR(C.BTIME, 'HH24:MI') AS BTIME,\n");
        sb.append("        TO_CHAR(C.ETIME, 'HH24:MI') AS ETIME,\n");
        sb.append("        B.MEDIA_CODE,\n");
        sb.append("        FUN_GET_MEDIA_NAME(B.MEDIA_CODE) AS MEDIA_NAME,\n");
        sb.append("        A.PROG_CODE,\n");
        sb.append("        A.PROG_NAME,\n");
        sb.append("        C.GOODS_CODE,\n");
        sb.append("        D.GOODS_NAME,\n");
        sb.append("        B.MAIN_PD,\n");
        sb.append("        E.USER_NAME AS MAIN_PD_NAME,\n");
        sb.append("        FUN_STAFF_NAME('06', B.SEQ_FRAME_NO) SHOW_HOST,\n");
        sb.append("        B.PLAN_AMT,\n");
        sb.append("        B.SEQ_FRAME_NO\n");
        sb.append(" FROM TPROGRAM A, \n");
        sb.append("      TMULTIFRAMESCHE B,\n");
        sb.append("      TMULTIDTBROAD C, \n");
        sb.append("      TGOODS D,\n");
        sb.append("      TUSER E\n");
        sb.append(" WHERE A.PROG_CODE = B.PROG_CODE\n");
        sb.append(" AND B.SEQ_FRAME_NO = C.SEQ_FRAME_NO\n");
        sb.append(" AND C.GOODS_CODE = D.GOODS_CODE\n");
        sb.append(" AND B.MAIN_PD = E.USER_ID\n");
        sb.append(" AND B.BD_DATE LIKE TO_DATE('"+retrieve.getString("bd_date")+"')||'%'\n");
        return sb.toString();
    }

    /**
     * 프로그램선택조회
     * @param retrieve
     * @return
     * @throws StoreException
     */
    public String makeSqlBdProgramSelect(RetrieveModel retrieve) throws StoreException{
    	StringBuffer sb = new StringBuffer();
		sb.append(" SELECT TO_CHAR(A.BD_DATE,'YYYY/MM/DD') AS BD_DATE,                                 \n");
		sb.append("        A.PROG_CODE,                                                                \n");
		sb.append("        B.PROG_NAME,                                                                \n");
		sb.append("        TO_CHAR(A.BD_BTIME,'YYYY/MM/DD HH24:MI:SS') AS BD_BTIME_ORG,                \n");
		sb.append("        TO_CHAR(A.BD_ETIME,'YYYY/MM/DD HH24:MI:SS') AS BD_ETIME_ORG,                \n");
		sb.append("        TO_CHAR(A.BD_BTIME,'HH24:MI') AS BD_BTIME,                                  \n");
		sb.append("        TO_CHAR(A.BD_ETIME,'HH24:MI') AS BD_ETIME,                                  \n");
		sb.append("        A.MAIN_PD,                                                                  \n");
		sb.append("        A.SUB_PD,                                                                   \n");
		sb.append("        C.USER_NAME AS MAIN_PD_NAME,                                                \n");
		sb.append("        D.USER_NAME AS SUB_PD_NAME,                                                 \n");
		sb.append("        FUN_STAFF_NAME('06',A.SEQ_FRAME_NO)  SHOWHOST,                              \n");
		sb.append("        A.PLAN_AMT,                                                                 \n");
		sb.append("        A.TAPE_CODE,                                                                \n");
		sb.append("        E.GOODS_CODE,                                                               \n");
		sb.append("        F.GOODS_NAME ,                                                              \n");
		sb.append("        A.SEQ_FRAME_NO,                                                             \n");
		sb.append("        A.MEDIA_CODE,                                                               \n");
		sb.append("        G.MEDIA_NAME                                                                \n");
		sb.append("   FROM TMULTIFRAMESCHE A,                                                          \n");
		sb.append("        TPROGRAM        B,                                                          \n");
		sb.append("        TUSER           C,                                                          \n");
		sb.append("        TUSER           D,                                                          \n");
		sb.append("        TMULTIDTBROAD   E,                                                          \n");
		sb.append("        TGOODS          F,                                                          \n");
		sb.append("        TMEDIA          G                                                           \n");
		sb.append("  WHERE A.PROG_CODE     = B.PROG_CODE                                               \n");
		sb.append("    AND A.MEDIA_CODE    = G.MEDIA_CODE                                              \n");
		sb.append("    AND A.SEQ_FRAME_NO  = E.SEQ_FRAME_NO(+)                                         \n");
		sb.append("    AND E.GOODS_CODE    = F.GOODS_CODE(+)                                           \n");
		sb.append("    AND A.MAIN_PD       = C.USER_ID(+)                                              \n");
		sb.append("    AND A.SUB_PD        = D.USER_ID(+)                                              \n");
		sb.append("    AND E.MAIN_YN (+)   = '1'                                                       \n");
		sb.append("    AND A.BD_DATE       = TO_DATE('"+retrieve.getString("bd_date")+"','YYYY/MM/DD') \n");
		//sb.append("    AND A.LIVE_FLAG    IN ('10','11')                                               \n");
		sb.append("    AND A.MEDIA_CODE   LIKE '" + retrieve.getString("media_code") + "%'                   \n");
		sb.append(retrieve.getString("MOD_QUERY"));
		sb.append("  ORDER BY A.BD_BTIME , A.PROG_CODE DESC                                            \n");
    	return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 입금은행 명
     * </PRE>
     * @return  Query
     */
     public String makeSqlReceiptsBankName(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("  SELECT BANK_CODE,  \n");
         sb.append("         BANK_SEQ,  \n");
         sb.append("         BANK_NAME,  \n");
         sb.append("         BANK_DEPOSIT_NO  \n");
         sb.append("    FROM TRECEIPTSBANK A   \n");
         sb.append("   WHERE USE_YN = '1'   \n");
         sb.append("     AND UPPER(BANK_NAME) LIKE '%'||UPPER('"+retrieve.getString("BANK_NAME")+"')||'%' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append("ORDER BY BANK_CODE, BANK_SEQ   \n");
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 방송편성스케쥴 Copy Pop
      * </PRE>
      * @return  Query
      */
      public String makeSqlBroadScheduleCopy(RetrieveModel retrieve) throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append(" SELECT  TO_CHAR(A.BD_DATE, 'YYYY/MM/DD')  AS BD_DATE,                              \n");
          sb.append(" 		   TO_CHAR(A.BD_BTIME, 'YYYY/MM/DD HH24:MI') AS BD_BTIME,                    \n");
          sb.append(" 		   TO_CHAR(A.BD_ETIME, 'YYYY/MM/DD HH24:MI') AS BD_ETIME,                    \n");
          sb.append(" 		   A.INSERT_DATE,                                                            \n");
          sb.append(" 		   A.MODIFY_DATE,                                                            \n");
          sb.append(" 		   A.PROG_CODE,                                                              \n");
          sb.append(" 		   C.PROG_NAME,	                                                             \n");
          sb.append(" 		   A.LIVE_YN, 	                                                             \n");
          sb.append("          A.MODIFY_YN,                                                              \n");
          sb.append(" 		   A.SNO,   	                                                             \n");
          sb.append(" 		   A.RECORD_YN,                                                              \n");
          sb.append(" 		   A.REMARK ,  	                                                             \n");
          sb.append(" 		   A.PLAN_AMT,                                                               \n");
          sb.append(" 		   A.PDMODIFY_YN,                                                            \n");
          sb.append(" 		   C.EDATE,		                                                             \n");
          sb.append("          '0' COMP_CHECK    										                 \n");
          sb.append("    FROM TFRAMESCHE A,                                                              \n");
          sb.append("         TPROGRAM   C                                                               \n");
          sb.append("   WHERE A.PROG_CODE = C.PROG_CODE                                                  \n");
          sb.append("     AND A.BD_DATE = TO_DATE('"+retrieve.getString("BROAD_DATE")+"', 'YYYY/MM/DD')  \n");
          sb.append(retrieve.getString("MOD_QUERY"));
          sb.append("   ORDER BY A.BD_DATE,                                                              \n");
          sb.append("            A.BD_BTIME                                                              \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 일별 판매 수량
       * </PRE>
       * @return  Query
       */
       public String makeSqlDailySale(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append(" SELECT   TO_CHAR(GATHER_DATE, 'YYYY/MM/DD') AS GATHER_DATE,  \n");
           sb.append("          GOODS_CODE    AS GOODS_CODE,    					\n");
           sb.append("          NVL(SUM(ORDER_QTY),0)   ORDER_QTY,   /* 접수 */    	\n");
           sb.append("          NVL(SUM(CANCEL_QTY),0)  CANCEL_QTY,  /* 취소 */    	\n");
           sb.append("          NVL(SUM(CLAIM_QTY),0)   CLAIM_QTY,   /* 반품 */      \n");
           sb.append("          NVL(SUM(EXCH_QTY),0)    EXCH_QTY,    /* 교환 */	    \n");
           sb.append("          NVL(SUM(ORDER_AMT),0)   ORDER_AMT    /* 주문 금액 */  \n");
           sb.append("   FROM   VSDORDERGOODS    \n");
           sb.append("  WHERE   GATHER_DATE BETWEEN TO_DATE('"+retrieve.getString("FROM_DATE")+"', 'YYYY/MM/DD')     \n");
           sb.append("    AND   TO_DATE('"+retrieve.getString("TO_DATE")+"', 'YYYY/MM/DD')                           \n");
           sb.append("    AND   GOODS_CODE   =  '"+retrieve.getString("GOODS_CODE")+"'                               \n");
           sb.append(" GROUP BY GATHER_DATE,  \n");
           sb.append(" 		  	GOODS_CODE    \n");
           sb.append(" ORDER BY GATHER_DATE,  \n");
           sb.append(" 		  	GOODS_CODE    \n");
           return sb.toString();
       }

       /**
        * <PRE>
        * Desc : 사용구분이 사용일 경우만 방송테입코드 SELECT
        * </PRE>
        * @return  Query
        */
        public String makeSqlTapeCode(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("  SELECT  A.TAPE_CODE,                                     \n");
            sb.append("          A.TAPE_NAME,                                     \n");
            sb.append("          D.GOODS_CODE,                                    \n");
            sb.append("          D.GOODS_NAME,                                    \n");
            sb.append("          A.TAPE_NO,                                       \n");
            sb.append("          A.TAPE_BARCODE,                                  \n");
            sb.append("          A.TAPE_TYPE,                                     \n");
            sb.append("          B.CODE_NAME AS  TAPE_TYPE_NAME,                  \n");
            sb.append("          TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') MAKE_DATE,    \n");
            sb.append("          DECODE(A.SNO1,'', A.SNO, A.SNO||','||A.SNO1) PD,    \n");
            sb.append("          SUBSTR(A.RUNTIME_FR,0,2)||':'||SUBSTR(A.RUNTIME_FR,3,2)||':'||SUBSTR(A.RUNTIME_FR,5,2)||':'||SUBSTR(A.RUNTIME_FR,7,3) AS RUNTIME_FR,     \n");
            sb.append("          SUBSTR(A.RUNTIME_TO,0,2)||':'||SUBSTR(A.RUNTIME_TO,3,2)||':'||SUBSTR(A.RUNTIME_TO,5,2)||':'||SUBSTR(A.RUNTIME_TO,7,3) AS RUNTIME_TO,     \n");
            sb.append("          A.REVIEW_TYPE,                                   \n");
            sb.append("          A.TAPE_DESC,                                     \n");
            sb.append("          A.REVIEW_DESC,                                   \n");
            sb.append("          FUN_PGM_SHOWHOST_NAME(A.TAPE_CODE) AS SHOWHOST,      \n");
            sb.append("          A.CAMERA_TEAM                                    \n");
            sb.append("     FROM TPGMTAPE A,                                      \n");
            sb.append("     		  TCODE B,   \n");
            sb.append("          TPGMTAPEGOODS C,   \n");
            sb.append("          TGOODS D 	                                     \n");
            sb.append("    WHERE A.USE_CODE = '00'                                \n");
            sb.append("      AND A.TAPE_CODE = C.TAPE_CODE   \n");
            sb.append("      AND C.GOODS_CODE = D.GOODS_CODE   \n");
            sb.append("      AND C.SPECIAL_YN = '1'   \n");
            sb.append("      AND B.CODE_LGROUP = 'B501'                	         \n");
            sb.append("    	 AND A.TAPE_TYPE = B.CODE_MGROUP        \n");
            sb.append("     AND A.TAPE_CODE LIKE '"+retrieve.getString("TAPE_CODE")+"'||'%' \n ");
            sb.append(retrieve.getString("MOD_QUERY"));
            sb.append("   ORDER BY  A.TAPE_CODE                           \n");
            return sb.toString();
        }

        /**
        * <PRE>
        * Desc : 사용구분이 사용일 경우만 방송테입이름 SELECT
        * </PRE>
        * @return  Query
        */
        public String makeSqlTapeName(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("  SELECT  A.TAPE_CODE,                                     \n");
            sb.append("          A.TAPE_NAME,                                     \n");
            sb.append("          D.GOODS_CODE,                                    \n");
            sb.append("          D.GOODS_NAME,                                    \n");
            sb.append("          A.TAPE_NO,                                       \n");
            sb.append("          A.TAPE_BARCODE,                                  \n");
            sb.append("          A.TAPE_TYPE,                                     \n");
            sb.append("          B.CODE_NAME AS  TAPE_TYPE_NAME,                  \n");
            sb.append("          TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') MAKE_DATE,    \n");
            sb.append("          DECODE(A.SNO1,'', A.SNO, A.SNO||','||A.SNO1) PD,    \n");
            sb.append("          SUBSTR(A.RUNTIME_FR,0,2)||':'||SUBSTR(A.RUNTIME_FR,3,2)||':'||SUBSTR(A.RUNTIME_FR,5,2)||':'||SUBSTR(A.RUNTIME_FR,7,3) AS RUNTIME_FR,     \n");
            sb.append("          SUBSTR(A.RUNTIME_TO,0,2)||':'||SUBSTR(A.RUNTIME_TO,3,2)||':'||SUBSTR(A.RUNTIME_TO,5,2)||':'||SUBSTR(A.RUNTIME_TO,7,3) AS RUNTIME_TO,     \n");
            sb.append("          A.REVIEW_TYPE,                                   \n");
            sb.append("          A.TAPE_DESC,                                     \n");
            sb.append("          A.REVIEW_DESC,                                   \n");
            sb.append("          FUN_PGM_SHOWHOST_NAME(A.TAPE_CODE) AS SHOWHOST,      \n");
            sb.append("          A.CAMERA_TEAM                                    \n");
            sb.append("     FROM TPGMTAPE A,                                      \n");
            sb.append("     		  TCODE B,   \n");
            sb.append("          TPGMTAPEGOODS C,   \n");
            sb.append("          TGOODS D 	                                     \n");
            sb.append("    WHERE A.USE_CODE = '00'                                \n");
            sb.append("      AND A.TAPE_CODE = C.TAPE_CODE   \n");
            sb.append("      AND C.GOODS_CODE = D.GOODS_CODE   \n");
            sb.append("      AND C.SPECIAL_YN = '1'   \n");
            sb.append("      AND B.CODE_LGROUP = 'B501'                	         \n");
            sb.append("    	 AND A.TAPE_TYPE = B.CODE_MGROUP        \n");
            sb.append("   AND UPPER(A.TAPE_NAME) LIKE '%' || UPPER('"+retrieve.getString("TAPE_NAME")+"') || '%' \n ");
            sb.append(retrieve.getString("MOD_QUERY"));
            sb.append("   ORDER BY  A.TAPE_CODE                           \n");
            return sb.toString();
        }

        /**
         * <PRE>
         * Desc : 사용구분이 사용일 경우만 방송테입코드 SELECT
         * </PRE>
         * @return  Query
         */
         public String makeSqlTapeCodeGoods(RetrieveModel retrieve) throws StoreException{
             StringBuffer sb = new StringBuffer();
             sb.append("  SELECT  A.TAPE_CODE,                                     \n");
             sb.append("          A.TAPE_NAME,                                     \n");
             sb.append("          D.GOODS_CODE,                                    \n");
             sb.append("          D.GOODS_NAME,                                    \n");
             sb.append("          A.TAPE_NO,                                       \n");
             sb.append("          A.TAPE_BARCODE,                                  \n");
             sb.append("          A.TAPE_TYPE,                                     \n");
             sb.append("          B.CODE_NAME AS  TAPE_TYPE_NAME,                  \n");
             sb.append("          TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') MAKE_DATE,    \n");
             sb.append("          DECODE(A.SNO1,'', A.SNO, A.SNO||','||A.SNO1) PD,    \n");
             sb.append("          SUBSTR(A.RUNTIME_FR,0,2)||':'||SUBSTR(A.RUNTIME_FR,3,2)||':'||SUBSTR(A.RUNTIME_FR,5,2)||':'||SUBSTR(A.RUNTIME_FR,7,3) AS RUNTIME_FR,     \n");
             sb.append("          SUBSTR(A.RUNTIME_TO,0,2)||':'||SUBSTR(A.RUNTIME_TO,3,2)||':'||SUBSTR(A.RUNTIME_TO,5,2)||':'||SUBSTR(A.RUNTIME_TO,7,3) AS RUNTIME_TO,     \n");
             sb.append("          A.REVIEW_TYPE,                                   \n");
             sb.append("          A.TAPE_DESC,                                     \n");
             sb.append("          A.REVIEW_DESC,                                   \n");
             sb.append("          FUN_PGM_SHOWHOST_NAME(A.TAPE_CODE) AS SHOWHOST,      \n");
             sb.append("          A.CAMERA_TEAM                                    \n");
             sb.append("     FROM TPGMTAPE A,                                      \n");
             sb.append("     		  TCODE B,   \n");
             sb.append("          TPGMTAPEGOODS C,   \n");
             sb.append("          TGOODS D 	                                     \n");
             sb.append("    WHERE A.USE_CODE = '00'                                \n");
             sb.append("      AND A.TAPE_CODE = C.TAPE_CODE   \n");
             sb.append("      AND C.GOODS_CODE = D.GOODS_CODE   \n");
             sb.append("      AND C.SPECIAL_YN = '1'   \n");
             sb.append("      AND B.CODE_LGROUP = 'B501'                	         \n");
             sb.append("    	 AND A.TAPE_TYPE = B.CODE_MGROUP        \n");
             sb.append("      AND C.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"' || '%'  \n");
             sb.append(retrieve.getString("MOD_QUERY"));
             sb.append("   ORDER BY  A.TAPE_CODE                           \n");
             return sb.toString();
         }

         /**
         * <PRE>
         * Desc : 사용구분이 사용일 경우만 방송테입이름 SELECT
         * </PRE>
         * @return  Query
         */
         public String makeSqlTapeNameGoods(RetrieveModel retrieve) throws StoreException{
             StringBuffer sb = new StringBuffer();
             sb.append("  SELECT  A.TAPE_CODE,                                     \n");
             sb.append("          A.TAPE_NAME,                                     \n");
             sb.append("          D.GOODS_CODE,                                    \n");
             sb.append("          D.GOODS_NAME,                                    \n");
             sb.append("          A.TAPE_NO,                                       \n");
             sb.append("          A.TAPE_BARCODE,                                  \n");
             sb.append("          A.TAPE_TYPE,                                     \n");
             sb.append("          B.CODE_NAME AS  TAPE_TYPE_NAME,                  \n");
             sb.append("          TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') MAKE_DATE,    \n");
             sb.append("          DECODE(A.SNO1,'', A.SNO, A.SNO||','||A.SNO1) PD,    \n");
             sb.append("          SUBSTR(A.RUNTIME_FR,0,2)||':'||SUBSTR(A.RUNTIME_FR,3,2)||':'||SUBSTR(A.RUNTIME_FR,5,2)||':'||SUBSTR(A.RUNTIME_FR,7,3) AS RUNTIME_FR,     \n");
             sb.append("          SUBSTR(A.RUNTIME_TO,0,2)||':'||SUBSTR(A.RUNTIME_TO,3,2)||':'||SUBSTR(A.RUNTIME_TO,5,2)||':'||SUBSTR(A.RUNTIME_TO,7,3) AS RUNTIME_TO,     \n");
             sb.append("          A.REVIEW_TYPE,                                   \n");
             sb.append("          A.TAPE_DESC,                                     \n");
             sb.append("          A.REVIEW_DESC,                                   \n");
             sb.append("          FUN_PGM_SHOWHOST_NAME(A.TAPE_CODE) AS SHOWHOST,      \n");
             sb.append("          A.CAMERA_TEAM                                    \n");
             sb.append("     FROM TPGMTAPE A,                                      \n");
             sb.append("     		  TCODE B,   \n");
             sb.append("          TPGMTAPEGOODS C,   \n");
             sb.append("          TGOODS D 	                                     \n");
             sb.append("    WHERE A.USE_CODE = '00'                                \n");
             sb.append("      AND A.TAPE_CODE = C.TAPE_CODE   \n");
             sb.append("      AND C.GOODS_CODE = D.GOODS_CODE   \n");
             sb.append("      AND C.SPECIAL_YN = '1'   \n");
             sb.append("      AND B.CODE_LGROUP = 'B501'                	         \n");
             sb.append("    	 AND A.TAPE_TYPE = B.CODE_MGROUP        \n");
             sb.append("      AND UPPER(D.GOODS_NAME) LIKE '%' || UPPER('"+retrieve.getString("GOODS_NAME")+"') || '%'   \n");
             sb.append(retrieve.getString("MOD_QUERY"));
             sb.append("   ORDER BY  A.TAPE_CODE                           \n");
             return sb.toString();
         }
        /**
         * 유형분석팝업
         * @param retrieve
         * @return
         * @throws StoreException
         */
        public String makeSqlBoardKinds(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();

            sb.append("    SELECT	A.CODE_NAME AS BOARD_LKINDS,								 \n");
            sb.append("   			C.CODE_NAME AS BOARD_MKINDS,	                             \n");
            sb.append("   			NVL(B.COUNT, 0) AS COUNT                                     \n");
            sb.append("     FROM	TCODE A,                                                     \n");
            sb.append("   		(	SELECT  C.BOARD_LKINDS AS BOARD_LKINDS,                      \n");
            sb.append("   					C.BOARD_MKINDS AS BOARD_MKINDS,                      \n");
            sb.append("   				COUNT(C.BOARD_SEQ) AS COUNT                              \n");
            sb.append("   			  FROM  TBOARD C                                             \n");
            sb.append("   		     WHERE  C.INSERT_DATE >= TO_DATE('"+retrieve.getString("B_DATE")+"','YYYY/MM/DD')      \n");
            sb.append("   			   AND  C.INSERT_DATE < TO_DATE('"+retrieve.getString("E_DATE")+"','YYYY/MM/DD') + 1   \n");
            sb.append("   		   GROUP BY C.BOARD_LKINDS, C.BOARD_MKINDS	) B,                 \n");
            sb.append("   			TCODE C                                                      \n");
            sb.append("    	 WHERE  A.CODE_MGROUP = B.BOARD_LKINDS                               \n");
            sb.append("   	   AND  A.CODE_LGROUP = 'F012'                                       \n");
            sb.append("   	   AND  A.REMARK	  = C.CODE_LGROUP                                \n");
            sb.append("   	   AND  C.CODE_MGROUP = B.BOARD_MKINDS                               \n");
            sb.append(retrieve.getString("MOD_QUERY"));
            sb.append("    ORDER BY A.CODE_MGROUP, C.CODE_MGROUP                                 \n");

            return sb.toString();
        }

        /**
         * SO사팝업
         * @param retrieve
         * @return
         * @throws StoreException
         */
        public String makeSqlSoCode(RetrieveModel retrieve) throws StoreException{

            StringBuffer sb = new StringBuffer();

            sb.append(" SELECT  A.SO_CODE,                                  \n");
            sb.append("         A.SO_NAME                                   \n");
            sb.append("    FROM TSOMANAGE A                                 \n");
            sb.append("   WHERE A.SO_CODE LIKE '"+retrieve.getString("SO_CODE")+"'||'%' \n ");
            sb.append(retrieve.getString("MOD_QUERY"));
            sb.append("   ORDER BY  A.SO_CODE                           	\n");

            return sb.toString();
        }

        /**
         * <PRE>
         * Desc : SO사 우편번호
         * </PRE>
         * @return  Query
         */
         public String makeSqlSoPostNo(RetrieveModel retrieve) throws StoreException{
             StringBuffer sb = new StringBuffer();
             sb.append("SELECT A.POST_NO,  \n");
             sb.append("       A.POST_SEQ,  \n");
             sb.append("       A.CITY_NAME,  \n");
             sb.append("       A.GU_NAME,  \n");
             sb.append("       A.DONG_NAME,  \n");
             sb.append("       '1' AS SELECTED,  \n");
             sb.append("       A.DDD  \n");
             sb.append("  FROM TPOST A  \n");
             sb.append(" WHERE A.USE_YN = '1' \n");
             sb.append("   AND A.POST_NO LIKE '"+retrieve.getString("POST_NO")+"'||'%' \n ");
             sb.append(retrieve.getString("MOD_QUERY"));
             sb.append(" ORDER BY A.POST_NO ASC, A.POST_SEQ ASC  \n");
             return sb.toString();
         }



         /**
         * <PRE>
         * Desc : StateCode Of Post
         * </PRE>
         * @return  Query
         */
         public String makeSqlStateCode(RetrieveModel retrieve) throws StoreException{
        	 StringBuffer sb = new StringBuffer();
             sb.append("    SELECT CODE_MGROUP,       \n");
             sb.append("           CODE_NAME,         \n");
             sb.append("           CODE_SNAME,         \n");
             sb.append("           CODE_GROUP,         \n");
             sb.append("           REMARK,             \n");
             sb.append("           REMARK1,            \n");
             sb.append("           REMARK2              \n");
             sb.append("      FROM TCODE                      \n");
             sb.append("     WHERE CODE_MGROUP LIKE '"+retrieve.getString("STATE_CODE")+"'||'%' \n ");
             sb.append("       AND CODE_LGROUP = 'B063' 			\n ");
             sb.append("       AND USE_YN = '1'               \n");
             sb.append("     ORDER BY CODE_MGROUP            \n");

             return sb.toString();
         }

         /**
         * <PRE>
         * Desc : StateName Of Post
         * </PRE>
         * @return  Query
         */
         public String makeSqlStateName(RetrieveModel retrieve) throws StoreException{
             StringBuffer sb = new StringBuffer();
             sb.append("    SELECT CODE_MGROUP,       \n");
             sb.append("           CODE_NAME,         \n");
             sb.append("           CODE_SNAME,         \n");
             sb.append("           CODE_GROUP,         \n");
             sb.append("           REMARK,             \n");
             sb.append("           REMARK1,            \n");
             sb.append("           REMARK2              \n");
             sb.append("      FROM TCODE                \n");
             sb.append("     WHERE UPPER(CODE_NAME) LIKE '%'||UPPER('"+retrieve.getString("STATE_NAME")+"')||'%' \n ");
             sb.append("       AND CODE_LGROUP = 'B063' 			\n ");
             sb.append("       AND USE_YN = '1'              \n");
             sb.append("     ORDER BY CODE_MGROUP            \n");

             return sb.toString();
         }

         /**
          * <PRE>
          * Desc : Cspf_code Of Tgoodsinfodt
          * </PRE>
          * @return  Query
          */
          public String makeSqlColorPattern(RetrieveModel retrieve) throws StoreException{
              StringBuffer sb = new StringBuffer();

              sb.append(" SELECT B.CSPF_GROUP,  			\n");
              sb.append("        B.CSPF_CODE, 				\n");
              sb.append("        B.CSPF_NAME,     			\n");
              sb.append("        B.USE_YN,   				\n");
              sb.append("        TO_CHAR(B.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE ,  \n");
              sb.append("        B.INSERT_ID,  				\n");
              sb.append("        '0' AS SELECTION  			\n");
              sb.append("   FROM TGOODSINFODT B   			\n");
              sb.append("  WHERE B.CSPF_GROUP LIKE UPPER('"+retrieve.getString("CSPF_GROUP")+"')	\n");
              sb.append("    AND B.USE_YN = '1'				\n");
              sb.append("  ORDER BY CSPF_CODE            	\n");

              return sb.toString();
          }

          /**
          * <PRE>
          * Desc : 구분코드(색상)
          * </PRE>
          * @return  Query
          */
          public String makeSqlCspfGroupColorCode(RetrieveModel retrieve) throws StoreException{
              StringBuffer sb = new StringBuffer();
              sb.append(" SELECT A.CSPF_CODE, 				\n");
              sb.append(" 		 A.CSPF_NAME				\n");
              sb.append("   FROM TGOODSINFODT A				\n");
              sb.append("  WHERE A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
              sb.append("    AND A.CSPF_CODE LIKE '"+retrieve.getString("CSPF_CODE")+"'||'%' \n ");
              sb.append("    AND A.USE_YN = '1'  			\n");
              sb.append(retrieve.getString("MOD_QUERY"));
              sb.append("  ORDER BY A.CSPF_CODE   			\n");

/*			 //= 2009/09/03 jsy  수정
              sb.append(" SELECT A.CSPF_FLAG, 	\n");
              sb.append("        A.CSPF_GROUP, 	\n");
              sb.append(" 		 A.CSPF_DESC	\n");
              sb.append("   FROM TGOODSINFOM A 			\n");
              sb.append("  WHERE A.CSPF_FLAG = 'C' 		\n ");
              sb.append("    AND A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
              sb.append("    AND A.USE_YN = '1'  		\n");
              sb.append(retrieve.getString("MOD_QUERY"));
              sb.append("  ORDER BY A.CSPF_GROUP ASC  	\n");
*/
              return sb.toString();
          }

          /**
           * <PRE>
           * Desc : 구분코드(사이즈)
           * </PRE>
           * @return  Query
           */
           public String makeSqlCspfGroupSizeCode(RetrieveModel retrieve) throws StoreException{
               StringBuffer sb = new StringBuffer();
               sb.append(" SELECT A.CSPF_FLAG, 	\n");
               sb.append("        A.CSPF_GROUP, 	\n");
               sb.append(" 		 A.CSPF_DESC	\n");
               sb.append("   FROM TGOODSINFOM A 			\n");
               sb.append("  WHERE A.CSPF_FLAG = 'S' 		\n ");
               sb.append("    AND A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
               sb.append("    AND A.USE_YN = '1'  		\n");
               sb.append(retrieve.getString("MOD_QUERY"));
               sb.append("  ORDER BY A.CSPF_GROUP ASC  	\n");
               return sb.toString();
           }

           /**
            * <PRE>
            * Desc : 구분코드(형태)
            * </PRE>
            * @return  Query
            */
            public String makeSqlCspfGroupFormCode(RetrieveModel retrieve) throws StoreException{
                StringBuffer sb = new StringBuffer();
                sb.append(" SELECT A.CSPF_FLAG, 	\n");
                sb.append("        A.CSPF_GROUP, 	\n");
                sb.append(" 		 A.CSPF_DESC	\n");
                sb.append("   FROM TGOODSINFOM A 			\n");
                sb.append("  WHERE A.CSPF_FLAG = 'F' 		\n ");
                sb.append("    AND A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
                sb.append("    AND A.USE_YN = '1'  		\n");
                sb.append(retrieve.getString("MOD_QUERY"));
                sb.append("  ORDER BY A.CSPF_GROUP ASC  	\n");
                return sb.toString();
            }

            /**
             * <PRE>
             * Desc : 구분코드(무늬)
             * </PRE>
             * @return  Query
             */
             public String makeSqlCspfGroupPatternCode(RetrieveModel retrieve) throws StoreException{
                 StringBuffer sb = new StringBuffer();
                 sb.append(" SELECT A.CSPF_CODE, 				\n");
                 sb.append(" 		 A.CSPF_NAME				\n");
                 sb.append("   FROM TGOODSINFODT A				\n");
                 sb.append("  WHERE A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
                 sb.append("    AND A.CSPF_CODE LIKE '"+retrieve.getString("CSPF_CODE")+"'||'%' \n ");
                 sb.append("    AND A.USE_YN = '1'  			\n");
                 sb.append(retrieve.getString("MOD_QUERY"));
                 sb.append("  ORDER BY A.CSPF_CODE   			\n");

/*				2009/09/03 jsy 수
                 sb.append(" SELECT A.CSPF_FLAG, 	\n");
                 sb.append("        A.CSPF_GROUP, 	\n");
                 sb.append(" 		 A.CSPF_DESC	\n");
                 sb.append("   FROM TGOODSINFOM A 			\n");
                 sb.append("  WHERE A.CSPF_FLAG = 'P' 		\n ");
                 sb.append("    AND A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
                 sb.append("    AND A.USE_YN = '1'  		\n");
                 sb.append(retrieve.getString("MOD_QUERY"));
                 sb.append("  ORDER BY A.CSPF_GROUP ASC  	\n");
*/
                 return sb.toString();
             }

             /**
              * <PRE>
              * Desc : 구분코드(마스터)
              * </PRE>
              * @return  Query
              */
              public String makeSqlCspfGroupCode(RetrieveModel retrieve) throws StoreException{
                  StringBuffer sb = new StringBuffer();
                  sb.append(" SELECT A.CSPF_FLAG, 								\n");
                  sb.append("        SUBSTR(A.CSPF_GROUP, 2, 3) AS CSPF_GROUP, 	\n");
                  sb.append(" 		 A.CSPF_DESC								\n");
                  sb.append("   FROM TGOODSINFOM A 								\n");
                  sb.append("  WHERE A.CSPF_FLAG = '"+retrieve.getString("CSPF_FLAG")+"' \n ");
                  sb.append("    AND A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
                  sb.append("    AND A.USE_YN = '1'  		\n");
                  sb.append(retrieve.getString("MOD_QUERY"));
                  sb.append("  ORDER BY A.CSPF_GROUP ASC  	\n");
                  return sb.toString();
              }

              /**
               * <PRE>
               * Desc : 구분코드(마스터)
               * </PRE>
               * @return  Query
               */
               public String makeSqlCspfGroupAutoSel(RetrieveModel retrieve) throws StoreException{
                   StringBuffer sb = new StringBuffer();
                   sb.append(" SELECT '0' SELECTION, 			\n");
                   sb.append("        A.CSPF_CODE, 				\n");
                   sb.append(" 		 A.CSPF_NAME,				\n");
                   sb.append(" 		 A.USE_YN					\n");
                   sb.append("   FROM TGOODSINFODT A				\n");
                   sb.append("  WHERE A.CSPF_GROUP LIKE '"+retrieve.getString("CSPF_GROUP")+"'||'%' \n ");
                   sb.append("    AND A.USE_YN = '1'  			\n");
                   sb.append(retrieve.getString("MOD_QUERY"));
                   sb.append("  ORDER BY A.CSPF_GROUP, 			\n");
                   sb.append("			A.CSPF_CODE   			\n");
                   return sb.toString();
               }

             /**
              * <PRE>
              * Desc : UserGroup ID 정보를 얻는다
              * </PRE>
              * @return  Query
              */
              public String makeSqlUserGroupId(RetrieveModel retrieve) throws StoreException{
                  StringBuffer sb = new StringBuffer();
                  sb.append("SELECT A.CODE_MGROUP, \n");
                  sb.append("       A.CODE_NAME \n");
                  sb.append("  FROM TCODE A \n");
                  sb.append(" WHERE A.CODE_LGROUP = 'B040' \n");
                  sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("USER_GROUP_ID")+"'||'%' \n ");
                  sb.append(retrieve.getString("MOD_QUERY"));
                  sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");
                  return sb.toString();
              }

              /**
               * <PRE>
               * Desc : 업체반출등록의 상품명과 반출가능수량 조회
               * </PRE>
               * @return  Query
               */
               public String makeSqlGoodsCodeEoutQty(RetrieveModel retrieve) throws StoreException{
            	   StringBuffer sb = new StringBuffer();
	               sb.append("	SELECT   /*+ RULE */ 						\n");
	               sb.append("			 A.GOODS_CODE, 						\n");
	               sb.append("			 A.GOODSDT_CODE,					\n");
	               sb.append("	         C.GOODS_NAME, 						\n");
	               sb.append("    	     D.GOODSDT_INFO, 					\n");
	               sb.append("       	  	 SUM(NVL(B.AQTY, 0)) - (SUM(NVL(A.ORDER_QTY,0)) + SUM(NVL(A.OUT_PLAN_QTY,0))) AS AQTY, \n");
	               sb.append("	         SUM(NVL(B.BQTY, 0)) AS BQTY 		\n");
	               sb.append("	    FROM TORDERSTOCK A, 					\n");
	               sb.append("	         TSTOCK B, 							\n");
	               sb.append("	         TGOODS C, 							\n");
	               sb.append("	         TGOODSDT D 						\n");
	               sb.append("	   WHERE A.GOODS_CODE   = B.GOODS_CODE 		\n");
	               sb.append("	     AND A.GOODSDT_CODE = B.GOODSDT_CODE 	\n");
	               sb.append("	     AND A.WH_CODE      = B.WH_CODE 		\n");
	               sb.append("	     AND C.GOODS_CODE   = D.GOODS_CODE 		\n");
	               sb.append("	     AND A.GOODS_CODE   = C.GOODS_CODE 		\n");
	               sb.append("	     AND A.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
	               sb.append("	     AND B.GOODS_CODE   = C.GOODS_CODE 		\n");
	               sb.append("	     AND B.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
	               sb.append("	     AND A.WH_CODE      = '"+retrieve.getString("WH_CODE")+"' 			\n");
	               sb.append("	     AND C.ENTP_CODE    = '"+retrieve.getString("ENTP_CODE")+"' 		\n");
	               sb.append("	     AND A.GOODS_CODE	LIKE '"+retrieve.getString("GOODS_CODE")+"' || '%' 		\n");
	               sb.append(retrieve.getString("MOD_QUERY"));
	               sb.append("	  GROUP BY A.GOODS_CODE, 					\n");
	               sb.append("	           A.GOODSDT_CODE, 					\n");
	               sb.append("	           C.GOODS_NAME, 					\n");
	               sb.append("	           D.GOODSDT_INFO 					\n");
	               return sb.toString();
               }

               /**
                * <PRE>
                * Desc : 업체반출등록의 상품명과 반출가능수량 조회
                * </PRE>
                * @return  Query
                */
                public String makeSqlGoodsNameEoutQty(RetrieveModel retrieve) throws StoreException{
             	   StringBuffer sb = new StringBuffer();
	               sb.append("	SELECT   /*+ RULE */ 						\n");
	               sb.append("			 A.GOODS_CODE, 						\n");
	               sb.append("			 A.GOODSDT_CODE,					\n");
 	               sb.append("	         C.GOODS_NAME, 						\n");
 	               sb.append("    	     D.GOODSDT_INFO, 					\n");
 	               sb.append("       	  	 SUM(NVL(B.AQTY, 0)) - (SUM(NVL(A.ORDER_QTY,0)) + SUM(NVL(A.OUT_PLAN_QTY,0))) AS AQTY, \n");
 	               sb.append("	         SUM(NVL(B.BQTY, 0)) AS BQTY 		\n");
 	               sb.append("	    FROM TORDERSTOCK A, 					\n");
 	               sb.append("	         TSTOCK B, 							\n");
 	               sb.append("	         TGOODS C, 							\n");
 	               sb.append("	         TGOODSDT D 						\n");
 	               sb.append("	   WHERE A.GOODS_CODE   = B.GOODS_CODE 		\n");
 	               sb.append("	     AND A.GOODSDT_CODE = B.GOODSDT_CODE 	\n");
 	               sb.append("	     AND A.WH_CODE      = B.WH_CODE 		\n");
 	               sb.append("	     AND C.GOODS_CODE   = D.GOODS_CODE 		\n");
 	               sb.append("	     AND A.GOODS_CODE   = C.GOODS_CODE 		\n");
 	               sb.append("	     AND A.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
 	               sb.append("	     AND B.GOODS_CODE   = C.GOODS_CODE 		\n");
 	               sb.append("	     AND B.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
 	               sb.append("	     AND A.WH_CODE      = '"+retrieve.getString("WH_CODE")+"' 			\n");
 	               sb.append("	     AND C.ENTP_CODE    = '"+retrieve.getString("ENTP_CODE")+"' 		\n");
 	               sb.append("	     AND A.GOODS_NAME	LIKE '"+retrieve.getString("GOODS_CODE")+"' || '%' 		\n");
 	               sb.append(retrieve.getString("MOD_QUERY"));
 	               sb.append("	  GROUP BY A.GOODS_CODE, 					\n");
 	               sb.append("	           A.GOODSDT_CODE, 					\n");
 	               sb.append("	           C.GOODS_NAME, 					\n");
 	               sb.append("	           D.GOODSDT_INFO 					\n");
 	               return sb.toString();
                }

                /**
                 * 결제 정보
                 * @param retrieve
                 * @return
                 * @throws StoreException
                 */
                public String makeSqlSettleino(RetrieveModel retrieve) throws StoreException{
                    StringBuffer sb = new StringBuffer();
                    sb.append(" SELECT SETTLE_FLAG, \n");
                    sb.append("        SETTLE_NAME \n");
                    sb.append("     FROM VSETTLEINFO \n");
                    sb.append("   WHERE SETTLE_FLAG LIKE '"+retrieve.getString("SETTLE_FLAG")+"'||'%' \n");
                    sb.append(retrieve.getString("MOD_QUERY"));
                    sb.append("   ORDER BY SETTLE_FLAG ASC  \n");
                    return sb.toString();
                }

                /**
                 * <PRE>
                 * Desc : Template code 템플릿 코드 정보를 얻는다
                 * </PRE>
                 * @return  Query
                 */
                 public String makeSqlTemplateCode(RetrieveModel retrieve) throws StoreException{
                     StringBuffer sb = new StringBuffer();
                     sb.append("SELECT A.CODE_MGROUP, \n");
                     sb.append("       A.CODE_NAME \n");
                     sb.append("  FROM TCODE A \n");
                     sb.append(" WHERE A.CODE_LGROUP = 'B117' \n");
                     sb.append("   AND A.USE_YN  = '1'  \n");
                     sb.append("   AND A.CODE_MGROUP LIKE '"+retrieve.getString("TEMPLATE_CODE")+"'||'%' \n ");
                     sb.append(retrieve.getString("MOD_QUERY"));
                     sb.append(" ORDER BY A.CODE_MGROUP ASC  \n");
                     return sb.toString();
                 }

                 /**
                  * <PRE>
                  * Desc : 기획전그룹코드
                  * </PRE>
                  * @return  Query
                  */
                  public String makeSqlPlanclassCode(RetrieveModel retrieve) throws StoreException{
                      StringBuffer sb = new StringBuffer();
                      sb.append("SELECT A.PLANCLASS_CODE,  \n");
                      sb.append("       A.PLANCLASS_NAME,   \n");
                      sb.append("       A.USE_YN   \n");
                      sb.append("  FROM TPLANCLASS A  \n");
                      sb.append(" WHERE A.PLANCLASS_CODE LIKE '"+retrieve.getString("PLANCLASS_CODE")+"'||'%' \n ");
                      sb.append(retrieve.getString("MOD_QUERY"));
                      sb.append(" ORDER BY PLANCLASS_CODE ASC \n");
                      return sb.toString();
                  }

                  /**
                   * <PRE>
                   * Desc : 기획전코드
                   * </PRE>
                   * @return  Query
                   */
                   public String makeSqlPlanCode(RetrieveModel retrieve) throws StoreException{
                       StringBuffer sb = new StringBuffer();
                       sb.append("SELECT A.PLAN_CODE,  \n");
                       sb.append("       A.PLAN_NAME   \n");
                       sb.append("  FROM TPLAN A  \n");
                       sb.append(" WHERE A.PLAN_CODE LIKE '"+retrieve.getString("PLAN_CODE")+"'||'%' \n ");
                       sb.append(retrieve.getString("MOD_QUERY"));
                       sb.append(" ORDER BY PLAN_CODE ASC \n");
                       return sb.toString();
                   }

                   /**
                    * <PRE>
                    * Desc : 상품코드로 SELECT (업체명, 업체코드 조회)
                    * </PRE>
                    * @return  Query
                    */
                    public String makeSqlGoodsCodeScm(RetrieveModel retrieve) throws StoreException{
                        StringBuffer sb = new StringBuffer();
                        sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
                        sb.append("       A.GOODS_NAME,   \n");
                        sb.append("       A.ENTP_CODE,   \n");
                        sb.append("       B.ENTP_NAME,   \n");
                        sb.append("       A.TAX_YN,  \n");
                        sb.append("       A.DELY_TYPE,   \n");
                        sb.append("       A.SALE_GB,  \n");
                        sb.append("       A.MD_CODE,   \n");
                        sb.append("       A.STOCK_CHK_PLACE  \n");
                        sb.append("  FROM TGOODS A,   \n");
                        sb.append("  	  TENTERPRISE B   \n");
                        sb.append(" WHERE A.ENTP_CODE = B.ENTP_CODE  \n ");
                        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
                        sb.append(retrieve.getString("MOD_QUERY"));
                        return sb.toString();
                    }

                    /**
                     * <PRE>
                     * Desc : 상품명으로 SELECT (업체명, 업체코드 조회)
                     * </PRE>
                     * @return  Query
                     */
                     public String makeSqlGoodsNameScm(RetrieveModel retrieve) throws StoreException{
                         StringBuffer sb = new StringBuffer();
                         sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
                         sb.append("       A.GOODS_NAME,   \n");
                         sb.append("       A.ENTP_CODE,   \n");
                         sb.append("       B.ENTP_NAME,   \n");
                         sb.append("       A.TAX_YN,  \n");
                         sb.append("       A.DELY_TYPE,   \n");
                         sb.append("       A.SALE_GB,  \n");
                         sb.append("       A.MD_CODE,   \n");
                         sb.append("       A.STOCK_CHK_PLACE  \n");
                         sb.append("  FROM TGOODS A,   \n");
                         sb.append("  	   TENTERPRISE B   \n");
                         sb.append(" WHERE A.ENTP_CODE = B.ENTP_CODE  \n");
                         sb.append("   AND UPPER(A.GOODS_NAME) LIKE '%'||UPPER('"+retrieve.getString("GOODS_NAME")+"')||'%' \n ");
                         sb.append(retrieve.getString("MOD_QUERY"));
                         return sb.toString();
                     }

                     /**
                      * 프로그램선택조회
                      * @param retrieve
                      * @return
                      * @throws StoreException
                      */
                     public String makeSqlInInspect(RetrieveModel retrieve) throws StoreException{
                     	 StringBuffer sb = new StringBuffer();
                     	 sb.append(" SELECT  A.BALJU_NO   											\n");
                         sb.append("        ,A.GOODSDT_CODE     									\n");
                         sb.append("        ,A.BALJU_QTY     										\n");
                         sb.append("        ,TO_CHAR(A.QA_DATE, 'YYYY/MM/DD') AS QA_DATE 			\n");
                         sb.append("        ,TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE 	\n");
                         sb.append("        ,A.INSERT_ID   											\n");
                         sb.append("        ,A.QA_QTY   											\n");
                         sb.append("        ,A.SQC_GB    											\n");
                         sb.append("        ,B.USER_NAME    										\n");
                         sb.append("        ,C.GOODSDT_INFO    										\n");
                         sb.append("   FROM  TAFTERQA A 											\n");
                         sb.append("       ,TUSER B 												\n");
                         sb.append("       ,TGOODSDT C 												\n");
                         sb.append("  WHERE A.INSERT_ID = B.USER_ID	 								\n");
                         sb.append("    AND A.GOODS_CODE = C.GOODS_CODE	 							\n");
                         sb.append("    AND A.GOODSDT_CODE = C.GOODSDT_CODE	 						\n");
                         sb.append("    AND A.GOODS_CODE LIKE '"+retrieve.getString("goods_code")+"' || '%'	\n");
                 		 sb.append(retrieve.getString("MOD_QUERY"));
                 		 sb.append("  ORDER BY A.BALJU_NO DESC                                      \n");
                     	 return sb.toString();
                     }
                  /**
                  * <PRE>
                  * Desc : 편성스케쥴 Copy Pop
                  * </PRE>
                  * @return  Query
                  */
                  public String makeSqlMultiBroadScheduleCopy(RetrieveModel retrieve) throws StoreException{
                      StringBuffer sb = new StringBuffer();
                      sb.append(" SELECT  TO_CHAR(A.BD_DATE, 'YYYY/MM/DD')  AS BD_DATE,                              \n");
                      sb.append(" 		   TO_CHAR(A.BD_BTIME, 'YYYY/MM/DD HH24:MI') AS BD_BTIME,                    \n");
                      sb.append(" 		   TO_CHAR(A.BD_ETIME, 'YYYY/MM/DD HH24:MI') AS BD_ETIME,                    \n");
                      sb.append("		   ROUND((A.BD_ETIME - A.BD_BTIME)*1440,0) AS RUNTIME, 						 \n");
                      sb.append(" 		   A.PROG_CODE,                                                              \n");
                      sb.append(" 		   B.PROG_NAME,	                                                             \n");
                      sb.append(" 		   B.EDATE,		                                                             \n");
                      sb.append(" 		   A.LIVE_FLAG,		                                                         \n");
                      sb.append(" 		   C.CODE_NAME AS LIVE_NM,                                                   \n");
                      sb.append("          '0' COMP_CHECK    										                 \n");
                      sb.append("    FROM TMULTIFRAMESCHE A,                                                         \n");
                      sb.append("         TPROGRAM   B,                                                              \n");
                      sb.append("         TCODE   C                                                               \n");
                      sb.append("   WHERE A.PROG_CODE = B.PROG_CODE                                                  \n");
                      sb.append("     AND A.LIVE_FLAG = C.CODE_MGROUP  \n");
                      sb.append("     AND C.CODE_LGROUP = 'C098' \n");
                      sb.append("     AND A.BD_DATE = TO_DATE('"+retrieve.getString("BROAD_DATE")+"', 'YYYY/MM/DD')  \n");
                      sb.append("     AND A.MEDIA_CODE LIKE '"+retrieve.getString("CHANNEL")+"' || '%' \n");
                      sb.append(retrieve.getString("MOD_QUERY"));
                      sb.append("   ORDER BY A.BD_BTIME                                                              \n");
                      return sb.toString();
                  }

                     /**
                      * 모델조회
                      * @param retrieve
                      * @return
                      * @throws StoreException
                      */
                     private String makeSqlModelCodeName(RetrieveModel retrieve) {
                    	StringBuffer sb = new StringBuffer();

						sb.append(" SELECT MM.MODEL_NO,                                						  \n");
						sb.append("	 MM.MODEL_NAME,                                    						  \n");
						sb.append("	 MM.ENTP_CODE,                                     						  \n");
						sb.append("	 EP.ENTP_NAME,                                     						  \n");
						sb.append("	 TCODE_NAME('B023',COUNTRY_CODE) AS COUNTRY_NAME   						  \n");
						sb.append(" FROM   TMODELMASTER  MM,                           						  \n");
						sb.append("        TENTERPRISE   EP                            						  \n");
						sb.append(" WHERE MM.ENTP_CODE = EP.ENTP_CODE                  						  \n");
						sb.append("  AND  MM.USE_YN = '1'			                						  \n");
						sb.append("	 AND  MM.MODEL_NO  LIKE '"+retrieve.getString("MODEL_NO")+"' || '%'       \n");
						sb.append("	 AND  MM.MODEL_NAME  LIKE '"+retrieve.getString("MODEL_NAME")+"' || '%'   \n");
						sb.append(retrieve.getString("MOD_QUERY"));
                    	return sb.toString();
                 	}
    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   flag          : 콤보박스의 조회구분( int 형으로 1부터증가처리)
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
   public RetrieveModel retrieve ( Connection con, int flag, RetrieveModel retrieve) throws StoreException{
        Statement stmt = null;
        ResultSet rset = null;
        Collection collection = new ArrayList();
        HashMap    hmSheet = null;

        String query = "";

        try {
            switch (flag)
                { case Construct.P_CODE_MGROUP  : {
                    query = makeSqlCodeMgroup(retrieve);
                    break;
                } case Construct.P_USER_ID : {
                    query = makeSqlUserId(retrieve);
                    break;
                } case Construct.P_EMPLOYEE_ID : {
                    query = makeSqlEmployeeId(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_ALL : {
                    query = makeSqlGoodsCodeAll(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_ALL : {
                    query = makeSqlGoodsNameAll(retrieve);
                    break;
                } case Construct.P_GOODS_CODE : {
                    query = makeSqlGoodsCode(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_D : {
                    query = makeSqlGoodsCodeD(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_D_UNIQUE : {
                    query = makeSqlGoodsCodeUniqueDt(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_DS : {
                    query = makeSqlGoodsCodeDS(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_DS1 : {
                    query = makeSqlGoodsCodeDS1(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_UNIT : {
                    query = makeSqlGoodsCodeUnit(retrieve);
                    break;
                } case Construct.P_GOODSDT_CODE_D : {
                    query = makeSqlGoodsDtCodeD(retrieve);
                    break;
                } case Construct.P_SGOODS_CODE_MD : {
                    query = makeSqlSGoodsCodeMd(retrieve);
                    break;
                } case Construct.P_GOODSDT_CODE : {
                    query = makeSqlGoodsDtCode(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_PRICE : {
                    query = makeSqlGoodsCodePrice(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_PRICE : {
                    query = makeSqlGoodsNamePrice(retrieve);
                    break;
                } case Construct.P_LGROUP : {
                    query = makeSqlLgroup(retrieve);
                    break;
                } case Construct.P_MGROUP : {
                    query = makeSqlMgroup(retrieve);
                    break;
                } case Construct.P_SGROUP : {
                    query = makeSqlSgroup(retrieve);
                    break;
                } case Construct.P_DGROUP : {
                    query = makeSqlDgroup(retrieve);
                    break;
                } case Construct.P_COLOR_CODE : {
                    query = makeSqlColorCode(retrieve);
                    break;
                } case Construct.P_SIZE_CODE : {
                    query = makeSqlSizeCode(retrieve);
                    break;
                } case Construct.P_PATTERN_CODE : {
                    query = makeSqlPatternCode(retrieve);
                    break;
                } case Construct.P_FORM_CODE : {
                    query = makeSqlFormCode(retrieve);
                    break;
                } case Construct.P_ENTP_CODE : {
                    query = makeSqlEntpCode(retrieve);
                    break;
                } case Construct.P_ENTP_MAN_SEQ : {
                    query = makeSqlEntpManSeq(retrieve);
                    break;
                } case Construct.P_MD_CODE : {
                    query = makeSqlMdCode(retrieve);
                    break;
                } case Construct.P_DM_CODE : {
                    query = makeSqlDmCode(retrieve);
                    break;
                } case Construct.P_DESCRIBE_BASE_CODE : {
                    query = makeSqlDescribeBaseCode(retrieve);
                    break;
                } case Construct.P_POST_NO : {
                    query = makeSqlPostNo(retrieve);
                    break;
                } case Construct.P_DONG_NAME : {
                    query = makeSqlDongName(retrieve);
                    break;
                } case Construct.P_AREA_GB : {
                    query = makeSqlAreaGb(retrieve);
                    break;
                } case Construct.P_POST_AREA : {
                    query = makeSqlPostArea(retrieve);
                    break;
                } case Construct.P_MAKECOMP_CODE : {
                    query = makeSqlMakeCompCode(retrieve);
                    break;
                } case Construct.P_MEDIA_CODE : {
                    query = makeSqlMediaCode(retrieve);
                    break;
                } case Construct.P_RACK_CODE : {
                    query = makeSqlRackCode(retrieve);
                    break;
                } case Construct.P_RACK_CODE_GOODS : {
                    query = makeSqlRackCodeGoods(retrieve);
                    break;
                } case Construct.P_RACK_CODE_GOODS_GRADE : {
                    query = makeSqlRackCodeGrade(retrieve);
                    break;
                } case Construct.P_RACK_CODE_FIX : {
                    query = makeSqlRackCodeFix(retrieve);
                    break;
                } case Construct.P_RACK_CODE_ETC : {
                    query = makeSqlRackCodeEtc(retrieve);
                    break;
                } case Construct.P_RACK_CODE_NOT_REG : {
                    query = makeSqlRackCodeNotReg(retrieve);
                    break;
                }case Construct.P_CHECK_CODE : {
                    query = makeSqlCheckCode(retrieve);
                    break;
                } case Construct.P_QC_LGROUP : {
                    query = makeSqlQcLgroup(retrieve);
                    break;
                } case Construct.P_QC_MGROUP : {
                    query = makeSqlQcMgroup(retrieve);
                    break;
                } case Construct.P_PROMO_NO : {
                    query = makeSqlPromoNo(retrieve);
                    break;
                } case Construct.P_ORIGIN_CODE : {
                    query = makeSqlOriginCode(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_DESCRIBE : {
                    query = makeSqlGoodsCodeDescribe(retrieve);
                    break;
                } case Construct.P_SLIP_SEARCH : {
                    query = makeSqlSlipSearch(retrieve);
                    break;
                } case Construct.P_CARD_CODE : {
                    query = makeSqlCardCode(retrieve);
                    break;
                } case Construct.P_GOODSNAME_CODE : {
                    query = makeSqlGoodsNameCode(retrieve);
                    break;
                } case Construct.P_WH_CODE : {
                    query = makeSqlWhCode(retrieve);
                    break;
                } case Construct.P_COUNSEL_LGROUP_CODE : {
                    query = makeSqlCounselLgroupCode(retrieve);
                    break;
                } case Construct.P_COUNSEL_MGROUP_CODE : {
                    query = makeSqlCounselMgroupCode(retrieve);
                    break;
                } case Construct.P_OB_SEQ : {
                    query = makeSqlObSeq(retrieve);
                    break;
                } case Construct.P_BRAND_CODE : {
                    query = makeSqlBrandCode(retrieve);
                    break;
                } case Construct.P_BRAND_NAME : {
                    query = makeSqlBrandName(retrieve);
                    break;
                } case Construct.P_BOARD_CODE : {
                    query = makeSqlBoardCode(retrieve);
                    break;
                } case Construct.P_CATEGORY_CODE : {
                    query = makeSqlCategoryCode(retrieve);
                    break;
                } case Construct.P_SNO : {
                    query = makeSqlSNo(retrieve);
                    break;
                } case Construct.P_SHOWHOST_ID : {
                    query = makeSqlShowHostId(retrieve);
                    break;
                } case Construct.P_PROG_CODE_REAL : {
                    query = makeSqlProgCodeReal(retrieve);
                    break;
                } case Construct.P_MD_CODE_USER : {
                    query = makeSqlMdCodeUser(retrieve);
                    break;
                } case Construct.P_LINK_GOODS_CODE : {
                    query = makeSqlLinkGoodsCode(retrieve);
                    break;
                } case Construct.P_WITH_CODE : {
                    query = makeSqlWithCode(retrieve);
                    break;
                } case Construct.P_SALE_CODE : {
                    query = makeSqlSaleCode(retrieve);
                    break;
                } case Construct.P_EVENT_NO : {
                    query = makeSqlEventNo(retrieve);
                    break;
                } case Construct.P_FAQ_KINDS : {
                    query = makeSqlFaqKinds(retrieve);
                    break;
                } case Construct.P_FAQ_CODE : {
                    query = makeSqlFaqCode(retrieve);
                    break;
                } case Construct.P_BOARD_LKINDS : {
                    query = makeSqlBoardLkinds(retrieve);
                    break;
                } case Construct.P_BOARD_MKINDS : {
                    query = makeSqlBoardMkinds(retrieve);
                    break;
                } case Construct.P_RESEARCH_NO : {
                    query = makeSqlResearchNo(retrieve);
                    break;
                } case Construct.P_RECEIPTSBANK_CODE : {
                    query = makeSqlReceiptsBank(retrieve);
                    break;
                } case Construct.P_BANK_CODE : {
                    query = makeSqlBank(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_GIFT : {
                    query = makeSqlGoodsCodeGift(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_GIFT : {
                    query = makeSqlGoodsNameGift(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_DESCRIBE_COPY : {
                    query = makeSqlGoodsCodeDescribeCopy(retrieve);
                    break;
                } case Construct.P_CUST_CARD : {
                    query = makeSqlCustCard(retrieve);
                    break;
                } case Construct.P_CUST_RECEIPTSBANK : {
                    query = makeSqlCustReceiptsbank(retrieve);
                    break;
                } case Construct.P_CUST_BANK : {
                    query = makeSqlCustBank(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_BALJU : {
                    query = makeSqlGoodsCodeBalju(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_ENTPTAKEOUT : {
                    query = makeSqlGoodsCodeEntpTakeout(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_ETCINOUT : {
                    query = makeSqlGoodsCodeEtcInout(retrieve);
                    break;
                } case Construct.P_GOODS_COPY_CODE : {
                    query = makeSqlGoodsCopyCode(retrieve);
                    break;
                } case Construct.P_RACK_CONTROL_NAME : {
                    query = makeSqlGoodsControlName(retrieve);
                    break;
                } case Construct.P_ENTP_PURCHASE_NO : {
                    query = makeSqlEntpPurchaseNo(retrieve);
                    break;
                } case Construct.P_DESCRIBE_CODE : {
                    query = makeSqlDescribeCode(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_UNIT : {
                    query = makeSqlGoodsNameUnit(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_DS : {
                    query = makeSqlGoodsNameDS(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_DS1 : {
                	query = makeSqlGoodsNameDS1(retrieve);
                	break;
                }case Construct.P_GOODS_NAME_D : {
                    query = makeSqlGoodsNameD(retrieve);
                    break;
                } case Construct.P_GOODS_NAME : {
                    query = makeSqlGoodsName(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_ETCINOUT : {
                    query = makeSqlGoodsNameEtcInout(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_BALJU : {
                    query = makeSqlGoodsNameBalju(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_ENTPTAKEOUT : {
                    query = makeSqlGoodsNameEntpTakeout(retrieve);
                    break;
                } case Construct.P_LOTTERY_PROMO_NO : {
                    query = makeSqlLotteryPromoNo(retrieve);
                    break;
                } case Construct.P_ORDER_MEDIA : {
                    query = makeSqlOrderMedia(retrieve);
                    break;
                } case Construct.P_SEG_CODE : {
                    query = makeSqlSegCode(retrieve);
                    break;
                } case Construct.P_CODE_LGROUP : {
                    query = makeSqlCodeLgroup(retrieve);
                    break;
                } case Construct.P_COUNSEL_GROUP : {
                    query = makeSqlCounselGroup(retrieve);
                    break;
                } case Construct.P_CODE_NAME : {
                    query = makeSqlCodeName(retrieve);
                    break;
                } case Construct.P_MASTER_CODE : {
                	query = makeSqlMasterCode(retrieve);
                    break;
                } case Construct.P_MASTER_NAME : {
                	query = makeSqlMasterName(retrieve);
                    break;
                }case Construct.P_GOODS_CODE_TREAT : {
                    query = makeSqlGoodsCodeTreat(retrieve);
                    break;
                }case Construct.P_GOODS_NAME_TREAT : {
                    query = makeSqlGoodsNameTreat(retrieve);
                    break;
                }case Construct.P_SAMPLE_NO : {
                    query = makeSqlSampleNo(retrieve);
                    break;
                }case Construct.P_MENU_PROGRAM : {
                    query = makeSqlMenuprogram(retrieve);
                    break;
                }case Construct.P_MMENU : {
                    query = makeSqlMmenu(retrieve);
                    break;
                }case Construct.P_SEND_SEQ : {
                    query = makeSqlSendSeq(retrieve);
                    break;
                } case Construct.P_SKINTEST : {
                    query = makeSqlSkinTest(retrieve);
                    break;
                } case Construct.P_RESEARCH : {
                    query = makeSqlResearch(retrieve);
                    break;
                } case Construct.P_GOODS_POLL : {
                    query = makeSqlGoodsPoll(retrieve);
                    break;
                }case Construct.P_THEMEGOODSBOARD_NO : {
                    query = makeSqlBeautyBoardCode(retrieve);
                    break;
                }case Construct.P_MAGAZINE_NO : {
                    query = makeSqlMagazineNo(retrieve);
                    break;
                } case Construct.P_SHOP_CODE : {
                    query = makeSqlShop(retrieve);
                    break;
                } case Construct.P_PROGRAM_ID : {
                    query = makeSqlProgram(retrieve);
                    break;
                } case Construct.P_EVENT_M : {
                	query = makeSqlEventM(retrieve);
                    break;
                } case Construct.P_FRAME : {
                	query = makeSqlFrame(retrieve);
                    break;
                } case Construct.P_MULTI_FRAME : {
                	query = makeSqlMultiFrame(retrieve);
                    break;
                } case Construct.P_RECEIPTSBANK_NAME : {
                    query = makeSqlReceiptsBankName(retrieve);
                    break;
                } case Construct.P_CODE_MGROUP_MULTI : {
                    query = makeSqlCodeMgroupMulti(retrieve);
                    break;
                } case Construct.P_MEDIA_CODE_MULTI : {
                    query = makeSqlMediaCodeMulti(retrieve);
                    break;
                } case Construct.P_BROAD_SCHEDULE_COPY : {
                    query = makeSqlBroadScheduleCopy(retrieve);
                    break;
                } case Construct.P_DAILY_SALE : {
                    query = makeSqlDailySale(retrieve);
                    break;
                } case Construct.P_TAPE_CODE : {
                    query = makeSqlTapeCode(retrieve);
                    break;
                } case Construct.P_TAPE_NAME : {
                    query = makeSqlTapeName(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_MD : {
                    query = makeSqlGoodsCodeMd(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_MD: {
                    query = makeSqlGoodsNameMd(retrieve);
                    break;
                } case Construct.P_BOARD_KINDS : {
                    query = makeSqlBoardKinds(retrieve);
                    break;
                } case Construct.P_SO_CODE : {
                    query = makeSqlSoCode(retrieve);
                    break;
                } case Construct.P_SO_POST_NO : {
                    query = makeSqlSoPostNo(retrieve);
                    break;
                } case Construct.P_TAPE_CODE_GOODS : {
                    query = makeSqlTapeCodeGoods(retrieve);
                    break;
                } case Construct.P_TAPE_NAME_GOODS : {
                    query = makeSqlTapeNameGoods(retrieve);
                    break;
                } case Construct.P_STATE_CODE : {
                    query = makeSqlStateCode(retrieve);
                    break;
                } case Construct.P_STATE_NAME : {
                    query = makeSqlStateName(retrieve);
                    break;
                } case Construct.P_COLOR_PATTERN_MULTI : {
                    query = makeSqlColorPattern(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_COLOR_CODE : {
                    query = makeSqlCspfGroupColorCode(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_SIZE_CODE : {
                    query = makeSqlCspfGroupSizeCode(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_PATTERN_CODE : {
                    query = makeSqlCspfGroupPatternCode(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_FORM_CODE : {
                    query = makeSqlCspfGroupFormCode(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_CODE : {
                    query = makeSqlCspfGroupCode(retrieve);
                    break;
                } case Construct.P_CSPF_GROUP_AUTO : {
                    query = makeSqlCspfGroupAutoSel(retrieve);
                    break;
                } case Construct.P_USER_GROUP_ID : {
                    query = makeSqlUserGroupId(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_WAREHOUSINGINPUT : {
                    query = makeSqlGoodsCodeWarehousingInput(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_EOUT : {
                    query = makeSqlGoodsCodeEoutQty(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_EOUT : {
                    query = makeSqlGoodsNameEoutQty(retrieve);
                    break;
                } case Construct.P_SETTLEINFO : {
                    query = makeSqlSettleino(retrieve);
                    break;
                } case Construct.P_TEMPLATE_CODE : {
                    query = makeSqlTemplateCode(retrieve);
                    break;
                } case Construct.P_PLANCLASS_CODE : {
                    query = makeSqlPlanclassCode(retrieve);
                    break;
                } case Construct.P_PLAN_CODE : {
                    query = makeSqlPlanCode(retrieve);
                    break;
                } case Construct.P_GOODS_CODE_SCM : {
                	query = makeSqlGoodsCodeScm(retrieve);
                    break;
                } case Construct.P_GOODS_NAME_SCM : {
                    query = makeSqlGoodsNameScm(retrieve);
                    break;
                } case Construct.P_BD_PROGRAM_SELECT : {
                	query = makeSqlBdProgramSelect(retrieve);
                	break;
                } case Construct.P_IN_INSPECT : {
                	query = makeSqlInInspect(retrieve);
                	break;
                }case Construct.P_MULTI_BROAD_SCHEDULE_COPY : {
                	query = makeSqlMultiBroadScheduleCopy(retrieve);
                	break;
                } case Construct.P_MODEL_CODE_NAME : {
                	query = makeSqlModelCodeName(retrieve);
                	break;
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("\n" + query);
            }

            stmt = con.createStatement();
            rset  = stmt.executeQuery(query);

            while (rset!=null && rset.next()){
                hmSheet = new HashMap();
                hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
                collection.add(hmSheet);
            }
            retrieve.put("RESULT",(HashMap[]) collection.toArray(new HashMap[0]));

        }catch(SQLException se){
            log.error("[PopupSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PopupSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return retrieve;
    }

}