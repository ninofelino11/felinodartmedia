
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 정산M
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpurchase_m extends BaseEntity {

public Tpurchase_m(){ super();}

    private String purchase_no;
    private String entp_code;
    private double prev_youbo;
    private long   sale_qty;
    private double sale_amt;
    private long   cancel_qty;
    private double cancel_amt;
    private long   tot_qty;
    private double tot_purchase_amt;
    private double tax_amt;
    private double youbo_amt;
    private double kongje_amt;
    private String youbo_memo;
    private String kongje_memo;
    private String purchase_fr;
    private String purchase_to;
    private String process_date;
    private String process_id;
    private double pay_1th;
    private double pay_2th;
    private double pay_amt1;
    private double pay_amt2;
    private double pay_amt3;
    private String flag;
    private String trans_date;
    private String trans_id;
    private String end_date;
    private String end_id;
    private double credit_amt;
    private double prev_credit_amt;
    private double buy_amt;
    private double out_amt;
    private double buy_tax_amt;
    private double buy_notax_amt;
    private double buy_vat;
    private double sale_tax_amt;
    private double sale_notax_amt;
    private double sale_vat;
    private String buy_vat_memo;
    private double sonik_amt;
    private String flag_1;
    private String flag_2;
    private String flag_3;
    
	public double getBuy_amt() {
		return buy_amt;
	}
	public void setBuy_amt(double buy_amt) {
		this.buy_amt = buy_amt;
	}
	public double getBuy_notax_amt() {
		return buy_notax_amt;
	}
	public void setBuy_notax_amt(double buy_notax_amt) {
		this.buy_notax_amt = buy_notax_amt;
	}
	public double getBuy_tax_amt() {
		return buy_tax_amt;
	}
	public void setBuy_tax_amt(double buy_tax_amt) {
		this.buy_tax_amt = buy_tax_amt;
	}
	public double getBuy_vat() {
		return buy_vat;
	}
	public void setBuy_vat(double buy_vat) {
		this.buy_vat = buy_vat;
	}
	public String getBuy_vat_memo() {
		return buy_vat_memo;
	}
	public void setBuy_vat_memo(String buy_vat_memo) {
		this.buy_vat_memo = buy_vat_memo;
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
	public double getCredit_amt() {
		return credit_amt;
	}
	public void setCredit_amt(double credit_amt) {
		this.credit_amt = credit_amt;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getEnd_id() {
		return end_id;
	}
	public void setEnd_id(String end_id) {
		this.end_id = end_id;
	}
	public String getEntp_code() {
		return entp_code;
	}
	public void setEntp_code(String entp_code) {
		this.entp_code = entp_code;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag_1() {
		return flag_1;
	}
	public void setFlag_1(String flag_1) {
		this.flag_1 = flag_1;
	}
	public String getFlag_2() {
		return flag_2;
	}
	public void setFlag_2(String flag_2) {
		this.flag_2 = flag_2;
	}
	public String getFlag_3() {
		return flag_3;
	}
	public void setFlag_3(String flag_3) {
		this.flag_3 = flag_3;
	}
	public double getKongje_amt() {
		return kongje_amt;
	}
	public void setKongje_amt(double kongje_amt) {
		this.kongje_amt = kongje_amt;
	}
	public String getKongje_memo() {
		return kongje_memo;
	}
	public void setKongje_memo(String kongje_memo) {
		this.kongje_memo = kongje_memo;
	}
	public double getOut_amt() {
		return out_amt;
	}
	public void setOut_amt(double out_amt) {
		this.out_amt = out_amt;
	}
	public double getPay_1th() {
		return pay_1th;
	}
	public void setPay_1th(double pay_1th) {
		this.pay_1th = pay_1th;
	}
	public double getPay_2th() {
		return pay_2th;
	}
	public void setPay_2th(double pay_2th) {
		this.pay_2th = pay_2th;
	}
	public double getPay_amt1() {
		return pay_amt1;
	}
	public void setPay_amt1(double pay_amt1) {
		this.pay_amt1 = pay_amt1;
	}
	public double getPay_amt2() {
		return pay_amt2;
	}
	public void setPay_amt2(double pay_amt2) {
		this.pay_amt2 = pay_amt2;
	}
	public double getPay_amt3() {
		return pay_amt3;
	}
	public void setPay_amt3(double pay_amt3) {
		this.pay_amt3 = pay_amt3;
	}
	public double getPrev_credit_amt() {
		return prev_credit_amt;
	}
	public void setPrev_credit_amt(double prev_credit_amt) {
		this.prev_credit_amt = prev_credit_amt;
	}
	public double getPrev_youbo() {
		return prev_youbo;
	}
	public void setPrev_youbo(double prev_youbo) {
		this.prev_youbo = prev_youbo;
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
	public String getPurchase_fr() {
		return purchase_fr;
	}
	public void setPurchase_fr(String purchase_fr) {
		this.purchase_fr = purchase_fr;
	}
	public String getPurchase_no() {
		return purchase_no;
	}
	public void setPurchase_no(String purchase_no) {
		this.purchase_no = purchase_no;
	}
	public String getPurchase_to() {
		return purchase_to;
	}
	public void setPurchase_to(String purchase_to) {
		this.purchase_to = purchase_to;
	}
	public double getSale_amt() {
		return sale_amt;
	}
	public void setSale_amt(double sale_amt) {
		this.sale_amt = sale_amt;
	}
	public double getSale_notax_amt() {
		return sale_notax_amt;
	}
	public void setSale_notax_amt(double sale_notax_amt) {
		this.sale_notax_amt = sale_notax_amt;
	}
	public long getSale_qty() {
		return sale_qty;
	}
	public void setSale_qty(long sale_qty) {
		this.sale_qty = sale_qty;
	}
	public double getSale_tax_amt() {
		return sale_tax_amt;
	}
	public void setSale_tax_amt(double sale_tax_amt) {
		this.sale_tax_amt = sale_tax_amt;
	}
	public double getSale_vat() {
		return sale_vat;
	}
	public void setSale_vat(double sale_vat) {
		this.sale_vat = sale_vat;
	}
	public double getSonik_amt() {
		return sonik_amt;
	}
	public void setSonik_amt(double sonik_amt) {
		this.sonik_amt = sonik_amt;
	}
	public double getTax_amt() {
		return tax_amt;
	}
	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}
	public double getTot_purchase_amt() {
		return tot_purchase_amt;
	}
	public void setTot_purchase_amt(double tot_purchase_amt) {
		this.tot_purchase_amt = tot_purchase_amt;
	}
	public long getTot_qty() {
		return tot_qty;
	}
	public void setTot_qty(long tot_qty) {
		this.tot_qty = tot_qty;
	}
	public String getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public double getYoubo_amt() {
		return youbo_amt;
	}
	public void setYoubo_amt(double youbo_amt) {
		this.youbo_amt = youbo_amt;
	}
	public String getYoubo_memo() {
		return youbo_memo;
	}
	public void setYoubo_memo(String youbo_memo) {
		this.youbo_memo = youbo_memo;
	}
    
} 