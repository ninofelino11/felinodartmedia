
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
import com.cware.back.entity.table.Tarskongje;
import com.cware.back.entity.table.Tgoods;
import com.cware.back.entity.table.Tgoodsdt;
import com.cware.back.entity.table.Tgoodssign;
import com.cware.back.entity.table.Tsetgoods;
import com.cware.back.entity.table.Tsetgoodsdt;

/**
 * Register promotion Service class
 *
 * @version 1.0, 2006/06/21
 */
public class SetgoodsSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public SetgoodsSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Setgoods List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlList( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT GOODS_CODE,        \n ");
        sb.append("         GOODS_NAME         \n ");
        sb.append("    FROM TGOODS             \n ");
        sb.append("   WHERE SET_YN  = '1'      \n ");
        sb.append("     AND GOODS_CODE LIKE ? || '%'  \n ");
        sb.append("   ORDER BY LPAD(GOODS_CODE, 10, '0') DESC  \n ");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Setgoods
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlGoods( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.GOODS_CODE,          \n ");
        sb.append("         A.GOODS_NAME,          \n ");
        sb.append("         A.MD_CODE,             \n ");
        sb.append("         C.MD_NAME,             \n ");
        sb.append("         A.LGROUP,              \n ");
        sb.append("         B.LGROUP_NAME,         \n ");
        sb.append("         A.MGROUP,              \n ");
        sb.append("         B.MGROUP_NAME,         \n ");
        sb.append("         A.SGROUP,              \n ");
        sb.append("         B.SGROUP_NAME,         \n ");
        sb.append("         A.DGROUP,              \n ");
        sb.append("         B.DGROUP_NAME,         \n ");
        sb.append("         A.DELY_TYPE,           \n ");
        sb.append("         A.WH_CODE,             \n ");
        sb.append("         A.SIGN_GB,             \n ");
        sb.append("         D.SALE_PRICE,          \n ");
        sb.append("         D.SAVEAMT_RATE,        \n ");
        sb.append("         D.SAVEAMT,             \n ");
        sb.append("         TO_CHAR(D.APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,\n ");
        sb.append("         A.NOREST_ALLOT_YN,     \n ");
        sb.append("         A.POST_YN,             \n ");
        sb.append("         A.EXCH_YN,             \n ");
        sb.append("         A.RETURN_YN,           \n ");
        sb.append("         A.ENTP_CODE,           \n ");
        sb.append("         T.ENTP_NAME,           \n ");
        sb.append("         A.ENTP_MAN_SEQ,        \n ");
        sb.append("         A.MAKECO_CODE,         \n ");
        sb.append("         A.KEYWORD,             \n ");
        sb.append("         A.BUY_MED,             \n ");
        sb.append("         A.TAX_YN,              \n ");
        sb.append("         A.VAT_RATE,            \n ");
        sb.append("         A.ORIGIN_CODE,         \n ");
        sb.append("         A.HANDLE_CODE,         \n ");
        sb.append("         A.MIXPACK_YN,          \n ");
        sb.append("         A.SET_YN,              \n ");
        sb.append("         A.GIFT_YN,             \n ");
        sb.append("         A.PAY_YN,              \n ");
        sb.append("         A.GIFT_RETURN_YN,      \n ");
        sb.append("         A.OUT_STOCK_YN,        \n ");
        sb.append("         A.ORDER_MIN_QTY,       \n ");
        sb.append("         A.STOCK_CHK_PLACE,     \n ");
        sb.append("         A.SALE_GB,             \n ");
        sb.append("         A.FIRST_BROAD_DATE,    \n ");
        sb.append("         A.INSERT_DATE,         \n ");
        sb.append("         A.INSERT_ID,           \n ");
        sb.append("         A.MODIFY_DATE,         \n ");
        sb.append("         A.MODIFY_ID,           \n ");
        sb.append("         A.SET_GOODS_YN,        \n ");
        sb.append("         A.ORDER_MEDIA_ALL_YN,  \n ");
        sb.append("         A.ORDER_MEDIA,         \n ");
        sb.append("         FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME, \n");
        sb.append("         A.ARS_NAME,            \n ");
        sb.append("         A.SQC_GB,              \n ");
        sb.append("         A.QC_LGROUP,           \n ");
//        sb.append("         E.QC_LGROUP_NAME,      \n ");
        sb.append("         '' QC_LGROUP_NAME,      \n ");
        sb.append("         A.QC_MGROUP,           \n ");
//        sb.append("         E.QC_MGROUP_NAME,      \n ");
        sb.append("         '' QC_MGROUP_NAME,      \n ");
        sb.append("         A.NOREST_ALLOT_MONTHS, \n ");
        sb.append("         A.COST_VAT_RATE,       \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 1, 1)  AS NOREST_1,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 2, 1)  AS NOREST_2,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 3, 1)  AS NOREST_3,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 4, 1)  AS NOREST_4,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 5, 1)  AS NOREST_5,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 6, 1)  AS NOREST_6,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 7, 1)  AS NOREST_7,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 8, 1)  AS NOREST_8,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 9, 1)  AS NOREST_9,  \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 10, 1) AS NOREST_10, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 11, 1) AS NOREST_11, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 12, 1) AS NOREST_12, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 13, 1) AS NOREST_13, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 14, 1) AS NOREST_14, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 15, 1) AS NOREST_15, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 16, 1) AS NOREST_16, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 17, 1) AS NOREST_17, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 18, 1) AS NOREST_18, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 19, 1) AS NOREST_19, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 20, 1) AS NOREST_20, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 21, 1) AS NOREST_21, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 22, 1) AS NOREST_22, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 23, 1) AS NOREST_23, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 24, 1) AS NOREST_24, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 25, 1) AS NOREST_25, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 26, 1) AS NOREST_26, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 27, 1) AS NOREST_27, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 28, 1) AS NOREST_28, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 29, 1) AS NOREST_29, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 30, 1) AS NOREST_30, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 31, 1) AS NOREST_31, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 32, 1) AS NOREST_32, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 33, 1) AS NOREST_33, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 34, 1) AS NOREST_34, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 35, 1) AS NOREST_35, \n ");
        sb.append("         SUBSTR(A.NOREST_ALLOT_MONTHS, 36, 1) AS NOREST_36, \n ");
        sb.append("         A.CUST_DC_YN,                                      \n ");
        sb.append("         A.MASTER_CODE,                                     \n ");
        sb.append("         F.GOODS_NAME AS MASTER_NAME,                       \n ");
        sb.append("         A.COMMENT_CNT,                                     \n ");
        sb.append("         TO_CHAR(A.SALE_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS SALE_START_DATE   \n ");
        sb.append("    FROM TGOODS A,                                          \n ");
        sb.append("         TGOODSKINDS B,                                     \n ");
        sb.append("         TMD C,                                             \n ");
        sb.append("         TGOODSSIGN D,                                      \n ");
//        sb.append("         TQCKINDS E,                                        \n ");
        sb.append("         TGOODS F,                                          \n ");
        sb.append("         TGOODSDT G,                                        \n ");
        sb.append("         TENTERPRISE T                                      \n ");
        sb.append("    WHERE A.LGROUP = B.LGROUP                               \n ");
        sb.append("      AND A.MGROUP = B.MGROUP                               \n ");
        sb.append("      AND A.SGROUP = B.SGROUP                               \n ");
        sb.append("      AND A.DGROUP = B.DGROUP                               \n ");
        sb.append("      AND A.MD_CODE = C.MD_CODE                             \n ");
        sb.append("      AND A.GOODS_CODE = D.GOODS_CODE                       \n ");
//        sb.append("      AND A.QC_LGROUP = E.QC_LGROUP(+)                      \n ");
//        sb.append("      AND A.QC_MGROUP = E.QC_MGROUP(+)                      \n ");
        sb.append("      AND A.MASTER_CODE = F.GOODS_CODE(+)                   \n ");
        sb.append("      AND A.SET_YN = '1'                                    \n ");
        sb.append("      AND D.SIGN_SEQ = ( SELECT MAX(E.SIGN_SEQ)             \n ");
        sb.append("                           FROM TGOODSSIGN E                \n ");
        sb.append("                          WHERE A.GOODS_CODE = E.GOODS_CODE )\n ");
        sb.append("      AND A.GOODS_CODE = ?                                   \n ");
        sb.append("      AND A.GOODS_CODE = G.GOODS_CODE                       \n ");
        sb.append("      AND G.GOODSDT_CODE = '001'                            \n ");
        sb.append("      AND A.ENTP_CODE = T.ENTP_CODE                         \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Setgoods1
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSet( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();


        sb.append("  SELECT A.GOODS_CODE,    \n ");
        sb.append("         A.IN_GOODS_SEQ,  \n ");
        sb.append("         A.IN_GOODS_CODE, \n ");
        sb.append("         B.GOODS_NAME,    \n ");
        sb.append("         (C.BUY_COST + C.BUY_VAT) BUY_PRICE,   \n ");
        sb.append("         A.SALE_PRICE,    \n ");
        sb.append("         A.SET_QTY,       \n ");
        sb.append("         A.GOODSDT_FIX_YN,\n ");
        sb.append("         A.INSERT_DATE,   \n ");
        sb.append("         A.INSERT_ID,     \n ");
        sb.append("         A.MODIFY_DATE,   \n ");
        sb.append("         A.MODIFY_ID,     \n ");
        sb.append("         B.DELY_TYPE,     \n ");
        sb.append("         (A.IN_GOODS_SEQ || A.IN_GOODS_CODE) GOODS_SEQ_GOODS_CODE      \n ");
        sb.append("    FROM TSETGOODS A,\n ");
        sb.append("         TGOODS B,        \n ");
        sb.append("         TGOODSPRICE C    \n ");
        sb.append("   WHERE A.IN_GOODS_CODE = B.GOODS_CODE  \n ");
        sb.append("     AND A.IN_GOODS_CODE = C.GOODS_CODE  \n ");
        sb.append("     AND C.APPLY_DATE = ( SELECT MAX(D.APPLY_DATE)              \n ");
        sb.append("                            FROM TGOODSPRICE D                  \n ");
        sb.append("                           WHERE A.IN_GOODS_CODE = D.GOODS_CODE \n ");
        sb.append("                             AND D.APPLY_DATE <= SYSDATE )      \n ");
        sb.append("     AND A.GOODS_CODE = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TSetgoodsDT
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSetDt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.GOODS_CODE,      \n ");
        sb.append("         A.IN_GOODS_CODE,   \n ");
        sb.append("         A.IN_GOODS_SEQ,    \n ");
        sb.append("         A.IN_GOODSDT_CODE, \n ");
        sb.append("         A.IN_GOODSDT_SEQ,  \n ");
        sb.append("         B.GOODSDT_INFO,    \n ");
        sb.append("         A.INSERT_DATE,     \n ");
        sb.append("         A.INSERT_ID,       \n ");
        sb.append("         A.MODIFY_DATE,     \n ");
        sb.append("         A.MODIFY_ID,       \n ");
        sb.append("         (A.IN_GOODS_SEQ || A.IN_GOODS_CODE) GOODS_SEQ_GOODS_CODE,      \n ");
        sb.append("         '1' AS SELECT_YN      \n ");
        sb.append("    FROM TSETGOODSDT A,     \n ");
        sb.append("         TGOODSDT B         \n ");
        sb.append("   WHERE A.IN_GOODS_CODE = B.GOODS_CODE     \n ");
        sb.append("     AND A.IN_GOODSDT_CODE = B.GOODSDT_CODE \n ");
        sb.append("     AND A.GOODS_CODE = ?   \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert Setgoods )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */

    private String makeSqlInsert(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TGOODS (           \n ");
        sb.append("          GOODS_CODE,            \n ");
        sb.append("          GOODS_NAME,            \n ");
        sb.append("          LGROUP,                \n ");
        sb.append("          MGROUP,                \n ");
        sb.append("          SGROUP,                \n ");
        sb.append("          DGROUP,                \n ");
        sb.append("          QC_LGROUP,             \n ");
        sb.append("          QC_MGROUP,             \n ");
        sb.append("          SALE_GB,               \n ");
        sb.append("          SIGN_GB,               \n ");
        sb.append("          ENTP_CODE,             \n ");
        sb.append("          ENTP_MAN_SEQ,          \n ");
        sb.append("          ACCOUNT_GB,            \n ");
        sb.append("          MD_CODE,               \n ");
        sb.append("          KEYWORD,               \n ");
        sb.append("          BUY_MED,               \n ");
        sb.append("          DELY_TYPE,             \n ");
        sb.append("          WH_CODE,               \n ");
        sb.append("          POST_YN,               \n ");
        sb.append("          TAX_YN,                \n ");
        sb.append("          VAT_RATE,              \n ");
        sb.append("          COST_TAX_YN,           \n ");
        sb.append("          COST_VAT_RATE,         \n ");
        sb.append("          MAKECO_CODE,           \n ");
        sb.append("          ORIGIN_CODE,           \n ");
        sb.append("          BRAND_CODE,            \n ");
        sb.append("          HANDLE_CODE,           \n ");
        sb.append("          MIXPACK_YN,            \n ");
        sb.append("          PACK_BOX,              \n ");
        sb.append("          SQC_YN,                \n ");
        sb.append("          SQC_GB,                \n ");
        sb.append("          SET_YN,                \n ");
        sb.append("          SET_GOODS_YN,          \n ");
        sb.append("          GIFT_YN,               \n ");
        sb.append("          PAY_YN,                \n ");
        sb.append("          GIFT_RETURN_YN,        \n ");
        sb.append("          EXCH_YN,               \n ");
        sb.append("          RETURN_YN,             \n ");
        sb.append("          AS_YN,                 \n ");
        sb.append("          OUT_STOCK_YN,          \n ");
        sb.append("          ORDER_MIN_QTY,         \n ");
        sb.append("          STOCK_CHK_PLACE,       \n ");
        sb.append("          FIRST_BROAD_DATE,      \n ");
        sb.append("          ORDER_MEDIA_ALL_YN,    \n ");
        sb.append("          ORDER_MEDIA,           \n ");
        sb.append("          ARS_NAME,              \n ");
        sb.append("          PARENT_GOODS_CODE,     \n ");
        sb.append("          NOREST_ALLOT_YN,       \n ");
        sb.append("          NOREST_ALLOT_MONTHS,   \n ");
        sb.append("          CUST_DC_YN,            \n ");
        sb.append("          MASTER_CODE,           \n ");
        sb.append("          COMMENT_CNT,           \n ");
        sb.append("          SALE_START_DATE,       \n ");
        sb.append("          INSERT_DATE,           \n ");
        sb.append("          INSERT_ID,             \n ");
        sb.append("          MODIFY_DATE,           \n ");
        sb.append("          MODIFY_ID )            \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?) \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update goods )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */
    private String makeSqlUpdate(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE TGOODS SET              \n ");
        sb.append("       GOODS_NAME          = ?,  \n ");
        sb.append("       LGROUP              = ?,  \n ");
        sb.append("       MGROUP              = ?,  \n ");
        sb.append("       SGROUP              = ?,  \n ");
        sb.append("       DGROUP              = ?,  \n ");
        sb.append("       QC_LGROUP           = ?,  \n ");
        sb.append("       QC_MGROUP           = ?,  \n ");
        sb.append("       SALE_GB             = ?,  \n ");
        sb.append("       SIGN_GB             = ?,  \n ");
        sb.append("       ENTP_CODE           = ?,  \n ");
        sb.append("       ENTP_MAN_SEQ        = ?,  \n ");
        sb.append("       ACCOUNT_GB          = ?,  \n ");
        sb.append("       MD_CODE             = ?,  \n ");
        sb.append("       KEYWORD             = ?,  \n ");
        sb.append("       BUY_MED             = ?,  \n ");
        sb.append("       DELY_TYPE           = ?,  \n ");
        sb.append("       WH_CODE             = ?,  \n ");
        sb.append("       POST_YN             = ?,  \n ");
        sb.append("       TAX_YN              = ?,  \n ");
        sb.append("       VAT_RATE            = ?,  \n ");
        sb.append("       COST_TAX_YN         = ?,  \n ");
        sb.append("       COST_VAT_RATE       = ?,  \n ");
        sb.append("       MAKECO_CODE         = ?,  \n ");
        sb.append("       ORIGIN_CODE         = ?,  \n ");
        sb.append("       BRAND_CODE          = ?,  \n ");
        sb.append("       HANDLE_CODE         = ?,  \n ");
        sb.append("       MIXPACK_YN          = ?,  \n ");
        sb.append("       PACK_BOX            = ?,  \n ");
        sb.append("       SQC_YN              = ?,  \n ");
        sb.append("       SQC_GB              = ?,  \n ");
        sb.append("       SET_YN              = ?,  \n ");
        sb.append("       SET_GOODS_YN        = ?,  \n ");
        sb.append("       GIFT_YN             = ?,  \n ");
        sb.append("       PAY_YN              = ?,  \n ");
        sb.append("       GIFT_RETURN_YN      = ?,  \n ");
        sb.append("       EXCH_YN             = ?,  \n ");
        sb.append("       RETURN_YN           = ?,  \n ");
        sb.append("       AS_YN               = ?,  \n ");
        sb.append("       OUT_STOCK_YN        = ?,  \n ");
        sb.append("       ORDER_MIN_QTY       = ?,  \n ");
        sb.append("       STOCK_CHK_PLACE     = ?,  \n ");
        sb.append("       FIRST_BROAD_DATE    = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("       ORDER_MEDIA_ALL_YN  = ?,  \n ");
        sb.append("       ORDER_MEDIA         = ?,  \n ");
        sb.append("       ARS_NAME            = ?,  \n ");
        sb.append("       PARENT_GOODS_CODE   = ?,  \n ");
        sb.append("       NOREST_ALLOT_YN     = ?,  \n ");
        sb.append("       NOREST_ALLOT_MONTHS = ?,  \n ");
        sb.append("       CUST_DC_YN          = ?,  \n ");
        sb.append("       MASTER_CODE         = UPPER(?),  \n ");
        sb.append("       SALE_START_DATE     = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("       MODIFY_DATE         = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("       MODIFY_ID           = ? \n ");
        sb.append(" WHERE GOODS_CODE          = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    /**
     * <PRE>
     * Desc : Make SQL ( Update goodsdt, )
     * </PRE>
     * @param   Ttgoodsdt
     * @return  String
     */
     private String makeSqlUpdate(Tgoodsdt tgoodsdt) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" UPDATE TGOODSDT SET              \n ");
         sb.append("       GOODS_CODE              = UPPER(?),  \n ");
         sb.append("       GOODSDT_CODE            = ?,  \n ");
         sb.append("       GOODS_NAME              = ?,  \n ");
         sb.append("       COLOR_CODE              = ?,  \n ");
         sb.append("       COLOR_NAME              = ?,  \n ");
         sb.append("       SIZE_CODE               = ?,  \n ");
         sb.append("       SIZE_NAME               = ?,  \n ");
         sb.append("       PATTERN_CODE            = ?,  \n ");
         sb.append("       PATTERN_NAME            = ?,  \n ");
         sb.append("       FORM_CODE               = ?,  \n ");
         sb.append("       FORM_NAME               = ?,  \n ");
         sb.append("       OTHER_TEXT              = ?,  \n ");
         sb.append("       GOODSDT_INFO            = ?,  \n ");
         sb.append("       SALE_GB                 = ?,  \n ");
         sb.append("       BARCODE                 = ?,  \n ");
         sb.append("       MODIFY_DATE             = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
         sb.append("       MODIFY_ID               = ?  \n ");
         sb.append(" WHERE GOODS_CODE              = ?  \n ");
         sb.append("   AND GOODSDT_CODE            = ?  \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert Setgoods )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */
    private final String insertSqlSetgoods =  " INSERT INTO TSETGOODS ( \n "
                                                 +"        GOODS_CODE,     \n "
                                                 +"        IN_GOODS_SEQ,   \n "
                                                 +"        IN_GOODS_CODE,  \n "
                                                 +"        SALE_PRICE,     \n "
                                                 +"        SET_QTY,        \n "
                                                 +"        GOODSDT_FIX_YN, \n "
                                                 +"        INSERT_DATE,    \n "
                                                 +"        INSERT_ID,      \n "
                                                 +"        MODIFY_DATE,    \n "
                                                 +"        MODIFY_ID)      \n "
                                                 +"VALUES ( \n "
                                                 +"        UPPER(?), \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?) \n ";

    private int insertSqlLogSetgoods =  0;

    private String makeSqlInsert(Tsetgoods tsetgoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogSetgoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlSetgoods);
            }
            insertSqlLogSetgoods = 1;
        }
        return insertSqlSetgoods;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( insert TSetgoodsDT )
    * </PRE>
    * @param   TSetgoodsdt
    * @return  String
    */
    private final String insertSqlSetgoodsdt =  " INSERT INTO TSETGOODSDT ( \n "
                                                 +"        GOODS_CODE,      \n "
                                                 +"        IN_GOODS_CODE,   \n "
                                                 +"        IN_GOODSDT_CODE, \n "
                                                 +"        IN_GOODS_SEQ,    \n "
                                                 +"        IN_GOODSDT_SEQ,  \n "
                                                 +"        INSERT_DATE,     \n "
                                                 +"        INSERT_ID,       \n "
                                                 +"        MODIFY_DATE,     \n "
                                                 +"        MODIFY_ID)       \n "
                                                 +"VALUES ( \n "
                                                 +"        UPPER(?), \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?) \n ";

    private int insertSqlLogSetgoodsdt =  0;

    private String makeSqlInsert(Tsetgoodsdt tsetgoodsdt) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogSetgoodsdt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlSetgoodsdt);
            }
            insertSqlLogSetgoodsdt = 1;
        }
        return insertSqlSetgoodsdt;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tgoodsdt )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */

    private String makeSqlInsert(Tgoodsdt tgoodsdt) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TGOODSDT (  \n ");
        sb.append("          GOODS_CODE,     \n ");
        sb.append("          GOODSDT_CODE,   \n ");
        sb.append("          GOODS_NAME,     \n ");
        sb.append("          COLOR_CODE,     \n ");
        sb.append("          COLOR_NAME,     \n ");
        sb.append("          SIZE_CODE,      \n ");
        sb.append("          SIZE_NAME,      \n ");
        sb.append("          PATTERN_CODE,   \n ");
        sb.append("          PATTERN_NAME,   \n ");
        sb.append("          FORM_CODE,      \n ");
        sb.append("          FORM_NAME,      \n ");
        sb.append("          OTHER_TEXT,     \n ");
        sb.append("          GOODSDT_INFO,   \n ");
        sb.append("          SALE_GB,        \n ");
        sb.append("          BARCODE,        \n ");
        sb.append("          INSERT_DATE,    \n ");
        sb.append("          INSERT_ID,      \n ");
        sb.append("          MODIFY_DATE,    \n ");
        sb.append("          MODIFY_ID )     \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?) \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tgoodssign )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */

    private String makeSqlInsert(Tgoodssign tgoodssign) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TGOODSSIGN (           \n ");
        sb.append("          GOODS_CODE,     \n ");
        sb.append("          SIGN_SEQ,       \n ");
        sb.append("          APPLY_DATE,     \n ");
        sb.append("          BUY_PRICE,      \n ");
        sb.append("          BUY_COST,       \n ");
        sb.append("          BUY_VAT,        \n ");
        sb.append("          SALE_PRICE,     \n ");
        sb.append("          SALE_VAT,       \n ");
        sb.append("          CUST_PRICE,     \n ");
        sb.append("          SAVEAMT_RATE,   \n ");
        sb.append("          SAVEAMT,        \n ");
        sb.append("          SIGN_GB,        \n ");
        sb.append("          INSERT_DATE,    \n ");
        sb.append("          INSERT_ID )     \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?) \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tarskongje )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */

    private String makeSqlInsert(Tarskongje tarskongje) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TARSKONGJE ( \n ");
        sb.append("          GOODS_CODE,   \n ");
        sb.append("          SEQ,          \n ");
        sb.append("          ARS_BDATE,    \n ");
        sb.append("          ARS_EDATE,    \n ");
        sb.append("          ENTP_AMT,     \n ");
        sb.append("          COM_AMT,      \n ");
        sb.append("          INSERT_DATE,  \n ");
        sb.append("          INSERT_ID )   \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          TRUNC(TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')), \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?) \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; List
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetList(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Goods
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetGoods(ResultSet rset)  throws Exception {
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Set
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetSet(ResultSet rset)  throws Exception {
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; SetDt
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetSetDt(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve List
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlList(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_LIST",makeSheetList(rset));

        }catch(SQLException se){
            log.error("[SetgoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Goods
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveGoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlGoods(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_GOODS",makeSheetGoods(rset));

        }catch(SQLException se){
            log.error("[SetgoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Set
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveSet(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSet(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_SET",makeSheetSet(rset));

        }catch(SQLException se){
            log.error("[SetgoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SetDt
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveSetDt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSetDt(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_SETDT",makeSheetSetDt(rset));

        }catch(SQLException se){
            log.error("[SetgoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoods
    * </PRE>
    * @param   Connection
    * @param   Setgoods
    * @return  int
    */
    public int insert(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tgoods));
            int index = 1;

            pstmt.setString(index++,tgoods.getGoods_code()         );
            pstmt.setString(index++,tgoods.getGoods_name()         );
            pstmt.setString(index++,tgoods.getLgroup()             );
            pstmt.setString(index++,tgoods.getMgroup()             );
            pstmt.setString(index++,tgoods.getSgroup()             );
            pstmt.setString(index++,tgoods.getDgroup()             );
            pstmt.setString(index++,tgoods.getQc_lgroup()          );
            pstmt.setString(index++,tgoods.getQc_mgroup()          );
            pstmt.setString(index++,tgoods.getSale_gb()            );
            pstmt.setString(index++,tgoods.getSign_gb()            );
            pstmt.setString(index++,tgoods.getEntp_code()          );
            pstmt.setString(index++,tgoods.getEntp_man_seq()       );
            pstmt.setString(index++,tgoods.getAccount_gb()         );
            pstmt.setString(index++,tgoods.getMd_code()            );
            pstmt.setString(index++,tgoods.getKeyword()            );
            pstmt.setString(index++,tgoods.getBuy_med()            );
            pstmt.setString(index++,tgoods.getDely_type()          );
            pstmt.setString(index++,tgoods.getWh_code()            );
            pstmt.setString(index++,tgoods.getPost_yn()            );
            pstmt.setString(index++,tgoods.getTax_yn()             );
            pstmt.setDouble(index++,tgoods.getVat_rate()           );
            pstmt.setString(index++,tgoods.getCost_tax_yn()        );
            pstmt.setDouble(index++,tgoods.getCost_vat_rate()      );
            pstmt.setString(index++,tgoods.getMakeco_code()        );
            pstmt.setString(index++,tgoods.getOrigin_code()        );
            pstmt.setString(index++,tgoods.getBrand_code()         );
            pstmt.setString(index++,tgoods.getHandle_code()        );
            pstmt.setString(index++,tgoods.getMixpack_yn()         );
            pstmt.setLong  (index++,tgoods.getPack_box()           );
            pstmt.setString(index++,tgoods.getSqc_yn()             );
            pstmt.setString(index++,tgoods.getSqc_gb()             );
            pstmt.setString(index++,tgoods.getSet_yn()             );
            pstmt.setString(index++,tgoods.getSet_goods_yn()       );
            pstmt.setString(index++,tgoods.getGift_yn()            );
            pstmt.setString(index++,tgoods.getPay_yn()             );
            pstmt.setString(index++,tgoods.getGift_return_yn()     );
            pstmt.setString(index++,tgoods.getExch_yn()            );
            pstmt.setString(index++,tgoods.getReturn_yn()          );
            pstmt.setString(index++,tgoods.getAs_yn()              );
            pstmt.setString(index++,tgoods.getOut_stock_yn()       );
            pstmt.setLong  (index++,tgoods.getOrder_min_qty()      );
            pstmt.setString(index++,tgoods.getStock_chk_place()    );
            pstmt.setString(index++,tgoods.getFirst_broad_date()   );
            pstmt.setString(index++,tgoods.getOrder_media_all_yn() );
            pstmt.setString(index++,tgoods.getOrder_media()        );
            pstmt.setString(index++,tgoods.getArs_name()           );
            pstmt.setString(index++,tgoods.getParent_goods_code()  );
            pstmt.setString(index++,tgoods.getNorest_allot_yn()    );
            pstmt.setString(index++,tgoods.getNorest_allot_months());
            pstmt.setString(index++,tgoods.getCust_dc_yn()         );
            pstmt.setString(index++,tgoods.getMaster_code()        );
            pstmt.setLong  (index++,tgoods.getComment_cnt()        );
            pstmt.setString(index++,tgoods.getSale_start_date()    );
            pstmt.setString(index++,tgoods.getInsert_date()        );
            pstmt.setString(index++,tgoods.getInsert_id()          );
            pstmt.setString(index++,tgoods.getModify_date()        );
            pstmt.setString(index++,tgoods.getModify_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );
            logString.append( tgoods.getGoods_name()         ); logString.append( "/" );
            logString.append( tgoods.getLgroup()             ); logString.append( "/" );
            logString.append( tgoods.getMgroup()             ); logString.append( "/" );
            logString.append( tgoods.getSgroup()             ); logString.append( "/" );
            logString.append( tgoods.getDgroup()             ); logString.append( "/" );
            logString.append( tgoods.getQc_lgroup()          ); logString.append( "/" );
            logString.append( tgoods.getQc_mgroup()          ); logString.append( "/" );
            logString.append( tgoods.getSale_gb()            ); logString.append( "/" );
            logString.append( tgoods.getSign_gb()            ); logString.append( "/" );
            logString.append( tgoods.getEntp_code()          ); logString.append( "/" );
            logString.append( tgoods.getEntp_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getAccount_gb()         ); logString.append( "/" );
            logString.append( tgoods.getMd_code()            ); logString.append( "/" );
            logString.append( tgoods.getKeyword()            ); logString.append( "/" );
            logString.append( tgoods.getBuy_med()            ); logString.append( "/" );
            logString.append( tgoods.getDely_type()          ); logString.append( "/" );
            logString.append( tgoods.getWh_code()            ); logString.append( "/" );
            logString.append( tgoods.getPost_yn()            ); logString.append( "/" );
            logString.append( tgoods.getTax_yn()             ); logString.append( "/" );
            logString.append( tgoods.getVat_rate()           ); logString.append( "/" );
            logString.append( tgoods.getCost_tax_yn()        ); logString.append( "/" );
            logString.append( tgoods.getCost_vat_rate()      ); logString.append( "/" );
            logString.append( tgoods.getMakeco_code()        ); logString.append( "/" );
            logString.append( tgoods.getOrigin_code()        ); logString.append( "/" );
            logString.append( tgoods.getBrand_code()         ); logString.append( "/" );
            logString.append( tgoods.getHandle_code()        ); logString.append( "/" );
            logString.append( tgoods.getMixpack_yn()         ); logString.append( "/" );
            logString.append( tgoods.getPack_box()           ); logString.append( "/" );
            logString.append( tgoods.getSqc_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSqc_gb()             ); logString.append( "/" );
            logString.append( tgoods.getSet_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSet_goods_yn()       ); logString.append( "/" );
            logString.append( tgoods.getGift_yn()            ); logString.append( "/" );
            logString.append( tgoods.getPay_yn()             ); logString.append( "/" );
            logString.append( tgoods.getGift_return_yn()     ); logString.append( "/" );
            logString.append( tgoods.getExch_yn()            ); logString.append( "/" );
            logString.append( tgoods.getReturn_yn()          ); logString.append( "/" );
            logString.append( tgoods.getAs_yn()              ); logString.append( "/" );
            logString.append( tgoods.getOut_stock_yn()       ); logString.append( "/" );
            logString.append( tgoods.getOrder_min_qty()      ); logString.append( "/" );
            logString.append( tgoods.getStock_chk_place()    ); logString.append( "/" );
            logString.append( tgoods.getFirst_broad_date()   ); logString.append( "/" );
            logString.append( tgoods.getOrder_media_all_yn() ); logString.append( "/" );
            logString.append( tgoods.getOrder_media()        ); logString.append( "/" );
            logString.append( tgoods.getArs_name()           ); logString.append( "/" );
            logString.append( tgoods.getParent_goods_code()  ); logString.append( "/" );
            logString.append( tgoods.getNorest_allot_yn()    ); logString.append( "/" );
            logString.append( tgoods.getNorest_allot_months()); logString.append( "/" );
            logString.append( tgoods.getCust_dc_yn()         ); logString.append( "/" );
            logString.append( tgoods.getMaster_code()        ); logString.append( "/" );
            logString.append( tgoods.getComment_cnt()        ); logString.append( "/" );
            logString.append( tgoods.getSale_start_date()    ); logString.append( "/" );
            logString.append( tgoods.getInsert_date()        ); logString.append( "/" );
            logString.append( tgoods.getInsert_id()          ); logString.append( "/" );
            logString.append( tgoods.getModify_date()        ); logString.append( "/" );
            logString.append( tgoods.getModify_id()          ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Setgoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int update(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoods));
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_name()         );
            pstmt.setString(index++,tgoods.getLgroup()             );
            pstmt.setString(index++,tgoods.getMgroup()             );
            pstmt.setString(index++,tgoods.getSgroup()             );
            pstmt.setString(index++,tgoods.getDgroup()             );
            pstmt.setString(index++,tgoods.getQc_lgroup()          );
            pstmt.setString(index++,tgoods.getQc_mgroup()          );
            pstmt.setString(index++,tgoods.getSale_gb()            );
            pstmt.setString(index++,tgoods.getSign_gb()            );
            pstmt.setString(index++,tgoods.getEntp_code()          );
            pstmt.setString(index++,tgoods.getEntp_man_seq()       );
            pstmt.setString(index++,tgoods.getAccount_gb()         );
            pstmt.setString(index++,tgoods.getMd_code()            );
            pstmt.setString(index++,tgoods.getKeyword()            );
            pstmt.setString(index++,tgoods.getBuy_med()            );
            pstmt.setString(index++,tgoods.getDely_type()          );
            pstmt.setString(index++,tgoods.getWh_code()            );
            pstmt.setString(index++,tgoods.getPost_yn()            );
            pstmt.setString(index++,tgoods.getTax_yn()             );
            pstmt.setDouble(index++,tgoods.getVat_rate()           );
            pstmt.setString(index++,tgoods.getCost_tax_yn()        );
            pstmt.setDouble(index++,tgoods.getCost_vat_rate()      );
            pstmt.setString(index++,tgoods.getMakeco_code()        );
            pstmt.setString(index++,tgoods.getOrigin_code()        );
            pstmt.setString(index++,tgoods.getBrand_code()         );
            pstmt.setString(index++,tgoods.getHandle_code()        );
            pstmt.setString(index++,tgoods.getMixpack_yn()         );
            pstmt.setLong  (index++,tgoods.getPack_box()           );
            pstmt.setString(index++,tgoods.getSqc_yn()             );
            pstmt.setString(index++,tgoods.getSqc_gb()             );
            pstmt.setString(index++,tgoods.getSet_yn()             );
            pstmt.setString(index++,tgoods.getSet_goods_yn()       );
            pstmt.setString(index++,tgoods.getGift_yn()            );
            pstmt.setString(index++,tgoods.getPay_yn()             );
            pstmt.setString(index++,tgoods.getGift_return_yn()     );
            pstmt.setString(index++,tgoods.getExch_yn()            );
            pstmt.setString(index++,tgoods.getReturn_yn()          );
            pstmt.setString(index++,tgoods.getAs_yn()              );
            pstmt.setString(index++,tgoods.getOut_stock_yn()       );
            pstmt.setLong  (index++,tgoods.getOrder_min_qty()      );
            pstmt.setString(index++,tgoods.getStock_chk_place()    );
            pstmt.setString(index++,tgoods.getFirst_broad_date()   );
            pstmt.setString(index++,tgoods.getOrder_media_all_yn() );
            pstmt.setString(index++,tgoods.getOrder_media()        );
            pstmt.setString(index++,tgoods.getArs_name()           );
            pstmt.setString(index++,tgoods.getParent_goods_code()  );
            pstmt.setString(index++,tgoods.getNorest_allot_yn()    );
            pstmt.setString(index++,tgoods.getNorest_allot_months());
            pstmt.setString(index++,tgoods.getCust_dc_yn()         );
            pstmt.setString(index++,tgoods.getMaster_code()        );
            pstmt.setString(index++,tgoods.getSale_start_date()    );
            pstmt.setString(index++,tgoods.getModify_date()        );
            pstmt.setString(index++,tgoods.getModify_id()          );
            pstmt.setString(index++,tgoods.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_name()         ); logString.append( "/" );
            logString.append( tgoods.getLgroup()             ); logString.append( "/" );
            logString.append( tgoods.getMgroup()             ); logString.append( "/" );
            logString.append( tgoods.getSgroup()             ); logString.append( "/" );
            logString.append( tgoods.getDgroup()             ); logString.append( "/" );
            logString.append( tgoods.getQc_lgroup()          ); logString.append( "/" );
            logString.append( tgoods.getQc_mgroup()          ); logString.append( "/" );
            logString.append( tgoods.getSale_gb()            ); logString.append( "/" );
            logString.append( tgoods.getSign_gb()            ); logString.append( "/" );
            logString.append( tgoods.getEntp_code()          ); logString.append( "/" );
            logString.append( tgoods.getEntp_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getAccount_gb()         ); logString.append( "/" );
            logString.append( tgoods.getMd_code()            ); logString.append( "/" );
            logString.append( tgoods.getKeyword()            ); logString.append( "/" );
            logString.append( tgoods.getBuy_med()            ); logString.append( "/" );
            logString.append( tgoods.getDely_type()          ); logString.append( "/" );
            logString.append( tgoods.getWh_code()            ); logString.append( "/" );
            logString.append( tgoods.getPost_yn()            ); logString.append( "/" );
            logString.append( tgoods.getTax_yn()             ); logString.append( "/" );
            logString.append( tgoods.getVat_rate()           ); logString.append( "/" );
            logString.append( tgoods.getCost_tax_yn()        ); logString.append( "/" );
            logString.append( tgoods.getCost_vat_rate()      ); logString.append( "/" );
            logString.append( tgoods.getMakeco_code()        ); logString.append( "/" );
            logString.append( tgoods.getOrigin_code()        ); logString.append( "/" );
            logString.append( tgoods.getBrand_code()         ); logString.append( "/" );
            logString.append( tgoods.getHandle_code()        ); logString.append( "/" );
            logString.append( tgoods.getMixpack_yn()         ); logString.append( "/" );
            logString.append( tgoods.getPack_box()           ); logString.append( "/" );
            logString.append( tgoods.getSqc_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSqc_gb()             ); logString.append( "/" );
            logString.append( tgoods.getSet_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSet_goods_yn()       ); logString.append( "/" );
            logString.append( tgoods.getGift_yn()            ); logString.append( "/" );
            logString.append( tgoods.getPay_yn()             ); logString.append( "/" );
            logString.append( tgoods.getGift_return_yn()     ); logString.append( "/" );
            logString.append( tgoods.getExch_yn()            ); logString.append( "/" );
            logString.append( tgoods.getReturn_yn()          ); logString.append( "/" );
            logString.append( tgoods.getAs_yn()              ); logString.append( "/" );
            logString.append( tgoods.getOut_stock_yn()       ); logString.append( "/" );
            logString.append( tgoods.getOrder_min_qty()      ); logString.append( "/" );
            logString.append( tgoods.getStock_chk_place()    ); logString.append( "/" );
            logString.append( tgoods.getFirst_broad_date()   ); logString.append( "/" );
            logString.append( tgoods.getOrder_media_all_yn() ); logString.append( "/" );
            logString.append( tgoods.getOrder_media()        ); logString.append( "/" );
            logString.append( tgoods.getArs_name()           ); logString.append( "/" );
            logString.append( tgoods.getParent_goods_code()  ); logString.append( "/" );
            logString.append( tgoods.getNorest_allot_yn()    ); logString.append( "/" );
            logString.append( tgoods.getNorest_allot_months()); logString.append( "/" );
            logString.append( tgoods.getCust_dc_yn()         ); logString.append( "/" );
            logString.append( tgoods.getMaster_code()        ); logString.append( "/" );
            logString.append( tgoods.getSale_start_date()    ); logString.append( "/" );
            logString.append( tgoods.getModify_date()        ); logString.append( "/" );
            logString.append( tgoods.getModify_id()          ); logString.append( "/" );
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int insert(Connection con, Tsetgoods tsetgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tsetgoods));
            int index = 1;

            pstmt.setString(index++,tsetgoods.getGoods_code()    );
            pstmt.setString(index++,tsetgoods.getIn_goods_seq()  );
            pstmt.setString(index++,tsetgoods.getIn_goods_code() );
            pstmt.setDouble(index++,tsetgoods.getSale_price()    );
            pstmt.setLong  (index++,tsetgoods.getSet_qty()       );
            pstmt.setString(index++,tsetgoods.getGoodsdt_fix_yn());
            pstmt.setString(index++,tsetgoods.getInsert_date()   );
            pstmt.setString(index++,tsetgoods.getInsert_id()     );
            pstmt.setString(index++,tsetgoods.getModify_date()   );
            pstmt.setString(index++,tsetgoods.getModify_id()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsetgoods.getGoods_code()    ); logString.append( "/" );
            logString.append( tsetgoods.getIn_goods_seq()  ); logString.append( "/" );
            logString.append( tsetgoods.getIn_goods_code() ); logString.append( "/" );
            logString.append( tsetgoods.getSale_price()    ); logString.append( "/" );
            logString.append( tsetgoods.getSet_qty()       ); logString.append( "/" );
            logString.append( tsetgoods.getGoodsdt_fix_yn()); logString.append( "/" );
            logString.append( tsetgoods.getInsert_date()   ); logString.append( "/" );
            logString.append( tsetgoods.getInsert_id()     ); logString.append( "/" );
            logString.append( tsetgoods.getModify_date()   ); logString.append( "/" );
            logString.append( tsetgoods.getModify_id()     ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TSetgoodsDT
    * </PRE>
    * @param   Connection
    * @param   TSetgoodsdt
    * @return  int
    */
    public int insert(Connection con, Tsetgoodsdt tsetgoodsdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tsetgoodsdt));
            int index = 1;
            pstmt.setString(index++,tsetgoodsdt.getGoods_code()     );
            pstmt.setString(index++,tsetgoodsdt.getIn_goods_code()  );
            pstmt.setString(index++,tsetgoodsdt.getIn_goodsdt_code());
            pstmt.setString(index++,tsetgoodsdt.getIn_goods_seq()   );
            pstmt.setString(index++,tsetgoodsdt.getIn_goodsdt_seq() );
            pstmt.setString(index++,tsetgoodsdt.getInsert_date()    );
            pstmt.setString(index++,tsetgoodsdt.getInsert_id()      );
            pstmt.setString(index++,tsetgoodsdt.getModify_date()    );
            pstmt.setString(index++,tsetgoodsdt.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsetgoodsdt.getGoods_code()     ); logString.append( "/" );
            logString.append( tsetgoodsdt.getIn_goods_code()  ); logString.append( "/" );
            logString.append( tsetgoodsdt.getIn_goodsdt_code()); logString.append( "/" );
            logString.append( tsetgoodsdt.getIn_goods_seq()   ); logString.append( "/" );
            logString.append( tsetgoodsdt.getIn_goodsdt_seq() ); logString.append( "/" );
            logString.append( tsetgoodsdt.getInsert_date()    ); logString.append( "/" );
            logString.append( tsetgoodsdt.getInsert_id()      ); logString.append( "/" );
            logString.append( tsetgoodsdt.getModify_date()    ); logString.append( "/" );
            logString.append( tsetgoodsdt.getModify_id()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TGoodsDT
    * </PRE>
    * @param   Connection
    * @param   TSetgoodsdt
    * @return  int
    */
    public int insert(Connection con, Tgoodsdt tgoodsdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tgoodsdt));
            int index = 1;
            pstmt.setString(index++,tgoodsdt.getGoods_code()  );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_code());
            pstmt.setString(index++,tgoodsdt.getGoods_name()  );
            pstmt.setString(index++,tgoodsdt.getColor_code()  );
            pstmt.setString(index++,tgoodsdt.getColor_name()  );
            pstmt.setString(index++,tgoodsdt.getSize_code()   );
            pstmt.setString(index++,tgoodsdt.getSize_name()   );
            pstmt.setString(index++,tgoodsdt.getPattern_code());
            pstmt.setString(index++,tgoodsdt.getPattern_name());
            pstmt.setString(index++,tgoodsdt.getForm_code()   );
            pstmt.setString(index++,tgoodsdt.getForm_name()   );
            pstmt.setString(index++,tgoodsdt.getOther_text()  );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_info());
            pstmt.setString(index++,tgoodsdt.getSale_gb()     );
            pstmt.setString(index++,tgoodsdt.getBarcode()     );
            pstmt.setString(index++,tgoodsdt.getInsert_date() );
            pstmt.setString(index++,tgoodsdt.getInsert_id()   );
            pstmt.setString(index++,tgoodsdt.getModify_date() );
            pstmt.setString(index++,tgoodsdt.getModify_id()   );


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsdt.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_code()   ); logString.append( "/" );
            logString.append( tgoodsdt.getGoods_name()     ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_code()     ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_name()     ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_code()      ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_name()      ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_code()   ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_name()   ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_code()      ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_name()      ); logString.append( "/" );
            logString.append( tgoodsdt.getOther_text()     ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_info()   ); logString.append( "/" );
            logString.append( tgoodsdt.getSale_gb()        ); logString.append( "/" );
            logString.append( tgoodsdt.getBarcode()        ); logString.append( "/" );
            logString.append( tgoodsdt.getInsert_date()    ); logString.append( "/" );
            logString.append( tgoodsdt.getInsert_id()      ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_date()    ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_id()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

//  = Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Setgoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int update(Connection con, Tgoodsdt tgoodsdt) throws StoreException{
    	PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsdt));
            int index = 1;

            pstmt.setString(index++,tgoodsdt.getGoods_code()    );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_code()  );
            pstmt.setString(index++,tgoodsdt.getGoods_name() );
            pstmt.setString(index++,tgoodsdt.getColor_code() );
            pstmt.setString(index++,tgoodsdt.getColor_name() );
            pstmt.setString(index++,tgoodsdt.getSize_code() );
            pstmt.setString(index++,tgoodsdt.getSize_name() );
            pstmt.setString(index++,tgoodsdt.getPattern_code() );
            pstmt.setString(index++,tgoodsdt.getPattern_name() );
            pstmt.setString(index++,tgoodsdt.getForm_code() );
            pstmt.setString(index++,tgoodsdt.getForm_name() );
            pstmt.setString(index++,tgoodsdt.getOther_text() );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_info() );
            pstmt.setString(index++,tgoodsdt.getSale_gb() );
            pstmt.setString(index++,tgoodsdt.getBarcode() );
            pstmt.setString(index++,tgoodsdt.getModify_date()   );
            pstmt.setString(index++,tgoodsdt.getModify_id()     );
            pstmt.setString(index++,tgoodsdt.getGoods_code()    );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_code()  );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsdt.getGoods_code()    ); logString.append( "/" );
            logString.append( tgoodsdt.getGoods_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getGoods_name()  ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_name()  ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_name()  ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_name()  ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_name()  ); logString.append( "/" );
            logString.append( tgoodsdt.getOther_text()  ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_info()  ); logString.append( "/" );
            logString.append( tgoodsdt.getSale_gb()  ); logString.append( "/" );
            logString.append( tgoodsdt.getBarcode()  ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_date()  ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_id()  ); logString.append( "/" );
            logString.append( tgoodsdt.getGoods_code()  ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TGoodsDT
    * </PRE>
    * @param   Connection
    * @param   TSetgoodsdt
    * @return  int
    */
    public int insert(Connection con, Tgoodssign tgoodssign) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tgoodssign));
            int index = 1;
            pstmt.setString(index++,tgoodssign.getGoods_code()  );
            pstmt.setString(index++,tgoodssign.getSign_seq()    );
            pstmt.setString(index++,tgoodssign.getApply_date()  );
            pstmt.setDouble(index++,tgoodssign.getBuy_price()   );
            pstmt.setDouble(index++,tgoodssign.getBuy_cost()    );
            pstmt.setDouble(index++,tgoodssign.getBuy_vat()     );
            pstmt.setDouble(index++,tgoodssign.getSale_price()  );
            pstmt.setDouble(index++,tgoodssign.getSale_vat()    );
            pstmt.setDouble(index++,tgoodssign.getCust_price()  );
            pstmt.setDouble(index++,tgoodssign.getSaveamt_rate());
            pstmt.setDouble(index++,tgoodssign.getSaveamt()     );
            pstmt.setString(index++,tgoodssign.getSign_gb()     );
            pstmt.setString(index++,tgoodssign.getInsert_date() );
            pstmt.setString(index++,tgoodssign.getInsert_id()   );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodssign.getGoods_code()  ); logString.append( "/" );
            logString.append( tgoodssign.getSign_seq()    ); logString.append( "/" );
            logString.append( tgoodssign.getApply_date()  ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_price()   ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_cost()    ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_vat()     ); logString.append( "/" );
            logString.append( tgoodssign.getSale_price()  ); logString.append( "/" );
            logString.append( tgoodssign.getSale_vat()    ); logString.append( "/" );
            logString.append( tgoodssign.getCust_price()  ); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt_rate()); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt()     ); logString.append( "/" );
            logString.append( tgoodssign.getSign_gb()     ); logString.append( "/" );
            logString.append( tgoodssign.getInsert_date() ); logString.append( "/" );
            logString.append( tgoodssign.getInsert_id()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TGoodsDT
    * </PRE>
    * @param   Connection
    * @param   TSetgoodsdt
    * @return  int
    */
    public int insert(Connection con, Tarskongje tarskongje) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tarskongje));
            int index = 1;
            pstmt.setString(index++,tarskongje.getGoods_code() );
            pstmt.setString(index++,tarskongje.getSeq()        );
            pstmt.setString(index++,tarskongje.getArs_bdate()  );
            pstmt.setString(index++,tarskongje.getArs_edate()  );
            pstmt.setDouble(index++,tarskongje.getEntp_amt()   );
            pstmt.setDouble(index++,tarskongje.getCom_amt()    );
            pstmt.setString(index++,tarskongje.getInsert_date());
            pstmt.setString(index++,tarskongje.getInsert_id()  );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tarskongje.getGoods_code() ); logString.append( "/" );
            logString.append( tarskongje.getSeq()        ); logString.append( "/" );
            logString.append( tarskongje.getArs_bdate()  ); logString.append( "/" );
            logString.append( tarskongje.getArs_edate()  ); logString.append( "/" );
            logString.append( tarskongje.getEntp_amt()   ); logString.append( "/" );
            logString.append( tarskongje.getCom_amt()    ); logString.append( "/" );
            logString.append( tarskongje.getInsert_date()); logString.append( "/" );
            logString.append( tarskongje.getInsert_id()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SetgoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SetgoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoods )
    * </PRE>
    * @param   Tgoods
    * @return  String
    */

    private String makeSqlDetele(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODS \n");
        sb.append(" WHERE GOODS_CODE = ?  \n");
        sb.append("   AND SIGN_GB <> '80'  \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoods
    * </PRE>
    * @param   Connection
    * @param   Tgoods
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int delete(Connection con, Tgoods tgoods, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDetele(tgoods));

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodsdt )
    * </PRE>
    * @param   Tgoods
    * @return  String
    */

    private String makeSqlDeteleTgoodsdt() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSDT \n");
        sb.append( "WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodsdt
    * </PRE>
    * @param   Connection
    * @param   Tgoodsdt
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodsdt(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodsdt());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tsetgoods )
    * </PRE>
    * @param   Tsetgoods
    * @return  String
    */

    private String makeSqlDeteleTsetgoods() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TSETGOODS \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodsdt
    * </PRE>
    * @param   Connection
    * @param   Tsetgoods
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTsetgoods(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTsetgoods());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tsetgoodsdt )
    * </PRE>
    * @param   Tsetgoodsdt
    * @return  String
    */

    private String makeSqlDeteleTsetgoodsdt() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TSETGOODSDT \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tsetgoodsdt
    * </PRE>
    * @param   Connection
    * @param   Tsetgoodsdt
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTsetgoodsdt(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTsetgoodsdt());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodssign )
    * </PRE>
    * @param   Tgoodssign
    * @return  String
    */

    private String makeSqlDeteleTgoodssign() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSSIGN \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodssign
    * </PRE>
    * @param   Connection
    * @param   Tgoodssign
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodssign(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodssign());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodsprice )
    * </PRE>
    * @param   Tgoodsprice
    * @return  String
    */

    private String makeSqlDeteleTgoodsprice() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSPRICE \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodsprice
    * </PRE>
    * @param   Connection
    * @param   Tgoodsprice
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodsprice(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodsprice());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

   /**
    * <PRE>
    * Desc : Make SQL ( delete Tsalenogoods )
    * </PRE>
    * @param   Tsalenogoods
    * @return  String
    */

    private String makeSqlDeteleTsalenogoods() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TSALENOGOODS \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tsalenogoods
    * </PRE>
    * @param   Connection
    * @param   Tsalenogoods
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTsalenogoods(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTsalenogoods());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

  /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodslink )
    * </PRE>
    * @param   Tgoodslink
    * @return  String
    */

    private String makeSqlDeteleTgoodslink() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSLINK \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodslink
    * </PRE>
    * @param   Connection
    * @param   Tgoodslink
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodslink(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodslink());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

  /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodslink )
    * </PRE>
    * @param   Tgoodslink
    * @return  String
    */

    private String makeSqlDeteleTgoodslinkLinkGoods() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSLINK \n");
        sb.append(" WHERE LINK_GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodslink
    * </PRE>
    * @param   Connection
    * @param   Tgoodslink
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodslinkLinkGoods(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodslinkLinkGoods());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( delete Tdescribe )
    * </PRE>
    * @param   Tdescribe
    * @return  String
    */

    private String makeSqlDeteleTdescribe() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TDESCRIBE \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tdescribe
    * </PRE>
    * @param   Connection
    * @param   Tdescribe
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTdescribe(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTdescribe());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

  /**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodsimage )
    * </PRE>
    * @param   Tgoodsimage
    * @return  String
    */

    private String makeSqlDeteleTgoodsimage() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSIMAGE \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodsimage
    * </PRE>
    * @param   Connection
    * @param   Tgoodsimage
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodsimage(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodsimage());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

  /**
    * <PRE>
    * Desc : Make SQL ( delete Tars )
    * </PRE>
    * @param   Tars
    * @return  String
    */

    private String makeSqlDeteleTars() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TARS \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tars
    * </PRE>
    * @param   Connection
    * @param   Tars
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTars(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTars());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


 /**
    * <PRE>
    * Desc : Make SQL ( delete Tarskongje )
    * </PRE>
    * @param   Tarskongje
    * @return  String
    */

    private String makeSqlDeteleTarskongje() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TARSKONGJE \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tarskongje
    * </PRE>
    * @param   Connection
    * @param   Tarskongje
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTarskongje(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTarskongje());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


 /**
    * <PRE>
    * Desc : Make SQL ( delete Tasentp )
    * </PRE>
    * @param   Tasentp
    * @return  String
    */

    private String makeSqlDeteleTasentp() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TASENTP \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tasentp
    * </PRE>
    * @param   Connection
    * @param   Tasentp
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTasentp(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTasentp());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

 /**
    * <PRE>
    * Desc : Make SQL ( delete Tdelynoarea )
    * </PRE>
    * @param   Tdelynoarea
    * @return  String
    */

    private String makeSqlDeteleTdelynoarea() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TDELYNOAREA \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tdelynoarea
    * </PRE>
    * @param   Connection
    * @param   Tdelynoarea
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTdelynoarea(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTdelynoarea());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

/**
    * <PRE>
    * Desc : Make SQL ( delete Tgoodsshare )
    * </PRE>
    * @param   Tgoodsshare
    * @return  String
    */

    private String makeSqlDeteleTgoodsshare() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM TGOODSSHARE \n");
        sb.append(" WHERE GOODS_CODE = ? \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }


    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete Tgoodsshare
    * </PRE>
    * @param   Connection
    * @param   Tgoodsshare
    * @param   goods_code
    * @return  RetrieveModel
    */
    public int deleteTgoodsshare(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeteleTgoodsshare());

            int index = 1;
            pstmt.setString(index++, goods_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( goods_code); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[SetgoodsSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SetgoodsSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
}







