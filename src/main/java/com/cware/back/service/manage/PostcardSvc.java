
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
import com.cware.back.entity.table.Tpostcard;
import com.cware.back.entity.table.Tsaveget;

/**
 * Register Sample Service class
 *
 * @version 1.0, 2006/08/12
 */
public class PostcardSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PostcardSvc() {}

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

        sb.append("SELECT A.POSTCARD_NO,          							  \n");
        sb.append("       A.YYYYMM,								 			  \n");
        sb.append("       A.CUST_NO,              							  \n");
        sb.append("       B.CUST_NAME,            		   		 		      \n");
        sb.append("       A.GOODS_CODE,           							  \n");
        sb.append("       C.GOODS_NAME,           							  \n");
        sb.append("       A.IMAGE_FILE,           							  \n");
        sb.append("       A.IMAGE_FILE1,          							  \n");
        sb.append("       A.USE_TERM,            							  \n");
        sb.append("       TO_CHAR(A.MAKE_DATE,'YYYY/MM/DD') AS MAKE_DATE,	  \n");
        sb.append("       A.CONTENT,              							  \n");
        sb.append("       A.DISPLAY_YN,           							  \n");
        sb.append("       A.POINT_YN,                                         \n");
        sb.append("       A.DISPLAY_PRIORITY,                                 \n");
        sb.append("       A.INSERT_DATE,                                      \n");
        sb.append("       A.INSERT_ID,                                        \n");
        sb.append("       A.MODIFY_DATE,                                      \n");
        sb.append("       A.MODIFY_ID                                         \n");
        sb.append("  FROM TPOSTCARD A,                                        \n");
        sb.append("       TCUSTOMER B,                                        \n");
        sb.append("       TGOODS C                                            \n");
        sb.append(" WHERE A.CUST_NO    = B.CUST_NO(+)                         \n");
        sb.append("   AND A.GOODS_CODE = C.GOODS_CODE(+)                      \n");
        sb.append("   AND A.YYYYMM    >= SUBSTR(?,1,4) || SUBSTR(?,6,8)       \n");
        sb.append("   AND A.YYYYMM    <= SUBSTR(?,1,4) || SUBSTR(?,6,8)       \n");
        sb.append("   AND NVL(A.CUST_NO, ' ')    LIKE ?||'%'                  \n");
        sb.append("   AND NVL(A.GOODS_CODE, ' ') LIKE ?||'%'                  \n");
          sb.append("	ORDER BY A.YYYYMM DESC , A.DISPLAY_PRIORITY ASC  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
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
    public String makeSqlTcode() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT REMARK1            							\n");
        sb.append(" FROM  TCODE    	           							\n");
        sb.append(" WHERE CODE_LGROUP=?                 				\n");
		sb.append("	AND   CODE_MGROUP=?	  								\n");
	         //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tpostcard 변경 )
     * </PRE>
     * @param   Tpostcard
     * @return  String
     */
     public String makeSqlUpdate(Tpostcard tpostcard) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("  UPDATE  TPOSTCARD 	  	 					 \n ");
         sb.append("	 SET  YYYYMM =  ?, 						     \n ");
         sb.append("		  GOODS_CODE = ?, 						 \n ");
         sb.append("		  DISPLAY_YN = ?, 						 \n ");
          sb.append("		  USE_TERM = ?, 						 \n ");
         sb.append("		  MAKE_DATE =  TO_DATE(?,'YYYY/MM/DD'),  \n ");
         sb.append("	   	  IMAGE_FILE = ?, 						 \n ");
         sb.append("		  IMAGE_FILE1 = ?, 						 \n ");
         sb.append("		  CONTENT = ?, 							 \n ");
         sb.append("		  DISPLAY_PRIORITY = ?, 				 \n ");
         sb.append("		  MODIFY_ID =  ?,                        \n ");
         sb.append("		  MODIFY_DATE =  SYSDATE                 \n ");
         sb.append("	WHERE POSTCARD_NO =? 						 \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ( insert Tpostcard )
      * </PRE>
      * @param   Tpostcard
      * @return  String
      */
      public String makeSqlInsert(Tpostcard tpostcard) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("  INSERT INTO TPOSTCARD 	  	 	 ( \n ");
          sb.append("         	   POSTCARD_NO,    		   \n ");
          sb.append("         	   YYYYMM,      		   \n ");
          sb.append("          	   CUST_NO,            	   \n ");
          sb.append("              GOODS_CODE,             \n ");
          sb.append("              DISPLAY_YN,      	   \n ");
          sb.append("          	   POINT_YN,    		   \n ");
          sb.append("              USE_TERM,       		   \n ");
          sb.append("              MAKE_DATE,     		   \n ");
          sb.append("              IMAGE_FILE,       	   \n ");
          sb.append("              IMAGE_FILE1,       	   \n ");
          sb.append("              CONTENT,      		   \n ");
          sb.append("              DISPLAY_PRIORITY,       \n ");
          sb.append("              INSERT_ID,   	 	   \n ");
          sb.append("              INSERT_DATE,   	 	   \n ");
          sb.append("              MODIFY_ID,   	 	   \n ");
          sb.append("              MODIFY_DATE )   	 	   \n ");
          sb.append(" 	    VALUES (   \n ");
          sb.append("      		    ?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("          		?, \n ");
          sb.append("          		?, \n ");
          sb.append("          		TO_DATE(?, 'yyyy/mm/dd'), \n ");
          sb.append("          		?, \n ");
          sb.append("          		?, \n ");
          sb.append("          		?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("      		    ?, \n ");
          sb.append("          		SYSDATE, \n ");
          sb.append("      		    ?, \n ");
          sb.append("          		SYSDATE ) \n ");

          //= log SQL -------------------------------------------------
          if (logSave.isDebugEnabled()) {
              logSave.debug(sb.toString());
          }

          return sb.toString();
      }


      //= TSAVEAMT -------------------------------------------------
      /**
      * <PRE>
      * Desc : Make SQL ; Insert TSAVEGET
      * </PRE>
      * @param   Tsaveget
      * @return  String
      */

      private final String insertSqlTsaveget = " INSERT INTO TSAVEGET \n"
										          + "    ( CUST_NO, \n"
										          + "      SAVEAMT_SEQ, \n"
										          + "      PROC_GB, \n"
										          + "      PROC_YN, \n"
										          + "      SAVEAMT_GB, \n"
										          + "      SAVEAMT_CODE, \n"
										          + "      SAVE_NOTE, \n"
										          + "      SAVEAMT, \n"
										          + "      PROC_ID, \n"
										          + "      PROC_DATE, \n"
		                                          + "      USABLE_AMT,   \n"
		                                          + "      EXPIRE_FLAG , \n"
										          + "      DUE_DATE )   \n "
										      + "VALUES   (?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      ?, \n"
										          + "      TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n"
		                                          + "      ?,  \n "
		                                          + "      ?,  \n "
										          + "      TO_DATE(?, 'YYYY/MM/DD') + TCODE_GROUP('C026', ?) )   \n "  ;

      private int insertSqlTsavegetLog =  0;

      private String makeSqlInsert(Tsaveget tsaveget) throws StoreException{
          //= log SQL -------------------------------------------------
          if (insertSqlTsavegetLog == 0) {
              if (logSave.isDebugEnabled()) {
                  logSave.debug("\n" + insertSqlTsaveget);
              }
              insertSqlTsavegetLog = 1;
          }
          return insertSqlTsaveget;
      }

      //= TCUSTSYSTEM -------------------------------------------------
      /**
      * <PRE>
      * Desc : Make SQL ; Update Tcustsystem
      * </PRE>
      * @param   Tcustsystem
      * @return  String
      */

//      public String makeSqlUpdateSaveamt() throws Exception {
//          StringBuffer sb = new StringBuffer();
//          sb.append("UPDATE TCUSTSYSTEM         \n");
//          sb.append("   SET USABLE_SAVEAMT = NVL(USABLE_SAVEAMT,0) + ?,	          \n");
//          sb.append("       TOT_SAVEAMT    = NVL(TOT_SAVEAMT,0) + ?,	          \n");
//          sb.append("       MODIFY_DATE    = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n");
//          sb.append("       MODIFY_ID      = ?  \n");
//          sb.append(" WHERE CUST_NO        = ?  \n");
//
//          //= log SQL -------------------------------------------------
//          if(logSave.isDebugEnabled()){
//              logSave.debug("\n" + sb.toString());
//          }
//          return sb.toString();
//      }


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
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("cust_no"));
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

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
    public Double retrieveTcode(Connection con) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        double            remark1     =0;
        try {
        	pstmt = con.prepareStatement(makeSqlTcode());
            int index = 1;
            pstmt.setString(index++, "C026");
            pstmt.setString(index++, "608");
        	rset  = pstmt.executeQuery();
        	rset.next();
            remark1 = rset.getDouble(1);
            logSave.info(remark1);

        }catch(SQLException se){
        	 log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	 log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return remark1;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpostcard
    * </PRE>
    * @param   Connection
    * @param   Tpostcard
    * @return  int
    */
    public int update(Connection con, Tpostcard tpostcard) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpostcard));
            int index = 1;
            pstmt.setString(index++,tpostcard.getYyyymm());
            pstmt.setString(index++,tpostcard.getGoods_code());
            pstmt.setString(index++,tpostcard.getDisplay_yn());
            pstmt.setString(index++,tpostcard.getUse_term());
            pstmt.setString(index++,tpostcard.getMake_date());
            pstmt.setString(index++,tpostcard.getImage_file());
            pstmt.setString(index++,tpostcard.getImage_file1());
            pstmt.setString(index++,tpostcard.getContent());
            pstmt.setString(index++,tpostcard.getDisplay_priority());
            pstmt.setString(index++,tpostcard.getModify_id());
            pstmt.setString(index++,tpostcard.getPostcard_no());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpostcard.getYyyymm()      	);  logString.append( "/" );
            logString.append( tpostcard.getGoods_code()   	);  logString.append( "/" );
            logString.append( tpostcard.getDisplay_yn()	 	);  logString.append( "/" );
            logString.append( tpostcard.getUse_term()  	 	);  logString.append( "/" );
            logString.append( tpostcard.getMake_date()    	);  logString.append( "/" );
            logString.append( tpostcard.getImage_file()   	);  logString.append( "/" );
            logString.append( tpostcard.getImage_file1()  	);  logString.append( "/" );
            logString.append( tpostcard.getContent()      	);  logString.append( "/" );
            logString.append( tpostcard.getDisplay_priority()); logString.append( "/" );
            logString.append( tpostcard.getModify_id()  	);  logString.append( "/" );
            logString.append( tpostcard.getPostcard_no()  	);  logString.append( "/" );


            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PostcardSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PostcardSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tpostcard
    * </PRE>
    * @param   Connection
    * @param   Tpostcard
    * @return  int
    */
    public int insert(Connection con, Tpostcard tpostcard) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpostcard));
            int index = 1;
            pstmt.setString(index++,tpostcard.getPostcard_no());
            pstmt.setString(index++,tpostcard.getYyyymm());
            pstmt.setString(index++,tpostcard.getCust_no());
            pstmt.setString(index++,tpostcard.getGoods_code());
            pstmt.setString(index++,tpostcard.getDisplay_yn());
            pstmt.setString(index++,tpostcard.getPoint_yn());
            pstmt.setString(index++,tpostcard.getUse_term());
            pstmt.setString(index++,tpostcard.getMake_date());
            pstmt.setString(index++,tpostcard.getImage_file());
            pstmt.setString(index++,tpostcard.getImage_file1());
            pstmt.setString(index++,tpostcard.getContent());
            pstmt.setString(index++,tpostcard.getDisplay_priority());
            pstmt.setString(index++,tpostcard.getInsert_id());
            pstmt.setString(index++,tpostcard.getModify_id());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpostcard.getPostcard_no()  	 ); logString.append( "/" );
            logString.append( tpostcard.getYyyymm()      	 ); logString.append( "/" );
            logString.append( tpostcard.getCust_no()      	 ); logString.append( "/" );
            logString.append( tpostcard.getGoods_code()   	 ); logString.append( "/" );
            logString.append( tpostcard.getDisplay_yn()	 	 ); logString.append( "/" );
            logString.append( tpostcard.getPoint_yn()  	 	 ); logString.append( "/" );
            logString.append( tpostcard.getUse_term()  	 	 ); logString.append( "/" );
            logString.append( tpostcard.getMake_date()    	 ); logString.append( "/" );
            logString.append( tpostcard.getImage_file()   	 ); logString.append( "/" );
            logString.append( tpostcard.getImage_file1()  	 ); logString.append( "/" );
            logString.append( tpostcard.getContent()      	 ); logString.append( "/" );
            logString.append( tpostcard.getDisplay_priority()); logString.append( "/" );
            logString.append( tpostcard.getInsert_id()		 ); logString.append( "/" );
            logString.append( tpostcard.getModify_id()		 ); logString.append( "/" );


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

    /**
     * <PRE>
     * Desc : Insert TSAVEGET
     * </PRE>
     * @param   Connection
     * @param   Tsaveget
     * @return  int
     */

     public int insert(Connection con, Tsaveget tsaveget) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
             pstmt = con.prepareStatement(makeSqlInsert(tsaveget));
             int index = 1;

             pstmt.setString(index++, tsaveget.getCust_no()       );
             pstmt.setString(index++, tsaveget.getSaveamt_seq()   );
             pstmt.setString(index++, tsaveget.getProc_gb()       );
             pstmt.setString(index++, tsaveget.getProc_yn()       );
             pstmt.setString(index++, tsaveget.getSaveamt_gb()    );
             pstmt.setString(index++, tsaveget.getSaveamt_code()  );
             pstmt.setString(index++, tsaveget.getSave_note()     );
             pstmt.setDouble(index++, tsaveget.getSaveamt()       );
             pstmt.setString(index++, tsaveget.getProc_id()       );
             pstmt.setString(index++, tsaveget.getProc_date()     );
             pstmt.setDouble(index++, tsaveget.getUsable_amt()    );
             pstmt.setString(index++, tsaveget.getExpire_flag()   );
             pstmt.setString(index++, tsaveget.getDue_date()      );
             pstmt.setString(index++, tsaveget.getSaveamt_code()  );
//             pstmt.setLong  (index++, tsaveget.getLimit_day()   );


             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tsaveget.getCust_no()        ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt_seq()    ); logString.append( "/" );
             logString.append( tsaveget.getProc_gb()        ); logString.append( "/" );
             logString.append( tsaveget.getProc_yn()        ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt_gb()     ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt_code()   ); logString.append( "/" );
             logString.append( tsaveget.getSave_note()      ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt()        ); logString.append( "/" );
             logString.append( tsaveget.getProc_id()        ); logString.append( "/" );
             logString.append( tsaveget.getProc_date()      ); logString.append( "/" );
             logString.append( tsaveget.getUsable_amt()     ); logString.append( "/" );
             logString.append( tsaveget.getExpire_flag()    ); logString.append( "/" );
             logString.append( tsaveget.getDue_date()       ); logString.append( "/" );
             logString.append( tsaveget.getSaveamt_code()   ); logString.append( "/" );
//             logString.append( tsaveget.getLimit_day()    ); logString.append( "/" );

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

     /**
      * <PRE>
      * Desc : Update Tcustsystem
      * </PRE>
      * @param   Connection
      * @param   Tcustsystem
      * @return  int
      */

//      public int update(Connection con, Tcustsystem tcustsystem) throws StoreException{
//          PreparedStatement pstmt       = null;
//          ResultSet         rset        = null;
//          int executedRtn = 0;
//
//          try {
//
//              pstmt = con.prepareStatement(makeSqlUpdateSaveamt());
//              int index = 1;
//
//              pstmt.setDouble(index++, tcustsystem.getUsable_saveamt());
//              pstmt.setDouble(index++, tcustsystem.getTot_saveamt());
//              pstmt.setString(index++, tcustsystem.getModify_date()   );
//              pstmt.setString(index++, tcustsystem.getModify_id()     );
//              pstmt.setString(index++, tcustsystem.getCust_no()       );
//
//              //= log Save Data ---------------------
//              StringBuffer logString = new StringBuffer();
//              logString.append( tcustsystem.getUsable_saveamt() ); logString.append( "/" );
//              logString.append( tcustsystem.getTot_saveamt()  ); logString.append( "/" );
//              logString.append( tcustsystem.getModify_date()    ); logString.append( "/" );
//              logString.append( tcustsystem.getModify_id()      ); logString.append( "/" );
//              logString.append( tcustsystem.getCust_no()        ); logString.append( "/" );
//              logSave.info(logString.toString());
//
//              executedRtn = pstmt.executeUpdate();
//          }catch(SQLException se){
//              logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//              throw new StoreException(se.getMessage());
//          }catch(Exception e){
//              logSave.error("Exception : ERR-"+e.getMessage());
//              throw new StoreException(e.getMessage());
//          }finally {
//              DBUtils.freeConnection(null, pstmt, rset);
//          }
//          return executedRtn;
//      }


}
