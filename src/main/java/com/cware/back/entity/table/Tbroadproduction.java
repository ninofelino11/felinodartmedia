package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * Tbroadproduction
 *
 * @version 1.0, 2011/02/09
 */
public class Tbroadproduction extends BaseEntity {

	public Tbroadproduction() {
		super();
	}

	private String prdtn_no;
	private String prdtn_flag;
	private String seq_frame_no;
	private String bd_date;
	private long bd_cost;
	private String pay_yymm;
	private String pay_end_yn;
	private String remark;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;

	public String getPrdtn_no() {
		return prdtn_no;
	}

	public void setPrdtn_no(String prdtnNo) {
		prdtn_no = prdtnNo;
	}

	public String getPrdtn_flag() {
		return prdtn_flag;
	}

	public void setPrdtn_flag(String prdtnFlag) {
		prdtn_flag = prdtnFlag;
	}

	public String getSeq_frame_no() {
		return seq_frame_no;
	}

	public void setSeq_frame_no(String seqFrameNo) {
		seq_frame_no = seqFrameNo;
	}

	public String getBd_date() {
		return bd_date;
	}

	public void setBd_date(String bdDate) {
		bd_date = bdDate;
	}

	public long getBd_cost() {
		return bd_cost;
	}

	public void setBd_cost(long bdCost) {
		bd_cost = bdCost;
	}

	public String getPay_yymm() {
		return pay_yymm;
	}

	public void setPay_yymm(String payYymm) {
		pay_yymm = payYymm;
	}

	public String getPay_end_yn() {
		return pay_end_yn;
	}

	public void setPay_end_yn(String payEndYn) {
		pay_end_yn = payEndYn;
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
