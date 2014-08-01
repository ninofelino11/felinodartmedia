
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 계산서수령
*
* @version 1.0, 2010/12/29
* @author Commerceware Ins.
*/
public class Tacctinvoice extends BaseEntity {

	public Tacctinvoice(){ super(); }

	private String pay_yymm;
	private String entp_code;
	private String tax_yn;
	private double last_hold_amt;
	private double curr_amt;
	private double bill_cost;
	private double bill_vat;
	private double bill_amt;
	private double cut_amt;
	private double curr_hold_amt;
	private String rcv_date;
	private String remark;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	private String rcv_yn;

	public String getPay_yymm() {
		return pay_yymm;
	}
	public void setPay_yymm(String payYymm) {
		pay_yymm = payYymm;
	}
	public String getEntp_code() {
		return entp_code;
	}
	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
	}
	public String getTax_yn() {
		return tax_yn;
	}
	public void setTax_yn(String taxYn) {
		tax_yn = taxYn;
	}
	public double getLast_hold_amt() {
		return last_hold_amt;
	}
	public void setLast_hold_amt(double lastHoldAmt) {
		last_hold_amt = lastHoldAmt;
	}
	public double getCurr_amt() {
		return curr_amt;
	}
	public void setCurr_amt(double currAmt) {
		curr_amt = currAmt;
	}
	public double getBill_cost() {
		return bill_cost;
	}
	public void setBill_cost(double billCost) {
		bill_cost = billCost;
	}
	public double getBill_vat() {
		return bill_vat;
	}
	public void setBill_vat(double billVat) {
		bill_vat = billVat;
	}
	public double getBill_amt() {
		return bill_amt;
	}
	public void setBill_amt(double billAmt) {
		bill_amt = billAmt;
	}
	public double getCut_amt() {
		return cut_amt;
	}
	public void setCut_amt(double cutAmt) {
		cut_amt = cutAmt;
	}
	public double getCurr_hold_amt() {
		return curr_hold_amt;
	}
	public void setCurr_hold_amt(double currHoldAmt) {
		curr_hold_amt = currHoldAmt;
	}
	public String getRcv_date() {
		return rcv_date;
	}
	public void setRcv_date(String rcvDate) {
		rcv_date = rcvDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
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
	public String getRcv_yn() {
		return rcv_yn;
	}
	public void setRcv_yn(String rcvYn) {
		rcv_yn = rcvYn;
	}
}