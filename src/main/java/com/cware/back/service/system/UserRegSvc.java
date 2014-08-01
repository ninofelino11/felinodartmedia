
package com.cware.back.service.system;

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
import com.cware.back.entity.table.Tuser;

/**
 * Register User Service class
 *
 * @version 1.0, 2006/06/21
 */
public class UserRegSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public UserRegSvc() {}

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

        sb.append("  SELECT A.USER_ID,  \n ");
        sb.append("         A.USER_NAME,  \n ");
        sb.append("         A.PASSWD,  \n ");
        sb.append("         A.DEPT_CODE,  \n ");
        sb.append("         A.SABUN,  \n ");
        sb.append("         A.WH_CODE,  \n ");
        sb.append("         A.LOCALE,  \n ");
        sb.append("         TO_CHAR(A.START_DATE,'YYYY/MM/DD') AS START_DATE,  \n ");
        sb.append("         TO_CHAR(A.END_DATE,'YYYY/MM/DD')   AS END_DATE,    \n ");
        sb.append("         A.USER_GB,  \n ");
        sb.append("         A.MAX_DC_RATE,  \n ");
        sb.append("         A.RESIDENT_NO,  \n ");
        sb.append("         A.SEX,  \n ");
        sb.append("         A.INSERT_DATE,  \n ");
        sb.append("         A.INSERT_ID,  \n ");
        sb.append("         A.MODIFY_DATE,  \n ");
        sb.append("         A.MODIFY_ID,  \n ");
        sb.append("         A.REMARK  \n ");
        sb.append("    FROM TUSER A  \n ");
        sb.append("   WHERE A.USER_ID LIKE   ?||'%'   \n ");

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
    * @param   Tuser
    * @return  String
    */
    public String makeSqlDupCheck(Tuser user) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(USER_ID) \n ");
        sb.append("    FROM VUSER \n ");
        sb.append("   WHERE USER_ID = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TUSER 에 등록 )
    * </PRE>
    * @param   Tuser
    * @return  String
    */
    public String makeSqlInsert(Tuser user) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TUSER ( \n ");
        sb.append("          USER_ID, \n ");
        sb.append("          USER_NAME, \n ");
        sb.append("          PASSWD, \n ");
        sb.append("          DEPT_CODE, \n ");
        sb.append("          SABUN, \n ");
        sb.append("          START_DATE, \n ");
        sb.append("          WH_CODE, \n ");
        sb.append("          LOCALE, \n ");
        sb.append("          END_DATE, \n ");
        sb.append("          USER_GB, \n ");
        sb.append("          MAX_DC_RATE, \n ");
        sb.append("          RESIDENT_NO, \n ");
        sb.append("          SEX, \n ");
        sb.append("          REMARK, \n ");
        sb.append("          INSERT_DATE, \n ");
        sb.append("          INSERT_ID, \n ");
        sb.append("          MODIFY_DATE, \n ");
        sb.append("          MODIFY_ID ) \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          UPPER(?), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          SYSDATE, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          SYSDATE, \n ");
        sb.append("          ?, \n ");
        sb.append("          SYSDATE, \n ");
        sb.append("          ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TUSER 변경 )
    * </PRE>
    * @param   Tuser
    * @return  String
    */
    public String makeSqlUpdate(Tuser user) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE TUSER SET \n ");
        sb.append("        USER_NAME   = ?, \n ");
        sb.append("        PASSWD      = ?, \n ");
        sb.append("        DEPT_CODE   = ?, \n ");
        sb.append("        SABUN       = ?, \n ");
        sb.append("        WH_CODE       = ?, \n ");
        sb.append("        LOCALE       = ?, \n ");
        sb.append("        END_DATE    = TO_DATE(?,'YYYY/MM/DD'), \n ");
        sb.append("        USER_GB     = ?, \n ");
        sb.append("        MAX_DC_RATE = ?, \n ");
        sb.append("        RESIDENT_NO = ?, \n ");
        sb.append("        SEX         = ?, \n ");
        sb.append("        REMARK      = ?, \n ");
        sb.append("        MODIFY_DATE = SYSDATE, \n ");
        sb.append("        MODIFY_ID   = ? \n ");
        sb.append("  WHERE USER_ID     = ? \n ");

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
            pstmt.setString(index++,retrieve.getString("user_id"));

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

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TUSER
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int insert(Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(user));
            int index = 1;
            pstmt.setString(index++,user.getUser_id()     );
            pstmt.setString(index++,user.getUser_name()   );
            pstmt.setString(index++,user.getPasswd()      );
            pstmt.setString(index++,user.getDept_code()   );
            pstmt.setString(index++,user.getSabun()       );
            
            pstmt.setString(index++,user.getWh_code()    );
            pstmt.setString(index++,user.getLocale()    );
            
            pstmt.setString(index++,user.getEnd_date()    );
            pstmt.setString(index++,user.getUser_gb()     );
            pstmt.setString(index++,user.getMax_dc_rate() );
            pstmt.setString(index++,user.getResident_no() );
            pstmt.setString(index++,user.getSex()         );
            pstmt.setString(index++,user.getRemark()      );
            pstmt.setString(index++,user.getInsert_id()   );
            pstmt.setString(index++,user.getModify_id()   );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user.getUser_id()      ); logString.append( "/" );
            logString.append( user.getUser_name()    ); logString.append( "/" );
            logString.append( user.getPasswd()       ); logString.append( "/" );
            logString.append( user.getDept_code()    ); logString.append( "/" );
            logString.append( user.getSabun()        ); logString.append( "/" );
            
            logString.append( user.getWh_code()     ); logString.append( "/" );
            logString.append( user.getLocale()     ); logString.append( "/" );
            
            logString.append( user.getEnd_date()     ); logString.append( "/" );
            logString.append( user.getUser_gb()      ); logString.append( "/" );
            logString.append( user.getMax_dc_rate()  ); logString.append( "/" );
            logString.append( user.getResident_no()  ); logString.append( "/" );
            logString.append( user.getSex()          ); logString.append( "/" );
            logString.append( user.getRemark()       ); logString.append( "/" );
            logString.append( user.getInsert_id()    ); logString.append( "/" );
            logString.append( user.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TUSER
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int update(Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(user));
            int index = 1;
            pstmt.setString(index++,user.getUser_name()   );
            pstmt.setString(index++,user.getPasswd()      );
            pstmt.setString(index++,user.getDept_code()   );
            pstmt.setString(index++,user.getSabun()       );
            
            pstmt.setString(index++,user.getWh_code()    );
            pstmt.setString(index++,user.getLocale()    );
            
            pstmt.setString(index++,user.getEnd_date()    );
            pstmt.setString(index++,user.getUser_gb()     );
            pstmt.setString(index++,user.getMax_dc_rate() );
            pstmt.setString(index++,user.getResident_no() );
            pstmt.setString(index++,user.getSex()         );
            pstmt.setString(index++,user.getRemark()      );
            pstmt.setString(index++,user.getModify_id()   );
            pstmt.setString(index++,user.getUser_id()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( user.getUser_name()    ); logString.append( "/" );
            logString.append( user.getPasswd()       ); logString.append( "/" );
            logString.append( user.getDept_code()    ); logString.append( "/" );
            logString.append( user.getSabun()        ); logString.append( "/" );
            
            logString.append( user.getWh_code()     ); logString.append( "/" );
            logString.append( user.getLocale()     ); logString.append( "/" );
            
            logString.append( user.getEnd_date()     ); logString.append( "/" );
            logString.append( user.getUser_gb()      ); logString.append( "/" );
            logString.append( user.getMax_dc_rate()  ); logString.append( "/" );
            logString.append( user.getResident_no()  ); logString.append( "/" );
            logString.append( user.getSex()          ); logString.append( "/" );
            logString.append( user.getRemark()       ); logString.append( "/" );
            logString.append( user.getModify_id()    ); logString.append( "/" );
            logString.append( user.getUser_id()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of TUSER
    * </PRE>
    * @param   Connection
    * @param   Tuser
    * @return  int
    */
    public int checkDup(Connection con, Tuser user) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(user));
            pstmt.setString(1,user.getUser_id());
            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
}
