
package com.cware.back.service.common;

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
 * 사전검사이력조회 Service class
 *
 * @version 1.0, 2011/01/18
 * @author  commerceware.co.kr
 */
public class PopBeforeInspectHistSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PopBeforeInspectHistSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSelect(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT A.SEQ_QA_NO 									\n");
        sb.append("        ,TO_CHAR(A.REQ_DATE, 'YYYY/MM/DD') AS REQ_DATE  	\n");
        sb.append("        ,TO_CHAR(A.QA_DATE, 'YYYY/MM/DD') AS QA_DATE  	\n");
        sb.append("        ,A.SQC_GB  										\n");
        sb.append("        ,A.INSERT_ID  									\n");
        sb.append("        ,B.USER_NAME  									\n");
        sb.append("        ,A.QA_OPINION  									\n");
        sb.append("    FROM TBEFOREQAM  A	 								\n");
        sb.append("        ,TUSER  B  										\n");
        sb.append("    WHERE A.INSERT_ID = B.USER_ID  						\n");
        sb.append("      AND A.GOODS_CODE LIKE ? || '%'  					\n");
        sb.append("    ORDER BY A.SEQ_QA_NO DESC 		 					\n");
        


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    private String makeSqlSelectDt(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT TCODE_NAME('B124',A.INSPECT_LCODE) AS INSPECT_NAME   			\n");
        sb.append("        ,B.INSPECT_MNAME  	                                            \n");
        sb.append("        ,A.NEEDS_NOTE  													\n");
        sb.append("        ,ACTION_YN  														\n");
        sb.append("        ,TO_CHAR(A.ACTION_DATE, 'YYYY/MM/DD') AS ACTION_DATE  			\n");
        sb.append("        ,A.CONF_YN  														\n");
        sb.append("        ,TO_CHAR(A.CONF_DATE, 'YYYY/MM/DD') AS CONF_DATE 				\n");
        sb.append("    FROM TBEFOREQADT  A	 												\n");
        sb.append("        ,TINSPECT_GOODS  B  											    \n");
        sb.append("    WHERE A.INSPECT_LCODE = B.INSPECT_LCODE   							\n");
        sb.append("    AND A.INSPECT_MCODE = B.INSPECT_MCODE   								\n");
        sb.append("    AND A.SEQ_QA_NO = ?   												\n");
        sb.append("    ORDER BY A.SEQ_QA_NO DESC 		 									\n");
        


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
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSelect(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSelectDt(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("seq_qa_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTDT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


}

