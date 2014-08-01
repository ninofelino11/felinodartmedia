package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * Tbroadpdcost
 *
 * @version 1.0, 2011/02/09
 */
public class Tbroadpdcost extends BaseEntity {

	public Tbroadpdcost() {
		super();
	}

	private String prdtn_no;
	private String acct_code;
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

	public String getAcct_code() {
		return acct_code;
	}

	public void setAcct_code(String acctCode) {
		acct_code = acctCode;
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
