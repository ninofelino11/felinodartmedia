package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tuser;

/**
 * User service Bean
 *
 * @version 1.0, 2006/05/08
 * @author kim sungtaek [webzest@commerceware.co.kr]
 */
public class UserSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public UserSvc() {}

    /**
    * <PRE>
    * Desc : Tuser; user information
    * </PRE>
    * @param   poolName                            : Database pool name
    */
    public String makeSqlTuser() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  A.USER_ID, \n");
        sb.append("         A.USER_NAME, \n");
        sb.append("         A.PASSWD, \n");
        sb.append("         A.DEPT_CODE, \n");
        sb.append("         A.SABUN, \n");
        sb.append("         A.START_DATE, \n");
        sb.append("         A.END_DATE, \n");
        sb.append("         A.USER_GB, \n");
        sb.append("         A.MAX_DC_RATE, \n");
        sb.append("         A.RESIDENT_NO, \n");
        sb.append("         A.SEX, \n");
        sb.append("         A.MAJOR_GROUP, \n");
        sb.append("         A.MINOR_GROUP, \n");
        sb.append("         A.ICD_NO, \n");
        sb.append("         A.AGENT_LEVEL, \n");
        sb.append("         A.AGENT_SKILL1, \n");
        sb.append("         A.AGENT_SKILL2, \n");
        sb.append("         A.AGENT_SKILL3, \n");
        sb.append("         A.PBX_LOGIN_ID, \n");
        sb.append("         A.WORKER, \n");
        sb.append("         A.DH_WK, \n");
        sb.append("         A.REMARK, \n");
        sb.append("         NVL(C.CODE_NAME, '')	 AS LOCALE_NAME, \n");
        sb.append("         ''      AS SHOP_CODE, \n");
        sb.append("         ''       AS SHOP_NAME, \n");
        sb.append("         NVL(A.WH_CODE, (SELECT VAL FROM TCONFIG WHERE ITEM='DEF_WH_CODE') ) AS WH_CODE, \n"); //B.WH_CODE >> A.WH_CODE 2011.02.23
        sb.append("         ''    AS SHOP_CUST_NO, \n");
        sb.append("         ''         AS POST_NO, \n");
        sb.append("         ''        AS POST_SEQ, \n");
        sb.append("         ''            AS ADDR, \n");
        sb.append("         ''     AS ORDER_MEDIA, \n");
        sb.append("         ''         AS DELY_GB, \n");
        sb.append("         ''     AS RECEIVER_GB, \n");
        sb.append("         ''       AS BANK_CODE, \n");
        sb.append("         ''      AS BANK_SEQ \n");
        sb.append("    FROM TUSER A, TCODE C \n");
        sb.append("   WHERE UPPER(A.USER_ID) = UPPER(?) \n");
        sb.append("     AND A.END_DATE >= SYSDATE \n");
        sb.append("     AND C.CODE_LGROUP(+) = 'B851' \n");
        sb.append("     AND A.LOCALE = C.CODE_MGROUP(+) \n");



        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
        	log.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tpgmtape 변경 )
    * </PRE>
    * @param   Tpgmtape
    * @return  String
    */
    public String makeSqlUpdate(Tuser user) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TUSER SET 			\n ");
        sb.append("         PASSWD    	= ?, 	\n ");
        sb.append("         MODIFY_DATE = SYSDATE, 	\n ");
        sb.append("         MODIFY_ID  	= ? 	\n ");
        sb.append("   WHERE USER_ID   	= ? 	\n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

//  retrieve user information
    public Tuser retrieveTuser ( Connection con, String user_id ) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        Tuser             user        = new Tuser();
        try {

        	pstmt = con.prepareStatement(makeSqlTuser());
            pstmt.setString(1,user_id);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                user.setUser_id      ( ComUtils.NVL(rset.getString(1)));
                user.setUser_name    ( ComUtils.NVL(rset.getString(2)));
                user.setPasswd       ( ComUtils.NVL(rset.getString(3)));
                user.setDept_code    ( ComUtils.NVL(rset.getString(4)));
                user.setSabun        ( ComUtils.NVL(rset.getString(5)));
                user.setStart_date   ( ComUtils.NVL(rset.getString(6)));
                user.setEnd_date     ( ComUtils.NVL(rset.getString(7)));
                user.setUser_gb      ( ComUtils.NVL(rset.getString(8)));
                user.setMax_dc_rate  ( ComUtils.NVL(rset.getString(9)));
                user.setResident_no  ( ComUtils.NVL(rset.getString(10)));
                user.setSex          ( ComUtils.NVL(rset.getString(11)));
                user.setMajor_group  ( ComUtils.NVL(rset.getString(12)));
                user.setMinor_group  ( ComUtils.NVL(rset.getString(13)));
                user.setIcd_no       ( ComUtils.NVL(rset.getString(14)));
                user.setAgent_level  ( ComUtils.NVL(rset.getString(15)));
                user.setAgent_skill1 ( ComUtils.NVL(rset.getString(16)));
                user.setAgent_skill2 ( ComUtils.NVL(rset.getString(17)));
                user.setAgent_skill3 ( ComUtils.NVL(rset.getString(18)));
                user.setPbx_login_id ( ComUtils.NVL(rset.getString(19)));
                user.setWorker       ( ComUtils.NVL(rset.getString(20)));
                user.setDh_wk        ( ComUtils.NVL(rset.getString(21)));
                user.setRemark       ( ComUtils.NVL(rset.getString(22)));
                user.setLocaleName   ( ComUtils.NVL(rset.getString(23)));
                user.setShop_code    ( ComUtils.NVL(rset.getString(24)));
                user.setShop_name    ( ComUtils.NVL(rset.getString(25)));
                user.setWh_code      ( ComUtils.NVL(rset.getString(26)));
                user.setShop_cust_no ( ComUtils.NVL(rset.getString(27)));
                user.setPost_no      ( ComUtils.NVL(rset.getString(28)));
                user.setPost_seq     ( ComUtils.NVL(rset.getString(29)));
                user.setAddr         ( ComUtils.NVL(rset.getString(30)));
                user.setOrder_media  ( ComUtils.NVL(rset.getString(31)));
                user.setDely_gb      ( ComUtils.NVL(rset.getString(32)));
                user.setReceiver_gb  ( ComUtils.NVL(rset.getString(33)));
                user.setBank_code    ( ComUtils.NVL(rset.getString(34)));
                user.setBank_seq     ( ComUtils.NVL(rset.getString(35)));
            }else{
                throw new StoreException(ComUtils.getMessage("msg.no_data_user"));
            }

        }catch(SQLException se){
            log.error("[UserSvc.retrieveTuser() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[UserSvc.retrieveTuser() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return user;
    }

    public String makeSqlUserProgram() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT A.LMENU_ID, \n");
        sb.append("        A.LMENU_NAME, \n");
        sb.append("        A.MMENU_ID, \n");
        sb.append("        A.MMENU_NAME, \n");
        sb.append("        B.PROGRAM_ID, \n");
        sb.append("        B.PROGRAM_NAME \n");
        sb.append("   FROM TSECMENU A, \n");
        sb.append("        TSECPROGRAM B, \n");
        sb.append("        TSECPERPROGRAM C \n");
        sb.append("  WHERE A.LMENU_ID   = B.LMENU_ID \n");
        sb.append("    AND A.LMENU_ID   = C.LMENU_ID \n");
        sb.append("    AND A.MMENU_ID   = B.MMENU_ID \n");
        sb.append("    AND A.MMENU_ID   = C.MMENU_ID \n");
        sb.append("    AND B.PROGRAM_ID = C.PROGRAM_ID \n");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append("    AND B.USE_YN = '1' \n");
        sb.append("    AND C.USER_ID    = ? \n");
        sb.append("  ORDER BY A.LMENU_ID, A.MMENU_ID, B.PROGRAM_NAME \n");
        return sb.toString();
    }

//  retrieve user's right information
    public HashMap[] retrieveUserProgram ( Connection con, String user_id ) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        HashMap[] hmArray = null;
        try {
            pstmt = con.prepareStatement(makeSqlUserProgram());
            pstmt.setString(1,user_id);
            rset  = pstmt.executeQuery();

            hmArray = DBUtils.executeQueryHashMapArray(rset,"");

        }catch(SQLException se){
            log.error("[UserSvc.retrieveUserProgram() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[UserSvc.retrieveUserProgram() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return hmArray;
    }

    public String makeSqlUserProgram(String lmenu_id) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT A.LMENU_ID, \n");
        sb.append("        A.LMENU_NAME, \n");
        sb.append("        A.MMENU_ID, \n");
        sb.append("        A.MMENU_NAME, \n");
        sb.append("        A.SMENU_ID, \n");
        sb.append("        A.SMENU_NAME, \n");
        sb.append("        B.SORT, \n");
        sb.append("        B.PROGRAM_ID, \n");
        sb.append("        B.PROGRAM_NAME \n");
        sb.append("   FROM TSECMENU A, \n");
        sb.append("        TSECPROGRAM B, \n");
        sb.append("        TSECPERPROGRAM C \n");
        sb.append("  WHERE A.LMENU_ID   = B.LMENU_ID \n");
        sb.append("    AND A.LMENU_ID   = C.LMENU_ID \n");
        sb.append("    AND A.MMENU_ID   = B.MMENU_ID \n");
        sb.append("    AND A.MMENU_ID   = C.MMENU_ID \n");
        sb.append("    AND A.SMENU_ID   = B.SMENU_ID \n");
        sb.append("    AND B.PROGRAM_ID = C.PROGRAM_ID \n");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append("    AND B.USE_YN = '1' \n");
        sb.append("    AND C.USER_ID    = ? \n");
        sb.append("    AND A.LMENU_ID   = ? \n");
        sb.append("  ORDER BY A.LMENU_ID, A.MMENU_ID, A.SMENU_ID, B.SORT, B.PROGRAM_NAME \n");
        return sb.toString();
    }

//  retrieve user's right information
    public HashMap[] retrieveUserProgram ( Connection con, String user_id, String lmenu_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        HashMap[] hmArray = null;
        try {
            pstmt = con.prepareStatement(makeSqlUserProgram(lmenu_id));
            pstmt.setString(1, user_id);
            pstmt.setString(2, lmenu_id);
            rset  = pstmt.executeQuery();

            hmArray = DBUtils.executeQueryHashMapArray(rset,"");

        }catch(SQLException se){
            log.error("[UserSvc.retrieveUserProgram() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[UserSvc.retrieveUserProgram() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return hmArray;
    }

    public String makeSqlSecPerProgram() throws StoreException {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.PROGRAM_ID, \n");
        sb.append("        A.PROGRAM_NAME, \n");
        sb.append("        B.QUERY_YN, \n");
        sb.append("        B.INSERT_YN, \n");
        sb.append("        B.DELETE_YN, \n");
        sb.append("        B.SAVE_YN, \n");
        sb.append("        B.PRINT_YN, \n");
        sb.append("        B.EXCEL_YN, \n");
        sb.append("        A.NEW_YN, \n");
        sb.append("        C.MMENU_NAME, \n");
        sb.append("        C.SMENU_NAME \n");
        sb.append("   FROM TSECPROGRAM A, TSECPERPROGRAM B, TSECMENU C \n");
        sb.append("  WHERE A.PROGRAM_ID = B.PROGRAM_ID \n");
        sb.append("    AND A.LMENU_ID = C.LMENU_ID \n");
        sb.append("    AND A.MMENU_ID = C.MMENU_ID \n");
        sb.append("    AND A.SMENU_ID = C.SMENU_ID \n");
        sb.append("    AND A.USE_YN = '1' \n");
        sb.append("    AND C.USE_YN = '1' \n");
        sb.append("    AND A.PROGRAM_ID = ? \n");
        sb.append("    AND B.USER_ID = ? \n");
        sb.append("    AND ROWNUM=1 \n");
        return sb.toString();
    }

    public HashMap retrieveSecPerProgram( Connection con, String user_id, String program_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        HashMap           rtnHm       = null;
        try {
            pstmt = con.prepareStatement(makeSqlSecPerProgram());
            pstmt.setString(1,program_id);
            pstmt.setString(2,user_id);
            rset  = pstmt.executeQuery();

            rtnHm = DBUtils.executeQueryHM(rset,"");

        }catch(SQLException se){
            log.error("[UserSvc.retrieveSecPerProgram() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[UserSvc.retrieveSecPerProgram() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return rtnHm;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpgmtape
    * </PRE>
    * @param   Connection
    * @param   Tpgmtape
    * @return  int
    */
    public int updatePass(Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(user));
            int index = 1;
            pstmt.setString(index++, user.getPasswd()	);
            pstmt.setString(index++, user.getUser_id()	);
            pstmt.setString(index++, user.getUser_id()	);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user.getPasswd() 	); logString.append( "/" );
            logString.append( user.getUser_id() ); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[UserSvc.updatePass() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[UserSvc.updatePass() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
}




