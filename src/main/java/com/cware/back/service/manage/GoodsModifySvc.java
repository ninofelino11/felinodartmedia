
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
import com.cware.back.entity.table.Tgoods;

/**
 * Register Goodsstep Service class
 *
 * @version 1.0, 2007/02/10
 */
public class GoodsModifySvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsModifySvc() {}

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

        sb.append("   SELECT A.GOODS_CODE,                      \n");
        sb.append("          A.GOODS_NAME,                      \n");
        sb.append("          A.MASTER_CODE,                     \n");
        sb.append("          D.GOODS_NAME AS MASTER_NAME,       \n");
        sb.append("          A.LGROUP,                          \n");
        sb.append("          A.MGROUP,                          \n");
        sb.append("          A.SGROUP,                          \n");
        sb.append("          A.DGROUP,                          \n");
        sb.append("          A.BRAND_CODE,                      \n");
        sb.append("          B.BRAND_NAME,                      \n");
        sb.append("          A.ORIGIN_CODE,                     \n");
        sb.append("          C.CODE_NAME AS ORIGIN_NAME         \n");
        sb.append("     FROM TGOODS A,                          \n");
        sb.append("          TBRAND B,                          \n");
        sb.append("          TCODE  C,                          \n");
        sb.append("          TGOODS D                           \n");
        sb.append("    WHERE A.MASTER_CODE = D.GOODS_CODE       \n");
        sb.append("      AND A.BRAND_CODE = B.BRAND_CODE        \n");
        sb.append("      AND C.CODE_LGROUP = 'B023'             \n");
        sb.append("      AND A.ORIGIN_CODE = C.CODE_MGROUP      \n");
        sb.append("      AND A.LGROUP LIKE ? || '%'             \n");
        sb.append("      AND A.MGROUP LIKE ? || '%'             \n");
        sb.append("      AND A.SGROUP LIKE ? || '%'             \n");
        sb.append("      AND A.DGROUP LIKE ? || '%'             \n");
        sb.append("      AND A.MASTER_CODE LIKE ? || '%'        \n");
        sb.append("      AND A.GOODS_CODE LIKE ? || '%'         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());


        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Update Tgoods )
     * </PRE>
     * @param   Tgoods
     * @return  String
     */
     private String makeSqlUpdate(Tgoods tgoods) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" UPDATE TGOODS SET                                                   \n ");
         sb.append("        LGROUP              = ?,                                     \n ");
         sb.append("        MGROUP              = ?,                                     \n ");
         sb.append("        SGROUP              = ?,                                     \n ");
         sb.append("        DGROUP              = ?,                                     \n ");
         sb.append("        BRAND_CODE          = ?,                                     \n ");
         sb.append("        ORIGIN_CODE         = ?,                                     \n ");
         sb.append("        MASTER_CODE	        = ?,             			            \n ");
         sb.append("        MODIFY_DATE         = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'),    \n ");
         sb.append("        MODIFY_ID           = ?                                      \n ");
         sb.append("  WHERE GOODS_CODE          = ?                                      \n ");

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
            pstmt.setString(index++,retrieve.getString("Lgroup"));
            pstmt.setString(index++,retrieve.getString("Mgroup"));
            pstmt.setString(index++,retrieve.getString("Sgroup"));
            pstmt.setString(index++,retrieve.getString("Dgroup"));
            pstmt.setString(index++,retrieve.getString("Master_code"));
            pstmt.setString(index++,retrieve.getString("goods_code"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("Lgroup")        	); logString.append( "/" );
            logString.append( retrieve.getString("Mgroup")        	); logString.append( "/" );
            logString.append( retrieve.getString("Sgroup")        	); logString.append( "/" );
            logString.append( retrieve.getString("Dgroup")      	); logString.append( "/" );
            logString.append( retrieve.getString("Master_code")     ); logString.append( "/" );
            logString.append( retrieve.getString("goods_code")      ); logString.append( "/" );
            log.info(logString.toString());

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

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
    * Desc : Update Tgoods
    * </PRE>
    * @param   Connection
    * @param   tgoods
    * @return  int
    */
    public int update(Connection con, Tgoods tgoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoods));
            int index = 1;
            pstmt.setString(index++,tgoods.getLgroup()             );
            pstmt.setString(index++,tgoods.getMgroup()             );
            pstmt.setString(index++,tgoods.getSgroup()             );
            pstmt.setString(index++,tgoods.getDgroup()             );
            pstmt.setString(index++,tgoods.getBrand_code()         );
            pstmt.setString(index++,tgoods.getOrigin_code()        );
            pstmt.setString(index++,tgoods.getMaster_code()        );
            pstmt.setString(index++,tgoods.getModify_date()        );
            pstmt.setString(index++,tgoods.getModify_id()          );
            pstmt.setString(index++,tgoods.getGoods_code()         );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoods.getLgroup()             ); logString.append( "/" );
            logString.append( tgoods.getMgroup()             ); logString.append( "/" );
            logString.append( tgoods.getSgroup()             ); logString.append( "/" );
            logString.append( tgoods.getDgroup()             ); logString.append( "/" );
            logString.append( tgoods.getBrand_code()         ); logString.append( "/" );
            logString.append( tgoods.getOrigin_code()        ); logString.append( "/" );
            logString.append( tgoods.getMaster_code()        ); logString.append( "/" );
            logString.append( tgoods.getModify_date()        ); logString.append( "/" );
            logString.append( tgoods.getModify_id()          ); logString.append( "/" );
            logString.append( tgoods.getGoods_code()         ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[Tgoods.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[Tgoods.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
}
