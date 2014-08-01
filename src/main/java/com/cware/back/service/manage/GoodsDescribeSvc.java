
package com.cware.back.service.manage;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.cware.back.entity.table.Tdescribe;


/**
 * Goods Describe Code Service class
 *
 * @version 1.0, 2009/06/8
 * @author commerceware.co.kr
 */
public class GoodsDescribeSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsDescribeSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT B.DESCRIBE_CODE,  \n");
        sb.append("         B.DESCRIBE_TITLE,  \n");
        sb.append("         A.DESCRIBE_EXT,  \n");
        sb.append("         A.WEB_FLAG,  \n");
        sb.append("         B.WEB_FLAG AS FIRST_WEB_FLAG,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         B.SORT_SEQ,  \n");
//        sb.append("         B.USE_YN,  \n");
        sb.append("         A.INSERT_DATE,  \n");
        sb.append("         A.INSERT_ID,  \n");
        sb.append("         A.MODIFY_DATE,  \n");
        sb.append("         A.MODIFY_ID  \n");
        sb.append("    FROM TDESCRIBE A,  \n");
        sb.append("         TDESCRIBECODE B  \n");
        sb.append("   WHERE A.DESCRIBE_CODE(+) = B.DESCRIBE_CODE  \n");
        sb.append("     AND A.GOODS_CODE(+)    = ?  \n");
        sb.append("ORDER BY A.DESCRIBE_CODE ASC, B.DESCRIBE_CODE  \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    public String makeSqlTdescribecode(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DESCRIBE_CODE,  \n");
        sb.append("         DESCRIBE_TITLE,  \n");
        sb.append("         '' AS DESCRIBE_EXT,  \n");
        sb.append("         WEB_FLAG,  \n");
        sb.append("         '' AS GOODS_CODE,  \n");
        sb.append("         SORT_SEQ,  \n");
        sb.append("         '' AS INSERT_DATE,  \n");
        sb.append("         '' AS INSERT_ID,  \n");
        sb.append("         '' AS MODIFY_DATE,  \n");
        sb.append("         '' AS MODIFY_ID  \n");
        sb.append("    FROM TDESCRIBECODE  \n");
//        sb.append("   WHERE USE_YN = '1'  \n");
        sb.append("   ORDER BY DESCRIBE_CODE   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    public String makeSqlTgoods(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT GOODS_NAME  \n");
        sb.append("    FROM TGOODS  \n");
        sb.append("   WHERE GOODS_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert  )
    * </PRE>
    * @param   Tdescribe
    * @return  String
    */

    private final String insertSql = " INSERT INTO TDESCRIBE ( \n "
                                    +"         DESCRIBE_CODE, \n "
                                    +"         DESCRIBE_TITLE, \n "
                                    +"         DESCRIBE_EXT, \n "
                                    +"         WEB_FLAG, \n "
                                    +"         GOODS_CODE, \n "
                                    +"         INSERT_DATE, \n "
                                    +"         INSERT_ID, \n "
                                    +"         MODIFY_DATE, \n "
                                    +"         MODIFY_ID ) \n "
                                    +" VALUES( \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ? ) \n ";
    private int insertSqlLog =  0;

    public String makeSqlInsert(Tdescribe tdescribe) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSql);
            }
            insertSqlLog = 1;
        }
        return insertSql;
    }

//    public String makeSqlInsert(Tdescribe tdescribe) throws StoreException{
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("  INSERT INTO TDESCRIBE ( 	\n ");
//        sb.append("         DESCRIBE_CODE,  	\n ");
//        sb.append("         DESCRIBE_TITLE,  	\n ");
//        sb.append("         DESCRIBE_NOTE, 		\n ");
//        sb.append("         WEB_FLAG,  			\n ");
//        sb.append("         GOODS_CODE,  		\n ");
//        sb.append("         INSERT_DATE,  		\n ");
//        sb.append("         INSERT_ID,  		\n ");
//        sb.append("         MODIFY_DATE,  		\n ");
//        sb.append("         MODIFY_ID )  		\n ");
//        sb.append("  VALUES (  \n ");
//        sb.append("'"+		tdescribe.getDescribe_code() + "',  	\n ");
//        sb.append("'"+		tdescribe.getDescribe_title() + "',  	\n ");
//        sb.append("'"+		tdescribe.getDescribe_note() + "',  	\n ");
//        sb.append("'"+		tdescribe.getWeb_flag() + "',  			\n ");
//        sb.append("'"+		tdescribe.getGoods_code() + "',  		\n ");
//        sb.append("         SYSDATE,  			\n ");
//        sb.append("'"+ 		tdescribe.getInsert_id() + "',  		\n ");
//        sb.append("         SYSDATE,  			\n ");
//        sb.append("'"+		tdescribe.getModify_id() + "')  		\n ");
//
//        //= log SQL -------------------------------------------------
//        if (logSave.isDebugEnabled()) {
//            logSave.debug(sb.toString());
//        }
//        return sb.toString();
//    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tdescribe
    * @return  String
    */
    private final String updateSql =  " UPDATE TDESCRIBE SET \n "
        							 +"        DESCRIBE_EXT = ?, \n "
                                     +"        WEB_FLAG = ?, \n "
                                     +"        MODIFY_DATE = SYSDATE, \n "
                                     +"        MODIFY_ID = ? \n "
                                     +"  WHERE GOODS_CODE = ? \n "
                                     +"    AND DESCRIBE_CODE = ? \n ";
    private int updateSqlLog =  0;

    public String makeSqlUpdate(Tdescribe tdescribe) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSql);
            }
            updateSqlLog = 1;
        }
        return updateSql;
    }
/*
    private final String updateClobSql =  " SELECT DESCRIBE_EXT \n "
                                     +"    FROM TDESCRIBE  \n "
                                     +"  WHERE GOODS_CODE = ? \n "
                                     +"    AND DESCRIBE_CODE = ? \n "
                                     +"  FOR UPDATE \n ";
    private int updateClobSqlLog =  0;
*/
/*    public String makeSqlUpdateClob(Tdescribe tdescribe) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateClobSqlLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateClobSql);
            }
            updateClobSqlLog = 1;
        }
        return updateClobSql;
    }
*/
//    public String makeSqlUpdate(Tdescribe tdescribe) throws StoreException{
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("  UPDATE TDESCRIBE SET \n ");
//        sb.append("         DESCRIBE_NOTE 		= '"+ tdescribe.getDescribe_note() 	+ "',  	\n ");
//        sb.append("         WEB_FLAG 			= '"+ tdescribe.getWeb_flag() 		+ "',  	\n ");
//        sb.append("         MODIFY_DATE 		= SYSDATE,  \n ");
//        sb.append("         MODIFY_ID 			= '"+ tdescribe.getModify_id() 		+ "'  	\n ");
//        sb.append("         WHERE GOODS_CODE 	= '"+ tdescribe.getGoods_code() 	+ "' 	\n ");
//        sb.append("         AND DESCRIBE_CODE 	= '"+ tdescribe.getDescribe_code() 	+ "' 	\n ");
//
//        //= log SQL -------------------------------------------------
//        if (logSave.isDebugEnabled()) {
//            logSave.debug(sb.toString());
//        }
//        return sb.toString();
//    }

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
        long         retRow     = 0;
        String       tempString = "";

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result : Tdescribecode
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
/**
    public HashMap[] makeSheetTdescribecode(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;
        String       tempString = "";

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            tempString = ComUtils.objToStr(hmSheet.get("DESCRIBE_NOTE"),"");
            tempString = ComUtils.replace(tempString, String.valueOf((char)13) , "\\r");
            tempString = ComUtils.replace(tempString, String.valueOf((char)10) , "\\n");

            hmSheet.put("DESCRIBE_NOTE", tempString);

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
*/

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL : Tdescribe
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
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsDescribeSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsDescribeSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL : Tdescribecode
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveTdescribecode(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlTdescribecode(retrieve));
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsDescribeSvc.retrieveTdescribecode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsDescribeSvc.retrieveTdescribecode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL : Tgoods
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */

    public String retrieveTgoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            result      = null;

        try {
            pstmt = con.prepareStatement(makeSqlTgoods(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) result = rset.getString(1);

        }catch(SQLException se){
            log.error("[GoodsDescribeSvc.retrieveTgoods() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsDescribeSvc.retrieveTgoods() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return result;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert
    * </PRE>
    * @param   Connection
    * @param   Tdescribe
    * @return  int
    */
    public int insert(Connection con, Tdescribe tdescribe) throws StoreException{
        PreparedStatement pstmt       = null;
//    	Statement         stmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

//        	stmt = con.createStatement();
            pstmt = con.prepareStatement(makeSqlInsert(tdescribe));
//        	pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;
            pstmt.setString(index++,tdescribe.getDescribe_code());
            pstmt.setString(index++,tdescribe.getDescribe_title());
            pstmt.setCharacterStream(index++, new StringReader(tdescribe.getDescribe_ext()), tdescribe.getDescribe_ext().length());

//            pstmt.setString(index++,tdescribe.getDescribe_note());
            pstmt.setString(index++,tdescribe.getWeb_flag());
            pstmt.setString(index++,tdescribe.getGoods_code());
            pstmt.setString(index++,tdescribe.getInsert_id());
            pstmt.setString(index++,tdescribe.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdescribe.getDescribe_code() ); logString.append( "/" );
            logString.append( tdescribe.getDescribe_title()); logString.append( "/" );
            logString.append( tdescribe.getDescribe_ext()  ); logString.append( "/" );
            logString.append( tdescribe.getWeb_flag()      ); logString.append( "/" );
            logString.append( tdescribe.getGoods_code()    ); logString.append( "/" );
            logString.append( tdescribe.getInsert_id()     ); logString.append( "/" );
            logString.append( tdescribe.getModify_id()     ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();
//            executedRtn = stmt.executeUpdate(makeSqlInsert(tdescribe));

        }catch(SQLException se){
            logSave.error("[GoodsDescribeSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsDescribeSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
//        	DBUtils.freeConnection(stmt, null, rset);
        	DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update
    * </PRE>
    * @param   Connection
    * @param   Tdescribe
    * @return  int
    */
    public int update(Connection con, Tdescribe tdescribe) throws StoreException{
        PreparedStatement pstmt       = null;
        Statement         stmt       = null;
        int executedRtn = 0;

        try {
//        	stmt = con.createStatement();

        	pstmt = con.prepareStatement(makeSqlUpdate(tdescribe));
            int index = 1;
            pstmt.setCharacterStream(index++, new StringReader(tdescribe.getDescribe_ext()), tdescribe.getDescribe_ext().length());
//            pstmt.setString(index++,tdescribe.getDescribe_ext());

            pstmt.setString(index++,tdescribe.getWeb_flag());
            pstmt.setString(index++,tdescribe.getModify_id());
            pstmt.setString(index++,tdescribe.getGoods_code());
            pstmt.setString(index++,tdescribe.getDescribe_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdescribe.getDescribe_ext() ); logString.append( "/" );
            logString.append( tdescribe.getWeb_flag()      ); logString.append( "/" );
            logString.append( tdescribe.getModify_id()     ); logString.append( "/" );
            logString.append( tdescribe.getGoods_code()    ); logString.append( "/" );
            logString.append( tdescribe.getDescribe_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

//            executedRtn = stmt.executeUpdate(makeSqlUpdate(tdescribe));

        }catch(SQLException se){
            logSave.error("[GoodsDescribeSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsDescribeSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
//        	DBUtils.freeConnection(stmt, null, null);
        	DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    /**
     * <PRE>
     * Desc : UpdateCLOB
     * </PRE>
     * @param   Connection
     * @param   Tdescribe
     * @return  int
     */
/*     public int updateClob(Connection con, Tdescribe tdescribe) throws StoreException{
    	 ResultSet rs = null;
         PreparedStatement pstmt = null;
         int executedRtn = 0;

         try {

         	pstmt = con.prepareStatement(makeSqlUpdateClob(tdescribe));
            int index = 1;

            pstmt.setString(index++,tdescribe.getGoods_code());
			pstmt.setString(index++,tdescribe.getDescribe_code());
			rs = pstmt.executeQuery();
			CLOB lob_loc = null;

			if (rs.next()) {
		        lob_loc = ((OracleResultSet)((DelegatingResultSet)rs).getDelegate()).getCLOB(1);
			}
			long pos = 0;
	        pos = lob_loc.length()+1;

	        lob_loc.putString(pos, tdescribe.getDescribe_ext());

			executedRtn = 1;

         }catch(SQLException se){
             logSave.error("[GoodsDescribeSvc.updateClob() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[GoodsDescribeSvc.updateClob() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
         	DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }
*/
    //  = Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlGoodsCheck( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(A.GOODS_CODE) AS GOODS_CNT   	\n");
        sb.append("   FROM TDESCRIBE A 				          	\n");
        sb.append("  WHERE A.GOODS_CODE = ? 			        \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Check GoodsCode
     * </PRE>
     * @param   Connection
     * @param   Tgoods
     * @return  String
     */
     public int chkExistGoodsCode(Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
         	pstmt = con.prepareStatement(makeSqlGoodsCheck(retrieve));

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logSave.info(logString.toString());

             rset = pstmt.executeQuery();

             if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
             executedRtn = rset.getInt(1);

         }catch(SQLException se){
             logSave.error("[Tdescribe.chkExistGoodsCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[Tdescribe.chkExistGoodsCode() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return executedRtn;
     }

}
