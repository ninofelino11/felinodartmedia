
package com.cware.back.service.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.cware.back.entity.table.Tgoodsimage;


/**
 * Goods Image List Service class
 *
 * @version 1.0, 2006/07/04
 */
public class GoodsImageBatchSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsImageBatchSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Goods Image List select
    * </PRE>
    * @param   poolName                           : Database pool name
    * @param   entp_code                          : 업체코드
    * @param   close_yn                           : 거래여부
    */

    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        long    categoryCode = 0;
        long    modRet1   = 0;
        long    modRet2   = 0;
        long    modRet3   = 0;
        long    divideRet = 0;
        String  data1     = "";

        categoryCode = retrieve.getLong("category_code");
        modRet1      = categoryCode%10000000;
        modRet2      = categoryCode%100000;
        modRet3      = categoryCode%1000;

        sb.append("SELECT GOODS.GOODS_CODE                 \n");
        sb.append("     , GOODS.GOODS_NAME                 \n");
        sb.append("     , GOODS.SALE_GB                    \n");
        sb.append("     , GOODSIMAGE.IMAGE_L               \n");
        sb.append("     , GOODSIMAGE.IMAGE_M               \n");
        sb.append("     , GOODSIMAGE.IMAGE_D               \n");
        sb.append("     , GOODSIMAGE.IMAGE_S               \n");
        sb.append("     , GOODSIMAGE.IMAGE_SS              \n");
        sb.append("     , GOODSIMAGE.IMAGE_URL             \n");
        sb.append("  FROM TGOODS         GOODS             \n");
        sb.append("     , TGOODSIMAGE    GOODSIMAGE        \n");
        sb.append(" WHERE GOODS.GOODS_CODE         = GOODSIMAGE.GOODS_CODE(+)      \n");
        sb.append("   AND GOODSIMAGE.IMAGE_NO(+)   = '01'      \n");
        sb.append("   AND GOODS.INSERT_DATE >= TO_DATE('" + retrieve.getString("fromDate") + "', 'YYYY/MM/DD')      \n");
        sb.append("   AND GOODS.INSERT_DATE <  TO_DATE('" + retrieve.getString("toDate") + "', 'YYYY/MM/DD') + 1     \n");
        sb.append("   AND GOODS.SALE_GB = '00'                  \n");

        // goods_code
        if(!retrieve.getString("goods_code").equals("")){
            sb.append("   AND GOODS.GOODS_CODE = '" + retrieve.getString("goods_code") + "' \n");
        }
        sb.append("   ORDER BY LPAD(GOODS.GOODS_CODE, 10, '0') \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement       stmt       = null;
        ResultSet       rset       = null;

        try {
            stmt = con.createStatement();

            rset  = stmt.executeQuery(makeSql(retrieve));

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return retrieve;
    }

    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   retrieve
    * @return  String
    */
    public String makeSqlGoodsCheck( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(GOODS_CODE) AS GOODS_CNT   	\n");
        sb.append("   FROM TGOODSIMAGE 				          	\n");
        sb.append("  WHERE GOODS_CODE = ? 			            \n");
        sb.append("    AND IMAGE_NO   = ? 			            \n");

        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Check GoodsCode
     * </PRE>
     * @param   con
     * @param   retrieve
     * @return  int
     */
     public int getImageCount(Connection con, RetrieveModel retrieve) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
         	pstmt = con.prepareStatement(makeSqlGoodsCheck(retrieve));

             int index = 1;
             pstmt.setString(index++, retrieve.getString("goods_code"));
             pstmt.setString(index++, retrieve.getString("image_no"));

             rset = pstmt.executeQuery();

             if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
             executedRtn = rset.getInt(1);

         }catch(SQLException se){
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return executedRtn;
     }

     /**
     * <PRE>
     * Desc : Make SQL ( Insert  )
     * </PRE>
     * @return  String
     */

     private final String insertSql = " INSERT INTO TGOODSIMAGE ( \n "
                                     +"         GOODS_CODE, \n "
                                     +"         IMAGE_NO, \n "
                                     +"         IMAGE_L, \n "
                                     +"         IMAGE_M, \n "
                                     +"         IMAGE_S, \n "
                                     +"         IMAGE_D, \n "
                                     +"         IMAGE_SS, \n "
                                     +"         IMAGE_URL, \n "
                                     +"         INSERT_DATE, \n "
                                     +"         INSERT_ID, \n "
                                     +"         MODIFY_DATE, \n "
                                     +"         MODIFY_ID ) \n "
                                     +" VALUES( \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         ?, \n "
                                     +"         SYSDATE, \n "
                                     +"         ?, \n "
                                     +"         SYSDATE, \n "
                                     +"         ? ) \n ";
     private int insertSqlLog =  0;

     public String makeSqlInsert() throws StoreException{

         if (insertSqlLog == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(insertSql);
             }
             insertSqlLog = 1;
         }
         return insertSql;
     }

     /**
     * <PRE>
     * Desc : Insert
     * </PRE>
     * @param   Connection
     * @param   Tgoodsimage
     * @return  int
     */
     public int insert(Connection con, Tgoodsimage tgoodsimage) throws StoreException{
         PreparedStatement pstmt       = null;
         int executedRtn = 0;

         try {

         	pstmt = con.prepareStatement(makeSqlInsert());
            int index = 1;

            pstmt.setString(index++, tgoodsimage.getGoods_code());
            pstmt.setString(index++, tgoodsimage.getImage_no());
            pstmt.setString(index++, tgoodsimage.getImage_l());
            pstmt.setString(index++, tgoodsimage.getImage_m());
            pstmt.setString(index++, tgoodsimage.getImage_s());
            pstmt.setString(index++, tgoodsimage.getImage_d());
            pstmt.setString(index++, tgoodsimage.getImage_ss());
            pstmt.setString(index++, tgoodsimage.getImage_url());
            pstmt.setString(index++, tgoodsimage.getInsert_id());
            pstmt.setString(index++, tgoodsimage.getModify_id());

            executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("[GoodsImageBatchSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[GoodsImageBatchSvc.insert() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
         	DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }

     /**
     * <PRE>
     * Desc : Make SQL ( Update )
     * </PRE>
     * @return  String
     */

     private final String updateSql =  " UPDATE TGOODSIMAGE SET \n "
         							  +"        IMAGE_L     = ?, \n "
         							  +"        IMAGE_M     = ?, \n "
         							  +"        IMAGE_S     = ?, \n "
         							  +"        IMAGE_D     = ?, \n "
         							  +"        IMAGE_SS    = ?, \n "
         							  +"        IMAGE_URL   = ?, \n "
                                      +"        MODIFY_DATE = SYSDATE, \n "
                                      +"        MODIFY_ID   = ? \n "
                                      +"  WHERE GOODS_CODE  = ? \n "
                                      +"    AND IMAGE_NO    = ? \n ";
     private int updateSqlLog =  0;

     public String makeSqlUpdate() throws StoreException {
         if (updateSqlLog == 0) {
             if (logSave.isDebugEnabled()) {
                 logSave.debug(updateSql);
             }
             updateSqlLog = 1;
         }
         return updateSql;
     }

     /**
     * <PRE>
     * Desc : Update
     * </PRE>
     * @param   Connection
     * @param   Tgoodsimage
     * @return  int
     */
     public int update(Connection con, Tgoodsimage tgoodsimage) throws StoreException{
         PreparedStatement pstmt      = null;
         int executedRtn = 0;

         try {
        	 pstmt = con.prepareStatement(makeSqlUpdate());
             int index = 1;

             pstmt.setString(index++, tgoodsimage.getImage_l());
             pstmt.setString(index++, tgoodsimage.getImage_m());
             pstmt.setString(index++, tgoodsimage.getImage_s());
             pstmt.setString(index++, tgoodsimage.getImage_d());
             pstmt.setString(index++, tgoodsimage.getImage_ss());
             pstmt.setString(index++, tgoodsimage.getImage_url());
             pstmt.setString(index++, tgoodsimage.getModify_id());
             pstmt.setString(index++, tgoodsimage.getGoods_code());
             pstmt.setString(index++, tgoodsimage.getImage_no());

             executedRtn = pstmt.executeUpdate();

         }catch(SQLException se){
             logSave.error("[GoodsImageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[GoodsImageSvc.update() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
         	DBUtils.freeConnection(null, pstmt, null);
         }
         return executedRtn;
     }
}
