
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
import com.cware.back.entity.table.Tfaq;

/**
 * Register faq Service class
 *
 * @version 1.0, 2006/12/05
 */
public class FaqSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public FaqSvc() {}

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
//            pstmt.setString(index++, retrieve.getString("frDate"));
//            pstmt.setString(index++, retrieve.getString("toDate"));
            pstmt.setString(index++, retrieve.getString("faq_kinds"));
            pstmt.setString(index++, retrieve.getString("faq_code"));
            pstmt.setString(index++, retrieve.getString("display_yn"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[FaqSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[FaqSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.FAQ_SEQ, \n");
        sb.append("       A.FAQ_KINDS, \n");
        sb.append("       NVL(B.REMARK1,'') AS FAQ_KINDS_NAME, \n");
        sb.append("       A.FAQ_CODE, \n");
        sb.append("       NVL((SELECT CODE_NAME \n");
        sb.append("                FROM TCODE \n");
        sb.append("             WHERE CODE_LGROUP = B.REMARK \n");
        sb.append("                AND CODE_MGROUP = A.FAQ_CODE ),'') AS FAQ_CODE_NAME, \n");
        sb.append("       A.QUESTION, \n");
        sb.append("       A.ANSWER, \n");
        sb.append("       A.SEARCH_CNT, \n");
        sb.append("       A.DISPLAY_YN, \n");
        sb.append("       A.DISPLAY_PRIORITY, \n");
        sb.append("       A.QUICK_YN, \n");
        sb.append("       TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE , \n");
        sb.append("       A.INSERT_ID, \n");
        sb.append("       TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE , \n");
        sb.append("       A.MODIFY_ID \n");
        sb.append("  FROM TFAQ  A, \n");
        sb.append("       TCODE B \n");
        sb.append(" WHERE B.CODE_LGROUP =  'B830' \n");
        sb.append("   AND B.CODE_MGROUP =  A.FAQ_KINDS \n");
//        sb.append("   AND A.INSERT_DATE >= TO_DATE(?, 'YYYY/MM/DD') \n");
//        sb.append("   AND A.INSERT_DATE <  TO_DATE(?,'YYYY/MM/DD') +1 \n");
        sb.append("   AND A.FAQ_KINDS LIKE ?||'%' \n");
        sb.append("   AND A.FAQ_CODE  LIKE ?||'%' \n");
        sb.append("   AND A.DISPLAY_YN LIKE ?||'%' \n");
        sb.append("ORDER BY A.DISPLAY_PRIORITY,   \n");
        sb.append("		    A.FAQ_SEQ   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("frDate: "     + retrieve.getString("frDate"));
            log.debug("toDate: "     + retrieve.getString("toDate"));
            log.debug("faq_kinds: "  + retrieve.getString("faq_kinds"));
            log.debug("faq_code: "   + retrieve.getString("faq_code"));
            log.debug("display_yn: " + retrieve.getString("display_yn"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Edit retrieval result
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
    * Desc : Insert TFAQ
    */
    public int insert(Connection con, Tfaq faq) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int               executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert());

            int index = 1;
            pstmt.setLong  (index++, faq.getFaq_seq());
            pstmt.setString(index++, faq.getFaq_kinds());
            pstmt.setString(index++, faq.getFaq_code());
            pstmt.setString(index++, faq.getQuestion());
            pstmt.setString(index++, faq.getAnswer());
            pstmt.setString(index++, faq.getDisplay_yn());
            pstmt.setLong  (index++, faq.getDisplay_priority());
            pstmt.setString(index++, faq.getInsert_id());
            pstmt.setString(index++, faq.getModify_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( faq.getFaq_seq());            logString.append( "/" );
            logString.append( faq.getFaq_kinds());          logString.append( "/" );
            logString.append( faq.getFaq_code());           logString.append( "/" );
            logString.append( faq.getQuestion());           logString.append( "/" );
            logString.append( faq.getAnswer());             logString.append( "/" );
            logString.append( faq.getDisplay_yn());         logString.append( "/" );
            logString.append( faq.getDisplay_priority());   logString.append( "/" );
            logString.append( faq.getInsert_id());          logString.append( "/" );
            logString.append( faq.getModify_id());          logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[FaqSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[FaqSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TFAQ)
    * </PRE>
    */
    public String makeSqlInsert() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TFAQ(  \n ");
        sb.append("         FAQ_SEQ,   \n ");
        sb.append("         FAQ_KINDS, \n ");
        sb.append("         FAQ_CODE,  \n ");
        sb.append("         QUESTION,  \n ");
        sb.append("         ANSWER,    \n ");
        sb.append("         DISPLAY_YN, \n ");
        sb.append("         DISPLAY_PRIORITY, \n ");
        sb.append("         INSERT_DATE,  \n ");
        sb.append("         INSERT_ID,  \n ");
        sb.append("         MODIFY_DATE,  \n ");
        sb.append("         MODIFY_ID  )  \n ");
        sb.append("  VALUES( \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ?, \n ");
        sb.append("         SYSDATE, \n ");
        sb.append("         ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Update TFAQ
    * </PRE>
    */
    public int update(Connection con, Tfaq faq) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate());
            int index = 1;
            pstmt.setString(index++, faq.getFaq_kinds());
            pstmt.setString(index++, faq.getFaq_code());
            pstmt.setString(index++, faq.getQuestion());
            pstmt.setString(index++, faq.getAnswer());
            pstmt.setString(index++, faq.getDisplay_yn());
            pstmt.setLong  (index++, faq.getDisplay_priority());
            pstmt.setString(index++, faq.getModify_id());
            pstmt.setLong  (index++, faq.getFaq_seq());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( faq.getFaq_kinds());        logString.append( "/" );
            logString.append( faq.getFaq_code());         logString.append( "/" );
            logString.append( faq.getQuestion());         logString.append( "/" );
            logString.append( faq.getAnswer());           logString.append( "/" );
            logString.append( faq.getDisplay_yn());       logString.append( "/" );
            logString.append( faq.getDisplay_priority()); logString.append( "/" );
            logString.append( faq.getModify_id());        logString.append( "/" );
            logString.append( faq.getFaq_seq());          logString.append( "/" );
            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[FaqSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[FaqSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TFAQ 변경 )
    * </PRE>
    * @param
    * @return  String
    */
    public String makeSqlUpdate() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TFAQ SET \n ");
        sb.append("         FAQ_KINDS  = ?, \n ");
        sb.append("         FAQ_CODE   = ?, \n ");
        sb.append("         QUESTION   = ?, \n ");
        sb.append("         ANSWER     = ?, \n ");
        sb.append("         DISPLAY_YN = ?, \n ");
        sb.append("         DISPLAY_PRIORITY = ?, \n ");
        sb.append("         MODIFY_DATE = SYSDATE, \n ");
        sb.append("         MODIFY_ID   = ? \n ");
        sb.append("   WHERE FAQ_SEQ    = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();
    }

}
