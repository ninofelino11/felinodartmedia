
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
import com.cware.back.entity.table.Tpromom;
import com.cware.back.entity.table.Tpromotarget;

/**
 * Register promotion Service class
 *
 * @version 1.0, 2011/02/14
 */
public class CardPromoSvc {


	private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public CardPromoSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TPROMOM List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlList( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DISTINCT A.PROMO_NO,  \n ");
        sb.append("         A.PROMO_NAME,  \n ");
        sb.append("         A.USE_CODE  \n ");
        sb.append("    FROM TPROMOM A,  \n ");
        sb.append("         TPROMOTARGET B  \n ");
        sb.append("   WHERE A.PROMO_NO = B.PROMO_NO(+)  \n ");
        sb.append("     AND A.APP_TYPE = '30'  \n ");
        sb.append("     AND A.DO_TYPE IN ('30','70')  \n ");
        sb.append("     AND A.PROMO_NO LIKE ?||'%'  \n ");
        sb.append("     AND ( ( A.PROMO_BDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("          OR A.PROMO_EDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') )   \n ");
        sb.append("        OR ( TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE   \n ");
        sb.append("          OR TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE ) )   \n ");
        sb.append("     AND A.USE_CODE LIKE ?||'%' \n ");
        sb.append("     AND A.DO_TYPE LIKE ?||'%' \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TPROMOM List
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlListGoods( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DISTINCT A.PROMO_NO,  \n ");
        sb.append("         A.PROMO_NAME,  \n ");
        sb.append("         A.USE_CODE  \n ");
        sb.append("    FROM TPROMOM A,  \n ");
        sb.append("         TPROMOTARGET B  \n ");
        sb.append("   WHERE A.PROMO_NO = B.PROMO_NO  \n ");
        sb.append("     AND A.APP_TYPE = '30'  \n ");
        sb.append("     AND A.DO_TYPE IN ('30','70')  \n ");
        sb.append("     AND A.PROMO_NO LIKE ?||'%'  \n ");
        sb.append("     AND ( ( A.PROMO_BDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("          OR A.PROMO_EDATE BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')   \n ");
        sb.append("                                 AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') )   \n ");
        sb.append("        OR ( TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE   \n ");
        sb.append("          OR TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE ) )   \n ");
        sb.append("     AND B.GOODS_CODE LIKE ?||'%'  \n ");
        sb.append("     AND A.USE_CODE LIKE ?||'%'  \n ");
        sb.append("     AND A.DO_TYPE LIKE ?||'%' \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TPROMOM
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlMaster( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("    SELECT A.PROMO_NO,                                                        \n ");
        sb.append("           A.PROMO_NAME,                                                      \n ");
        //sb.append("           A.COUPON_YN,                                                       \n ");
        sb.append("           A.APP_TYPE,                                                        \n ");
        sb.append("           A.DO_TYPE,                                                         \n ");
        sb.append("           TO_CHAR(A.PROMO_BDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_BDATE,    \n ");
        sb.append("           TO_CHAR(A.PROMO_EDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_EDATE,    \n ");
        sb.append("           A.ORDER_MEDIA_ALL_YN,                                              \n ");
        sb.append("           A.ORDER_MEDIA,                                                     \n ");
        sb.append("           FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME,\n ");
        sb.append("           A.MEDIA_CODE_ALL_YN,                                               \n ");
        sb.append("           A.MEDIA_CODE,                                                      \n ");
        sb.append("           FUN_GET_MEDIA_NAME(A.MEDIA_CODE) AS MEDIA_NAME,                    \n ");
        sb.append("           A.CARD_ALL_YN,                                                     \n ");
        sb.append("           A.CARD_CODE,                                                       \n ");
        sb.append("           B.CARD_NAME,                                                       \n ");
        sb.append("           A.CARD_DC_FLAG,                                                    \n ");
        sb.append("           A.NOREST_ALLOT_MONTHS,                                             \n ");
        //sb.append("           A.LIMIT_YN,                                                        \n ");
        //sb.append("           A.LIMIT_QTY,                                                       \n ");
        sb.append("           A.GOODS_ALL_YN,                                                    \n ");
        //sb.append("           A.GROSS_NET_FLAG,                                                  \n ");
        sb.append("           A.APP_AMT,                                                         \n ");
        sb.append("           A.AMT_RATE_FLAG,                                                   \n ");
        sb.append("           A.DO_AMT ,                                                         \n ");
        //sb.append("           A.SELECT_YN,                                                       \n ");
        //sb.append("           A.SELECT_QTY,                                                      \n ");
        //sb.append("           A.COUPON_PROMO_NO,                                                 \n ");
        //sb.append("           B.PROMO_NAME AS COUPON_PROMO_NAME,                                 \n ");
        //sb.append("           A.LOTTERY_PROMO_NO,                                                \n ");
        //sb.append("           C.LOTTERY_PROMO_NAME,				                                 \n ");
        sb.append("           A.USE_CODE,                                                        \n ");
        sb.append("           A.DO_RATE,                                                         \n ");
        sb.append("           A.REMARK,                                                          \n ");
        //sb.append("           A.FIRST_ORDER_YN,                                                  \n ");
        //sb.append("           A.MEMB_GB_ALL_YN,                                                  \n ");
        //sb.append("           A.MEMB_GB,                                                     	 \n ");
        //sb.append("           A.COUPON_USE_FIX_YN,                                             	 \n ");
        //sb.append("           A.VALID_DAYS,                                                   	 \n ");
        sb.append("           A.INSERT_DATE,                                                     \n ");
        sb.append("           A.INSERT_ID,                                                       \n ");
        sb.append("           A.MODIFY_DATE,                                                     \n ");
        sb.append("           A.MODIFY_ID                                                        \n ");
        sb.append("      FROM TPROMOM A,                                                         \n ");
        sb.append("           TCARDCODE B                                                         \n ");
        //sb.append("      	  TLOTTERYPROMOM C                                                   \n ");
        //sb.append("     WHERE A.COUPON_PROMO_NO = B.PROMO_NO(+)                                  \n ");
        //sb.append("       AND A.LOTTERY_PROMO_NO = C.LOTTERY_PROMO_NO (+)                        \n ");
        sb.append("     WHERE A.CARD_CODE = B.CARD_CODE(+)                                           \n ");
        sb.append("       AND A.PROMO_NO = ?                                    		 			\n ");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TPROMOTARGET
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlMaster1( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.PROMO_NO,  \n ");
        sb.append("         A.PROMO_SEQ,  \n ");
        sb.append("         A.GOODS_CODE,  \n ");
        sb.append("         B.GOODS_NAME,  \n ");
        sb.append("         A.GIFT_AMT,  \n ");
        sb.append("         0 COMP_SALE_PRICE,  \n ");
        sb.append("         A.INSERT_DATE,  \n ");
        sb.append("         A.INSERT_ID,  \n ");
        sb.append("         A.MODIFY_DATE,  \n ");
        sb.append("         A.MODIFY_ID,  \n ");
        sb.append("         B.TAX_YN  \n ");
        sb.append("    FROM TPROMOTARGET A,  \n ");
        sb.append("         TGOODS B  \n ");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n ");
        sb.append("     AND A.PROMO_NO = ?  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TPROMOM )
    * </PRE>
    * @param   Tpromom
    * @return  String
    */

    private String makeSqlInsert(Tpromom tpromom) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("   INSERT INTO TPROMOM ( \n ");
        sb.append("          PROMO_NO, \n ");
        sb.append("          PROMO_NAME, \n ");
        sb.append("          COUPON_YN, \n ");
        sb.append("          APP_TYPE, \n ");
        sb.append("          DO_TYPE, \n ");
        sb.append("          PROMO_BDATE, \n ");
        sb.append("          PROMO_EDATE, \n ");
        sb.append("          ORDER_MEDIA_ALL_YN, \n ");
        sb.append("          ORDER_MEDIA, \n ");
        sb.append("          MEDIA_CODE_ALL_YN, \n ");
        sb.append("          MEDIA_CODE, \n ");
        sb.append("          LIMIT_YN, \n ");
        sb.append("          LIMIT_QTY, \n ");
        sb.append("          GOODS_ALL_YN, \n ");
        sb.append("          GROSS_NET_FLAG, \n ");
        sb.append("          APP_AMT, \n ");
        sb.append("          AMT_RATE_FLAG, \n ");
        sb.append("          DO_RATE, \n ");
        sb.append("          DO_AMT, \n ");
        sb.append("          SELECT_YN, \n ");
        sb.append("          SELECT_QTY, \n ");
        sb.append("          CARD_ALL_YN,    \n ");
        sb.append("          CARD_CODE,      \n ");
        sb.append("          CARD_DC_FLAG,   \n ");
        sb.append("          NOREST_ALLOT_MONTHS,  \n ");
        //sb.append("          COUPON_PROMO_NO, \n ");
        //sb.append("          LOTTERY_PROMO_NO, \n ");
        sb.append("          USE_CODE, \n ");
        sb.append("          REMARK, \n ");
        sb.append("          FIRST_ORDER_YN, \n ");
        //sb.append("          MEMB_GB, \n ");
        sb.append("          MEMB_GB_ALL_YN, \n ");
        //sb.append("          COUPON_USE_FIX_YN, \n ");
        //sb.append("          VALID_DAYS, \n ");
        sb.append("          INSERT_DATE, \n ");
        sb.append("          INSERT_ID, \n ");
        sb.append("          MODIFY_DATE, \n ");
        sb.append("          MODIFY_ID) \n ");
        sb.append("  VALUES ( \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ?, \n ");
        sb.append("          TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TPROMOM )
    * </PRE>
    * @param   Tpromom
    * @return  String
    */
    private String makeSqlUpdate(Tpromom tpromom) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("   UPDATE TPROMOM SET \n ");
        sb.append("          PROMO_NAME = ?, \n ");
        //sb.append("          COUPON_YN = ?,\n ");
        sb.append("          APP_TYPE = ?, \n ");
        sb.append("          DO_TYPE = ?, \n ");
        sb.append("          PROMO_BDATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          PROMO_EDATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          ORDER_MEDIA_ALL_YN = ?, \n ");
        sb.append("          ORDER_MEDIA = ?, \n ");
        sb.append("          MEDIA_CODE_ALL_YN = ?, \n ");
        sb.append("          MEDIA_CODE = ?, \n ");
        //sb.append("          LIMIT_YN = ?, \n ");
        //sb.append("          LIMIT_QTY = ?, \n ");
        sb.append("          GOODS_ALL_YN = ?, \n ");
        sb.append("          GROSS_NET_FLAG = ?, \n ");
        sb.append("          APP_AMT = ?, \n ");
        sb.append("          AMT_RATE_FLAG = ?, \n ");
        sb.append("          DO_RATE = ?, \n ");
        sb.append("          DO_AMT = ?, \n ");
        //sb.append("          SELECT_YN = ?, \n ");
        //sb.append("          SELECT_QTY = ?, \n ");
        sb.append("          USE_CODE = ?, \n ");
        sb.append("          REMARK = ?, \n ");
        sb.append("          CARD_ALL_YN = ?,  \n ");
        sb.append("          CARD_CODE = ?,    \n ");
        sb.append("          CARD_DC_FLAG = ?, \n ");
        sb.append("          NOREST_ALLOT_MONTHS = ?, \n ");
        //sb.append("          FIRST_ORDER_YN = ?, \n ");
        //sb.append("          MEMB_GB_ALL_YN = ?, \n ");
        sb.append("          MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("          MODIFY_ID = ? \n ");
        sb.append("    WHERE PROMO_NO = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert TPROMOTARGET )
    * </PRE>
    * @param   Tpromotarget
    * @return  String
    */
    private final String insertSqlTpromotarget =  " INSERT INTO TPROMOTARGET ( \n "
                                                 +"        PROMO_NO, \n "
                                                 +"        PROMO_SEQ, \n "
                                                 +"        GOODS_CODE, \n "
                                                 +"        GIFT_AMT, \n "
                                                 +"        INSERT_DATE, \n "
                                                 +"        INSERT_ID, \n "
                                                 +"        MODIFY_DATE, \n "
                                                 +"        MODIFY_ID) \n "
                                                 +"VALUES ( \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ?) \n ";

    private int insertSqlLogTpromotarget =  0;

    private String makeSqlInsert(Tpromotarget tpromotarget) throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTpromotarget == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTpromotarget);
            }
            insertSqlLogTpromotarget = 1;
        }
        return insertSqlTpromotarget;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TPROMOTARGET )
    * </PRE>
    * @param   Tpromotarget
    * @return  String
    */
    private final String updateSqlTpromotarget =  " UPDATE TPROMOTARGET SET \n "
                                                 +"        GOODS_CODE  = ?, \n "
                                                 +"        GIFT_AMT    = ?, \n "
                                                 +"        MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        MODIFY_ID   = ? \n "
                                                 +"  WHERE PROMO_NO  = ? \n "
                                                 +"    AND PROMO_SEQ = ? \n ";

    private int updateSqlLogTpromotarget =  0;

    private String makeSqlUpdate(Tpromotarget tpromotarget) throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTpromotarget == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTpromotarget);
            }
            updateSqlLogTpromotarget = 1;
        }
        return updateSqlTpromotarget;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Delete TPROMOTARGET )
    * </PRE>
    * @param   Tpromotarget
    * @return  String
    */
    private final String deleteSqlTpromotarget =  " DELETE  TPROMOTARGET WHERE PROMO_NO = ? AND PROMO_SEQ = ? \n ";

    private int deleteSqlLogTpromotarget =  0;

    private String makeSqlDelete(Tpromotarget tpromotarget) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTpromotarget == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTpromotarget);
            }
            deleteSqlLogTpromotarget = 1;
        }
        return deleteSqlTpromotarget;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; List
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetList(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetMaster(ResultSet rset)  throws Exception {
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Master1
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheetMaster1(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve List
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlList(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("use_code"));
            pstmt.setString(index++,retrieve.getString("find_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_LIST",makeSheetList(rset));

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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve List
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveListGoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlListGoods(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("begin"));
            pstmt.setString(index++,retrieve.getString("end"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("use_code"));
            pstmt.setString(index++,retrieve.getString("find_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_LIST",makeSheetList(rset));

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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Master
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveMaster(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlMaster(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_MASTER",makeSheetMaster(rset));

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

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve Master1
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveMaster1(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlMaster1(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("promo_no"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_MASTER1",makeSheetMaster1(rset));

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

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TPROMOM
    * </PRE>
    * @param   Connection
    * @param   Tpromom
    * @return  int
    */
    public int insert(Connection con, Tpromom tpromom) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpromom));
            int index = 1;

            pstmt.setString(index++,tpromom.getPromo_no());
            pstmt.setString(index++,tpromom.getPromo_name());
            pstmt.setString(index++,tpromom.getCoupon_yn());
            pstmt.setString(index++,tpromom.getApp_type());
            pstmt.setString(index++,tpromom.getDo_type());
            pstmt.setString(index++,tpromom.getPromo_bdate());
            pstmt.setString(index++,tpromom.getPromo_edate());

            pstmt.setString(index++,tpromom.getOrder_media_all_yn());
            pstmt.setString(index++,tpromom.getOrder_media());
            pstmt.setString(index++,tpromom.getMedia_code_all_yn());
            pstmt.setString(index++,tpromom.getMedia_code());
            pstmt.setString(index++,tpromom.getLimit_yn());
            pstmt.setLong(index++,tpromom.getLimit_qty());
            pstmt.setString(index++,tpromom.getGoods_all_yn());

            pstmt.setString(index++,tpromom.getGross_net_flag());
            pstmt.setDouble(index++,tpromom.getApp_amt());
            pstmt.setString(index++,tpromom.getAmt_rate_flag());
            pstmt.setDouble(index++,tpromom.getDo_rate());
            pstmt.setDouble(index++,tpromom.getDo_amt());
            pstmt.setString(index++,tpromom.getSelect_yn());

            pstmt.setLong(index++,tpromom.getSelect_qty());
            pstmt.setString(index++,tpromom.getCard_all_yn());
            pstmt.setString(index++,tpromom.getCard_code());
            pstmt.setString(index++,tpromom.getCard_dc_falg());
            pstmt.setString(index++,tpromom.getNorest_allot_months());
            //pstmt.setString(index++,tpromom.getCoupon_promo_no());
            //pstmt.setString(index++,tpromom.getLottery_promo_no());
            pstmt.setString(index++,tpromom.getUse_code());
            pstmt.setString(index++,tpromom.getRemark());

            pstmt.setString(index++,tpromom.getFirst_order_yn());
            //pstmt.setString(index++,tpromom.getMemb_gb());
            pstmt.setString(index++,tpromom.getMemb_gb_all_yn());
            //pstmt.setString(index++,tpromom.getCoupon_use_fix_yn());
            //pstmt.setInt(index++,tpromom.getValid_days());

            pstmt.setString(index++,tpromom.getInsert_date());
            pstmt.setString(index++,tpromom.getInsert_id());
            pstmt.setString(index++,tpromom.getModify_date());
            pstmt.setString(index++,tpromom.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpromom.getPromo_no()          ); logString.append( "/" );
            logString.append( tpromom.getPromo_name()        ); logString.append( "/" );
            logString.append( tpromom.getCoupon_yn()         ); logString.append( "/" );
            logString.append( tpromom.getApp_type()          ); logString.append( "/" );
            logString.append( tpromom.getDo_type()           ); logString.append( "/" );
            logString.append( tpromom.getPromo_bdate()       ); logString.append( "/" );
            logString.append( tpromom.getPromo_edate()       ); logString.append( "/" );

            logString.append( tpromom.getOrder_media_all_yn()); logString.append( "/" );
            logString.append( tpromom.getOrder_media()       ); logString.append( "/" );
            logString.append( tpromom.getMedia_code_all_yn() ); logString.append( "/" );
            logString.append( tpromom.getMedia_code()        ); logString.append( "/" );
            logString.append( tpromom.getLimit_yn()          ); logString.append( "/" );
            logString.append( tpromom.getLimit_qty()         ); logString.append( "/" );
            logString.append( tpromom.getGoods_all_yn()      ); logString.append( "/" );

            logString.append( tpromom.getGross_net_flag()    ); logString.append( "/" );
            logString.append( tpromom.getApp_amt()           ); logString.append( "/" );
            logString.append( tpromom.getAmt_rate_flag()     ); logString.append( "/" );
            logString.append( tpromom.getDo_rate()           ); logString.append( "/" );
            logString.append( tpromom.getDo_amt()            ); logString.append( "/" );
            logString.append( tpromom.getSelect_yn()         ); logString.append( "/" );

            logString.append( tpromom.getSelect_qty()        ); logString.append( "/" );
            logString.append( tpromom.getCard_all_yn()       ); logString.append( "/" );
            logString.append( tpromom.getCard_code()         ); logString.append( "/" );
            logString.append( tpromom.getCard_dc_falg()      ); logString.append( "/" );
            logString.append( tpromom.getNorest_allot_months()); logString.append( "/" );
            logString.append( tpromom.getUse_code()          ); logString.append( "/" );
            logString.append( tpromom.getRemark()            ); logString.append( "/" );

            logString.append( tpromom.getFirst_order_yn()    ); logString.append( "/" );
            logString.append( tpromom.getMemb_gb_all_yn()    ); logString.append( "/" );

            logString.append( tpromom.getInsert_date()       ); logString.append( "/" );
            logString.append( tpromom.getInsert_id()         ); logString.append( "/" );
            logString.append( tpromom.getModify_date()       ); logString.append( "/" );
            logString.append( tpromom.getModify_id()         ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CardPromoSvc.tpromom_insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CardPromoSvc.tpromom_insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpromom
    * </PRE>
    * @param   Connection
    * @param   Tpromom
    * @return  int
    */
    public int update(Connection con, Tpromom tpromom) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpromom));
            int index = 1;

            pstmt.setString(index++,tpromom.getPromo_name());
            pstmt.setString(index++,tpromom.getApp_type());
            pstmt.setString(index++,tpromom.getDo_type());
            pstmt.setString(index++,tpromom.getPromo_bdate());
            pstmt.setString(index++,tpromom.getPromo_edate());

            pstmt.setString(index++,tpromom.getOrder_media_all_yn());
            pstmt.setString(index++,tpromom.getOrder_media());
            pstmt.setString(index++,tpromom.getMedia_code_all_yn());
            pstmt.setString(index++,tpromom.getMedia_code());
            pstmt.setString(index++,tpromom.getGoods_all_yn());

            pstmt.setString(index++,tpromom.getGross_net_flag());
            pstmt.setDouble(index++,tpromom.getApp_amt());
            pstmt.setString(index++,tpromom.getAmt_rate_flag());
            pstmt.setDouble(index++,tpromom.getDo_rate());
            pstmt.setDouble(index++,tpromom.getDo_amt());
            pstmt.setString(index++,tpromom.getUse_code());
            pstmt.setString(index++,tpromom.getRemark());
            pstmt.setString(index++,tpromom.getCard_all_yn());
            pstmt.setString(index++,tpromom.getCard_code());
            pstmt.setString(index++,tpromom.getCard_dc_falg());
            pstmt.setString(index++,tpromom.getNorest_allot_months());

            pstmt.setString(index++,tpromom.getModify_date());
            pstmt.setString(index++,tpromom.getModify_id());

            pstmt.setString(index++,tpromom.getPromo_no());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpromom.getPromo_name()         ); logString.append( "/" );
            logString.append( tpromom.getApp_type()           ); logString.append( "/" );
            logString.append( tpromom.getDo_type()            ); logString.append( "/" );
            logString.append( tpromom.getPromo_bdate()        ); logString.append( "/" );
            logString.append( tpromom.getPromo_edate()        ); logString.append( "/" );

            logString.append( tpromom.getOrder_media_all_yn() ); logString.append( "/" );
            logString.append( tpromom.getOrder_media()        ); logString.append( "/" );
            logString.append( tpromom.getMedia_code_all_yn()  ); logString.append( "/" );
            logString.append( tpromom.getMedia_code()         ); logString.append( "/" );
            logString.append( tpromom.getGoods_all_yn()       ); logString.append( "/" );

            logString.append( tpromom.getGross_net_flag()     ); logString.append( "/" );
            logString.append( tpromom.getApp_amt()            ); logString.append( "/" );
            logString.append( tpromom.getAmt_rate_flag()      ); logString.append( "/" );
            logString.append( tpromom.getDo_rate()            ); logString.append( "/" );
            logString.append( tpromom.getDo_amt()             ); logString.append( "/" );
            logString.append( tpromom.getUse_code()           ); logString.append( "/" );
            logString.append( tpromom.getRemark()             ); logString.append( "/" );

            logString.append( tpromom.getCard_all_yn()       ); logString.append( "/" );
            logString.append( tpromom.getCard_code()         ); logString.append( "/" );
            logString.append( tpromom.getCard_dc_falg()      ); logString.append( "/" );
            logString.append( tpromom.getNorest_allot_months()); logString.append( "/" );
            logString.append( tpromom.getModify_date()        ); logString.append( "/" );
            logString.append( tpromom.getModify_id()          ); logString.append( "/" );

            logString.append( tpromom.getPromo_no()           ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CardPromoSvc.tpromom_update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CardPromoSvc.tpromom_update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TPROMOTARGET
    * </PRE>
    * @param   Connection
    * @param   Tpromotarget
    * @return  int
    */
    public int insert(Connection con, Tpromotarget tpromotarget) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpromotarget));
            int index = 1;
            pstmt.setString(index++,tpromotarget.getPromo_no());
            pstmt.setString(index++,tpromotarget.getPromo_seq());
            pstmt.setString(index++,tpromotarget.getGoods_code());
            pstmt.setString(index++,tpromotarget.getGift_amt());
            pstmt.setString(index++,tpromotarget.getInsert_date());
            pstmt.setString(index++,tpromotarget.getInsert_id());
            pstmt.setString(index++,tpromotarget.getModify_date());
            pstmt.setString(index++,tpromotarget.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpromotarget.getPromo_no()      ); logString.append( "/" );
            logString.append( tpromotarget.getPromo_seq()     ); logString.append( "/" );
            logString.append( tpromotarget.getGoods_code()    ); logString.append( "/" );
            logString.append( tpromotarget.getGift_amt()      ); logString.append( "/" );
            logString.append( tpromotarget.getInsert_date()   ); logString.append( "/" );
            logString.append( tpromotarget.getInsert_id()     ); logString.append( "/" );
            logString.append( tpromotarget.getModify_date()   ); logString.append( "/" );
            logString.append( tpromotarget.getModify_id()     ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CardPromoSvc.tpromotarget_insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CardPromoSvc.tpromotarget_insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpromotarget
    * </PRE>
    * @param   Connection
    * @param   Tpromotarget
    * @return  int
    */
    public int update(Connection con, Tpromotarget tpromotarget) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpromotarget));
            int index = 1;
            pstmt.setString(index++,tpromotarget.getGoods_code());
            pstmt.setString(index++,tpromotarget.getGift_amt());
            pstmt.setString(index++,tpromotarget.getModify_date());
            pstmt.setString(index++,tpromotarget.getModify_id());
            pstmt.setString(index++,tpromotarget.getPromo_no());
            pstmt.setString(index++,tpromotarget.getPromo_seq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpromotarget.getPromo_no()    ); logString.append( "/" );
            logString.append( tpromotarget.getGift_amt()    ); logString.append( "/" );
            logString.append( tpromotarget.getModify_date() ); logString.append( "/" );
            logString.append( tpromotarget.getModify_id()   ); logString.append( "/" );
            logString.append( tpromotarget.getPromo_no()    ); logString.append( "/" );
            logString.append( tpromotarget.getPromo_seq()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CardPromoSvc.tpromotarget_update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CardPromoSvc.tpromotarget_update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tpromotarget
    * </PRE>
    * @param   Connection
    * @param   Tpromotarget
    * @return  int
    */
    public int delete(Connection con, Tpromotarget tpromotarget) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tpromotarget));
            int index = 1;
            pstmt.setString(index++,tpromotarget.getPromo_no());
            pstmt.setString(index++,tpromotarget.getPromo_seq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpromotarget.getPromo_no()   ); logString.append( "/" );
            logString.append( tpromotarget.getPromo_seq()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[CardPromoSvc.tpromotarget_delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[CardPromoSvc.tpromotarget_delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
