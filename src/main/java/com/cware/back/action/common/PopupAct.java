
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PopupSvc;

/**
 * Popup Action Bean
 *
 * @version 1.0, 2006/06/14
 * @author commerceware.co.kr
 */
public class PopupAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PopupAct()   { }

    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   flag          : 팝업의 조회구분( int 형으로 1부터증가처리)
    * @param   paramValues   : 조회구분 에 따라 조회조건을 처리하기 위해서 배열로 저장
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(int flag, RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopupSvc svc = new PopupSvc();
            retrieve = svc.retrieve(con, flag, retrieve);

            switch (flag)
                { case Construct.P_GOODS_CODE  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_NAME  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_CODE_ALL  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_NAME_ALL  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_CODE_GIFT  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_NAME_GIFT  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;


                } case Construct.P_RACK_CODE_GOODS  : {
                    //= retrieve L038
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L038"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_RACK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_RACK_CODE_GOODS_GRADE  : {
                    //= retrieve L038
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L038"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_RACK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_RACK_CODE_FIX  : {
                    //= retrieve L038
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L038"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_RACK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_RACK_CODE_ETC  : {
                    //= retrieve L038
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L038"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_RACK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_RACK_CODE_NOT_REG  : {
                    //= retrieve L038
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L038"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_RACK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_CHECK_CODE  : {
                    //= retrieve L013
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "L013"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_CHECK_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_CODE_DESCRIBE_COPY  : {

                    HashMap[] retSheet = null;

                    retSheet = (HashMap[])retrieve.getObject("RESULT");
                    String tempString = "";
                    Collection   collection = new ArrayList();

                    if(retSheet != null){
                        for(int i =0; i < retSheet.length ; i++){

                            collection.add(retSheet[i]);
                        }

                        retrieve.put("RESULT",(HashMap[]) collection.toArray(new HashMap[0]));
                    }

                    break;
                } case Construct.P_MASTER_CODE  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_MASTER_NAME  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_OB_SEQ : {
                	ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "C030"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_C030",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;
                } case Construct.P_MENU_PROGRAM : {
                	ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    // lgroup, mgroup
                    paramValues[0] = ""; //
                    retrieve.put("RESULT_C_LMENU",comboAct.retrieve(con, Construct.C_LMENU, null, Construct.P_COMBO_DISPLAY_HTML_BLANK));
                    break;
                } case Construct.P_SLIP_SEARCH : {
                	ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    // dely_gb
                    paramValues[0] = "B005"; //
                    retrieve.put("RESULT_C_DELY_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;
                } case Construct.P_PROGRAM_ID : {
                	ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    break;
                } case Construct.P_GOODS_CODE_SCM  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_GOODS_NAME_SCM  : {
                    //= retrieve sale_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B032"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_SALE_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                } case Construct.P_MODEL_CODE_NAME : {
                    //= retrieve sqc_gb
                    ComboAct comboAct = new ComboAct();
                    String[] paramValues = new String[1];
                    paramValues[0] = "B023"; // TCODE.CODE_LGROUP
                    retrieve.put("RESULT_C_MODEL_GB",comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
                    break;

                }
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
