
package com.cware.back.action.common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.ComUtils;
import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.Message;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tcardlog;
import com.cware.back.service.common.CommonDBSvc;

/**
 * Common DB util Action class
 *
 * @version 1.0, 2006/07/19
 * @author commerceware.co.kr
 */
public class CommonDBAct {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public CommonDBAct()   { }

    /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message getReturnDelyGb(Message msg) throws StoreException {

        Connection con  = null;

        try{
            msg.put("ERROR_MESSAGE", "000000");
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            msg = getReturnDelyGb(con, msg, msg.getString("goods_code"), msg.getString("post_no"), msg.getString("post_seq"));

        }catch(StoreException se){
            msg.put("ERROR_MESSAGE", se.getMessage());
        }catch(Exception e){
            msg.put("ERROR_MESSAGE", e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }

        return msg;

    }

    /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Connection
    * @param   Message
    * @param   String
    * @param   String
    * @param   String
    * @return  Message
    */
    public Message getReturnDelyGb(Connection con, Message msg, String goods_code, String post_no, String post_seq) throws StoreException {

        String  area_gb      = "";
        String  dely_no_flag = "0";
        String  send_sp_note = "";

        try{

            CommonDBSvc svc = new CommonDBSvc();

            area_gb    = svc.retrieveAreaGb(con, post_no, post_seq);

            dely_no_flag = svc.retrieveDelyNoFlag(con, goods_code, area_gb);

            if(dely_no_flag.equals("")) dely_no_flag = "0";

            send_sp_note = "";

            if(dely_no_flag.equals("1")){
                msg.put("msg", ComUtils.getMessage("msg.dely_out_area"));
            }else if(!dely_no_flag.equals("1")){
                if(!send_sp_note.equals("")){
                    msg.put("msg", ComUtils.getMessage("msg.dely_unusual")+" \n "+send_sp_note);
                }
            }


            msg.put("CommonDBAct.dely_no_flag" , dely_no_flag );
            msg.put("CommonDBAct.dely_gb"      , "11"         );
            msg.put("CommonDBAct.send_sp_note" , ""           );

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return msg;
    }

        /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message getExchDelyGb(Message msg) throws StoreException {

        Connection con  = null;

        try{
            msg.put("ERROR_MESSAGE", "000000");
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            msg = getExchDelyGb(con, msg, msg.getString("goods_code"), msg.getString("post_no"), msg.getString("post_seq"));

        }catch(StoreException se){
            msg.put("ERROR_MESSAGE", se.getMessage());
        }catch(Exception e){
            msg.put("ERROR_MESSAGE", e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }

        return msg;

    }

    /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Connection
    * @param   Message
    * @param   String
    * @param   String
    * @param   String
    * @return  Message
    */
    public Message getExchDelyGb(Connection con, Message msg, String goods_code, String post_no, String post_seq) throws StoreException {

        String  area_gb      = "";
        String  dely_no_flag = "0";
        String  send_sp_note = "";

        try{

            CommonDBSvc svc = new CommonDBSvc();

            area_gb    = svc.retrieveAreaGb(con, post_no, post_seq);

            dely_no_flag = svc.retrieveDelyNoFlag(con, goods_code, area_gb);

            if(dely_no_flag.equals("")) dely_no_flag = "0";

            send_sp_note = "";

            if(dely_no_flag.equals("1")){
                msg.put("msg", ComUtils.getMessage("msg.dely_out_area"));
            }else if(!dely_no_flag.equals("1")){
                if(!send_sp_note.equals("")){
                    msg.put("msg", ComUtils.getMessage("msg.dely_unusual")+" \n "+send_sp_note);
                }
            }


            msg.put("CommonDBAct.dely_no_flag" , dely_no_flag );
            msg.put("CommonDBAct.dely_gb"      , "11"         );
            msg.put("CommonDBAct.send_sp_note" , ""           );

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return msg;
    }
    /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message getDelyGb(Message msg) throws StoreException {

        Connection con  = null;

        try{
            msg.put("ERROR_MESSAGE", "000000");
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            msg = getDelyGb(con, msg, msg.getString("goods_code"), msg.getString("post_no"), msg.getString("post_seq"));

        }catch(StoreException se){
            msg.put("ERROR_MESSAGE", se.getMessage());
        }catch(Exception e){
            msg.put("ERROR_MESSAGE", e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }

        return msg;

    }

    /**
    * <PRE>
    * Desc : 배송구분을 Select
    * reference : Netshopping8 gft_get_dely_gb()
    * </PRE>
    * @param   Connection
    * @param   Message
    * @param   String
    * @param   String
    * @param   String
    * @return  Message
    */
    public Message getDelyGb(Connection con, Message msg, String goods_code, String post_no, String post_seq) throws StoreException {

        String  area_gb      = "";
        String  dely_no_flag = "0";
        String  send_sp_note = "";

        try{

            CommonDBSvc svc = new CommonDBSvc();

            area_gb    = svc.retrieveAreaGb(con, post_no, post_seq);

            dely_no_flag = svc.retrieveDelyNoFlag(con, goods_code, area_gb);

            if(dely_no_flag.equals("")) dely_no_flag = "0";

            send_sp_note = "";

            if(dely_no_flag.equals("1")){
                msg.put("msg", ComUtils.getMessage("msg.dely_out_area"));
            }else if(!dely_no_flag.equals("1")){
                if(!send_sp_note.equals("")){
                    msg.put("msg", ComUtils.getMessage("msg.dely_unusual")+" \n "+send_sp_note);
                }
            }


            msg.put("CommonDBAct.dely_no_flag" , dely_no_flag );
            msg.put("CommonDBAct.dely_gb"      , "11"         );
            msg.put("CommonDBAct.send_sp_note" , ""           );

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return msg;
    }

    /**
    * <PRE>
    * Desc : 주민번호 중복 검사
    * reference : Netshopping8 g_resi_chk()
    * </PRE>
    * @param   String
    * @return  Message
    */
    public int checkDupResidentNo(Connection con, String resident_no ) throws StoreException {

        int         executedRtn = 0;

        try{

            CommonDBSvc svc = new CommonDBSvc();

            executedRtn = svc.retrieveResidentNo(con, resident_no);

            if (executedRtn < 1){
                executedRtn = 1;
            } else {
                executedRtn = -1;
            }

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return executedRtn;
    }

    public int deleteTcounsel(String order_no) throws StoreException {
        Connection con = null;
        return deleteTcounsel(con, order_no, "", "", "");
    }

    public int deleteTcounsel(String order_no, String order_g_seq) throws StoreException {
        Connection con = null;
        return deleteTcounsel(con, order_no, order_g_seq, "", "");
    }

    public int deleteTcounsel(String order_no, String order_g_seq, String order_d_seq, String order_w_seq) throws StoreException {
        Connection con = null;
        return deleteTcounsel(con, order_no, order_g_seq, order_d_seq, order_w_seq);
    }

    public int deleteTcounsel(Connection con, String order_no) throws StoreException {
        return deleteTcounsel(con, order_no, "", "", "");
    }

    public int deleteTcounsel(Connection con, String order_no, String order_g_seq) throws StoreException {
        return deleteTcounsel(con, order_no, order_g_seq, "", "");
    }

    /**
    * <PRE>
    * Desc : 주문 상품 상담 삭제
    * reference : Netshopping8 gf_order_stock_delete_multi()
    * </PRE>
    * @param   Connection
    * @param   String
    * @param   String
    * @param   String
    * @param   String
    * @return  int
    */
    public int deleteTcounsel(Connection con, String order_no, String order_g_seq, String order_d_seq, String order_w_seq) throws StoreException {

        int executedRtn = -1;

        if(ComUtils.NVL(order_no,"").equals("")) return -1;

        if(ComUtils.NVL(order_g_seq,"").equals("")) order_g_seq = "%";

        if(ComUtils.NVL(order_d_seq,"").equals("")) order_d_seq = "%";

        if(ComUtils.NVL(order_w_seq,"").equals("")) order_w_seq = "%";

        if(con == null){

            try{

                con = DBUtils.getConnection(Construct.DB_POOL_NAME);

                CommonDBSvc svc = new CommonDBSvc();

                executedRtn = svc.deleteTcounsel(con, order_no, order_g_seq, order_d_seq, order_w_seq);

                if (executedRtn < 0){
                    executedRtn = -1;
                }
                con.commit();
            }catch(StoreException se){
                if ( con != null ) try{con.rollback();}catch(Exception sec){}
                executedRtn = -1;
            }catch(Exception e){
                if ( con != null ) try{con.rollback();}catch(Exception sec){}
                executedRtn = -1;
            }finally {
                DBUtils.freeConnection(con);
            }

        }else{

            try{

                CommonDBSvc svc = new CommonDBSvc();

                executedRtn = svc.deleteTcounsel(con, order_no, order_g_seq, order_d_seq, order_w_seq);

                if (executedRtn < 0){
                    executedRtn = -1;
                }

            }catch(StoreException se){
                executedRtn = -1;
            }catch(Exception e){
                executedRtn = -1;
            }

        }
        return executedRtn;
    }

    public int deleteTpromocounsel(String order_no) throws StoreException {
        Connection con = null;
        return deleteTpromocounsel(con, order_no, "");
    }

    public int deleteTpromocounsel(String order_no, String order_g_seq) throws StoreException {
        Connection con = null;
        return deleteTpromocounsel(con, order_no, order_g_seq);
    }

    public int deleteTpromocounsel(Connection con, String order_no) throws StoreException {
        return deleteTpromocounsel(con, order_no, "");
    }

    /**
    * <PRE>
    * Desc : 한정 프로모션 상담 삭제
    * reference : Netshopping8 gf_promo_delete()
    * </PRE>
    * @param   Connection
    * @param   String
    * @param   String
    * @return  int
    */
    public int deleteTpromocounsel(Connection con, String order_no, String order_g_seq) throws StoreException {

        int executedRtn = -1;

        if(ComUtils.NVL(order_no,"").equals("")) return -1;

        if(ComUtils.NVL(order_g_seq,"").equals("")) order_g_seq = "%";

        if(con == null){

            try{

                con = DBUtils.getConnection(Construct.DB_POOL_NAME);

                CommonDBSvc svc = new CommonDBSvc();

                executedRtn = svc.deleteTpromocounsel(con, order_no, order_g_seq);

                if (executedRtn < 0){
                    executedRtn = -1;
                }
                con.commit();
            }catch(StoreException se){
                if ( con != null ) try{con.rollback();}catch(Exception sec){}
                executedRtn = -1;
            }catch(Exception e){
                if ( con != null ) try{con.rollback();}catch(Exception sec){}
                executedRtn = -1;
            }finally {
                DBUtils.freeConnection(con);
            }

        }else{

            try{

                CommonDBSvc svc = new CommonDBSvc();

                executedRtn = svc.deleteTpromocounsel(con, order_no, order_g_seq);

                if (executedRtn < 0){
                    executedRtn = -1;
                }
                con.commit();
            }catch(StoreException se){
                executedRtn = -1;
            }catch(Exception e){
                executedRtn = -1;
            }

        }
        return executedRtn;
    }


    /**
    * <PRE>
    * Desc : card_code 에 따른 할부 개월수 세팅
    *        format : 000000000000000000000000000000000000
    * reference : Netshopping8 gf_promo_delete()
    * </PRE>
    * @param   Connection
    * @param   String
    * @param   String
    * @return  int
    */
    public String retrieveInsMonth(String card_code) throws StoreException {
        String     rtnAllotMonth = null;
        Connection con           = null;
        ArrayList  rtnAl         = null;
        String     month         = null;
        int        checkPoint    = 0;
        try{

            month = ComUtils.rpad("1",36,"0");

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);

            CommonDBSvc svc = new CommonDBSvc();

            rtnAl = svc.retrieveInsMonth(con, card_code);

            for(int i = 0 ; i < rtnAl.size() ; i++){
                checkPoint = Integer.parseInt(rtnAl.get(i).toString());
                month = month.substring(0,checkPoint-1) + "1" + month.substring(checkPoint);
            }

        }catch(StoreException se){
            month = "";
        }catch(Exception e){
            month = "";
        }finally {
            DBUtils.freeConnection(con);
        }
        return month;
    }

    /**
    * <PRE>
    * Desc : Select dc rate of user
    * reference : Netshopping8 G_DC_RATE_SEL
    * </PRE>
    * @param   Connection
    * @param   String
    * @return  double
    */
    public double retrieveMaxDcRate(Connection con, String user_id) throws StoreException {
        double     max_dc_rate = 0;
        try{

            CommonDBSvc svc = new CommonDBSvc();

            max_dc_rate = svc.retrieveMaxDcRate(con, user_id);

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return max_dc_rate;
    }

    /**
    * <PRE>
    * Desc : MultiName MediaName
    * reference : Netshopping8 G_DC_RATE_SEL
    * </PRE>
    * @param   Connection
    * @param   String
    * @return  double
    */
    public Message getMultiName(Connection con, Message msg) throws StoreException {
        try{

            CommonDBSvc svc = new CommonDBSvc();

            if(msg.getString("MULTINAME_TYPE").equals("G_MEDIANAME")){
                msg = svc.retrieveMultiNameMediaName(con, msg);
            }

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return msg;
    }

    /**
    * <PRE>
    * Desc : TCUSTSYSTEM INFORMATION
    * reference : Netshopping8 gf_custsys_sheet10
    * </PRE>
    * @param   Connection
    * @param   String
    * @return  HashMap
    */
    public HashMap getCustSysSheet10(Connection con, String cust_no) throws StoreException {
        HashMap htSheet = null;
        try{

            CommonDBSvc svc = new CommonDBSvc();

            htSheet = svc.retrieveCustSysSheet10(htSheet, con, cust_no);

        }catch(StoreException se){
            throw new StoreException(se.getMessage());
        }catch(Exception e){
            throw new StoreException(e.getMessage());
        }
        return htSheet;
    }

    /**
     * <PRE>
     * Desc : Get SequenceNo
     * </PRE>
     * @param   String
     * @return  String
     */
    public String getSequenceNo(String type) throws StoreException {
        Connection con  = null;
        String seqNo    = "";
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            seqNo = DBUtils.getSequenceNo(con, type);

        }catch(StoreException se){
        }catch(Exception e){
        }finally {
            DBUtils.freeConnection(con);
        }

        return seqNo;
    }

    public long saveTcardlog(Tcardlog tcardlog) throws StoreException {
        Connection  con         = null;
        CommonDBSvc svc         = null;
        long        card_log_no = 0;
        try{
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);
            svc = new CommonDBSvc();
            card_log_no = svc.retrieveCardLogNo(con);
            tcardlog.setCard_log_no(card_log_no);
            svc.insertTcardlog(con, tcardlog);
            con.commit();
        }catch(StoreException se){
        }catch(Exception e){
        }finally {
            DBUtils.freeConnection(con);
        }
        return card_log_no;
    }

}
