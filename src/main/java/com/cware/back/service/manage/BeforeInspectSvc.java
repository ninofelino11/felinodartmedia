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
import com.cware.back.entity.table.Tbeforeqadt;
import com.cware.back.entity.table.Tbeforeqam;

/**
 * BeforeInspect Service class
 *
 * @version 1.0, 2011/01/25
 */
public class BeforeInspectSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BeforeInspectSvc() {}

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
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("seq_qa_no"));
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[BeforeInspectSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[BeforeInspectSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

		sb.append(" SELECT SEQ_QA_NO,                                    \n");
		sb.append("        TO_CHAR(REQ_DATE, 'YYYY/MM/DD') AS REQ_DATE,  \n");
		sb.append("        TO_CHAR(QA_DATE, 'YYYY/MM/DD') AS QA_DATE,    \n");
		sb.append("        SQC_GB                                        \n");
		sb.append("   FROM TBEFOREQAM A                                  \n");
		sb.append("  WHERE SEQ_QA_NO LIKE ? || '%'                       \n");
		sb.append("    AND GOODS_CODE LIKE ? || '%'                      \n");
		sb.append("    AND SQC_GB > '00'                                 \n"); //접수이상

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("seq_qa_no:  " + retrieve.getString("seq_qa_no"));
            log.debug("goods_code:  " + retrieve.getString("goods_code"));
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
    public RetrieveModel retrieveMasterInfo(Connection con, RetrieveModel retrieve) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;

    	try {
    		pstmt = con.prepareStatement(makeSqlMasterInfo(retrieve));

    		int index = 1;
    		pstmt.setString(index++, retrieve.getString("seq_qa_no"));
    		rset  = pstmt.executeQuery();
    		retrieve.put("RESULT_MASTER", makeSheet(rset));
    	}catch(SQLException se){
    		log.error("[BeforeInspectSvc.retrieveMasterInfo() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[BeforeInspectSvc.retrieveMasterInfo() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSqlMasterInfo( RetrieveModel retrieve ) throws StoreException{

    	StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.SEQ_QA_NO,                                                                            \n");
		sb.append("        A.GOODS_CODE,                                                                           \n");
		sb.append("        B.GOODS_NAME,                                                                           \n");
		sb.append("        A.REQ_FLAG,                                                                             \n");
		sb.append("        TO_CHAR(A.REQ_DATE, 'YYYY/MM/DD') AS REQ_DATE,                                          \n");
		sb.append("        TO_CHAR(A.ACPT_DATE, 'YYYY/MM/DD') AS ACPT_DATE ,                                       \n");
		sb.append("        A.SQC_GB,                                                                               \n");
		sb.append("        DECODE(A.QA_QTY, 0, 1, A.QA_QTY) AS QA_QTY,                                                                               \n");
		sb.append("        TO_CHAR(A.QA_DATE, 'YYYY/MM/DD') AS QA_DATE,                                            \n");
		sb.append("        A.QA_ID,                                                                                \n");
		sb.append("        C.USER_NAME AS QA_NAME,                                                             \n");
		sb.append("        A.QA_OPINION,                                                                           \n");
		sb.append("        D.LMSD_CODE,                                                                            \n");
		sb.append("        D.LGROUP_NAME||'|'||D.MGROUP_NAME||'|'||D.SGROUP_NAME||'|'||D.DGROUP_NAME AS LMSD_NAME, \n");
		sb.append("        B.SALE_GB,                                                                              \n");
		sb.append("        B.MD_CODE,                                                                              \n");
		sb.append("        E.MD_NAME,                                                                              \n");
		sb.append("        B.ENTP_CODE,                                                                            \n");
		sb.append("        F.ENTP_NAME,                                                                            \n");
		sb.append("        B.BRAND_CODE,                                                                           \n");
		sb.append("        G.BRAND_NAME,                                                                           \n");
		sb.append("        B.ORIGIN_CODE,                                                                          \n");
		sb.append("        TCODE_NAME('B023', B.ORIGIN_CODE) AS ORIGIN_NAME                                        \n");
		sb.append("   FROM TBEFOREQAM A, TGOODS B, TUSER C, TGOODSKINDS D, TMD E, TENTERPRISE F, TBRAND G          \n");
		sb.append("  WHERE A.SEQ_QA_NO = ?                                                                         \n");
		sb.append("    AND A.GOODS_CODE = B.GOODS_CODE                                                             \n");
		sb.append("    AND A.QA_ID = C.USER_ID (+)                                                                 \n");
		sb.append("    AND B.LMSD_CODE = D.LMSD_CODE                                                               \n"); //INDEX
//		sb.append("    AND B.LGROUP = D.LGROUP                                                                     \n");
//		sb.append("    AND B.MGROUP = D.MGROUP                                                                     \n");
//		sb.append("    AND B.SGROUP = D.SGROUP                                                                     \n");
//		sb.append("    AND B.DGROUP = D.DGROUP                                                                     \n");
		sb.append("    AND B.MD_CODE = E.MD_CODE                                                                   \n");
		sb.append("    AND B.ENTP_CODE = F.ENTP_CODE                                                               \n");
		sb.append("    AND B.BRAND_CODE = G.BRAND_CODE    	                                                       \n");


    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug("\n" + sb.toString());
    		log.debug("seq_qa_no:  " + retrieve.getString("seq_qa_no"));
    	}
    	return sb.toString();
    }

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
    		pstmt = con.prepareStatement(makeSqlDetail(retrieve));

    		int index = 1;
    		pstmt.setString(index++, retrieve.getString("seq_qa_no"));
    		rset  = pstmt.executeQuery();

    		retrieve.put("RESULT_DETAIL", makeSheet(rset));

    	}catch(SQLException se){
    		log.error("[BeforeInspectSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[BeforeInspectSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

    	StringBuffer sb = new StringBuffer();
		sb.append("   SELECT A.SEQ_QA_NO,                                                    \n");
		sb.append("          B.INSPECT_LCODE,                                                \n");
		sb.append("          C.CODE_NAME AS INSPECT_LNAME,                                   \n");
		sb.append("          B.INSPECT_MCODE,                                                \n");
		sb.append("          D.INSPECT_MNAME,                                                \n");
		sb.append("          B.NEEDS_NOTE,                                                   \n");
		sb.append("          B.NEEDS_YN,                                                     \n");
		sb.append("          B.ACTION_YN,                                                    \n");
		sb.append("          B.ACTION_OPINION,                                               \n");
		sb.append("          TO_CHAR(B.ACTION_DATE, 'YYYY/MM/DD') AS ACTION_DATE,            \n");
		sb.append("          B.CONF_YN,                                                      \n");
		sb.append("          TO_CHAR(B.CONF_DATE, 'YYYY/MM/DD') AS CONF_DATE                 \n");
		sb.append("     FROM TBEFOREQAM A, TBEFOREQADT B, TCODE C, TINSPECT_GOODS D          \n");
		sb.append("    WHERE A.SEQ_QA_NO = ?                                                 \n");
		sb.append("      AND A.SEQ_QA_NO = B.SEQ_QA_NO                                       \n");
		sb.append("      AND B.INSPECT_LCODE = C.CODE_MGROUP                                 \n");
		sb.append("      AND C.CODE_LGROUP = 'B124'                                          \n");
		sb.append("      AND B.INSPECT_LCODE = D.INSPECT_LCODE                               \n");
		sb.append("      AND B.INSPECT_MCODE = D.INSPECT_MCODE                               \n");
//		sb.append("  SELECT A.SEQ_QA_NO,                                                                         \n");
//		sb.append("         A.INSPECT_LCODE,                                                                     \n");
//		sb.append("         A.INSPECT_LNAME,                                                                     \n");
//		sb.append("         A.INSPECT_MCODE,                                                                     \n");
//		sb.append("         A.INSPECT_MNAME,                                                                     \n");
//		sb.append("         A.IGG_YN,                                                                            \n");
//		sb.append("         A.IG_YN,                                                                             \n");
//		sb.append("         B.NEEDS_NOTE,                                                                        \n");
//		sb.append("         NVL(B.NEEDS_YN, '0') AS NEEDS_YN,                                                    \n");
//		sb.append("         NVL(B.ACTION_YN, '0') AS ACTION_YN,                                                  \n");
//		sb.append("         B.ACTION_OPINION,                                                                    \n");
//		sb.append("         TO_CHAR(B.ACTION_DATE, 'YYYY/MM/DD') AS ACTION_DATE,                                 \n");
//		sb.append("         NVL(B.CONF_YN, '0') AS CONF_YN,                                                      \n");
//		sb.append("         TO_CHAR(B.CONF_DATE, 'YYYY/MM/DD') AS CONF_DATE,                                     \n");
//		sb.append("         DECODE(B.SEQ_QA_NO, NULL, '0', '1') AS EXIST                                         \n");
//		sb.append("    FROM (                                                                                    \n");
//		sb.append("      SELECT A.SEQ_QA_NO,                                                                     \n");
//		sb.append("             C.INSPECT_LCODE,                                                                 \n");
//		sb.append("             E.CODE_NAME AS INSPECT_LNAME,                                                    \n");
//		sb.append("             C.INSPECT_MCODE,                                                                 \n");
//		sb.append("             D.INSPECT_MNAME,                                                                 \n");
//		sb.append("             C.USE_YN AS IGG_YN,                                                              \n");
//		sb.append("             D.USE_YN AS IG_YN                                                                \n");
//		sb.append("        FROM TBEFOREQAM A, TGOODS B, TINSPECT_GOODS_GRP C, TINSPECT_GOODS D, TCODE E          \n");
//		sb.append("       WHERE A.SEQ_QA_NO = ?                                                                  \n");
//		sb.append("         AND A.GOODS_CODE = B.GOODS_CODE                                                      \n");
//		sb.append("         AND B.LMSD_CODE = C.LMSD_CODE                                                        \n");
//		sb.append("         AND C.INSPECT_LCODE = D.INSPECT_LCODE                                                \n");
//		sb.append("         AND C.INSPECT_MCODE = D.INSPECT_MCODE                                                \n");
//		sb.append("         AND C.INSPECT_LCODE = E.CODE_MGROUP                                                  \n");
//		sb.append("         AND E.CODE_LGROUP = 'B124'                                                           \n");
//		sb.append("  ) A, TBEFOREQADT B                                                                          \n");
//		sb.append("   WHERE A.SEQ_QA_NO = B.SEQ_QA_NO (+)                                                        \n");
//		sb.append("     AND A.INSPECT_LCODE = B.INSPECT_LCODE (+)                                                \n");
//		sb.append("     AND A.INSPECT_MCODE = B.INSPECT_MCODE (+)                                                \n");
//		sb.append("     AND CASE WHEN B.SEQ_QA_NO IS NULL                                                        \n");
//		sb.append("              THEN (CASE WHEN IGG_YN = '1' AND IG_YN = '1' THEN '1' ELSE '0' END)             \n");
//		sb.append("              ELSE '1' END = '1' /*기존자료외에는 검사분류가 사용중인것만 조회*/ 						 \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug("\n" + sb.toString());
    		log.debug("seq_qa_no:  " + retrieve.getString("seq_qa_no"));
    	}
    	return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Edit retrieval result
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

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TBEFOREQAM
    * </PRE>
    * @param   Connection
    * @param   Tbeforeqam
    * @return  int
    */
	public int update(Connection con, Tbeforeqam tbeforeqam) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tbeforeqam));
            int index = 1;
            pstmt.setString(index++, tbeforeqam.getSqc_gb()    	);
            pstmt.setInt   (index++, tbeforeqam.getQa_qty()	   	);
            pstmt.setString(index++, tbeforeqam.getQa_date()	);
            pstmt.setString(index++, tbeforeqam.getQa_id()      );
            pstmt.setString(index++, tbeforeqam.getQa_opinion()	);
            pstmt.setString(index++, tbeforeqam.getModify_id()	);
            pstmt.setString(index++, tbeforeqam.getSeq_qa_no()	);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeforeqam.getSqc_gb    	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getQa_qty	   	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getQa_date	        	()	); logString.append( "/" );
            logString.append( tbeforeqam.getQa_id	        	()	); logString.append( "/" );
            logString.append( tbeforeqam.getQa_opinion	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getModify_id	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getSeq_qa_no	        ()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[BeforeInspectSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[BeforeInspectSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}



    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TBEFOREQAM Update
     * </PRE>
     * @param   Tbeforeqam
     * @return  String
     */
	private final String updateSqlTbeforeqam = " UPDATE TBEFOREQAM "
		                                     + "    SET SQC_GB = ?, QA_QTY = ?, QA_DATE = TO_DATE(?, 'YYYY/MM/DD'), QA_ID = ?, QA_OPINION = ?, MODIFY_ID = ?, MODIFY_DATE = SYSDATE "
		                                     + "  WHERE SEQ_QA_NO = ? ";

    private String makeSqlUpdate( Tbeforeqam tbeforeqam ) throws StoreException{
    	//= log SQL -------------------------------------------------
    	if (logSave.isDebugEnabled()) {
    		logSave.debug(updateSqlTbeforeqam);
    	}
    	return updateSqlTbeforeqam;
    }

	//= Update -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : insert TBEFOREQADT
	 * </PRE>
	 * @param   Connection
	 * @param   Tbeforeqadt
	 * @return  int
	 */
	public int insertDetail(Connection con, Tbeforeqadt tbeforeqadt) throws StoreException {
		PreparedStatement pstmt       = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlInsertDetail(tbeforeqadt));
			int index = 1;
			pstmt.setString(index++, tbeforeqadt.getSeq_qa_no     ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_lcode ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_mcode ()	);
			pstmt.setString(index++, tbeforeqadt.getNeeds_note    ()	);
			pstmt.setString(index++, tbeforeqadt.getNeeds_yn      ()	);
			pstmt.setString(index++, tbeforeqadt.getAction_yn     ()	);
			pstmt.setString(index++, tbeforeqadt.getAction_opinion()	);
			pstmt.setString(index++, tbeforeqadt.getAction_date   ()	);
			pstmt.setString(index++, tbeforeqadt.getConf_yn       ()	);
			pstmt.setString(index++, tbeforeqadt.getConf_date     ()	);
			pstmt.setString(index++, tbeforeqadt.getInsert_id     ()	);
			pstmt.setString(index++, tbeforeqadt.getModify_id     ()	);

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( 	tbeforeqadt.getSeq_qa_no     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getInspect_lcode ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getInspect_mcode ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getNeeds_note    ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getNeeds_yn      ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_yn     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_opinion()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_date   ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getConf_yn       ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getConf_date     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getInsert_id     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getModify_id     ()		); logString.append( "/" );
			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		}catch(SQLException se){
			logSave.error("[BeforeInspectSvc.insertDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			logSave.error("[BeforeInspectSvc.insertDetail() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, null);
		}
		return executedRtn;
	}

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TBEFOREQADT Insert
     * </PRE>
     * @param   Tbeforeqadt
     * @return  String
     */
	private int insertSqlLogTbeforeqadt =  0;
	private final String insertSqlTbeforeqadt = " INSERT INTO TBEFOREQADT ( "
											  + " SEQ_QA_NO, INSPECT_LCODE, INSPECT_MCODE, NEEDS_NOTE, NEEDS_YN, ACTION_YN, ACTION_OPINION, ACTION_DATE, CONF_YN, CONF_DATE, "
											  + " INSERT_ID, INSERT_DATE, MODIFY_ID, MODIFY_DATE "
											  + " ) VALUES (  "
											  + " ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'), ?, TO_DATE(?, 'YYYY/MM/DD'), ?, SYSDATE, ?, SYSDATE "
											  + " ) ";

	private String makeSqlInsertDetail( Tbeforeqadt tbeforeqadt ) throws StoreException{
		//= log SQL -------------------------------------------------
		if (logSave.isDebugEnabled()) {
			if (insertSqlLogTbeforeqadt == 0) logSave.debug(insertSqlTbeforeqadt);
			insertSqlLogTbeforeqadt = 1;
		}
		return insertSqlTbeforeqadt;
	}

	//= Update -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Update TBEFOREQADT
	 * </PRE>
	 * @param   Connection
	 * @param   Tbeforeqadt
	 * @return  int
	 */
	public int updateDetail(Connection con, Tbeforeqadt tbeforeqadt) throws StoreException {
		PreparedStatement pstmt       = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlUpdateDetail(tbeforeqadt));
			int index = 1;
			pstmt.setString(index++, tbeforeqadt.getNeeds_note    ()	);
			pstmt.setString(index++, tbeforeqadt.getNeeds_yn      ()	);
			pstmt.setString(index++, tbeforeqadt.getAction_yn     ()	);
			pstmt.setString(index++, tbeforeqadt.getAction_opinion()	);
			pstmt.setString(index++, tbeforeqadt.getAction_date   ()	);
			pstmt.setString(index++, tbeforeqadt.getConf_yn       ()	);
			pstmt.setString(index++, tbeforeqadt.getConf_date     ()	);
			pstmt.setString(index++, tbeforeqadt.getModify_id     ()	);
			pstmt.setString(index++, tbeforeqadt.getSeq_qa_no     ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_lcode ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_mcode ()	);

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( 	tbeforeqadt.getNeeds_note    ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getNeeds_yn      ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_yn     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_opinion()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getAction_date   ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getConf_yn       ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getConf_date     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getModify_id     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getSeq_qa_no     ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getInspect_lcode ()		); logString.append( "/" );
			logString.append( 	tbeforeqadt.getInspect_mcode ()		); logString.append( "/" );
			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		}catch(SQLException se){
			logSave.error("[BeforeInspectSvc.updateDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			logSave.error("[BeforeInspectSvc.updateDetail() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, null);
		}
		return executedRtn;
	}

	//= Edit SQL -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Make SQL ; TBEFOREQADT Update
	 * </PRE>
	 * @param   Tbeforeqadt
	 * @return  String
	 */
	private int updateSqlLogTbeforeqadt =  0;
	private final String updateSqlTbeforeqadt = " UPDATE TBEFOREQADT	" +
												" SET NEEDS_NOTE = ?, NEEDS_YN = ?, ACTION_YN = ?, ACTION_OPINION = ?, ACTION_DATE = TO_DATE(?, 'YYYY/MM/DD'), CONF_YN = ?, CONF_DATE = TO_DATE(?, 'YYYY/MM/DD'), " +
												" MODIFY_ID = ?, MODIFY_DATE = SYSDATE " +
												" WHERE SEQ_QA_NO = ? AND INSPECT_LCODE = ? AND INSPECT_MCODE = ? ";

	private String makeSqlUpdateDetail( Tbeforeqadt tbeforeqadt ) throws StoreException{
		//= log SQL -------------------------------------------------
		if (logSave.isDebugEnabled()) {
			if (updateSqlLogTbeforeqadt == 0) logSave.debug(updateSqlTbeforeqadt);
			updateSqlLogTbeforeqadt = 1;
		}
		return updateSqlTbeforeqadt;
	}

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   Tbeforeqadt
     * @return  int
     */
    public int checkTbeforeqadt(Connection con, Tbeforeqadt tbeforeqadt) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;
    	int cnt = 0;
    	try {
    		pstmt = con.prepareStatement("SELECT COUNT(*) FROM TBEFOREQADT WHERE SEQ_QA_NO = ? AND INSPECT_LCODE = ? AND INSPECT_MCODE = ? ");

    		int index = 1;
			pstmt.setString(index++, tbeforeqadt.getSeq_qa_no     ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_lcode ()	);
			pstmt.setString(index++, tbeforeqadt.getInspect_mcode ()	);

    		rset  = pstmt.executeQuery();

    		if (rset.next()) {
    			cnt = rset.getInt(1);
    		}

    	}catch(SQLException se){
    		log.error("[QaInspectSvc.checkTbeforeqadt() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[QaInspectSvc.checkTbeforeqadt() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return cnt;
    }

	//= Edit SQL -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Make SQL ; TBEFOREQADT Update
	 * </PRE>
	 * @param   Tbeforeqadt
	 * @return  String
	 */
	private int updateSqlLogTGoodsSqcGb =  0;
	private final String updateSqlTGoodsSqcGb = " UPDATE TGOODS	" +
												" SET SQC_GB = ?, MODIFY_ID = ?, MODIFY_DATE = SYSDATE " +
												" WHERE GOODS_CODE = ? ";

	private String makeSqlUpdateGoodsSqcGb( Tbeforeqam tbeforeqam ) throws StoreException{
		//= log SQL -------------------------------------------------
		if (logSave.isDebugEnabled()) {
			if (updateSqlLogTGoodsSqcGb == 0) logSave.debug(updateSqlTGoodsSqcGb);
			updateSqlLogTGoodsSqcGb = 1;
		}
		return updateSqlTGoodsSqcGb;
	}

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TGOODS
    * </PRE>
    * @param   Connection
    * @param   Tbeforeqam
    * @return  int
    */
	public int updateGoodsSqcGb(Connection con, Tbeforeqam tbeforeqam) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateGoodsSqcGb(tbeforeqam));
            int index = 1;
            pstmt.setString(index++, tbeforeqam.getSqc_gb()    	);
            pstmt.setString(index++, tbeforeqam.getModify_id()	);
            pstmt.setString(index++, tbeforeqam.getGoods_code()	);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeforeqam.getSqc_gb    	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getModify_id	        ()	); logString.append( "/" );
            logString.append( tbeforeqam.getGoods_code	        ()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[BeforeInspectSvc.updateGoodsSqcGb() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[BeforeInspectSvc.updateGoodsSqcGb() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSqlBeforeSqcGb( Tbeforeqam tbeforeqam ) throws StoreException{

        StringBuffer sb = new StringBuffer();

		sb.append(" SELECT SQC_GB, QA_QTY                                \n");
		sb.append("   FROM TBEFOREQAM A                                  \n");
		sb.append("  WHERE SEQ_QA_NO = ?                                 \n"); //접수이상

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("seq_qa_no:  " + tbeforeqam.getSqc_gb());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
    public String retrieveBeforeSqcGb(Connection con, Tbeforeqam tbeforeqam) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;

    	try {
    		pstmt = con.prepareStatement(makeSqlBeforeSqcGb(tbeforeqam));
    		pstmt.setString(1, tbeforeqam.getSeq_qa_no());
    		rset  = pstmt.executeQuery();
    		if (rset.next()) {
    			return rset.getString("SQC_GB");
    		}
    	}catch(SQLException se){
    		log.error("[BeforeInspectSvc.retrieveBeforeSqcGb() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[BeforeInspectSvc.retrieveBeforeSqcGb() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return null;
    }
}
