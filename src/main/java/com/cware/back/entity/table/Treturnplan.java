package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 하차예정정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Treturnplan extends BaseEntity {

    public Treturnplan(){ super();}

    private String return_no;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String wh_code;
    private String dely_gb;
    private String return_yn;
    private String return_end_yn;
    private String nreturn_code;
    private String return_plan_slip;
    private String return_plan_date;
    private String file_name;
    private String data_date;
    private String data_proc_id;
    private String file_yn;
    private String downcar_carno;
    private String downcar_date;
    private String downcar_yn;
    private String end_yn;
    private String end_date;
    private String end_id;
    private String service_proc_yn;
    private String proc_code;
    private String proc_date1;
    private String proc_date2;
    private String proc_note1;
    private String proc_note2;
    private String re_withdraw_yn;

    /** Set Method **/
    public void setReturn_no( String return_no ){ this.return_no = return_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setDely_gb( String dely_gb ){ this.dely_gb = dely_gb; }
    public void setReturn_yn( String return_yn ){ this.return_yn = return_yn; }
    public void setReturn_end_yn( String return_end_yn ){ this.return_end_yn = return_end_yn; }
    public void setNreturn_code( String nreturn_code ){ this.nreturn_code = nreturn_code; }
    public void setReturn_plan_slip( String return_plan_slip ){ this.return_plan_slip = return_plan_slip; }
    public void setReturn_plan_date( String return_plan_date ){ this.return_plan_date = return_plan_date; }
    public void setFile_name( String file_name ){ this.file_name = file_name; }
    public void setData_date( String data_date ){ this.data_date = data_date; }
    public void setData_proc_id( String data_proc_id ){ this.data_proc_id = data_proc_id; }
    public void setFile_yn( String file_yn ){ this.file_yn = file_yn; }
    public void setDowncar_carno( String downcar_carno ){ this.downcar_carno = downcar_carno; }
    public void setDowncar_date( String downcar_date ){ this.downcar_date = downcar_date; }
    public void setDowncar_yn( String downcar_yn ){ this.downcar_yn = downcar_yn; }
    public void setEnd_yn( String end_yn ){ this.end_yn = end_yn; }
    public void setEnd_date( String end_date ){ this.end_date = end_date; }
    public void setEnd_id( String end_id ){ this.end_id = end_id; }
    public void setService_proc_yn( String service_proc_yn ){ this.service_proc_yn = service_proc_yn; }
    public void setProc_code( String proc_code ){ this.proc_code = proc_code; }
    public void setProc_date1( String proc_date1 ){ this.proc_date1 = proc_date1; }
    public void setProc_date2( String proc_date2 ){ this.proc_date2 = proc_date2; }
    public void setProc_note1( String proc_note1 ){ this.proc_note1 = proc_note1; }
    public void setProc_note2( String proc_note2 ){ this.proc_note2 = proc_note2; }
    public void setRe_withdraw_yn( String re_withdraw_yn ){ this.re_withdraw_yn = re_withdraw_yn; }

    /** Get Method **/
    public String getReturn_no(){ return return_no; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
    public String getWh_code(){ return wh_code; }
    public String getDely_gb(){ return dely_gb; }
    public String getReturn_yn(){ return return_yn; }
    public String getReturn_end_yn(){ return return_end_yn; }
    public String getNreturn_code(){ return nreturn_code; }
    public String getReturn_plan_slip(){ return return_plan_slip; }
    public String getReturn_plan_date(){ return return_plan_date; }
    public String getFile_name(){ return file_name; }
    public String getData_date(){ return data_date; }
    public String getData_proc_id(){ return data_proc_id; }
    public String getFile_yn(){ return file_yn; }
    public String getDowncar_carno(){ return downcar_carno; }
    public String getDowncar_date(){ return downcar_date; }
    public String getDowncar_yn(){ return downcar_yn; }
    public String getEnd_yn(){ return end_yn; }
    public String getEnd_date(){ return end_date; }
    public String getEnd_id(){ return end_id; }
    public String getService_proc_yn(){ return service_proc_yn; }
    public String getProc_code(){ return proc_code; }
    public String getProc_date1(){ return proc_date1; }
    public String getProc_date2(){ return proc_date2; }
    public String getProc_note1(){ return proc_note1; }
    public String getProc_note2(){ return proc_note2; }
    public String getRe_withdraw_yn(){ return re_withdraw_yn; }


} 