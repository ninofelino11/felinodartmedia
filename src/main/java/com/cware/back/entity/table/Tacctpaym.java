package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 대금지급
 *
 * @version 1.0, 2010/12/31
 * @author Commerceware Ins.
 */
public class Tacctpaym extends BaseEntity {

	public Tacctpaym() {
		super();
	}

	private String pay_yymm;
	private String pay_times;
	private String entp_code;
	private double last_hold_amt;
	private double curr_amt;
	private double curr_hold_amt;
	private double curr_deduct_amt;
	private double curr_pay_amt;
	private String conf_yn;
	private String pay_date;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;

	public String getPay_yymm() {
		return pay_yymm;
	}

	public void setPay_yymm(String payYymm) {
		pay_yymm = payYymm;
	}

	public String getPay_times() {
		return pay_times;
	}

	public void setPay_times(String payTimes) {
		pay_times = payTimes;
	}

	public String getEntp_code() {
		return entp_code;
	}

	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
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

	public double getCurr_hold_amt() {
		return curr_hold_amt;
	}

	public void setCurr_hold_amt(double currHoldAmt) {
		curr_hold_amt = currHoldAmt;
	}

	public double getCurr_deduct_amt() {
		return curr_deduct_amt;
	}

	public void setCurr_deduct_amt(double currDeductAmt) {
		curr_deduct_amt = currDeductAmt;
	}

	public double getCurr_pay_amt() {
		return curr_pay_amt;
	}

	public void setCurr_pay_amt(double currPayAmt) {
		curr_pay_amt = currPayAmt;
	}

	public String getConf_yn() {
		return conf_yn;
	}

	public void setConf_yn(String confYn) {
		conf_yn = confYn;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String payDate) {
		pay_date = payDate;
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
}