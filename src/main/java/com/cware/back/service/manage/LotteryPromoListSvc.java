
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
 * 추첨프로모션 추첨현황 Service class
 *
 * @version 1.0, 2006/11/15
 */
public class LotteryPromoListSvc {


    public LotteryPromoListSvc() {}

    
    public String makeSql()  {
        StringBuffer sb = new StringBuffer();
        
        
        sb.append(" SELECT PM.LOTTERY_PROMO_NO   	AS LOTTERY_PROMO_NO,      	   \n");
        sb.append("	       PM.LOTTERY_PROMO_NAME 	AS LOTTERY_PROMO_NAME,         \n");
        sb.append("	       PM.DO_TYPE            	AS DO_TYPE,                    \n");
        sb.append("	       TO_CHAR(PM.END_DATE, 'YYYY/MM/DD') AS END_DATE,         \n"); 
        sb.append("	       PM.PROVIDE_CNT        	AS PROVIDE_CNT,                \n");
        sb.append("	       PM.CONFIRM_CNT        	AS CONFIRM_CNT,                \n");
        sb.append("	       ( SELECT COUNT(*) AS CNT                                \n");
        sb.append("	          FROM TLOTTERYPROMOCUST A                             \n");
        sb.append("	          WHERE PM.LOTTERY_PROMO_NO = A.LOTTERY_PROMO_NO) CNT  \n");
        sb.append("	FROM TLOTTERYPROMOM  PM                                        \n");
        sb.append(" WHERE PM.END_YN        = '1'                                   \n");
        sb.append("   AND PM.END_DATE     >= TO_DATE(?,'YYYY/MM/DD')  			   \n");       
        sb.append("   AND PM.END_DATE      < TO_DATE(?,'YYYY/MM/DD') + 1  		   \n");  
   
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
    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
//            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
            
            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        return null;
       
    }


 
    
}
