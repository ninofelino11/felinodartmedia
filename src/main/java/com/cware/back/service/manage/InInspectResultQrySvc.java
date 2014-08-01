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
 * 입고검사결과조회 Service class
 *
 * @version 1.0, 2011/01/26
 */

public class InInspectResultQrySvc {
	
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
        	
        	pstmt = con.prepareStatement(slectUnitRetrieveSQL(retrieve));
        	pstmt = parameterSetting(pstmt,retrieve);
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT",makeSheet(rset));
            
        }catch(SQLException se){
            log.error("[InInspectResultQrySvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[InInspectResultQrySvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    private PreparedStatement parameterSetting(PreparedStatement pstmt,RetrieveModel retrieve) throws SQLException {
    	
    	int index = 1;
    	
        pstmt.setString(index++,retrieve.getString("fromDate"));
        pstmt.setString(index++,retrieve.getString("toDate"));
        
        if(!blank.equals(retrieve.getString("balju_no"))){
        	pstmt.setString(index++,retrieve.getString("balju_no"));
        }
        if(!blank.equals(retrieve.getString("tester_id"))){
        	pstmt.setString(index++,retrieve.getString("tester_id"));
        }
        if(!blank.equals(retrieve.getString("goods_code"))){
        	pstmt.setString(index++,retrieve.getString("goods_code"));
        }
        if(!blank.equals(retrieve.getString("md_code"))){
        	pstmt.setString(index++,retrieve.getString("md_code"));
        }
		return pstmt;
    }

	private String slectUnitRetrieveSQL(RetrieveModel retrieve) throws StoreException{
		
    	String selectSql = "";
    	String unit = retrieve.getString("unitRetrieve");
    	
    	if(("1").equals(unit)){
    		selectSql = makeSqlBaljuNumber(retrieve);
    	}else if(("2").equals(unit)){
    		selectSql = makeSqlTester(retrieve);
    	}else if(("3").equals(unit)){
    		selectSql = makeSqlGoodsKind(retrieve);
    	}else if(("4").equals(unit)){
    		selectSql = makeSqlVendor(retrieve);
    	}else if(("5").equals(unit)){
    		selectSql = makeSqlMd(retrieve);
    	}
    	
    	return selectSql;
    }
   
	private String makeSqlBaljuNumber(RetrieveModel retrieve) throws StoreException{
    	
    	StringBuffer sb = new StringBuffer();
    	
	   	sb.append(" SELECT SUBSTR(A.BALJU_NO,1,8)||'-'||SUBSTR(A.BALJU_NO,9) AS UNIT_RETRIEVE , --조회단위    \n");
	    sb.append("    A.GOODS_CODE,                                                             \n");
	    sb.append("    A.GOODSDT_CODE,                                                           \n");
	    sb.append("    B.GOODS_NAME,                                                             \n");
	    sb.append("    C.GOODSDT_INFO, --단품상세                                                	  		 \n");
	    sb.append("    A.QA_QTY, --검사수량                                                     				 \n");
	    sb.append("    1 AS QA_CNT, --검사건수                                                  			 	 \n");
	    sb.append("    CASE WHEN A.SQC_GB = '16' THEN A.QA_QTY ELSE 0 END AS PASS_QTY, --합격      \n");
	    sb.append("    CASE WHEN A.SQC_GB != '16' THEN A.QA_QTY ELSE 0 END AS FAIL_QTY --불합격    \n");
		sb.append("    FROM TAFTERQA A, TGOODS B, TGOODSDT C                               		 \n");
		sb.append("    WHERE A.GOODS_CODE = B.GOODS_CODE                           		    	 \n");
		sb.append("     AND A.GOODS_CODE = C.GOODS_CODE                                          \n");
		sb.append("     AND A.GOODSDT_CODE = C.GOODSDT_CODE                                      \n");
		sb.append(makeSqlCommon(retrieve));
		sb.append("     ORDER BY A.BALJU_NO DESC                                                 \n");
    	sb.append(" --입고검사결과조회[발주번호]                           					         \n");

		logPrint(sb, retrieve);
	
		return sb.toString();
    }

	private String makeSqlTester(RetrieveModel retrieve) throws StoreException{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("	SELECT U.USER_NAME AS UNIT_RETRIEVE , --조회단위                                             \n");
		sb.append("       A.GOODS_CODE,                                                                       	 \n");
		sb.append("       A.GOODSDT_CODE,                                                                      	 \n");
		sb.append("       B.GOODS_NAME,                                                                        	 \n");
		sb.append("       C.GOODSDT_INFO, --단품상세                                                             \n");
		sb.append("       NVL(SUM(A.QA_QTY), 0) AS QA_QTY, --검사수량                                            \n");
		sb.append("       COUNT(A.BALJU_NO) AS QA_CNT, --검사건수                                                \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB = '16' THEN A.QA_QTY ELSE 0 END), 0) AS PASS_QTY, --합격    \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB != '16' THEN A.QA_QTY ELSE 0 END), 0) AS FAIL_QTY --불합격  \n");
		sb.append("	  FROM TAFTERQA A, TGOODS B, TGOODSDT C, TUSER U                                         	 \n");
		sb.append("	 WHERE A.GOODS_CODE = B.GOODS_CODE                                                           \n");
		sb.append("	   AND A.GOODS_CODE = C.GOODS_CODE                                                           \n");
		sb.append("	   AND A.GOODSDT_CODE = C.GOODSDT_CODE                                                       \n");
		sb.append("	   AND A.INSERT_ID = U.USER_ID                                                               \n");
		sb.append(makeSqlCommon(retrieve));
		sb.append("	 GROUP BY U.USER_NAME, A.GOODS_CODE, A.GOODSDT_CODE, B.GOODS_NAME, C.GOODSDT_INFO            \n");
		sb.append("	 ORDER BY 1                                                                                  \n");
		sb.append("	--입고검사결과조회[검사자]                                             						 \n");
		
		logPrint(sb, retrieve);
		
		return sb.toString();
	}
	
	private String makeSqlGoodsKind(RetrieveModel retrieve) throws StoreException{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("	SELECT D.LGROUP_NAME||'|'||D.MGROUP_NAME||'|'||D.SGROUP_NAME||'|'||D.DGROUP_NAME AS UNIT_RETRIEVE, \n");
		sb.append("       A.GOODS_CODE,                                                                                \n");
		sb.append("       A.GOODSDT_CODE,                                                                              \n");
		sb.append("       B.GOODS_NAME,                                                                                \n");
		sb.append("       C.GOODSDT_INFO, --단품상세                                                                   \n");
		sb.append("       NVL(SUM(A.QA_QTY), 0) AS QA_QTY, --검사수량                                                  \n");
		sb.append("       COUNT(A.BALJU_NO) AS QA_CNT, --검사건수                                                      \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB = '16' THEN A.QA_QTY ELSE 0 END), 0) AS PASS_QTY, --합격          \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB != '16' THEN A.QA_QTY ELSE 0 END), 0) AS FAIL_QTY --불합격        \n");
		sb.append("	  FROM TAFTERQA A, TGOODS B, TGOODSDT C, TGOODSKINDS D                                             \n");
		sb.append("	 WHERE A.GOODS_CODE = B.GOODS_CODE                                                                 \n");
		sb.append("	   AND A.GOODS_CODE = C.GOODS_CODE                                                                 \n");
		sb.append("	   AND A.GOODSDT_CODE = C.GOODSDT_CODE                                                             \n");
		sb.append("	   AND B.LMSD_CODE = D.LMSD_CODE                                                                   \n");
		sb.append(makeSqlCommon(retrieve));
		sb.append("	 GROUP BY D.LGROUP_NAME||'|'||D.MGROUP_NAME||'|'||D.SGROUP_NAME||'|'||D.DGROUP_NAME,               \n");
		sb.append("	          A.GOODS_CODE, A.GOODSDT_CODE, B.GOODS_NAME, C.GOODSDT_INFO                               \n");
		sb.append("	 ORDER BY 1                                                                                        \n");
		sb.append("	--입고검사결과조회[상품분류]                                             						   \n");
	 
 		logPrint(sb, retrieve);
 		
		return sb.toString();
	}
	
	private String makeSqlVendor(RetrieveModel retrieve) throws StoreException{
		
		StringBuffer sb = new StringBuffer();
 		
		sb.append("	SELECT D.ENTP_NAME AS UNIT_RETRIEVE, --조회단위                                              \n");
		sb.append("       A.GOODS_CODE,                                                                          \n");
		sb.append("       A.GOODSDT_CODE,                                                                        \n");
		sb.append("       B.GOODS_NAME,                                                                          \n");
		sb.append("       C.GOODSDT_INFO, --단품상세                                                             \n");
		sb.append("       NVL(SUM(A.QA_QTY), 0) AS QA_QTY, --검사수량                                            \n");
		sb.append("       COUNT(A.BALJU_NO) AS QA_CNT, --검사건수                                                \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB = '16' THEN A.QA_QTY ELSE 0 END), 0) AS PASS_QTY, --합격    \n");
		sb.append("       NVL(SUM(CASE WHEN A.SQC_GB != '16' THEN A.QA_QTY ELSE 0 END), 0) AS FAIL_QTY --불합격  \n");
		sb.append("	  FROM TAFTERQA A, TGOODS B, TGOODSDT C, TENTERPRISE D                                     	 \n");
		sb.append("	 WHERE A.GOODS_CODE = B.GOODS_CODE                                                         	 \n");
		sb.append("	   AND A.GOODS_CODE = C.GOODS_CODE                                                           \n");
		sb.append("	   AND A.GOODSDT_CODE = C.GOODSDT_CODE                                                       \n");
		sb.append("	   AND B.ENTP_CODE = D.ENTP_CODE                                                             \n");
		sb.append(makeSqlCommon(retrieve));
		sb.append("	 GROUP BY D.ENTP_NAME, A.GOODS_CODE, A.GOODSDT_CODE, B.GOODS_NAME, C.GOODSDT_INFO            \n");
		sb.append("	 ORDER BY 1                                                                                  \n");
		sb.append("	--입고검사결과조회[협력사]                                               					 \n");
	
		logPrint(sb, retrieve);                                                                            
 		
		return sb.toString();
	}
	
 	private String makeSqlMd(RetrieveModel retrieve) throws StoreException{
 		
 		StringBuffer sb = new StringBuffer();
 		    
	 	sb.append("	SELECT D.MD_NAME AS UNIT_RETRIEVE, --조회단위                                               \n");
	    sb.append("    A.GOODS_CODE,                                                                            \n");
	    sb.append("    A.GOODSDT_CODE,                                                                          \n");
	    sb.append("    B.GOODS_NAME,                                                                            \n");
	    sb.append("    C.GOODSDT_INFO, --단품상세                                                               \n");
	    sb.append("    NVL(SUM(A.QA_QTY), 0) AS QA_QTY, --검사수량                                              \n");
	    sb.append("    COUNT(A.BALJU_NO) AS QA_CNT, --검사건수                                                  \n");
	    sb.append("    NVL(SUM(CASE WHEN A.SQC_GB = '16' THEN A.QA_QTY ELSE 0 END), 0) AS PASS_QTY, --합격      \n");
	    sb.append("    NVL(SUM(CASE WHEN A.SQC_GB != '16' THEN A.QA_QTY ELSE 0 END), 0) AS FAIL_QTY --불합격    \n");
		sb.append("   FROM TAFTERQA A, TGOODS B, TGOODSDT C, TMD D                                              \n");
		sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE                                                          \n");
		sb.append("    AND A.GOODS_CODE = C.GOODS_CODE                                                          \n");
		sb.append("    AND A.GOODSDT_CODE = C.GOODSDT_CODE                                                      \n");
		sb.append("    AND B.MD_CODE = D.MD_CODE                                                                \n");
		sb.append(makeSqlCommon(retrieve));
		sb.append("  GROUP BY D.MD_NAME, A.GOODS_CODE, A.GOODSDT_CODE, B.GOODS_NAME, C.GOODSDT_INFO             \n");
		sb.append("  ORDER BY 1                                                                                 \n");
		sb.append("	--입고검사결과조회[MD]                                              						\n");
 		
 		logPrint(sb, retrieve);
 		
		return sb.toString();
	}

    private String makeSqlCommon(RetrieveModel retrieve) throws StoreException{
		
    	 StringBuffer sb = new StringBuffer();
    	
	    	 sb.append("  AND A.QA_DATE >= TO_DATE(?, 'YYYY/MM/DD')         \n");
	    	 sb.append("  AND A.QA_DATE <  TO_DATE(?, 'YYYY/MM/DD') + 1     \n");
    	 if(!blank.equals(retrieve.getString("balju_no"))){
    		 sb.append("  AND A.BALJU_NO = ?                                \n");
    	 }
    	 if(!blank.equals(retrieve.getString("tester_id"))){
    		 sb.append("  AND A.INSERT_ID = ?                               \n");
    	 }
    	 if(!blank.equals(retrieve.getString("goods_code"))){
    		 sb.append("  AND A.GOODS_CODE LIKE ? || '%'                    \n");
    	 }
    	 if(!blank.equals(retrieve.getString("md_code"))){
    		 sb.append("  AND B.MD_CODE LIKE ? || '%'                       \n");
    	 }
		return sb.toString();
    }

    private void logPrint(StringBuffer query, RetrieveModel retrieve){
    	
        if (log.isDebugEnabled()) {
            log.debug("\n" + query.toString());
            log.debug("from_date  : " + retrieve.getString("fromDate"));
            log.debug("to_date    : " + retrieve.getString("toDate"));
            log.debug("balju_no : " + retrieve.getString("balju_no"));
            log.debug("tester_id  : " + retrieve.getString("tester_id"));
            log.debug("goods_code  : " + retrieve.getString("goods_code"));
            log.debug("md_code  : " + retrieve.getString("md_code"));
        }
    }

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
