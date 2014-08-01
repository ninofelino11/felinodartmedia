
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
import com.cware.back.entity.table.Tpurchase_dt;
import com.cware.back.entity.table.Tpurchase_m;

/**
 * Register ServeEntpAccount Service class
 *
 * @version 1.0, 2006/08/12
 */
public class ServeEntpAccountSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ServeEntpAccountSvc() {}


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   purchase_no                    : purchase_no
    * @return  String
    */
    public String makeSqlDt0( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT B.PURCHASE_NO,                     \n");
        sb.append("          B.GOODS_CODE,                      \n");
        sb.append("          C.GOODS_NAME,                      \n");
        sb.append("          C.BUY_MED,                         \n");
        sb.append("          C.COST_TAX_YN    AS TAX_YN,        \n");
        sb.append("          B.GOODSDT_CODE,                    \n");
        sb.append("          D.GOODSDT_INFO,                    \n");
        sb.append("          A.ENTP_CODE,                       \n");
        sb.append("          B.BUY_QTY,                      \n");
        sb.append("          B.BUY_COST,                        \n");
        sb.append("          B.BUY_VAT,                        \n");
        sb.append("          B.BUY_COST + B.BUY_VAT       AS BUY_PRICE,       \n");
        sb.append("          B.RETURN_QTY,                      \n");
        sb.append("          B.RETURN_COST,                    \n");
        sb.append("          B.RETURN_VAT,                      \n");
        sb.append("          B.RETURN_COST + B.RETURN_VAT       AS RETURN_PRICE,    \n");
        sb.append("          B.BUY_QTY - B.RETURN_QTY       AS PURCHASE_QTY,     \n");
        sb.append("          B.BUY_COST - B.RETURN_COST       AS PURCHASE_COST,    \n");
        sb.append("          B.BUY_VAT - B.RETURN_VAT       AS PURCHASE_VAT,     \n");
        sb.append("          (B.BUY_COST + B.BUY_VAT)-(B.RETURN_COST + B.RETURN_VAT)       AS PURCHASE_PRICE,          \n");
        sb.append("          B.PROCESS_DATE,                         \n");
        sb.append("          B.PROCESS_ID,                         \n");
        sb.append("          B.SEQ                              \n");
        sb.append("     FROM TPURCHASE_M    A,                  \n");
        sb.append("          TPURCHASE_DT   B,                  \n");
        sb.append("          TGOODS         C,                  \n");
        sb.append("          TGOODSDT       D                   \n");
        sb.append("    WHERE A.PURCHASE_NO  = ?                 \n");
        sb.append("      AND A.PURCHASE_NO  = B.PURCHASE_NO     \n");
        sb.append("      AND B.GOODS_CODE   = C.GOODS_CODE      \n");
        sb.append("      AND B.GOODSDT_CODE = D.GOODSDT_CODE    \n");
        sb.append("      AND B.GOODS_CODE   = D.GOODS_CODE      \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("purchase_no  : " + retrieve.getString("purchase_no"));
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   purchase_no                    : purchase_no
    * @return  String
    */
    public String makeSqlDt1( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.PURCHASE_NO,                    \n");
        sb.append("         A.ENTP_CODE,                      \n");
        sb.append("         B.ENTP_NAME,                      \n");
        sb.append("         A.PREV_YOUBO,                     \n");
        sb.append("         A.SALE_QTY,                       \n");
        sb.append("         A.CANCEL_QTY,                     \n");
        sb.append("         A.TOT_QTY,                        \n");
        sb.append("         A.TOT_PURCHASE_AMT,               \n");
        sb.append("         A.PAY_AMT3 		AS SUM_PAY_AMT,   \n");
        sb.append("         A.YOUBO_AMT,                      \n");
        sb.append("         A.KONGJE_AMT,                     \n");
        sb.append("         A.YOUBO_MEMO,                     \n");
        sb.append("         A.KONGJE_MEMO,                    \n");
        sb.append("         TO_CHAR(A.PURCHASE_FR,'YYYY/MM/DD') AS PURCHASE_FR,  \n");
        sb.append("         TO_CHAR(A.PURCHASE_TO,'YYYY/MM/DD') AS PURCHASE_TO,   \n");
        sb.append("         TO_CHAR(A.PROCESS_DATE,'YYYY/MM/DD HH24:MI:SS') AS PROCESS_DATE,    \n");
        sb.append("         A.PROCESS_ID,                     \n");
        sb.append("         A.FLAG,                           \n");
        sb.append("         A.SALE_TAX_AMT,                   \n");
        sb.append("         A.SALE_VAT,                       \n");
        sb.append("         A.SALE_NOTAX_AMT,                 \n");
        sb.append("         0 AS SALE_NOTAX_VAT,              \n");
        sb.append("         A.SALE_TAX_AMT + A.SALE_VAT AS SALE_TAX_SUM,    \n");
        sb.append("         A.SALE_NOTAX_AMT AS SALE_NOTAX_SUM,      \n");
        sb.append("         A.SALE_TAX_AMT + A.SALE_VAT + A.SALE_NOTAX_AMT AS SUM_SALE_AMT    \n");
        sb.append("    FROM TPURCHASE_M   A,                  \n");
        sb.append("         TENTERPRISE   B                   \n");
        sb.append("   WHERE PURCHASE_NO = ?                   \n");
        sb.append("     AND A.ENTP_CODE = B.ENTP_CODE         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("purchase_no  : " + retrieve.getString("purchase_no"));
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   entp_code                    : entp_code
    * @param   account_date                 : account_date
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.GOODS_CODE                 \n");
        sb.append("      , B.GOODS_NAME               \n");
        sb.append("      , A.GOODSDT_CODE               \n");
        sb.append("      , C.GOODSDT_INFO               \n");
        sb.append("      , A.BUY_MED					 \n");
        sb.append("      , B.COST_TAX_YN AS TAX_YN					    \n");
        sb.append("      , SUM(A.BUY_QTY) 	                     AS BUY_QTY 		    \n");
        sb.append("      , SUM(A.BUY_COST) 	                     AS BUY_COST		  \n");
        sb.append("      , SUM(A.BUY_VAT)  	                     AS BUY_VAT			  \n");
        sb.append("      , SUM(A.BUY_COST) + SUM(A.BUY_VAT)       AS BUY_PRICE		  \n");
        sb.append("      , SUM(A.RETURN_QTY) 	                 AS RETURN_QTY		  \n");
        sb.append("      , SUM(A.RETURN_COST)                     AS RETURN_COST		\n");
        sb.append("      , SUM(A.RETURN_VAT)                      AS RETURN_VAT		\n");
        sb.append("      , SUM(A.RETURN_COST) + SUM(A.RETURN_VAT) AS RETURN_PRICE	\n");
        sb.append("      , SUM(A.BUY_QTY) - SUM(A.RETURN_QTY)     AS PURCHASE_QTY	\n");
        sb.append("      , SUM(A.BUY_COST) - SUM(A.RETURN_COST)   AS PURCHASE_COST	\n");
        sb.append("      , SUM(A.BUY_VAT) - SUM(A.RETURN_VAT)     AS PURCHASE_VAT	\n");
        sb.append("      , (SUM(A.BUY_COST) + SUM(A.BUY_VAT)) - (SUM(A.RETURN_COST) + SUM(A.RETURN_VAT)) AS PURCHASE_PRICE	    \n");
        sb.append("      ,'' AS SEQ                              \n");
        sb.append("   FROM MSDPURCHASE A		                                            \n");
        sb.append("      , TGOODS      B		                                            \n");
        sb.append("      , TGOODSDT    C		                                            \n");
        sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE								              \n");
        sb.append("    AND A.GOODS_CODE = C.GOODS_CODE								              \n");
        sb.append("    AND A.GOODSDT_CODE = C.GOODSDT_CODE								          \n");
        sb.append("    AND A.ENTP_CODE  = ?								        \n");
        sb.append("    AND A.GATHER_DATE BETWEEN TO_DATE(?,'YYYY/MM') AND ADD_MONTHS(TO_DATE(?,'YYYY/MM'),1) -1	\n");
        sb.append(" GROUP BY A.GOODS_CODE,   \n");
        sb.append(" 		 B.GOODS_NAME,  \n");
        sb.append(" 		 A.GOODSDT_CODE,  \n");
        sb.append(" 		 C.GOODSDT_INFO,    \n");
        sb.append(" 		 A.BUY_MED,    \n");
        sb.append(" 		 B.COST_TAX_YN       \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
            log.debug("fromDate  : " + retrieve.getString("fromDate"));
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Select )
    * </PRE>
    * @param
    * @return  String
    */
    private String makeSqlDt2( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT  A.PURCHASE_NO,  TO_CHAR(A.PURCHASE_TO,'YYYYMM') AS PURCHASE_TO,\n");
        sb.append("           A.FLAG,  TCODE_NAME('B076',A.FLAG) AS CODE_NAME                \n");
        sb.append("     FROM  TPURCHASE_M     A                                             \n");
        sb.append("    WHERE  (A.ENTP_CODE, A.PURCHASE_TO) = ( SELECT Z.ENTP_CODE, MAX(Z.PURCHASE_TO)       \n");
        sb.append("                                             FROM TPURCHASE_M  Z                        \n");
        sb.append("                                            WHERE   Z.ENTP_CODE = ?                     \n");
        sb.append("                                            GROUP BY  Z.ENTP_CODE )                      \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Select )
    * </PRE>
    * @param
    * @return  String
    */
    private String makeSqlDt3( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT    NVL(A.YOUBO_AMT, 0) AS  YOUBO_AMT,                                  \n");
        sb.append("             NVL(A.CREDIT_AMT, 0)  AS  CREDIT_AMT                                \n");
        sb.append("    FROM TPURCHASE_M  A                                                          \n");
        sb.append("   WHERE (A.ENTP_CODE, A.PURCHASE_TO) = ( SELECT Z.ENTP_CODE,                    \n");
        sb.append("                                                 MAX(Z.PURCHASE_TO)              \n");
        sb.append("                                           FROM  TPURCHASE_M  Z                  \n");
        sb.append("                                          WHERE  Z.ENTP_CODE = ?                 \n");
        sb.append("                                            AND  Z.PURCHASE_TO < TO_DATE(?||'/01' , 'YYYY/MM/DD')    \n");
        sb.append("                                          GROUP  BY  Z.ENTP_CODE )               \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
            log.debug("fromDate  : " + retrieve.getString("fromDate"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Select )
    * </PRE>
    * @param
    * @return  String
    */
    private String makeSqlDt4( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT  A.ENTP_CODE,    B.ENTP_NAME,                  \n");
       sb.append("            A.YOUBO_AMT,    A.CREDIT_AMT                  \n");
        sb.append("     FROM  TPURCHASE_M  A,                               \n");
        sb.append("           TENTERPRISE  B                                \n");
        sb.append("    WHERE  A.ENTP_CODE   = B.ENTP_CODE                   \n");
        sb.append("      AND  A.ENTP_CODE   = ?                             \n");
        sb.append("      AND  A.PURCHASE_TO = TO_DATE(?||'/01','yyyy/mm/dd') - 1           \n");
        sb.append("      AND ( A.YOUBO_AMT <> 0 OR A.CREDIT_AMT <> 0 )       \n");
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n"+sb.toString());
            log.debug("entp_code  : " + retrieve.getString("entp_code"));
            log.debug("fromDate  : " + retrieve.getString("fromDate"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tpurchase_m 에 등록 )
    * </PRE>
    * @param   Tpurchase_m
    * @return  String
    */

    public String makeSqlInsert(Tpurchase_m tpurchase_m) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TPURCHASE_M(  \n ");
        sb.append("         PURCHASE_NO,  \n ");
        sb.append("         ENTP_CODE,  \n ");
        sb.append("         PREV_YOUBO,  \n ");
        sb.append("         SALE_QTY,  \n ");
        sb.append("         CANCEL_QTY,  \n ");
        sb.append("         TOT_QTY,  \n ");
        sb.append("         TOT_PURCHASE_AMT,  \n ");
        sb.append("         PAY_AMT3,  \n ");
        sb.append("         YOUBO_AMT      ,  \n ");
        sb.append("         KONGJE_AMT     ,  \n ");
        sb.append("         YOUBO_MEMO   ,  \n ");
        sb.append("         KONGJE_MEMO   ,  \n ");
        sb.append("         PURCHASE_FR    ,  \n ");
        sb.append("         PURCHASE_TO    ,  \n ");
        sb.append("         PROCESS_DATE   ,  \n ");
        sb.append("         PROCESS_ID     ,  \n ");
        sb.append("         FLAG           ,  \n ");
        sb.append("         SALE_TAX_AMT   ,  \n ");
        sb.append("         SALE_NOTAX_AMT ,  \n ");
        sb.append("         SALE_VAT  )  \n ");
        sb.append("  VALUES( \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD'),  \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD'),  \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?)  \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tpurchase_dt 에 등록 )
    * </PRE>
    * @param   Tpurchase_dt
    * @return  String
    */
    private final String updateSqltpurchase_dt  =" INSERT INTO TPURCHASE_DT(  \n "
                                             +"           PURCHASE_NO,        \n "
                                             +"           GOODS_CODE,         \n "
                                             +"           GOODSDT_CODE,       \n "
                                             +"           BUY_QTY,         \n "
                                             +"           BUY_COST,           \n "
                                             +"           BUY_VAT,           \n "
                                             +"           RETURN_QTY,         \n "
                                             +"           RETURN_COST,         \n "
                                             +"           RETURN_VAT,          \n "
                                             +"           SEQ,          \n "
                                             +"           PROCESS_DATE,       \n "
                                             +"           PROCESS_ID) \n "
                                             +"     VALUES(                   \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           ?,                  \n "
                                             +"           TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                             +"           ?)                  \n ";

    private int updateSqlLogtpurchase_dt =  0;

    private String makeSqlInsert(Tpurchase_dt tpurchase_dt)  throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogtpurchase_dt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqltpurchase_dt);
            }
            updateSqlLogtpurchase_dt = 1;
        }
        return updateSqltpurchase_dt;
    }
/**
    public String makeSqlInsert(Tpurchase_dt tpurchase_dt) throws StoreException{


        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TPURCHASE_DT(  \n ");
        sb.append("         PURCHASE_NO,  \n ");
        sb.append("         GOODS_CODE,  \n ");
        sb.append("         GOODSDT_CODE,  \n ");
        sb.append("         SALE_PRICE,  \n ");
        sb.append("         SALE_QTY,  \n ");
        sb.append("         SALE_AMT,  \n ");
        sb.append("         CANCEL_QTY,  \n ");
        sb.append("         CANCEL_AMT,  \n ");
        sb.append("         PROCESS_DATE,  \n ");
        sb.append("         PROCESS_ID,  \n ");
        sb.append("         PAYDAY_GB,  \n ");
        sb.append("         SALE_COST      ,  \n ");
        sb.append("         SALE_VAT     ,  \n ");
        sb.append("         BUY_QTY   ,  \n ");
        sb.append("         BUY_AMT    ,  \n ");
        sb.append("         OUT_QTY    ,  \n ");
        sb.append("         OUT_AMT   ,  \n ");
        sb.append("         SEQ         ) \n");
        sb.append("  VALUES( \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?,  \n ");
        sb.append("         ?)  \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }
    */

    /**
    * <PRE>
    * Desc : Make SQL ( Tpurchase_m 에 등록 )
    * </PRE>
    * @param   Tpurchase_m
    * @return  String
    */

    public String makeSqlUpdate(Tpurchase_m tpurchase_m) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TPURCHASE_M  SET         \n ");
        sb.append("         YOUBO_AMT  = ?  ,        \n ");
        sb.append("         KONGJE_AMT  = ?  ,       \n ");
        sb.append("         TOT_PURCHASE_AMT  = ?  , \n ");
        sb.append("         YOUBO_MEMO  = ?     ,    \n ");
        sb.append("         KONGJE_MEMO  = ?       \n ");
        sb.append("  WHERE  PURCHASE_NO = ?          \n ");


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
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDt1(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt1(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("purchase_no"));
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_DT1", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieveDt1() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieveDt1() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveDt0(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt0(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("purchase_no"));
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_DT0", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieveDt0() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieveDt0() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveCheck(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt2(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("entp_code"));
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_CHECK", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieveDt2() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieveDt2() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveDt3(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt3(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("fromDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT3", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieveDt3() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieveDt3() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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
    public RetrieveModel retrieveDt4(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt4(retrieve));
            int index = 1;

            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("fromDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT4", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieveDt4() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieveDt4() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
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

            pstmt.setString(index++, retrieve.getString("entp_code"));
            pstmt.setString(index++, retrieve.getString("fromDate"));
            pstmt.setString(index++, retrieve.getString("fromDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[ServeEntpAccountSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ServeEntpAccountSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tpurchase_m
    * </PRE>
    * @param   Connection
    * @param   Tpurchase_m
    * @return  int
    */

    public int insert(Connection con, Tpurchase_m tpurchase_m) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpurchase_m));

            int index = 1;
            pstmt.setString(index++, tpurchase_m.getPurchase_no   ());
            pstmt.setString(index++, tpurchase_m.getEntp_code   ());
            pstmt.setDouble(index++, tpurchase_m.getPrev_youbo   ());
            pstmt.setLong  (index++, tpurchase_m.getSale_qty   ());
            pstmt.setLong  (index++, tpurchase_m.getCancel_qty   ());
            pstmt.setLong  (index++, tpurchase_m.getTot_qty   ());
            pstmt.setDouble(index++, tpurchase_m.getTot_purchase_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getPay_amt3  ());
            pstmt.setDouble(index++, tpurchase_m.getYoubo_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getKongje_amt   ());
            pstmt.setString(index++, tpurchase_m.getYoubo_memo   ());
            pstmt.setString(index++, tpurchase_m.getKongje_memo   ());
            pstmt.setString(index++, tpurchase_m.getPurchase_fr   ());
            pstmt.setString(index++, tpurchase_m.getPurchase_to   ());
            pstmt.setString(index++, tpurchase_m.getProcess_date   ());
            pstmt.setString(index++, tpurchase_m.getProcess_id   ());
            pstmt.setString(index++, tpurchase_m.getFlag   ());
            pstmt.setDouble(index++, tpurchase_m.getSale_tax_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getSale_notax_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getSale_vat   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpurchase_m.getPurchase_no      ()); logString.append( "/" );
            logString.append( tpurchase_m.getEntp_code        ()); logString.append( "/" );
            logString.append( tpurchase_m.getPrev_youbo       ()); logString.append( "/" );
            logString.append( tpurchase_m.getSale_qty         ()); logString.append( "/" );
            logString.append( tpurchase_m.getCancel_qty       ()); logString.append( "/" );
            logString.append( tpurchase_m.getTot_qty          ()); logString.append( "/" );
            logString.append( tpurchase_m.getTot_purchase_amt ()); logString.append( "/" );
            logString.append( tpurchase_m.getPay_amt3 		  ()); logString.append( "/" );
            logString.append( tpurchase_m.getYoubo_amt        ()); logString.append( "/" );
            logString.append( tpurchase_m.getKongje_amt       ()); logString.append( "/" );
            logString.append( tpurchase_m.getYoubo_memo       ()); logString.append( "/" );
            logString.append( tpurchase_m.getKongje_memo      ()); logString.append( "/" );
            logString.append( tpurchase_m.getPurchase_fr      ()); logString.append( "/" );
            logString.append( tpurchase_m.getPurchase_to      ()); logString.append( "/" );
            logString.append( tpurchase_m.getProcess_date     ()); logString.append( "/" );
            logString.append( tpurchase_m.getProcess_id       ()); logString.append( "/" );
            logString.append( tpurchase_m.getFlag             ()); logString.append( "/" );
            logString.append( tpurchase_m.getSale_tax_amt     ()); logString.append( "/" );
            logString.append( tpurchase_m.getSale_notax_amt   ()); logString.append( "/" );
            logString.append( tpurchase_m.getSale_vat         ()); logString.append( "/" );


            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ServeEntpAccountSvc.insert(Tpurchase_m) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ServeEntpAccountSvc.insert(Tpurchase_m) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tpurchase_m
    * </PRE>
    * @param   Connection
    * @param   Tpurchase_m
    * @return  int
    */

    public int insert(Connection con, Tpurchase_dt tpurchase_dt) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tpurchase_dt));

            int index = 1;
            pstmt.setString	(index++, tpurchase_dt.getPurchase_no   ());
            pstmt.setString	(index++, tpurchase_dt.getGoods_code   ());
            pstmt.setString	(index++, tpurchase_dt.getGoodsdt_code   ());
            pstmt.setLong	(index++, tpurchase_dt.getBuy_qty   ());
            pstmt.setDouble (index++, tpurchase_dt.getBuy_cost   ());
            pstmt.setDouble	(index++, tpurchase_dt.getBuy_vat   ());
            pstmt.setLong  	(index++, tpurchase_dt.getReturn_qty  ());
            pstmt.setDouble	(index++, tpurchase_dt.getReturn_cost   ());
            pstmt.setDouble	(index++, tpurchase_dt.getReturn_vat   ());
            pstmt.setString	(index++, tpurchase_dt.getSeq   ());
            pstmt.setString	(index++, tpurchase_dt.getProcess_date   ());
            pstmt.setString	(index++, tpurchase_dt.getProcess_id   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpurchase_dt.getPurchase_no      ()); logString.append( "/" );
            logString.append( tpurchase_dt.getGoods_code        ()); logString.append( "/" );
            logString.append( tpurchase_dt.getGoodsdt_code       ()); logString.append( "/" );
            logString.append( tpurchase_dt.getBuy_qty         ()); logString.append( "/" );
            logString.append( tpurchase_dt.getBuy_cost         ()); logString.append( "/" );
            logString.append( tpurchase_dt.getBuy_vat       ()); logString.append( "/" );
            logString.append( tpurchase_dt.getReturn_qty       ()); logString.append( "/" );
            logString.append( tpurchase_dt.getReturn_cost          ()); logString.append( "/" );
            logString.append( tpurchase_dt.getReturn_vat        ()); logString.append( "/" );
            logString.append( tpurchase_dt.getSeq       ()); logString.append( "/" );
            logString.append( tpurchase_dt.getProcess_date ()); logString.append( "/" );
            logString.append( tpurchase_dt.getProcess_id          ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ServeEntpAccountSvc.insert(Tpurchase_dt) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ServeEntpAccountSvc.insert(Tpurchase_dt) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tpurchase_m
    * </PRE>
    * @param   Connection
    * @param   Tpurchase_m
    * @return  int
    */

    public int update(Connection con, Tpurchase_m tpurchase_m) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tpurchase_m));

            int index = 1;
            pstmt.setDouble(index++, tpurchase_m.getYoubo_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getKongje_amt   ());
            pstmt.setDouble(index++, tpurchase_m.getTot_purchase_amt());
            pstmt.setString(index++, tpurchase_m.getYoubo_memo   ());
            pstmt.setString(index++, tpurchase_m.getKongje_memo   ());
            pstmt.setString(index++, tpurchase_m.getPurchase_no   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpurchase_m.getYoubo_amt        ()); logString.append( "/" );
            logString.append( tpurchase_m.getKongje_amt       ()); logString.append( "/" );
            logString.append( tpurchase_m.getTot_purchase_amt       ()); logString.append( "/" );
            logString.append( tpurchase_m.getYoubo_memo       ()); logString.append( "/" );
            logString.append( tpurchase_m.getKongje_memo      ()); logString.append( "/" );
            logString.append( tpurchase_m.getPurchase_no      ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[ServeEntpAccountSvc.update(Tpurchase_m) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[ServeEntpAccountSvc.update(Tpurchase_m) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}
