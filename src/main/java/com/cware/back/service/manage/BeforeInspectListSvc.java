
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
import com.cware.back.entity.table.Tbeforeqam;

/**
 * Register TBEFOREQAM Service class
 *
 * @version 1.0, 2011/01/14
 */
public class BeforeInspectListSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BeforeInspectListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT '0' AS CHK     											\n");
        sb.append("        ,A.SEQ_QA_NO     										\n");
        sb.append("        ,A.GOODS_CODE     										\n");
        sb.append("        ,B.GOODS_NAME    										\n");
        sb.append("        ,A.REQ_FLAG     											\n");
        sb.append("        ,A.SQC_GB     											\n");
        sb.append("        ,TO_CHAR(A.HOPE_DATE, 'YYYY/MM/DD') AS HOPE_DATE 		\n");
        sb.append("        ,TO_CHAR(A.ACPT_DATE, 'YYYY/MM/DD') AS ACPT_DATE 		\n");
        sb.append("        ,TO_CHAR(A.QA_DATE, 'YYYY/MM/DD') AS QA_DATE 			\n");
        sb.append("        ,A.INSERT_ID   											\n");
        sb.append("        ,A.REQ_NOTE   											\n");
        sb.append("        ,A.QA_OPINION    										\n");
        sb.append("   FROM TBEFOREQAM A 											\n");
        sb.append("       ,TGOODS B 												\n");
        sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE 								\n");
        sb.append("    AND A.SQC_GB  =  ? 			 								\n");
        sb.append("    AND A.SEQ_QA_NO  LIKE  ? || '%' 								\n");
        //QA결과가 신청일때
        if (retrieve.getString("sqc_gb").equals("00")) {
        sb.append("    AND A.REQ_DATE   >= TO_DATE(?,'YYYY/MM/DD')        			\n");
        sb.append("    AND A.REQ_DATE   <  TO_DATE(?,'YYYY/MM/DD') + 1    			\n");
        //QA결과가 접수일때
        } else if (retrieve.getString("sqc_gb").equals("05")) {
    	sb.append("    AND A.ACPT_DATE   >= TO_DATE(?,'YYYY/MM/DD')        			\n");
        sb.append("    AND A.ACPT_DATE   <  TO_DATE(?,'YYYY/MM/DD') + 1    			\n");
        } else {
    	sb.append("    AND A.QA_DATE   >= TO_DATE(?,'YYYY/MM/DD')        			\n");
        sb.append("    AND A.QA_DATE   <  TO_DATE(?,'YYYY/MM/DD') + 1    			\n");
        }
        sb.append("    AND A.GOODS_CODE  LIKE  ? || '%' 							\n");
        sb.append("    AND B.MD_CODE     LIKE  ? || '%' 						    \n");
        sb.append("    ORDER BY A.SEQ_QA_NO DESC 									\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("sqc_gb    : "  + retrieve.getString("sqc_gb"));
            log.debug("seq_qa_no : "  + retrieve.getString("seq_qa_no"));
            log.debug("from_date : "  + retrieve.getString("from_date"));
            log.debug("to_date   : "  + retrieve.getString("to_date"));
            log.debug("goods_code: "  + retrieve.getString("goods_code"));
            log.debug("md_code   : "  + retrieve.getString("md_code"));
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update TBEFOREQAM )
    * </PRE>
    * @param   TBEFOREQAM
    * @return  String
    */
    private String makeSqlUpdate(Tbeforeqam qa) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TBEFOREQAM SET 			\n ");
        sb.append("         SQC_GB 	= '05', ACPT_DATE = TRUNC(SYSDATE), MODIFY_ID = ?, MODIFY_DATE = SYSDATE		 	\n ");
        sb.append("   WHERE SEQ_QA_NO  = ?  		\n ");
        sb.append("     AND SQC_GB     = '00'  		\n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }



    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result : Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve SQL ; Master
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("sqc_gb"));
            pstmt.setString(index++,retrieve.getString("seq_qa_no"));
            pstmt.setString(index++,retrieve.getString("from_date"));
            pstmt.setString(index++,retrieve.getString("to_date"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("md_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[BeforeInspectListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[BeforeInspectListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TBEFOREQAM
    * </PRE>
    * @param   Connection
    * @param   TMD
    * @return  int
    */
	public int update(Connection con, Tbeforeqam qa) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(qa));
            int index = 1;
            pstmt.setString(index++,qa.getModify_id());
            pstmt.setString(index++,qa.getSeq_qa_no());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( qa.getSeq_qa_no()        ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[BeforeInspectListSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[BeforeInspectListSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}
