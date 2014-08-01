
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
import com.cware.back.entity.table.Tgoodsinfodt;
import com.cware.back.entity.table.Tgoodsinfom;

/**
 * Register MD Service class
 *
 * @version 1.0, 2006/07/13
 */
public class GoodsInfoSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsInfoSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CSPF_FLAG,     						\n");
        sb.append("        SUBSTR(A.CSPF_GROUP,2,3) CSPF_GROUP ,    \n");
        sb.append("        A.CSPF_DESC, 							\n");
        sb.append("        A.USE_YN,   								\n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE , \n");
        sb.append("        A.INSERT_ID  			 				\n");
        sb.append("   FROM TGOODSINFOM A 			 				\n");
        sb.append("  WHERE A.CSPF_FLAG LIKE ? 		 				\n");
        sb.append("     AND A.CSPF_GROUP LIKE ?		 				\n");

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

        sb.append(" SELECT B.CSPF_GROUP,  \n");
        sb.append("        B.CSPF_CODE, \n");
        sb.append("        B.CSPF_NAME,     \n");
        sb.append("        B.USE_YN,   \n");
        sb.append("        TO_CHAR(B.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE ,  \n");
        sb.append("        B.INSERT_ID   \n");
        sb.append("   FROM TGOODSINFODT B   \n");
        sb.append("  WHERE B.CSPF_GROUP LIKE ? \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tgoodsinfodt )
    * </PRE>
    * @param   Tgoodsinfodt
    * @return  String
    */
    private String makeSqlInsert(Tgoodsinfom tgoodsinfom) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TGOODSINFOM ( \n ");
        sb.append("          CSPF_FLAG,  \n ");
        sb.append("          CSPF_GROUP,  \n ");
        sb.append("          CSPF_DESC,   \n ");
        sb.append("          USE_YN,   \n ");
        sb.append("          INSERT_DATE, \n ");
        sb.append("          INSERT_ID )   \n ");
        sb.append("  VALUES (   \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODSINFOM )
    * </PRE>
    * @param   Tgoodsinfom
    * @return  String
    */
    private String makeSqlUpdate(Tgoodsinfom tgoodsinfom) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODSINFOM SET 	\n ");
        sb.append("         USE_YN 		= ? 	\n ");
        sb.append("   WHERE CSPF_GROUP  = ?  	\n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TGOODSINFODT )
    * </PRE>
    * @param   Tgoodsinfodt
    * @return  String
    */
    private final String insertSqlTgoodsinfodt =  " INSERT INTO TGOODSINFODT ( \n "
	                                             +"        CSPF_GROUP, \n "
	                                             +"        CSPF_CODE, \n "
	                                             +"        CSPF_NAME,    \n "
	                                             +"        USE_YN,  \n "
	                                             +"        INSERT_DATE, \n "
	                                             +"        INSERT_ID)   \n "
	                                             +"VALUES (   \n "
	                                             +"        ?, \n "
	                                             +"        ?, \n "
	                                             +"        ?, \n "
	                                             +"        ?, \n "
	                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
	                                             +"        ?) \n ";

    private int insertSqlLogTgoodsinfodt =  0;

    private String makeSqlInsert(Tgoodsinfodt tgoodsinfodt) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTgoodsinfodt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTgoodsinfodt);
            }
            insertSqlLogTgoodsinfodt = 1;
        }
        return insertSqlTgoodsinfodt;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODSINFODT )
    * </PRE>
    * @param   Tgoodsinfodt
    * @return  String
    */
    private final String updateSqlTgoodsinfodt = " UPDATE TGOODSINFODT SET \n "
	                                          + "         USE_YN      	= ? \n "
	                                          + "  WHERE CSPF_GROUP     = ? \n "
	                                          + "    AND CSPF_CODE     	= ? \n ";

    private int updateSqlLogTgoodsinfodt =  0;

    private String makeSqlUpdate(Tgoodsinfodt tgoodsinfodt) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTgoodsinfodt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTgoodsinfodt);
            }
            updateSqlLogTgoodsinfodt = 1;
        }
        return updateSqlTgoodsinfodt;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tgoodsinfom 구분코드  중복 체크 )
     * </PRE>
     * @param   Tgoodsinfom
     * @return  String
     */
     public String makeSqlDupCheck(Tgoodsinfom  tgoodsinfom) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT COUNT(CSPF_GROUP) 				\n ");
		 sb.append("  FROM TGOODSINFOM 						\n ");
		 sb.append(" WHERE CSPF_GROUP = ? 				    \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ( Tgoodsinfodt 사용자ID  중복 체크 )
      * </PRE>
      * @param   Tcode
      * @return  String
      */
      public String makeSqlDupCheck(Tgoodsinfodt  tgoodsinfodt) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("SELECT COUNT(CSPF_CODE) 				\n ");
 		  sb.append("  FROM TGOODSINFODT 					\n ");
 		  sb.append(" WHERE CSPF_GROUP = ? 				    \n ");
 		  sb.append("   AND CSPF_CODE  = ? 				    \n ");

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

            int index = 1;
            pstmt.setString(index++,retrieve.getString("cspf_flag"));
            pstmt.setString(index++,retrieve.getString("cspf_group"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsInfoSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsInfoSvc.retrieve() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,retrieve.getString("cspf_group"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DETAIL",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[GoodsInfoSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsInfoSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TGOODSINFOM
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfom
    * @return  int
    */
    public int insert(Connection con, Tgoodsinfom tgoodsinfom) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tgoodsinfom));
            int index = 1;

            pstmt.setString(index++,tgoodsinfom.getCspf_flag());
            pstmt.setString(index++,tgoodsinfom.getCspf_group());
            pstmt.setString(index++,tgoodsinfom.getCspf_desc());
            pstmt.setString(index++,tgoodsinfom.getUse_yn());
            pstmt.setString(index++,tgoodsinfom.getInsert_date());
            pstmt.setString(index++,tgoodsinfom.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsinfom.getCspf_flag()    ); logString.append( "/" );
            logString.append( tgoodsinfom.getCspf_group()   ); logString.append( "/" );
            logString.append( tgoodsinfom.getCspf_desc()    ); logString.append( "/" );
            logString.append( tgoodsinfom.getUse_yn()       ); logString.append( "/" );
            logString.append( tgoodsinfom.getInsert_date()  ); logString.append( "/" );
            logString.append( tgoodsinfom.getInsert_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.insert(tgoodsinfom) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.insert(tgoodsinfom) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TGOODSINFOM
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfom
    * @return  int
    */
    public int update(Connection con, Tgoodsinfom tgoodsinfom) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsinfom));
            int index = 1;
            pstmt.setString(index++,tgoodsinfom.getUse_yn() );
            pstmt.setString(index++,tgoodsinfom.getCspf_group());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsinfom.getUse_yn()      	); logString.append( "/" );
            logString.append( tgoodsinfom.getCspf_group()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.update(tgoodsinfom) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.update(tgoodsinfom) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TGOODSINFODT
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfodt
    * @return  int
    */
    public int insert(Connection con, Tgoodsinfodt tgoodsinfodt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tgoodsinfodt));
            int index = 1;
            pstmt.setString(index++,tgoodsinfodt.getCspf_group());
            pstmt.setString(index++,tgoodsinfodt.getCspf_code());
            pstmt.setString(index++,tgoodsinfodt.getCspf_name());
            pstmt.setString(index++,tgoodsinfodt.getUse_yn());
            pstmt.setString(index++,tgoodsinfodt.getInsert_date());
            pstmt.setString(index++,tgoodsinfodt.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsinfodt.getCspf_group()   ); logString.append( "/" );
            logString.append( tgoodsinfodt.getCspf_code()    ); logString.append( "/" );
            logString.append( tgoodsinfodt.getCspf_name()    ); logString.append( "/" );
            logString.append( tgoodsinfodt.getUse_yn()   	 ); logString.append( "/" );
            logString.append( tgoodsinfodt.getInsert_date()  ); logString.append( "/" );
            logString.append( tgoodsinfodt.getInsert_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.insert(tgoodsinfodt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.insert(tgoodsinfodt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TGOODSINFODT
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfodt
    * @return  int
    */
    public int update(Connection con, Tgoodsinfodt tgoodsinfodt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsinfodt));
            int index = 1;
            pstmt.setString(index++,tgoodsinfodt.getUse_yn());
            pstmt.setString(index++,tgoodsinfodt.getCspf_group());
            pstmt.setString(index++,tgoodsinfodt.getCspf_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsinfodt.getUse_yn()        ); logString.append( "/" );
            logString.append( tgoodsinfodt.getCspf_group()  ); logString.append( "/" );
            logString.append( tgoodsinfodt.getCspf_code()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tgoodsinfom
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfom
    * @return  int
    */
    public int checkDup(Connection con, Tgoodsinfom  tgoodsinfom) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tgoodsinfom));
            pstmt.setString(1, tgoodsinfom.getCspf_group());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.checkDup(Tgoodsinfom) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.checkDup(Tgoodsinfom) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tgoodsinfodt
    * </PRE>
    * @param   Connection
    * @param   Tgoodsinfodt
    * @return  int
    */
    public int checkDup(Connection con, Tgoodsinfodt  tgoodsinfodt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tgoodsinfodt));
            pstmt.setString(1, tgoodsinfodt.getCspf_group());
            pstmt.setString(2, tgoodsinfodt.getCspf_code());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[GoodsInfoSvc.checkDup(Tgoodsinfodt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsInfoSvc.checkDup(Tgoodsinfodt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}
