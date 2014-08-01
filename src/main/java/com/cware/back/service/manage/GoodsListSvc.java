
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


/**
 * 상품내역조회 Service class
 *
 * @version 1.0, 2006/06/30
 */
public class GoodsListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        /** not exist TSQCRESULTM table   */
        sb.append("    SELECT /*+ RULE */     \n");
        sb.append("           A.GOODS_CODE,   \n");
        sb.append("           A.GOODS_NAME,   \n");
        sb.append("           A.SET_YN,       \n");
        sb.append("           A.SAMPLE_YN,    \n");
        sb.append("           A.DM_YN,        \n");
        sb.append("           A.GIFT_YN,      \n");
        sb.append("           A.PAY_YN,       \n");
        sb.append("           D.MD_NAME,      \n");
        sb.append("           C.ENTP_CODE,    \n");
        sb.append("           C.ENTP_NAME,    \n");
        sb.append("           A.SALE_GB,      \n");
        sb.append("           A.SQC_GB,       \n");
        sb.append("           B.LGROUP_NAME,  \n");
        sb.append("           B.MGROUP_NAME,  \n");
        sb.append("           B.SGROUP_NAME,  \n");
        sb.append("           B.DGROUP_NAME,  \n");
        sb.append("           E.BUY_PRICE,    \n");
        sb.append("           NVL(E.SALE_PRICE, 0) SALE_PRICE,   \n");
        sb.append("           TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') AS INSERT_DATE,   \n");
        sb.append("           M.ENTP_MAN_SEQ,   \n");
        sb.append("           M.ENTP_MAN_NAME,  \n");
        sb.append("           DECODE(M.ENTP_MAN_TEL2,NULL,' ',LTRIM(M.ENTP_MAN_DDD) || ')' || LTRIM(M.ENTP_MAN_TEL1) || '-' || LTRIM(M.ENTP_MAN_TEL2)) AS ENTP_MAN_TEL,   \n");
        sb.append("           DECODE(M.ENTP_MAN_FAX3,NULL,' ',LTRIM(M.ENTP_MAN_FAX1) || ')' || LTRIM(M.ENTP_MAN_FAX2) || '-' || LTRIM(M.ENTP_MAN_FAX3)) AS ENTP_FAX_TEL,  \n");
        sb.append("           DECODE(M.ENTP_MAN_HP3,NULL,' ',LTRIM(M.ENTP_MAN_HP1) || ')' || LTRIM(M.ENTP_MAN_HP2) || '-' || LTRIM(M.ENTP_MAN_HP3)) AS ENTP_HAND_TEL,     \n");
        sb.append("           A.NOREST_ALLOT_MONTHS,  \n");
        sb.append("           E.SAVEAMT,               \n");
        sb.append("           A.DELY_TYPE,             \n");
        sb.append("           A.SIGN_GB,               \n");
        sb.append("           ''   AS SQC_SEQ,    \n");
        sb.append("           '00' AS SQC_SIGN,    \n");
        sb.append("           A.MASTER_CODE    \n");
        sb.append("      FROM TGOODS A,                        \n");
        sb.append("           TGOODSKINDS B,                   \n");
        sb.append("           TENTERPRISE C,                   \n");
        sb.append("           TMD D,                           \n");
        sb.append("           TGOODSPRICE E,                   \n");
        sb.append("           TENTPUSER    M                   \n");
        sb.append("     WHERE A.LGROUP = B.LGROUP              \n");
        sb.append("       AND A.MGROUP = B.MGROUP              \n");
        sb.append("       AND A.SGROUP = B.SGROUP              \n");
        sb.append("       AND A.DGROUP = B.DGROUP              \n");
        sb.append("       AND A.ENTP_CODE = C.ENTP_CODE        \n");
        sb.append("       AND A.MD_CODE = D.MD_CODE            \n");
        sb.append("       AND A.GOODS_CODE = E.GOODS_CODE(+)                                                                                    \n");
        sb.append("       AND NVL(E.APPLY_DATE, SYSDATE) = ( SELECT NVL(MAX(F.APPLY_DATE), SYSDATE)                                             \n");
        sb.append("                                            FROM TGOODSPRICE F                                                               \n");
        sb.append("                                           WHERE A.GOODS_CODE = F.GOODS_CODE                                                 \n");
        sb.append("                                             AND DECODE(SIGN(F.APPLY_DATE-SYSDATE), 1, SYSDATE, APPLY_DATE) <= SYSDATE )     \n");
        sb.append("       AND A.ENTP_CODE    = M.ENTP_CODE(+)          \n");
        sb.append("       AND A.ENTP_MAN_SEQ = M.ENTP_MAN_SEQ(+)       \n");

        if(retrieve.getString("chk_goods_code").equals("1") || retrieve.getString("chk_goods_name").equals("1")){
        	if(retrieve.getString("chk_master_yn").equals("1")){
        		sb.append("       AND A.MASTER_CODE LIKE '" + retrieve.getString("goods_code") + "' || '%'      \n");

        	}else if(retrieve.getString("chk_goods_code").equals("1")){
                sb.append("       AND A.GOODS_CODE LIKE '" + retrieve.getString("goods_code") + "' || '%'      \n");
            }

            if(retrieve.getString("chk_goods_name").equals("1")){
                sb.append("       AND A.GOODS_NAME LIKE '%'||'" + retrieve.getString("goods_name") + "' || '%'      \n");
            }

        }else{
            sb.append("       AND A.INSERT_DATE >= TO_DATE('" + retrieve.getString("fromDate") + "' , 'YYYY/MM/DD')      \n");
            sb.append("       AND A.INSERT_DATE < TO_DATE('" + retrieve.getString("toDate") + "' , 'YYYY/MM/DD') + 1     \n");
            //= MD코드
            if(!retrieve.getString("md_code").equals("")){
                sb.append("       AND A.MD_CODE = '" + retrieve.getString("md_code") + "'                 \n");
            }
            //= 공급업체코드
            if(!retrieve.getString("entp_code").equals("")){
                sb.append("       AND A.ENTP_CODE = '" + retrieve.getString("entp_code") + "'             \n");
            }
            //= 판매구분
            if(!retrieve.getString("sale_gb").equals("")){
                sb.append("       AND A.SALE_GB = '" + retrieve.getString("sale_gb") + "'                 \n");
            }
            //= QC구분
            if(!retrieve.getString("sqc_gb").equals("")){
                sb.append("       AND A.SQC_GB = '" + retrieve.getString("sqc_gb") + "'                 \n");
            }
            //= 결재구분
            if(!retrieve.getString("sign_gb").equals("")){
                sb.append("       AND A.SIGN_GB = '" + retrieve.getString("sign_gb") + "'                 \n");
            }
            //= 대분류
            if(!retrieve.getString("lgroup").equals("")){
                sb.append("       AND A.LGROUP = '" + retrieve.getString("lgroup") + "'                 \n");
            }
            //= 중분류
            if(!retrieve.getString("mgroup").equals("")){
                sb.append("       AND A.MGROUP = '" + retrieve.getString("mgroup") + "'                 \n");
            }
            //= 소분류
            if(!retrieve.getString("sgroup").equals("")){
                sb.append("       AND A.SGROUP = '" + retrieve.getString("sgroup") + "'                 \n");
            }
            //= 세분류
            if(!retrieve.getString("dgroup").equals("")){
                sb.append("       AND A.DGROUP = '" + retrieve.getString("dgroup") + "'                 \n");
            }
	    }


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug(retrieve);
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
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;

            //pstmt.setString(index++,retrieve.getString("fromDate"));
            //pstmt.setString(index++,retrieve.getString("fromDate"));
            //pstmt.setString(index++,retrieve.getString("lgroup"));
            //pstmt.setString(index++,retrieve.getString("lgroup"));
            //pstmt.setString(index++,retrieve.getString("sale_gb"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}
