
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tuser;
import com.cware.back.service.common.PopPwdChangeSvc;

/**
 * Common DB util Action class
 *
 * @version 1.0, 2006/07/19
 * @author commerceware.co.kr
 */
public class PopPwdChangeAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public PopPwdChangeAct()   { }

//  Control save -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control save action
    * </PRE>
    * @param   Tuser
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */
    public String save(Tuser user, String ip_addr) throws StoreException {
        Connection con  = null;
        String rtnMsg = "000000";
        int executedRtn = 0;
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopPwdChangeSvc svc = new PopPwdChangeSvc();

            rtnMsg = svc.retrieve(con, user);
            if (rtnMsg != "000000"){
                DBUtils.insertTlogin(con, user.getUser_id(), ip_addr, "Chang Password Fail", user.getUser_id() + " / " + user.getPasswd() + " / " + user.getRemark() );
                logTemp = ComUtils.getMessage("msg.check_pass");
                logSave.error(logTemp);
                throw new StoreException(logTemp);
            }else{

	            executedRtn = svc.update(con, user);
	            if (executedRtn < 1){
	                DBUtils.insertTlogin(con, user.getUser_id(), ip_addr, "Chang Password Fail", user.getUser_id() + " / " + user.getPasswd() + " / " + user.getRemark() );
	                logTemp = ComUtils.getMessage("msg.cannot_save", "tuser update");
	                logSave.error(logTemp);
	                throw new StoreException(logTemp);
	            }
                DBUtils.insertTlogin(con, user.getUser_id(), ip_addr, "Chang Password Success", user.getUser_id() + " / " + user.getPasswd() + " / " + user.getRemark() );
            }
            con.commit();

        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            rtnMsg = se.getMessage();
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception ec){}
            rtnMsg = e.getMessage();
        }finally {
            DBUtils.freeConnection(con);
        }
        return rtnMsg;
    }
}