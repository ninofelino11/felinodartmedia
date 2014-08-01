
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
import com.cware.back.entity.table.Tbeforeqam;

/**
 * Register TBEFOREQAM Service class
 *
 * @version 1.0, 2011/01/14
 */
public class BeforeInspectResultQrySvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public BeforeInspectResultQrySvc() {}

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

        sb.append("  SELECT QUERY_FLAG_NM,                                                                                                     \n");
        sb.append("         SUM(REQ_CNT)  AS REQ_CNT,                                                                                          \n");
        sb.append("         SUM(ACPT_CNT) AS ACPT_CNT,                                                                                         \n");
        sb.append("         SUM(PASS_CNT) AS PASS_CNT,                                                                                         \n");
        sb.append("         SUM(HOLD_CNT) AS HOLD_CNT,                                                                                         \n");
        sb.append("         SUM(FAIL_CNT) AS FAIL_CNT                                                                                          \n");
        sb.append("    FROM (                                                                                                                  \n");
        sb.append("         SELECT DECODE(?, '1', TO_CHAR(ACPT_DATE,'YYYY/MM/DD'),                                                             \n");
        sb.append("                          '2', TO_CHAR(QA_DATE,'YYYY/MM/DD'),                                                               \n");
        sb.append("                          '3', BQ.QA_ID||' '||USER_NAME,                                                                \n");
        sb.append("                          '4', GD.LMSD_CODE||' '||LGROUP_NAME||'|'||MGROUP_NAME||'|'||SGROUP_NAME||'|'||DGROUP_NAME ,       \n");
        sb.append("                          '5', GD.ENTP_CODE||' '||ENTP_NAME,                                                                \n");
        sb.append("                          '6', GD.MD_CODE  ||' '||MD_NAME) QUERY_FLAG_NM,                                                   \n");
        sb.append("                CASE                                                                                                        \n");
        sb.append("                   WHEN BQ.REQ_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD') AND TO_DATE(?,'YYYY/MM/DD')  THEN 1       \n");
        sb.append("                   ELSE 0                                                                                                   \n");
        sb.append("                END   REQ_CNT,                                                                                              \n");
        sb.append("                                                                                                                            \n");
        sb.append("                CASE                                                                                                        \n");
        sb.append("                   WHEN BQ.ACPT_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD') AND TO_DATE(?,'YYYY/MM/DD')  THEN 1      \n");
        sb.append("                   ELSE 0                                                                                                   \n");
        sb.append("                END  ACPT_CNT,                                                                                              \n");
        sb.append("                DECODE(BQ.SQC_GB, '16',1, '19',1, 0) PASS_CNT,                                                              \n");
        sb.append("                DECODE(BQ.SQC_GB, '06',1, 0)         HOLD_CNT,                                                              \n");
        sb.append("                DECODE(BQ.SQC_GB, '09',1, 0)         FAIL_CNT                                                               \n");
        sb.append("           FROM TBEFOREQAM   BQ,                                                                                            \n");
        sb.append("                TGOODS       GD,                                                                                            \n");
        sb.append("                TUSER        US,                                                                                            \n");
        sb.append("                TGOODSKINDS  GK,                                                                                            \n");
        sb.append("                TENTERPRISE  EP,                                                                                            \n");
        sb.append("                TMD          MD                                                                                             \n");
        sb.append("          WHERE BQ.GOODS_CODE = GD.GOODS_CODE                                                                               \n");
        sb.append("            AND BQ.QA_ID  = US.USER_ID                                                                                  \n");
        sb.append("            AND GD.LMSD_CODE  = GK.LMSD_CODE                                                                                \n");
        sb.append("            AND GD.ENTP_CODE  = EP.ENTP_CODE                                                                                \n");
        sb.append("            AND GD.MD_CODE    = MD.MD_CODE                                                                                  \n");

        if(retrieve.getString("query_st").equals("1")) {
        	sb.append("            AND REQ_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD') AND TO_DATE(?,'YYYY/MM/DD')                                        \n");
        } else if(retrieve.getString("query_st").equals("2")) {
        	sb.append("            AND ACPT_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD') AND TO_DATE(?,'YYYY/MM/DD')                                       \n");
        } else if(retrieve.getString("query_st").equals("3")) {
        	sb.append("            AND QA_DATE BETWEEN TO_DATE(?,'YYYY/MM/DD') AND TO_DATE(?,'YYYY/MM/DD')                                         \n");
        }

        sb.append("            AND BQ.QA_ID  LIKE ? || '%'                                                                                 \n");
        sb.append("            AND GD.GOODS_CODE LIKE ? || '%'                                                                                 \n");
        sb.append("            AND GD.MD_CODE    LIKE ? || '%'                                                                                 \n");
        sb.append("         ) A                                                                                                                \n");
        sb.append("GROUP BY QUERY_FLAG_NM									                                                                   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("query_st   : " + retrieve.getString("query_st"));
            log.debug("query_unit : " + retrieve.getString("query_unit"));
            log.debug("from_date  : " + retrieve.getString("from_date"));
            log.debug("to_date    : " + retrieve.getString("to_date"));
            log.debug("user_id    : " + retrieve.getString("user_id"));
            log.debug("goods_code : " + retrieve.getString("goods_code"));
            log.debug("md_code    : " + retrieve.getString("md_code"));
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
            pstmt.setString(index++,retrieve.getString("query_unit"));
            pstmt.setString(index++,retrieve.getString("from_date"));
            pstmt.setString(index++,retrieve.getString("to_date"));
            pstmt.setString(index++,retrieve.getString("from_date"));
            pstmt.setString(index++,retrieve.getString("to_date"));
            pstmt.setString(index++,retrieve.getString("from_date"));
            pstmt.setString(index++,retrieve.getString("to_date"));
            pstmt.setString(index++,retrieve.getString("user_id"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("md_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[BeforeInspectResultQrySvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[BeforeInspectResultQrySvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

}
