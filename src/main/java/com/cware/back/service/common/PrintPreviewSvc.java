
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;

/**
 * PrintPreview Service Class
 *
 * @version 1.0, 2006/08/25
 */
public class PrintPreviewSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PrintPreviewSvc() {}

    /**
    * <PRE>
    * Desc : 상품분류코드 출력
    * </PRE>
    * @return  Query
    */
    public String makeReportSqlGoodsKinds() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.MGROUP       || '|' || \n ");
        sb.append("        A.MGROUP_NAME  || '|' || \n ");
        sb.append("        A.SGROUP       || '|' || \n ");
        sb.append("        A.SGROUP_NAME  || '|' || \n ");
        sb.append("        A.DGROUP       || '|' || \n ");
        sb.append("        A.DGROUP_NAME  || '|' || \n ");
        sb.append("        B.CODE_NAME    || '|' || \n ");
        sb.append("        LTRIM(TO_CHAR(A.DEF_VAT_RATE, '999.99')) || ';' AS PRTDATA \n ");
        sb.append("   FROM TGOODSKINDS A, \n ");
        sb.append("        TCODE B 		  \n ");
        sb.append("  WHERE A.LGROUP = ?   \n ");
        sb.append("    AND B.CODE_LGROUP = 'B800' \n "); 
        sb.append("    AND A.SIZE_LGROUP = B.CODE_MGROUP \n ");  
        sb.append("  ORDER BY A.LGROUP, A.MGROUP,A.SGROUP,A.DGROUP ");

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품분류코드 전체 출력
    * </PRE>
    * @return  Query
    */
    public String makeReportSqlGoodsKindsAll() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.LGROUP       || '|' || \n ");
        sb.append("        A.LGROUP_NAME  || '|' || \n ");
        sb.append("        A.MGROUP       || '|' || \n ");
        sb.append("        A.MGROUP_NAME  || '|' || \n ");
        sb.append("        A.SGROUP       || '|' || \n ");
        sb.append("        A.SGROUP_NAME  || '|' || \n ");
        sb.append("        A.DGROUP       || '|' || \n ");
        sb.append("        A.DGROUP_NAME  || '|' || \n ");
        sb.append("        B.CODE_NAME    || '|' || \n ");
        sb.append("        LTRIM(TO_CHAR(A.DEF_VAT_RATE, '999.99')) || ';' AS PRTDATA \n ");
        sb.append("   FROM TGOODSKINDS A,  \n ");
        sb.append("        TCODE B 		   \n ");
        sb.append("  WHERE A.SIZE_LGROUP = B.CODE_MGROUP \n "); 
//        sb.append("    AND B.CODE_LGROUP = 'B800' \n ");
        sb.append("  ORDER BY A.LGROUP, A.MGROUP,A.SGROUP,A.DGROUP ");

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : MD LIST 출력
    * </PRE>
    * @return  Query
    */
    public String makeReportSqlMd() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.MD_CODE             || '|' || \n ");
        sb.append("        A.MD_NAME             || '|' || \n ");
        sb.append("        NVL(B.USER_ID, '')    || '|' || \n ");
        sb.append("        NVL(C.USER_NAME, '')  || '|' || \n ");
        sb.append("        NVL(TO_CHAR(B.START_DATE,'YYYY/MM/DD'), '') || '|' || \n ");
        sb.append("        NVL(TO_CHAR(B.END_DATE,  'YYYY/MM/DD'), '') || ';' AS PRTDATA \n ");
        sb.append("   FROM TMD     A,  \n ");
        sb.append("        TMDLINK B,  \n ");
        sb.append("        TUSER   C   \n ");
        sb.append("  WHERE A.MD_CODE = B.MD_CODE(+) \n ");
        sb.append("    AND B.USER_ID = C.USER_ID(+) \n ");
        sb.append("    AND A.MD_CODE LIKE ?  \n ");
        sb.append("  ORDER BY A.MD_CODE, B.USER_ID ");

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
   public RetrieveModel retrieve ( Connection con, RetrieveModel retrieve, String[] paramValues ) throws StoreException{
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        Collection collection   = new ArrayList();
        HashMap[]  hashmap      = null;
        HashMap    hmSheet      = null;

        String query = "", flag = "";

        try {
            flag = retrieve.getString("prtSQL");

            if (flag.equals("GoodsKinds")) {
                query = makeReportSqlGoodsKinds();
            } else if (flag.equals("GoodsKindsAll")) {
                query = makeReportSqlGoodsKindsAll();
            } else if (flag.equals("Md")) {
                query = makeReportSqlMd();
            }

            pstmt = con.prepareStatement(query);

            if(paramValues != null){
                for(int i = 1 ; i <= paramValues.length ; i++){
                    pstmt.setString(i, paramValues[i-1]);
                }
            }

            rset  = pstmt.executeQuery();

            while (rset!=null && rset.next()){
                hmSheet = new HashMap();
                hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
                collection.add(hmSheet);
            }
            retrieve.put("RESULT",(HashMap[]) collection.toArray(new HashMap[0]));

        }catch(SQLException se){
            log.error("[PrintPreviewSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PrintPreviewSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

}