package com.cware.back.service.common;

import java.io.Reader;
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
import com.cware.back.entity.table.Tboardtm;

public class BoardTmSvc {
	
    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    public BoardTmSvc() {}   

    private HashMap[] makeSheet(ResultSet rset)  throws Exception {
        Collection   collection = new ArrayList();
        HashMap      hmSheet    = null;
        long         retRow     = 0;

        while (rset!=null && rset.next()){
            hmSheet = new HashMap();
            hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
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

    public RetrieveModel retrieveList(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        int               index = 1;           

        try {

            retrieve.put("c_page"    , 0);
            retrieve.put("start"     , 0);            
            retrieve.put("total_cnt" , 0);
            retrieve.put("t_page"    , 0);  
            if(retrieve.getString("str_c_page").equals("")) retrieve.put("str_c_page","1");
        	
            pstmt = con.prepareStatement(makeSqlSumCount(retrieve));
            rset  = pstmt.executeQuery();
            
            if(rset != null && rset.next()){
            	retrieve.put("total_cnt",rset.getInt(1));
            }
            rset.close();
            pstmt.close();            
            
            retrieve.put("c_page" , retrieve.getInt("str_c_page"));                        
            retrieve.put("start"  , retrieve.getInt("total_cnt") - (retrieve.getInt("str_c_page") - 1) * retrieve.getInt("list_num"));
        	
            if( ( retrieve.getInt("total_cnt") % retrieve.getInt("list_num") ) == 0 ) retrieve.put("t_page",retrieve.getInt("total_cnt") / retrieve.getInt("list_num"));
        	else retrieve.put("t_page",( retrieve.getInt("total_cnt") / retrieve.getInt("list_num") ) + 1);                                
            
            if(retrieve.getInt("total_cnt") > 0){            	
                pstmt = con.prepareStatement(makeSqlSelectList(retrieve));
                pstmt.setInt(index++, retrieve.getInt("start"));
                pstmt.setInt(index++, retrieve.getInt("list_num"));
                rset  = pstmt.executeQuery(); 
            	retrieve.put("RESULT",makeSheet(rset));
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }    
    
    public int insert(Connection con, Tboardtm tboardtm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;

        try {
        	
            pstmt = con.prepareStatement(makeSqlInsert());
            
            int index = 1;
            pstmt.setString(index++, tboardtm.getAid      ());
            pstmt.setString(index++, tboardtm.getRid      ());
            pstmt.setString(index++, tboardtm.getThread   ());
            pstmt.setString(index++, tboardtm.getSubject  ());
            pstmt.setString(index++, tboardtm.getUser_id  ());
            pstmt.setString(index++, tboardtm.getContents ());
            pstmt.setString(index++, tboardtm.getFilename ());

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tboardtm.getAid      ()); logString.append( "/" );
            logString.append( tboardtm.getRid      ()); logString.append( "/" );
            logString.append( tboardtm.getThread   ()); logString.append( "/" );
            logString.append( tboardtm.getSubject  ()); logString.append( "/" );
            logString.append( tboardtm.getUser_id  ()); logString.append( "/" );
            logString.append( tboardtm.getContents ()); logString.append( "/" );
            logString.append( tboardtm.getFilename ()); logString.append( "/" );
            
            log.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[BoardTmSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	log.error("[BoardTmSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    public RetrieveModel retrieveView(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        Tboardtm          tboardtm = null;
        int               index = 1;  
        Collection        collection = new ArrayList();
        StringBuffer        sBuf     = null;
        Reader              in       = null;  
        int                 r_code   = 0;

        try {

            pstmt = con.prepareStatement(makeSqlSelectOneRow(retrieve));
            pstmt.setString(1, retrieve.getString("str_aid"));
            rset  = pstmt.executeQuery();
            if(rset != null && rset.next()){     
            	tboardtm = new Tboardtm();            	            	
                in = rset.getCharacterStream("CONTENTS");
                r_code = 0;
                sBuf = new StringBuffer();
                while ((r_code = in.read()) != -1){
                    sBuf.append((char)r_code);
                }
                tboardtm.setContents(sBuf.toString()); 
            	tboardtm.setAid(rset.getString("AID"));
            	tboardtm.setThread(rset.getString("THREAD"));
            	tboardtm.setRid(rset.getString("RID"));
            	tboardtm.setSubject(rset.getString("SUBJECT"));
            	tboardtm.setUser_id(rset.getString("USER_ID"));
            	tboardtm.setInsert_date(rset.getString("INSERT_DATE"));
            	tboardtm.setHits(rset.getString("HITS"));
            	tboardtm.setFilename(rset.getString("FILENAME"));
            }            
            rset.close();
            pstmt.close();
            retrieve.put("RESULT_ONEROW", tboardtm);
            
            if(!tboardtm.getAid().equals("") && tboardtm.getThread().equals("A")){
	            pstmt = con.prepareStatement(makeSqlSelectSubList(retrieve));
	            pstmt.setString(1, tboardtm.getRid());
	            pstmt.setString(2, tboardtm.getAid());
	            rset  = pstmt.executeQuery();
	            while(rset != null && rset.next()){     
	            	tboardtm = new Tboardtm();       
	                in = rset.getCharacterStream("CONTENTS");
	                r_code = 0;
	                sBuf = new StringBuffer();
	                while ((r_code = in.read()) != -1){
	                    sBuf.append((char)r_code);
	                }
	                tboardtm.setContents(sBuf.toString()); 	            	
	            	tboardtm.setAid(rset.getString("AID"));
	            	tboardtm.setThread(rset.getString("THREAD"));
	            	tboardtm.setRid(rset.getString("RID"));
	            	tboardtm.setSubject(rset.getString("SUBJECT"));
	            	tboardtm.setUser_id(rset.getString("USER_ID"));
	            	tboardtm.setHits(rset.getString("HITS"));
	            	tboardtm.setFilename(rset.getString("FILENAME"));
	            	collection.add(tboardtm);
	            }            
	            rset.close();
	            pstmt.close(); 
	            retrieve.put("RESULT", (Tboardtm[])collection.toArray(new Tboardtm[0]));

	            pstmt = con.prepareStatement(makeSqlUpdateHit(retrieve)); 
	            pstmt.setString(1, retrieve.getString("str_aid"));
	            pstmt.executeUpdate(); 
            }

        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }    

    public int update(Connection con, Tboardtm tboardtm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;
        int index = 1;

        try {
        	
            pstmt = con.prepareStatement(makeSqlUpdate());
                        
            pstmt.setString(index++, tboardtm.getUser_id  ());
            pstmt.setString(index++, tboardtm.getSubject  ());            
            pstmt.setString(index++, tboardtm.getContents ());
            pstmt.setString(index++, tboardtm.getFilename ());
            pstmt.setString(index++, tboardtm.getFilename ());            
            pstmt.setString(index++, tboardtm.getAid      ());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tboardtm.getUser_id  ()); logString.append( "/" );
            logString.append( tboardtm.getSubject  ()); logString.append( "/" );            
            logString.append( tboardtm.getContents ()); logString.append( "/" );
            logString.append( tboardtm.getFilename ()); logString.append( "/" );
            logString.append( tboardtm.getAid      ()); logString.append( "/" );            
            
            log.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[BoardTmSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	log.error("[BoardTmSvc.insert() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }

    public int delete(Connection con, Tboardtm tboardtm) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        int executedRtn = 0;
        int index = 1;

        try {
        	
            pstmt = con.prepareStatement(makeSqlDelete());
                                   
            pstmt.setString(index++, tboardtm.getAid      ());            

            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();
            logString.append( tboardtm.getAid      ()); logString.append( "/" );            
            
            log.info("\n" + logString.toString());
                              
            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            log.error("[BoardTmSvc.delete() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
        	log.error("[BoardTmSvc.delete() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return executedRtn;
    }    
    
    public RetrieveModel getOriginalData(Connection con, RetrieveModel retrieve) throws StoreException{
        PreparedStatement pstmt = null;
        ResultSet         rset  = null;
        int               index = 1;  

        try {
            pstmt = con.prepareStatement(makeSqlSelectOriginalData(retrieve));
            pstmt.setString(index++, retrieve.getString("str_aid"));
            rset  = pstmt.executeQuery();
            if(rset != null && rset.next()){     
            	retrieve.put("RID", rset.getString("RID")); 
            	retrieve.put("THREAD", rset.getString("THREAD"));   
            	retrieve.put("FILENAME", rset.getString("FILENAME"));              	
            	retrieve.put("MAX_THREAD", rset.getString("MAX_THREAD"));              	
            }
        }catch(SQLException se){
            log.error("SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    } 
    
    
    private String makeSqlSumCount(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT COUNT(*) FROM TBOARDTM WHERE THREAD = 'A' \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }
    
    private String makeSqlSelectList(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT XA.AID, \n");
        sb.append("        XA.USER_ID, \n");
        sb.append("        XA.SUBJECT, \n");
        sb.append("        TO_CHAR( XA.INSERT_DATE, 'YYYY/MM/DD HH24:MI' ) AS INSERT_DATE, \n");
        sb.append("        XA.HITS, \n");
        sb.append("        XA.CONTENTS, \n");
        sb.append("        XA.THREAD, \n");
        sb.append("        XA.RID, \n");
        sb.append("        ( SELECT COUNT(*) \n");
        sb.append("            FROM TBOARDTM \n");
        sb.append("           WHERE RID= XA.AID \n");
        sb.append("             AND AID != XA.AID ) AS REPLY_CNT \n");
        sb.append("   FROM ( SELECT * \n");
        sb.append("            FROM ( SELECT * \n");
        sb.append("                     FROM TBOARDTM \n");
        sb.append("                    WHERE THREAD='A' \n");
        sb.append("                    ORDER BY RID ASC , THREAD DESC ) \n");
        sb.append("           WHERE ROWNUM <= ? \n");
        sb.append("           ORDER BY RID DESC , THREAD ASC ) XA \n");
        sb.append("   WHERE ROWNUM <= ? \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }
    
    private String makeSqlSelectOneRow(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT AID, \n");
        sb.append("        THREAD, \n");
        sb.append("        RID, \n");
        sb.append("        SUBJECT, \n");
        sb.append("        USER_ID, \n");
        sb.append("        INSERT_DATE, \n");
        sb.append("        HITS, \n");
        sb.append("        CONTENTS, \n");
        sb.append("        FILENAME \n");
        sb.append("   FROM TBOARDTM \n");
        sb.append("  WHERE AID = ? \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }
    
    private String makeSqlInsert() throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TBOARDTM ( \n");
        sb.append("        AID, \n");
        sb.append("        RID, \n");
        sb.append("        THREAD, \n");
        sb.append("        SUBJECT, \n");
        sb.append("        USER_ID, \n");
        sb.append("        INSERT_DATE, \n");
        sb.append("        HITS, \n");
        sb.append("        CONTENTS, \n");
        sb.append("        FILENAME ) \n");
        sb.append(" VALUES( ?, \n");
        sb.append("         ?, \n");
        sb.append("         ?, \n");
        sb.append("         ?, \n");
        sb.append("         ?, \n");
        sb.append("         SYSDATE, \n");
        sb.append("         0, \n");
        sb.append("         ?, \n");
        sb.append("         ? ) \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }

    private String makeSqlSelectSubList(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT AID, \n");
        sb.append("        RID, \n");
        sb.append("        USER_ID, \n");
        sb.append("        SUBJECT, \n");
        sb.append("        CONTENTS, \n");
        sb.append("        HITS, \n");
        sb.append("        THREAD, \n");
        sb.append("        FILENAME \n");
        sb.append("   FROM TBOARDTM \n");
        sb.append("  WHERE RID = ? \n");
        sb.append("    AND AID <> ? \n");
        sb.append("    AND THREAD <> 'A' \n");        
        sb.append("  ORDER BY THREAD ASC \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }

    private String makeSqlSelectOriginalData(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.RID, \n");
        sb.append("        A.THREAD, \n");
        sb.append("        A.FILENAME, \n");        
        sb.append("        (SELECT MAX(THREAD) \n");
        sb.append("           FROM TBOARDTM \n");
        sb.append("          WHERE RID = A.RID \n");
        sb.append("            AND THREAD LIKE A.THREAD||'%' \n");
        sb.append("            AND LENGTH(THREAD) = LENGTH(A.THREAD)+1) AS MAX_THREAD \n");
        sb.append("   FROM TBOARDTM A \n");
        sb.append("  WHERE A.AID = ?  \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }
    
    private String makeSqlUpdate() throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE TBOARDTM \n");
        sb.append("    SET USER_ID=?, \n");
        sb.append("        SUBJECT=?, \n");
        sb.append("        CONTENTS=?, \n");
        sb.append("        FILENAME= DECODE(?,'',FILENAME,?) \n");
        sb.append("  WHERE AID = ? \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }

    private String makeSqlUpdateHit(RetrieveModel retrieve) throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE TBOARDTM SET HITS = HITS+1 WHERE AID = ? \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }

    private String makeSqlDelete() throws Exception {    	
        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM TBOARDTM WHERE AID = ? \n");

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }     
        return sb.toString();
    }

    
}
