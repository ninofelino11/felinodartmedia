
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



/**
 * Retrive Goods Simple List Service class
 *
 * @version 1.0, 2008/05/13
 */
public class GoodsSimpleListSvc {



    public GoodsSimpleListSvc() {}


    public String makeSql() {

        StringBuffer sb = new StringBuffer();

        sb.append("    SELECT /*+ RULE */     \n");
        sb.append("           A.GOODS_CODE,   \n");
        sb.append("           A.GOODS_NAME,   \n");  
        sb.append("           A.SET_YN,       \n");
        sb.append("           A.SAMPLE_YN,    \n");
        sb.append("           A.DM_YN,        \n");
        sb.append("           A.GIFT_YN,      \n");
        sb.append("           A.PAY_YN,       \n");
        sb.append("           D.MD_NAME,      \n");
        sb.append("           C.ENTP_CODE,    \n");
        sb.append("           C.ENTP_NAME,    \n"); 
        sb.append("           A.SALE_GB,      \n");
        sb.append("           A.SQC_GB,       \n");
        sb.append("           B.LGROUP_NAME,  \n");   
        sb.append("           B.MGROUP_NAME,  \n");   
        sb.append("           B.SGROUP_NAME,  \n");   
        sb.append("           B.DGROUP_NAME,  \n");   
        sb.append("           E.BUY_PRICE,    \n");
        sb.append("           NVL(E.SALE_PRICE, 0) SALE_PRICE,   \n"); 
        sb.append("           TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,   \n");
        sb.append("           M.ENTP_MAN_SEQ,   \n");
        sb.append("           M.ENTP_MAN_NAME,  \n"); 
        sb.append("           DECODE(M.ENTP_MAN_TEL2,NULL,' ',LTRIM(M.ENTP_MAN_DDD) || ')' || LTRIM(M.ENTP_MAN_TEL1) || '-' || LTRIM(M.ENTP_MAN_TEL2)) AS ENTP_MAN_TEL,   \n");
        sb.append("           DECODE(M.ENTP_MAN_FAX3,NULL,' ',LTRIM(M.ENTP_MAN_FAX1) || ')' || LTRIM(M.ENTP_MAN_FAX2) || '-' || LTRIM(M.ENTP_MAN_FAX3)) AS ENTP_FAX_TEL,  \n");
        sb.append("           DECODE(M.ENTP_MAN_HP3,NULL,' ',LTRIM(M.ENTP_MAN_HP1) || ')' || LTRIM(M.ENTP_MAN_HP2) || '-' || LTRIM(M.ENTP_MAN_HP3)) AS ENTP_HAND_TEL,     \n");
        sb.append("           A.NOREST_ALLOT_MONTHS,  \n");
        sb.append("           E.SAVEAMT,               \n"); 
        sb.append("           A.DELY_TYPE,             \n");
        sb.append("           A.SIGN_GB,               \n");
        sb.append("           ''   AS SQC_SEQ,    \n");
        sb.append("           '00' AS SQC_SIGN,    \n");
        sb.append("           A.MASTER_CODE    \n");
        sb.append("      FROM TGOODS A,                        \n"); 
        sb.append("           TGOODSKINDS B,                   \n");
        sb.append("           TENTERPRISE C,                   \n");
        sb.append("           TMD D,                           \n");
        sb.append("           TGOODSPRICE E,                   \n");
        sb.append("           TENTPUSER    M                   \n");
        sb.append("     WHERE A.LGROUP = B.LGROUP              \n");
        sb.append("       AND A.MGROUP = B.MGROUP              \n");
        sb.append("       AND A.SGROUP = B.SGROUP              \n");
        sb.append("       AND A.DGROUP = B.DGROUP              \n");
        sb.append("       AND A.ENTP_CODE = C.ENTP_CODE        \n");
        sb.append("       AND A.MD_CODE = D.MD_CODE            \n");
        sb.append("       AND A.GOODS_CODE = E.GOODS_CODE(+)                                                                                    \n");
        sb.append("       AND NVL(E.APPLY_DATE, SYSDATE) = ( SELECT NVL(MAX(F.APPLY_DATE), SYSDATE)                                             \n");
        sb.append("                                            FROM TGOODSPRICE F                                                               \n");
        sb.append("                                           WHERE A.GOODS_CODE = F.GOODS_CODE                                                 \n");
        sb.append("                                             AND DECODE(SIGN(F.APPLY_DATE-SYSDATE), 1, SYSDATE, APPLY_DATE) <= SYSDATE )     \n");
        sb.append("       AND A.ENTP_CODE    = M.ENTP_CODE(+)          \n"); 
        sb.append("       AND A.ENTP_MAN_SEQ = M.ENTP_MAN_SEQ(+)       \n");
        sb.append("       AND A.GOODS_CODE   LIKE ? || '%'           \n");

        //= log SQL -------------------------------------------------
      
        return sb.toString();
    }
    
    public String makeSqldt() {

        StringBuffer sb = new StringBuffer();

       sb.append("select a.goods_code,               \n");
       sb.append("       b.goodsdt_code,  			 \n");
       sb.append("       a.goods_name,     			 \n");
       sb.append("       b.color          			 \n");
       sb.append("       b.color_name,    			 \n");
       sb.append("       b.size_name,                \n");
       sb.append("       b.pattern_code,             \n");
       sb.append("       b.pattern_name,             \n");
       sb.append("       b.form_code,                \n");
       sb.append("       b.form_name,                \n");
       sb.append("       b.goodsdt_info,             \n");
       sb.append("       b.sale_gb,                  \n");
       sb.append("       b.barcode,                  \n");
       sb.append("       b.insert_id                 \n");
       sb.append("       from tgoods a, tgoodsdt b   \n");
       sb.append("");
       sb.append("");
       sb.append("");
       
       
        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }
    
    /*
    
    select a.goods_code, b.goodsdt_code, a.goods_name,
    b.color_code, b.color_name, b.size_code, b.size_name, b.pattern_code,
    b.pattern_name, b.form_code, b.form_name, b.goodsdt_info, b.sale_gb, b.barcode, b.insert_id
    from tgoods a, tgoodsdt b 
    where a.goods_code= b.goods_code
    
   */

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection   = new ArrayList();
        HashMap      hmSheet      = null;
        long         retRow       = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
//            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
     
        return (HashMap[])collection.toArray(new HashMap[0]);
    }




    
    public String makeSqlDetail() {

        StringBuffer sb = new StringBuffer();
        sb.append("   select GOODS_CODE,    		  \n");
        sb.append("          GOODSDT_CODE,  		  \n");
        sb.append("          GOODS_NAME,    		  \n");
        sb.append("          COLOR_NAME,    		  \n");
        sb.append("          SIZE_NAME,     		  \n");
        sb.append("          PATTERN_NAME,  		  \n");
        sb.append("          OTHER_TEXT,    		  \n");
        sb.append("          GOODSDT_INFO,   		  \n");
        sb.append("          SALE_GB,                 \n");
        sb.append("          BARCODE FROM GOODSDT     \n");
      

        return sb.toString();
    }


    public HashMap[] makeSheetDetail(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
//            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }

        return (HashMap[])collection.toArray(new HashMap[0]);
    }

    

}
