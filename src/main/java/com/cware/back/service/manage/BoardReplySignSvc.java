
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

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tboard;


/**
 * Register notice Service class
 *
 * @version 1.0, 2006/07/14
 */
public class BoardReplySignSvc {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);
    private static Log logSave  = LogFactory.getLog(Construct.LOG_SAVE);

    public BoardReplySignSvc() {}

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

        sb.append(" SELECT	A.BOARD_SEQ,                                            \n");
        sb.append(" 			A.BOARD_TITLE,                                            \n");
        sb.append(" 			A.BOARD_CONTENT,                                          \n");
        sb.append(" 			A.SEARCH_CNT,                                             \n");
        sb.append(" 			A.RECO_CNT,                                               \n");
        sb.append(" 			A.BOARD_REF,                                              \n");
        sb.append(" 			A.BOARD_STEP,                                             \n");
        sb.append(" 			A.BOARD_LEVEL,                                            \n");
        sb.append(" 			A.DELETE_YN,                                              \n");
        sb.append("  			A.DELETE_YN 	AS	 P_DELETE_YN,        				  \n");
        sb.append(" 			A.INSERT_DATE,                                            \n");
        sb.append(" 			A.INSERT_ID,                                              \n");
        sb.append(" 			A.MODIFY_DATE,                                            \n");
        sb.append(" 			A.EMAIL_ADDR,                                             \n");
        sb.append(" 			A.EMAIL_RET_YN,                                           \n");
        sb.append(" 			A.BOARD_LKINDS,                                           \n");
        sb.append(" 			( SELECT CODE_NAME FROM TCODE WHERE CODE_LGROUP = 'F012' AND CODE_MGROUP = A.BOARD_LKINDS ) AS BOARD_LKINDS_NAME, \n");
        sb.append(" 			A.BOARD_MKINDS,                                                                                                  \n");
        sb.append(" 			( SELECT C.CODE_NAME FROM TCODE B, TCODE C                                                                       \n");
        sb.append("             WHERE B.REMARK = C.CODE_LGROUP AND B.CODE_LGROUP = 'F012' AND B.CODE_MGROUP = A.BOARD_LKINDS               \n");
        sb.append("               AND C.CODE_MGROUP = A.BOARD_MKINDS ) AS BOARD_MKINDS_NAME,                                               \n");
        sb.append(" 			A.IP_ADDR,                                                                                                       \n");
        sb.append(" 			A.CUST_NO,                                                                                                       \n");
        sb.append(" 			B.CUST_NAME,                                                                                                     \n");
        sb.append(" 			A.SIGN_YN,                                                                                                       \n");
        sb.append(" 			A.SIGN_YN   	AS	 P_SIGN_YN,                                                                                   \n");
        sb.append(" 			A.SIGN_DATE,                                                                                                     \n");
        sb.append(" 			A.SIGN_ID,                                                                                                       \n");
        sb.append(" 			A.REPLY_YN                                                                                                       \n");
        sb.append("   FROM	TBOARD A,                                                                                                      \n");
        sb.append(" 			TCUSTOMER B                                                                                                      \n");
        sb.append("  WHERE	A.BOARD_REF IN (	SELECT	B.BOARD_REF                                                                          \n");
        sb.append(" 									  FROM	TBOARD B                                                                                     \n");
        sb.append(" 									 WHERE	B.SIGN_YN = '0'                                                                              \n");
        sb.append(" 										AND	B.DELETE_YN = '0'                                                                              \n");
        sb.append(" 								)                                                                                                      \n");
        sb.append(" 	AND	A.CUST_NO = B.CUST_NO (+)                                                                                        \n");
        sb.append("   AND   A.INSERT_DATE BETWEEN TO_DATE(?, 'YYYY/MM/DD') AND TO_DATE( ?, 'YYYY/MM/DD')+1           \n");
        sb.append(" ORDER BY A.BOARD_REF DESC , A.BOARD_STEP                                                                               \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());

            log.debug("fromDate   : " + retrieve.getString("fromDate"));
            log.debug("toDate     : " + retrieve.getString("toDate"));
        }

        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : Make SQL ( Tboard 변경 )
    * </PRE>
    * @param   Tboard
    * @return  String
    */
    public String makeSqlUpdate(Tboard board) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("  UPDATE TBOARD SET \n ");
        sb.append("         BOARD_TITLE         = ?, \n ");
        sb.append("         BOARD_CONTENT       = ?, \n ");
        sb.append("         DELETE_YN           = ?, \n ");
        sb.append("         MODIFY_DATE         = SYSDATE, \n ");
        sb.append("         SIGN_YN         	= ? \n ");
        sb.append("   WHERE BOARD_SEQ = ?     \n ");

        //= log SQL -------------------------------------------------
        if (logSave.isDebugEnabled()) {
            logSave.debug("\n" + sb.toString());
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( Tboard 변경 )
     * </PRE>
     * @param   Tboard
     * @return  String
     */
     public String makeSqlUpdateSign(Tboard board) throws StoreException{

         StringBuffer sb = new StringBuffer();

         sb.append("  UPDATE TBOARD SET \n ");
         sb.append("         BOARD_TITLE         = ?, \n ");
         sb.append("         BOARD_CONTENT       = ?, \n ");
         sb.append("         DELETE_YN           = ?, \n ");
         sb.append("         MODIFY_DATE         = SYSDATE, \n ");
         sb.append("         SIGN_DATE          = SYSDATE, \n ");
         sb.append("         SIGN_YN         	= ?, \n ");
         sb.append("         SIGN_ID         	= ? \n ");
         sb.append("   WHERE BOARD_SEQ = ?     \n ");

         //= log SQL -------------------------------------------------
         if (logSave.isDebugEnabled()) {
             logSave.debug("\n" + sb.toString());
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
            log.debug("NoticeboardManageSvc Retrieve Row : " + retRow);
        }
        return (HashMap[])collection.toArray(new HashMap[0]);
    }


    //= Retrieve -------------------------------------------------
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

            pstmt.setString(index++,retrieve.getString("fromDate"));
            pstmt.setString(index++,retrieve.getString("toDate"));

            rset  = pstmt.executeQuery();

            retrieve.put("RESULT",makeSheet(rset));

        }catch(SQLException se){
            log.error("NoticeboardManageSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("NoticeboardManageSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return retrieve;
    }



    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TBOARD
    * </PRE>
    * @param   Connection
    * @param   Tboard
    * @return  int
    */
    public int update(Connection con, Tboard board) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdate(board));
            int index = 1;

            pstmt.setString(index++,board.getBoard_title());
            pstmt.setString(index++,board.getBoard_content());
            pstmt.setString(index++,board.getDelete_yn());
            pstmt.setString(index++,board.getSign_yn());
            pstmt.setLong  (index++,board.getBoard_seq());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( board.getBoard_title());        logString.append( "/" );
            logString.append( board.getBoard_content());        logString.append( "/" );
            logString.append( board.getDelete_yn());        logString.append( "/" );
            logString.append( board.getSign_yn());        logString.append( "/" );
            logString.append( board.getBoard_seq());        logString.append( "/" );


            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("NoticeboardManageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("NoticeboardManageSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }

    //= Update -------------------------------------------------
    /**
    * <PRE>
    * Desc : Update TBOARD
    * </PRE>
    * @param   Connection
    * @param   Tboard
    * @return  int
    */
    public int updateSign(Connection con, Tboard board) throws StoreException{
        PreparedStatement pstmt       = null;
        int executedRtn = 0;

        try {
            pstmt = con.prepareStatement(makeSqlUpdateSign(board));
            int index = 1;

            pstmt.setString(index++,board.getBoard_title());
            pstmt.setString(index++,board.getBoard_content());
            pstmt.setString(index++,board.getDelete_yn());
            pstmt.setString(index++,board.getSign_yn());
            pstmt.setString(index++,board.getSign_id());
            pstmt.setLong  (index++,board.getBoard_seq());
            //= log Save Data ---------------------
            StringBuffer logString = new StringBuffer();

            logString.append( board.getBoard_title());        logString.append( "/" );
            logString.append( board.getBoard_content());        logString.append( "/" );
            logString.append( board.getDelete_yn());        logString.append( "/" );
            logString.append( board.getSign_yn());        logString.append( "/" );
            logString.append( board.getBoard_seq());        logString.append( "/" );


            logSave.info(logString.toString());

            executedRtn = pstmt.executeUpdate();

        }catch(SQLException se){
            logSave.error("NoticeboardManageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            logSave.error("NoticeboardManageSvc.update() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, null);
        }
        return executedRtn;
    }
      //= Get Reply_Yn -------------------------------------------------
      /**
      * <PRE>
      * Desc : Get BOARD_SEQ No. from TBOARD
      * </PRE>
      * @param   Connection
      * @param   Tboard
      * @return  int
      */

      public String getReplyYn(Connection con, Tboard board) throws StoreException{
          PreparedStatement pstmt       = null;
          ResultSet         rset        = null;
          String            reply_yn = "";
          try {
        	  pstmt = con.prepareStatement(makeSqlgetReplyYn(board));

              int index = 1;
              long board_level = board.getBoard_level()-1;
              pstmt.setLong(index++,board.getBoard_ref());
              pstmt.setLong(index++,board_level);
              pstmt.setLong(index++,board.getBoard_seq());

              rset  = pstmt.executeQuery();

              if (rset!=null && rset.next()){
            	  reply_yn = rset.getString("REPLY_YN");
            	  board.setP_board_step(ComUtils.objToLong(rset.getString("BOARD_STEP")));
            	  board.setP_board_seq(ComUtils.objToLong(rset.getString("BOARD_SEQ")));
              }else reply_yn = "";

          }catch(SQLException se){
              logSave.error("[EntpInputSvc.getCode() SQLException : ERR-"+se.getErrorCode()+":"+se);
              throw new StoreException(se.getMessage());
          }catch(Exception e){
              logSave.error("[EntpInputSvc.getCode() Exception : ERR-"+e.getMessage());
              throw new StoreException(e.getMessage());
          }finally {
              DBUtils.freeConnection(null, pstmt, rset);
          }
          return reply_yn;
      }

      /**
       * <PRE>
       * Desc : Make SQL ( BOARD_SEQ No. Select )
       * </PRE>
       * @param   Tboard
       * @return  String
       */
       public String makeSqlgetReplyYn(Tboard board) throws StoreException{

           StringBuffer sb = new StringBuffer();

           sb.append("   SELECT A.BOARD_SEQ, \n");
           sb.append("			A.BOARD_STEP, \n");
           sb.append("			A.REPLY_YN \n");
           sb.append("    FROM  TBOARD A \n ");
           sb.append("   WHERE  A.BOARD_REF = ? \n ");
           sb.append("     AND  A.BOARD_LEVEL = ? \n ");
           sb.append("     AND  A.BOARD_STEP = (	SELECT Max(B.BOARD_STEP) \n ");
           sb.append("     							  FROM TBOARD B \n");
           sb.append("     							 WHERE B.BOARD_REF = A.BOARD_REF \n");
           sb.append("     							   AND B.BOARD_LEVEL = A.BOARD_LEVEL \n");
           sb.append("     							   AND B.BOARD_STEP < ? )");

           //= log SQL -------------------------------------------------
           if (logSave.isDebugEnabled()) {
               logSave.debug(sb.toString());
           }

           return sb.toString();
       }

       //= Check Dup -------------------------------------------------
       /**
       * <PRE>
       * Desc : Check Duplication of Tdescribecode
       * </PRE>
       * @param   Connection
       * @param   Tboard
       * @return  int
       */
       public int checkDup(Connection con, Tboard board) throws StoreException{
           PreparedStatement pstmt       = null;
           ResultSet         rset        = null;
           int executedRtn = 0;

           try {
               pstmt = con.prepareStatement(makeSqlDupCheck(board));
               int index = 1;
               pstmt.setLong(index++,  board.getBoard_ref());
               pstmt.setLong(index++,  board.getBoard_level());
               pstmt.setLong(index++,  board.getP_board_step());
               pstmt.setLong(index++,  board.getBoard_seq());
               rset  = pstmt.executeQuery();
               executedRtn = DBUtils.executeQueryI(rset, "");

           }catch(SQLException se){
               logSave.error("[WhCodeSvc.insert() SQLException : ERR-"+se.getErrorCode()+":"+se);
               throw new StoreException(se.getMessage());
           }catch(Exception e){
               logSave.error("[WhCodeSvc.insert() Exception : ERR-"+e.getMessage());
               throw new StoreException(e.getMessage());
           }finally {
               DBUtils.freeConnection(null, pstmt, rset);
           }
           return executedRtn;
       }

        public String makeSqlDupCheck(Tboard board) throws StoreException{

            StringBuffer sb = new StringBuffer();

            sb.append("  SELECT COUNT(*) AS CNT 			\n");
            sb.append("    FROM TBOARD A  					\n");
            sb.append("   WHERE A.BOARD_REF = ?  			\n");
            sb.append("     AND A.BOARD_LEVEL = ?  			\n");
            sb.append("     AND A.BOARD_STEP > ?  			\n");
            sb.append("     AND A.BOARD_SEQ <> ?  			\n");
            sb.append("     AND A.DELETE_YN = '0'  			\n");
            sb.append("     AND A.SIGN_YN = '1' 			\n");

            //= log SQL -------------------------------------------------
            if (logSave.isDebugEnabled()) {
                logSave.debug(sb.toString());
            }

            return sb.toString();
        }

        //= Update Flag -------------------------------------------------
        /**
        * <PRE>
        * Desc : Update TBOARD
        * </PRE>
        * @param   Connection
        * @param   Tboard
        * @return  int
        */
        public int updateReplyYn(Connection con, Tboard board) throws StoreException{
            PreparedStatement pstmt       = null;
            int executedRtn = 0;

            try {
                pstmt = con.prepareStatement(makeSqlUpdateReplyYn(board));
                int index = 1;

                pstmt.setString(index++,board.getP_reply_yn());
                pstmt.setLong(index++,board.getP_board_seq());

                //= log Save Data ---------------------
                StringBuffer logString = new StringBuffer();

                logString.append( board.getBoard_ref());        logString.append( "/" );
                logSave.info(logString.toString());

                executedRtn = pstmt.executeUpdate();

            }catch(SQLException se){
                logSave.error("NoticeboardManageSvc.update() SQLException : ERR-"+se.getErrorCode()+":"+se);
                throw new StoreException(se.getMessage());
            }catch(Exception e){
                logSave.error("NoticeboardManageSvc.update() Exception : ERR-"+e.getMessage());
                throw new StoreException(e.getMessage());
            }finally {
                DBUtils.freeConnection(null, pstmt, null);
            }
            return executedRtn;
        }

        /**
        * <PRE>
        * Desc : Make SQL ( Tboard Reply_Yn 변경 )
        * </PRE>
        * @param   Tboard
        * @return  String
        */
        public String makeSqlUpdateReplyYn(Tboard board) throws StoreException{

            StringBuffer sb = new StringBuffer();

            sb.append("  UPDATE TBOARD SET \n ");
            sb.append("         REPLY_YN   = ? ,  \n ");
            sb.append("         MODIFY_DATE   = SYSDATE 	  \n ");
            sb.append("   WHERE BOARD_SEQ = ?     \n ");

            //= log SQL -------------------------------------------------
            if (logSave.isDebugEnabled()) {
                logSave.debug("\n" + sb.toString());
            }

            return sb.toString();
        }

}
