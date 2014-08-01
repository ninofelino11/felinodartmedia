
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
import com.cware.back.entity.table.Tsecusergroup;


/**
 * UserGroupProgramCompetence Service class
 *
 * @version 1.0, 2007/01/18
 * @author commerceware.co.kr
 */
public class UserGroupProgramCompetenceSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public UserGroupProgramCompetenceSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlUser(RetrieveModel retrieve) throws Exception {

        StringBuffer sb = new StringBuffer();

        //=사용자별 기능조회
        sb.append(" SELECT  B.LMENU_ID,                  \n");
        sb.append("         B.MMENU_ID,                  \n");
        sb.append("         B.SMENU_ID,                  \n");
        sb.append("         B.PROGRAM_ID ,               \n");
        sb.append("         B.PROGRAM_NAME ,             \n");
        sb.append("         B.USE_YN,                    \n");
        sb.append("         A.QUERY_YN,                  \n");
        sb.append("         A.INSERT_YN,                 \n");
        sb.append("         A.DELETE_YN,                 \n");
        sb.append("         A.SAVE_YN,                   \n");
        sb.append("         A.PRINT_YN,                  \n");
        sb.append("         A.EXCEL_YN,                  \n");
        sb.append("         B.REMARK,                    \n");
        sb.append("         A.USER_GB                    \n");
        sb.append("    FROM TSECUSERGROUP A  ,           \n");
        sb.append("         TSECPROGRAM B                \n");
        sb.append("   WHERE A.LMENU_ID = B.LMENU_ID      \n");
        sb.append("     AND A.MMENU_ID = B.MMENU_ID      \n");
        sb.append("     AND A.PROGRAM_ID = B.PROGRAM_ID  \n");
        sb.append("     AND B.USE_YN  = 1                \n");
        sb.append("     AND A.USER_GB  = ?               \n");
        sb.append("     AND B.PROGRAM_ID LIKE '' || '%'   \n");
        sb.append("     AND B.LMENU_ID LIKE ? || '%'     \n");
        sb.append("     AND B.MMENU_ID LIKE ? || '%'     \n");
        sb.append("   ORDER BY B.LMENU_ID, B.MMENU_ID, B.SMENU_ID  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("user_gb     : " + retrieve.getString("user_gb"));
            log.debug("program_id  : " + retrieve.getString("program_id"));
            log.debug("lmenu_id    : " + retrieve.getString("lmenu_id"));
            log.debug("mmenu_id    : " + retrieve.getString("mmenu_id"));
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlProgram(RetrieveModel retrieve) throws Exception {

        StringBuffer sb = new StringBuffer();

        // 프로그램별 기능조회
        sb.append(" SELECT A.USER_GB,                   \n");
        sb.append("        C.CODE_NAME,                 \n");
        sb.append("        B.USE_YN,                    \n");
        sb.append("        A.LMENU_ID,                  \n");
        sb.append("        A.MMENU_ID,                  \n");
        sb.append("        A.PROGRAM_ID,                \n");
        sb.append("        A.QUERY_YN,                  \n");
        sb.append("        A.INSERT_YN,                 \n");
        sb.append("        A.DELETE_YN,                 \n");
        sb.append("        A.SAVE_YN,                   \n");
        sb.append("        A.PRINT_YN,                  \n");
        sb.append("        A.EXCEL_YN                   \n");
        sb.append("   FROM TSECUSERGROUP A ,            \n");
        sb.append("        TSECPROGRAM    B ,           \n");
        sb.append("        TCODE          C             \n");
        sb.append("  WHERE A.LMENU_ID   = B.LMENU_ID    \n");
        sb.append("    AND A.MMENU_ID   = B.MMENU_ID    \n");
        sb.append("    AND A.PROGRAM_ID = B.PROGRAM_ID  \n");
        sb.append("    AND A.USER_GB    = C.CODE_MGROUP \n");
        sb.append("    AND C.CODE_LGROUP = 'B040'		\n");
        sb.append("    AND B.USE_YN     = 1             \n");
        sb.append("    AND B.PROGRAM_ID = ?             \n");

        sb.append("  ORDER BY A.USER_GB      \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("program_id  : " + retrieve.getString("program_id"));
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
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveUser(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlUser(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("user_gb"     ));
            pstmt.setString(index++, retrieve.getString("lmenu_id"    ));
            pstmt.setString(index++, retrieve.getString("mmenu_id"    ));
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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveProgram(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlProgram(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("program_id"  ));

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






    /**
     * <PRE>
     * Desc : Make SQL ( Update Tsecusergroup )
     * </PRE>
     * @param   Tsecusergroup
     * @return  String
     */
     private final String updateSql = ""
         + " UPDATE TSECUSERGROUP   \n "
         + "    SET QUERY_YN   = ?,  \n "
         + "        INSERT_YN  = ?,  \n "
         + "        DELETE_YN  = ?,  \n "
         + "        SAVE_YN    = ?,  \n "
         + "        PRINT_YN   = ?,  \n "
         + "        EXCEL_YN   = ?   \n "
         + "  WHERE USER_GB    = ?   \n "
         + "    AND LMENU_ID   = ?   \n "
         + "    AND MMENU_ID   = ?   \n "
         + "    AND PROGRAM_ID = ?   \n ";

     private int updateSqlLog =  0;

     public String makeSqlUpdate(Tsecusergroup tsecusergroup) throws StoreException{
         //= log SQL -------------------------------------------------
         if (updateSqlLog == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(updateSql);
             }
             updateSqlLog = 1;
         }
         return updateSql;
     }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update tsecusergroup
    * </PRE>
    * @param   Connection
    * @param   tsecusergroup
    * @return  int
    */
    public int update(Connection con, Tsecusergroup tsecusergroup) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tsecusergroup));
            int index = 1;
            pstmt.setString(index++,tsecusergroup.getQuery_yn());
            pstmt.setString(index++,tsecusergroup.getInsert_yn());
            pstmt.setString(index++,tsecusergroup.getDelete_yn());
            pstmt.setString(index++,tsecusergroup.getSave_yn());
            pstmt.setString(index++,tsecusergroup.getPrint_yn());
            pstmt.setString(index++,tsecusergroup.getExcel_yn());
            pstmt.setString(index++,tsecusergroup.getUser_gb());
            pstmt.setString(index++,tsecusergroup.getLmenu_id());
            pstmt.setString(index++,tsecusergroup.getMmenu_id());
            pstmt.setString(index++,tsecusergroup.getProgram_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsecusergroup.getQuery_yn()   ); logString.append( "/" );
            logString.append( tsecusergroup.getInsert_yn()  ); logString.append( "/" );
            logString.append( tsecusergroup.getDelete_yn()  ); logString.append( "/" );
            logString.append( tsecusergroup.getSave_yn()    ); logString.append( "/" );
            logString.append( tsecusergroup.getPrint_yn()   ); logString.append( "/" );
            logString.append( tsecusergroup.getExcel_yn()   ); logString.append( "/" );
            logString.append( tsecusergroup.getUser_gb()    ); logString.append( "/" );
            logString.append( tsecusergroup.getLmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getMmenu_id()   ); logString.append( "/" );
            logString.append( tsecusergroup.getProgram_id() ); logString.append( "/" );
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


}
