
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
import com.cware.back.entity.table.Tolivemagazinedt;
import com.cware.back.entity.table.Tolivemagazinem;



/**
 * Register Sample Service class
 *
 * @version 1.0, 2006/08/12
 */
public class OlivemagazineSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public OlivemagazineSvc() {}

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
        sb.append("  FROM 	 TOLIVEMAGAZINEM A                                           						 	 \n");
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
        sb.append("  FROM 	 TOLIVEMAGAZINEM A                                           						 \n");
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
        sb.append("	FROM	 TOLIVEMAGAZINEDT A 			\n");
        sb.append("	WHERE 	 A.MAGAZINE_NO = ?	    		\n");
        sb.append(" ORDER BY A.MAGAZINE_NO, A.MAGAZINE_PAGE \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("magazine_no    : " + retrieve.getString("magazine_no"));
           
        }
        return sb.toString();
    }
    
    
    public String makeSqlUpdateM(Tolivemagazinem tolivemagazine) throws StoreException{
    
    	StringBuffer sb = new StringBuffer();
         
	    sb.append("UPDATE TOLIVEMAGAZINEM 	  	 	   			 					   		\n"); 
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
     * Desc : Make SQL ( insert Tolivemagazinem )
     * </PRE>
     * @param   Tolivemagazinem
     * @return  String
     */
     public String makeSqlInsertM(Tolivemagazinem tolivemagazinem) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append(" INSERT INTO  TOLIVEMAGAZINEM ( 				                 \n");
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
      * Desc : Make SQL ( Update Tolivemagazinedt 변경 )
      * </PRE>
      * @param   Tolivemagazinedt
      * @return  String
      */
     private final String updateSqlTolivemagazinedt = " UPDATE TOLIVEMAGAZINEDT SET                  \n "
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
     				
     private int updateSqlTolivemagazinedtLog =  0;
     
     private String makeSqlUpdate(Tolivemagazinedt tolivemagazinedt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTolivemagazinedtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTolivemagazinedt);
     		}
     	   updateSqlTolivemagazinedtLog = 1;
     	}
     	return updateSqlTolivemagazinedt;
      }
     
     
     /**
      * <PRE>
      * Desc : Make SQL ( Insert Tolivemagazinedt )
      * </PRE>
      * @param   
      * @return  String SQL
      */
      private final String insertSqlOlivemagazine = "   INSERT INTO TOLIVEMAGAZINEDT (  \n "
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
      
      private int insertSqlOlivemagazineLog =  0;     

      public String makeSqlInsert(Tolivemagazinedt tolivemagazindedt) throws StoreException{
  	   	//= log SQL -------------------------------------------------
  		if (insertSqlOlivemagazineLog == 0) {
  			if (logSave.isDebugEnabled()) {
  				logSave.debug(insertSqlOlivemagazine);
  			}
  			insertSqlOlivemagazineLog = 1;
  		}
  		return insertSqlOlivemagazine;
  	}
    
    //= Update -------------------------------------------------                                     
    /**                                                                                              
    * <PRE>                                                                                          
    * Desc : Update Tolivemagazinem                                                                    
    * </PRE>                                                                                         
    * @param   Connection                                                                            
    * @param   Tpostcard                                                                             
    * @return  int                                                                                   
    */                                                                                               
    public int update(Connection con, Tolivemagazinem tolivemagazinem) throws StoreException{          
        PreparedStatement pstmt       = null;                                                        
        int executedRtn = 0;                                                                         
                                                                                                     
        try {                                                                                        
            pstmt = con.prepareStatement(makeSqlUpdateM(tolivemagazinem));                               
            int index = 1;                                                                           
            pstmt.setString(index++,tolivemagazinem.getMagazine_title());                                                  
            pstmt.setString(index++,tolivemagazinem.getMagazine_month());                                                 
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_l());                                                  
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_m());                                                  
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_s());                                                  
            pstmt.setString(index++,tolivemagazinem.getMagazine_content());                                                  
            pstmt.setString(index++,tolivemagazinem.getDisplay_yn()); 
            pstmt.setString(index++,tolivemagazinem.getDisplay_begin_date());                                                  
            pstmt.setString(index++,tolivemagazinem.getDisplay_end_date());    
            pstmt.setString(index++,tolivemagazinem.getDisplay_priority());                                              
            pstmt.setString(index++,tolivemagazinem.getModify_id());
            pstmt.setString(index++,tolivemagazinem.getMagazine_no());
            
                                                                                                     
            //= log Save Data ---------------------                                                  
            StringBuffer logString = new StringBuffer();                                             
            logString.append( tolivemagazinem.getMagazine_title());     logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_month());     logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_image_l());   logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_image_m());   logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_image_s());   logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_content());   logString.append( "/" );                                
            logString.append( tolivemagazinem.getDisplay_yn()); 		logString.append( "/" );                                
            logString.append( tolivemagazinem.getDisplay_begin_date()); logString.append( "/" );                                
            logString.append( tolivemagazinem.getDisplay_end_date()); 	logString.append( "/" );  
            logString.append( tolivemagazinem.getDisplay_priority()); 	logString.append( "/" );  
            logString.append( tolivemagazinem.getModify_id());   		logString.append( "/" );                                
            logString.append( tolivemagazinem.getMagazine_no());        logString.append( "/" );                                
                                                                                                
                                                                                                     
            logSave.info(logString.toString());                                                      
                                                                                                     
            executedRtn = pstmt.executeUpdate();                                                     
                                                                                                     
        }catch(SQLException se){                                                                     
            logSave.error("[OlivemagazineSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());                                               
        }catch(Exception e){                                                                         
            logSave.error("[OlivemagazineSvc.update() Exception : ERR-"+e.getMessage());             
            throw new StoreException(e.getMessage());                                                
        }finally {                                                                                   
            DBUtils.freeConnection(null, pstmt, null);                                               
        }                                                                                            
        return executedRtn;                                                                          
    }                                                                                                
                                                                                                     
//  = Insert -------------------------------------------------                                     
    /**                                                                                          
    * <PRE>                                                                                      
    * Desc : Insert TOLIVEMAGAZINEM                                                              
    * </PRE>                                                                                     
    * @param   Connection                                                                        
    * @param   tolivemagazinem                                                                      
    * @return  int                                                                               
    */                                                                                           
    public int insert(Connection con, Tolivemagazinem tolivemagazinem) throws StoreException{          
        PreparedStatement pstmt       = null;                                                    
        ResultSet         rset        = null;                                                    
        int executedRtn = 0;                                                                     
                                                                                                 
        try {                                                                                    
            pstmt = con.prepareStatement(makeSqlInsertM(tolivemagazinem));                           
            int index = 1;                                                                       
            pstmt.setString(index++,tolivemagazinem.getMagazine_no());                                
            pstmt.setString(index++,tolivemagazinem.getMagazine_title());                             
            pstmt.setString(index++,tolivemagazinem.getMagazine_content());                           
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_l());                             
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_m());                        
            pstmt.setString(index++,tolivemagazinem.getMagazine_image_s());                               
            pstmt.setString(index++,tolivemagazinem.getMagazine_month());                       
            pstmt.setString(index++,tolivemagazinem.getDisplay_yn());                         
            pstmt.setString(index++,tolivemagazinem.getDisplay_begin_date());                                
            pstmt.setString(index++,tolivemagazinem.getDisplay_end_date());                                
            pstmt.setString(index++,tolivemagazinem.getDelete_yn());                                
            pstmt.setString(index++,tolivemagazinem.getDisplay_priority());                          
            pstmt.setString(index++,tolivemagazinem.getRemark());                           
            pstmt.setString(index++,tolivemagazinem.getRemark1_v());                                
            pstmt.setString(index++,tolivemagazinem.getRemark2_v());                              
            pstmt.setString(index++,tolivemagazinem.getRemark3_n());                            
            pstmt.setString(index++,tolivemagazinem.getRemark4_n());                    
            pstmt.setString(index++,tolivemagazinem.getInsert_id());                         
            pstmt.setString(index++,tolivemagazinem.getModify_id());                           
                                                                                
                                                                                                 
            //= log Save Data ---------------------                                              
            StringBuffer logString = new StringBuffer();                                         
            logString.append( tolivemagazinem.getMagazine_no());  	    logString.append( "/" );    
            logString.append( tolivemagazinem.getMagazine_title());     logString.append( "/" );
            logString.append( tolivemagazinem.getMagazine_content());   logString.append( "/" );
            logString.append( tolivemagazinem.getMagazine_image_l());   logString.append( "/" );  
            logString.append( tolivemagazinem.getMagazine_image_m());   logString.append( "/" );  
            logString.append( tolivemagazinem.getMagazine_image_s());   logString.append( "/" );    
            logString.append( tolivemagazinem.getMagazine_month());     logString.append( "/" );
            logString.append( tolivemagazinem.getDisplay_yn()); 	    logString.append( "/" );
            logString.append( tolivemagazinem.getDisplay_begin_date()); logString.append( "/" );
            logString.append( tolivemagazinem.getDisplay_end_date());   logString.append( "/" );  
            logString.append( tolivemagazinem.getDelete_yn()); 			logString.append( "/" );
            logString.append( tolivemagazinem.getDisplay_priority()); 	logString.append( "/" );    
            logString.append( tolivemagazinem.getRemark()); 			logString.append( "/" );    
            logString.append( tolivemagazinem.getRemark1_v()); 			logString.append( "/" );
            logString.append( tolivemagazinem.getRemark2_v()); 			logString.append( "/" );
            logString.append( tolivemagazinem.getRemark3_n()); 			logString.append( "/" );    
            logString.append( tolivemagazinem.getRemark4_n()); 			logString.append( "/" );
            logString.append( tolivemagazinem.getInsert_id());			logString.append( "/" );      
            logString.append( tolivemagazinem.getModify_id()); 		 	logString.append( "/" );      
                                                                   
                                                                                                 
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
    * Desc : Update Tolivemagazinedt
    * </PRE>
    * @param   Connection
    * @param   Tolivemagazindedt
    * @return  int
    *
    *  
    */
    
    public int update(Connection con, Tolivemagazinedt tolivemagazinedt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tolivemagazinedt));
            
            int index = 1;

            pstmt.setString(index++, tolivemagazinedt.getMagazine_page());
            pstmt.setString(index++, tolivemagazinedt.getDisplay_gubun());            
            pstmt.setString(index++, tolivemagazinedt.getMagazine_page_title());            
            pstmt.setString(index++, tolivemagazinedt.getMagazine_page_name());            
            pstmt.setString(index++, tolivemagazinedt.getDisplay_priority());            
            pstmt.setString(index++, tolivemagazinedt.getModify_id());            
            pstmt.setString(index++, tolivemagazinedt.getMagazine_no());            
            pstmt.setString(index++, tolivemagazinedt.getOld_magazine_page());
            pstmt.setString(index++, tolivemagazinedt.getOld_display_gubun()); 
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            
            logString.append( tolivemagazinedt.getMagazine_page()); 	 		logString.append( "/" );
            logString.append( tolivemagazinedt.getDisplay_gubun()); 	 		logString.append( "/" );            
            logString.append( tolivemagazinedt.getMagazine_page_title());   	logString.append( "/" );
            logString.append( tolivemagazinedt.getMagazine_page_name());		logString.append( "/" );
            logString.append( tolivemagazinedt.getDisplay_priority());   		logString.append( "/" );            
            logString.append( tolivemagazinedt.getModify_id()); 		 		logString.append( "/" );
            logString.append( tolivemagazinedt.getMagazine_no());	 	 		logString.append( "/" );            
            logString.append( tolivemagazinedt.getOld_magazine_page()); 	 	logString.append( "/" );
            logString.append( tolivemagazinedt.getOld_display_gubun()); 	 	logString.append( "/" );            

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[OlivemagazineSvc.update(tolivemagazinedt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[OlivemagazineSvc.update(tolivemagazinedt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    
    
//  = Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tolivemagazinedt
    * </PRE>
    * @param   Connection
    * @param   Tolivemagazinedt
    * @return  int
    * 
    *  
    * 
    */
    public int insert(Connection con, Tolivemagazinedt tolivemagazinedt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tolivemagazinedt));
            
            int index = 1;

            pstmt.setString(index++, tolivemagazinedt.getMagazine_no());
            pstmt.setString(index++, tolivemagazinedt.getMagazine_page());
            pstmt.setString(index++, tolivemagazinedt.getDisplay_gubun());
            pstmt.setString(index++, tolivemagazinedt.getMagazine_page_title());            
            pstmt.setString(index++, tolivemagazinedt.getMagazine_page_name());            
            pstmt.setString(index++, tolivemagazinedt.getDisplay_priority());            
            pstmt.setString(index++, tolivemagazinedt.getInsert_id());            
            pstmt.setString(index++, tolivemagazinedt.getModify_id());            
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
           
            logString.append( tolivemagazinedt.getMagazine_no());	 		logString.append( "/" );
            logString.append( tolivemagazinedt.getMagazine_page());	 		logString.append( "/" );
            logString.append( tolivemagazinedt.getDisplay_gubun()); 		logString.append( "/" );
            logString.append( tolivemagazinedt.getMagazine_page_title());   logString.append( "/" );
            logString.append( tolivemagazinedt.getMagazine_page_name());    logString.append( "/" );
            logString.append( tolivemagazinedt.getDisplay_priority()); 	    logString.append( "/" );
            logString.append( tolivemagazinedt.getInsert_id()); 			logString.append( "/" );
            logString.append( tolivemagazinedt.getModify_id()); 			logString.append( "/" );
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[OlivemagazineSvc.insert(tolivemagazinedt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[OlivemagazineSvc.insert(tolivemagazinedt) Exception : ERR-"+e.getMessage());
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
