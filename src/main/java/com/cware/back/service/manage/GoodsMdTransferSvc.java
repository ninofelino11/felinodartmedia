package com.cware.back.service.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;

public class GoodsMdTransferSvc {

	private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

	public int update(Connection con, RetrieveModel retrieve) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate());
            int index = 1;

            pstmt.setString(index++,retrieve.getString("MD_CODE_AFTER"));
            pstmt.setString(index++,retrieve.getString("MD_CODE_BEFORE"));
            pstmt.setString(index++,retrieve.getString("GOODS_CODE"));
            pstmt.setString(index++,retrieve.getString("LGROUP"));
            pstmt.setString(index++,retrieve.getString("MGROUP"));
            pstmt.setString(index++,retrieve.getString("SGROUP"));
            pstmt.setString(index++,retrieve.getString("DGROUP"));

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("MD_CODE_AFTER") ); logString.append( "/" );
            logString.append( retrieve.getString("MD_CODE_BEFORE") ); logString.append( "/" );
            logString.append( retrieve.getString("GOODS_CODE") ); logString.append( "/" );
            logString.append( retrieve.getString("LGROUP") ); logString.append( "/" );
            logString.append( retrieve.getString("MGROUP") ); logString.append( "/" );
            logString.append( retrieve.getString("SGROUP") ); logString.append( "/" );
            logString.append( retrieve.getString("DGROUP") ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsMdTransferSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsMdTransferSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
		return executedRtn;
	}

	/**
	    * <PRE>
	    * Desc : Make SQL ( Update TGOODS )
	    * </PRE>
	    * @param
	    * @return  String
	    */
		 private String makeSqlUpdate() throws StoreException{

	    	StringBuilder updateSql = new StringBuilder();

	    	updateSql.append(" UPDATE TGOODS                             \n ");
	    	updateSql.append(" SET MD_CODE = ?                           \n ");
	    	updateSql.append(" WHERE MD_CODE = ?                         \n ");
	    	updateSql.append(" AND   GOODS_CODE LIKE ?||'%'              \n ");
	    	updateSql.append(" AND   LMSD_CODE  LIKE ?||?||?||? ||'%'    \n ");

            logSave.debug(updateSql);

	        return updateSql.toString();
	    }
}
