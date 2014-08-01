
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SCM상품
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tscmgoods extends BaseEntity {

	private static final long serialVersionUID = 1L;

    public Tscmgoods(){ super();}

    private String goods_code;
    private String goods_name;
    private String keyword;
    private String lgroup;
    private String mgroup;
    private String sgroup;
    private String dgroup;
    private String entp_code;
    private String entp_man_seq;
    private String md_code;
    private String buy_med;
    private String dely_type;
    private String tax_yn;
    private String cost_tax_yn;
    private String makeco_code;
    private String origin_code;
    private String brand_code;
    private String gift_yn;
    private String weight;
    private String form_code;
    private String size_code;
    private String order_tv;
    private String order_ctlg;
    private String order_int;
    private String sign_gb;
    private String sign_note;
    private String confirm_goods_code;
    private String confirm_date;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getBrand_code() {
		return brand_code;
	}
	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}
	public String getBuy_med() {
		return buy_med;
	}
	public void setBuy_med(String buy_med) {
		this.buy_med = buy_med;
	}
	public String getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getConfirm_goods_code() {
		return confirm_goods_code;
	}
	public void setConfirm_goods_code(String confirm_goods_code) {
		this.confirm_goods_code = confirm_goods_code;
	}
	public String getCost_tax_yn() {
		return cost_tax_yn;
	}
	public void setCost_tax_yn(String cost_tax_yn) {
		this.cost_tax_yn = cost_tax_yn;
	}
	public String getDely_type() {
		return dely_type;
	}
	public void setDely_type(String dely_type) {
		this.dely_type = dely_type;
	}
	public String getDgroup() {
		return dgroup;
	}
	public void setDgroup(String dgroup) {
		this.dgroup = dgroup;
	}
	public String getEntp_code() {
		return entp_code;
	}
	public void setEntp_code(String entp_code) {
		this.entp_code = entp_code;
	}
	public String getEntp_man_seq() {
		return entp_man_seq;
	}
	public void setEntp_man_seq(String entp_man_seq) {
		this.entp_man_seq = entp_man_seq;
	}
	public String getForm_code() {
		return form_code;
	}
	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}
	public String getGift_yn() {
		return gift_yn;
	}
	public void setGift_yn(String gift_yn) {
		this.gift_yn = gift_yn;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getLgroup() {
		return lgroup;
	}
	public void setLgroup(String lgroup) {
		this.lgroup = lgroup;
	}
	public String getMakeco_code() {
		return makeco_code;
	}
	public void setMakeco_code(String makeco_code) {
		this.makeco_code = makeco_code;
	}
	public String getMd_code() {
		return md_code;
	}
	public void setMd_code(String md_code) {
		this.md_code = md_code;
	}
	public String getMgroup() {
		return mgroup;
	}
	public void setMgroup(String mgroup) {
		this.mgroup = mgroup;
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
	public String getOrder_ctlg() {
		return order_ctlg;
	}
	public void setOrder_ctlg(String order_ctlg) {
		this.order_ctlg = order_ctlg;
	}
	public String getOrder_int() {
		return order_int;
	}
	public void setOrder_int(String order_int) {
		this.order_int = order_int;
	}
	public String getOrder_tv() {
		return order_tv;
	}
	public void setOrder_tv(String order_tv) {
		this.order_tv = order_tv;
	}
	public String getOrigin_code() {
		return origin_code;
	}
	public void setOrigin_code(String origin_code) {
		this.origin_code = origin_code;
	}
	public String getSgroup() {
		return sgroup;
	}
	public void setSgroup(String sgroup) {
		this.sgroup = sgroup;
	}
	public String getSign_gb() {
		return sign_gb;
	}
	public void setSign_gb(String sign_gb) {
		this.sign_gb = sign_gb;
	}
	public String getSign_note() {
		return sign_note;
	}
	public void setSign_note(String sign_note) {
		this.sign_note = sign_note;
	}
	public String getSize_code() {
		return size_code;
	}
	public void setSize_code(String size_code) {
		this.size_code = size_code;
	}
	public String getTax_yn() {
		return tax_yn;
	}
	public void setTax_yn(String tax_yn) {
		this.tax_yn = tax_yn;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}



}