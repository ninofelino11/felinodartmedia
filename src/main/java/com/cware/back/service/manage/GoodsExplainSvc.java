
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
import com.cware.back.entity.table.Tgoodsshare;

/**
 * 제조업체등록 Service class
 *
 * @version 1.0, 2006/06/21
 */
public class GoodsExplainSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public GoodsExplainSvc() {}


    /**
    * <PRE>
    * Desc : Make SQL ( Tgoodsshare 에 등록 )
    * </PRE>
    * @param   Tgoodsshare
    * @return  String
    */
    public String makeSqlInsert(Tgoodsshare tgoodsshare) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TGOODSSHARE (    \n ");
        sb.append("              GOODS_CODE     , \n ");
        sb.append("              SEQ_NO         , \n ");
        sb.append("              TITLE1         , \n ");
        sb.append("              CONTENTS       , \n ");
        sb.append("              INSERT_ID      , \n ");
        sb.append("              INSERT_DATE    , \n ");
        sb.append("              MODIFY_ID      , \n ");
        sb.append("              MODIFY_DATE    ) \n ");
        sb.append("       VALUES (                \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE, \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE) \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tgoodsshare 변경 )
    * </PRE>
    * @param   Tgoodsshare
    * @return  String
    */
    public String makeSqlUpdate(Tgoodsshare tgoodsshare) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TGOODSSHARE           \n ");
        sb.append("     SET TITLE1            = ?,  \n ");
        sb.append("         CONTENTS         = ?,  \n ");
        sb.append("         MODIFY_ID        = ?,  \n ");
        sb.append("         MODIFY_DATE      = SYSDATE \n ");
        sb.append("   WHERE GOODS_CODE       = ?  \n ");
        sb.append("     AND SEQ_NO           = ?  \n ");

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

        sb.append("SELECT A.GOODS_CODE AS GOODS_CODE,    \n");
        sb.append("       A.GOODS_NAME                   \n");
        sb.append("  FROM TGOODS A                       \n");
        sb.append(" WHERE A.GOODS_CODE = ?               \n");
        sb.append(" UNION ALL                            \n");
        sb.append("SELECT A.IN_GOODS_CODE AS GOODS_CODE, \n");
        sb.append("       B.GOODS_NAME                   \n");
        sb.append("  FROM TSETGOODS A, TGOODS B          \n");
        sb.append(" WHERE A.IN_GOODS_CODE = B.GOODS_CODE \n");
        sb.append("   AND A.GOODS_CODE = ?               \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDt1( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                         \n");
        sb.append("          B.SORT_SEQ,                           \n");
        sb.append("          A.DESCRIBE_CODE,                      \n");
        sb.append("          B.DESCRIBE_TITLE,                     \n");
        sb.append("          A.DESCRIBE_EXT,                       \n");
        sb.append("          A.WEB_FLAG                            \n");
        sb.append("     FROM TDESCRIBE      A,                     \n");
        sb.append("          TDESCRIBECODE  B                      \n");
        sb.append("    WHERE A.DESCRIBE_CODE = B.DESCRIBE_CODE     \n");
        sb.append("      AND A.GOODS_CODE    = ?                   \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT1
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDt1(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt1(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT1",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrieveDt1() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrieveDt1() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDt2( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT A.GOODS_CODE,                                                                      \n");
        sb.append("          A.GOODS_NAME,                                                                      \n");
        sb.append("          B.LGROUP_NAME,                                                                     \n");
        sb.append("          B.MGROUP_NAME,                                                                     \n");
        sb.append("          B.SGROUP_NAME,                                                                     \n");
        sb.append("          B.DGROUP_NAME,                                                                     \n");
//        sb.append("          DECODE(A.SET_YN, '1', '', TCODE_NAME('B021',A.DELY_TYPE )) AS DELY_TYPE,           \n");
        sb.append("          TCODE_NAME('B021',A.DELY_TYPE) AS DELY_TYPE,           							\n");
        sb.append("          C.ENTP_NAME,                                                                       \n");
        sb.append("          D.ENTP_MAN_NAME,                                                                   \n");
        sb.append("          D.ENTP_MAN_LEVEL,                                                                  \n");
        sb.append("          D.ENTP_MAN_DDD,                                                                    \n");
        sb.append("          D.ENTP_MAN_TEL1,                                                                   \n");
        sb.append("          D.ENTP_MAN_TEL2,                                                                   \n");
        sb.append("          D.ENTP_MAN_TEL3,                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX1,                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX2,                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX3,                                                                   \n");
        sb.append("          D.ENTP_MAN_HP1,                                                                    \n");
        sb.append("          D.ENTP_MAN_HP2,                                                                    \n");
        sb.append("          D.ENTP_MAN_HP3,                                                                    \n");
        sb.append("          E.MD_NAME,                                                                         \n");
        sb.append("          TCODE_NAME('B032',A.SALE_GB )AS SALE_GB,                                            \n");
//        sb.append("          G.SALE_PRICE,                                                                      \n");
        sb.append("          FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'6') AS SALE_PRICE,                      \n");
        sb.append("          TO_CHAR( DECODE( A.SALE_GB, '00', TO_DATE(NULL), F.INSERT_DATE ) , 'YYYY/MM/DD HH24:MI:SS') AS SALE_NO_DATE,   \n");
        sb.append("          A.FIRST_BROAD_DATE,                                                                \n");
        sb.append("          TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,                                \n");
//        sb.append("          G.SAVEAMT_RATE / 100 AS SAVEAMT_RATE,                                              \n");
//        sb.append("          G.SAVEAMT,                                                                         \n");
        sb.append("          A.NOREST_ALLOT_MONTHS,                                                             \n");
        sb.append("          0  COMP_MONTH,                                                                     \n");
//        sb.append("          G.CUST_PRICE,                                                                      \n");
        sb.append("          FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'7') AS CUST_PRICE,                      \n");
        sb.append("          H.MAKECO_NAME,                                                                     \n");
        sb.append("          I.BRAND_NAME,                                                                      \n");
        sb.append("          TCODE_NAME('B023',A.ORIGIN_CODE )AS ORIGIN_CODE,                                   \n");
        sb.append("          (SELECT X.IMAGE_URL||X.IMAGE_L FROM TGOODSIMAGE X WHERE X.GOODS_CODE = A.GOODS_CODE AND X.IMAGE_NO = '01') AS IMAGE_L_PATH \n");
        sb.append("     FROM TGOODS      A,                                                                     \n");
        sb.append("          TGOODSKINDS B,                                                                     \n");
        sb.append("          TENTERPRISE C,                                                                     \n");
        sb.append("          TENTPUSER  D,                                                                      \n");
        sb.append("          TMD         E,                                                                     \n");
        sb.append("          TSALENOGOODS F,                                                                    \n");
//        sb.append("          TGOODSPRICE G,                                                                     \n");
        sb.append("          TMAKECOMP   H,                                                                     \n");
        sb.append("          TBRAND      I                                                                     \n");
        sb.append("    WHERE A.LGROUP       = B.LGROUP(+)                                                       \n");
        sb.append("      AND A.MGROUP       = B.MGROUP(+)                                                       \n");
        sb.append("      AND A.SGROUP       = B.SGROUP(+)                                                       \n");
        sb.append("      AND A.DGROUP       = B.DGROUP(+)                                                       \n");
        sb.append("      AND A.ENTP_CODE    = C.ENTP_CODE(+)                                                    \n");
        sb.append("      AND A.ENTP_CODE    = D.ENTP_CODE(+)                                                    \n");
        sb.append("      AND A.ENTP_MAN_SEQ = D.ENTP_MAN_SEQ(+)                                                 \n");
        sb.append("      AND A.MD_CODE      = E.MD_CODE(+)                                                      \n");
        sb.append("      AND A.GOODS_CODE   = F.GOODS_CODE(+)                                                   \n");
        sb.append("      AND A.SALE_GB      = F.SALE_GB(+)                                                      \n");
//        sb.append("      AND A.GOODS_CODE   = G.GOODS_CODE(+)                                                   \n");
        sb.append("      AND NVL(F.INSERT_DATE, SYSDATE) =                                                      \n");
        sb.append("          ( SELECT NVL(MAX(G.INSERT_DATE), SYSDATE)                                          \n");
        sb.append("              FROM TSALENOGOODS G                                                            \n");
        sb.append("             WHERE A.GOODS_CODE = G.GOODS_CODE                                               \n");
        sb.append("               AND A.SALE_GB   = G.SALE_GB )                                                 \n");
//        sb.append("       AND NVL(G.APPLY_DATE, SYSDATE) =                                                      \n");
//        sb.append("          ( SELECT NVL(MAX(I.APPLY_DATE), SYSDATE)                                           \n");
//        sb.append("              FROM TGOODSPRICE I                                                             \n");
//        sb.append("             WHERE G.GOODS_CODE = I.GOODS_CODE                                               \n");
//        sb.append("               AND DECODE(SIGN(I.APPLY_DATE-SYSDATE), 1, SYSDATE, APPLY_DATE) <= SYSDATE )   \n");
        sb.append("      AND A.MAKECO_CODE = H.MAKECO_CODE                                                      \n");
        sb.append("      AND A.BRAND_CODE = I.BRAND_CODE                                                        \n");
        sb.append("      AND A.GOODS_CODE = ?                                                                   \n");
        sb.append("      AND ROWNUM = 1                                                                         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT2
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode2
    * @return  RetrieveMode2
    */
    public RetrieveModel retrieveDt2(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt2(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT2",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrieveDt2() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrieveDt2() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDt3( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT GOODS_CODE AS SHARE_GOODS_CODE,   \n");
        sb.append("        SEQ_NO,       \n");
        sb.append("        TITLE1,        \n");
        sb.append("        INSERT_ID,    \n");
        sb.append("        TO_CHAR(INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        MODIFY_ID,    \n");
        sb.append("        TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        CONTENTS      \n");
        sb.append("   FROM TGOODSSHARE   \n");
        sb.append("  WHERE GOODS_CODE = ? \n");
        sb.append("  ORDER BY SEQ_NO DESC \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT3
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode3
    * @return  RetrieveMode3
    */
    public RetrieveModel retrieveDt3(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt3(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT3",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrieveDt3() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrieveDt3() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlDt4( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT GOODS_CODE,   \n");
        sb.append("        SEQ_NO,       \n");
        sb.append("        TITLE1,        \n");
        sb.append("        INSERT_ID,    \n");
        sb.append("        TO_CHAR(INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("        MODIFY_ID,    \n");
        sb.append("        TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE,  \n");
        sb.append("        CONTENTS      \n");
        sb.append("  FROM  TGOODSSHARE   \n");
        sb.append(" WHERE  GOODS_CODE = ? \n");
        sb.append("   AND  SEQ_NO     = ? \n");


        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve DT3
    * </PRE>
    * @param   Connection
    * @param   RetrieveMode3
    * @return  RetrieveMode3
    */
    public RetrieveModel retrieveDt4(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlDt4(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            pstmt.setString(index++,retrieve.getString("seq_no"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_DT4",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrieveDt4() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrieveDt4() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tgoodsshare
    * </PRE>
    * @param   Connection
    * @param   Tgoodsshare
    * @return  int
    */
    public int insert(Connection con, Tgoodsshare tgoodsshare) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tgoodsshare));
            int index = 1;

            pstmt.setString(index++,tgoodsshare.getGoods_code()    );
            pstmt.setLong(index++,tgoodsshare.getSeq_no()    );
            pstmt.setString(index++,tgoodsshare.getTitle()    );
            pstmt.setString(index++,tgoodsshare.getContents()    );
            pstmt.setString(index++,tgoodsshare.getInsert_id()      );
            pstmt.setString(index++,tgoodsshare.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsshare.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoodsshare.getSeq_no()         ); logString.append( "/" );
            logString.append( tgoodsshare.getTitle()          ); logString.append( "/" );
            logString.append( tgoodsshare.getContents()       ); logString.append( "/" );
            logString.append( tgoodsshare.getInsert_id()      ); logString.append( "/" );
            logString.append( tgoodsshare.getModify_id()      ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsExplainSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsExplainSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tgoodsshare
    * </PRE>
    * @param   Connection
    * @param   Tgoodsshare
    * @return  int
    */
    public int update(Connection con, Tgoodsshare tgoodsshare) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tgoodsshare));
            int index = 1;

            pstmt.setString(index++,tgoodsshare.getTitle()      );
            pstmt.setString(index++,tgoodsshare.getContents()   );
            pstmt.setString(index++,tgoodsshare.getModify_id()  );
            pstmt.setString(index++,tgoodsshare.getGoods_code() );
            pstmt.setLong  (index++,tgoodsshare.getSeq_no()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsshare.getTitle()          ); logString.append( "/" );
            logString.append( tgoodsshare.getContents()       ); logString.append( "/" );
            logString.append( tgoodsshare.getModify_id()      ); logString.append( "/" );
            logString.append( tgoodsshare.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoodsshare.getSeq_no()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsExplainSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsExplainSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Delete Tgoodsshare  )
    * </PRE>
    * @param   Tgoodsshare
    * @return  String
    */
    private final String deleteSqlTgoodsshare =  " DELETE FROM TGOODSSHARE WHERE GOODS_CODE = ? AND SEQ_NO = ?  \n ";

    private int deleteSqlLogTgoodsshare =  0;

    private String makeSqlDelete(Tgoodsshare tgoodsshare) throws StoreException{
        //= log SQL -------------------------------------------------
        if (deleteSqlLogTgoodsshare == 0) {
            if (logSave.isDebugEnabled()) {
                logSave.debug(deleteSqlTgoodsshare);
            }
            deleteSqlLogTgoodsshare = 1;
        }
        return deleteSqlTgoodsshare;
    }

    //= delete -------------------------------------------------
    /**
    * <PRE>
    * Desc : Delete Tgoodsshare
    * </PRE>
    * @param   Connection
    * @param   tdelynoarea
    * @return  int
    */
    public int delete(Connection con, Tgoodsshare tgoodsshare) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlDelete(tgoodsshare));
            int index = 1;

            pstmt.setString(index++,tgoodsshare.getGoods_code()    );
            pstmt.setLong(index++,tgoodsshare.getSeq_no()    );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tgoodsshare.getGoods_code()     ); logString.append( "/" );
            logString.append( tgoodsshare.getSeq_no()         ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[GoodsExplainSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[GoodsExplainSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlPrintM( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                                                                                      \n");
        sb.append("          A.GOODS_NAME,                                                                                      \n");
        sb.append("          B.LGROUP_NAME,                                                                                     \n");
        sb.append("          B.MGROUP_NAME,                                                                                     \n");
        sb.append("          B.SGROUP_NAME,                                                                                     \n");
        sb.append("          B.DGROUP_NAME,                                                                                     \n");
        sb.append("          A.DELY_TYPE,                                                                                       \n");
        sb.append("          H.MAKECO_NAME   AS MAKECO_NAME,                                                                    \n");
        sb.append("          I.BRAND_NAME    AS BRAND_NAME,                                                                     \n");
        sb.append("          J.CODE_NAME     AS ORIGIN_NAME,                                                                    \n");
        sb.append("          C.ENTP_NAME,                                                                                       \n");
        sb.append("          D.ENTP_MAN_NAME,                                                                                   \n");
        sb.append("          D.ENTP_MAN_LEVEL,                                                                                  \n");
        sb.append("          D.ENTP_MAN_DDD,                                                                                    \n");
        sb.append("          D.ENTP_MAN_TEL1,                                                                                   \n");
        sb.append("          D.ENTP_MAN_TEL2,                                                                                   \n");
        sb.append("          D.ENTP_MAN_TEL3,                                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX1,                                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX2,                                                                                   \n");
        sb.append("          D.ENTP_MAN_FAX3,                                                                                   \n");
        sb.append("          D.ENTP_MAN_HP1,                                                                                    \n");
        sb.append("          D.ENTP_MAN_HP2,                                                                                    \n");
        sb.append("          D.ENTP_MAN_HP3,                                                                                    \n");
        sb.append("          E.MD_NAME,                                                                                         \n");
        sb.append("          A.SALE_GB,                                                                                         \n");
        sb.append("          TO_CHAR(DECODE( A.SALE_GB, '00', TO_DATE(NULL), F.INSERT_DATE ),'YYYY/MM/DD') AS NO_INSERT_DATE,   \n");
        sb.append("          G.SALE_PRICE,                                                                                      \n");
        sb.append("          TO_CHAR(A.FIRST_BROAD_DATE,'YYYY/MM/DD') AS FIRST_BROAD_DATE,                                      \n");
        sb.append("          TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE,                                                \n");
        sb.append("          TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI') AS NOW_DATE                                                  \n");
        sb.append("     FROM TGOODS       A,                                                                                    \n");
        sb.append("          TGOODSKINDS  B,                                                                                    \n");
        sb.append("          TENTERPRISE  C,                                                                                    \n");
        sb.append("          TENTPUSER    D,                                                                                    \n");
        sb.append("          TMD          E,                                                                                    \n");
        sb.append("          TSALENOGOODS F,                                                                                    \n");
        sb.append("          TGOODSPRICE  G,                                                                                    \n");
        sb.append("          TMAKECOMP    H,                                                                                    \n");
        sb.append("          TBRAND       I,                                                                                    \n");
        sb.append("          TCODE        J                                                                                     \n");
        sb.append("    WHERE A.LGROUP       = B.LGROUP(+)                                                                       \n");
        sb.append("      AND A.MGROUP       = B.MGROUP(+)                                                                       \n");
        sb.append("      AND A.SGROUP       = B.SGROUP(+)                                                                       \n");
        sb.append("      AND A.DGROUP       = B.DGROUP(+)                                                                       \n");
        sb.append("      AND A.ENTP_CODE    = C.ENTP_CODE(+)                                                                    \n");
        sb.append("      AND A.ENTP_CODE    = D.ENTP_CODE(+)                                                                    \n");
        sb.append("      AND A.ENTP_MAN_SEQ = D.ENTP_MAN_SEQ(+)                                                                 \n");
        sb.append("      AND A.MD_CODE      = E.MD_CODE(+)                                                                      \n");
        sb.append("      AND A.GOODS_CODE   = G.GOODS_CODE(+)                                                                   \n");
        sb.append("      AND A.GOODS_CODE   = F.GOODS_CODE(+)                                                                   \n");
        sb.append("      AND A.MAKECO_CODE  = H.MAKECO_CODE(+)                                                                  \n");
        sb.append("      AND A.BRAND_CODE   = I.BRAND_CODE(+)                                                                   \n");
        sb.append("      AND A.ORIGIN_CODE  = J.CODE_MGROUP(+)                                                                  \n");
        sb.append("      AND J.CODE_LGROUP  = 'B023'                                                                            \n");
        sb.append("      AND NVL(F.INSERT_DATE, SYSDATE) =                                                                      \n");
        sb.append("          ( SELECT NVL(MAX(G.INSERT_DATE), SYSDATE)                                                          \n");
        sb.append("              FROM TSALENOGOODS G                                                                            \n");
        sb.append("             WHERE A.GOODS_CODE = G.GOODS_CODE                                                               \n");
        sb.append("               AND A.SALE_GB    = G.SALE_GB ) /* AND G.SALE_GB > '00' ) update same to retrive 2006/09/19 */ \n");
        sb.append("      AND NVL(G.APPLY_DATE, SYSDATE) =                                                                       \n");
        sb.append("          ( SELECT NVL(MAX(I.APPLY_DATE), SYSDATE)                                                           \n");
        sb.append("              FROM TGOODSPRICE I                                                                             \n");
        sb.append("             WHERE G.GOODS_CODE = I.GOODS_CODE                                                               \n");
        sb.append("               AND DECODE(SIGN(I.APPLY_DATE-SYSDATE), 1, SYSDATE, APPLY_DATE) <= SYSDATE )                   \n");
        sb.append("      AND A.SALE_GB < '20'                                                                                   \n");
        sb.append("      AND A.GOODS_CODE = ?                                                                                   \n");
        sb.append("      AND ROWNUM = 1                                                                                         \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
    public RetrieveModel retrievePrintM(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPrintM(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PRTM",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrievePrintM() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrievePrintM() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlPrintDt( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("   SELECT A.GOODS_CODE,                   \n");
        sb.append("          A.DESCRIBE_TITLE,               \n");
        sb.append("          A.DESCRIBE_NOTE,                \n");
        sb.append("          A.WEB_FLAG                      \n");
        sb.append("     FROM TDESCRIBE A                     \n");
        sb.append("    WHERE A.GOODS_CODE = ?                \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
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
    public RetrieveModel retrievePrintDt(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSqlPrintDt(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("goods_code"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT_PRTDT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[GoodsExaplainSvc.retrievePrintDt() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[GoodsExaplainSvc.retrievePrintDt() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL Check goods_code
     * </PRE>
     * @param   RetrieveModel
     * @return  String
     */
     public String makeSqlDt_CHK(String goods_code) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append(" SELECT                                    \n");
         sb.append("        COUNT(A.GOODS_CODE) AS GOODS_CNT   \n");
         sb.append("   FROM TGOODS A 				           \n");
         sb.append("  WHERE A.GOODS_CODE = ?                   \n");

         //= log SQL -------------------------------------------------
         if (log.isDebugEnabled()) {
             log.debug("\n" + sb.toString());
             log.debug("goods_code  : " + goods_code);
         }
         return sb.toString();
     }

    /**
     * <PRE>
     * Desc : Check goods_code
     * </PRE>
     * @param   Connection
     * @param   goods_code
     * @return  int
     */
     public int chkGoodsCode(Connection con, String goods_code) throws StoreException{
         PreparedStatement pstmt       = null;
         ResultSet         rset        = null;
         int executedRtn = 0;

         try {
         	pstmt = con.prepareStatement(makeSqlDt_CHK(goods_code));
             int index = 1;
             pstmt.setString(index++, goods_code   );

             rset = pstmt.executeQuery();

             if (rset!=null && rset.next()) executedRtn = rset.getInt(1);
            	executedRtn = rset.getInt(1);

         }catch(SQLException se){
             logSave.error("[Tgoods.chkGoodsCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
             throw new StoreException(se.getMessage());
         }catch(Exception e){
             logSave.error("[Tgoods.chkGoodsCode() Exception : ERR-"+e.getMessage());
             throw new StoreException(e.getMessage());
         }finally {
             DBUtils.freeConnection(null, pstmt, rset);
         }
         return executedRtn;
     }


}