package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 공제보류
 *
 * @version 1.0, 2010/12/31
 * @author Commerceware Ins.
 */
public class Tacctpaydt extends BaseEntity {

	public Tacctpaydt() {
		super();
	}

	private String pay_yymm;
	private String pay_times;
	private String entp_code;
	private String ho_de_code;
	private String ho_de_flag;
	private double ho_de_amt;
	private String remark;
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

	public String getHo_de_code() {
		return ho_de_code;
	}

	public void setHo_de_code(String hoDeCode) {
		ho_de_code = hoDeCode;
	}

	public String getHo_de_flag() {
		return ho_de_flag;
	}

	public void setHo_de_flag(String hoDeFlag) {
		ho_de_flag = hoDeFlag;
	}

	public double getHo_de_amt() {
		return ho_de_amt;
	}

	public void setHo_de_amt(double hoDeAmt) {
		ho_de_amt = hoDeAmt;
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
}