
package com.cware.back.service.system;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
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



/**
 * BatchCheck Service class
 *
 * @version 1.0, 2006/07/07
 * @author commerceware.co.kr
 */
public class BatchCheckSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public BatchCheckSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL 
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlFirstDay( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
            
        sb.append(" SELECT NVL(B.OK_YN, '0') AS OK_YN,                                                  \n ");
        sb.append("        A.CODE_MGROUP,                                                               \n ");
        sb.append("        A.CODE_NAME,                                                                 \n ");
        sb.append("        TO_CHAR(C.CLOSE_DATE,'yyyy/mm/dd') AS CLOSE_DATE,                            \n ");
        sb.append("        A.CODE_GROUP                                                                 \n ");
        sb.append("   FROM (SELECT X.CODE_MGROUP,                                                       \n ");
        sb.append("                X.CODE_GROUP,                                                        \n ");
        sb.append("                X.CODE_NAME                                                          \n ");
        sb.append("           FROM TCODE X                                                              \n ");
        sb.append("          WHERE X.CODE_LGROUP = 'B065'                                               \n ");
        sb.append("            AND X.USE_YN = '1'                                                       \n ");
        sb.append("        ) A,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Y.PRG_ID, './', '') AS PRG_ID,                               \n ");
        sb.append("                Y.OK_YN                                                              \n ");
        sb.append("           FROM TCLOSEHISTORY Y                                                      \n ");
        sb.append("          WHERE Y.CLOSE_DATE = TO_DATE(? ,'yyyy/mm/dd')                         \n ");
        sb.append("        ) B,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Z.PRG_ID, './', '') AS PRG_ID,                               \n ");
        sb.append("                MAX(Z.CLOSE_DATE) AS CLOSE_DATE                                      \n ");
        sb.append("           FROM TCLOSEHISTORY Z                                                      \n ");
        sb.append("          WHERE Z.CLOSE_DATE <= TO_DATE(? ,'yyyy/mm/dd')                        \n ");
        sb.append("          GROUP BY REPLACE(Z.PRG_ID, './', '')                                       \n ");
        sb.append("        ) C                                                                          \n ");
        sb.append("  WHERE A.CODE_MGROUP = B.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_MGROUP = C.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_GROUP = '10'                                                          \n ");
        sb.append(" UNION ALL                                                                           \n ");
        sb.append(" SELECT NVL(B.OK_YN, '0') AS OK_YN,                                                  \n ");
        sb.append("        A.CODE_MGROUP,                                                               \n ");
        sb.append("        A.CODE_NAME,                                                                 \n ");
        sb.append("        TO_CHAR(C.CLOSE_DATE,'yyyy/mm/dd') AS CLOSE_DATE,                            \n ");
        sb.append("        A.CODE_GROUP                                                                 \n ");
        sb.append("   FROM (SELECT X.CODE_MGROUP,                                                       \n ");
        sb.append("                X.CODE_GROUP,                                                        \n ");
        sb.append("                X.CODE_NAME                                                          \n ");
        sb.append("           FROM TCODE X                                                              \n ");
        sb.append("          WHERE X.CODE_LGROUP = 'B065'                                               \n ");
        sb.append("            AND X.USE_YN = '1'                                                       \n ");
        sb.append("        ) A,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Y.PRG_ID, './', '')  AS PRG_ID,                              \n ");
        sb.append("                Y.OK_YN                                                              \n ");
        sb.append("           FROM TCLOSEHISTORY Y                                                      \n ");
        sb.append("          WHERE Y.CLOSE_DATE = TO_DATE(? ,'yyyy/mm/dd')                      \n ");
        sb.append("        ) B,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Z.PRG_ID, './', '')  AS PRG_ID,                              \n ");
        sb.append("                MAX(Z.CLOSE_DATE) AS CLOSE_DATE                                      \n ");
        sb.append("           FROM TCLOSEHISTORY Z                                                      \n ");
        sb.append("          WHERE Z.CLOSE_DATE <= TO_DATE(? ,'yyyy/mm/dd')                     \n ");
        sb.append("          GROUP BY REPLACE(Z.PRG_ID, './', '')                                       \n ");
        sb.append("        ) C                                                                          \n ");
        sb.append("  WHERE A.CODE_MGROUP = B.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_MGROUP = C.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_GROUP = '20'                                                          \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug(" close_date    : " + retrieve.getString("close_date"   ) + "\n");
            log.debug(" dateCondition : " + retrieve.getString("dateCondition") + "\n");
        }
        return sb.toString();
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL 
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT NVL(B.OK_YN, '0') AS OK_YN,                                                  \n ");
        sb.append("        A.CODE_MGROUP,                                                               \n ");
        sb.append("        A.CODE_NAME,                                                                 \n ");
        sb.append("        TO_CHAR(C.CLOSE_DATE,'yyyy/mm/dd') AS CLOSE_DATE,                            \n ");
        sb.append("        A.CODE_GROUP                                                                 \n ");
        sb.append("   FROM (SELECT X.CODE_MGROUP,                                                       \n ");
        sb.append("                X.CODE_GROUP,                                                        \n ");
        sb.append("                X.CODE_NAME                                                          \n ");
        sb.append("           FROM TCODE X                                                              \n ");
        sb.append("          WHERE X.CODE_LGROUP = 'B065'                                               \n ");
        sb.append("            AND X.USE_YN = '1'                                                       \n ");
        sb.append("        ) A,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Y.PRG_ID, './', '') AS PRG_ID,                               \n ");
        sb.append("                Y.OK_YN                                                              \n ");
        sb.append("           FROM TCLOSEHISTORY Y                                                      \n ");
        sb.append("          WHERE Y.CLOSE_DATE = TO_DATE(? ,'yyyy/mm/dd')                         \n ");
        sb.append("        ) B,                                                                         \n ");
        sb.append("        (SELECT REPLACE(Z.PRG_ID, './', '') AS PRG_ID,                               \n ");
        sb.append("                MAX(Z.CLOSE_DATE) AS CLOSE_DATE                                      \n ");
        sb.append("           FROM TCLOSEHISTORY Z                                                      \n ");
        sb.append("          WHERE Z.CLOSE_DATE <= TO_DATE(? ,'yyyy/mm/dd')                        \n ");
        sb.append("          GROUP BY REPLACE(Z.PRG_ID, './', '')                                       \n ");
        sb.append("        ) C                                                                          \n ");
        sb.append("  WHERE A.CODE_MGROUP = B.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_MGROUP = C.PRG_ID(+)                                                  \n ");
        sb.append("    AND A.CODE_GROUP = '10'                                                          \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug(" close_date    : " + retrieve.getString("close_date"   ) + "\n");
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
//            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

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
            pstmt.setString(index++, retrieve.getString("close_date"   ));
            pstmt.setString(index++, retrieve.getString("close_date"   ));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("BatchCheckSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
//            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("BatchCheckSvc.retrieve() Exception : ERR-"+e.getMessage());
  //          throw new StoreException(e.getMessage());
        }finally {
    //        DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }
}
