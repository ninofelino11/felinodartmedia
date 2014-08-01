
package com.cware.back.service.system;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import java.sql.CallableStatement;
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



/**
 * BatchExecute Service class
 *
 * @version 1.0, 2006/07/13
 * @author commerceware.co.kr
 */
public class BatchExecuteSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BatchExecuteSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.CODE_GROUP,    \n");
        sb.append("        A.CODE_MGROUP,   \n");
        sb.append("        A.CODE_NAME,     \n");
        sb.append("        A.REMARK,        \n");
        sb.append("        A.CONTENT,       \n");
        sb.append("        A.REMARK1        \n");
        sb.append("   FROM TCODE A          \n");
        sb.append("  WHERE A.CODE_LGROUP = 'B065' \n");
        sb.append("    AND A.USE_YN      = '1'    \n");
        sb.append(" ORDER BY A.CODE_GROUP ASC,    \n");
        sb.append("          A.REMARK ASC         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT TO_CHAR(CLOSE_DATE, 'YYYY/MM/DD') AS CLOSE_DATE,    \n");
        sb.append("        OK_YN,           \n");
        sb.append("        ERR_TEXT         \n");
        sb.append("   FROM TCLOSEHISTORY    \n");
        sb.append("  WHERE PRG_ID = ?       \n");
        sb.append("    AND CLOSE_DATE > ADD_MONTHS(TO_DATE(?, 'YYYY/MM/DD'), -2)    \n");
        sb.append("    AND CLOSE_DATE <=TO_DATE(?, 'YYYY/MM/DD')  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    * Desc : Retrieve SQL ; Master
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException, com.cware.back.common.StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
  //          throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Detail
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDetail(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve SQL ; Detail
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
            pstmt.setString(index++,retrieve.getString("program_id"));
            pstmt.setString(index++,retrieve.getString("close_date"));
            pstmt.setString(index++,retrieve.getString("close_date"));
            
//          = log Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("program_id")  ); logString.append( "/" );
            logString.append( retrieve.getString("close_date")  ); logString.append( "/" );
            log.info(logString.toString());
            
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
        //    throw new StoreException(e.getMessage());
        }finally {
        //    DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

//  = Excute Procedure-----------------------------------------------
    /**
    * <PRE>
    * Desc : Excute Procedure SP_SLIPCREATE
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel execProcedure(Connection con, RetrieveModel retrieve) throws StoreException{
        CallableStatement cstmt     = null;
        ResultSet         rset      = null;
        int executedRtn             = 0;

        try {
        	/* ex)
        	 * exec_procedure( arg_user_id in varchar2, 
        	 *                 arg_date in varchar2,                                            
             *                 arg_sp_name in varchar2,
             *                 arg_wh_code in varchar2,
             *                 arg_rtn_msg out varchar2 )
        	 */
            cstmt = con.prepareCall("begin exec_procedure(?, ?, ?, ?, ?, ?); end;");

            int index = 1;
            
            cstmt.registerOutParameter(index++, java.sql.Types.LONGVARCHAR);
            cstmt.registerOutParameter(index++, java.sql.Types.LONGVARCHAR);
            cstmt.setString(index++,retrieve.getString("user_id"));
            cstmt.setString(index++,retrieve.getString("close_date"));
            cstmt.setString(index++,retrieve.getString("program_id"));
            cstmt.setString(index++,"");
            
                        
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("user_id")      ); logString.append( "/" );   
            logString.append( retrieve.getString("close_date")   ); logString.append( "/" );
            logString.append( retrieve.getString("program_id")   ); logString.append( "/" );
            logSave.info(logString.toString());

            //== 프로시져를 실행합니다.
            executedRtn = cstmt.executeUpdate();

           // retrieve.put("out_rtncode",            ComUtils.NVL(cstmt.getString(1),"0"));
         //   retrieve.put("out_rtnmsg",             ComUtils.NVL(cstmt.getString(2),"0"));

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
       //     throw new StoreException(se.getMessage());
       // }catch(Exception e){
           // logSave.error("Exception : ERR-"+e.getMessage());
         //   throw new StoreException(e.getMessage());
        }finally {
           // DBUtils.freeConnection(null, null, null, cstmt, null, rset);
        }
        return retrieve;
    }

}
