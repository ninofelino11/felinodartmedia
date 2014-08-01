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
import com.cware.back.entity.table.Tdirectcost;

public class DirectCostManageSvc {
//	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
  //  private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);


	public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;

		try{
			pstmt = con.prepareStatement(makeSql(retrieve));

			int index = 1;
			pstmt.setString(index++, retrieve.getString("standard_year_month"));

			rset = pstmt.executeQuery();

			retrieve.put("RESULT",makeSheet(rset));

		}catch(SQLException se){
			log.error("[DirectCostManageSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			log.error("[DirectCostManageSvc.retrieve() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, rset);
		}
		return retrieve;
	}



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

	//= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update tcode
    * </PRE>
    * @param   Connection
    * @param   tcode
    * @return  int
    */
	public int update(Connection con, Tdirectcost tdirectcost) throws StoreException{
        PreparedStatement pstmt       = null;
        int 			  executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tdirectcost));
            int index = 1;
            pstmt.setString(index++, tdirectcost.getCost_flag());
            pstmt.setDouble(index++, tdirectcost.getCost_value());
            pstmt.setString(index++, tdirectcost.getRemark());
            pstmt.setString(index++, tdirectcost.getModify_id());
            pstmt.setString(index++, tdirectcost.getYymm());
            pstmt.setString(index++, tdirectcost.getCost_code());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdirectcost.getCost_flag()  ); logString.append( "/" );
            logString.append( tdirectcost.getCost_value()  	); logString.append( "/" );
            logString.append( tdirectcost.getRemark() ); logString.append( "/" );
            logString.append( tdirectcost.getModify_id() ); logString.append( "/" );
            logString.append( tdirectcost.getYymm()); logString.append( "/" );
            logString.append( tdirectcost.getCost_code()); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DirectCostmanageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DirectCostmanageSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}


	//= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCODE
    * </PRE>
    * @param   Connection
    * @param   Tcode
    * @return  int
    */
	public int insert(Connection con, Tdirectcost tdirectcost) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tdirectcost));
            int index = 1;

            pstmt.setString(index++, tdirectcost.getYymm());
            pstmt.setString(index++, tdirectcost.getCost_code());
            pstmt.setString(index++, tdirectcost.getCost_flag());
            pstmt.setDouble(index++, tdirectcost.getCost_value());
            pstmt.setString(index++, tdirectcost.getRemark());
            pstmt.setString(index++, tdirectcost.getInsert_id());
            pstmt.setString(index++, tdirectcost.getModify_id());


            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tdirectcost.getYymm() 				); logString.append( "/" );
            logString.append( tdirectcost.getCost_code()			); logString.append( "/" );
            logString.append( tdirectcost.getCost_flag()     		); logString.append( "/" );
            logString.append( tdirectcost.getCost_value()     		); logString.append( "/" );
            logString.append( tdirectcost.getRemark()        		); logString.append( "/" );
            logString.append( tdirectcost.getInsert_id()     		); logString.append( "/" );
            logString.append( tdirectcost.getModify_id()     		); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DirectCostmanageSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DirectCostmanageSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }




	//====================================================sql문====================================================
	public String makeSql(RetrieveModel retrieve) throws Exception{
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT YYMM,												\n");
		sb.append("        COST_CODE,											\n");
		sb.append("        COST_FLAG,											\n");
		sb.append("        COST_VALUE,											\n");
		sb.append("        REMARK 												\n");
		sb.append(" FROM   TDIRECTCOST											\n");
		sb.append(" WHERE  YYMM = ?												\n");

		if(log.isDebugEnabled()){
			log.debug(sb.toString());
			log.debug("standard_year_month :"+retrieve.getString("standard_year_month"));
		}
		return sb.toString();
	}
	public String makeSqlInsert(Tdirectcost tdirectcost){
		StringBuffer sb = new StringBuffer();

		sb.append("INSERT INTO TDIRECTCOST								\n");
		sb.append("			  (YYMM,									\n");
		sb.append("			   COST_CODE,								\n");
		sb.append("            COST_FLAG,								\n");
		sb.append("            COST_VALUE,								\n");
		sb.append("            REMARK,									\n");
		sb.append("            INSERT_ID,								\n");
		sb.append("            INSERT_DATE,								\n");
		sb.append("            MODIFY_ID,								\n");
		sb.append("            MODIFY_DATE)								\n");
		sb.append("VALUES     (?,?,?,?,?,?,SYSDATE,?,SYSDATE)				\n");

		//= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
		return sb.toString();
	}

	public String makeSqlUpdate(Tdirectcost tdirectcost){
		StringBuffer sb = new StringBuffer();

		sb.append("UPDATE   TDIRECTCOST 						\n");
		sb.append("SET		COST_FLAG = ?,						\n");
		sb.append("			COST_VALUE = ?,						\n");
		sb.append("			REMARK = ?,							\n");
		sb.append("			MODIFY_ID = ?,						\n");
		sb.append("			MODIFY_DATE = SYSDATE				\n");
		sb.append("WHERE 	YYMM = ?							\n");
		sb.append("AND		COST_CODE = ?						\n");

		//= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
		return sb.toString();
	}

	public int check(Connection con, Tdirectcost tdirectcost) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;
		int check = 0;
		try{
			pstmt = con.prepareStatement(makeSqlCheck(tdirectcost));

			int index = 1;
			pstmt.setString(index++, tdirectcost.getYymm());
			pstmt.setString(index++, tdirectcost.getCost_code());

			rset = pstmt.executeQuery();

			if (rset.next()) {
				check = rset.getInt(1);
			}

		}catch(SQLException se){
			log.error("[DirectCostManageSvc.check() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			log.error("[DirectCostManageSvc.check() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, rset);
		}
		return check;
	}

	public String makeSqlCheck(Tdirectcost tdirectcost){
		String sql = "SELECT COUNT(*) FROM TDIRECTCOST WHERE YYMM = ? AND COST_CODE = ?  ";

		//= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            logSave.debug(sql);
            logSave.debug("yymm:  " + tdirectcost.getYymm());
            logSave.debug("cost_code:  " + tdirectcost.getCost_code());
        }
		return sql;
	}

}