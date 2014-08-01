
package com.cware.back.service.common;

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
import com.cware.back.entity.table.Tasentp;


/**
 * A/S업체 Service class
 *
 * @version 1.0, 2006/07/28
 * @author  commerceware.co.kr
 */
public final class PopGoodsserviceSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopGoodsserviceSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSqlSelect(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.ENTP_CODE,  \n");
        sb.append("        A.ENTP_SEQ,  \n");
        sb.append("        A.ENTP_NAME,  \n");
        sb.append("        A.ENTP_POST,  \n");
        sb.append("        A.ENTP_POST_SEQ,  \n");
        sb.append("        FUN_DISPLAY_POSTADDR(B.CITY_NAME, B.GU_NAME, B.DONG_NAME) AS ADDR,  \n");
        sb.append("        A.ENTP_ADDR,  \n");
        sb.append("        A.ENTP_MAN_NAME,  \n");
        sb.append("        A.ENTP_DDD,  \n");
        sb.append("        A.ENTP_TEL1,  \n");
        sb.append("        A.ENTP_TEL2,  \n");
        sb.append("        A.ENTP_TEL3,  \n");
        sb.append("        A.ENTP_FAX1,  \n");
        sb.append("        A.ENTP_FAX2,  \n");
        sb.append("        A.ENTP_FAX3,  \n");
        sb.append("        A.ENTP_HP1,  \n");
        sb.append("        A.ENTP_HP2,  \n");
        sb.append("        A.ENTP_HP3,  \n");
        sb.append("        A.USE_YN,  \n");
        sb.append("        TO_CHAR(A.INSERT_DATE, 'yyyy/mm/dd hh24:mi:ss') AS INSERT_DATE,   \n");
        sb.append("        A.INSERT_ID,  \n");
        sb.append("        TO_CHAR(A.MODIFY_DATE, 'yyyy/mm/dd hh24:mi:ss') AS MODIFY_DATE,  \n");
        sb.append("        A.MODIFY_ID  \n");
        sb.append("   FROM TASENTP A,  \n");
        sb.append("        TPOST B  \n");
        sb.append("  WHERE A.ENTP_POST      =   B.POST_NO  \n");
        sb.append("    AND A.ENTP_POST_SEQ  =   B.POST_SEQ  \n");
        sb.append("    AND A.ENTP_CODE      =  ?   \n");
        sb.append("  ORDER BY A.ENTP_SEQ ASC   \n");

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
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
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
            pstmt = con.prepareStatement(makeSqlSelect(retrieve));

            int index = 1;
            pstmt.setString(index++, retrieve.getString("entp_code"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }



    /**
    * <PRE>
    * Desc : Make SQL ; Insert Tasentp
    * </PRE>
    * @param   Tasentp
    * @return  String
    */
    private String makeSqlInsert(Tasentp tasentp) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO TASENTP  \n");
        sb.append("          ( ENTP_CODE,        ENTP_SEQ,        ENTP_NAME,  \n");
        sb.append("            ENTP_POST,        ENTP_POST_SEQ,   ENTP_ADDR,  \n");
        sb.append("            ENTP_MAN_NAME,    ENTP_DDD,        ENTP_TEL1,   \n");
        sb.append("            ENTP_TEL2,        ENTP_TEL3,       ENTP_FAX1,  \n");
        sb.append("            ENTP_FAX2,        ENTP_FAX3,       ENTP_HP1,  \n");
        sb.append("            ENTP_HP2,         ENTP_HP3,        USE_YN,  \n");
        sb.append("            INSERT_ID,  \n");
        sb.append("            INSERT_DATE,  \n");
        sb.append("            MODIFY_ID,  \n");
        sb.append("            MODIFY_DATE )  \n");
        sb.append("   VALUES ( ?,      ?,      ?,  \n");
        sb.append("            ?,      ?,      ?,  \n");
        sb.append("            ?,      ?,      ?,  \n");
        sb.append("            ?,      ?,      ?,  \n");
        sb.append("            ?,      ?,      ?,  \n");
        sb.append("            ?,      ?,      ?,  \n");
        sb.append("            ?,  \n");
        sb.append("            SYSDATE,  \n");
        sb.append("            ?,  \n");
        sb.append("            SYSDATE )  \n");

        //= log SQL -------------------------------------------------
        logSave.info(sb.toString());

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Insert Tasentp
    * </PRE>
    * @param   Connection
    * @param   Tasentp
    * @return  int
    */
    public int insert(Connection con, Tasentp tasentp) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tasentp));
            int index = 1;
            pstmt.setString(index++, tasentp.getEntp_code()     );
            pstmt.setString(index++, tasentp.getEntp_seq()      );
            pstmt.setString(index++, tasentp.getEntp_name()     );
            pstmt.setString(index++, tasentp.getEntp_post()     );
            pstmt.setString(index++, tasentp.getEntp_post_seq() );
            pstmt.setString(index++, tasentp.getEntp_addr()     );
            pstmt.setString(index++, tasentp.getEntp_man_name() );
            pstmt.setString(index++, tasentp.getEntp_ddd()      );
            pstmt.setString(index++, tasentp.getEntp_tel1 ()    );
            pstmt.setString(index++, tasentp.getEntp_tel2()     );
            pstmt.setString(index++, tasentp.getEntp_tel3 ()    );
            pstmt.setString(index++, tasentp.getEntp_fax1()     );
            pstmt.setString(index++, tasentp.getEntp_fax2()     );
            pstmt.setString(index++, tasentp.getEntp_fax3()     );
            pstmt.setString(index++, tasentp.getEntp_hp1()      );
            pstmt.setString(index++, tasentp.getEntp_hp2()      );
            pstmt.setString(index++, tasentp.getEntp_hp3()      );
            pstmt.setString(index++, tasentp.getUse_yn()        );
            pstmt.setString(index++, tasentp.getInsert_id()     );
            pstmt.setString(index++, tasentp.getModify_id()     );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tasentp.getEntp_code()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_seq()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_name()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_post()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_post_seq() ); logString.append( "/" );
            logString.append( tasentp.getEntp_addr()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_man_name() ); logString.append( "/" );
            logString.append( tasentp.getEntp_ddd()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel1 ()    ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel2()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel3 ()    ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax1()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax2()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax3()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp1()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp2()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp3()      ); logString.append( "/" );
            logString.append( tasentp.getUse_yn()        ); logString.append( "/" );
            logString.append( tasentp.getInsert_id()     ); logString.append( "/" );
            logString.append( tasentp.getModify_id()     ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }




    /**
    * <PRE>
    * Desc : Make SQL ; Update tasentp
    * </PRE>
    * @param   tasentp
    * @return  String
    */
    private String makeSqlUpdate(Tasentp tasentp) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE TASENTP             \n");
        sb.append("    SET ENTP_NAME     = ?,  \n");
        sb.append("        ENTP_POST     = ?,  \n");
        sb.append("        ENTP_POST_SEQ = ?,  \n");
        sb.append("        ENTP_ADDR     = ?,  \n");
        sb.append("        ENTP_MAN_NAME = ?,  \n");
        sb.append("        ENTP_DDD      = ?,  \n");
        sb.append("        ENTP_TEL1     = ?,  \n");
        sb.append("        ENTP_TEL2     = ?,  \n");
        sb.append("        ENTP_TEL3     = ?,  \n");
        sb.append("        ENTP_FAX1     = ?,  \n");
        sb.append("        ENTP_FAX2     = ?,  \n");
        sb.append("        ENTP_FAX3     = ?,  \n");
        sb.append("        ENTP_HP1      = ?,  \n");
        sb.append("        ENTP_HP2      = ?,  \n");
        sb.append("        ENTP_HP3      = ?,  \n");
        sb.append("        USE_YN        = ?,  \n");
        sb.append("        MODIFY_ID     = ?,  \n");
        sb.append("        MODIFY_DATE   = SYSDATE  \n");
        sb.append("  WHERE ENTP_CODE     = ?  \n");
        sb.append("    AND ENTP_SEQ      = ?  \n");

        //= log SQL -------------------------------------------------
        logSave.info(sb.toString());

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Update Tasentp
    * </PRE>
    * @param   Connection
    * @param   Tasentp
    * @return  int
    */
    public int update(Connection con, Tasentp tasentp) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tasentp));

            int index = 1;
            pstmt.setString(index++, tasentp.getEntp_name()     );
            pstmt.setString(index++, tasentp.getEntp_post()     );
            pstmt.setString(index++, tasentp.getEntp_post_seq() );
            pstmt.setString(index++, tasentp.getEntp_addr()     );
            pstmt.setString(index++, tasentp.getEntp_man_name() );
            pstmt.setString(index++, tasentp.getEntp_ddd()      );
            pstmt.setString(index++, tasentp.getEntp_tel1()     );
            pstmt.setString(index++, tasentp.getEntp_tel2()     );
            pstmt.setString(index++, tasentp.getEntp_tel3()     );
            pstmt.setString(index++, tasentp.getEntp_fax1()     );
            pstmt.setString(index++, tasentp.getEntp_fax2()     );
            pstmt.setString(index++, tasentp.getEntp_fax3()     );
            pstmt.setString(index++, tasentp.getEntp_hp1()      );
            pstmt.setString(index++, tasentp.getEntp_hp2()      );
            pstmt.setString(index++, tasentp.getEntp_hp3()      );
            pstmt.setString(index++, tasentp.getUse_yn()        );
            pstmt.setString(index++, tasentp.getModify_id()     );
            pstmt.setString(index++, tasentp.getEntp_code()     );
            pstmt.setString(index++, tasentp.getEntp_seq()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tasentp.getEntp_name()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_post()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_post_seq() ); logString.append( "/" );
            logString.append( tasentp.getEntp_addr()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_man_name() ); logString.append( "/" );
            logString.append( tasentp.getEntp_ddd()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel1()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel2()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_tel3()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax1()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax2()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_fax3()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp1()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp2()      ); logString.append( "/" );
            logString.append( tasentp.getEntp_hp3()      ); logString.append( "/" );
            logString.append( tasentp.getUse_yn()        ); logString.append( "/" );
            logString.append( tasentp.getModify_id()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_code()     ); logString.append( "/" );
            logString.append( tasentp.getEntp_seq()      ); logString.append( "/" );

            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}

