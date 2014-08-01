package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 사전검사
 *
 * @version 1.0, 2006/07/11
 * @author Commerceware Ins.
 */
public class Tbeforeqam extends BaseEntity {

	public Tbeforeqam() {
		super();
	}

	private String seq_qa_no;
	private String goods_code;
	private String req_date;
	private String hope_date;
	private String req_flag;
	private String req_note;
	private String sqc_gb;
	private int    qa_qty;
	private String acpt_date;
	private String qa_date;
	private String qa_opinion;
	private String qa_id;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	private String lmsdCode;

	public String getLmsdCode() {
		return lmsdCode;
	}

	public void setLmsdCode(String lmsdCode) {
		this.lmsdCode = lmsdCode;
	}

	public String getSeq_qa_no() {
		return seq_qa_no;
	}

	public void setSeq_qa_no(String seqQaNo) {
		seq_qa_no = seqQaNo;
	}

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}

	public String getReq_date() {
		return req_date;
	}

	public void setReq_date(String reqDate) {
		req_date = reqDate;
	}

	public String getHope_date() {
		return hope_date;
	}

	public void setHope_date(String hopeDate) {
		hope_date = hopeDate;
	}

	public String getReq_flag() {
		return req_flag;
	}

	public void setReq_flag(String reqFlag) {
		req_flag = reqFlag;
	}

	public String getReq_note() {
		return req_note;
	}

	public void setReq_note(String reqNote) {
		req_note = reqNote;
	}

	public String getSqc_gb() {
		return sqc_gb;
	}

	public void setSqc_gb(String sqcGb) {
		sqc_gb = sqcGb;
	}

	public int getQa_qty() {
		return qa_qty;
	}

	public void setQa_qty(int qaQty) {
		qa_qty = qaQty;
	}

	public String getAcpt_date() {
		return acpt_date;
	}

	public void setAcpt_date(String acptDate) {
		acpt_date = acptDate;
	}

	public String getQa_date() {
		return qa_date;
	}

	public void setQa_date(String qaDate) {
		qa_date = qaDate;
	}

	public String getQa_opinion() {
		return qa_opinion;
	}

	public void setQa_opinion(String qaOpinion) {
		qa_opinion = qaOpinion;
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

	public String getQa_id() {
		return qa_id;
	}

	public void setQa_id(String qaId) {
		qa_id = qaId;
	}

}