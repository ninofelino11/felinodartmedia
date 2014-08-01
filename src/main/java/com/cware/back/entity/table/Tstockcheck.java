
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 재고실사정보
*
* @version 1.0, 2006/11/20
* @author Commerceware Ins.
*/
public class Tstockcheck extends BaseEntity {

	public Tstockcheck(){ super();}

	private String check_code;
	private String goods_code;
	private String goodsdt_code;
	private String rack_code;
	private String wh_code;
	private String check_date;
	private String rack_grade;
	private long   rack_qty;
	private long   stockcheck_qty;
	private String result_code;
	private String result_note;
	private String ctrl_yn;
	private String ctrl_date;
	private String med_flag;
	private String insert_date;
	private String insert_id;
	private String modify_date;
	private String modify_id;
	
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getCtrl_date() {
		return ctrl_date;
	}
	public void setCtrl_date(String ctrl_date) {
		this.ctrl_date = ctrl_date;
	}
	public String getCtrl_yn() {
		return ctrl_yn;
	}
	public void setCtrl_yn(String ctrl_yn) {
		this.ctrl_yn = ctrl_yn;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public String getMed_flag() {
		return med_flag;
	}
	public void setMed_flag(String med_flag) {
		this.med_flag = med_flag;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
	public String getRack_code() {
		return rack_code;
	}
	public void setRack_code(String rack_code) {
		this.rack_code = rack_code;
	}
	public String getRack_grade() {
		return rack_grade;
	}
	public void setRack_grade(String rack_grade) {
		this.rack_grade = rack_grade;
	}
	public long getRack_qty() {
		return rack_qty;
	}
	public void setRack_qty(long rack_qty) {
		this.rack_qty = rack_qty;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_note() {
		return result_note;
	}
	public void setResult_note(String result_note) {
		this.result_note = result_note;
	}
	public long getStockcheck_qty() {
		return stockcheck_qty;
	}
	public void setStockcheck_qty(long stockcheck_qty) {
		this.stockcheck_qty = stockcheck_qty;
	}
	public String getWh_code() {
		return wh_code;
	}
	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}

} 