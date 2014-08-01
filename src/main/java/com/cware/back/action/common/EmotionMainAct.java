
package com.cware.back.action.common;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.HeaderModel;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.EmotionMainSvc;

/**
 * 총괄현황 Action Bean
 *
 * @version 1.0, 2007/03/08
 */
public class EmotionMainAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public EmotionMainAct() {}

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

            EmotionMainSvc svc = new EmotionMainSvc();

            retrieve = svc.retrieve(con, retrieve);
            retrieve = svc.retrieveWeeklySale(con, retrieve);

        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return retrieve;
    }

    // Control retreive -------------------------------------------------
    /**
    * <PRE>
    * Desc : Control retrieval action Order
    * </PRE>
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieveGraph(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{

        	con = DBUtils.getConnection(Construct.DB_POOL_NAME);
        	EmotionMainSvc svc = new EmotionMainSvc();

            retrieve = svc.retrieveGraph(con, retrieve);

        }catch(StoreException se){
            retrieve.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            retrieve.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
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
    public RetrieveModel retrieveMain(RetrieveModel retrieve) throws StoreException {
        Connection con  = null;
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);

            EmotionMainSvc svc = new EmotionMainSvc();

            retrieve = svc.retrieveCustomer(con, retrieve);
            retrieve = svc.retrieveGoods(con, retrieve);
            retrieve = svc.retrievePromo(con, retrieve);
            retrieve = svc.retrieveDelivery(con, retrieve);

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