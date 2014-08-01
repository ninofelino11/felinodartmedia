package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * Tbroadpdgoods
 *
 * @version 1.0, 2011/02/09
 */
public class Tbroadpdgoods extends BaseEntity {

	public Tbroadpdgoods() {
		super();
	}

	private String prdtn_no;
	private String goods_code;
	private double goods_rate;
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

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}

	public double getGoods_rate() {
		return goods_rate;
	}

	public void setGoods_rate(double goodsRate) {
		goods_rate = goodsRate;
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
