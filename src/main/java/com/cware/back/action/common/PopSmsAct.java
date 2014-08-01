
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tsms;
import com.cware.back.service.common.PopSmsSvc;

/**
 * Common DB util Action class
 *
 * @version 1.0, 2008/07/02
 * @author commerceware.co.kr
 */
public class PopSmsAct {

    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public PopSmsAct()   { }

//  Control save -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control save action
    * </PRE>
    * @param   Tuser    
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */    
    public String save(Tsms sms) throws StoreException {
        Connection con  = null;
        String rtnMsg = "000000";
        int executedRtn = 0;
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopSmsSvc svc = new PopSmsSvc();
            
            executedRtn = svc.insert(con, sms);    
            if (executedRtn < 1){
                logTemp = ComUtils.getMessage("msg.cannot_save", "TSMS (insert)");
                logSave.error(logTemp);
                throw new StoreException(logTemp);
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