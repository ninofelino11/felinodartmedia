
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 발주Master
*
* @version 1.0, 2006/07/28
* @author Commerceware Ins.
*/
public class Tbaljum extends BaseEntity {

public Tbaljum(){ super();}

    private String balju_no;
    private String wh_code;
    private String entp_code;
    private String entp_man_seq;
    private long   entp_balju_seq;
    private String md_code;
    private String balju_date;
    private String balju_dely_date;
    private String balju_gb;
    private String rebalju_yn;
    private String rebalju_code;
    private String rebalju_note;
    private long   balju_prt_cnt;
    private String look_yn;
    private String look_date;
    private String look_id;
    private String ipgo_plan_date;
    private String ch_code;
    private String ch_note;
    private String ctrl_in_date;
    private String ctrl_date;
    private String ctrl_id;
    private String in_end_yn;
    private String in_plan_date;
    private String in_end_date;
    private String cancel_yn;
    private String cancel_code;
    private String cancel_note;
    private String cancel_id;
    private String cancel_date;
    private String old_balju_no;
    private long   balju_seq;
    private String note;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setBalju_no( String balju_no ){ this.balju_no = balju_no; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setEntp_man_seq( String entp_man_seq ){ this.entp_man_seq = entp_man_seq; }
    public void setEntp_balju_seq( long entp_balju_seq ){ this.entp_balju_seq = entp_balju_seq; }
    public void setMd_code( String md_code ){ this.md_code = md_code; }
    public void setBalju_date( String balju_date ){ this.balju_date = balju_date; }
    public void setBalju_dely_date( String balju_dely_date ){ this.balju_dely_date = balju_dely_date; }
    public void setBalju_gb( String balju_gb ){ this.balju_gb = balju_gb; }
    public void setRebalju_yn( String rebalju_yn ){ this.rebalju_yn = rebalju_yn; }
    public void setRebalju_code( String rebalju_code ){ this.rebalju_code = rebalju_code; }
    public void setRebalju_note( String rebalju_note ){ this.rebalju_note = rebalju_note; }
    public void setBalju_prt_cnt( long balju_prt_cnt ){ this.balju_prt_cnt = balju_prt_cnt; }
    public void setLook_yn( String look_yn ){ this.look_yn = look_yn; }
    public void setLook_date( String look_date ){ this.look_date = look_date; }
    public void setLook_id( String look_id ){ this.look_id = look_id; }
    public void setIpgo_plan_date( String ipgo_plan_date ){ this.ipgo_plan_date = ipgo_plan_date; }
    public void setCh_code( String ch_code ){ this.ch_code = ch_code; }
    public void setCh_note( String ch_note ){ this.ch_note = ch_note; }
    public void setCtrl_in_date( String ctrl_in_date ){ this.ctrl_in_date = ctrl_in_date; }
    public void setCtrl_date( String ctrl_date ){ this.ctrl_date = ctrl_date; }
    public void setCtrl_id( String ctrl_id ){ this.ctrl_id = ctrl_id; }
    public void setIn_end_yn( String in_end_yn ){ this.in_end_yn = in_end_yn; }
    public void setIn_plan_date( String in_plan_date ){ this.in_plan_date = in_plan_date; }
    public void setIn_end_date( String in_end_date ){ this.in_end_date = in_end_date; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setCancel_code( String cancel_code ){ this.cancel_code = cancel_code; }
    public void setCancel_note( String cancel_note ){ this.cancel_note = cancel_note; }
    public void setCancel_id( String cancel_id ){ this.cancel_id = cancel_id; }
    public void setCancel_date( String cancel_date ){ this.cancel_date = cancel_date; }
    public void setOld_balju_no( String old_balju_no ){ this.old_balju_no = old_balju_no; }
    public void setBalju_seq( long balju_seq ){ this.balju_seq = balju_seq; }
    public void setNote( String note ){ this.note = note; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getBalju_no(){ return balju_no; }
    public String getWh_code(){ return wh_code; }
    public String getEntp_code(){ return entp_code; }
    public String getEntp_man_seq(){ return entp_man_seq; }
    public long   getEntp_balju_seq(){ return entp_balju_seq; }
    public String getMd_code(){ return md_code; }
    public String getBalju_date(){ return balju_date; }
    public String getBalju_dely_date(){ return balju_dely_date; }
    public String getBalju_gb(){ return balju_gb; }
    public String getRebalju_yn(){ return rebalju_yn; }
    public String getRebalju_code(){ return rebalju_code; }
    public String getRebalju_note(){ return rebalju_note; }
    public long   getBalju_prt_cnt(){ return balju_prt_cnt; }
    public String getLook_yn(){ return look_yn; }
    public String getLook_date(){ return look_date; }
    public String getLook_id(){ return look_id; }
    public String getIpgo_plan_date(){ return ipgo_plan_date; }
    public String getCh_code(){ return ch_code; }
    public String getCh_note(){ return ch_note; }
    public String getCtrl_in_date(){ return ctrl_in_date; }
    public String getCtrl_date(){ return ctrl_date; }
    public String getCtrl_id(){ return ctrl_id; }
    public String getIn_end_yn(){ return in_end_yn; }
    public String getIn_plan_date(){ return in_plan_date; }
    public String getIn_end_date(){ return in_end_date; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getCancel_code(){ return cancel_code; }
    public String getCancel_note(){ return cancel_note; }
    public String getCancel_id(){ return cancel_id; }
    public String getCancel_date(){ return cancel_date; }
    public String getOld_balju_no(){ return old_balju_no; }
    public long   getBalju_seq(){ return balju_seq; }
    public String getNote(){ return note; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 