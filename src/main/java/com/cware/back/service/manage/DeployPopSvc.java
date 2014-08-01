
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
import com.cware.back.entity.table.Tcategory;
import com.cware.back.entity.table.Tcategorygoods;
import com.cware.back.entity.table.Tcategoryurl;
import com.cware.back.entity.table.Tpresentation;


/**
 * 카테고리 서비스 적용 Service class
 *
 * @version 1.0, 2006/08/04
 * @author commerceware.co.kr
 */
public class DeployPopSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public DeployPopSvc() {}

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


        sb.append("  SELECT A.PRESENTATION_CODE,  \n");
        sb.append("         A.GOODS_CODE,  \n");
        sb.append("         B.GOODS_NAME,  \n");
        sb.append("         A.DISPLAY_YN,  \n");
        sb.append("         A.PRESENTATION_KINDS  \n");
        sb.append("    FROM TPRESENTATION A,  \n");
        sb.append("         TGOODS        B  \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("     AND A.CATEGORY_CODE = '0'  \n");
        sb.append("   ORDER BY A.PRESENTATION_KINDS, A.GOODS_CODE  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispGoodsCntY() throws Exception {
        StringBuffer sb = new StringBuffer();


        sb.append("  SELECT COUNT(*) AS CNT \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY      B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND B.CATEGORY_GB = '01'  \n");
        sb.append("     AND A.DISPLAY_YN = '1'  \n");
        sb.append("     AND A.GOODS_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispGoodsCntN() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT \n");
        sb.append("    FROM TCATEGORYGOODS A,  \n");
        sb.append("         TCATEGORY      B  \n");
        sb.append("   WHERE A.CATEGORY_CODE = B.CATEGORY_CODE  \n");
        sb.append("     AND B.CATEGORY_GB = '01'  \n");
        sb.append("     AND A.GOODS_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCheck5(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         DISPLAY_GOODS_CNT,  \n");
        sb.append("         C_GOODS_CNT  \n");
        sb.append("    FROM (  \n");
        sb.append("          SELECT A.CATEGORY_CODE,  \n");
        sb.append("                 A.DISPLAY_GOODS_CNT,  \n");
        sb.append("                (  \n");
        sb.append("                    SELECT COUNT(B.GOODS_CODE)  \n");
        sb.append("                      FROM TCATEGORYGOODS B  \n");
        sb.append("                     WHERE B.DISPLAY_YN = '1'  \n");
        sb.append("                       AND B.CATEGORY_CODE = A.CATEGORY_CODE  \n");
        sb.append("                ) AS C_GOODS_CNT  \n");
        sb.append("           FROM TCATEGORY A  \n");
        sb.append("          WHERE SUBSTR(A.CATEGORY_CODE, 6, 3) <> '000'  \n");
        sb.append("            AND A.DISPLAY_YN = '1'  \n");
        sb.append("         )  \n");
        sb.append("   WHERE DISPLAY_GOODS_CNT <> C_GOODS_CNT  \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCheck4(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DISPLAY_GOODS_CNT,  \n");
        sb.append("         C_GOODS_CNT  \n");
        sb.append("    FROM (  \n");
        sb.append("         SELECT A.CATEGORY_CODE,  \n");
        sb.append("                A.CATEGORY_GB,  \n");
        sb.append("                A.DISPLAY_GOODS_CNT,  \n");
        sb.append("                (  \n");
        sb.append("                 SELECT NVL(SUM(B.DISPLAY_GOODS_CNT) , 0)  \n");
        sb.append("                   FROM TCATEGORY B  \n");
        sb.append("                  WHERE B.P_CATEGORY_CODE = A.CATEGORY_CODE  \n");
        sb.append("                    AND B.DISPLAY_YN = '1'  \n");
        sb.append("                 ) AS C_GOODS_CNT  \n");
        sb.append("           FROM TCATEGORY A  \n");
        sb.append("          WHERE SUBSTR(A.CATEGORY_CODE, 4, 2) <> '00'  \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 6, 3) = '000'  \n");
        sb.append("            AND A.DISPLAY_YN = '1'  \n");
        sb.append("         )  \n");
        sb.append("   WHERE ( CATEGORY_GB = '01' AND DISPLAY_GOODS_CNT <> C_GOODS_CNT )  \n");
        sb.append("      OR ( CATEGORY_GB <> '01' )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCheck3(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DISPLAY_GOODS_CNT,  \n");
        sb.append("         C_GOODS_CNT  \n");
        sb.append("    FROM (  \n");
        sb.append("          SELECT A.CATEGORY_CODE,  \n");
        sb.append("                 A.CATEGORY_GB,  \n");
        sb.append("                 A.DISPLAY_GOODS_CNT,  \n");
        sb.append("                 (  \n");
        sb.append("                  SELECT NVL(SUM(B.DISPLAY_GOODS_CNT), 0)  \n");
        sb.append("                    FROM TCATEGORY B  \n");
        sb.append("                   WHERE B.P_CATEGORY_CODE = A.CATEGORY_CODE  \n");
        sb.append("                     AND B.DISPLAY_YN = '1'  \n");
        sb.append("                 ) AS C_GOODS_CNT  \n");
        sb.append("            FROM TCATEGORY A  \n");
        sb.append("           WHERE SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00'  \n");
        sb.append("             AND SUBSTR(A.CATEGORY_CODE, 4, 2) = '00'  \n");
        sb.append("             AND SUBSTR(A.CATEGORY_CODE, 6, 3) = '000'  \n");
        sb.append("             AND A.DISPLAY_YN = '1'  \n");
        sb.append("         )  \n");
        sb.append("   WHERE ( CATEGORY_GB = '01' AND DISPLAY_GOODS_CNT <> C_GOODS_CNT )    \n");
        sb.append("      OR ( CATEGORY_GB <> '01' )  \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCheck2(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DISPLAY_GOODS_CNT,  \n");
        sb.append("         C_GOODS_CNT  \n");
        sb.append("    FROM (  \n");
        sb.append("          SELECT A.CATEGORY_CODE,  \n");
        sb.append("                 A.CATEGORY_GB,  \n");
        sb.append("                 A.DISPLAY_GOODS_CNT,  \n");
        sb.append("                 ( SELECT NVL(SUM(B.DISPLAY_GOODS_CNT), 0)  \n");
        sb.append("                     FROM TCATEGORY B  \n");
        sb.append("                    WHERE B.P_CATEGORY_CODE = A.CATEGORY_CODE  \n");
        sb.append("                      AND B.DISPLAY_YN = '1'  \n");
        sb.append("                 ) AS C_GOODS_CNT  \n");
        sb.append("            FROM TCATEGORY A  \n");
        sb.append("           WHERE SUBSTR(A.CATEGORY_CODE, 1, 1) <> '0'  \n");
        sb.append("             AND SUBSTR(A.CATEGORY_CODE, 2, 2) = '00'  \n");
        sb.append("             AND SUBSTR(A.CATEGORY_CODE, 4, 2) = '00'  \n");
        sb.append("             AND SUBSTR(A.CATEGORY_CODE, 6, 3) = '000'  \n");
        sb.append("             AND A.DISPLAY_YN = '1'  \n");
        sb.append("         )  \n");
        sb.append("   WHERE ( CATEGORY_GB = '01' AND DISPLAY_GOODS_CNT <> C_GOODS_CNT )  \n");
        sb.append("      OR ( CATEGORY_GB <> '01' )  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCheck1(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.CATEGORY_CODE,  \n");
        sb.append("         A.CATEGORY_GB,  \n");
        sb.append("         A.DISPLAY_GOODS_CNT,  \n");
        sb.append("        ( SELECT NVL(SUM(B.DISPLAY_GOODS_CNT), 0)  \n");
        sb.append("            FROM TCATEGORY B  \n");
        sb.append("           WHERE B.P_CATEGORY_CODE = A.CATEGORY_CODE  \n");
        sb.append("             AND B.DISPLAY_YN = '1' )  \n");
        sb.append("    FROM TCATEGORY A  \n");
        sb.append("   WHERE A.CATEGORY_CODE = '0'  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlDispCnt() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(*) AS CNT  \n");
        sb.append("    FROM TCATEGORYGOODS   \n");
        sb.append("   WHERE DISPLAY_YN = '1'  \n");
        sb.append("      AND CATEGORY_CODE = ?  \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlTcategory(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_NAME,  \n");
        sb.append("         P_CATEGORY_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DISPLAY_GOODS_CNT  \n");
        sb.append("    FROM " + retrieve.getString("swUser") + ".TCATEGORY  \n");
        sb.append( retrieve.getString("modSqlStaging") );
        sb.append("    MINUS  \n");
        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         CATEGORY_NAME,  \n");
        sb.append("         P_CATEGORY_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         CATEGORY_GB,  \n");
        sb.append("         DISPLAY_GOODS_CNT  \n");
        sb.append("    FROM " + retrieve.getString("wUser") + ".TCATEGORY  \n");
        sb.append( retrieve.getString("modSqlOrg") );

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlTcategorygoods(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         GOODS_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         TO_CHAR(DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE  \n");
        sb.append("    FROM " + retrieve.getString("swUser") + ".TCATEGORYGOODS  \n");
        sb.append( retrieve.getString("modSqlStaging") );
        sb.append("    MINUS  \n");
        sb.append("  SELECT CATEGORY_CODE,  \n");
        sb.append("         GOODS_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         TO_CHAR(DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE  \n");
        sb.append("    FROM " + retrieve.getString("wUser") + ".TCATEGORYGOODS  \n");
        sb.append( retrieve.getString("modSqlOrg") );

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
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
    public String makeSqlTpresentation(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT PRESENTATION_CODE,  \n");
        sb.append("         PRESENTATION_KINDS,  \n");
        sb.append("         PRESENTATION_GB,  \n");
        sb.append("         CATEGORY_CODE,  \n");
        sb.append("         GOODS_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         TO_CHAR(DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,  \n");
        sb.append("         PRESENTATION_DESC  \n");
        sb.append("    FROM " + retrieve.getString("swUser") + ".TPRESENTATION  \n");
        sb.append( retrieve.getString("modSqlStaging") );
        sb.append( retrieve.getString("whereSql") );
        sb.append("    MINUS  \n");
        sb.append("  SELECT PRESENTATION_CODE,  \n");
        sb.append("         PRESENTATION_KINDS,  \n");
        sb.append("         PRESENTATION_GB,  \n");
        sb.append("         CATEGORY_CODE,  \n");
        sb.append("         GOODS_CODE,  \n");
        sb.append("         DISPLAY_YN,  \n");
        sb.append("         DISPLAY_PRIORITY,  \n");
        sb.append("         TO_CHAR(DISPLAY_START_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE,  \n");
        sb.append("         TO_CHAR(DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE,  \n");
        sb.append("         PRESENTATION_DESC  \n");
        sb.append("    FROM " + retrieve.getString("wUser") + ".TPRESENTATION  \n");
        sb.append( retrieve.getString("modSqlOrg") );
        sb.append( retrieve.getString("whereSql") );

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( DELETE Tcategory)
    * </PRE>
    * @param   
    * @return  String
    */

    private int deleteSqlLogTcategory =  0;

    public String makeSqlDeleteTcategory(RetrieveModel retrieve) throws StoreException{

        String deleteSqlTcategory = " DELETE FROM " + retrieve.getString("wUser") + ".TCATEGORY \n "
                                   +"       WHERE (( CATEGORY_CODE , P_CATEGORY_CODE, CATEGORY_GB ) IN ( \n "
                                   +"                SELECT CATEGORY_CODE , P_CATEGORY_CODE, CATEGORY_GB \n "
                                   +"                  FROM " + retrieve.getString("wUser") + ".TCATEGORY \n "
                                   +"                 " + retrieve.getString("modSqlOrg") + " \n "
                                   +"                 MINUS \n "
                                   +"                SELECT CATEGORY_CODE , P_CATEGORY_CODE, CATEGORY_GB \n "
                                   +"                  FROM " + retrieve.getString("swUser") + ".TCATEGORY \n "
                                   +"                 " + retrieve.getString("modSqlStaging") + " \n "
                                   +"             )) ";    
    
    
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTcategory == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTcategory);
            }            
            deleteSqlLogTcategory = 1;
        }
        return deleteSqlTcategory;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( DELETE Tcategorygoods)
    * </PRE>
    * @param   
    * @return  String
    */

    private int deleteSqlLogTcategorygoods =  0;

    public String makeSqlDeleteTcategorygoods(RetrieveModel retrieve) throws StoreException{

        String deleteSqlTcategorygoods = " DELETE FROM " + retrieve.getString("wUser") + ".TCATEGORYGOODS \n "
                                        +"      WHERE (( CATEGORY_CODE , GOODS_CODE ) IN ( \n "
                                        +"               SELECT CATEGORY_CODE , GOODS_CODE \n "
                                        +"                 FROM " + retrieve.getString("wUser") + ".TCATEGORYGOODS \n "
                                        +"                " + retrieve.getString("modSqlOrg") + " \n "
                                        +"                MINUS \n "
                                        +"               SELECT CATEGORY_CODE , GOODS_CODE \n "
                                        +"                 FROM " + retrieve.getString("swUser") + ".TCATEGORYGOODS \n "
                                        +"                " + retrieve.getString("modSqlStaging") + " \n "
                                        +"            )) ";

        //= log SQL -------------------------------------------------
        if (deleteSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTcategorygoods);
            }            
            deleteSqlLogTcategorygoods = 1;
        }
        return deleteSqlTcategorygoods;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( DELETE TPRESENTATION)
    * </PRE>
    * @param   
    * @return  String
    */

    private int deleteSqlLogTpresentation =  0;

    public String makeSqlDeleteTpresentation(RetrieveModel retrieve) throws StoreException{

        String deleteSqlTpresentation = " DELETE FROM " + retrieve.getString("wUser") + ".TPRESENTATION \n "
                                       +"       WHERE (( PRESENTATION_CODE ) IN ( \n "
                                       +"                SELECT PRESENTATION_CODE \n "
                                       +"                  FROM " + retrieve.getString("wUser") + ".TPRESENTATION \n "
                                       +"                " + retrieve.getString("modSqlOrg") + " \n "
                                       +"                " + retrieve.getString("whereSql") + " \n "
                                       +"                 MINUS \n "
                                       +"                SELECT PRESENTATION_CODE \n "
                                       +"                  FROM TPRESENTATION \n "
                                       +"                " + retrieve.getString("modSqlStaging") + " \n "
                                       +"                " + retrieve.getString("whereSql") + " \n "
                                       +"             )) ";


        //= log SQL -------------------------------------------------
        if (deleteSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTpresentation);
            }            
            deleteSqlLogTpresentation = 1;
        }
        return deleteSqlTpresentation;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( DELETE Tcategorygoods)
    * </PRE>
    * @param   
    * @return  String
    */

    private int deleteSqlLogTcategoryurl =  0;

    public String makeSqlDeleteTcategoryurl(RetrieveModel retrieve) throws StoreException{

        String deleteSqlTcategoryurl = " DELETE FROM " + retrieve.getString("wUser") + ".TCATEGORYURL \n "
                                      +"      " + retrieve.getString("modSqlOrg") + " \n ";

        //= log SQL -------------------------------------------------
        if (deleteSqlLogTcategoryurl == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTcategoryurl);
            }            
            deleteSqlLogTcategoryurl = 1;
        }
        return deleteSqlTcategoryurl;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update  )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */
    private final String updateSqlDispGoodsCnt =  " UPDATE TCATEGORY SET \n "
                                                 +"        DISPLAY_GOODS_CNT = ?, \n "
                                                 +"        MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') \n "
                                                 +"  WHERE CATEGORY_CODE = ? \n ";
    private int updateSqlDispGoodsCntLog =  0;

    public String makeSqlUpdateDispGoodsCnt() throws StoreException{
        //= log SQL -------------------------------------------------
        if (updateSqlDispGoodsCntLog == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(updateSqlDispGoodsCnt);
            }            
            updateSqlDispGoodsCntLog = 1;
        }
        return updateSqlDispGoodsCnt;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Update Tcategory )
    * </PRE>
    * @param
    * @return  String
    */
    private final String updateSqlTcategory =  " "
                                     +" UPDATE TCATEGORY SET  \n "
                                     +"        CATEGORY_NAME     = ?,  \n "
                                     +"        DISPLAY_YN        = ?,  \n "
                                     +"        DISPLAY_PRIORITY  = ?,  \n "
                                     +"        DISPLAY_GOODS_CNT = ?,  \n "
                                     +"        MODIFY_DATE       = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        MODIFY_ID         = ?  \n "
                                     +"  WHERE CATEGORY_CODE     = ?  \n "
                                     +"    AND P_CATEGORY_CODE   = ?  \n "
                                     +"    AND CATEGORY_GB       = ?  \n ";
    private int updateSqlLogTcategory =  0;

    public String makeSqlUpdate(Tcategory tcategory) throws StoreException{
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
    * @param
    * @return  String
    */
    private final String updateSqlTcategorygoods = " "
                                     +" UPDATE TCATEGORYGOODS SET  \n "
                                     +"        DISPLAY_YN        = ?,  \n "
                                     +"        DISPLAY_PRIORITY  = ?,  \n "
                                     +"        DISPLAY_START_DATE= TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        DISPLAY_END_DATE  = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        MODIFY_DATE       = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        MODIFY_ID         = ?  \n "
                                     +"  WHERE CATEGORY_CODE     = ?  \n "
                                     +"    AND GOODS_CODE        = ?  \n ";

    private int updateSqlLogTcategorygoods =  0;

    public String makeSqlUpdate(Tcategorygoods tcategorygoods) throws StoreException{
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
    * Desc : Make SQL ( Update Tpresentation )
    * </PRE>
    * @param
    * @return  String
    */

    private final String updateSqlTpresentation = " "
                                     +" UPDATE TPRESENTATION SET  \n "
                                     +"        PRESENTATION_KINDS = ?,  \n "
                                     +"        PRESENTATION_GB    = ?,  \n "
                                     +"        CATEGORY_CODE      = ?,  \n "
                                     +"        GOODS_CODE         = ?,  \n "
                                     +"        DISPLAY_YN         = ?,  \n "
                                     +"        DISPLAY_PRIORITY   = ?,  \n "
                                     +"        DISPLAY_START_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        DISPLAY_END_DATE   = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        PRESENTATION_DESC  = ?,  \n "
                                     +"        MODIFY_DATE        = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'),  \n "
                                     +"        MODIFY_ID          = ?  \n "
                                     +"  WHERE PRESENTATION_CODE  = ?  \n ";

    private int updateSqlLogTpresentation =  0;

    public String makeSqlUpdate(Tpresentation tpresentation) throws StoreException{
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
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategory
    * @return  String
    */

    private int insertSqlLog =  0;

    public String makeSqlInsert(Tcategory tcategory, RetrieveModel retrieve) throws StoreException{

        String insertSql = " INSERT INTO " + retrieve.getString("wUser") + ".TCATEGORY ( \n "
                          +"         CATEGORY_CODE, \n "
                          +"         CATEGORY_NAME, \n "
                          +"         P_CATEGORY_CODE, \n "
                          +"         DISPLAY_YN, \n "
                          +"         DISPLAY_PRIORITY, \n "
                          +"         CATEGORY_GB, \n "
                          +"         DISPLAY_GOODS_CNT, \n "
                          +"         INSERT_DATE, \n "
                          +"         INSERT_ID, \n "
                          +"         MODIFY_DATE, \n "
                          +"         MODIFY_ID ) \n "
                          +"  SELECT CATEGORY_CODE, \n "
                          +"         CATEGORY_NAME, \n "
                          +"         P_CATEGORY_CODE, \n "
                          +"         DISPLAY_YN, \n "
                          +"         DISPLAY_PRIORITY, \n "
                          +"         CATEGORY_GB, \n "
                          +"         DISPLAY_GOODS_CNT, \n "
                          +"         TO_DATE('" + retrieve.getString("insert_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n "
                          +"         INSERT_ID, \n "
                          +"         TO_DATE('" + retrieve.getString("modify_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n "
                          +"         MODIFY_ID  \n "
                          +"    FROM " + retrieve.getString("swUser") + ".TCATEGORY  \n "
                          +"   WHERE (( CATEGORY_CODE , P_CATEGORY_CODE, CATEGORY_GB ) IN (  \n "
                          +"            SELECT CATEGORY_CODE,  P_CATEGORY_CODE, CATEGORY_GB  \n "
                          +"              FROM TCATEGORY  \n "
                          +"            " + retrieve.getString("modSqlStaging") + "  \n "
                          +"            MINUS  \n "
                          +"            SELECT CATEGORY_CODE,  P_CATEGORY_CODE, CATEGORY_GB  \n "
                          +"              FROM " + retrieve.getString("wUser") + ".TCATEGORY  \n "
                          +"            " + retrieve.getString("modSqlOrg") + "  \n "
                          +"          ))  \n ";




        //= log SQL -------------------------------------------------
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
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategorygoods
    * @return  String
    */

    private int insertSqlLogTcategorygoods =  0;

    public String makeSqlInsert(Tcategorygoods tcategorygoods, RetrieveModel retrieve) throws StoreException{

        String insertSqlTcategorygoods = " INSERT INTO " + retrieve.getString("wUser") + ".TCATEGORYGOODS ( \n "
                                        +"         CATEGORY_CODE, \n "
                                        +"         GOODS_CODE, \n "
                                        +"         DISPLAY_YN, \n "
                                        +"         DISPLAY_PRIORITY, \n "
                                        +"         DISPLAY_START_DATE, \n "
                                        +"         DISPLAY_END_DATE, \n "
                                        +"         INSERT_DATE, \n "
                                        +"         INSERT_ID, \n "
                                        +"         MODIFY_DATE, \n "
                                        +"         MODIFY_ID ) \n "
                                        +"  SELECT CATEGORY_CODE, \n "
                                        +"         GOODS_CODE, \n "
                                        +"         DISPLAY_YN, \n "
                                        +"         DISPLAY_PRIORITY, \n "
                                        +"         DISPLAY_START_DATE, \n "
                                        +"         DISPLAY_END_DATE, \n "
                                        +"         TO_DATE('" + retrieve.getString("insert_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n "
                                        +"         INSERT_ID, \n "
                                        +"         TO_DATE('" + retrieve.getString("modify_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n "
                                        +"         MODIFY_ID  \n "
                                        +"    FROM " + retrieve.getString("swUser") + ".TCATEGORYGOODS  \n "
                                        +"   WHERE (( CATEGORY_CODE , GOODS_CODE ) IN (  \n "
                                        +"            SELECT CATEGORY_CODE,  GOODS_CODE  \n "
                                        +"              FROM TCATEGORYGOODS  \n "
                                        +"            " + retrieve.getString("modSqlStaging") + "  \n "
                                        +"            MINUS  \n "
                                        +"            SELECT CATEGORY_CODE,  GOODS_CODE  \n "
                                        +"              FROM " + retrieve.getString("wUser") + ".TCATEGORYGOODS  \n "
                                        +"            " + retrieve.getString("modSqlOrg") + "  \n "
                                        +"          ))  \n ";


        //= log SQL -------------------------------------------------
        if (insertSqlLogTcategorygoods == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTcategorygoods);
            }                        
            insertSqlLogTcategorygoods = 1;
        }
        return insertSqlTcategorygoods;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tpresentation
    * @return  String
    */

    private int insertSqlLogTpresentation =  0;

    public String makeSqlInsert(Tpresentation tpresentation, RetrieveModel retrieve) throws StoreException{

        String insertSqlTpresentation = " INSERT INTO " + retrieve.getString("wUser") + ".TPRESENTATION ( \n "
                                       +"         PRESENTATION_CODE, \n "
                                       +"         PRESENTATION_KINDS, \n "
                                       +"         PRESENTATION_GB, \n "
                                       +"         CATEGORY_CODE, \n "
                                       +"         GOODS_CODE, \n "
                                       +"         DISPLAY_YN, \n "
                                       +"         DISPLAY_PRIORITY, \n "
                                       +"         PRESENTATION_DESC, \n "
                                       +"         DISPLAY_START_DATE, \n "
                                       +"         DISPLAY_END_DATE, \n "
                                       +"         INSERT_DATE, \n "
                                       +"         INSERT_ID, \n "
                                       +"         MODIFY_DATE, \n "
                                       +"         MODIFY_ID ) \n "
                                       +"  SELECT PRESENTATION_CODE, \n "
                                       +"         PRESENTATION_KINDS, \n "
                                       +"         PRESENTATION_GB, \n "
                                       +"         CATEGORY_CODE, \n "
                                       +"         GOODS_CODE, \n "
                                       +"         DISPLAY_YN, \n "
                                       +"         DISPLAY_PRIORITY, \n "
                                       +"         PRESENTATION_DESC, \n "
                                       +"         DISPLAY_START_DATE, \n "
                                       +"         DISPLAY_END_DATE, \n "
                                       +"         TO_DATE('" + retrieve.getString("insert_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n "
                                       +"         INSERT_ID, \n "
                                       +"         TO_DATE('" + retrieve.getString("modify_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n "
                                       +"         MODIFY_ID  \n "
                                       +"    FROM TPRESENTATION  \n "
                                       +"   WHERE (( PRESENTATION_CODE ) IN (  \n "
                                       +"            SELECT PRESENTATION_CODE  \n "
                                       +"              FROM TPRESENTATION  \n "
                                       +"            " + retrieve.getString("modSqlStaging") + "  \n "
                                       +"            MINUS  \n "
                                       +"            SELECT PRESENTATION_CODE  \n "
                                       +"              FROM " + retrieve.getString("wUser") + ".TPRESENTATION  \n "
                                       +"            " + retrieve.getString("modSqlOrg") + "  \n "
                                       +"          ))  \n ";


        //= log SQL -------------------------------------------------
        if (insertSqlLogTpresentation == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTpresentation);
            }            
            insertSqlLogTpresentation = 1;
        }
        return insertSqlTpresentation;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Insert )
    * </PRE>
    * @param   Tcategoryurl
    * @return  String
    */

    private int insertSqlLogTcategoryurl =  0;

    public String makeSqlInsert(Tcategoryurl tcategoryurl, RetrieveModel retrieve) throws StoreException{

        String insertSqlTcategoryurl = " INSERT INTO " + retrieve.getString("wUser") + ".TCATEGORYURL ( \n "
                                      +"         CATEGORYURL_CODE, \n "
                                      +"         CATEGORYURL_KINDS, \n "
                                      +"         CATEGORY_CODE, \n "
                                      +"         NAME, \n "
                                      +"         TARGET_URL, \n "
                                      +"         IMAGE_URL, \n "
                                      +"         DISPLAY_YN, \n "
                                      +"         DISPLAY_PRIORITY, \n "
                                      +"         DISPLAY_START_DATE, \n "
                                      +"         DISPLAY_END_DATE, \n "
                                      +"         INSERT_DATE, \n "
                                      +"         INSERT_ID, \n "
                                      +"         MODIFY_DATE, \n "
                                      +"         MODIFY_ID ) \n "
                                      +"  SELECT CATEGORYURL_CODE, \n "
                                      +"         CATEGORYURL_KINDS, \n "
                                      +"         CATEGORY_CODE, \n "
                                      +"         NAME, \n "
                                      +"         TARGET_URL, \n "
                                      +"         IMAGE_URL, \n "
                                      +"         DISPLAY_YN, \n "
                                      +"         DISPLAY_PRIORITY, \n "
                                      +"         DISPLAY_START_DATE, \n "
                                      +"         DISPLAY_END_DATE, \n "
                                      +"         TO_DATE('" + retrieve.getString("insert_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE, \n "
                                      +"         INSERT_ID, \n "
                                      +"         TO_DATE('" + retrieve.getString("modify_date") + "', 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE, \n "
                                      +"         MODIFY_ID  \n "
                                      +"    FROM TCATEGORYURL  \n "
                                      +"  " + retrieve.getString("modSqlStaging") + "  \n ";


        //= log SQL -------------------------------------------------
        if (insertSqlLogTcategoryurl == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(insertSqlTcategoryurl);
            }            
            insertSqlLogTcategoryurl = 1;
        }
        return insertSqlTcategoryurl;
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetTcategory(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetTcategorygoods(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetTpresentation(ResultSet rset)  throws Exception {
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDispCheck5(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDispCheck4(ResultSet rset)  throws Exception {
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
    
    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDispCheck3(ResultSet rset)  throws Exception {
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

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDispCheck2(ResultSet rset)  throws Exception {
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


    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheetDispCheck1(ResultSet rset)  throws Exception {
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
    * @return  int
    */
    public int retrieveDispGoodsCntY(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               cnt         = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCntY());
            int index = 1;
            pstmt.setString(index++,  goods_code);
            
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) cnt = rset.getInt(1);            

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveDispGoodsCntY() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveDispGoodsCntY() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return cnt;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  int
    */
    public int retrieveDispGoodsCntN(Connection con, String goods_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               cnt         = 0;
        
        try {
            pstmt = con.prepareStatement(makeSqlDispGoodsCntN());
            int index = 1;
            pstmt.setString(index++,  goods_code);
            
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) cnt = rset.getInt(1);            

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveDispGoodsCntN() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveDispGoodsCntN() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return cnt;
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  int
    */
    public int retrieveDispCnt(Connection con, String category_code) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               cnt         = 0;
        
        try {
            pstmt = con.prepareStatement(makeSqlDispCnt());
            int index = 1;
            pstmt.setString(index++,  category_code);
            
            rset  = pstmt.executeQuery();

            if (rset!=null && rset.next()) cnt = rset.getInt(1);            

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveDispCnt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveDispCnt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return cnt;
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

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveTcategory(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlTcategory(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_TCATEGORY",makeSheet(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveTcategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveTcategory() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveTcategorygoods(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlTcategorygoods(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_TCATEGORYGOODS",makeSheetTcategorygoods(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveTcategorygoods() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveTcategorygoods() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveTpresentation(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlTpresentation(retrieve));


            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_TPRESENTATION",makeSheetTpresentation(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieveTpresentation() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieveTpresentation() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDispCheck5(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDispCheck5(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHECK5",makeSheetDispCheck5(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDispCheck4(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDispCheck4(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHECK4",makeSheetDispCheck4(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDispCheck3(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDispCheck3(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHECK3",makeSheetDispCheck3(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDispCheck2(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDispCheck2(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHECK2",makeSheetDispCheck2(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
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
    public RetrieveModel retrieveDispCheck1(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDispCheck1(retrieve));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DISPCHECK1",makeSheetDispCheck1(rset));

        }catch(SQLException se){
            log.error("[DeplyPopSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[DeplyPopSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCATEGORY
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int insert(Connection con, Tcategory tcategory, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        ResultSet rset  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();
            int index = 1;

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("insert_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modify_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlInsert(tcategory, retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCATEGORYGOODS
    * </PRE>
    * @param   Connection
    * @param   Tcategorygoods
    * @return  int
    */
    public int insert(Connection con, Tcategorygoods tcategorygoods, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        ResultSet rset  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("insert_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modify_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlInsert(tcategorygoods, retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCATEGORYURL
    * </PRE>
    * @param   Connection
    * @param   Tcategoryurl
    * @return  int
    */
    public int insert(Connection con, Tcategoryurl tcategoryurl, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        ResultSet rset  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("insert_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modify_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlInsert(tcategoryurl, retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return executedRtn;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TCATEGORYURL
    * </PRE>
    * @param   Connection
    * @param   Tcategoryurl
    * @return  int
    */
    public int insert(Connection con, Tpresentation tpresentation, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        ResultSet rset  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("insert_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modify_date")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlInsert(tpresentation, retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return executedRtn;
    }
    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update tcategory.disp_goods_cnt
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int updateDispGoodsCnt(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDispGoodsCnt());
            int index = 1;
            pstmt.setString(index++,tcategory.getDisplay_goods_cnt());
            pstmt.setString(index++,tcategory.getModify_date());
            pstmt.setString(index++,tcategory.getCategory_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getDisplay_goods_cnt() ); logString.append( "/" );
            logString.append( tcategory.getModify_date()   ); logString.append( "/" );
            logString.append( tcategory.getCategory_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update
    * </PRE>
    * @param   Connection
    * @param   Tcategory
    * @return  int
    */
    public int update(Connection con, Tcategory tcategory) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(tcategory));
            int index = 1;
            pstmt.setString(index++,tcategory.getCategory_name());
            pstmt.setString(index++,tcategory.getDisplay_yn());
            pstmt.setString(index++,tcategory.getDisplay_priority());
            pstmt.setString(index++,tcategory.getDisplay_goods_cnt());
            pstmt.setString(index++,tcategory.getModify_date());
            pstmt.setString(index++,tcategory.getModify_id());
            pstmt.setString(index++,tcategory.getCategory_code());
            pstmt.setString(index++,tcategory.getP_category_code());
            pstmt.setString(index++,tcategory.getCategory_gb());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategory.getCategory_name() ); logString.append( "/" );
            logString.append( tcategory.getDisplay_yn() ); logString.append( "/" );
            logString.append( tcategory.getDisplay_priority() ); logString.append( "/" );
            logString.append( tcategory.getDisplay_goods_cnt() ); logString.append( "/" );
            logString.append( tcategory.getModify_date() ); logString.append( "/" );
            logString.append( tcategory.getModify_id() ); logString.append( "/" );
            logString.append( tcategory.getCategory_code() ); logString.append( "/" );
            logString.append( tcategory.getP_category_code() ); logString.append( "/" );
            logString.append( tcategory.getCategory_gb() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update
    * </PRE>
    * @param   Connection
    * @param   Tcategorygoods
    * @return  int
    */
    public int update(Connection con, Tcategorygoods tcategorygoods) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(tcategorygoods));
            int index = 1;
            pstmt.setString(index++,tcategorygoods.getDisplay_yn());
            pstmt.setString(index++,tcategorygoods.getDisplay_priority());
            pstmt.setString(index++,tcategorygoods.getDisplay_start_date());
            pstmt.setString(index++,tcategorygoods.getDisplay_end_date());
            pstmt.setString(index++,tcategorygoods.getModify_date());
            pstmt.setString(index++,tcategorygoods.getModify_id());
            pstmt.setString(index++,tcategorygoods.getCategory_code());
            pstmt.setString(index++,tcategorygoods.getGoods_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tcategorygoods.getDisplay_yn() ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_priority() ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_start_date() ); logString.append( "/" );
            logString.append( tcategorygoods.getDisplay_end_date() ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_date() ); logString.append( "/" );
            logString.append( tcategorygoods.getModify_id() ); logString.append( "/" );
            logString.append( tcategorygoods.getCategory_code() ); logString.append( "/" );
            logString.append( tcategorygoods.getGoods_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update
    * </PRE>
    * @param   Connection
    * @param   Tpresentation
    * @return  int
    */
    public int update(Connection con, Tpresentation tpresentation) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlUpdate(tpresentation));
            int index = 1;
            pstmt.setString(index++,tpresentation.getPresentation_kinds());
            pstmt.setString(index++,tpresentation.getPresentation_gb());
            pstmt.setString(index++,tpresentation.getCategory_code());
            pstmt.setString(index++,tpresentation.getGoods_code());
            pstmt.setString(index++,tpresentation.getDisplay_yn());
            pstmt.setString(index++,tpresentation.getDisplay_priority());
            pstmt.setString(index++,tpresentation.getDisplay_start_date());
            pstmt.setString(index++,tpresentation.getDisplay_end_date());
            pstmt.setString(index++,"");
            pstmt.setString(index++,tpresentation.getModify_date());
            pstmt.setString(index++,tpresentation.getModify_id());
            pstmt.setString(index++,tpresentation.getPresentation_code());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tpresentation.getPresentation_kinds() ); logString.append( "/" );
            logString.append( tpresentation.getPresentation_gb() ); logString.append( "/" );
            logString.append( tpresentation.getCategory_code() ); logString.append( "/" );
            logString.append( tpresentation.getGoods_code() ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_yn() ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_priority() ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_start_date() ); logString.append( "/" );
            logString.append( tpresentation.getDisplay_end_date() ); logString.append( "/" );
            logString.append( "" ); logString.append( "/" );
            logString.append( tpresentation.getModify_date() ); logString.append( "/" );
            logString.append( tpresentation.getModify_id() ); logString.append( "/" );
            logString.append( tpresentation.getPresentation_code() ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategory
    * </PRE>
    * @param   Connection
    * @param   
    * @return  int
    */
    public int deleteTcategory(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement stmt       = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );   
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlDeleteTcategory(retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.deleteTcategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.deleteTcategory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, null);
        }
        return executedRtn;
    } 

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategorygoods
    * </PRE>
    * @param   Connection
    * @param   
    * @return  int
    */
    public int deleteTcategorygoods(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlOrg")  ); logString.append( "/" );   
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlDeleteTcategorygoods(retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.deleteTcategory() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.deleteTcategory() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, null);
        }
        return executedRtn;
    } 

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tpresentation
    * </PRE>
    * @param   Connection
    * @param   
    * @return  int
    */
    public int deleteTpresentation(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );   
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlDeleteTpresentation(retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.deleteTpresentation() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.deleteTpresentation() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt,  null, null);
        }
        return executedRtn;
    } 


    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tcategorygurl
    * </PRE>
    * @param   Connection
    * @param   
    * @return  int
    */
    public int deleteTcategoryurl(Connection con, RetrieveModel retrieve) throws StoreException{
        Statement stmt  = null;
        int executedRtn = 0;

        try {
            stmt = con.createStatement();
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( retrieve.getString("swUser")  ); logString.append( "/" );   
            logString.append( retrieve.getString("modSqlStaging")  ); logString.append( "/" );   
            logSave.info(logString.toString());

            executedRtn = stmt.executeUpdate(makeSqlDeleteTcategoryurl(retrieve));

        }catch(SQLException se){
            logSave.error("[DeployPopSvc.deleteTcategoryurl() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[DeployPopSvc.deleteTcategoryurl() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, null);
        }
        return executedRtn;
    } 


}
