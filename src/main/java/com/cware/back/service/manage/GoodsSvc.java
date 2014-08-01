
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
import com.cware.back.entity.table.Tbeforeqadt;
import com.cware.back.entity.table.Tbeforeqam;
import com.cware.back.entity.table.Tcategory;
import com.cware.back.entity.table.Tcategorygoods;
import com.cware.back.entity.table.Tdelynoarea;
import com.cware.back.entity.table.Tgoods;
import com.cware.back.entity.table.Tgoodsdt;
import com.cware.back.entity.table.Tgoodsservice;
import com.cware.back.entity.table.Tgoodssign;
import com.cware.back.entity.table.Torderstock;
import com.cware.back.entity.table.Tpresentation;

/**
 * Register Slipnoreg Service class
 *
 * @version 1.0, 2006/07/10
 */
public class GoodsSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   wh_code                      : 창고구분
    * @param   dely_gb                      : 배송구분
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT DISTINCT                      \n");
        sb.append("        A.GOODS_CODE,                 \n");
        sb.append("        A.GOODS_NAME,                 \n");
        sb.append("        A.LMSD_CODE,                 \n");
        sb.append("        A.ENTP_CODE                   \n");
        sb.append("  FROM TGOODS A            \n");
        sb.append(" WHERE A.INSERT_ID LIKE ?||'%'          \n");
        sb.append("   AND A.SET_YN = '0'                 \n");
        sb.append("   AND A.GOODS_CODE LIKE  ? || '%'    \n");
        if( retrieve.getString("master_code").equals("") ){
        	sb.append("   AND A.MASTER_CODE LIKE  ? || '%'   \n");
        }else{
        	sb.append("   AND A.MASTER_CODE =  ?   \n");
        }
        sb.append("   ORDER BY LPAD(A.GOODS_CODE, 10, '0') DESC \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("user_id     : " + retrieve.getString("user_id"));
            log.debug("md_code     : " + retrieve.getString("md_code"));
            log.debug("goods_code  : " + retrieve.getString("goods_code"));
            log.debug("master_code : " + retrieve.getString("master_code"));
            log.debug("entp_code   : " + retrieve.getString("entp_code"));
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 상품기초정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt1( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT   A.GOODS_CODE,                                       \n");
        sb.append("          A.GOODS_NAME,                                       \n");
        sb.append("          A.LGROUP,                                           \n");
        sb.append("          B.LGROUP_NAME,                                      \n");
        sb.append("          A.MGROUP,                                           \n");
        sb.append("          B.MGROUP_NAME,                                      \n");
        sb.append("          A.SGROUP,                                           \n");
        sb.append("          B.SGROUP_NAME,                                      \n");
        sb.append("          A.DGROUP,                                           \n");
        sb.append("          B.DGROUP_NAME,                                      \n");
        sb.append("          A.QC_LGROUP,                                        \n");
        sb.append("        '' QC_LGROUP_NAME,                                   \n");
//        sb.append("          Q.QC_LGROUP_NAME,                                   \n");
        sb.append("          A.QC_MGROUP,                                        \n");
        sb.append("          ''QC_MGROUP_NAME,                                   \n");
//        sb.append("          Q.QC_MGROUP_NAME,                                   \n");
        sb.append("          A.SALE_GB,                                          \n");
        sb.append("          A.SIGN_GB AS FORM_SIGN_GB,                          \n");
        sb.append("          A.ENTP_CODE,                                        \n");
        sb.append("          C.ENTP_NAME,                                        \n");
        sb.append("          A.ENTP_MAN_SEQ,                                     \n");
        sb.append("          I.ENTP_MAN_NAME AS ENTP_MAN_SEQ_NAME,               \n");
        sb.append("          A.ACCOUNT_GB,                                       \n");
        sb.append("          A.MD_CODE,                                          \n");
        sb.append("          D.MD_NAME,                                          \n");
        sb.append("          A.KEYWORD,                                          \n");
        sb.append("          A.BUY_MED,                                          \n");
        sb.append("          A.DELY_TYPE,                                        \n");
        sb.append("          A.WH_CODE,                                          \n");
        sb.append("          A.POST_YN,                                          \n");
        sb.append("          A.TAX_YN,                                           \n");
        sb.append("          A.VAT_RATE,                                         \n");
        sb.append("          A.COST_TAX_YN,                                      \n");
        sb.append("          A.COST_VAT_RATE,                                    \n");
        sb.append("          A.MAKECO_CODE,                                      \n");
        sb.append("          A.ORIGIN_CODE,                                      \n");
        sb.append("          A.BRAND_CODE,                                       \n");
        sb.append("          A.HANDLE_CODE,                                      \n");
        sb.append("          A.MIXPACK_YN,                                       \n");
        sb.append("          A.DM_YN,                                            \n");
        sb.append("          A.PACK_BOX,                                         \n");
        sb.append("          A.SQC_YN,                                           \n");
        sb.append("          A.SQC_GB,                                           \n");
        sb.append("          A.SET_YN,                                           \n");
        sb.append("          A.SET_GOODS_YN,                                     \n");
        sb.append("          A.GIFT_YN,                                          \n");
        sb.append("          A.PAY_YN,                                           \n");
        sb.append("          A.GIFT_RETURN_YN,                                   \n");
        sb.append("          A.EXCH_YN,                                          \n");
        sb.append("          A.RETURN_YN,                                        \n");
        sb.append("          A.AS_YN,                                            \n");
        sb.append("          A.OUT_STOCK_YN,                                     \n");
        sb.append("          A.ORDER_MIN_QTY,                                    \n");
        sb.append("          A.STOCK_CHK_PLACE,                                  \n");
        sb.append("          A.ORDER_MEDIA_ALL_YN,                               \n");
        sb.append("          A.ORDER_MEDIA,                                      \n");
        sb.append("          FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME, \n");
        sb.append("          A.ARS_NAME,                                         \n");
        sb.append("          A.PARENT_GOODS_CODE,                                \n");
        sb.append("          A.BASE_GOODS_CODE,                                  \n");
        sb.append("          A.NOREST_ALLOT_YN,                                  \n");
        sb.append("          A.NOREST_ALLOT_MONTHS,                              \n");
        sb.append("          TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE, \n");
        sb.append("          A.INSERT_ID,                                        \n");
        sb.append("          TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD') AS MODIFY_DATE, \n");
        sb.append("          A.MODIFY_ID,                                        \n");

        sb.append("          A.WH_FIX_YN,                                        \n");
        sb.append("          A.ADVC_REFUND_YN,                                   \n");
        sb.append("          A.ORDER_MAKE_YN,                                    \n");
        sb.append("          A.INSTALL_YN,                                       \n");

        sb.append("          A.DIRECT_SHIP_YN,                                        \n");
        sb.append("          A.SHIP_MAN_SEQ,                                        \n");
        sb.append("          I_1.ENTP_MAN_NAME AS SHIP_MAN_NAME,                                        \n");
        sb.append("          (CASE WHEN I_1.ENTP_MAN_DDD IS NULL  THEN '' ELSE I_1.ENTP_MAN_DDD||')'||I_1.ENTP_MAN_TEL1||'-'||I_1.ENTP_MAN_TEL2 END) || (CASE WHEN I_1.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||I_1.ENTP_MAN_TEL3||']' END) AS SHIP_MAN_TEL,\n");
        sb.append("          FUN_ADD_POSTADDR(I_1.POST_NO, I_1.POST_SEQ, I_1.ADDR) AS SHIP_MAN_ADDR, \n");
        sb.append("          A.DIRECT_RETURN_YN,                                        \n");
        sb.append("          A.RETURN_MAN_SEQ,                                        \n");
        sb.append("          I_2.ENTP_MAN_NAME AS RETURN_MAN_NAME,                                        \n");
        sb.append("          (CASE WHEN I_2.ENTP_MAN_DDD IS NULL  THEN '' ELSE I_2.ENTP_MAN_DDD||')'||I_2.ENTP_MAN_TEL1||'-'||I_2.ENTP_MAN_TEL2 END) || (CASE WHEN I_2.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||I_2.ENTP_MAN_TEL3||']' END) AS RETURN_MAN_TEL,\n");
        sb.append("          FUN_ADD_POSTADDR(I_2.POST_NO, I_2.POST_SEQ, I_2.ADDR) AS RETURN_MAN_ADDR, \n");

        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 1, 1) as ifi_1,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 2, 1) as ifi_2,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 3, 1) as ifi_3,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 4, 1) as ifi_4,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 5, 1) as ifi_5,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 6, 1) as ifi_6,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 7, 1) as ifi_7,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 8, 1) as ifi_8,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 9, 1) as ifi_9,    \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 10, 1) as ifi_10,  \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 11, 1) as ifi_11,  \n");
        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 12, 1) as ifi_12,  \n");

//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 1, 1) as Norest_1,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 2, 1) as Norest_2,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 3, 1) as Norest_3,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 4, 1) as Norest_4,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 5, 1) as Norest_5,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 6, 1) as Norest_6,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 7, 1) as Norest_7,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 8, 1) as Norest_8,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 9, 1) as Norest_9,    \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 10, 1) as Norest_10,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 11, 1) as Norest_11,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 12, 1) as Norest_12,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 13, 1) as Norest_13,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 14, 1) as Norest_14,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 15, 1) as Norest_15,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 16, 1) as Norest_16,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 17, 1) as Norest_17,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 18, 1) as Norest_18,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 19, 1) as Norest_19,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 20, 1) as Norest_20,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 21, 1) as Norest_21,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 22, 1) as Norest_22,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 23, 1) as Norest_23,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 24, 1) as Norest_24,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 25, 1) as Norest_25,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 26, 1) as Norest_26,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 27, 1) as Norest_27,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 28, 1) as Norest_28,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 29, 1) as Norest_29,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 30, 1) as Norest_30,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 31, 1) as Norest_31,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 32, 1) as Norest_32,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 33, 1) as Norest_33,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 34, 1) as Norest_34,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 35, 1) as Norest_35,  \n");
//        sb.append("          SUBSTR(A.NOREST_ALLOT_MONTHS, 36, 1) as Norest_36,  \n");

        sb.append("          E.MAKECO_NAME,                                      \n");
        sb.append("          F.BRAND_NAME,                                       \n");
        sb.append("          G.CODE_NAME AS ORIGIN_NAME,                    	 \n");
        sb.append("          A.CUST_DC_YN,                                       \n");
        sb.append("          A.SAMPLE_YN,                                         \n");
        sb.append("          TO_CHAR(A.SALE_START_DATE ,'YYYY/MM/DD HH24:MI:SS') AS SALE_START_DATE, \n");
        sb.append("          A.V_VOLUME,                                          \n");
        sb.append("          A.MASTER_CODE,                                       \n");
        sb.append("          H.GOODS_NAME AS MASTER_NAME,                         \n");
        sb.append("          A.IN_UNIT,					                          \n");
        sb.append("          A.IN_UNIT_QTY,                  			          \n");
        sb.append("          A.COMMENT_CNT,                 			          \n");
        sb.append("          A.WEIGHT,                        			          \n");
        sb.append("          A.FORM_CODE AS FORM_GROUP,        			          \n");
        sb.append("          J.CSPF_DESC AS FORM_GROUP_NAME,   			          \n");
        sb.append("          A.SIZE_CODE AS SIZE_GROUP,          			     \n");
        sb.append("          K.CSPF_DESC AS SIZE_GROUP_NAME,     	          	 \n");
        sb.append("          A.DELY_BOX_QTY,     	          	 				\n");
        sb.append("          NVL(C.TAX_RATE, (SELECT VAL FROM TCONFIG CF WHERE CF.ITEM = 'DEF_VAT_BUY_RATE')) AS DEF_BUY_VAT_RATE			\n");
        sb.append("     FROM TGOODS A,                                           \n");
        sb.append("          TGOODSKINDS B,                                      \n");
        sb.append("          TENTERPRISE C,                                      \n");
        sb.append("          TMD D,                                              \n");
//        sb.append("          TQCKINDS Q,                                         \n");
        sb.append("          TMAKECOMP E,                                        \n");
        sb.append("          TBRAND F,                                           \n");
        sb.append("          TCODE G,                                            \n");
        sb.append("          TGOODS H,                                           \n");
        sb.append("          TENTPUSER I,                                        \n");

        sb.append("          TENTPUSER I_1,                                        \n");
        sb.append("          TENTPUSER I_2,                                        \n");

        sb.append("          TGOODSINFOM J,                                      \n");
        sb.append("          TGOODSINFOM K                                       \n");
        sb.append("    WHERE A.LGROUP = B.LGROUP                                 \n");
        sb.append("      AND A.MGROUP = B.MGROUP                                 \n");
        sb.append("      AND A.SGROUP = B.SGROUP                                 \n");
        sb.append("      AND A.DGROUP = B.DGROUP                                 \n");
        sb.append("      AND A.ENTP_CODE = C.ENTP_CODE                           \n");
        sb.append("      AND A.MD_CODE = D.MD_CODE                               \n");
//        sb.append("      AND A.QC_LGROUP    = Q.QC_LGROUP(+)                     \n");
//        sb.append("      AND A.QC_MGROUP    = Q.QC_MGROUP(+)                     \n");
        sb.append("      AND A.MAKECO_CODE = E.MAKECO_CODE                       \n");
        sb.append("      AND A.BRAND_CODE = F.BRAND_CODE                         \n");
        sb.append("      AND A.ORIGIN_CODE = G.CODE_MGROUP                       \n");
        sb.append("      AND G.CODE_LGROUP = 'B023'                              \n");
        sb.append("      AND H.GOODS_CODE = A.MASTER_CODE                        \n");
        sb.append("      AND A.ENTP_CODE  = I.ENTP_CODE                       	 \n");
        sb.append("      AND A.ENTP_MAN_SEQ  = I.ENTP_MAN_SEQ                    \n");

        sb.append("      AND A.ENTP_CODE  = I_1.ENTP_CODE(+)                   	 \n");
        sb.append("      AND A.SHIP_MAN_SEQ  = I_1.ENTP_MAN_SEQ(+)             	 \n");
        sb.append("      AND A.ENTP_CODE  = I_2.ENTP_CODE(+)                  	 \n");
        sb.append("      AND A.RETURN_MAN_SEQ  = I_2.ENTP_MAN_SEQ(+)          	 \n");

        sb.append("      AND A.FORM_CODE  = J.CSPF_GROUP	                     \n");
        sb.append("      AND A.SIZE_CODE  = K.CSPF_GROUP	                     \n");
        sb.append("      AND A.GOODS_CODE = ?                                    \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 업체관련정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt2( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                     \n");
        sb.append("          A.ENTP_MAN_SEQ,                   \n");
        sb.append("          B.ENTP_MAN_NAME,                  \n");
        sb.append("          B.ENTP_MAN_LEVEL,                 \n");
        sb.append("          B.ENTP_MAN_DDD,                   \n");
        sb.append("          B.ENTP_MAN_TEL1,                  \n");
        sb.append("          B.ENTP_MAN_TEL2,                  \n");
        sb.append("          B.ENTP_MAN_TEL3,                  \n");
        sb.append("          B.ENTP_MAN_FAX1,                  \n");
        sb.append("          B.ENTP_MAN_FAX2,                  \n");
        sb.append("          B.ENTP_MAN_FAX3,                  \n");
        sb.append("          B.ENTP_MAN_HP1,                   \n");
        sb.append("          B.ENTP_MAN_HP2,                   \n");
        sb.append("          B.ENTP_MAN_HP3,                   \n");
        sb.append("          B.TRANS_NOTE                      \n");
        sb.append("     FROM TGOODS A,                         \n");
        sb.append("          TENTPUSER B                       \n");
        sb.append("    WHERE A.ENTP_CODE = B.ENTP_CODE         \n");
        sb.append("      AND A.ENTP_MAN_SEQ = B.ENTP_MAN_SEQ   \n");
        sb.append("      AND A.GOODS_CODE = ?                  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 상품업체관련정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt3( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT   A.ENTP_CODE,                                                                                    \n");
        sb.append("          A.GOODS_CODE,                                                                                   \n");
        sb.append("          A.AS_TERM,                                                                                      \n");
        sb.append("          A.AS_DELY_CHARGE,                                                                               \n");
        sb.append("          A.AS_RECEIVE_TYPE,                                                                              \n");
        sb.append("          A.AS_DELY_TYPE,                                                                                 \n");
        sb.append("          A.AS_COM1,                                                                                      \n");
        sb.append("          A.AS_COM2,                                                                                      \n");
        sb.append("          B.ENTP_NAME AS ENTP_NAME_COM1,                                                                  \n");
        sb.append("          B.ENTP_POST AS ENTP_POST_COM1,                                                                  \n");
        sb.append("          FUN_ADD_POSTADDR(B.ENTP_POST,B.ENTP_POST_SEQ,B.ENTP_ADDR) AS AS_COM1_ADDR,                      \n");
        sb.append("          B.ENTP_MAN_NAME AS AS_COM1_MAN,                                                                 \n");
        sb.append("          B.ENTP_DDD || ')' || B.ENTP_TEL1 || '-' || B.ENTP_TEL2 || ' ' || B.ENTP_TEL3   AS AS_COM1_TEL,  \n");
        sb.append("          B.ENTP_FAX1 || ')' || B.ENTP_FAX1 || '-' || B.ENTP_FAX2    AS AS_COM1_FAX,                      \n");
        sb.append("          B.ENTP_HP1 || '-' || B.ENTP_HP2 || '-' || B.ENTP_HP3   AS AS_COM1_HP,                           \n");
        sb.append("          C.ENTP_NAME AS ENTP_NAME_COM2,                                                                  \n");
        sb.append("          B.ENTP_POST AS ENTP_POST_COM2,                                                                  \n");
        sb.append("          FUN_ADD_POSTADDR(C.ENTP_POST,C.ENTP_POST_SEQ,C.ENTP_ADDR) AS AS_COM1_ADDR,                      \n");
        sb.append("          C.ENTP_MAN_NAME AS AS_COM2_MAN,                                                                 \n");
        sb.append("          C.ENTP_DDD || ')' || C.ENTP_TEL1 || '-' || C.ENTP_TEL2 || ' ' || C.ENTP_TEL3   AS AS_COM2_TEL,  \n");
        sb.append("          C.ENTP_FAX1 || ')' || C.ENTP_FAX1 || '-' || C.ENTP_FAX2    AS AS_COM2_FAX,                      \n");
        sb.append("          C.ENTP_HP1 || '-' || C.ENTP_HP2 || '-' || C.ENTP_HP3   AS AS_COM2_HP,                           \n");
        sb.append("          A.AS_REPAIR_TERM,                                                                               \n");
        sb.append("          A.AS_REPAIR_AMT,                                                                                \n");
        sb.append("          A.AS_NOTE,                                                                                      \n");
        sb.append("          A.INSERT_ID,                                                                                    \n");
        sb.append("          A.INSERT_DATE,                                                                                  \n");
        sb.append("          A.MODIFY_ID,                                                                                    \n");
        sb.append("          A.MODIFY_DATE                                                                                   \n");
        sb.append("     FROM TGOODSSERVICE A,                                                                                \n");
        sb.append("          TASENTP B,                                                                                      \n");
        sb.append("          TASENTP C                                                                                       \n");
        sb.append("   WHERE  A.ENTP_CODE     = B.ENTP_CODE                                                                   \n");
        sb.append("     AND  A.AS_COM1       = B.ENTP_SEQ                                                                    \n");
        sb.append("     AND  A.ENTP_CODE     = C.ENTP_CODE                                                                   \n");
        sb.append("     AND  A.AS_COM2       = C.ENTP_SEQ                                                                    \n");
        sb.append("     AND  A.GOODS_CODE    = ?                                                                             \n");
        sb.append("     AND  A.ENTP_CODE     = ?                                                                             \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 상품업체관련정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt4( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                                                 \n");
        sb.append("          A.SIGN_SEQ,                                                   \n");
        sb.append("          TO_CHAR(A.APPLY_DATE,'YYYY/MM/DD') AS APPLY_DATE,             \n");
        sb.append("          A.BUY_PRICE,                                                  \n");
        sb.append("          A.BUY_COST,                                                   \n");
        sb.append("          A.BUY_VAT,                                                    \n");
        sb.append("          A.BUY_VAT_RATE,                                               \n");
        sb.append("          A.SALE_PRICE,                                                 \n");
        sb.append("          A.SALE_VAT,                                                   \n");
        sb.append("          A.SALE_VAT_RATE,                                              \n");
        sb.append("          A.CUST_PRICE,                                                 \n");
        sb.append("          A.SAVEAMT_RATE,                                               \n");
        sb.append("          A.SAVEAMT,                                                    \n");
        sb.append("          A.SIGN_GB,                                                    \n");
        sb.append("          TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE,           \n");
        sb.append("          A.INSERT_ID,                                                  \n");
        sb.append("          A.SIGN_NO_CODE,                                               \n");
        sb.append("          A.SIGN_NO_NOTE                                                \n");
        sb.append("     FROM TGOODSSIGN A,                                                 \n");
        sb.append("          TGOODS B                                                      \n");
        sb.append("    WHERE A.GOODS_CODE = B.GOODS_CODE                                   \n");
        sb.append("      AND B.GOODS_CODE = ?                                              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 단품기초정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt5( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                        \n");
        sb.append("          A.GOODS_NAME,                        \n");
        sb.append("          A.GOODSDT_CODE,                      \n");
        sb.append("          A.BARCODE,                           \n");
        sb.append("          A.COLOR_CODE,                        \n");
        sb.append("          A.COLOR_NAME,                        \n");
        sb.append("          A.COLOR_NAME COMP_COLOR_NAME,        \n");
        sb.append("          A.SIZE_CODE,                         \n");
        sb.append("          A.SIZE_NAME,                         \n");
        sb.append("          A.SIZE_NAME COMP_SIZE_NAME,          \n");
        sb.append("          A.PATTERN_CODE,                      \n");
        sb.append("          A.PATTERN_NAME,                      \n");
        sb.append("          A.PATTERN_NAME COMP_PATTERN_NAME,    \n");
        sb.append("          A.FORM_CODE,                         \n");
        sb.append("          A.FORM_NAME,                         \n");
        sb.append("          A.FORM_NAME COMP_FORM_NAME,          \n");
        sb.append("          A.OTHER_TEXT,                        \n");
        sb.append("          A.GOODSDT_INFO,                      \n");
        sb.append("          A.SALE_GB,                           \n");
        sb.append("          A.INSERT_DATE,                       \n");
        sb.append("          A.INSERT_ID,                         \n");
        sb.append("          A.MODIFY_DATE,                       \n");
        sb.append("          A.MODIFY_ID                          \n");
        sb.append("     FROM TGOODSDT A                           \n");
        sb.append("    WHERE A.GOODSDT_CODE > '000'               \n");
        sb.append("      AND A.GOODS_CODE = ?                     \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 단품기초(입고예정산정수량정보) Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt6( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                                      \n");
        sb.append("          A.GOODSDT_CODE,                                    \n");
        sb.append("          A.SEQ,                                             \n");
        sb.append("          TO_CHAR(A.START_DATE,'YYYY/MM/DD') AS START_DATE,  \n");
        sb.append("          TO_CHAR(A.END_DATE,'YYYY/MM/DD') AS END_DATE,      \n");
        sb.append("          A.LEAD_TIME,                                       \n");
        sb.append("          A.DAILY_CAPA_QTY,                                  \n");
        sb.append("          A.INPLAN_QTY,		                                \n");
        sb.append("          A.MAX_SALE_QTY,                                    \n");
        sb.append("          A.INSERT_DATE,                                     \n");
        sb.append("          A.INSERT_ID,                                       \n");
        sb.append("          TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS') as SYSDATE1  \n");
        sb.append("     FROM TINPLANQTY A                                       \n");
        sb.append("    WHERE A.GOODS_CODE = ?                                   \n");
        sb.append("      AND A.GOODSDT_CODE = ?                                 \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 입고예정산정정보수량 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt7_stock1( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT SUM(TOT_SALE_QTY) AS TOT_SALE_QTY,       				\n");
        sb.append(" 	   TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS') as SYSDATETIME  \n");
        sb.append("   FROM TORDERSTOCK                             					\n");
        sb.append("  WHERE GOODS_CODE = ?                          					\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlDt7_stock2( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT SUM(TOT_SALE_QTY)  AS TOT_SALE_QTY,           				\n");
        sb.append(" 	   TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS') as SYSDATETIME  \n");
        sb.append("           FROM TORDERSTOCK                     					\n");
        sb.append("          WHERE GOODS_CODE = ?                  					\n");
        sb.append("             AND GOODSDT_CODE = ?               					\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    private String makeSqlDt7_stock3( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("     SELECT SUM(TOT_SALE_QTY)  AS TOT_SALE_QTY,   					\n");
        sb.append(" 	  	   TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS') as SYSDATETIME  \n");
        sb.append("       FROM TORDERSTOCK                          					\n");
        sb.append("      WHERE GOODS_CODE = ?                       					\n");
        sb.append("         AND (GOODSDT_CODE = ?                   					\n");
        sb.append("          OR  GOODSDT_CODE = '000')              					\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 배송불가능지역 조회
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt8( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                \n");
        sb.append("          A.AREA_GB,                   \n");
        sb.append("          B.AREA_NAME AS CODE_NAME,    \n");
        sb.append("          A.INSERT_DATE,               \n");
        sb.append("          A.INSERT_ID                  \n");
        sb.append("     FROM TDELYNOAREA A,               \n");
        sb.append("          TAREA_ZONE B                 \n");
        sb.append("    WHERE A.AREA_GB = B.AREA_GB        \n");
        sb.append("      AND A.GOODS_CODE = ?             \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 발주 CHECK
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt9( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT 1 AS CNT_BALJU          \n");
        sb.append("     FROM TBALJUDT                \n");
        sb.append("    WHERE GOODS_CODE = ?          \n");
        sb.append("      AND ROWNUM = 1              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Order Check
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt10( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("      SELECT SUM(ORDER_QTY + OUT_PLAN_QTY + TOT_SALE_QTY) AS ORDERSUM  \n");
        sb.append("        FROM TORDERSTOCK                                               \n");
        sb.append("         WHERE GOODS_CODE = ?                                          \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ;  Delivery Order Check
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt11( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("     SELECT  1 AS CNT_DELIVER       \n");
        sb.append("        FROM TORDERDT               \n");
        sb.append("        WHERE GOODS_CODE = ?        \n");
        sb.append("          AND DO_FLAG >= '25'       \n");
        sb.append("          AND SYSLAST > 0           \n");
        sb.append("          AND ROWNUM = 1            \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ;  출하지시 Check
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt12( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("     SELECT  1 AS CNT_OUT      \n");
        sb.append("        FROM TSLIPDT           \n");
        sb.append("        WHERE GOODS_CODE = ?   \n");
        sb.append("          AND ROWNUM = 1       \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //  = Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDt_CHK( Tgoods tgoods ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT                                    \n");
        sb.append("        COUNT(A.GOODS_CODE) AS GOODS_CNT   \n");
        sb.append("  FROM TGOODS A 				              \n");
        sb.append(" WHERE A.GOODS_CODE = ?                    \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("goods_code  : " + tgoods.getGoods_code());
        }
        return sb.toString();
    }

    //  = Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlGoodsCheck( Tgoods tgoods ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT                                    \n");
        sb.append("       COUNT(A.GOODS_CODE) AS GOODS_CNT   \n");
        sb.append("  FROM TGOODS A 				              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ;  카테고리 상품 조회
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCategory( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("    SELECT FF.CATEGORY_CODE,    \n");
        sb.append("           FF.CATEGORY_GB,    \n");
        sb.append("           '' AS DEPTH1,    \n");
        sb.append("           GG.CATEGORY_NAME AS DEPTH2,    \n");
        sb.append("           FF.DEPTH3,    \n");
        sb.append("           FF.DEPTH4,    \n");
        sb.append("           FF.DEPTH5,    \n");
        sb.append("           HH.GOODS_CODE,    \n");
        sb.append("           FF.DEPTH,    \n");
        sb.append("           '' AS NOTE    \n");
        sb.append("      FROM TCATEGORY GG,    \n");
        sb.append("           TCATEGORYGOODS HH,    \n");
        sb.append("          (SELECT DD.CATEGORY_CODE,    \n");
        sb.append("                 DD.CATEGORY_GB,    \n");
        sb.append("                 EE.P_CATEGORY_CODE,    \n");
        sb.append("                 EE.CATEGORY_NAME AS DEPTH3,    \n");
        sb.append("                 DD.DEPTH4,    \n");
        sb.append("                 DD.DEPTH5,    \n");
        sb.append("                 DD.DEPTH    \n");
        sb.append("            FROM TCATEGORY EE,    \n");
        sb.append("                (SELECT BB.CATEGORY_CODE,    \n");
        sb.append("                        BB.CATEGORY_GB,    \n");
        sb.append("                        CC.P_CATEGORY_CODE,    \n");
        sb.append("                        CC.CATEGORY_NAME AS DEPTH4,    \n");
        sb.append("                        BB.DEPTH5,    \n");
        sb.append("                        BB.DEPTH    \n");
        sb.append("                   FROM TCATEGORY CC,    \n");
        sb.append("                        (SELECT AA.P_CATEGORY_CODE,     \n");
        sb.append("                                AA.CATEGORY_CODE,     \n");
        sb.append("                                AA.CATEGORY_GB,     \n");
        sb.append("                                AA.CATEGORY_NAME AS DEPTH5,    \n");
        sb.append("                                DECODE(TO_NUMBER(SUBSTR(AA.CATEGORY_CODE,1,1)),0, 1,       \n");
        sb.append("                                DECODE(TO_NUMBER(SUBSTR(AA.CATEGORY_CODE,2,2)),0, 2,       \n");
        sb.append("                                DECODE(TO_NUMBER(SUBSTR(AA.CATEGORY_CODE,4,2)),0, 3,       \n");
        sb.append("                                DECODE(TO_NUMBER(SUBSTR(AA.CATEGORY_CODE,6,3)),0, 4, 5)))) AS DEPTH     \n");
        sb.append("                           FROM TCATEGORY AA) BB    \n");
        sb.append("                  WHERE CC.CATEGORY_CODE = BB.P_CATEGORY_CODE) DD    \n");
        sb.append("           WHERE EE.CATEGORY_CODE = DD.P_CATEGORY_CODE) FF    \n");
        sb.append("     WHERE GG.CATEGORY_CODE = FF.P_CATEGORY_CODE    \n");
        sb.append("       AND FF.CATEGORY_CODE = HH.CATEGORY_CODE    \n");
        sb.append("       AND HH.GOODS_CODE = ?    \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ;  프레젠테이션 상품 조회
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPresentation( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.CATEGORY_CODE,   \n");
        sb.append("        A.CATEGORY_NAME,      \n");
        sb.append("        A.PRESENTATION_CODE,      \n");
        sb.append("        A.PRESENTATION_KINDS,      \n");
        sb.append("        A.PRESENTATION_GB,  \n");
        sb.append("        A.DEPTH,  \n");
        sb.append("        B.CODE_NAME AS PRESENTATION_NAME   \n");
        sb.append("   FROM (SELECT AA.CATEGORY_CODE,      \n");
        sb.append("                BB.CATEGORY_NAME,      \n");
        sb.append("                AA.PRESENTATION_CODE,      \n");
        sb.append("                AA.PRESENTATION_KINDS,      \n");
        sb.append("                AA.PRESENTATION_GB,     \n");
        sb.append("                DECODE(TO_NUMBER(SUBSTR(BB.CATEGORY_CODE,1,1)),0, 'B111',          \n");
        sb.append("                DECODE(TO_NUMBER(SUBSTR(BB.CATEGORY_CODE,2,2)),0, '',          \n");
        sb.append("                DECODE(TO_NUMBER(SUBSTR(BB.CATEGORY_CODE,4,2)),0, 'B112',          \n");
        sb.append("                DECODE(TO_NUMBER(SUBSTR(BB.CATEGORY_CODE,6,3)),0, 'B113', 'B114'))))          \n");
        sb.append("                AS DEPTH                  \n");
        sb.append("           FROM TPRESENTATION AA,      \n");
        sb.append("                TCATEGORY BB  \n");
        sb.append("          WHERE AA.CATEGORY_CODE = BB.CATEGORY_CODE      \n");
        sb.append("            AND AA.GOODS_CODE = ?) A,  \n");
        sb.append("        TCODE B  \n");
        sb.append("  WHERE A.DEPTH = B.CODE_LGROUP  \n");
        sb.append("    AND B.USE_YN = '1'  \n");

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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt1(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt1 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva2 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt2(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt2 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva3 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt3(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt3 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva4 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt4(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt4 Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva5 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt5(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt5 Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva6 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt6(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt6 Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva7 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt7(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt7 Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva8 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt8(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt8 Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva9 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt9(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt9 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva10 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt10(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt10 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva11 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt11(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt11 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieva11 result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap makeSheetDt12(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify
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
            log.debug("Retrieve makeSheetDt12 Row : " + retRow);
        }
        return hmSheet;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @param   media_gb                      : Media 구분
    * @param   media_code                    : Media code
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("user_id"  ));
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("master_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT1
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDt1(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt1(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT1",makeSheetDt1(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt1() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt1() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT2
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt2(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt2(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT2",makeSheetDt2(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt2() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt2() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT3
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode3
    * @return  RetrieveMode3
    */
    public RetrieveModel retrieveDt3(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt3(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("entp_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT3",makeSheetDt3(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt3() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt3() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT4
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt4(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt4(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT4",makeSheetDt4(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt4() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt4() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT5
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt5(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt5(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT5",makeSheetDt5(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt5() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt5() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT6
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt6(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt6(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("goodsdt_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT6",makeSheetDt6(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt6() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt6() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT7
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt7(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            int index = 1;

            if( retrieve.getString("stock_chk_place").equals("1")){
                pstmt = con.prepareStatement(makeSqlDt7_stock1(retrieve));
                index = 1;
                pstmt.setString(index++,retrieve.getString("goods_code"));

            }else if(retrieve.getString("stock_chk_place").equals("2")){
                pstmt = con.prepareStatement(makeSqlDt7_stock2(retrieve));
                index = 1;
                pstmt.setString(index++,retrieve.getString("goods_code"));
                pstmt.setString(index++,retrieve.getString("goodsdt_code"));

            }else{  //3:복합
                pstmt = con.prepareStatement(makeSqlDt7_stock3(retrieve));
                index = 1;
                pstmt.setString(index++,retrieve.getString("goods_code"));
                pstmt.setString(index++,retrieve.getString("goodsdt_code"));
            }

            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_DT7",makeSheetDt7(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt7() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt7() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT8
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
	public RetrieveModel retrieveDt8(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt8(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT8",makeSheetDt8(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt8() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt8() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT9
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt9(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt9(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT9",makeSheetDt9(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt9() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt9() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT10
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt10(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt10(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT10",makeSheetDt10(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt10() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt10() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT11
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt11(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            //Check Delivery Order Check
            pstmt = con.prepareStatement(makeSqlDt11(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT11",makeSheetDt11(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt11() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt11() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT12
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt12(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            //Check Delivery Order Check
            pstmt = con.prepareStatement(makeSqlDt12(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT12",makeSheetDt12(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt12() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt12() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Category
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveCategory(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            //Check Delivery Order Check
            pstmt = con.prepareStatement(makeSqlCategory(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CATEGORY",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt12() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt12() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Presentation
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrievePresentation(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            //Check Delivery Order Check
            pstmt = con.prepareStatement(makeSqlPresentation(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PRESENTATION",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDt12() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDt12() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
        sb.append("          LMSD_CODE,             \n ");
        sb.append("          QC_LGROUP,             \n ");
        sb.append("          QC_MGROUP,             \n ");
        sb.append("          SALE_GB,               \n ");
        sb.append("          SIGN_GB,               \n ");
        sb.append("          ENTP_CODE,             \n ");
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
        sb.append("          SQC_YN,                \n ");
        sb.append("          SQC_GB,                \n ");
        sb.append("          SET_YN,                \n ");
        sb.append("          SET_GOODS_YN,          \n ");
        sb.append("          GIFT_YN,               \n ");
        sb.append("          PAY_YN,                \n ");
        sb.append("          GIFT_RETURN_YN,        \n ");
        sb.append("          EXCH_YN,               \n ");
        sb.append("          RETURN_YN,             \n ");
        sb.append("          OUT_STOCK_YN,          \n ");
        sb.append("          ORDER_MIN_QTY,         \n ");
        sb.append("          STOCK_CHK_PLACE,       \n ");
        sb.append("          FIRST_BROAD_DATE,      \n ");
        sb.append("          ORDER_MEDIA_ALL_YN,    \n");
        sb.append("          ORDER_MEDIA,           \n");
        sb.append("          ARS_NAME,              \n ");
        sb.append("          PARENT_GOODS_CODE,     \n ");
        sb.append("          NOREST_ALLOT_YN,       \n ");
        sb.append("          NOREST_ALLOT_MONTHS,   \n ");
        sb.append("          CUST_DC_YN,            \n ");
        sb.append("          SAMPLE_YN,             \n ");
        sb.append("          SALE_START_DATE,       \n ");
        sb.append("          V_VOLUME,              \n ");
        sb.append("          MASTER_CODE,           \n ");
        sb.append("          IN_UNIT,               \n ");
        sb.append("          IN_UNIT_QTY,           \n ");
        sb.append("          COMMENT_CNT,           \n ");
        sb.append("          BASE_GOODS_CODE,       \n ");
        sb.append("          WEIGHT,      			\n ");
        sb.append("			 DM_YN, 				\n ");
        sb.append("          FORM_CODE,      		\n ");
        sb.append("			 SIZE_CODE, 			\n ");
        sb.append("			 ENTP_MAN_SEQ, 	    	\n ");

        sb.append("			 WH_FIX_YN, 	    	\n ");
        sb.append("			 ADVC_REFUND_YN,	   	\n ");
        sb.append("			 ORDER_MAKE_YN,	    	\n ");
        sb.append("			 INSTALL_YN, 	    	\n ");

        sb.append("			 DIRECT_SHIP_YN,    	\n ");
        sb.append("			 SHIP_MAN_SEQ, 	    	\n ");
        sb.append("			 DIRECT_RETURN_YN,    	\n ");
        sb.append("			 RETURN_MAN_SEQ,    	\n ");
        sb.append("			 DELY_BOX_QTY,    		\n ");

        sb.append("          INSERT_DATE,           \n ");
        sb.append("          INSERT_ID,             \n ");
        sb.append("          MODIFY_DATE,           \n ");
        sb.append("          MODIFY_ID )            \n ");
        sb.append("  VALUES ( \n ");
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
    * Desc : Make SQL ( Insert tcategorygoods )
    * </PRE>
    * @return  String
    */

    private String makeSqlInsert(Tcategorygoods tcategorygoods) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TCATEGORYGOODS (  \n ");
        sb.append("           CATEGORY_CODE,  \n ");
        sb.append("           GOODS_CODE,  \n ");
        sb.append("           DISPLAY_YN,  \n ");
        sb.append("           DISPLAY_PRIORITY,  \n ");
        sb.append("           DISPLAY_START_DATE,  \n ");
        sb.append("           DISPLAY_END_DATE,  \n ");
        sb.append("           INSERT_DATE,  \n ");
        sb.append("           INSERT_ID,  \n ");
        sb.append("           MODIFY_DATE,  \n ");
        sb.append("           MODIFY_ID)  \n ");
        sb.append("   VALUES (  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           ?,  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           ?)  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert tpresentation )
    * </PRE>
    * @return  String
    */

    private String makeSqlInsert(Tpresentation tpresentation) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TPRESENTATION (  \n ");
        sb.append("           PRESENTATION_CODE,  \n ");
        sb.append("           PRESENTATION_KINDS,  \n ");
        sb.append("           PRESENTATION_GB,  \n ");
        sb.append("           CATEGORY_CODE,  \n ");
        sb.append("           GOODS_CODE,  \n ");
        sb.append("           DISPLAY_YN,  \n ");
        sb.append("           DISPLAY_PRIORITY,  \n ");
        sb.append("           DISPLAY_START_DATE,  \n ");
        sb.append("           DISPLAY_END_DATE,  \n ");
        sb.append("           INSERT_DATE,  \n ");
        sb.append("           INSERT_ID,  \n ");
        sb.append("           MODIFY_DATE,  \n ");
        sb.append("           MODIFY_ID)  \n ");
        sb.append("   VALUES (  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           ?,  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           ?,  \n ");
        sb.append("           TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("           ?)  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Tgoods )
    * </PRE>
    * @param   Tgoods
    * @return  String
    */
    private String makeSqlUpdate(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE TGOODS SET                                                   \n ");
        sb.append("        GOODS_NAME          = ?,                                     \n ");
        sb.append("        LGROUP              = ?,                                     \n ");
        sb.append("        MGROUP              = ?,                                     \n ");
        sb.append("        SGROUP              = ?,                                     \n ");
        sb.append("        DGROUP              = ?,                                     \n ");
        sb.append("        QC_LGROUP           = ?,                                     \n ");
        sb.append("        QC_MGROUP           = ?,                                     \n ");
        sb.append("        SALE_GB             = ?,                                     \n ");
        sb.append("        SIGN_GB             = ?,                                     \n ");
        sb.append("        ENTP_CODE           = ?,                                     \n ");
        sb.append("        ACCOUNT_GB          = ?,                                     \n ");
        sb.append("        MD_CODE             = ?,                                     \n ");
        sb.append("        KEYWORD             = ?,                                     \n ");
        sb.append("        BUY_MED             = ?,                                     \n ");
        sb.append("        DELY_TYPE           = ?,                                     \n ");
        sb.append("        WH_CODE             = ?,                                     \n ");
        sb.append("        POST_YN             = ?,                                     \n ");
        sb.append("        TAX_YN              = ?,                                     \n ");
        sb.append("        VAT_RATE            = ?,                                     \n ");
        sb.append("        COST_TAX_YN         = ?,                                     \n ");
        sb.append("        COST_VAT_RATE       = ?,                                     \n ");
        sb.append("        MAKECO_CODE         = ?,                                     \n ");
        sb.append("        ORIGIN_CODE         = ?,                                     \n ");
        sb.append("        BRAND_CODE          = ?,                                     \n ");
        sb.append("        HANDLE_CODE         = ?,                                     \n ");
        sb.append("        MIXPACK_YN          = ?,                                     \n ");
        sb.append("        SQC_YN              = ?,                                     \n ");
        sb.append("        SQC_GB              = ?,                                     \n ");
        sb.append("        SET_YN              = ?,                                     \n ");
        sb.append("        SET_GOODS_YN        = ?,                                     \n ");
        sb.append("        GIFT_YN             = ?,                                     \n ");
        sb.append("        PAY_YN              = ?,                                     \n ");
        sb.append("        GIFT_RETURN_YN      = ?,                                     \n ");
        sb.append("        EXCH_YN             = ?,                                     \n ");
        sb.append("        RETURN_YN           = ?,                                     \n ");
        sb.append("        OUT_STOCK_YN        = ?,                                     \n ");
        sb.append("        ORDER_MIN_QTY       = ?,                                     \n ");
        sb.append("        STOCK_CHK_PLACE     = ?,                                     \n ");
        sb.append("        FIRST_BROAD_DATE    = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),	\n ");
        sb.append("        ORDER_MEDIA_ALL_YN  = ?,                                     \n ");
        sb.append("        ORDER_MEDIA         = ?,                                     \n ");
        sb.append("        ARS_NAME            = ?,                                     \n ");
        sb.append("        PARENT_GOODS_CODE   = ?,                                     \n ");
        sb.append("        NOREST_ALLOT_YN     = ?,                                     \n ");
        sb.append("        NOREST_ALLOT_MONTHS = ?,                                     \n ");
        sb.append("        CUST_DC_YN          = ?,                                     \n ");
        sb.append("        SAMPLE_YN           = ?,                                     \n ");
        sb.append("        SALE_START_DATE     = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),	\n ");
        sb.append("        V_VOLUME            = ?,                                     \n ");
        sb.append("        MASTER_CODE         = ?,                                     \n ");
        sb.append("        IN_UNIT             = ?,                                     \n ");
        sb.append("        IN_UNIT_QTY         = ?,                                     \n ");
        sb.append("        COMMENT_CNT         = ?,                                     \n ");
        sb.append("        BASE_GOODS_CODE     = ?,             			            \n ");
        sb.append("        WEIGHT              = ?,             			            \n ");
        sb.append("        DM_YN               = ?,             			            \n ");
        sb.append("        FORM_CODE           = ?,             			            \n ");
        sb.append("        SIZE_CODE           = ?,             			            \n ");
        sb.append("        ENTP_MAN_SEQ        = ?,             			            \n ");

        sb.append("        WH_FIX_YN           = ?,             			            \n ");
        sb.append("        ADVC_REFUND_YN      = ?,             			            \n ");
        sb.append("        ORDER_MAKE_YN       = ?,             			            \n ");
        sb.append("        INSTALL_YN          = ?,             			            \n ");

        sb.append("        DIRECT_SHIP_YN      = ?,             			            \n ");
        sb.append("        SHIP_MAN_SEQ        = ?,             			            \n ");
        sb.append("        DIRECT_RETURN_YN    = ?,             			            \n ");
        sb.append("        RETURN_MAN_SEQ        = ?,             			            \n ");
        sb.append("        DELY_BOX_QTY        = ?,             			            \n ");

        sb.append("        MODIFY_DATE         = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),    \n ");
        sb.append("        MODIFY_ID           = ?                                      \n ");
        sb.append("  WHERE GOODS_CODE          = ?                                      \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update tgoodsservice )
    * </PRE>
    * @param   Setgoodsservice
    * @return  String
    */
    private String makeSqlUpdate(Tgoodsservice tgoodsservice) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  UPDATE TGOODSSERVICE SET                                          \n ");
        sb.append("         AS_TERM              = ?,                                  \n ");
        sb.append("         AS_DELY_CHARGE       = ?,                                  \n ");
        sb.append("         AS_RECEIVE_TYPE      = ?,                                  \n ");
        sb.append("         AS_DELY_TYPE         = ?,                                  \n ");
        sb.append("         AS_COM1              = ?,                                  \n ");
        sb.append("         AS_COM2              = ?,                                  \n ");
        sb.append("         AS_REPAIR_TERM       = ?,                                  \n ");
        sb.append("         AS_NOTE              = ?,                                  \n ");
        sb.append("         ENTP_CODE            = ?,                                  \n ");
        sb.append("         MODIFY_DATE          = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID            = ?                                   \n ");
        sb.append("   WHERE GOODS_CODE           = ?                                   \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();

    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Tgoodssign )
    * </PRE>
    * @param   Tgoodssign
    * @return  String
    */
    private final String updateSqlgoodssign =  " UPDATE TGOODSSIGN SET \n "
                                              +"        BUY_PRICE      = ?, \n "
                                              +"        BUY_COST       = ?, \n "
                                              +"        BUY_VAT        = ?, \n "
                                              +"        BUY_VAT_RATE   = ?, \n "
                                              +"        SALE_PRICE     = ?, \n "
                                              +"        SALE_VAT       = ?, \n "
                                              +"        SALE_VAT_RATE  = ?, \n "
                                              +"        CUST_PRICE     = ?, \n "
                                              +"        SAVEAMT_RATE   = ?, \n "
                                              +"        SAVEAMT        = ?  \n "
                                              +"  WHERE GOODS_CODE     = ?  \n "
                                              +"    AND SIGN_SEQ       = ?  \n " ;
    private int updateSqlLogtgoodssign =  0;

    private String makeSqlUpdate(Tgoodssign tgoodssign) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogtgoodssign == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlgoodssign);
            }
            updateSqlLogtgoodssign = 1;
        }
        return updateSqlgoodssign;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Tgoodsdt )
    * </PRE>
    * @param   Tgoodsdt
    * @return  String
    */

    private final String updateSqlgoodsdt   =  " UPDATE TGOODSDT SET \n "
                                              +"        COLOR_CODE     = ?, \n "
                                              +"        COLOR_NAME     = ?, \n "
                                              +"        SIZE_CODE      = ?, \n "
                                              +"        SIZE_NAME      = ?, \n "
                                              +"        PATTERN_CODE   = ?, \n "
                                              +"        PATTERN_NAME   = ?, \n "
                                              +"        FORM_CODE      = ?, \n "
                                              +"        FORM_NAME      = ?, \n "
                                              +"        OTHER_TEXT     = ?, \n "
                                              +"        GOODSDT_INFO   = ?, \n "
                                              +"        BARCODE        = ?, \n "
                                              +"        MODIFY_DATE    = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              +"        MODIFY_ID      = ?  \n "
                                              +"  WHERE GOODS_CODE     = ?  \n "
                                              +"    AND GOODSDT_CODE   = ?  \n " ;
    private int updateSqlLogtgoodsdt =  0;

    private String makeSqlUpdate(Tgoodsdt tgoodsdt) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogtgoodsdt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlgoodsdt);
            }
            updateSqlLogtgoodsdt = 1;
        }
        return updateSqlgoodsdt;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Tgoods )
    * </PRE>
    * @param   Tgoods
    * @return  String
    */

    private String makeSqlUpdateSaleGb(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  UPDATE TGOODS SET              \n ");
        sb.append("         SALE_GB          = '00' \n ");
        sb.append("   WHERE GOODS_CODE       = ?    \n ");
        sb.append("     AND SALE_GB          = ?    \n ");

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

    private String makeSqlInsert(Tgoodsservice tgoodsservice) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TGOODSSERVICE (           \n ");
        sb.append("          GOODS_CODE,                   \n ");
        sb.append("          AS_TERM,                      \n ");
        sb.append("          AS_DELY_CHARGE,               \n ");
        sb.append("          AS_RECEIVE_TYPE,              \n ");
        sb.append("          AS_DELY_TYPE,                 \n ");
        sb.append("          AS_COM1,                      \n ");
        sb.append("          AS_COM2,                      \n ");
        sb.append("          AS_REPAIR_TERM,               \n ");
        sb.append("          AS_NOTE,                      \n ");
        sb.append("          ENTP_CODE,                    \n ");
        sb.append("          INSERT_DATE,                  \n ");
        sb.append("          INSERT_ID,                    \n ");
        sb.append("          MODIFY_DATE,                  \n ");
        sb.append("          MODIFY_ID )                   \n ");
        sb.append("  VALUES ( \n ");
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
    * Desc : Make SQL ( Insert tgoodssign )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */
    private final String insertSqltgoodssign =  " INSERT INTO TGOODSSIGN( \n "
                                         +"         GOODS_CODE,     \n "
                                         +"         SIGN_SEQ,       \n "
                                         +"         APPLY_DATE,     \n "
                                         +"         BUY_PRICE,      \n "
                                         +"         BUY_COST,       \n "
                                         +"         BUY_VAT,        \n "
                                         +"         BUY_VAT_RATE,   \n "
                                         +"         SALE_PRICE,     \n "
                                         +"         SALE_VAT,       \n "
                                         +"         SALE_VAT_RATE,  \n "
                                         +"         CUST_PRICE,     \n "
                                         +"         SAVEAMT_RATE,   \n "
                                         +"         SAVEAMT,        \n "
                                         +"         SIGN_GB,        \n "
                                         +"         INSERT_DATE,    \n "
                                         +"         INSERT_ID )     \n "
                                          +" VALUES (      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        ?,      \n "
                                         +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                         +"        ?)      \n ";


    private int insertSqlLogtgoodssign =  0;

    private String makeSqlInsert(Tgoodssign tgoodssign) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogtgoodssign == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqltgoodssign);
            }
            insertSqlLogtgoodssign = 1;
        }
        return insertSqltgoodssign;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert tgoodsdt )
    * </PRE>
    * @param   tgoodsdt
    * @return  String
    */
    private final String insertSqltgoodsdt  =  " INSERT INTO TGOODSDT (   \n "
                                             +"          GOODS_CODE,      \n "
                                             +"          GOODSDT_CODE,    \n "
                                             +"          GOODS_NAME,      \n "
                                             +"          COLOR_CODE,      \n "
                                             +"          COLOR_NAME,      \n "
                                             +"          SIZE_CODE ,      \n "
                                             +"          SIZE_NAME ,      \n "
                                             +"          PATTERN_CODE,    \n "
                                             +"          PATTERN_NAME,    \n "
                                             +"          FORM_CODE   ,    \n "
                                             +"          FORM_NAME   ,    \n "
                                             +"          OTHER_TEXT  ,    \n "
                                             +"          GOODSDT_INFO,    \n "
                                             +"          SALE_GB     ,    \n "
                                             +"          BARCODE     ,    \n "
                                             +"          INSERT_DATE ,    \n "
                                             +"          INSERT_ID   ,    \n "
                                             +"          MODIFY_DATE ,    \n "
                                             +"          MODIFY_ID  )     \n "
                                             +"  VALUES ( \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"          ?, \n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"          ?) \n ";

    private int insertSqlLogtgoodsdt =  0;

    private String makeSqlInsert(Tgoodsdt tgoodsdt) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogtgoodsdt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqltgoodsdt);
            }
            insertSqlLogtgoodsdt = 1;
        }
        return insertSqltgoodsdt;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Torderstock )
    * </PRE>
    * @param   torderstock
    * @return  String
    */

    private final String insertsqltstock  =  " INSERT INTO TORDERSTOCK (            \n "
                                             +"          GOODS_CODE,                \n "
                                             +"          GOODSDT_CODE,              \n "
                                             +"          WH_CODE,                   \n "
                                             +"          ORDER_QTY,                 \n "
                                             +"          OUT_PLAN_QTY,              \n "
                                             +"          BROAD_QTY,                 \n "
                                             +"          TOT_SALE_QTY,              \n "
                                             +"          INSERT_DATE,               \n "
                                             +"          INSERT_ID,                 \n "
                                             +"          MODIFY_DATE,               \n "
                                             +"          MODIFY_ID )                \n "
                                             +"  SELECT  ?, \n "
                                             +"          ?, \n "
                                             +"          WH_CODE, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          ?, \n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"          ?, \n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"          ? \n "
                                             +"    FROM TWAREHOUSE \n "
                                             +"   WHERE STOCK_CHECK_YN = '1' \n ";
//                                             +"     AND STOCK_CHECK_YN = '1' \n ";



    private int insertSqlLogtstock =  0;

    private String makeSqlInsert(Torderstock torderstock) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogtstock == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertsqltstock);
            }
            insertSqlLogtstock = 1;
        }
        return insertsqltstock;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tdelynoarea )
    * </PRE>
    * @param   tdelynoarea
    * @return  String
    */

    private final String insertsqltdelynoarea  =  " INSERT  INTO  TDELYNOAREA (           \n "
                                                 +"          GOODS_CODE,             \n "
                                                 +"          AREA_GB,                \n "
                                                 +"          INSERT_DATE,            \n "
                                                 +"          INSERT_ID )             \n "
                                                 +"  VALUES ( \n   "
                                                 +"          ?, \n "
                                                 +"          ?, \n "
                                                 +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"          ?) \n ";

    private int insertSqlLogtdelynoarea =  0;

    private String makeSqlInsert(Tdelynoarea tdelynoarea) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogtdelynoarea == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertsqltdelynoarea);
            }
            insertSqlLogtdelynoarea = 1;
        }
        return insertsqltdelynoarea;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tarskongje )
    * </PRE>
    * @param   tarskongje
    * @return  String
    */

    private String makeSqlInsert(Tarskongje tarskongje) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TARSKONGJE (           \n ");
        sb.append("          GOODS_CODE,                \n ");
        sb.append("          SEQ,                       \n ");
        sb.append("          ARS_BDATE,                 \n ");
        sb.append("          ARS_EDATE,                 \n ");
        sb.append("          ENTP_AMT,                  \n ");
        sb.append("          COM_AMT,                   \n ");
        sb.append("          INSERT_DATE,               \n ");
        sb.append("          INSERT_ID )                \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
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


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Setgoods )
    * </PRE>
    * @param   Setgoods
    * @return  String
    */
    private String makeSqlSelect(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT A.SQC_RESULT                                                \n ");
        sb.append("    FROM TSQCRESULTM A                                               \n ");
        sb.append("   WHERE A.SQC_SEQ = ( SELECT MAX(B.SQC_SEQ)                         \n ");
        sb.append("                            FROM TSQCRESULTM B                       \n ");
        sb.append("                           WHERE B.GOODS_CODE = ?                    \n ");
        sb.append("                                   AND B.SQC_RESULT IS NOT NULL )    \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }

   //= Select -------------------------------------------------
    /**
    * <PRE>
    * Desc : Select Tgoods Check Balju, Order ...
    * </PRE>
    * @param   Connection
    * @param   Setgoods
    * @return  int
    */
    public String sqcChkHistory(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String executedRtn = "";
        try {

            pstmt = con.prepareStatement(makeSqlSelect(tgoods));
            int index = 1;

            pstmt.setString(index++,tgoods.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            rset  = pstmt.executeQuery();
            if (rset != null && rset.next()){
                executedRtn = rset.getString(1);
            }else{
                executedRtn = "00";
            }

        }catch(SQLException se){
            logSave.error("[Tgoods.sqcChkHistory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.sqcChkHistory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( sqc result count )
    * </PRE>
    * @param   tgoods
    * @return  String
    */
    private String makeSqlSqcCnt(Tgoods tgoods) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT COUNT(GOODS_CODE) AS CNT   \n ");
        sb.append("    FROM TSQCRESULTM A              \n ");
        sb.append("   WHERE A.GOODS_CODE = ?           \n ");
        sb.append("     AND ROWNUM       = 1           \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }

   //= Select -------------------------------------------------
    /**
    * <PRE>
    * Desc : Select Tsqcresultm Count
    * </PRE>
    * @param   Connection
    * @param   Setgoods
    * @return  int
    */
    public int sqcCnt(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int      executedRtn = 0;
        try {

            pstmt = con.prepareStatement(makeSqlSqcCnt(tgoods));
            int index = 1;

            pstmt.setString(index++,tgoods.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            rset  = pstmt.executeQuery();
            if (rset != null && rset.next()){
                executedRtn = rset.getInt(1);
            }else{
                executedRtn = 0;
            }

        }catch(SQLException se){
            logSave.error("[Tgoods.sqcCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.sqcCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
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
            pstmt.setString(index++,tgoods.getLmsd_code()          );
            pstmt.setString(index++,tgoods.getQc_lgroup()          );
            pstmt.setString(index++,tgoods.getQc_mgroup()          );
            pstmt.setString(index++,tgoods.getSale_gb()            );
            pstmt.setString(index++,tgoods.getSign_gb()            );
            pstmt.setString(index++,tgoods.getEntp_code()          );
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
            pstmt.setString(index++,tgoods.getSqc_yn()             );
            pstmt.setString(index++,tgoods.getSqc_gb()             );
            pstmt.setString(index++,tgoods.getSet_yn()             );
            pstmt.setString(index++,tgoods.getSet_goods_yn()       );
            pstmt.setString(index++,tgoods.getGift_yn()            );
            pstmt.setString(index++,tgoods.getPay_yn()             );
            pstmt.setString(index++,tgoods.getGift_return_yn()     );
            pstmt.setString(index++,tgoods.getExch_yn()            );
            pstmt.setString(index++,tgoods.getReturn_yn()          );
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
            pstmt.setString(index++,tgoods.getSample_yn()          );
            pstmt.setString(index++,tgoods.getSale_start_date()    );
            pstmt.setString(index++,tgoods.getV_volume()           );
            pstmt.setString(index++,tgoods.getMaster_code()        );
            pstmt.setString(index++,tgoods.getIn_unit()            );
            pstmt.setLong  (index++,tgoods.getIn_unit_qty()        );
            pstmt.setLong  (index++,tgoods.getComment_cnt()        );
            pstmt.setString(index++,tgoods.getBase_goods_code()    );
            pstmt.setString(index++,tgoods.getWeight()             );
            pstmt.setString(index++,tgoods.getDm_yn()              );
            pstmt.setString(index++,tgoods.getForm_code()          );
            pstmt.setString(index++,tgoods.getSize_code()          );
            pstmt.setString(index++,tgoods.getEntp_man_seq()       );

            pstmt.setString(index++,tgoods.getWh_fix_yn()       );
            pstmt.setString(index++,tgoods.getAdvc_refund_yn()       );
            pstmt.setString(index++,tgoods.getOrder_make_yn()       );
            pstmt.setString(index++,tgoods.getInstall_yn()       );

            pstmt.setString(index++,tgoods.getDirect_ship_yn()       );
            pstmt.setString(index++,tgoods.getShip_man_seq()       );
            pstmt.setString(index++,tgoods.getDirect_return_yn()       );
            pstmt.setString(index++,tgoods.getReturn_man_seq()       );
            pstmt.setLong(index++,tgoods.getDely_box_qty()       );

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
            logString.append( tgoods.getLmsd_code()          ); logString.append( "/" );
            logString.append( tgoods.getQc_lgroup()          ); logString.append( "/" );
            logString.append( tgoods.getQc_mgroup()          ); logString.append( "/" );
            logString.append( tgoods.getSale_gb()            ); logString.append( "/" );
            logString.append( tgoods.getSign_gb()            ); logString.append( "/" );
            logString.append( tgoods.getEntp_code()          ); logString.append( "/" );
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
            logString.append( tgoods.getSqc_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSqc_gb()             ); logString.append( "/" );
            logString.append( tgoods.getSet_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSet_goods_yn()       ); logString.append( "/" );
            logString.append( tgoods.getGift_yn()            ); logString.append( "/" );
            logString.append( tgoods.getPay_yn()             ); logString.append( "/" );
            logString.append( tgoods.getGift_return_yn()     ); logString.append( "/" );
            logString.append( tgoods.getExch_yn()            ); logString.append( "/" );
            logString.append( tgoods.getReturn_yn()          ); logString.append( "/" );
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
            logString.append( tgoods.getSample_yn()          ); logString.append( "/" );
            logString.append( tgoods.getSale_start_date()    ); logString.append( "/" );
            logString.append( tgoods.getV_volume()           ); logString.append( "/" );
            logString.append( tgoods.getMaster_code()        ); logString.append( "/" );
            logString.append( tgoods.getIn_unit()            ); logString.append( "/" );
            logString.append( tgoods.getIn_unit_qty()        ); logString.append( "/" );
            logString.append( tgoods.getComment_cnt()        ); logString.append( "/" );
            logString.append( tgoods.getBase_goods_code()    ); logString.append( "/" );
            logString.append( tgoods.getWeight()             ); logString.append( "/" );
            logString.append( tgoods.getDm_yn()              ); logString.append( "/" );
            logString.append( tgoods.getForm_code()          ); logString.append( "/" );
            logString.append( tgoods.getSize_code()          ); logString.append( "/" );
            logString.append( tgoods.getEntp_man_seq()       ); logString.append( "/" );

            logString.append( tgoods.getWh_fix_yn()       ); logString.append( "/" );
            logString.append( tgoods.getAdvc_refund_yn()       ); logString.append( "/" );
            logString.append( tgoods.getOrder_make_yn()       ); logString.append( "/" );
            logString.append( tgoods.getInstall_yn()       ); logString.append( "/" );

            logString.append( tgoods.getDirect_ship_yn()       ); logString.append( "/" );
            logString.append( tgoods.getShip_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getDirect_return_yn()       ); logString.append( "/" );
            logString.append( tgoods.getReturn_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getDely_box_qty()       ); logString.append( "/" );

            logString.append( tgoods.getInsert_date()        ); logString.append( "/" );
            logString.append( tgoods.getInsert_id()          ); logString.append( "/" );
            logString.append( tgoods.getModify_date()        ); logString.append( "/" );
            logString.append( tgoods.getModify_id()          ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[Tgoods.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
     /**
     * <PRE>
     * Desc : Insert Tcategorygoods
     * </PRE>
     * @param   Connection
     * @return  int
     */
     public int insert(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {

             pstmt = con.prepareStatement(makeSqlInsert(tcategorygoods));
             int index = 1;

             pstmt.setString(index++,tcategorygoods.getCategory_code());
             pstmt.setString(index++,tcategorygoods.getGoods_code());
             pstmt.setString(index++,tcategorygoods.getDisplay_yn());
             pstmt.setString(index++,tcategorygoods.getDisplay_priority());
             pstmt.setString(index++,tcategorygoods.getDisplay_start_date());
             pstmt.setString(index++,tcategorygoods.getDisplay_end_date());
             pstmt.setString(index++,tcategorygoods.getInsert_date());
             pstmt.setString(index++,tcategorygoods.getInsert_id());
             pstmt.setString(index++,tcategorygoods.getModify_date());
             pstmt.setString(index++,tcategorygoods.getModify_id());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tcategorygoods.getCategory_code()); logString.append( "/" );
             logString.append( tcategorygoods.getGoods_code()); logString.append( "/" );
             logString.append( tcategorygoods.getDisplay_yn()); logString.append( "/" );
             logString.append( tcategorygoods.getDisplay_priority()); logString.append( "/" );
             logString.append( tcategorygoods.getDisplay_start_date()); logString.append( "/" );
             logString.append( tcategorygoods.getDisplay_end_date()); logString.append( "/" );
             logString.append( tcategorygoods.getInsert_date()); logString.append( "/" );
             logString.append( tcategorygoods.getInsert_id()); logString.append( "/" );
             logString.append( tcategorygoods.getModify_date()); logString.append( "/" );
             logString.append( tcategorygoods.getModify_id()); logString.append( "/" );

             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();
//             logSave.info(""+Integer.toString(executedRtn));

         }catch(SQLException se){
             logSave.error("[Tcategorygoods.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[Tcategorygoods.insert() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return executedRtn;
     }

     //= Insert -------------------------------------------------
      /**
      * <PRE>
      * Desc : Insert Tpresentation
      * </PRE>
      * @param   Connection
      * @return  int
      */
      public int insert(Connection con, Tpresentation tpresentation) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;
          int executedRtn = 0;

          try {

              pstmt = con.prepareStatement(makeSqlInsert(tpresentation));
              int index = 1;

              pstmt.setString(index++,tpresentation.getPresentation_code());
              pstmt.setString(index++,tpresentation.getPresentation_kinds());
              pstmt.setString(index++,tpresentation.getPresentation_gb());
              pstmt.setString(index++,tpresentation.getCategory_code());
              pstmt.setString(index++,tpresentation.getGoods_code());
              pstmt.setString(index++,tpresentation.getDisplay_yn());
              pstmt.setString(index++,tpresentation.getDisplay_priority());
              pstmt.setString(index++,tpresentation.getDisplay_start_date());
              pstmt.setString(index++,tpresentation.getDisplay_end_date());
              pstmt.setString(index++,tpresentation.getInsert_date());
              pstmt.setString(index++,tpresentation.getInsert_id());
              pstmt.setString(index++,tpresentation.getModify_date());
              pstmt.setString(index++,tpresentation.getModify_id());

              //= log Save Data ---------------------
              StringBuffer logString = new StringBuffer();
              logString.append( tpresentation.getPresentation_code()); logString.append( "/" );
              logString.append( tpresentation.getPresentation_kinds()); logString.append( "/" );
              logString.append( tpresentation.getPresentation_gb()); logString.append( "/" );
              logString.append( tpresentation.getCategory_code()); logString.append( "/" );
              logString.append( tpresentation.getGoods_code()); logString.append( "/" );
              logString.append( tpresentation.getDisplay_yn()); logString.append( "/" );
              logString.append( tpresentation.getDisplay_priority()); logString.append( "/" );
              logString.append( tpresentation.getDisplay_start_date()); logString.append( "/" );
              logString.append( tpresentation.getDisplay_end_date()); logString.append( "/" );
              logString.append( tpresentation.getInsert_date()); logString.append( "/" );
              logString.append( tpresentation.getInsert_id()); logString.append( "/" );
              logString.append( tpresentation.getModify_date()); logString.append( "/" );
              logString.append( tpresentation.getModify_id()); logString.append( "/" );

              logSave.info(logString.toString());

              executedRtn = pstmt.executeUpdate();
//              logSave.info(""+Integer.toString(executedRtn));

          }catch(SQLException se){
              logSave.error("[Tpresentation.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              logSave.error("[Tpresentation.insert() Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return executedRtn;
      }
    //  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Tgoods
    * </PRE>
    * @param   Connection
    * @param   Tgoods
    * @return  int
    */
    public int chkGoodsCode(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
        	pstmt = con.prepareStatement(makeSqlDt_CHK(tgoods));
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            rset = pstmt.executeQuery();

            if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
           	executedRtn = rset.getInt(1);

        }catch(SQLException se){
            logSave.error("[Tgoods.chkGoodsCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.chkGoodsCode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check GoodsCode
    * </PRE>
    * @param   Connection
    * @param   Tgoods
    * @return  String
    */
    public int chkFirstGoodsCode(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
        	pstmt = con.prepareStatement(makeSqlGoodsCheck(tgoods));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logSave.info(logString.toString());

            rset = pstmt.executeQuery();

            if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
            executedRtn = rset.getInt(1);

        }catch(SQLException se){
            logSave.error("[Tgoods.chkFirstGoodsCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.chkFirstGoodsCode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoods
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
            pstmt.setString(index++,tgoods.getSqc_yn()             );
            pstmt.setString(index++,tgoods.getSqc_gb()             );
            pstmt.setString(index++,tgoods.getSet_yn()             );
            pstmt.setString(index++,tgoods.getSet_goods_yn()       );
            pstmt.setString(index++,tgoods.getGift_yn()            );
            pstmt.setString(index++,tgoods.getPay_yn()             );
            pstmt.setString(index++,tgoods.getGift_return_yn()     );
            pstmt.setString(index++,tgoods.getExch_yn()            );
            pstmt.setString(index++,tgoods.getReturn_yn()          );
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
            pstmt.setString(index++,tgoods.getSample_yn()          );
            pstmt.setString(index++,tgoods.getSale_start_date()    );
            pstmt.setString(index++,tgoods.getV_volume()           );
            pstmt.setString(index++,tgoods.getMaster_code()        );
            pstmt.setString(index++,tgoods.getIn_unit()            );
            pstmt.setLong  (index++,tgoods.getIn_unit_qty()        );
            pstmt.setLong  (index++,tgoods.getComment_cnt()        );
            pstmt.setString(index++,tgoods.getBase_goods_code()    );
            pstmt.setString(index++,tgoods.getWeight()             );
            pstmt.setString(index++,tgoods.getDm_yn()              );
            pstmt.setString(index++,tgoods.getForm_code()          );
            pstmt.setString(index++,tgoods.getSize_code()          );
            pstmt.setString(index++,tgoods.getEntp_man_seq()       );

            pstmt.setString(index++,tgoods.getWh_fix_yn()       );
            pstmt.setString(index++,tgoods.getAdvc_refund_yn()       );
            pstmt.setString(index++,tgoods.getOrder_make_yn()       );
            pstmt.setString(index++,tgoods.getInstall_yn()       );

            pstmt.setString(index++,tgoods.getDirect_ship_yn()       );
            pstmt.setString(index++,tgoods.getShip_man_seq()       );
            pstmt.setString(index++,tgoods.getDirect_return_yn()       );
            pstmt.setString(index++,tgoods.getReturn_man_seq()       );
            pstmt.setLong(index++,tgoods.getDely_box_qty()       );

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
            logString.append( tgoods.getSqc_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSqc_gb()             ); logString.append( "/" );
            logString.append( tgoods.getSet_yn()             ); logString.append( "/" );
            logString.append( tgoods.getSet_goods_yn()       ); logString.append( "/" );
            logString.append( tgoods.getGift_yn()            ); logString.append( "/" );
            logString.append( tgoods.getPay_yn()             ); logString.append( "/" );
            logString.append( tgoods.getGift_return_yn()     ); logString.append( "/" );
            logString.append( tgoods.getExch_yn()            ); logString.append( "/" );
            logString.append( tgoods.getReturn_yn()          ); logString.append( "/" );
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
            logString.append( tgoods.getSample_yn()          ); logString.append( "/" );
            logString.append( tgoods.getSale_start_date()    ); logString.append( "/" );
            logString.append( tgoods.getV_volume()           ); logString.append( "/" );
            logString.append( tgoods.getMaster_code()        ); logString.append( "/" );
            logString.append( tgoods.getIn_unit()            ); logString.append( "/" );
            logString.append( tgoods.getIn_unit_qty()        ); logString.append( "/" );
            logString.append( tgoods.getComment_cnt()        ); logString.append( "/" );
            logString.append( tgoods.getBase_goods_code()    ); logString.append( "/" );
            logString.append( tgoods.getWeight()             ); logString.append( "/" );
            logString.append( tgoods.getDm_yn()              ); logString.append( "/" );
            logString.append( tgoods.getForm_code()          ); logString.append( "/" );
            logString.append( tgoods.getSize_code()          ); logString.append( "/" );
            logString.append( tgoods.getEntp_man_seq()       ); logString.append( "/" );

            logString.append( tgoods.getWh_fix_yn()       ); logString.append( "/" );
            logString.append( tgoods.getAdvc_refund_yn()       ); logString.append( "/" );
            logString.append( tgoods.getOrder_make_yn()       ); logString.append( "/" );
            logString.append( tgoods.getInstall_yn()       ); logString.append( "/" );

            logString.append( tgoods.getDirect_ship_yn()       ); logString.append( "/" );
            logString.append( tgoods.getShip_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getDirect_return_yn()       ); logString.append( "/" );
            logString.append( tgoods.getReturn_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getDely_box_qty()       ); logString.append( "/" );

            logString.append( tgoods.getModify_date()        ); logString.append( "/" );
            logString.append( tgoods.getModify_id()          ); logString.append( "/" );
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoods.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

   //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoodsservice
    * </PRE>
    * @param   Connection
    * @param   Setgoods
    * @return  int
    */
    public int insert(Connection con, Tgoodsservice tgoodsservice) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodsservice));
            int index = 1;

            pstmt.setString(index++,tgoodsservice.getGoods_code()         );
            pstmt.setString(index++,tgoodsservice.getAs_term()            );
            pstmt.setString(index++,tgoodsservice.getAs_dely_charge()     );
            pstmt.setString(index++,tgoodsservice.getAs_receive_type()    );
            pstmt.setString(index++,tgoodsservice.getAs_dely_type()       );
            pstmt.setString(index++,tgoodsservice.getAs_com1()            );
            pstmt.setString(index++,tgoodsservice.getAs_com2()            );
            pstmt.setString(index++,tgoodsservice.getAs_repair_term()     );
            pstmt.setString(index++,tgoodsservice.getAs_note()            );
            pstmt.setString(index++,tgoodsservice.getEntp_code()          );
            pstmt.setString(index++,tgoodsservice.getInsert_date()        );
            pstmt.setString(index++,tgoodsservice.getInsert_id()          );
            pstmt.setString(index++,tgoodsservice.getModify_date()        );
            pstmt.setString(index++,tgoodsservice.getModify_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsservice.getGoods_code()      ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_term()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_dely_charge()  ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_receive_type() ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_dely_type()    ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_com1()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_com2()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_repair_term()  ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_note()         ); logString.append( "/" );
            logString.append( tgoodsservice.getEntp_code()       ); logString.append( "/" );
            logString.append( tgoodsservice.getInsert_date()     ); logString.append( "/" );
            logString.append( tgoodsservice.getInsert_id()       ); logString.append( "/" );
            logString.append( tgoodsservice.getModify_date()     ); logString.append( "/" );
            logString.append( tgoodsservice.getModify_id()       ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[tgoodsservice.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsservice.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

   //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoodsdt
    * </PRE>
    * @param   Connection
    * @param   Tgoodsdt
    * @return  int
    */
    public int insert(Connection con, Tgoodsdt tgoodsdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodsdt));
            int index = 1;

            pstmt.setString(index++,tgoodsdt.getGoods_code()         );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_code()       );
            pstmt.setString(index++,tgoodsdt.getGoods_name()         );
            pstmt.setString(index++,tgoodsdt.getColor_code()         );
            pstmt.setString(index++,tgoodsdt.getColor_name()         );
            pstmt.setString(index++,tgoodsdt.getSize_code()          );
            pstmt.setString(index++,tgoodsdt.getSize_name()          );
            pstmt.setString(index++,tgoodsdt.getPattern_code()       );
            pstmt.setString(index++,tgoodsdt.getPattern_name()       );
            pstmt.setString(index++,tgoodsdt.getForm_code()          );
            pstmt.setString(index++,tgoodsdt.getForm_name()          );
            pstmt.setString(index++,tgoodsdt.getOther_text()         );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_info()       );
            pstmt.setString(index++,tgoodsdt.getSale_gb()            );
            pstmt.setString(index++,tgoodsdt.getBarcode()            );
            pstmt.setString(index++,tgoodsdt.getInsert_date()        );
            pstmt.setString(index++,tgoodsdt.getInsert_id()          );
            pstmt.setString(index++,tgoodsdt.getModify_date()        );
            pstmt.setString(index++,tgoodsdt.getModify_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsdt.getGoods_code()      ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_code()    ); logString.append( "/" );
            logString.append( tgoodsdt.getGoods_name()      ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_code()      ); logString.append( "/" );
            logString.append( tgoodsdt.getColor_name()      ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_code()       ); logString.append( "/" );
            logString.append( tgoodsdt.getSize_name()       ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_code()    ); logString.append( "/" );
            logString.append( tgoodsdt.getPattern_name()    ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_code()       ); logString.append( "/" );
            logString.append( tgoodsdt.getForm_name()       ); logString.append( "/" );
            logString.append( tgoodsdt.getOther_text()      ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_info()    ); logString.append( "/" );
            logString.append( tgoodsdt.getSale_gb()         ); logString.append( "/" );
            logString.append( tgoodsdt.getBarcode()         ); logString.append( "/" );
            logString.append( tgoodsdt.getInsert_date()     ); logString.append( "/" );
            logString.append( tgoodsdt.getInsert_id()       ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_date()     ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_id()       ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[tgoodsdt.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsdt.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


   //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Torderstock
    * </PRE>
    * @param   Connection
    * @param   torderstock
    * @return  int
    */
    public int insert(Connection con, Torderstock torderstock) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(torderstock));
            int index = 1;

            pstmt.setString(index++,torderstock.getGoods_code()         );
            pstmt.setString(index++,torderstock.getGoodsdt_code()       );
            pstmt.setLong(index++,torderstock.getOrder_qty()            );
            pstmt.setLong(index++,torderstock.getOut_plan_qty()         );
            pstmt.setLong(index++,torderstock.getBroad_qty()            );
            pstmt.setLong(index++,torderstock.getTot_sale_qty()         );
            pstmt.setString(index++,torderstock.getInsert_date()        );
            pstmt.setString(index++,torderstock.getInsert_id()          );
            pstmt.setString(index++,torderstock.getModify_date()        );
            pstmt.setString(index++,torderstock.getModify_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( torderstock.getGoods_code()      ); logString.append( "/" );
            logString.append( torderstock.getGoodsdt_code()    ); logString.append( "/" );
            logString.append( torderstock.getOrder_qty()       ); logString.append( "/" );
            logString.append( torderstock.getOut_plan_qty()    ); logString.append( "/" );
            logString.append( torderstock.getBroad_qty()       ); logString.append( "/" );
            logString.append( torderstock.getTot_sale_qty()    ); logString.append( "/" );
            logString.append( torderstock.getInsert_date()     ); logString.append( "/" );
            logString.append( torderstock.getInsert_id()       ); logString.append( "/" );
            logString.append( torderstock.getModify_date()     ); logString.append( "/" );
            logString.append( torderstock.getModify_id()       ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[torderstock.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[torderstock.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }



   //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tdelynoarea
    * </PRE>
    * @param   Connection
    * @param   tdelynoarea
    * @return  int
    */
    public int insert(Connection con, Tdelynoarea tdelynoarea) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tdelynoarea));
            int index = 1;

            pstmt.setString(index++,tdelynoarea.getGoods_code()         );
            pstmt.setString(index++,tdelynoarea.getArea_gb()            );
            pstmt.setString(index++,tdelynoarea.getInsert_date()        );
            pstmt.setString(index++,tdelynoarea.getInsert_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdelynoarea.getGoods_code()      ); logString.append( "/" );
            logString.append( tdelynoarea.getArea_gb()         ); logString.append( "/" );
            logString.append( tdelynoarea.getInsert_date()     ); logString.append( "/" );
            logString.append( tdelynoarea.getInsert_id()       ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[tdelynoarea.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tdelynoarea.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

   //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tarskongje
    * </PRE>
    * @param   Connection
    * @param   tarskongje
    * @return  int
    */
    public int insert(Connection con, Tarskongje tarskongje) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tarskongje));
            int index = 1;

            pstmt.setString(index++,tarskongje.getGoods_code()         );
            pstmt.setString(index++,tarskongje.getSeq()                );
            pstmt.setString(index++,tarskongje.getArs_bdate()          );
            pstmt.setString(index++,tarskongje.getArs_edate()          );
            pstmt.setDouble(  index++,tarskongje.getEntp_amt()           );
            pstmt.setDouble(  index++,tarskongje.getCom_amt()            );
            pstmt.setString(index++,tarskongje.getInsert_date()        );
            pstmt.setString(index++,tarskongje.getInsert_id()          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tarskongje.getGoods_code()      ); logString.append( "/" );
            logString.append( tarskongje.getSeq()             ); logString.append( "/" );
            logString.append( tarskongje.getArs_bdate()       ); logString.append( "/" );
            logString.append( tarskongje.getArs_edate()       ); logString.append( "/" );
            logString.append( tarskongje.getEntp_amt()        ); logString.append( "/" );
            logString.append( tarskongje.getCom_amt()         ); logString.append( "/" );
            logString.append( tarskongje.getInsert_date()     ); logString.append( "/" );
            logString.append( tarskongje.getInsert_id()       ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            logSave.info(""+Integer.toString(executedRtn));

        }catch(SQLException se){
            logSave.error("[tarskongje.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tarskongje.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


 //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoodsservice
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int update(Connection con, Tgoodsservice tgoodsservice) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsservice));
            int index = 1;
            pstmt.setString(index++,tgoodsservice.getAs_term()            );
            pstmt.setString(index++,tgoodsservice.getAs_dely_charge()     );
            pstmt.setString(index++,tgoodsservice.getAs_receive_type()    );
            pstmt.setString(index++,tgoodsservice.getAs_dely_type()       );
            pstmt.setString(index++,tgoodsservice.getAs_com1()            );
            pstmt.setString(index++,tgoodsservice.getAs_com2()            );
            pstmt.setString(index++,tgoodsservice.getAs_repair_term()     );
            pstmt.setString(index++,tgoodsservice.getAs_note()            );
            pstmt.setString(index++,tgoodsservice.getEntp_code()          );
            pstmt.setString(index++,tgoodsservice.getModify_date()        );
            pstmt.setString(index++,tgoodsservice.getModify_id()          );
            pstmt.setString(index++,tgoodsservice.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsservice.getAs_term()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_dely_charge()  ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_receive_type() ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_dely_type()    ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_com1()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_com2()         ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_repair_term()  ); logString.append( "/" );
            logString.append( tgoodsservice.getAs_note()         ); logString.append( "/" );
            logString.append( tgoodsservice.getEntp_code()       ); logString.append( "/" );
            logString.append( tgoodsservice.getModify_date()     ); logString.append( "/" );
            logString.append( tgoodsservice.getModify_id()       ); logString.append( "/" );
            logString.append( tgoodsservice.getGoods_code()      ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoodsservice.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoodsservice.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


 //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoodssign
    * </PRE>
    * @param   Connection
    * @param   tgoodssign
    * @return  int
    */
    public int update(Connection con, Tgoodssign tgoodssign) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(tgoodssign));
            int index = 1;
            pstmt.setDouble(index++,tgoodssign.getBuy_price()   );
            pstmt.setDouble(index++,tgoodssign.getBuy_cost()    );
            pstmt.setDouble(index++,tgoodssign.getBuy_vat()     );
            pstmt.setDouble(index++,tgoodssign.getBuy_vat_rate());
            pstmt.setDouble(index++,tgoodssign.getSale_price()  );
            pstmt.setDouble(index++,tgoodssign.getSale_vat()    );
            pstmt.setDouble(index++,tgoodssign.getSale_vat_rate());
            pstmt.setDouble(index++,tgoodssign.getCust_price()  );
            pstmt.setDouble(index++,tgoodssign.getSaveamt_rate());
            pstmt.setDouble(index++,tgoodssign.getSaveamt()     );
            pstmt.setString(index++,tgoodssign.getGoods_code()  );
            pstmt.setString(index++,tgoodssign.getSign_seq()    );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodssign.getBuy_price()       ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_cost()        ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_vat()         ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_vat_rate()    ); logString.append( "/" );
            logString.append( tgoodssign.getSale_price()      ); logString.append( "/" );
            logString.append( tgoodssign.getSale_vat()        ); logString.append( "/" );
            logString.append( tgoodssign.getSale_vat_rate()   ); logString.append( "/" );
            logString.append( tgoodssign.getCust_price()      ); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt_rate()    ); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt()         ); logString.append( "/" );
            logString.append( tgoodssign.getGoods_code()      ); logString.append( "/" );
            logString.append( tgoodssign.getSign_seq()        ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoodssign.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoodssign.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

 //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoodsdt
    * </PRE>
    * @param   Connection
    * @param   tgoodsdt
    * @return  int
    */
    public int update(Connection con, Tgoodsdt tgoodsdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

//= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsdt));
            int index = 1;
            pstmt.setString(index++,tgoodsdt.getColor_code()   );
            pstmt.setString(index++,tgoodsdt.getColor_name()   );
            pstmt.setString(index++,tgoodsdt.getSize_code()    );
            pstmt.setString(index++,tgoodsdt.getSize_name()    );
            pstmt.setString(index++,tgoodsdt.getPattern_code() );
            pstmt.setString(index++,tgoodsdt.getPattern_name() );
            pstmt.setString(index++,tgoodsdt.getForm_code()    );
            pstmt.setString(index++,tgoodsdt.getForm_name()    );
            pstmt.setString(index++,tgoodsdt.getOther_text()   );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_info() );
            pstmt.setString(index++,tgoodsdt.getBarcode() );
            pstmt.setString(index++,tgoodsdt.getModify_date()  );
            pstmt.setString(index++,tgoodsdt.getModify_id()    );
            pstmt.setString(index++,tgoodsdt.getGoods_code()   );
            pstmt.setString(index++,tgoodsdt.getGoodsdt_code() );

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
            logString.append( tgoodsdt.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoodsdt.getGoodsdt_code()   ); logString.append( "/" );
            logString.append( tgoodsdt.getBarcode()        ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_date()    ); logString.append( "/" );
            logString.append( tgoodsdt.getModify_id()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoodssign.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoodssign.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

 //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoods SALE_GB
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int updateSaleGb(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdateSaleGb(tgoods));
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code()   );
            pstmt.setString(index++,tgoods.getSale_gb() );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoods.getSale_gb()        ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoods.updateSaleGb() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.updateSaleGb() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoodssign
    * </PRE>
    * @param   Connection
    * @param   Setgoods
    * @return  int
    */
    public int insert(Connection con, Tgoodssign tgoodssign) throws StoreException{//★
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodssign));
            int index = 1;
            pstmt.setString(index++,tgoodssign.getGoods_code()    );
            pstmt.setString(index++,tgoodssign.getSign_seq()      );
            pstmt.setString(index++,tgoodssign.getApply_date()    );
            pstmt.setDouble(index++,tgoodssign.getBuy_price()     );
            pstmt.setDouble(index++,tgoodssign.getBuy_cost()      );
            pstmt.setDouble(index++,tgoodssign.getBuy_vat()       );
            pstmt.setDouble(index++,tgoodssign.getBuy_vat_rate()  );
            pstmt.setDouble(index++,tgoodssign.getSale_price()    );
            pstmt.setDouble(index++,tgoodssign.getSale_vat()      );
            pstmt.setDouble(index++,tgoodssign.getSale_vat_rate() );
            pstmt.setDouble(index++,tgoodssign.getCust_price()    );
            pstmt.setDouble(index++,tgoodssign.getSaveamt_rate()  );
            pstmt.setDouble(index++,tgoodssign.getSaveamt()       );
            pstmt.setString(index++,tgoodssign.getSign_gb()       );
            pstmt.setString(index++,tgoodssign.getInsert_date()   );
            pstmt.setString(index++,tgoodssign.getInsert_id()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodssign.getGoods_code()   ); logString.append( "/" );
            logString.append( tgoodssign.getSign_seq()     ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_price()    ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_cost()     ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_vat()      ); logString.append( "/" );
            logString.append( tgoodssign.getBuy_vat_rate() ); logString.append( "/" );
            logString.append( tgoodssign.getSale_price()   ); logString.append( "/" );
            logString.append( tgoodssign.getSale_vat()     ); logString.append( "/" );
            logString.append( tgoodssign.getSale_vat_rate()); logString.append( "/" );
            logString.append( tgoodssign.getCust_price()   ); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt_rate() ); logString.append( "/" );
            logString.append( tgoodssign.getSaveamt()      ); logString.append( "/" );
            logString.append( tgoodssign.getSign_gb()      ); logString.append( "/" );
            logString.append( tgoodssign.getInsert_date()  ); logString.append( "/" );
            logString.append( tgoodssign.getInsert_id()    ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodssign.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodssign.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tdelynoarea )
    * </PRE>
    * @param   tdelynoarea
    * @return  String
    */
    private final String deleteSqlTdelynoarea =  " DELETE FROM TDELYNOAREA WHERE GOODS_CODE = ? AND AREA_GB = ?  \n ";

    private int deleteSqlLogTdelynoarea =  0;

    private String makeSqlDelete(Tdelynoarea tdelynoarea) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTdelynoarea == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTdelynoarea);
            }
            deleteSqlLogTdelynoarea = 1;
        }
        return deleteSqlTdelynoarea;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tcategorygoods )
    * </PRE>
    * @param   tcategorygoods
    * @return  String
    */
    private final String deleteSqlTcategorygoods =  " DELETE FROM TCATEGORYGOODS WHERE CATEGORY_CODE = ? AND GOODS_CODE = ?   \n ";

    private int deleteSqlLogTcategorygoods=  0;

    private String makeSqlDelete(Tcategorygoods tcategorygoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTcategorygoods);
            }
            deleteSqlLogTcategorygoods = 1;
        }
        return deleteSqlTcategorygoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tpresentation )
    * </PRE>
    * @param   tpresentation
    * @return  String
    */
    private final String deleteSqlTpresentation =  " DELETE FROM TPRESENTATION WHERE PRESENTATION_CODE = ?   \n ";

    private int deleteSqlLogTpresentation=  0;

    private String makeSqlDelete(Tpresentation tpresentation) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTpresentation);
            }
            deleteSqlLogTpresentation = 1;
        }
        return deleteSqlTpresentation;
    }
    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tdelynoarea
    * </PRE>
    * @param   Connection
    * @param   tdelynoarea
    * @return  int
    */
    public int delete(Connection con, Tdelynoarea tdelynoarea) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tdelynoarea));
            int index = 1;
            pstmt.setString(index++,tdelynoarea.getGoods_code());
            pstmt.setString(index++,tdelynoarea.getArea_gb());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdelynoarea.getGoods_code()   ); logString.append( "/" );
            logString.append( tdelynoarea.getArea_gb()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tdelynoarea.delete(tdelynoarea) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tdelynoarea.delete(tdelynoarea) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategorygoods
    * </PRE>
    * @param   Connection
    * @param   tcategorygoods
    * @return  int
    */
    public int delete(Connection con, Tpresentation tpresentation) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tpresentation));
            int index = 1;
            pstmt.setString(index++,tpresentation.getPresentation_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpresentation.getPresentation_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tpresentation.delete(tpresentation) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tpresentation.delete(tpresentation) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tpresentation
    * </PRE>
    * @param   Connection
    * @param   tdelynoarea
    * @return  int
    */
    public int delete(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tcategorygoods));
            int index = 1;
            pstmt.setString(index++,tcategorygoods.getCategory_code());
            pstmt.setString(index++,tcategorygoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategorygoods.getCategory_code()   ); logString.append( "/" );
            logString.append( tcategorygoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tcategorygoods.delete(tcategorygoods) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tcategorygoods.delete(tcategorygoods) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoods )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoods =  " DELETE FROM TGOODS WHERE GOODS_CODE = ? AND SIGN_GB <> '80'  \n ";

    private int deleteSqlLogTgoods =  0;

    private String makeSqlDeleteTgoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoods);
            }
            deleteSqlLogTgoods = 1;
        }
        return deleteSqlTgoods;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoods(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoods());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoods.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoods.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodsdt )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodsdt =  " DELETE FROM TGOODSDT WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodsdt =  0;

    private String makeSqlDeleteTgoodsdt() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsdt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsdt);
            }
            deleteSqlLogTgoodsdt = 1;
        }
        return deleteSqlTgoodsdt;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsdt
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteToodsdt(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodsdt());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodsdt.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsdt.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Torderstock )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTorderstock =  " DELETE FROM TORDERSTOCK WHERE GOODS_CODE = ? \n ";

    private int deleteSqlLogTorderstock =  0;

    private String makeSqlDeleteTorderstock() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTorderstock == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTorderstock);
            }
            deleteSqlLogTorderstock = 1;
        }
        return deleteSqlTorderstock;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Torderstock
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTorderstock(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTorderstock());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            //executedRtn = pstmt.executeUpdate();
            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[torderstock.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[torderstock.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tinplanqty )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTinplanqty =  " DELETE FROM TINPLANQTY WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTinplanqty =  0;

    private String makeSqlDeleteTinplanqty() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTinplanqty == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTinplanqty);
            }
            deleteSqlLogTinplanqty = 1;
        }
        return deleteSqlTinplanqty;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tinplanqty
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTinplanqty(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTinplanqty());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tinplanqty.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tinplanqty.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tarskongje )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodsservice =  " DELETE FROM TGOODSSERVICE WHERE GOODS_CODE = ? \n ";

    private int deleteSqlLogTgoodsservice =  0;

    private String makeSqlDeleteTgoodsservice() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsservice == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsservice);
            }
            deleteSqlLogTgoodsservice = 1;
        }
        return deleteSqlTgoodsservice;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsservice
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodsservice(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodsservice());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[Tgoodsservice.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoodsservice.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodssign )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodssign =  " DELETE FROM TGOODSSIGN WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodssign =  0;

    private String makeSqlDeleteTgoodssign() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodssign == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodssign);
            }
            deleteSqlLogTgoodssign = 1;
        }
        return deleteSqlTgoodssign;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodssign
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodssign(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodssign());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodssign.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodssign.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tarskongje )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTarskongjeAll =  " DELETE FROM TARSKONGJE WHERE GOODS_CODE = ? \n ";

    private int deleteSqlLogTarskongjeAll =  0;

    private String makeSqlDeleteTarskongjeAll() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTarskongjeAll == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTarskongjeAll);
            }
            deleteSqlLogTarskongjeAll = 1;
        }
        return deleteSqlTarskongjeAll;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tarskongje
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTarskongjeAll(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTarskongjeAll());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[Tarskongje.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tarskongje.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodsprice )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodsprice =  " DELETE FROM TGOODSPRICE WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodsprice =  0;

    private String makeSqlDeleteTgoodsprice() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsprice == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsprice);
            }
            deleteSqlLogTgoodsprice = 1;
        }
        return deleteSqlTgoodsprice;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsprice
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodsprice(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodsprice());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodsprice.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsprice.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tsalenogoods )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTsalenogoods =  " DELETE FROM TSALENOGOODS WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTsalenogoods =  0;

    private String makeSqlDeleteTsalenogoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTsalenogoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTsalenogoods);
            }
            deleteSqlLogTsalenogoods = 1;
        }
        return deleteSqlTsalenogoods;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tsalenogoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTsalenogoods(Connection con,  Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTsalenogoods());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tsalenogoods.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tsalenogoods.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodslink )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodslink =  " DELETE FROM TGOODSLINK WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodslink =  0;

    private String makeSqlDeleteTgoodslink() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodslink == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodslink);
            }
            deleteSqlLogTgoodslink = 1;
        }
        return deleteSqlTgoodslink;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodslink
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodslink(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodslink());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodslink.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodslink.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodslink )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodslink1 =  " DELETE FROM TGOODSLINK WHERE LINK_GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodslink1 =  0;

    private String makeSqlDeleteTgoodslink1() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodslink1 == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodslink1);
            }
            deleteSqlLogTgoodslink1 = 1;
        }
        return deleteSqlTgoodslink1;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodslink link_goods_code
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodslink1(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodslink1());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodslink.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodslink.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tdescribe )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTdescribe =  " DELETE FROM TDESCRIBE WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTdescribe =  0;

    private String makeSqlDeleteTdescribe() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTdescribe == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTdescribe);
            }
            deleteSqlLogTdescribe = 1;
        }
        return deleteSqlTdescribe;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tdescribe
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTdescribe(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTdescribe());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tdescribe.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tdescribe.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodsimage )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodsimage =  " DELETE FROM TGOODSIMAGE WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodsimage =  0;

    private String makeSqlDeleteTgoodsimage() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsimage == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsimage);
            }
            deleteSqlLogTgoodsimage = 1;
        }
        return deleteSqlTgoodsimage;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsimage
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodsimage(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodsimage());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodsimage.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsimage.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tars )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTars =  " DELETE FROM TARS WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTars =  0;

    private String makeSqlDeleteTars() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTars == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTars);
            }
            deleteSqlLogTars = 1;
        }
        return deleteSqlTars;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tars
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTars(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTars());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tars.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tars.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tarskongje )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTarskongje =  " DELETE FROM TARSKONGJE WHERE GOODS_CODE = ? AND AREA_GB = ? \n ";

    private int deleteSqlLogTarskongje =  0;

    private String makeSqlDeleteTarskongje() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTarskongje == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTarskongje);
            }
            deleteSqlLogTarskongje = 1;
        }
        return deleteSqlTarskongje;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tarskongje
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTarskongje(Connection con, Tdelynoarea tdelynoarea) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTarskongje());
            int index = 1;
            pstmt.setString(index++,tdelynoarea.getGoods_code());
            pstmt.setString(index++,tdelynoarea.getArea_gb());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdelynoarea.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tdelynoarea.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tdelynoarea.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tasentp )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTasentp =  " DELETE FROM TASENTP WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTasentp =  0;

    private String makeSqlDeleteTasentp() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTasentp == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTasentp);
            }
            deleteSqlLogTasentp = 1;
        }
        return deleteSqlTasentp;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tasentp
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTasentp(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTasentp());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tasentp.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tasentp.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tdelynoarea )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTdelynoareaGoods =  " DELETE FROM TDELYNOAREA WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTdelynoareaGoods =  0;

    private String makeSqlDeleteTdelynoareaGoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTdelynoareaGoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTdelynoareaGoods);
            }
            deleteSqlLogTdelynoareaGoods = 1;
        }
        return deleteSqlTdelynoareaGoods;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tdelynoarea
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTdelynoareaGoods(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTdelynoareaGoods());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tdelynoarea.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tdelynoarea.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodsshare )
    * </PRE>
    * @param
    * @return  String
    */
    private final String deleteSqlTgoodsshare =  " DELETE FROM TGOODSSHARE WHERE GOODS_CODE = ?  \n ";

    private int deleteSqlLogTgoodsshare =  0;

    private String makeSqlDeleteTgoodsshare() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsshare == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsshare);
            }
            deleteSqlLogTgoodsshare = 1;
        }
        return deleteSqlTgoodsshare;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsshare
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int deleteTgoodsshare(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteTgoodsshare());
            int index = 1;
            pstmt.setString(index++,tgoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[tgoodsshare.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tgoodsshare.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDispGoodsCnt( Tcategorygoods tcategorygoods ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(1) AS DISPLAY_GOODS_CNT \n");
        sb.append("   FROM TCATEGORYGOODS \n");
        sb.append("  WHERE CATEGORY_CODE = ? \n");
        sb.append("    AND DISPLAY_YN    = '1' \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
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
    public int retrieveDispGoodsCnt(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCnt(tcategorygoods));

            int index = 1;
            pstmt.setString(index++,  tcategorygoods.getCategory_code());

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

            log.info("[GoodsSvc.retrieveDispGoodsCnt category_code : "+tcategorygoods.getCategory_code());
            log.info("[GoodsSvc.retrieveDispGoodsCnt dispGoodsCnt : "+dispGoodsCnt);

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDispGoodsCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDispGoodsCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategoryurl
    * @return  String
    */

    private final String updateSqlDispGoodsCnt  =" UPDATE TCATEGORY SET   \n"
                                                +"        DISPLAY_GOODS_CNT  = ?, \n"
                                                +"        MODIFY_DATE        = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS') \n"
                                                +"  WHERE CATEGORY_CODE   = ? \n"
                                                +"    AND CATEGORY_GB     = ? \n" ;

    private int updateSqlLogDispGoodsCnt =  0;

    private String makeSqlUpdateDispGoodsCnt() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogDispGoodsCnt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlDispGoodsCnt);
            }
            updateSqlLogDispGoodsCnt = 1;
        }
        return updateSqlDispGoodsCnt;
    }
    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  int
    */
    public int updateDispGoodsCnt(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDispGoodsCnt());

            int index = 1;
            pstmt.setString(index++,tcategory.getDisplay_goods_cnt());
            pstmt.setString(index++,tcategory.getModify_date());
            pstmt.setString(index++,tcategory.getCategory_code());
            pstmt.setString(index++,tcategory.getCategory_gb());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getDisplay_goods_cnt()      ); logString.append( "/" );
            logString.append( tcategory.getModify_date()  ); logString.append( "/" );
            logString.append( tcategory.getCategory_code()  ); logString.append( "/" );
            logString.append( tcategory.getCategory_gb()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsSvc.updateDispGoodsCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsSvc.updateDispGoodsCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    private String makeSqlGetP( Tcategory tcategory ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT P_CATEGORY_CODE \n");
        sb.append("   FROM TCATEGORY \n");
        sb.append("  WHERE CATEGORY_CODE = ? \n");
        sb.append("    AND CATEGORY_GB     = ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    public String getPcategory(Connection con, Tcategory tcategory) throws StoreException{

        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String p_category_code = "";

        try {
            pstmt = con.prepareStatement(makeSqlGetP(tcategory));

            int index = 1;
            pstmt.setString(index++,  tcategory.getCategory_code());
            pstmt.setString(index++,  tcategory.getCategory_gb());

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) p_category_code = rset.getString(1);

        }catch(SQLException se){
            log.error("[GoodsSvc.getPcategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.getPcategory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return p_category_code;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDispGoodsCntSum( Tcategory tcategory ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT SUM(DISPLAY_GOODS_CNT) AS DISPLAY_GOODS_CNT \n");
        sb.append("   FROM TCATEGORY \n");
        sb.append("  WHERE P_CATEGORY_CODE = ? \n");
        sb.append("    AND CATEGORY_GB     like ?||'%' \n");
        sb.append("    AND DISPLAY_YN      = '1' \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
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
    public int retrieveDispGoodsCntSum(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCntSum = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCntSum(tcategory));

            int index = 1;
            pstmt.setString(index++,  tcategory.getCategory_code());
            pstmt.setString(index++,  tcategory.getCategory_gb());

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCntSum = rset.getInt(1);

            log.info("[GoodsSvc.retrieveDispGoodsCntSum category_code : "+tcategory.getCategory_code());
            log.info("[GoodsSvc.retrieveDispGoodsCntSum category_gb : "+tcategory.getCategory_gb());
            log.info("[GoodsSvc.retrieveDispGoodsCntSum dispGoodsCntSum : "+dispGoodsCntSum);

        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveDispGoodsCntSum() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveDispGoodsCntSum() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCntSum;
    }

  //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlQA( RetrieveModel retrieve ) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT  SEQ_QA_NO,\n");
        sb.append("        GOODS_CODE, \n");
        sb.append("        TO_CHAR(REQ_DATE, 'YYYY/MM/DD') AS REQ_DATE, \n");
        sb.append("        TO_CHAR(HOPE_DATE, 'YYYY/MM/DD') AS HOPE_DATE, \n");
        sb.append("        REQ_FLAG, \n");
        sb.append("        REQ_NOTE, \n");
        sb.append("        SQC_GB\n");
        sb.append("FROM    TBEFOREQAM\n");
        sb.append("WHERE   GOODS_CODE = ?\n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("goods_code  : " + retrieve.getString("goods_code"));
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
    public RetrieveModel retrieveQA(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        try {
            pstmt = con.prepareStatement(makeSqlQA(retrieve));
            int index = 1;
            pstmt.setString(index++,  retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_QA", makeSheet(rset));
        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveQA() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveQA() Exception : ERR-"+e.getMessage());
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
    public String makeSqlSeqOfQA() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SEQ_QA_NO.NEXTVAL AS SEQ_QA_NO FROM DUAL\n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
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
    public String retrieveSeqOfQA(Connection con) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String tmpSeq=null;
        try {
            pstmt = con.prepareStatement(makeSqlSeqOfQA());
            rset  = pstmt.executeQuery();
            if(rset!=null&&rset.next()){
            	tmpSeq=rset.getString("SEQ_QA_NO");
            }
        }catch(SQLException se){
            log.error("[GoodsSvc.retrieveSeqOfQA() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.retrieveSeqOfQA() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return tmpSeq;
    }
  //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlInsertQA(Tbeforeqam qa) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO TBEFOREQAM(\n");
        sb.append("        SEQ_QA_NO,\n");
        sb.append("        GOODS_CODE,\n");
        sb.append("        REQ_DATE,\n");
        sb.append("        HOPE_DATE,\n");
        sb.append("        REQ_FLAG,\n");
        sb.append("        REQ_NOTE,\n");
        sb.append("        SQC_GB,\n");
        sb.append("        INSERT_ID,\n");
        sb.append("        INSERT_DATE,\n");
        sb.append("        MODIFY_ID,\n");
        sb.append("        MODIFY_DATE)\n");
        sb.append("VALUES (\n");
        sb.append("        ?,\n");
        sb.append("        ?,\n");
        sb.append("        SYSDATE,\n");
        sb.append("        TO_DATE(?,'YYYY/MM/DD'),\n");
        sb.append("        ?,\n");
        sb.append("        ?,\n");
        sb.append("        '00',\n");
        sb.append("        ?,\n");
        sb.append("        SYSDATE,\n");
        sb.append("        ?,\n");
        sb.append("        SYSDATE\n");
        sb.append(")\n");
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
        	logSave.debug("\n" + sb.toString());
        	logSave.debug("seq_qa_no  : " + qa.getSeq_qa_no());
        	logSave.debug("goods_code  : " + qa.getGoods_code());
        	logSave.debug("hope_date  : " + qa.getHope_date());
        	logSave.debug("req_flag  : " + qa.getReq_flag());
        	logSave.debug("req_note  : " + qa.getReq_note());
        	logSave.debug("insert_id  : " + qa.getInsert_id());
        	logSave.debug("modify_id  : " + qa.getModify_id());
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
    public int insert(Connection con, Tbeforeqam qa) throws StoreException{
        PreparedStatement pstmt       = null;
        int rtn=0;
        try {
            pstmt = con.prepareStatement(makeSqlInsertQA(qa));
            int index = 1;
            pstmt.setString(index++,  qa.getSeq_qa_no());
            pstmt.setString(index++,  qa.getGoods_code());
            pstmt.setString(index++,  qa.getHope_date());
            pstmt.setString(index++,  qa.getReq_flag());
            pstmt.setString(index++,  qa.getReq_note());
            pstmt.setString(index++,  qa.getInsert_id());
            pstmt.setString(index++,  qa.getModify_id());
            rtn=pstmt.executeUpdate();
        }catch(SQLException se){
            log.error("[GoodsSvc.insert(Tbeforeqam) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.insert(Tbeforeqam) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return rtn;
    }
  //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlInsertQADT(Tbeforeqam qa) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO TBEFOREQADT(\n");
        sb.append("        SEQ_QA_NO,\n");
        sb.append("        INSPECT_LCODE,\n");
        sb.append("        INSPECT_MCODE,\n");
        sb.append("        NEEDS_YN,\n");
        sb.append("        ACTION_YN,\n");
        sb.append("        CONF_YN,\n");
        sb.append("        INSERT_ID,\n");
        sb.append("        INSERT_DATE,\n");
        sb.append("        MODIFY_ID,\n");
        sb.append("        MODIFY_DATE)\n");
        sb.append("(SELECT ?,\n");
        sb.append("       INSPECT_LCODE, INSPECT_MCODE,\n");
        sb.append("       0,\n");
        sb.append("       0,\n");
        sb.append("       0,\n");
        sb.append("       ?,\n");
        sb.append("       SYSDATE,\n");
        sb.append("       ?,\n");
        sb.append("       SYSDATE\n");
        sb.append("FROM TINSPECT_GOODS_GRP \n");
        sb.append("WHERE LMSD_CODE = ? \n");
        sb.append("AND USE_YN = '1') \n");
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
        	logSave.debug("\n" + sb.toString());
        	logSave.debug("seq_qa_no  : " + qa.getSeq_qa_no());
            logSave.debug("insert_id  : " + qa.getInsert_id());
            logSave.debug("modify_id  : " + qa.getModify_id());
            logSave.debug("lmsd_code  : " + qa.getLmsdCode());
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
    public int insertQADt(Connection con, Tbeforeqam qa) throws StoreException{
        PreparedStatement pstmt       = null;
        int rtn=0;
        try {
            pstmt = con.prepareStatement(makeSqlInsertQADT(qa));
            int index = 1;
            pstmt.setString(index++,  qa.getSeq_qa_no());
            pstmt.setString(index++,  qa.getInsert_id());
            pstmt.setString(index++,  qa.getModify_id());
            pstmt.setString(index++,  qa.getLmsdCode());
            rtn=pstmt.executeUpdate();
        }catch(SQLException se){
            log.error("[GoodsSvc.insertQADt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsSvc.insertQADt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return rtn;
    }
    private String makeSqlDelete(Tbeforeqam tbeforeqam) throws StoreException{
    	StringBuffer sb=new StringBuffer();
    	sb.append(" DELETE FROM TBEFOREQAM WHERE SEQ_QA_NO = ? AND GOODS_CODE = ? ");
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }
    public int delete(Connection con, Tbeforeqam tbeforeqam) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;
        try {
            pstmt = con.prepareStatement(makeSqlDelete(tbeforeqam));
            int index = 1;
            pstmt.setString(index++,tbeforeqam.getSeq_qa_no());
            pstmt.setString(index++,tbeforeqam.getGoods_code());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeforeqam.getSeq_qa_no()   ); logString.append( "/" );
            logString.append( tbeforeqam.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[GoodsSvc.delete(Tbeforeqam) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsSvc.delete(Tbeforeqam) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    private String makeSqlDeleteQaDt(Tbeforeqam tbeforeqam) throws StoreException{
    	StringBuffer sb=new StringBuffer();
    	sb.append(" DELETE FROM TBEFOREQADT WHERE SEQ_QA_NO = ? ");
        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }
    public int deleteQaDt(Connection con, Tbeforeqam tbeforeqam) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;
        try {
            pstmt = con.prepareStatement(makeSqlDeleteQaDt(tbeforeqam));
            int index = 1;
            pstmt.setString(index++,tbeforeqam.getSeq_qa_no());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeforeqam.getSeq_qa_no()   ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();
        }catch(SQLException se){
            logSave.error("[tpresentation.deleteQaDt(Tbeforeqam) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[tpresentation.deleteQaDt(Tbeforeqam) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 업체매입부가세비율
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlEntpBuyVatRate( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT EP.ENTP_CODE, NVL(TAX_RATE, VAL) AS BUY_VAT_RATE	\n");
        sb.append("  FROM TENTERPRISE EP, TCONFIG CF						\n");
        sb.append(" WHERE EP.ENTP_CODE = ?									\n");
        sb.append("   AND CF.ITEM = 'DEF_VAT_BUY_RATE'						\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

	public RetrieveModel retrieveEntpBuyVatRate(Connection con, RetrieveModel retrieve) throws StoreException{
		PreparedStatement pstmt       = null;
	    ResultSet         rset        = null;
	    HashMap result = new HashMap();
	    try {
	        pstmt = con.prepareStatement(makeSqlEntpBuyVatRate(retrieve));

	        int index = 1;
	        pstmt.setString(index++, retrieve.getString("entp_code"));
	        rset  = pstmt.executeQuery();

	        if (rset.next()) {
	        	result.put("BUY_VAT_RATE", rset.getString("BUY_VAT_RATE"));
	        }
	        result.put("ENTP_CODE", retrieve.getString("entp_code"));
	        retrieve.put("RESULT_BUY_VAT_RATE", result);

	    }catch(SQLException se){
	        log.error("[GoodsSvc.retrieveEntpBuyVatRate() SQLException : ERR-"+se.getErrorCode()+":"+se);
	        throw new StoreException(se.getMessage());
	    }catch(Exception e){
	        log.error("[GoodsSvc.retrieveEntpBuyVatRate() Exception : ERR-"+e.getMessage());
	        throw new StoreException(e.getMessage());
	    }finally {
	        DBUtils.freeConnection(null, pstmt, rset);
	    }
	    return retrieve;
    }
}