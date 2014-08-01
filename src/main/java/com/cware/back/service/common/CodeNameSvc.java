package com.cware.back.service.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.RetrieveModel;
import com.cware.back.common.StoreException;
import com.cware.back.entity.common.MultipurposeEntity;

public class CodeNameSvc {

    private static Log log = LogFactory.getLog(Construct.LOG_BASE);

    //사용자명 조회
    public Collection getName(Connection con, String arg_case, String arg_code, String arg_where) throws Exception {
        Collection          collection  = new ArrayList();
        PreparedStatement   pstmt       = null;
        ResultSet           rset        = null;
        MultipurposeEntity  entity      = null;
        long                retRow      = 0;
        String              sSql        = "";

        if (arg_case.equals("USER_ID")) sSql = makeSqlUserName();
        else if (arg_case.equals("BRAND_CODE")) sSql = makeSqlBrandName();
        else if (arg_case.equals("BANK_CODE")) sSql = makeSqlBankName();
        else if (arg_case.equals("CARD_CODE")) sSql = makeSqlCardName();
        else if (arg_case.equals("GOODS_CODE") ||
                 arg_case.equals("LINK_GOODS_CODE")) sSql = makeSqlGoodsName();
        else if (arg_case.equals("GOODSDT_CODE")) sSql = makeSqlGoodsDtName();
        else if (arg_case.equals("LGROUP")) sSql = makeSqlLgroupName();
        else if (arg_case.equals("MGROUP")) sSql = makeSqlMgroupName();
        else if (arg_case.equals("SGROUP")) sSql = makeSqlSgroupName();
        else if (arg_case.equals("DGROUP")) sSql = makeSqlDgroupName();
        else if (arg_case.equals("MD_CODE")) sSql = makeSqlMdName();
        else if (arg_case.equals("FORM_CODE")) sSql = makeSqlFormName();
        else if (arg_case.equals("RECIEPTSBANK")) sSql = makeSqlReceiptBankName();
        else if (arg_case.equals("FAQ_KINDS")) sSql = makeSqlFaqKindsName();
        else if (arg_case.equals("FAQ_CODE")) sSql = makeSqlFaqCodeName();
        else if (arg_case.equals("MMENU_ID")) sSql = makeSqlMmenuName();
        else if (arg_case.equals("WH_CODE")) sSql = makeSqlWhName();
        else if (arg_case.equals("LOTTERY_PROMO_NO")) sSql = makeSqlLotteryPromoName();
        else if (arg_case.equals("PROMO_NO")) sSql = makeSqlPromoName();
        else if (arg_case.equals("DESCRIBE_CODE")) sSql = makeSqlDescribeName();
        else if (arg_case.equals("MAKECO_CODE")) sSql = makeSqlMakeCompName();
        else if (arg_case.equals("MEDIA_CODE")) sSql = makeSqlMakeMediaName();
        else if (arg_case.equals("ENTP_CODE")) sSql = makeSqlMakeEntpName();
        else if (arg_case.equals("CUST_NO")) sSql = makeSqlMakeCustName();
        else if (arg_case.equals("BOARD_CODE")) sSql = makeSqlMakeBoardName();
        else if (arg_case.equals("COUNSEL_MGROUP_CODE")) sSql = makeSqlMakeCounselMgroupCodeName();
        else if (arg_case.equals("CODE_NAME")) sSql = makeSqlCodeName(); //makeSqlGroupName();//'CODE_NAME' 를 그룹명에서 코드명으로 변경(사용하는곳없음) BY KHH 2011.01.20
        else if (arg_case.equals("ORIGIN_CODE")) sSql = makeSqlOriginCode();
        else if (arg_case.equals("COLOR_CODE")) sSql = makeSqlColorName();
        else if (arg_case.equals("SIZE_CODE")) sSql = makeSqlSizeName();
        else if (arg_case.equals("PATTERN_CODE")) sSql = makeSqlPatternName();
        else if (arg_case.equals("MASTER_CODE")) sSql = makeSqlMasterName();
        else if (arg_case.equals("RACK_CODE")) sSql = makeSqlRackCode();
        else if (arg_case.equals("RACK_CODE_ETC")) sSql = makeSqlRackCodeEtc();
        else if (arg_case.equals("DEF_OUT_RACK")) sSql = makeSqlDefaultOutRackCode();
        else if (arg_case.equals("CTRL_CODE")) sSql = makeSqlGoodsControlName();
        else if (arg_case.equals("GIFT_GOODS_CODE")) sSql = makeSqlGiftGoodsName();
        else if (arg_case.equals("CATEGORY_CODE")) sSql = makeSqlCategoryName();
        else if (arg_case.equals("SEGMENT_CODE")) sSql = makeSqlSegmentName();
        else if (arg_case.equals("SETTLE_GB")) sSql = makeSqlSettleName();
        else if (arg_case.equals("PROG_CODE")) sSql = makeSqlProgName();
        else if (arg_case.equals("EVENT_NO")) sSql = makeSqlEventTitle();
        else if (arg_case.equals("BOARD_MKINDS")) sSql = makeSqlBoardMkinds();
        else if (arg_case.equals("TAPE_CODE")) sSql = makeSqlTapeName();
        else if (arg_case.equals("SO_CODE")) sSql = makeSqlSoName();
        else if (arg_case.equals("POST_GB")) sSql = makeSqlStateName();
        else if (arg_case.equals("SIZE_GROUP")) sSql = makeSqlGroupSize();
        else if (arg_case.equals("FORM_GROUP")) sSql = makeSqlGroupForm();
        else if (arg_case.equals("COLOR_GROUP")) sSql = makeSqlGroupColor();
        else if (arg_case.equals("PATTERN_GROUP")) sSql = makeSqlGroupPattern();
        else if (arg_case.equals("USER_GROUP_ID")) sSql = makeSqlUserGroupName();
        else if (arg_case.equals("T_CODE")) sSql = makeSqlTCodeName();
        else if (arg_case.equals("STOCK_GOODSNAME")) sSql = makeSqlStockGoodsName();
        else if (arg_case.equals("CODE_FORM")) sSql = makeSqlFormCode();
        else if (arg_case.equals("CODE_SIZE")) sSql = makeSqlSizeCode();
        else if (arg_case.equals("CODE_COLOR")) sSql = makeSqlColorCode();
        else if (arg_case.equals("CODE_PATTERN")) sSql = makeSqlPatternCode();
        else if (arg_case.equals("TEMPLATE_CODE")) sSql = makeSqlTemplateCode();
        else if (arg_case.equals("PLANCLASS_CODE")) sSql = makeSqlPlanclassCode();
        else if (arg_case.equals("PLAN_CODE")) sSql = makeSqlPlanCode();
        else if (arg_case.equals("HO_DE_CODE")) sSql = makeSqlHoDeCode();
        else if (arg_case.equals("MODEL_NO")) sSql = makeSqlModelCodeName();
        else if (arg_case.equals("AREA_GB")) sSql = makeSqlAreaName();

        else if (arg_case.equals("PROGRAM_ID")){
            sSql = makeSqlProgramName();
        }

        sSql += arg_where;
        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sSql);
            log.debug("arg_code  : " + arg_code);
        }
        pstmt = con.prepareStatement(sSql);
        pstmt.setString(1, arg_code);
        rset = pstmt.executeQuery();

        while (rset!=null && rset.next()){
            entity = new MultipurposeEntity();
            entity.setString1(rset.getString(1));

            collection.add(entity);
            retRow++;
        }
        DBUtils.freeConnection(null, pstmt, rset);
        return collection;
    }

    public RetrieveModel getMultiName ( Connection con, int flag, RetrieveModel retrieve) throws StoreException{
        Statement stmt = null;
        ResultSet rset = null;
        Collection collection = new ArrayList();
        HashMap    hmSheet = null;

        String query = "";

        try {
            switch (flag)
                { case Construct.P_GOODS_CODE_PRICE  : {
                        query = makeSqlGoodsCodePrice(retrieve);
                        break;
                    }
                    case Construct.C_LMENU  : {
                        query = makeSqlLmenu();
                        break;
                    }
                    case Construct.T_CUST_NO    : {
                        query = makeSqlGetCustSystem(retrieve);
                        break;
                    }
                    case Construct.T_CUST_NAME  : {
                        query = makeSqlCustName(retrieve);
                        break;
                    }
                    case Construct.T_COUNSEL_LGROUP_CODE    : {
                        query = makeSqlMakeCounselLgroupCode(retrieve);
                        break;
                    }
                    case Construct.T_CODE   : {
                        query = makeSqlMakeTCode(retrieve);
                        break;
                    }
                    case Construct.P_CHECK_CODE :{
                        query = makeSqlCheckCode(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_BALJU : {
                        query = makeSqlGoodsCodeBalju(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_ENTPTAKEOUT : {
                        query = makeSqlGoodsCodeEntpTakeout(retrieve);
                        break;
                    }
                    case Construct.P_DGROUP : {
                        query = makeSqlDgroup(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_DS1 : {
                        query = makeSqlGoodsCodeDS1(retrieve);
                        break;
                    }
                    case Construct.P_GOODSDT_CODE : {
                        query = makeSqlGoodsDtCode(retrieve);
                        break;
                    }
                    case Construct.P_RACK_CODE_GOODS_GRADE : {
                        query = makeSqlRackCodeGrade(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_SEARCH : {
                        query = makeSqlGoodsSearch(retrieve);
                        break;
                    }
                    case Construct.P_OB_SEQ : {
                        query = makeSqlObSeq(retrieve);
                        break;
                    }
                    case Construct.P_LOTTERY_PROMO_NO : {
                        query = makeSqlLotteryPromoNo(retrieve);
                        break;
                    }
                    case Construct.P_SEG_CODE : {
                        query = makeSqlSegmentCode(retrieve);
                        break;
                    }
                    case Construct.P_ORDER_GOODS_CODE : {
                        query = makeSqlOrderGoodsName(retrieve);
                        break;
                    }
                    case Construct.T_COUNSEL_LGROUP_CODE_FR    : {
                        query = makeSqlMakeCounselLgroupCodeFr(retrieve);
                        break;
                    }
                    case Construct.T_COUNSEL_LGROUP_CODE_TO    : {
                        query = makeSqlMakeCounselLgroupCodeTo(retrieve);
                        break;
                    }
                    case Construct.P_MEDIA_CODE    : {
                        query = makeSqlMakeMedia(retrieve);
                        break;
                    }
                    case Construct.P_USER_ID    : {
                        query = makeSqlMakeUser(retrieve);
                        break;
                    }
                    case Construct.P_BOARD_CODE    : {
                        query = makeSqlBoardLkindsCode(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_MD    : {
                        query = makeSqlGoodsCodeMd(retrieve);
                        break;
                    }
                    case Construct.T_ETIME    : {
                        query = makeSqlEtime(retrieve);
                        break;
                    }
                    case Construct.T_PROGRAM_NAME    : {
                        query = makeSqlProgramCodeName(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_EOUT    : {
                        query = makeSqlEoutQty(retrieve);
                        break;
                    }
                    case Construct.T_GET_GOODS_PRICE    : {
                        query = makeSqlGetGoodsPrice(retrieve);
                        break;
                    }
                    case Construct.P_GOODS_CODE_DS : {
                        query = makeSqlGoodsCodeDS(retrieve);
                        break;
                    }
                    case Construct.T_PLANCLASS_CODE  : {
                        query = makeSqlPlanclass(retrieve);
                        break;
                    }
                    case Construct.P_ENTP_MAN_SEQ  : {
                        query = makeSqlEntpManSeq(retrieve);
                        break;
                    }
                    case Construct.T_STOCK_QTY    : {
                        query = makeSqlStockQty(retrieve);
                        break;
                    }
                    case Construct.P_TAPE_CODE    : {
                    	query = makeSqlTapeCode(retrieve);
                    	break;
                    }
                    case Construct.P_BD_PROGRAM_SELECT : {
                    	query = makeSqlBdProgramSelect(retrieve);
                    	break;
                    }
                    case Construct.P_MODEL_CODE_NAME : {
                    	query = makeSqlModelCodeName(retrieve);
                    	break;
                    }case Construct.P_GIFT_NAME_PRICE : {
                    	query = makeSqlGiftNamePrice(retrieve);
                    	break;
                    }
            }

            if (log.isDebugEnabled()) log.debug("\n" + query);

            stmt = con.createStatement();
            rset  = stmt.executeQuery(query);

            while (rset!=null && rset.next()){
                hmSheet = new HashMap();
                hmSheet = DBUtils.executeQueryHashMap(rset, hmSheet);
                collection.add(hmSheet);
            }
            retrieve.put("RESULT_MULTI",(HashMap[]) collection.toArray(new HashMap[0]));

        }catch(SQLException se){
            log.error("[PopupSvc.retrieve() SQLException : ERR-"+se.getErrorCode()+":"+se);
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            log.error("[PopupSvc.retrieve() Exception : ERR-"+e.getMessage());
            throw new StoreException(e.getMessage());
        }finally {
            DBUtils.freeConnection(stmt, null, rset);
        }
        return retrieve;
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 사용자명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlUserName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT USER_NAME FROM TUSER WHERE USER_ID = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 방송프로그램명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlProgName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT PROG_NAME FROM TPROGRAM WHERE PROG_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 이벤트 타이틀 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlEventTitle() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT EVENT_TITLE FROM TEVENTM WHERE EVENT_NO = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 브랜드명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlBrandName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT BRAND_NAME FROM TBRAND WHERE BRAND_CODE = ? \n ");

        return sb.toString();
    }

    /**
     *
     * <PRE>
     * Desc : Make SQL ( 은행명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlBankName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT BANK_NAME FROM TBANK WHERE USE_YN = '1' AND BANK_CODE = ? \n ");

        return sb.toString();
    }

    /**
     *
     * <PRE>
     * Desc : Make SQL ( 카드명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlCardName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT CARD_NAME FROM TCARDCODE WHERE CARD_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 상품명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlGoodsName() {

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT GOODS_NAME, SALE_GB FROM TGOODS WHERE GOODS_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 단품명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlGoodsDtName() {

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT GOODSDT_INFO FROM TGOODSDT WHERE GOODSDT_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 대분류명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlLgroupName() {

         StringBuffer sb = new StringBuffer();

         sb.append("SELECT LGROUP_NAME FROM TGOODSKINDS WHERE LGROUP = ? \n ");

         return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 중분류명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlMgroupName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT MGROUP_NAME FROM TGOODSKINDS WHERE MGROUP = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 소분류명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlSgroupName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SGROUP_NAME FROM TGOODSKINDS WHERE SGROUP = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 세분류명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlDgroupName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DGROUP_NAME FROM TGOODSKINDS WHERE DGROUP = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( MD명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlMdName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT MD_NAME FROM TMD WHERE MD_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 형태명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlFormName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT FORM_NAME FROM TFORM WHERE FORM_CODE = ? AND FORM_CODE < '999' \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 결제은행명 조회 )
     * </PRE>
     * @return  String
    */
    public String makeSqlReceiptBankName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT BANK_NAME  \n");
        sb.append("    FROM TRECEIPTSBANK A   \n");
        sb.append("   WHERE USE_YN = '1'   \n");
        sb.append("     AND BANK_CODE = ? \n ");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 상품코드(가격)
    * </PRE>
    * @return  Query
    */
    public String makeSqlGoodsCodePrice(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT /*+RULE*/  \n");
        sb.append("       A.GOODS_CODE,  \n");
        sb.append("       A.GOODS_NAME,  \n");
        sb.append("       A.ENTP_CODE, \n");
        sb.append("       C.ENTP_NAME,  \n");
        sb.append("       A.DELY_TYPE, \n");
        sb.append("       B.BUY_COST,  \n");
        sb.append("       B.BUY_VAT,  \n");
        sb.append("       B.SALE_PRICE,  \n");
        sb.append("       B.CUST_PRICE, \n");
        sb.append("       A.MAKECO_CODE, \n");
        sb.append("       D.MAKECO_NAME,  \n");
        sb.append("       E.BRAND_NAME, \n");
        sb.append("       A.SET_YN,  \n");
        sb.append("       A.TAX_YN  \n");
        sb.append("  FROM TGOODS A, \n");
        sb.append("       TGOODSPRICE B, \n");
        sb.append("       TENTERPRISE C, \n");
        sb.append("       TMAKECOMP D, \n");
        sb.append("       TBRAND E \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
        sb.append("   AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE)   \n");
        sb.append("                          FROM TGOODSPRICE C   \n");
        sb.append("                         WHERE A.GOODS_CODE = C.GOODS_CODE   \n");
        sb.append("                           AND C.APPLY_DATE <= SYSDATE )   \n");
        sb.append("   AND A.ENTP_CODE   = C.ENTP_CODE  \n");
        sb.append("   AND A.MAKECO_CODE = D.MAKECO_CODE \n");
        sb.append("   AND A.BRAND_CODE  = E.BRAND_CODE \n");
        sb.append("   AND A.GOODS_CODE = '"+retrieve.getString("GOODS_CODE")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( FAQ구분 조회 )
     * </PRE>
     * @return  String
    */
    public String makeSqlFaqKindsName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT REMARK1 AS FAQ_KINDS    \n");
        sb.append("    FROM TCODE                   \n");
        sb.append("   WHERE CODE_LGROUP='B830'      \n");
        sb.append("     AND CODE_GROUP='01'         \n");
        sb.append("     AND CODE_MGROUP = ?         \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( FAQ코드 조회 )
     * </PRE>
     * @return  String
    */
    public String makeSqlFaqCodeName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT XB.CODE_NAME  AS FAQ_CODE                               \n");
        sb.append("     FROM (SELECT CODE_LGROUP , CODE_MGROUP , REMARK1 , REMARK    \n");
        sb.append("             FROM TCODE                                           \n");
        sb.append("            WHERE CODE_LGROUP='B830'                              \n");
        sb.append("              AND USE_YN='1'                                      \n");
        sb.append("              AND CODE_GROUP='01' ) XA , TCODE XB                 \n");
        sb.append("     WHERE XA.CODE_LGROUP = 'B830'                                \n");
        sb.append("       AND XB.CODE_LGROUP = XA.REMARK                             \n");
        sb.append("       AND XB.CODE_MGROUP = ?                                     \n");
        sb.append("     ORDER BY XB.CODE_LGROUP ASC                                  \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( MMENU_NAME 조회 )
     * </PRE>
     * @return  Query
     */
    public String makeSqlMmenuName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.MMENU_NAME   \n");
        sb.append("   FROM TSECMENU  A    \n");
        sb.append("  WHERE A.MMENU_ID = ? \n");

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
     * Desc : 고객 정보를 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlGetCustSystem(RetrieveModel retrieve) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("  SELECT A.CUST_NO,                                                                                          \n ");
        sb.append("         A.CUST_NAME,                                                                                        \n ");
        sb.append("         A.CUST_NAME1,                                                                                       \n ");
        sb.append("         A.CUST_NAME2,                                                                                       \n ");
        sb.append("         A.CUST_NAME3,                                                                                       \n ");
        sb.append("         CWARE_ENC_DEC(A.RESIDENT_NO, 'd') AS RESIDENT_NO,                                                   \n ");
        sb.append("         B.CUST_GB,                                                                                          \n ");
        sb.append("         A.SEX,                                                                                              \n ");
        sb.append("         A.BIRTHDAY_YN,                                                                                      \n ");
        sb.append("         A.BIRTHDAY,                                                                                         \n ");
        sb.append("         A.WEDDING_YN,                                                                                       \n ");
        sb.append("         TO_CHAR(A.WEDDING_DATE,'YYYYMMDD') AS WEDDING_DATE,                                                 \n ");
        sb.append("         B.CUST_WARNING,                                                                                     \n ");
        sb.append("         B.CUST_CHAR,                                                                                        \n ");
        sb.append("         B.MEMB_GB,                                                                                          \n ");
        sb.append("         '1' AS WHICH_TB,                                                                                    \n ");
        sb.append("         A.RECEIVE_METHOD,                                                                                   \n ");
        sb.append("         A.MEM_ID,                                                                                           \n ");
        sb.append("         A.PASSWD,                                                                                           \n ");
        sb.append("         A.MEMB_NO,                                                                                          \n ");
        sb.append("         A.SMS_YN,                                                                                           \n ");
        sb.append("         A.WITHDRAWAL_YN,                                                                                    \n ");
        sb.append("         A.EMAIL_FLAG,                                                                                      \n ");
        sb.append("         A.EMAIL_ADDR,                                                                                      \n ");
        sb.append("         A.EMAIL_YN,                                                                                        \n ");
        sb.append("         A.ORDER_EMAIL_YN,                                                                                  \n ");
        sb.append("         NVL((SELECT SA.VAL3                                                                                 \n ");
        sb.append("                FROM TMEMBGB SA                                                                              \n ");
        sb.append("              WHERE SA.MEMB_GB = B.MEMB_GB                                                                  \n ");
        sb.append("                  AND SA.TYPE    = '21'                                                                      \n ");
        sb.append("                    AND ROWNUM     = 1),'1') AS SHIPCOST_YN ,                                               \n ");
        sb.append("         (SELECT NVL(SUM(DECODE(SA.TYPE,'10',1,0)) -                                                         \n ");
        sb.append("                 SUM(DECODE(SA.TYPE,'20',1,0)),0) AS SHIPFREE_RESULT                                         \n ");
        sb.append("            FROM TORDERSHIPCOST SA,                                                                          \n ");
        sb.append("                 TORDERM SB                                                                                  \n ");
        sb.append("           WHERE SA.ORDER_NO = SB.ORDER_NO                                                                   \n ");
        sb.append("             AND SB.CUST_NO = A.CUST_NO                                                                      \n ");
        sb.append("             AND SB.ORDER_DATE >= TRUNC( SYSDATE , 'MONTH')                                                 \n ");
        sb.append("             AND SB.ORDER_DATE < LAST_DAY(TRUNC( SYSDATE )) + 1                                             \n ");
        sb.append("             AND SA.SHPFEE_CODE = '60' ) AS SHIPFREE_RESULT,                                                \n ");

        sb.append("         TO_CHAR(B.FIRST_ORDER_DATE,'YYYYMMDD') AS FIRST_ORDER_DATE ,                                       \n ");
        sb.append("         TO_CHAR(B.LAST_ORDER_DATE,'YYYYMMDD') AS LAST_ORDER_DATE ,                                         \n ");
        sb.append("         (SELECT CONTENT FROM TCUSTSPINFO WHERE CUST_NO = A.CUST_NO AND TYPE = '70') JOIN_MOTIVE,           \n ");

        sb.append("    		C.RECEIVER_DDD,                                                                                    \n ");
        sb.append("    		C.RECEIVER_TEL1,                                                                                   \n ");
        sb.append("    		C.RECEIVER_TEL2,                                                                                   \n ");
        sb.append("    		C.RECEIVER_TEL3,                                                                                   \n ");
        sb.append("    		C.TEL          		                                                                               \n ");

        sb.append("    FROM TCUSTOMER   A,                                                                                     \n ");
        sb.append("         TCUSTSYSTEM B,                                                                                     \n ");
        sb.append("         TRECEIVER C                	                                                                       \n ");
        sb.append("   WHERE A.CUST_NO  = '"+retrieve.getString("CUST_NO")+"'                                               \n ");
        sb.append("     AND A.CUST_NO  = B.CUST_NO                                                                             \n ");
        sb.append("     AND B.CUST_NO  = C.CUST_NO                                                                              \n ");
        sb.append("     AND C.USE_YN  = '1'                                                                      				\n ");
        sb.append("     AND C.DEFAULT_YN = '1'                                                                             	    \n ");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ; get cust_name, cust_warning, tel
     * </PRE>
     * @param
     * @return  String
     */
    public String makeSqlCustName(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.CUST_NAME,  \n");
        sb.append("        C.MEMB_GB,    \n");
        sb.append("        NVL(B.TEL, '') AS TEL,  \n");
        sb.append("        NVL(B.RECEIVER_DDD, '') AS DDD,  \n");
        sb.append("        NVL(B.RECEIVER_TEL1, '') AS TEL1,  \n");
        sb.append("        NVL(B.RECEIVER_TEL2, '') AS TEL2,  \n");
        sb.append("        NVL(B.RECEIVER_TEL3, '') AS TEL3  \n");
        sb.append("   FROM TCUSTOMER A,  \n");
        sb.append("        TRECEIVER B,  \n");
        sb.append("        TCUSTSYSTEM C  \n");
        sb.append("  WHERE A.CUST_NO    = B.CUST_NO  \n");
        sb.append("    AND A.CUST_NO    = C.CUST_NO  \n");
        sb.append("    AND B.DEFAULT_YN = '1'  \n");
        sb.append("    AND A.CUST_NO    = '"+retrieve.getString("CUST_NO")+"' \n");

        //= log SQL -------------------------------------------------
        if (log.isDebugEnabled()) {
            log.debug("\n" + sb.toString());
        }

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : WH_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlWhName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.WH_NAME     \n"   );
        sb.append(  " FROM TWAREHOUSE  A \n"   );
        sb.append("  WHERE A.WH_CODE = ? \n"   );

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : LOTTERY_PROMO_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlLotteryPromoName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.LOTTERY_PROMO_NAME \n");
        sb.append("  FROM TLOTTERYPROMOM A \n");
        sb.append(" WHERE A.LOTTERY_PROMO_NO = ? ");
//      sb.append(" AND A.USE_CODE = '00' AND A.END_YN = '1'");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : PROMO_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlPromoName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.PROMO_NAME \n");
        sb.append("  FROM TPROMOM A  ");
        sb.append("  WHERE A.PROMO_NO = ?  ");
//      sb.append("    AND A.COUPON_YN = '1'   ORDER BY A.PROMO_NO DESC   ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : DESCRIBE_TITLE 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlDescribeName() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.DESCRIBE_TITLE \n");
        sb.append("  FROM TDESCRIBECODE A \n");
        sb.append(" WHERE A.DESCRIBE_CODE = ? ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : MAKECO_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeCompName() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.MAKECO_NAME \n");
        sb.append("  FROM TMAKECOMP A \n");
        sb.append(" WHERE A.MAKECO_CODE = ? ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : ORIGIN_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlOriginCode() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_NAME \n");
        sb.append("  FROM TCODE A     \n");
        sb.append(" WHERE A.CODE_LGROUP = 'B023'  \n");
        sb.append("   AND A.CODE_MGROUP = ?    \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : MEDIA_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeMediaName() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.MEDIA_NAME \n");
        sb.append("  FROM TMEDIA A \n");
        sb.append(" WHERE A.MEDIA_CODE = ? ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : ENTP_NAME 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeEntpName() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.ENTP_NAME \n");
        sb.append("  FROM TENTERPRISE A \n");
        sb.append(" WHERE A.ENTP_CODE = ? ");

        return sb.toString();
    }

    public String makeSqlMakeCustName() throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT CUST_NAME, MEMB_NO FROM TCUSTOMER WHERE CUST_NO = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : BOARD_DESC 을 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeBoardName() throws StoreException{

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.BOARD_DESC \n");
        sb.append("  FROM TBOARDINFO A \n");
        sb.append(" WHERE A.BOARD_CODE = ? ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담 대분류
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeCounselLgroupCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_NAME, \n");
        sb.append("       A.REMARK  \n");
        sb.append("  FROM TCODE A  \n");
        sb.append(" WHERE A.CODE_LGROUP = 'C031' \n");
        sb.append("   AND A.USE_YN      = '1' \n");
        sb.append("   AND A.CODE_MGROUP = '"+retrieve.getString("OUT_LGROUP_CODE")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담 대분류 from
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeCounselLgroupCodeFr(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_NAME, \n");
        sb.append("       A.REMARK  \n");
        sb.append("  FROM TCODE A  \n");
        sb.append(" WHERE A.CODE_LGROUP = 'C031' \n");
        sb.append("   AND A.USE_YN      = '1' \n");
        sb.append("   AND A.CODE_MGROUP = '"+retrieve.getString("OUT_LGROUP_CODE_FR")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담 대분류 to
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeCounselLgroupCodeTo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_NAME, \n");
        sb.append("       A.REMARK  \n");
        sb.append("  FROM TCODE A  \n");
        sb.append(" WHERE A.CODE_LGROUP = 'C031' \n");
        sb.append("   AND A.USE_YN      = '1' \n");
        sb.append("   AND A.CODE_MGROUP = '"+retrieve.getString("OUT_LGROUP_CODE_TO")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담 중분류
     * </PRE>
     * @return  Query
     */
    public String makeSqlMakeCounselMgroupCodeName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.CODE_NAME \n");
        sb.append("  FROM TCODE A \n");
        sb.append(" WHERE A.CODE_MGROUP = ?  \n ");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상담 중분류
     * </PRE>
     * @return  Query
     */
    public String makeSqlProgramName() throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.PROGRAM_NAME \n");
        sb.append("  FROM TSECPROGRAM A \n");
        sb.append(" WHERE UPPER(A.PROGRAM_ID) = ?  \n ");
        return sb.toString();
    }

   /**
    * <PRE>
    * Desc : Make SQL ; retreive code_group
    * </PRE>
    * @param   RetrieveModel
    * @return  String
    */
    public String makeSqlGroupName() throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT CODE_NAME  \n");
        sb.append("   FROM TCODE \n");
        sb.append(" WHERE CODE_LGROUP = '0000' \n");
        sb.append("   AND CODE_MGROUP = ? \n");
        sb.append("   AND ROWNUM = 1 \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ; retreive code_group
     * </PRE>
     * @param   RetrieveModel
     * @return  String
     */
    public String makeSqlCodeName() throws Exception {
    	StringBuffer sb = new StringBuffer();

    	sb.append(" SELECT CODE_NAME       \n");
    	sb.append("   FROM TCODE           \n");
    	sb.append("  WHERE CODE_MGROUP = ? \n");
    	sb.append("    AND ROWNUM = 1      \n");

    	return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 기초코드
     * </PRE>
     * @return  Query
     */
     public String makeSqlMakeTCode(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT CODE_MGROUP,                            \n");
         sb.append("          CODE_NAME,                            \n");
         sb.append("          CODE_GROUP,                           \n");
         sb.append("          REMARK                                \n");
         sb.append("     FROM TCODE                                 \n");
         sb.append("    WHERE CODE_MGROUP = UPPER('"+retrieve.getString("CODE_LGROUP")+"')  \n");
         sb.append("   AND CODE_LGROUP = '0000' AND CODE_MGROUP <> '0000' AND USE_YN='1'    \n");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

    /**
     * <PRE>
     * Desc : Make SQL ( 색상명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlColorName() {

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_NAME                            \n");
        sb.append("     FROM TCODE                              \n");
        sb.append("    WHERE CODE_LGROUP = 'B035'               \n");
        sb.append("      AND CODE_MGROUP < '999' AND USE_YN='1' \n");
        sb.append("      AND CODE_MGROUP = ?                    \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 크기명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlSizeName() {

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT CODE_NAME                            \n");
        sb.append("     FROM TCODE                              \n");
        sb.append("    WHERE CODE_MGROUP < '999' AND USE_YN='1' \n");
        sb.append("      AND CODE_MGROUP = ?                    \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 무늬명 조회 )
     * </PRE>
     * @return  String
    */
    private String makeSqlPatternName() {

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT CODE_NAME                            \n");
        sb.append("     FROM TCODE                              \n");
        sb.append("    WHERE CODE_LGROUP = 'B036'               \n");
        sb.append("      AND CODE_MGROUP < '999' AND USE_YN='1' \n");
        sb.append("      AND CODE_MGROUP = ?                    \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 상품명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlMasterName() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT GOODS_NAME, SALE_GB FROM TGOODS WHERE MASTER_CODE = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 재고조정 사유 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlGoodsControlName() {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT /*+RULE*/                     \n");
        sb.append("        A.CODE_NAME                   \n");
        sb.append("  FROM TCODE A                        \n");
        sb.append(" WHERE A.CODE_LGROUP = 'L010'         \n");
        sb.append("   AND A.USE_YN = '1'                 \n");
        sb.append("   AND A.CODE_MGROUP = ?              \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 사은품 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlGiftGoodsName() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_NAME \n");
        sb.append("   FROM TGOODS A, TMD B        \n");
        sb.append("  WHERE A.MD_CODE = B.MD_CODE  \n");
        sb.append("    AND A.GOODS_CODE = ?       \n");
        sb.append("    AND A.SALE_GB = '00'       \n");
        sb.append("    AND A.SIGN_GB = '80'       \n");
        sb.append("    AND A.SQC_GB LIKE '1%'     \n");
        sb.append("    AND (A.GIFT_YN = '1'       \n");
        sb.append("        OR A.SAMPLE_YN = '1')  \n");
        sb.append("    AND A.SET_GOODS_YN = '0'   \n");
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 카테고리 이름 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlCategoryName(){
        StringBuffer sb = new StringBuffer();
         sb.append(" SELECT CATEGORY_NAME     \n");
         sb.append("  FROM TCATEGORY          \n");
         sb.append(" WHERE CATEGORY_CODE = ?  \n");
        return sb.toString();
    }
    /**
     * <PRE>
     * Desc : Make SQL ( 카테고리 이름 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlSegmentName(){
        StringBuffer sb = new StringBuffer();
         sb.append(" SELECT SEGMENT_NAME     \n");
         sb.append("  FROM TSEGMENT          \n");
         sb.append(" WHERE SEGMENT_CODE = ?  \n");
         return sb.toString();
    }
    /**
     * <PRE>
     * Desc : Make SQL ( 결제구분 이름 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlSettleName(){
        StringBuffer sb = new StringBuffer();
         sb.append(" SELECT SETTLE_NAME FROM VSETTLEINFO WHERE SETTLE_FLAG = ? \n");
         return sb.toString();
    }
    /**
    * <PRE>
    * Desc : 실사코드정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlCheckCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.CHECK_CODE, \n");
        sb.append("        A.CHECK_GB, \n");
        sb.append("        A.STOCK_CHECK_NOTE, \n");
        sb.append("        TO_CHAR(A.CHECK_DATE, 'YYYY/MM/DD') AS CHECK_DATE \n");
        sb.append("   FROM TSTOCKCHECKCODE A \n");
        sb.append("  WHERE A.CHECK_CODE = '"+retrieve.getString("CHECK_CODE")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.CHECK_DATE ASC  \n");
        return sb.toString();
    }

    /**
    * <PRE>
    * Desc : 세분류정보
    * </PRE>
    * @return  Query
    */
    public String makeSqlDgroup(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        String modString = retrieve.getString("MOD_QUERY");
        if ( modString.equals("") )
            modString = " AND A.LGROUP IN (SELECT CODE_MGROUP FROM TCODE WHERE CODE_LGROUP = 'B053' AND USE_YN = '1')";

        if ( !retrieve.getString("LGROUP").equals("") )
            modString = " AND A.LGROUP = '" + retrieve.getString("LGROUP") + "'";
        if ( !retrieve.getString("MGROUP").equals("") )
            modString += " AND A.MGROUP = '" + retrieve.getString("MGROUP") + "'";
        if ( !retrieve.getString("SGROUP").equals("") )
            modString += " AND A.SGROUP = '" + retrieve.getString("SGROUP") + "'";

        retrieve.put("MOD_QUERY",modString);

        sb.append(" SELECT A.DGROUP_NAME,   \n");
//        sb.append("        A.SIZE_LGROUP,   \n");
        sb.append("        A.QC_LGROUP,     \n");
        sb.append("        A.QC_MGROUP      \n");
        sb.append("   FROM TGOODSKINDS A    \n");
        sb.append("  WHERE A.DGROUP LIKE '" + retrieve.getString("DGROUP_CODE")   +"' || '%' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 발주용 상품,단품조회 Popup
     * </PRE>
     * @return  Query
     */
    public String makeSqlGoodsCodeBalju(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT /*+RULE*/       \n");
        sb.append("        A.GOODS_CODE,   \n");
        sb.append("        B.GOODSDT_CODE, \n");
        sb.append("        A.GOODS_NAME,   \n");
        sb.append("        B.GOODSDT_INFO, \n");
        sb.append("        A.SQC_GB,       \n");
        sb.append("        C.BUY_COST      \n");
        sb.append("   FROM TGOODS      A,  \n");
        sb.append("        TGOODSDT    B,  \n");
        sb.append("        TGOODSPRICE C   \n");
        sb.append("  WHERE A.GOODS_CODE      = B.GOODS_CODE \n");
        sb.append("    AND A.GOODS_CODE      = C.GOODS_CODE \n");
        sb.append("    AND B.GOODSDT_CODE    > '000' \n");
        sb.append("    AND A.BUY_MED        IN ( '11', '13','21' ) \n");
        sb.append("    AND A.SIGN_GB         = '80' \n");
        sb.append("    AND B.SALE_GB        <> '19' \n");
        sb.append("    AND A.SET_YN          = '0' \n");
        sb.append("    AND A.GOODS_CODE      > ' ' \n");
        sb.append("    AND A.GOODS_CODE   = '" + retrieve.getString("GOODS_CODE")   +"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append("    AND A.MD_CODE      = '"    + retrieve.getString("MD_CODE")      + "' \n");
        sb.append("    AND A.ENTP_CODE    = '"    + retrieve.getString("ENTP_CODE")    + "' \n");
        sb.append("    AND A.WH_CODE      = '"    + retrieve.getString("WH_CODE")      + "' \n");
        sb.append("    AND A.BUY_MED      = '"    + retrieve.getString("BUY_MED")      + "' \n");
        sb.append("    AND C.APPLY_DATE   = \n");
        sb.append("                 ( SELECT MAX(Z.APPLY_DATE) \n");
        sb.append("                     FROM TGOODSPRICE Z \n");
        sb.append("                    WHERE Z.GOODS_CODE  = A.GOODS_CODE \n");
        sb.append("                      AND Z.APPLY_DATE <= SYSDATE ) \n");

        log.info(sb.toString());
        return sb.toString();
    }

    private String makeSqlRackCode() {

       StringBuffer sb = new StringBuffer();
       sb.append("SELECT RACK_CODE FROM TRACKCODE WHERE USE_YN = '1' AND RACK_CODE = ? \n ");

       return sb.toString();
    }

    private String makeSqlRackCodeEtc() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SUBSTR(RACK_CODE,4, 7)|| '/' || RACK_GRADE || '/' || RACK_GB || '/' || RACK_NOTE FROM TRACKCODE WHERE USE_YN = '1' AND RACK_CODE = ? \n ");

        return sb.toString();
    }

    private String makeSqlDefaultOutRackCode() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT VAL FROM TCONFIG WHERE ITEM = ? \n ");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상품코드(재고가 있는상품)
     * </PRE>
     * @return  Query
     */
    public String makeSqlGoodsCodeDS1(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("    SELECT /*+RULE*/                                                                       \n");
        sb.append("    C.GOODS_CODE,            		                                                      \n");
        sb.append("    C.GOODSDT_CODE,                                                                        \n");
        sb.append("    C.GOODS_NAME,            		                                                      \n");
        sb.append("    C.GOODSDT_INFO,              	                                                      \n");
        sb.append("    NVL(F.AQTY, 0)-NVL(F.ORDER_QTY, 0)-NVL(F.OUT_PLAN_QTY, 0) AS AQTY,  /*수정한부분*/       \n");
        sb.append("    NVL(F.BQTY, 0) AS BQTY,  		                                                      \n");
        sb.append("    NVL(F.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,                                         \n");
        sb.append("    NVL(F.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,                                         \n");
        sb.append("    C.STOCK_CHK_PLACE,                                                                     \n");
        sb.append("    F.WH_CODE        		                                                              \n");
        sb.append("    FROM (                                                                                 \n");
        sb.append("          SELECT A.GOODS_CODE,                                                             \n");
        sb.append("                 B.GOODSDT_CODE,                                                           \n");
        sb.append("                 A.GOODS_NAME,                                                             \n");
        sb.append("                 B.GOODSDT_INFO,                                                           \n");
        sb.append("                 A.STOCK_CHK_PLACE,                                                        \n");
        sb.append("                 A.WH_CODE                                                                 \n");
        sb.append("            FROM TGOODS A,                                                                 \n");
        sb.append("                 TGOODSDT B        	                                                      \n");
        sb.append("           WHERE A.GOODS_CODE = B.GOODS_CODE) C,     		                              \n");
        sb.append("           (                                                                               \n");
        sb.append("           SELECT D.WH_CODE,                                                               \n");
        sb.append("                  D.GOODS_CODE,                                                            \n");
        sb.append("                  D.GOODSDT_CODE,                                                          \n");
        sb.append("                  D.AQTY,                                                                  \n");
        sb.append("                  D.BQTY,                                                                  \n");
        sb.append("                  D.EOUT_QUEST_AQTY,                                                       \n");
        sb.append("                  D.EOUT_QUEST_BQTY,                                                       \n");
        sb.append("                  NVL(E.ORDER_QTY, 0) AS ORDER_QTY,                                        \n");
        sb.append("                  NVL(E.OUT_PLAN_QTY, 0) AS OUT_PLAN_QTY                                   \n");
        sb.append("             FROM TSTOCK D,                  						                      \n");
        sb.append("                  TORDERSTOCK E                                                            \n");
        sb.append("            WHERE D.WH_CODE = E.WH_CODE(+)                                                 \n");
        sb.append("              AND D.GOODS_CODE = E.GOODS_CODE(+)                                           \n");
        sb.append("              AND D.GOODSDT_CODE = E.GOODSDT_CODE(+) ) F                                   \n");
        sb.append("    WHERE F.GOODS_CODE = C.GOODS_CODE                                                      \n");
        sb.append("      AND F.GOODSDT_CODE = C.GOODSDT_CODE                                                  \n");
        sb.append("      AND C.GOODSDT_CODE > ' '               											  \n");
        sb.append("      AND F.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%'  				  \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();

        //= 2009.07.09 JSY DELETE
//        sb.append("  SELECT /*+RULE*/               \n");
//        sb.append("  C.GOODS_CODE,            		\n");
//        sb.append("  C.GOODSDT_CODE,                \n");
//        sb.append("  C.GOODS_NAME,            		\n");
//        sb.append("  C.GOODSDT_INFO,              	\n");
//        sb.append("  NVL(D.AQTY, 0)-NVL(E.ORDER_QTY, 0)-NVL(E.OUT_PLAN_QTY, 0) AS AQTY,  /*수정한부분*/  \n");
//        sb.append("  NVL(D.BQTY, 0) AS BQTY,  		\n");
//        sb.append("  NVL(D.EOUT_QUEST_AQTY,  0) AS EOUT_QUEST_AQTY,       \n");
//        sb.append("  NVL(D.EOUT_QUEST_BQTY,  0) AS EOUT_QUEST_BQTY,       \n");
//        sb.append("  C.STOCK_CHK_PLACE        		\n");
//        sb.append("  FROM (                         \n");
//        sb.append("        SELECT A.GOODS_CODE,     \n");
//        sb.append("                 B.GOODSDT_CODE, \n");
//        sb.append("             A.GOODS_NAME,       \n");
//        sb.append("                 B.GOODSDT_INFO, \n");
//        sb.append("                 A.STOCK_CHK_PLACE,    \n");
//        sb.append("                 A.WH_CODE             \n");
//        sb.append("          FROM TGOODS A,               \n");
//        sb.append("               TGOODSDT B        	  \n");
//        sb.append("         WHERE A.GOODS_CODE = B.GOODS_CODE) C,     		\n");
//        sb.append("         TSTOCK D,                  						\n");
//        sb.append("         TORDERSTOCK E                   /*수정한부분*/    \n");
//        sb.append("  WHERE D.GOODS_CODE   = C.GOODS_CODE    /*수정한부분*/	\n");
//        sb.append("    AND D.GOODSDT_CODE = C.GOODSDT_CODE  /*수정한부분*/    \n");
//        sb.append("    AND D.WH_CODE      = C.WH_CODE       /*수정한부분*/    \n");
//        sb.append("    AND E.WH_CODE 	  = D.WH_CODE       /*수정한부분*/    \n");
//        sb.append("    AND E.GOODS_CODE   = D.GOODS_CODE    /*수정한부분*/    \n");
//        sb.append("    AND E.GOODS_CODE   = C.GOODS_CODE    /*수정한부분*/    \n");
//        sb.append("    AND E.GOODSDT_CODE = C.GOODSDT_CODE  /*수정한부분*/    \n");
//
//        sb.append("    AND C.GOODSDT_CODE > ' '               \n");
//        sb.append("    AND C.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%'  \n ");
//        sb.append(retrieve.getString("MOD_QUERY"));

    }

    /**
     * <PRE>
     * Desc : 셋트 구성단품
     * </PRE>
     * @return  Query
     */
    public String makeSqlGoodsDtCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.GOODS_CODE,                \n");
        sb.append("        B.GOODSDT_CODE,              \n");
        sb.append("        A.GOODS_NAME,                \n");
        sb.append("        B.GOODSDT_INFO,              \n");
        sb.append("        0 AQTY,                      \n");
        sb.append("        0 BQTY,                      \n");
        sb.append("        0 EOUT_QUEST_AQTY,           \n");
        sb.append("        0 EOUT_QUEST_BQTY,           \n");
        sb.append("        A.STOCK_CHK_PLACE            \n");
        sb.append("   FROM TGOODS A,                    \n");
        sb.append("        TGOODSDT B                   \n");
        sb.append("  WHERE A.GOODS_CODE = B.GOODS_CODE  \n");
        sb.append("   AND  B.GOODSDT_CODE > '000'       \n");
        sb.append("   AND  A.GOODS_CODE = '"+retrieve.getString("GOODS_CODE")+"'    \n");
        sb.append("   AND  B.GOODSDT_CODE = '"+retrieve.getString("GOODSDT_CODE")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상품단품의 위치코드정보
     * </PRE>
     * @return  Query
     */
    public String makeSqlRackCodeGrade(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.RACK_CODE,                      \n");
        sb.append("        A.RACK_GRADE,                     \n");
        sb.append("        A.RACK_GB,                        \n");
        sb.append("        A.RACK_NOTE,                      \n");
        sb.append("        B.RACK_QTY,                       \n");
        sb.append("        B.PICKING_QTY,                    \n");
        sb.append("        B.GOODS_CODE,                     \n");
        sb.append("        B.GOODSDT_CODE,                    \n");
        sb.append("        DECODE(A.RACK_GRADE, '1' , (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_ARACK'), '2', (SELECT VAL FROM TCONFIG WHERE ITEM = 'DEF_OUT_BRACK')) AS OUT_RACK \n");
        sb.append("   FROM TRACKCODE A,                      \n");
        sb.append("        (SELECT Z.RACK_CODE,              \n");
        sb.append("                Z.GOODS_CODE,             \n");
        sb.append("                Z.GOODSDT_CODE,           \n");
        sb.append("                Z.RACK_QTY,               \n");
        sb.append("                Z.PICKING_QTY             \n");
        sb.append("           FROM TRACK Z                   \n");
        sb.append("          WHERE Z.GOODS_CODE   = '" + retrieve.getString("GOODS_CODE") + "' \n ");
        sb.append(retrieve.getString("MOD_QUERY1"));
        sb.append("            AND Z.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE") + "' )B \n ");
        sb.append("  WHERE A.RACK_CODE = '" + retrieve.getString("RACK_CODE") + "' \n ");
        sb.append("    AND A.RACK_CODE = B.RACK_CODE(+)      \n");
        sb.append("    AND A.USE_YN = '1'                 \n");
        sb.append(retrieve.getString("MOD_QUERY2"));
        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상품단품의 위치코드정보
     * </PRE>
     * @return  Query
     */
    public String makeSqlGoodsSearch(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("  SELECT DISTINCT A.GOODS_CODE,   \n");
        sb.append("         A.GOODS_NAME,           \n");
        sb.append("         B.BUY_COST,             \n");
        sb.append("         B.BUY_VAT,              \n");
        sb.append("         B.SALE_PRICE,           \n");
        sb.append("         FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'8') AS MARGIN_RATE, \n");
        sb.append("         A.SET_YN,               \n");
        sb.append("         A.ENTP_CODE,                \n");
        sb.append("         F.ENTP_NAME,                \n");
        sb.append("         0 AS SALE_CNT_AMT,      \n");
        sb.append("         DECODE(X.IMAGE_M, '', 'N', 'Y') AS M,            \n");
        sb.append("         DECODE(X.IMAGE_M, '', '0', '1') AS CHOICE,    \n");
        sb.append("         A.MAKECO_CODE,          \n");
        sb.append("         I.MAKECO_NAME,          \n");
        sb.append("         A.BRAND_CODE,           \n");
        sb.append("         H.BRAND_NAME,           \n");
        sb.append("         TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,  \n");
        sb.append("         '0000/00/00' AS BROAD_DATE                                      \n");
        sb.append("    FROM TGOODS A,           \n");
        sb.append("         TGOODSPRICE B,      \n");
        sb.append("         TENTERPRISE F,      \n");
        sb.append("         TMAKECOMP I,            \n");
        sb.append("         TBRAND H,           \n");
        sb.append("         TMD M,              \n");
        sb.append("         TGOODSIMAGE X       \n");
        sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE                         \n");
        sb.append("     AND B.APPLY_DATE = ( SELECT MAX(C.APPLY_DATE)           \n");
        sb.append("                            FROM TGOODSPRICE C               \n");
        sb.append("                           WHERE A.GOODS_CODE = C.GOODS_CODE  \n");
        sb.append("                             AND C.APPLY_DATE <= sysdate)        \n");
        sb.append("     AND A.ENTP_CODE = F.ENTP_CODE                           \n");
        sb.append("     AND A.MAKECO_CODE = I.MAKECO_CODE                       \n");
        sb.append("     AND A.BRAND_CODE = H.BRAND_CODE                         \n");
        sb.append("     AND A.SIGN_GB = '80'                                        \n");
        sb.append("     AND A.MD_CODE = M.MD_CODE                               \n");
        sb.append("     AND A.GOODS_CODE = X.GOODS_CODE(+)                      \n");
        sb.append("     AND A.GOODS_CODE = '"+retrieve.getString("GOODS_CODE")+"' \n ");
        sb.append("     "+retrieve.getString("modString")+" \n ");
        sb.append("   ORDER BY TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') DESC, LPAD(A.GOODS_CODE, 10, '0')    \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 상품단품의 위치코드정보
     * </PRE>
     * @return  Query
     */
    public String makeSqlObSeq(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.OB_GB, \n");
        sb.append("       A.OB_SEQ, \n");
        sb.append("       A.OB_TITLE, \n");
        sb.append("       TO_CHAR(A.SELECT_DATE,'YYYY/MM/DD HH24:MI:SS') AS SELECT_DATE, \n");
        sb.append("       A.SELECT_ID, \n");
        sb.append("       B.USER_NAME AS SELECT_NAME, \n");
        sb.append("       A.SELECT_CNT, \n");
        sb.append("       A.OB_NOTE \n");
        sb.append("  FROM TOBCODE A, \n");
        sb.append("        TUSER B    \n");
        sb.append(" WHERE A.SELECT_ID = B.USER_ID \n ");
        sb.append("   AND A.OB_SEQ = '"+retrieve.getString("OB_SEQ")+"' \n ");
        sb.append(retrieve.getString("MOD_QUERY"));
        sb.append(" ORDER BY A.OB_GB, A.OB_SEQ ASC  \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : LOTTERY_PROMO_NO를 키로 추첨프로모션정보를 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlLotteryPromoNo(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.LOTTERY_PROMO_NO,     \n");
        sb.append("       A.LOTTERY_PROMO_NAME,   \n");
        sb.append("       A.PROMO_NOTE,       \n");
        sb.append("       A.PROVIDE_CNT,      \n");
        sb.append("       A.END_YN,           \n");
        sb.append("       TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,  \n");
        sb.append("       A.CONFIRM_CNT,      \n");
        sb.append("       A.DO_TYPE,          \n");
        sb.append("        (SELECT COUNT(*) AS CNT                            \n");
        sb.append("           FROM TLOTTERYPROMOCUST B, TCUSTOMER C           \n");
        sb.append("          WHERE B.CUST_NO = C.CUST_NO                      \n");
        sb.append("            AND B.LOTTERY_PROMO_NO = A.LOTTERY_PROMO_NO    \n");
        sb.append("            AND B.CANCEL_YN = '0') CNT,                     \n");
        sb.append("        A.APPLY_YN                     \n");
        sb.append("   FROM TLOTTERYPROMOM A                                   \n");
        sb.append("  WHERE A.LOTTERY_PROMO_NO = '"+retrieve.getString("LOTTERY_PROMO_NO")+"' ");
        sb.append(retrieve.getString("MOD_QUERY"));

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : SEGMENT_CODE를 키로 세그먼트정보를 얻는다.
     * </PRE>
     * @return  Query
     */
    public String makeSqlSegmentCode(RetrieveModel retrieve) throws StoreException{
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT A.SEGMENT_CODE,     \n");
        sb.append("       A.SEGMENT_NAME,     \n");
        sb.append("       A.TARGET_QTY,       \n");
        sb.append("       A.MEMO              \n");
        sb.append("  FROM TSEGMENT A          \n");
        sb.append("  WHERE A.SEGMENT_CODE = '"+retrieve.getString("SEGMENT_CODE")+"' ");
        sb.append(retrieve.getString("MOD_QUERY"));

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : Make SQL ( 주문 상품명 조회 )
     * </PRE>
     * @return  String
     */
    private String makeSqlOrderGoodsName(RetrieveModel retrieve) throws StoreException{

        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT A.GOODS_CODE,                                              \n");
        sb.append("        A.GOODS_NAME,                                              \n");
        sb.append("        B.SALE_PRICE,                                              \n");
        sb.append("        A.NOREST_ALLOT_YN,                                         \n");
        sb.append("        A.SET_YN,                                                  \n");
        sb.append("        A.EXCH_YN,                                                 \n");
        sb.append("        A.RETURN_YN,                                               \n");
        sb.append("        A.SALE_GB,                                                 \n");
        sb.append("        A.ENTP_CODE,                                               \n");
        sb.append("        A.NOREST_ALLOT_MONTHS                                      \n");
        sb.append("   FROM TGOODS A, TGOODSPRICE B                                    \n");
        //= PKG11 주문접수 프로그램에서 상품코드 5자리입력 후 itemchange 시 카타로그의 상품코드를 조회  - 20110328 by kst
        if(retrieve.getString("CATALOG_GOODS_YN").equals("1")){
        	sb.append(" WHERE A.GOODS_CODE  = ( \n");
        	sb.append(" SELECT GOODS_CODE \n");
        	sb.append("     FROM TMEDIAGOODS \n");
        	sb.append("   WHERE CTLG_CODE  = '"+retrieve.getString("GOODS_CODE")+"' \n");
        	sb.append("     AND MEDIA_CODE = ( SELECT MAX(MEDIA_CODE) \n");
        	sb.append("                   	       FROM TMEDIA A \n");
        	sb.append("                   	     WHERE A.SEND_DATE = ( SELECT MAX(SEND_DATE) \n");
        	sb.append("                                                    FROM TMEDIAGOODS MG, \n");
        	sb.append("                                                         TMEDIA ME \n");
        	sb.append("                                                  WHERE MG.MEDIA_CODE = ME.MEDIA_CODE \n");
        	sb.append("                                                    AND MG.CTLG_CODE  = '"+retrieve.getString("GOODS_CODE")+"') ) ) \n");        	
        }else{
        	sb.append("  WHERE A.GOODS_CODE      = '"+retrieve.getString("GOODS_CODE")+"' \n");
        }
        sb.append("    AND A.GOODS_CODE      = B.GOODS_CODE                           \n");
        sb.append("    AND A.GIFT_YN         = '0'                                    \n");
        sb.append("    AND A.SET_GOODS_YN    = '0'                                    \n");
        sb.append("    AND A.SALE_GB         < '20'                                   \n");
        sb.append("    AND A.SQC_GB          LIKE '1%'                                \n");
        sb.append("    AND A.SAMPLE_YN       = '0'                                    \n");
        sb.append("    AND A.SALE_START_DATE <= SYSDATE                               \n");
        sb.append("    AND B.APPLY_DATE   IN                                          \n");
        sb.append("                     ( SELECT MAX(APPLY_DATE)                      \n");
        sb.append("                         FROM TGOODSPRICE                          \n");
        sb.append("                        WHERE APPLY_DATE  <=  SYSDATE              \n");
        sb.append("                          AND GOODS_CODE   =  A.GOODS_CODE )       \n");
        sb.append("    AND ( A.ORDER_MEDIA_ALL_YN = '1' OR                            \n");
        sb.append("          (A.ORDER_MEDIA_ALL_YN = '0' AND INSTR(A.ORDER_MEDIA, '"+retrieve.getString("ORDER_MEDIA")+"') > 0) \n");
        sb.append("        )                                                          \n");

        return sb.toString();
    }

    /**
     * <PRE>
     * Desc : 업체반출용 상품코드(상품,단품조회 Popup)
     * </PRE>
     * @return  Query
     */
     public String makeSqlGoodsCodeEntpTakeout(RetrieveModel retrieve) throws StoreException{
     	StringBuffer sb = new StringBuffer();
         sb.append("SELECT /*+RULE*/  \n");
         sb.append("       A.GOODS_CODE,  \n");
         sb.append("       B.GOODSDT_CODE,  \n");
         sb.append("       A.GOODS_NAME,  \n");
         sb.append("       B.GOODSDT_INFO,  \n");
         sb.append("       C.AQTY,  \n");
         sb.append("       C.BQTY,  \n");
         sb.append("       C.EOUT_QUEST_AQTY,  \n");
         sb.append("       C.EOUT_QUEST_BQTY,  \n");
         sb.append("       A.STOCK_CHK_PLACE,  \n");
         sb.append("     	 ( SELECT D.ENTP_MAN_SEQ  \n");
         sb.append("      		FROM TENTPUSER D  \n");
         sb.append("      		WHERE D.ENTP_CODE = A.ENTP_CODE  \n");
         sb.append("       		  AND D.DEFAULT_YN = '1'  \n");
         sb.append("       		  AND D.USE_YN = '1'  \n");
         sb.append("       		  AND ROWNUM = 1)  AS ENTP_MAN_SEQ  \n");
         sb.append("  FROM TGOODS A,  \n");
         sb.append("       TGOODSDT B,  \n");
         sb.append("       TSTOCK C  \n");
         sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
         sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
         sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
         sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
         sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
         sb.append("   AND A.GOODS_CODE = '" + retrieve.getString("GOODS_CODE")	+ "' \n");
         sb.append("   AND A.BUY_MED 	= '" + retrieve.getString("BUY_MED")		+ "' \n");
         sb.append("   AND A.MD_CODE 	= '" + retrieve.getString("MD_CODE")		+ "' \n");
         sb.append("   AND A.ENTP_CODE	= '" + retrieve.getString("ENTP_CODE")	+ "' \n");
         sb.append("   AND C.WH_CODE 	= '" + retrieve.getString("WH_CODE")		+ "' \n");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : MEDIA_CODE (광고매체 조회).
      * </PRE>
      * @return  Query
      */
     public String makeSqlMakeMedia(RetrieveModel retrieve) throws StoreException{

         StringBuffer sb = new StringBuffer();
         sb.append("	SELECT B.CODE_MGROUP AS MEDIA_GB, \n");
         sb.append("		   B.CODE_NAME, \n");
         sb.append("		   A.MEDIA_CODE, \n");
         sb.append("		   A.MEDIA_NAME, \n");
         sb.append("		   A.MEDIA_YEAR  \n");
         sb.append("	  FROM TMEDIA A, 	 \n");
         sb.append("		   TCODE B		 \n");
         sb.append("	 WHERE B.CODE_LGROUP = 'B009'  \n");
         sb.append("	   AND B.CODE_MGROUP = A.MEDIA_GB  \n");
         sb.append("	   AND A.MEDIA_CODE = '"+retrieve.getString("MEDIA_CODE")+"'  \n");
         sb.append(retrieve.getString("MOD_QUERY"));

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 사용자 조회.
      * </PRE>
      * @return  Query
      */
     public String makeSqlMakeUser(RetrieveModel retrieve) throws StoreException{

    	 StringBuffer sb = new StringBuffer();
         sb.append("	SELECT A.USER_ID AS INSERT_ID, \n");
         sb.append("		   A.USER_NAME AS INSERT_NAME, \n");
         sb.append("		   A.USER_GB  \n");
         sb.append("	  FROM TUSER A 	 \n");
         sb.append("	 WHERE A.USER_ID = '"+retrieve.getString("INSERT_ID")+"'  \n");
         sb.append(retrieve.getString("MOD_QUERY"));

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 게시판대분류
      * </PRE>
      * @return  Query
      */
     public String makeSqlBoardLkindsCode(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.CODE_NAME, \n");
         sb.append("       A.REMARK  \n");
         sb.append("  FROM TCODE A  \n");
         sb.append(" WHERE A.CODE_LGROUP = 'F012' \n");
         sb.append("   AND A.USE_YN      = '1' \n");
         sb.append("   AND A.CODE_MGROUP = '"+retrieve.getString("BOARD_LKINDS")+"' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 게시판소분류.
      * </PRE>
      * @return  Query
      */
     public String makeSqlBoardMkinds() throws StoreException{

    	 StringBuffer sb = new StringBuffer();
         sb.append("	SELECT A.CODE_NAME \n");
         sb.append("	  FROM TCODE A 	 \n");
         sb.append("	 WHERE A.CODE_MGROUP = ?  \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 상품MD.
      * </PRE>
      * @return  Query
      */
     public String makeSqlGoodsCodeMd(RetrieveModel retrieve) throws StoreException{

    	 StringBuffer sb = new StringBuffer();
         sb.append("SELECT /*+ INDEX_ASC (A PK_TGOODS) */ A.GOODS_CODE,  \n");
         sb.append("       A.GOODS_NAME,   \n");
         sb.append("       A.ENTP_CODE,   \n");
         sb.append("       A.TAX_YN,  \n");
         sb.append("       A.DELY_TYPE,   \n");
         sb.append("       A.SALE_GB,  \n");
         sb.append("       A.MD_CODE,   \n");
         sb.append("       B.MD_NAME,   \n");
         sb.append("       A.STOCK_CHK_PLACE  \n");
         sb.append("  FROM TGOODS A,   \n");
         sb.append("  	   TMD B   \n");
         sb.append(" WHERE A.MD_CODE = B.MD_CODE	\n");
         sb.append("   AND A.SALE_GB   != '19'  \n");
         sb.append("   AND A.GOODS_CODE = '"+retrieve.getString("GOODS_CODE")+"' \n ");
         sb.append(retrieve.getString("MOD_QUERY"));

         return sb.toString();
     }
     /**
      * <PRE>
      * Desc : 테입명.
      * </PRE>
      * @return  Query
      */
     public String makeSqlTapeName() throws StoreException{

    	 StringBuffer sb = new StringBuffer();
         sb.append("	SELECT A.TAPE_NAME \n");
         sb.append("	  FROM TPGMTAPE A 	 \n");
         sb.append("	 WHERE A.TAPE_CODE = ?  \n");

         return sb.toString();
     }
     /**
      * <PRE>
      * Desc : 테입명.
      * </PRE>
      * @return  Query
      */
     public String makeSqlSoName() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

         sb.append(" SELECT  A.SO_NAME           \n");
         sb.append("    FROM TSOMANAGE A         \n");
         sb.append("   WHERE A.SO_CODE = ? 		 \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 종료시간.
      * </PRE>
      * @return  Query
      */
     public String makeSqlEtime(RetrieveModel retrieve) throws StoreException{

    	 StringBuffer sb = new StringBuffer();

         sb.append(" SELECT TO_CHAR(TO_DATE('"+retrieve.getString("BTIME")+"','YYYY/MM/DD HH24:MI') + '"+retrieve.getString("RUNTIME")+"'/1440, 'YYYY/MM/DD HH24:MI') AS ETIME FROM DUAL \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : State of Post.
      * </PRE>
      * @return  Query
      */
     public String makeSqlStateName() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

         sb.append("  SELECT A.CODE_NAME AS  STATE        	\n");
         sb.append("    FROM TCODE A         				\n");
         sb.append("   WHERE A.CODE_MGROUP = ? 		 		\n");
         sb.append("     AND A.CODE_LGROUP = 'B063'     	\n");
         sb.append("     AND A.USE_YN = '1'     		 	\n");
         sb.append("   ORDER BY A.CODE_MGROUP   	 		\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : size of TGOODSINFOM.
      * </PRE>
      * @return  Query
      */
     public String makeSqlGroupSize() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_DESC 			\n");
         sb.append("    FROM TGOODSINFOM A 			\n");
         sb.append("   WHERE A.CSPF_FLAG = 'S' 		\n ");
         sb.append("     AND A.CSPF_GROUP LIKE ? 	\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");
         sb.append("   ORDER BY A.CSPF_GROUP   	 	\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : form of TGOODSINFOM.
      * </PRE>
      * @return  Query
      */
     public String makeSqlGroupForm() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_DESC 			\n");
         sb.append("    FROM TGOODSINFOM A 			\n");
         sb.append("   WHERE A.CSPF_FLAG = 'F' 		\n ");
         sb.append("     AND A.CSPF_GROUP LIKE ? 	\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");
         sb.append("   ORDER BY A.CSPF_GROUP   	 	\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : color of TGOODSINFOM.
      * </PRE>
      * @return  Query
      */
     public String makeSqlGroupColor() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_DESC 			\n");
         sb.append("    FROM TGOODSINFOM A 			\n");
         sb.append("   WHERE A.CSPF_FLAG = 'C' 		\n ");
         sb.append("     AND A.CSPF_GROUP LIKE ? 	\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");
         sb.append("   ORDER BY A.CSPF_GROUP   	 	\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : pattern of TGOODSINFOM.
      * </PRE>
      * @return  Query
      */
     public String makeSqlGroupPattern() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_DESC 			\n");
         sb.append("    FROM TGOODSINFOM A 			\n");
         sb.append("   WHERE A.CSPF_FLAG = 'P' 		\n ");
         sb.append("     AND A.CSPF_GROUP LIKE ? 	\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");
         sb.append("   ORDER BY A.CSPF_GROUP   	 	\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : size of TGOODSINFODT.
      * </PRE>
      * @return  Query
      */
     public String makeSqlColorCode() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_NAME 			\n");
         sb.append("    FROM TGOODSINFODT A 		\n");
         sb.append("   WHERE A.CSPF_GROUP = 'C000' 	\n ");
         sb.append("     AND A.CSPF_CODE  = ? 		\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : size of TGOODSINFODT.
      * </PRE>
      * @return  Query
      */
     public String makeSqlPatternCode() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_NAME 			\n");
         sb.append("    FROM TGOODSINFODT A 		\n");
         sb.append("   WHERE A.CSPF_GROUP = 'P000' 	\n ");
         sb.append("     AND A.CSPF_CODE  = ? 		\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : size of TGOODSINFODT.
      * </PRE>
      * @return  Query
      */
     public String makeSqlSizeCode() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_NAME 			\n");
         sb.append("    FROM TGOODSINFODT A 		\n");
         sb.append("   WHERE A.CSPF_CODE  = ? 		\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : size of TGOODSINFODT.
      * </PRE>
      * @return  Query
      */
     public String makeSqlFormCode() throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("  SELECT A.CSPF_NAME 			\n");
         sb.append("    FROM TGOODSINFODT A 		\n");
         sb.append("   WHERE A.CSPF_CODE  = ? 		\n ");
         sb.append("     AND A.USE_YN = '1'  		\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ( 사용자그룹명 조회 )
      * </PRE>
      * @return  String
      */
     private String makeSqlUserGroupName() {

         StringBuffer sb = new StringBuffer();

         sb.append("  SELECT CODE_NAME AS USER_GROUP_NAME    \n");
         sb.append("    FROM TCODE                   \n");
         sb.append("   WHERE CODE_LGROUP='B040'      \n");
         sb.append("     AND CODE_MGROUP = ?         \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 상담 대분류
      * </PRE>
      * @return  Query
      */
     public String makeSqlProgramCodeName(RetrieveModel retrieve) throws StoreException{
         StringBuffer sb = new StringBuffer();
         sb.append("SELECT A.LMENU_ID, \n");
         sb.append("       A.MMENU_ID,  \n");
         sb.append("       A.PROGRAM_ID,  \n");
         sb.append("       A.PROGRAM_NAME  \n");
         sb.append("  FROM TSECPROGRAM A  \n");
         sb.append(" WHERE A.PROGRAM_ID  = '"+retrieve.getString("PROGRAM_ID")+"' \n");
         sb.append("   AND A.USE_YN      = '1' \n");
         sb.append(retrieve.getString("MOD_QUERY"));
         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 기초코드
      * Date : 2009/04/23
      * Registrator : 진유나
      * </PRE>
      * @return  Query
      */
     private String makeSqlTCodeName() {

         StringBuffer sb = new StringBuffer();

          sb.append("  SELECT CODE_NAME 		      \n");
          sb.append("    FROM TCODE                   \n");
          sb.append("   WHERE CODE_MGROUP = ?         \n");
          sb.append("   AND CODE_LGROUP = '0000'	  \n");

          return sb.toString();
      }

     /**
      * <PRE>
      * Desc : 업체반출등록 수량
      * </PRE>
      * @return  Query
      */
     public String makeSqlEoutQty(RetrieveModel retrieve) throws StoreException{

    	 StringBuffer sb = new StringBuffer();

    	 sb.append("	  SELECT /*+ RULE */  						\n");
         sb.append("			 A.GOODS_CODE,						\n");
         sb.append("			 A.GOODSDT_CODE,					\n");
         sb.append("	         C.GOODS_NAME, 						\n");
         sb.append("    	     D.GOODSDT_INFO, 					\n");
         sb.append("       	  	 SUM(NVL(B.AQTY, 0)) - (SUM(NVL(A.ORDER_QTY,0)) + SUM(NVL(A.OUT_PLAN_QTY,0))) AS AQTY, \n");
         sb.append("	         SUM(NVL(B.BQTY, 0)) AS BQTY 		\n");
         sb.append("	    FROM TORDERSTOCK A, 					\n");
         sb.append("	         TSTOCK B, 							\n");
         sb.append("	         TGOODS C, 							\n");
         sb.append("	         TGOODSDT D 						\n");
         sb.append("	   WHERE A.GOODS_CODE   = B.GOODS_CODE 		\n");
         sb.append("	     AND A.GOODSDT_CODE = B.GOODSDT_CODE 	\n");
         sb.append("	     AND A.WH_CODE      = B.WH_CODE 		\n");
         sb.append("	     AND C.GOODS_CODE   = D.GOODS_CODE 		\n");
         sb.append("	     AND A.GOODS_CODE   = C.GOODS_CODE 		\n");
         sb.append("	     AND A.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
         sb.append("	     AND B.GOODS_CODE   = C.GOODS_CODE 		\n");
         sb.append("	     AND B.GOODSDT_CODE = D.GOODSDT_CODE 	\n");
         sb.append("	     AND A.WH_CODE      = '"+retrieve.getString("WH_CODE")+"' 			\n");
         sb.append("	     AND C.ENTP_CODE    = '"+retrieve.getString("ENTP_CODE")+"' 		\n");
         sb.append("	     AND A.GOODS_CODE	= '"+retrieve.getString("GOODS_CODE")+"' 		\n");
         sb.append("	     AND A.GOODSDT_CODE	= '"+retrieve.getString("GOODSDT_CODE")+"' 		\n");
         sb.append(retrieve.getString("MOD_QUERY"));
         sb.append("	  GROUP BY A.GOODS_CODE, 					\n");
         sb.append("	           A.GOODSDT_CODE, 					\n");
         sb.append("	           C.GOODS_NAME, 					\n");
         sb.append("	           D.GOODSDT_INFO 					\n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 입고수량
      * Date : 2009/05/07
      * Registrator : 정재훈
      * </PRE>
      * @return  Query
      */
     public String makeSqlGetGoodsPrice(RetrieveModel retrieve) throws StoreException{

    	 StringBuffer sb = new StringBuffer();

         sb.append("SELECT FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE, '1') AS SLIP_IN_PRICE   \n");
         sb.append("     , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE, '2') AS RATE 			 \n");
         sb.append("     , FUN_GET_GOODS_PRICE(A.GOODS_CODE,SYSDATE,'10') AS TAX_RATE	     \n");
         sb.append("     , B.GOODSDT_CODE													 \n");
         sb.append("     , B.GOODSDT_INFO													 \n");
		 sb.append("  FROM TGOODS   A														 \n");
         sb.append("     , TGOODSDT B														 \n");
         sb.append(" WHERE A.GOODS_CODE   = B.GOODS_CODE									 \n");
         sb.append("   AND A.WH_CODE      = '" + retrieve.getString("WH_CODE")      +"'		 \n");
         sb.append("   AND A.ENTP_CODE    = '" + retrieve.getString("ENTP_CODE")    +"'		 \n");
         sb.append("   AND B.GOODSDT_CODE = '" + retrieve.getString("GOODSDT_CODE") +"'		 \n");
         sb.append("   AND A.GOODS_CODE   = '" + retrieve.getString("GOODS_CODE")   +"'		 \n");
         sb.append("   AND A.SIGN_GB      = '80'											 \n");
         sb.append("   AND A.BUY_MED      = '" + retrieve.getString("BUY_MED")      +"'		 \n");
         sb.append("   AND A.SET_YN       = '0'												 \n");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : Make SQL ( 상품명 조회 )
      * </PRE>
      * @return  String
      */
     private String makeSqlStockGoodsName() {

         StringBuffer sb = new StringBuffer();

         sb.append("  SELECT A.GOODS_NAME, A.SALE_GB  		\n ");
         sb.append("    FROM TGOODS A, TSTOCK B 			\n ");
         sb.append("   WHERE A.GOODS_CODE = B.GOODS_CODE(+) \n ");
         sb.append("     AND B.GOODS_CODE = ? 				\n ");

         return sb.toString();
     }

     /**
      * <PRE>
      * Desc : 상품코드(단품까지, 실재고 > 반품요청수량)
      * </PRE>
      * @return  Query
      */
      public String makeSqlGoodsCodeDS(RetrieveModel retrieve) throws StoreException{
          StringBuffer sb = new StringBuffer();
          sb.append("SELECT /*+RULE*/  \n");
          sb.append("       A.GOODS_CODE,  \n");
          sb.append("       B.GOODSDT_CODE,  \n");
          sb.append("       A.GOODS_NAME,  \n");
          sb.append("       B.GOODSDT_INFO,  \n");
          sb.append("       C.AQTY,  \n");
          sb.append("       C.BQTY,  \n");
          sb.append("       C.EOUT_QUEST_AQTY,  \n");
          sb.append("       C.EOUT_QUEST_BQTY,  \n");
          sb.append("       A.STOCK_CHK_PLACE  \n");
          sb.append("  FROM TGOODS A,  \n");
          sb.append("       TGOODSDT B,  \n");
          sb.append("       TSTOCK C  \n");
          sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE   \n");
          sb.append("   AND A.GOODS_CODE = C.GOODS_CODE   \n");
          sb.append("   AND B.GOODSDT_CODE = C.GOODSDT_CODE   \n");
          sb.append("   AND (C.AQTY   > C.EOUT_QUEST_AQTY   \n");
          sb.append("    OR C.BQTY   > C.EOUT_QUEST_BQTY )  \n");
          sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GOODS_CODE")+"'||'%' \n ");
          sb.append(retrieve.getString("MOD_QUERY"));
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : TPLANCLASS ( 기획전그룹코드,사용여부)
       * </PRE>
       * @return  Query
       */
       public String makeSqlPlanclass(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append("SELECT /*+RULE*/  \n");
           sb.append("		 A.PLANCLASS_CODE,  \n");
           sb.append("       A.PLANCLASS_NAME,   \n");
           sb.append("       A.USE_YN   \n");
           sb.append("  FROM TPLANCLASS A  \n");
           sb.append(" WHERE A.PLANCLASS_CODE LIKE '"+retrieve.getString("PLANCLASS_CODE")+"'||'%' \n ");
           sb.append(retrieve.getString("MOD_QUERY"));
           return sb.toString();
       }

       /**
        * <PRE>
        * Desc : TPLANCLASS ( 기획전그룹코드,사용여부)
        * </PRE>
        * @return  Query
        */
        public String makeSqlEntpManSeq(RetrieveModel retrieve) throws StoreException{
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT /*+RULE*/  \n");
            sb.append("		  A.ENTP_CODE, \n");
            sb.append("       A.ENTP_NAME, \n");
            sb.append("       A.S_IDNO, \n");
            sb.append("       A.OWNER_NAME, \n");
            sb.append("       ( SELECT D.ENTP_MAN_SEQ  \n");
            sb.append("           FROM TENTPUSER D \n");
            sb.append("          WHERE D.ENTP_CODE = A.ENTP_CODE  \n");
            sb.append("       	   AND D.DEFAULT_YN = '1' \n");
            sb.append("       	   AND D.USE_YN = '1' \n");
            sb.append("       	   AND ROWNUM = 1)  AS ENTP_MAN_SEQ  \n");
            sb.append("  FROM TENTERPRISE A \n");
            sb.append(" WHERE A.ENTP_CODE LIKE '"+retrieve.getString("ENTP_CODE")+"'||'%' \n ");
            sb.append(retrieve.getString("MOD_QUERY"));
            sb.append(" ORDER BY A.ENTP_CODE ASC \n");
            return sb.toString();
        }

      /**
       * <PRE>
       * Desc : Make SQL ( 템플릿코드 조회 )
       * </PRE>
       * @return  String
       */
      private String makeSqlTemplateCode() {
          StringBuffer sb = new StringBuffer();

          sb.append(" SELECT /*+RULE*/                     \n");
          sb.append("        A.CODE_NAME                   \n");
          sb.append("  FROM TCODE A                        \n");
          sb.append(" WHERE A.CODE_LGROUP = 'B117'         \n");
          sb.append("   AND A.USE_YN = '1'                 \n");
          sb.append("   AND A.CODE_MGROUP = ?              \n");

          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Make SQL ( 기획전그룹코드 조회 )
       * </PRE>
       * @return  String
       */
      private String makeSqlPlanclassCode(){
          StringBuffer sb = new StringBuffer();
           sb.append(" SELECT A.PLANCLASS_NAME     \n");
           sb.append("  FROM TPLANCLASS A          \n");
           sb.append(" WHERE A.PLANCLASS_CODE = ?  \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Make SQL ( 기획전코드 조회 )
       * </PRE>
       * @return  String
       */
      private String makeSqlPlanCode(){
          StringBuffer sb = new StringBuffer();
           sb.append(" SELECT A.PLAN_NAME     \n");
           sb.append("  FROM TPLAN A          \n");
           sb.append(" WHERE A.PLAN_CODE = ?  \n");
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 재고조정 적재수량
       * Date : 2009/06/02
       * Registrator : 조민하
       * </PRE>
       * @return  Query
       */
      public String makeSqlStockQty(RetrieveModel retrieve) throws StoreException{
          StringBuffer sb = new StringBuffer();
          if (retrieve.getString("GRADE").equals("1")){
              sb.append("SELECT FUN_ORDER_OK_QTY('990',GOODS_CODE,GOODSDT_CODE,ENTP_CODE) AS STOCK_QTY \n");
              sb.append("  FROM TSCMSTOCK  \n");
              sb.append(" WHERE ENTP_CODE    = '"+retrieve.getString("ENTP_CODE")+"'    \n");
              sb.append("   AND GOODS_CODE   = '"+retrieve.getString("GOODS_CODE")+"'   \n");
              sb.append("   AND GOODSDT_CODE = '"+retrieve.getString("GOODSDT_CODE")+"' \n");
              sb.append(retrieve.getString("MOD_QUERY"));
          } else {
         	 sb.append("SELECT BQTY AS STOCK_QTY \n");
              sb.append("  FROM TSCMSTOCK  \n");
              sb.append(" WHERE ENTP_CODE    = '"+retrieve.getString("ENTP_CODE")+"'    \n");
              sb.append("   AND GOODS_CODE   = '"+retrieve.getString("GOODS_CODE")+"'   \n");
              sb.append("   AND GOODSDT_CODE = '"+retrieve.getString("GOODSDT_CODE")+"' \n");
              sb.append(retrieve.getString("MOD_QUERY"));
          }
          return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 보류/공제코드(A002)
       * Date : 2010/12/31
       * Registrator : 강희훈
       * </PRE>
       * @return  Query
       */
      public String makeSqlHoDeCode() throws StoreException{
          StringBuffer sb = new StringBuffer();

           sb.append("  SELECT CODE_NAME 		      	\n");
           sb.append("    FROM TCODE                   	\n");
           sb.append("   WHERE CODE_LGROUP = 'A002'     \n");
           sb.append("     AND CODE_GROUP = '2' /*수기*/ \n");
           sb.append("     AND CODE_MGROUP = ?     		\n");

           return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 모델조회
       * Date : 2011/02/01
       * Registrator : 황인철
       * </PRE>
       * @return  Query
       */
      public String makeSqlModelCodeName() throws StoreException{
          StringBuffer sb = new StringBuffer();

			sb.append(" SELECT MODEL_NAME    		\n");
			sb.append(" FROM   TMODELMASTER         \n");
			sb.append("	WHERE  USE_YN = '1'  		\n");
			sb.append("	AND   MODEL_NO = ?	    	\n");

           return sb.toString();
      }

      /**
       * <PRE>
       * Desc : Zone조회
       * Date : 2011/02/11
       * Registrator : 황인철
       * </PRE>
       * @return  Query
       */
      public String makeSqlAreaName() throws StoreException{
          StringBuffer sb = new StringBuffer();

			sb.append(" SELECT AREA_NAME   		\n");
			sb.append(" FROM   TAREA_ZONE       \n");
			sb.append("	WHERE  AREA_GB  = ? 	\n");

           return sb.toString();
      }

      /**
       * <PRE>
       * Desc : 사용구분이 사용일 경우만 방송테입코드 SELECT
       * </PRE>
       * @return  Query
       */
       public String makeSqlTapeCode(RetrieveModel retrieve) throws StoreException{
           StringBuffer sb = new StringBuffer();
           sb.append("  SELECT  A.TAPE_CODE,                                     \n");
           sb.append("          A.TAPE_NAME,                                     \n");
           sb.append("          D.GOODS_CODE,                                    \n");
           sb.append("          D.GOODS_NAME,                                    \n");
           sb.append("          A.TAPE_NO,                                       \n");
           sb.append("          A.TAPE_BARCODE,                                  \n");
           sb.append("          A.TAPE_TYPE,                                     \n");
           sb.append("          B.CODE_NAME AS  TAPE_TYPE_NAME,                  \n");
           sb.append("          TO_CHAR(A.MAKE_DATE, 'YYYY/MM/DD') MAKE_DATE,    \n");
           sb.append("          DECODE(A.SNO1,'', A.SNO, A.SNO||','||A.SNO1) PD,    \n");
           sb.append("          SUBSTR(A.RUNTIME_FR,0,2)||':'||SUBSTR(A.RUNTIME_FR,3,2)||':'||SUBSTR(A.RUNTIME_FR,5,2)||':'||SUBSTR(A.RUNTIME_FR,7,3) AS RUNTIME_FR,     \n");
           sb.append("          SUBSTR(A.RUNTIME_TO,0,2)||':'||SUBSTR(A.RUNTIME_TO,3,2)||':'||SUBSTR(A.RUNTIME_TO,5,2)||':'||SUBSTR(A.RUNTIME_TO,7,3) AS RUNTIME_TO,     \n");
           sb.append("          A.REVIEW_TYPE,                                   \n");
           sb.append("          A.TAPE_DESC,                                     \n");
           sb.append("          A.REVIEW_DESC,                                   \n");
           sb.append("          FUN_PGM_SHOWHOST_NAME(A.TAPE_CODE) AS SHOWHOST,      \n");
           sb.append("          A.CAMERA_TEAM                                    \n");
           sb.append("     FROM TPGMTAPE A,                                      \n");
           sb.append("     		  TCODE B,   \n");
           sb.append("          TPGMTAPEGOODS C,   \n");
           sb.append("          TGOODS D 	                                     \n");
           sb.append("    WHERE A.USE_CODE = '00'                                \n");
           sb.append("      AND A.TAPE_CODE = C.TAPE_CODE   \n");
           sb.append("      AND C.GOODS_CODE = D.GOODS_CODE   \n");
           sb.append("      AND C.SPECIAL_YN = '1'   \n");
           sb.append("      AND B.CODE_LGROUP = 'B501'                	         \n");
           sb.append("    	 AND A.TAPE_TYPE = B.CODE_MGROUP        \n");
           sb.append("     AND A.TAPE_CODE = '"+retrieve.getString("TAPE_CODE")+"' \n ");
           sb.append(retrieve.getString("MOD_QUERY"));
           sb.append("   ORDER BY  A.TAPE_CODE                           \n");
           return sb.toString();
       }

   /**
    * 프로그램선택조회
    * @param retrieve
    * @return
    * @throws StoreException
    */
   public String makeSqlBdProgramSelect(RetrieveModel retrieve) throws StoreException{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT TO_CHAR(A.BD_DATE,'YYYY/MM/DD') AS BD_DATE,                                 \n");
		sb.append("        A.PROG_CODE,                                                                \n");
		sb.append("        B.PROG_NAME,                                                                \n");
		sb.append("        TO_CHAR(A.BD_BTIME,'YYYY/MM/DD HH24:MI:SS') AS BD_BTIME_ORG,                \n");
		sb.append("        TO_CHAR(A.BD_ETIME,'YYYY/MM/DD HH24:MI:SS') AS BD_ETIME_ORG,                \n");
		sb.append("        TO_CHAR(A.BD_BTIME,'HH24:MI') AS BD_BTIME,                                  \n");
		sb.append("        TO_CHAR(A.BD_ETIME,'HH24:MI') AS BD_ETIME,                                  \n");
		sb.append("        A.MAIN_PD,                                                                  \n");
		sb.append("        A.SUB_PD,                                                                   \n");
		sb.append("        C.USER_NAME AS MAIN_PD_NAME,                                                \n");
		sb.append("        D.USER_NAME AS SUB_PD_NAME,                                                 \n");
		sb.append("        FUN_STAFF_NAME('06',A.SEQ_FRAME_NO)  SHOWHOST,                              \n");
		sb.append("        A.PLAN_AMT,                                                                 \n");
		sb.append("        A.TAPE_CODE,                                                                \n");
		sb.append("        E.GOODS_CODE,                                                               \n");
		sb.append("        F.GOODS_NAME ,                                                              \n");
		sb.append("        A.SEQ_FRAME_NO,                                                             \n");
		sb.append("        A.MEDIA_CODE,                                                               \n");
		sb.append("        G.MEDIA_NAME                                                                \n");
		sb.append("   FROM TMULTIFRAMESCHE A,                                                          \n");
		sb.append("        TPROGRAM        B,                                                          \n");
		sb.append("        TUSER           C,                                                          \n");
		sb.append("        TUSER           D,                                                          \n");
		sb.append("        TMULTIDTBROAD   E,                                                          \n");
		sb.append("        TGOODS          F,                                                          \n");
		sb.append("        TMEDIA          G                                                           \n");
		sb.append("  WHERE A.PROG_CODE     = B.PROG_CODE                                               \n");
		sb.append("    AND A.MEDIA_CODE    = G.MEDIA_CODE                                              \n");
		sb.append("    AND A.SEQ_FRAME_NO  = E.SEQ_FRAME_NO(+)                                         \n");
		sb.append("    AND E.GOODS_CODE    = F.GOODS_CODE(+)                                           \n");
		sb.append("    AND A.MAIN_PD       = C.USER_ID(+)                                              \n");
		sb.append("    AND A.SUB_PD        = D.USER_ID(+)                                              \n");
		sb.append("    AND E.MAIN_YN (+) = '1'                                              \n");
		sb.append("    AND A.SEQ_FRAME_NO  = '"+retrieve.getString("SEQ_FRAME_NO")+"' \n");
		sb.append("    AND A.LIVE_FLAG    IN ('10','11')                                               \n");
		sb.append(retrieve.getString("MOD_QUERY"));
		sb.append("  ORDER BY A.BD_BTIME , A.PROG_CODE DESC                                            \n");
		return sb.toString();
    }

   /**
	* 모델조회
	* @param retrieve
	* @return
	* @throws StoreException
	*/
	private String makeSqlModelCodeName(RetrieveModel retrieve) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT MM.MODEL_NO,                                						  \n");
		sb.append("	 MM.MODEL_NAME,                                    						  \n");
		sb.append("	 MM.ENTP_CODE,                                     						  \n");
		sb.append("	 EP.ENTP_NAME,                                     						  \n");
		sb.append("	 TCODE_NAME('B023',COUNTRY_CODE) AS COUNTRY_NAME   						  \n");
		sb.append(" FROM   TMODELMASTER  MM,                           						  \n");
		sb.append("        TENTERPRISE   EP                            						  \n");
		sb.append(" WHERE MM.ENTP_CODE = EP.ENTP_CODE                  						  \n");
		sb.append("  AND  MM.USE_YN = '1'			                						  \n");
		sb.append("	 AND  MM.MODEL_NO = '"+retrieve.getString("MODEL_NO")+"'     \n");
		sb.append(retrieve.getString("MOD_QUERY"));
		return sb.toString();
	}
	/**
	* 상품명과 가격 조회
	* @param retrieve
	* @return
	* @throws StoreException
	*/
	private String makeSqlGiftNamePrice(RetrieveModel retrieve) {
		StringBuffer sb = new StringBuffer();

		sb.append("SELECT A.GOODS_CODE, \n");
        sb.append("       A.GOODS_NAME, \n");
        sb.append("       B.BUY_PRICE 		\n");
        sb.append("  FROM TGOODS A, TGOODSPRICE B \n");
        sb.append(" WHERE A.GOODS_CODE = B.GOODS_CODE \n");
        sb.append("   AND A.GOODS_CODE LIKE '"+retrieve.getString("GIFT_GOODS_CODE")+"'||'%' \n");
        sb.append("   AND A.SALE_GB = '00' \n");
        sb.append("   AND A.SIGN_GB = '80' \n");
        sb.append("   AND A.SQC_GB LIKE '1%' \n");
        sb.append("   AND (A.GIFT_YN = '1'  \n");
        sb.append("   OR A.SAMPLE_YN = '1')  \n");
        sb.append("   AND A.SET_GOODS_YN = '0' \n");
        sb.append(retrieve.getString("MOD_QUERY"));
        return sb.toString();
	}
}
