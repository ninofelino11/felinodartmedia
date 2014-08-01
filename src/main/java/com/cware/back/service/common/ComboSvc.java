
package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.StoreException;
import com.cware.back.entity.common.BaseCombo;

/**
 * Combo Box  Service Class
 *
 * @version 1.0, 2006/06/15
 */
public class ComboSvc {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    public ComboSvc() {}

    /**
    * <PRE>
    * Desc : Tcode 의 code_lgroup 에 따른 정보를 얻는다
    *        USE_YN = '1'
    * </PRE>
    * @return  Query
    */
    public String makeSqlTcode() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_MGROUP,     \n ");
        sb.append("        CODE_NAME,       \n ");
        sb.append("        CODE_SNAME,      \n ");
        sb.append("        CODE_GROUP,      \n ");
        sb.append("        REMARK,          \n ");
        sb.append("        REMARK1,         \n ");
        sb.append("        REMARK2          \n ");
        sb.append("   FROM TCODE            \n ");
        sb.append("  WHERE CODE_LGROUP = ?  \n ");
        sb.append("    AND USE_YN = '1'     \n ");
        sb.append("  ORDER BY CODE_MGROUP   \n ");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Tcode 의 code_lgroup 에 따른 정보를 얻는다
    *        USE_YN = ALL
    * </PRE>
    * @return  Query
    */
    public String makeSqlTcodeAll() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_MGROUP,     \n ");
        sb.append("        CODE_NAME,       \n ");
        sb.append("        CODE_SNAME,      \n ");
        sb.append("        CODE_GROUP,      \n ");
        sb.append("        REMARK,          \n ");
        sb.append("        REMARK1,         \n ");
        sb.append("        REMARK2          \n ");
        sb.append("   FROM TCODE            \n ");
        sb.append("  WHERE CODE_LGROUP = ?  \n ");
        sb.append("  ORDER BY CODE_MGROUP   \n ");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : Tcode 의 code_lgroup 에 따른 정보를 얻는다
    *        Modify SQL
    * </PRE>
    * @return  Query
    */
    public String makeSqlTcodeMody() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_MGROUP,     \n ");
        sb.append("        CODE_NAME,       \n ");
        sb.append("        CODE_SNAME,      \n ");
        sb.append("        CODE_GROUP,      \n ");
        sb.append("        REMARK,          \n ");
        sb.append("        REMARK1,         \n ");
        sb.append("        REMARK2          \n ");
        sb.append("   FROM TCODE            \n ");
        sb.append("  WHERE CODE_LGROUP = ?  \n ");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 창고 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTwh() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT WH_CODE,  \n");
        sb.append("        WH_NAME  \n");
        sb.append("   FROM TWAREHOUSE  \n");
        return sb.toString();
    }


    /**
    * <PRE>
    * Desc : 창고 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTwhentp() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT WH_CODE,  \n");
        sb.append("        WH_NAME  \n");
        sb.append("   FROM TWAREHOUSE  \n");
        sb.append("  WHERE WH_REAL_YN     = '0'  \n");
        sb.append("    AND WH_KIND_FLAG   = '01'  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 창고 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTwhgoods() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT WH_CODE,  \n");
        sb.append("        WH_NAME  \n");
        sb.append("   FROM TWAREHOUSE  \n");
        sb.append("  WHERE WH_KIND_FLAG   = '01'  \n");
        sb.append("    AND WH_CODE <> '901'  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 창고 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTwhloc() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT WH_CODE,  \n");
        sb.append("        WH_NAME  \n");
        sb.append("   FROM TWAREHOUSE  \n");
        sb.append("  WHERE WH_REAL_YN     = '1'  \n");
        sb.append("    AND WH_KIND_FLAG   = '01'  \n");
        sb.append("  ORDER BY WH_CODE  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 창고 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTwhsamp() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT WH_CODE,  \n");
        sb.append("        WH_NAME  \n");
        sb.append("   FROM TWAREHOUSE  \n");
        sb.append("  WHERE WH_KIND_FLAG   = '02'  \n");
//        sb.append("    AND WH_CODE = '990'  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 은행 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTbank() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT BANK_CODE,  \n");
        sb.append("        BANK_NAME  \n");
        sb.append("   FROM TBANK  \n");
        sb.append("  WHERE USE_YN = '1'  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 카드 코드 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTcardcode() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CARD_CODE,  \n");
        sb.append("        CARD_NAME  \n");
        sb.append("   FROM TCARDCODE  \n");
        sb.append("  WHERE USE_YN = '1'  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 업체담당자 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTentpuser() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ENTP_MAN_SEQ,  \n");
        sb.append("        ENTP_MAN_NAME  \n");
        sb.append("   FROM TENTPUSER  \n");
        sb.append("  WHERE ENTP_CODE = ?  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 대분류 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTgoodskinds() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT  \n");
        sb.append("        LGROUP,  \n");
        sb.append("        LGROUP_NAME  \n");
        sb.append("   FROM TGOODSKINDS  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : MD 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTmd() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT MD_CODE,  \n");
        sb.append("        MD_NAME  \n");
        sb.append("   FROM TMD  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 사용자 리스트를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTuser() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT USER_ID,  \n");
        sb.append("        USER_NAME  \n");
        sb.append("   FROM TUSER  \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 사용자 리스트를 얻는다.
     * </PRE>
     * @return  Query
     */
     public String makeSqlTuserShowhost() throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT USER_ID,  \n");
         sb.append("        USER_NAME  \n");
         sb.append("   FROM TUSER  \n");
         sb.append("  WHERE USER_GB = '06'  \n");
         sb.append("  ORDER BY USER_ID ASC \n");
         return sb.toString();
     }

    /**
    * <PRE>
    * Desc : 매체코드를 얻는다.
    * </PRE>
    * @return  Query
    */
    public String makeSqlTmedia() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.MEDIA_CODE,  \n");
        sb.append(" 	   A.MEDIA_NAME \n");
        sb.append("   FROM TMEDIA A  \n");
        sb.append("  WHERE A.USE_YN = '1'  \n");
        sb.append(" ORDER BY A.MEDIA_CODE ASC  \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 모델코드를 얻는다.
     * </PRE>
     * @return  Query
     */
     public String makeSqlTmodel() throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT A.MODEL_NO,  \n");
         sb.append(" 	    A.MODEL_NAME \n");
         sb.append("   FROM TMODELMASTER A  \n");
         sb.append("  WHERE A.USE_YN = '1'  \n");
         sb.append("    AND A.MODEL_FLAG = '10'  \n");
         sb.append(" ORDER BY A.MODEL_NO ASC  \n");
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 게스트코드를 얻는다.
      * </PRE>
      * @return  Query
      */
      public String makeSqlTmodelGuest() throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append(" SELECT A.MODEL_NO,  \n");
          sb.append(" 	     A.MODEL_NAME \n");
          sb.append("   FROM TMODELMASTER A  \n");
          sb.append("  WHERE A.USE_YN = '1'  \n");
          sb.append("    AND A.MODEL_FLAG = '20'  \n");
          sb.append(" ORDER BY A.MODEL_NO ASC  \n");
          return sb.toString();
      }
    /**
     * <PRE>
     * Desc : 매체코드를 얻는다.
     * </PRE>
     * @return  Query
     */
     public String makeSqlTmediaChannel() throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT A.MEDIA_CODE,  \n");
         sb.append(" 	   A.MEDIA_NAME \n");
         sb.append("   FROM TMEDIA A  \n");
         sb.append("  WHERE A.USE_YN = '1'  \n");
         sb.append("    AND A.MEDIA_GB = '01'  \n");
         sb.append(" ORDER BY A.MEDIA_CODE ASC  \n");
         return sb.toString();
     }
    /**
     * <PRE>
     * Desc : DM의 발송순번을 얻는다.
     * </PRE>
     * @return  Query
     */
     public String makeSqlTsendSeq() throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT A.SEND_SEQ       \n");
         sb.append("   FROM TDMSENDM A       \n");
         sb.append("  WHERE  A.DM_YEAR = ?   \n");
         sb.append("    AND  A.DM_CODE = ?   \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 대분류메뉴를 얻는다.
      * </PRE>
      * @return  Query
      */
      public String makeSqlTlgroup() throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append("  SELECT TSECMENU.LMENU_ID,   \n");
          sb.append("         TSECMENU.LMENU_NAME  \n");
          sb.append("    FROM TSECMENU             \n");
          sb.append("GROUP BY TSECMENU.LMENU_ID,   \n");
          sb.append("         TSECMENU.LMENU_NAME  \n");
          sb.append("ORDER BY TSECMENU.LMENU_ID,   \n");
          sb.append("         TSECMENU.LMENU_NAME  \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 중분류메뉴를 얻는다.
       * </PRE>
       * @return  Query
       */
       public String makeSqlTmgroup() throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append("  SELECT DISTINCT TSECMENU.MMENU_ID,   \n");
           sb.append("         TSECMENU.MMENU_NAME, \n");
           sb.append("         TSECMENU.LMENU_ID    \n");
           sb.append("    FROM TSECMENU             \n");

           return sb.toString();
       }

       /**
        * <PRE>
        * Desc : 소분류메뉴를 얻는다.
        * </PRE>
        * @return  Query
        */
        public String makeSqlTsgroup() throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("  SELECT DISTINCT TSECMENU.SMENU_ID,   \n");
            sb.append("         TSECMENU.SMENU_NAME, \n");
            sb.append("         TSECMENU.LMENU_ID,   \n");
            sb.append("         TSECMENU.MMENU_ID    \n");
            sb.append("    FROM TSECMENU             \n");

            return sb.toString();
        }

     /**
      * <PRE>
      * Desc : MENU의 LGROUP을 얻는다.
      * </PRE>
      * @return  Query
      */
      public String makeSqlLmenu() throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append(" SELECT A.LMENU_ID,   \n"             );
          sb.append(       " A.LMENU_NAME  \n"             );
          sb.append(  " FROM TSECMENU  A   \n"             );
          sb.append( " GROUP BY A.LMENU_ID, A.LMENU_NAME \n");
          sb.append( " ORDER BY A.LMENU_ID, A.LMENU_NAME \n");

          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : TCODE의 스킨타입을 얻는다.
       * </PRE>
       * @return  Query
       */
       public String makeSqlTSkinType() throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append( " SELECT A.CODE_MGROUP,           \n");
           sb.append(        " A.CODE_NAME              \n");
           sb.append(   " FROM TCODE A                  \n");
           sb.append(  " WHERE A.CODE_LGROUP = 'C078'   \n");
           sb.append(   "  AND A.CODE_GROUP = '1'       \n");
           sb.append("ORDER BY A.CODE_MGROUP ASC        \n");

           return sb.toString();
       }

       /**
        * <PRE>
        * Desc : 단품정보의 색상 리스트를 얻는다.
        * </PRE>
        * @return  Query
        */
        public String makeSqlTcspfColor() throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT CSPF_GROUP,        \n");
            sb.append("        CSPF_DESC          \n");
            sb.append("   FROM TGOODSINFOM        \n");
            sb.append("  WHERE CSPF_FLAG = 'C'    \n");
            sb.append("    AND USE_YN = '1'       \n");
            sb.append(" ORDER BY CSPF_GROUP		  \n");
            return sb.toString();
        }

        /**
         * <PRE>
         * Desc : 단품정보의 무늬 리스트를 얻는다.
         * </PRE>
         * @return  Query
         */
         public String makeSqlTcspfPattern() throws StoreException{
        	 StringBuffer sb = new StringBuffer();
             sb.append(" SELECT CSPF_GROUP,        \n");
             sb.append("        CSPF_DESC          \n");
             sb.append("   FROM TGOODSINFOM        \n");
             sb.append("  WHERE CSPF_FLAG = 'P'    \n");
             sb.append("    AND USE_YN = '1'       \n");
             sb.append(" ORDER BY CSPF_GROUP		  \n");
             return sb.toString();
         }

         public String makeSqlSettleInfo() throws StoreException{
        	 StringBuffer sb = new StringBuffer();
        	    sb.append(" SELECT SETTLE_FLAG, \n");
        	    sb.append("        SETTLE_NAME, \n");
        	    sb.append("        REFUND_FLAG, \n");
        	    sb.append("        REFUND_NAME, \n");
        	    sb.append("        PREPAY_YN, \n");
        	    sb.append("        SETTLE_REPAY \n");
        	    sb.append("     FROM VSETTLEINFO \n ");
             return sb.toString();
         }

         public String makeSqlQuickGroup() throws StoreException{
        	 StringBuffer sb = new StringBuffer();
             sb.append(" SELECT T.GROUP_CODE   \n");
             sb.append("      , T.GROUP_NAME   \n");
             sb.append("      , 1   		   \n");
             sb.append("   FROM TFAVORITESM T  \n");
             sb.append("  WHERE T.USER_ID = ?  \n");
             sb.append("  UNION ALL            \n");
             sb.append(" SELECT 'ROOT'         \n");
             sb.append("      , 'DEFAULT'      \n");
             sb.append("      , 0              \n");
             sb.append("   FROM DUAL           \n");
             sb.append(" ORDER BY 3, 1         \n");
             return sb.toString();
         }

         public String makeSqlTareaZone() throws StoreException{
        	 StringBuffer sb = new StringBuffer();
             sb.append("SELECT AREA_GB ,			\n");
             sb.append("       AREA_NAME		 	\n");
             sb.append("  FROM TAREA_ZONE 		\n");
             sb.append(" WHERE 1 = 1   			\n");
        	 return sb.toString();
         }

    /**
    * <PRE>
    * Desc : 조회 Method
    * </PRE>
    * @param   poolName    : DB 의 서비스명
    * @param   flag        : 콤보박스의 조회구분( int 형으로 1부터증가처리)
    * @param   paramValues : 조회구분 에 따라 조회조건을 처리하기 위해서 배열로 저장
    * @return  Query
    */
    public BaseCombo[] retrieve ( Connection con, int flag, String[] paramValues) throws StoreException{
        PreparedStatement pstmt       = null;
        ResultSet         rset        = null;
        Collection collection = new ArrayList();
        BaseCombo   entity  = null;
        BaseCombo[] entitys = null;

        String query = "";

        try {
            switch (flag)
                { case Construct.C_TCODE  : {
                    query = makeSqlTcode();
                    break;
                } case Construct.C_TCODE_ALL  : {
                    query = makeSqlTcodeAll();
                    break;
                } case Construct.C_TCODE_MODI  : {
                    query = makeSqlTcodeMody();
                    query = query + paramValues[1] + "  ORDER BY CODE_MGROUP   \n ";
                    String tempString = "";
                    tempString = paramValues[0];
                    paramValues = new String[1];
                    paramValues[0] = tempString;
                    break;
                } case Construct.C_WH_CODE : {
                    query = makeSqlTwh();
                    if ( paramValues != null){
                        query += paramValues[0] ;
                        paramValues = null;
                    }
                    query += "  ORDER BY WH_CODE   \n ";
                    break;
                } case Construct.C_WH_CODE_ENTP : {
                    query = makeSqlTwhentp();
                    break;
                } case Construct.C_WH_CODE_GOODS : {
                    query = makeSqlTwhgoods();
                    break;
                } case Construct.C_WH_CODE_LOC : {
                    query = makeSqlTwhloc();
                    break;
                } case Construct.C_WH_CODE_SAMP : {
                    query = makeSqlTwhsamp();
                    break;
                } case Construct.C_BANK_CODE : {
                    query = makeSqlTbank();
                    break;
                } case Construct.C_CARD_CODE : {
                    query = makeSqlTcardcode();
                    break;
                } case Construct.C_ENTP_USER : {
                    query = makeSqlTentpuser();
                    break;
                } case Construct.C_GOODS_KINDS : {
                    query = makeSqlTgoodskinds();
                    break;
                } case Construct.C_MD_CODE : {
                    query = makeSqlTmd();
                    break;
                } case Construct.C_USER_CODE : {
                    query = makeSqlTuser();
                    break;
                } case Construct.C_TMEDIA : {
                    query = makeSqlTmedia();
                    break;
                } case Construct.C_MEDIACHANNEL : {
                	query = makeSqlTmediaChannel();
                    break;
                } case Construct.C_SEND_SEQ : {
                    query = makeSqlTsendSeq();
                    break;
                } case Construct.C_LGROUP : {
                    query = makeSqlTlgroup();
                    break;
                } case Construct.C_LMENU : {
                    query = makeSqlLmenu();
                    break;
                } case Construct.C_MGROUP : {
                    query = makeSqlTmgroup();
                    break;
                } case Construct.C_SGROUP : {
                    query = makeSqlTsgroup();
                    break;
                } case Construct.C_SKIN_TYPE : {
                    query = makeSqlTSkinType();
                    break;
                } case Construct.C_SCPF_FLAG_C : {
                    query = makeSqlTcspfColor();
                    break;
                } case Construct.C_SCPF_FLAG_P : {
                    query = makeSqlTcspfPattern();
                    break;
                } case Construct.C_SETTLEINFO : {
                    query = makeSqlSettleInfo();
                    query = query + paramValues[0] + "  ORDER BY SETTLE_FLAG   \n ";
                    paramValues = null;
                    break;
                } case Construct.C_QUICK_GRUOP : {
                	query = makeSqlQuickGroup();
                	break;
                } case Construct.C_SHOWHOST_CODE : {
                	query = makeSqlTuserShowhost();
                	break;
                } case Construct.C_MODEL_CODE : {
                	query = makeSqlTmodel();
                	break;
                } case Construct.C_GUEST_CODE : {
                	query = makeSqlTmodelGuest();
                	break;
                } case Construct.C_AREA_GB : {
                	query = makeSqlTareaZone();
                	if (paramValues != null) {
                		if (paramValues.length > 0) query = query + paramValues[0];
                		paramValues = null;
                	}
                	query = query + " ORDER BY AREA_GB ASC ";
                	break;
                }
            }
            //=logging sql
            if (log.isDebugEnabled()) {
                log.debug("\n" + query);
            }

            pstmt = con.prepareStatement(query);
            if(paramValues != null){
                for(int i = 1 ; i <= paramValues.length ; i++){
                    pstmt.setString(i,paramValues[i-1]);
                }
            }
            rset  = pstmt.executeQuery();
            while (rset!=null && rset.next()){
                entity = new BaseCombo();
                entity.setCode(ComUtils.NVL(rset.getString(1),""));
                entity.setCodeName(ComUtils.NVL(rset.getString(2),""));

                switch (flag)
                	{ case Construct.C_TCODE  : {
	                	entity.setCodeSName(ComUtils.NVL(rset.getString(3),""));
	                	entity.setCodeGroup(ComUtils.NVL(rset.getString(4),""));
	                	entity.setRemark(ComUtils.NVL(rset.getString(5),""));
	                	entity.setRemark1(ComUtils.NVL(rset.getString(6),""));
	                	entity.setRemark2(ComUtils.NVL(rset.getString(7),""));
	                    break;
	                } case Construct.C_TCODE_ALL  : {
	                	entity.setCodeSName(ComUtils.NVL(rset.getString(3),""));
	                	entity.setCodeGroup(ComUtils.NVL(rset.getString(4),""));
	                	entity.setRemark(ComUtils.NVL(rset.getString(5),""));
	                	entity.setRemark1(ComUtils.NVL(rset.getString(6),""));
	                	entity.setRemark2(ComUtils.NVL(rset.getString(7),""));
	                    break;
	                } case Construct.C_TCODE_MODI  : {
	                	entity.setCodeSName(ComUtils.NVL(rset.getString(3),""));
	                	entity.setCodeGroup(ComUtils.NVL(rset.getString(4),""));
	                	entity.setRemark(ComUtils.NVL(rset.getString(5),""));
	                	entity.setRemark1(ComUtils.NVL(rset.getString(6),""));
	                	entity.setRemark2(ComUtils.NVL(rset.getString(7),""));
	                    break;
	                } case Construct.C_SETTLEINFO  : {
	                	entity.setCodeGroup(ComUtils.NVL(rset.getString(3),""));
	                	entity.setRemark(ComUtils.NVL(rset.getString(4),""));
	                	entity.setRemark1(ComUtils.NVL(rset.getString(5),""));
	                	entity.setRemark2(ComUtils.NVL(rset.getString(6),""));
	                    break;
	                }
                }

                collection.add(entity);
/*
                if (log.isDebugEnabled()) {
                    log.debug("rset.getString(1) : " + rset.getString(1));
                    log.debug("rset.getString(2) : " + rset.getString(2));
                }
*/
            }
            entitys = (BaseCombo[]) collection.toArray(new BaseCombo[0]);

        }catch(SQLException se){
            log.error("[ComboSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se + " / flag : " + flag );
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[ComboSvc.retrieve() Exception : ERR-"+e.getMessage() + " / flag : " + flag );
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(null, pstmt, rset);
        }
        return entitys;
    }

}