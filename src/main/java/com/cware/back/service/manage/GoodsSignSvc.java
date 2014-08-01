
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



public class GoodsSignSvc {

  

    public GoodsSignSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(){

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.GOODS_CODE,   \n ");
        sb.append("         A.SIGN_SEQ,     \n ");
        sb.append("         B.GOODS_NAME,   \n ");
        sb.append("         B.SET_YN,       \n ");
        sb.append("         B.GIFT_YN,      \n ");
        sb.append("         B.SAMPLE_YN,    \n ");
        sb.append("         B.DM_YN,        \n ");
        sb.append("         TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,   \n ");
        sb.append("         A.SIGN_GB,      \n ");
        sb.append("         A.SIGN_DATE,    \n ");
        sb.append("         A.SIGN_ID,      \n ");
        sb.append("         A.SIGN_NO_CODE, \n ");
        sb.append("         A.SIGN_NO_NOTE, \n ");
        sb.append("         A.INSERT_ID,    \n ");
        sb.append("         A.INSERT_DATE,  \n ");
        sb.append("         B.SALE_GB,      \n ");
        sb.append("         B.SQC_GB,       \n ");
        sb.append("         B.BUY_MED,       \n ");
        sb.append("         B.SIGN_GB AS TGOODS_SIGN_GB,       \n ");
        sb.append("         B.STOCK_CHK_PLACE       \n ");
        sb.append("    FROM TGOODSSIGN A,   \n ");
        sb.append("         TGOODS B        \n ");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE   \n ");
        sb.append("     AND B.SALE_GB    <> '19'          \n ");
        sb.append("     AND A.GOODS_CODE LIKE ?           \n ");
        sb.append("     AND A.SIGN_GB    LIKE ?           \n ");
        sb.append("     AND B.SALE_GB    LIKE ?           \n ");

        //= log SQL -------------------------------------------------
     
        return sb.toString();
    }

    
    private String makeSqlDt1() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.GOODS_CODE   	AS GOODS_CODE,   	\n");
        sb.append("         A.SIGN_SEQ     	AS SIGN_SEQ,     	\n");
        sb.append("         A.SIGN_GB      	AS SIGN_GB,      	\n");
        sb.append("         TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,   \n");
        sb.append("         A.BUY_PRICE    	AS BUY_PRICE,    	\n");
        sb.append("         A.BUY_COST     	AS BUY_COST,     	\n");
        sb.append("         A.BUY_VAT      	AS BUY_VAT,      	\n");
        sb.append("         A.BUY_VAT_RATE 	AS BUY_VAT_RATE, 	\n");
        sb.append("         A.SALE_PRICE   	AS SALE_PRICE,   	\n");
        sb.append("         A.SALE_VAT     	AS SALE_VAT,     	\n");
        sb.append("         A.SALE_VAT_RATE	AS SALE_VAT_RATE,	\n");
        sb.append("         A.CUST_PRICE   	AS CUST_PRICE,   	\n");
        sb.append("         A.SAVEAMT_RATE 	AS SAVEAMT_RATE, 	\n");
        sb.append("         A.SAVEAMT      	AS SAVEAMT,      	\n");
        sb.append("         B.NOREST_ALLOT_MONTHS   AS NOREST_ALLOT_MONTHS,  \n");
        sb.append("         B.DELY_TYPE    	AS DELY_TYPE     	\n");
        sb.append("    FROM TGOODSSIGN A,                   	\n");
        sb.append("         TGOODS B                        	\n");
        sb.append("   WHERE A.GOODS_CODE    = B.GOODS_CODE  	\n");
        sb.append("     AND A.GOODS_CODE    = ?     \n");
        sb.append("     AND A.SIGN_SEQ      = ?     \n");
        sb.append("     AND A.SIGN_GB       = ?     \n");

        //= log SQL -------------------------------------------------
     
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; 단품정보 Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt2(){

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.GOODS_CODE,          \n");
        sb.append("        A.GOODSDT_CODE,        \n");
        sb.append("        A.GOODSDT_INFO,        \n");
        sb.append("        A.SALE_GB              \n");
        sb.append("   FROM TGOODSDT A             \n");
        sb.append("  WHERE A.GOODSDT_CODE > '000' \n");
        sb.append("    AND A.GOODS_CODE = ?       \n");

        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt3(){

        StringBuffer sb = new StringBuffer();


        sb.append(" SELECT A.GOODS_CODE,      \n");
        sb.append("        A.GOODS_NAME,     \n");
        sb.append("        A.KEYWORD,        \n");
        sb.append("        A.SALE_GB,        \n");
        sb.append("        A.ENTP_CODE,      \n");
        sb.append("        C.ENTP_NAME,      \n");
        sb.append("        A.MD_CODE,        \n");
        sb.append("        D.MD_NAME,        \n");
        sb.append("        A.LGROUP,         \n");
        sb.append("        B.LGROUP_NAME,    \n");
        sb.append("        A.MGROUP,         \n");
        sb.append("        B.MGROUP_NAME,    \n");
        sb.append("        A.SGROUP,         \n");
        sb.append("        B.SGROUP_NAME,    \n");
        sb.append("        A.DGROUP,         \n");
        sb.append("        B.DGROUP_NAME,    \n");
        sb.append("        A.SIGN_GB,        \n");
        sb.append("        A.BUY_MED,        \n");
        sb.append("        A.TAX_YN,         \n");
        sb.append("        A.COST_TAX_YN,    \n");
        sb.append("        A.DELY_TYPE,      \n");
        sb.append("        A.POST_YN,        \n");
        sb.append("        A.NOREST_ALLOT_YN,\n");
        sb.append("        A.STOCK_CHK_PLACE,\n");
        sb.append("        A.ORDER_MIN_QTY,  \n");
        sb.append("        A.SET_GOODS_YN,   \n");
        sb.append("        A.GIFT_YN,        \n");
        sb.append("        A.PAY_YN,         \n");
        sb.append("        A.GIFT_RETURN_YN, \n");
        sb.append("        A.EXCH_YN,        \n");
        sb.append("        A.RETURN_YN,      \n");
        sb.append("        A.OUT_STOCK_YN,   \n");
        sb.append("        A.GIFT_RETURN_YN, \n");
        sb.append("        A.CUST_DC_YN,     \n");
        sb.append("        A.MAKECO_CODE,    \n");
        sb.append("        E.MAKECO_NAME COMP_MAKECO_NAME,   \n");
        sb.append("        A.BRAND_CODE,     \n");
        sb.append("        F.BRAND_NAME,     \n");
        sb.append("        A.ORIGIN_CODE,    \n");
        sb.append("        G.CODE_NAME COMP_ORIGIN_NAME,   \n");
        sb.append("        A.WH_CODE,        \n");
        sb.append("        A.SET_YN,         \n");
        sb.append("        A.ENTP_MAN_SEQ,   \n");
        sb.append("        A.HANDLE_CODE,    \n");
        sb.append("        A.MIXPACK_YN,     \n");
        sb.append("        A.INSERT_DATE,    \n");
        sb.append("        A.INSERT_ID,      \n");
        sb.append("        A.MODIFY_DATE,    \n");
        sb.append("        A.MODIFY_ID  ,    \n");
        sb.append("        A.SQC_GB,         \n");
        sb.append("        A.QC_LGROUP,      \n");
        sb.append("        '' QC_LGROUP_NAME, \n");
//=        sb.append("        Q.QC_LGROUP_NAME, \n");
        sb.append("        A.QC_MGROUP,      \n");
        sb.append("        '' QC_MGROUP_NAME, \n");
        sb.append("        A.ORDER_MEDIA_ALL_YN, \n");
        sb.append("        A.ORDER_MEDIA,    \n");
        sb.append("        A.ARS_NAME,       \n");
        sb.append("        A.SAMPLE_YN,     \n");
        sb.append("        A.DM_YN,         \n");
        sb.append("        A.MASTER_CODE,   \n");
        sb.append("        H.GOODS_NAME AS MASTER_NAME,   \n");
        sb.append("        A.IN_UNIT,       \n");
        sb.append("        A.IN_UNIT_QTY,   \n");
        sb.append("        A.V_VOLUME       \n");
        sb.append("   FROM TGOODS A,         \n");
        sb.append("        TGOODSKINDS B,    \n");
        sb.append("        TENTERPRISE C,    \n");
        sb.append("        TMD D,            \n");
        sb.append("        TMAKECOMP E,      \n");
        sb.append("        TBRAND F,         \n");
        sb.append("        TCODE G,          \n");
//        sb.append("        TQCKINDS Q,       \n");
        sb.append("        TGOODS H          \n");
        sb.append("  WHERE A.LGROUP = B.LGROUP            \n");
        sb.append("    AND A.MGROUP = B.MGROUP            \n");
        sb.append("    AND A.SGROUP = B.SGROUP            \n");
        sb.append("    AND A.DGROUP = B.DGROUP            \n");
        sb.append("    AND A.ENTP_CODE = C.ENTP_CODE      \n");
        sb.append("    AND A.MD_CODE = D.MD_CODE          \n");
        sb.append("    AND A.MAKECO_CODE = E.MAKECO_CODE  \n");
        sb.append("    AND A.BRAND_CODE = F.BRAND_CODE    \n");
        sb.append("    AND A.ORIGIN_CODE = G.CODE_MGROUP  \n");
        sb.append("    AND G.CODE_LGROUP = 'B023'         \n");
//        sb.append("    AND A.QC_LGROUP = Q.QC_LGROUP(+)   \n");
//        sb.append("    AND A.QC_MGROUP = Q.QC_MGROUP(+)   \n");
        sb.append("    AND A.MASTER_CODE = H.GOODS_CODE   \n");
        sb.append("    AND A.GOODS_CODE = ?               \n");

        //= log SQL -------------------------------------------------
     
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDt4() {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.GOODS_CODE,     \n");
        sb.append("        A.GOODSDT_CODE,   \n");
        sb.append("        A.SEQ,            \n");
        sb.append("        TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,     \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,       \n");
        sb.append("        A.LEAD_TIME,      \n");
        sb.append("        A.DAILY_CAPA_QTY, \n");
        sb.append("        A.INPLAN_QTY, 	 \n");
        sb.append("        A.MAX_SALE_QTY    \n");
        sb.append("   FROM TINPLANQTY A      \n");
        sb.append("  WHERE A.GOODS_CODE =  ? \n");
        sb.append("    AND A.GOODSDT_CODE =? \n");

        return sb.toString();
    }


   
    public String makeSqlUpdate(){

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODSSIGN SET     \n ");
        sb.append("         APPLY_DATE    = TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("         SIGN_GB       = ?, \n ");
        sb.append("         SIGN_DATE     = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         SIGN_ID       = ?, \n ");
        sb.append("         SIGN_NO_CODE  = ?, \n ");
        sb.append("         SIGN_NO_NOTE  = ?  \n ");
        sb.append("   WHERE GOODS_CODE  = ?    \n ");
        sb.append("     AND SIGN_SEQ    = ?    \n ");

        //= log SQL -------------------------------------------------
   
        return sb.toString();
    }

    public String makeSqlUpdateGoodsPrice(){

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODSPRICE         \n ");
        sb.append("     SET   (BUY_PRICE,     BUY_COST,     BUY_VAT,    BUY_VAT_RATE,  SALE_PRICE,    SALE_VAT,     \n ");
        sb.append("            SALE_VAT_RATE, CUST_PRICE,   SAVEAMT_RATE, SAVEAMT,    INSERT_DATE,   INSERT_ID )   \n ");
        sb.append("  = (SELECT BUY_PRICE,     BUY_COST,     BUY_VAT,    BUY_VAT_RATE,  SALE_PRICE,    SALE_VAT,     \n ");
        sb.append("            SALE_VAT_RATE, CUST_PRICE,   SAVEAMT_RATE, SAVEAMT,    TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),     ?\n ");
        sb.append("       FROM TGOODSSIGN       \n ");
        sb.append("      WHERE GOODS_CODE = ?   \n ");
        sb.append("        AND SIGN_SEQ   = ?)  \n ");
        sb.append("   WHERE ( GOODS_CODE,  APPLY_DATE ) = ( SELECT GOODS_CODE,  ?  \n ");
        sb.append("                                           FROM TGOODSSIGN      \n ");
        sb.append("                                          WHERE GOODS_CODE = ?  \n ");
        sb.append("                                            AND SIGN_SEQ   = ?) \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tgoodsprice 변경 )
    * </PRE>
    * @param   Tgoodssign
    * @return  String
    */
    public String makeSqlInsertGoodsPrice() {

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TGOODSPRICE    \n ");
        sb.append("          (GOODS_CODE,   APPLY_DATE,     BUY_PRICE,      BUY_COST,       BUY_VAT, 		BUY_VAT_RATE, \n ");
        sb.append("          SALE_PRICE,    SALE_VAT,       SALE_VAT_RATE,  CUST_PRICE,     SAVEAMT_RATE,   SAVEAMT, \n ");
        sb.append("          INSERT_DATE,   INSERT_ID )                                       \n ");
        sb.append("   SELECT GOODS_CODE,    TO_DATE(?, 'YYYY/MM/DD'), BUY_PRICE,      BUY_COST,       BUY_VAT, BUY_VAT_RATE, \n ");
        sb.append("          SALE_PRICE,     SALE_VAT,      SALE_VAT_RATE,	CUST_PRICE,     SAVEAMT_RATE,   SAVEAMT, \n ");
        sb.append("          TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), ? \n ");
        sb.append("     FROM TGOODSSIGN         \n ");
        sb.append("    WHERE GOODS_CODE = ?     \n ");
        sb.append("      AND SIGN_SEQ   = ?     \n ");


        return sb.toString();
    }

    public String makeSqlUpdateGoods(){

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODS           \n ");
        sb.append("     SET SIGN_GB     = ?, \n ");
        sb.append("         MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID   = ?  \n ");
        sb.append("   WHERE GOODS_CODE  = ?  \n ");


        return sb.toString();
    }

    public String makeSqlMaxOrderDate(){

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT TO_CHAR(TRUNC(MAX(ORDER_DATE)), 'YYYY/MM/DD') AS MAX_ORDER_DATE \n ");
        sb.append("    FROM TORDERDT               \n ");
        sb.append("   WHERE GOODS_CODE = ?         \n ");

        //= log SQL -------------------------------------------------

        return sb.toString();
    }


    public String makeSqlMaxInDate(){

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT TO_CHAR(MAX(IN_DATE), 'YYYY/MM/DD')  AS MAX_IN_DATE  \n ");
        sb.append("    FROM TINM M,               \n ");
        sb.append("         TINDT D               \n ");
        sb.append("   WHERE M.IN_DATE >= TO_DATE(?, 'YYYY/MM/DD')        \n ");
        sb.append("     AND M.IN_NO = D.IN_NO     \n ");
        sb.append("     AND D.GOODS_CODE = ?      \n ");
        sb.append("     AND D.IN_QTY > 0          \n ");

    
        return sb.toString();
    }


    
    public String makeSqlBuyPrice1() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT BUY_COST         \n ");
        sb.append("    FROM TGOODSPRICE A    \n ");
        sb.append("   WHERE GOODS_CODE = ?   \n ");
        sb.append("     AND APPLY_DATE = ( SELECT MAX(B.APPLY_DATE)            \n ");
        sb.append("                          FROM TGOODSPRICE B                \n ");
        sb.append("                         WHERE A.GOODS_CODE = B.GOODS_CODE  \n ");
        sb.append("                           AND B.APPLY_DATE <= SYSDATE )   \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }


    public String makeSqlBuyPrice2(){

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT BUY_COST        \n ");
        sb.append("    FROM TGOODSSIGN      \n ");
        sb.append("   WHERE SIGN_GB = '00'  \n ");
        sb.append("     AND GOODS_CODE = ?  \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    
    public String makeSqlMaxEoutDate() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT TO_CHAR(MAX(EOUT_DATE), 'YYYY/MM/DD') AS MAX_EOUT_DATE \n ");
        sb.append("    FROM TENTPOUTM M,                  \n ");
        sb.append("         TENTPOUTDT D                  \n ");
        sb.append("   WHERE M.EOUT_DATE >= TO_DATE(?, 'YYYY/MM/DD') \n ");
        sb.append("     AND M.EOUT_NO   = D.EOUT_NO       \n ");
        sb.append("     AND D.GOODS_CODE = ?              \n ");
        sb.append("     AND D.EOUT_AQTY + D.EOUT_BQTY > 0 \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tgoodssign 변경 )
    * </PRE>
    * @param   Tgoodssign
    * @return  String
    */
    public String makeSqlChkGoodsPrice() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(B.GOODS_CODE) AS CNT  \n ");
        sb.append("    FROM TGOODSPRICE A,              \n ");
        sb.append("         TGOODS B                    \n ");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE \n ");
        sb.append("     AND (A.GOODS_CODE, A.APPLY_DATE) = (SELECT GOODS_CODE, TO_DATE(?, 'YYYY/MM/DD') \n ");
        sb.append("                                           FROM TGOODSSIGN       \n ");
        sb.append("                                          WHERE GOODS_CODE = ?   \n ");
        sb.append("                                             AND SIGN_SEQ  = ?   \n ");
        sb.append("                                             AND ROWNUM = 1  )    \n ");
        sb.append("    AND B.GOODS_CODE = ? \n ");
        sb.append("    AND ROWNUM = 1       \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    
    public String makeSqlChkGoods() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(GOODS_CODE) AS CNT    \n ");
        sb.append("    FROM TGOODS         \n ");
        sb.append("   WHERE GOODS_CODE = ? \n ");
        sb.append("     AND SIGN_GB    = ? \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }


    public String makeSqlgetInCnt1() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(A.GOODS_CODE) AS CNT  \n ");
        sb.append("    FROM TINDT A, TINM B             \n ");
        sb.append("   WHERE GOODS_CODE = ?              \n ");
        sb.append("     AND A.IN_NO    = B.IN_NO        \n ");
        sb.append("     AND B.IN_DATE < TO_DATE(TO_CHAR(SYSDATE, 'yyyy/mm') || '/01','YYYY/MM/DD') \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    
    public String makeSqlgetInCnt2() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(GOODS_CODE) AS CNT \n ");
        sb.append("    FROM TINDT          \n ");
        sb.append("  WHERE GOODS_CODE = ?  \n ");

        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

    public String makeSqlgetOrderCnt(){

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(GOODS_CODE) AS CNT  \n ");
        sb.append("    FROM TORDERDT            \n ");
        sb.append("   WHERE GOODS_CODE = ?      \n ");
        sb.append("     AND ORDER_DATE <= SYSDATE  \n ");

        //= log SQL -------------------------------------------------

        return sb.toString();
    }

    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
//            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

//            //= Modify
//            tempString = ComUtils.objToStr(hmSheet.get("SIGN_NO_NOTE"),"");
//            tempString = ComUtils.replace(tempString, String.valueOf((char)13) , "\\r");
//            tempString = ComUtils.replace(tempString, String.valueOf((char)10) , "\\n");
//
//            hmSheet.put("SIGN_NO_NOTE", tempString);

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        return (HashMap[])collection.toArray(new HashMap[0]);
    }
public HashMap[] makeSheetDt2(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
          //  hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }
        return null;
    }


    
    

    
    

    
    
    

    
    

    public String makeSqlchkSignCondition() {

        StringBuffer sb = new StringBuffer();


        sb.append("  SELECT GOODSDT_CODE    \n ");
        sb.append("    FROM TGOODSDT        \n ");
        sb.append("   WHERE GOODS_CODE =  ? \n ");
//        if((retrieve.getString("stock_chk_place")).equals("1")){
  //          sb.append("   AND GOODSDT_CODE = '000' \n ");
    //    }else if((retrieve.getString("stock_chk_place")).equals("2")){
            sb.append("   AND GOODSDT_CODE > '000'   \n ");
            sb.append("   AND SALE_GB < '19'         \n ");
            sb.append("   AND INSERT_DATE <= SYSDATE \n ");
      //  }else{
            sb.append("   AND SALE_GB < '19'         \n ");
            sb.append("   AND INSERT_DATE <= SYSDATE \n ");
        //}
        sb.append("   ORDER BY GOODSDT_CODE \n ");

        //= log SQL -------------------------------------------------
        
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
    public HashMap[] makeSheetchkSignCondition(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
        //    hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify;

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    //= Get getInPlanCnt -------------------------------------------------
    /**
    * <PRE>
    * Desc : Get getInPlanCnt from TINPLANQTY
    * </PRE>
    * @param   Connection
    * @param   tgoodssign
    * @return  int
    */
    public String makeSqlgetInPlanCnt() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(GOODSDT_CODE) AS CNT \n ");
        sb.append("    FROM TINPLANQTY       \n ");
        sb.append("   WHERE GOODS_CODE   = ? \n ");
        sb.append("     AND GOODSDT_CODE = ? \n ");

        return sb.toString();
    }


    public String makeSqlchkSignGb() {

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT B.CHK_SIGN_GB FROM (         \n ");
        sb.append("     SELECT SIGN_GB AS CHK_SIGN_GB    \n ");
        sb.append("      FROM TGOODSSIGN                 \n ");
        sb.append("      WHERE GOODS_CODE   =  ?         \n ");
        sb.append("       ORDER BY SIGN_SEQ DESC) B      \n ");
        sb.append("  WHERE ROWNUM = 1                    \n ");

        //= log SQL -------------------------------------------------
       
        return sb.toString();
    }

}

