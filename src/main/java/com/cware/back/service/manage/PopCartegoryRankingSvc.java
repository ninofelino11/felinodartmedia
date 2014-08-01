
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
import com.cware.back.entity.table.Tcategorygoods;


/**
 * CartegoryRanking Service class
 *
 * @version 1.0, 2010/11/25
 * @author  commerceware.co.kr
 */
public class PopCartegoryRankingSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopCartegoryRankingSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql(RetrieveModel retrieve) throws Exception {
    	
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT GD.GOODS_CODE, ");
        sb.append("       GD.GOODS_NAME, ");
        sb.append("       CG.DISPLAY_PRIORITY, ");
        sb.append("       SUBSTR(GD.GOODS_CODE, 6, 7) AS SUB_COODE_CODE, ");
        sb.append("       CF.VAL                      AS IMG_SERVER_URL, ");
        sb.append("       GP.SALE_PRICE, ");
        sb.append("       CG.CATEGORY_CODE ");
        sb.append("FROM   TGOODS GD, ");
        sb.append("       (SELECT VAL ");
        sb.append("        FROM   TCONFIG ");
        sb.append("        WHERE  ITEM = 'FLEX_IMG_SERVER_URL') CF, ");
        sb.append("       TCATEGORYGOODS CG, ");
        sb.append("       TGOODSPRICE GP ");
        sb.append("WHERE  GD.GOODS_CODE = CG.GOODS_CODE ");
        sb.append("       AND CG.GOODS_CODE = GP.GOODS_CODE ");
        sb.append("       AND GP.APPLY_DATE = (SELECT MAX(D.APPLY_DATE) ");
        sb.append("                            FROM   TGOODSPRICE D ");
        sb.append("                            WHERE  D.APPLY_DATE <= SYSDATE ");
        sb.append("                                   AND D.GOODS_CODE = GD.GOODS_CODE) ");
        sb.append("       AND CG.CATEGORY_CODE = ? ");
        sb.append("       ORDER BY CG.DISPLAY_PRIORITY, GD.GOODS_CODE");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("category_code : " + retrieve.getString("category_code") );
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
            pstmt.setString(1,  retrieve.getString("category_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[PopCategoryRankingSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PopCategoryRankingSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */
    private final String updateSql =  " UPDATE TCATEGORYGOODS SET \n "
        +"        DISPLAY_PRIORITY = ?, \n "
        +"        MODIFY_ID = ?, \n "
        +"        MODIFY_DATE = SYSDATE \n "
        +"  WHERE CATEGORY_CODE = ? \n "
        +"    AND GOODS_CODE = ? \n ";

	private int updateSqlLog =  0;
	
	public String makeSqlUpdate(Tcategorygoods tcategorygoods) throws StoreException{
		//= log SQL -------------------------------------------------
		if (updateSqlLog == 0) {
			if (logSave.isDebugEnabled()) {
				logSave.debug(updateSql);
			}
			updateSqlLog = 1;
		}
		return updateSql;
	}


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tcategorygoods
    * </PRE>
    * @param   Connection
    * @param   Tcategorygoods
    * @return  int
    */
    public int update(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tcategorygoods));
            int index = 1;
            pstmt.setString(index++,tcategorygoods.getDisplay_priority());
            pstmt.setString(index++,tcategorygoods.getModify_id());
            pstmt.setString(index++,tcategorygoods.getCategory_code());
            pstmt.setString(index++,tcategorygoods.getGoods_code());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategorygoods.getDisplay_priority()  ); logString.append( "/" );
            logString.append( tcategorygoods.getCategory_code() ); logString.append( "/" );
            logString.append( tcategorygoods.getGoods_code()     ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_id()    ); logString.append( "/" );
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

