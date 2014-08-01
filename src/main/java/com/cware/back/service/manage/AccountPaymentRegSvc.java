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
import com.cware.back.entity.table.Tacctpaydt;
import com.cware.back.entity.table.Tacctpaym;

/**
 * AccountDailyCloseList Service class
 *
 * @version 1.0, 2010/12/23
 */
public class AccountPaymentRegSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public AccountPaymentRegSvc() {}

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
            pstmt.setString(index++, retrieve.getString("pay_yymm"));
           	pstmt.setString(index++, retrieve.getString("pay_times"));
            pstmt.setString(index++, retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[AccountPaymentRegSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AccountPaymentRegSvc.retrieve() Exception : ERR-"+e.getMessage());
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

		sb.append("  SELECT A.ENTP_CODE,                                                                                             \n");
		sb.append("         B.ENTP_NAME,                                                                                             \n");
		sb.append("         TO_CHAR(TO_DATE(PAY_YYMM, 'YYYYMM'), 'YYYY/MM') AS PAY_YYMM,                                             \n");
		sb.append("         PAY_TIMES,                                                                                               \n");
		sb.append("         LAST_HOLD_AMT,                                                                                           \n");
		sb.append("         CURR_AMT,                                                                                                \n");
		sb.append("         CURR_HOLD_AMT,                                                                                           \n");
		sb.append("         CURR_DEDUCT_AMT,                                                                                         \n");
		sb.append("         CURR_PAY_AMT,                                                                                            \n");
		sb.append("         CONF_YN,                                                                                                 \n");
		sb.append("         TO_CHAR(PAY_DATE, 'YYYY/MM/DD') AS PAY_DATE                                                              \n");
		sb.append("    FROM TACCTPAYM A, TENTERPRISE B                                                                               \n");
		sb.append("   WHERE A.ENTP_CODE = B.ENTP_CODE                                                                                \n");
		sb.append("     AND PAY_YYMM = ?                 																			 \n");
		sb.append("     AND PAY_TIMES = ?   	                                                                                     \n");
		sb.append("     AND A.ENTP_CODE = ?                                                                                          \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("pay_yymm:  " + retrieve.getString("pay_yymm"));
            log.debug("pay_times: " + retrieve.getString("pay_times"));
            log.debug("entp_code: " + retrieve.getString("entp_code"));
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
    		pstmt.setString(index++, retrieve.getString("pay_yymm"));
    		pstmt.setString(index++, retrieve.getString("pay_times"));
    		pstmt.setString(index++, retrieve.getString("entp_code"));
    		pstmt.setString(index++, retrieve.getString("ho_de_flag"));

    		rset  = pstmt.executeQuery();

    		retrieve.put("RESULT_DETAIL" + retrieve.getString("ho_de_flag"), makeSheet(rset));

    	}catch(SQLException se){
    		log.error("[AccountPaymentRegSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[AccountPaymentRegSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
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
		sb.append(" SELECT PD.PAY_YYMM,                                             \n");
		sb.append("        PD.PAY_TIMES,                                            \n");
		sb.append("        PD.ENTP_CODE,                                            \n");
		sb.append("        PD.HO_DE_CODE,                                           \n");
		sb.append("        CC.CODE_NAME AS HO_DE_NAME,                              \n");
		sb.append("        PD.HO_DE_FLAG,                                           \n");
		sb.append("        PD.HO_DE_AMT,                                            \n");
		sb.append("        PD.REMARK,  												\n");
		sb.append("        PM.CONF_YN, 												\n");
		sb.append("        CC.CODE_GROUP AS AUTO_FLAG /* 생성여부 : 1-자동 2-수기*/     \n");
		sb.append("   FROM TACCTPAYDT PD, TCODE CC, TACCTPAYM PM  					\n");
		sb.append("  WHERE PM.PAY_YYMM = PD.PAY_YYMM  								\n");
		sb.append("    AND PM.PAY_TIMES = PD.PAY_TIMES  							\n");
		sb.append("    AND PM.ENTP_CODE = PD.ENTP_CODE  							\n");
		sb.append("    AND PD.HO_DE_CODE  = CC.CODE_MGROUP                          \n");
		sb.append("    AND CC.CODE_LGROUP = 'A002'                                  \n");
		sb.append("    AND PD.PAY_YYMM    = ?                                       \n");
		sb.append("    AND PD.PAY_TIMES   = ?                                       \n");
		sb.append("    AND PD.ENTP_CODE   = ?                                       \n");
		sb.append("    AND PD.HO_DE_FLAG  = ?                        				\n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug("\n" + sb.toString());
    		log.debug("pay_yymm:  " + retrieve.getString("pay_yymm"));
    		log.debug("pay_times: " + retrieve.getString("pay_times"));
    		log.debug("entp_code: " + retrieve.getString("entp_code"));
    		log.debug("ho_de_flag: " + retrieve.getString("ho_de_flag"));
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
    * Desc : Insert Tacctpaydt
    * </PRE>
    * @param   Connection
    * @param   Tacctpaydt
    * @return  int
    */
	public int insertAcctPayDt(Connection con, Tacctpaydt tacctpaydt) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertAcctPayDt(tacctpaydt));
            int index = 1;
            pstmt.setString(index++, tacctpaydt.getPay_yymm().replaceAll("/", "")		);
            pstmt.setString(index++, tacctpaydt.getPay_times()		);
            pstmt.setString(index++, tacctpaydt.getEntp_code()		);
            pstmt.setString(index++, tacctpaydt.getHo_de_code()		);
            pstmt.setString(index++, tacctpaydt.getHo_de_flag()		);
            pstmt.setDouble(index++, tacctpaydt.getHo_de_amt()		);
            pstmt.setString(index++, tacctpaydt.getRemark()			);
            pstmt.setString(index++, tacctpaydt.getInsert_id()		);
            pstmt.setString(index++, tacctpaydt.getModify_id()		);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tacctpaydt.getPay_yymm().replaceAll("/", "")		); logString.append( "/" );
            logString.append( tacctpaydt.getPay_times()		); logString.append( "/" );
            logString.append( tacctpaydt.getEntp_code()		); logString.append( "/" );
            logString.append( tacctpaydt.getHo_de_code()	); logString.append( "/" );
            logString.append( tacctpaydt.getHo_de_flag()	); logString.append( "/" );
            logString.append( tacctpaydt.getHo_de_amt()		); logString.append( "/" );
            logString.append( tacctpaydt.getRemark()		); logString.append( "/" );
            logString.append( tacctpaydt.getInsert_id()		); logString.append( "/" );
            logString.append( tacctpaydt.getModify_id()		); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[AccountPaymentRegSvc.insertAcctPayDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[AccountPaymentRegSvc.insertAcctPayDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tacctpaydt
    * </PRE>
    * @param   Connection
    * @param   Tacctpaydt
    * @return  int
    */
	public int updateAcctPayDt(Connection con, Tacctpaydt tacctpaydt) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateAcctPayDt(tacctpaydt));
            int index = 1;
            pstmt.setString(index++, tacctpaydt.getHo_de_code()		);
            pstmt.setDouble(index++, tacctpaydt.getHo_de_amt()		);
            pstmt.setString(index++, tacctpaydt.getRemark()			);
            pstmt.setString(index++, tacctpaydt.getModify_id()		);
            pstmt.setString(index++, tacctpaydt.getPay_yymm().replaceAll("/", "")		);
            pstmt.setString(index++, tacctpaydt.getPay_times()		);
            pstmt.setString(index++, tacctpaydt.getEntp_code()		);
            pstmt.setString(index++, tacctpaydt.getHo_de_flag()		);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tacctpaydt.getHo_de_code()	); logString.append( "/" );
            logString.append( tacctpaydt.getHo_de_amt()		); logString.append( "/" );
            logString.append( tacctpaydt.getRemark()		); logString.append( "/" );
            logString.append( tacctpaydt.getModify_id()		); logString.append( "/" );
            logString.append( tacctpaydt.getPay_yymm().replaceAll("/", "")		); logString.append( "/" );
            logString.append( tacctpaydt.getPay_times()		); logString.append( "/" );
            logString.append( tacctpaydt.getEntp_code()		); logString.append( "/" );
            logString.append( tacctpaydt.getHo_de_flag()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[AccountPaymentRegSvc.updateAcctPayDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[AccountPaymentRegSvc.updateAcctPayDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tacctpaym
    * </PRE>
    * @param   Connection
    * @param   Tacctpaym
    * @return  int
    */
	public int update(Connection con, Tacctpaym tacctpaym) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tacctpaym));
            int index = 1;
            pstmt.setString(index++, tacctpaym.getConf_yn()			);
            pstmt.setString(index++, tacctpaym.getPay_date()		);
            pstmt.setString(index++, tacctpaym.getModify_id()		);
            pstmt.setString(index++, tacctpaym.getPay_yymm().replaceAll("/", "")		);
            pstmt.setString(index++, tacctpaym.getPay_times()		);
            pstmt.setString(index++, tacctpaym.getEntp_code()		);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tacctpaym.getConf_yn()	); logString.append( "/" );
            logString.append( tacctpaym.getPay_date()	); logString.append( "/" );
            logString.append( tacctpaym.getModify_id()	); logString.append( "/" );
            logString.append( tacctpaym.getPay_yymm().replaceAll("/", "")	); logString.append( "/" );
            logString.append( tacctpaym.getPay_times()	); logString.append( "/" );
            logString.append( tacctpaym.getEntp_code()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[AccountPaymentRegSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[AccountPaymentRegSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TACCTPAYDT Insert
    * </PRE>
    * @param   Tacctpaydt
    * @return  String
    */
    private String makeSqlInsertAcctPayDt( Tacctpaydt tacctpaydt ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TACCTPAYDT (                                                                                                            \n");
        sb.append("    PAY_YYMM, PAY_TIMES, ENTP_CODE, HO_DE_CODE, HO_DE_FLAG, HO_DE_AMT, REMARK, INSERT_ID, INSERT_DATE, MODIFY_ID, MODIFY_DATE   \n");
        sb.append(" ) VALUES (                                                                                                                     \n");
        sb.append("     ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE                                                                                \n");
        sb.append(" )                                                                                                                              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }
    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TACCTPAYDT Update
     * </PRE>
     * @param   Tacctpaydt
     * @return  String
     */
    private String makeSqlUpdateAcctPayDt( Tacctpaydt tacctpaydt ) throws StoreException{

    	StringBuffer sb = new StringBuffer();
    	sb.append(" UPDATE TACCTPAYDT                      \n");
    	sb.append("    SET HO_DE_CODE = ?                  \n");
    	sb.append("      , HO_DE_AMT = ?                   \n");
    	sb.append("      , REMARK = ?                      \n");
    	sb.append("      , MODIFY_ID = ?                   \n");
    	sb.append("      , MODIFY_DATE = SYSDATE           \n");
    	sb.append("  WHERE PAY_YYMM = ?                    \n");
    	sb.append("    AND PAY_TIMES = ?                   \n");
    	sb.append("    AND ENTP_CODE = ?                   \n");
    	sb.append("    AND HO_DE_FLAG = ?                  \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TACCTPAYM Update
     * </PRE>
     * @param   Tacctpaym
     * @return  String
     */
    private String makeSqlUpdate( Tacctpaym tacctpaym ) throws StoreException{

    	StringBuffer sb = new StringBuffer();
    	sb.append("   UPDATE TACCTPAYM A                                                                 \n");
    	sb.append("      SET CONF_YN = ?                                                                 \n");
    	sb.append("        , PAY_DATE = TO_DATE(?, 'YYYY/MM/DD')                                         \n");
    	sb.append("        , CURR_HOLD_AMT = (SELECT NVL(SUM(HO_DE_AMT), 0)                              \n");
    	sb.append("                             FROM TACCTPAYDT                                          \n");
    	sb.append("                            WHERE PAY_YYMM = A.PAY_YYMM                               \n");
    	sb.append("                              AND PAY_TIMES = A.PAY_TIMES                             \n");
    	sb.append("                              AND ENTP_CODE = A.ENTP_CODE                             \n");
    	sb.append("                              AND HO_DE_FLAG = '1' /*보류*/)                           \n");
    	sb.append("        , CURR_DEDUCT_AMT = (SELECT NVL(SUM(HO_DE_AMT), 0)                            \n");
    	sb.append("                             FROM TACCTPAYDT                                          \n");
    	sb.append("                            WHERE PAY_YYMM = A.PAY_YYMM                               \n");
    	sb.append("                              AND PAY_TIMES = A.PAY_TIMES                             \n");
    	sb.append("                              AND ENTP_CODE = A.ENTP_CODE                             \n");
    	sb.append("                              AND HO_DE_FLAG = '2' /*공제*/)                           \n");
    	sb.append("        , CURR_PAY_AMT = LAST_HOLD_AMT + CURR_AMT - (SELECT NVL(SUM(HO_DE_AMT), 0)    \n");
    	sb.append("                             FROM TACCTPAYDT                                          \n");
    	sb.append("                            WHERE PAY_YYMM = A.PAY_YYMM                               \n");
    	sb.append("                              AND PAY_TIMES = A.PAY_TIMES                             \n");
    	sb.append("                              AND ENTP_CODE = A.ENTP_CODE                             \n");
    	sb.append("                              AND HO_DE_FLAG = '1' /*보류*/)                           \n");
    	sb.append("                              - (SELECT NVL(SUM(HO_DE_AMT), 0)                        \n");
    	sb.append("                             FROM TACCTPAYDT                                          \n");
    	sb.append("                            WHERE PAY_YYMM = A.PAY_YYMM                               \n");
    	sb.append("                              AND PAY_TIMES = A.PAY_TIMES                             \n");
    	sb.append("                              AND ENTP_CODE = A.ENTP_CODE                             \n");
    	sb.append("                              AND HO_DE_FLAG = '2' /*공제*/)                           \n");
    	sb.append("        , MODIFY_ID = ?                                                               \n");
    	sb.append("        , MODIFY_DATE = SYSDATE                                                       \n");
    	sb.append("    WHERE PAY_YYMM = ?                                                                \n");
    	sb.append("      AND PAY_TIMES = ?                                                               \n");
    	sb.append("      AND ENTP_CODE = ?                                                               \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }

}
