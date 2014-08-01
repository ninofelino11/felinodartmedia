package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tboardtm;
import com.cware.back.service.common.BoardTmSvc;

public class BoardTmAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public BoardTmAct() {}

    /**
     * 게시판 리스트 조회
     * @param retrieve
     * @return
     * @throws StoreException
     */
    public RetrieveModel retrieveList(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
        	//con = DBUtils.getConnection("jdbc/dbsource1");
            BoardTmSvc svc = new BoardTmSvc();

            svc.retrieveList(con, retrieve);

        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }

    public String save(Tboardtm tboardtm) throws StoreException {
        Connection con  = null;
        String rtnMsg = "000000";
        String aid = "";
        int executedRtn = 0;
        RetrieveModel retrieve = new RetrieveModel();
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
        	//con = DBUtils.getConnection("jdbc/dbsource1");
        	BoardTmSvc svc = new BoardTmSvc();

            if(tboardtm.getCwareAction().equals("INSERT")) {
            	aid  = DBUtils.getMax(con, "TBOARDTM", "AID", "", 0);
                if(aid.equals("")){
                	rtnMsg = ComUtils.getMessage("msg.cannot_create") + "AID";
                    log.error(rtnMsg);
                    return rtnMsg;
                }
            	tboardtm.setAid(aid);
            	tboardtm.setRid(aid);
            	tboardtm.setThread("A");
//ComUtils.text2db

            	log.info("//---- insert TBOARDTM");
                executedRtn = svc.insert(con, tboardtm);
                if (executedRtn != 1){
                	rtnMsg = ComUtils.getMessage("msg.cannot_save") + "TBRAND insert";
                    log.error(rtnMsg);
                    return rtnMsg;
                }

            } else if (tboardtm.getCwareAction().equals("MODIFY")) {
                executedRtn = svc.update(con, tboardtm);
                if (executedRtn != 1){
                	rtnMsg = ComUtils.getMessage("msg.cannot_save") + "TBRAND insert";
                    log.error(rtnMsg);
                    return rtnMsg;
                }
            } else if (tboardtm.getCwareAction().equals("REPLY")) {
            	retrieve.put("str_aid", tboardtm.getAid());
            	svc.getOriginalData(con, retrieve);

        	  	String new_thread = "";
        	  	if(!retrieve.getString("MAX_THREAD").equals("")) {
        	   		char last = retrieve.getString("MAX_THREAD").charAt(retrieve.getString("MAX_THREAD").length()-1);
        	   		last++;
        	   		new_thread = retrieve.getString("MAX_THREAD").substring(0,retrieve.getString("MAX_THREAD").length()-1) + last;
        	  	} else{
        	  		new_thread = retrieve.getString("THREAD")+"A";
        	  	}

            	aid  = DBUtils.getMax(con, "TBOARDTM", "AID", "", 0);
                if(aid.equals("")){
                	rtnMsg = ComUtils.getMessage("msg.cannot_create") + "AID";
                    log.error(rtnMsg);
                    return rtnMsg;
                }
            	tboardtm.setAid(aid);
            	tboardtm.setRid(retrieve.getString("RID"));
            	tboardtm.setThread(new_thread);

                executedRtn = svc.insert(con, tboardtm);
                if (executedRtn != 1){
                	rtnMsg = ComUtils.getMessage("msg.cannot_save") + "TBOARDTM insert";
                    log.error(rtnMsg);
                    return rtnMsg;
                }
            } else if (tboardtm.getCwareAction().equals("DELETE")) {
            	retrieve.put("str_aid", tboardtm.getAid());
            	svc.getOriginalData(con, retrieve);
            	tboardtm.setFilename(retrieve.getString("FILENAME"));
            	if(retrieve.getString("MAX_THREAD").equals("")){
	                executedRtn = svc.delete(con, tboardtm);
	                if (executedRtn != 1){
	                	rtnMsg = ComUtils.getMessage("msg.cannot_save") + "TBOARDTM delete";
	                    log.error(rtnMsg);
	                    return rtnMsg;
	                }
            	}
            } else {
            	rtnMsg = ComUtils.getMessage("msg.cannot_process_act");
                log.error(rtnMsg);
                return rtnMsg;
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

    public RetrieveModel retrieveView(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
        	//con = DBUtils.getConnection("jdbc/dbsource1");
            BoardTmSvc svc = new BoardTmSvc();

            svc.retrieveView(con, retrieve);

            con.commit();

        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception ec){}
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }

}
