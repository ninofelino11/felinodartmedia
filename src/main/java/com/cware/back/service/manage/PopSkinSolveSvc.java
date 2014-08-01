
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
import com.cware.back.entity.table.Tskintestrecipe;


/**
 * PopSkinSolve Service class
 *
 * @version 1.0, 2007/02/26
 */
public class PopSkinSolveSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);    

    public PopSkinSolveSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Enterprise Skin Solve
    * </PRE>
    * @param   poolName                           : Database pool name
    * @param   skin_type                          : 피부타입
    */

    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SKIN_TYPE,           \n");
        sb.append("        A.SKIN_FEATURE,        \n");
        sb.append("        A.SKIN_TROUBLE,        \n");
        sb.append("        A.SKIN_CLEANSING,      \n");
        sb.append("        A.SKIN_CARE,           \n");
        sb.append("        A.SEASON_SERVICE,      \n");
        sb.append("        A.SEASON_SERVICE_MAN,  \n");
        sb.append("        A.INSERT_DATE,         \n");
        sb.append("        A.INSERT_ID,           \n");
        sb.append("        A.SKIN_FEATURE_MAN,    \n");
        sb.append("        A.SKIN_TROUBLE_MAN,    \n");
        sb.append("        A.SKIN_SHAVING_MAN,    \n");
        sb.append("        A.SKIN_CARE_MAN        \n");
        sb.append("   FROM TSKINTESTRECIPE A      \n");
        sb.append("  WHERE A.SKIN_TYPE = ?        \n");        

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
        	log.debug("skin_type  : " + retrieve.getString("skin_type"));
            log.debug(sb.toString());
        }
        return sb.toString();
    }
    
    /**
     * <PRE>
     * Desc : Make SQL ( Tskintestrecipe 변경 )
     * </PRE>
     * @param   Tentp
     * @return  String
     */
     public String makeSqlUpdate(Tskintestrecipe skintestrecipe) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("  UPDATE TSKINTESTRECIPE            \n ");
         sb.append("     SET SKIN_FEATURE         = ?,  \n ");
         sb.append("         SKIN_TROUBLE         = ?,  \n ");
         sb.append("         SKIN_CLEANSING       = ?,  \n ");
         sb.append("         SKIN_CARE            = ?,  \n ");
         sb.append("         SEASON_SERVICE       = ?,  \n ");
         sb.append("         SEASON_SERVICE_MAN   = ?,  \n ");
         sb.append("         SKIN_FEATURE_MAN     = ?,  \n ");
         sb.append("         SKIN_TROUBLE_MAN     = ?,  \n ");
         sb.append("         SKIN_SHAVING_MAN     = ?,  \n ");
         sb.append("         SKIN_CARE_MAN        = ?,  \n ");
         sb.append("         INSERT_DATE          = SYSDATE,  \n ");
         sb.append("         INSERT_ID            = ?   \n ");
         sb.append("   WHERE SKIN_TYPE            = ?   \n ");
             
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
            pstmt.setString(index++,retrieve.getString("skin_type"));

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
    * Desc : Update Tskintestrecipe
    * </PRE>
    * @param   Connection
    * @param   Tentp
    * @return  int
    */
    public int update(Connection con, Tskintestrecipe skintestrecipe) throws StoreException{
    	PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(skintestrecipe));
            int index = 1;
            
            pstmt.setString(index++,skintestrecipe.getSkin_feature()            );
            pstmt.setString(index++,skintestrecipe.getSkin_trouble()            );
            pstmt.setString(index++,skintestrecipe.getSkin_cleansing()            );
            pstmt.setString(index++,skintestrecipe.getSkin_care()            );
            pstmt.setString(index++,skintestrecipe.getSeason_service()            );
            pstmt.setString(index++,skintestrecipe.getSeason_service_man()            );
            pstmt.setString(index++,skintestrecipe.getSkin_feature_man()            );
            pstmt.setString(index++,skintestrecipe.getSkin_trouble_man()            );
            pstmt.setString(index++,skintestrecipe.getSkin_shaving_man()            );
            pstmt.setString(index++,skintestrecipe.getSkin_care_man()            );
            pstmt.setString(index++,skintestrecipe.getInsert_id()            );
            pstmt.setString(index++,skintestrecipe.getSkin_type()            );
            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( skintestrecipe.getSkin_feature()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_trouble()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_cleansing()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_care()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSeason_service()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSeason_service_man()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_feature_man()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_trouble_man()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_shaving_man()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_care_man()         ); logString.append( "/" );
            logString.append( skintestrecipe.getInsert_id()         ); logString.append( "/" );
            logString.append( skintestrecipe.getSkin_type()         ); logString.append( "/" );

            logSave.info(logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(pstmt, null, null);
        }
        return executedRtn;
    }    
}
