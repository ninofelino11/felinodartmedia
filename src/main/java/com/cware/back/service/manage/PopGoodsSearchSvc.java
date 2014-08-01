
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

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * 카테고리등록 상품 PopUp Service class
 *
 * @version 1.0, 2006/07/27
 * @author  commerceware.co.kr
 */
public class PopGoodsSearchSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopGoodsSearchSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb         = new StringBuffer();
        String sbModSql   = "";
        String sbModCgSql = "";
        long         category_code_num = 0;

        String category_code_sel  = retrieve.getString("category_code_sel");
        String goods_code     = retrieve.getString("goods_code");
        String goods_name     = retrieve.getString("goods_name");
        String entp_code      = retrieve.getString("entp_code");
        String makeco_code    = retrieve.getString("makeco_code");
        String brand_code     = retrieve.getString("barnd_code");
        String gather_date_fr = retrieve.getString("gather_date_fr");
        String gather_date_to = retrieve.getString("gather_date_to");
        String sale_price_fr  = retrieve.getString("sale_price_fr");
        String sale_price_to  = retrieve.getString("sale_price_to");
        String md_code        = retrieve.getString("md_code");
        String lgroup         = retrieve.getString("lgroup");
        String mgroup         = retrieve.getString("mgroup");
        String sgroup         = retrieve.getString("sgroup");
        String dgroup         = retrieve.getString("dgroup");
        String displayY       = retrieve.getString("displayY");
        String displayN       = retrieve.getString("displayN");
        String category_code  = retrieve.getString("category_code");
        String base_gb        = retrieve.getString("base_gb");
        String broad_yn       = retrieve.getString("broad_yn");
        String parmModStr     = retrieve.getString("modString");
        String parentFormName = retrieve.getString("parentFormName");
        String display_y      = retrieve.getString("display_y");
        String display_n      = retrieve.getString("display_n");

        if(broad_yn.equals("1")){
            base_gb = "99";
        }

            log.debug("broad_yn          : " + broad_yn);
            log.debug("base_gb           : " + base_gb);
            log.debug("parentFormName    : " + parentFormName);
            log.debug("parmModStr        : " + parmModStr);
            log.debug("category_code_sel : " + category_code_sel);
            log.debug("display_y         : " + display_y);
            log.debug("display_n         : " + display_n);


        if( !broad_yn.equals("1") && (parentFormName.equals("goods_search") || parentFormName.equals("goods_search1") || parentFormName.equals("goods_search2") || parentFormName.equals("goods_search3"))){


            sbModSql = "  SELECT DISTINCT A.GOODS_CODE,  \n"
                     + "         A.GOODS_NAME,  \n"
                     + "         B.BUY_COST,  \n"
                     + "         B.BUY_VAT,  \n"
                     + "         B.SALE_PRICE,  \n"
                     + "         A.SET_YN,  \n"
                     + "         A.ENTP_CODE,  \n"
                     + "         F.ENTP_NAME,  \n"
                     + "         0 AS SALE_CNT_AMT,  \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE,  \n"
                     + "         I.MAKECO_NAME,  \n"
                     + "         A.BRAND_CODE,  \n"
                     + "         H.BRAND_NAME,  \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         '0000/00/00' AS BROAD_DATE  \n"
                     + "    FROM TGOODS A,  \n"
                     + "         TGOODSPRICE B,  \n"
                     + "         TENTERPRISE F,  \n"
                     + "         TMAKECOMP I,  \n"
                     + "         TBRAND H,  \n"
                     + "         TMD M,  \n"
                     + "         TGOODSIMAGE X  \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE  \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE)  \n"
                     + "                            FROM TGOODSPRICE C  \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE  \n"
                     + "                             AND C.APPLY_DATE <= sysdate)  \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE  \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE  \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE  \n"
                     + "     AND A.SIGN_GB = '80'  \n"
                     + "     AND A.MD_CODE = M.MD_CODE  \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+)  \n"
                     + parmModStr ;

        }else if(base_gb.equals("0")){
            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "         A.GOODS_NAME, \n"
                     + "         B.BUY_COST, \n"
                     + "         B.BUY_VAT, \n"
                     + "         B.SALE_PRICE, \n"
                     + "         A.SET_YN, \n"
                     + "         A.ENTP_CODE, \n"
                     + "         F.ENTP_NAME, \n"
                     + "         NVL(SUM(G.ORDER_OUT_QTY) - SUM(G.RETURN_QTY), 0) AS SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE, \n"
                     + "         I.MAKECO_NAME, \n"
                     + "         A.BRAND_CODE, \n"
                     + "         H.BRAND_NAME, \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         '0000/00/00' AS BROAD_DATE \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TCATEGORYGOODS E, \n"
                     + "         TENTERPRISE F, VSDORDERGOODS G, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND  C.APPLY_DATE <= sysdate) \n"
                     + "     AND A.GOODS_CODE = E.GOODS_CODE \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "     AND A.GOODS_CODE = G.GOODS_CODE \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + "     AND A.MD_CODE = M.MD_CODE \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "     AND A.SIGN_GB = '80' \n"
                     + "     AND E.DISPLAY_YN = '1' \n"
                     + "     AND E.CATEGORY_CODE IN ( SELECT CATEGORY_CODE \n"
                     + "                                FROM TCATEGORY \n"
                     + "                             CONNECT BY \n"
                     + "                               PRIOR CATEGORY_CODE = P_CATEGORY_CODE \n"
                     + "                               START WITH CATEGORY_CODE = '" + category_code_sel + "' ) \n";

        }else if(base_gb.equals("1")){

            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "         A.GOODS_NAME, \n"
                     + "         B.BUY_COST, \n"
                     + "         B.BUY_VAT, \n"
                     + "         B.SALE_PRICE, \n"
                     + "         A.SET_YN, \n"
                     + "         A.ENTP_CODE, \n"
                     + "         F.ENTP_NAME, \n"
                     + "         NVL(SUM(G.ORDER_OUT_AMT) - SUM(G.RETURN_AMT), 0) AS SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE, \n"
                     + "         I.MAKECO_NAME, \n"
                     + "         A.BRAND_CODE, \n"
                     + "         H.BRAND_NAME, \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         '0000/00/00' AS BROAD_DATE \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TCATEGORYGOODS E, \n"
                     + "         TENTERPRISE F, VSDORDERGOODS G, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND  C.APPLY_DATE <= sysdate) \n"
                     + "     AND A.GOODS_CODE = E.GOODS_CODE \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "     AND A.GOODS_CODE = G.GOODS_CODE \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + "     AND A.MD_CODE = M.MD_CODE \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "     AND A.SIGN_GB = '80' \n"
                     + "     AND E.DISPLAY_YN = '1' \n"
                     + "     AND E.CATEGORY_CODE IN ( SELECT CATEGORY_CODE \n"
                     + "                                FROM TCATEGORY \n"
                     + "                             CONNECT BY \n"
                     + "                               PRIOR CATEGORY_CODE = P_CATEGORY_CODE \n"
                     + "                               START WITH CATEGORY_CODE = '" + category_code_sel + "' ) \n"    ;


        }else if(base_gb.equals("2") || base_gb.equals("3")){

            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "            A.GOODS_NAME, \n"
                     + "            B.BUY_COST, \n"
                     + "            B.BUY_VAT, \n"
                     + "            B.SALE_PRICE, \n"
                     + "            A.SET_YN, \n"
                     + "            A.ENTP_CODE, \n"
                     + "            F.ENTP_NAME, \n"
                     + "            0 SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "            A.MAKECO_CODE, \n"
                     + "            I.MAKECO_NAME, \n"
                     + "            A.BRAND_CODE, \n"
                     + "            H.BRAND_NAME, \n"
                     + "            TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "            '0000/00/00' AS BROAD_DATE \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TCATEGORYGOODS E, \n"
                     + "         TENTERPRISE F, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND C.APPLY_DATE <= sysdate) \n"
                     + "      AND A.GOODS_CODE = E.GOODS_CODE \n"
                     + "      AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + "      AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "      AND A.MD_CODE = M.MD_CODE \n"
                     + "      AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "      AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "      AND A.SIGN_GB = '80' \n"
                     + "      AND E.DISPLAY_YN = '1' \n"
                     + "      AND E.CATEGORY_CODE IN ( SELECT CATEGORY_CODE \n"
                     + "                                 FROM TCATEGORY \n"
                     + "                              CONNECT BY \n"
                     + "                                PRIOR CATEGORY_CODE = P_CATEGORY_CODE   \n"
                     + "                                START WITH CATEGORY_CODE = '" + category_code_sel + "' ) \n";


        }else if(base_gb.equals("4")){

            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "         A.GOODS_NAME, \n"
                     + "         B.BUY_COST, \n"
                     + "         B.BUY_VAT, \n"
                     + "         B.SALE_PRICE, \n"
                     + "         A.SET_YN, \n"
                     + "         A.ENTP_CODE, \n"
                     + "         F.ENTP_NAME, \n"
                     + "         NVL(SUM(G.ORDER_OUT_QTY) - SUM(G.RETURN_QTY), 0) AS SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE, \n"
                     + "         I.MAKECO_NAME, \n"
                     + "         A.BRAND_CODE, \n"
                     + "         H.BRAND_NAME, \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         '0000/00/00' AS BROAD_DATE  \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TCATEGORYGOODS E, \n"
                     + "         TENTERPRISE F, VSDORDERGOODS G, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND G.ORDER_MEDIA = '61' \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND C.APPLY_DATE <= sysdate) \n"
                     + "     AND A.GOODS_CODE = E.GOODS_CODE \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "     AND A.GOODS_CODE = G.GOODS_CODE \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + "     AND A.MD_CODE = M.MD_CODE \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "     AND A.SIGN_GB = '80' \n"
                     + "     AND E.DISPLAY_YN = '1' \n"
                     + "     AND E.CATEGORY_CODE IN ( SELECT CATEGORY_CODE \n"
                     + "                                FROM TCATEGORY \n"
                     + "                             CONNECT BY \n"
                     + "                               PRIOR CATEGORY_CODE = P_CATEGORY_CODE \n"
                     + "                                START WITH CATEGORY_CODE = '" + category_code_sel + "' ) \n";

        }else if(base_gb.equals("5")){
            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "         A.GOODS_NAME, \n"
                     + "         B.BUY_COST, \n"
                     + "         B.BUY_VAT, \n"
                     + "         B.SALE_PRICE, \n"
                     + "         A.SET_YN, \n"
                     + "         A.ENTP_CODE, \n"
                     + "         F.ENTP_NAME, \n"
                     + "         NVL(SUM(G.ORDER_OUT_AMT) - SUM(G.RETURN_AMT), 0) AS SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE, \n"
                     + "         I.MAKECO_NAME, \n"
                     + "         A.BRAND_CODE, \n"
                     + "         H.BRAND_NAME, \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         '0000/00/00' AS BROAD_DATE \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TCATEGORYGOODS E, \n"
                     + "         TENTERPRISE F, VSDORDERGOODS G, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND G.ORDER_MEDIA = '61' \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND C.APPLY_DATE <= sysdate) \n"
                     + "     AND A.GOODS_CODE = E.GOODS_CODE \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "     AND A.GOODS_CODE = G.GOODS_CODE \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + "     AND A.MD_CODE = M.MD_CODE \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "     AND A.SIGN_GB = '80' \n"
                     + "     AND E.DISPLAY_YN = '1' \n"
                     + "     AND E.CATEGORY_CODE IN ( SELECT CATEGORY_CODE \n"
                     + "                                FROM TCATEGORY \n"
                     + "                             CONNECT BY \n"
                     + "                               PRIOR CATEGORY_CODE = P_CATEGORY_CODE \n"
                     + "                               START WITH CATEGORY_CODE = '" + category_code_sel + "' ) \n";


        }else if(base_gb.equals("99")){

            sbModSql = "  SELECT DISTINCT A.GOODS_CODE, \n"
                     + "         A.GOODS_NAME, \n"
                     + "         B.BUY_COST, \n"
                     + "         B.BUY_VAT, \n"
                     + "         B.SALE_PRICE, \n"
                     + "         A.SET_YN, \n"
                     + "         A.ENTP_CODE, \n"
                     + "         F.ENTP_NAME, \n"
                     + "         0 SALE_CNT_AMT, \n"
                     + "         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,  \n"
                     + "         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,  \n"
                     + "         A.MAKECO_CODE, \n"
                     + "         I.MAKECO_NAME, \n"
                     + "         A.BRAND_CODE, \n"
                     + "         H.BRAND_NAME, \n"
                     + "         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n"
                     + "         TO_CHAR(Y.BD_DATE,'YYYY/MM/DD') AS BROAD_DATE \n"
                     + "    FROM TGOODS A, TGOODSPRICE B, TENTERPRISE F, TBRAND H, \n"
                     + "         TMAKECOMP I, TMD M, TGOODSIMAGE X, \n"
                     + "        (SELECT GOODS_CODE, MAX(BD_DATE) BD_DATE \n"
                     + "           FROM TDTBROAD \n"
                     + "          GROUP BY GOODS_CODE) Y \n"
                     + "   WHERE A.GOODS_CODE = B.GOODS_CODE \n"
                     + "     AND A.GOODS_CODE = Y.GOODS_CODE(+) \n"
                     + "     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE) \n"
                     + "                            FROM TGOODSPRICE C \n"
                     + "                           WHERE A.GOODS_CODE = C.GOODS_CODE \n"
                     + "                             AND C.APPLY_DATE <= sysdate) \n"
                     + "     AND A.ENTP_CODE = F.ENTP_CODE \n"
                     + "     AND A.SIGN_GB = '80' \n"
                     + "     AND A.MD_CODE = M.MD_CODE \n"
                     + "     AND A.BRAND_CODE = H.BRAND_CODE \n"
                     + "     AND A.MAKECO_CODE = I.MAKECO_CODE \n"
                     + "     AND A.GOODS_CODE = X.GOODS_CODE(+) \n"
                     + parmModStr ;
        }



        if(display_y.equals("1") && display_n.equals("0"))   { sbModSql = sbModSql + " AND A.GOODS_CODE IN ( SELECT DISTINCT GOODS_CODE FROM TCATEGORYGOODS WHERE DISPLAY_YN = '1' ) \n"; }
        if(display_y.equals("0") && display_n.equals("1"))   { sbModSql = sbModSql + " AND A.GOODS_CODE NOT IN ( SELECT DISTINCT GOODS_CODE FROM TCATEGORYGOODS WHERE DISPLAY_YN = '1' ) \n"; }

        if(!goods_code.equals(""))    { sbModSql = sbModSql + " AND A.GOODS_CODE LIKE '"  + goods_code + "%' \n"; }
        if(!goods_name.equals(""))    { sbModSql = sbModSql + " AND A.GOODS_NAME LIKE '"  + goods_name + "%' \n"; }
        if(!entp_code.equals(""))     { sbModSql = sbModSql + " AND A.ENTP_CODE LIKE '"   + entp_code + "%' \n"; }
        if(!makeco_code.equals(""))   { sbModSql = sbModSql + " AND A.MAKECO_CODE LIKE '" + makeco_code + "%' \n"; }
        if(!sale_price_fr.equals("")) { sbModSql = sbModSql + " AND B.SALE_PRICE >= '"    + sale_price_fr + "' \n"; }
        if(!sale_price_to.equals("")) { sbModSql = sbModSql + " AND B.SALE_PRICE <= '"    + sale_price_to + "' \n"; }
        if(!md_code.equals(""))       { sbModSql = sbModSql + " AND A.MD_CODE LIKE '"     + md_code + "%' \n"; }
        if(!lgroup.equals(""))        { sbModSql = sbModSql + " AND A.LGROUP LIKE '"      + lgroup + "%' \n"; }
        if(!mgroup.equals(""))        { sbModSql = sbModSql + " AND A.MGROUP LIKE '"      + mgroup + "%' \n"; }
        if(!sgroup.equals(""))        { sbModSql = sbModSql + " AND A.SGROUP LIKE '"      + sgroup + "%' \n"; }
        if(!dgroup.equals(""))        { sbModSql = sbModSql + " AND A.DGROUP LIKE '"      + dgroup + "%' \n"; }


        if(!category_code.equals("0") && !category_code.equals("")){

            category_code_num = ComUtils.objToLong(category_code, 0);
            if(category_code_num%10000000 ==0){
                sbModCgSql = " WHERE CATEGORY_CODE LIKE '" + String.valueOf(category_code_num/10000000) + "%' \n";
            }else if(category_code_num%100000 ==0){
                sbModCgSql = " WHERE CATEGORY_CODE LIKE '" + String.valueOf(category_code_num/100000) + "%' \n";
            }else if(category_code_num%1000 ==0){
                sbModCgSql = " WHERE CATEGORY_CODE LIKE '" + String.valueOf(category_code_num/1000) + "%' \n";
            }else{
                sbModCgSql = " WHERE CATEGORY_CODE = '" + category_code + "' \n";
            }

        sbModSql = sbModSql + " AND A.GOODS_CODE IN ( SELECT GOODS_CODE FROM TCATEGORYGOODS " + sbModCgSql + ") ";

        }



        if(base_gb.equals("0") || base_gb.equals("1")){  //= 매출수량, 매출금액

            sbModSql = sbModSql + "   AND G.GATHER_DATE >= TO_DATE('" + gather_date_fr + "', 'YYYY/MM/DD') \n"
                                + "   AND G.GATHER_DATE <  TO_DATE('" + gather_date_to + "', 'YYYY/MM/DD') + 1 \n"
                                + " GROUP BY  A.GOODS_CODE, A.GOODS_NAME, B.BUY_COST, B.BUY_VAT, B.SALE_PRICE, \n"
                                + "               A.SET_YN,   A.ENTP_CODE, F.ENTP_NAME, \n"
                                + "               DECODE(X.IMAGE_M, '', 'N', 'Y'), DECODE(X.IMAGE_M, '', '0', '1'), \n"
                                + "               A.MAKECO_CODE, I.MAKECO_NAME, A.BRAND_CODE, H.BRAND_NAME, A.INSERT_DATE \n";

            base_gb = "SALE_CNT_AMT";

        }else if(base_gb.equals("4") || base_gb.equals("5")){  //= 매출수량(인터넷), 매출금액(인터넷)

            sbModSql = sbModSql + "   AND G.ORDER_MEDIA = '61' \n"
                                + "   AND G.GATHER_DATE >= TO_DATE('" + gather_date_fr + "', 'YYYY/MM/DD') \n"
                                + "   AND G.GATHER_DATE <  TO_DATE('" + gather_date_to + "', 'YYYY/MM/DD') + 1 \n"
                                + " GROUP BY  A.GOODS_CODE, A.GOODS_NAME, B.BUY_COST, B.BUY_VAT, B.SALE_PRICE, \n"
                                + "           A.SET_YN,   A.ENTP_CODE, F.ENTP_NAME, \n"
                                + "           DECODE(X.IMAGE_M, '', 'N', 'Y'), DECODE(X.IMAGE_M, '', '0', '1'), \n"
                                + "           A.MAKECO_CODE, I.MAKECO_NAME, A.BRAND_CODE, H.BRAND_NAME, A.INSERT_DATE \n";

            base_gb = "SALE_CNT_AMT";

        }else if(base_gb.equals("2")){

            sbModSql = sbModSql + "   AND A.INSERT_DATE >= TO_DATE('" + gather_date_fr + "', 'YYYY/MM/DD') \n"
                                + "   AND A.INSERT_DATE <  TO_DATE('" + gather_date_to + "', 'YYYY/MM/DD') + 1 \n";
            base_gb = "TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS')";

        }else if(base_gb.equals("3")){

            base_gb = "TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS')";

        }else if(base_gb.equals("99")){

            sbModSql = sbModSql + "   AND Y.BD_DATE >= TO_DATE('" + gather_date_fr + "', 'YYYY/MM/DD') \n"
                                + "   AND Y.BD_DATE <  TO_DATE('" + gather_date_to + "', 'YYYY/MM/DD') + 1 \n";

            base_gb = "BROAD_DATE";
        }

        sbModSql = sbModSql + " ORDER BY " + base_gb + " DESC, LPAD(A.GOODS_CODE, 10, '0')  ";


        sb.append( sbModSql );

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
                marginRate   = ComUtils.modAmt( ((sale_price - buyPrice) / (double)sale_price) * 100,4,2);
            }

            hmSheet.put("marginRate",    String.valueOf(marginRate));
            hmSheet.put("buyPrice",      String.valueOf(buyPrice));


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
        Statement stmt       = null;
        ResultSet         rset        = null;

        try {
            stmt = con.createStatement();

            rset  = stmt.executeQuery(makeSql(retrieve));

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return retrieve;
    }

}

