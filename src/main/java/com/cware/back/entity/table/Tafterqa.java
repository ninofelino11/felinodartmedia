package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 입고검사
 *
 * @version 1.0, 2011/01/26
 * @author Commerceware Ins.
 */
public class Tafterqa extends BaseEntity {

	public Tafterqa() {
		super();
	}

	private String balju_no;
	private String goods_code;
	private String goodsdt_code;
	private int    balju_qty;
	private String sqc_gb;
	private int    qa_qty;
	private String qa_date;
	private String insert_id;
	private String insert_date;
	
	
	public String getBalju_no() {
		return balju_no;
	}
	public void setBalju_no(String baljuNo) {
		balju_no = baljuNo;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdtCode) {
		goodsdt_code = goodsdtCode;
	}
	public int getBalju_qty() {
		return balju_qty;
	}
	public void setBalju_qty(int baljuQty) {
		balju_qty = baljuQty;
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
	public String getQa_date() {
		return qa_date;
	}
	public void setQa_date(String qaDate) {
		qa_date = qaDate;
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
}