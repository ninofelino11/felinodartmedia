
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
import com.cware.back.entity.table.Tinplanqty;
import com.cware.back.entity.table.Torderstock;

/**
 * Register Slipnoreg Service class
 *
 * @version 1.0, 2006/07/10
 */
public class GoodsScmSignSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsScmSignSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   entp_code                   : 업체코드
    * @param   md_code                     : md코드
    * @param   fromDate                    : 등록일
    * @param   toDate                      : 등록일
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.CONFIRM_GOODS_CODE,		\n");
        sb.append("          TO_CHAR(A.CONFIRM_DATE, 'YYYY/MM/DD HH24:MI:SS') CONFIRM_DATE,	\n");
        sb.append("          A.GOODS_CODE,	\n");
        sb.append("          A.GOODS_NAME,	\n");
        sb.append("          A.MD_CODE,	\n");
        sb.append("          B.MD_NAME,	\n");
        sb.append("          A.KEYWORD,	\n");
        sb.append("          A.ENTP_CODE,	\n");
        sb.append("          C.ENTP_NAME,	\n");
        sb.append("          A.ENTP_MAN_SEQ,	\n");
        sb.append("          D.ENTP_MAN_NAME,	\n");
        sb.append("          A.LGROUP,	\n");
        sb.append("          E.LGROUP_NAME,	\n");
        sb.append("          A.MGROUP,	\n");
        sb.append("          E.MGROUP_NAME,	\n");
        sb.append("          A.SGROUP,	\n");
        sb.append("          E.SGROUP_NAME,	\n");
        sb.append("          A.DGROUP,	\n");
        sb.append("          E.DGROUP_NAME,	\n");
        sb.append("          A.FORM_CODE,	\n");
        sb.append("          F.CSPF_DESC AS FORM_NAME,	\n");
        sb.append("          A.SIZE_CODE,	\n");
        sb.append("          G.CSPF_DESC AS SIZE_NAME,	\n");
        sb.append("          A.BUY_MED,	\n");
        sb.append("          A.DELY_TYPE,	\n");
        sb.append("          A.TAX_YN,	\n");
        sb.append("          A.COST_TAX_YN,	\n");
        sb.append("          A.WEIGHT,	\n");
        sb.append("          A.GIFT_YN,	\n");
        sb.append("          A.ORDER_MEDIA_ALL_YN, \n");
        sb.append("          A.ORDER_MEDIA, \n");
        sb.append("          FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME, \n");
        sb.append("          A.MAKECO_CODE,	\n");
        sb.append("          H.MAKECO_NAME,	\n");
        sb.append("          A.BRAND_CODE,	\n");
        sb.append("          I.BRAND_NAME,	\n");
        sb.append("          A.ORIGIN_CODE,	\n");
        sb.append("          TCODE_NAME('B023', A.ORIGIN_CODE) ORIGIN_NAME,	\n");
        sb.append("          A.BUY_PRICE,	\n");
        sb.append("          A.BUY_COST,	\n");
        sb.append("          A.BUY_VAT_RATE,	\n");
        sb.append("          A.BUY_VAT,	\n");
        sb.append("          A.CUST_PRICE,	\n");
        sb.append("          A.SALE_PRICE,	\n");
        sb.append("          A.SALE_VAT_RATE,	\n");
        sb.append("          A.SALE_VAT,	\n");
        sb.append("          A.SIGN_GB,	\n");
        sb.append("          A.SIGN_NOTE,	\n");

        sb.append("          GOODS.ORDER_MAKE_YN,	\n");
        sb.append("          GOODS.INSTALL_YN,	\n");
        sb.append("        SHIP_MAN.DIRECT_SHIP_YN AS DIRECT_SHIP_YN,\n");
        sb.append("        SHIP_MAN.ENTP_MAN_SEQ AS SHIP_MAN_SEQ,\n");
        sb.append("        SHIP_MAN.ENTP_MAN_NAME AS SHIP_MAN_NAME,\n");
        sb.append("        (CASE WHEN SHIP_MAN.ENTP_MAN_DDD IS NULL  THEN '' ELSE SHIP_MAN.ENTP_MAN_DDD||')'||SHIP_MAN.ENTP_MAN_TEL1||'-'||SHIP_MAN.ENTP_MAN_TEL2 END) || (CASE WHEN SHIP_MAN.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||SHIP_MAN.ENTP_MAN_TEL3||']' END) AS SHIP_MAN_TEL,\n");
        sb.append("        FUN_ADD_POSTADDR(SHIP_MAN.POST_NO, SHIP_MAN.POST_SEQ, SHIP_MAN.ADDR) AS SHIP_MAN_ADDR,\n");
        sb.append("        RETURN_MAN.DIRECT_RETURN_YN AS DIRECT_RETURN_YN,\n");
        sb.append("        RETURN_MAN.ENTP_MAN_SEQ AS RETURN_MAN_SEQ,\n");
        sb.append("        RETURN_MAN.ENTP_MAN_NAME AS RETURN_MAN_NAME,\n");
        sb.append("        (CASE WHEN RETURN_MAN.ENTP_MAN_DDD IS NULL  THEN '' ELSE RETURN_MAN.ENTP_MAN_DDD||')'||RETURN_MAN.ENTP_MAN_TEL1||'-'||RETURN_MAN.ENTP_MAN_TEL2 END) || (CASE WHEN RETURN_MAN.ENTP_MAN_TEL3 IS NULL THEN '' ELSE '['||RETURN_MAN.ENTP_MAN_TEL3||']' END) AS RETURN_MAN_TEL,\n");
        sb.append("        FUN_ADD_POSTADDR(RETURN_MAN.POST_NO, RETURN_MAN.POST_SEQ, RETURN_MAN.ADDR) AS SHIP_MAN_ADDR\n");

        sb.append("     FROM TSCMGOODS A,	\n");
        sb.append("          TMD B,	\n");
        sb.append("          TENTERPRISE C,	\n");
        sb.append("          TENTPUSER D,	\n");
        sb.append("          TGOODSKINDS E,	\n");
        sb.append("          TGOODSINFOM F,	\n");
        sb.append("          TGOODSINFOM G,	\n");
        sb.append("          TMAKECOMP H,	\n");
        sb.append("          TBRAND I,   	\n");

        sb.append("          TGOODS GOODS,   	\n");
        sb.append("        (SELECT A.GOODS_CODE,\n");
        sb.append("                A.DIRECT_SHIP_YN,\n");
        sb.append("               A.ENTP_CODE,           \n");
        sb.append("               B.ENTP_MAN_SEQ, \n");
        sb.append("               B.ENTP_MAN_NAME, \n");
        sb.append("               B.ENTP_MAN_DDD, \n");
        sb.append("               B.ENTP_MAN_TEL1, \n");
        sb.append("               B.ENTP_MAN_TEL2,\n");
        sb.append("               B.ENTP_MAN_TEL3,\n");
        sb.append("               B.POST_NO,\n");
        sb.append("               B.POST_SEQ,\n");
        sb.append("               B.ADDR\n");
        sb.append("        FROM TGOODS A, TENTPUSER B\n");
        sb.append("        WHERE A.ENTP_CODE = B.ENTP_CODE(+)\n");
        sb.append("        AND A.SHIP_MAN_SEQ = B.ENTP_MAN_SEQ(+)) SHIP_MAN,\n");
        sb.append("        (SELECT A.GOODS_CODE,\n");
        sb.append("                A.DIRECT_RETURN_YN,\n");
        sb.append("               A.ENTP_CODE,             \n");
        sb.append("               B.ENTP_MAN_SEQ, \n");
        sb.append("               B.ENTP_MAN_NAME, \n");
        sb.append("               B.ENTP_MAN_DDD, \n");
        sb.append("               B.ENTP_MAN_TEL1, \n");
        sb.append("               B.ENTP_MAN_TEL2,\n");
        sb.append("               B.ENTP_MAN_TEL3,\n");
        sb.append("               B.POST_NO,\n");
        sb.append("               B.POST_SEQ,\n");
        sb.append("               B.ADDR\n");
        sb.append("        FROM TGOODS A, TENTPUSER B\n");
        sb.append("        WHERE A.ENTP_CODE = B.ENTP_CODE(+)\n");
        sb.append("        AND A.RETURN_MAN_SEQ = B.ENTP_MAN_SEQ(+)) RETURN_MAN\n");

        sb.append("     WHERE A.MD_CODE      = B.MD_CODE	\n");
        sb.append("       AND A.SIGN_GB      IN ('10', '40', '80')  	\n");
        sb.append("       AND A.ENTP_CODE    = C.ENTP_CODE	\n");
        sb.append("       AND A.ENTP_CODE    = D.ENTP_CODE	\n");
        sb.append("       AND A.ENTP_MAN_SEQ = D.ENTP_MAN_SEQ  	\n");

        sb.append("        AND A.CONFIRM_GOODS_CODE = GOODS.GOODS_CODE(+) \n");
        sb.append("        AND A.CONFIRM_GOODS_CODE = SHIP_MAN.GOODS_CODE(+)\n");
        sb.append("        AND A.CONFIRM_GOODS_CODE = RETURN_MAN.GOODS_CODE(+)\n");

        sb.append("       AND A.LGROUP       = E.LGROUP	\n");
        sb.append("       AND A.MGROUP       = E.MGROUP	\n");
        sb.append("       AND A.SGROUP       = E.SGROUP	\n");
        sb.append("       AND A.DGROUP       = E.DGROUP 	\n");
        sb.append("       AND A.FORM_CODE    = F.CSPF_GROUP	\n");
        sb.append("       AND A.SIZE_CODE    = G.CSPF_GROUP	\n");
        sb.append("       AND A.MAKECO_CODE  = H.MAKECO_CODE	\n");
        sb.append("       AND A.BRAND_CODE   = I.BRAND_CODE	\n");
        sb.append("       AND (C.CLOSE_DATE IS NULL OR C.CLOSE_DATE > SYSDATE) AND C.DISHONOR_YN = '0'	\n");
        sb.append("       AND A.SIGN_GB   LIKE ?||'%'	\n");
        sb.append("       AND A.ENTP_CODE   LIKE ?||'%'	\n");
        sb.append("       AND A.MD_CODE   LIKE ?||'%'	\n");
        sb.append("       AND TRUNC(A.INSERT_DATE) >= TO_DATE(?,'YYYY/MM/DD') AND TRUNC(A.INSERT_DATE) <= TO_DATE(?,'YYYY/MM/DD')+1 	\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("sign_gb     	 : " + retrieve.getString("sign_gb"));
            log.debug("entp_code     : " + retrieve.getString("entp_code"));
            log.debug("md_code     	 : " + retrieve.getString("md_code"));
            log.debug("fromDate  	 : " + retrieve.getString("fromDate"));
            log.debug("toDate 		 : " + retrieve.getString("toDate"));
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
    private String makeSqlDt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                        		\n");
        sb.append("          A.GOODS_NAME,                        		\n");
        sb.append("          A.GOODSDT_CODE,                      		\n");
        sb.append("          A.COLOR_CODE,                        		\n");
        sb.append("          A.COLOR_NAME,                        		\n");
        sb.append("          A.SIZE_CODE,                         		\n");
        sb.append("          A.SIZE_NAME,                         		\n");
        sb.append("          A.PATTERN_CODE,                      		\n");
        sb.append("          A.PATTERN_NAME,                      		\n");
        sb.append("          A.FORM_CODE,                         		\n");
        sb.append("          A.FORM_NAME,                         		\n");
        sb.append("          A.OTHER_TEXT,                        		\n");
        sb.append("          A.GOODSDT_INFO,                      		\n");
        sb.append("          NVL(A.LEAD_TIME,0) AS LEAD_TIME,        	\n");
        sb.append("          NVL(A.DAILY_CAPA_QTY,0) AS DAILY_CAPA_QTY, \n");
        sb.append("          NVL(A.MAX_SALE_QTY,0) AS MAX_SALE_QTY,     \n");
        sb.append("          NVL(A.MAX_SALE_QTY,0) AS INPLAN_QTY,       \n");
        sb.append("          A.INSERT_DATE,                       		\n");
        sb.append("          A.INSERT_ID,                         		\n");
        sb.append("          A.MODIFY_DATE,                       		\n");
        sb.append("          A.MODIFY_ID                          		\n");
        sb.append("     FROM TSCMGOODSDT A                        		\n");
        sb.append("    WHERE A.GOODSDT_CODE > '000'               		\n");
        sb.append("      AND A.GOODS_CODE = ?                     		\n");

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
    * Desc : Edit retrieva result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap
    */
    private HashMap[] makeSheetDt(ResultSet rset)  throws Exception {
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
            log.debug("Retrieve makeSheetDt Row : " + retRow);
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
    * @param   entp_code                 : entp_code
    * @param   md_code                   : md_code
    * @param   fromDate                  : fromDate
    * @param   toDate                    : toDate
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("sign_gb"  ));
            pstmt.setString(index++, retrieve.getString("entp_code"  ));
            pstmt.setString(index++, retrieve.getString("md_code"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsScmSignSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsScmSignSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDt(rset));

        }catch(SQLException se){
            log.error("[GoodsScmSignSvc.retrieveDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsScmSignSvc.retrieveDt() Exception : ERR-"+e.getMessage());
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

        sb.append("			 ORDER_MAKE_YN, 	    	\n ");
        sb.append("			 INSTALL_YN, 	    	\n ");
        sb.append("			 DIRECT_SHIP_YN, 	    	\n ");
        sb.append("			 SHIP_MAN_SEQ, 	    	\n ");
        sb.append("			 DIRECT_RETURN_YN, 	    	\n ");
        sb.append("			 RETURN_MAN_SEQ, 	    	\n ");

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
     * Desc : Make SQL ( Update Tscmgoods )
     * </PRE>
     * @param   Tgoods
     * @return  String
     */
     private String makeSqlScmUpdate(Tgoods tgoods) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" UPDATE TSCMGOODS SET                               		 \n ");
         sb.append("        CONFIRM_GOODS_CODE	  		= ?,           			 \n ");
         sb.append("        CONFIRM_DATE                = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),	 \n ");
         sb.append("        MODIFY_DATE                 = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),	 \n ");
         sb.append("        SIGN_GB             		= ?,                	 \n ");
         sb.append("        SIGN_NOTE           		= ?                 	 \n ");
         sb.append("  WHERE GOODS_CODE   		        = ?                 	 \n ");
         sb.append("    AND CONFIRM_GOODS_CODE   	   IS NULL                	 \n ");

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
    * Desc : Make SQL ( Insert tinplanqty )
    * </PRE>
    * @param   tinplanqty
    * @return  String
    */
    private final String insertSqltinplanqty  =  " INSERT INTO TINPLANQTY (   					\n "
                                             +"          GOODS_CODE,      						\n "
                                             +"          GOODSDT_CODE,    						\n "
                                             +"          SEQ,      								\n "
                                             +"          START_DATE,      						\n "
                                             +"          END_DATE,      						\n "
                                             +"          LEAD_TIME ,      						\n "
                                             +"          DAILY_CAPA_QTY ,      					\n "
                                             +"          INPLAN_QTY,    						\n "
                                             +"          MAX_SALE_QTY,    						\n "
                                             +"          INSERT_DATE,    						\n "
                                             +"          INSERT_ID,	    						\n "
                                             +"          MODIFY_DATE, 							\n "
                                             +"          MODIFY_ID )							\n "
                                             +"  VALUES ( 										\n "
                                             +"          ?, 									\n "
                                             +"          ?, 									\n "
                                             +"          TO_CHAR(LPAD(NVL(?,0),5,'0')), 		\n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), 	\n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), 	\n "
                                             +"          ?, 									\n "
                                             +"          ?, 									\n "
                                             +"          ?, 									\n "
                                             +"          ?,	 									\n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), 	\n "
                                             +"          ?, 									\n "
                                             +"          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), 	\n "
                                             +"          ?) 									\n ";

    private int insertSqlLogtinplanqty =  0;

    private String makeSqlInsert(Tinplanqty tinplanqty) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogtinplanqty == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqltinplanqty);
            }
            insertSqlLogtinplanqty = 1;
        }
        return insertSqltinplanqty;
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

            pstmt.setString(index++,tgoods.getOrder_make_yn()       );
            pstmt.setString(index++,tgoods.getInstall_yn()       );
            pstmt.setString(index++,tgoods.getDirect_ship_yn()       );
            pstmt.setString(index++,tgoods.getShip_man_seq()       );
            pstmt.setString(index++,tgoods.getDirect_return_yn()       );
            pstmt.setString(index++,tgoods.getReturn_man_seq()       );

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

            logString.append( tgoods.getOrder_make_yn()       ); logString.append( "/" );
            logString.append( tgoods.getInstall_yn()       ); logString.append( "/" );
            logString.append( tgoods.getDirect_ship_yn()       ); logString.append( "/" );
            logString.append( tgoods.getShip_man_seq()       ); logString.append( "/" );
            logString.append( tgoods.getDirect_return_yn()       ); logString.append( "/" );
            logString.append( tgoods.getReturn_man_seq()       ); logString.append( "/" );

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
    * Desc : Update TSCMGOODS
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int updateScm(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlScmUpdate(tgoods));
            int index = 1;
            pstmt.setString(index++,tgoods.getConfirm_goods_code()  );
            pstmt.setString(index++,tgoods.getConfirm_date()        );
            pstmt.setString(index++,tgoods.getModify_date()         );
            pstmt.setString(index++,tgoods.getScm_sign_gb()         );
            pstmt.setString(index++,tgoods.getScm_sign_note()       );
            pstmt.setString(index++,tgoods.getScm_goods_code()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getConfirm_goods_code()    ); logString.append( "/" );
            logString.append( tgoods.getConfirm_date()         	); logString.append( "/" );
            logString.append( tgoods.getModify_date()         	); logString.append( "/" );
            logString.append( tgoods.getScm_sign_gb()           ); logString.append( "/" );
            logString.append( tgoods.getScm_sign_note()         ); logString.append( "/" );
            logString.append( tgoods.getScm_goods_code()        ); logString.append( "/" );
            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tscmgoods.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tscmgoods.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
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
     * Desc : Insert Tinplanqty
     * </PRE>
     * @param   Connection
     * @param   Tinplanqty
     * @return  int
     */
     public int insert(Connection con, Tinplanqty tinplanqty) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {

             pstmt = con.prepareStatement(makeSqlInsert(tinplanqty));
             int index = 1;

             pstmt.setString(index++,tinplanqty.getGoods_code()         );
             pstmt.setString(index++,tinplanqty.getGoodsdt_code()       );
             pstmt.setString(index++,tinplanqty.getSeq()         		);
             pstmt.setString(index++,tinplanqty.getStart_date()         );
             pstmt.setString(index++,tinplanqty.getEnd_date()         	);
             pstmt.setLong(index++,tinplanqty.getLead_time()        	);
             pstmt.setLong(index++,tinplanqty.getDaily_capa_qty()       );
             pstmt.setLong(index++,tinplanqty.getInplan_qty()         	);
             pstmt.setLong(index++,tinplanqty.getMax_sale_qty()         );
             pstmt.setString(index++,tinplanqty.getInsert_date()        );
             pstmt.setString(index++,tinplanqty.getInsert_id()         	);
             pstmt.setString(index++,tinplanqty.getModify_date()        );
             pstmt.setString(index++,tinplanqty.getModify_id()         	);

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tinplanqty.getGoods_code()      	); logString.append( "/" );
             logString.append( tinplanqty.getGoodsdt_code()    	); logString.append( "/" );
             logString.append( tinplanqty.getSeq()      		); logString.append( "/" );
             logString.append( tinplanqty.getStart_date()      	); logString.append( "/" );
             logString.append( tinplanqty.getEnd_date()      	); logString.append( "/" );
             logString.append( tinplanqty.getLead_time()       	); logString.append( "/" );
             logString.append( tinplanqty.getDaily_capa_qty()   ); logString.append( "/" );
             logString.append( tinplanqty.getInplan_qty()       ); logString.append( "/" );
             logString.append( tinplanqty.getMax_sale_qty()     ); logString.append( "/" );
             logString.append( tinplanqty.getInsert_date()    	); logString.append( "/" );
             logString.append( tinplanqty.getInsert_id()    	); logString.append( "/" );
             logString.append( tinplanqty.getModify_date()    	); logString.append( "/" );
             logString.append( tinplanqty.getModify_id()    	); logString.append( "/" );

             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();
//             logSave.info(""+Integer.toString(executedRtn));

         }catch(SQLException se){
             logSave.error("[tinplanqty.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[tinplanqty.insert() Exception : ERR-"+e.getMessage());
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

}