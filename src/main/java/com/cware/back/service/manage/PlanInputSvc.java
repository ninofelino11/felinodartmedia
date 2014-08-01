
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
import com.cware.back.entity.table.Tplan;
import com.cware.back.entity.table.Tplangoods;

/**
 * Register PlanInput Service class
 *
 * @version 1.0, 2009/07/23
 */
public class PlanInputSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PlanInputSvc() {}

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

        sb.append("  SELECT A.PLANCLASS_CODE,     	\n ");
        sb.append("         A.PLAN_CODE,     	\n ");
        sb.append("         A.PLAN_NAME,    	\n ");
        sb.append("         A.DISPLAY_PRIORITY,    	\n ");
        sb.append("         A.DISPLAY_YN,    	\n ");
        sb.append("        	TO_CHAR(A.DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE, \n");
        sb.append("        	TO_CHAR(A.DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE, \n");
        sb.append("         A.MGNT_ID,    	\n ");
        sb.append("         B.USER_NAME AS MGNT_NAME,     	\n ");
        sb.append("         A.PLAN_GB,    	\n ");
        sb.append("         A.CONTENT, 				\n ");
        sb.append("         A.IMAGE_URL,     		\n ");
        sb.append("         A.TARGET_URL,     		\n ");
        sb.append("         A.TEMPLATE_CODE,     	\n ");
        sb.append("         TCODE_NAME('B117',A.TEMPLATE_CODE) AS TEMPLATE_NAME, \n ");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,   		\n ");
        sb.append("         A.INSERT_ID,     		\n ");
        sb.append("         TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,   		\n ");
        sb.append("         A.MODIFY_ID      		\n ");
        sb.append("    FROM TPLAN  A,          \n ");
        sb.append("    		TUSER 	B           \n ");
        sb.append("   WHERE A.MGNT_ID = B.USER_ID(+)     \n ");
        sb.append("		AND A.PLANCLASS_CODE = ? 	\n ");
        sb.append("		AND A.PLAN_CODE LIKE ? || '%'	\n ");
        if( retrieve.getString("chk_query").equals("1") ){
            sb.append("     AND ( ( A.DISPLAY_START_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
            sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
            sb.append("          OR A.DISPLAY_END_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
            sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') )   \n ");
            sb.append("        OR ( TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.DISPLAY_START_DATE AND A.DISPLAY_END_DATE   \n ");
            sb.append("          OR TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.DISPLAY_START_DATE AND A.DISPLAY_END_DATE ) )   \n ");
            sb.append("		AND A.DISPLAY_YN LIKE ? || '%'      		\n ");
            sb.append("		AND NVL(A.PLAN_GB,' ') LIKE ? || '%'      		\n ");
        }
        sb.append("		ORDER BY A.DISPLAY_PRIORITY,A.PLAN_CODE	\n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("planclass_code 	: " + retrieve.getString("planclass_code"));
            log.debug("plan_code   		: " + retrieve.getString("plan_code"));
            log.debug("chk_query     	: " + retrieve.getString("chk_query"));
            log.debug("display_start_date : " + retrieve.getString("display_start_date"));
            log.debug("display_end_date : " + retrieve.getString("display_end_date"));
            log.debug("display_yn 		: " + retrieve.getString("display_yn"));
            log.debug("plan_gb 			: " + retrieve.getString("plan_gb"));
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

        sb.append("  SELECT A.PLANCLASS_CODE,  \n");
        sb.append("         A.PLAN_CODE,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         A.DISPLAY_PRIORITY,  \n");
        sb.append("         B.SALE_GB,  \n");
        sb.append("         B.SET_YN,  \n");
        sb.append("         FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'6') AS SALE_PRICE,  \n");
        sb.append("         FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'8') AS MARGIN_RATE,  \n");
        sb.append("         A.DISPLAY_YN,  \n");
        sb.append("         B.ENTP_CODE,  \n");
        sb.append("         E.ENTP_NAME,  \n");
        sb.append("         G.BRAND_NAME, \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,   		\n ");
        sb.append("         A.INSERT_ID,     		\n ");
        sb.append("         TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,   		\n ");
        sb.append("         A.MODIFY_ID      		\n ");
        sb.append("    FROM TPLANGOODS A,  \n");
        sb.append("         TGOODS         B,  \n");
        sb.append("         TENTERPRISE    E,  \n");
        sb.append("         TBRAND         G  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND B.ENTP_CODE     = E.ENTP_CODE  \n");
        sb.append("     AND B.BRAND_CODE    = G.BRAND_CODE  \n");
        sb.append("     AND A.PLAN_CODE = ?  \n");
        sb.append("		ORDER BY DISPLAY_PRIORITY	\n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TPLAN )
    * </PRE>
    * @param   Tplan
    * @return  String
    */
    private String makeSqlInsert(Tplan tplan) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TPLAN ( \n ");
        sb.append("          PLANCLASS_CODE,  \n ");
        sb.append("          PLAN_CODE,  \n ");
        sb.append("          PLAN_NAME,   \n ");
        sb.append("          DISPLAY_PRIORITY,  \n ");
        sb.append("          DISPLAY_YN,   \n ");
        sb.append("          DISPLAY_START_DATE,  \n ");
        sb.append("          DISPLAY_END_DATE,   \n ");
        sb.append("          MGNT_ID,   \n ");
        sb.append("          PLAN_GB,   \n ");
        sb.append("          CONTENT,   \n ");
        sb.append("          IMAGE_URL,   \n ");
        sb.append("          TARGET_URL,   \n ");
        sb.append("          TEMPLATE_CODE,   \n ");
        sb.append("          INSERT_DATE, \n ");
        sb.append("          INSERT_ID,   \n ");
        sb.append("          MODIFY_DATE, \n ");
        sb.append("          MODIFY_ID )   \n ");
        sb.append("  VALUES (   \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
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
    * Desc : Make SQL ( Update TPLAN )
    * </PRE>
    * @param   Tplan
    * @return  String
    */
    private String makeSqlUpdate(Tplan tplan) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TPLAN SET \n ");
        sb.append("         PLAN_NAME = ?, \n ");
        sb.append("         DISPLAY_PRIORITY  = ?, \n ");
        sb.append("         DISPLAY_YN  = ?, \n ");
        sb.append("         DISPLAY_START_DATE  = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         DISPLAY_END_DATE  = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MGNT_ID  = ?, \n ");
        sb.append("         PLAN_GB  = ?, \n ");
        sb.append("         CONTENT  = ?, \n ");
        sb.append("         IMAGE_URL  = ?, \n ");
        sb.append("         TARGET_URL  = ?, \n ");
        sb.append("         TEMPLATE_CODE  = ?, \n ");
        sb.append("         MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID   = ?  \n ");
        sb.append("   WHERE PLANCLASS_CODE     = ?  \n ");
        sb.append("   AND 	PLAN_CODE     = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TPLANGOODS )
    * </PRE>
    * @param   tplangoods
    * @return  String
    */
    private final String insertSqlTplangoods =  " INSERT INTO TPLANGOODS ( \n "
                                             +"        PLANCLASS_CODE, \n "
                                             +"        PLAN_CODE, \n "
                                             +"        GOODS_CODE,    \n "
                                             +"        DISPLAY_PRIORITY,  \n "
                                             +"        DISPLAY_YN,    \n "
                                             +"        INSERT_DATE, \n "
                                             +"        INSERT_ID,   \n "
                                             +"        MODIFY_DATE, \n "
                                             +"        MODIFY_ID)   \n "
                                             +"VALUES (   \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"        ?, \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"        ?) \n ";

    private int insertSqlLogTplangoods =  0;

    private String makeSqlInsert(Tplangoods tplangoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTplangoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTplangoods);
            }
            insertSqlLogTplangoods = 1;
        }
        return insertSqlTplangoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TPLANGOODS )
    * </PRE>
    * @param   tplangoods
    * @return  String
    */
    private final String updateSqlTplangoods = " UPDATE TPLANGOODS SET \n "
									        +"        DISPLAY_YN         = ?, \n"
									        +"        DISPLAY_PRIORITY   = ?, \n"
									        +"        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
									        +"        MODIFY_ID   = ? \n "
									        +"  WHERE PLANCLASS_CODE     = ? \n "
									        +"    AND PLAN_CODE     = ? \n "
									        +"    AND GOODS_CODE     = ? \n ";

    private int updateSqlLogTplangoods =  0;

    private String makeSqlUpdate(Tplangoods tplangoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTplangoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTplangoods);
            }
            updateSqlLogTplangoods = 1;
        }
        return updateSqlTplangoods;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete TPLANGOODS )
    * </PRE>
    * @param   Tplangoods
    * @return  String
    */
    private final String deleteSqlTplangoods =  " DELETE FROM TPLANGOODS WHERE PLANCLASS_CODE = ? AND PLAN_CODE = ? AND GOODS_CODE = ? \n";

    private int deleteSqlLogTplangoods =  0;

    private String makeSqlDelete(Tplangoods tplangoods) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTplangoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTplangoods);
            }
            deleteSqlLogTplangoods = 1;
        }
        return deleteSqlTplangoods;
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
            pstmt.setString(index++,retrieve.getString("planclass_code"));
            pstmt.setString(index++,retrieve.getString("plan_code"));
//            pstmt.setString(index++,retrieve.getString("md_name"));
            if( retrieve.getString("chk_query").equals("1") ){
                pstmt.setString(index++,retrieve.getString("display_start_date"));
                pstmt.setString(index++,retrieve.getString("display_end_date"));
                pstmt.setString(index++,retrieve.getString("display_start_date"));
                pstmt.setString(index++,retrieve.getString("display_end_date"));
                pstmt.setString(index++,retrieve.getString("display_start_date"));
                pstmt.setString(index++,retrieve.getString("display_end_date"));
                pstmt.setString(index++,retrieve.getString("use_yn"));
                pstmt.setString(index++,retrieve.getString("plan_gb"));
            }
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[PlanInputSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PlanInputSvc.retrieve() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,retrieve.getString("plan_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[PlanInputSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PlanInputSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TPLAN
    * </PRE>
    * @param   Connection
    * @param   Tplan
    * @return  int
    */
    public int insert(Connection con, Tplan tplan) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tplan));
            int index = 1;

            pstmt.setString(index++,tplan.getPlanclass_code());
            pstmt.setString(index++,tplan.getPlan_code());
            pstmt.setString(index++,tplan.getPlan_name());
            pstmt.setString(index++,tplan.getDisplay_priority());
            pstmt.setString(index++,tplan.getDisplay_yn());
            pstmt.setString(index++,tplan.getDisplay_start_date());
            pstmt.setString(index++,tplan.getDisplay_end_date());
            pstmt.setString(index++,tplan.getMgnt_id());
            pstmt.setString(index++,tplan.getPlan_gb());
            pstmt.setString(index++,tplan.getContent());
            pstmt.setString(index++,tplan.getImage_url());
            pstmt.setString(index++,tplan.getTarget_url());
            pstmt.setString(index++,tplan.getTemplate_code());
            pstmt.setString(index++,tplan.getInsert_date());
            pstmt.setString(index++,tplan.getInsert_id());
            pstmt.setString(index++,tplan.getModify_date());
            pstmt.setString(index++,tplan.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplan.getPlanclass_code()      ); logString.append( "/" );
            logString.append( tplan.getPlan_code()      	); logString.append( "/" );
            logString.append( tplan.getPlan_name()      	); logString.append( "/" );
            logString.append( tplan.getDisplay_priority()      ); logString.append( "/" );
            logString.append( tplan.getDisplay_yn()      	); logString.append( "/" );
            logString.append( tplan.getDisplay_start_date()      ); logString.append( "/" );
            logString.append( tplan.getDisplay_end_date()      ); logString.append( "/" );
            logString.append( tplan.getMgnt_id()      		); logString.append( "/" );
            logString.append( tplan.getPlan_gb()      		); logString.append( "/" );
            logString.append( tplan.getContent()      		); logString.append( "/" );
            logString.append( tplan.getImage_url()      	); logString.append( "/" );
            logString.append( tplan.getTarget_url()      	); logString.append( "/" );
            logString.append( tplan.getTemplate_code()      ); logString.append( "/" );
            logString.append( tplan.getInsert_date()  ); logString.append( "/" );
            logString.append( tplan.getInsert_id()    ); logString.append( "/" );
            logString.append( tplan.getModify_date()  ); logString.append( "/" );
            logString.append( tplan.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TPLAN
    * </PRE>
    * @param   Connection
    * @param   Tplan
    * @return  int
    */
    public int update(Connection con, Tplan tplan) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tplan));
            int index = 1;

            pstmt.setString(index++,tplan.getPlan_name());
            pstmt.setString(index++,tplan.getDisplay_priority());
            pstmt.setString(index++,tplan.getDisplay_yn());
            pstmt.setString(index++,tplan.getDisplay_start_date());
            pstmt.setString(index++,tplan.getDisplay_end_date());
            pstmt.setString(index++,tplan.getMgnt_id());
            pstmt.setString(index++,tplan.getPlan_gb());
            pstmt.setString(index++,tplan.getContent());
            pstmt.setString(index++,tplan.getImage_url());
            pstmt.setString(index++,tplan.getTarget_url());
            pstmt.setString(index++,tplan.getTemplate_code());
            pstmt.setString(index++,tplan.getModify_date());
            pstmt.setString(index++,tplan.getModify_id());
            pstmt.setString(index++,tplan.getPlanclass_code());
            pstmt.setString(index++,tplan.getPlan_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplan.getPlan_name()      	); logString.append( "/" );
            logString.append( tplan.getDisplay_priority()      ); logString.append( "/" );
            logString.append( tplan.getDisplay_yn()      	); logString.append( "/" );
            logString.append( tplan.getDisplay_start_date()      ); logString.append( "/" );
            logString.append( tplan.getDisplay_end_date()      ); logString.append( "/" );
            logString.append( tplan.getMgnt_id()      		); logString.append( "/" );
            logString.append( tplan.getPlan_gb()      		); logString.append( "/" );
            logString.append( tplan.getContent()      		); logString.append( "/" );
            logString.append( tplan.getImage_url()      	); logString.append( "/" );
            logString.append( tplan.getTarget_url()      	); logString.append( "/" );
            logString.append( tplan.getTemplate_code()      ); logString.append( "/" );
            logString.append( tplan.getInsert_date()  ); logString.append( "/" );
            logString.append( tplan.getInsert_id()    ); logString.append( "/" );
            logString.append( tplan.getModify_date()  ); logString.append( "/" );
            logString.append( tplan.getModify_id()    ); logString.append( "/" );
            logString.append( tplan.getPlanclass_code()      ); logString.append( "/" );
            logString.append( tplan.getPlan_code()      	); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanInputSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanInputSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TPLANGOODS
    * </PRE>
    * @param   Connection
    * @param   tplangoods
    * @return  int
    */
    public int insert(Connection con, Tplangoods tplangoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tplangoods));
            int index = 1;
            pstmt.setString(index++,tplangoods.getPlanclass_code());
            pstmt.setString(index++,tplangoods.getPlan_code());
            pstmt.setString(index++,tplangoods.getGoods_code());
            pstmt.setString(index++,tplangoods.getDisplay_priority());
            pstmt.setString(index++,tplangoods.getDisplay_yn());
            pstmt.setString(index++,tplangoods.getInsert_date());
            pstmt.setString(index++,tplangoods.getInsert_id());
            pstmt.setString(index++,tplangoods.getModify_date());
            pstmt.setString(index++,tplangoods.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplangoods.getPlanclass_code()      ); logString.append( "/" );
            logString.append( tplangoods.getPlan_code()      ); logString.append( "/" );
            logString.append( tplangoods.getGoods_code()         ); logString.append( "/" );
            logString.append( tplangoods.getDisplay_priority()   ); logString.append( "/" );
            logString.append( tplangoods.getDisplay_yn()     ); logString.append( "/" );
            logString.append( tplangoods.getInsert_date()  ); logString.append( "/" );
            logString.append( tplangoods.getInsert_id()    ); logString.append( "/" );
            logString.append( tplangoods.getModify_date()  ); logString.append( "/" );
            logString.append( tplangoods.getModify_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TPLANGOODS
    * </PRE>
    * @param   Connection
    * @param   Tplangoods
    * @return  int
    */
    public int update(Connection con, Tplangoods tplangoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tplangoods));
            int index = 1;

            pstmt.setString(index++,tplangoods.getDisplay_yn());
            pstmt.setString(index++,tplangoods.getDisplay_priority());
            pstmt.setString(index++,tplangoods.getModify_date());
            pstmt.setString(index++,tplangoods.getModify_id());
            pstmt.setString(index++,tplangoods.getPlanclass_code());
            pstmt.setString(index++,tplangoods.getPlan_code());
            pstmt.setString(index++,tplangoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( tplangoods.getDisplay_priority()   ); logString.append( "/" );
            logString.append( tplangoods.getDisplay_yn()     ); logString.append( "/" );
            logString.append( tplangoods.getModify_date()  ); logString.append( "/" );
            logString.append( tplangoods.getModify_id()    ); logString.append( "/" );
            logString.append( tplangoods.getPlanclass_code()      ); logString.append( "/" );
            logString.append( tplangoods.getPlan_code()      ); logString.append( "/" );
            logString.append( tplangoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanInputSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanInputSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }



    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete TPLANGOODS
    * </PRE>
    * @param   Connection
    * @param   Tplangoods
    * @return  int
    */
    public int delete(Connection con, Tplangoods tplangoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tplangoods));
            int index = 1;
            pstmt.setString(index++,tplangoods.getPlanclass_code());
            pstmt.setString(index++,tplangoods.getPlan_code());
            pstmt.setString(index++,tplangoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplangoods.getPlanclass_code()      ); logString.append( "/" );
            logString.append( tplangoods.getPlan_code()      ); logString.append( "/" );
            logString.append( tplangoods.getGoods_code()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanInputSvc.delete(Tplangoods) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanInputSvc.delete(Tplangoods) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
