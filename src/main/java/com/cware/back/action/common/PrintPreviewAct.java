
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.service.common.PrintPreviewSvc;

/**
 * PrintPreview Action Bean
 *
 * @version 1.0, 2006/08/25
 * @author commerceware.co.kr
 */
public class PrintPreviewAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public PrintPreviewAct()   { }

    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName      : DB 의 서비스명
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(RetrieveModel retrieve, String[] paramValues) throws StoreException {
        Connection con  = null;
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            PrintPreviewSvc svc = new PrintPreviewSvc();
            retrieve = svc.retrieve(con, retrieve, paramValues);

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
