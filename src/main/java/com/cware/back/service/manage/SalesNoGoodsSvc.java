
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
import com.cware.back.entity.table.Tsalenogoods;


/**
 * Register SalesNoGoods Service class
 *
 * @version 1.0, 2006/08/25
 */
public class SalesNoGoodsSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public SalesNoGoodsSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Tgoods
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT B.GOODS_CODE,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         A.SET_YN,  \n");
        sb.append("         B.GOODSDT_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         B.GOODSDT_INFO,  \n");
        sb.append("         '' SALE_NO_SEQ,  \n");
        sb.append("         B.SALE_GB,  \n");
        sb.append("         '' SALE_NO_CODE,  \n");
        sb.append("         '' SALE_NO_NOTE,  \n");
        sb.append("         TO_CHAR(B.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("         B.MODIFY_ID,  \n");
        sb.append("         '0' AS CHECK_YN  \n");
        sb.append("    FROM TGOODS   A,  \n");
        sb.append("         TGOODSDT B  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND ( ( A.SET_YN = '0' AND B.GOODSDT_CODE > '000' )  \n");
        sb.append("      OR   ( A.SET_YN = '1' AND B.GOODSDT_CODE = '000' ) )  \n");
        sb.append("     AND A.MD_CODE    LIKE ?||'%'  \n");
        sb.append("     AND A.ENTP_CODE  LIKE ?||'%'  \n");
        sb.append("     AND A.GOODS_CODE LIKE ?||'%'  \n");
        sb.append("     AND A.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')  \n");
        sb.append("     AND A.INSERT_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1  \n");
        sb.append("   ORDER BY B.GOODS_CODE,  \n");
        sb.append("            B.GOODSDT_CODE  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ; tsalenogoods
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSaleNoGoods( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT /*+ RULE */ A.GOODS_CODE,  \n");
        sb.append("         C.GOODS_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         C.SET_YN,  \n");
        sb.append("         A.GOODSDT_CODE,  \n");
        sb.append("         B.GOODSDT_INFO,  \n");
        sb.append("         A.SALE_NO_SEQ,  \n");
        sb.append("         A.SALE_GB,  \n");
        sb.append("         A.SALE_NO_CODE,  \n");
        sb.append("         A.SALE_NO_NOTE,  \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("         D.USER_NAME  \n");
        sb.append("    FROM TSALENOGOODS A,  \n");
        sb.append("         TGOODSDT     B,  \n");
        sb.append("         TGOODS       C,  \n");
        sb.append("         VUSER        D  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODSDT_CODE = B.GOODSDT_CODE  \n");
        sb.append("     AND A.GOODS_CODE = C.GOODS_CODE  \n");
        sb.append("     AND A.INSERT_ID  = D.USER_ID  \n");
        sb.append("     AND ( ( C.SET_YN = '0' AND B.GOODSDT_CODE > '000' )  \n");
        sb.append("      OR   ( C.SET_YN = '1' AND B.GOODSDT_CODE = '000' ) )  \n");
        sb.append("     AND C.MD_CODE    LIKE ?||'%'  \n");
        sb.append("     AND C.ENTP_CODE  LIKE ?||'%'  \n");
        sb.append("     AND C.GOODS_CODE LIKE ?||'%'  \n");
        sb.append("     AND A.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD')  \n");
        sb.append("     AND A.INSERT_DATE < TO_DATE(?, 'YYYY/MM/DD') + 1  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ; tgoodsdt.sale_gb
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSaleGb() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT MIN(B.SALE_GB) AS SALE_GB  \n");
        sb.append("    FROM TGOODS   A, \n");
        sb.append("         TGOODSDT B  \n");
        sb.append("   WHERE A.GOODS_CODE  = B.GOODS_CODE  \n");
        sb.append("     AND A.GOODS_CODE = ?  \n");
        sb.append("     AND ( ( A.SET_YN = '0' AND B.GOODSDT_CODE > '000' )  \n");
        sb.append("      OR   ( A.SET_YN = '1' AND B.GOODSDT_CODE = '000' ) )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ; tsetgoods
     * </PRE>
     * @param   RetrieveModel
     * @return  String
     */
     private String makeSqlSetgoods() throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT A.GOODS_CODE AS SET_GOODS_CODE, \n");
         sb.append("       B.GOODSDT_CODE AS SET_GOODSDT_CODE, \n");
         sb.append("       MAX(C.SALE_GB) AS SALE_GB   \n");
         sb.append("  FROM TSETGOODS A,   \n");
         sb.append("	   TGOODSDT B,   \n");
         sb.append("	   TGOODS C   \n");
         sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
         sb.append("   AND A.IN_GOODS_CODE = C.GOODS_CODE   \n");
         sb.append("   AND A.GOODS_CODE IN (SELECT GOODS_CODE    \n");
         sb.append("                          FROM TSETGOODS    \n");
         sb.append("                         WHERE IN_GOODS_CODE = ?)   \n");
         sb.append(" GROUP BY A.GOODS_CODE,   \n");
         sb.append("          B.GOODSDT_CODE   \n");
         sb.append(" ORDER BY A.GOODS_CODE,   \n");
         sb.append("          B.GOODSDT_CODE   \n");

         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug(sb.toString());
         }
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : Make SQL ; TCATEGORYGOODS
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCgCnt() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT  COUNT(*) AS CNT  \n");
        sb.append("    FROM  TCATEGORYGOODS  \n");
        sb.append("   WHERE  DISPLAY_YN = '1'  \n");
        sb.append("     AND  GOODS_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ; STCATEGORYGOODS
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlScgCnt() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT  COUNT(*) AS CNT  \n");
        sb.append("    FROM  STCATEGORYGOODS  \n");
        sb.append("   WHERE  DISPLAY_YN = '1'  \n");
        sb.append("     AND  GOODS_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ; TCATEGORYGOODS
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlCgList() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE  \n");
        sb.append("    FROM TCATEGORYGOODS  \n");
        sb.append("   WHERE DISPLAY_YN  = '1'  \n");
        sb.append("     AND GOODS_CODE  = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ; STCATEGORYGOODS
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlScgList() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE  \n");
        sb.append("    FROM STCATEGORYGOODS  \n");
        sb.append("   WHERE DISPLAY_YN  = '1'  \n");
        sb.append("     AND GOODS_CODE  = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert Tsalenogoods )
    * </PRE>
    * @param   Tsalenogoods
    * @return  String
    */
    private final String insertSqlTsalenogoods =  " INSERT INTO TSALENOGOODS ( \n "
                                                 +"        GOODS_CODE,    \n "
                                                 +"        GOODSDT_CODE,  \n "
                                                 +"        SALE_NO_SEQ,   \n "
                                                 +"        SALE_GB,       \n "
                                                 +"        SALE_NO_CODE,  \n "
                                                 +"        SALE_NO_NOTE,  \n "
                                                 +"        INSERT_DATE,   \n "
                                                 +"        INSERT_ID)     \n "
                                                 +"VALUES ( \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        ?, \n "
                                                 +"        TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                                 +"        ? ) \n ";

    private int insertSqlLogTsalenogoods =  0;

    private String makeSqlInsertTsalenogoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (insertSqlLogTsalenogoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTsalenogoods);
            }
            insertSqlLogTsalenogoods = 1;
        }
        return insertSqlTsalenogoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODS )
    * </PRE>
    * @param   Tgoods
    * @return  String
    */
    private final String updateSqlTgoods = "  UPDATE TGOODS SET \n "
                                          + "         SALE_GB     = ?, \n "
                                          + "         MODIFY_DATE = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                          + "         MODIFY_ID   = ? \n "
                                          + "   WHERE GOODS_CODE  = ? \n ";

    private int updateSqlLogTgoods =  0;

    private String makeSqlUpdateTgoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTgoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTgoods);
            }
            updateSqlLogTgoods = 1;
        }
        return updateSqlTgoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update TGOODSDT )
    * </PRE>
    * @param   Tgoodsdt
    * @return  String
    */
     private final String updateSqlTgoodsdt = "  UPDATE TGOODSDT SET \n "
                                            + "         SALE_GB      = ?, \n "
                                            + "         MODIFY_DATE  = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n "
                                            + "         MODIFY_ID    = ? \n "
                                            + "   WHERE GOODS_CODE   = ? \n "
                                            + "     AND GOODSDT_CODE = ? \n ";

    private int updateSqlLogTgoodsdt =  0;

    private String makeSqlUpdateTgoodsdt() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTgoodsdt == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTgoodsdt);
            }
            updateSqlLogTgoodsdt = 1;
        }
        return updateSqlTgoodsdt;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Tcategory )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */
     private final String updateSqlTcategory = " "
                                            + "   UPDATE TCATEGORY  \n "
                                            + "      SET DISPLAY_GOODS_CNT = DISPLAY_GOODS_CNT - 1  \n "
                                            + "    WHERE CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n "
                                            + "                               FROM TCATEGORY  \n "
                                            + "                            CONNECT BY  \n "
                                            + "                              PRIOR P_CATEGORY_CODE = CATEGORY_CODE  \n "
                                            + "                              START WITH CATEGORY_CODE = ?  \n "
                                            + "                            )  \n ";

    private int updateSqlLogTcategory =  0;

    private String makeSqlUpdateTcategory() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTcategory == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTcategory);
            }
            updateSqlLogTcategory = 1;
        }
        return updateSqlTcategory;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Tcategorygoods )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */
     private final String updateSqlTcategorygoods = " "
                                            + "   UPDATE TCATEGORYGOODS  \n "
                                            + "      SET DISPLAY_YN = '0',  \n "
                                            + "          MODIFY_ID  = ?,  \n "
                                            + "          MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n "
                                            + "    WHERE GOODS_CODE  = ?  ";

    private int updateSqlLogTcategorygoods =  0;

    private String makeSqlUpdateTcategorygoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTcategorygoods);
            }
            updateSqlLogTcategorygoods = 1;
        }
        return updateSqlTcategorygoods;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Stcategorygoods )
    * </PRE>
    * @param   stcategorygoods
    * @return  String
    */
     private final String updateSqlStcategory = " "
                                            + "   UPDATE STCATEGORY  \n "
                                            + "      SET DISPLAY_GOODS_CNT = DISPLAY_GOODS_CNT - 1  \n "
                                            + "    WHERE CATEGORY_CODE IN ( SELECT CATEGORY_CODE  \n "
                                            + "                               FROM STCATEGORY  \n "
                                            + "                            CONNECT BY  \n "
                                            + "                              PRIOR P_CATEGORY_CODE = CATEGORY_CODE  \n "
                                            + "                              START WITH CATEGORY_CODE = ?  \n "
                                            + "                            )  \n ";

    private int updateSqlLogStcategory =  0;

    private String makeSqlUpdateStcategory() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogStcategory == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlStcategory);
            }
            updateSqlLogStcategory = 1;
        }
        return updateSqlStcategory;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Stcategorygoods )
    * </PRE>
    * @param   Stcategorygoods
    * @return  String
    */
     private final String updateSqlStcategorygoods = " "
                                            + "   UPDATE STCATEGORYGOODS  \n "
                                            + "      SET DISPLAY_YN = '0',  \n "
                                            + "          MODIFY_ID  = ?,  \n "
                                            + "          MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n "
                                            + "    WHERE GOODS_CODE  = ?  ";

    private int updateSqlLogStcategorygoods =  0;

    private String makeSqlUpdateStcategorygoods() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogStcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlStcategorygoods);
            }
            updateSqlLogStcategorygoods = 1;
        }
        return updateSqlStcategorygoods;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Tpresentation )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */
     private final String updateSqlTpresentation = " "
                                                 + "   UPDATE TPRESENTATION  \n "
                                                 + "      SET DISPLAY_YN = '0',  \n "
                                                 + "          MODIFY_ID  = ?,  \n "
                                                 + "          MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n "
                                                 + "    WHERE GOODS_CODE  = ?  ";

    private int updateSqlLogTpresentation =  0;

    private String makeSqlUpdateTpresentation() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlTpresentation);
            }
            updateSqlLogTpresentation = 1;
        }
        return updateSqlTpresentation;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Update Stpresentation )
    * </PRE>
    * @param   stpresentation
    * @return  String
    */
     private final String updateSqlStpresentation = " "
                                                 + "   UPDATE STPRESENTATION  \n "
                                                 + "      SET DISPLAY_YN = '0',  \n "
                                                 + "          MODIFY_ID  = ?,  \n "
                                                 + "          MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')  \n "
                                                 + "    WHERE GOODS_CODE  = ?  ";

    private int updateSqlLogStpresentation =  0;

    private String makeSqlUpdateStpresentation() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlLogStpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlStpresentation);
            }
            updateSqlLogStpresentation = 1;
        }
        return updateSqlStpresentation;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result : Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve SQL ;
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
            pstmt.setString(index++,retrieve.getString("md_code"));
            pstmt.setString(index++,retrieve.getString("entp_code"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ; SaleNoGoods list
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveSaleNoGoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlSaleNoGoods(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("md_code"));
            pstmt.setString(index++,retrieve.getString("entp_code"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_SALENOGOODS",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
    * <PRE>
    * Desc : select sale_gb
    * </PRE>
    * @param   Connection
    * @param
    * @return  String
    */

    public String getSaleGb(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            sale_gb     = "";

        try {
            pstmt = con.prepareStatement(makeSqlSaleGb());

            int index = 1;
            pstmt.setString(index++,  goods_code);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                sale_gb = rset.getString(1);
            }else{
                throw new StoreException("Can not retrieve SALE_GB");
            }

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return sale_gb;
    }

    /**
     * <PRE>
     * Desc : select Setgoods sale_gb
     * </PRE>
     * @param   Connection
     * @param
     * @return  String
     */

     public RetrieveModel getSetgoods(Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         String            sale_gb     = "";

         try {
             pstmt = con.prepareStatement(makeSqlSetgoods());

             int index = 1;
             pstmt.setString(index++,  retrieve.getString("goods_code"));
//           = log SQL -------------------------------------------------
             if (log.isDebugEnabled()) {
                 log.debug("goods_code : " + retrieve.getString("goods_code"));
             }
             rset  = pstmt.executeQuery();

             retrieve.put("RESULT_SETGOODS",makeSheet(rset));

         }catch(SQLException se){
             log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return retrieve;
     }

    /**
    * <PRE>
    * Desc : select count
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */

    public int getCgCnt(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               cnt         = 0;

        try {
            pstmt = con.prepareStatement(makeSqlCgCnt());

            int index = 1;
            pstmt.setString(index++,  goods_code);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                cnt = rset.getInt(1);
            }

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return cnt;
    }


    /**
    * <PRE>
    * Desc : select category_code
    * </PRE>
    * @param   Connection
    * @param
    * @return  String
    */

    public RetrieveModel retrieveCgList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            sale_gb     = "";

        try {
            pstmt = con.prepareStatement(makeSqlCgList());

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_CGLIST",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    /**
    * <PRE>
    * Desc : select count
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */

    public int getScgCnt(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               cnt         = 0;

        try {
            pstmt = con.prepareStatement(makeSqlScgCnt());

            int index = 1;
            pstmt.setString(index++,  goods_code);

            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()){
                cnt = rset.getInt(1);
            }

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return cnt;
    }


    /**
    * <PRE>
    * Desc : select category_code
    * </PRE>
    * @param   Connection
    * @param
    * @return  String
    */

    public RetrieveModel retrieveScgList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        String            sale_gb     = "";

        try {
            pstmt = con.prepareStatement(makeSqlScgList());

            int index = 1;
            pstmt.setString(index++, retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_SCGLIST",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SalesNoGoodsSvc.getProcDate() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SalesNoGoodsSvc.getProcDate() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TSALENOGOODS
    * </PRE>
    * @param   Connection
    * @param   Tsalenogoods
    * @return  int
    */
    public int insert(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsertTsalenogoods());
            int index = 1;

            pstmt.setString(index++,tsalenogoods.getGoods_code());
            pstmt.setString(index++,tsalenogoods.getGoodsdt_code());
            pstmt.setString(index++,tsalenogoods.getSale_no_seq());
            pstmt.setString(index++,tsalenogoods.getSale_gb());
            pstmt.setString(index++,tsalenogoods.getSale_no_code());
            pstmt.setString(index++,tsalenogoods.getSale_no_note());
            pstmt.setString(index++,tsalenogoods.getInsert_date());
            pstmt.setString(index++,tsalenogoods.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getGoods_code()   ); logString.append( "/" );
            logString.append( tsalenogoods.getGoodsdt_code() ); logString.append( "/" );
            logString.append( tsalenogoods.getSale_no_seq()  ); logString.append( "/" );
            logString.append( tsalenogoods.getSale_gb()      ); logString.append( "/" );
            logString.append( tsalenogoods.getSale_no_code() ); logString.append( "/" );
            logString.append( tsalenogoods.getSale_no_note() ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date()  ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_id()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TSALENOGOODS
    * </PRE>
    * @param   Connection
    * @param   Tsalenogoods
    * @return  int
    */
    public int updateTgoods(Connection con, Tsalenogoods tsalenogoods, String sale_gb_goods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTgoods());
            int index = 1;
            pstmt.setString(index++,sale_gb_goods);
            pstmt.setString(index++,tsalenogoods.getInsert_date()); //Modify_date
            pstmt.setString(index++,tsalenogoods.getInsert_id());
            pstmt.setString(index++,tsalenogoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( sale_gb_goods                  ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date()  ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_id()    ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()   ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update(tgoods) SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update(tgoods) Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TGOODSDT
    * </PRE>
    * @param   Connection
    * @param   Tgoodsdt
    * @return  int
    */
    public int updateTgoodsdt(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTgoodsdt());
            int index = 1;
            pstmt.setString(index++,tsalenogoods.getSale_gb());
            pstmt.setString(index++,tsalenogoods.getInsert_date());  //Modify_date
            pstmt.setString(index++,tsalenogoods.getInsert_id());
            pstmt.setString(index++,tsalenogoods.getGoods_code());
            pstmt.setString(index++,tsalenogoods.getGoodsdt_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getSale_gb()      ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date()  ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_id()    ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()   ); logString.append( "/" );
            logString.append( tsalenogoods.getGoodsdt_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TCATEGORY
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateTcategory(Connection con, String category_code) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTcategory());
            int index = 1;
            pstmt.setString(index++, category_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( category_code ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TCATEGORYGOODS
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateTcategorygoods(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTcategorygoods());
            int index = 1;
            pstmt.setString(index++, tsalenogoods.getInsert_id()  );
            pstmt.setString(index++, tsalenogoods.getInsert_date());
            pstmt.setString(index++, tsalenogoods.getGoods_code() );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getInsert_id()   ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date() ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TCATEGORYGOODS
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateTpresentation(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateTpresentation());
            int index = 1;
            pstmt.setString(index++, tsalenogoods.getInsert_id()  );
            pstmt.setString(index++, tsalenogoods.getInsert_date());
            pstmt.setString(index++, tsalenogoods.getGoods_code() );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getInsert_id()   ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date() ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update STCATEGORY
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateStcategory(Connection con, String category_code) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateStcategory());
            int index = 1;
            pstmt.setString(index++, category_code);

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( category_code ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update STCATEGORYGOODS
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateStcategorygoods(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateStcategorygoods());
            int index = 1;
            pstmt.setString(index++, tsalenogoods.getInsert_id()  );
            pstmt.setString(index++, tsalenogoods.getInsert_date());
            pstmt.setString(index++, tsalenogoods.getGoods_code() );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getInsert_id()   ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date() ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update STPRESENTATION
    * </PRE>
    * @param   Connection
    * @param
    * @return  int
    */
    public int updateStpresentation(Connection con, Tsalenogoods tsalenogoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateStpresentation());
            int index = 1;
            pstmt.setString(index++, tsalenogoods.getInsert_id()  );
            pstmt.setString(index++, tsalenogoods.getInsert_date());
            pstmt.setString(index++, tsalenogoods.getGoods_code() );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tsalenogoods.getInsert_id()   ); logString.append( "/" );
            logString.append( tsalenogoods.getInsert_date() ); logString.append( "/" );
            logString.append( tsalenogoods.getGoods_code()  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[SalesNoGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[SalesNoGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

}
