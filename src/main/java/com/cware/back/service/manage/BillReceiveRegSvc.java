
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
import com.cware.back.entity.table.Tacctinvoice;

/**
 * Excute BillReceiveReg Service class
 *
 * @version 1.0, 2006/07/25
 */
public class BillReceiveRegSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BillReceiveRegSvc() {}

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
             pstmt.setString(index++, retrieve.getString("pay_yymm"));
             pstmt.setString(index++, retrieve.getString("entp_code"));

             rset  = pstmt.executeQuery();

             retrieve.put("RESULT", makeSheet(rset));

         }catch(SQLException se){
             log.error("[BillReceiveRegSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[BillReceiveRegSvc.retrieve() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return retrieve;
     }

     /**
      * <PRE>
      * Desc : Make SQL
      * */
     public String makeSql( RetrieveModel retrieve ) throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("     SELECT PAY_YYMM,                                          \n");
		 sb.append("            ENTP_CODE,                                         \n");
		 sb.append("            TAX_YN,                                            \n");
		 sb.append("            LAST_HOLD_AMT,                                     \n");
		 sb.append("            CURR_AMT,                                          \n");
		 sb.append("            BILL_COST,                                         \n");
		 sb.append("            BILL_VAT,                                          \n");
		 sb.append("            BILL_AMT,                                          \n");
		 sb.append("            CUT_AMT,                                           \n");
		 sb.append("            CURR_HOLD_AMT,                                     \n");
		 sb.append("            TO_CHAR(RCV_DATE, 'YYYY/MM/DD') AS RCV_DATE,       \n");
		 sb.append("            REMARK,                                            \n");
		 sb.append("            RCV_YN,                                            \n");
		 sb.append("            LAST_HOLD_AMT + CURR_AMT AS TOTAL_SUM,             \n");
		 sb.append("            LAST_HOLD_AMT + CURR_AMT - (BILL_COST + BILL_VAT) AS DIFF_AMT   \n");
         sb.append("       FROM TACCTINVOICE A                                     \n");
         sb.append("      WHERE PAY_YYMM = ?                                       \n");
         sb.append("        AND ENTP_CODE = ?                                      \n");

         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("pay_yymm: "     + retrieve.getString("pay_yymm"));
             log.debug("entp_code: " + retrieve.getString("entp_code"));
         }
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Edit retrieval result
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

      //= Update -------------------------------------------------
      /**
      * <PRE>
      * Desc : Update TACCTINVOICE
      * </PRE>
      * @param   Connection
      * @param   Tacctinvoice
      * @return  int
      */
      public int update(Connection con, Tacctinvoice tacctinvoice) throws StoreException{

	      PreparedStatement pstmt       = null;
	      int executedRtn = 0;

	      try {

	          pstmt = con.prepareStatement(makeSqlUpdate(tacctinvoice));
	          int index = 1;

	          pstmt.setDouble(index++,tacctinvoice.getLast_hold_amt());
	          pstmt.setDouble(index++,tacctinvoice.getCurr_amt     ());
	          pstmt.setDouble(index++,tacctinvoice.getBill_cost    ());
	          pstmt.setDouble(index++,tacctinvoice.getBill_vat     ());
	          pstmt.setDouble(index++,tacctinvoice.getBill_amt     ());
	          pstmt.setDouble(index++,tacctinvoice.getCut_amt      ());
	          pstmt.setDouble(index++,tacctinvoice.getCurr_hold_amt());
	          pstmt.setString(index++,tacctinvoice.getRcv_date     ());
	          pstmt.setString(index++,tacctinvoice.getRcv_yn       ());
	          pstmt.setString(index++,tacctinvoice.getRemark       ());
	          pstmt.setString(index++,tacctinvoice.getModify_id    ());
	          pstmt.setString(index++,tacctinvoice.getPay_yymm     ());
	          pstmt.setString(index++,tacctinvoice.getEntp_code    ());

 	          //= log Save Data ---------------------
	          StringBuffer logString = new StringBuffer();
	          logString.append( tacctinvoice.getLast_hold_amt() ); logString.append( "/" );
	          logString.append( tacctinvoice.getCurr_amt     () ); logString.append( "/" );
	          logString.append( tacctinvoice.getBill_cost    () ); logString.append( "/" );
	          logString.append( tacctinvoice.getBill_vat     () ); logString.append( "/" );
	          logString.append( tacctinvoice.getBill_amt     () ); logString.append( "/" );
	          logString.append( tacctinvoice.getCut_amt      () ); logString.append( "/" );
	          logString.append( tacctinvoice.getCurr_hold_amt() ); logString.append( "/" );
	          logString.append( tacctinvoice.getRcv_date     () ); logString.append( "/" );
	          logString.append( tacctinvoice.getRcv_yn       () ); logString.append( "/" );
	          logString.append( tacctinvoice.getRemark       () ); logString.append( "/" );
	          logString.append( tacctinvoice.getModify_id    () ); logString.append( "/" );
	          logString.append( tacctinvoice.getPay_yymm     () ); logString.append( "/" );
	          logString.append( tacctinvoice.getEntp_code    () ); logString.append( "/" );
	          logSave.info(logString.toString());

	          executedRtn = pstmt.executeUpdate();

	      }catch(SQLException se){
	          logSave.error("[BillReceiveRegSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
	          throw new StoreException(se.getMessage());
	      }catch(Exception e){
	          logSave.error("[BillReceiveRegSvc.update() Exception : ERR-"+e.getMessage());
	          throw new StoreException(e.getMessage());
	      }finally {
	          DBUtils.freeConnection(null, pstmt, null);
	      }
	      return executedRtn;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( Update TACCTINVOICE )
       * </PRE>
       * @param   Tacctinvoice
       * @return  String
       */
      	private String makeSqlUpdate(Tacctinvoice tacctinvoice) throws StoreException{
            StringBuffer sb = new StringBuffer();

            sb.append("  UPDATE TACCTINVOICE               \n");
			sb.append("     SET LAST_HOLD_AMT = ?,         \n");
			sb.append("         CURR_AMT = ?,              \n");
			sb.append("         BILL_COST = ?,             \n");
			sb.append("         BILL_VAT = ?,              \n");
			sb.append("         BILL_AMT = ?,              \n");
			sb.append("         CUT_AMT = ?,               \n");
			sb.append("         CURR_HOLD_AMT = ?,         \n");
			sb.append("         RCV_DATE = ?,              \n");
			sb.append("         RCV_YN = ?,                \n");
			sb.append("         REMARK = ?,                \n");
			sb.append("         MODIFY_ID = ?,             \n");
			sb.append("         MODIFY_DATE = SYSDATE      \n");
			sb.append("   WHERE PAY_YYMM = ?               \n");
			sb.append("     AND ENTP_CODE = ?              \n");
			sb.append("     AND RCV_YN = '0'               \n");

           //= log SQL -------------------------------------------------
           if (logSave.isDebugEnabled()) {
               logSave.debug(sb.toString());
           }

           return sb.toString();
       }
}
