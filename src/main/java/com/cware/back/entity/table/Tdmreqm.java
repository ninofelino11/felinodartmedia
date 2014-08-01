
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdmreqm extends BaseEntity {

    public Tdmreqm(){ super();}

    private String dmreq_no;
    private String memb_gb;
    private String cust_gb;
    private String cust_no;
    private String receiver_seq;
    private String order_no;
    private String send_way;
    private String cancel_yn;
    private String cancel_code;
    private String proc_date;
    private String proc_id;
    private String send_yn;
    private String slip_i_no;
    private String return_yn;
    private String return_code;
    private String return_date;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String send_date;



	public String getCancel_code() {
		return cancel_code;
	}
	public void setCancel_code(String cancel_code) {
		this.cancel_code = cancel_code;
	}
	public String getCancel_yn() {
		return cancel_yn;
	}
	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}
	public String getCust_gb() {
		return cust_gb;
	}
	public void setCust_gb(String cust_gb) {
		this.cust_gb = cust_gb;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getDmreq_no() {
		return dmreq_no;
	}
	public void setDmreq_no(String dmreq_no) {
		this.dmreq_no = dmreq_no;
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
	public String getMemb_gb() {
		return memb_gb;
	}
	public void setMemb_gb(String memb_gb) {
		this.memb_gb = memb_gb;
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
	public String getReceiver_seq() {
		return receiver_seq;
	}
	public void setReceiver_seq(String receiver_seq) {
		this.receiver_seq = receiver_seq;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getReturn_yn() {
		return return_yn;
	}
	public void setReturn_yn(String return_yn) {
		this.return_yn = return_yn;
	}
	public String getSend_way() {
		return send_way;
	}
	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}
	public String getSend_yn() {
		return send_yn;
	}
	public void setSend_yn(String send_yn) {
		this.send_yn = send_yn;
	}
	public String getSlip_i_no() {
		return slip_i_no;
	}
	public void setSlip_i_no(String slip_i_no) {
		this.slip_i_no = slip_i_no;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String sendDate) {
		send_date = sendDate;
	}

}