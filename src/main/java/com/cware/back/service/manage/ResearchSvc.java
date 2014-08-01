
package com.cware.back.service.manage;

import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tresearchm;

/**
 * Register Research Service class
 *
 * @version 1.0, 2007/02/22
 */
public class ResearchSvc {


 //   private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
  //  private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ResearchSvc() {}

    public String makeSql( RetrieveModel retrieve ) {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RESEARCH_NO,      \n");
        sb.append("        A.RESEARCH_TITLE,   \n");
        sb.append("        A.RESEARCH_IMAGE,   \n");
        sb.append("        NVL(A.POINT_AMT, 0) AS POINT_AMT,  \n");
        sb.append("        TO_CHAR(A.START_DATE,'YYYY/MM/DD HH24:MI:SS') AS START_DATE,    \n");
        sb.append("        TO_CHAR(A.END_DATE,'YYYY/MM/DD HH24:MI:SS') AS END_DATE,        \n");
        sb.append("        A.INSERT_DATE,      \n");
        sb.append("        A.INSERT_ID,        \n");
        sb.append("        A.DISPLAY_YN,       \n");
        sb.append("        NVL(A.RESEARCH_CNT, 0) AS RESEARCH_CNT      \n"); 
        sb.append("   FROM TRESEARCHM A        \n");
        sb.append("  WHERE A.END_DATE      >= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')     \n ");
        sb.append("    AND A.START_DATE    <= TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')     \n ");
        sb.append("    AND A.RESEARCH_NO LIKE ? || '%'  \n");
        
        //= log SQL -------------------------------------------------
    
        return sb.toString();
    }

   
    public String makeSqlQ( RetrieveModel retrieve ) {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT B.RESEARCH_NO,                \n");
        sb.append("        B.QUESTION_NO,                \n");
        sb.append("        B.QUESTION,                   \n");
        sb.append("        B.OBJECTIVE_YN,               \n");
        sb.append("        B.INSERT_DATE,                \n");
        sb.append("        B.INSERT_ID                   \n");
        sb.append("   FROM TRESEARCHM A,                 \n");
        sb.append("        TRESEARCHM1 B                 \n");        
        sb.append("  WHERE A.RESEARCH_NO = B.RESEARCH_NO \n");
        sb.append("    AND A.RESEARCH_NO LIKE ? || '%'   \n");        
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("research_no  : " + retrieve.getString("research_no"));                           
         }
         return sb.toString();
     }
    
    
    public String makeSqlA( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT DISTINCT NVL(B.RESEARCH_NO, C.RESEARCH_NO) AS RESEARCH_NO, \n");
        sb.append("        NVL(B.QUESTION_NO, C.QUESTION_NO) AS QUESTION_NO,          \n");
        sb.append("        B.ANSWER_NO,                                               \n");
        sb.append("        NVL(B.ANSWER, C.ANSWER) AS ANSWER,                         \n");
        sb.append("        B.BR_YN,                                                   \n");
        sb.append("        B.INSERT_DATE,                                             \n");
        sb.append("        B.INSERT_ID,                                               \n");
        sb.append("        B.OBJECTIVE_YN,                                            \n");
        sb.append("        B.LINK_QUESTION_NO,                                        \n");
        sb.append("        NVL(B.ANSWER_CNT, 0) AS ANSWER_CNT                         \n");
        sb.append("   FROM TRESEARCHM A,                                              \n");
        sb.append("        TRESEARCHDT B,                                             \n");
        sb.append("        TRESEARCHHISDT C                                           \n");
        sb.append("  WHERE A.RESEARCH_NO = B.RESEARCH_NO                              \n");
        sb.append("    AND B.RESEARCH_NO = C.RESEARCH_NO(+)                           \n");
        sb.append("    AND B.QUESTION_NO = C.QUESTION_NO(+)                           \n");
        sb.append("    AND A.RESEARCH_NO = ?                           \n");
        sb.append("    AND B.QUESTION_NO = ?                           \n");
         
         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("research_no  : " + retrieve.getString("research_no"));
             log.debug("question_no  : " + retrieve.getString("question_no"));
         }
         return sb.toString();
     }
    
    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tresearchm )
     * </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlResearchm = "   INSERT INTO TRESEARCHM ( \n "
     								+"                    RESEARCH_NO,      \n "
     								+"                    RESEARCH_TITLE,   \n "
     								+"                    RESEARCH_IMAGE,   \n "
     								+"                    START_DATE,       \n "     								
     								+"                    END_DATE,         \n "
     								+"                    POINT_AMT,        \n "     								
     								+"                    RESEARCH_CNT,     \n "
     								+"                    DISPLAY_YN,       \n "     								     								
     								+"                    INSERT_DATE,      \n "
     								+"                    INSERT_ID)        \n "
     								+"             VALUES (   \n "
     								+"                    ?, \n "
     								+"                    ?, \n "
     								+"                    ?, \n "
     								+"                    TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
     								+"                    TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
     								+"                    ?, \n "
     								+"                    ?, \n "
     								+"                    ?, \n "     								
     								+"                    SYSDATE, \n "
     								+"                    ?) \n " ;
     private int insertSqlResearchmLog =  0;     

     public String makeSqlInsert(Tresearchm tresearchm) throws StoreException{
 	   	//= log SQL -------------------------------------------------
 		if (insertSqlResearchmLog == 0) {
 			if (logSave.isDebugEnabled()) {
 				logSave.debug(insertSqlResearchm);
 			}
 			insertSqlResearchmLog = 1;
 		}
 		return insertSqlResearchm;
 	}
     
     /**
      * <PRE>
      * Desc : Make SQL ( Update Tresearchm )
      * </PRE>
      * @param   Tresearchm
      * @return  String
      */
     private final String updateSqlTresearchm = " UPDATE TRESEARCHM SET \n "
                                             + "         RESEARCH_TITLE  = ?,  \n "
                                             + "         RESEARCH_IMAGE  = ?,  \n "
                                             + "         START_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             + "         END_DATE   = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             + "         DISPLAY_YN  = ?,  \n "
                                             + "         POINT_AMT  = ?  \n "                                             
                                             + "   WHERE RESEARCH_NO    = ?  \n ";
     private int updateSqlTresearchmLog =  0;
     private String makeSqlUpdate(Tresearchm tresearchm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTresearchmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTresearchm);
     		}
     	   updateSqlTresearchmLog = 1;
     	}
     	return updateSqlTresearchm;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( Delete Tresearchm  )
       * </PRE>
       * @param   Tresearch
       * @return  String
       */
     private final String deleteSqlTresearchm = " DELETE FROM TRESEARCHM \n "
                                              + "  WHERE RESEARCH_NO   = ?  \n " ;
 	private int deleteSqlTresearchmLog =  0;
 	private String makeSqlDelete(Tresearchm tresearchm) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTresearchmLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTresearchm);
     		}
     	   deleteSqlTresearchmLog = 1;
     	}
     	return deleteSqlTresearchm;
     }

    /**
     * <PRE>
 Desc : Make SQL ( Insert Tresearchm )
 </PRE>
     * @param   
     * @return  String SQL
     */
     private final String insertSqlResearchm1 = "INSERT INTO TRESEARCHM1 ( \n "
     								+"                  RESEARCH_NO,       \n "
     								+"                  QUESTION_NO,       \n "
     								+"                  QUESTION,          \n "
     								+"                  OBJECTIVE_YN,      \n "								
     								+"                  INSERT_DATE,       \n "
     								+"                  INSERT_ID)         \n "
     								+"           VALUES (   \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "
     								+"                  ?, \n "	     								
     								+"                  SYSDATE, \n "
     								+"                  ?) \n " ;
     private int insertSqlResearchm1Log =  0;     

         private final String updateSqlTresearchm1 = " UPDATE  TRESEARCHM1 SET \n "
                                               + "         QUESTION        = ?,  \n "
                                               + "         OBJECTIVE_YN    = ?   \n "                                               
                                               + "   WHERE RESEARCH_NO     = ?   \n "
     										   + "     AND QUESTION_NO     = ?   \n ";
     private int updateSqlTresearchm1Log =  0;
     private String makeSqlUpdatea(Tresearchm tresearchm1) {
         //= log SQL -------------------------------------------------
     	if (updateSqlTresearchm1Log == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTresearchm1);
     		}
     	    updateSqlTresearchm1Log = 1;
     	}
     	return updateSqlTresearchm1;
      }

      /**
       * <PRE>
 Desc : Make SQL ( Delete Tresearchm )
 </PRE>
       * @param   Tresearchm1
       * @return  String
       */
     private final String deleteSqlTresearchm1 = " DELETE FROM TRESEARCHM1 \n "
                                               + "  WHERE RESEARCH_NO   = ?  \n " 
     										   + "    AND QUESTION_NO   = ?  \n " ;
 	private final int deleteSqlTresearchm1Log =  0;
 	
     	
     }
 	
    /**
     * <PRE>
 Desc : Make SQL ( Delete Tresearchm )
 </PRE>
     * @param   Tresearchm1
     * @return  String
     */
  // private final String deleteSqlTresearchm1dt = " DELETE FROM TRESEARCHDT \n "
  //                                           + "  WHERE RESEARCH_NO   = ?  \n " 
//   										   + "    AND QUESTION_NO   = ?  \n " ;
//	private int deleteSqlTresearchm1dtLog =  0;
//	private String makeSqlDeleteDT(Tresearchm tresearchm1) throws StoreException{
       //= log SQL -------------------------------------------------
   //	if (deleteSqlTresearchm1dtLog == 0) {
   //	    if (logSave.isDebugEnabled()) {
  // 		    logSave.debug(deleteSqlTresearchm1dt);
   //		}
   //	    deleteSqlTresearchm1dtLog = 1;
   //	}
   //	return deleteSqlTresearchm1dt;
   //} 	

    /**
     * <PRE>
     * Desc : Make SQL ( Insert Tresearchdt )
     * </PRE>
     * @param   
     * @return  String SQL
     */
//     private final String insertSqlTresearchdt = "   INSERT INTO TRESEARCHDT ( \n "
  //   							              	+"          RESEARCH_NO,       \n "
    // 								            +"          QUESTION_NO,       \n "
     //							 	            +"          ANSWER_NO,         \n "
     	//							            +"          ANSWER,            \n "
     	//							            +"          BR_YN,             \n "
     	//							            +"          LINK_QUESTION_NO,  \n "
     	//							            +"          OBJECTIVE_YN,      \n "
     	//							            +"          ANSWER_CNT,        \n "     								            
     	//							            +"          INSERT_DATE,       \n "
     	//							            +"          INSERT_ID)         \n "
     	//							            +"  VALUES (   \n "  
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "
     	//							            +"          ?, \n "     								                 								            
     	//							            +"          SYSDATE, \n "
     	//							            +"          ?) \n " ;
    // private int insertSqlTresearchdtLog =  0;     
/**
     public String makeSqlInsert(Tresearchdt Tresearchdt) throws StoreException{
 	   	//= log SQL -------------------------------------------------
 		if (insertSqlTresearchdtLog == 0) {
 			if (logSave.isDebugEnabled()) {
 				logSave.debug(insertSqlTresearchdt);
 			}
 			insertSqlTresearchdtLog = 1;
 		}
 		return insertSqlTresearchdt;
 	}
    
     /**
      * <PRE>
      * Desc : Make SQL (Update Tresearchdt )
      * </PRE>
      * @param   Tskintestm
      * @return  String
      */
/**   
private final String updateSqlTresearchdt = " UPDATE TRESEARCHDT SET        \n "
                                               + "        ANSWER = ?,            \n "
                                               + "        LINK_QUESTION_NO = ?,  \n "
                                               + "        OBJECTIVE_YN = ?,      \n "
                                               + "        BR_YN = ?,             \n "
                                               + "        ANSWER_CNT = ?         \n " 
                                               + "  WHERE RESEARCH_NO    = ?     \n "
     										   + "    AND QUESTION_NO    = ?     \n "
     										   + "    AND ANSWER_NO      = ?     \n ";
     private int updateSqlTresearchdtLog =  0;
     private String makeSqlUpdate(Tresearchdt tresearchdt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (updateSqlTresearchdtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(updateSqlTresearchdt);
     		}
     	    updateSqlTresearchdtLog = 1;
     	}
     	return updateSqlTresearchdt;
      }

      /**
       * <PRE>
       * Desc : Make SQL (Delete Tresearchdt )
       * </PRE>
    
       * @return  String
       */
    /** private final String deleteSqlTresearchdt = " DELETE FROM TRESEARCHDT \n "
                                               + "  WHERE RESEARCH_NO   = ?  \n " 
     										   + "    AND QUESTION_NO   = ?  \n " 
     										   + "    AND ANSWER_NO     = ?  \n " ;
 	private int deleteSqlTresearchdtLog =  0;
 	private String makeSqlDelete(Tresearchdt tresearchdt) throws StoreException{
         //= log SQL -------------------------------------------------
     	if (deleteSqlTresearchdtLog == 0) {
     	    if (logSave.isDebugEnabled()) {
     		    logSave.debug(deleteSqlTresearchdt);
     		}
     	    deleteSqlTresearchdtLog = 1;
     	}
     	return deleteSqlTresearchdt;
     }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
 /**   public HashMap[] makeSheet(ResultSet rset)  throws Exception {
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
/**
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("sum_fromDate"));
            pstmt.setString(index++, retrieve.getString("sum_toDate"));
            pstmt.setString(index++, retrieve.getString("research_no"));            

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
/**    
public RetrieveModel retrieveQ(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlQ(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("research_no"));                                

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTQ", makeSheet(rset));

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
/**   
public RetrieveModel retrieveA(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlA(retrieve));
            
            int index = 1;
            pstmt.setString(index++, retrieve.getString("research_no"));
            pstmt.setString(index++, retrieve.getString("question_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULTA", makeSheet(rset));

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
    * Desc : Insert Tresearchm
    * </PRE>
    * @param   Connection
    * @param   Tresearchm
    * @return  int
    */
/**   
public int insert(Connection con, Tresearchm tresearchm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tresearchm));
            
            int index = 1;

            pstmt.setString(index++, tresearchm.getResearch_no  ());
            pstmt.setString(index++, tresearchm.getResearch_title());
            pstmt.setString(index++, tresearchm.getResearch_image());
            pstmt.setString(index++, tresearchm.getStart_date  ());
            pstmt.setString(index++, tresearchm.getEnd_date());
            pstmt.setString(index++, tresearchm.getPoint_amt());
            pstmt.setString(index++, tresearchm.getResearch_cnt  ());
            pstmt.setString(index++, tresearchm.getDisplay_yn());
            pstmt.setString(index++, tresearchm.getInsert_id());                       

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tresearchm.getResearch_no    ()); logString.append( "/" );
            logString.append( tresearchm.getResearch_title ()); logString.append( "/" );
            logString.append( tresearchm.getResearch_image ()); logString.append( "/" );
            logString.append( tresearchm.getStart_date     ()); logString.append( "/" );
            logString.append( tresearchm.getEnd_date       ()); logString.append( "/" );
            logString.append( tresearchm.getPoint_amt      ()); logString.append( "/" );
            logString.append( tresearchm.getResearch_cnt   ()); logString.append( "/" );
            logString.append( tresearchm.getDisplay_yn     ()); logString.append( "/" );
            logString.append( tresearchm.getInsert_id      ()); logString.append( "/" );            

            
            logSave.info("\n" + logString.toString());
                              
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

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tresearchm
    * </PRE>
    * @param   Connection
    * @param   Tresearchm
    * @return  int
    */
/**   
public int update(Connection con, Tresearchm tresearchm) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tresearchm));
            
            int index = 1;

            pstmt.setString(index++, tresearchm.getResearch_title  ());
            pstmt.setString(index++, tresearchm.getResearch_image  ());
            pstmt.setString(index++, tresearchm.getStart_date      ());
            pstmt.setString(index++, tresearchm.getEnd_date        ());
            pstmt.setString(index++, tresearchm.getDisplay_yn      ());
            pstmt.setString(index++, tresearchm.getPoint_amt       ());
            pstmt.setString(index++, tresearchm.getResearch_no     ());               

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tresearchm.getResearch_title  ()); logString.append( "/" );
            logString.append( tresearchm.getResearch_image  ()); logString.append( "/" );
            logString.append( tresearchm.getStart_date      ()); logString.append( "/" );
            logString.append( tresearchm.getEnd_date        ()); logString.append( "/" );
            logString.append( tresearchm.getDisplay_yn      ()); logString.append( "/" );
            logString.append( tresearchm.getPoint_amt       ()); logString.append( "/" );
            logString.append( tresearchm.getResearch_no     ()); logString.append( "/" );           

            logSave.info("\n" + logString.toString());

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
    
    //= Delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tresearchm
    * </PRE>
    * @param   Connection
    * @param   Tresearchm
    * @return  int
    */
   

