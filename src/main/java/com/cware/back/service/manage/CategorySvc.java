
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

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tcategory;
import com.cware.back.entity.table.Tcategorygoods;
import com.cware.back.entity.table.Tcategoryurl;
import com.cware.back.entity.table.Tpresentation;

/**
 * Register Category Service class
 *
 * @version 1.0, 2006/07/26
 */
public class CategorySvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public CategorySvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

/*
        sb.append(" SELECT CATEGORY_NAME, \n");
        sb.append("        CATEGORY_CODE, \n");
        sb.append("        P_CATEGORY_CODE, \n");
        sb.append("        CATEGORY_GB, \n");
        sb.append("        DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,1,1)),0, 1, \n");
        sb.append("               DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,2,2)),0, 2, \n");
        sb.append("               DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,4,2)),0, 3, \n");
        sb.append("               DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,6,3)),0, 4, 5)))) \n");
        sb.append("        AS DEPTH, \n");
        sb.append("        DISPLAY_PRIORITY, \n");
        sb.append("        FUN_CATEGORY_DISP(CATEGORY_CODE) AS CT_SORT \n");
        sb.append("   FROM TCATEGORY \n");
        sb.append("  WHERE DISPLAY_YN = '1' \n");
        sb.append("  START WITH P_CATEGORY_CODE = 'ROOT' \n");
        sb.append("CONNECT BY PRIOR CATEGORY_CODE = P_CATEGORY_CODE \n");
*/

        sb.append("  SELECT CATEGORY_NAME,  \n");
        sb.append("         CATEGORY_CODE,  \n");
        sb.append("         P_CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DEPTH,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         CT_SORT  \n");
        sb.append("    FROM (  \n");
        sb.append("           SELECT CATEGORY_NAME,   \n");
        sb.append("                  CATEGORY_CODE,   \n");
        sb.append("                  P_CATEGORY_CODE,   \n");
        sb.append("                  CATEGORY_GB,   \n");
        sb.append("                  DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,1,1)),0, 1,   \n");
        sb.append("                         DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,2,2)),0, 2,   \n");
        sb.append("                         DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,4,2)),0, 3,   \n");
        sb.append("                         DECODE(TO_NUMBER(SUBSTR(CATEGORY_CODE,6,3)),0, 4, 5))))   \n");
        sb.append("                  AS DEPTH,   \n");
        sb.append("                  DISPLAY_PRIORITY,  \n");
        sb.append("                  FUN_CATEGORY_DISP(CATEGORY_CODE) AS CT_SORT   \n");
        sb.append("             FROM TCATEGORY   \n");
        sb.append("            START WITH P_CATEGORY_CODE = 'ROOT'   \n");
        sb.append("          CONNECT BY PRIOR CATEGORY_CODE = P_CATEGORY_CODE   \n");
        sb.append("         )  \n");
        sb.append("   ORDER BY CT_SORT  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoods( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         A.DISPLAY_YN,  \n");
        sb.append("         A.DISPLAY_PRIORITY,  \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("         A.INSERT_ID,  \n");
        sb.append("         TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("         A.MODIFY_ID,  \n");
        sb.append("         C.BUY_COST,  \n");
        sb.append("         C.BUY_VAT,  \n");
        sb.append("         C.SALE_PRICE,  \n");
        sb.append("         B.ENTP_CODE,  \n");
        sb.append("         E.ENTP_NAME,  \n");
        sb.append("         B.MAKECO_CODE,  \n");
        sb.append("         F.MAKECO_NAME,  \n");
        sb.append("         G.BRAND_NAME,  \n");
        sb.append("         B.SET_YN,  \n");
        sb.append("         B.SALE_GB,  \n");
        sb.append("         B.SIGN_GB,  \n");
        sb.append("         TO_CHAR(A.DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(A.DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE  \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TGOODS         B,  \n");
        sb.append("         TGOODSPRICE    C,  \n");
        sb.append("         TENTERPRISE    E,  \n");
        sb.append("         TMAKECOMP      F,  \n");
        sb.append("         TBRAND         G  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODS_CODE = C.GOODS_CODE  \n");
        sb.append("     AND C.APPLY_DATE = ( SELECT MAX(D.APPLY_DATE)  \n");
        sb.append("                            FROM TGOODSPRICE D  \n");
        sb.append("                           WHERE D.APPLY_DATE <= SYSDATE  \n");
        sb.append("                             AND D.GOODS_CODE = A.GOODS_CODE)  \n");
        sb.append("     AND B.ENTP_CODE     = E.ENTP_CODE  \n");
        sb.append("     AND B.MAKECO_CODE   = F.MAKECO_CODE  \n");
        sb.append("     AND B.BRAND_CODE    = G.BRAND_CODE  \n");
        sb.append("     AND A.CATEGORY_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code : " + retrieve.getString("category_code") );
        }
        return sb.toString();
    }


    private String makeSqlPresentation( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.PRESENTATION_CODE,  \n");
        sb.append("         A.PRESENTATION_KINDS,  \n");
        sb.append("         A.PRESENTATION_GB,  \n");
        sb.append("         A.CATEGORY_CODE,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         A.DISPLAY_YN,  \n");
        sb.append("         A.DISPLAY_PRIORITY,  \n");
        sb.append("         A.PRESENTATION_DESC,  \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("         A.INSERT_ID,  \n");
        sb.append("         TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("         A.MODIFY_ID,  \n");
        sb.append("         B.SET_YN,  \n");
        sb.append("         C.BUY_COST,  \n");
        sb.append("         C.BUY_VAT,  \n");
        sb.append("         C.SALE_PRICE,  \n");
        sb.append("         B.SALE_GB,  \n");
        sb.append("         TO_CHAR(A.DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(A.DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE  \n");
        sb.append("    FROM TPRESENTATION A,  \n");
        sb.append("         TGOODS B,  \n");
        sb.append("         TGOODSPRICE C  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODS_CODE = C.GOODS_CODE  \n");
        sb.append("     AND C.APPLY_DATE = ( SELECT MAX(D.APPLY_DATE)  \n");
        sb.append("                            FROM TGOODSPRICE D  \n");
        sb.append("                           WHERE D.APPLY_DATE <= SYSDATE  \n");
        sb.append("                             AND D.GOODS_CODE = A.GOODS_CODE)  \n");
        sb.append("     AND A.CATEGORY_CODE      = ?  \n");
        sb.append("     AND A.PRESENTATION_KINDS = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code      : " + retrieve.getString("category_code") );
            log.debug("presentation_kinds : " + retrieve.getString("presentation_kinds") );
        }
        return sb.toString();
    }


    private String makeSqlCategoryInfo( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         A.CATEGORY_NAME,  \n");
        sb.append("         A.P_CATEGORY_CODE,  \n");
        sb.append("         A.DISPLAY_YN,  \n");
        sb.append("         A.DISPLAY_PRIORITY,  \n");
        sb.append("         A.CATEGORY_GB,  \n");
        sb.append("         A.DISPLAY_GOODS_CNT,  \n");
        sb.append("         A.INSERT_DATE,  \n");
        sb.append("         A.INSERT_ID,  \n");
        sb.append("         A.MODIFY_DATE,  \n");
        sb.append("         A.MODIFY_ID  \n");
//        sb.append("         A.MODIFY_ID,  \n");
//        sb.append("            '' P_CATEGORY_NAME  \n");
        sb.append("    FROM TCATEGORY A  \n");
        sb.append("   WHERE A.CATEGORY_CODE = ?  \n");
        sb.append("     AND A.CATEGORY_GB   = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code : " + retrieve.getString("category_code") );
            log.debug("category_gb   : " + retrieve.getString("category_gb") );
        }
        return sb.toString();
    }


    private String makeSqlCategoryUrl( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT  CATEGORYURL_CODE,  \n");
        sb.append("          CATEGORYURL_KINDS,  \n");
        sb.append("          CATEGORY_CODE,  \n");
        sb.append("          NAME,  \n");
        sb.append("          TARGET_URL,  \n");
        sb.append("          IMAGE_URL,  \n");
        sb.append("          DISPLAY_YN,  \n");
        sb.append("          DISPLAY_PRIORITY,  \n");
        sb.append("          TO_CHAR(INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("          INSERT_ID,  \n");
        sb.append("          TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("          MODIFY_ID,  \n");
        sb.append("          TO_CHAR(DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("          TO_CHAR(DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE  \n");
        sb.append("     FROM TCATEGORYURL  \n");
        sb.append("    WHERE CATEGORY_CODE     = ?  \n");
        sb.append("      AND CATEGORYURL_KINDS = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code     : " + retrieve.getString("category_code") );
            log.debug("categoryurl_kinds : " + retrieve.getString("categoryurl_kinds") );
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlTcode( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT CODE_NAME \n");
        sb.append("   FROM TCODE \n");
        sb.append("  WHERE CODE_LGROUP = ? \n");
        sb.append("    AND USE_YN = '1' \n");
        sb.append("  ORDER BY CODE_MGROUP \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPCategoryCode( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT B.CATEGORY_CODE,  \n");
        sb.append("        B.CATEGORY_GB  \n");
        sb.append("   FROM TCATEGORY A,  \n");
        sb.append("        TCATEGORY B  \n");
        sb.append("  WHERE A.P_CATEGORY_CODE = B.CATEGORY_CODE \n");
        sb.append("    AND A.CATEGORY_CODE = ? \n");
        sb.append("    AND A.CATEGORY_GB   = ? \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDispGoodsCntSum( RetrieveModel retrieve ) throws StoreException{

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

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDispGoodsCnt( RetrieveModel retrieve ) throws StoreException{

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


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCategoryDispYnChk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(*)  AS CNT  \n");
        sb.append("   FROM TCATEGORY  \n");
        sb.append("  WHERE DISPLAY_YN = '1'  \n");
        sb.append("    AND CATEGORY_CODE <> ?  \n");
        sb.append("CONNECT BY  \n");
        sb.append("  PRIOR CATEGORY_CODE = P_CATEGORY_CODE  \n");
        sb.append("  START WITH CATEGORY_CODE = ?  \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPresentationDispYnChk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(*) AS CNT  \n");
        sb.append("   FROM TCATEGORYGOODS  \n");
        sb.append("  WHERE DISPLAY_YN = '1'  \n");
        sb.append("    AND GOODS_CODE = ?  \n");
        sb.append("    AND CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n");
        sb.append("                             FROM TCATEGORY  \n");
        sb.append("                          CONNECT BY  \n");
        sb.append("                            PRIOR CATEGORY_CODE = P_CATEGORY_CODE  \n");
        sb.append("                            START WITH CATEGORY_CODE = ? )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCategoryGoodsDispExist( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.CATEGORY_CODE,  \n");
        sb.append("          B.CATEGORY_NAME,  \n");
        sb.append("          A.PRESENTATION_CODE,  \n");
        sb.append("          A.PRESENTATION_KINDS,  \n");
        sb.append("          '' AS CODE_NAME  \n");
        sb.append("     FROM TPRESENTATION A,  \n");
        sb.append("          TCATEGORY B  \n");
        sb.append("    WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("      AND A.GOODS_CODE = ?  \n");
        sb.append("      AND A.DISPLAY_YN = '1'  \n");
        sb.append("      AND A.PRESENTATION_KINDS <> '99'  \n");
        sb.append("      AND A.CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n");
        sb.append("                                 FROM TCATEGORY  \n");
        sb.append("                              CONNECT BY  \n");
        sb.append("                                PRIOR P_CATEGORY_CODE = CATEGORY_CODE  \n");
        sb.append("                                START WITH CATEGORY_CODE = ? )  \n");
        sb.append(" UNION   \n");
        sb.append("   SELECT D.CATEGORY_CODE AS CATEGORY_CODE,  \n");
        sb.append("          A.PLANCLASS_NAME AS CATEGORY_NAME,  \n");
        sb.append("          B.PLANCLASS_CODE AS PRESENTATION_CODE,  \n");
        sb.append("          B.PLAN_CODE AS PRESENTATION_KINDS,  \n");
        sb.append("          B.PLAN_NAME AS CODE_NAME  \n");
        sb.append("     FROM TPLANCLASS A,  \n");
        sb.append("          TPLAN B,  \n");
        sb.append("          TPLANGOODS C,  \n");
        sb.append("          TCATEGORY D,  \n");
        sb.append("          TGOODS E  \n");
        sb.append("    WHERE A.CATEGORY_CODE = D.CATEGORY_CODE  \n");
        sb.append("      AND A.PLANCLASS_CODE = B.PLANCLASS_CODE  \n");
        sb.append("      AND B.PLANCLASS_CODE = C.PLANCLASS_CODE  \n");
        sb.append("      AND B.PLAN_CODE = C.PLAN_CODE  \n");
        sb.append("      AND C.GOODS_CODE = E.GOODS_CODE  \n");
        sb.append("      AND B.DISPLAY_YN = '1'  \n");
        sb.append("      AND C.DISPLAY_YN = '1'  \n");
        sb.append("      AND C.GOODS_CODE = ?   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoodsExist( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.CATEGORY_CODE,  \n");
        sb.append("          B.CATEGORY_NAME,  \n");
        sb.append("          A.PRESENTATION_CODE,  \n");
        sb.append("          A.PRESENTATION_KINDS  \n");
        sb.append("     FROM TPRESENTATION A,  \n");
        sb.append("          TCATEGORY B  \n");
        sb.append("    WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("      AND A.GOODS_CODE = ?  \n");
        sb.append("      AND A.PRESENTATION_KINDS <> '99'  \n");
        sb.append("      AND A.CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n");
        sb.append("                                 FROM TCATEGORY  \n");
        sb.append("                              CONNECT BY  \n");
        sb.append("                                PRIOR P_CATEGORY_CODE = CATEGORY_CODE  \n");
        sb.append("                                START WITH CATEGORY_CODE = ? )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoodsDispExistCnt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT  \n");
        sb.append("    FROM TCATEGORYGOODS  \n");
        sb.append("   WHERE GOODS_CODE = ?  \n");
        sb.append("     AND DISPLAY_YN = '1'  \n");
        sb.append("     AND CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n");
        sb.append("                              FROM TCATEGORY  \n");
        sb.append("                             WHERE CATEGORY_CODE <> ?  \n");
        sb.append("                               AND CATEGORY_GB   = ?  \n");
        sb.append("                           CONNECT BY  \n");
        sb.append("                             PRIOR CATEGORY_CODE = P_CATEGORY_CODE  \n");
        sb.append("                             START WITH CATEGORY_CODE = ? )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    private String makeSqlCategoryGoodsExistCnt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT  \n");
        sb.append("    FROM TCATEGORYGOODS  \n");
        sb.append("   WHERE GOODS_CODE = ?  \n");
        sb.append("     AND CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n");
        sb.append("                              FROM TCATEGORY  \n");
        sb.append("                             WHERE CATEGORY_CODE <> ?  \n");
        sb.append("                               AND CATEGORY_GB   = ?  \n");
        sb.append("                           CONNECT BY  \n");
        sb.append("                             PRIOR CATEGORY_CODE = P_CATEGORY_CODE  \n");
        sb.append("                             START WITH CATEGORY_CODE = ? )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoods02Chk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         B.CATEGORY_NAME  \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.GOODS_CODE    = ?  \n");
        sb.append("     AND B.CATEGORY_GB   = '02'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoods02DispChk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         B.CATEGORY_NAME  \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.GOODS_CODE    = ?  \n");
        sb.append("     AND A.DISPLAY_YN    = '1'  \n");
        sb.append("     AND B.CATEGORY_GB   = '02'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoods01DispCnt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT  \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.CATEGORY_CODE <> ?  \n");
        sb.append("     AND A.GOODS_CODE     = ?  \n");
        sb.append("     AND A.DISPLAY_YN     = '1'  \n");
        sb.append("     AND B.CATEGORY_GB    = '01'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    private String makeSqlCategoryGoods01Cnt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT  \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.CATEGORY_CODE <> ?  \n");
        sb.append("     AND A.GOODS_CODE     = ?  \n");
        sb.append("     AND B.CATEGORY_GB    = '01'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code : " + retrieve.getString("category_code"));
            log.debug("goods_code    : " + retrieve.getString("goods_code"));
        }
        return sb.toString();
    }

    private String makeSqlCategoryGoods0299DispChk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         B.CATEGORY_NAME,  \n");
        sb.append("         A.PRESENTATION_CODE,  \n");
        sb.append("         A.PRESENTATION_KINDS  \n");
        sb.append("    FROM TPRESENTATION A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.GOODS_CODE    = ?  \n");
        sb.append("     AND A.DISPLAY_YN    = '1'  \n");
        sb.append("     AND A.PRESENTATION_KINDS <> '99'  \n");
        sb.append("     AND B.CATEGORY_GB   = '02'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    private String makeSqlCategoryGoods0299Chk( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         B.CATEGORY_NAME,  \n");
        sb.append("         A.PRESENTATION_CODE,  \n");
        sb.append("         A.PRESENTATION_KINDS  \n");
        sb.append("    FROM TPRESENTATION A,  \n");
        sb.append("         TCATEGORY B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND A.GOODS_CODE    = ?  \n");
        sb.append("     AND A.PRESENTATION_KINDS <> '99'  \n");
        sb.append("     AND B.CATEGORY_GB   = '02'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL : Max Category Info
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlMaxCategory( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT MAX(CATEGORY_CODE) AS CATEGORY_CODE,  \n");
        sb.append("         TRIM(TO_CHAR(TO_NUMBER(NVL(MAX(CATEGORY_GB), '0'))+1, '00')) AS CATEGORY_GB  \n");
        sb.append("    FROM TCATEGORY  \n");
        sb.append("   WHERE P_CATEGORY_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete TCATEGORYURL )
    * </PRE>
    * @param   Tcategoryurl
    * @return  String
    */
    private final String deleteSqlTcategoryurl =  " DELETE FROM TCATEGORYURL WHERE CATEGORYURL_CODE = ? \n";

    private int deleteSqlLogTcategoryurl =  0;

    private String makeSqlDelete(Tcategoryurl tcategoryurl) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTcategoryurl == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTcategoryurl);
            }
            deleteSqlLogTcategoryurl = 1;
        }
        return deleteSqlTcategoryurl;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete TPRESENTATION )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */
    private final String deleteSqlTpresentation =  " DELETE FROM TPRESENTATION WHERE PRESENTATION_CODE = ? \n";

    private int deleteSqlLogTpresentation =  0;

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


    /**
    * <PRE>
    * Desc : Make SQL ( Delete TPRESENTATION )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */
    private final String deleteSqlTpresentationSub =  " DELETE FROM TPRESENTATION WHERE CATEGORY_CODE = ? AND GOODS_CODE = ? \n";

    private int deleteSqlLogTpresentationSub =  0;

    private String makeSqlDeleteSub(Tpresentation tpresentation) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTpresentationSub == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTpresentationSub);
            }
            deleteSqlLogTpresentationSub = 1;
        }
        return deleteSqlTpresentationSub;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete TCATEGORYGOODS )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */
    private final String deleteSqlTcategorygoods =  " DELETE FROM TCATEGORYGOODS WHERE CATEGORY_CODE = ? AND GOODS_CODE = ? \n";

    private int deleteSqlLogTcategorygoods =  0;

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
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */
    private final String updateSqlTpresentaionDispYn = " UPDATE TPRESENTATION SET \n"
                                                      +"        DISPLAY_YN      = '0', \n"
                                                      +"        MODIFY_DATE     = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                      +"        MODIFY_ID       = ? \n"
                                                      +"  WHERE CATEGORY_CODE   = ? \n"
                                                      +"    AND GOODS_CODE      = ? \n" ;

    private int updateSqlLogTpresentaionDispYn =  0;

    private String makeSqlUpdateTpresentaionDispYn() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTpresentaionDispYn == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTpresentaionDispYn);
            }
            updateSqlLogTpresentaionDispYn = 1;
        }
        return updateSqlTpresentaionDispYn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */
    private final String updateSqlTcategory =  " UPDATE TCATEGORY SET \n"
                                              +"        CATEGORY_NAME     = ?, \n"
                                              +"        DISPLAY_YN        = ?, \n"
                                              +"        DISPLAY_PRIORITY  = ?, \n"
                                              +"        DISPLAY_GOODS_CNT = ?, \n"
                                              +"        MODIFY_DATE       = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                              +"        MODIFY_ID         = ? \n"
                                              +"  WHERE CATEGORY_CODE     = ? \n" ;

    private int updateSqlLogTcategory =  0;

    private String makeSqlUpdate(Tcategory tcategory) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTcategory == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTcategory);
            }
            updateSqlLogTcategory = 1;
        }
        return updateSqlTcategory;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */

    private final String updateSqlTpresentation =" UPDATE TPRESENTATION SET \n"
                                                +"        DISPLAY_YN         = ?, \n"
                                                +"        DISPLAY_PRIORITY   = ?, \n"
                                                +"        DISPLAY_START_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        DISPLAY_END_DATE   = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_DATE        = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_ID          = ? \n"
                                                +"  WHERE PRESENTATION_CODE  = ? \n" ;

    private int updateSqlLogTpresentation =  0;

    private String makeSqlUpdate(Tpresentation tpresentation) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTpresentation);
            }
            updateSqlLogTpresentation = 1;
        }
        return updateSqlTpresentation;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */

    private final String updateSqlTcategorygoods=" UPDATE TCATEGORYGOODS SET \n"
                                                +"        DISPLAY_YN         = ?, \n"
                                                +"        DISPLAY_PRIORITY   = ?, \n"
                                                +"        DISPLAY_START_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        DISPLAY_END_DATE   = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_DATE        = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_ID          = ? \n"
                                                +"  WHERE CATEGORY_CODE      = ? \n"
                                                +"    AND GOODS_CODE         = ? \n";

    private int updateSqlLogTcategorygoods =  0;

    private String makeSqlUpdate(Tcategorygoods tcategorygoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTcategorygoods);
            }
            updateSqlLogTcategorygoods = 1;
        }
        return updateSqlTcategorygoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategoryurl
    * @return  String
    */

    private final String updateSqlTcategoryurl  =" UPDATE TCATEGORYURL SET   \n"
                                                +"        NAME               = ?, \n"
                                                +"        TARGET_URL         = ?, \n"
                                                +"        IMAGE_URL          = ?, \n"
                                                +"        DISPLAY_YN         = ?, \n"
                                                +"        DISPLAY_PRIORITY   = ?, \n"
                                                +"        DISPLAY_START_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        DISPLAY_END_DATE   = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_DATE        = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                +"        MODIFY_ID          = ? \n"
                                                +"  WHERE CATEGORYURL_CODE   = ? \n" ;

    private int updateSqlLogTcategoryurl =  0;

    private String makeSqlUpdate(Tcategoryurl tcategoryurl) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTcategoryurl == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTcategoryurl);
            }
            updateSqlLogTcategoryurl = 1;
        }
        return updateSqlTcategoryurl;
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

    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */

    private final String insertSqlTcategory =     " INSERT INTO TCATEGORY ( \n"
                                                 +"        CATEGORY_CODE, \n"
                                                 +"        CATEGORY_NAME, \n"
                                                 +"        P_CATEGORY_CODE, \n"
                                                 +"        DISPLAY_YN, \n"
                                                 +"        DISPLAY_PRIORITY, \n"
                                                 +"        CATEGORY_GB, \n"
                                                 +"        DISPLAY_GOODS_CNT, \n"
                                                 +"        INSERT_DATE, \n"
                                                 +"        INSERT_ID, \n"
                                                 +"        MODIFY_DATE, \n"
                                                 +"        MODIFY_ID) \n"
                                                 +"VALUES ( \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?) \n";

    private int insertSqlLogTcategory =  0;

    private String makeSqlInsert(Tcategory tcategory) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTcategory == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTcategory);
            }
            insertSqlLogTcategory = 1;
        }
        return insertSqlTcategory;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */

    private final String insertSqlTpresentation  =" INSERT INTO TPRESENTATION ( \n"
                                                 +"        PRESENTATION_CODE, \n"
                                                 +"        PRESENTATION_KINDS, \n"
                                                 +"        PRESENTATION_GB, \n"
                                                 +"        CATEGORY_CODE, \n"
                                                 +"        GOODS_CODE, \n"
                                                 +"        DISPLAY_YN, \n"
                                                 +"        DISPLAY_PRIORITY, \n"
                                                 +"        DISPLAY_START_DATE, \n"
                                                 +"        DISPLAY_END_DATE, \n"
                                                 +"        INSERT_DATE, \n"
                                                 +"        INSERT_ID, \n"
                                                 +"        MODIFY_DATE, \n"
                                                 +"        MODIFY_ID) \n"
                                                 +"VALUES ( \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?) \n";

    private int insertSqlLogTpresentation =  0;

    private String makeSqlInsert(Tpresentation tpresentation) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTpresentation);
            }
            insertSqlLogTpresentation = 1;
        }
        return insertSqlTpresentation;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */

    private final String insertSqlTcategorygoods =" INSERT INTO TCATEGORYGOODS ( \n"
                                                 +"        CATEGORY_CODE, \n"
                                                 +"        GOODS_CODE, \n"
                                                 +"        DISPLAY_YN, \n"
                                                 +"        DISPLAY_PRIORITY, \n"
                                                 +"        DISPLAY_START_DATE, \n"
                                                 +"        DISPLAY_END_DATE, \n"
                                                 +"        INSERT_DATE, \n"
                                                 +"        INSERT_ID, \n"
                                                 +"        MODIFY_DATE, \n"
                                                 +"        MODIFY_ID) \n"
                                                 +"VALUES ( \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?) \n";

    private int insertSqlLogTcategorygoods =  0;

    private String makeSqlInsert(Tcategorygoods tcategorygoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTcategorygoods);
            }
            insertSqlLogTcategorygoods = 1;
        }
        return insertSqlTcategorygoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategoryurl
    * @return  String
    */


    private final String insertSqlTcategoryurl   =" INSERT INTO TCATEGORYURL ( \n"
                                                 +"        CATEGORYURL_CODE, \n"
                                                 +"        CATEGORYURL_KINDS, \n"
                                                 +"        CATEGORY_CODE, \n"
                                                 +"        NAME, \n"
                                                 +"        TARGET_URL, \n"
                                                 +"        IMAGE_URL, \n"
                                                 +"        DISPLAY_YN, \n"
                                                 +"        DISPLAY_PRIORITY, \n"
                                                 +"        DISPLAY_START_DATE, \n"
                                                 +"        DISPLAY_END_DATE, \n"
                                                 +"        INSERT_DATE, \n"
                                                 +"        INSERT_ID, \n"
                                                 +"        MODIFY_DATE, \n"
                                                 +"        MODIFY_ID) \n"
                                                 +"VALUES ( \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?, \n"
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n"
                                                 +"        ?) \n";

    private int insertSqlLogTcategoryurl =  0;

    private String makeSqlInsert(Tcategoryurl tcategoryurl) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTcategoryurl == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTcategoryurl);
            }
            insertSqlLogTcategoryurl = 1;
        }
        return insertSqlTcategoryurl;
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetCategoryGoods(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;
        double       sale_price = 0;
        double       buy_cost   = 0;
        double       buy_vat    = 0;
        double       buyPrice   = 0;
        double       marginRate = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            sale_price = ComUtils.objToDouble(hmSheet.get("SALE_PRICE"),0);
            buy_cost   = ComUtils.objToDouble(hmSheet.get("BUY_COST"),0);
            buy_vat    = ComUtils.objToDouble(hmSheet.get("BUY_VAT"),0);
            buyPrice   = buy_cost + buy_vat;

            if(sale_price > 0){
                marginRate   = ComUtils.modAmt( ((sale_price - buyPrice) / (double)sale_price) * 100, 4, 2);
            }

            hmSheet.put("marginRate",    String.valueOf(marginRate));
            hmSheet.put("buyPrice",      String.valueOf(buyPrice));
//            hmSheet.put("marginRate",    marginRate);
//            hmSheet.put("buyPrice",      buyPrice);

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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetPresentation(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;
        double       sale_price = 0;
        double       buy_cost   = 0;
        double       buy_vat    = 0;
        double       buyPrice   = 0;
        double       marginRate = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            sale_price = ComUtils.objToDouble(hmSheet.get("SALE_PRICE"),0);
            buy_cost   = ComUtils.objToDouble(hmSheet.get("BUY_COST"),0);
            buy_vat    = ComUtils.objToDouble(hmSheet.get("BUY_VAT"),0);
            buyPrice   = buy_cost + buy_vat;

            if(sale_price > 0){
                marginRate   = ComUtils.modAmt( ((sale_price - buyPrice) / (double)sale_price) * 100, 4, 2);
            }

            hmSheet.put("marginRate",    String.valueOf(marginRate));
            hmSheet.put("buyPrice",      String.valueOf(buyPrice));
//            hmSheet.put("marginRate",    marginRate);
//            hmSheet.put("buyPrice",      buyPrice);

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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap makeSheetCategoryInfo(ResultSet rset)  throws Exception {
        HashMap      hmSheet    = null;
        String       tempString = "";
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
            log.debug("Retrieve Row : " + retRow);
        }
        return hmSheet;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetCategoryUrl(ResultSet rset)  throws Exception {
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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetTcode(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet  = null;
        long         retRow   = 0;
        int          j        = 0;

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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap makeSheetPCategoryCode(ResultSet rset)  throws Exception {
        HashMap      hmSheet    = null;
        String       tempString = "";
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
            log.debug("Retrieve Row : " + retRow);
        }
        return hmSheet;
    }


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetCategoryGoodsExist(ResultSet rset)  throws Exception {
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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetCategoryGoodsChk(ResultSet rset)  throws Exception {
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
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetCategoryGoods02Chk(ResultSet rset)  throws Exception {
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

    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap makeSheetMaxCategory(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
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
        return hmSheet;
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

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryGoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods(retrieve));
            pstmt.setString(1,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CG", makeSheetCategoryGoods(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrievePresentation(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            ptKinds     = "";

        try {
            pstmt = con.prepareStatement(makeSqlPresentation(retrieve));
            int index = 1;

            ptKinds = retrieve.getString("presentation_kinds");

            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  ptKinds);

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PT"+ptKinds, makeSheetPresentation(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrievePresentation() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrievePresentation() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryInfo(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryInfo(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CI", makeSheetCategoryInfo(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryInfo() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryInfo() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryUrl(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryUrl(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("categoryurl_kinds"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_URL", makeSheetCategoryUrl(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryUrl() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryUrl() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public int retrieveDispGoodsCntSum(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCntSum = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCntSum(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_gb"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCntSum = rset.getInt(1);

            log.info("[CategorySvc.retrieveDispGoodsCntSum category_code : "+retrieve.getString("category_code"));
            log.info("[CategorySvc.retrieveDispGoodsCntSum category_gb : "+retrieve.getString("category_gb"));
            log.info("[CategorySvc.retrieveDispGoodsCntSum dispGoodsCntSum : "+dispGoodsCntSum);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveDispGoodsCntSum() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveDispGoodsCntSum() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCntSum;
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
    public int retrieveDispGoodsCnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCnt(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

            log.info("[CategorySvc.retrieveDispGoodsCnt category_code : "+retrieve.getString("category_code"));
            log.info("[CategorySvc.retrieveDispGoodsCnt dispGoodsCnt : "+dispGoodsCnt);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveDispGoodsCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveDispGoodsCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public RetrieveModel retrieveTcode(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlTcode(retrieve));
            code_lgroup = retrieve.getString("code_lgroup");

            int index = 1;
            pstmt.setString(index++, code_lgroup);

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_"+code_lgroup, makeSheetTcode(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrievePCategoryCode(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPCategoryCode(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("category_code"));
            pstmt.setString(index++, retrieve.getString("category_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PCATEGORYCODE", makeSheetPCategoryCode(rset));

        }catch(SQLException se){
            log.error("[retrievePCategoryCode.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[retrievePCategoryCode.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public int retrieveCategoryDispYnChk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryDispYnChk(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryDispYnChk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryDispYnChk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public int retrievePresentationDispYnChk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlPresentationDispYnChk(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

        }catch(SQLException se){
            log.error("[CategorySvc.retrievePresentationDispYnChk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrievePresentationDispYnChk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public RetrieveModel retrieveCategoryGoodsDispExist(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoodsDispExist(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("category_code"));
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHK", makeSheetCategoryGoodsExist(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoodsDispExist() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoodsDispExist() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryGoodsExist(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoodsExist(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CHK", makeSheetCategoryGoodsExist(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoodsExist() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoodsExist() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryGoods02Chk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods02Chk(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CHK02", makeSheetCategoryGoods02Chk(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods02Chk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods02Chk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public int retrieveCategoryGoods01Cnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods01Cnt(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

            log.info("[CategorySvc.retrieveDispGoodsCnt category_code : "+retrieve.getString("goods_code"));
            log.info("[CategorySvc.retrieveDispGoodsCnt CNT : "+dispGoodsCnt);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods01Cnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods01Cnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public int retrieveCategoryGoods01DispCnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods01DispCnt(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

            log.info("[CategorySvc.retrieveDispGoodsCnt category_code : "+retrieve.getString("goods_code"));
            log.info("[CategorySvc.retrieveDispGoodsCnt CNT : "+dispGoodsCnt);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods01DispCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods01DispCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public int retrieveCategoryGoodsDispExistCnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoodsDispExistCnt(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("goods_code"));
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_gb"));
            pstmt.setString(index++,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoodsExistCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoodsExistCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public int retrieveCategoryGoodsExistCnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               dispGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoodsExistCnt(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("goods_code"));
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_gb"));
            pstmt.setString(index++,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) dispGoodsCnt = rset.getInt(1);

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoodsExistCntDel() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoodsExistCntDel() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return dispGoodsCnt;
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
    public RetrieveModel retrieveCategoryGoods02DispChk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods02DispChk(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHK02", makeSheetCategoryGoods02Chk(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods02DispChk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods02DispChk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCategoryGoods0299DispChk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods0299DispChk(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHK0299", makeSheetCategoryGoods02Chk(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods0299DispChk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods0299DispChk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveCategoryGoods0299Chk(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            code_lgroup = "";
        int               j           = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoods0299Chk(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CHK0299", makeSheetCategoryGoods02Chk(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveCategoryGoods0299Chk() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveCategoryGoods0299Chk() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    /**
    * <PRE>
    * Desc : Retrieve SQL : Max Category Info
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveMaxCategory(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlMaxCategory(retrieve));
            int index = 1;
            pstmt.setString(index++, retrieve.getString("p_category_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_MAXCATEGORY", makeSheetMaxCategory(rset));

        }catch(SQLException se){
            log.error("[CategorySvc.retrieveMaxCategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.retrieveMaxCategory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int insert(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tcategory));
            int index = 1;
            pstmt.setString(index++,tcategory.getCategory_code());
            pstmt.setString(index++,tcategory.getCategory_name());
            pstmt.setString(index++,tcategory.getP_category_code());
            pstmt.setString(index++,tcategory.getDisplay_yn());
            pstmt.setString(index++,tcategory.getDisplay_priority());
            pstmt.setString(index++,tcategory.getCategory_gb());
            pstmt.setString(index++,tcategory.getDisplay_goods_cnt());
            pstmt.setString(index++,tcategory.getInsert_date());
            pstmt.setString(index++,tcategory.getInsert_id());
            pstmt.setString(index++,tcategory.getModify_date());
            pstmt.setString(index++,tcategory.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getCategory_code()               ); logString.append( "/" );
            logString.append( tcategory.getCategory_name()               ); logString.append( "/" );
            logString.append( tcategory.getP_category_code()             ); logString.append( "/" );
            logString.append( tcategory.getDisplay_yn()                  ); logString.append( "/" );
            logString.append( tcategory.getDisplay_priority()            ); logString.append( "/" );
            logString.append( tcategory.getCategory_gb()                 ); logString.append( "/" );
            logString.append( tcategory.getDisplay_goods_cnt()           ); logString.append( "/" );
            logString.append( tcategory.getInsert_date()                 ); logString.append( "/" );
            logString.append( tcategory.getInsert_id()                   ); logString.append( "/" );
            logString.append( tcategory.getModify_date()                 ); logString.append( "/" );
            logString.append( tcategory.getModify_id()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert
    * </PRE>
    * @param   Connection
    * @param   Tpresentation
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
            logString.append( tpresentation.getPresentation_code()           ); logString.append( "/" );
            logString.append( tpresentation.getPresentation_kinds()          ); logString.append( "/" );
            logString.append( tpresentation.getPresentation_gb()             ); logString.append( "/" );
            logString.append( tpresentation.getCategory_code()               ); logString.append( "/" );
            logString.append( tpresentation.getGoods_code()                  ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_yn()                  ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_priority()            ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_start_date()          ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_end_date()            ); logString.append( "/" );
            logString.append( tpresentation.getInsert_date()                 ); logString.append( "/" );
            logString.append( tpresentation.getInsert_id()                   ); logString.append( "/" );
            logString.append( tpresentation.getModify_date()                 ); logString.append( "/" );
            logString.append( tpresentation.getModify_id()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert
    * </PRE>
    * @param   Connection
    * @param   Tcategorygoods
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
            logString.append( tcategorygoods.getCategory_code()               ); logString.append( "/" );
            logString.append( tcategorygoods.getGoods_code()                  ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_yn()                  ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_priority()            ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_start_date()          ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_end_date()            ); logString.append( "/" );
            logString.append( tcategorygoods.getInsert_date()                 ); logString.append( "/" );
            logString.append( tcategorygoods.getInsert_id()                   ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_date()                 ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_id()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert
    * </PRE>
    * @param   Connection
    * @param   Tcategorygoods
    * @return  int
    */
    public int insert(Connection con, Tcategoryurl tcategoryurl) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tcategoryurl));
            int index = 1;
            pstmt.setString(index++,tcategoryurl.getCategoryurl_code());
            pstmt.setString(index++,tcategoryurl.getCategoryurl_kinds());
            pstmt.setString(index++,tcategoryurl.getCategory_code());
            pstmt.setString(index++,tcategoryurl.getName());
            pstmt.setString(index++,tcategoryurl.getTarget_url());
            pstmt.setString(index++,tcategoryurl.getImage_url());
            pstmt.setString(index++,tcategoryurl.getDisplay_yn());
            pstmt.setString(index++,tcategoryurl.getDisplay_priority());
            pstmt.setString(index++,tcategoryurl.getDisplay_start_date());
            pstmt.setString(index++,tcategoryurl.getDisplay_end_date());
            pstmt.setString(index++,tcategoryurl.getInsert_date());
            pstmt.setString(index++,tcategoryurl.getInsert_id());
            pstmt.setString(index++,tcategoryurl.getModify_date());
            pstmt.setString(index++,tcategoryurl.getModify_id());

           //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategoryurl.getCategoryurl_code()            ); logString.append( "/" );
            logString.append( tcategoryurl.getCategoryurl_kinds()           ); logString.append( "/" );
            logString.append( tcategoryurl.getCategory_code()               ); logString.append( "/" );
            logString.append( tcategoryurl.getName()                        ); logString.append( "/" );
            logString.append( tcategoryurl.getTarget_url()                  ); logString.append( "/" );
            logString.append( tcategoryurl.getImage_url()                   ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_yn()                  ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_priority()            ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_start_date()          ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_end_date()            ); logString.append( "/" );
            logString.append( tcategoryurl.getInsert_date()                 ); logString.append( "/" );
            logString.append( tcategoryurl.getInsert_id()                   ); logString.append( "/" );
            logString.append( tcategoryurl.getModify_date()                 ); logString.append( "/" );
            logString.append( tcategoryurl.getModify_id()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int update(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tcategory));
            int index = 1;
            pstmt.setString(index++,tcategory.getCategory_name());
            pstmt.setString(index++,tcategory.getDisplay_yn());
            pstmt.setString(index++,tcategory.getDisplay_priority());
            pstmt.setString(index++,tcategory.getDisplay_goods_cnt());
            pstmt.setString(index++,tcategory.getModify_date());
            pstmt.setString(index++,tcategory.getModify_id());
            pstmt.setString(index++,tcategory.getCategory_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getCategory_name()                ); logString.append( "/" );
            logString.append( tcategory.getDisplay_yn()                   ); logString.append( "/" );
            logString.append( tcategory.getDisplay_priority()             ); logString.append( "/" );
            logString.append( tcategory.getDisplay_goods_cnt()            ); logString.append( "/" );
            logString.append( tcategory.getModify_date()                  ); logString.append( "/" );
            logString.append( tcategory.getModify_id()                    ); logString.append( "/" );
            logString.append( tcategory.getCategory_code()                ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int update(Connection con, Tpresentation tpresentation) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpresentation));
            int index = 1;
            pstmt.setString(index++,tpresentation.getDisplay_yn());
            pstmt.setString(index++,tpresentation.getDisplay_priority());
            pstmt.setString(index++,tpresentation.getDisplay_start_date());
            pstmt.setString(index++,tpresentation.getDisplay_end_date());
            pstmt.setString(index++,tpresentation.getModify_date());
            pstmt.setString(index++,tpresentation.getModify_id());
            pstmt.setString(index++,tpresentation.getPresentation_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpresentation.getDisplay_yn()                   ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_priority()             ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_start_date()           ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_end_date()             ); logString.append( "/" );
            logString.append( tpresentation.getModify_date()                  ); logString.append( "/" );
            logString.append( tpresentation.getModify_id()                    ); logString.append( "/" );
            logString.append( tpresentation.getPresentation_code()            ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int update(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tcategorygoods));
            int index = 1;
            pstmt.setString(index++,tcategorygoods.getDisplay_yn());
            pstmt.setString(index++,tcategorygoods.getDisplay_priority());
            pstmt.setString(index++,tcategorygoods.getDisplay_start_date());
            pstmt.setString(index++,tcategorygoods.getDisplay_end_date());
            pstmt.setString(index++,tcategorygoods.getModify_date());
            pstmt.setString(index++,tcategorygoods.getModify_id());
            pstmt.setString(index++,tcategorygoods.getCategory_code());
            pstmt.setString(index++,tcategorygoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategorygoods.getDisplay_yn()                   ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_priority()             ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_start_date()           ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_end_date()             ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_date()                  ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_id()                    ); logString.append( "/" );
            logString.append( tcategorygoods.getCategory_code()                ); logString.append( "/" );
            logString.append( tcategorygoods.getGoods_code()                   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int update(Connection con, Tcategoryurl tcategoryurl) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tcategoryurl));
            int index = 1;
            pstmt.setString(index++,tcategoryurl.getName());
            pstmt.setString(index++,tcategoryurl.getTarget_url());
            pstmt.setString(index++,tcategoryurl.getImage_url());
            pstmt.setString(index++,tcategoryurl.getDisplay_yn());
            pstmt.setString(index++,tcategoryurl.getDisplay_priority());
            pstmt.setString(index++,tcategoryurl.getDisplay_start_date());
            pstmt.setString(index++,tcategoryurl.getDisplay_end_date());
            pstmt.setString(index++,tcategoryurl.getModify_date());
            pstmt.setString(index++,tcategoryurl.getModify_id());
            pstmt.setString(index++,tcategoryurl.getCategoryurl_code());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategoryurl.getName()                         ); logString.append( "/" );
            logString.append( tcategoryurl.getTarget_url()                   ); logString.append( "/" );
            logString.append( tcategoryurl.getImage_url()                    ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_yn()                   ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_priority()             ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_start_date()           ); logString.append( "/" );
            logString.append( tcategoryurl.getDisplay_end_date()             ); logString.append( "/" );
            logString.append( tcategoryurl.getModify_date()                  ); logString.append( "/" );
            logString.append( tcategoryurl.getModify_id()                    ); logString.append( "/" );
            logString.append( tcategoryurl.getCategoryurl_code()             ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
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
    public int updateDispGoodsCnt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDispGoodsCnt());

            int index = 1;
            pstmt.setInt(index++,retrieve.getInt("dispCnt"));
            pstmt.setString(index++,retrieve.getString("modify_date"));
            pstmt.setString(index++,retrieve.getString("category_code"));
            pstmt.setString(index++,retrieve.getString("category_gb"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getInt("dispCnt")      ); logString.append( "/" );
            logString.append( retrieve.getString("modify_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("category_code")  ); logString.append( "/" );
            logString.append( retrieve.getString("category_gb")    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.updateDispGoodsCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.updateDispGoodsCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int updateTpresentaionDispYn(Connection con, Tpresentation tpresentation) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTpresentaionDispYn());

            int index = 1;
            pstmt.setString(index++, tpresentation.getModify_date());
            pstmt.setString(index++, tpresentation.getModify_id());
            pstmt.setString(index++, tpresentation.getCategory_code());
            pstmt.setString(index++, tpresentation.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpresentation.getModify_date()  ); logString.append( "/" );
            logString.append( tpresentation.getModify_id()    ); logString.append( "/" );
            logString.append( tpresentation.getCategory_code()); logString.append( "/" );
            logString.append( tpresentation.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.updateTpresentaionDispYn() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.updateTpresentaionDispYn() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategoryurl
    * </PRE>
    * @param   Connection
    * @param   Tcategoryurl
    * @return  int
    */
    public int delete(Connection con, Tcategoryurl tcategoryurl) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tcategoryurl));
            int index = 1;
            pstmt.setString(index++,tcategoryurl.getCategoryurl_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategoryurl.getCategoryurl_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.delete(tcategoryurl) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.delete(tcategoryurl) Exception : ERR-"+e.getMessage());
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
    * @param   Tpresentation
    * @return  int
    */
    public int deleteSub(Connection con, Tpresentation tpresentation) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteSub(tpresentation));
            int index = 1;
            pstmt.setString(index++,tpresentation.getCategory_code());
            pstmt.setString(index++,tpresentation.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpresentation.getCategory_code()   ); logString.append( "/" );
            logString.append( tpresentation.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.deleteSub(Tpresentation) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.deleteSub(Tpresentation) Exception : ERR-"+e.getMessage());
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
    * @param   Tpresentation
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
            logSave.error("[CategorySvc.delete(Tpresentation) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.delete(Tpresentation) Exception : ERR-"+e.getMessage());
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
    * @param   Tcategorygoods
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
            logString.append( tcategorygoods.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.delete(Tcategorygoods) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.delete(Tcategorygoods) Exception : ERR-"+e.getMessage());
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
    private String makeSqlEndNodeCnt( String category_code ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT COUNT(1) AS END_NODE_CNT \n");
        sb.append("  FROM TCATEGORY \n");
        sb.append(" WHERE P_CATEGORY_CODE = ? \n");

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
    * @param   category_code
    * @return  endNodeCnt
    */
    public int getEndNodeCnt(Connection con, String category_code) throws StoreException{
        PreparedStatement pstmt      = null;
        ResultSet         rset       = null;
        int               endNodeCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlEndNodeCnt(category_code));

            int index = 1;
            pstmt.setString(index++, category_code);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) endNodeCnt = rset.getInt(1);

            log.info("[CategorySvc.getEndNodeCnt category_code : "+category_code);
            log.info("[CategorySvc.getEndNodeCnt endNodeCnt    : "+endNodeCnt);

        }catch(SQLException se){
            log.error("[CategorySvc.getEndNodeCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.getEndNodeCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return endNodeCnt;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCategoryGoodsCnt( String category_code ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT COUNT(1) AS CATEGORY_GOODS_CNT \n");
        sb.append("  FROM TCATEGORYGOODS \n");
        sb.append(" WHERE CATEGORY_CODE = ? \n");
        sb.append("   AND ROWNUM = 1 \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

   //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   category_code
    * @return  categoryGoodsCnt
    */
    public int getCategoryGoodsCnt(Connection con, String category_code) throws StoreException{
        PreparedStatement pstmt            = null;
        ResultSet         rset             = null;
        int               categoryGoodsCnt = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCategoryGoodsCnt(category_code));

            int index = 1;
            pstmt.setString(index++, category_code);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) categoryGoodsCnt = rset.getInt(1);

            logSave.info("[CategorySvc.getCategoryGoodsCnt category_code    : "+category_code);
            logSave.info("[CategorySvc.getCategoryGoodsCnt categoryGoodsCnt : "+categoryGoodsCnt);

        }catch(SQLException se){
            logSave.error("[CategorySvc.getCategoryGoodsCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.getCategoryGoodsCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return categoryGoodsCnt;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete TCATEGORY )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */
    private String makeSqlDelete(Tcategory tcategory) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" DELETE FROM TCATEGORY \n");
        sb.append("  WHERE CATEGORY_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategory
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int delete(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tcategory));
            int index = 1;
            pstmt.setString(index++,tcategory.getCategory_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getCategory_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CategorySvc.delete(tcategory) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CategorySvc.delete(tcategory) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    public String getPcategory(Connection con, RetrieveModel retrieve) throws StoreException{

        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String p_category_code = "";

        try {
            pstmt = con.prepareStatement(makeSqlGetP(retrieve));

            int index = 1;
            pstmt.setString(index++,  retrieve.getString("category_code"));
            pstmt.setString(index++,  retrieve.getString("category_gb"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) p_category_code = rset.getString(1);

        }catch(SQLException se){
            log.error("[CategorySvc.getPcategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[CategorySvc.getPcategory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return p_category_code;
    }


    private String makeSqlGetP( RetrieveModel retrieve ) throws StoreException{

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
}



