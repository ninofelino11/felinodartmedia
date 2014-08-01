
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* SCM상품DT
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tscmgoodsdt extends BaseEntity {

	private static final long serialVersionUID = 1L;

    public Tscmgoodsdt(){ super();}

    private String   goods_code;
    private String   goodsdt_code;
    private String   goods_name;
    private String   color_code;
    private String   color_name;
    private String   size_code;
    private String   size_name;
    private String   pattern_code;
    private String   pattern_name;
    private String   form_code;
    private String   form_name;
    private String   other_text;
    private String   goodsdt_info;
    private long     lead_time;
    private long     daily_capa_qty;
    private long     max_sale_qty;
    private double   buy_cost;
    private double   buy_vat;
    private double   buy_vat_rate;
    private double   buy_price;
    private double   sale_price;
    private double   sale_vat_rate;
    private double   sale_vat;
    private double   cust_price;
    private String   insert_date;
    private String   insert_id;
    private String   modify_date;
    private String   modify_id;

	public double getBuy_cost() {
		return buy_cost;
	}
	public void setBuy_cost(double buy_cost) {
		this.buy_cost = buy_cost;
	}
	public double getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}
	public double getBuy_vat() {
		return buy_vat;
	}
	public void setBuy_vat(double buy_vat) {
		this.buy_vat = buy_vat;
	}
	public double getBuy_vat_rate() {
		return buy_vat_rate;
	}
	public void setBuy_vat_rate(double buy_vat_rate) {
		this.buy_vat_rate = buy_vat_rate;
	}
	public String getColor_code() {
		return color_code;
	}
	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public double getCust_price() {
		return cust_price;
	}
	public void setCust_price(double cust_price) {
		this.cust_price = cust_price;
	}
	public long getDaily_capa_qty() {
		return daily_capa_qty;
	}
	public void setDaily_capa_qty(long daily_capa_qty) {
		this.daily_capa_qty = daily_capa_qty;
	}
	public String getForm_code() {
		return form_code;
	}
	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}
	public String getForm_name() {
		return form_name;
	}
	public void setForm_name(String form_name) {
		this.form_name = form_name;
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
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
	}
	public String getGoodsdt_info() {
		return goodsdt_info;
	}
	public void setGoodsdt_info(String goodsdt_info) {
		this.goodsdt_info = goodsdt_info;
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
	public long getLead_time() {
		return lead_time;
	}
	public void setLead_time(long lead_time) {
		this.lead_time = lead_time;
	}
	public long getMax_sale_qty() {
		return max_sale_qty;
	}
	public void setMax_sale_qty(long max_sale_qty) {
		this.max_sale_qty = max_sale_qty;
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
	public String getOther_text() {
		return other_text;
	}
	public void setOther_text(String other_text) {
		this.other_text = other_text;
	}
	public String getPattern_code() {
		return pattern_code;
	}
	public void setPattern_code(String pattern_code) {
		this.pattern_code = pattern_code;
	}
	public String getPattern_name() {
		return pattern_name;
	}
	public void setPattern_name(String pattern_name) {
		this.pattern_name = pattern_name;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public double getSale_vat() {
		return sale_vat;
	}
	public void setSale_vat(double sale_vat) {
		this.sale_vat = sale_vat;
	}
	public double getSale_vat_rate() {
		return sale_vat_rate;
	}
	public void setSale_vat_rate(double sale_vat_rate) {
		this.sale_vat_rate = sale_vat_rate;
	}
	public String getSize_code() {
		return size_code;
	}
	public void setSize_code(String size_code) {
		this.size_code = size_code;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}




}