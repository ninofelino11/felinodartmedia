
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
import com.cware.back.entity.table.Tmedia;

/**
 * Register Media Service class
 *
 * @version 1.0, 2006/07/10
 */
public class MediaSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MediaSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @param   media_gb                      : Media 구분
    * @param   media_code                    : Media code
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.MEDIA_CODE, \n");
        sb.append("       A.MEDIA_YEAR, \n");
        sb.append("       A.MEDIA_NAME, \n");
        sb.append("       A.MEDIA_GB, \n");
        sb.append("       A.MEDIA_STYLE, \n");
        sb.append("       A.MEDIA_MONTH, \n");
        sb.append("       A.USE_YN, \n");
        sb.append("       A.CATALOG_PAGE, \n");
        sb.append("       A.CATALOG_SIZE, \n");
        sb.append("       A.WEIGHT, \n");
        sb.append("       A.PAPER_QUALITY, \n");
        sb.append("       A.PRINT_MED, \n");
        sb.append("       TO_CHAR(A.VALID_BDATE, 'YYYY/MM/DD HH24:MI:SS') VALID_BDATE, \n");
        sb.append("       TO_CHAR(A.VALID_EDATE, 'YYYY/MM/DD HH24:MI:SS') VALID_EDATE, \n");
        sb.append("       TO_CHAR(A.SEND_DATE, 'YYYY/MM/DD') SEND_DATE, \n");
        sb.append("       A.GOODS_CNT, \n");
        sb.append("       A.PRODUCT_CNT, \n");
        sb.append("       A.SEND_NOTE, \n");
        sb.append("       A.SPECIAL_NOTE, \n");
        sb.append("       TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE, \n");
        sb.append("       A.INSERT_ID, \n");
        sb.append("       TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE, \n");
        sb.append("       A.MODIFY_ID, \n");
        sb.append("       B.GOODS_CODE, \n");
        sb.append("       B.GOODS_NAME, \n");
        sb.append("       A.PAGE_COST \n");
        sb.append("  FROM TMEDIA A, \n");
        sb.append("       TGOODS B \n");
        sb.append(" WHERE A.MEDIA_GB LIKE ? \n");
        sb.append("   AND A.MEDIA_CODE LIKE ? \n");
        sb.append("   AND A.GOODS_CODE=B.GOODS_CODE(+)      \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("media_gb    : " + retrieve.getString("media_gb"));
            log.debug("media_code  : " + retrieve.getString("media_code"));
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( 아이디 중복 체크 )
    * </PRE>
    * @param   Tmedia
    * @return  String
    */
    public String makeSqlDupCheck(Tmedia media) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT COUNT(MEDIA_CODE) \n ");
        sb.append("  FROM TMEDIA \n ");
        sb.append(" WHERE MEDIA_CODE = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( check Exist Media Code )
    * </PRE>
    * @param   Tmedia
    * @return  String
    */
    public String makeSqlMediaCode() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT COUNT(MEDIA_NAME)   				\n ");
        sb.append("   FROM TMEDIA   						\n ");
        sb.append("  WHERE UPPER(MEDIA_CODE) = UPPER(?) 	\n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tmedia 에 등록 )
    * </PRE>
    * @param   Tmedia
    * @return  String
    */
    public String makeSqlInsert(Tmedia media) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("INSERT INTO TMEDIA ( \n ");
        sb.append("       MEDIA_CODE, \n ");
        sb.append("       MEDIA_YEAR, \n ");
        sb.append("       MEDIA_NAME, \n ");
        sb.append("       MEDIA_GB, \n ");
        sb.append("       MEDIA_STYLE, \n ");
        sb.append("       MEDIA_MONTH, \n ");
        sb.append("       USE_YN, \n ");
        sb.append("       CATALOG_PAGE, \n ");
        sb.append("       CATALOG_SIZE, \n ");
        sb.append("       WEIGHT, \n ");
        sb.append("       PAPER_QUALITY, \n ");
        sb.append("       PRINT_MED, \n ");
        sb.append("       VALID_BDATE, \n ");
        sb.append("       VALID_EDATE, \n ");
        sb.append("       SEND_DATE, \n ");
        sb.append("       GOODS_CNT, \n ");
        sb.append("       PRODUCT_CNT, \n ");
        sb.append("       SEND_NOTE, \n ");
        sb.append("       SPECIAL_NOTE, \n ");
        sb.append("       GOODS_CODE, \n ");
        sb.append("       PAGE_COST, \n ");
        sb.append("       INSERT_DATE, \n ");
        sb.append("       INSERT_ID, \n ");
        sb.append("       MODIFY_DATE, \n ");
        sb.append("       MODIFY_ID ) \n ");
        sb.append("VALUES ( \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");

        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("        TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("        TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        ?, \n ");
        sb.append("        SYSDATE, \n ");
        sb.append("        ?, \n ");
        sb.append("        SYSDATE, \n ");
        sb.append("        ? ) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tmedia 변경 )
    * </PRE>
    * @param   Tmedia
    * @return  String
    */
    public String makeSqlUpdate(Tmedia media) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("UPDATE TMEDIA SET \n ");
        sb.append(   "    MEDIA_YEAR    = ?, \n ");
        sb.append(   "    MEDIA_NAME    = ?, \n ");
        sb.append(   "    MEDIA_GB      = ?, \n ");
        sb.append(   "    MEDIA_STYLE   = ?, \n ");
        sb.append(   "    MEDIA_MONTH   = ?, \n ");
        sb.append(   "    USE_YN        = ?, \n ");
        sb.append(   "    CATALOG_PAGE  = ?, \n ");
        sb.append(   "    CATALOG_SIZE  = ?, \n ");
        sb.append(   "    WEIGHT        = ?, \n ");
        sb.append(   "    PAPER_QUALITY = ?, \n ");
        sb.append(   "    PRINT_MED     = ?, \n ");
        sb.append(   "    VALID_BDATE   = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append(   "    VALID_EDATE   = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append(   "    SEND_DATE     = TO_DATE(?, 'YYYY/MM/DD'), \n ");
        sb.append(   "    GOODS_CNT     = ?, \n ");
        sb.append(   "    PRODUCT_CNT   = ?, \n ");
        sb.append(   "    SEND_NOTE     = ?, \n ");
        sb.append(   "    SPECIAL_NOTE  = ?, \n ");
        sb.append(   "    GOODS_CODE    = ?, \n ");
        sb.append(   "    PAGE_COST    = ?, \n ");
        sb.append(   "    MODIFY_DATE   = SYSDATE, \n ");
        sb.append(   "    MODIFY_ID     = ? \n ");
        sb.append(" WHERE MEDIA_CODE  = ? \n ");
        sb.append("   AND MODIFY_DATE = TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') \n ");
        sb.append("   AND MODIFY_ID   = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
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
    * @param   media_gb                      : Media 구분
    * @param   media_code                    : Media code
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("media_gb"  ));
            pstmt.setString(index++, retrieve.getString("media_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[MediaSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[MediaSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tmedia
    * </PRE>
    * @param   Connection
    * @param   Tmedia
    * @return  int
    */
    public int insert(Connection con, Tmedia media) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(media));

            int index = 1;
            pstmt.setString(index++, media.getMedia_code   ());
            pstmt.setString(index++, media.getMedia_year   ());
            pstmt.setString(index++, media.getMedia_name   ());
            pstmt.setString(index++, media.getMedia_gb     ());
            pstmt.setString(index++, media.getMedia_style  ());
            pstmt.setString(index++, media.getMedia_month  ());
            pstmt.setString(index++, media.getUse_yn       ());
            pstmt.setString(index++, media.getCatalog_page ());
            pstmt.setString(index++, media.getCatalog_size ());
            pstmt.setString(index++, media.getWeight       ());
            pstmt.setString(index++, media.getPaper_quality());
            pstmt.setString(index++, media.getPrint_med    ());
            pstmt.setString(index++, media.getValid_bdate  ());
            pstmt.setString(index++, media.getValid_edate  ());
            pstmt.setString(index++, media.getSend_date    ());
            pstmt.setString(index++, media.getGoods_cnt    ());
            pstmt.setString(index++, media.getProduct_cnt  ());
            pstmt.setString(index++, media.getSend_note    ());
            pstmt.setString(index++, media.getSpecial_note ());
            pstmt.setString(index++, media.getGoods_code   ());
            pstmt.setString(index++, media.getPage_cost    ());
            pstmt.setString(index++, media.getInsert_id    ());
            pstmt.setString(index++, media.getModify_id    ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( media.getMedia_code   ()); logString.append( "/" );
            logString.append( media.getMedia_year   ()); logString.append( "/" );
            logString.append( media.getMedia_name   ()); logString.append( "/" );
            logString.append( media.getMedia_gb     ()); logString.append( "/" );
            logString.append( media.getMedia_style  ()); logString.append( "/" );
            logString.append( media.getMedia_month  ()); logString.append( "/" );
            logString.append( media.getUse_yn       ()); logString.append( "/" );
            logString.append( media.getCatalog_page ()); logString.append( "/" );
            logString.append( media.getCatalog_size ()); logString.append( "/" );
            logString.append( media.getWeight       ()); logString.append( "/" );
            logString.append( media.getPaper_quality()); logString.append( "/" );
            logString.append( media.getPrint_med    ()); logString.append( "/" );
            logString.append( media.getValid_bdate  ()); logString.append( "/" );
            logString.append( media.getValid_edate  ()); logString.append( "/" );
            logString.append( media.getSend_date    ()); logString.append( "/" );
            logString.append( media.getGoods_cnt    ()); logString.append( "/" );
            logString.append( media.getProduct_cnt  ()); logString.append( "/" );
            logString.append( media.getSend_note    ()); logString.append( "/" );
            logString.append( media.getSpecial_note ()); logString.append( "/" );
            logString.append( media.getGoods_code   ()); logString.append( "/" );
            logString.append( media.getPage_cost    ()); logString.append( "/" );
            logString.append( media.getInsert_id    ()); logString.append( "/" );
            logString.append( media.getModify_id    ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MediaSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MediaSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tmedia
    * </PRE>
    * @param   Connection
    * @param   Tmedia
    * @return  int
    */
    public int update(Connection con, Tmedia media) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(media));
            int index = 1;
            pstmt.setString(index++, media.getMedia_year   ());
            pstmt.setString(index++, media.getMedia_name   ());
            pstmt.setString(index++, media.getMedia_gb     ());
            pstmt.setString(index++, media.getMedia_style  ());
            pstmt.setString(index++, media.getMedia_month  ());
            pstmt.setString(index++, media.getUse_yn       ());
            pstmt.setString(index++, media.getCatalog_page ());
            pstmt.setString(index++, media.getCatalog_size ());
            pstmt.setString(index++, media.getWeight       ());
            pstmt.setString(index++, media.getPaper_quality());
            pstmt.setString(index++, media.getPrint_med    ());
            pstmt.setString(index++, media.getValid_bdate  ());
            pstmt.setString(index++, media.getValid_edate  ());
            pstmt.setString(index++, media.getSend_date    ());
            pstmt.setString(index++, media.getGoods_cnt    ());
            pstmt.setString(index++, media.getProduct_cnt  ());
            pstmt.setString(index++, media.getSend_note    ());
            pstmt.setString(index++, media.getSpecial_note ());
            pstmt.setString(index++, media.getGoods_code   ());
            pstmt.setString(index++, media.getPage_cost    ());
            pstmt.setString(index++, media.getModify_id    ());
            pstmt.setString(index++, media.getMedia_code   ());
            pstmt.setString(index++, media.getModify_date  ());
            pstmt.setString(index++, media.getInsert_id    ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( media.getMedia_year   ()); logString.append( "/" );
            logString.append( media.getMedia_name   ()); logString.append( "/" );
            logString.append( media.getMedia_gb     ()); logString.append( "/" );
            logString.append( media.getMedia_style  ()); logString.append( "/" );
            logString.append( media.getMedia_month  ()); logString.append( "/" );
            logString.append( media.getUse_yn       ()); logString.append( "/" );
            logString.append( media.getCatalog_page ()); logString.append( "/" );
            logString.append( media.getCatalog_size ()); logString.append( "/" );
            logString.append( media.getWeight       ()); logString.append( "/" );
            logString.append( media.getPaper_quality()); logString.append( "/" );
            logString.append( media.getPrint_med    ()); logString.append( "/" );
            logString.append( media.getValid_bdate  ()); logString.append( "/" );
            logString.append( media.getValid_edate  ()); logString.append( "/" );
            logString.append( media.getSend_date    ()); logString.append( "/" );
            logString.append( media.getGoods_cnt    ()); logString.append( "/" );
            logString.append( media.getProduct_cnt  ()); logString.append( "/" );
            logString.append( media.getSend_note    ()); logString.append( "/" );
            logString.append( media.getSpecial_note ()); logString.append( "/" );
            logString.append( media.getGoods_code   ()); logString.append( "/" );
            logString.append( media.getPage_cost    ()); logString.append( "/" );
            logString.append( media.getModify_id    ()); logString.append( "/" );
            logString.append( media.getMedia_code   ()); logString.append( "/" );
            logString.append( media.getModify_date  ()); logString.append( "/" );
            logString.append( media.getInsert_id    ()); logString.append( "/" );

            logSave.info("\n" + logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MediaSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MediaSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Select Max Media Code -------------------------------------------------
    /**
    * <PRE>
    * Desc : Select Max Media Code of Tmedia
    * </PRE>
    * @param   Connection
    * @param   Tmedia
    * @return  int
    */
    public int getMediaCode(Connection con, Tmedia media) throws StoreException{
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        int				  rtn 	= 0;
        String media_code = "";

        try {

            pstmt = con.prepareStatement(makeSqlMediaCode());
            int index = 1;
            pstmt.setString(index++, media.getMedia_year   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( media.getMedia_year   ()); logString.append( "/" );

            rset  = pstmt.executeQuery();
            if (rset != null && rset.next()){
            	rtn = rset.getInt(1);
            }

        }catch(SQLException se){
            logSave.error("[MediaSvc.getMediaCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MediaSvc.getMediaCode() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return rtn;
    }
}
