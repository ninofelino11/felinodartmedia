
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 적립금 부여내역
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsaveget extends BaseEntity {

    public Tsaveget(){ super();}

    private String cust_no;
    private String saveamt_seq;
    private String proc_gb;
    private String proc_yn;
    private String trans_cust_no;
    private String use_seq;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private String saveamt_gb;
    private String saveamt_code;
    private String save_note;
    private double saveamt;
    private String proc_id;
    private String proc_date;
    private String remark1_v;
    private String remark2_v;
    private long   remark3_n;
    private long   remark4_n;
    private String remark5_d;
    private String remark6_d;
    private String due_date;
    private double usable_amt;
    private String expire_flag;

    //= user add property
    private long   limit_day;

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public long getLimit_day() {
		return limit_day;
	}

	public void setLimit_day(long limit_day) {
		this.limit_day = limit_day;
	}

	public String getOrder_d_seq() {
		return order_d_seq;
	}

	public void setOrder_d_seq(String order_d_seq) {
		this.order_d_seq = order_d_seq;
	}

	public String getOrder_g_seq() {
		return order_g_seq;
	}

	public void setOrder_g_seq(String order_g_seq) {
		this.order_g_seq = order_g_seq;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrder_w_seq() {
		return order_w_seq;
	}

	public void setOrder_w_seq(String order_w_seq) {
		this.order_w_seq = order_w_seq;
	}

	public String getProc_date() {
		return proc_date;
	}

	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}

	public String getProc_gb() {
		return proc_gb;
	}

	public void setProc_gb(String proc_gb) {
		this.proc_gb = proc_gb;
	}

	public String getProc_id() {
		return proc_id;
	}

	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}

	public String getProc_yn() {
		return proc_yn;
	}

	public void setProc_yn(String proc_yn) {
		this.proc_yn = proc_yn;
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

	public String getRemark5_d() {
		return remark5_d;
	}

	public void setRemark5_d(String remark5_d) {
		this.remark5_d = remark5_d;
	}

	public String getRemark6_d() {
		return remark6_d;
	}

	public void setRemark6_d(String remark6_d) {
		this.remark6_d = remark6_d;
	}

	public String getSave_note() {
		return save_note;
	}

	public void setSave_note(String save_note) {
		this.save_note = save_note;
	}

	public double getSaveamt() {
		return saveamt;
	}

	public void setSaveamt(double saveamt) {
		this.saveamt = saveamt;
	}

	public String getSaveamt_code() {
		return saveamt_code;
	}

	public void setSaveamt_code(String saveamt_code) {
		this.saveamt_code = saveamt_code;
	}

	public String getSaveamt_gb() {
		return saveamt_gb;
	}

	public void setSaveamt_gb(String saveamt_gb) {
		this.saveamt_gb = saveamt_gb;
	}

	public String getSaveamt_seq() {
		return saveamt_seq;
	}

	public void setSaveamt_seq(String saveamt_seq) {
		this.saveamt_seq = saveamt_seq;
	}

	public String getTrans_cust_no() {
		return trans_cust_no;
	}

	public void setTrans_cust_no(String trans_cust_no) {
		this.trans_cust_no = trans_cust_no;
	}

	public String getUse_seq() {
		return use_seq;
	}

	public void setUse_seq(String use_seq) {
		this.use_seq = use_seq;
	}

	public double getUsable_amt() {
		return usable_amt;
	}

	public void setUsable_amt(double usable_amt) {
		this.usable_amt = usable_amt;
	}

	public String getExpire_flag() {
		return expire_flag;
	}

	public void setExpire_flag(String expire_flag) {
		this.expire_flag = expire_flag;
	}







}