
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

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.Message;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tinplanqty;

/**
 * 입고예정수량산정정보 일괄등록 Service class
 *
 * @version 1.0, 2006/06/30
 */
public class PopInplanqtyBatchinsertSvc {

   // private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
   // private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopInplanqtyBatchinsertSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; main cursor
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT GOODS_CODE,  \n");
        sb.append("       GOODSDT_CODE,  \n");
        sb.append("       GOODSDT_INFO  \n");
        sb.append("  FROM TGOODSDT  \n");
        sb.append(" WHERE GOODS_CODE    = ?  \n");
        sb.append("   AND GOODSDT_CODE <> '000'  \n");
        sb.append(" ORDER BY GOODSDT_CODE  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    private String makeSqlDt(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT GOODS_CODE,  \n");
        sb.append("       GOODSDT_CODE,  \n");
        sb.append("       GOODSDT_INFO  \n");
        sb.append("  FROM TGOODSDT  \n");
        sb.append(" WHERE GOODS_CODE    = ?  \n");
        sb.append("   AND GOODSDT_CODE = ?   \n");
        sb.append(" ORDER BY GOODSDT_CODE  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; main cursor
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(Connection con, ResultSet rset, RetrieveModel retrieve)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        Message         message             = null;
        int             executedRtn         = 0;
        String          goods_code          = "";
        String          goodsdt_code        = "";
        String          pre_seq             = "";
        String          pre_lead_time       = "";
        String          pre_daily_capa_qty  = "";
        String          pre_inplan_qty  	= "";
        String          pre_max_sale_qty    = "";
        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            message = new Message();

            goods_code    = ComUtils.objToStr(hmSheet.get("GOODS_CODE"),"");
            goodsdt_code  = ComUtils.objToStr(hmSheet.get("GOODSDT_CODE"),"");
            pre_seq       = retrieve.getString("NOW_SEQ");

            executedRtn = retrieveBefore(con, goods_code, goodsdt_code, pre_seq, message);

            pre_lead_time       = message.getString("PRE_LEAD_TIME");
            pre_daily_capa_qty  = message.getString("PRE_DAILY_CAPA_QTY");
            pre_inplan_qty  	= message.getString("PRE_INPLAN_QTY");
            pre_max_sale_qty    = message.getString("PRE_MAX_SALE_QTY");

            hmSheet.put("PRE_LEAD_TIME",       pre_lead_time);
            hmSheet.put("PRE_DAILY_CAPA_QTY",  pre_daily_capa_qty);
            hmSheet.put("PRE_INPLAN_QTY",  	   pre_inplan_qty);
            hmSheet.put("PRE_MAX_SALE_QTY",    pre_max_sale_qty);

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
    * Desc : Retrieve SQL ; main cursor
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            int index = 1;

            if( retrieve.getString("stock_chk_place").toString().equals("3")){
                index = 1;
                pstmt = con.prepareStatement(makeSql(retrieve));
                pstmt.setString(index++, retrieve.getString("goods_code"));

            }else{
                index = 1;
                pstmt = con.prepareStatement(makeSqlDt(retrieve));
                pstmt.setString(index++, retrieve.getString("goods_code"));
                pstmt.setString(index++, retrieve.getString("goodsdt_code"));
            }

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(con, rset, retrieve ));

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

    /**
    * <PRE>
    * Desc : Make SQL ; now TINPLANQTY information
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNow = "SELECT /*+INDEX_DESC(TINPLANQTY PK_TINPLANQTY)*/   \n"
                                      + "        SEQ,   									\n"
                                      + "        TO_CHAR(SYSDATE, 'YYYY/MM/DD'),   			\n"
                                      + "        LEAD_TIME,   								\n"
                                      + "        DAILY_CAPA_QTY,   							\n"
                                      + "        INPLAN_QTY,	   							\n"
                                      + "        MAX_SALE_QTY   							\n"
                                      + "  FROM  TINPLANQTY   								\n"
                                      + " WHERE  GOODS_CODE   = ?  							\n"
                                      + "   AND  GOODSDT_CODE = ?  							\n"
                                      + "   AND  ROWNUM = 1   								\n";
    private int selectSqlNowLog =  0;

    private String makeSqlNow() throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNowLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNow);
            }
            selectSqlNowLog = 1;
        }
        return selectSqlNow;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; now TINPLANQTY information
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlNowDt = "SELECT /*+INDEX_DESC(TINPLANQTY PK_TINPLANQTY)*/		\n"
                                      + "        SEQ,   										\n"
                                      + "        TO_CHAR(SYSDATE, 'YYYY/MM/DD'),   				\n"
                                      + "        LEAD_TIME,   									\n"
                                      + "        DAILY_CAPA_QTY,   								\n"
                                      + "        INPLAN_QTY,	   								\n"
                                      + "        MAX_SALE_QTY   								\n"
                                      + "  FROM  TINPLANQTY   									\n"
                                      + " WHERE  GOODS_CODE   = ?  								\n"
                                      + "   AND  GOODSDT_CODE = ?  								\n";
    private int selectSqlNowDtLog =  0;

    private String makeSqlNowDt() throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlNowDtLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlNowDt);
            }
            selectSqlNowDtLog = 1;
        }
        return selectSqlNowDt;
    }

    /**
    * <PRE>
    * Desc : SELECT ; now TINPLANQTY information
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  int
    */
    public int retrieveNow(Connection con, RetrieveModel retrieve) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        String          seq            = "";
        String          start_date     = "";
        String          lead_time      = "";
        String          daily_capa_qty = "";
        String			inplan_qty	   = "";
        String          max_sale_qty   = "";
        try{
            if( retrieve.getString("stock_chk_place").toString().equals("1")){ //상품조건변경
                 pstmt = con.prepareStatement(makeSqlNowDt());
            }else{
                 pstmt = con.prepareStatement(makeSqlNow());
            }

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));
            pstmt.setString(index++, retrieve.getString("goodsdt_code"));

            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) {

                seq            = rset.getString(1);
                start_date     = rset.getString(2);
                lead_time      = rset.getString(3);
                daily_capa_qty = rset.getString(4);
                inplan_qty	   = rset.getString(5);
                max_sale_qty   = rset.getString(6);
                retrieve.put("NOW_SEQ",         seq);
                retrieve.put("START_DATE",      start_date);
                retrieve.put("LEAD_TIME",       lead_time);
                retrieve.put("DAILY_CAPA_QTY",  daily_capa_qty);
                retrieve.put("INPLAN_QTY",  	inplan_qty);
                retrieve.put("MAX_SALE_QTY",    max_sale_qty);
            } else {

                retrieve.put("NOW_SEQ",             "00000");
                retrieve.put("START_DATE",          DBUtils.getSysDateTime(con, "YYYY/MM/DD"));
                retrieve.put("PRE_LEAD_TIME",       "0");
                retrieve.put("PRE_DAILY_CAPA_QTY",  "0");
                retrieve.put("PRE_INPLAN_QTY",  	"0");
                retrieve.put("PRE_MAX_SALE_QTY",    "0");
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return 1;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; before TINPLANQTY information
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlBefore = " SELECT LEAD_TIME, DAILY_CAPA_QTY, INPLAN_QTY, MAX_SALE_QTY  \n"
                                         + "   FROM TINPLANQTY  \n"
                                         + "  WHERE GOODS_CODE   = ?  \n"
                                         + "    AND GOODSDT_CODE = ?  \n"
                                         + "    AND SEQ          = ?  \n"  ;
    private int selectSqlBeforeLog =  0;

    private String makeSqlBefore( ) throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlBeforeLog == 0) {
            if (log.isDebugEnabled()) {
                log.debug(selectSqlBefore);
            }
            selectSqlBeforeLog = 1;
        }
        return selectSqlBefore;
    }

    /**
    * <PRE>
    * Desc : SELECT ; before TINPLANQTY information
    * </PRE>
    * @param   Connection
    * @param   String goods_code
    * @param   String goodsdt_code
    * @param   String seq
    * @param   Message
    * @return  int
    */
    private int retrieveBefore(Connection con, String goods_code, String goodsdt_code, String seq, Message message) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;

        String          pre_lead_time      = "";
        String          pre_daily_capa_qty = "";
        String          pre_max_sale_qty   = "";
        String			pre_inplan_qty	   = "";
        try{
            pstmt = con.prepareStatement(makeSqlBefore());
            int index = 1;
            pstmt.setString(index++, goods_code);
            pstmt.setString(index++, goodsdt_code);
            pstmt.setString(index++, seq);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) {

                pre_lead_time      = rset.getString(1);
                pre_daily_capa_qty = rset.getString(2);
                pre_inplan_qty 	   = rset.getString(3);
                pre_max_sale_qty   = rset.getString(4);
                message.put("PRE_LEAD_TIME",       pre_lead_time);
                message.put("PRE_DAILY_CAPA_QTY",  pre_daily_capa_qty);
                message.put("PRE_INPLAN_QTY",  	   pre_inplan_qty);
                message.put("PRE_MAX_SALE_QTY",    pre_max_sale_qty);
            } else {

                message.put("PRE_LEAD_TIME",       "0");
                message.put("PRE_DAILY_CAPA_QTY",  "0");
                message.put("PRE_INPLAN_QTY",  	   "0");
                message.put("PRE_MAX_SALE_QTY",    "0");
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return 1;
    }



    /**
    * <PRE>
    * Desc : Make SQL ; before TINPLANQTY.START_DATE
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlBeforeStartDate = "SELECT TO_CHAR(START_DATE, 'YYYY/MM/DD') \n"
                                                  + "  FROM TINPLANQTY \n"
                                                  + " WHERE GOODS_CODE   = ? \n"
                                                  + "   AND GOODSDT_CODE = '000' \n"
                                                  + "   AND SEQ          = ? \n"  ;
    private int selectSqlBeforeStartDateLog =  0;

    private String makeSqlBeforeStartDate() throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlBeforeStartDateLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(selectSqlBeforeStartDate);
            }
            selectSqlBeforeStartDateLog = 1;
        }
        return selectSqlBeforeStartDate;
    }

    /**
    * <PRE>
    * Desc : SELECT ; before TINPLANQTY.START_DATE
    * </PRE>
    * @param   Connection
    * @param   String goods_code
    * @param   String seq
    * @return  int
    */
    public String getBeforeStartDate(Connection con, String goods_code, String seq) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;

        String            before_start_date = "";

        try{
            pstmt = con.prepareStatement(makeSqlBeforeStartDate());
            int index = 1;
            pstmt.setString(index++, goods_code);
            pstmt.setString(index++, seq);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) {
                before_start_date      = rset.getString(1);
            } else {
                before_start_date      = "1900/01/01";
            }

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return before_start_date;
    }


    /**
    * <PRE>
    * Desc : Make SQL ; SUM(TORDERSTOCK.TOT_SALE_QTY)
    * </PRE>
    * @param
    * @return  String
    */
    private final String selectSqlTotalSaleQty = "SELECT SUM(TOT_SALE_QTY) \n"
                                               + "  FROM TORDERSTOCK \n"
                                               + " WHERE GOODS_CODE    = ? \n"
                                               + "   AND (GOODSDT_CODE = ? \n"
                                               + "   OR GOODSDT_CODE = '000') \n"  ;
    private int selectSqlTotalSaleQtyLog =  0;

    private String makeSqlTotalSaleQty() throws StoreException{
        //= log SQL -------------------------------------------------
        if (selectSqlTotalSaleQtyLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(selectSqlTotalSaleQty);
            }
            selectSqlTotalSaleQtyLog = 1;
        }
        return selectSqlTotalSaleQty;
    }

    /**
    * <PRE>
    * Desc : SELECT ; SUM(TORDERSTOCK.TOT_SALE_QTY)
    * </PRE>
    * @param   Connection
    * @param   String goods_code
    * @param   String seq
    * @return  long
    */
    public long getTotalSaleQty(Connection con, String goods_code, String goodsdt_code) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;

        long              total_sale_qty = 0;

        try{
            pstmt = con.prepareStatement(makeSqlTotalSaleQty());
            int index = 1;
            pstmt.setString(index++, goods_code);
            pstmt.setString(index++, goodsdt_code);
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) {
                total_sale_qty      = rset.getLong(1);
            }

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return total_sale_qty;
    }

    /**
    * <PRE>
    * Desc : Make SQL ; Update before TINPLANQTY.END_DATE
    * </PRE>
    * @param
    * @return  String
    */
    private final String updateSqlBeforeEndDate = "UPDATE TINPLANQTY \n"
                                                + "   SET END_DATE     = TO_DATE(?, 'YYYY/MM/DD'), \n"
                                                + "   	  MODIFY_DATE  = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n"
                                                + "       MODIFY_ID	   = ? \n"
                                                + " WHERE GOODS_CODE   = ? \n"
                                                + "   AND GOODSDT_CODE = ? \n"
                                                + "   AND SEQ          = ? \n"  ;
    private int updateSqlBeforeEndDateLog =  0;

    private String makeSqlUpdateBeforeEndDate() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlBeforeEndDateLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlBeforeEndDate);
            }
            updateSqlBeforeEndDateLog = 1;
        }
        return updateSqlBeforeEndDate;
    }


    /**
    * <PRE>
    * Desc : Update before TINPLANQTY.END_DATE
    * </PRE>
    * @param   Connection
    * @param   goods_code
    * @param   goodsdt_code
    * @param   seq
    * @param   end_date
    * @return  int
    */
    public int updateBeforeEndDate(Connection con, String goods_code, String goodsdt_code, String seq, String end_date, String sysdatetime, String user_id) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateBeforeEndDate());
            int index = 1;
            pstmt.setString(index++, end_date     );
            pstmt.setString(index++, sysdatetime  );
            pstmt.setString(index++, user_id      );
            pstmt.setString(index++, goods_code   );
            pstmt.setString(index++, goodsdt_code );
            pstmt.setString(index++, seq          );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( end_date     ); logString.append( "/" );
            logString.append( sysdatetime  ); logString.append( "/" );
            logString.append( user_id      ); logString.append( "/" );
            logString.append( goods_code   ); logString.append( "/" );
            logString.append( goodsdt_code ); logString.append( "/" );
            logString.append( seq          ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ; Insert Tinplanqty
    * </PRE>
    * @param   Tinplanqty
    * @return  String
    */
    private final String insertSqlTinplanqty = "INSERT INTO TINPLANQTY 				\n"
                                             + "      ( GOODS_CODE, 				\n"
                                             + "        GOODSDT_CODE, 				\n"
                                             + "        SEQ, 						\n"
                                             + "        START_DATE, 				\n"
                                             + "        END_DATE, 					\n"
                                             + "        LEAD_TIME, 					\n"
                                             + "        DAILY_CAPA_QTY, 			\n"
                                             + "        INPLAN_QTY, 				\n"
                                             + "        MAX_SALE_QTY, 				\n"
                                             + "        INSERT_DATE, 				\n"
                                             + "        INSERT_ID, 					\n"
                                             + "        MODIFY_DATE, 				\n"
                                             + "        MODIFY_ID) 					\n"
                                             + "VALUES( ?, 							\n"
                                             + "        ?, 							\n"
                                             + "        ?, 							\n"
                                             + "        TO_DATE(?, 'YYYY/MM/DD'), 	\n"
                                             + "        TO_DATE(?, 'YYYY/MM/DD'), 	\n"
                                             + "        ?, 							\n"
                                             + "        ?, 							\n"
                                             + "        ?, 							\n"
                                             + "        ?, 							\n"
                                             + "        SYSDATE, 					\n"
                                             + "        ? , 						\n"
                                             + "        SYSDATE, 					\n"
                                             + "        ? ) 						\n";
    private int insertSqlTinplanqtyLog =  0;

    private String makeSqlInsert(Tinplanqty tinplanqty) throws StoreException{

        //= log SQL -------------------------------------------------
        if (insertSqlTinplanqtyLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTinplanqty);
            }
            insertSqlTinplanqtyLog = 1;
        }
        return insertSqlTinplanqty;
    }

    /**
    * <PRE>
    * Desc : Insert Tinplanqty
    * </PRE>
    * @param   Connection
    * @param   Tinplanqty
    * @return  int
    */
    public int insert(Connection con, Tinplanqty tinplanqty) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tinplanqty));
            int index = 1;
            pstmt.setString(index++, tinplanqty.getGoods_code()     );
            pstmt.setString(index++, tinplanqty.getGoodsdt_code()   );
            pstmt.setString(index++, tinplanqty.getSeq()            );
            pstmt.setString(index++, tinplanqty.getStart_date()     );
            pstmt.setString(index++, tinplanqty.getEnd_date()       );
            pstmt.setLong  (index++, tinplanqty.getLead_time()      );
            pstmt.setLong  (index++, tinplanqty.getDaily_capa_qty() );
            pstmt.setLong  (index++, tinplanqty.getInplan_qty()		);
            pstmt.setLong  (index++, tinplanqty.getMax_sale_qty()   );
            pstmt.setString(index++, tinplanqty.getInsert_id()      );
            pstmt.setString(index++, tinplanqty.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tinplanqty.getGoods_code()     ); logString.append( "/" );
            logString.append( tinplanqty.getGoodsdt_code()   ); logString.append( "/" );
            logString.append( tinplanqty.getSeq()            ); logString.append( "/" );
            logString.append( tinplanqty.getStart_date()     ); logString.append( "/" );
            logString.append( tinplanqty.getEnd_date()       ); logString.append( "/" );
            logString.append( tinplanqty.getLead_time()      ); logString.append( "/" );
            logString.append( tinplanqty.getDaily_capa_qty() ); logString.append( "/" );
            logString.append( tinplanqty.getInplan_qty() 	 ); logString.append( "/" );
            logString.append( tinplanqty.getMax_sale_qty()   ); logString.append( "/" );
            logString.append( tinplanqty.getInsert_id()      ); logString.append( "/" );
            logString.append( tinplanqty.getModify_id()      ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }



    /**
    * <PRE>
    * Desc : Make SQL ; SUM(TINPLANQTY.MAX_SALE_QTY)
    * </PRE>
    * @param
    * @return  String
    */
    private String makeSqlSumMaxSaleQty() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SUM(NVL(MAX_SALE_QTY,0))  \n");
        sb.append("  FROM TINPLANQTY A, TGOODSDT B  \n");
        sb.append(" WHERE A.GOODS_CODE   = B.GOODS_CODE  \n");
        sb.append("   AND A.GOODSDT_CODE = B.GOODSDT_CODE  \n");
        sb.append("   AND A.GOODS_CODE   = ?  \n");
        sb.append("   AND B.SALE_GB      = '00'  \n");
        sb.append("   AND A.SEQ          = ?  \n");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : SELECT ; SUM(TINPLANQTY.MAX_SALE_QTY)
    * </PRE>
    * @param   Connection
    * @param   String goods_code
    * @param   String seq
    * @return  long
    */
    public long getSumMaxSaleQty(Connection con, String goods_code, String seq) throws StoreException {
        PreparedStatement pstmt  = null;
        ResultSet         rset   = null;
        long              sum_max_sael_qty = 0;

        try{
            pstmt = con.prepareStatement(makeSqlSumMaxSaleQty());
            int index = 1;
            pstmt.setString(index++, goods_code);
            pstmt.setString(index++, seq);
            rset  = pstmt.executeQuery();
            if (rset!=null && rset.next()) {
                sum_max_sael_qty      = rset.getLong(1);
            }

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return sum_max_sael_qty;
    }

    /**
     * <PRE>
     * Desc : Make SQL ; SUM(TORDERSTOCK.TOT_SALE_QTY)
     * </PRE>
     * @param
     * @return  String
     */
     private String makeSqlSumTotSaleQty() throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("SELECT SUM(NVL(TOT_SALE_QTY,0))  		\n");
         sb.append("  FROM TORDERSTOCK A  					\n");
         sb.append(" WHERE A.GOODS_CODE   		= ?  		\n");
         sb.append("   AND A.GOODSDT_CODE  		= ?  		\n");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug(sb.toString());
         }
         return sb.toString();
     }

     /**
     * <PRE>
     * Desc : SELECT ; SUM(TINPLANQTY.MAX_SALE_QTY)
     * </PRE>
     * @param   Connection
     * @param   String goods_code
     * @param   String seq
     * @return  long
     */
     public long getSumTotSaleQty(Connection con, String goods_code, String goodsdt_code) throws StoreException {
         PreparedStatement pstmt  = null;
         ResultSet         rset   = null;
         long              sum_tot_sale_qty = 0;

         try{
             pstmt = con.prepareStatement(makeSqlSumTotSaleQty());
             int index = 1;
             pstmt.setString(index++, goods_code);
             pstmt.setString(index++, goodsdt_code);
             rset  = pstmt.executeQuery();
             if (rset!=null && rset.next()) {
            	 sum_tot_sale_qty      = rset.getLong(1);
             }

         }catch(SQLException se){
             logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return sum_tot_sale_qty;
     }


}
