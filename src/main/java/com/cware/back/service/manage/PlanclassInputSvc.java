
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
import com.cware.back.entity.table.Tplanclass;

/**
 * 기획전그룹등록 Service class
 *
 * @version 1.0, 2009/07/22
 */
public class PlanclassInputSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PlanclassInputSvc() {}

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

        sb.append("  SELECT A.PLANCLASS_CODE,     	\n ");
        sb.append("         A.PLANCLASS_NAME,     	\n ");
        sb.append("         A.CATEGORY_CODE,    	\n ");
        sb.append("         B.CATEGORY_NAME,     	\n ");
        sb.append("         A.USE_YN,        		\n ");
        sb.append("         A.IMAGE_URL,     		\n ");
        sb.append("         A.TARGET_URL,     		\n ");
        sb.append("         A.TEMPLATE_CODE,     	\n ");
        sb.append("         TCODE_NAME('B117',A.TEMPLATE_CODE) AS TEMPLATE_NAME, \n ");
        sb.append("         A.CONTENT, 				\n ");
        sb.append("         A.INSERT_DATE,   		\n ");
        sb.append("         A.INSERT_ID,     		\n ");
        sb.append("         A.MODIFY_DATE,   		\n ");
        sb.append("         A.MODIFY_ID      		\n ");
        sb.append("    FROM TPLANCLASS  A,          \n ");
        sb.append("    		TCATEGORY 	B           \n ");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE(+)     \n ");
        sb.append("		AND A.PLANCLASS_CODE LIKE ? || '%'		\n ");
        sb.append("		AND A.USE_YN LIKE ? || '%'      		\n ");
        sb.append("   ORDER BY PLANCLASS_CODE  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tplanclass 에 등록 )
    * </PRE>
    * @param   Tplanclass
    * @return  String
    */
    public String makeSqlInsert(Tplanclass tplanclass) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TPLANCLASS (    \n ");
        sb.append("              PLANCLASS_CODE    ,   \n ");
        sb.append("              PLANCLASS_NAME    ,   \n ");
        sb.append("              CATEGORY_CODE   ,   \n ");
        sb.append("              USE_YN    ,     \n ");
        sb.append("              IMAGE_URL       ,   \n ");
        sb.append("              TARGET_URL    ,   \n ");
        sb.append("              TEMPLATE_CODE    ,   \n ");
        sb.append("              CONTENT    ,   \n ");
        sb.append("              INSERT_DATE  ,   \n ");
        sb.append("              INSERT_ID    ,   \n ");
        sb.append("              MODIFY_DATE  ,   \n ");
        sb.append("              MODIFY_ID    )   \n ");
        sb.append("       VALUES (                \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,         \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,         \n ");
        sb.append("              ? )              \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tplanclass 변경 )
    * </PRE>
    * @param   Tplanclass
    * @return  String
    */
    public String makeSqlUpdate(Tplanclass tplanclass) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TPLANCLASS           \n ");
        sb.append("     SET PLANCLASS_NAME      = ?,  \n ");
        sb.append("         CATEGORY_CODE       = ?,  \n ");
        sb.append("         USE_YN          	= ?,  \n ");
        sb.append("         IMAGE_URL           = ?,  \n ");
        sb.append("         TARGET_URL        	= ?,  \n ");
        sb.append("         TEMPLATE_CODE       = ?,  \n ");
        sb.append("         CONTENT        		= ?,  \n ");
        sb.append("         MODIFY_DATE      	= SYSDATE,  \n ");
        sb.append("         MODIFY_ID        	= ?  \n ");
        sb.append("   WHERE PLANCLASS_CODE     = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( tplan 전시중인 기획전 체크 )
     * </PRE>
     * @param   tplanclass
     * @return  String
     */
     public String makeSqlDupCheck(Tplanclass tplanclass) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT COUNT(A.PLAN_CODE) 				\n ");
		 sb.append("  FROM TPLAN A 						\n ");
		 sb.append(" WHERE A.DISPLAY_YN ='1'  \n ");
		 sb.append("   AND ( ( ( A.DISPLAY_START_DATE BETWEEN SYSDATE AND SYSDATE OR A.DISPLAY_END_DATE BETWEEN SYSDATE AND SYSDATE )   \n ");
		 sb.append("   OR SYSDATE BETWEEN A.DISPLAY_START_DATE AND A.DISPLAY_END_DATE ) OR A.DISPLAY_END_DATE > SYSDATE )   \n ");
		 sb.append("   AND A.PLANCLASS_CODE = ?  \n ");

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
            pstmt.setString(index++,retrieve.getString("planclass_code"));
            pstmt.setString(index++,retrieve.getString("use_yn"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tplanclass
    * </PRE>
    * @param   Connection
    * @param   Tplanclass
    * @return  int
    */
    public int insert(Connection con, Tplanclass tplanclass) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tplanclass));
            int index = 1;
            pstmt.setString(index++,tplanclass.getPlanclass_code());
            pstmt.setString(index++,tplanclass.getPlanclass_name());
            pstmt.setString(index++,tplanclass.getCategory_code());
            pstmt.setString(index++,tplanclass.getUse_yn());
            pstmt.setString(index++,tplanclass.getImage_url());
            pstmt.setString(index++,tplanclass.getTarget_url());
            pstmt.setString(index++,tplanclass.getTemplate_code());
            pstmt.setString(index++,tplanclass.getContent());
            pstmt.setString(index++,tplanclass.getInsert_id());
            pstmt.setString(index++,tplanclass.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplanclass.getPlanclass_code()    ); logString.append( "/" );
            logString.append( tplanclass.getPlanclass_name()    ); logString.append( "/" );
            logString.append( tplanclass.getCategory_code()     ); logString.append( "/" );
            logString.append( tplanclass.getUse_yn()         	); logString.append( "/" );
            logString.append( tplanclass.getImage_url()         ); logString.append( "/" );
            logString.append( tplanclass.getTarget_url()        ); logString.append( "/" );
            logString.append( tplanclass.getTemplate_code()     ); logString.append( "/" );
            logString.append( tplanclass.getContent()         	); logString.append( "/" );
            logString.append( tplanclass.getInsert_id()         ); logString.append( "/" );
            logString.append( tplanclass.getModify_id()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanclassInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanclassInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tplanclass
    * </PRE>
    * @param   Connection
    * @param   Tplanclass
    * @return  int
    */
    public int update(Connection con, Tplanclass tplanclass) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tplanclass));
            int index = 1;
            pstmt.setString(index++,tplanclass.getPlanclass_name());
            pstmt.setString(index++,tplanclass.getCategory_code());
            pstmt.setString(index++,tplanclass.getUse_yn());
            pstmt.setString(index++,tplanclass.getImage_url());
            pstmt.setString(index++,tplanclass.getTarget_url());
            pstmt.setString(index++,tplanclass.getTemplate_code());
            pstmt.setString(index++,tplanclass.getContent());
            pstmt.setString(index++,tplanclass.getModify_id());
            pstmt.setString(index++,tplanclass.getPlanclass_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tplanclass.getPlanclass_name()    ); logString.append( "/" );
            logString.append( tplanclass.getCategory_code()     ); logString.append( "/" );
            logString.append( tplanclass.getUse_yn()         	); logString.append( "/" );
            logString.append( tplanclass.getImage_url()         ); logString.append( "/" );
            logString.append( tplanclass.getTarget_url()        ); logString.append( "/" );
            logString.append( tplanclass.getTemplate_code()     ); logString.append( "/" );
            logString.append( tplanclass.getContent()         	); logString.append( "/" );
            logString.append( tplanclass.getModify_id()         ); logString.append( "/" );
            logString.append( tplanclass.getPlanclass_code()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[PlanclassInputSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[PlanclassInputSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Check Dup -------------------------------------------------
    /**
    * <PRE>
    * Desc : Check Duplication of Tplanclass
    * </PRE>
    * @param   Connection
    * @param   Tplanclass
    * @return  int
    */
    public int checkDup(Connection con, Tplanclass tplanclass) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDupCheck(tplanclass));
            pstmt.setString(1, tplanclass.getPlanclass_code());

            rset  = pstmt.executeQuery();
            executedRtn = DBUtils.executeQueryI(rset, "");

        }catch(SQLException se){
            logSave.error("[CodeInputSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CodeInputSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }
}