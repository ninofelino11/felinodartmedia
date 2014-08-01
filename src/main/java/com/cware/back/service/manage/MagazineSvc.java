
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
import com.cware.back.entity.table.Tmagazinedt;
import com.cware.back.entity.table.Tmagazinem;



/**
 * Register Sample Service class
 *
 * @version 1.0, 2006/08/12
 */
public class MagazineSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MagazineSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT 	 A.MAGAZINE_NO,                                              						 	 \n");
        sb.append("       	 A.MAGAZINE_TITLE, 	                                      						 	 	 \n");
        sb.append("       	 A.MAGAZINE_CONTENT,                                         						 	 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_L,                                         						 	 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_M,                                         						 	 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_S,                                         						 	 \n");
        sb.append("       	 TO_CHAR(A.MAGAZINE_MONTH,'YYYY/MM') AS MAGAZINE_MONTH,	  						 	 	 \n");
        sb.append("       	 A.DISPLAY_YN,                                               	   					 	 \n");
        sb.append("       	 TO_CHAR(A.DISPLAY_BEGIN_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_BEGIN_DATE,        	 \n");
        sb.append("       	 TO_CHAR(A.DISPLAY_END_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,            	 \n");
        sb.append("       	 A.DELETE_YN,                                               					     	 \n");
        sb.append("       	 A.DISPLAY_PRIORITY,                                        						 	 \n");
        sb.append("       	 A.INSERT_ID,                                                 						 	 \n");
        sb.append("       	 A.INSERT_DATE,                                              						 	 \n");
        sb.append("       	 A.MODIFY_ID,                                                						 	 \n");
        sb.append("       	 A.MODIFY_DATE,                                              					     	 \n");
        sb.append("       	 '' AS SOURCE_PATH1,                                         						 	 \n");
        sb.append("       	 '' AS SOURCE_PATH2,                                        						 	 \n");
        sb.append("       	 '' AS SOURCE_PATH3                                          						 	 \n");
        sb.append("  FROM 	 TMAGAZINEM A                                           						 	 \n");
        sb.append("  WHERE   A.DISPLAY_BEGIN_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD HH24:MI:SS') AND TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("    OR    A.DISPLAY_END_DATE   BETWEEN TO_DATE(?,'YYYY/MM/DD HH24:MI:SS') AND TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("    OR    A.DISPLAY_BEGIN_DATE <= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')  AND A.DISPLAY_END_DATE >= TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("    AND 	 A.MAGAZINE_NO LIKE ?||'%'    								  					     	 \n");
        sb.append("	ORDER BY A.MAGAZINE_NO DESC													   		     	 	 \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("sum_fromDate       : " + retrieve.getString("sum_fromDate"));
            log.debug("sum_toDate 	      : " + retrieve.getString("sum_toDate"));
            log.debug("magazine_no    : " + retrieve.getString("magazine_no"));

        }
        return sb.toString();
    }

//  = Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param
    * @return  String
    */
    public String makeSqlNo( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT 	 A.MAGAZINE_NO,                                              						 \n");
        sb.append("       	 A.MAGAZINE_TITLE, 	                                      						 	 \n");
        sb.append("       	 A.MAGAZINE_CONTENT,                                         						 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_L,                                         						 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_M,                                         						 \n");
        sb.append("       	 A.MAGAZINE_IMAGE_S,                                         						 \n");
        sb.append("       	 TO_CHAR(A.MAGAZINE_MONTH,'YYYY/MM') AS MAGAZINE_MONTH,	  						 	 \n");
        sb.append("       	 A.DISPLAY_YN,                                               	   					 \n");
        sb.append("       	 TO_CHAR(A.DISPLAY_BEGIN_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_BEGIN_DATE,        \n");
        sb.append("       	 TO_CHAR(A.DISPLAY_END_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,            \n");
        sb.append("       	 A.DELETE_YN,                                               					     \n");
        sb.append("       	 A.DISPLAY_PRIORITY,                                        						 \n");
        sb.append("       	 A.INSERT_ID,                                                 						 \n");
        sb.append("       	 A.INSERT_DATE,                                              						 \n");
        sb.append("       	 A.MODIFY_ID,                                                						 \n");
        sb.append("       	 A.MODIFY_DATE,                                              					     \n");
        sb.append("       	 '' AS SOURCE_PATH1,                                         						 \n");
        sb.append("       	 '' AS SOURCE_PATH2,                                        						 \n");
        sb.append("       	 '' AS SOURCE_PATH3                                          						 \n");
        sb.append("  FROM 	 TMAGAZINEM A                                           						 \n");
        sb.append(" WHERE 	 A.MAGAZINE_NO LIKE ?||'%'  											 			 \n");
        sb.append("	ORDER BY A.MAGAZINE_NO DESC													   		     	 \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("magazine_no    : " + retrieve.getString("magazine_no"));

        }
        return sb.toString();
    }


    public String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT  A.MAGAZINE_NO,   				\n");
        sb.append("  	     A.MAGAZINE_PAGE, 				\n");
        sb.append("  		 A.MAGAZINE_PAGE_TITLE,	 		\n");
        sb.append("   		 A.MAGAZINE_PAGE_NAME, 			\n");
        sb.append("  		 A.DISPLAY_PRIORITY, 			\n");
        sb.append("   		 A.DISPLAY_GUBUN, 				\n");
        sb.append("   		 A.INSERT_ID, 					\n");
        sb.append("   		 A.INSERT_DATE, 				\n");
        sb.append("   		 A.MODIFY_ID, 					\n");
        sb.append("  		 A.MODIFY_DATE 					\n");
        sb.append("	FROM	 tmagazinedt A 		        	\n");
        sb.append("	WHERE 	 A.MAGAZINE_NO = ?	    		\n");
        sb.append(" ORDER BY A.MAGAZINE_NO, A.MAGAZINE_PAGE \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("magazine_no    : " + retrieve.getString("magazine_no"));

        }
        return sb.toString();
    }


    public String makeSqlUpdateM(Tmagazinem tmagazine) throws StoreException{

    	StringBuffer sb = new StringBuffer();

	    sb.append("UPDATE TMAGAZINEM 	  	 	   			 					   		\n");
	    sb.append("	 SET  MAGAZINE_TITLE     = ?, 						 			   		\n");
	    sb.append("	  	  MAGAZINE_MONTH     = TO_DATE(?||'/01','YYYY/MM/DD'),  	  		\n");
	    sb.append("       MAGAZINE_IMAGE_L   = ?,                                      		\n");
	    sb.append("       MAGAZINE_IMAGE_M   = ?,                                      		\n");
	    sb.append("       MAGAZINE_IMAGE_S   = ?,                                      		\n");
	    sb.append("       MAGAZINE_CONTENT   = ?, 									   		\n");
	    sb.append("	  	  DISPLAY_YN         = ?, 						 			   		\n");
	    sb.append("       DISPLAY_BEGIN_DATE =  TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),         \n");
	    sb.append("	  	  DISPLAY_END_DATE   =  TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),    		\n");
	    sb.append("       DISPLAY_PRIORITY   = ?, 									   		\n");
	    sb.append("	  	  MODIFY_ID 		 = ?,                 					   		\n");
	    sb.append("	      MODIFY_DATE 		 = 	SYSDATE            					   		\n");
	    sb.append("WHERE  MAGAZINE_NO =? 						                       		\n");

	    //= log SQL -------------------------------------------------
	    if (logSave.isDebugEnabled()) {
	    	logSave.debug(sb.toString());
	    }
    	return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( insert Tmagazinem )
     * </PRE>
     * @param   Tmagazinem
     * @return  String
     */
     public String makeSqlInsertM(Tmagazinem tmagazinem) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append(" INSERT INTO  TMAGAZINEM ( 				                 \n");
         sb.append("         	  MAGAZINE_NO,    		                             \n");
         sb.append("         	  MAGAZINE_TITLE,      		                         \n");
         sb.append("        	  MAGAZINE_CONTENT,                    	             \n");
         sb.append("              MAGAZINE_IMAGE_L,                                  \n");
         sb.append("              MAGAZINE_IMAGE_M,                                  \n");
         sb.append("         	  MAGAZINE_IMAGE_S,    		   		                 \n");
         sb.append("              MAGAZINE_MONTH,       	      	                 \n");
         sb.append("              DISPLAY_YN,     		 	                         \n");
         sb.append("              DISPLAY_BEGIN_DATE,       	    	             \n");
         sb.append("              DISPLAY_END_DATE,       	    	                 \n");
         sb.append("              DELETE_YN,      		    		                 \n");
         sb.append("              DISPLAY_PRIORITY,       				             \n");
         sb.append("              REMARK,   	 	     	                         \n");
         sb.append("              REMARK1_V,   	                                     \n");
         sb.append("              REMARK2_V,   	 	                                 \n");
         sb.append("              REMARK3_N, 							             \n");
         sb.append("              REMARK4_N, 						                 \n");
         sb.append("              INSERT_ID, 						                 \n");
         sb.append("              INSERT_DATE, 						                 \n");
         sb.append("              MODIFY_ID, 					                     \n");
         sb.append("              MODIFY_DATE )   	 	   			                 \n");
         sb.append("  VALUES (	  ?,                                                 \n");
         sb.append("   		   	  ?,                                                 \n");
         sb.append("   		      ?,                                                 \n");
         sb.append("   		   	  ?,                                           	     \n");
         sb.append("   		      ?,                                           	     \n");
         sb.append("   		      ?,                                          		 \n");
         sb.append("       	      TO_DATE(?||'/01','YYYY/MM/DD'),              	     \n");
         sb.append("       	      ?,                                           	     \n");
         sb.append("       	      TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),          	     \n");
         sb.append("       	      TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),          	     \n");
         sb.append("       	      ?,                                           	     \n");
         sb.append("  		      ?,                                             	 \n");
         sb.append("  		      ?,                                             	 \n");
         sb.append("       	      ?,                                           	     \n");
         sb.append("   		      ?,                                           	     \n");
         sb.append("          	  ?,                                                 \n");
         sb.append("              ?,                                          		 \n");
         sb.append("              ?,                                          		 \n");
         sb.append("              SYSDATE,  					                     \n");
         sb.append("          	  ?, 			 					                 \n");
         sb.append("              SYSDATE )                                   		 \n");

         //= log SQL -------------------------------------------------
 	    if (logSave.isDebugEnabled()) {
 	    	logSave.debug(sb.toString());
 	    }
     	return sb.toString();
    }
     /**
      * <PRE>
      * Desc : Make SQL ( Update Tmagazinedt 변경 )
      * </PRE>
      * @param   Tmagazinedt
      * @return  String
      */
     private final String updateSqltmagazinedt = " UPDATE tmagazinedt SET                  \n "
    	 											+ "        MAGAZINE_PAGE  			 = ?, 		 \n "
     												+ "        DISPLAY_GUBUN  		 	 = ?,   	 \n "
     												+ "        MAGAZINE_PAGE_TITLE  	 = ?, 		 \n "
                                             		+ "        MAGAZINE_PAGE_NAME  		 = ?,   	 \n "
                                             		+ "        DISPLAY_PRIORITY  		 = ?,     	 \n "
                                             		+ "        MODIFY_ID 				 = ?,  		 \n "
                                             		+ "        MODIFY_DATE  			 = SYSDATE   \n "
                                             		+ "  WHERE MAGAZINE_NO    			 = ?   	 	 \n "
     												+ "  AND   MAGAZINE_PAGE    		 = ?   	 	 \n "
     												+ "  AND   DISPLAY_GUBUN    		 = ?   	 	 \n ";

     private int updateSqltmagazinedtLog =  0;

     private String makeSqlUpdate(Tmagazinedt tmagazinedt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqltmagazinedtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqltmagazinedt);
     		}
     	   updateSqltmagazinedtLog = 1;
     	}
     	return updateSqltmagazinedt;
      }


     /**
      * <PRE>
      * Desc : Make SQL ( Insert Tmagazinedt )
      * </PRE>
      * @param
      * @return  String SQL
      */
      private final String insertSqlMagazine = "   INSERT INTO tmagazinedt (  \n "
      								+"                    MAGAZINE_NO,    		    	\n "
      								+"                    MAGAZINE_PAGE,   		   	    \n "
      								+"                    DISPLAY_GUBUN,     			\n "
      								+"                    MAGAZINE_PAGE_TITLE,      	\n "
      								+"                    MAGAZINE_PAGE_NAME,       	\n "
      								+"                    DISPLAY_PRIORITY,     		\n "
      								+"                    INSERT_ID,     				\n "
      								+"                    INSERT_DATE,    				\n "
      								+"                    MODIFY_ID,     				\n "
      								+"                    MODIFY_DATE)     				\n "
      								+"             VALUES (  	       	   				\n "
      								+"                    	  ?,       	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  ?, 	   	   				\n "
      								+"                    	  SYSDATE, 	   				\n "
          							+"                    	  ?, 		   				\n "
      								+"                        SYSDATE)	   				\n " ;

      private int insertSqlMagazineLog =  0;

      public String makeSqlInsert(Tmagazinedt tMagazindedt) throws StoreException{
  	   	//= log SQL -------------------------------------------------
  		if (insertSqlMagazineLog == 0) {
  			if (logSave.isDebugEnabled()) {
  				logSave.debug(insertSqlMagazine);
  			}
  			insertSqlMagazineLog = 1;
  		}
  		return insertSqlMagazine;
  	}

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tmagazinem
    * </PRE>
    * @param   Connection
    * @param   Tpostcard
    * @return  int
    */
    public int update(Connection con, Tmagazinem tmagazinem) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateM(tmagazinem));
            int index = 1;
            pstmt.setString(index++,tmagazinem.getMagazine_title());
            pstmt.setString(index++,tmagazinem.getMagazine_month());
            pstmt.setString(index++,tmagazinem.getMagazine_image_l());
            pstmt.setString(index++,tmagazinem.getMagazine_image_m());
            pstmt.setString(index++,tmagazinem.getMagazine_image_s());
            pstmt.setString(index++,tmagazinem.getMagazine_content());
            pstmt.setString(index++,tmagazinem.getDisplay_yn());
            pstmt.setString(index++,tmagazinem.getDisplay_begin_date());
            pstmt.setString(index++,tmagazinem.getDisplay_end_date());
            pstmt.setString(index++,tmagazinem.getDisplay_priority());
            pstmt.setString(index++,tmagazinem.getModify_id());
            pstmt.setString(index++,tmagazinem.getMagazine_no());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmagazinem.getMagazine_title());     logString.append( "/" );
            logString.append( tmagazinem.getMagazine_month());     logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_l());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_m());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_s());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_content());   logString.append( "/" );
            logString.append( tmagazinem.getDisplay_yn()); 		logString.append( "/" );
            logString.append( tmagazinem.getDisplay_begin_date()); logString.append( "/" );
            logString.append( tmagazinem.getDisplay_end_date()); 	logString.append( "/" );
            logString.append( tmagazinem.getDisplay_priority()); 	logString.append( "/" );
            logString.append( tmagazinem.getModify_id());   		logString.append( "/" );
            logString.append( tmagazinem.getMagazine_no());        logString.append( "/" );


            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MagazineSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MagazineSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

//  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TMAGAZINEM
    * </PRE>
    * @param   Connection
    * @param   tmagazinem
    * @return  int
    */
    public int insert(Connection con, Tmagazinem tmagazinem) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertM(tmagazinem));
            int index = 1;
            pstmt.setString(index++,tmagazinem.getMagazine_no());
            pstmt.setString(index++,tmagazinem.getMagazine_title());
            pstmt.setString(index++,tmagazinem.getMagazine_content());
            pstmt.setString(index++,tmagazinem.getMagazine_image_l());
            pstmt.setString(index++,tmagazinem.getMagazine_image_m());
            pstmt.setString(index++,tmagazinem.getMagazine_image_s());
            pstmt.setString(index++,tmagazinem.getMagazine_month());
            pstmt.setString(index++,tmagazinem.getDisplay_yn());
            pstmt.setString(index++,tmagazinem.getDisplay_begin_date());
            pstmt.setString(index++,tmagazinem.getDisplay_end_date());
            pstmt.setString(index++,tmagazinem.getDelete_yn());
            pstmt.setString(index++,tmagazinem.getDisplay_priority());
            pstmt.setString(index++,tmagazinem.getRemark());
            pstmt.setString(index++,tmagazinem.getRemark1_v());
            pstmt.setString(index++,tmagazinem.getRemark2_v());
            pstmt.setString(index++,tmagazinem.getRemark3_n());
            pstmt.setString(index++,tmagazinem.getRemark4_n());
            pstmt.setString(index++,tmagazinem.getInsert_id());
            pstmt.setString(index++,tmagazinem.getModify_id());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tmagazinem.getMagazine_no());  	    logString.append( "/" );
            logString.append( tmagazinem.getMagazine_title());     logString.append( "/" );
            logString.append( tmagazinem.getMagazine_content());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_l());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_m());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_image_s());   logString.append( "/" );
            logString.append( tmagazinem.getMagazine_month());     logString.append( "/" );
            logString.append( tmagazinem.getDisplay_yn()); 	    logString.append( "/" );
            logString.append( tmagazinem.getDisplay_begin_date()); logString.append( "/" );
            logString.append( tmagazinem.getDisplay_end_date());   logString.append( "/" );
            logString.append( tmagazinem.getDelete_yn()); 			logString.append( "/" );
            logString.append( tmagazinem.getDisplay_priority()); 	logString.append( "/" );
            logString.append( tmagazinem.getRemark()); 			logString.append( "/" );
            logString.append( tmagazinem.getRemark1_v()); 			logString.append( "/" );
            logString.append( tmagazinem.getRemark2_v()); 			logString.append( "/" );
            logString.append( tmagazinem.getRemark3_n()); 			logString.append( "/" );
            logString.append( tmagazinem.getRemark4_n()); 			logString.append( "/" );
            logString.append( tmagazinem.getInsert_id());			logString.append( "/" );
            logString.append( tmagazinem.getModify_id()); 		 	logString.append( "/" );


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

//  = Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tmagazinedt
    * </PRE>
    * @param   Connection
    * @param   TMagazindedt
    * @return  int
    *
    *
    */

    public int update(Connection con, Tmagazinedt tmagazinedt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tmagazinedt));

            int index = 1;

            pstmt.setString(index++, tmagazinedt.getMagazine_page());
            pstmt.setString(index++, tmagazinedt.getDisplay_gubun());
            pstmt.setString(index++, tmagazinedt.getMagazine_page_title());
            pstmt.setString(index++, tmagazinedt.getMagazine_page_name());
            pstmt.setString(index++, tmagazinedt.getDisplay_priority());
            pstmt.setString(index++, tmagazinedt.getModify_id());
            pstmt.setString(index++, tmagazinedt.getMagazine_no());
            pstmt.setString(index++, tmagazinedt.getOld_magazine_page());
            pstmt.setString(index++, tmagazinedt.getOld_display_gubun());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( tmagazinedt.getMagazine_page()); 	 		logString.append( "/" );
            logString.append( tmagazinedt.getDisplay_gubun()); 	 		logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_page_title());   	logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_page_name());		logString.append( "/" );
            logString.append( tmagazinedt.getDisplay_priority());   		logString.append( "/" );
            logString.append( tmagazinedt.getModify_id()); 		 		logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_no());	 	 		logString.append( "/" );
            logString.append( tmagazinedt.getOld_magazine_page()); 	 	logString.append( "/" );
            logString.append( tmagazinedt.getOld_display_gubun()); 	 	logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MagazineSvc.update(tmagazinedt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MagazineSvc.update(tmagazinedt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


//  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tmagazinedt
    * </PRE>
    * @param   Connection
    * @param   Tmagazinedt
    * @return  int
    *
    *
    *
    */
    public int insert(Connection con, Tmagazinedt tmagazinedt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tmagazinedt));

            int index = 1;

            pstmt.setString(index++, tmagazinedt.getMagazine_no());
            pstmt.setString(index++, tmagazinedt.getMagazine_page());
            pstmt.setString(index++, tmagazinedt.getDisplay_gubun());
            pstmt.setString(index++, tmagazinedt.getMagazine_page_title());
            pstmt.setString(index++, tmagazinedt.getMagazine_page_name());
            pstmt.setString(index++, tmagazinedt.getDisplay_priority());
            pstmt.setString(index++, tmagazinedt.getInsert_id());
            pstmt.setString(index++, tmagazinedt.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( tmagazinedt.getMagazine_no());	 		logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_page());	 		logString.append( "/" );
            logString.append( tmagazinedt.getDisplay_gubun()); 		logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_page_title());   logString.append( "/" );
            logString.append( tmagazinedt.getMagazine_page_name());    logString.append( "/" );
            logString.append( tmagazinedt.getDisplay_priority()); 	    logString.append( "/" );
            logString.append( tmagazinedt.getInsert_id()); 			logString.append( "/" );
            logString.append( tmagazinedt.getModify_id()); 			logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MagazineSvc.insert(tmagazinedt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MagazineSvc.insert(tmagazinedt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
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
            pstmt.setString(index++, retrieve.getString("sum_fromDate"));
            pstmt.setString(index++, retrieve.getString("sum_toDate"));
            pstmt.setString(index++, retrieve.getString("sum_fromDate"));
            pstmt.setString(index++, retrieve.getString("sum_toDate"));
            pstmt.setString(index++, retrieve.getString("sum_fromDate"));
            pstmt.setString(index++, retrieve.getString("sum_toDate"));
            pstmt.setString(index++, retrieve.getString("magazine_no"));

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

//  = Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveNo(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlNo(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("magazine_no"));

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
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDetail(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("magazine_no"));

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

}
