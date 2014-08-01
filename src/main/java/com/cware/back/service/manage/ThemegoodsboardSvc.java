
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
import com.cware.back.entity.table.Tbeautyboard;


/**
 * Register Themegoodsboards Service class
 *
 * @version 1.0, 2006/08/12
 */
public class ThemegoodsboardSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ThemegoodsboardSvc() {}

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




        sb.append("SELECT  A.BEAUTY_NO,   																		 	\n");
        sb.append(" 	   A.BEAUTY_TITLE,																	     	\n");
        sb.append("        A.BEAUTY_CONTENT,			    													 	\n");
        sb.append("        A.BEAUTY_GUBUN,																		 	\n");
        sb.append("        A.BEAUTYBOARD_IMAGE,		 													         	\n");
        sb.append("        A.BEAUTY_TYPE,																		 	\n");
        sb.append("        A.DISPLAY_YN,																		 	\n");
        sb.append("        TO_CHAR(A.DISPLAY_BEGIN_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_BEGIN_DATE,		      	\n");
        sb.append("        TO_CHAR(A.DISPLAY_END_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,		  	 		\n");
        sb.append("        A.MAIN_DISPLAY_YN,		  															 	\n");
        sb.append("        A.DELETE_YN,																		  		\n");
        sb.append("        A.INSERT_ID,																		  	 	\n");
        sb.append("        A.INSERT_DATE,																	  	 	\n");
        sb.append("        A.MODIFY_ID,																		  	 	\n");
        sb.append("        A.MODIFY_DATE,																		 	\n");
        sb.append("        A.DISPLAY_PRIORITY,																	 	\n");
        sb.append("        A.BEAUTY_SOURCE																		 	\n");
        sb.append("  FROM  TBEAUTYBOARD A			        													 	\n");
        sb.append("  WHERE   A.DISPLAY_BEGIN_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD HH24:MI:SS') AND TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("    OR    A.DISPLAY_END_DATE   BETWEEN TO_DATE(?,'YYYY/MM/DD HH24:MI:SS') AND TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("    OR    A.DISPLAY_BEGIN_DATE <= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')  AND A.DISPLAY_END_DATE >= TO_DATE(?, 'yyyy/mm/dd HH24:MI:SS') \n ");
        sb.append("ORDER BY A.BEAUTY_NO DESC 																        \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("sum_fromDate       : " + retrieve.getString("sum_fromDate"));
            log.debug("sum_toDate 	      : " + retrieve.getString("sum_toDate"));
        }
        return sb.toString();
    }

    public String makeSqlNo( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT  A.BEAUTY_NO,   																		 	\n");
        sb.append(" 	   A.BEAUTY_TITLE,																	     	\n");
        sb.append("        A.BEAUTY_CONTENT,			    													 	\n");
        sb.append("        A.BEAUTY_GUBUN,																		 	\n");
        sb.append("        A.BEAUTYBOARD_IMAGE,		 													         	\n");
        sb.append("        A.BEAUTY_TYPE,																		 	\n");
        sb.append("        A.DISPLAY_YN,																		 	\n");
        sb.append("        TO_CHAR(A.DISPLAY_BEGIN_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_BEGIN_DATE,		 		\n");
        sb.append("        TO_CHAR(A.DISPLAY_END_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,		  	 		\n");
        sb.append("        A.MAIN_DISPLAY_YN,		  															 	\n");
        sb.append("        A.DELETE_YN,																		  		\n");
        sb.append("        A.INSERT_ID,																		  	 	\n");
        sb.append("        A.INSERT_DATE,																	  	 	\n");
        sb.append("        A.MODIFY_ID,																		  	 	\n");
        sb.append("        A.MODIFY_DATE,																		 	\n");
        sb.append("        A.DISPLAY_PRIORITY,																	 	\n");
        sb.append("        A.BEAUTY_SOURCE																		 	\n");
        sb.append("  FROM  TBEAUTYBOARD A			        													 	\n");
        sb.append("  WHERE  A.BEAUTY_NO  LIKE ?||'%'  															 	\n");
        sb.append("ORDER BY A.BEAUTY_NO ASC																            \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());

        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tbeautyboard 변경 )
     * </PRE>
     * @param   Tbeautyboard
     * @return  String
     */
     public String makeSqlUpdate(Tbeautyboard tbeautyboard) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("  UPDATE  TBEAUTYBOARD 	  	 	   			 								\n ");
         sb.append("		 SET 	BEAUTY_TITLE  = ?, 						 					\n ");
         sb.append("		  		BEAUTY_GUBUN  = ?, 						 					\n ");
         sb.append("		  		BEAUTY_SOURCE = ?, 											\n ");
         sb.append("		  		DISPLAY_YN    = ?, 						 					\n ");
         sb.append("		  		DISPLAY_BEGIN_DATE =  TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),   \n ");
         sb.append("		  		DISPLAY_END_DATE   =  TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),   \n ");
         sb.append("	   	     	DISPLAY_PRIORITY = ?, 										\n ");
         sb.append("		  		MAIN_DISPLAY_YN = ?, 						 				\n ");
         sb.append("		  		BEAUTY_CONTENT = ?, 							 			\n ");
         sb.append("		  		MODIFY_ID =  ?,                        						\n ");
         sb.append("		  		MODIFY_DATE =  SYSDATE                 						\n ");
         sb.append("	WHERE 		BEAUTY_NO =? 						 							\n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }


     /**
      * <PRE>
      * Desc : Make SQL ( insert Tbeautyboard )
      * </PRE>
      * @param   Tbeautyboard
      * @return  String
      */
      public String makeSqlInsert(Tbeautyboard tbeautyboard) throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append(" INSERT INTO  TBEAUTYBOARD ( 				      	\n ");
          sb.append("         	   BEAUTY_NO,    		              	\n ");
          sb.append("         	   BEAUTY_TITLE,      		          	\n ");
          sb.append("        	   BEAUTY_CONTENT,                    	\n ");
          sb.append("              BEAUTY_GUBUN,                      	\n ");
          sb.append("              BEAUTYBOARD_IMAGE,                 	\n ");
          sb.append("         	   DISPLAY_YN,    		   		      	\n ");
          sb.append("              DISPLAY_BEGIN_DATE,       	      	\n ");
          sb.append("              DISPLAY_END_DATE,     		 	  	\n ");
          sb.append("              MAIN_DISPLAY_YN,       	    	  	\n ");
          sb.append("              SUB_DISPLAY_YN,       	    	  	\n ");
          sb.append("              DELETE_YN,      		    		  	\n ");
          sb.append("              BEAUTY_TYPE,       				  	\n ");
          sb.append("              BEAUTY_SOURCE,   	 	     	  	\n ");
          sb.append("              BEAUTYBOARD_POP_IMAGE,   	      	\n ");
          sb.append("              DISPLAY_PRIORITY,   	 	          	\n ");
          sb.append("              REMARK, 							  	\n ");
          sb.append("              REMARK1_V, 						  	\n ");
          sb.append("              REMARK2_V, 						  	\n ");
          sb.append("              REMARK3_N, 						  	\n ");
          sb.append("              REMARK4_N, 					      	\n ");
          sb.append("              INSERT_ID, 						  	\n ");
          sb.append("              INSERT_DATE,                       	\n ");
          sb.append("              MODIFY_ID,   	 	   		      	\n ");
          sb.append("              MODIFY_DATE )   	 	   			 	\n ");
          sb.append("  VALUES (	    ?,                                	\n ");
          sb.append("   		    ?,                                	\n ");
          sb.append("   		    ?,                                	\n ");
          sb.append("   		    ?,                                	\n ");
          sb.append("   		    ?,                                	\n ");
          sb.append("       		?,                                	\n ");
          sb.append("       		TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
          sb.append("       		TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),	\n ");
          sb.append("       		?,                                	\n ");
          sb.append("       		?,                                	\n ");
          sb.append("       		?,                                	\n ");
          sb.append("  		        ?,                                	\n ");
          sb.append("  		        ?,                                	\n ");
          sb.append("       		?,                               	\n ");
          sb.append("   		    ?,                                	\n ");
          sb.append("          	    ?,                                	\n ");
          sb.append("               ?,                                	\n ");
          sb.append("               ?,                                	\n ");
          sb.append("               ?,                               	\n ");
          sb.append("               ?,                                	\n ");
          sb.append("               ?,                                	\n ");
          sb.append("       	    SYSDATE,  					      	\n ");
          sb.append("          	    ?, 			 					  	\n ");
          sb.append("               SYSDATE )                         	\n ");
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
    public RetrieveModel retrieveNo(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlNo(retrieve));

            int index = 1;

            pstmt.setString(index++, retrieve.getString("beauty_no"));

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

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tbeautyboard
    * </PRE>
    * @param   Connection
    * @param   Tbeautyboard
    * @return  int
    */
    public int update(Connection con, Tbeautyboard tbeautyboard) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tbeautyboard));
            int index = 1;
            pstmt.setString(index++,tbeautyboard.getBeauty_title());
            pstmt.setString(index++,tbeautyboard.getBeauty_gubun());
            pstmt.setString(index++,tbeautyboard.getBeauty_source());
            pstmt.setString(index++,tbeautyboard.getDisplay_yn());
            pstmt.setString(index++,tbeautyboard.getDisplay_begin_date());
            pstmt.setString(index++,tbeautyboard.getDisplay_end_date());
            pstmt.setString(index++,tbeautyboard.getDisplay_priority());
            pstmt.setString(index++,tbeautyboard.getMain_display_yn());
            pstmt.setString(index++,tbeautyboard.getBeauty_content());
            pstmt.setString(index++,tbeautyboard.getModify_id());
            pstmt.setString(index++,tbeautyboard.getBeauty_no());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeautyboard.getBeauty_title()      	); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_gubun()      	); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_source()   	); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_yn()	 	    ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_begin_date()  ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_end_date()  	); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_priority()    ); logString.append( "/" );
            logString.append( tbeautyboard.getMain_display_yn()   	); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_content()  	); logString.append( "/" );
            logString.append( tbeautyboard.getModify_id()      		); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_no()			); logString.append( "/" );


            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ThemegoodsboardSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ThemegoodsboardSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tbeautyboard
    * </PRE>
    * @param   Connection
    * @param   Tbeautyboard
    * @return  int
    */
    public int insert(Connection con, Tbeautyboard tbeautyboard) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tbeautyboard));
            int index = 1;
            pstmt.setString(index++,tbeautyboard.getBeauty_no());
            pstmt.setString(index++,tbeautyboard.getBeauty_title());
            pstmt.setString(index++,tbeautyboard.getBeauty_content());
            pstmt.setString(index++,tbeautyboard.getBeauty_gubun());
            pstmt.setString(index++,tbeautyboard.getBeautyboard_image());
            pstmt.setString(index++,tbeautyboard.getDisplay_yn());
            pstmt.setString(index++,tbeautyboard.getDisplay_begin_date());
            pstmt.setString(index++,tbeautyboard.getDisplay_end_date());
            pstmt.setString(index++,tbeautyboard.getMain_display_yn());
            pstmt.setString(index++,tbeautyboard.getSub_display_yn());
            pstmt.setString(index++,tbeautyboard.getDelete_yn());
            pstmt.setString(index++,tbeautyboard.getBeauty_type());
            pstmt.setString(index++,tbeautyboard.getBeauty_source());
            pstmt.setString(index++,tbeautyboard.getBeautyboard_pop_image());
            pstmt.setString(index++,tbeautyboard.getDisplay_priority());
            pstmt.setString(index++,tbeautyboard.getRemark());
            pstmt.setString(index++,tbeautyboard.getRemark1_v());
            pstmt.setString(index++,tbeautyboard.getRemark2_v());
            pstmt.setString(index++,tbeautyboard.getRemark3_n());
            pstmt.setString(index++,tbeautyboard.getRemark4_n());
            pstmt.setString(index++,tbeautyboard.getInsert_id());
            pstmt.setString(index++,tbeautyboard.getModify_id());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tbeautyboard.getBeauty_no()  			  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_title()      	  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_content()        ); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_gubun()   		  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeautyboard_image()	  ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_yn()  	 	  ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_begin_date() 	  ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_end_date()      ); logString.append( "/" );
            logString.append( tbeautyboard.getMain_display_yn()   	  ); logString.append( "/" );
            logString.append( tbeautyboard.getSub_display_yn()  	  ); logString.append( "/" );
            logString.append( tbeautyboard.getDelete_yn()      	   	  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_type()			  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeauty_source()		  ); logString.append( "/" );
            logString.append( tbeautyboard.getBeautyboard_pop_image() ); logString.append( "/" );
            logString.append( tbeautyboard.getDisplay_priority()  	  ); logString.append( "/" );
            logString.append( tbeautyboard.getRemark()      		  ); logString.append( "/" );
            logString.append( tbeautyboard.getRemark1_v()             ); logString.append( "/" );
            logString.append( tbeautyboard.getRemark2_v()			  ); logString.append( "/" );
            logString.append( tbeautyboard.getRemark3_n()			  ); logString.append( "/" );
            logString.append( tbeautyboard.getRemark4_n()			  ); logString.append( "/" );
            logString.append( tbeautyboard.getInsert_id()			  ); logString.append( "/" );
            logString.append( tbeautyboard.getModify_id()			  ); logString.append( "/" );


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


