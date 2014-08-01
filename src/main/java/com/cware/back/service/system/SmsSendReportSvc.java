
package com.cware.back.service.system;

import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * 일일매출현황 Service class
 *
 * @version 1.0, 2006/06/27
 */
public class SmsSendReportSvc {

    public SmsSendReportSvc() {}

    public String makeSql() throws Exception {
        StringBuffer sb = new StringBuffer();

		sb.append(" SELECT C.CODE_MGROUP,                                  \n");
		sb.append("        C.CODE_NAME,                                    \n");
		sb.append("        SUM(DECODE(D.CODE_GROUP, '1', 1, 0)) AS SUCCESS_CNT, \n");
		sb.append("        SUM(DECODE(D.CODE_GROUP, '0', 1, 0)) AS FAIL_CNT     \n");
		sb.append("  FROM SDK_SMS_REPORT A,                                \n");
		sb.append("       SDK_SMS_REPORT_DETAIL B,                         \n");
		sb.append("       TCODE C,                                         \n");
		sb.append("       TCODE D                                          \n");
		sb.append(" WHERE A.MSG_ID = B.MSG_ID                              \n");
		sb.append("   AND A.USER_ID = B.USER_ID                            \n");
		sb.append("   AND A.JOB_ID = B.JOB_ID                              \n");
		sb.append("   AND A.RESERVED5 = 'SHOP'                             \n");
		sb.append("   AND C.CODE_LGROUP = 'F092'                           \n");
		sb.append("   AND A.RESERVED1 = C.CODE_MGROUP                      \n");
		sb.append("   AND D.CODE_lGROUP = 'F093'                           \n");
		sb.append("   AND B.RESULT = TO_NUMBER(D.CODE_MGROUP)              \n");
        sb.append("   AND A.SEND_DATE >= TO_CHAR(TO_DATE(?), 'YYYYMMDD')|| '0000' \n");
        sb.append("   AND A.SEND_DATE <= TO_CHAR(TO_DATE(?), 'YYYYMMDD')|| '2359' \n");
        sb.append("   AND C.CODE_MGROUP like ? || '%'                      \n");
        sb.append("   AND D.CODE_GROUP like ? || '%'                       \n");
		sb.append(" GROUP BY C.CODE_MGROUP, C.CODE_NAME				       \n");
		sb.append(" ORDER BY C.CODE_MGROUP                                 \n");

      
            
		return sb.toString();

    }

    
    public String makeSqlDetail() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.SEND_DATE,                                     \n");
        sb.append("        A.RESERVED1,                                     \n");
        sb.append("        C.CODE_NAME AS SMS_FLAG_NAME,                    \n");
        sb.append("        B.DEST_NAME,                                     \n");
        sb.append("        B.PHONE_NUMBER,                                  \n");
        sb.append("        A.SMS_MSG,                                       \n");
        sb.append("        B.RESULT,                                        \n");
        sb.append("        D.CODE_NAME AS RESULT_NAME                       \n");
		sb.append("   FROM SDK_SMS_REPORT A,                                \n");
		sb.append("        SDK_SMS_REPORT_DETAIL B,                         \n");
		sb.append("        TCODE C,                                         \n");
		sb.append("        TCODE D                                          \n");
		sb.append("  WHERE A.MSG_ID = B.MSG_ID                              \n");
		sb.append("    AND A.USER_ID = B.USER_ID                            \n");
		sb.append("    AND A.JOB_ID = B.JOB_ID                              \n");
		sb.append("    AND A.RESERVED5 = 'SHOP'                             \n");
		sb.append("    AND C.CODE_LGROUP = 'F092'                           \n");
		sb.append("    AND A.RESERVED1 = C.CODE_MGROUP                      \n");
		sb.append("    AND D.CODE_lGROUP = 'F093'                           \n");
		sb.append("    AND B.RESULT = TO_NUMBER(D.CODE_MGROUP)              \n");
        sb.append("    AND A.SEND_DATE >= TO_CHAR(TO_DATE(?), 'YYYYMMDD')|| '0000' \n");
        sb.append("    AND A.SEND_DATE <= TO_CHAR(TO_DATE(?), 'YYYYMMDD')|| '2359' \n");
        sb.append("    AND C.CODE_MGROUP like ? || '%'                      \n");
        sb.append("    AND D.CODE_GROUP like ? || '%'                       \n");
		sb.append(" ORDER BY A.SEND_DATE DESC                               \n");

        //= log SQL -------------------------------------------------
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
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
/**
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
        **/
        return (HashMap[])collection.toArray(new HashMap[0]);
    }

 
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) {
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("send_date_from"));
            pstmt.setString(index++,retrieve.getString("send_date_to"  ));
            pstmt.setString(index++,retrieve.getString("sms_flag"      ));
            pstmt.setString(index++,retrieve.getString("send_result"   ));
 
            rset  = pstmt.executeQuery();
            retrieve.put("RESULT",makeSheet(rset));

            pstmt = con.prepareStatement(makeSqlDetail(retrieve));
            index=1;

            pstmt.setString(index++,retrieve.getString("send_date_from"));
            pstmt.setString(index++,retrieve.getString("send_date_to"  ));
            pstmt.setString(index++,retrieve.getString("sms_flag"      ));
            pstmt.setString(index++,retrieve.getString("send_result"   ));

            rset  = pstmt.executeQuery();
            retrieve.put("RESULT_DETAIL",makeSheet(rset));
            
        }catch(SQLException se){
          //  log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
//            throw new StoreException(se.getMessage());
        }catch(Exception e){
           // log.error("Exception : ERR-"+e.getMessage());
  //          throw new StoreException(e.getMessage());
        }finally {
            try {
                DBUtils.freeConnection(null, pstmt, rset);
            } catch (StoreException ex) {
                Logger.getLogger(SmsSendReportSvc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retrieve;
    }

    private String makeSql(RetrieveModel retrieve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String makeSqlDetail(RetrieveModel retrieve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
