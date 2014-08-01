
package com.cware.back.service.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tafterqa;

/**
 * Register TAFTERQA Service class
 *
 * @version 1.0, 2011/01/14
 */
public class InspectGoodsSvc {


    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public InspectGoodsSvc() {}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; Master
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    private String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT  E.BALJU_NO   											\n");
        sb.append("        ,D.WH_CODE	    										\n");
        sb.append("        ,TO_CHAR(B.IN_DATE, 'YYYY/MM/DD') AS IN_DATE	    		\n");
        sb.append("        ,B.IN_MAN_ID     										\n");
        sb.append("        ,E.GOODS_CODE     										\n");
        sb.append("        ,E.GOODSDT_CODE     										\n");
        sb.append("        ,E.BALJU_QTY     										\n");
        sb.append("        ,TO_CHAR(A.QA_DATE, 'YYYY/MM/DD') AS QA_DATE 			\n");
        sb.append("        ,A.QA_QTY   												\n");
        sb.append("        ,A.SQC_GB    											\n");
        sb.append("        ,C.GOODSDT_INFO    										\n");
        sb.append("        ,C.GOODS_NAME    										\n");
        sb.append("   FROM  TAFTERQA A 												\n");
        sb.append("       ,TINM B 													\n");
        sb.append("       ,TGOODSDT C 												\n");
        sb.append("       ,TBALJUM D 												\n");
        sb.append("       ,TBALJUDT E 												\n");
        sb.append("  WHERE E.BALJU_NO = A.BALJU_NO(+) 								\n");
        sb.append("    AND E.GOODS_CODE = A.GOODS_CODE(+)	 						\n");
        sb.append("    AND E.GOODSDT_CODE = A.GOODSDT_CODE(+)	 					\n");
        sb.append("    AND B.BALJU_NO(+) = D.BALJU_NO 								\n");
        sb.append("    AND D.BALJU_NO = E.BALJU_NO	 								\n");
        sb.append("    AND C.GOODS_CODE = E.GOODS_CODE	 							\n");
        sb.append("    AND C.GOODSDT_CODE = E.GOODSDT_CODE	 						\n");
        sb.append("    AND D.BALJU_NO = ?											\n");
        sb.append("    ORDER BY E.GOODS_CODE,E.GOODSDT_CODE 						\n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
            log.debug("balju_no    : "  + retrieve.getString("balju_no"));
        }
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Insert TAFTERQA )
    * </PRE>
    * @param   TAFTERQA
    * @return  String
    */
    private String makeSqlInsert(Tafterqa tafterqa) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  INSERT INTO TAFTERQA (    \n ");
        sb.append("              BALJU_NO    ,   \n ");
        sb.append("              GOODS_CODE    ,   \n ");
        sb.append("              GOODSDT_CODE   ,   \n ");
        sb.append("              BALJU_QTY ,   		\n ");
        sb.append("              QA_DATE          ,   \n ");
        sb.append("              SQC_GB          ,   \n ");
        sb.append("              QA_QTY          ,   \n ");
        sb.append("              INSERT_DATE  ,   \n ");
        sb.append("              INSERT_ID    )   \n ");
        sb.append("       VALUES (                \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              ?,               \n ");
        sb.append("              SYSDATE,         \n ");
        sb.append("              ?)              \n ");


        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug(sb.toString());
        }

        return sb.toString();
    }



    //= Edit Result-----------------------------------------------
    /**
    * <PRE>
    * Desc : Edit retrieval result : Master
    * </PRE>
    * @param   ResultSet
    * @return  HashMap[]
    */
    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        String       tempString = "";
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);

            //= Modify

            collection.add(hmSheet);
            retRow++;
        }

        //= log Column Name & retrieve row no ---------------------
        if (log.isDebugEnabled()) {
            if(hmSheet != null){
                Collection c=hmSheet.keySet();
                Iterator i=c.iterator();
                while(i.hasNext()){
                    Object key=i.next();
                    log.debug(key.toString());
                }
            }
            log.debug("Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }


    //= Retrieve -------------------------------------------------
    /**
    * <PRE>
    * Desc : Retrieve SQL ; Master
    * </PRE>
    * @param   Connection
    * @param   RetrieveModel
    * @return  RetrieveModel
    */
    public RetrieveModel retrieve(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;

        try {
            pstmt = con.prepareStatement(makeSql(retrieve));

            int index = 1;
            pstmt.setString(index++,retrieve.getString("balju_no"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("[InspectGoodsSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[InspectGoodsSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }


    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TBEFOREQAM
    * </PRE>
    * @param   Connection
    * @param   TMD
    * @return  int
    */
	public int insert(Connection con, Tafterqa tafterqa) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {

            pstmt = con.prepareStatement(makeSqlInsert(tafterqa));
            int index = 1;
            pstmt.setString(index++,tafterqa.getBalju_no());
            pstmt.setString(index++,tafterqa.getGoods_code());
            pstmt.setString(index++,tafterqa.getGoodsdt_code());
            pstmt.setInt	(index++,tafterqa.getBalju_qty());
            //pstmt.setString(index++,tafterqa.getQa_date());
            pstmt.setString(index++,tafterqa.getSqc_gb());
            pstmt.setInt	(index++,tafterqa.getQa_qty());
            pstmt.setString(index++,tafterqa.getInsert_id());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tafterqa.getBalju_no()        ); logString.append( "/" );
            logString.append( tafterqa.getGoods_code()        ); logString.append( "/" );
            logString.append( tafterqa.getGoodsdt_code()        ); logString.append( "/" );
            logString.append( tafterqa.getBalju_qty()        ); logString.append( "/" );
            //logString.append( tafterqa.getQa_date()        ); logString.append( "/" );
            logString.append( tafterqa.getSqc_gb()        ); logString.append( "/" );
            logString.append( tafterqa.getQa_qty()        ); logString.append( "/" );
            logString.append( tafterqa.getInsert_id()        ); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[InspectGoodsSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[InspectGoodsSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

}
