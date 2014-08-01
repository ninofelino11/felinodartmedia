package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 카탈로그상품
 *
 * @version 1.0, 2011/01/12
 * @author Commerceware Ins.
 */
public class Tmediagoods extends BaseEntity {

	public Tmediagoods() {
		super();
	}

	private String media_code;
	private String goods_code;
	private String ctlg_code;
	private int    page_no;
	private double page_rate;
	private double page_cost;
	private String ch_yn;
	private String new_old_yn;
	private String tm_careful;
	private String use_yn;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;

	public String getMedia_code() {
		return media_code;
	}
	public void setMedia_code(String mediaCode) {
		media_code = mediaCode;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}
	public String getCtlg_code() {
		return ctlg_code;
	}
	public void setCtlg_code(String ctlgCode) {
		ctlg_code = ctlgCode;
	}
	public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int pageNo) {
		page_no = pageNo;
	}
	public double getPage_rate() {
		return page_rate;
	}
	public void setPage_rate(double pageRate) {
		page_rate = pageRate;
	}
	public double getPage_cost() {
		return page_cost;
	}
	public void setPage_cost(double pageCost) {
		page_cost = pageCost;
	}
	public String getCh_yn() {
		return ch_yn;
	}
	public void setCh_yn(String chYn) {
		ch_yn = chYn;
	}
	public String getNew_old_yn() {
		return new_old_yn;
	}
	public void setNew_old_yn(String newOldYn) {
		new_old_yn = newOldYn;
	}
	public String getTm_careful() {
		return tm_careful;
	}
	public void setTm_careful(String tmCareful) {
		tm_careful = tmCareful;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String useYn) {
		use_yn = useYn;
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