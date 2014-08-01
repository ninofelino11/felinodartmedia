
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

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;


/**
 * Goods Image List Service class
 *
 * @version 1.0, 2006/07/04
 */
public class GoodsImageListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public GoodsImageListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Goods Image List select
    * </PRE>
    * @param   poolName                           : Database pool name
    * @param   entp_code                          : 업체코드
    * @param   close_yn                           : 거래여부
    */

    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        long    categoryCode = 0;
        long    modRet1   = 0;
        long    modRet2   = 0;
        long    modRet3   = 0;
        long    divideRet = 0;
        String  data1     = "";

        categoryCode = retrieve.getLong("category_code");
        modRet1      = categoryCode%10000000;
        modRet2      = categoryCode%100000;
        modRet3      = categoryCode%1000;


        sb.append("SELECT DISTINCT                         \n");
        sb.append("       GOODS.GOODS_CODE                 \n");
        sb.append("     , GOODS.GOODS_NAME                 \n");
        sb.append("     , GOODS.SALE_GB                    \n");
        sb.append("     , GOODSIMAGE.IMAGE_L               \n");
        sb.append("     , GOODSIMAGE.IMAGE_M               \n");
        sb.append("     , GOODSIMAGE.IMAGE_D               \n");
        sb.append("     , GOODSIMAGE.IMAGE_S               \n");
        sb.append("     , GOODSIMAGE.IMAGE_SS              \n");
        sb.append("     , GOODSIMAGE.IMAGE_URL             \n");
        sb.append("  FROM TGOODS         GOODS             \n");
        sb.append("     , TGOODSIMAGE    GOODSIMAGE        \n");
        sb.append("     , TCATEGORYGOODS GOODSCATEGORY     \n");
        sb.append(" WHERE GOODSCATEGORY.GOODS_CODE         = GOODSIMAGE.GOODS_CODE(+)      \n");
        sb.append("   AND GOODSCATEGORY.GOODS_CODE = GOODS.GOODS_CODE      \n");
        sb.append("   AND GOODSIMAGE.IMAGE_NO(+)   = '01'      \n");
        sb.append("   AND GOODS.INSERT_DATE >= TO_DATE('" + retrieve.getString("fromDate") + "', 'YYYY/MM/DD')      \n");
        sb.append("   AND GOODS.INSERT_DATE <  TO_DATE('" + retrieve.getString("toDate") + "', 'YYYY/MM/DD') + 1     \n");

        // category condition
        if (!retrieve.getString("category_code").equals("")) {
	        if (!retrieve.getString("category_code").equals("0")) {

	            if(modRet1 == 0 ) {
	                divideRet = categoryCode/10000000;
	                data1 = String.valueOf(divideRet);
	                sb.append("   AND GOODSCATEGORY.CATEGORY_CODE LIKE '" + data1 + "%' \n");
	            }else if (modRet2 == 0) {
	                divideRet = categoryCode/100000;
	                data1 = String.valueOf(divideRet);
	                sb.append("   AND GOODSCATEGORY.CATEGORY_CODE LIKE '" + data1 + "%' \n");
	            }else if (modRet3 == 0) {
	                divideRet = categoryCode/10000;
	                data1 = String.valueOf(divideRet);
	                sb.append("   AND GOODSCATEGORY.CATEGORY_CODE LIKE '" + data1 + "%' \n");
	            }else {
	                sb.append("   AND GOODSCATEGORY.CATEGORY_CODE = '" + categoryCode + "' \n");
	            }
	        }
        }
        sb.append("   AND GOODS.SALE_GB = '00'                  \n");

        // goods_code
        if(!retrieve.getString("goods_code").equals("")){
            sb.append("   AND GOODS.GOODS_CODE = '" + retrieve.getString("goods_code") + "' \n");
        }

        sb.append("   AND TO_DATE('" + retrieve.getString("fromDate") + "', 'YYYY/MM/DD') <= GOODSCATEGORY.DISPLAY_END_DATE \n");
        sb.append("   AND TO_DATE('" + retrieve.getString("toDate") + "', 'YYYY/MM/DD')   >= GOODSCATEGORY.DISPLAY_START_DATE \n");
        sb.append("   ORDER BY LPAD(GOODS.GOODS_CODE, 10, '0') \n");

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
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement       stmt       = null;
        ResultSet       rset       = null;

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
