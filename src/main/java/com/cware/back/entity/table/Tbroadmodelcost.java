package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * Tbroadmodelcost
 *
 * @version 1.0, 2011/02/09
 */
public class Tbroadmodelcost extends BaseEntity {

	public Tbroadmodelcost() {
		super();
	}

	private String prdtn_no;
	private String model_no;
	private long own_cost;
	private long entp_cost;
	private String entp_code;
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

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String modelNo) {
		model_no = modelNo;
	}

	public long getOwn_cost() {
		return own_cost;
	}

	public void setOwn_cost(long ownCost) {
		own_cost = ownCost;
	}

	public long getEntp_cost() {
		return entp_cost;
	}

	public void setEntp_cost(long entpCost) {
		entp_cost = entpCost;
	}

	public String getEntp_code() {
		return entp_code;
	}

	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
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
