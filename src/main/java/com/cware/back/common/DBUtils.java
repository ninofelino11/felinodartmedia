
package com.cware.back.common;

import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NoInitialContextException;
import javax.sql.DataSource;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DataBase Control Utility
 * @version 1.0, 2006/04/01
 * @author  kim Sungtaek <webzest@commerceware.co.kr>
 */
public class DBUtils {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);
    private static String logTemp       = "";

    private static String PoolName = "service";

    public DBUtils(){}

    public static String getUserName(Connection con, String user_id) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            user_name    = "";

        try{
            pstmt = con.prepareStatement("SELECT USER_NAME FROM TUSER WHERE USER_ID = ?");

            pstmt.setString(1, user_id);

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) user_name = rset.getString(1);

        } catch(SQLException se) {
        	logTemp = "[DBUtils.getUserName] SQLException:"+se;
        	log.error(logTemp);
            throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = "[DBUtils.getUserName] SQLException:"+e;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = "[DBUtils.getUserName] Connection Recover Exception:"+s;
                log.error(logTemp);
            }
        }
        return user_name.toUpperCase();
    }

    /** DB Connection User Name **/
    public static String getConnUserID(Connection con) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            user_id    = "";

        try{
            pstmt = con.prepareStatement("SELECT SYS.LOGIN_USER() AS USER_ID FROM DUAL");

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) user_id = rset.getString(1);

        } catch(SQLException se) {
        	logTemp = "[DBUtils.getConnUserName] SQLException:"+se;
        	log.error(logTemp);
            throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = "[DBUtils.getConnUserName] SQLException:"+e;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = "[DBUtils.getConnUserName] Connection Recover Exception:"+s;
                log.error(logTemp);
            }
        }
        return user_id;
    }

    /** TCODE 에서 CODE_LGROUP, CODE_MGROUP 을 사용하여 CODE_NAME 값 얻기 **/
    public static String findTcodeCodeName(Connection con, String codeLgroup, String codeMgroup) throws StoreException {
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;
        String            codeName = "";

        try{
            pstmt = con.prepareStatement("SELECT NVL(CODE_NAME,'') AS CODE_NAME  FROM TCODE WHERE CODE_LGROUP=? AND CODE_MGROUP=? AND USE_YN=1 AND ROWNUM=1");
            pstmt.setString(1,codeLgroup);
            pstmt.setString(2,codeMgroup);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) codeName = rset.getString(1);

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_get", "CODE_NAME")+se;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_get", "CODE_NAME")+e;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_get", "CODE_NAME")+s;
                log.error(logTemp);
                throw new StoreException(logTemp);
            }
        }
        return codeName;
    }

    /** TCODE 에서 CODE_LGROUP, CODE_MGROUP 을 사용하여 원하는 필드 값 얻기 **/
    public static String findTcodeCodeName(Connection con, String codeLgroup, String codeMgroup, String fild_name) throws StoreException {
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;
        String            codeName = "";

        try{
            pstmt = con.prepareStatement("SELECT NVL( " + fild_name + ", CODE_NAME ) AS CODE_NAME  FROM TCODE WHERE CODE_LGROUP=? AND CODE_MGROUP=? AND USE_YN=1 AND ROWNUM=1");
            pstmt.setString(1,codeLgroup);
            pstmt.setString(2,codeMgroup);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) codeName = rset.getString(1);

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_get", "CODE_NAME")+se;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_get", "CODE_NAME")+e;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_get", "FIEDL_CODE_NAME")+s;
                log.error(logTemp);
                throw new StoreException(logTemp);
            }
        }
        return codeName;
    }

    //= gft_sequence_no 변환 : 각종 Sequence No를 생성하여 Return
    public static String getSequenceNo(Connection con, String type) throws StoreException {
        String sequenceNo = "";

        if(type.equals("CUST_NO")){                 // 10 : 미완
            sequenceNo = getCustNo(con);
        }else if(type.equals("ORDER_NO")){          // 20 : 완료
            sequenceNo = getOrdNo(con);
        }else if(type.equals("REPAY_NO")){          // 50 : 미완
            sequenceNo = getRepayNo(con);
        }else if(type.equals("PREV_NO")){           // 60 : 미완
            sequenceNo = getFromDual(con);
        }else if(type.equals("GOODS_SELECT_NO")){   // 70 : 완료
            sequenceNo = getItemNo(con);
        }else if(type.equals("RECEIPT_NO")){        // 90 : 미완
            sequenceNo = getInamtNo(con);
        }else if(type.equals("UMS_NO")){            // 100 : 완료
            sequenceNo = getUmsNo(con);
        }else if(type.equals("MEMB_NO")){           // 110 : 완료
            sequenceNo = getMemberNo(con);
        }else if(type.equals("GRADE_CTRL_NO")){     //  : 완료
            sequenceNo = getGradeCtrlNo(con);
        }else if(type.equals("PICKING_SEQ")){       //  : 완료
            sequenceNo = getPickingSeq(con);
        }else if(type.equals("BALJU_NO")){         //  : 완료
            sequenceNo = getBaljuSeq(con);
        }else if(type.equals("IN_NO")){            //  : 완료
            sequenceNo = getInSeq(con);
        }else if(type.equals("SLIP_I_NO")){         //  : 완료
            sequenceNo = getSlipINo(con);
        }else if(type.equals("RETURN_NO")){         //  : 완료
            sequenceNo = getReturnNo(con);
        }else if(type.equals("PRESENTATION_CODE")){
            sequenceNo = getPresentationCode(con);
        }else if(type.equals("CATEGORYURL_CODE")){
            sequenceNo = getCategoryurlCode(con);
        }else if(type.equals("CMS_NO")){
            sequenceNo = getCmsNo(con);
        }else if(type.equals("LINK_NO")){
            sequenceNo = getLinkNo(con);
        }else if(type.equals("EOUT_QUEST_NO")){
            sequenceNo = getEoutQuestNo(con);
        }else if(type.equals("EOUT_NO")){
            sequenceNo = getEoutNo(con);
        }else if(type.equals("AS_NO")){             // : 완료
            sequenceNo = getAsNo(con);
        }else if(type.equals("ETCIO_NO")){
            sequenceNo = getEtcioNo(con);
        }else if(type.equals("COUPONISSUE_SEQ")){
            sequenceNo = getCouponissueSeq(con);
        }else if(type.equals("ORDERPROMO_SEQ")){
            sequenceNo = getOrderpromoSeq(con);
        }else if(type.equals("LOTTERYPROMOCUST_SEQ")){
            sequenceNo = getLotterypromocustSeq(con);
        }else if(type.equals("LOTTERYPRIZE_SEQ")){
            sequenceNo = getLotterypromoprizeSeq(con);
        }else if(type.equals("COUNSEL_SEQ")){
	        sequenceNo = getCounselSeq(con);
        }else if(type.equals("SAMPLEREQ_NO_SEQ")){
	        sequenceNo = getSamplereqNoSeq(con);
	    }else if(type.equals("SEQ_OB_SEQ")){
	        sequenceNo = getObSeq(con);
	    }else if(type.equals("SEQ_DMREQ_NO")){
	    	sequenceNo = getDmSeq(con);
	    }else if(type.equals("POSTCARD_NO")){
	        sequenceNo = getPostcardNo(con);
	    }else if(type.equals("BEAUTY_NO")){
	        sequenceNo = getBeautyNo(con);
	    }else if(type.equals("MAGAZINE_NO")){
	    	sequenceNo = getMagazineNo(con);
	    }else if(type.equals("RACK_MOVE_NO")){
	    	sequenceNo = getRackMoveNo(con);
	    }else if(type.equals("SEGMENT_CODE")){
	    	sequenceNo = getSegmentCode(con);
	    }else if(type.equals("EVENT_NO")){
            sequenceNo = getEventNo(con);
	    }else if(type.equals("APPLY_SEQ")){
            sequenceNo = getApplySeq(con);
	    }else if(type.equals("PLANCLASS_CODE")){
            sequenceNo = getPlanclassCode(con);
	    }else if(type.equals("PLAN_CODE")){
            sequenceNo = getPlanCode(con);
	    }else if(type.equals("PRDTN_NO")){
	    	sequenceNo = getPrdtnNo(con);
	    }

	        return sequenceNo;
	    }



    private static String getRepayNo (Connection con) throws StoreException {
        String            repayNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_REPAY_NO.NEXTVAL, '0000000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                repayNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "REPAY_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "REPAY_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "REPAY_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "REPAY_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return repayNo;
    }


    private static String getAsNo(Connection con) throws StoreException {

        String            AsNo     = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_AS_NO.NEXTVAL,'000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                AsNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "A/S");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "A/S")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "A/S")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "A/S")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return AsNo;
    }


    private static String getEtcioNo(Connection con) throws StoreException {

        String            etcioNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_ETCIO_NO.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                etcioNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ETCIO_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ETCIO_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ETCIO_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ETCIO_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return etcioNo;
    }


    private static String getCouponissueSeq(Connection con) throws StoreException {

        String            couponissueSeq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_COUPONISSUE_SEQ.NEXTVAL,'00000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	couponissueSeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "COUPONISSUE_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "COUPONISSUE_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "COUPONISSUE_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "COUPONISSUE_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return couponissueSeq;
    }



    private static String getOrderpromoSeq(Connection con) throws StoreException {

        String            orderpromoSeq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_ORDERPROMO_SEQ.NEXTVAL,'00000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	orderpromoSeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDERPROMO_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDERPROMO_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDERPROMO_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDERPROMO_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return orderpromoSeq;
    }


    private static String getLotterypromocustSeq(Connection con) throws StoreException {

        String            lotterypromocustSeq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_LOTTERYPROMOCUST_SEQ.NEXTVAL,'00000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	lotterypromocustSeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPROMOCUST_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPROMOCUST_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPROMOCUST_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPROMOCUST_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return lotterypromocustSeq;
    }


    private static String getLotterypromoprizeSeq(Connection con) throws StoreException {

        String            prizeSeq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_LOTTERYPRIZE_SEQ.NEXTVAL,'00000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                prizeSeq = rset.getString(1);
            }else{
                logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPRIZE_SEQ");
                log.error(logTemp);
                throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
            logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPRIZE_SEQ")+se;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } catch(Exception e) {
            logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPRIZE_SEQ")+e;
            log.error(logTemp);
            throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                logTemp = ComUtils.getMessage("msg.cannot_create", "LOTTERYPRIZE_SEQ")+s;
                log.error(logTemp);
                throw new StoreException(logTemp);
            }
        }
        return prizeSeq;
    }




    private static String getCounselSeq(Connection con) throws StoreException {

        String            lotterypromocustSeq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_COUNSEL_SEQ.NEXTVAL,'000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	lotterypromocustSeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "COUNSEL_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "COUNSEL_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "COUNSEL_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "COUNSEL_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return lotterypromocustSeq;
    }

    private static String getSamplereqNoSeq(Connection con) throws StoreException {

        String            samplereq_no_Seq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_SAMPLEREQ_NO.NEXTVAL,'000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	samplereq_no_Seq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SAMPLEREQ_NO_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SAMPLEREQ_NO_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SAMPLEREQ_NO_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SAMPLEREQ_NO_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return samplereq_no_Seq;
    }


    private static String getObSeq(Connection con) throws StoreException {

        String            samplereq_no_Seq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_OB_SEQ.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	samplereq_no_Seq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return samplereq_no_Seq;
    }

    private static String getDmSeq(Connection con) throws StoreException {

        String            dm_no_Seq = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_DMREQ_NO.NEXTVAL,'000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	dm_no_Seq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_DMREQ_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_DMREQ_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_DMREQ_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_DMREQ_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return dm_no_Seq;
    }


    private static String getOrdNo(Connection con) throws StoreException {

        String            seqTable = "";
        String            orderNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT DECODE(TO_NUMBER(TO_CHAR(SYSDATE, ?)), \n");
            sb.append("        1,'SUN', 2,'MON', 3,'TUE', \n");
            sb.append("        4,'WED', 5,'THU', 6,'FRI', 7,'SAT') \n");
            sb.append("   FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"D");
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                seqTable = "SEQ_ORDER_NO_" + rset.getString(1)+".NEXTVAL";
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDER_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, ?) || LTRIM(TO_CHAR("+seqTable+",?)) \n");
            sb.append("   FROM DUAL ");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"yyyymmdd");
            pstmt.setString(2,"000000");

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                orderNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDER_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDER_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDER_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "ORDER_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return orderNo;
    }

    private static String getCustNo(Connection con) throws StoreException {

        String            monthFlag= "";
        String            custNo   = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb.append("	SELECT MOD(TO_CHAR(SYSDATE, 'MM'),2)  \n");
            sb.append("   FROM DUAL  \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                monthFlag = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CUST_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

            sb = new StringBuffer();
            if( monthFlag.equals("0") ) {
                sb.append(" SELECT LTRIM(TO_CHAR(SYSDATE,'yyyymm'))|| LTRIM(TO_CHAR(SEQ_CUST_NO_EVEN.NEXTVAL, '000000'))  \n");
                sb.append("   FROM DUAL   \n");
            } else {
                sb.append(" SELECT LTRIM(TO_CHAR(SYSDATE,'yyyymm'))|| LTRIM(TO_CHAR(SEQ_CUST_NO_ODD.NEXTVAL, '000000'))  \n");
                sb.append("   FROM DUAL   \n");
            }

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                custNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CUST_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CUST_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CUST_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CUST_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return custNo;
    }

    private static String getGradeCtrlNo(Connection con) throws StoreException {

        String            gradeCtrlNo   = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') ||                   \n");
            sb.append("        LTRIM(TO_CHAR(SEQ_GRADE_CTRL_NO.NEXTVAL, '0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                gradeCtrlNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "GRADE_CTRL_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "GRADE_CTRL_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "GRADE_CTRL_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "GRADE_CTRL_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return gradeCtrlNo;
    }

    private static String getPresentationCode(Connection con) throws StoreException {

        String            presentationCode   = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_PRESENTATION_CODE.NEXTVAL, '0000000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                presentationCode = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PRESENTATION_CODE");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PRESENTATION_CODE")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PRESENTATION_CODE")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PRESENTATION_CODE")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return presentationCode;
    }

    private static String getCategoryurlCode(Connection con) throws StoreException {

        String            categoryurlCode   = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_CATEGORYURL_CODE.NEXTVAL, '0000000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                categoryurlCode = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CATEGORYURL_CODE");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CATEGORYURL_CODE")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CATEGORYURL_CODE")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CATEGORYURL_CODE")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return categoryurlCode;
    }

    private static String getCmsNo(Connection con) throws StoreException {

        String            cmsNo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append("SELECT LTRIM(TO_CHAR(SEQ_CMS_NO.NEXTVAL,'00000000000')) \n");
            sb.append("  FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                cmsNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CMS_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CMS_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CMS_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CMS_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return cmsNo;
    }

    private static String getLinkNo(Connection con) throws StoreException {

        String            linkNo = "";
        StringBuffer      sb        = new StringBuffer();
        PreparedStatement pstmt     = null;
        ResultSet         rset      = null;

        try{

            sb.append("SELECT LTRIM(TO_CHAR(SEQ_LINK_NO.NEXTVAL, '000000000000')) \n");
            sb.append("  FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                linkNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "LINK_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "LINK_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "LINK_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "LINK_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return linkNo;
    }

    /* 2006/08/09 */
    private static String getEoutQuestNo(Connection con) throws StoreException {

        String            eoutQuestNo = "";
        StringBuffer      sb          = new StringBuffer();
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try{

            sb.append("SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') || \n");
            sb.append("       LTRIM(TO_CHAR(SEQ_EOUT_QUEST_NO.NEXTVAL, '0000')) EOUT_QUEST_NO \n");
            sb.append("  FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                eoutQuestNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_QUEST_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_QUEST_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_QUEST_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_QUEST_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return eoutQuestNo;
    }

    /* 2006/08/09 */
    private static String getEoutNo(Connection con) throws StoreException {

        String            eoutNo = "";
        StringBuffer      sb     = new StringBuffer();
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;

        try{

            sb.append("SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||  LTRIM(TO_CHAR(SEQ_EOUT_NO.NEXTVAL, '0000')) EOUT_NO \n");
            sb.append("  FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                eoutNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EOUT_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return eoutNo;
    }

    private static String getPickingSeq(Connection con) throws StoreException {

        String            pickingSeq = "";
        StringBuffer      sb         = new StringBuffer();
        PreparedStatement pstmt      = null;
        ResultSet         rset       = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') ||                   \n");
            sb.append("        LTRIM(TO_CHAR(SEQ_PICKING_SEQ.NEXTVAL, '0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                pickingSeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PICKING_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PICKING_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PICKING_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PICKING_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return pickingSeq;
    }

    private static String getBaljuSeq(Connection con) throws StoreException {

        String            balju_no   = "";
        StringBuffer      sb         = new StringBuffer();
        PreparedStatement pstmt      = null;
        ResultSet         rset       = null;

        try{

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') ||              \n");
            sb.append("        LTRIM(TO_CHAR(SEQ_BALJU_NO.NEXTVAL, '0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                balju_no = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "BALJU_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "BALJU_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "BALJU_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "BALJU_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return balju_no;
    }

    private static String getInSeq(Connection con) throws StoreException {

        String            in_no   = "";
        StringBuffer      sb         = new StringBuffer();
        PreparedStatement pstmt      = null;
        ResultSet         rset       = null;

        try{
            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') ||              \n");
            sb.append("        LTRIM(TO_CHAR(SEQ_IN_NO.NEXTVAL, '0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                in_no = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "IN_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "IN_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "IN_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "IN_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return in_no;
    }

    private static String getFromDual(Connection con) throws StoreException {
        String fromDual = "";

        return fromDual;
    }

    private static String getItemNo(Connection con) throws StoreException {
        String            seqTable = "";
        String            itemNo   = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT DECODE(TO_NUMBER(TO_CHAR(SYSDATE, ?)), \n");
            sb.append("        1,'SUN', 2,'MON', 3,'TUE', \n");
            sb.append("        4,'WED', 5,'THU', 6,'FRI', 7,'SAT') \n");
            sb.append("   FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"D");
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                seqTable = "SEQ_GOODS_SELECT_NO_" + rset.getString(1)+".NEXTVAL";
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "GOODS_SELECT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, ?) || LTRIM(TO_CHAR("+seqTable+",?)) \n");
            sb.append("     FROM DUAL ");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"yyyymmdd");
            pstmt.setString(2,"00000000");

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                itemNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "GOODS_SELECT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "GOODS_SELECT_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "GOODS_SELECT_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "GOODS_SELECT_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return itemNo;
    }

    private static String getInamtNo(Connection con) throws StoreException {
        String            seqTable = "";
        String            inamtNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT DECODE(TO_NUMBER(TO_CHAR(SYSDATE, ?)), \n");
            sb.append("        1,'SUN', 2,'MON', 3,'TUE', \n");
            sb.append("        4,'WED', 5,'THU', 6,'FRI', 7,'SAT') \n");
            sb.append("   FROM DUAL \n");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"D");
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                seqTable = "SEQ_RECEIPT_NO_" + rset.getString(1)+".NEXTVAL";
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "RECEIPT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, ?) || LTRIM(TO_CHAR("+seqTable+",?)) \n");
            sb.append("   FROM DUAL ");

            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,"yyyymmdd");
            pstmt.setString(2,"00000000");

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                inamtNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "RECEIPT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "RECEIPT_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "RECEIPT_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "RECEIPT_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return inamtNo;
    }

    private static String getUmsNo(Connection con) throws StoreException {
        String            umsNo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') ||  \n");
            sb.append("        LTRIM(TO_CHAR(SEQ_UMS_NO.NEXTVAL, '000000'))  \n");
            sb.append("   FROM DUAL  ");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                umsNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "UMS_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "UMS_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "UMS_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "UMS_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return umsNo;
    }


    private static String getMemberNo(Connection con) throws StoreException {
        String            member_no= "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_MEMB_NO.NEXTVAL, '00000000'))  \n");
            sb.append("   FROM DUAL  \n");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                member_no = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "MEMBER_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "MEMBER_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "MEMBER_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "MEMBER_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return member_no;
    }


    private static String getSlipINo(Connection con) throws StoreException {
        String            slipINo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT LPAD(SEQ_SLIP_I_NO.NEXTVAL, 14, '0')  \n");
            sb.append("   FROM DUAL  ");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                slipINo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SLIP_I_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SLIP_I_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SLIP_I_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SLIP_I_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return slipINo;
    }


    private static String getReturnNo(Connection con) throws StoreException {
        String            returnNo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_RETURN_NO.NEXTVAL, '0000'))  \n");
            sb.append("   FROM DUAL  ");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                returnNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "RETURN_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "RETURN_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "RETURN_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "RETURN_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return returnNo;
    }

    public static String getCtrlNo(Connection con) throws StoreException {
        String            returnNo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_CTRL_NO.NEXTVAL, '0000'))  \n");
            sb.append("   FROM DUAL  ");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                returnNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CTRL_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CTRL_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "CTRL_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "CTRL_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return returnNo;
    }

    public static String getPurchaseNo(Connection con) throws StoreException {
        String            returnNo    = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_PURCHASE_NO.NEXTVAL, '0000'))  \n");
            sb.append("   FROM DUAL  ");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                returnNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PURCHASE_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PURCHASE_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PURCHASE_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PURCHASE_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return returnNo;
    }


    public static String getSysDateTime(Connection con, String type) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            sysDateTime = "";

        try{
            pstmt = con.prepareStatement("SELECT TO_CHAR(SYSDATE,?) FROM DUAL");
            pstmt.setString(1,type);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) sysDateTime = rset.getString(1);

        } catch(SQLException se) {
            sysDateTime = "";
            log.error("[DBUtils.getSysDateTime] SQLException:"+se);
        } catch(Exception e) {
            sysDateTime = "";
            log.error("[DBUtils.getSysDateTime] Exception:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getSysDateTime] Connection Recover Exception:"+s);
            }
        }
        return sysDateTime;
    }

    public static String getSysDateTimeAdd(Connection con, String type, int addDay) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            sysDateTime = "";

        try{
            pstmt = con.prepareStatement("SELECT TO_CHAR(SYSDATE + ?,?) FROM DUAL");
            pstmt.setInt   (1,addDay);
            pstmt.setString(2,type);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) sysDateTime = rset.getString(1);

        } catch(SQLException se) {
            sysDateTime = "";
            log.error("[DBUtils.getSysDateTime] SQLException:"+se);
        } catch(Exception e) {
            sysDateTime = "";
            log.error("[DBUtils.getSysDateTime] Exception:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getSysDateTime] Connection Recover Exception:"+s);
            }
        }
        return sysDateTime;
    }

    public static String getDateTimeAdd(Connection con, String date, String type, int addDay) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            newDateTime = "";

        try{
            pstmt = con.prepareStatement("SELECT TO_DATE(? ,?) + ?) AS NEW_DATE FROM DUAL");
            pstmt.setString   (1,date);
            pstmt.setString   (2,type);
            pstmt.setInt      (3,addDay);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) newDateTime = rset.getString(1);

        } catch(SQLException se) {
        	newDateTime = "";
            log.error("[DBUtils.getDateTimeAdd] SQLException:"+se);
        } catch(Exception e) {
        	newDateTime = "";
            log.error("[DBUtils.getDateTimeAdd] Exception:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getDateTimeAdd] Connection Recover Exception:"+s);
            }
        }
        return newDateTime;
    }

    public static String getSysDateTimeAdd(Connection con, int addTime, String type) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            newDateTime = "";

        try{
            pstmt = con.prepareStatement("SELECT TO_CHAR((SYSDATE + (?/24)), ?) AS NEW_DATE FROM DUAL");
            pstmt.setInt      (1,addTime);
            pstmt.setString   (2,type);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) newDateTime = rset.getString(1);

        } catch(SQLException se) {
        	newDateTime = "";
            log.error("[DBUtils.getDateTimeAdd] SQLException:"+se);
        } catch(Exception e) {
        	newDateTime = "";
            log.error("[DBUtils.getDateTimeAdd] Exception:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getDateTimeAdd] Connection Recover Exception:"+s);
            }
        }
        return newDateTime;
    }


    public static String getMax(Connection con, String tableName, String columnName, String modString, int seqFormat) throws StoreException {
        Statement    stmt  = null;
        ResultSet    rset   = null;
        String       maxNo  = "";
        StringBuffer query  = new StringBuffer();
        try{

            query.append(" SELECT /*+INDEX_DESC( "+tableName+" PK_"+tableName+")*/ "+ columnName + "  \n");
            query.append("   FROM "+tableName+"   \n");
            if(modString.equals("")){
                query.append("   WHERE ROWNUM = 1  \n");
            }else{
                query.append(modString+" AND ROWNUM = 1  \n");
            }

            stmt = con.createStatement();
            rset = stmt.executeQuery(query.toString());
            if (rset!=null && rset.next()){
                maxNo = rset.getString(1);
                maxNo = ComUtils.increaseLpad(maxNo, seqFormat, "0");
            }else{
                maxNo = ComUtils.lpad("1", seqFormat, "0");
            }

        } catch(SQLException se) {
            maxNo = "";
            log.error("[DBUtils.getMax] SQLException:"+se);
        } catch(Exception e) {
            maxNo = "";
            log.error("[DBUtils.getMax] Exception:"+e);
        } finally {
            try{
                freeConnection(stmt, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getMax] Connection Recover Exception:"+s);
            }
        }
        return maxNo;
    }

    public static String getSeqNo(Connection con, String tableName, String columnName, String argDate, int seqFormat) throws StoreException {
        Statement    stmt  = null;
        ResultSet    rset   = null;
        String       seqNo  = "";
        StringBuffer query  = new StringBuffer();
        try{

            query.append(" SELECT /*+INDEX_DESC( A PK_"+tableName+")*/ "+ columnName + " + 1  \n");
            query.append("   FROM "+tableName+" A  \n");
            query.append("  WHERE A."+columnName+" LIKE TO_CHAR(TO_DATE('"+argDate+"','YYYY/MM/DD hh24:mi:ss'),'YYYYMMDD')||'%'  \n");
            query.append("    AND ROWNUM = 1  \n");

            stmt = con.createStatement();
            rset = stmt.executeQuery(query.toString());
            if (rset!=null && rset.next()){
                seqNo = rset.getString(1);
            }else{
                query  = new StringBuffer();
                query.append(" SELECT TO_CHAR(TO_DATE('"+argDate+"','YYYY/MM/DD hh24:mi:ss'),'YYYYMMDD') || LPAD(TO_CHAR('1'), "+seqFormat+", '0')  \n");
                query.append("     FROM DUAL ");
                rset = stmt.executeQuery(query.toString());
                if (rset!=null && rset.next()){
                    seqNo = rset.getString(1);
                }
            }

        } catch(SQLException se) {
            seqNo = "";
            log.error("[DBUtils.getSeqNo] SQLException:"+se);
        } catch(Exception e) {
            seqNo = "";
            log.error("[DBUtils.getSeqNo] Exception:"+e);
        } finally {
            try{
                freeConnection(stmt, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getSeqNo] Connection Recover Exception:"+s);
            }
        }
        return seqNo;
    }

    public static String getSeqNo(Connection con, String tableName, String columnName, String argDate, String modString, int seqFormat) throws StoreException {
        Statement    stmt  = null;
        ResultSet    rset   = null;
        String       seqNo  = "";
        StringBuffer query  = new StringBuffer();
        try{

            query.append(" SELECT /*+INDEX_DESC( A PK_"+tableName+")*/ "+ columnName + " + 1  \n");
            query.append("   FROM "+tableName+" A  \n");
            query.append("  WHERE A."+columnName+" LIKE TO_CHAR(TO_DATE('"+argDate+"','YYYY/MM/DD hh24:mi:ss'),'YYYYMMDD')||'%'  \n");
            /* add modString : 2007/01/23 */
            if(!modString.equals("")) query.append( modString );

            query.append("     AND ROWNUM = 1  \n");

            stmt = con.createStatement();
            rset = stmt.executeQuery(query.toString());
            if (rset!=null && rset.next()){
                seqNo = rset.getString(1);
            }else{
                query  = new StringBuffer();
                query.append(" SELECT TO_CHAR(TO_DATE('"+argDate+"','YYYY/MM/DD hh24:mi:ss'),'YYYYMMDD') || LPAD(TO_CHAR('1'), "+seqFormat+", '0')  \n");
                query.append("   FROM DUAL ");
                rset = stmt.executeQuery(query.toString());
                if (rset!=null && rset.next()){
                    seqNo = rset.getString(1);
                }
            }

        } catch(SQLException se) {
            seqNo = "";
            log.error("[DBUtils.getSeqNo] SQLException:"+se);
        } catch(Exception e) {
            seqNo = "";
            log.error("[DBUtils.getSeqNo] Exception:"+e);
        } finally {
            try{
                freeConnection(stmt, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getSeqNo] Connection Recover Exception:"+s);
            }
        }
        return seqNo;
    }


    public static String getConfig(Connection con, String item) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            val    = "";

        try{
            pstmt = con.prepareStatement("SELECT VAL  FROM TCONFIG  WHERE ITEM = ?");

            pstmt.setString(1,item);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) val = rset.getString(1);

        } catch(SQLException se) {
            log.error("[DBUtils.getConfig] SQLException:"+se);
            throw new StoreException("[DBUtils.getConfig] SQLException:"+se);
        } catch(Exception e) {
            log.error("[DBUtils.getConfig] SQLException:"+e);
            throw new StoreException("[DBUtils.getConfig] SQLException:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getConfig] Connection Recover Exception:"+s);
            }
        }
        return val;
    }

    //= DB의 Config 전체 Load
    public static HashMap getConfig(Connection con) throws Exception {
        PreparedStatement 		pstmt      = null;
        ResultSet 		  		rset       = null;
        HashMap config = new HashMap();

        try{
	        pstmt = con.prepareStatement("SELECT ITEM, VAL FROM TCONFIG ");
	        rset = pstmt.executeQuery();

			while (rset!=null && rset.next()){
				config.put(rset.getString("ITEM"), rset.getString("VAL"));
	        }
        } catch(SQLException se) {
            log.error("[DBUtils.getConfig] SQLException:"+se);
            throw new StoreException("[DBUtils.getConfig] SQLException:"+se);
        } catch(Exception e) {
            log.error("[DBUtils.getConfig] SQLException:"+e);
            throw new StoreException("[DBUtils.getConfig] SQLException:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getConfig] Connection Recover Exception:"+s);
            }
        }

		return config;
    }

    public static String getTmakecomp(Connection con, String item) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            val    = "";

        try{
            pstmt = con.prepareStatement("SELECT MAKECO_NAME  FROM TMAKECOMP  WHERE MAKECO_CODE = ?");

            pstmt.setString(1,item);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) val = rset.getString(1);

        } catch(SQLException se) {
            log.error("[DBUtils.getTmakecomp] SQLException:"+se);
            throw new StoreException("[DBUtils.getTmakecomp] SQLException:"+se);
        } catch(Exception e) {
            log.error("[DBUtils.getTmakecomp] SQLException:"+e);
            throw new StoreException("[DBUtils.getTmakecomp] SQLException:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getTmakecomp] Connection Recover Exception:"+s);
            }
        }
        return val;
    }

    public static String getTbrand(Connection con, String item) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            val    = "";

        try{
            pstmt = con.prepareStatement("SELECT BRAND_NAME  FROM TBRAND  WHERE BRAND_CODE = ?");

            pstmt.setString(1,item);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) val = rset.getString(1);

        } catch(SQLException se) {
            log.error("[DBUtils.getTbrand] SQLException:"+se);
            throw new StoreException("[DBUtils.getTbrand] SQLException:"+se);
        } catch(Exception e) {
            log.error("[DBUtils.getTbrand] SQLException:"+e);
            throw new StoreException("[DBUtils.getTbrand] SQLException:"+e);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
                log.error("[DBUtils.getTbrand] Connection Recover Exception:"+s);
            }
        }
        return val;
    }



    public static HashMap executeQueryHashMapTrim(ResultSet rset, HashMap hmSheet) throws StoreException {

        ResultSetMetaData   meta     = null;
        String              dbString = "";
        StringBuffer        sBuf     = null;
        Reader              in       = null;
        int                 r_code   = 0;
        DecimalFormat		df		 = new DecimalFormat("#.##");

        try{
            meta = rset.getMetaData();
            for(int i=1; i <= meta.getColumnCount(); i++){
                dbString = "";
                if(meta.getColumnTypeName(i).equals("-1")){
                    in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                    r_code = 0;
                    sBuf = new StringBuffer();
                    while ((r_code = in.read()) != -1){
                        sBuf.append((char)r_code);
                    }
                    dbString = sBuf.toString();
                }else{
                	if ( meta.getColumnType(i) == OracleTypes.NUMBER )
                		dbString =  rset.getObject(meta.getColumnName(i))==null?null:df.format(rset.getObject(meta.getColumnName(i)));
                	else dbString = rset.getString(meta.getColumnName(i));
                }
                if(dbString!=null){
                    hmSheet.put(meta.getColumnName(i).toUpperCase(),
                            dbString.trim());
                } else hmSheet.put(meta.getColumnName(i),"");
            }
        } catch(SQLException se){
            throw new StoreException(se.getMessage());
        } catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return hmSheet;
    }

     public static HashMap executeQueryHashMap(ResultSet rset, HashMap hmSheet) throws StoreException {

        ResultSetMetaData   meta     = null;
        Object              dbString = "";
        StringBuffer        sBuf     = null;
        Reader              in       = null;
        int                 r_code   = 0;
        DecimalFormat		df		 = new DecimalFormat("#.##");

        try{
            meta = rset.getMetaData();

            for(int i=1; i <= meta.getColumnCount(); i++){
                dbString = "";
                if(meta.getColumnTypeName(i).equals("-1") ||
                    meta.getColumnType(i) == OracleTypes.CLOB){
                     in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                     if (in != null){
                         r_code = 0;
                     	sBuf = new StringBuffer();
 	                    while ((r_code = in.read()) != -1){
 	                        sBuf.append((char)r_code);
 	                    }
 	                    dbString = sBuf.toString();
                     }
                }else{
                	if ( meta.getColumnType(i) == OracleTypes.NUMBER )
                		dbString =  rset.getObject(meta.getColumnName(i))==null?null:df.format(rset.getObject(meta.getColumnName(i)));
                	else dbString = rset.getString(meta.getColumnName(i));
                }
                if(dbString!=null){
                    hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString);
                } else hmSheet.put(meta.getColumnName(i),"");
            }
        } catch(SQLException se){
            throw new StoreException(se.getMessage());
        } catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return hmSheet;
    }

    public static HashMap[] executeQueryHashMapArrayTrim(ResultSet rset, String query) throws StoreException {

        ResultSetMetaData meta  = null;
        HashMap      hmSheet    = null;
        Collection   collection = new ArrayList();
        String       dbString   = "";
        StringBuffer sBuf       = null;
        Reader       in         = null;
        int          r_code     = 0;
        DecimalFormat df		= new DecimalFormat("#.##");

        try{
            meta = rset.getMetaData();
            while(rset!=null && rset.next()) {
                hmSheet=new HashMap();
                for(int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                    	if ( meta.getColumnType(i) == OracleTypes.NUMBER )
                    		dbString =  rset.getObject(meta.getColumnName(i))==null?null:df.format(rset.getObject(meta.getColumnName(i)));
                    	else dbString = rset.getString(meta.getColumnName(i));
                    }
                    if(dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(),
                                dbString.trim());
                    } else hmSheet.put(meta.getColumnName(i),"");
                }
                if(hmSheet!=null) collection.add(hmSheet);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryAL] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryAL] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryAL] Connection Recover Exception:"+s);
            }
        }
        return (HashMap[]) collection.toArray(new HashMap[0]);
    }

     public static HashMap[] executeQueryHashMapArray(ResultSet rset, String query) throws StoreException {

        ResultSetMetaData meta  = null;
        HashMap      hmSheet    = null;
        Collection   collection = new ArrayList();
        String       dbString   = "";
        StringBuffer sBuf       = null;
        Reader       in         = null;
        int          r_code     = 0;
        DecimalFormat	df		= new DecimalFormat("#.##");

        try{
            meta = rset.getMetaData();
            while(rset!=null && rset.next()) {
                hmSheet=new HashMap();
                for(int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                    	if ( meta.getColumnType(i) == OracleTypes.NUMBER )
                    		dbString =  rset.getObject(meta.getColumnName(i))==null?null:df.format(rset.getObject(meta.getColumnName(i)));
                    	else dbString = rset.getString(meta.getColumnName(i));
                    }
                    if(dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString);
                    } else hmSheet.put(meta.getColumnName(i),"");
                }
                if(hmSheet!=null) collection.add(hmSheet);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryAL] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryAL] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryAL] Connection Recover Exception:"+s);
            }
        }
        return (HashMap[]) collection.toArray(new HashMap[0]);
    }

    public static Connection getConnection(String service_name) throws StoreException  {
        Context    ctx = null;
        //Context    envCtx = null;
        DataSource ds  = null;
        Connection con = null;
        try{
            if(service_name.equals("")) service_name = PoolName;
            ctx = new InitialContext();

            try {
//            	envCtx  = (Context)ctx.lookup("java:/comp/env");
//            	ds = (DataSource)envCtx.lookup(service_name);
//                con = ds.getConnection();

/**
 *
 * ds = (DataSource) ctx.lookup("java:comp/env/"+service_name);
 * con = ds.getConnection();
 *
 */
  
            	ds = (DataSource) ctx.lookup("java:comp/env/"+service_name); //=tomcat
//            	ds = (DataSource) ctx.lookup("java:/"+service_name); //=jboss
//            	ds = (DataSource) ctx.lookup(service_name); //=WebShpere
                con = ds.getConnection();

            } catch (NoInitialContextException e) {
            	/** WAS로 부터 datasource 가져오기 실패할 경우 **/
            	/** 이런 경우는 Junit을 이용한 test이다. **/
            	/** test는 local에서 직접 이루어 지므로 DB로 직접 접속한다. **/
            	/** 실제 운영환경에서는 필요없다. **/
                /** 실제 운영시 아래 내용을 주석처리한다. **/
//            	Class.forName(Construct.LOCAL_DB_CLASS);
//            	String url = Construct.LOCAL_DB_URL;
//            	String userName = Construct.LOCAL_DB_USERNAME;
//            	String password = Construct.LOCAL_DB_PASSWORD;
//            	con = DriverManager.getConnection(url, userName, password);

            }

            con.setAutoCommit(false);

        }catch(SQLException sqle){
            log.error("[DBUtils.getConnection() :"+service_name+": SQLException] \n"+sqle);
            con = null;
            throw new StoreException(sqle.getMessage(), sqle);
        }catch(Exception e){
            log.error("[DBUtils.getConnection() :"+service_name+": Exception] \n"+e);
            con = null;
            throw new StoreException(e.getMessage(), e);
        }
        return con;
    }

    public static void freeConnection( Connection con ) throws StoreException {
        try{
            if( con   != null ) {
                try { if(con.getAutoCommit() == false) con.setAutoCommit(true); }catch(Exception ce){ log.error("[DBUtils.freeConnection() con.setAutoCommit(true); Exception] \n"+ce); }
                try { con.close(); }catch(Exception ce){ log.error("[DBUtils.freeConnection() con.close(); Exception] \n"+ce); }
                con = null;
            }
        }catch(Exception e){
            log.error("[DBUtils.freeConnection() Exception] \n"+e);
        }
    }

    public static void freeConnection(Statement stmt, PreparedStatement pstmt, ResultSet rset) throws StoreException {
        try{
            if( rset   != null ) try { rset.close();   }catch(Exception ce){log.error("[DBUtils.freeConnection() rset.close();   Exception] \n"+ce); }
            if( pstmt  != null ) try { pstmt.close();  }catch(Exception ce){log.error("[DBUtils.freeConnection() pstmt.close();  Exception] \n"+ce); }
            if( stmt   != null ) try { stmt.close();   }catch(Exception ce){log.error("[DBUtils.freeConnection() stmt.close();   Exception] \n"+ce); }
        }catch(Exception e){
            log.error("[DBUtils.freeConnection() Exception] \n"+e);
        }
    }

    public static void freeConnection(Connection con, Statement stmt, PreparedStatement pstmt, CallableStatement cstmt, OracleCallableStatement ocstmt, ResultSet rset) throws StoreException {
        try{
            if( rset   != null ) try { rset.close();   }catch(Exception ce){log.error("[DBUtils.freeConnection() rset.close();   Exception] \n"+ce); }
            if( pstmt  != null ) try { pstmt.close();  }catch(Exception ce){log.error("[DBUtils.freeConnection() pstmt.close();  Exception] \n"+ce); }
            if( stmt   != null ) try { stmt.close();   }catch(Exception ce){log.error("[DBUtils.freeConnection() stmt.close();   Exception] \n"+ce); }
            if( cstmt  != null ) try { cstmt.close();  }catch(Exception ce){log.error("[DBUtils.freeConnection() cstmt.close();  Exception] \n"+ce); }
            if( ocstmt != null ) try { ocstmt.close(); }catch(Exception ce){log.error("[DBUtils.freeConnection() ocstmt.close(); Exception] \n"+ce); }
            if( con   != null ) {
                try { if(con.getAutoCommit() == false) con.setAutoCommit(true); }catch(Exception ce){ log.error("[DBUtils.freeConnection() con.setAutoCommit(true); Exception] \n"+ce); }
                try { con.close(); }catch(Exception ce){ log.error("[DBUtils.freeConnection() con.close(); Exception] \n"+ce); }
                con = null;
            }
        }catch(Exception e){
            log.error("[DBUtils.freeConnection() Exception] \n"+e);
        }
    }

    public static PreparedStatement setPstmtValues( PreparedStatement pstmt, Object column_data[]) throws StoreException {

        if ( column_data == null ) return pstmt;

        int column_data_size = 0;

        try{ column_data_size  = column_data.length; }catch(Exception e){ return null; }

        try{
            if(column_data_size > 0){
                for(int i = 0 ; i < column_data_size; i++){
                    if(column_data[i] instanceof String) {
                        pstmt.setString(i+1,column_data[i].toString());
                    } else if(column_data[i] instanceof java.sql.Date || column_data[i] instanceof java.util.Date) {
                        pstmt.setString(i+1,column_data[i].toString());
                    } else if(column_data[i] instanceof Integer) {
                        pstmt.setInt(i+1,((Integer)column_data[i]).intValue());
                    } else if(column_data[i] instanceof Long) {
                        pstmt.setLong(i+1,((Long)column_data[i]).longValue());
                    } else if(column_data[i] instanceof Float) {
                        pstmt.setFloat(i+1,((Float)column_data[i]).floatValue());
                    } else if(column_data[i] instanceof Double) {
                        pstmt.setDouble(i+1,((Double)column_data[i]).doubleValue());
                    } else if(column_data[i] instanceof Boolean) {
                        pstmt.setBoolean(i+1,((Boolean)column_data[i]).booleanValue());
                    } else if(column_data[i] instanceof Character) {
                        pstmt.setString(i+1,column_data[i].toString());
                    } else {
                        pstmt.setString(i+1,column_data[i].toString());
                    }
                }
            }
        }catch(Exception e){return null;}
        return pstmt;
    }

    public static int executeUpdate(String poolName, String query) throws StoreException{

        int        execRtn = 0;
        Connection con     = null;
        Statement  stmt    = null;

        try{
            con     = getConnection(poolName);
            stmt    = con.createStatement();
            execRtn = stmt.executeUpdate(query);

            if (execRtn < 1){
                log.error("[DBUtils.executeUpdate] No Reflect Row \n"+query);
                con.rollback();
            }else{
                con.commit();
            }

        } catch(SQLException se) {
            try{ con.rollback(); } catch(Exception e) { log.error("[DBUtils.executeUpdate] RollBack Exception:"+e); }
            log.error("[DBUtils.executeUpdate] SQLException:"+se);
        } catch(Exception e) {
            try{ con.rollback(); } catch(Exception ee) { log.error("[DBUtils.executeUpdate] RollBack Exception:"+e); }
            log.error("[DBUtils.executeUpdate] Exception:"+e+" \n"+query);
        } finally {
            try{ freeConnection(con, stmt, null, null, null, null); }
            catch(Exception s) { log.error("[DBUtils.executeUpdate] finally Exception:"+s); }
        }
        return execRtn;
    }

    public static int executeUpdate(String poolName, String query, Object column_data[]) throws StoreException{

        int               execRtn = 0;
        Connection        con     = null;
        PreparedStatement pstmt   = null;

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement(query);
            pstmt = setPstmtValues(pstmt, column_data);
            if(pstmt == null) {
                con.rollback();
                return execRtn;
            }
            execRtn = pstmt.executeUpdate();
            if (execRtn < 1){
                log.error("DBUtils.executeUpdate No Reflect Row \n"+query);
                con.rollback();
            }else{
                con.commit();
            }
            pstmt.close();
        } catch(SQLException se) {
            try{ con.rollback(); } catch(Exception e) { log.error("[DBUtils.executeUpdate] RollBack Exception:"+e); }
            log.error("[DBUtils.executeUpdate] SQLException:"+se);
        } catch(Exception e) {
            try{ con.rollback(); } catch(Exception ee) { log.error("[DBUtils.executeUpdate] RollBack Exception:"+e); }
            log.error("[DBUtils.executeUpdate] Exception:"+e+" \n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, null);
            } catch(Exception s) {
                 log.error("[DBUtils.executeUpdate] finally Exception : "+s);
            }
        }
        return execRtn;
    }

    public static int executeQueryI(String poolName, String query) throws StoreException{

        Connection con  = null;
        Statement  stmt = null;
        ResultSet  rset = null;
        int        Rint = 0;

        try{
            con  = getConnection(poolName);
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset!=null && rset.next()){
                Rint = rset.getInt(1);
            }
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryI] SQLException:"+se+" \n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryI] SQLException:"+e+" \n"+query);
        } finally {
            try{
                freeConnection(con, stmt, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryI] Connection Recover Exception:"+s);
            }
        }
        return Rint;
    }

    public static int executeQueryI(String poolName, String query, Object column_data[]) throws StoreException{

        Connection        con   = null;
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        int               Rint  = 0;

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement(query);
            pstmt = setPstmtValues(pstmt, column_data);
            if(pstmt == null) { return Rint; }
            rset = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                Rint = rset.getInt(1);
            }
            pstmt.close();
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryI] SQLException:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryI] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryI] Connection Recover Exception:"+s);
            }
        }
        return Rint;
    }

    public static int executeQueryI(ResultSet rset, String query) throws StoreException {

        int result = 0;

        try{
            if(rset!=null && rset.next()) {
                result = rset.getInt(1);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryI] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryI] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryI] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static long executeQueryL(String poolName, String query) throws StoreException {
        Connection  con     = null;
        Statement   stmt    = null;
        ResultSet   rset    = null;
        long        result  = 0;

        try{
            con  = getConnection(poolName);
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset!=null && rset.next()){
                result = rset.getLong(1);
            }
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryL] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryL] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, stmt, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryL] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static long executeQueryL(String poolName, String query, Object column_data[]) throws StoreException{

        Connection        con   = null;
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        long              Rlong = 0;

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement(query);
            pstmt = setPstmtValues(pstmt, column_data);
            if(pstmt == null) { return Rlong; }
            rset = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                Rlong = rset.getLong(1);
            }
            pstmt.close();
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryL] SQLException:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryL] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryL] Connection Recover Exception:"+s);
            }
        }
        return Rlong;
    }

    public static long executeQueryL(String query, ResultSet rset) throws StoreException {

        long result = 0;

        try{
            if(rset!=null && rset.next()) {
                result = rset.getLong(1);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryL] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryL] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryL] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static String executeQueryS(String poolName, String query) throws StoreException {
        Connection  con     = null;
        Statement   stmt    = null;
        ResultSet   rset    = null;
        String      result  = null;

        try{
            con  = getConnection(poolName);
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset!=null && rset.next()){
                result = rset.getString(1);
            }
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryS] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryS] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, stmt, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryS] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static String executeQueryS(String poolName, String query, Object column_data[]) throws StoreException {
        Connection        con    = null;
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            result = null;

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement(query);
            pstmt = setPstmtValues(pstmt, column_data);
            if(pstmt == null) { return result; }
            rset = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                result = rset.getString(1);
            }
            pstmt.close();
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryS] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryS] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryS] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

     public static String executeQueryS(ResultSet rset, String query) throws StoreException {
        String result = "";

        try{
            if(rset!=null && rset.next()) {
                result = rset.getString(1);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryS] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryS] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryS] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static HashMap executeQueryHM(String poolName, String query) throws StoreException {

        Connection          con      = null;
        Statement           stmt     = null;
        ResultSet           rset     = null;
        ResultSetMetaData   meta     = null;
        HashMap             hmSheet  = new HashMap();
        String              dbString = "";
        StringBuffer        sBuf     = null;
        Reader              in       = null;
        int                 r_code   = 0;

        try {
            con  = getConnection(poolName);
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            meta = rset.getMetaData();

            while (rset!=null && rset.next()){
                for (int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }

                    if (dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString.trim());
                    } else {
                        hmSheet.put(meta.getColumnName(i),"");
                    }
                }
            }
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryHM] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryHM] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, stmt, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryHM] Connection Recover Exception:"+s);
            }
        }
        return hmSheet;
    }

    public static HashMap executeQueryHM(String poolName, String query, Object column_data[]) throws StoreException {

        Connection          con     = null;
        PreparedStatement   pstmt   = null;
        ResultSet           rset    = null;
        ResultSetMetaData   meta    = null;
        HashMap             hmSheet = new HashMap();

        String              dbString = "";
        StringBuffer        sBuf     = new StringBuffer();
        Reader              in       = null;
        int                 r_code   = 0;

        try {
            con = getConnection(poolName);
            pstmt = con.prepareStatement(query);
            pstmt = setPstmtValues(pstmt, column_data);
            if(pstmt == null) { return hmSheet; }
            rset = pstmt.executeQuery();
            meta = rset.getMetaData();

            while (rset!=null && rset.next()){
                for (int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }
                    if (dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString.trim());
                    } else {
                        hmSheet.put(meta.getColumnName(i),"");
                    }
                }
            }
            pstmt.close();
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryHM] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryHM] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryHM] Connection Recover Exception:"+s);
            }
        }
        return hmSheet;
    }

     public static HashMap executeQueryHM(ResultSet rset, String query) throws StoreException {

        ResultSetMetaData   meta     = null;
        HashMap             hmSheet  = new HashMap();
        String              dbString = "";
        StringBuffer        sBuf     = null;
        Reader              in       = null;
        int                 r_code   = 0;

        try{
            meta = rset.getMetaData();
            while(rset!=null && rset.next()) {
                for(int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }
                    if(dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(),
                                dbString.trim());
                    } else hmSheet.put(meta.getColumnName(i),"");
                }
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryHM] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryHM] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryHM] Connection Recover Exception:"+s);
            }
        }
        return hmSheet;
    }

    public static ArrayList executeQueryAL(String poolName, String query) throws StoreException{

        Connection        con  = null;
        Statement         stmt = null;
        ResultSet         rset = null;
        ResultSetMetaData meta = null;
        HashMap      hmSheet   = null;
        ArrayList    alSheet   = new ArrayList();
        String       dbString  = "";
        StringBuffer sBuf      = null;
        Reader       in        = null;
        int          r_code    = 0;

        try{
            con  = getConnection(poolName);
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            meta = rset.getMetaData();

            while (rset!=null && rset.next()){
                hmSheet=new HashMap();
                for (int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }
                    if (dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString.trim());
                    } else {
                        hmSheet.put(meta.getColumnName(i),"");
                    }
                }
                if (hmSheet!=null) alSheet.add(hmSheet);
            }
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryAL] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryAL] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, stmt, null, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryAL] Connection Recover Exception:"+s);
            }
        }

        return alSheet;
    }

    public static ArrayList executeQueryAL(String poolName, String query, Object column_data[]) throws StoreException{

        Connection          con         = null;
        PreparedStatement   pstmt       = null;
        ResultSet           rset        = null;
        ResultSetMetaData   meta        = null;
        HashMap             hmSheet     = null;
        ArrayList           alSheet     = new ArrayList();
        String              dbString    = "";
        StringBuffer        sBuf        = null;
        Reader              in          = null;
        int                 r_code      = 0;

        try{
            con     = getConnection(poolName);
            pstmt   = con.prepareStatement(query);
            pstmt   = setPstmtValues(pstmt, column_data);
            if(pstmt == null) { return alSheet; }
            rset = pstmt.executeQuery();
            meta = rset.getMetaData();

            while (rset!=null && rset.next()){
                hmSheet = new HashMap();
                for (int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }

                    if (dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(), dbString.trim());
                    } else {
                        hmSheet.put(meta.getColumnName(i),"");
                    }
                }
                if (hmSheet!=null) alSheet.add(hmSheet);
            }
            pstmt.close();
        } catch(SQLException se) {
            log.error("[DBUtils.executeQueryAL] Exception:"+se+"\n"+query);
        } catch(Exception e) {
            log.error("[DBUtils.executeQueryAL] Exception:"+e+"\n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.executeQueryAL] Connection Recover Exception:"+s);
            }
        }
        return alSheet;
    }

     public static ArrayList executeQueryAL(ResultSet rset, String query) throws StoreException {

        ResultSetMetaData meta = null;
        HashMap      hmSheet  = null;
        ArrayList    alSheet  = new ArrayList();
        String       dbString = "";
        StringBuffer sBuf     = null;
        Reader       in       = null;
        int          r_code   = 0;

        try{
            meta = rset.getMetaData();
            while(rset!=null && rset.next()) {
                hmSheet=new HashMap();
                for(int i=1; i <= meta.getColumnCount(); i++){
                    dbString = "";
                    if(meta.getColumnTypeName(i).equals("-1")){
                        in = rset.getCharacterStream(meta.getColumnName(i).toUpperCase());
                        r_code = 0;
                        sBuf = new StringBuffer();
                        while ((r_code = in.read()) != -1){
                            sBuf.append((char)r_code);
                        }
                        dbString = sBuf.toString();
                    }else{
                        dbString = rset.getString(meta.getColumnName(i));
                    }
                    if(dbString!=null){
                        hmSheet.put(meta.getColumnName(i).toUpperCase(),
                                dbString.trim());
                    } else hmSheet.put(meta.getColumnName(i),"");
                }
                if(hmSheet!=null) alSheet.add(hmSheet);
            }
        } catch(SQLException se){
            log.error("[DBUtils.executeQueryAL] Exception:"+se+"\n"+query);
        } catch(Exception e){
            log.error("[DBUtils.executeQueryAL] Exception:"+e+"\n"+query);
        } finally {
            try {
                freeConnection(null, null, null, null, null, rset);
            } catch (Exception s){
                log.error("[DBUtils.executeQueryAL] Connection Recover Exception:"+s);
            }
        }
        return alSheet;
    }

    public static String realDate(String poolName, String type) throws StoreException {
        Connection        con    = null;
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            result = "";

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement("SELECT TO_CHAR(SYSDATE,?) FROM DUAL");
            pstmt.setString(1,type);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) result = rset.getString(1);

        } catch(SQLException se) {
            result = "";
            log.error("[DBUtils.realDate] SQLException:"+se);
        } catch(Exception e) {
            result = "";
            log.error("[DBUtils.realDate] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.realDate] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static String maxNo(String poolName, String Table, String Column, String Len) throws StoreException {
        Connection        con    = null;
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            result = "";

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement("SELECT LPAD(NVL(MAX(?)+1, '1'), ?, '0') FROM " + Table);
            pstmt.setString(1,Column);
            pstmt.setString(2,Len);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) result = rset.getString(1);

        } catch(SQLException se) {
            result = "";
            log.error("[DBUtils.realDate] SQLException:"+se);
        } catch(Exception e) {
            result = "";
            log.error("[DBUtils.realDate] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.realDate] Connection Recover Exception:"+s);
            }
        }
        return result;
    }

    public static String getMsg(String poolName, String msgCode) throws StoreException {
        Connection        con    = null;
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            result = "";

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement("SELECT A.MSG_TEXT FROM TMESSAGE A WHERE A.MSG_CODE = ?");
            pstmt.setString(1,msgCode);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) result = rset.getString(1);

        } catch(SQLException se) {
            result = "Sorry, Web Service Instability!! ";
            log.error("[DBUtils.realDate] SQLException:"+se);
        } catch(Exception e) {
            result = "Sorry, Web Service Instability!! ";
            log.error("[DBUtils.realDate] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.realDate] Connection Recover Exception:"+s);
            }
        }
        return result;
    }


    public static HashMap AfterDate(String poolName, int n, String type) throws StoreException {
        Connection        con     = null;
        PreparedStatement pstmt   = null;
        ResultSet         rset    = null;
        String            result  = "";
        HashMap           hmSheet = null;

        try{
            con   = getConnection(poolName);
            pstmt = con.prepareStatement("SELECT TO_CHAR(SYSDATE,?), TO_CHAR(SYSDATE+?,?) FROM DUAL");
            pstmt.setString(1,type);
            pstmt.setInt   (2,n);
            pstmt.setString(3,type);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                hmSheet = new HashMap();
                hmSheet.put("CURRENTDATE", rset.getString(1));
                hmSheet.put("AFTERDATE"  , rset.getString(2));
            }
        } catch(SQLException se) {
            hmSheet = null;
            log.error("[DBUtils.AfterDate] SQLException:"+se);
        } catch(Exception e) {
            hmSheet = null;
            log.error("[DBUtils.AfterDate] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.AfterDate] Connection Recover Exception:"+s);
            }
        }
        return hmSheet;
    }

    public static String queryDateType(String strFormat) throws StoreException {
        StringBuffer query = new StringBuffer();
        query.append(" SELECT DECODE(TO_NUMBER(TO_CHAR(SYSDATE, ?)), \n");
        query.append("        1,'SUN', 2,'MON', 3,'TUE', \n");
        query.append("        4,'WED', 5,'THU', 6,'FRI', 7,'SAT') FROM DUAL\n");
        return query.toString();
    }

    public static String querySequenceNo() throws StoreException {
        StringBuffer query = new StringBuffer();
        query.append(" SELECT TO_CHAR(SYSDATE, ?) || LPAD(TO_CHAR(?), ?, '0') AS SEQ_NO ");
        query.append("   FROM DUAL ");
        return query.toString();

    }



    /** dateType : 'D' **/
    public static String sequenceNo(String poolName, String dateType, String strFormat, String table, String count) throws StoreException {
        Connection        con      = null;
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;
        String            result   = "";
        String            seqTable = "";
        StringBuffer      sb = new StringBuffer();

        try{
            con   = getConnection(poolName);
            sb.append(" SELECT DECODE(TO_NUMBER(TO_CHAR(SYSDATE, ?)), \n");
            sb.append("        1,'SUN', 2,'MON', 3,'TUE', \n");
            sb.append("        4,'WED', 5,'THU', 6,'FRI', 7,'SAT') FROM DUAL\n");
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,dateType);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                seqTable = "SEQ_" + table + "_" + rset.getString(1)+".NEXTVAL";
            }else{
                return result;
            }

            rset.close();
            pstmt.close();

            pstmt = con.prepareStatement("SELECT TO_CHAR(SYSDATE, ?) || LPAD(TO_CHAR(?), ?, '0') FROM DUAL ");
            pstmt.setString(1,strFormat);
            pstmt.setString(2,seqTable);
            pstmt.setString(3,count);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
                result = rset.getString(1);
            }else{
                return result;
            }

            rset.close();
            pstmt.close();


        } catch(SQLException se) {
            result = "";
            log.error("[DBUtils.sequenceNo] SQLException:"+se);
        } catch(Exception e) {
            result = "";
            log.error("[DBUtils.sequenceNo] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.sequenceNo] Connection Recover Exception:"+s);
            }
        }
        return result;

    }

    private static String getPostcardNo(Connection con) throws StoreException {

        String            postcardNo = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

            sb.append(" SELECT SEQ_POSTCARD_NO.NEXTVAL \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	postcardNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_OB_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return postcardNo;
    }

    private static String getBeautyNo(Connection con) throws StoreException {

    	String            beautyNo = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

//            pstmt = con.prepareStatement(sb.toString());

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymm') || LTRIM(TO_CHAR(SEQ_BEAUTY_NO.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	beautyNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_BEAUTY_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_BEAUTY_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_BEAUTY_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_BEAUTY_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return beautyNo;
    }

    private static String getMagazineNo(Connection con) throws StoreException {

    	String            magazineNo = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

//            pstmt = con.prepareStatement(sb.toString());

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymm') || LTRIM(TO_CHAR(SEQ_MAGAZINE_NO.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	magazineNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_MAGAZINE_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_MAGAZINE_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_MAGAZINE_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_MAGAZINE_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return magazineNo;
    }

    private static String getRackMoveNo(Connection con) throws StoreException {

    	String            RackMoveNo = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

//            pstmt = con.prepareStatement(sb.toString());

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_RACK_MOVE_NO.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	RackMoveNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_RACK_MOVE_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_RACK_MOVE_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_RACK_MOVE_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_RACK_MOVE_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return RackMoveNo;
    }

    private static String getSegmentCode(Connection con) throws StoreException {

    	String            RackMoveNo = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{

//            pstmt = con.prepareStatement(sb.toString());

            sb.append(" SELECT TO_CHAR(SYSDATE, 'yyyymmdd') || LTRIM(TO_CHAR(SEQ_SEGMENT_CODE.NEXTVAL,'0000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	RackMoveNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_SEGMENT_CODE");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_SEGMENT_CODE")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_SEGMENT_CODE")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "SEQ_SEGMENT_CODE")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return RackMoveNo;
    }

    private static String getEventNo(Connection con) throws StoreException {
        String            eventNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_EVENT_NO.NEXTVAL, '0000000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	eventNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EVENT_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EVENT_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "EVENT_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "EVENT_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return eventNo;
    }

    private static String getApplySeq(Connection con) throws StoreException {
        String            applySeq  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_APPLY_SEQ.NEXTVAL, '0000000000')) \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	applySeq = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "APPLY_SEQ");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "APPLY_SEQ")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "APPLY_SEQ")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "APPLY_SEQ")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return applySeq;
    }

    /** TCODE 의 정보 얻기 **/
    public static ArrayList findTCode(String poolName, String codeLgroup) throws StoreException {
        Connection        con     = null;
        PreparedStatement pstmt   = null;
        ResultSet         rset    = null;
        StringBuffer      query   = new StringBuffer();
        ArrayList         alSheet = null;

        try{
            con   = getConnection(poolName);
            query.append(" SELECT CODE_LGROUP,CODE_MGROUP,CODE_NAME,CODE_SNAME, \n");
            query.append("        CODE_GROUP,REMARK1,REMARK2 \n");
            query.append("   FROM TCODE \n");
            query.append("  WHERE CODE_LGROUP = ? \n");
            query.append("    AND USE_YN='1' \n");
            query.append("  ORDER BY CODE_MGROUP ASC \n");
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1,codeLgroup);
            rset  = pstmt.executeQuery();
            alSheet = executeQueryAL(rset, query.toString());

        } catch(SQLException se) {
            alSheet = null;
            log.error("[DBUtils.findTCode] SQLException:"+se);
        } catch(Exception e) {
            alSheet = null;
            log.error("[DBUtils.findTCode] Exception:"+e);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, rset);
            } catch (Exception s) {
                log.error("[DBUtils.findTCode] Connection Recover Exception:"+s);
            }
        }
        return alSheet;
    }

    public static int insertTUms(String poolName, String umsFlag, String orderNo, String custNo, String slipINo, String receiptNo,
                                 String repayNo,  String remark1, String remark2 ) throws StoreException {

        int               execRtn = 0;
        Connection        con     = null;
        PreparedStatement pstmt   = null;
        StringBuffer      query   = new StringBuffer();

        try{
            con   = getConnection(poolName);
            query.append(" INSERT INTO TUMS( UMS_NO,   UMS_FLAG,  ORDER_NO, \n");
            query.append("                   CUST_NO,  SLIP_I_NO, RECEIPT_NO, \n");
            query.append("                   REPAY_NO, REMARK1,   REMARK2,    INSERT_DATE) \n");
            query.append("          VALUES ( TO_CHAR(SYSDATE, 'YYYYMMDD') || LPAD(TO_CHAR(SEQ_UMS_NO.NEXTVAL), 6, '0'), ?, ?, \n");
            query.append("                   ?, ?, ?, \n");
            query.append("                   ?, ?, ?, SYSDATE) ");
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1,umsFlag);
            pstmt.setString(2,orderNo);
            pstmt.setString(3,custNo);
            pstmt.setString(4,slipINo);
            pstmt.setString(5,receiptNo);
            pstmt.setString(6,repayNo);
            pstmt.setString(7,remark1);
            pstmt.setString(8,remark2);

            execRtn = pstmt.executeUpdate();

            if (execRtn < 1){
                log.error("DBUtils.insertTUms No Reflect Row \n"+query);
                con.rollback();
            }else{
                con.commit();
            }
            pstmt.close();
        } catch(SQLException se) {
            try{
                con.rollback();
            } catch(Exception e) {
                log.error("[DBUtils.insertTUms] RollBack Exception:"+e);
            }
            log.error("[DBUtils.insertTUms] SQLException:"+se);
        } catch(Exception e) {
            log.error("[DBUtils.insertTUms] Exception:"+e+" \n"+query);
        } finally {
            try{
                freeConnection(con, null, pstmt, null, null, null);
            } catch(Exception s) {
                 log.error("[DBUtils.insertTUms] finally Exception : "+s);
            }
        }
        return execRtn;
    }

    public static int insertTlogin(Connection con, String user_id, String ip_addr, String action, String content ) throws StoreException {

    	int               execRtn = 0;
    	PreparedStatement pstmt   = null;
        ResultSet         rset    = null;
    	StringBuffer      query   = new StringBuffer();

    	try{
    		query.append(" INSERT INTO TLOGIN( USER_ID,  ACTION_DATE,  IP_ADDR,   \n");
    		query.append("                     ACTION,   CONTENT)   \n");
			query.append("             VALUES( ?,        SYSDATE,      ?,   \n");
			query.append("                     ?,        ?)   ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,user_id);
			pstmt.setString(2,ip_addr);
			pstmt.setString(3,action);
			pstmt.setString(4,content);

			execRtn = pstmt.executeUpdate();

			if (execRtn < 1){
				log.error("DBUtils.insertTlogin No Reflect Row \n"+query);
				con.rollback();
			}else{
				con.commit();
			}
			pstmt.close();

        }catch(SQLException se){
        	logTemp = "DBUtils.insertTlogin SQLException : ERR-"+se.getErrorCode()+":"+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        }catch(Exception e){
        	logTemp = "DBUtils.insertTlogin Exception : ERR-"+e.getMessage();
            log.error(logTemp);
        	throw new StoreException(logTemp);
        }finally {
            freeConnection(null, pstmt, rset);
        }

    	return execRtn;
    }

    private static String getPlanclassCode(Connection con) throws StoreException {
        String            planclass_code = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT LTRIM(TO_CHAR(SEQ_PLANCLASS_CODE.NEXTVAL, '00000000'))  \n");
            sb.append("   FROM DUAL  \n");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
            	planclass_code = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PLANCLASS_CODE");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PLANCLASS_CODE")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PLANCLASS_CODE")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PLANCLASS_CODE")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return planclass_code;
    }

    private static String getPlanCode(Connection con) throws StoreException {
        String            plan_code = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb = new StringBuffer();
            sb.append(" SELECT LTRIM(TO_CHAR(SYSDATE,'YYYYMM'))|| LTRIM(TO_CHAR(SEQ_PLAN_CODE.NEXTVAL, '0000'))  \n");
            sb.append("   FROM DUAL  \n");

            pstmt = con.prepareStatement(sb.toString());

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()){
            	plan_code = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PLAN_CODE");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PLAN_CODE")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PLAN_CODE")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PLAN_CODE")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return plan_code;
    }

    private static String getPrdtnNo(Connection con) throws StoreException {
        String            seqNo  = "";
        StringBuffer      sb       = new StringBuffer();
        PreparedStatement pstmt    = null;
        ResultSet         rset     = null;

        try{
            sb.append(" SELECT LPAD(PRDTN_NO.NEXTVAL, 10, '0') \n");
            sb.append("   FROM DUAL     \n");

            pstmt = con.prepareStatement(sb.toString());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
            	seqNo = rset.getString(1);
            }else{
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PRDTN_NO");
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }

            rset.close();
            pstmt.close();

        } catch(SQLException se) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PRDTN_NO")+se;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } catch(Exception e) {
        	logTemp = ComUtils.getMessage("msg.cannot_create", "PRDTN_NO")+e;
            log.error(logTemp);
        	throw new StoreException(logTemp);
        } finally {
            try{
                freeConnection(null, pstmt, rset);
            } catch (Exception s) {
            	logTemp = ComUtils.getMessage("msg.cannot_create", "PRDTN_NO")+s;
                log.error(logTemp);
            	throw new StoreException(logTemp);
            }
        }
        return seqNo;
    }
}