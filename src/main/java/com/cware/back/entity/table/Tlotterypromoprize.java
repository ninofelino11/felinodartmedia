package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 추첨프로모션 프로모션 당첨자 정보
*
* @version 1.0, 2006/11/14
* @author Commerceware Ins.
*/
public class Tlotterypromoprize extends BaseEntity {

public Tlotterypromoprize(){ super();}

    private String lottery_promo_no;
    private String seq;
    private String cust_no;
    private String promo_order_no;
    private String cancel_yn;
    private String cancel_date;
    private String cancel_id;
    private String remark;
    private String insert_date;
    private String insert_id;

    private long do_amt;
    private String tax_rcv_yn;
    private String tax_rcv_date;
    private long tax_rcv_amt;
    private String tax_rcv_id;
    private String resident_no;
    private String modify_id;
    private String modify_date;

    /** Set Method **/
    public void setLottery_promo_no( String lottery_promo_no ){ this.lottery_promo_no = lottery_promo_no; }
    public void setSeq( String seq ){ this.seq = seq; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setPromo_order_no( String promo_order_no ){ this.promo_order_no = promo_order_no; }
    public void setCancel_yn( String cancel_yn ){ this.cancel_yn = cancel_yn; }
    public void setCancel_date( String cancel_date ){ this.cancel_date = cancel_date; }
    public void setCancel_id( String cancel_id ){ this.cancel_id = cancel_id; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getLottery_promo_no(){ return lottery_promo_no; }
    public String getSeq(){ return seq; }
    public String getCust_no(){ return cust_no; }
    public String getPromo_order_no(){ return promo_order_no; }
    public String getCancel_yn(){ return cancel_yn; }
    public String getCancel_date(){ return cancel_date; }
    public String getCancel_id(){ return cancel_id; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

	public long getDo_amt() {
		return do_amt;
	}
	public void setDo_amt(long doAmt) {
		do_amt = doAmt;
	}
	public String getTax_rcv_yn() {
		return tax_rcv_yn;
	}
	public void setTax_rcv_yn(String taxRcvYn) {
		tax_rcv_yn = taxRcvYn;
	}
	public String getTax_rcv_date() {
		return tax_rcv_date;
	}
	public void setTax_rcv_date(String taxRcvDate) {
		tax_rcv_date = taxRcvDate;
	}
	public long getTax_rcv_amt() {
		return tax_rcv_amt;
	}
	public void setTax_rcv_amt(long taxRcvAmt) {
		tax_rcv_amt = taxRcvAmt;
	}
	public String getTax_rcv_id() {
		return tax_rcv_id;
	}
	public void setTax_rcv_id(String taxRcvId) {
		tax_rcv_id = taxRcvId;
	}
	public String getResident_no() {
		return resident_no;
	}
	public void setResident_no(String residentNo) {
		resident_no = residentNo;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}

}