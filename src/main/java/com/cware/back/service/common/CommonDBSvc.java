
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.Message;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tcardlog;

/**
 * CommonDB Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class CommonDBSvc {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    public CommonDBSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; area_gb
    * </PRE>
    * @param   String
    * @param   String
    * @return  String
    */
    private String makeSqlAreaGb( String post_no, String post_seq ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT AREA_GB \n ");
        sb.append("     FROM TPOST \n ");
        sb.append("   WHERE POST_NO  = ? \n ");
        sb.append("     AND POST_SEQ = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : area_gb 얻기
    * </PRE>
    * @param   poolName
    * @param   String
    * @param   String
    * @return  String
    */
   public String retrieveAreaGb ( Connection con, String post_no, String post_seq) throws StoreException{
        PreparedStatement pstmt   = null;
        ResultSet         rset    = null;
        String            area_gb = "";

        try {
            pstmt = con.prepareStatement(makeSqlAreaGb(post_no, post_seq));

            int index = 1;
            pstmt.setString(index++,post_no);
            pstmt.setString(index++,post_seq);

            while (rset!=null && rset.next()){
                area_gb = rset.getString(1);
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return area_gb;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; delyNoFlag
    * </PRE>
    * @param   String
    * @param   String
    * @return  String
    */
    private String makeSqlDelyNoFlag( String goods_code, String area_gb ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT 1 \n ");
        sb.append("     FROM DUAL \n ");
        sb.append("   WHERE EXISTS \n ");
        sb.append("   (SELECT GOODS_CODE \n ");
        sb.append("        FROM TDELYNOAREA \n ");
        sb.append("      WHERE GOODS_CODE = ? \n ");
        sb.append("        AND AREA_GB    = ? )  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : area_gb 얻기
    * </PRE>
    * @param   poolName
    * @param   String
    * @param   String
    * @return  String
    */
   public String retrieveDelyNoFlag ( Connection con, String goods_code, String area_gb) throws StoreException{
        PreparedStatement pstmt      = null;
        ResultSet         rset       = null;
        String            delyNoFlag = "0";

        try {
            pstmt = con.prepareStatement(makeSqlDelyNoFlag(goods_code, area_gb));

            int index = 1;
            pstmt.setString(index++,goods_code);
            pstmt.setString(index++,area_gb);

            while (rset!=null && rset.next()){
                delyNoFlag = rset.getString(1);
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return delyNoFlag;
    }



    /**
    * <PRE>
    * Desc : Make SQL ; resident_no
    * </PRE>
    * @return  String
    */
    private String makeSqlResidentNo() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  COUNT(CUST_NO)  \n ");
        sb.append("   FROM  TCUSTOMER  \n ");
        sb.append("  WHERE  RESIDENT_NO = CWARE_ENC_DEC(?, 'e') \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : resident_no 조회
    * </PRE>
    * @param   poolName
    * @param   String
    * @return  int
    */
   public int retrieveResidentNo ( Connection con, String resident_no) throws StoreException{
        PreparedStatement pstmt   = null;
        ResultSet         rset    = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlResidentNo());

            int index = 1;
            pstmt.setString(index++, resident_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                executedRtn = rset.getInt(1);
            } else {
                executedRtn = 0;
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ; card_log_no
    * </PRE>
    * @return  String
    */
    private String makeSqlCardLogNo() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT SEQ_CARD_LOG_NO.NEXTVAL  \n ");
        sb.append("     FROM DUAL \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : card_log_no 조회
    * </PRE>
    * @param   poolName
    * @param   String
    * @return  int
    */
   public long retrieveCardLogNo ( Connection con ) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        long              card_log_no = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCardLogNo());

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                card_log_no = rset.getLong(1);
            } else {
                card_log_no = 0;
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            return 0;
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            return 0;
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return card_log_no;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; insert tcardlog
    * </PRE>
    * @return  String
    */
    private String makeSqlInserttcardlog() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TCARDLOG  \n ");
        sb.append(" 			  (CARD_LOG_NO,  \n ");
        sb.append(" 			  CARD_NO,  \n ");
        sb.append(" 			  CVV,  \n ");
        sb.append(" 			  VAN_CODE,  \n ");
        sb.append(" 			  CARD_CODE,  \n ");
        sb.append(" 			  VALID_DATE,  \n ");
        sb.append(" 			  PAY_MONTH,  \n ");
        sb.append(" 			  QUEST_AMT,  \n ");
        sb.append(" 			  POS_ID,  \n ");
        sb.append(" 			  CUST_NO,  \n ");
        sb.append(" 			  ORDER_NO,  \n ");
        sb.append(" 			  RECEIPT_NO,  \n ");
        sb.append(" 			  TRANS_TYPE,  \n ");
        sb.append(" 			  APPROVE_CODE,  \n ");
        sb.append(" 			  TRANS_NO,  \n ");
        sb.append(" 			  AUTH_RRPID,  \n ");
        sb.append(" 			  TERM_SEQ,  \n ");
        sb.append(" 			  INSERT_DATE,  \n ");
        sb.append(" 			  INSERT_ID,  \n ");
        sb.append(" 			  SAVE_YN,  \n ");
        sb.append(" 			  EVENT_NAME,  \n ");
        sb.append(" 			  RESPONSE_CODE,  \n ");
        sb.append(" 			  RESPONSE_MSG,  \n ");
        sb.append(" 			  PROTX_VENDORTXCODE,  \n ");
        sb.append(" 			  PROTX_STATUS,  \n ");
        sb.append(" 			  PROTX_STATUSDETAIL,  \n ");
        sb.append(" 			  PROTX_VPSTXID,  \n ");
        sb.append(" 			  PROTX_SECURITYKEY,  \n ");
        sb.append(" 			  PROTX_TXAUTHNO ,   \n ");
        sb.append(" 			  PROTX_RELATED_VPSTXID,  \n ");
        sb.append(" 			  PROTX_RELATED_VENDORTXCODE,  \n ");
        sb.append(" 			  PROTX_RELATED_SECURITYKEY,  \n ");
        sb.append(" 			  PROTX_RELATED_TXAUTHNO,  \n ");
        sb.append(" 			  ISSUE_NUMBER )  \n ");
        sb.append(" 	VALUES (  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  SYSDATE,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  '0',  \n ");
        sb.append(" 			  SUBSTRB(?, 1, 100),  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  SUBSTRB(?, 1, 100),  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?,  \n ");
        sb.append(" 			  ?) \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : insert tcardlog
    * </PRE>
    * @param   poolName
    * @param   Tcardlog
    * @return  int
    */
   public int insertTcardlog ( Connection con, Tcardlog tcardlog) throws StoreException{
        PreparedStatement pstmt   = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInserttcardlog());

            int index = 1;
            pstmt.setLong  (index++, tcardlog.getCard_log_no());
            pstmt.setString(index++, tcardlog.getCard_no());
            pstmt.setString(index++, tcardlog.getCvv());
            pstmt.setString(index++, tcardlog.getVan_code());
            pstmt.setString(index++, tcardlog.getCard_code());
            pstmt.setString(index++, tcardlog.getValid_date());
            pstmt.setLong  (index++, tcardlog.getPay_month());
            pstmt.setDouble(index++, tcardlog.getQuest_amt());
            pstmt.setString(index++, tcardlog.getPos_id());
            pstmt.setString(index++, tcardlog.getCust_no());
            pstmt.setString(index++, tcardlog.getOrder_no());
            pstmt.setString(index++, tcardlog.getReceipt_no());
            pstmt.setString(index++, tcardlog.getTrans_type());
            pstmt.setString(index++, tcardlog.getApprove_code());
            pstmt.setString(index++, tcardlog.getTrans_no());
            pstmt.setString(index++, tcardlog.getAuth_rrpid());
            pstmt.setString(index++, tcardlog.getTerm_seq());
            pstmt.setString(index++, tcardlog.getInsert_id());
            pstmt.setString(index++, tcardlog.getEvent_name());
            pstmt.setString(index++, tcardlog.getResponse_code());
            pstmt.setString(index++, tcardlog.getResponse_msg());

            pstmt.setString(index++, tcardlog.getProtx_vendortxcode());
            pstmt.setString(index++, tcardlog.getProtx_status());
            pstmt.setString(index++, tcardlog.getProtx_statusdetail());
            pstmt.setString(index++, tcardlog.getProtx_vpstxid());
            pstmt.setString(index++, tcardlog.getProtx_securitykey());
            pstmt.setString(index++, tcardlog.getProtx_txauthno());

            pstmt.setString(index++, tcardlog.getProtx_related_vpstxid());
            pstmt.setString(index++, tcardlog.getProtx_related_vendortxcode());
            pstmt.setString(index++, tcardlog.getProtx_related_securitykey());
            pstmt.setString(index++, tcardlog.getProtx_related_txauthno());

            pstmt.setString(index++, tcardlog.getIssue_number());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcardlog.getCard_log_no()    ); logString.append( "/" );
            logString.append( tcardlog.getCard_no()        ); logString.append( "/" );
            logString.append( tcardlog.getCvv()            ); logString.append( "/" );
            logString.append( tcardlog.getVan_code()       ); logString.append( "/" );
            logString.append( tcardlog.getCard_code()      ); logString.append( "/" );
            logString.append( tcardlog.getValid_date()     ); logString.append( "/" );
            logString.append( tcardlog.getPay_month()      ); logString.append( "/" );
            logString.append( tcardlog.getQuest_amt()      ); logString.append( "/" );
            logString.append( tcardlog.getPos_id()         ); logString.append( "/" );
            logString.append( tcardlog.getCust_no()        ); logString.append( "/" );
            logString.append( tcardlog.getOrder_no()       ); logString.append( "/" );
            logString.append( tcardlog.getReceipt_no()     ); logString.append( "/" );
            logString.append( tcardlog.getTrans_type()     ); logString.append( "/" );
            logString.append( tcardlog.getApprove_code()   ); logString.append( "/" );
            logString.append( tcardlog.getTrans_no()       ); logString.append( "/" );
            logString.append( tcardlog.getAuth_rrpid()     ); logString.append( "/" );
            logString.append( tcardlog.getTerm_seq()       ); logString.append( "/" );
            logString.append( tcardlog.getInsert_id()      ); logString.append( "/" );
            logString.append( tcardlog.getEvent_name()     ); logString.append( "/" );
            logString.append( tcardlog.getResponse_code()  ); logString.append( "/" );
            logString.append( tcardlog.getResponse_msg()   ); logString.append( "/" );

            logString.append( tcardlog.getProtx_vendortxcode()  ); logString.append( "/" );
            logString.append( tcardlog.getProtx_status()       ); logString.append( "/" );
            logString.append( tcardlog.getProtx_statusdetail() ); logString.append( "/" );
            logString.append( tcardlog.getProtx_vpstxid()     ); logString.append( "/" );
            logString.append( tcardlog.getProtx_securitykey() ); logString.append( "/" );
            logString.append( tcardlog.getProtx_txauthno()      ); logString.append( "/" );

            logString.append( tcardlog.getProtx_related_vpstxid()  ); logString.append( "/" );
            logString.append( tcardlog.getProtx_related_vendortxcode()      ); logString.append( "/" );
            logString.append( tcardlog.getProtx_related_securitykey() ); logString.append( "/" );
            logString.append( tcardlog.getProtx_related_txauthno()     ); logString.append( "/" );

            logString.append( tcardlog.getIssue_number()     ); logString.append( "/" );

            log.info(logString.toString());

            executedRtn  = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            return 0;
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            return 0;
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ; delete tcounsel
    * </PRE>
    * @return  String
    */
    private String makeSqlDeleteTcounsel() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM TCOUNSEL \n ");
        sb.append("       WHERE ORDER_NO     = ? \n ");
        sb.append("         AND ORDER_G_SEQ  LIKE ? \n ");
        sb.append("         AND ORDER_D_SEQ  LIKE ? \n ");
        sb.append("         AND ORDER_W_SEQ  LIKE ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : delete tcounsel
    * </PRE>
    * @param   poolName
    * @param   Tcardlog
    * @return  int
    */
   public int deleteTcounsel ( Connection con, String order_no, String order_g_seq, String order_d_seq, String order_w_seq) throws StoreException{
        int               executedRtn = 0;
        PreparedStatement pstmt       = null;

        try {

            pstmt = con.prepareStatement(makeSqlDeleteTcounsel());

            int index = 1;
            pstmt.setString(index++, order_no);
            pstmt.setString(index++, order_g_seq);
            pstmt.setString(index++, order_d_seq);
            pstmt.setString(index++, order_w_seq);

            executedRtn  = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            return -1;
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            return -1;
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; delete tpromocounsel
    * </PRE>
    * @return  String
    */
    private String makeSqlDeleteTpromocounsel() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM TPROMOCOUNSEL \n ");
        sb.append("       WHERE ORDER_NO     = ? \n ");
        sb.append("         AND ORDER_G_SEQ  LIKE ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : delete tpromocounsel
    * </PRE>
    * @param   poolName
    * @param   Tcardlog
    * @return  int
    */
   public int deleteTpromocounsel ( Connection con, String order_no, String order_g_seq) throws StoreException{
        int               executedRtn = 0;
        PreparedStatement pstmt       = null;

        try {

            pstmt = con.prepareStatement(makeSqlDeleteTpromocounsel());

            int index = 1;
            pstmt.setString(index++, order_no);
            pstmt.setString(index++, order_g_seq);

            executedRtn  = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            return -1;
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            return -1;
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; get Month by card_code
    * </PRE>
    * @return  String
    */
    private String makeSqlInsMonth() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT MONTH \n ");
        sb.append("     FROM TCARDDT \n ");
        sb.append("   WHERE CARD_CODE = ? \n ");
        sb.append("     AND PROC_DATE < SYSDATE  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : get Month by card_code
    * </PRE>
    * @param   poolName
    * @param   Tcardlog
    * @return  int
    */
   public ArrayList retrieveInsMonth ( Connection con, String card_code) throws StoreException{
        ArrayList         month       = null;
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {

            month = new ArrayList();

            pstmt = con.prepareStatement(makeSqlInsMonth());

            pstmt.setString(1, card_code);

            rset  = pstmt.executeQuery();

            while (rset!=null && rset.next()) {
                month.add(rset.getString(1));
            }


        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            month = null;
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            month = null;
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return month;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; Select dc rate of user
    * </PRE>
    * @return  String
    */
    private String makeSqlMaxDcRate() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT MAX_DC_RATE \n ");
        sb.append("   FROM TUSER \n ");
        sb.append("  WHERE USER_ID = ?  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Select dc rate of user
    * </PRE>
    * @param   poolName
    * @param   Tcardlog
    * @return  int
    */
   public double retrieveMaxDcRate ( Connection con, String user_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        double            max_dc_rate = 0;

        try {

            pstmt = con.prepareStatement(makeSqlMaxDcRate());

            pstmt.setString(1, user_id);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                max_dc_rate = rset.getDouble(1);
            }


        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return max_dc_rate;
    }


    /**
    * <PRE>
    * Desc : Make SQL ; MultiName MediaName
    * </PRE>
    * @return  String
    */
    private String makeSqlMultiNameMediaName(Message msg) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.MEDIA_NAME, \n ");
        sb.append(" 		 A.MEDIA_GB \n ");
        sb.append("   FROM TMEDIA A  \n ");
        sb.append("  WHERE A.USE_YN = '1' \n ");
        sb.append(" 	AND A.MEDIA_CODE = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : MultiName MediaName
    * </PRE>
    * @param   poolName
    * @param   RetrieveModel
    * @return  Message
    */
   public Message retrieveMultiNameMediaName ( Connection con, Message msg) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {

            pstmt = con.prepareStatement(makeSqlMultiNameMediaName(msg));

            pstmt.setString(1, msg.getString("MEDIA_CODE"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                msg.put("RESULT_MEDIA_NAME", rset.getString(1));
                msg.put("RESULT_MEDIA_GB",   rset.getString(2));
            }


        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return msg;
    }




    /**
    * <PRE>
    * Desc : 예치금 사용순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewDeposituseSeq = "  SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(USE_SEQ)), 0) + 1,'0000000000'))  \n "
                                                   + "    FROM TDEPOSITUSE    \n "
                                                   + "   WHERE CUST_NO = ?  \n "  ;

    private int selectSqlNewDeposituseSeqLog =  0;

    private String makeSqlNewDeposituseSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewDeposituseSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewDeposituseSeq);
            }
            selectSqlNewDeposituseSeqLog = 1;
        }
        return selectSqlNewDeposituseSeq;
    }

    /**
    * <PRE>
    * Desc : 예치금 사용순번 get
    * </PRE>
    * @param
    * @return  String
    */
    public String getNewDeposituseSeq(Connection con, String cust_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            deposituse_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewDeposituseSeq());
            pstmt.setString(1, cust_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) deposituse_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return deposituse_seq;
    }

    /**
    * <PRE>
    * Desc : 예치금 순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewDepositSeq = "  SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(DEPOSITAMT_SEQ)), 0) + 1, '0000000000'))  \n "
                                                   + "    FROM TDEPOSIT    \n "
                                                   + "   WHERE CUST_NO = ?  \n "  ;

    private int selectSqlNewDepositSeqLog =  0;

    private String makeSqlNewDepositSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewDepositSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewDepositSeq);
            }
            selectSqlNewDepositSeqLog = 1;
        }
        return selectSqlNewDepositSeq;
    }

    /**
    * <PRE>
    * Desc : 예치금 순번 get
    * </PRE>
    * @param
    * @return  String
    */
    public String getNewDepositSeq(Connection con, String cust_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;

        String            depositamt_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewDepositSeq());
            pstmt.setString(1, cust_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) depositamt_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return depositamt_seq;
    }



    /**
    * <PRE>
    * Desc : get ORDER_P_SEQ SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewOrderPSeq = " SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(ORDER_P_SEQ)), 0) + 1, '000'))  \n "
                                               + "   FROM TORDERPROC A   \n "
                                               + "  WHERE ORDER_NO    = ?  \n "
                                               + "    AND ORDER_G_SEQ = ?  \n "
                                               + "    AND ORDER_D_SEQ = ?  \n "
                                               + "    AND ORDER_W_SEQ = ?  \n " ;
    private int selectSqlNewOrderPSeqLog =  0;

    private String makeSqlNewOrderPSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewOrderPSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewOrderPSeq);
            }
            selectSqlNewOrderPSeqLog = 1;
        }
        return selectSqlNewOrderPSeq;
    }

    /**
    * <PRE>
    * Desc : get ORDER_P_SEQ
    * </PRE>
    * @param
    * @return  String
    */
    public String getNewOrderPSeq(Connection con, String order_no, String order_g_seq, String order_d_seq, String order_w_seq) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            order_p_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewOrderPSeq());
            int index = 1;
            pstmt.setString(index++, order_no);
            pstmt.setString(index++, order_g_seq);
            pstmt.setString(index++, order_d_seq);
            pstmt.setString(index++, order_w_seq);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) order_p_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return order_p_seq;
    }



    /**
    * <PRE>
    * Desc : 적립금 부여순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewSaveamtSeq = "  SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(SAVEAMT_SEQ)), 0) + 1,'0000000000'))  \n "
                                             + "    FROM TSAVEGET A   \n "
                                             + "   WHERE CUST_NO = ?   \n "  ;
    private int selectSqlNewSaveamtSeqLog =  0;

    private String makeSqlNewSaveamtSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewSaveamtSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewSaveamtSeq);
            }
            selectSqlNewSaveamtSeqLog = 1;
        }
        return selectSqlNewSaveamtSeq;
    }

    /**
    * <PRE>
    * Desc : 적립금 부여순번 get
    * </PRE>
    * @param
    * @return  String
    */
    public String getNewSaveamtSeq(Connection con, String cust_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            saveamt_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewSaveamtSeq());
            pstmt.setString(1, cust_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) saveamt_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return saveamt_seq;
    }





    /**
    * <PRE>
    * Desc : 적립금 사용순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewSaveuseSeq = "  SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(USE_SEQ)), 0) + 1,'0000000000'))  \n "
                                                + "    FROM TSAVEUSE   \n "
                                                + "   WHERE CUST_NO = ?  \n "  ;
    private int selectSqlNewSaveuseSeqLog =  0;

    private String makeSqlNewSaveuseSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewSaveuseSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewSaveuseSeq);
            }
            selectSqlNewSaveuseSeqLog = 1;
        }
        return selectSqlNewSaveuseSeq;
    }

    /**
    * <PRE>
    * Desc : 적립금 사용순번 get
    * </PRE>
    * @param
    * @return  String
    */
    public String getNewSaveuseSeq(Connection con, String cust_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            saveuse_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewSaveuseSeq());
            pstmt.setString(1, cust_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) saveuse_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return saveuse_seq;
    }



    /**
    * <PRE>
    * Desc : 결제수단 사용내역 새순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewSettleSeq = " SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(SETTLE_SEQ)), 0) + 1,'000'))   \n "
                                               + "   FROM TCUSTSETTLE   \n "
                                               + "  WHERE CUST_NO = ?  \n " ;
    private int selectSqlNewSettleSeqLog =  0;

    private String makeSqlNewSettlSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewSettleSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewSettleSeq);
            }
            selectSqlNewSettleSeqLog = 1;
        }
        return selectSqlNewSettleSeq;
    }

    /**
    * <PRE>
    * Desc : 결제수단 사용내역 새순번 get
    * </PRE>
    * @param   String cust_no
    * @return  String
    */
    public String getNewSettlSeq(Connection con, String cust_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            settle_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewSettlSeq());
            pstmt.setString(1,cust_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) settle_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return settle_seq;
    }

    /**
    * <PRE>
    * Desc : 입금순번 새순번 SQL
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNewReceiptSeq = "SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(RECEIPT_SEQ)), 0) + 1, '000'))   \n "
                                                + "  FROM TORDERRECEIPTSPROC A   \n "
                                                + " WHERE RECEIPT_NO = ?  \n "  ;
    private int selectSqlNewReceiptSeqLog =  0;

    private String makeSqlNewReceiptSeq( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNewReceiptSeqLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNewReceiptSeq);
            }
            selectSqlNewReceiptSeqLog = 1;
        }
        return selectSqlNewReceiptSeq;
    }

    /**
    * <PRE>
    * Desc : 입금순번 새순번 get
    * </PRE>
    * @param   String receipt_no
    * @return  String receipt_seq
    */
    public String getNewReceiptSeq(Connection con, String receipt_no) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String            receipt_seq = "";

        try{
            pstmt = con.prepareStatement(makeSqlNewReceiptSeq());
            pstmt.setString(1,receipt_no);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) receipt_seq = rset.getString(1);

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return receipt_seq;
    }



    /**
    * <PRE>
    * Desc : Make SQL ; TCUSTSYSTEM INFORMATION
    * </PRE>
    * @return  String
    */
    private String makeSqlCustSysSheet10(String cust_no) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT A.CUST_NO, \n ");
        sb.append("       A.CUST_WARNING, \n ");
        sb.append("       A.CUST_CHAR, \n ");
        sb.append("       A.USE_DEPOSIT, \n ");
        sb.append("       A.USE_PB_DEPOSIT, \n ");
        sb.append("       A.TOT_ORDER_CNT, \n ");
        sb.append("       A.TOT_ORDER_AMT, \n ");
        sb.append("       A.TOT_CANCEL_CNT, \n ");
        sb.append("       A.TOT_CANCEL_AMT, \n ");
        sb.append("       A.TOT_RETURN_CNT, \n ");
        sb.append("       A.TOT_RETURN_AMT, \n ");
        sb.append("       B.EM_NO, \n ");
        sb.append("       TO_CHAR(A.FIRST_ORDER_DATE, 'yyyy/mm/dd hh24:mi:ss') AS FIRST_ORDER_DATE, \n ");
        sb.append("       A.USE_SAVEAMT, \n "); //= 추후 사용안할 예정 by ojm
        sb.append("       C.GRANT_SAVEAMT, \n ");
        sb.append("       C.RETURN_SAVEAMT, \n ");
        sb.append("       C.USABLE_SAVEAMT, \n ");
        sb.append("       C.TOT_SAVEAMT, \n ");
        sb.append("       A.MEMB_GB, \n ");
        sb.append("       B.SEX \n ");
        sb.append("    FROM TCUSTSYSTEM A, \n ");
        sb.append("  	    TCUSTOMER B, \n ");
        sb.append("  	    VSAVEGET C \n ");
        sb.append("   WHERE A.CUST_NO = ? \n ");
        sb.append("    AND A.CUST_NO = B.CUST_NO \n ");
        sb.append("    AND B.CUST_NO = C.CUST_NO \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : TCUSTSYSTEM INFORMATION
    * </PRE>
    * @param   poolName
    * @param   String
    * @return  HashMap
    */
   public HashMap retrieveCustSysSheet10 ( HashMap hmSheet, Connection con, String cust_no) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {

            pstmt = con.prepareStatement(makeSqlCustSysSheet10(cust_no));

            pstmt.setString(1, cust_no);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                hmSheet = new HashMap();
                hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : "+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return hmSheet;
    }


   /**
   * <PRE>
   * Desc : MyMenu 그룹코드 순번 SQL
   * </PRE>
   * @param
   * @return  String
   */
   private final String selectSqlNewGroupSeq = "  SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(GROUP_CODE)), 0) + 1,'00000'))  \n "
                                                  + "    FROM TFAVORITESM    \n "
                                                  + "   WHERE USER_ID = ?  \n "  ;

   private int selectSqlNewGroupSeqLog =  0;

   private String makeSqlNewGroupSeq( ) throws StoreException{
       //= log SQL -------------------------------------------------
       if (selectSqlNewGroupSeqLog == 0) {
           if (log.isDebugEnabled()) {
               log.debug(selectSqlNewGroupSeq);
           }
           selectSqlNewGroupSeqLog = 1;
       }
       return selectSqlNewGroupSeq;
   }     
 

   /**
   * <PRE>
   * Desc : MyMenu 그룹코드 순번 get
   * </PRE>
   * @param   String user_id
   * @return  String receipt_seq
   */
   public String getNewGroupSeq(Connection con, String user_id) throws StoreException {
       PreparedStatement pstmt  = null;
       ResultSet         rset   = null;
       String            group_code = "";

       try{
           pstmt = con.prepareStatement(makeSqlNewGroupSeq());
           pstmt.setString(1,user_id);
           rset  = pstmt.executeQuery();

           if (rset!=null && rset.next()) group_code = rset.getString(1);

       }catch(SQLException se){
           log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
           throw new StoreException(se.getMessage());
       }catch(Exception e){
           log.error("Exception : ERR-"+e.getMessage());
           throw new StoreException(e.getMessage());
       }finally {
           DBUtils.freeConnection(null, pstmt, rset);
       }
       return group_code;
   }
   
}