
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
 * Enterprise company List Service class
 *
 * @version 1.0, 2006/07/04
 */
public class EntpListSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public EntpListSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Enterprise company List select
    * </PRE>
    * @param   poolName                           : Database pool name
    * @param   entp_code                          : 업체코드
    * @param   close_yn                           : 거래여부
    */

    public String makeSql(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.ENTP_CODE,    \n");
        sb.append("        A.ENTP_NAME,    \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'yyyy/mm/dd') AS INSERT_DATE,  \n");
        sb.append("        A.WORK_TYPE,    \n");
        sb.append("        A.WORK_KIND,    \n");
        sb.append("        A.S_IDNO,       \n");
        sb.append("        TO_CHAR(A.CLOSE_DATE, 'yyyy/mm/dd') AS CLOSE_DATE,  \n");
        sb.append("        A.DISHONOR_YN,  \n");
        sb.append("        A.CLOSE_REASON, \n");
        sb.append("        NVL(A.OWNER_NAME, B.ENTP_MAN_NAME)  AS OWNER_NAME, \n");
        sb.append("        B.ENTP_MAN_NAME, \n");
        sb.append("        A.ENTP_DDD || ')' || A.ENTP_TEL1 || '-' || A.ENTP_TEL2 AS TEL \n");
        sb.append("   FROM TENTERPRISE A, \n");
        sb.append("        TENTPUSER   B  \n");
        sb.append("  WHERE A.ENTP_CODE     = B.ENTP_CODE(+) \n");
        sb.append("    AND B.DEFAULT_YN(+) = '1'            \n");
        sb.append("	   AND A.ENTP_CODE LIKE ? || '%'  \n");
	      if (retrieve.getString("close_yn").equals("2")) {
	      // 거래중단일때
	      sb.append("	AND ( A.DISHONOR_YN = ?  OR A.CLOSE_DATE <= SYSDATE ) \n");
		  }else if (!retrieve.getString("close_yn").equals("")) {
		      //= 전체조회 아닐경우
		      sb.append("	AND ( A.CLOSE_DATE IS NULL OR A.CLOSE_DATE > SYSDATE ) \n");
		      sb.append("	AND A.DISHONOR_YN = ? \n");
		  }

        // check condition
//        if (retrieve.getString("close_yn").equals("2")) {
//            // 거래중단일때
//            sb.append("	AND A.CLOSE_DATE IS NOT NULL AND A.CLOSE_DATE <= SYSDATE \n");
//        } else if (retrieve.getString("close_yn").equals("0")) {
//            //= 거래정상일때 (OR A.CLOSE_DATE > SYSDATE //01.06.27 BSO)
//            sb.append("	AND ( A.CLOSE_DATE IS NULL OR A.CLOSE_DATE > SYSDATE ) \n");
//            sb.append("	AND A.DISHONOR_YN = '0' \n");
//        } else if (retrieve.getString("close_yn").equals("1")) {
//            //= 업체부도일때
//            sb.append("	AND A.DISHONOR_YN = '1' \n");
//        } else {
//            sb.append("	AND ( A.CLOSE_DATE IS NULL OR A.CLOSE_DATE >= SYSDATE OR (A.CLOSE_DATE IS NOT NULL AND A.CLOSE_DATE <= sysdate)) \n");
//        }



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
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("entp_code"));
            if (!retrieve.getString("close_yn").equals("")) {
                pstmt.setString(index++,retrieve.getString("close_yn"));
            }

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[EntpListSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[EntpListSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}
