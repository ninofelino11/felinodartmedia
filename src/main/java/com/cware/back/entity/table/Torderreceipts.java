
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문결제
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Torderreceipts extends BaseEntity implements Comparable {

    public Torderreceipts(){ super();}

    //= compare
    private Torderreceipts od = null;
    public int compareTo(Object o){
        int  rtn = 0;
    	od = (Torderreceipts)o;
    	rtn = (this.getSettle_gb()).compareTo(od.getSettle_gb());
    	return rtn;
    }

    private String receipt_no;
    private String order_no;
    private String cust_no;
    private String do_flag;
    private String settle_gb;
    private String card_bank_code;
    private String bank_seq;
    private String card_name;
    private String card_no;
    private String cvv;
    private String depositor;
    private String valid_date;
    private double quest_amt;
    private String receipt_plan_date;
    private String ok_no;
    private String ok_date;
    private String ok_med;
    private String ok_error_code;
    private String van_comp;
    private long   pay_month;
    private String norest_yn;
    private double norest_rate;
    private double norest_amt;
    private double receipt_amt;
    private String receipt_date;
    private String end_yn;
    private double repay_pb_amt;
    private String cancel_yn;
    private String cancel_code;
    private String cancel_date;
    private String cancel_id;
    private String saveamt_use_flag;
    private String cod_dely_yn;
    private String divide_yn;
    private String remark1_v;
    private String remark2_v;
    private long   remark3_n;
    private long   remark4_n;
    private double remark5_d;
    private double remark6_d;
    private String remark;
    private String insert_date;
    private String insert_id;    
    private String proc_date;
    private String proc_id;    
    
    /* extra property */
    private String do_flag_org;
    private double repay_pb_amt_org;
    private String depositamt_gb;
    private String saveamt_gb;
    private String family_gb;
    private String shop_yn = "0";
    
	public Torderreceipts getOd() {
		return od;
	}
	public void setOd(Torderreceipts od) {
		this.od = od;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
	}
	public String getSettle_gb() {
		return settle_gb;
	}
	public void setSettle_gb(String settle_gb) {
		this.settle_gb = settle_gb;
	}
	public String getCard_bank_code() {
		return card_bank_code;
	}
	public void setCard_bank_code(String card_bank_code) {
		this.card_bank_code = card_bank_code;
	}
	public String getBank_seq() {
		return bank_seq;
	}
	public void setBank_seq(String bank_seq) {
		this.bank_seq = bank_seq;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getDepositor() {
		return depositor;
	}
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}
	public String getValid_date() {
		return valid_date;
	}
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	public double getQuest_amt() {
		return quest_amt;
	}
	public void setQuest_amt(double quest_amt) {
		this.quest_amt = quest_amt;
	}
	public String getReceipt_plan_date() {
		return receipt_plan_date;
	}
	public void setReceipt_plan_date(String receipt_plan_date) {
		this.receipt_plan_date = receipt_plan_date;
	}
	public String getOk_no() {
		return ok_no;
	}
	public void setOk_no(String ok_no) {
		this.ok_no = ok_no;
	}
	public String getOk_date() {
		return ok_date;
	}
	public void setOk_date(String ok_date) {
		this.ok_date = ok_date;
	}
	public String getOk_med() {
		return ok_med;
	}
	public void setOk_med(String ok_med) {
		this.ok_med = ok_med;
	}
	public String getOk_error_code() {
		return ok_error_code;
	}
	public void setOk_error_code(String ok_error_code) {
		this.ok_error_code = ok_error_code;
	}
	public String getVan_comp() {
		return van_comp;
	}
	public void setVan_comp(String van_comp) {
		this.van_comp = van_comp;
	}
	public long getPay_month() {
		return pay_month;
	}
	public void setPay_month(long pay_month) {
		this.pay_month = pay_month;
	}
	public String getNorest_yn() {
		return norest_yn;
	}
	public void setNorest_yn(String norest_yn) {
		this.norest_yn = norest_yn;
	}
	public double getNorest_rate() {
		return norest_rate;
	}
	public void setNorest_rate(double norest_rate) {
		this.norest_rate = norest_rate;
	}
	public double getNorest_amt() {
		return norest_amt;
	}
	public void setNorest_amt(double norest_amt) {
		this.norest_amt = norest_amt;
	}
	public double getReceipt_amt() {
		return receipt_amt;
	}
	public void setReceipt_amt(double receipt_amt) {
		this.receipt_amt = receipt_amt;
	}
	public String getReceipt_date() {
		return receipt_date;
	}
	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
	}
	public String getEnd_yn() {
		return end_yn;
	}
	public void setEnd_yn(String end_yn) {
		this.end_yn = end_yn;
	}
	public double getRepay_pb_amt() {
		return repay_pb_amt;
	}
	public void setRepay_pb_amt(double repay_pb_amt) {
		this.repay_pb_amt = repay_pb_amt;
	}
	public String getCancel_yn() {
		return cancel_yn;
	}
	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}
	public String getCancel_code() {
		return cancel_code;
	}
	public void setCancel_code(String cancel_code) {
		this.cancel_code = cancel_code;
	}
	public String getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(String cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_id() {
		return cancel_id;
	}
	public void setCancel_id(String cancel_id) {
		this.cancel_id = cancel_id;
	}
	public String getSaveamt_use_flag() {
		return saveamt_use_flag;
	}
	public void setSaveamt_use_flag(String saveamt_use_flag) {
		this.saveamt_use_flag = saveamt_use_flag;
	}
	public String getCod_dely_yn() {
		return cod_dely_yn;
	}
	public void setCod_dely_yn(String cod_dely_yn) {
		this.cod_dely_yn = cod_dely_yn;
	}
	public String getDivide_yn() {
		return divide_yn;
	}
	public void setDivide_yn(String divide_yn) {
		this.divide_yn = divide_yn;
	}
	public String getRemark1_v() {
		return remark1_v;
	}
	public void setRemark1_v(String remark1_v) {
		this.remark1_v = remark1_v;
	}
	public String getRemark2_v() {
		return remark2_v;
	}
	public void setRemark2_v(String remark2_v) {
		this.remark2_v = remark2_v;
	}
	public long getRemark3_n() {
		return remark3_n;
	}
	public void setRemark3_n(long remark3_n) {
		this.remark3_n = remark3_n;
	}
	public long getRemark4_n() {
		return remark4_n;
	}
	public void setRemark4_n(long remark4_n) {
		this.remark4_n = remark4_n;
	}
	public double getRemark5_d() {
		return remark5_d;
	}
	public void setRemark5_d(double remark5_d) {
		this.remark5_d = remark5_d;
	}
	public double getRemark6_d() {
		return remark6_d;
	}
	public void setRemark6_d(double remark6_d) {
		this.remark6_d = remark6_d;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getProc_date() {
		return proc_date;
	}
	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}
	public String getProc_id() {
		return proc_id;
	}
	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}
	public String getDo_flag_org() {
		return do_flag_org;
	}
	public void setDo_flag_org(String do_flag_org) {
		this.do_flag_org = do_flag_org;
	}
	public double getRepay_pb_amt_org() {
		return repay_pb_amt_org;
	}
	public void setRepay_pb_amt_org(double repay_pb_amt_org) {
		this.repay_pb_amt_org = repay_pb_amt_org;
	}
	public String getDepositamt_gb() {
		return depositamt_gb;
	}
	public void setDepositamt_gb(String depositamt_gb) {
		this.depositamt_gb = depositamt_gb;
	}
	public String getSaveamt_gb() {
		return saveamt_gb;
	}
	public void setSaveamt_gb(String saveamt_gb) {
		this.saveamt_gb = saveamt_gb;
	}
	public String getFamily_gb() {
		return family_gb;
	}
	public void setFamily_gb(String family_gb) {
		this.family_gb = family_gb;
	}
	public String getShop_yn() {
		return shop_yn;
	}
	public void setShop_yn(String shop_yn) {
		this.shop_yn = shop_yn;
	}    

}