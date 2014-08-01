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

/**
 * 카탈로그 발송현황 Service class
 *
 * @version 1.0, 2011/01/13
 */

public class SampleInOutListSvc {
	
	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
	private final String blank = "";
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {

        	pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));
            if(!blank.equals(retrieve.getString("seq_samp_no"))){
            	pstmt.setString(index++,retrieve.getString("seq_samp_no"));
            }
            pstmt.setString(index++,retrieve.getString("io_flag"));
            pstmt.setString(index++,retrieve.getString("wh_code"));
            if(!blank.equals(retrieve.getString("receiver_id"))){
            	pstmt.setString(index++,retrieve.getString("receiver_id"));
            }

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SampleInOutListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SampleInOutListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws StoreException{
		
    	StringBuffer sb = new StringBuffer();
    	
		 sb.append("		  SELECT A.IO_FLAG,                                                          \n");
		 sb.append("	       TO_CHAR(A.IO_DATE, 'YYYY/MM/DD') AS IO_DATE,                              \n");
		 sb.append("	       A.GOODS_CODE,                                                             \n");
		 sb.append("	       B.GOODS_NAME,                                                             \n");
		 sb.append("	       A.IO_QTY,                                                                 \n");
		 sb.append("	       A.RECEIVER_ID,                                                            \n");
		 sb.append("	       C.USER_NAME AS RECEIVER_NAME,                                             \n");
		 sb.append("	       A.IO_NOTE,                                                                \n");
		 sb.append("	       A.SEQ_SAMP_NO                                                             \n");
		 sb.append("	  FROM TSAMPLE_INOUT A, TGOODS B, TUSER C                                        \n");
		 sb.append("	 WHERE A.GOODS_CODE = B.GOODS_CODE                                               \n");
		 sb.append("	   AND A.RECEIVER_ID = C.USER_ID                                                 \n");
		 sb.append("	   AND A.IO_DATE BETWEEN TO_DATE(? , 'YYYY/MM/DD') AND TO_DATE(? , 'YYYY/MM/DD') \n");
		 
		 if(!blank.equals(retrieve.getString("seq_samp_no"))){
			 sb.append("	   AND A.SEQ_SAMP_NO = ?                                                     \n");
		 }
		 
		 sb.append("	   AND A.IO_FLAG LIKE ? || '%'                                                   \n");
		 sb.append("	   AND A.WH_CODE = ?                                                             \n");
		 
		 if(!blank.equals(retrieve.getString("receiver_id"))){
			 sb.append("	   AND A.RECEIVER_ID = ?                                                     \n");
		 }
		logPrint(sb, retrieve);

		return sb.toString();
    }

    private void logPrint(StringBuffer query, RetrieveModel retrieve){
    	  //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + query.toString());
            log.debug("from_date  : " + retrieve.getString("fromDate"));
            log.debug("to_date    : " + retrieve.getString("toDate"));
            log.debug("seq_samp_no : " + retrieve.getString("seq_samp_no"));
            log.debug("io_flag  : " + retrieve.getString("io_flag"));
            log.debug("wh_code  : " + retrieve.getString("wh_code"));
            log.debug("receiver_id  : " + retrieve.getString("receiver_id"));
        }
    }
    
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
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

        //= log Column Name -------------------------------------------------
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
}
