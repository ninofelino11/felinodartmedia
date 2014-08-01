
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;

/**
 * Card Approve class
 *
 * @version 1.0, 2006/07/19
 * @author commerceware.co.kr
 */
public class Vaccount {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public Vaccount()   { }

    /**
    * <PRE>
    * Desc : 가상계좌 번호얻기
    * reference : Netshopping8 G_VACCOUNT_C
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public String getVaccountNo(String bank_code, String bank_seq, double quest_amt) throws StoreException {

        Connection con  = null;
        String     vaccountNo = "";

        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);

            vaccountNo = getVaccountNo(con,bank_code,bank_seq,quest_amt);

            con.commit();

        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(se);
            vaccountNo = "";
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(e);
            vaccountNo = "";
        }finally {
            DBUtils.freeConnection(con);
        }
        return vaccountNo;
    }

    public String getVaccountNo(Connection con, String bank_code, String bank_seq, double quest_amt) throws StoreException {

        String vaccountNo = "";

        try{
            vaccountNo = DBUtils.getSysDateTime(con,"YYYYMMDDhh24miss");

        }catch(StoreException se){
            log.error(se);
            vaccountNo = "";
        }catch(Exception e){
            log.error(e);
            vaccountNo = "";
        }
        return vaccountNo;
    }
}
