
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.HashMap;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PopOrderstockSvc;

/**
 * 주문재고현황 Popup Action class
 *
 * @version 1.0, 2006/08/31
 * @author  commerceware.co.kr
 */
public class PopOrderstockAct {

    public PopOrderstockAct() { }

    public HeaderModel retrieveHeader(HeaderModel retrieve, String programId) throws StoreException {

        if(!programId.equals(retrieve.getString("PROGRAM_ID"))){
        	retrieve = new HeaderModel();
        	Connection con = null;
            try{
                con = DBUtils.getConnection(Construct.DB_POOL_NAME);

                retrieve.put("PROGRAM_ID", programId);

                ComboAct comboAct = new ComboAct();
                retrieve.put("HEAD_C_WH_CODE",comboAct.retrieve(con, Construct.C_WH_CODE, null, Construct.P_COMBO_DISPLAY_GRID));

                String[] paramValues = new String[1];
                paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                retrieve.put("SHEET_C_SALE_GB", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));



            }catch(StoreException se){
                retrieve.put("ERROR_MESSAGE",se.getMessage());
            }catch(Exception e){
                retrieve.put("ERROR_MESSAGE",e.getMessage());
            }finally {
                DBUtils.freeConnection(con);
            }
        }
        return retrieve;
    }
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
            PopOrderstockSvc svc = new PopOrderstockSvc();

            retrieve = svc.retrieve(con, retrieve);

            retSheet = (HashMap[])retrieve.getObject("RESULT");

            if(retSheet == null || retSheet.length < 1) {
                retrieve = svc.retrieveSet(con, retrieve);
            }

//            ComboAct comboAct = new ComboAct();
//            String[] paramValues = new String[1];
//            paramValues[0] = "B032"; // TCODE.CODE_LGROUP
//            retrieve.put("SHEET_C_SALE_GB", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));

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
