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

public class LmsdChangeRateListSvc {
	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
	
	//= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {

        	pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("yymm"));
            pstmt.setString(index++,retrieve.getString("lGroup"));
            pstmt.setString(index++,retrieve.getString("mGroup"));
            pstmt.setString(index++,retrieve.getString("sGroup"));
            pstmt.setString(index++,retrieve.getString("dGroup"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[LmsdChangeRateListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[LmsdChangeRateListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    public String makeSql(RetrieveModel retrieve) throws StoreException{
    		StringBuffer sb = new StringBuffer();

			sb.append(" SELECT CR.LMSD_CODE,                           \n");
			sb.append("    GK.LGROUP_NAME||'|'||                       \n");
			sb.append("    GK.MGROUP_NAME||'|'||                       \n");
			sb.append("    GK.SGROUP_NAME||'|'||                       \n");
			sb.append("    GK.DGROUP_NAME LMSD_NAME,                   \n");
			sb.append("    CR.CHANGE_RATE,                             \n");
			sb.append("    ' ' REMARK                                  \n");
			sb.append(" FROM TGOODSKINDS GK ,TSMLMSD_CHANGE_RATE CR    \n");
			sb.append(" WHERE CR.LMSD_CODE = GK.LMSD_CODE              \n");
			sb.append(" AND CR.YYMM = ?                                \n");
			sb.append(" AND CR.LMSD_CODE LIKE ?||?||?||?||'%'          \n");
		
			logPrint(sb, retrieve);

			return sb.toString();
    }

    private void logPrint(StringBuffer query, RetrieveModel retrieve){
    	  //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + query.toString());
            log.debug("yymm  : " + retrieve.getString("yymm"));
            log.debug("lGroup    : " + retrieve.getString("lGroup"));
            log.debug("mGroup : " + retrieve.getString("mGroup"));
            log.debug("sGroup : " + retrieve.getString("sGroup"));
            log.debug("dGroup  : " + retrieve.getString("dGroup"));
        }
    }
    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
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

        //= log Column Name -------------------------------------------------
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

}
