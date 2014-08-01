
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tasentp;
import com.cware.back.service.common.PopGoodsserviceSvc;

/**
 * A/S업체 Popup Action class
 *
 * @version 1.0, 2006/07/28
 * @author  commerceware.co.kr
 */
public class PopGoodsserviceAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public PopGoodsserviceAct() { }

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
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopGoodsserviceSvc svc = new PopGoodsserviceSvc();

            retrieve = svc.retrieve(con, retrieve);

/*
            ComboAct comboAct = new ComboAct();
            String[] paramValues = new String[1];
            paramValues[0] = "B120"; // TCODE.CODE_LGROUP
            retrieve.put("SHEET_C_ENTP_MAN_GB_GRID", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
            retrieve.put("SHEET_C_ENTP_MAN_GB_HTML", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_HTML));
*/
        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }


    // Control save -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control save action
    * </PRE>
    * @param   tasentp
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */

    public String save(Tasentp tasentp) throws StoreException {
        Connection  con         = null;
        String      rtnMsg      = "000000";
        int         executedRtn = 0;
        String      use_yn      = "";
        String      entp_seq    = "";
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopGoodsserviceSvc svc = new PopGoodsserviceSvc();

            //= 입력자료 정리
            tasentp.setBlank();

            use_yn = tasentp.getUse_yn();
            if( use_yn.equals("1" ) ) {
                tasentp.setUse_yn("1");
            } else {
                tasentp.setUse_yn("0");
            }

            //= insert
            if( tasentp.getCwareAction().equals("INSERT") ) {

                logSave.info("//= INSERT TASENTP");
                entp_seq = DBUtils.getMax(con,
                                              "TASENTP",
                                              "ENTP_SEQ",
                                              "WHERE ENTP_CODE = '"+tasentp.getEntp_code()+"'",
                                              3);
                tasentp.setEntp_seq ( entp_seq );

                executedRtn = svc.insert(con, tasentp);
                if (executedRtn < 1){
                    logTemp = ComUtils.getMessage("msg.cannot_save", "TASENTP (insert)");
                    logSave.error(logTemp);
                    throw new StoreException(logTemp);
                }

            //= update
            } else if( tasentp.getCwareAction().equals("MODIFY") ) {

                logSave.info("//= UPDATE TASENTP");
                executedRtn = svc.update(con, tasentp);

                if (executedRtn < 1){
                    logTemp = ComUtils.getMessage("msg.cannot_save", "TASENTP (update)");
                    logSave.error(logTemp);
                    throw new StoreException(logTemp);
                }

            } else {
                logTemp = ComUtils.getMessage("msg.no_flag_a", "update flag");
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
