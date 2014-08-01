
package com.cware.back.service.common;

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

/**
 * Register MD Service class
 *
 * @version 1.0, 2006/07/13
 */
public class PopExpectedProfitSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopExpectedProfitSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT GD.GOODS_CODE, GD.GOODS_NAME,																																	\n");
        sb.append("        MD.MD_CODE, MD.MD_NAME,                                                                        \n");
        sb.append("        GK.LMSD_CODE,                                                                                  \n");
        sb.append("        GK.LGROUP_NAME||' '||GK.MGROUP_NAME||' '||GK.SGROUP_NAME||' '||GK.DGROUP_NAME AS LMSD_NAME,    \n");
        sb.append("        TCODE_NAME('B021', GD.DELY_TYPE) AS DELY_TYPE_NAME,                                            \n");
        sb.append("        GD.DIRECT_RETURN_YN,                                                                           \n");
        sb.append("        CR.CHANGE_RATE,                                                                                \n");
        sb.append("        GP.SALE_PRICE - GP.SALE_VAT AS SALE_COST,                                                      \n");
        sb.append("        GP.BUY_COST,                                                                                   \n");
        sb.append("        GP.SALE_PRICE - GP.SALE_VAT - GP.BUY_COST AS PROFIT                                            \n");
        sb.append(" FROM  TGOODS      GD,                                                                                 \n");
        sb.append("       TMD         MD,                                                                                 \n");
        sb.append("       TGOODSKINDS GK,                                                                                 \n");
        sb.append("       TGOODSPRICE GP,                                                                                 \n");
        sb.append("       TSMLMSD_CHANGE_RATE CR                                                                          \n");
        sb.append(" WHERE GD.GOODS_CODE = GP.GOODS_CODE                                                                   \n");
        sb.append(" AND   GD.MD_CODE    = MD.MD_CODE                                                                      \n");
        sb.append(" AND   GD.LMSD_CODE  = GK.LMSD_CODE                                                                    \n");
        sb.append(" AND   GP.ROWID      = (SELECT /*+ INDEX_DESC ( GP2 PK_TGOODSPRICE)*/                                  \n");
        sb.append("                               ROWID                                                                   \n");
        sb.append("                        FROM  TGOODSPRICE GP2                                                          \n");
        sb.append("                        WHERE GOODS_CODE = GP.GOODS_CODE                                               \n");
        sb.append("                        AND   APPLY_DATE <= SYSDATE                                                    \n");
        sb.append("                        AND   ROWNUM      = 1)                                                         \n");
        sb.append(" AND   GD.LMSD_CODE   = CR.LMSD_CODE                                                                   \n");
        sb.append(" AND   CR.YYMM        = TO_CHAR(ADD_MONTHS(SYSDATE,-1),'YYYYMM')                                       \n");
        sb.append(" AND   GD.GOODS_CODE  = ?                                                                              \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT   C.CODE_NAME,																														\n"); 
        sb.append("          DECODE(DC.COST_FLAG,'2', ?*DC.COST_VALUE/100, DC.COST_VALUE) COST_AMT  \n");
        sb.append(" FROM     TCODE       C,                                                         \n");
        sb.append("          TDIRECTCOST DC                                                         \n");
        sb.append(" WHERE    C.CODE_LGROUP = 'A004'                                                 \n");
        sb.append(" AND      C.CODE_MGROUP = DC.COST_CODE                                           \n");
        sb.append(" AND      DC.YYMM        = TO_CHAR(SYSDATE,'YYYYMM')                             \n");
        sb.append(" ORDER BY C.REMARK                                                               \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
    * Desc : Retrieve SQL ; Master
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
            pstmt.setString(index++,retrieve.getString("goods_code"));

            rset  = pstmt.executeQuery();
            
            retrieve.put("RESULT1"		,makeSheet(rset));
            
        }catch(SQLException se){
            log.error("[PopExpectedProfitSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PopExpectedProfitSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result ; Detail
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDetail(ResultSet rset)  throws Exception {
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
    * Desc : Retrieve SQL ; Detail
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDetail(retrieve));

            int index = 1;
            pstmt.setDouble(index++,retrieve.getDouble("sale_cost"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT2",makeSheetDetail(rset));

        }catch(SQLException se){
            log.error("[PopExpectedProfitSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PopExpectedProfitSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}
