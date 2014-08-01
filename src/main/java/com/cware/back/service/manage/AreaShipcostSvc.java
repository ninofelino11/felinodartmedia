
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
import com.cware.back.entity.table.Tareashipcost;
import com.cware.back.entity.table.Tmd;
import com.cware.back.entity.table.Tmdlink;

/**
 * Register MD Service class
 *
 * @version 1.0, 2006/07/13
 */
public class AreaShipcostSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public AreaShipcostSvc() {}

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

        sb.append(" SELECT DISTINCT AC.AREA_GB     		\n");
        sb.append("   FROM TAREASHIPCOST AC,    		\n");
        sb.append("   	   TAREA_ZONE CD    			\n");
        sb.append("  WHERE AC.AREA_GB = CD.AREA_GB		\n");
        //sb.append("    AND CD.USE_YN = '1'    			\n");
        sb.append("    AND AC.AREA_GB LIKE ? || '%'    	\n");
        sb.append("  ORDER BY AC.AREA_GB     			\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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

        sb.append(" SELECT TO_CHAR(APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,   \n");
        sb.append("        WEIGHT,    \n");
        sb.append("        SHIP_FEE,    \n");
        sb.append("        SLIP_FLAG,    \n");
        sb.append("        AREA_GB    \n");
        sb.append("   FROM TAREASHIPCOST   \n");
        sb.append("  WHERE AREA_GB    = ?   \n");
        sb.append("  ORDER BY APPLY_DATE DESC,   \n");
        sb.append("           WEIGHT   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Print
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlPrint( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.MD_CODE,     \n");
        sb.append("        C.MD_NAME,   \n");
        sb.append("        A.USER_ID,     \n");
        sb.append("        B.USER_NAME,     \n");
        sb.append("        TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,  \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,   \n");
        sb.append("        A.MODIFY_ID,   \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n");
        sb.append("        A.RATE         \n");
        sb.append("   FROM TMDLINK A,     \n");
        sb.append("        TUSER   B,     \n");
        sb.append("        TMD     C      \n");
        sb.append("  WHERE A.USER_ID = B.USER_ID \n");
        sb.append("    AND A.MD_CODE = C.MD_CODE \n");
        sb.append("    AND A.MD_CODE LIKE ? || '%'  \n");
        sb.append("  ORDER BY A.MD_CODE, B.USER_ID \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TMD )
    * </PRE>
    * @param   Tmd
    * @return  String
    */
    private String makeSqlInsert(Tareashipcost tareashipcost) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TAREASHIPCOST (        \n");
        sb.append("          APPLY_DATE,                \n");
        sb.append("          AREA_GB,                   \n");
        sb.append("          WEIGHT,                    \n");
        sb.append("          SLIP_FLAG,                 \n");
        sb.append("          SHIP_FEE,                  \n");
        sb.append("          COD_YN,                    \n");
        sb.append("          INSERT_DATE,               \n");
        sb.append("          INSERT_ID )                \n");
        sb.append("  VALUES (                           \n");
        sb.append("          TO_DATE(?, 'YYYY/MM/DD'),  \n");
        sb.append("          ?,                         \n");
        sb.append("          ?,                         \n");
        sb.append("          ?,                         \n");
        sb.append("          ?,                         \n");
        sb.append("          ?,                         \n");
        sb.append("          SYSDATE,                   \n");
        sb.append("          ? )                        \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TMD )
    * </PRE>
    * @param   Tmd
    * @return  String
    */
    private String makeSqlUpdate(Tmd tmd) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TMD SET \n ");
        sb.append("         MD_NAME = ?, \n ");
        sb.append("         REMARK  = ?, \n ");
        sb.append("         MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID   = ?  \n ");
        sb.append("   WHERE MD_CODE     = ?  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TMDLINK )
    * </PRE>
    * @param   Tmdlink
    * @return  String
    */
    private final String insertSqlTmdlink =  " INSERT INTO TMDLINK ( \n "
                                             +"        MD_CODE, \n "
                                             +"        USER_ID, \n "
                                             +"        RATE,    \n "
                                             +"        START_DATE,  \n "
                                             +"        END_DATE,    \n "
                                             +"        INSERT_DATE, \n "
                                             +"        INSERT_ID,   \n "
                                             +"        MODIFY_DATE, \n "
                                             +"        MODIFY_ID)   \n "
                                             +"VALUES (   \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        ?, \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD'), \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD'), \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"        ?, \n "
                                             +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                             +"        ?) \n ";

    private int insertSqlLogTmdlink =  0;

    private String makeSqlInsert(Tmdlink tmdlink) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTmdlink == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTmdlink);
            }
            insertSqlLogTmdlink = 1;
        }
        return insertSqlTmdlink;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TMDLINK )
    * </PRE>
    * @param   Tmdlink
    * @return  String
    */
    private final String updateSqlTmdlink = " UPDATE TMDLINK SET \n "
                                          + "        RATE        = ?, \n "
                                          + "        START_DATE  = TO_DATE(?,'YYYY/MM/DD')?, \n "
                                          + "        END_DATE    = TO_DATE(?,'YYYY/MM/DD'), \n "
                                          + "        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                          + "        MODIFY_ID   = ? \n "
                                          + "  WHERE MD_CODE     = ? \n "
                                          + "    AND USER_ID     = ? \n ";

    private int updateSqlLogTmdlink =  0;

    private String makeSqlUpdate(Tmdlink tmdlink) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTmdlink == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTmdlink);
            }
            updateSqlLogTmdlink = 1;
        }
        return updateSqlTmdlink;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tmdlink 사용자ID  중복 체크 )
     * </PRE>
     * @param   Tcode
     * @return  String
     */
     public String makeSqlDupCheck(Tmdlink tmdlink) throws StoreException{

         StringBuffer sb = new StringBuffer();


         sb.append("SELECT COUNT(USER_ID) 				\n ");
		 sb.append("  FROM TMDLINK 						\n ");
		 sb.append(" WHERE MD_CODE = ? AND USER_ID = ?  \n ");


         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }

         return sb.toString();
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
            pstmt.setString(index++,retrieve.getString("area_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[AreaShipcostSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AreaShipcostSvc.retrieve() Exception : ERR-"+e.getMessage());
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
            pstmt.setString(index++,retrieve.getString("area_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[AreaShipcostSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AreaShipcostSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ; Print
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrievePrint(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPrint(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("md_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[AreaShipcostSvc.retrievePrint() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[AreaShipcostSvc.retrievePrint() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tareashipcost
    * </PRE>
    * @param   Connection
    * @param   Tareashipcost
    * @return  int
    */
    public int insert(Connection con, Tareashipcost tareashipcost) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tareashipcost));
            int index = 1;

            pstmt.setString(index++, tareashipcost.getApply_date());
            pstmt.setString(index++, tareashipcost.getArea_gb());
            pstmt.setString(index++, tareashipcost.getWeight());
            pstmt.setString(index++, tareashipcost.getSlip_flag());
            pstmt.setString(index++, tareashipcost.getShip_fee());
            pstmt.setString(index++, tareashipcost.getCod_yn());
            pstmt.setString(index++, tareashipcost.getCwareInfo());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tareashipcost.getApply_date() ); logString.append( "/" );
            logString.append( tareashipcost.getArea_gb()    ); logString.append( "/" );
            logString.append( tareashipcost.getWeight()     ); logString.append( "/" );
            logString.append( tareashipcost.getSlip_flag()  ); logString.append( "/" );
            logString.append( tareashipcost.getShip_fee()   ); logString.append( "/" );
            logString.append( tareashipcost.getCod_yn()     ); logString.append( "/" );
            logString.append( tareashipcost.getCwareInfo()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[AreaShipcostSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[AreaShipcostSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


}
