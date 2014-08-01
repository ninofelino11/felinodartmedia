
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
import com.cware.back.entity.table.Temployee;

/**
 * Register Employee Service class
 *
 * @version 1.0, 2008/04/11
 */
public class EmployeeRegSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public EmployeeRegSvc() {}

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

        sb.append("  SELECT A.EMPLOYEE_ID,  \n ");
        sb.append("         A.EMPLOYEE_NAME,  \n ");
        sb.append("         A.PASSWD,  \n ");
        sb.append("         A.DEPT_CODE,  \n ");
        sb.append("         A.SABUN,  \n ");
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
        sb.append("    FROM TEMPLOYEE A  \n ");
        sb.append("   WHERE A.EMPLOYEE_ID LIKE   ?||'%'   \n ");

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
    public String makeSqlDupCheck(Temployee employee) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(EMPLOYEE_ID) \n ");
        sb.append("    FROM TEMPLOYEE \n ");
        sb.append("   WHERE EMPLOYEE_ID = ? \n ");

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
    public String makeSqlInsert(Temployee employee) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TEMPLOYEE ( \n ");
        sb.append("          EMPLOYEE_ID, \n ");
        sb.append("          EMPLOYEE_NAME, \n ");
        sb.append("          PASSWD, \n ");
        sb.append("          DEPT_CODE, \n ");
        sb.append("          SABUN, \n ");
        sb.append("          START_DATE, \n ");
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
    public String makeSqlUpdate(Temployee employee) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE TEMPLOYEE SET \n ");
        sb.append("        EMPLOYEE_NAME   = ?, \n ");
        sb.append("        PASSWD      = ?, \n ");
        sb.append("        DEPT_CODE   = ?, \n ");
        sb.append("        SABUN       = ?, \n ");
        sb.append("        END_DATE    = TO_DATE(?,'YYYY/MM/DD'), \n ");
        sb.append("        USER_GB     = ?, \n ");
        sb.append("        MAX_DC_RATE = ?, \n ");
        sb.append("        RESIDENT_NO = ?, \n ");
        sb.append("        SEX         = ?, \n ");
        sb.append("        REMARK      = ?, \n ");
        sb.append("        MODIFY_DATE = SYSDATE, \n ");
        sb.append("        MODIFY_ID   = ? \n ");
        sb.append("  WHERE EMPLOYEE_ID     = ? \n ");

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
            pstmt.setString(index++,retrieve.getString("employee_id"));

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
    public int insert(Connection con, Temployee employee) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(employee));
            int index = 1;
            pstmt.setString(index++,employee.getEmployee_id()  );
            pstmt.setString(index++,employee.getEmployee_name());
            pstmt.setString(index++,employee.getPasswd()       );
            pstmt.setString(index++,employee.getDept_code()    );
            pstmt.setString(index++,employee.getSabun()        );
            pstmt.setString(index++,employee.getEnd_date()     );
            pstmt.setString(index++,employee.getUser_gb()      );
            pstmt.setDouble(index++,employee.getMax_dc_rate()  );
            pstmt.setString(index++,employee.getResident_no()  );
            pstmt.setString(index++,employee.getSex()          );
            pstmt.setString(index++,employee.getRemark()       );
            pstmt.setString(index++,employee.getInsert_id()    );
            pstmt.setString(index++,employee.getModify_id()    );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( employee.getEmployee_id()  ); logString.append( "/" );
            logString.append( employee.getEmployee_name()); logString.append( "/" );
            logString.append( employee.getPasswd()       ); logString.append( "/" );
            logString.append( employee.getDept_code()    ); logString.append( "/" );
            logString.append( employee.getSabun()        ); logString.append( "/" );
            logString.append( employee.getEnd_date()     ); logString.append( "/" );
            logString.append( employee.getUser_gb()      ); logString.append( "/" );
            logString.append( employee.getMax_dc_rate()  ); logString.append( "/" );
            logString.append( employee.getResident_no()  ); logString.append( "/" );
            logString.append( employee.getSex()          ); logString.append( "/" );
            logString.append( employee.getRemark()       ); logString.append( "/" );
            logString.append( employee.getInsert_id()    ); logString.append( "/" );
            logString.append( employee.getModify_id()    ); logString.append( "/" );
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
    public int update(Connection con, Temployee employee) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(employee));
            int index = 1;
            pstmt.setString(index++,employee.getEmployee_name());
            pstmt.setString(index++,employee.getPasswd()       );
            pstmt.setString(index++,employee.getDept_code()    );
            pstmt.setString(index++,employee.getSabun()        );
            pstmt.setString(index++,employee.getEnd_date()     );
            pstmt.setString(index++,employee.getUser_gb()      );
            pstmt.setDouble(index++,employee.getMax_dc_rate()  );
            pstmt.setString(index++,employee.getResident_no()  );
            pstmt.setString(index++,employee.getSex()          );
            pstmt.setString(index++,employee.getRemark()       );
            pstmt.setString(index++,employee.getModify_id()    );
            pstmt.setString(index++,employee.getEmployee_id()  );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( employee.getEmployee_name()); logString.append( "/" );
            logString.append( employee.getPasswd()       ); logString.append( "/" );
            logString.append( employee.getDept_code()    ); logString.append( "/" );
            logString.append( employee.getSabun()        ); logString.append( "/" );
            logString.append( employee.getEnd_date()     ); logString.append( "/" );
            logString.append( employee.getUser_gb()      ); logString.append( "/" );
            logString.append( employee.getMax_dc_rate()  ); logString.append( "/" );
            logString.append( employee.getResident_no()  ); logString.append( "/" );
            logString.append( employee.getSex()          ); logString.append( "/" );
            logString.append( employee.getRemark()       ); logString.append( "/" );
            logString.append( employee.getModify_id()    ); logString.append( "/" );
            logString.append( employee.getEmployee_id()  ); logString.append( "/" );
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
    public int checkDup(Connection con, Temployee employee) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(employee));
            pstmt.setString(1,employee.getEmployee_id());
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
