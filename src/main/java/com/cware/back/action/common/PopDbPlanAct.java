
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PopDbPlanSvc;

/**
 * Common DB util Action class
 *
 * @version 1.0, 2006/07/19
 * @author commerceware.co.kr
 */
public class PopDbPlanAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private String logTemp      = "";

    public PopDbPlanAct()   { }

//  Control retreive -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control retrieval action
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        HashMap[]     retSheet = null;
        int executedRtn = 0;
        String      sysDateTime    = "";

        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopDbPlanSvc svc = new PopDbPlanSvc();

            sysDateTime = DBUtils.getSysDateTime(con, "YYYYMMDDHH24MISS");
            if(sysDateTime.equals("")){
                logTemp = ComUtils.getMessage("msg.cannot_sysdate") + " : sysDateTime";
                log.error(logTemp);
                throw new StoreException(logTemp);
            }

            retrieve.put("ID",sysDateTime);

            executedRtn = svc.insert(con, retrieve);
            retrieve = svc.retrieve(con, retrieve);

            retSheet = (HashMap[])retrieve.getObject("RESULT");

        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }

}
