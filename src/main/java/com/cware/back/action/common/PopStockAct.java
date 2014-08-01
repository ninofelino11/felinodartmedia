
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PopStockSvc;

/**
 * 상품재고 Popup Action class
 *
 * @version 1.0, 2006/09/19
 * @author  commerceware.co.kr
 */
public class PopStockAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private String logTemp      = "";

    public PopStockAct() { }

    // Control retreive -------------------------------------------------
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

        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopStockSvc svc = new PopStockSvc();

            ComboAct comboAct = new ComboAct();
            retrieve.put("HEAD_C_WH_CODE",comboAct.retrieve(con, Construct.C_WH_CODE, null, Construct.P_COMBO_DISPLAY_HTML));

            retrieve = svc.retrieve(con, retrieve);

            retSheet = (HashMap[])retrieve.getObject("RESULT");

            if(retSheet == null || retSheet.length < 1) {
                retrieve = svc.retrieveSet(con, retrieve);
            }


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
