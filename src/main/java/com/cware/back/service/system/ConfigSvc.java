
package com.cware.back.service.system;

import com.cware.back.common.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;




/**
 * Config Service class
 *
 * @version 1.0, 2006/07/07
 * @author commerceware.co.kr
 */
public class ConfigSvc {

 //   private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
 //   private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public ConfigSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT A.ITEM,    \n ");
        sb.append("         A.VAL,     \n ");
        sb.append("         A.CONTENT  \n ");
        sb.append("    FROM TCONFIG A  \n ");

        //= log SQL -------------------------------------------------
     
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Make SQL ( TUSER 변경 )
    * </PRE>
    * @param   Tconfig
    * @return  String
    */
    public String makeSqlUpdate() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE  TCONFIG SET \n ");
        sb.append("         VAL     = ? \n ");
        sb.append("   WHERE ITEM    = ? \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }
        return sb.toString();

    }

    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    public HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }
        return null;
    }
}