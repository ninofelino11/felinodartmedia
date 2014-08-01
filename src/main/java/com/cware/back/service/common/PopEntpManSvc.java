
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
import com.cware.back.entity.table.Tentpuser;


/**
 * 업체담당자 Service class
 *
 * @version 1.0, 2006/07/27
 * @author  commerceware.co.kr
 */
public class PopEntpManSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public PopEntpManSvc() {}

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
        sb.append("SELECT A.ENTP_CODE,  \n");
        sb.append("       A.ENTP_MAN_SEQ,  \n");
        sb.append("       A.ENTP_MAN_NAME,  \n");
        sb.append("       A.ENTP_MAN_LEVEL,  \n");
        sb.append("       A.ENTP_MAN_DDD,  \n");
        sb.append("       A.ENTP_MAN_TEL1,  \n");
        sb.append("       A.ENTP_MAN_TEL2,  \n");
        sb.append("       A.ENTP_MAN_TEL3,  \n");
        sb.append("       A.ENTP_MAN_FAX1,  \n");
        sb.append("       A.ENTP_MAN_FAX2,  \n");
        sb.append("       A.ENTP_MAN_FAX3,  \n");
        sb.append("       A.ENTP_MAN_HP1,  \n");
        sb.append("       A.ENTP_MAN_HP2,  \n");
        sb.append("       A.ENTP_MAN_HP3,  \n");
        sb.append("       A.TRANS_NOTE,  \n");
        sb.append("       A.ENTP_MAN_GB,  \n");
        sb.append("       TCODE_NAME('B120', A.ENTP_MAN_GB) AS ENTP_MAN_GB_NAME,\n");
        sb.append("       A.DEFAULT_YN,  \n");
        sb.append("       A.EMAIL_ADDR,  \n");
        sb.append("       A.POST_NO,  \n");
        sb.append("       A.POST_SEQ,  \n");
        sb.append("       FUN_ADD_POSTADDR(A.POST_NO, A.POST_SEQ, '') AS POST_ADDR,  \n");
        sb.append("       A.ADDR,  \n");
        sb.append("       FUN_ADD_POSTADDR(A.POST_NO, A.POST_SEQ, A.ADDR) AS FULL_ADDR,  \n");
        sb.append("       A.USE_YN,  \n");
        sb.append("       '' AS ENTP_MAN_TEL,  \n");//needs to set data in flex 
        sb.append("       A.ENTP_MAN_FAX1||' '||A.ENTP_MAN_FAX2||' '||A.ENTP_MAN_FAX3 AS ENTP_MAN_FAX,  \n");
        sb.append("       A.ENTP_MAN_HP1||' '||A.ENTP_MAN_HP2||' '||A.ENTP_MAN_HP3 AS ENTP_MAN_HP  \n");
        sb.append("  FROM TENTPUSER A  \n");
        sb.append(" WHERE A.ENTP_CODE       = ?  \n");
//        sb.append("   AND A.ENTP_MAN_SEQ LIKE ?||'%'  \n");
        sb.append("   AND A.ENTP_MAN_GB = ?\n");
        sb.append(" ORDER BY A.ENTP_CODE, A.ENTP_MAN_SEQ ASC  \n");

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
//            pstmt.setString(index++, retrieve.getString("entp_man_seq"));
            pstmt.setString(index++, retrieve.getString("entp_man_gb"));
            
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
    * Desc : Make SQL ; Insert Tentpuser
    * </PRE>
    * @param   Tentpuser
    * @return  String
    */
    private String makeSqlInsert(Tentpuser tentpuser) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO TENTPUSER  \n");
        sb.append("          ( ENTP_CODE,       ENTP_MAN_SEQ,    ENTP_MAN_GB,  \n");
        sb.append("            ENTP_MAN_NAME,   ENTP_MAN_LEVEL,  ENTP_MAN_DDD,  \n");
        sb.append("            ENTP_MAN_TEL1,   ENTP_MAN_TEL2,   ENTP_MAN_TEL3,  \n");
        sb.append("            ENTP_MAN_FAX1,   ENTP_MAN_FAX2,   ENTP_MAN_FAX3,  \n");
        sb.append("            ENTP_MAN_HP1,    ENTP_MAN_HP2,    ENTP_MAN_HP3,  \n");
        sb.append("            POST_NO,    		POST_SEQ,    	 ADDR,  \n");
        sb.append("            EMAIL_ADDR,      TRANS_NOTE,      DEFAULT_YN,  \n");
        sb.append("            USE_YN,  \n");
        sb.append("            INSERT_DATE,     INSERT_ID,  \n");
        sb.append("            MODIFY_DATE,     MODIFY_ID  )  \n");
        sb.append("   VALUES ( ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,   ?,    ?,  \n");
        sb.append("            ?,  \n");
        sb.append("            SYSDATE,   ?,  \n");
        sb.append("            SYSDATE,   ?  )  \n");

        //= log SQL -------------------------------------------------
        logSave.info(sb.toString());

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Insert Tentpuser
    * </PRE>
    * @param   Connection
    * @param   Tentpuser
    * @return  int
    */
    public int insert(Connection con, Tentpuser tentpuser) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tentpuser));
            int index = 1;
            pstmt.setString(index++, tentpuser.getEntp_code()      );
            pstmt.setString(index++, tentpuser.getEntp_man_seq()   );
            pstmt.setString(index++, tentpuser.getEntp_man_gb()    );
            pstmt.setString(index++, tentpuser.getEntp_man_name()  );
            pstmt.setString(index++, tentpuser.getEntp_man_level() );
            pstmt.setString(index++, tentpuser.getEntp_man_ddd()   );
            pstmt.setString(index++, tentpuser.getEntp_man_tel1()  );
            pstmt.setString(index++, tentpuser.getEntp_man_tel2()  );
            pstmt.setString(index++, tentpuser.getEntp_man_tel3()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax1()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax2()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax3()  );
            pstmt.setString(index++, tentpuser.getEntp_man_hp1()   );
            pstmt.setString(index++, tentpuser.getEntp_man_hp2()   );
            pstmt.setString(index++, tentpuser.getEntp_man_hp3()   );
            
            pstmt.setString(index++, tentpuser.getPost_no()   );
            pstmt.setString(index++, tentpuser.getPost_seq()   );
            pstmt.setString(index++, tentpuser.getAddr()   );
            
            pstmt.setString(index++, tentpuser.getEmail_addr()     );
            pstmt.setString(index++, tentpuser.getTrans_note()     );
            pstmt.setString(index++, tentpuser.getDefault_yn()     );
            pstmt.setString(index++, tentpuser.getUse_yn()         );
            pstmt.setString(index++, tentpuser.getInsert_id()      );
            pstmt.setString(index++, tentpuser.getModify_id()      );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tentpuser.getEntp_code()      ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_seq()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_gb()    ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_name()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_level() ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_ddd()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel1()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel2()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel3()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax1()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax2()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax3()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp1()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp2()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp3()   ); logString.append( "/" );
            
            logString.append( tentpuser.getPost_no()   ); logString.append( "/" );
            logString.append( tentpuser.getPost_seq()   ); logString.append( "/" );
            logString.append( tentpuser.getAddr()   ); logString.append( "/" );
            
            logString.append( tentpuser.getEmail_addr()     ); logString.append( "/" );
            logString.append( tentpuser.getTrans_note()     ); logString.append( "/" );
            logString.append( tentpuser.getDefault_yn()     ); logString.append( "/" );
            logString.append( tentpuser.getUse_yn()         ); logString.append( "/" );
            logString.append( tentpuser.getInsert_id()      ); logString.append( "/" );
            logString.append( tentpuser.getModify_id()      ); logString.append( "/" );

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
    * Desc : Make SQL ; Update Tentpuser
    * </PRE>
    * @param   Tentpuser
    * @return  String
    */
    private String makeSqlUpdate(Tentpuser tentpuser) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE TENTPUSER  \n");
        sb.append("   SET ENTP_MAN_GB    = ?,  \n");
        sb.append("       ENTP_MAN_NAME  = ?,  \n");
        sb.append("       ENTP_MAN_LEVEL = ?,  \n");
        sb.append("       ENTP_MAN_DDD   = ?,  \n");
        sb.append("       ENTP_MAN_TEL1  = ?,  \n");
        sb.append("       ENTP_MAN_TEL2  = ?,  \n");
        sb.append("       ENTP_MAN_TEL3  = ?,  \n");
        sb.append("       ENTP_MAN_FAX1  = ?,  \n");
        sb.append("       ENTP_MAN_FAX2  = ?,  \n");
        sb.append("       ENTP_MAN_FAX3  = ?,  \n");
        sb.append("       ENTP_MAN_HP1   = ?,  \n");
        sb.append("       ENTP_MAN_HP2   = ?,  \n");
        sb.append("       ENTP_MAN_HP3   = ?,  \n");
        sb.append("       POST_NO   = ?,  \n");
        sb.append("       POST_SEQ   = ?,  \n");
        sb.append("       ADDR   = ?,  \n");
        sb.append("       EMAIL_ADDR     = ?,  \n");
        sb.append("       TRANS_NOTE     = ?,  \n");
        sb.append("       DEFAULT_YN     = ?,  \n");
        sb.append("       USE_YN         = ?,  \n");
        sb.append("       MODIFY_DATE    = SYSDATE,  \n");
        sb.append("       MODIFY_ID      = ?  \n");
        sb.append(" WHERE ENTP_CODE      = ?  \n");
        sb.append("   AND ENTP_MAN_SEQ   = ?  \n");

        //= log SQL -------------------------------------------------
        logSave.info(sb.toString());

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Update Tentpuser
    * </PRE>
    * @param   Connection
    * @param   Tentpuser
    * @return  int
    */
    public int update(Connection con, Tentpuser tentpuser) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tentpuser));

            int index = 1;
            pstmt.setString(index++, tentpuser.getEntp_man_gb()    );
            pstmt.setString(index++, tentpuser.getEntp_man_name()  );
            pstmt.setString(index++, tentpuser.getEntp_man_level() );
            pstmt.setString(index++, tentpuser.getEntp_man_ddd()   );
            pstmt.setString(index++, tentpuser.getEntp_man_tel1()  );
            pstmt.setString(index++, tentpuser.getEntp_man_tel2()  );
            pstmt.setString(index++, tentpuser.getEntp_man_tel3()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax1()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax2()  );
            pstmt.setString(index++, tentpuser.getEntp_man_fax3()  );
            pstmt.setString(index++, tentpuser.getEntp_man_hp1()   );
            pstmt.setString(index++, tentpuser.getEntp_man_hp2()   );
            pstmt.setString(index++, tentpuser.getEntp_man_hp3()   );
            
            pstmt.setString(index++, tentpuser.getPost_no()   );
            pstmt.setString(index++, tentpuser.getPost_seq()   );
            pstmt.setString(index++, tentpuser.getAddr()   );
            
            pstmt.setString(index++, tentpuser.getEmail_addr()     );
            pstmt.setString(index++, tentpuser.getTrans_note()     );
            pstmt.setString(index++, tentpuser.getDefault_yn()     );
            pstmt.setString(index++, tentpuser.getUse_yn()         );
            pstmt.setString(index++, tentpuser.getModify_id()      );
            pstmt.setString(index++, tentpuser.getEntp_code()      );
            pstmt.setString(index++, tentpuser.getEntp_man_seq()   );

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tentpuser.getEntp_man_gb()    ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_name()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_level() ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_ddd()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel1()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel2()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_tel3()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax1()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax2()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_fax3()  ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp1()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp2()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_hp3()   ); logString.append( "/" );
            
            logString.append( tentpuser.getPost_no()   ); logString.append( "/" );
            logString.append( tentpuser.getPost_seq()   ); logString.append( "/" );
            logString.append( tentpuser.getAddr()   ); logString.append( "/" );
            
            logString.append( tentpuser.getEmail_addr()     ); logString.append( "/" );
            logString.append( tentpuser.getTrans_note()     ); logString.append( "/" );
            logString.append( tentpuser.getDefault_yn()     ); logString.append( "/" );
            logString.append( tentpuser.getUse_yn()         ); logString.append( "/" );
            logString.append( tentpuser.getModify_id()      ); logString.append( "/" );
            logString.append( tentpuser.getEntp_code()      ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_seq()   ); logString.append( "/" );

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
    * Desc : Make SQL ; Update Tentpuser
    * </PRE>
    * @param   Tentpuser
    * @return  String
    */
    private String makeSqlUpdateDisableDefault(Tentpuser tentpuser) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE TENTPUSER  \n");
        sb.append("   SET DEFAULT_YN    = '0',  \n");
        sb.append("       MODIFY_DATE   = SYSDATE,  \n");
        sb.append("       MODIFY_ID     = ?  \n");
        sb.append(" WHERE ENTP_CODE     = ?  \n");
        /*
        sb.append("   AND ENTP_MAN_GB   = ?  \n");
        sb.append("   AND ENTP_MAN_SEQ <> ?  \n");
        */
        //= log SQL -------------------------------------------------
        logSave.info(sb.toString());

        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Update Tentpuser ; disable  default_yn
    * </PRE>
    * @param   Connection
    * @param   Tentpuser
    * @return  int
    */
    public int updateDisableDefault(Connection con, Tentpuser tentpuser) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateDisableDefault(tentpuser));
            int index = 1;
            pstmt.setString(index++, tentpuser.getModify_id()     );
            pstmt.setString(index++, tentpuser.getEntp_code()     );
            /*
            pstmt.setString(index++, tentpuser.getEntp_man_gb()   );
            pstmt.setString(index++, tentpuser.getEntp_man_seq()  );
            */
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tentpuser.getModify_id()     ); logString.append( "/" );
            logString.append( tentpuser.getEntp_code()     ); logString.append( "/" );
            /*
            logString.append( tentpuser.getEntp_man_gb()   ); logString.append( "/" );
            logString.append( tentpuser.getEntp_man_seq()  ); logString.append( "/" );
            */
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

