
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tfavoritesdt;
import com.cware.back.entity.table.Tfavoritesm;
import com.cware.back.entity.table.Tmd;
import com.cware.back.entity.table.Tmdlink;
import com.cware.back.service.common.CommonDBSvc;
import com.cware.back.service.common.MenuSvc;

/**
 * User Action class
 *
 * @version 1.0, 2006/05/08
 */
public class MenuAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

	public MenuAct()	{ }

    // Control Haeder retreive -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control Haeder retrieval action
    * </PRE>
    * @return  HeaderModel
    */

    public HeaderModel retrieveHeader(HeaderModel retrieve, String programId) throws StoreException {

        if(!programId.equals(retrieve.getString("PROGRAM_ID"))){
        	Connection con = null;
            try{
                con = DBUtils.getConnection(Construct.DB_POOL_NAME);
                MenuSvc svc = new MenuSvc();

                ComboAct comboAct = new ComboAct();
                String[] paramValues = new String[1];

                retrieve.put("PROGRAM_ID",programId);
                paramValues[0] = retrieve.getString("userId");
                retrieve.put("HEAD_C_QUICK_GRUOP",comboAct.retrieve(con, Construct.C_QUICK_GRUOP, paramValues, Construct.P_COMBO_DISPLAY_HTML));

                
            }catch(StoreException se){
                retrieve.put("ERROR_MESSAGE",se.getMessage());
                throw new StoreException(se.getMessage());
            }catch(Exception e){
                retrieve.put("ERROR_MESSAGE",e.getMessage());
                throw new StoreException(e.getMessage());
            }finally {
                DBUtils.freeConnection(con);
            }
        }
        return retrieve;
    }
	public RetrieveModel retrieveQuickM(String user_id) throws StoreException {
		
		Connection con = null;
		RetrieveModel quickMenu = new RetrieveModel();

		try {
			con = DBUtils.getConnection(Construct.DB_POOL_NAME);
			MenuSvc svc = new MenuSvc();

			svc.retrieveQuickM(con, quickMenu, user_id);

		} catch (StoreException se) {
			log.debug("StoreException : " + se);
			throw new StoreException(se.getMessage());
		} catch (Exception e) {
			log.debug("Exception : " + e);
			throw new StoreException(e.getMessage());
		} finally {
			DBUtils.freeConnection(con);
		}
		return quickMenu;
	}

	public RetrieveModel retrieveQuickDT(String user_id, String group_code) throws StoreException {
		Connection con = null;
		RetrieveModel quickMenu = new RetrieveModel();
		
		try {
			con = DBUtils.getConnection(Construct.DB_POOL_NAME);
			MenuSvc svc = new MenuSvc();
		
			svc.retrieveQuickDT(con, quickMenu, user_id, group_code);
		
		} catch (StoreException se) {
			log.debug("StoreException : " + se);
			throw new StoreException(se.getMessage());
		} catch (Exception e) {
			log.debug("Exception : " + e);
			throw new StoreException(e.getMessage());
		} finally {
			DBUtils.freeConnection(con);
		}
		return quickMenu;
	}
    // Control save -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control save action
    * </PRE>
    * @param   Tfavoritesm Array
    * @param   Tfavoritesdt Array
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */
    public String save(Tfavoritesm[] tfvtm, Tfavoritesdt[] tfvtdt) throws StoreException {

        int        executedRtn    = 0;
        String     rtnMsg         = "000000";
        String     sysDateTime    = "";
        String     validateMsg    = "";
        Connection con            = null;

        try{
            validateMsg = validateData(tfvtm, tfvtdt);
            if(!validateMsg.equals("000000")){
                rtnMsg = validateMsg;
                return rtnMsg;
            }

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            MenuSvc svc = new MenuSvc();

            sysDateTime = DBUtils.getSysDateTime(con, "YYYY/MM/DD HH24:MI:SS");
            if(sysDateTime.equals("")){
                logTemp = ComUtils.getMessage("msg.cannot_sysdate") + " : sysDateTime";
                logSave.error(logTemp);
                throw new StoreException(logTemp);
            }
            
            Tfavoritesm regTFvtM = null;
            Tfavoritesdt regTFvtDt = null;
            
            if(tfvtm != null){
              for(int i = 0 ; i < tfvtm.length; i++){
            	  regTFvtM = tfvtm[i];
                  if(regTFvtM.getCwareAction().equals("I")){
                	  regTFvtM.setInsert_date( sysDateTime );
                	  regTFvtM.setModify_date( sysDateTime );

                      // = Tfavoritesm 삽입시  중복 user_id 체크
                      executedRtn = svc.checkMDup(con, regTFvtM);
                      if (executedRtn != 0){
          	            logTemp = ComUtils.getMessage("msg.dup_data", ComUtils.getMessage("word.group_code"));
          	            logSave.error(logTemp);
          	            throw new StoreException(logTemp);
                      }else {
                          executedRtn = svc.insertM(con, regTFvtM) ;
                          if (executedRtn != 1){
                              logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESM insert");
                              logSave.error(logTemp);
                              throw new StoreException(logTemp);
                          }
                     }
                  }else if(regTFvtM.getCwareAction().equals("U") || regTFvtM.getCwareAction().equals("UC")){
                	  regTFvtM.setModify_date( sysDateTime );

                      executedRtn = svc.updateM(con, regTFvtM ) ;
                      if (executedRtn != 1){
                          logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESM update");
                          logSave.error(logTemp);
                          throw new StoreException(logTemp);
                      }
                  }else if(regTFvtM.getCwareAction().equals("D")){
                	  
                	  executedRtn = svc.deleteM(con, regTFvtM ) ;
                	  if (executedRtn != 1){
                		  logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESM delete");
                		  logSave.error(logTemp);
                		  throw new StoreException(logTemp);
                	  }
                	  //= Master DELETE 인경우 Detail도 함께 Delete한다. 
                	  regTFvtDt = new Tfavoritesdt();
                	  regTFvtDt.setUser_Id(regTFvtM.getUser_Id());
                	  regTFvtDt.setGroup_Code(regTFvtM.getGroup_Code());
                	  regTFvtDt.setProgram_Id("%");
                	  
                	  executedRtn = svc.deleteDt(con, regTFvtDt ) ;
                	  if (executedRtn != 1){
                		  logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT Muli delete");
                		  logSave.error(logTemp);
                		  throw new StoreException(logTemp);
                	  }
                      
                  } else {
                      logTemp = ComUtils.getMessage("msg.cannot_process_act");
                      logSave.error(logTemp);
                      throw new StoreException(logTemp);
                  }
              }
          }

          if(tfvtdt != null){
        	  for(int i = 0 ; i < tfvtdt.length; i++){
        		  regTFvtDt = tfvtdt[i];
	              if(regTFvtDt.getCwareAction().equals("I")){
	            	  regTFvtDt.setInsert_date( sysDateTime );
	
	                  // = Tfavoritesdt 삽입시  중복 user_id 체크
	                  executedRtn = svc.checkDtDup(con, regTFvtDt);
	                  if (executedRtn != 0){
	      	            logTemp = ComUtils.getMessage("msg.dup_data", ComUtils.getMessage("word.program_id"));
	      	            logSave.error(logTemp);
	      	            throw new StoreException(logTemp);
	                  }else {
	                      executedRtn = svc.insertDt(con, regTFvtDt) ;
	                      if (executedRtn != 1){
	                          logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT insert");
	                          logSave.error(logTemp);
	                          throw new StoreException(logTemp);
	                      }
	                 }
	              }else if(regTFvtDt.getCwareAction().equals("U") || regTFvtDt.getCwareAction().equals("UC")){
	
	                  executedRtn = svc.updateDt(con, regTFvtDt ) ;
	                  if (executedRtn != 1){
	                      logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT update");
	                      logSave.error(logTemp);
	                      throw new StoreException(logTemp);
	                  }
	              }else if(regTFvtDt.getCwareAction().equals("D")){
	            	  
	            	  executedRtn = svc.deleteDt(con, regTFvtDt ) ;
	            	  if (executedRtn != 1){
	            		  logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT delete");
	            		  logSave.error(logTemp);
	            		  throw new StoreException(logTemp);
	            	  }
	
	              } else {
	                  logTemp = ComUtils.getMessage("msg.cannot_process_act");
	                  logSave.error(logTemp);
	                  throw new StoreException(logTemp);
	              }
	          }     
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

    // Control save -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control save action
    * </PRE>
    * @param   Tfavoritesdt Array
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */
    public String save(Tfavoritesdt tfvtdt, String group_yn, String group_name) throws StoreException {

        int        executedRtn    = 0;
        String     rtnMsg         = "000000";
        String     sysDateTime    = "";
        String	   group_code	  = "";
        Connection con            = null;

        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            MenuSvc svc = new MenuSvc();

            sysDateTime = DBUtils.getSysDateTime(con, "YYYY/MM/DD HH24:MI:SS");
            if(sysDateTime.equals("")){
                logTemp = ComUtils.getMessage("msg.cannot_sysdate") + " : sysDateTime";
                logSave.error(logTemp);
                throw new StoreException(logTemp);
            }
            
            if ( group_yn.equals("1")) {
            	//= Master 직접 추가 포함인경우
            	CommonDBSvc     comsvc  = new CommonDBSvc();
            	tfvtdt.setGroup_Code(comsvc.getNewGroupSeq(con, tfvtdt.getUser_Id()));

            	Tfavoritesm regM = new Tfavoritesm();
    			regM.setUser_Id(tfvtdt.getUser_Id());
    			regM.setGroup_Code(tfvtdt.getGroup_Code());
    			regM.setGroup_Name(group_name);
    			regM.setP_Group_Id("ROOT");
    			regM.setSort(tfvtdt.getSort());
    			regM.setInsert_id(tfvtdt.getUser_Id());
    			regM.setModify_id(tfvtdt.getUser_Id());
    			regM.setInsert_date( sysDateTime );
    			regM.setModify_date( sysDateTime );

    			logSave.info("//= INSERT TFAVORITESM");
    			// = Tfavoritesm 삽입시  중복 user_id 체크
    			executedRtn = svc.insertM(con, regM);
                if (executedRtn < 1){
    	            logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESM insert");
    	            logSave.error(logTemp);
    	            throw new StoreException(logTemp);
                }    			
            }

            //= Check
            executedRtn = svc.checkDtDup(con, tfvtdt);
            if( executedRtn > 0 ){
            	tfvtdt.setCwareAction("MODIFY");
            }else{
            	tfvtdt.setCwareAction("INSERT");
            }            

            if(tfvtdt.getCwareAction().equals("INSERT")) {
                logSave.info("//= INSERT TFAVORITESDT");
                tfvtdt.setInsert_date( sysDateTime );
                executedRtn = svc.insertDt(con, tfvtdt);
                if (executedRtn < 1){
    	            logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT insert");
    	            logSave.error(logTemp);
    	            throw new StoreException(logTemp);
                }

            }else{
            	logSave.info("//= UPDATE TFAVORITESDT");
                executedRtn = svc.updateDt(con, tfvtdt);
                if (executedRtn < 1){
    	            logTemp = ComUtils.getMessage("msg.cannot_save", "TFAVORITESDT update");
    	            logSave.error(logTemp);
    	            throw new StoreException(logTemp);
                }
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


    // Control validate Data -------------------------------------------------
    /**
    * <PRE>
    * Desc : validate check action
    * </PRE>
    * @param   Tfavoritesm Array
    * @param   Tfavoritesdt Array
    * @return  String ( SUCCESS:000000 , ERROR:MESSAGE )
    */
    public String validateData ( Tfavoritesm[] tfvtm, Tfavoritesdt[] tfvtdt ) throws StoreException {

        String rtnMsg = "000000";

          if( ( tfvtm!=null && tfvtm.length > 0 && tfvtm[0].getCwareAction()==null )?true:false
        		  ||  ( tfvtdt!=null && tfvtdt.length > 0 && tfvtdt[0].getCwareAction()==null )?true:false ) {
            	logTemp = ComUtils.getMessage("msg.do_retry_un")
				+ " " +ComUtils.getMessage("msg.no_data", "getCwareAction()");
        	logSave.error(logTemp);
        	rtnMsg = logTemp;
        	return rtnMsg;

        }

        return rtnMsg;
    }
}
