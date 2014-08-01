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

public class SampStockListActSvc {
	
	
 /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @return  RetrieveModel
    */
	
	private String makeSql(){
		
		StringBuffer sb = new StringBuffer();
   	
		sb.append(" SELECT                              \n");
		sb.append(" B.GOODS_CODE,                       \n");
		sb.append(" B.GOODS_NAME,                       \n");
		sb.append(" (A.aqty - A.bqty) AS QTY            \n");
		sb.append(" FROM TSTOCK A, TGOODS B             \n");
		sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
		sb.append(" AND A.GOODSDT_CODE='000'            \n");
		sb.append(" AND A.WH_CODE = ?                   \n");
		sb.append(" AND B.GOODS_CODE LIKE ? || '%'      \n");
		
	//	logPrint(sb, retrieve);
		return sb.toString();
   }

   private void logPrint(){
   	
       
   }

       

}
