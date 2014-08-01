
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PopExpectedProfitSvc;

/**
 * Register MD Action class
 *
 * @version 1.0, 2006/07/13
 */
public class PopExpectedProfitAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);
    private String logTemp      = "";

    public PopExpectedProfitAct()    { }

    // Control Haeder retreive -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control Haeder retrieval action
    * </PRE>
    * @return  HeaderModel
    */
//    public HeaderModel retrieveHeader(HeaderModel retrieve, String programId) throws StoreException {
//
//        if(!programId.equals(retrieve.getString("PROGRAM_ID"))){
//        	retrieve = new HeaderModel();
//        	Connection con  = null;
//            try{
//                con = DBUtils.getConnection(Construct.DB_POOL_NAME);
//
//                retrieve.put("PROGRAM_ID",programId);
//
//            }catch(StoreException se){
//                retrieve.put("ERROR_MESSAGE",se.getMessage());
//            }catch(Exception e){
//                retrieve.put("ERROR_MESSAGE",e.getMessage());
//            }finally {
//                DBUtils.freeConnection(con);
//            }
//        }
//        return retrieve;
//    }

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
        HashMap[] resultSheet = null;
        double sale_cost = 0.0;

        try{

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopExpectedProfitSvc svc = new PopExpectedProfitSvc();

            retrieve = svc.retrieve(con, retrieve);
            resultSheet = (HashMap[])retrieve.get("RESULT1");
            if (resultSheet != null && resultSheet.length > 0) {
	            sale_cost = ComUtils.objToDouble(resultSheet[0].get("SALE_COST"));
	            retrieve.put("sale_cost", sale_cost);
            }

            retrieve = svc.retrieveDetail(con, retrieve);

        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }

    // Control retreive Detail -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control retrieval action ; Detail
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveDetail(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PopExpectedProfitSvc svc = new PopExpectedProfitSvc();

            retrieve = svc.retrieveDetail(con, retrieve);

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