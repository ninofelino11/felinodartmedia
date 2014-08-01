package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 기초코드
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/

public class Tsampleinout extends BaseEntity {
	
	public Tsampleinout() { super(); }
	
	private String seq_samp_no;
	private String io_flag;
	private String io_date;
	private String wh_code;
	private String gooods_code;
	private String io_qty;
	private String receiver_id;
	private String io_note;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	
	//SET
	public void setSeq_samp_no(String seqSampNo) {
		seq_samp_no = seqSampNo;
	}
	public void setIo_flag(String ioFlag) {
		io_flag = ioFlag;
	}
	public void setIo_date(String ioDate) {
		io_date = ioDate;
	}
	public void setWh_code(String whCode) {
		wh_code = whCode;
	}
	public void setGooods_code(String gooodsCode) {
		gooods_code = gooodsCode;
	}
	public void setIo_qty(String ioQty) {
		io_qty = ioQty;
	}
	public void setReceiver_id(String receiverId) {
		receiver_id = receiverId;
	}
	public void setIo_note(String ioNote) {
		io_note = ioNote;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	
	//GET
	public String getSeq_samp_no() {
		return seq_samp_no;
	}
	public String getIo_flag() {
		return io_flag;
	}
	public String getIo_date() {
		return io_date;
	}
	public String getWh_code() {
		return wh_code;
	}
	public String getGooods_code() {
		return gooods_code;
	}
	public String getIo_qty() {
		return io_qty;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public String getIo_note() {
		return io_note;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public String getModify_date() {
		return modify_date;
	}
	
	

}
