
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
import com.cware.back.entity.table.Tgoodsstep;

/**
 * Register Goodsstep Service class
 *
 * @version 1.0, 2007/02/10
 */
public class GoodsstepSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsstepSvc() {}

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

        sb.append("   SELECT CODE_MGROUP,           \n");
        sb.append("          CODE_NAME              \n");
        sb.append("     FROM TCODE                  \n");
        sb.append("    WHERE CODE_LGROUP = 'C071'   \n");
        sb.append("      AND USE_YN = '1'           \n");
        sb.append(" ORDER BY CODE_MGROUP            \n");

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

        sb.append(" SELECT A.SKIN_TYPE,                 \n");
        sb.append("        A.STEP,                      \n");
        sb.append("        A.SEQ,                       \n");
        sb.append("        A.GOODS_CODE,                \n");
        sb.append("        B.GOODS_NAME                 \n");
        sb.append("   FROM TGOODSSTEP A,                \n");
        sb.append("        TGOODS B                     \n");
        sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("    AND A.SKIN_TYPE = ?              \n");
        sb.append("    AND A.STEP = ?                   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("SKIN_TYPE       : " + retrieve.getString("skin_type"));
            log.debug("STEP 	      : " + retrieve.getString("step"));

        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tgoodsstep )
    * </PRE>
    * @param
    * @return  String SQL
    */
    private final String insertSql = "   INSERT INTO TGOODSSTEP ( \n "
    								+"          SKIN_TYPE,       \n "
    								+"          STEP,            \n "
    								+"          SEQ,             \n "
    								+"          GOODS_CODE,      \n "
    								+"          INSERT_DATE,     \n "
    								+"          INSERT_ID,       \n "
    								+"          MODIFY_DATE,     \n "
    								+"          MODIFY_ID )      \n "
    								+"  VALUES (   \n "
    								+"          ?, \n "
    								+"          ?, \n "
    								+"          ?, \n "
    								+"          ?, \n "
    								+"          SYSDATE, \n "
    								+"          ?, \n "
    								+"          SYSDATE, \n "
    								+"          ? ) \n " ;
    private int insertSqlLog =  0;

    public String makeSqlInsert() throws StoreException{
	   	//= log SQL -------------------------------------------------
		if (insertSqlLog == 0) {
			if (logSave.isDebugEnabled()) {
				logSave.debug(insertSql);
			}
			insertSqlLog = 1;
		}
		return insertSql;
	}

    /**
     * <PRE>
     * Desc : Make SQL ( Delete TGOODSSTEP )
     * </PRE>
     * @param   Tgoodsstep
     * @return  String
     */
     private final String deleteSqlTgoodsstep =  " DELETE  TGOODSSTEP WHERE SKIN_TYPE = ? AND STEP = ? AND SEQ = ? \n ";

     private int deleteSqlLogTgoodsstep =  0;

     private String makeSqlDelete(Tgoodsstep tgoodsstep) throws StoreException{
         //= log SQL -------------------------------------------------
         if (deleteSqlLogTgoodsstep == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(deleteSqlTgoodsstep);
             }
             deleteSqlLogTgoodsstep = 1;
         }
         return deleteSqlTgoodsstep;
     }

     /**
      * <PRE>
      * Desc : Make SQL ( SEQ  중복 체크 )
      * </PRE>
      * @param   Tgoodsstep
      * @return  String
      */
      public String makeSqlSeqCheck(Tgoodsstep tgoodsstep) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("SELECT COUNT(A.SEQ) \n ");
          sb.append("  FROM TGOODSSTEP A \n ");
          sb.append(" WHERE A.SKIN_TYPE = ? \n ");
          sb.append(" AND A.STEP = ? \n ");
          sb.append(" AND A.SEQ = ? \n ");

          //= log SQL -------------------------------------------------
          if (logSave.isDebugEnabled()) {
              logSave.debug(sb.toString());
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
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

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
            pstmt.setString(index++,retrieve.getString("skin_type"));
            pstmt.setString(index++,retrieve.getString("step"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

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
    * Desc : Insert TGOODSSTEP
    * </PRE>
    * @param   Connection
    * @param   Tmd
    * @return  int
    */
    public int insert(Connection con, Tgoodsstep tgoodsstep) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;

            pstmt.setString(index++,tgoodsstep.getSkin_type());
            pstmt.setString(index++,tgoodsstep.getStep());
            pstmt.setString(index++,tgoodsstep.getSeq());
            pstmt.setString(index++,tgoodsstep.getGoods_code());
            pstmt.setString(index++,tgoodsstep.getInsert_id());
            pstmt.setString(index++,tgoodsstep.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsstep.getSkin_type()    ); logString.append( "/" );
            logString.append( tgoodsstep.getStep()         ); logString.append( "/" );
            logString.append( tgoodsstep.getSeq()          ); logString.append( "/" );
            logString.append( tgoodsstep.getGoods_code()   ); logString.append( "/" );
            logString.append( tgoodsstep.getInsert_id()    ); logString.append( "/" );
            logString.append( tgoodsstep.getModify_id()    ); logString.append( "/" );
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


    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsstep
    * </PRE>
    * @param   Connection
    * @param   Tgoodsstep
    * @return  int
    */
    public int delete(Connection con, Tgoodsstep tgoodsstep) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tgoodsstep));
            int index = 1;
            pstmt.setString(index++,tgoodsstep.getSkin_type());
            pstmt.setString(index++,tgoodsstep.getStep());
            pstmt.setString(index++,tgoodsstep.getSeq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsstep.getSkin_type()   ); logString.append( "/" );
            logString.append( tgoodsstep.getStep()  ); logString.append( "/" );
            logString.append( tgoodsstep.getSeq()  ); logString.append( "/" );
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
    * Desc : Check SEQ of Tgoodsstep
    * </PRE>
    * @param   Connection
    * @param   Tgoodsstep
    * @return  int
    */
    public int checkSeq(Connection con, Tgoodsstep tgoodsstep) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlSeqCheck(tgoodsstep));
            int index = 1;
            pstmt.setString(index++, tgoodsstep.getSkin_type());
            pstmt.setString(index++, tgoodsstep.getStep());
            pstmt.setString(index++, tgoodsstep.getSeq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsstep.getSkin_type()   ); logString.append( "/" );
            logString.append( tgoodsstep.getStep()  ); logString.append( "/" );
            logString.append( tgoodsstep.getSeq()  ); logString.append( "/" );
            logSave.info(logString.toString());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[GoodsstepSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsstepSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
}
