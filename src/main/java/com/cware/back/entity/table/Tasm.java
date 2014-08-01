
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tasm extends BaseEntity {

    public Tasm(){ super();}

    private String as_no;
    private String cust_no;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String goodsdt_code;
    private String as_code;
    private long   qty;
    private double price;
    private String as_note;
    private String receiver_seq1;
    private String as_com1;
    private String receiver_seq2;
    private String as_com2;
    private String wh_code;
    private String wh_yn;
    private String final_proc;
    private String final_date;
    private String final_id;
    private double repair_amt;
    private String repair_date;
    private String repair_id;
    private String repair_note;
    private double deposit_amt;
    private String deposit_date;
    private String deposit_id;
    private String slip1;
    private String slip2;
    private String in_dely_gb;
    private String out_dely_gb;
    private String content;
    private String entpslip_no;
    private String receive_type;
    private String as_dely_type;
    private String as_proc_gb;
    private String as_proc_note;
    private long   repair_term;
    private String err_return_yn;
    private String err_return_note;
    private String in_send_yn;
    private String out_send_yn;
    private String entp_code;
    private String dely_type;
    private String as_can_yn;
    private String as_can_confirm;
    private String goods_code;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setAs_no( String as_no ){ this.as_no = as_no; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setOrder_g_seq( String order_g_seq ){ this.order_g_seq = order_g_seq; }
    public void setOrder_d_seq( String order_d_seq ){ this.order_d_seq = order_d_seq; }
    public void setOrder_w_seq( String order_w_seq ){ this.order_w_seq = order_w_seq; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setAs_code( String as_code ){ this.as_code = as_code; }
    public void setQty( long qty ){ this.qty = qty; }
    public void setPrice( double price ){ this.price = price; }
    public void setAs_note( String as_note ){ this.as_note = as_note; }
    public void setReceiver_seq1( String receiver_seq1 ){ this.receiver_seq1 = receiver_seq1; }
    public void setAs_com1( String as_com1 ){ this.as_com1 = as_com1; }
    public void setReceiver_seq2( String receiver_seq2 ){ this.receiver_seq2 = receiver_seq2; }
    public void setAs_com2( String as_com2 ){ this.as_com2 = as_com2; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setWh_yn( String wh_yn ){ this.wh_yn = wh_yn; }
    public void setFinal_proc( String final_proc ){ this.final_proc = final_proc; }
    public void setFinal_date( String final_date ){ this.final_date = final_date; }
    public void setFinal_id( String final_id ){ this.final_id = final_id; }
    public void setRepair_amt( double   repair_amt ){ this.repair_amt = repair_amt; }
    public void setRepair_date( String repair_date ){ this.repair_date = repair_date; }
    public void setRepair_id( String repair_id ){ this.repair_id = repair_id; }
    public void setRepair_note( String repair_note ){ this.repair_note = repair_note; }
    public void setDeposit_amt( double   deposit_amt ){ this.deposit_amt = deposit_amt; }
    public void setDeposit_date( String deposit_date ){ this.deposit_date = deposit_date; }
    public void setDeposit_id( String deposit_id ){ this.deposit_id = deposit_id; }
    public void setSlip1( String slip1 ){ this.slip1 = slip1; }
    public void setSlip2( String slip2 ){ this.slip2 = slip2; }
    public void setIn_dely_gb( String in_dely_gb ){ this.in_dely_gb = in_dely_gb; }
    public void setOut_dely_gb( String out_dely_gb ){ this.out_dely_gb = out_dely_gb; }
    public void setContent( String content ){ this.content = content; }
    public void setEntpslip_no( String entpslip_no ){ this.entpslip_no = entpslip_no; }
    public void setReceive_type( String receive_type ){ this.receive_type = receive_type; }
    public void setAs_dely_type( String as_dely_type ){ this.as_dely_type = as_dely_type; }
    public void setAs_proc_gb( String as_proc_gb ){ this.as_proc_gb = as_proc_gb; }
    public void setAs_proc_note( String as_proc_note ){ this.as_proc_note = as_proc_note; }
    public void setRepair_term( long   repair_term ){ this.repair_term = repair_term; }
    public void setErr_return_yn( String err_return_yn ){ this.err_return_yn = err_return_yn; }
    public void setErr_return_note( String err_return_note ){ this.err_return_note = err_return_note; }
    public void setIn_send_yn( String in_send_yn ){ this.in_send_yn = in_send_yn; }
    public void setOut_send_yn( String out_send_yn ){ this.out_send_yn = out_send_yn; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setDely_type( String dely_type ){ this.dely_type = dely_type; }
    public void setAs_can_yn( String as_can_yn ){ this.as_can_yn = as_can_yn; }
    public void setAs_can_confirm( String as_can_confirm ){ this.as_can_confirm = as_can_confirm; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getAs_no(){ return as_no; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_no(){ return order_no; }
    public String getOrder_g_seq(){ return order_g_seq; }
    public String getOrder_d_seq(){ return order_d_seq; }
    public String getOrder_w_seq(){ return order_w_seq; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getAs_code(){ return as_code; }
    public long   getQty(){ return qty; }
    public double getPrice(){ return price; }
    public String getAs_note(){ return as_note; }
    public String getReceiver_seq1(){ return receiver_seq1; }
    public String getAs_com1(){ return as_com1; }
    public String getReceiver_seq2(){ return receiver_seq2; }
    public String getAs_com2(){ return as_com2; }
    public String getWh_code(){ return wh_code; }
    public String getWh_yn(){ return wh_yn; }
    public String getFinal_proc(){ return final_proc; }
    public String getFinal_date(){ return final_date; }
    public String getFinal_id(){ return final_id; }
    public double getRepair_amt(){ return repair_amt; }
    public String getRepair_date(){ return repair_date; }
    public String getRepair_id(){ return repair_id; }
    public String getRepair_note(){ return repair_note; }
    public double getDeposit_amt(){ return deposit_amt; }
    public String getDeposit_date(){ return deposit_date; }
    public String getDeposit_id(){ return deposit_id; }
    public String getSlip1(){ return slip1; }
    public String getSlip2(){ return slip2; }
    public String getIn_dely_gb(){ return in_dely_gb; }
    public String getOut_dely_gb(){ return out_dely_gb; }
    public String getContent(){ return content; }
    public String getEntpslip_no(){ return entpslip_no; }
    public String getReceive_type(){ return receive_type; }
    public String getAs_dely_type(){ return as_dely_type; }
    public String getAs_proc_gb(){ return as_proc_gb; }
    public String getAs_proc_note(){ return as_proc_note; }
    public long   getRepair_term(){ return repair_term; }
    public String getErr_return_yn(){ return err_return_yn; }
    public String getErr_return_note(){ return err_return_note; }
    public String getIn_send_yn(){ return in_send_yn; }
    public String getOut_send_yn(){ return out_send_yn; }
    public String getEntp_code(){ return entp_code; }
    public String getDely_type(){ return dely_type; }
    public String getAs_can_yn(){ return as_can_yn; }
    public String getAs_can_confirm(){ return as_can_confirm; }
    public String getGoods_code(){ return goods_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 