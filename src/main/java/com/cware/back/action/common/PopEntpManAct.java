
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tentpuser;
import com.cware.back.service.common.PopEntpManSvc;

/**
 * 업체담당자 Popup Action class
 *
 * @version 1.0, 2006/07/27
 * @author  commerceware.co.kr
 */
public class PopEntpManAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public PopEntpManAct() { }
    // Control Haeder retreive -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control Haeder retrieval action
    * </PRE>
    * @return  HeaderModel
    */

    public HeaderModel retrieveHeader(HeaderModel retrieve, String programId) throws StoreException {

        if(!programId.equals(retrieve.getString("PROGRAM_ID"))){
        	retrieve = new HeaderModel();
        	Connection con = null;
            try{
                con = DBUtils.getConnection(Construct.DB_POOL_NAME);

                retrieve.put("PROGRAM_ID", programId);

                //= retrieve code

                ComboAct comboAct = new ComboAct();
                String[] paramValues = new String[1];

                paramValues[0] = "B120"; // TCODE.CODE_LGROUP
           //     retrieve.put("SHEET_C_ENTP_MAN_GB_GRID", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_GRID));
             //   retrieve.put("SHEET_C_ENTP_MAN_GB_HTML", comboAct.retrieve(con, Construct.C_TCODE, paramValues, Construct.P_COMBO_DISPLAY_HTML));

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
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopEntpManSvc svc = new PopEntpManSvc();

            retrieve = svc.retrieve(con, retrieve);

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
    * @param   tentpuser
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */

    public String save(Tentpuser tentpuser) throws StoreException {
        Connection  con         = null;
        String      rtnMsg      = "000000";
        int         executedRtn = 0;
        String      default_yn  = "";
        String      use_yn      = "";
        String      entp_man_seq= "";
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopEntpManSvc svc = new PopEntpManSvc();

            //= 입력자료 정리
            tentpuser.setBlank();

            default_yn = tentpuser.getDefault_yn();
            if( default_yn.equals("1" ) ) {
                tentpuser.setDefault_yn("1");
            } else {
                tentpuser.setDefault_yn("0");
            }

            use_yn = tentpuser.getUse_yn();
            if( use_yn.equals("1" ) ) {
                tentpuser.setUse_yn("1");
            } else {
                tentpuser.setUse_yn("0");
            }

            tentpuser.setEntp_man_name( ComUtils.text2db( tentpuser.getEntp_man_name() ) );
            tentpuser.setTrans_note   ( ComUtils.text2db( tentpuser.getTrans_note() ) );

            //= 업체 담당자의 default_yn  처리
            if( tentpuser.getCwareAction().equals("INSERT") || tentpuser.getCwareAction().equals("MODIFY")) {
               // if( tentpuser.getCwareInfo().equals("DEFAULT_CHANGE") ) {
            	  if( tentpuser.getDefault_yn().equals("1")){
                    executedRtn = svc.updateDisableDefault(con, tentpuser);
                    if (executedRtn < 0){
                        logTemp = ComUtils.getMessage("msg.cannot_save", "DEFAULT_CHANGE (update)");
                        logSave.error(logTemp);
                        throw new StoreException(logTemp);
                    }
                 }
            }

            //= insert
            if( tentpuser.getCwareAction().equals("INSERT") ) {

                logSave.info("//= INSERT TENTPUSER");
                entp_man_seq = DBUtils.getMax(con,
                                              "TENTPUSER",
                                              "ENTP_MAN_SEQ",
                                              "WHERE ENTP_CODE = '"+tentpuser.getEntp_code()+"'",
                                              3);
                tentpuser.setEntp_man_seq ( entp_man_seq );

                executedRtn = svc.insert(con, tentpuser);
                if (executedRtn < 1){
                    logTemp = ComUtils.getMessage("msg.cannot_save", "TENTPUSER (insert)");
                    logSave.error(logTemp);
                    throw new StoreException(logTemp);
                }

            //= update
            } else if( tentpuser.getCwareAction().equals("MODIFY") ) {

                logSave.info("//= UPDATE TENTPUSER");
                executedRtn = svc.update(con, tentpuser);

                if (executedRtn < 1){
                    logTemp = ComUtils.getMessage("msg.cannot_save", "TENTPUSER (update)");
                    logSave.error(logTemp);
                    throw new StoreException(logTemp);
                }
                /*
                if( tentpuser.getCwareInfo().equals("DEFAULT_CHANGE") ) {
                    executedRtn = svc.updateDisableDefault(con, tentpuser);
                    if (executedRtn < 0){
                        logTemp = "DEFAULT_CHANGE (update) 저장에 실패했습니다.";
                        logSave.error(logTemp);
                        throw new StoreException(logTemp);
                    }
                }
                */
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
