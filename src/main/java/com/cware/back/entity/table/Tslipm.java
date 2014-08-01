
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TSLIPM
*
* @version 1.0, 2006/07/26
* @author Commerceware Ins.
*/
public class Tslipm extends BaseEntity {

    public Tslipm(){ super();}

    private String slip_i_no;
    private String cust_no;
    private String receiver_seq;
    private String wh_code;
    private String slip_no;
    private String slip_flag;
    private String slip_gb;
    private String dely_type;
    private String dely_gb;
    private String msg;
    private String mixpack_flag;
    private String create_date;
    private String create_seq;
    private String slip_proc;
    private String slip_proc_date;
    private String slip_proc_id;
    private String dely_hope_date;
    private String dely_hope_yn;
    private String dely_hope_time;
    private String redely_yn;
    private String out_close_yn;
    private String out_close_date;
    private String msg_note;
    private String happy_card_yn;
    private String pack_yn;
    private String anony_yn;
    private String pre_dely_yn;
    private String real_receiver;
    private String real_dely_date;
    private String receipt_yn;
    private String real_out_date;
    private String remark1_v;
    private String remark2_v;
    private String remark3_n;
    private Double ship_fee;
    private String ship_fee_date;

    /** Set Method **/
    public void setSlip_i_no( String slip_i_no ){ this.slip_i_no = slip_i_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setReceiver_seq( String receiver_seq ){ this.receiver_seq = receiver_seq; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setSlip_no( String slip_no ){ this.slip_no = slip_no; }
    public void setSlip_flag( String slip_flag ){ this.slip_flag = slip_flag; }
    public void setSlip_gb( String slip_gb ){ this.slip_gb = slip_gb; }
    public void setDely_type( String dely_type ){ this.dely_type = dely_type; }
    public void setDely_gb( String dely_gb ){ this.dely_gb = dely_gb; }
    public void setMsg( String msg ){ this.msg = msg; }
    public void setMixpack_flag( String mixpack_flag ){ this.mixpack_flag = mixpack_flag; }
    public void setCreate_date( String create_date ){ this.create_date = create_date; }
    public void setCreate_seq( String create_seq ){ this.create_seq = create_seq; }
    public void setSlip_proc( String slip_proc ){ this.slip_proc = slip_proc; }
    public void setSlip_proc_date( String slip_proc_date ){ this.slip_proc_date = slip_proc_date; }
    public void setSlip_proc_id( String slip_proc_id ){ this.slip_proc_id = slip_proc_id; }
    public void setDely_hope_date( String dely_hope_date ){ this.dely_hope_date = dely_hope_date; }
    public void setDely_hope_yn( String dely_hope_yn ){ this.dely_hope_yn = dely_hope_yn; }
    public void setDely_hope_time( String dely_hope_time ){ this.dely_hope_time = dely_hope_time; }
    public void setRedely_yn( String redely_yn ){ this.redely_yn = redely_yn; }
    public void setOut_close_yn( String out_close_yn ){ this.out_close_yn = out_close_yn; }
    public void setOut_close_date( String out_close_date ){ this.out_close_date = out_close_date; }
    public void setMsg_note( String msg_note ){ this.msg_note = msg_note; }
    public void setHappy_card_yn( String happy_card_yn ){ this.happy_card_yn = happy_card_yn; }
    public void setPack_yn( String pack_yn ){ this.pack_yn = pack_yn; }
    public void setAnony_yn( String anony_yn ){ this.anony_yn = anony_yn; }
    public void setPre_dely_yn( String pre_dely_yn ){ this.pre_dely_yn = pre_dely_yn; }
    public void setReal_receiver( String real_receiver ){ this.real_receiver = real_receiver; }
    public void setReal_dely_date( String real_dely_date ){ this.real_dely_date = real_dely_date; }
    public void setReceipt_yn( String receipt_yn ){ this.receipt_yn = receipt_yn; }
    public void setReal_out_date( String real_out_date ){ this.real_out_date = real_out_date; }
    public void setRemark1_v( String remark1_v ){ this.remark1_v = remark1_v; }
    public void setRemark2_v( String remark2_v ){ this.remark2_v = remark2_v; }
    public void setRemark3_n( String remark3_n ){ this.remark3_n = remark3_n; }
    public void setShip_fee( Double ship_fee ){ this.ship_fee = ship_fee; }
    public void setShip_fee_date( String ship_fee_date ){ this.ship_fee_date = ship_fee_date; }

    /** Get Method **/
    public String getSlip_i_no(){ return slip_i_no; }
    public String getCust_no(){ return cust_no; }
    public String getReceiver_seq(){ return receiver_seq; }
    public String getWh_code(){ return wh_code; }
    public String getSlip_no(){ return slip_no; }
    public String getSlip_flag(){ return slip_flag; }
    public String getSlip_gb(){ return slip_gb; }
    public String getDely_type(){ return dely_type; }
    public String getDely_gb(){ return dely_gb; }
    public String getMsg(){ return msg; }
    public String getMixpack_flag(){ return mixpack_flag; }
    public String getCreate_date(){ return create_date; }
    public String getCreate_seq(){ return create_seq; }
    public String getSlip_proc(){ return slip_proc; }
    public String getSlip_proc_date(){ return slip_proc_date; }
    public String getSlip_proc_id(){ return slip_proc_id; }
    public String getDely_hope_date(){ return dely_hope_date; }
    public String getDely_hope_yn(){ return dely_hope_yn; }
    public String getDely_hope_time(){ return dely_hope_time; }
    public String getRedely_yn(){ return redely_yn; }
    public String getOut_close_yn(){ return out_close_yn; }
    public String getOut_close_date(){ return out_close_date; }
    public String getMsg_note(){ return msg_note; }
    public String getHappy_card_yn(){ return happy_card_yn; }
    public String getPack_yn(){ return pack_yn; }
    public String getAnony_yn(){ return anony_yn; }
    public String getPre_dely_yn(){ return pre_dely_yn; }
    public String getReal_receiver(){ return real_receiver; }
    public String getReal_dely_date(){ return real_dely_date; }
    public String getReceipt_yn(){ return receipt_yn; }
    public String getReal_out_date(){ return real_out_date; }
    public String getRemark1_v(){ return remark1_v; }
    public String getRemark2_v(){ return remark2_v; }
    public String getRemark3_n(){ return remark3_n; }
    public Double getShip_fee(){ return ship_fee; }
    public String getShip_fee_date(){ return ship_fee_date; }


}