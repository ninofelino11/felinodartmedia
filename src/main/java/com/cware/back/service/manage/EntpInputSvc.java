
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
import com.cware.back.entity.table.Tenterprise;

/**
 * 업체등록 Service class
 *
 * @version 1.0, 2006/06/21
 */
public class EntpInputSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public EntpInputSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.ENTP_CODE,     \n ");
        sb.append("         A.ENTP_NAME,     \n ");
        sb.append("         A.ENTP_GUBUN,    \n ");
        sb.append("         A.S_IDNO,        \n ");
        sb.append("         A.WORK_TYPE,     \n ");
        sb.append("         A.WORK_KIND,     \n ");
        sb.append("         A.ENTP_POST,     \n ");
        sb.append("         A.ENTP_POST_SEQ, \n ");
        sb.append("         A.ENTP_ADDR,     \n ");
        sb.append("         A.ENTP_DDD,      \n ");
        sb.append("         A.ENTP_TEL1,     \n ");
        sb.append("         A.ENTP_TEL2,     \n ");
        sb.append("         A.ENTP_TEL3,     \n ");
        sb.append("         A.ENTP_FAX1,     \n ");
        sb.append("         A.ENTP_FAX2,     \n ");
        sb.append("         A.ENTP_FAX3,     \n ");
        sb.append("         A.OWNER_NAME,    \n ");
        sb.append("         TO_CHAR(A.FIRST_DATE, 'YYYY/MM/DD') AS FIRST_DATE,    \n ");
        sb.append("         A.DISHONOR_YN,   \n ");
        sb.append("         A.SECRET_NO,     \n ");
        sb.append("         TO_CHAR(A.CLOSE_DATE, 'YYYY/MM/DD') AS CLOSE_DATE,    \n ");
        sb.append("         A.CLOSE_REASON,  \n ");
        sb.append("         A.INSERT_DATE,   \n ");
        sb.append("         A.INSERT_ID,     \n ");
        sb.append("         A.MODIFY_DATE,   \n ");
        sb.append("         A.MODIFY_ID,     \n ");
        sb.append("         A.ETC,           \n ");
        sb.append("         A.EMAIL_ADDR,    \n ");
        sb.append("         FUN_ADD_POSTADDR(A.ENTP_POST,A.ENTP_POST_SEQ,'') AS COMP_ADDR   \n ");
        sb.append("    FROM TENTERPRISE A                 \n ");
        sb.append("   WHERE A.ENTP_CODE LIKE ? || '%'      \n ");
        sb.append("   ORDER BY ENTP_CODE     \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( 아이디 중복 체크 )
    * </PRE>
    * @param   Tentp
    * @return  String
    */
    public String makeSqlDupCheck(Tenterprise entp) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(entp_ID) \n ");
        sb.append("    FROM Tentp \n ");
        sb.append("   WHERE entp_ID = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( ENTP CODE No. Select )
    * </PRE>
    * @param   Tentp
    * @return  String
    */
    public String makeSqlGetCode() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT  TO_CHAR(LPAD(NVL(MAX(TO_NUMBER(SUBSTR(ENTP_CODE, 1, 6))), 0) + 1, 6, '0')) AS ENTP_CODE \n ");
        sb.append("    FROM  TENTERPRISE  \n ");
        sb.append("   WHERE  ENTP_CODE NOT IN ('999999', '999998') \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tentp 에 등록 )
    * </PRE>
    * @param   Tentp
    * @return  String
    */
    public String makeSqlInsert(Tenterprise entp) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TENTERPRISE (    \n ");
        sb.append("              ENTP_CODE    ,   \n ");
        sb.append("              ENTP_NAME    ,   \n ");
        sb.append("              ENTP_GUBUN   ,   \n ");
        sb.append("              DELY_GB    ,     \n ");
        sb.append("              S_IDNO       ,   \n ");
        sb.append("              WORK_TYPE    ,   \n ");
        sb.append("              WORK_KIND    ,   \n ");
        sb.append("              ENTP_POST    ,   \n ");
        sb.append("              ENTP_POST_SEQ,   \n ");
        sb.append("              ENTP_ADDR    ,   \n ");
        sb.append("              ENTP_DDD     ,   \n ");
        sb.append("              ENTP_TEL1    ,   \n ");
        sb.append("              ENTP_TEL2    ,   \n ");
        sb.append("              ENTP_TEL3    ,   \n ");
        sb.append("              ENTP_FAX1    ,   \n ");
        sb.append("              ENTP_FAX2    ,   \n ");
        sb.append("              ENTP_FAX3    ,   \n ");
        sb.append("              EMAIL_ADDR   ,   \n ");
        sb.append("              OWNER_NAME   ,   \n ");
        sb.append("              DISHONOR_YN  ,   \n ");
        sb.append("              SECRET_NO    ,   \n ");
        sb.append("              FIRST_DATE   ,   \n ");
        sb.append("              CLOSE_DATE   ,   \n ");
        sb.append("              CLOSE_REASON ,   \n ");
        sb.append("              ETC          ,   \n ");
        sb.append("              INSERT_DATE  ,   \n ");
        sb.append("              INSERT_ID    ,   \n ");
        sb.append("              MODIFY_DATE  ,   \n ");
        sb.append("              MODIFY_ID    )   \n ");
        sb.append("       VALUES (                \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              TO_DATE(?, 'YYYY/MM/DD'),               \n ");
        sb.append("              TO_DATE(?, 'YYYY/MM/DD'),               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,         \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,         \n ");
        sb.append("              ? )              \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tentp 변경 )
    * </PRE>
    * @param   Tentp
    * @return  String
    */
    public String makeSqlUpdate(Tenterprise entp) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TENTERPRISE           \n ");
        sb.append("     SET ENTP_NAME        = ?,  \n ");
        sb.append("         ENTP_GUBUN       = ?,  \n ");
        sb.append("         DELY_GB          = ?,  \n ");
        sb.append("         S_IDNO           = ?,  \n ");
        sb.append("         WORK_TYPE        = ?,  \n ");
        sb.append("         WORK_KIND        = ?,  \n ");
        sb.append("         ENTP_POST        = ?,  \n ");
        sb.append("         ENTP_POST_SEQ    = ?,  \n ");
        sb.append("         ENTP_ADDR        = ?,  \n ");
        sb.append("         ENTP_DDD         = ?,  \n ");
        sb.append("         ENTP_TEL1        = ?,  \n ");
        sb.append("         ENTP_TEL2        = ?,  \n ");
        sb.append("         ENTP_TEL3        = ?,  \n ");
        sb.append("         ENTP_FAX1        = ?,  \n ");
        sb.append("         ENTP_FAX2        = ?,  \n ");
        sb.append("         ENTP_FAX3        = ?,  \n ");
        sb.append("         EMAIL_ADDR       = ?,  \n ");
        sb.append("         OWNER_NAME       = ?,  \n ");
        sb.append("         DISHONOR_YN      = ?,  \n ");
        sb.append("         SECRET_NO        = ?,  \n ");
        sb.append("         FIRST_DATE       = TO_DATE(?, 'YYYY/MM/DD'),  \n ");
        sb.append("         CLOSE_DATE       = TO_DATE(?, 'YYYY/MM/DD'),  \n ");
        sb.append("         CLOSE_REASON     = ?,  \n ");
        sb.append("         ETC              = ?,  \n ");
        sb.append("         MODIFY_DATE      = SYSDATE,  \n ");
        sb.append("         MODIFY_ID        = ?  \n ");
        sb.append("   WHERE ENTP_CODE        = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
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
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tentp
    * </PRE>
    * @param   Connection
    * @param   Tentp
    * @return  int
    */
    public int insert(Connection con, Tenterprise entp) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(entp));
            int index = 1;
            pstmt.setString(index++,entp.getEntp_code());
            pstmt.setString(index++,entp.getEntp_name());
            pstmt.setString(index++,entp.getEntp_gubun());
            pstmt.setString(index++,entp.getDely_gb());
            pstmt.setString(index++,entp.getS_idno());
            pstmt.setString(index++,entp.getWork_type());
            pstmt.setString(index++,entp.getWork_kind());
            pstmt.setString(index++,entp.getEntp_post());
            pstmt.setString(index++,entp.getEntp_post_seq());
            pstmt.setString(index++,entp.getEntp_addr());
            pstmt.setString(index++,entp.getEntp_ddd());
            pstmt.setString(index++,entp.getEntp_tel1());
            pstmt.setString(index++,entp.getEntp_tel2());
            pstmt.setString(index++,entp.getEntp_tel3());
            pstmt.setString(index++,entp.getEntp_fax1());
            pstmt.setString(index++,entp.getEntp_fax2());
            pstmt.setString(index++,entp.getEntp_fax3());
            pstmt.setString(index++,entp.getEmail_addr());
            pstmt.setString(index++,entp.getOwner_name());
            pstmt.setString(index++,entp.getDishonor_yn());
            pstmt.setString(index++,entp.getSecret_no());
            pstmt.setString(index++,entp.getFirst_date());
            pstmt.setString(index++,entp.getClose_date());
            pstmt.setString(index++,entp.getClose_reason());
            pstmt.setString(index++,entp.getEtc());
            pstmt.setString(index++,entp.getInsert_id());
            pstmt.setString(index++,entp.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( entp.getEntp_code()         ); logString.append( "/" );
            logString.append( entp.getEntp_name()         ); logString.append( "/" );
            logString.append( entp.getEntp_gubun()        ); logString.append( "/" );
            logString.append( entp.getDely_gb()        	  ); logString.append( "/" );
            logString.append( entp.getS_idno()            ); logString.append( "/" );
            logString.append( entp.getWork_type()         ); logString.append( "/" );
            logString.append( entp.getWork_kind()         ); logString.append( "/" );
            logString.append( entp.getEntp_post()         ); logString.append( "/" );
            logString.append( entp.getEntp_post_seq()     ); logString.append( "/" );
            logString.append( entp.getEntp_addr()         ); logString.append( "/" );
            logString.append( entp.getEntp_ddd()          ); logString.append( "/" );
            logString.append( entp.getEntp_tel1()         ); logString.append( "/" );
            logString.append( entp.getEntp_tel2()         ); logString.append( "/" );
            logString.append( entp.getEntp_tel3()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax1()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax2()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax3()         ); logString.append( "/" );
            logString.append( entp.getEmail_addr()        ); logString.append( "/" );
            logString.append( entp.getOwner_name()        ); logString.append( "/" );
            logString.append( entp.getDishonor_yn()       ); logString.append( "/" );
            logString.append( entp.getSecret_no()         ); logString.append( "/" );
            logString.append( entp.getFirst_date()        ); logString.append( "/" );
            logString.append( entp.getClose_date()        ); logString.append( "/" );
            logString.append( entp.getClose_reason()      ); logString.append( "/" );
            logString.append( entp.getEtc()               ); logString.append( "/" );
            logString.append( entp.getInsert_id()         ); logString.append( "/" );
            logString.append( entp.getModify_id()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[EntpInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[EntpInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tentp
    * </PRE>
    * @param   Connection
    * @param   Tentp
    * @return  int
    */
    public int update(Connection con, Tenterprise entp) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(entp));
            int index = 1;
            pstmt.setString(index++,entp.getEntp_name());
            pstmt.setString(index++,entp.getEntp_gubun());
            pstmt.setString(index++,entp.getDely_gb());
            pstmt.setString(index++,entp.getS_idno());
            pstmt.setString(index++,entp.getWork_type());
            pstmt.setString(index++,entp.getWork_kind());
            pstmt.setString(index++,entp.getEntp_post());
            pstmt.setString(index++,entp.getEntp_post_seq());
            pstmt.setString(index++,entp.getEntp_addr());
            pstmt.setString(index++,entp.getEntp_ddd());
            pstmt.setString(index++,entp.getEntp_tel1());
            pstmt.setString(index++,entp.getEntp_tel2());
            pstmt.setString(index++,entp.getEntp_tel3());
            pstmt.setString(index++,entp.getEntp_fax1());
            pstmt.setString(index++,entp.getEntp_fax2());
            pstmt.setString(index++,entp.getEntp_fax3());
            pstmt.setString(index++,entp.getEmail_addr());
            pstmt.setString(index++,entp.getOwner_name());
            pstmt.setString(index++,entp.getDishonor_yn());
            pstmt.setString(index++,entp.getSecret_no());
            pstmt.setString(index++,entp.getFirst_date());
            pstmt.setString(index++,entp.getClose_date());
            pstmt.setString(index++,entp.getClose_reason());
            pstmt.setString(index++,entp.getEtc());
            pstmt.setString(index++,entp.getModify_id());
            pstmt.setString(index++,entp.getEntp_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( entp.getEntp_name()         ); logString.append( "/" );
            logString.append( entp.getEntp_gubun()        ); logString.append( "/" );
            logString.append( entp.getDely_gb()        	  ); logString.append( "/" );
            logString.append( entp.getS_idno()            ); logString.append( "/" );
            logString.append( entp.getWork_type()         ); logString.append( "/" );
            logString.append( entp.getWork_kind()         ); logString.append( "/" );
            logString.append( entp.getEntp_post()         ); logString.append( "/" );
            logString.append( entp.getEntp_post_seq()     ); logString.append( "/" );
            logString.append( entp.getEntp_addr()         ); logString.append( "/" );
            logString.append( entp.getEntp_ddd()          ); logString.append( "/" );
            logString.append( entp.getEntp_tel1()         ); logString.append( "/" );
            logString.append( entp.getEntp_tel2()         ); logString.append( "/" );
            logString.append( entp.getEntp_tel3()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax1()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax2()         ); logString.append( "/" );
            logString.append( entp.getEntp_fax3()         ); logString.append( "/" );
            logString.append( entp.getEmail_addr()        ); logString.append( "/" );
            logString.append( entp.getOwner_name()        ); logString.append( "/" );
            logString.append( entp.getDishonor_yn()       ); logString.append( "/" );
            logString.append( entp.getSecret_no()         ); logString.append( "/" );
            logString.append( entp.getFirst_date()        ); logString.append( "/" );
            logString.append( entp.getClose_date()        ); logString.append( "/" );
            logString.append( entp.getClose_reason()      ); logString.append( "/" );
            logString.append( entp.getEtc()               ); logString.append( "/" );
            logString.append( entp.getInsert_date()       ); logString.append( "/" );
            logString.append( entp.getInsert_id()         ); logString.append( "/" );
            logString.append( entp.getModify_date()       ); logString.append( "/" );
            logString.append( entp.getModify_id()         ); logString.append( "/" );
            logString.append( entp.getEntp_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[EntpInputSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[EntpInputSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Get Entp Code -------------------------------------------------
    /**
    * <PRE>
    * Desc : Get Entp Code No. from TENTERPRISE
    * </PRE>
    * @param   Connection
    * @param   Tentp
    * @return  int
    */
    public String getCode(Connection con, Tenterprise entp) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            new_entp_code = "";
        try {
            pstmt = con.prepareStatement(makeSqlGetCode());
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                new_entp_code = rset.getString("ENTP_CODE");
            }else new_entp_code = "";

        }catch(SQLException se){
            logSave.error("[EntpInputSvc.getCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[EntpInputSvc.getCode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return new_entp_code;
    }

    //  = Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlEntpCodeCheck( Tenterprise tenterprise ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(A.ENTP_CODE) AS ENTP_CNT     \n");
        sb.append("  FROM TENTERPRISE A 				      \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    //= Check -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check GoodsCode
    * </PRE>
    * @param   Connection
    * @param   Tenterprise
    * @return  String
    */
    public int chkFirstEntpCode(Connection con, Tenterprise tenterprise) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
        	pstmt = con.prepareStatement(makeSqlEntpCodeCheck(tenterprise));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logSave.info(logString.toString());

            rset = pstmt.executeQuery();

            if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
            executedRtn = rset.getInt(1);

        }catch(SQLException se){
            logSave.error("[Tenterprise.chkFirstEntpCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tenterprise.chkFirstEntpCode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
}