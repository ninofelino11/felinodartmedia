
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
import com.cware.back.entity.table.Tmakecomp;

/**
 * 제조업체등록 Service class
 *
 * @version 1.0, 2006/06/21
 */
public class MakecompSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public MakecompSvc() {}

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

        sb.append("  SELECT A.MAKECO_CODE,   \n ");
        sb.append("         A.MAKECO_NAME,   \n ");
        sb.append("         A.MAKECO_POST,   \n ");
        sb.append("         A.MAKECO_POST_SEQ,   \n ");
        sb.append("         FUN_ADD_POSTADDR(A.MAKECO_POST,A.MAKECO_POST_SEQ,'') AS ADDR,   \n ");
        sb.append("         A.MAKECO_ADDR,   \n ");
        sb.append("         A.MAKECO_DDD,    \n ");
        sb.append("         A.MAKECO_TEL1,   \n ");
        sb.append("         A.MAKECO_TEL2,   \n ");
        sb.append("         A.MAKECO_TEL3,   \n ");
        sb.append("         A.MAKECO_FAX1,   \n ");
        sb.append("         A.MAKECO_FAX2,   \n ");
        sb.append("         A.MAKECO_FAX3,   \n ");
        sb.append("         A.MAKECO_REMARK, \n ");
        sb.append("         A.INSERT_DATE,   \n ");
        sb.append("         A.INSERT_ID,     \n ");
        sb.append("         A.MODIFY_DATE,   \n ");
        sb.append("         A.MODIFY_ID      \n ");
        sb.append("    FROM TMAKECOMP A     \n ");
        sb.append("   WHERE A.MAKECO_CODE > '0000'             \n ");
        sb.append("     AND A.MAKECO_CODE < '9999'             \n ");
        sb.append("     AND A.MAKECO_CODE LIKE ? || '%'  \n ");
        sb.append("     AND A.MAKECO_NAME LIKE ? || '%'  \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( 아이디 중복 체크 )
    * </PRE>
    * @param   Tmakecomp
    * @return  String
    */
    public String makeSqlDupCheck(Tmakecomp comp) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT COUNT(MAKECO_CODE) \n ");
        sb.append("    FROM TMAKECOMP \n ");
        sb.append("   WHERE MAKECO_CODE = ? \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Tmakecomp 에 등록 )
    * </PRE>
    * @param   Tmakecomp
    * @return  String
    */
    public String makeSqlInsert(Tmakecomp comp) throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("  INSERT INTO TMAKECOMP (    \n ");
        sb.append("              MAKECO_CODE    , \n ");
        sb.append("              MAKECO_NAME    , \n ");
        sb.append("              MAKECO_POST    , \n ");
        sb.append("              MAKECO_POST_SEQ, \n ");
        sb.append("              MAKECO_ADDR    , \n ");
        sb.append("              MAKECO_DDD     , \n ");
        sb.append("              MAKECO_TEL1    , \n ");
        sb.append("              MAKECO_TEL2    , \n ");
        sb.append("              MAKECO_TEL3    , \n ");
        sb.append("              MAKECO_FAX1    , \n ");
        sb.append("              MAKECO_FAX2    , \n ");
        sb.append("              MAKECO_FAX3    , \n ");
        sb.append("              MAKECO_REMARK  , \n ");
        sb.append("              INSERT_DATE    , \n ");
        sb.append("              INSERT_ID      , \n ");
        sb.append("              MODIFY_DATE    , \n ");
        sb.append("              MODIFY_ID)       \n ");
        sb.append("       VALUES (                \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("              ?,               \n ");
        sb.append("              TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("              ? )              \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( Tmakecomp 변경 )
    * </PRE>
    * @param   Tmakecomp
    * @return  String
    */
    public String makeSqlUpdate(Tmakecomp comp) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TMAKECOMP           \n ");
        sb.append("     SET MAKECO_NAME      = ?,  \n ");
        sb.append("         MAKECO_POST      = ?,  \n ");
        sb.append("         MAKECO_POST_SEQ  = ?,  \n ");
        sb.append("         MAKECO_ADDR      = ?,  \n ");
        sb.append("         MAKECO_DDD       = ?,  \n ");
        sb.append("         MAKECO_TEL1      = ?,  \n ");
        sb.append("         MAKECO_TEL2      = ?,  \n ");
        sb.append("         MAKECO_TEL3      = ?,  \n ");
        sb.append("         MAKECO_FAX1      = ?,  \n ");
        sb.append("         MAKECO_FAX2      = ?,  \n ");
        sb.append("         MAKECO_FAX3      = ?,  \n ");
        sb.append("         MAKECO_REMARK    = ?,  \n ");
        sb.append("         MODIFY_DATE      = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), \n ");
        sb.append("         MODIFY_ID        = ?  \n ");
        sb.append("   WHERE MAKECO_CODE      = ?  \n ");

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
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("makeco_code"));
            pstmt.setString(index++,retrieve.getString("makeco_name"));
            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[SearchSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[SearchSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert Tmakecomp
    * </PRE>
    * @param   Connection
    * @param   Tmakecomp
    * @return  int
    */
    public int insert(Connection con, Tmakecomp comp) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(comp));
            int index = 1;

            pstmt.setString(index++,comp.getMakeco_code()    );
            pstmt.setString(index++,comp.getMakeco_name()    );
            pstmt.setString(index++,comp.getMakeco_post()    );
            pstmt.setString(index++,comp.getMakeco_post_seq());
            pstmt.setString(index++,comp.getMakeco_addr()    );
            pstmt.setString(index++,comp.getMakeco_ddd()     );
            pstmt.setString(index++,comp.getMakeco_tel1()    );
            pstmt.setString(index++,comp.getMakeco_tel2()    );
            pstmt.setString(index++,comp.getMakeco_tel3()    );
            pstmt.setString(index++,comp.getMakeco_fax1()    );
            pstmt.setString(index++,comp.getMakeco_fax2()    );
            pstmt.setString(index++,comp.getMakeco_fax3()    );
            pstmt.setString(index++,comp.getMakeco_remark()  );
            pstmt.setString(index++,comp.getInsert_date()    );
            pstmt.setString(index++,comp.getInsert_id()      );
            pstmt.setString(index++,comp.getModify_date()    );
            pstmt.setString(index++,comp.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( comp.getMakeco_code()    ); logString.append( "/" );
            logString.append( comp.getMakeco_name()    ); logString.append( "/" );
            logString.append( comp.getMakeco_post()    ); logString.append( "/" );
            logString.append( comp.getMakeco_post_seq()); logString.append( "/" );
            logString.append( comp.getMakeco_addr()    ); logString.append( "/" );
            logString.append( comp.getMakeco_ddd()     ); logString.append( "/" );
            logString.append( comp.getMakeco_tel1()    ); logString.append( "/" );
            logString.append( comp.getMakeco_tel2()    ); logString.append( "/" );
            logString.append( comp.getMakeco_tel3()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax1()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax2()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax3()    ); logString.append( "/" );
            logString.append( comp.getMakeco_remark()  ); logString.append( "/" );
            logString.append( comp.getInsert_date()    ); logString.append( "/" );
            logString.append( comp.getInsert_id()      ); logString.append( "/" );
            logString.append( comp.getModify_date()    ); logString.append( "/" );
            logString.append( comp.getModify_id()      ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MakecompSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MakecompSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update Tmakecomp
    * </PRE>
    * @param   Connection
    * @param   Tmakecomp
    * @return  int
    */
    public int update(Connection con, Tmakecomp comp) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(comp));
            int index = 1;
            pstmt.setString(index++,comp.getMakeco_name()    );
            pstmt.setString(index++,comp.getMakeco_post()    );
            pstmt.setString(index++,comp.getMakeco_post_seq());
            pstmt.setString(index++,comp.getMakeco_addr()    );
            pstmt.setString(index++,comp.getMakeco_ddd()     );
            pstmt.setString(index++,comp.getMakeco_tel1()    );
            pstmt.setString(index++,comp.getMakeco_tel2()    );
            pstmt.setString(index++,comp.getMakeco_tel3()    );
            pstmt.setString(index++,comp.getMakeco_fax1()    );
            pstmt.setString(index++,comp.getMakeco_fax2()    );
            pstmt.setString(index++,comp.getMakeco_fax3()    );
            pstmt.setString(index++,comp.getMakeco_remark()  );
            pstmt.setString(index++,comp.getModify_date()    );
            pstmt.setString(index++,comp.getModify_id()      );
            pstmt.setString(index++,comp.getMakeco_code()    );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( comp.getMakeco_name()    ); logString.append( "/" );
            logString.append( comp.getMakeco_post()    ); logString.append( "/" );
            logString.append( comp.getMakeco_post_seq()); logString.append( "/" );
            logString.append( comp.getMakeco_addr()    ); logString.append( "/" );
            logString.append( comp.getMakeco_ddd()     ); logString.append( "/" );
            logString.append( comp.getMakeco_tel1()    ); logString.append( "/" );
            logString.append( comp.getMakeco_tel2()    ); logString.append( "/" );
            logString.append( comp.getMakeco_tel3()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax1()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax2()    ); logString.append( "/" );
            logString.append( comp.getMakeco_fax3()    ); logString.append( "/" );
            logString.append( comp.getMakeco_remark()  ); logString.append( "/" );
            logString.append( comp.getModify_date()    ); logString.append( "/" );
            logString.append( comp.getModify_id()      ); logString.append( "/" );
            logString.append( comp.getMakeco_code()    ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[MakecompSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[MakecompSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
}