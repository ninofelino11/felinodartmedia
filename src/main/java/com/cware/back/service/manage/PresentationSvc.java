
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
 * Presentation Service class
 *
 * @version 1.0, 2006/12/06
 */
public class PresentationSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PresentationSvc() {}

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
         String filter = "";
         
         try {
             
             filter = retrieve.getString("filter");
             if (filter.equals("0")){
                 pstmt = con.prepareStatement(makeSql());
             }else {
                 pstmt = con.prepareStatement(makeSqlFilter());
             }

             int index = 1;
             pstmt.setString(index++,retrieve.getString("cate_code"));
             pstmt.setString(index++,retrieve.getString("cate_code"));
             //pstmt.setString(index++,retrieve.getString("cate_code"));
             //pstmt.setString(index++,retrieve.getString("cate_code"));
             //pstmt.setString(index++,retrieve.getString("cate_code"));
             //pstmt.setString(index++,retrieve.getString("cate_code"));

             rset  = pstmt.executeQuery();

             retrieve.put("RESULT",makeSheet(rset));

         }catch(SQLException se){
             log.error("[PresentationSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             log.error("[PresentationSvc.retrieve() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return retrieve;
     }

    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT T.CATEGORY_CODE, \n");
        sb.append("       T.CATEGORY_NAME, \n");
        sb.append("       T.CODE_MGROUP AS CATEGORY_KINDS, \n");
        sb.append("       T.CODE_NAME AS CATEGORY_KINDS_NAME, \n");
        sb.append("       T.REMARK AS DISPLAY_CNT, \n");
        sb.append("       T.COUNT AS DISPLAYED_CNT \n");
        sb.append(" FROM ( \n");
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP   = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE   >  SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '00' \n");
        sb.append("            AND B.CODE_LGROUP = 'B111' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
        sb.append("         UNION ALL \n");
/**        
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '01' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4 ) = '00000' \n");
        sb.append("            AND B.CODE_LGROUP = 'B112' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
        sb.append("         UNION ALL \n");
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '01' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 6 ) = '000' \n");
        sb.append("            AND B.CODE_LGROUP = 'B113' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
        sb.append("         UNION ALL \n");
**/        
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '01' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 6 ) <> '000' \n");
        sb.append("            AND B.CODE_LGROUP = 'B114' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
        /**          
        sb.append("         UNION ALL \n");      
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '02' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
        sb.append("            AND B.CODE_LGROUP = 'B116' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
        sb.append("         UNION ALL \n");
        sb.append("         SELECT A.CATEGORY_CODE , \n");
        sb.append("                A.CATEGORY_NAME , \n");
        sb.append("                B.CODE_MGROUP , \n");
        sb.append("                B.CODE_NAME , \n");
        sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
        sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
        sb.append("                   FROM TPRESENTATION C \n");
        sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
        sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
        sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
        sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
        sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
        sb.append("               ) AS COUNT \n");
        sb.append("           FROM TCATEGORY A, \n");
        sb.append("                TCODE B \n");
        sb.append("          WHERE A.CATEGORY_GB = '02' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
        sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4 ) = '00000' \n");
        sb.append("            AND B.CODE_LGROUP = 'B117' \n");
        sb.append("            AND B.USE_YN = '1' \n");
        sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
**/           
        sb.append("      ) T \n");
        sb.append(" ORDER BY T.CATEGORY_CODE, T.CODE_MGROUP  \n");     

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }
    
    /**
     * <PRE>
     * Desc : Make SQL ; Master
     * </PRE>
     * @param   RetrieveModel
     * @return  String
     */
     private String makeSqlFilter() throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT T.CATEGORY_CODE, \n");
         sb.append("       T.CATEGORY_NAME, \n");
         sb.append("       T.CODE_MGROUP AS CATEGORY_KINDS, \n");
         sb.append("       T.CODE_NAME AS CATEGORY_KINDS_NAME, \n");
         sb.append("       T.REMARK AS DISPLAY_CNT, \n");
         sb.append("       T.COUNT AS DISPLAYED_CNT \n");
         sb.append(" FROM ( \n");
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP   = C.PRESENTATION_KINDS \n");
         //sb.append("                    AND SUBSTR(A.CATEGORY_CODE, 6 ) = '000' \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE   >  SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '00' \n");
         sb.append("            AND B.CODE_LGROUP = 'B111' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
         sb.append("         UNION ALL \n");
/**         
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '01' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4 ) = '00000' \n");
         sb.append("            AND B.CODE_LGROUP = 'B112' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
         sb.append("         UNION ALL \n");
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '01' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 6 ) = '000' \n");
         sb.append("            AND B.CODE_LGROUP = 'B113' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
         sb.append("         UNION ALL \n");
**/         
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '01' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 6 ) <> '000' \n");
         sb.append("            AND B.CODE_LGROUP = 'B114' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
/**         
         sb.append("         UNION ALL \n");
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '02' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
         sb.append("            AND B.CODE_LGROUP = 'B116' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
         sb.append("         UNION ALL \n");
         sb.append("         SELECT A.CATEGORY_CODE , \n");
         sb.append("                A.CATEGORY_NAME , \n");
         sb.append("                B.CODE_MGROUP , \n");
         sb.append("                B.CODE_NAME , \n");
         sb.append("                TO_NUMBER(B.REMARK) AS REMARK , \n");
         sb.append("               ( SELECT COUNT(C.GOODS_CODE) \n");
         sb.append("                   FROM TPRESENTATION C \n");
         sb.append("                  WHERE C.DISPLAY_YN = '1' \n");
         sb.append("                    AND A.CATEGORY_CODE = C.CATEGORY_CODE \n");
         sb.append("                    AND B.CODE_MGROUP = C.PRESENTATION_KINDS \n");
         sb.append("                    AND C.DISPLAY_START_DATE <= SYSDATE \n");
         sb.append("                    AND C.DISPLAY_END_DATE  > SYSDATE \n");
         sb.append("               ) AS COUNT \n");
         sb.append("           FROM TCATEGORY A, \n");
         sb.append("                TCODE B \n");
         sb.append("          WHERE A.CATEGORY_GB = '02' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 2, 2) <> '00' \n");
         sb.append("            AND SUBSTR(A.CATEGORY_CODE, 4 ) = '00000' \n");
         sb.append("            AND B.CODE_LGROUP = 'B117' \n");
         sb.append("            AND A.CATEGORY_CODE LIKE ?||'%' \n");
**/         
         sb.append("      ) T \n");
         sb.append(" WHERE T.REMARK > T.COUNT  \n");
         sb.append(" ORDER BY T.CATEGORY_CODE, T.CODE_MGROUP  \n");

         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug(sb.toString());
         }
         return sb.toString();
     }
     
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
              pstmt = con.prepareStatement(makeSqlDetail());

              int index = 1;
              pstmt.setString(index++,retrieve.getString("category_code"));
              pstmt.setString(index++,retrieve.getString("category_kinds"));
              
              rset  = pstmt.executeQuery();

              retrieve.put("RESULT",makeSheetDetail(rset));

          }catch(SQLException se){
              log.error("[PresentationSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              log.error("[PresentationSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return retrieve;
      }
    /**
    * <PRE>
    * Desc : Make SQL ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlDetail() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.GOODS_CODE, \n");
        sb.append("       B.GOODS_NAME, \n");
        sb.append("       A.DISPLAY_YN, \n");
        sb.append("       TO_CHAR(A.DISPLAY_START_DATE,'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_START_DATE, \n");
        sb.append("       TO_CHAR(A.DISPLAY_END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS DISPLAY_END_DATE \n");
        sb.append("  FROM TPRESENTATION A, \n");
        sb.append("       TGOODS B \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE \n");
        sb.append("   AND A.CATEGORY_CODE = ? \n");
        sb.append("   AND A.PRESENTATION_KINDS = ? \n");
        sb.append("   AND A.DISPLAY_START_DATE <= SYSDATE  \n");
        sb.append("   AND A.DISPLAY_END_DATE  > SYSDATE \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

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


}
