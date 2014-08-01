package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.common.MultipurposeEntity;
import com.cware.back.entity.table.Tfavoritesdt;
import com.cware.back.entity.table.Tfavoritesm;

/**
 * User service Bean
 *
 * @version 1.0, 2006/05/08
 * @author kim sungtaek [webzest@commerceware.co.kr]
 */
public class MenuSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MenuSvc() {}

    public Collection retrieve_lgroup(Connection con, String lgroup_code, String user_id) throws Exception {
        Collection   	  		collection = new ArrayList();
        PreparedStatement 		pstmt      = null;
        ResultSet 		  		rset       = null;
        MultipurposeEntity	  	tmenu 	   = null;
        long         	  		retRow     = 0;

        pstmt = con.prepareStatement(makeSqllgroup());
        pstmt.setString(1, lgroup_code.toUpperCase());
        pstmt.setString(2, user_id.toUpperCase());
//        pstmt.setString(3, lgroup_code.toUpperCase());
//        pstmt.setString(4, user_id.toUpperCase());
        rset = pstmt.executeQuery();

		while (rset!=null && rset.next()){
			tmenu = new MultipurposeEntity();
			tmenu.setString1	(rset.getString("LMENU_ID"));
			tmenu.setString2	(rset.getString("LMENU_NAME"));
			tmenu.setString3	(rset.getString("MMENU_ID"));
			tmenu.setString4	(rset.getString("MMENU_NAME"));
			tmenu.setString5	(rset.getString("DEPTH"));

			collection.add(tmenu);
            retRow++;
        }
        DBUtils.freeConnection(null, pstmt, rset);
		return collection;
    }

    public Collection retrieve_mgroup(Connection con, String lgroup_id, String user_id) throws Exception {
        Collection   	  		collection = new ArrayList();
        PreparedStatement 		pstmt      = null;
        ResultSet 		  		rset       = null;
        MultipurposeEntity	  	tmenu 	   = null;
        long         	  		retRow     = 0;

        pstmt = con.prepareStatement(makeSqlmgroup(lgroup_id, user_id));
        pstmt.setString(1, lgroup_id);
        pstmt.setString(2, user_id);
        rset = pstmt.executeQuery();

		while (rset!=null && rset.next()){
			tmenu = new MultipurposeEntity();
			tmenu.setString1	(rset.getString("MMENU_ID"));
			tmenu.setString2	(rset.getString("MMENU_NAME"));
			tmenu.setString3	(rset.getString("LMENU_ID"));

			collection.add(tmenu);
            retRow++;
        }
        DBUtils.freeConnection(null, pstmt, rset);
		return collection;
    }

    public String makeSqllgroup() throws StoreException{

        StringBuffer sb = new StringBuffer();

//        sb.append(" SELECT DISTINCT '1' AS DEPTH    	\n");
//        sb.append("        , A.LMENU_ID     		    \n");
//        sb.append("        , A.LMENU_NAME	   			\n");
//        sb.append("        , '' AS MMENU_ID   			\n");
//        sb.append("        , '' AS MMENU_NAME	        \n");
//        sb.append("   FROM TSECMENU  A,					\n");
//        sb.append("        TSECPERPROGRAM B             \n");
//        sb.append("  WHERE USE_YN ='1'       			\n");
//        sb.append("    AND A.LMENU_ID = B.LMENU_ID	   	\n");
//        sb.append("    AND A.MMENU_ID = B.MMENU_ID	   	\n");
//        sb.append("    AND A.LMENU_ID LIKE ?	   		\n");
//        sb.append("    AND B.USER_ID = ?   				\n");
//        sb.append(" UNION   							\n");
        sb.append(" SELECT DISTINCT  '2' AS DEPTH      	\n");
        sb.append("      , A.LMENU_ID				   	\n");
        sb.append("      , A.LMENU_NAME                	\n");
        sb.append("      , A.MMENU_ID                  	\n");
        sb.append("      , A.MMENU_NAME          	   	\n");
        sb.append("   FROM TSECMENU A,				   	\n");
        sb.append("        TSECPERPROGRAM B            	\n");
        sb.append("  WHERE USE_YN ='1'				   	\n");
        sb.append("    AND A.LMENU_ID = B.LMENU_ID	   	\n");
        sb.append("    AND A.MMENU_ID = B.MMENU_ID	   	\n");
        sb.append("    AND A.LMENU_ID LIKE ?	   		\n");
        sb.append("    AND B.USER_ID = ?  	 			\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    public String makeSqlmgroup(String lgroup_id, String user_id) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT DISTINCT     			\n");
        sb.append("        A.LMENU_ID				\n");
        sb.append("      , A.LMENU_NAME             \n");
        sb.append("      , A.MMENU_ID               \n");
        sb.append("      , A.MMENU_NAME          	\n");
        sb.append("   FROM TSECMENU A,				\n");
        sb.append("        TSECPERPROGRAM B         \n");
        sb.append("  WHERE USE_YN ='1'				\n");
        sb.append("    AND A.LMENU_ID = B.LMENU_ID	\n");
        sb.append("    AND A.MMENU_ID = B.MMENU_ID	\n");
        sb.append("    AND A.LMENU_ID = ?			\n");
        sb.append("    AND B.USER_ID = ?  			\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("lgroup_id  : " + lgroup_id);
            log.debug("user_id  : " + user_id);
        }
        return sb.toString();
    }

    public Collection retrieve_menu(Connection con, String lmenu_id, String mmenu_id, String user_id) throws Exception {
        Collection   	  		collection = new ArrayList();
        PreparedStatement 		pstmt      = null;
        ResultSet 		  		rset       = null;
        MultipurposeEntity	  	tmenu 	   = null;
        long         	  		retRow     = 0;

        pstmt = con.prepareStatement(makeSql(lmenu_id, mmenu_id, user_id));
        pstmt.setString(1, lmenu_id);
        pstmt.setString(2, mmenu_id);
        pstmt.setString(3, user_id);
        pstmt.setString(4, lmenu_id);
        pstmt.setString(5, mmenu_id);
        pstmt.setString(6, user_id);
        rset = pstmt.executeQuery();

		while (rset!=null && rset.next()){
			tmenu = new MultipurposeEntity();
			tmenu.setString1	(rset.getString("DEPTH"));
			tmenu.setString2	(rset.getString("P_ID"));
			tmenu.setString3	(rset.getString("P_NAME"));
			tmenu.setString4	(rset.getString("ID"));
			tmenu.setString5	(rset.getString("NAME"));
			tmenu.setString6	(rset.getString("PROGRAM_ID"));
			tmenu.setString7	(rset.getString("QUERY_YN"));
			tmenu.setString8	(rset.getString("INSERT_YN"));
			tmenu.setString9	(rset.getString("DELETE_YN"));
			tmenu.setString10	(rset.getString("SAVE_YN"));
			tmenu.setString11	(rset.getString("PRINT_YN"));
			tmenu.setString12	(rset.getString("EXCEL_YN"));

			collection.add(tmenu);
            retRow++;
        }
        DBUtils.freeConnection(null, pstmt, rset);
		return collection;
    }

    public String makeSql(String lmenu_id, String mmenu_id, String user_id) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DISTINCT '1' AS DEPTH                  	\n");
        sb.append("       , A.MMENU_ID AS P_ID				       	\n");
        sb.append("       , A.MMENU_NAME AS P_NAME                  \n");
        sb.append("       , A.SMENU_ID AS ID                        \n");
        sb.append("       , A.SMENU_NAME AS NAME      		       	\n");
        sb.append("       , '' AS PROGRAM_ID   						\n");
        sb.append("       , '' AS QUERY_YN  						\n");
        sb.append("       , '' AS INSERT_YN  						\n");
        sb.append("       , '' AS DELETE_YN  						\n");
        sb.append("       , '' AS SAVE_YN  							\n");
        sb.append("       , '' AS PRINT_YN                      	\n");
        sb.append("       , '' AS EXCEL_YN                      	\n");
        sb.append("    FROM TSECMENU A,				       			\n");
        sb.append("         TSECPERPROGRAM B           				\n");
        sb.append("   WHERE A.USE_YN ='1'  					       	\n");
        sb.append("     AND A.MMENU_ID = B.MMENU_ID					\n");
        sb.append("     AND A.LMENU_ID = ?	   					   	\n");
        sb.append("     AND A.MMENU_ID = ?						    \n");
        sb.append("     AND B.USER_ID = ?   		     	   		\n");
        sb.append("  UNION									       	\n");
        sb.append("  SELECT DISTINCT '2' AS DEPTH                  	\n");
        sb.append("       , A.SMENU_ID AS P_ID				       	\n");
        sb.append("       , A.SMENU_NAME AS P_NAME                 	\n");
        sb.append("       , C.SMENU_ID||C.SORT AS ID               	\n");
        sb.append("       , C.PROGRAM_NAME AS NAME  		       	\n");
//        sb.append("       , REPLACE(INITCAP(REPLACE(LTRIM(C.PROGRAM_ID, 'w_'), '_', ' ')), ' ') AS PROGRAM_ID    \n");
//        sb.append("       , REPLACE(INITCAP(REPLACE(substr(C.PROGRAM_ID, 3), '_', ' ')), ' ') AS PROGRAM_ID    \n");
        sb.append("       , C.PROGRAM_ID    						\n");
        sb.append("       , B.QUERY_YN  							\n");
        sb.append("       , B.INSERT_YN  							\n");
        sb.append("       , B.DELETE_YN  							\n");
        sb.append("       , B.SAVE_YN  								\n");
        sb.append("       , B.PRINT_YN  							\n");
        sb.append("       , B.EXCEL_YN  							\n");
        sb.append("    FROM TSECMENU A,						       	\n");
        sb.append("         TSECPERPROGRAM B,				       	\n");
        sb.append("         TSECPROGRAM C                          	\n");
        sb.append("   WHERE A.USE_YN ='1' 					       	\n");
        sb.append("   	AND C.USE_YN ='1' 					       	\n");
        sb.append("     AND A.MMENU_ID = B.MMENU_ID			       	\n");
        sb.append("     AND B.MMENU_ID = C.MMENU_ID			       	\n");
        sb.append("     AND A.SMENU_ID = C.SMENU_ID			       	\n");
        sb.append("     AND B.PROGRAM_ID = C.PROGRAM_ID				\n");
        sb.append("     AND A.LMENU_ID = ?	   					   	\n");
        sb.append("     AND A.MMENU_ID = ?					       	\n");
        sb.append("     AND B.USER_ID = ?  		 	       			\n");
        sb.append("   ORDER BY 4 ASC  		  						\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("lmenu_id  : " + lmenu_id);
            log.debug("mmenu_id  : " + mmenu_id);
            log.debug("user_id  : " + user_id);
        }
        return sb.toString();
    }

    //= Full Menu
    public Collection retrieve_menu(Connection con, String lgroup_code, String user_id) throws Exception {
        Collection   	  		collection = new ArrayList();
        PreparedStatement 		pstmt      = null;
        ResultSet 		  		rset       = null;
        MultipurposeEntity	  	tmenu 	   = null;
        long         	  		retRow     = 0;

        pstmt = con.prepareStatement(makeSql(lgroup_code, user_id));
        pstmt.setString(1, lgroup_code.toUpperCase());
        pstmt.setString(2, user_id.toUpperCase());
        pstmt.setString(3, lgroup_code.toUpperCase());
        pstmt.setString(4, user_id.toUpperCase());
        rset = pstmt.executeQuery();

		while (rset!=null && rset.next()){
			tmenu = new MultipurposeEntity();
			tmenu.setString1	(rset.getString("DEPTH"));
			tmenu.setString2	(rset.getString("P_ID"));
			tmenu.setString3	(rset.getString("P_NAME"));
			tmenu.setString4	(rset.getString("ID"));
			tmenu.setString5	(rset.getString("NAME"));
			tmenu.setString6	(rset.getString("PROGRAM_ID"));
			tmenu.setString7	(rset.getString("QUERY_YN"));
			tmenu.setString8	(rset.getString("INSERT_YN"));
			tmenu.setString9	(rset.getString("DELETE_YN"));
			tmenu.setString10	(rset.getString("SAVE_YN"));
			tmenu.setString11	(rset.getString("PRINT_YN"));
			tmenu.setString12	(rset.getString("EXCEL_YN"));
			tmenu.setString13	(rset.getString("LMENU_ID"));
			tmenu.setString14	(rset.getString("MMENU_ID"));

			collection.add(tmenu);
            retRow++;
        }
        DBUtils.freeConnection(null, pstmt, rset);
		return collection;
    }

    public String makeSql(String lgroup_code, String user_id) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DISTINCT '1' AS DEPTH                  	\n");
        sb.append("       , A.LMENU_ID AS LMENU_ID			       	\n");
        sb.append("       , A.MMENU_ID AS MMENU_ID                  \n");
        sb.append("       , A.MMENU_ID AS P_ID				       	\n");
        sb.append("       , A.MMENU_NAME AS P_NAME                  \n");
        sb.append("       , A.SMENU_ID AS ID                        \n");
        sb.append("       , A.SMENU_NAME AS NAME      		       	\n");
        sb.append("       , '' AS PROGRAM_ID   						\n");
        sb.append("       , '' AS QUERY_YN  						\n");
        sb.append("       , '' AS INSERT_YN  						\n");
        sb.append("       , '' AS DELETE_YN  						\n");
        sb.append("       , '' AS SAVE_YN  							\n");
        sb.append("       , '' AS PRINT_YN                      	\n");
        sb.append("       , '' AS EXCEL_YN                      	\n");
        sb.append("    FROM TSECMENU A,				       			\n");
        sb.append("         TSECPERPROGRAM B,				       	\n");
        sb.append("         TSECPROGRAM C                          	\n");
        sb.append("   WHERE A.USE_YN ='1'  					       	\n");
        sb.append("   	AND C.USE_YN ='1' 					       	\n");
        sb.append("     AND A.LMENU_ID = B.LMENU_ID			       	\n");
        sb.append("     AND A.MMENU_ID = B.MMENU_ID			       	\n");
        sb.append("     AND A.LMENU_ID = C.LMENU_ID			       	\n");
        sb.append("     AND B.MMENU_ID = C.MMENU_ID			       	\n");
        sb.append("     AND A.SMENU_ID = C.SMENU_ID			       	\n");
        sb.append("     AND B.PROGRAM_ID = C.PROGRAM_ID				\n");
        sb.append("     AND A.LMENU_ID LIKE ?						\n");
        sb.append("     AND B.USER_ID = ?   		     	   		\n");
        sb.append("  UNION									       	\n");
        sb.append("  SELECT DISTINCT '2' AS DEPTH                  	\n");
        sb.append("       , A.LMENU_ID AS LMENU_ID			       	\n");
        sb.append("       , A.MMENU_ID AS MMENU_ID                  \n");
        sb.append("       , A.SMENU_ID AS P_ID				       	\n");
        sb.append("       , A.SMENU_NAME AS P_NAME                 	\n");
        sb.append("       , C.SMENU_ID||LPAD(C.SORT, 3, '0') AS ID  \n");
        sb.append("       , C.PROGRAM_NAME AS NAME  		       	\n");
//        sb.append("       , REPLACE(INITCAP(REPLACE(LTRIM(C.PROGRAM_ID, 'w_'), '_', ' ')), ' ') AS PROGRAM_ID    \n");
//        sb.append("       , REPLACE(INITCAP(REPLACE(substr(C.PROGRAM_ID, 3), '_', ' ')), ' ') AS PROGRAM_ID    \n");
        sb.append("       , C.PROGRAM_ID    						\n");
        sb.append("       , B.QUERY_YN  							\n");
        sb.append("       , B.INSERT_YN  							\n");
        sb.append("       , B.DELETE_YN  							\n");
        sb.append("       , B.SAVE_YN  								\n");
        sb.append("       , B.PRINT_YN  							\n");
        sb.append("       , B.EXCEL_YN  							\n");
        sb.append("    FROM TSECMENU A,						       	\n");
        sb.append("         TSECPERPROGRAM B,				       	\n");
        sb.append("         TSECPROGRAM C                          	\n");
        sb.append("   WHERE A.USE_YN ='1' 					       	\n");
        sb.append("   	AND C.USE_YN ='1' 					       	\n");
        sb.append("     AND A.LMENU_ID = B.LMENU_ID			       	\n");
        sb.append("     AND A.MMENU_ID = B.MMENU_ID			       	\n");
        sb.append("     AND A.LMENU_ID = C.LMENU_ID			       	\n");
        sb.append("     AND B.MMENU_ID = C.MMENU_ID			       	\n");
        sb.append("     AND A.SMENU_ID = C.SMENU_ID			       	\n");
        sb.append("     AND B.PROGRAM_ID = C.PROGRAM_ID				\n");
        sb.append("     AND A.LMENU_ID LIKE ?			       		\n");
        sb.append("     AND B.USER_ID = ?  		 	       			\n");
        sb.append("  ORDER BY LMENU_ID ASC, MMENU_ID ASC, ID ASC	\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("lgroup_code  : " + lgroup_code);
            log.debug("user_id  : " + user_id);
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : MyMenu list
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveQuick(Connection con, RetrieveModel retrieve, String user_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlQuick(user_id));

            int index = 1;
            pstmt.setString(index++,user_id.toUpperCase());
            pstmt.setString(index++,user_id.toUpperCase());
            pstmt.setString(index++,user_id.toUpperCase());

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", ComUtils.makeSheet(rset));

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

    /**
     * <PRE>
     * Desc : MyMenu list
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
     public RetrieveModel retrieveQuickM(Connection con, RetrieveModel retrieve, String user_id) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;

         try {
             pstmt = con.prepareStatement(makeSqlQuickM(user_id));

             int index = 1;
             pstmt.setString(index++, user_id.toUpperCase());
             pstmt.setString(index++, user_id.toUpperCase());
             pstmt.setString(index++, user_id.toUpperCase());

             rset  = pstmt.executeQuery();

             retrieve.put("RESULT", ComUtils.makeSheet(rset));

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
     
     /**
      * <PRE>
      * Desc : MyMenu list
      * </PRE>
      * @param   Connection
      * @param   RetrieveModel
      * @return  RetrieveModel
      */
      public RetrieveModel retrieveQuickDT(Connection con, RetrieveModel retrieve, String user_id, String group_code) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;

          try {
              pstmt = con.prepareStatement(makeSqlQuickDT(user_id, group_code));

              int index = 1;
              pstmt.setString(index++, user_id.toUpperCase());
              pstmt.setString(index++, group_code.toUpperCase());

              rset  = pstmt.executeQuery();

              retrieve.put("RESULT_DETAIL", ComUtils.makeSheet(rset));

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
      
     /**
     * <PRE>
     * Desc : MyMenu list
     * </PRE>
     * @param user_id
     * @return
     * @throws StoreException
     */
    public String makeSqlQuick(String user_id) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT XXA.USER_ID                                     \n");
        sb.append("      , XXA.GROUP_CODE                                  \n");
        sb.append("      , XXA.GROUP_NAME                                  \n");
        sb.append("      , XXA.P_GROUP_ID                                  \n");
        sb.append("      , XXA.SORT AS M_SORT                              \n");
        sb.append("      , XB.SORT  AS DT_SORT                             \n");
        sb.append("      , XB.PROGRAM_ID                                   \n");
        sb.append("      , MAX(XD.PROGRAM_NAME) AS PROGRAM_NAME            \n");
        sb.append("      , MIN(XC.LMENU_ID)     AS LMENU_ID                \n");
        sb.append("      , MIN(XC.MMENU_ID)     AS MMENU_ID                \n");
        sb.append("      , MIN(XD.SMENU_ID)     AS SMENU_ID                \n");
        sb.append("      , MAX(XC.QUERY_YN)     AS QUERY_YN                \n");
        sb.append("      , MAX(XC.INSERT_YN)    AS INSERT_YN               \n");
        sb.append("      , MAX(XC.DELETE_YN)    AS DELETE_YN               \n");
        sb.append("      , MAX(XC.SAVE_YN)      AS SAVE_YN                 \n");
        sb.append("      , MAX(XC.PRINT_YN)     AS PRINT_YN                \n");
        sb.append("      , MAX(XC.EXCEL_YN)     AS EXCEL_YN                \n");
        sb.append("   FROM (                                               \n");
        sb.append("          SELECT XA.USER_ID                             \n");
        sb.append("               , XA.GROUP_CODE                          \n");
        sb.append("               , XA.GROUP_NAME                          \n");
        sb.append("               , XA.P_GROUP_ID                          \n");
        sb.append("               , XA.SORT                                \n");
        sb.append("               , LEVEL AS LEV                           \n");
        sb.append("            FROM ( SELECT TA.USER_ID                    \n");
        sb.append("                        , TA.GROUP_CODE                 \n");
        sb.append("                        , TA.GROUP_NAME                 \n");
        sb.append("                        , TA.P_GROUP_ID                 \n");
        sb.append("                        , TA.SORT                       \n");
        sb.append("                     FROM TFAVORITESM TA                \n");
        sb.append("                    WHERE TA.USER_ID = ?			       \n");
        sb.append("                    UNION ALL                           \n");
        sb.append("                   SELECT ?			                   \n");
        sb.append("                        , 'ROOT' AS GROUP_CODE          \n");
        sb.append("                        , 'DEFAULT' AS GROUP_NAME       \n");
        sb.append("                        , '0'    AS P_GROUP_ID          \n");
        sb.append("                        , '0'   AS SORT                 \n");
        sb.append("                     FROM DUAL TB                       \n");
        sb.append("                 ) XA                                   \n");
        sb.append("           WHERE XA.USER_ID LIKE ?			           \n");
        sb.append("         CONNECT BY PRIOR XA.GROUP_CODE = XA.P_GROUP_ID \n");
        sb.append("             AND PRIOR    XA.USER_ID    = XA.USER_ID    \n");
        sb.append("           START WITH     XA.P_GROUP_ID = '0'           \n");
        sb.append("        ) XXA                                           \n");
        sb.append("        , TFAVORITESDT    XB                            \n");
        sb.append("        , TSECPERPROGRAM  XC                            \n");
        sb.append("        , TSECPROGRAM     XD                            \n");
        sb.append("    WHERE XXA.USER_ID    = XB.USER_ID(+)                \n");
        sb.append("      AND XXA.GROUP_CODE = XB.GROUP_CODE(+)             \n");
        sb.append("      AND XB.USER_ID     = XC.USER_ID(+)                \n");
        sb.append("      AND XB.PROGRAM_ID  = XC.PROGRAM_ID(+)             \n");
        sb.append("      AND XB.PROGRAM_ID  = XD.PROGRAM_ID(+)             \n");
        sb.append(" GROUP BY  XXA.USER_ID                                  \n");
        sb.append("      , XXA.GROUP_CODE                                  \n");
        sb.append("      , XXA.GROUP_NAME                                  \n");
        sb.append("      , XXA.P_GROUP_ID                                  \n");
        sb.append("      , XXA.SORT                                        \n");
        sb.append("      , XB.PROGRAM_ID                                   \n");
        sb.append("      , XB.SORT                                         \n");
        sb.append("      , XXA.LEV                                         \n");
        sb.append(" ORDER BY XXA.LEV || XXA.SORT                           \n");
        sb.append("        , XB.SORT                                       \n");
        sb.append("        , XB.PROGRAM_ID                                 \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("user_id  : " + user_id);
        }
        return sb.toString();
    }


    /**
     * <PRE>
     * Desc : MyMenu list
     * </PRE>
     * @param user_id
     * @return
     * @throws StoreException
     */
    public String makeSqlQuickM(String user_id) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT XA.USER_ID                               \n");
        sb.append("      , XA.GROUP_CODE                            \n");
        sb.append("      , XA.GROUP_NAME                            \n");
        sb.append("      , XA.P_GROUP_ID                            \n");
        sb.append("      , XA.SORT                                  \n");
        sb.append("      , LEVEL AS LEV                             \n");
        sb.append("   FROM ( SELECT TA.USER_ID                      \n");
        sb.append("               , TA.GROUP_CODE                   \n");
        sb.append("               , TA.GROUP_NAME                   \n");
        sb.append("               , TA.P_GROUP_ID                   \n");
        sb.append("               , TA.SORT                         \n");
        sb.append("            FROM TFAVORITESM TA                  \n");
        sb.append("           WHERE TA.USER_ID = ?                  \n");
        sb.append("           UNION ALL                             \n");
        sb.append("          SELECT  ?   			                \n");
        sb.append("               , 'ROOT' AS GROUP_CODE            \n");
        sb.append("               , 'DEFAULT' AS GROUP_NAME         \n");
        sb.append("               , '0'    AS P_GROUP_ID            \n");
        sb.append("               , '0'   AS SORT                   \n");
        sb.append("            FROM DUAL TB                         \n");
        sb.append("        ) XA                                     \n");
        sb.append("  WHERE XA.USER_ID = ?       			        \n");
        sb.append(" CONNECT BY PRIOR XA.GROUP_CODE = XA.P_GROUP_ID  \n");
        sb.append("    AND PRIOR    XA.USER_ID    = XA.USER_ID      \n");
        sb.append("  START WITH     XA.P_GROUP_ID = '0'             \n");
        sb.append(" ORDER BY LEVEL || XA.SORT                       \n");
          
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("user_id  : " + user_id);
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : MyMenu list
     * </PRE>
     * @param user_id
     * @return
     * @throws StoreException
     */
    public String makeSqlQuickDT(String user_id, String group_code) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT TA.USER_ID                           \n");
        sb.append("      , TA.GROUP_CODE                        \n");
        sb.append("      , TA.PROGRAM_ID                        \n");
        sb.append("      , MAX(TB.PROGRAM_NAME) AS PROGRAM_NAME \n");
        sb.append("      , TA.SORT                              \n");
        sb.append("      , TA.REMARK                            \n");
        sb.append("   FROM TFAVORITESDT TA                      \n");
        sb.append("      , TSECPROGRAM  TB                      \n");
        sb.append("  WHERE TA.PROGRAM_ID = TB.PROGRAM_ID        \n");
        sb.append("    AND TA.USER_ID    = ?                    \n");
        sb.append("    AND TA.GROUP_CODE = ?                    \n");
        sb.append("  GROUP BY TA.USER_ID                        \n");
        sb.append("         , TA.GROUP_CODE                     \n");
        sb.append("         , TA.PROGRAM_ID                     \n");
        sb.append("         , TA.SORT                           \n");
        sb.append("         , TA.REMARK                         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("user_id  : " + user_id);
            log.debug("group_code  : " + group_code);
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Insert TFAVORITESM )
     * </PRE>
     * @return  String
     */
     private final String insertSqlTfvtm =  " INSERT INTO TFAVORITESM ( \n "
                                              +"        USER_ID, \n "
                                              +"        GROUP_CODE, \n "
                                              +"        GROUP_NAME,    \n "
                                              +"        P_GROUP_ID,  \n "
                                              +"        SORT,    \n "
                                              +"        INSERT_DATE, \n "
                                              +"        INSERT_ID,   \n "
                                              +"        MODIFY_DATE, \n "
                                              +"        MODIFY_ID)   \n "
                                              +"VALUES (   \n "
                                              +"        ?, \n "
                                              +"        (SELECT LTRIM(TO_CHAR(NVL(MAX(TO_NUMBER(GROUP_CODE)), 0) + 1,'00000')) \n "
                                              +"           FROM TFAVORITESM \n "
                                              +"          WHERE USER_ID = ?), \n "
                                              +"        ?, \n "
                                              +"        ?, \n "
                                              +"        ?, \n "
                                              +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              +"        ?, \n "
                                              +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                              +"        ?) \n ";

    private int insertSqlLogTfvtm =  0;    
    private String makeSqlMInsert() throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTfvtm == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTfvtm);
            }
            insertSqlLogTfvtm = 1;
        }
        return insertSqlTfvtm;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update TFAVORITESM )
    * </PRE>
    * @return  String
    */
    private final String updateSqlTfvtm =   " UPDATE TFAVORITESM \n "
                                          + "    SET GROUP_NAME  = ?, \n "
                                          + "        P_GROUP_ID  = ?, \n "
                                          + "        SORT		 = ?, \n "
                                          + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                          + "        MODIFY_ID   = ? \n "
                                          + "  WHERE USER_ID     = ? \n "
                                          + "    AND GROUP_CODE  = ? \n ";

    private int updateSqlLogTfvtm =  0;

    private String makeSqlUpdateM() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTfvtm == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTfvtm);
            }
            updateSqlLogTfvtm = 1;
        }
        return updateSqlTfvtm;
    }
    

    /**
    * <PRE>
    * Desc : Make SQL ( Update TMDLINK )
    * </PRE>
    * @param   Tmdlink
    * @return  String
    */
    private final String deleteSqlTfvtm =   " DELETE FROM TFAVORITESM \n "
                                          + "  WHERE USER_ID     = ? \n "
                                          + "    AND GROUP_CODE  = ? \n ";

    private int deleteSqlLogTfvtm =  0;

    private String makeSqlDeleteM() throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTfvtm == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTfvtm);
            }
            deleteSqlLogTfvtm = 1;
        }
        return deleteSqlTfvtm;
    }    
    
    /**
     * <PRE>
     * Desc : Make SQL ( TFAVORITESM GROUP_CODE  중복 체크 )
     * </PRE>
     * @return  String
     */
     public String makeSqlDupMCheck() throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT COUNT(GROUP_CODE) 	\n ");
		 sb.append("  FROM TFAVORITESM		\n ");
		 sb.append(" WHERE USER_ID = ?   	\n ");
		 sb.append("   AND GROUP_CODE = ? 	\n ");


         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ( Insert TFAVORITESDT )
      * </PRE>
      * @return  String
      */
      private final String insertSqlTfvtdt =  " INSERT INTO TFAVORITESDT ( \n "
                                               +"        USER_ID, \n "
                                               +"        GROUP_CODE, \n "
                                               +"        PROGRAM_ID,  \n "
                                               +"        SORT,    \n "
                                               +"        INSERT_DATE, \n "
                                               +"        INSERT_ID)   \n "
                                               +"VALUES (   \n "
                                               +"        ?, \n "
                                               +"        ?, \n "
                                               +"        ?, \n "
                                               +"        ?, \n "
                                               +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                               +"        ?) \n ";

      private int insertSqlLogTfvtdt =  0;    
     private String makeSqlDtInsert() throws StoreException{
         //= log SQL -------------------------------------------------
         if (insertSqlLogTfvtdt == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(insertSqlTfvtdt);
             }
             insertSqlLogTfvtdt = 1;
         }
         return insertSqlTfvtdt;
     }


     /**
     * <PRE>
     * Desc : Make SQL ( Update TFAVORITESDT )
     * </PRE>
     * @return  String
     */
     private final String updateSqlTfvtdt =   " UPDATE TFAVORITESDT \n "
                                           + "    SET SORT		  = ? \n "
                                           + "  WHERE USER_ID     = ? \n "
                                           + "    AND GROUP_CODE  = ? \n "
                                           + "    AND PROGRAM_ID  = ? \n ";

     private int updateSqlLogTfvtdt =  0;

     private String makeSqlUpdateDt() throws StoreException{
         //= log SQL -------------------------------------------------
         if (updateSqlLogTfvtdt == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(updateSqlTfvtdt);
             }
             updateSqlLogTfvtdt = 1;
         }
         return updateSqlTfvtdt;
     }
     

     /**
     * <PRE>
     * Desc : Make SQL ( Update TMDLINK )
     * </PRE>
     * @param   Tmdlink
     * @return  String
     */
     private final String deleteSqlTfvtdt =   " DELETE FROM TFAVORITESDT \n "
                                           + "  WHERE USER_ID     = ? \n "
                                           + "    AND GROUP_CODE  = ? \n "
                                           + "    AND PROGRAM_ID  LIKE ? \n ";

     private int deleteSqlLogTfvtdt =  0;

     private String makeSqlDeleteDt() throws StoreException{
         //= log SQL -------------------------------------------------
         if (deleteSqlLogTfvtdt == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(deleteSqlTfvtdt);
             }
             deleteSqlLogTfvtdt = 1;
         }
         return deleteSqlTfvtdt;
     }

     /**
      * <PRE>
      * Desc : Make SQL ( TFAVORITESDT GROUP_CODE  중복 체크 )
      * </PRE>
      * @return  String
      */
      public String makeSqlDupDtCheck() throws StoreException{

          StringBuffer sb = new StringBuffer();

          sb.append("SELECT COUNT(GROUP_CODE) 	\n ");
 		 sb.append("  FROM TFAVORITESDT		\n ");
 		 sb.append(" WHERE USER_ID = ?   	\n ");
 		 sb.append("   AND GROUP_CODE = ? 	\n ");
 		 sb.append("   AND PROGRAM_ID = ? 	\n ");


          //= log SQL -------------------------------------------------
          if (logSave.isDebugEnabled()) {
              logSave.debug(sb.toString());
          }

          return sb.toString();
      }

      //= Insert -------------------------------------------------
      /**
      * <PRE>
      * Desc : Insert TFAVORITESM
      * </PRE>
      * @param   Connection
      * @param   Tfavoritesm
      * @return  int
      */
      public int insertM(Connection con, Tfavoritesm tfvtm) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;
          int executedRtn = 0;

          try {
              pstmt = con.prepareStatement(makeSqlMInsert());
              int index = 1;
              pstmt.setString(index++,tfvtm.getUser_Id());
              pstmt.setString(index++,tfvtm.getUser_Id());
              pstmt.setString(index++,tfvtm.getGroup_Name());
              pstmt.setString(index++,tfvtm.getP_Group_Id());
              pstmt.setString(index++,tfvtm.getSort());
              pstmt.setString(index++,tfvtm.getInsert_date());
              pstmt.setString(index++,tfvtm.getInsert_id());
              pstmt.setString(index++,tfvtm.getModify_date());
              pstmt.setString(index++,tfvtm.getModify_id());
              
              //= log Save Data ---------------------
              StringBuffer logString = new StringBuffer();
              logString.append( tfvtm.getUser_Id()      ); logString.append( "/" );
              logString.append( tfvtm.getUser_Id()      ); logString.append( "/" );
              logString.append( tfvtm.getGroup_Name()   ); logString.append( "/" );
              logString.append( tfvtm.getP_Group_Id()   ); logString.append( "/" );
              logString.append( tfvtm.getSort()   	   ); logString.append( "/" );
              logString.append( tfvtm.getInsert_date()  ); logString.append( "/" );
              logString.append( tfvtm.getInsert_id()    ); logString.append( "/" );
              logString.append( tfvtm.getModify_date()  ); logString.append( "/" );
              logString.append( tfvtm.getModify_id()    ); logString.append( "/" );

              logSave.info(logString.toString());

              executedRtn = pstmt.executeUpdate();

          }catch(SQLException se){
              logSave.error("[MenuSvc.insertM() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              logSave.error("[MenuSvc.insertM() Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return executedRtn;
      }

     //= Update -------------------------------------------------
     /**
     * <PRE>
     * Desc : Update TFAVORITESM
     * </PRE>
     * @param   Connection
     * @param   Tfavoritesm
     * @return  int
     */
     public int updateM(Connection con, Tfavoritesm tfvtm) throws StoreException{
         PreparedStatement pstmt       = null;
         int executedRtn = 0;

         try {
             pstmt = con.prepareStatement(makeSqlUpdateM());
             int index = 1;
             pstmt.setString(index++,tfvtm.getGroup_Name());
             pstmt.setString(index++,tfvtm.getSort());
             pstmt.setString(index++,tfvtm.getP_Group_Id());
             pstmt.setString(index++,tfvtm.getModify_date());
             pstmt.setString(index++,tfvtm.getModify_id());
             pstmt.setString(index++,tfvtm.getUser_Id());
             pstmt.setString(index++,tfvtm.getGroup_Code());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tfvtm.getGroup_Name()   ); logString.append( "/" );
             logString.append( tfvtm.getSort()   	   ); logString.append( "/" );
             logString.append( tfvtm.getP_Group_Id()   ); logString.append( "/" );
             logString.append( tfvtm.getModify_date()  ); logString.append( "/" );
             logString.append( tfvtm.getModify_id()    ); logString.append( "/" );
             logString.append( tfvtm.getUser_Id()      ); logString.append( "/" );
             logString.append( tfvtm.getGroup_Code()   ); logString.append( "/" );

             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("[MenuSvc.updateM() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[MenuSvc.updateM() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }


     //= Update -------------------------------------------------
     /**
     * <PRE>
     * Desc : delete TFAVORITESM
     * </PRE>
     * @param   Connection
     * @param   Tfavoritesm
     * @return  int
     */
     public int deleteM(Connection con, Tfavoritesm tfvtm) throws StoreException{
         PreparedStatement pstmt       = null;
         int executedRtn = 0;

         try {
             pstmt = con.prepareStatement(makeSqlDeleteM());
             int index = 1;
             pstmt.setString(index++,tfvtm.getUser_Id());
             pstmt.setString(index++,tfvtm.getGroup_Code());

             //= log Save Data ---------------------
             StringBuffer logString = new StringBuffer();
             logString.append( tfvtm.getUser_Id()      ); logString.append( "/" );
             logString.append( tfvtm.getGroup_Code()   ); logString.append( "/" );
             
             logSave.info(logString.toString());

             executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("[MenuSvc.deleteM() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[MenuSvc.deleteM() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }

    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tfavoritesm
    * </PRE>
    * @param   Connection
    * @param   Tfavoritesm
    * @return  int
    */
    public int checkMDup(Connection con, Tfavoritesm tfvtm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupMCheck());
            pstmt.setString(1, tfvtm.getUser_Id());
            pstmt.setString(2, tfvtm.getGroup_Code());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[MenuSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MenuSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TFAVORITESDT
    * </PRE>
    * @param   Connection
    * @param   Tfavoritesdt
    * @return  int
    */
    public int insertDt(Connection con, Tfavoritesdt tfvtdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDtInsert());
            int index = 1;
            pstmt.setString(index++,tfvtdt.getUser_Id());
            pstmt.setString(index++,tfvtdt.getGroup_Code());
            pstmt.setString(index++,tfvtdt.getProgram_Id());
            pstmt.setString(index++,tfvtdt.getSort());
            pstmt.setString(index++,tfvtdt.getInsert_date());
            pstmt.setString(index++,tfvtdt.getInsert_id());
            
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tfvtdt.getUser_Id()      ); logString.append( "/" );
            logString.append( tfvtdt.getGroup_Code()   ); logString.append( "/" );
            logString.append( tfvtdt.getProgram_Id()   ); logString.append( "/" );
            logString.append( tfvtdt.getSort()   	    ); logString.append( "/" );
            logString.append( tfvtdt.getInsert_date()  ); logString.append( "/" );
            logString.append( tfvtdt.getInsert_id()    ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MenuSvc.insertDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MenuSvc.insertDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TFAVORITESM
    * </PRE>
    * @param   Connection
    * @param   Tfavoritesm
    * @return  int
    */
    public int updateDt(Connection con, Tfavoritesdt tfvtdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDt());
            int index = 1;
            pstmt.setString(index++,tfvtdt.getSort());
            pstmt.setString(index++,tfvtdt.getUser_Id());
            pstmt.setString(index++,tfvtdt.getGroup_Code());
            pstmt.setString(index++,tfvtdt.getProgram_Id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tfvtdt.getSort()   	   ); logString.append( "/" );
            logString.append( tfvtdt.getUser_Id()      ); logString.append( "/" );
            logString.append( tfvtdt.getGroup_Code()   ); logString.append( "/" );
            logString.append( tfvtdt.getProgram_Id()   ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MenuSvc.updateDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MenuSvc.updateDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : delete TFAVORITESM
    * </PRE>
    * @param   Connection
    * @param   Tfavoritesm
    * @return  int
    */
    public int deleteDt(Connection con, Tfavoritesdt tfvtdt) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDeleteDt());
            int index = 1;
            pstmt.setString(index++,tfvtdt.getUser_Id());
            pstmt.setString(index++,tfvtdt.getGroup_Code());
            pstmt.setString(index++,tfvtdt.getProgram_Id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tfvtdt.getUser_Id()      ); logString.append( "/" );
            logString.append( tfvtdt.getGroup_Code()   ); logString.append( "/" );
            logString.append( tfvtdt.getProgram_Id()   ); logString.append( "/" );
            
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MenuSvc.deleteDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MenuSvc.deleteDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
    
    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tfavoritesdt
    * </PRE>
    * @param   Connection
    * @param   Tfavoritesdt
    * @return  int
    */
    public int checkDtDup(Connection con, Tfavoritesdt tfvtdt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupDtCheck());
            pstmt.setString(1, tfvtdt.getUser_Id());
            pstmt.setString(2, tfvtdt.getGroup_Code());
            pstmt.setString(3, tfvtdt.getProgram_Id());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[MenuSvc.checkDtDup() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MenuSvc.checkDtDup() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }    
}
