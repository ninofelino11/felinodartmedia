
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 정산 D
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpurchase_dt extends BaseEntity {

public Tpurchase_dt(){ super();}

    private String purchase_no;
    private String goods_code;
    private String goodsdt_code;
    private double sale_price;
    private long   sale_qty;
    private double sale_amt;
    private long   cancel_qty;
    private double cancel_amt;
    private String process_date;
    private String process_id;
    private long   priv_credit_qty;
    private long   aft_credit_qty;
    private String payday_gb;
    private double sale_cost;
    private double sale_vat;
    private long   buy_qty;
    private double buy_amt;
    private long   out_qty;
    private double out_amt;
    private String seq;


//    private long   buy_qty;
    private double buy_cost;
    private double buy_vat;
    private long   return_qty;
    private double return_cost;
    private double return_vat;


	public long getAft_credit_qty() {
		return aft_credit_qty;
	}
	public void setAft_credit_qty(long aft_credit_qty) {
		this.aft_credit_qty = aft_credit_qty;
	}
	public double getBuy_amt() {
		return buy_amt;
	}
	public void setBuy_amt(double buy_amt) {
		this.buy_amt = buy_amt;
	}
	public long getBuy_qty() {
		return buy_qty;
	}
	public void setBuy_qty(long buy_qty) {
		this.buy_qty = buy_qty;
	}
	public double getCancel_amt() {
		return cancel_amt;
	}
	public void setCancel_amt(double cancel_amt) {
		this.cancel_amt = cancel_amt;
	}
	public long getCancel_qty() {
		return cancel_qty;
	}
	public void setCancel_qty(long cancel_qty) {
		this.cancel_qty = cancel_qty;
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
	public double getOut_amt() {
		return out_amt;
	}
	public void setOut_amt(double out_amt) {
		this.out_amt = out_amt;
	}
	public long getOut_qty() {
		return out_qty;
	}
	public void setOut_qty(long out_qty) {
		this.out_qty = out_qty;
	}
	public String getPayday_gb() {
		return payday_gb;
	}
	public void setPayday_gb(String payday_gb) {
		this.payday_gb = payday_gb;
	}
	public long getPriv_credit_qty() {
		return priv_credit_qty;
	}
	public void setPriv_credit_qty(long priv_credit_qty) {
		this.priv_credit_qty = priv_credit_qty;
	}
	public String getProcess_date() {
		return process_date;
	}
	public void setProcess_date(String process_date) {
		this.process_date = process_date;
	}
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getPurchase_no() {
		return purchase_no;
	}
	public void setPurchase_no(String purchase_no) {
		this.purchase_no = purchase_no;
	}
	public double getSale_amt() {
		return sale_amt;
	}
	public void setSale_amt(double sale_amt) {
		this.sale_amt = sale_amt;
	}
	public double getSale_cost() {
		return sale_cost;
	}
	public void setSale_cost(double sale_cost) {
		this.sale_cost = sale_cost;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public long getSale_qty() {
		return sale_qty;
	}
	public void setSale_qty(long sale_qty) {
		this.sale_qty = sale_qty;
	}
	public double getSale_vat() {
		return sale_vat;
	}
	public void setSale_vat(double sale_vat) {
		this.sale_vat = sale_vat;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}


	public double getBuy_cost() {
		return buy_cost;
	}
	public void setBuy_cost(double buy_cost) {
		this.buy_cost = buy_cost;
	}
	public double getBuy_vat() {
		return buy_vat;
	}
	public void setBuy_vat(double buy_vat) {
		this.buy_vat = buy_vat;
	}
	public double getReturn_cost() {
		return return_cost;
	}
	public void setReturn_cost(double return_cost) {
		this.return_cost = return_cost;
	}
	public long getReturn_qty() {
		return return_qty;
	}
	public void setReturn_qty(long return_qty) {
		this.return_qty = return_qty;
	}
	public double getReturn_vat() {
		return return_vat;
	}
	public void setReturn_vat(double return_vat) {
		this.return_vat = return_vat;
	}





}