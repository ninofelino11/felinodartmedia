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
import com.cware.back.entity.table.Tinspect_goods;
import com.cware.back.entity.table.Tinspect_goods_grp;

/**
 * QaInspect Service class
 *
 * @version 1.0, 2011/01/20
 */
public class QaInspectSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public QaInspectSvc() {}

    /**
    * <PRE>
    * Desc : Retrieve SQL
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
            pstmt.setString(index++, retrieve.getString("inspect_lcode"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT", makeSheet(rset));

        }catch(SQLException se){
            log.error("[QaInspectSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[QaInspectSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSql( RetrieveModel retrieve ) throws StoreException{

        StringBuffer sb = new StringBuffer();

		sb.append(" SELECT A.INSPECT_LCODE,                    \n");
		sb.append("        B.CODE_NAME AS INSPECT_LNAME,       \n");
		sb.append("        A.INSPECT_MCODE,                    \n");
		sb.append("        A.INSPECT_MNAME,                    \n");
		sb.append("        A.USE_YN                            \n");
		sb.append("   FROM TINSPECT_GOODS A, TCODE B           \n");
		sb.append("  WHERE A.INSPECT_LCODE = B.CODE_MGROUP     \n");
		sb.append("    AND B.CODE_LGROUP = 'B124'              \n");
		sb.append("    AND A.INSPECT_LCODE LIKE ? || '%'       \n");
		sb.append("  ORDER BY A.INSPECT_LCODE, A.INSPECT_MCODE \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
            log.debug("inspect_lcode:  " + retrieve.getString("inspect_lcode"));
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   RetrieveModel
     * @return  RetrieveModel
     */
    public RetrieveModel retrieveDetail(Connection con, RetrieveModel retrieve) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;

    	try {
    		pstmt = con.prepareStatement(makeSqlDetail(retrieve));

    		int index = 1;
    		pstmt.setString(index++, retrieve.getString("inspect_lcode"));
    		pstmt.setString(index++, retrieve.getString("inspect_mcode"));

    		rset  = pstmt.executeQuery();

    		retrieve.put("RESULT_DETAIL", makeSheet(rset));

    	}catch(SQLException se){
    		log.error("[QaInspectSvc.retrieveDetail() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[QaInspectSvc.retrieveDetail() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL
     * */
    public String makeSqlDetail( RetrieveModel retrieve ) throws StoreException{

    	StringBuffer sb = new StringBuffer();
		sb.append("SELECT A.INSPECT_LCODE, A.INSPECT_MCODE,                     \n");
		sb.append("       B.LGROUP, B.LGROUP_NAME,                              \n");
		sb.append("       B.MGROUP, B.MGROUP_NAME,                              \n");
		sb.append("       B.SGROUP, B.SGROUP_NAME,                              \n");
		sb.append("       B.DGROUP, B.DGROUP_NAME,                              \n");
		sb.append("       A.USE_YN                                              \n");
		sb.append("  FROM TINSPECT_GOODS_GRP A, TGOODSKINDS B                   \n");
		sb.append(" WHERE A.INSPECT_LCODE = ?                                   \n");
		sb.append("   AND A.INSPECT_MCODE = ?                                   \n");
		sb.append("   AND A.LMSD_CODE = B.LMSD_CODE                             \n");
		sb.append(" ORDER BY A.LMSD_CODE  										\n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug("\n" + sb.toString());
    		log.debug("inspect_lcode:  " + retrieve.getString("inspect_lcode"));
    		log.debug("inspect_mcode: " + retrieve.getString("inspect_mcode"));
    	}
    	return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Edit retrieval result
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

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   Tinspect_goods
     * @return  int
     */
    public int checkTinspectGoods(Connection con, Tinspect_goods tinspectGoods) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;
    	int cnt = 0;
    	try {
    		pstmt = con.prepareStatement("SELECT COUNT(*) FROM TINSPECT_GOODS WHERE INSPECT_LCODE = ? AND INSPECT_MCODE = ? ");

    		int index = 1;
    		pstmt.setString(index++, tinspectGoods.getInspect_lcode());
    		pstmt.setString(index++, tinspectGoods.getInspect_mcode());

    		rset  = pstmt.executeQuery();

    		if (rset.next()) {
    			cnt = rset.getInt(1);
    		}

    	}catch(SQLException se){
    		log.error("[QaInspectSvc.checkTinspectGoods() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[QaInspectSvc.checkTinspectGoods() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return cnt;
    }

    /**
     * <PRE>
     * Desc : Retrieve SQL
     * </PRE>
     * @param   Connection
     * @param   Tinspect_goods
     * @return  int
     */
    public int checkTinspectGoodsGrp(Connection con, Tinspect_goods_grp tinspectGoodsGrp) throws StoreException{
    	PreparedStatement pstmt       = null;
    	ResultSet         rset        = null;
    	int cnt = 0;
    	try {
    		pstmt = con.prepareStatement("SELECT COUNT(*) FROM TINSPECT_GOODS_GRP WHERE INSPECT_LCODE = ? AND INSPECT_MCODE = ? AND LMSD_CODE = ? ");

    		int index = 1;
    		pstmt.setString(index++, tinspectGoodsGrp.getInspect_lcode());
    		pstmt.setString(index++, tinspectGoodsGrp.getInspect_mcode());
    		pstmt.setString(index++, tinspectGoodsGrp.getLmsd_code());

    		rset  = pstmt.executeQuery();

    		if (rset.next()) {
    			cnt = rset.getInt(1);
    		}

    	}catch(SQLException se){
    		log.error("[QaInspectSvc.checkTinspectGoodsGrp() SQLException : ERR-"+se.getErrorCode()+":"+se);
    		throw new StoreException(se.getMessage());
    	}catch(Exception e){
    		log.error("[QaInspectSvc.checkTinspectGoodsGrp() Exception : ERR-"+e.getMessage());
    		throw new StoreException(e.getMessage());
    	}finally {
    		DBUtils.freeConnection(null, pstmt, rset);
    	}
    	return cnt;
    }

    //= Insert -------------------------------------------------
    /**
    * <PRE>
    * Desc : Insert TINSPECT_GOODS
    * </PRE>
    * @param   Connection
    * @param   Tinspect_goods
    * @return  int
    */
	public int insert(Connection con, Tinspect_goods tinspectGoods) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlInsert(tinspectGoods));
            int index = 1;
            pstmt.setString(index++, tinspectGoods.getInspect_lcode   ());
            pstmt.setString(index++, tinspectGoods.getInspect_mcode	  ());
            pstmt.setString(index++, tinspectGoods.getInspect_mname	  ());
            pstmt.setString(index++, tinspectGoods.getUse_yn       	  ());
            pstmt.setString(index++, tinspectGoods.getInsert_id       ());
            pstmt.setString(index++, tinspectGoods.getModify_id       ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tinspectGoods.getInspect_lcode      ()	); logString.append( "/" );
            logString.append( tinspectGoods.getInspect_mcode	  ()	); logString.append( "/" );
            logString.append( tinspectGoods.getInspect_mname	  ()	); logString.append( "/" );
            logString.append( tinspectGoods.getUse_yn       	  ()	); logString.append( "/" );
            logString.append( tinspectGoods.getInsert_id          ()	); logString.append( "/" );
            logString.append( tinspectGoods.getModify_id          ()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[QaInspectSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[QaInspectSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TINSPECT_GOODS
    * </PRE>
    * @param   Connection
    * @param   Tinspect_goods
    * @return  int
    */
	public int update(Connection con, Tinspect_goods tinspectGoods) throws StoreException {
		PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(tinspectGoods));
            int index = 1;
            pstmt.setString(index++, tinspectGoods.getInspect_mname	  ());
            pstmt.setString(index++, tinspectGoods.getUse_yn          ());
            pstmt.setString(index++, tinspectGoods.getModify_id       ());
            pstmt.setString(index++, tinspectGoods.getInspect_lcode   ());
            pstmt.setString(index++, tinspectGoods.getInspect_mcode   ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tinspectGoods.getInspect_mname         ()	); logString.append( "/" );
            logString.append( tinspectGoods.getUse_yn                ()	); logString.append( "/" );
            logString.append( tinspectGoods.getModify_id             ()	); logString.append( "/" );
            logString.append( tinspectGoods.getInspect_lcode         ()	); logString.append( "/" );
            logString.append( tinspectGoods.getInspect_mcode         ()	); logString.append( "/" );
            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("[QaInspectSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("[QaInspectSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
	}

	//= Update -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Update TINSPECT_GOODS_GRP
	 * </PRE>
	 * @param   Connection
	 * @param   Tinspect_goods
	 * @return  int
	 */
	public int updateGrpAll(Connection con, Tinspect_goods tinspectGoods) throws StoreException {
		PreparedStatement pstmt       = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlUpdateGrpAll(tinspectGoods));
			int index = 1;
			pstmt.setString(index++, tinspectGoods.getUse_yn          ());
			pstmt.setString(index++, tinspectGoods.getModify_id       ());
			pstmt.setString(index++, tinspectGoods.getInspect_lcode   ());
			pstmt.setString(index++, tinspectGoods.getInspect_mcode   ());

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( tinspectGoods.getUse_yn                ()	); logString.append( "/" );
			logString.append( tinspectGoods.getModify_id             ()	); logString.append( "/" );
			logString.append( tinspectGoods.getInspect_lcode         ()	); logString.append( "/" );
			logString.append( tinspectGoods.getInspect_mcode         ()	); logString.append( "/" );
			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		}catch(SQLException se){
			logSave.error("[QaInspectSvc.updateGrpAll() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			logSave.error("[QaInspectSvc.updateGrpAll() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, null);
		}
		return executedRtn;
	}

	//= Insert -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Insert TINSPECT_GOODS_GRP
	 * </PRE>
	 * @param   Connection
	 * @param   Tinspect_goods_grp
	 * @return  int
	 */
	public int insertGrp(Connection con, Tinspect_goods_grp tinspectGoodsGrp) throws StoreException {
		PreparedStatement pstmt       = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlInsertGrp(tinspectGoodsGrp));
			int index = 1;
			pstmt.setString(index++, tinspectGoodsGrp.getLmsd_code       ());
			pstmt.setString(index++, tinspectGoodsGrp.getInspect_lcode   ());
			pstmt.setString(index++, tinspectGoodsGrp.getInspect_mcode	 ());
			pstmt.setString(index++, tinspectGoodsGrp.getUse_yn       	 ());
			pstmt.setString(index++, tinspectGoodsGrp.getInsert_id       ());
			pstmt.setString(index++, tinspectGoodsGrp.getModify_id       ());

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( tinspectGoodsGrp.getLmsd_code       ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getInspect_lcode   ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getInspect_mcode	  ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getUse_yn       	  ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getInsert_id       ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getModify_id       ()	); logString.append( "/" );
			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		}catch(SQLException se){
			logSave.error("[QaInspectSvc.insertGrp() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			logSave.error("[QaInspectSvc.insertGrp() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, null);
		}
		return executedRtn;
	}

	//= Update -------------------------------------------------
	/**
	 * <PRE>
	 * Desc : Update TINSPECT_GOODS
	 * </PRE>
	 * @param   Connection
	 * @param   Tinspect_goods
	 * @return  int
	 */
	public int updateGrp(Connection con, Tinspect_goods_grp tinspectGoodsGrp) throws StoreException {
		PreparedStatement pstmt       = null;
		int executedRtn = 0;

		try {
			pstmt = con.prepareStatement(makeSqlUpdateGrp(tinspectGoodsGrp));
			int index = 1;
			pstmt.setString(index++, tinspectGoodsGrp.getUse_yn          ());
			pstmt.setString(index++, tinspectGoodsGrp.getModify_id       ());
			pstmt.setString(index++, tinspectGoodsGrp.getLmsd_code       ());
			pstmt.setString(index++, tinspectGoodsGrp.getInspect_lcode   ());
			pstmt.setString(index++, tinspectGoodsGrp.getInspect_mcode   ());

			//= log Save Data ---------------------
			StringBuffer logString = new StringBuffer();
			logString.append( tinspectGoodsGrp.getUse_yn                ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getModify_id             ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getLmsd_code             ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getInspect_lcode         ()	); logString.append( "/" );
			logString.append( tinspectGoodsGrp.getInspect_mcode         ()	); logString.append( "/" );
			logSave.info(logString.toString());

			executedRtn = pstmt.executeUpdate();

		}catch(SQLException se){
			logSave.error("[QaInspectSvc.updateGrp() SQLException : ERR-"+se.getErrorCode()+":"+se);
			throw new StoreException(se.getMessage());
		}catch(Exception e){
			logSave.error("[QaInspectSvc.updateGrp() Exception : ERR-"+e.getMessage());
			throw new StoreException(e.getMessage());
		}finally {
			DBUtils.freeConnection(null, pstmt, null);
		}
		return executedRtn;
	}

    //= Edit SQL -------------------------------------------------
    /**
    * <PRE>
    * Desc : Make SQL ; TINSPECT_GOODS Insert
    * </PRE>
    * @param   Tinspect_goods
    * @return  String
    */
    private String makeSqlInsert( Tinspect_goods tinspectGoods ) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TINSPECT_GOODS (                                                                             \n");
        sb.append("    INSPECT_LCODE, INSPECT_MCODE, INSPECT_MNAME, USE_YN, INSERT_ID, INSERT_DATE, MODIFY_ID, MODIFY_DATE   \n");
        sb.append(" ) VALUES (                                                                                               \n");
        sb.append("     ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE                                                                   \n");
        sb.append(" )                                                                                                        \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TINSPECT_GOODS Update
     * </PRE>
     * @param   Tinspect_goods
     * @return  String
     */
    private String makeSqlUpdate( Tinspect_goods tinspectGoods ) throws StoreException{

    	StringBuffer sb = new StringBuffer();

    	sb.append(" UPDATE TINSPECT_GOODS                                                  \n");
    	sb.append("    SET INSPECT_MNAME = ?, USE_YN = ?, MODIFY_ID = ?, MODIFY_DATE = SYSDATE   \n");
    	sb.append("  WHERE INSPECT_LCODE = ? AND INSPECT_MCODE = ?                         \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TINSPECT_GOODS_GRP Insert
     * </PRE>
     * @param   Tinspect_goods_grp
     * @return  String
     */
    private String makeSqlInsertGrp( Tinspect_goods_grp tinspectGoodsGrp ) throws StoreException{

    	StringBuffer sb = new StringBuffer();

    	sb.append(" INSERT INTO TINSPECT_GOODS_GRP (                                                                     \n");
    	sb.append("    LMSD_CODE, INSPECT_LCODE, INSPECT_MCODE, USE_YN, INSERT_ID, INSERT_DATE, MODIFY_ID, MODIFY_DATE   \n");
    	sb.append(" ) VALUES (                                                                                           \n");
    	sb.append("     ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE                                                               \n");
    	sb.append(" )                                                                                                    \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TINSPECT_GOODS_GRP Update
     * </PRE>
     * @param   Tinspect_goods_grp
     * @return  String
     */
    private String makeSqlUpdateGrp( Tinspect_goods_grp tinspectGoodsGrp ) throws StoreException{

    	StringBuffer sb = new StringBuffer();

    	sb.append(" UPDATE TINSPECT_GOODS_GRP                                                   \n");
    	sb.append("    SET USE_YN = ?, MODIFY_ID = ?, MODIFY_DATE = SYSDATE      			\n");
    	sb.append("  WHERE LMSD_CODE = ? AND INSPECT_LCODE = ? AND INSPECT_MCODE = ?        \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }

    //= Edit SQL -------------------------------------------------
    /**
     * <PRE>
     * Desc : Make SQL ; TINSPECT_GOODS_GRP Update
     * </PRE>
     * @param   Tinspect_goods
     * @return  String
     */
    private String makeSqlUpdateGrpAll( Tinspect_goods tinspectGoods ) throws StoreException{

    	StringBuffer sb = new StringBuffer();

    	sb.append(" UPDATE TINSPECT_GOODS_GRP                                               \n");
    	sb.append("    SET USE_YN = ?, MODIFY_ID = ?, MODIFY_DATE = SYSDATE      			\n");
    	sb.append("  WHERE INSPECT_LCODE = ? AND INSPECT_MCODE = ?        \n");

    	//= log SQL -------------------------------------------------
    	if (log.isDebugEnabled()) {
    		log.debug(sb.toString());
    	}
    	return sb.toString();
    }
}
