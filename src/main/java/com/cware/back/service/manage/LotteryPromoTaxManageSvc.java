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
import com.cware.back.entity.table.Tlotterypromoprize;

public class LotteryPromoTaxManageSvc {
//	private static Log log = LogFactory.getLog(Construct.LOG_BASE);
//	private static Log logSave = LogFactory.getLog(Construct.LOG_SAVE);

	public LotteryPromoTaxManageSvc() {
	}

	//= Edit SQL -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Make SQL
	 * </PRE>
	 *
	 * @param RetrieveModel
	 * @return String
	 */
	public String makeSql(RetrieveModel retrieve) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("    SELECT LP.LOTTERY_PROMO_NO, LPP.SEQ,                                                                    \n ");
		sb.append(" 	      LP.LOTTERY_PROMO_NAME,                                                                           \n ");
		sb.append(" 	      CM.CUST_NO,                                                                                      \n ");
		sb.append(" 	      CM.CUST_NAME,                                                                                    \n ");
		sb.append(" 	      CWARE_ENC_DEC(NVL(LPP.RESIDENT_NO, CM.RESIDENT_NO),'d') AS RESIDENT_NO,                          \n ");
		sb.append(" 	      NVL(LPP.DO_AMT, 0) AS DO_AMT,                                                                    \n ");
		sb.append(" 	      NVL(DECODE(SIGN(TO_NUMBER(CF2.VAL) - LPP.DO_AMT), 1, 0, LPP.DO_AMT * CF1.VAL/100), 0) AS PROMO_TAX,     \n ");
		sb.append(" 	      TAX_RCV_AMT,                                                                                     \n ");
		sb.append(" 	      TO_CHAR(TAX_RCV_DATE, 'YYYY/MM/DD') AS TAX_RCV_DATE,                                             \n ");
		sb.append(" 	      TAX_RCV_YN AS ORG_TAX_RCV_YN,                                                                    \n ");
		sb.append(" 	      TAX_RCV_YN                                                                                       \n ");
		sb.append(" 	 FROM TLOTTERYPROMOPRIZE LPP,                                                                          \n ");
		sb.append(" 	      TLOTTERYPROMOM LP,                                                                               \n ");
		sb.append(" 	      TCUSTOMER CM,                                                                                    \n ");
		sb.append(" 	      TCONFIG CF1,                                                                                     \n ");
		sb.append(" 	      TCONFIG CF2                                                                                      \n ");
		sb.append(" 	WHERE LPP.LOTTERY_PROMO_NO = LP.LOTTERY_PROMO_NO                                                       \n ");
		sb.append(" 	  AND LPP.CUST_NO = CM.CUST_NO                                                                         \n ");
		sb.append(" 	  AND CF1.ITEM = 'PROMO_TAX_RATE'                                                                      \n ");
		sb.append(" 	  AND CF2.ITEM = 'PROMO_TAX_AMT'                                                                       \n ");
		sb.append(" 	  AND LPP.CANCEL_YN = '0'                                                                              \n ");
		sb.append(" 	  AND LPP.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')                                                      \n ");
		sb.append(" 	  AND LPP.INSERT_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1                                                   \n ");
		sb.append(" 	  AND LPP.LOTTERY_PROMO_NO LIKE ? ||'%'                                                                \n ");
		if (!"".equals(retrieve.getString("tax_rcv_yn"))) {
			sb.append(" 	  AND LPP.TAX_RCV_YN = ?                                                                               \n ");
		}
		//= log SQL -------------------------------------------------
		if (log.isDebugEnabled()) {
			log.debug(sb.toString());
			log.debug("fromDate   : " + retrieve.getString("fromDate"));
			log.debug("toDate   : " + retrieve.getString("toDate"));
			log.debug("lottery_promo_no   : " + retrieve.getString("lottery_promo_no"));
			if (!"".equals(retrieve.getString("tax_rcv_yn"))) {
				log.debug("tax_rcv_yn   : " + retrieve.getString("tax_rcv_yn"));
			}
		}

		return sb.toString();
	}

	//= Edit Result-----------------------------------------------
	/**
	 * <PRE>
	 * Desc : Edit retrieval result
	 * </PRE>
	 *
	 * @param ResultSet
	 * @return HashMap[]
	 */
	public HashMap[] makeSheet(ResultSet rset) throws Exception {
		Collection collection = new ArrayList();
		HashMap hmSheet = null;
		long retRow = 0;

		while (rset != null && rset.next()) {
			hmSheet = new HashMap();
			hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
			//= Modify
			collection.add(hmSheet);
			retRow++;
		}

		//= log Column Name & retrieve row no ---------------------
		if (log.isDebugEnabled()) {
			if (hmSheet != null) {
				Collection c = hmSheet.keySet();
				Iterator i = c.iterator();
				while (i.hasNext()) {
					Object key = i.next();
					log.debug(key.toString());
				}
			}
			log.debug("Retrieve Row : " + retRow);
		}
		return (HashMap[]) collection.toArray(new HashMap[0]);
	}

	//= Retrieve -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Retrieve SQL
	 * </PRE>
	 *
	 * @param Connection
	 * @param RetrieveModel
	 * @return RetrieveModel
	 */
	public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = con.prepareStatement(makeSql(retrieve));

			int index = 1;
			pstmt.setString(index++, retrieve.getString("fromDate"));
			pstmt.setString(index++, retrieve.getString("toDate"));
			pstmt.setString(index++, retrieve.getString("lottery_promo_no"));

			if (!"".equals(retrieve.getString("tax_rcv_yn"))) {
				pstmt.setString(index++, retrieve.getString("tax_rcv_yn"));
			}

			rset = pstmt.executeQuery();

			retrieve.put("RESULT", makeSheet(rset));

		} catch (SQLException se) {
			log.error("[LotteryPromoTaxManageSvc.retrieve() SQLException : ERR-" + se.getErrorCode() + ":" + se);
			throw new StoreException(se.getMessage());
		} catch (Exception e) {
			log.error("[LotteryPromoTaxManageSvc.retrieve() Exception : ERR-" + e.getMessage());
			throw new StoreException(e.getMessage());
		} finally {
			DBUtils.freeConnection(null, pstmt, rset);
		}
		return retrieve;
	}

	//= update -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : update Tmultidtbroad
	 * </PRE>
	 *
	 * @param Connection
	 * @param Tgoodslink
	 * @return int
	 */
	public int update(Connection con, Tlotterypromoprize tlotterypromoprize) throws StoreException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlUpdate(tlotterypromoprize));
			int index = 1;

			pstmt.setLong(  index++, tlotterypromoprize.getTax_rcv_amt 	()	);
			pstmt.setString(index++, tlotterypromoprize.getTax_rcv_date	()	);
			pstmt.setString(index++, tlotterypromoprize.getTax_rcv_yn 	()	);
			pstmt.setString(index++, tlotterypromoprize.getTax_rcv_id 	()	);
			pstmt.setString(index++, tlotterypromoprize.getResident_no 	()	);
			pstmt.setString(index++, tlotterypromoprize.getLottery_promo_no());
			pstmt.setString(index++, tlotterypromoprize.getSeq()		);

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( tlotterypromoprize.getTax_rcv_amt 	()		); logString.append("/");
			logString.append( tlotterypromoprize.getTax_rcv_date	()		); logString.append("/");
			logString.append( tlotterypromoprize.getTax_rcv_yn 		()		); logString.append("/");
			logString.append( tlotterypromoprize.getTax_rcv_id 		()		); logString.append("/");
			logString.append( tlotterypromoprize.getResident_no 	()		); logString.append("/");
			logString.append( tlotterypromoprize.getLottery_promo_no 	()		); logString.append("/");
			logString.append( tlotterypromoprize.getSeq 	()		); logString.append("/");

			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		} catch (SQLException se) {
			logSave.error("LotteryPromoTaxManageSvc.update SQLException : ERR-" + se.getErrorCode() + ":" + se);
			throw new StoreException(se.getMessage());
		} catch (Exception e) {
			logSave.error("LotteryPromoTaxManageSvc.update Exception : ERR-" + e.getMessage());
			throw new StoreException(e.getMessage());
		} finally {
			DBUtils.freeConnection(null, pstmt, rset);
		}
		return executedRtn;
	}

	/**
	 * <PRE>
	 * Desc : Make SQL ( Update TLOTTERYPROMOPRIZE )
	 * </PRE>
	 *
	 * @param
	 * @return String
	 */
	private final String updateSql = " UPDATE TLOTTERYPROMOPRIZE 					\n" +
								     "  SET TAX_RCV_AMT   = ?,						\n" +
								     "  	TAX_RCV_DATE  = TO_DATE(?,'YYYY/MM/DD'),\n" +
								     "   	TAX_RCV_YN    = ?,						\n" +
								     "   	TAX_RCV_ID    = ?,						\n" +
								     "      RESIDENT_NO   = CWARE_ENC_DEC(?, 'e')	\n" +
								     "WHERE LOTTERY_PROMO_NO=? AND SEQ = ?			";

	private int updateSqlLog = 0;

	private String makeSqlUpdate(Tlotterypromoprize tlotterypromoprize) throws StoreException {
		//= log SQL -------------------------------------------------
		if (updateSqlLog == 0) {
			if (logSave.isDebugEnabled()) {
				logSave.debug(updateSql);
			}
			updateSqlLog = 1;
		}
		return updateSql;
	}
}
