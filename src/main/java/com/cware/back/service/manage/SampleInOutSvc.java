package com.cware.back.service.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tsampleinout;


/**
 * 생플 입출고 등록 Service class
 *
 * @version 1.0, 2011/01/20
 */
public class SampleInOutSvc {

	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
	private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    //= Retrieve Qty -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @return  RetrieveModel
    */
	public Integer retrieveQty(Connection con, String goodsCode)throws StoreException {
		PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        Integer qty = null;
        try {
            pstmt = con.prepareStatement(makeSqlQty(goodsCode));

            int index = 1;
            pstmt.setString(index++,goodsCode );
            rset = pstmt.executeQuery();

            if(rset.next()){
            	qty = new Integer(rset.getInt("QTY"));
            }
        	log.debug("\n AQTY : " + qty);

        }catch(SQLException se){
            logSave.error("[SampleInOutSvc.retrieveQty() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleInOutSvc.retrieveQty() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return qty;
	}

	public int insertSampleInOut(Connection con, Tsampleinout tsampleInOut) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertTSample(tsampleInOut));
            int index = 1;
            pstmt.setString(index++,tsampleInOut.getIo_flag());
            pstmt.setString(index++,tsampleInOut.getWh_code());
            pstmt.setString(index++,tsampleInOut.getGooods_code());
            pstmt.setString(index++,tsampleInOut.getIo_qty());
            pstmt.setString(index++,tsampleInOut.getReceiver_id());
            pstmt.setString(index++,tsampleInOut.getIo_note());
            pstmt.setString(index++, tsampleInOut.getInsert_id());
            pstmt.setString(index++, tsampleInOut.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsampleInOut.getIo_flag()); logString.append( "/" );
            logString.append( tsampleInOut.getWh_code()); logString.append( "/" );
            logString.append( tsampleInOut.getGooods_code());    logString.append( "/" );
            logString.append( tsampleInOut.getIo_qty());    logString.append( "/" );
            logString.append( tsampleInOut.getReceiver_id()); logString.append( "/" );
            logString.append( tsampleInOut.getIo_note()); logString.append( "/" );
            logString.append( tsampleInOut.getInsert_id()); logString.append( "/" );
            logString.append( tsampleInOut.getInsert_id()); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleInOutSvc.insertSampleInOut() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleInOutSvc.insertSampleInOut() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }

		return executedRtn;
	}

	public int insertStock(Connection con,Tsampleinout tsampleInOut) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertTStoke());
            int index = 1;
            pstmt.setString(index++,tsampleInOut.getGooods_code());
            pstmt.setString(index++,tsampleInOut.getWh_code());
            pstmt.setString(index++,tsampleInOut.getIo_qty());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsampleInOut.getGooods_code()); logString.append( "/" );
            logString.append( tsampleInOut.getWh_code()); logString.append( "/" );
            logString.append( tsampleInOut.getIo_qty());    logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleInOutSvc.insertStock() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleInOutSvc.insertStock() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
		return executedRtn;
	}

	public int updateStrock(Connection con, Tsampleinout tsampleInOut) throws StoreException{
		PreparedStatement pstmt       = null;
		ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTStock(tsampleInOut.getIo_flag()));
            int index = 1;
            pstmt.setString(index++,tsampleInOut.getIo_qty());
            pstmt.setString(index++,tsampleInOut.getGooods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsampleInOut.getIo_qty()); logString.append( "/" );
            logString.append( tsampleInOut.getGooods_code()); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SampleInOutSvc.updateStrock() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SampleInOutSvc.updateStrock() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
		return executedRtn;
	}


/**
    * <PRE>
    * Desc : Make SQL ( Select tstock )
    * </PRE>
    * @param   Tsample_input
    * @return  String
    */
	private String makeSqlQty(String goodsCode) {

		  StringBuffer sb = new StringBuffer();

		  sb.append("		SELECT (AQTY-BQTY) as QTY       \n");
		  sb.append("	    FROM tstock   					\n");
		  sb.append("	       WHERE goodsdt_code = '000'   \n");
		  sb.append("	       AND goods_code = ?   		\n");

		  logPrint(sb, goodsCode);

		  return sb.toString();
	}

	private void logPrint(StringBuffer query, String goodsCode){
  	  //= log SQL -------------------------------------------------
      if (log.isDebugEnabled()) {
          log.debug("\n" + query.toString());
          log.debug("GoodsCode  : " + goodsCode);
      }
	}


/**
    * <PRE>
    * Desc : Make SQL ( Insert TSAMPLE_INOUT )
    * </PRE>
    * @param   Tsample_input
    * @return  String
    */
    private final String insertSqlTSampleInOut = " INSERT INTO TSAMPLE_INOUT ( \n "
    								+"         SEQ_SAMP_NO, \n "
                                    +"         IO_FLAG, \n "
                                    +"         IO_DATE, \n "
                                    +"         WH_CODE, \n "
                                    +"         GOODS_CODE, \n "
                                    +"         IO_QTY, \n "
                                    +"         RECEIVER_ID, \n "
                                    +"         IO_NOTE, \n "
                                    +"         INSERT_ID, \n "
                                    +"         INSERT_DATE, \n "
                                    +"         MODIFY_ID, \n "
                                    +"         MODIFY_DATE) \n"
                                    +" VALUES( \n "
                                    +"         LPAD(SEQ_SAMP_NO.NEXTVAL, 10, '0'), \n "
                                    +"         ?, \n "
                                    +"         TO_DATE(SYSDATE), \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE, \n "
                                    +"         ?, \n "
                                    +"         SYSDATE) \n";
    private int insertSqlTSampleLog =  0;

    public String makeSqlInsertTSample(Tsampleinout tsampleinput) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlTSampleLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTSampleInOut);
            }
            insertSqlTSampleLog = 1;
        }
        return insertSqlTSampleInOut;
    }

/**
    * <PRE>
    * Desc : Make SQL ( Insert TSTOKE )
    * </PRE>
    * @param
    * @return  String
    */
	 private final String insertSqlTStoke = " INSERT INTO TSTOCK ( \n "
	                                 +"         GOODS_CODE, \n "
	                                 +"         GOODSDT_CODE, \n "
	                                 +"         WH_CODE, \n "
	                                 +"         AQTY, \n "
	                                 +"         BQTY, \n "
	                                 +"         BALJU_QTY, \n "
	                                 +"         EOUT_QUEST_AQTY, \n "
	                                 +"         EOUT_QUEST_BQTY, \n "
	                                 +"         DELY_HOPE_QTY) \n"
	                                 +" VALUES( \n "
	                                 +"         ?, \n "
	                                 +"         '000', \n "
	                                 +"         ?, \n "
	                                 +"         ?, \n "
	                                 +"         0, \n "
	                                 +"         0, \n "
	                                 +"         0, \n "
	                                 +"         0, \n "
	                                 +"         0) \n";
	 private int insertSqlTStokeLog =  0;

	 public String makeSqlInsertTStoke() throws StoreException{
	     //= log SQL -------------------------------------------------
	     if (insertSqlTStokeLog == 0) {
	         if (logSave.isDebugEnabled()) {
	             logSave.debug(insertSqlTStoke);
	         }
	         insertSqlTStokeLog = 1;
	     }
	     return insertSqlTStoke;
	 }


	 private int updateSqlLogTstock =  0;

 /**
    * <PRE>
    * Desc : Make SQL ( Update TSTOCK )
    * </PRE>
    * @param
    * @return  String
    */
	 private String makeSqlUpdateTStock(String dealType) throws StoreException{

    	String orderIn = "1";
    	String orderTakeOut = "2";

    	StringBuilder updateSqlTstock = new StringBuilder();
    	updateSqlTstock.append(" UPDATE TSTOCK SET \n ");

    	if(dealType.equals(orderIn)){
    		updateSqlTstock.append("         AQTY = AQTY + ? \n ");
    	}else if(dealType.equals(orderTakeOut)){
    		updateSqlTstock.append("         AQTY = AQTY - ? \n ");
    	}else{
    		throw new StoreException("SampleInOut Update TSTOKE dealType Error");
    	}

    	updateSqlTstock.append("  WHERE GOODS_CODE      = ? \n ");
    	updateSqlTstock.append("    AND GOODSDT_CODE = '000' \n ");
        //= log SQL -------------------------------------------------
        if (updateSqlLogTstock == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTstock);
            }
            updateSqlLogTstock = 1;
        }
        return updateSqlTstock.toString();
    }

}
