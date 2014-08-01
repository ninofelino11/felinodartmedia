
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
import com.cware.back.entity.table.Teventdt;
import com.cware.back.entity.table.Teventm;



/**
 * Register Sample Service class
 *
 * @version 1.0, 2006/08/12
 */
public class EventSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public EventSvc() {}

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
        
        sb.append("          SELECT A.EVENT_NO,                         \n");  
        sb.append("                 A.GROUP_NO,                         \n");  
        sb.append("                 A.EVENT_LGROUP,                     \n");  
        sb.append("                 A.EVENT_MGROUP,                     \n");  
        sb.append("                 A.EVENT_PLACE,                      \n");  
        sb.append("                 A.MANAGE_DEPT,                      \n");  
        sb.append("                 A.CO_DEPT,                          \n");  
        sb.append("                 A.EVENT_TITLE,                      \n");  
        sb.append("                 A.EVENT_COMMENT,                    \n");  
        sb.append("                 A.EVENT_NOTE,                       \n");  
        sb.append("                 A.REFER_NOTE,                       \n");  
        sb.append("       	 		TO_CHAR(A.START_DATE,'YYYY/MM/DD HH24:MI:SS') AS START_DATE,        	 \n");
        sb.append("       	 		TO_CHAR(A.END_DATE,'YYYY/MM/DD HH24:MI:SS') AS END_DATE,        	 \n");
        sb.append("                 A.NOTICE_DATE,                      \n");  
        sb.append("                 A.MAIL_DATE,                        \n");  
        sb.append("                 A.MAIL_FLAG,                        \n");  
        sb.append("                 A.APPLY_COUNT,                      \n");  
        sb.append("                 A.PROMO_COUNT,                      \n");  
        sb.append("                 A.PROMO_OPTION,                     \n");  
        sb.append("                 A.PROMO_YN,                         \n");  
        sb.append("                 A.DISPLAY_YN,                       \n");  
        sb.append("                 A.LIST_DISPLAY_YN,                  \n");  
        sb.append("                 A.LIST_DISPLAY_FLAG,                \n");  
        sb.append("                 A.INSERT_ID,                        \n");  
        sb.append("       	 		TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,        	 \n");
        sb.append("                 A.MODIFY_ID,						\n");  
        sb.append("       	 		TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,        	 \n");
        sb.append("                 A.PROMO_ID,							\n");  
        sb.append("                 A.PROMO_DATE,						\n");  
        sb.append("                 '0' AS PROC_FLAG					\n");  
        sb.append("    FROM TEVENTM A                                                                    \n");
        sb.append("   WHERE (( A.START_DATE >= TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')    					 \n");
        sb.append("     AND    A.START_DATE <= TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'))   					 \n");
        sb.append("      OR  ( A.START_DATE <  TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')    					 \n");
        sb.append("     AND    A.END_DATE   >= TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')))  					 \n");
        sb.append("     AND NVL(A.EVENT_LGROUP, '10') LIKE ? || '%'                                      \n");
        sb.append("     AND A.EVENT_MGROUP LIKE ? || '%'                                                 \n");
        sb.append("     AND A.EVENT_NO LIKE ? || '%'                                                     \n");
        

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            logSave.error(sb.toString());
           
        }
        logSave.error(sb.toString());
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
        
        sb.append("          SELECT A.EVENT_NO,                         \n");  
        sb.append("                 A.EVENT_LGROUP,                     \n");  
        sb.append("                 A.EVENT_MGROUP,                     \n");  
        sb.append("                 B.CODE_NAME,                        \n");  
        sb.append("                 A.EVENT_PLACE,                      \n");  
        sb.append("                 A.MANAGE_DEPT,                      \n");  
        sb.append("                 A.CO_DEPT,                          \n");  
        sb.append("                 A.EVENT_TITLE,                      \n");  
        sb.append("                 A.EVENT_COMMENT,                    \n");  
        sb.append("                 A.EVENT_NOTE,                       \n");  
        sb.append("                 A.REFER_NOTE,                       \n");  
        sb.append("       	 		TO_CHAR(A.START_DATE,'YYYY/MM/DD HH24:MI:SS') AS START_DATE,        	 \n");
        sb.append("       	 		TO_CHAR(A.END_DATE,'YYYY/MM/DD HH24:MI:SS') AS END_DATE,        	 \n");
        sb.append("                 A.NOTICE_DATE,                      \n");  
        sb.append("                 A.MAIL_DATE,                        \n");  
        sb.append("                 A.MAIL_FLAG,                        \n");  
        sb.append("                 A.APPLY_COUNT,                      \n");  
        sb.append("                 A.PROMO_COUNT,                      \n");  
        sb.append("                 A.PROMO_OPTION,                     \n");  
        sb.append("                 A.PROMO_YN,                         \n");  
        sb.append("                 A.DISPLAY_YN,                       \n");  
        sb.append("                 A.LIST_DISPLAY_YN,                  \n");  
        sb.append("                 A.LIST_DISPLAY_FLAG,                \n");  
        sb.append("                 A.INSERT_ID,                        \n");  
        sb.append("       	 		TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,        	 \n");
        sb.append("                 A.MODIFY_ID,                        \n");  
        sb.append("       	 		TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,        	 \n");
        sb.append("                 A.PROMO_ID,                         \n");  
        sb.append("                 A.PROMO_DATE,                       \n");  
        sb.append("                 '0' AS PROC_FLAG                    \n");  
        sb.append("    		   FROM TEVENTM A,                          \n");
        sb.append("         		TCODE   B                           \n");
        sb.append("   		  WHERE A.EVENT_NO = ?           			\n");
        sb.append("      		AND A.EVENT_MGROUP = B.CODE_MGROUP      \n");
        sb.append("       		AND B.CODE_LGROUP  = 'B836'             \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("event_no    : " + retrieve.getString("event_no"));
           
        }
        logSave.error(sb.toString());
        return sb.toString();
    }
    
    
    public String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.EVENT_NO,               \n");
        sb.append("         A.EVENT_SEQ,              \n");
        sb.append("         A.START_RANK,             \n");
        sb.append("         A.END_RANK,               \n");
        sb.append("         A.PROMO_FLAG,             \n");
        sb.append("         A.PROMO_TITLE,            \n");
        sb.append("         A.DISPLAY_TITLE           \n");	
        sb.append("    FROM TEVENTDT A                \n");
        sb.append("   WHERE A.EVENT_NO = ?			  \n");
        
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("event_no    : " + retrieve.getString("event_no"));
           
        }
        logSave.error(sb.toString());
        return sb.toString();
    }
    
    public String makeSqlUpdateM(Teventm tolivemagazine) throws StoreException{
    
    	StringBuffer sb = new StringBuffer();

    	sb.append("UPDATE TEVENTM                                           			\n");
	    sb.append("   SET EVENT_TITLE 		= ?,  										\n");
	    sb.append("       EVENT_COMMENT 	= ?,                   						\n");
	    sb.append("       START_DATE 		= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),       \n");
	    sb.append("       END_DATE 			= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),       \n");
	    sb.append("       PROMO_COUNT 		= ?,                       					\n");
	    sb.append("       PROMO_YN 			= ?,                             			\n");
	    sb.append("       DISPLAY_YN 		= ?,                          				\n");
	    sb.append("       LIST_DISPLAY_YN 	= ?,               							\n");
	    sb.append("       LIST_DISPLAY_FLAG = ?,           								\n");
	    sb.append("       MODIFY_DATE 		= SYSDATE,                       			\n");
	    sb.append("       MODIFY_ID 		= ?,                           				\n");
	    sb.append("       EVENT_LGROUP 		= ?,                     					\n");
	    sb.append("       EVENT_MGROUP 		= ?,                     					\n");
	    sb.append("       EVENT_PLACE 		= ?,                       					\n");
	    sb.append("       MANAGE_DEPT 		= ?,                       					\n");
	    sb.append("       CO_DEPT 			= ?                                			\n");
	    sb.append(" WHERE EVENT_NO = ?                             						\n");	    
	    
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
     public String makeSqlInsertM(Teventm teventm) throws StoreException{

         StringBuffer sb = new StringBuffer();

			sb.append("INSERT INTO TEVENTM                                             \n");
			sb.append("            (EVENT_NO,                                          \n");
			sb.append("             EVENT_TITLE,                                       \n");
			sb.append("             EVENT_COMMENT,                                     \n");
			sb.append("             START_DATE,                                        \n");
			sb.append("             END_DATE,                                          \n");
			sb.append("             APPLY_COUNT,                                       \n");
			sb.append("             PROMO_COUNT,                                       \n");
			sb.append("             DISPLAY_YN,                                        \n");
			sb.append("             LIST_DISPLAY_YN,                                   \n");
			sb.append("             LIST_DISPLAY_FLAG,                                 \n");
			sb.append("             INSERT_DATE,                                       \n");
			sb.append("             INSERT_ID,                                         \n");
			sb.append("             MODIFY_DATE,                                       \n");
			sb.append("             MODIFY_ID,                                         \n");
			sb.append("             EVENT_LGROUP,                                      \n");
			sb.append("             EVENT_MGROUP,                                      \n");
			sb.append("             EVENT_PLACE,                                       \n");
			sb.append("             MANAGE_DEPT,                                       \n");
			sb.append("             CO_DEPT)                                           \n");
			sb.append("     VALUES (?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),                \n");
			sb.append("             TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),                \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             SYSDATE,                						   \n");
			sb.append("             ?,                                                 \n");
			sb.append("             SYSDATE,                						   \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?,                                                 \n");
			sb.append("             ?)                                                 \n");

         	
         //= log SQL -------------------------------------------------
 	    if (logSave.isDebugEnabled()) {
 	    	logSave.debug(sb.toString());
 	    }
     	return sb.toString();
    }

     /**
      * <PRE>
      * Desc : Make SQL ( Update Tolivemagazinedt 蹂寃?)
      * </PRE>
      * @param   Tolivemagazinedt
      * @return  String
      */
     
     private final String updateSqlTeventdt 		= "  UPDATE TEVENTDT                             \n "
    	 											+ "    SET DISPLAY_TITLE = ?        			 \n "
    	 											+ "  WHERE EVENT_NO = ?                  		 \n "
    	 											+ "    AND EVENT_SEQ = ?                		 \n " ;
      				
     private int updateSqlTeventdtLog =  0;
     
     private String makeSqlUpdateDt(Teventdt teventdt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTeventdtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTeventdtLog);
     		}
     	   updateSqlTeventdtLog = 1;
     	}
     	return updateSqlTeventdt;
      }
     

     /**
      * <PRE>
      * Desc : Make SQL ( Insert Tolivemagazinedt )
      * </PRE>
      * @param   
      * @return  String SQL
      */

    
    private final String insertSqlEventdt = " INSERT INTO TEVENTDT            		\n "  
                                        +"            (EVENT_NO,                    \n "  
                                        +"             EVENT_SEQ,                   \n "  
                                        +"             START_RANK,                  \n "  
                                        +"             END_RANK,                    \n "  
                                        +"             PROMO_FLAG,                  \n "  
                                        +"             PROMO_TITLE,                 \n "  
                                        +"             DISPLAY_TITLE)               \n "  
                                        +"     VALUES (?,                   		\n "  
                                        +"             ?,                  			\n "  
                                        +"             ?,                 			\n "  
                                        +"             ?,                   		\n "  
                                        +"             ?,                 			\n "  
                                        +"             ?,                			\n "  
                                        +"             ?)              				\n " ;
      
      private int insertSqlEventdtLog =  0;     

      public String makeSqlInsert(Teventdt teventdt) throws StoreException{
  	   	//= log SQL -------------------------------------------------
  		if (insertSqlEventdtLog == 0) {
  			if (logSave.isDebugEnabled()) {
  				logSave.debug(insertSqlEventdt);
  			}
  			insertSqlEventdtLog = 1;
  		}
  		return insertSqlEventdt;
  	}

    //= Update -------------------------------------------------                                     
    /**                                                                                              
    * <PRE>                                                                                          
    * Desc : Update Teventm                                                                    
    * </PRE>                                                                                         
    * @param   Connection                                                                            
    * @param   Tpostcard                                                                             
    * @return  int                                                                                   
    */          

    public int update(Connection con, Teventm teventm) throws StoreException{          
        PreparedStatement pstmt       = null;                                                        
        int executedRtn = 0;                                                                         
                                                                                                     
        try {                                                                                        
            pstmt = con.prepareStatement(makeSqlUpdateM(teventm));                               
            int index = 1;                                                                           
            

            pstmt.setString(index++,teventm.getEvent_title());                                                  
            pstmt.setString(index++,teventm.getEvent_comment());                                                 
            pstmt.setString(index++,teventm.getStart_date());                                                  
            pstmt.setString(index++,teventm.getEnd_date());                                                  
            pstmt.setString(index++,teventm.getPromo_count());                                                  
            pstmt.setString(index++,teventm.getPromo_yn()); 
            pstmt.setString(index++,teventm.getDisplay_yn());                                                  
            pstmt.setString(index++,teventm.getList_display_yn());    
            pstmt.setString(index++,teventm.getList_display_flag());                                              
            pstmt.setString(index++,teventm.getModify_id());
            pstmt.setString(index++,teventm.getEvent_lgroup());
            pstmt.setString(index++,teventm.getEvent_mgroup());
            pstmt.setString(index++,teventm.getEvent_place());
            pstmt.setString(index++,teventm.getManage_dept());
            pstmt.setString(index++,teventm.getCo_dept());
            pstmt.setString(index++,teventm.getEvent_no());
            
                                                                                                     
            //= log Save Data ---------------------                                                  
            StringBuffer logString = new StringBuffer(); 
            
            logString.append( teventm.getEvent_title());     	logString.append( "/" );                                
            logString.append( teventm.getEvent_comment());     	logString.append( "/" );                                
            logString.append( teventm.getStart_date());   		logString.append( "/" );                                
            logString.append( teventm.getEnd_date());   		logString.append( "/" );                                
            logString.append( teventm.getPromo_count());   		logString.append( "/" );                                
            logString.append( teventm.getPromo_yn()); 			logString.append( "/" );                                
            logString.append( teventm.getDisplay_yn());			logString.append( "/" );                                
            logString.append( teventm.getList_display_yn()); 	logString.append( "/" );  
            logString.append( teventm.getList_display_flag());  logString.append( "/" );                                
            logString.append( teventm.getModify_id());        	logString.append( "/" );                                
            logString.append( teventm.getEvent_lgroup());       logString.append( "/" );                                
            logString.append( teventm.getEvent_mgroup());       logString.append( "/" );                                
            logString.append( teventm.getEvent_place());        logString.append( "/" );                                
            logString.append( teventm.getManage_dept());        logString.append( "/" );                                
            logString.append( teventm.getCo_dept());        	logString.append( "/" );                                                                                                              
            logString.append( teventm.getEvent_no());        	logString.append( "/" ); 
     
            
            logSave.error ("222222222: " + teventm.getEvent_place());                                                                                      
            
            logSave.info(logString.toString());                                                      
                                                                                                     
            executedRtn = pstmt.executeUpdate();                                                     
                                                                                                     
        }catch(SQLException se){                                                                     
            logSave.error("[EventSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());                                               
        }catch(Exception e){                                                                         
            logSave.error("[EventSvc.update() Exception : ERR-"+e.getMessage());             
            throw new StoreException(e.getMessage());                                                
        }finally {                                                                                   
            DBUtils.freeConnection(null, pstmt, null);                                               
        }                                                                                            
        return executedRtn;                                                                          
    }                                                                                                
                                                                                               
//  = Insert -------------------------------------------------                                     
    /**                                                                                          
    * <PRE>                                                                                      
    * Desc : Insert TEVENTM                                                              
    * </PRE>                                                                                     
    * @param   Connection                                                                        
    * @param   teventm                                                                      
    * @return  int                                                                               
    */
    
    public int insert(Connection con, Teventm teventm) throws StoreException{          
        PreparedStatement pstmt       = null;                                                    
        ResultSet         rset        = null;                                                    
        int executedRtn = 0;                                                                     
                                                                                                 
        try {                                                                                    
            pstmt = con.prepareStatement(makeSqlInsertM(teventm));                           
            int index = 1;                                                                       


			pstmt.setString(index++,teventm.getEvent_no());                                
			pstmt.setString(index++,teventm.getEvent_title());      
			pstmt.setString(index++,teventm.getEvent_comment());      
			pstmt.setString(index++,teventm.getStart_date());      
			pstmt.setString(index++,teventm.getEnd_date());      
			pstmt.setString(index++,teventm.getApply_count());      
			pstmt.setString(index++,teventm.getPromo_count());      
			pstmt.setString(index++,teventm.getDisplay_yn());      
			pstmt.setString(index++,teventm.getList_display_yn());
			pstmt.setString(index++,teventm.getList_display_flag());
			pstmt.setString(index++,teventm.getInsert_id());
			pstmt.setString(index++,teventm.getModify_id());
			pstmt.setString(index++,teventm.getEvent_lgroup());
			pstmt.setString(index++,teventm.getEvent_mgroup());
			pstmt.setString(index++,teventm.getEvent_place());
			pstmt.setString(index++,teventm.getManage_dept());
			pstmt.setString(index++,teventm.getCo_dept());                                                                    
                                                                                                 
            //= log Save Data ---------------------                                              
            StringBuffer logString = new StringBuffer();                                         
            logString.append( teventm.getEvent_no());  	    	logString.append( "/" );    
            logString.append( teventm.getEvent_title());   		logString.append( "/" );
            logString.append( teventm.getEvent_comment());   	logString.append( "/" );  
            logString.append( teventm.getStart_date());   		logString.append( "/" );  
            logString.append( teventm.getEnd_date());   		logString.append( "/" );    
            logString.append( teventm.getApply_count()); 	    logString.append( "/" );
            logString.append( teventm.getPromo_count()); 		logString.append( "/" );
            logString.append( teventm.getDisplay_yn()); 		logString.append( "/" );
            logString.append( teventm.getList_display_yn()); 	logString.append( "/" );    
            logString.append( teventm.getList_display_flag()); 	logString.append( "/" );    
            logString.append( teventm.getInsert_id()); 			logString.append( "/" );
            logString.append( teventm.getModify_id()); 			logString.append( "/" );
            logString.append( teventm.getEvent_lgroup()); 		logString.append( "/" );
            logString.append( teventm.getEvent_mgroup()); 		logString.append( "/" );
            logString.append( teventm.getEvent_place()); 		logString.append( "/" );
            logString.append( teventm.getManage_dept()); 		logString.append( "/" );
            logString.append( teventm.getCo_dept()); 			logString.append( "/" );
                                                                   
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

    public int update(Connection con, Teventdt teventdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDt(teventdt));
            
            int index = 1;
            
            pstmt.setString(index++, teventdt.getDisplay_title());
            pstmt.setString(index++, teventdt.getEvent_no());            
            pstmt.setString(index++, teventdt.getEvent_seq());            
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            
            logString.append( teventdt.getDisplay_title()); 	 		logString.append( "/" );
            logString.append( teventdt.getEvent_no()); 	 				logString.append( "/" );            
            logString.append( teventdt.getEvent_seq());   				logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[EventSvc.update(teventdt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[EventSvc.update(teventdt) Exception : ERR-"+e.getMessage());
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

    public int insert(Connection con, Teventdt teventdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(teventdt));
            
            int index = 1;

            pstmt.setString(index++, teventdt.getEvent_no());
            pstmt.setString(index++, teventdt.getEvent_seq());
            pstmt.setString(index++, teventdt.getStart_rank());
            pstmt.setString(index++, teventdt.getEnd_rank());            
            pstmt.setString(index++, teventdt.getPromo_flag());            
            pstmt.setString(index++, teventdt.getPromo_title());            
            pstmt.setString(index++, teventdt.getDisplay_title());            
          
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
           
            logString.append( teventdt.getEvent_no());	 		logString.append( "/" );
            logString.append( teventdt.getEvent_seq());	 		logString.append( "/" );
            logString.append( teventdt.getStart_rank()); 		logString.append( "/" );
            logString.append( teventdt.getEnd_rank());   		logString.append( "/" );
            logString.append( teventdt.getPromo_flag());    	logString.append( "/" );
            logString.append( teventdt.getPromo_title()); 	    logString.append( "/" );
            logString.append( teventdt.getDisplay_title()); 	logString.append( "/" );
            
            logSave.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[EventSvc.insert(teventdt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[EventSvc.insert(teventdt) Exception : ERR-"+e.getMessage());
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
             
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("event_lgroup"));
            pstmt.setString(index++, retrieve.getString("event_mgroup"));
            pstmt.setString(index++, retrieve.getString("event_no"));
            
            log.error("111111" + retrieve.getString("fromDate") );
            log.error("222222" + retrieve.getString("toDate") );
            log.error("333333" + retrieve.getString("event_lgroup") );
            log.error("444444" + retrieve.getString("event_mgroup") );
            log.error("555555" + retrieve.getString("event_no") );
            
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
            pstmt.setString(index++, retrieve.getString("event_no"));
          
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
            pstmt.setString(index++, retrieve.getString("event_no"));

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
