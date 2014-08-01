
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 영수증요청
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Treceiptreq extends BaseEntity {

    public Treceiptreq(){ super();}

    private String order_no;
    private String cust_no;
    private String receiver_seq;
    private String insert_date;
    private String insert_id;
    private String proc_yn;
    private String proc_date;
    private String proc_id;
    private String cancel_yn;
    private String cancel_date;
    private String cancel_id;
    private String invoice_type;
    private String s_idno;
    private String corp_name;
    private String invoice_no;
    private String receive_date;
    private String email_addr;
    private String sex;
    
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
	public String getCancel_yn() {
		return cancel_yn;
	}
	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
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
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public String getProc_yn() {
		return proc_yn;
	}
	public void setProc_yn(String proc_yn) {
		this.proc_yn = proc_yn;
	}
	public String getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}
	public String getReceiver_seq() {
		return receiver_seq;
	}
	public void setReceiver_seq(String receiver_seq) {
		this.receiver_seq = receiver_seq;
	}
	public String getS_idno() {
		return s_idno;
	}
	public void setS_idno(String s_idno) {
		this.s_idno = s_idno;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}


} 