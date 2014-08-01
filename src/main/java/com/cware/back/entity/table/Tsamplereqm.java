
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsamplereqm extends BaseEntity {

    public Tsamplereqm(){ super();}

    private String samplereq_no;
    private String samplereq_date;
    private String samplereq_type;
    private String add_yn;
    private String cust_no;
    private String receiver_seq;
    private String sample_no;
    private String send_way;
    private String send_date;
    private String cancel_yn;
    private String cancel_code;
    private String proc_date;
    private String proc_id;
    private String reproc_date;
    private String return_yn;
    private String return_code;
    private String return_date;
    private String order_no;
    private String slip_i_no;
    private String insert_id;

    /** Set Method **/
	public void setSamplereq_no(String samplereq_no) { this.samplereq_no = samplereq_no; }
	public void setSamplereq_date(String samplereq_date) { this.samplereq_date = samplereq_date; }
	public void setSamplereq_type(String samplereq_type) { this.samplereq_type = samplereq_type; }
    public void setAdd_yn(String add_yn) { this.add_yn = add_yn; }
	public void setCust_no(String cust_no) { this.cust_no = cust_no; }
	public void setReceiver_seq(String receiver_seq) { this.receiver_seq = receiver_seq; }
    public void setSample_no(String sample_no) { this.sample_no = sample_no; }
	public void setSend_way(String send_way) { this.send_way = send_way; }
	public void setSend_date(String send_date) { this.send_date = send_date; }
	public void setCancel_yn(String cancel_yn) { this.cancel_yn = cancel_yn; }
	public void setCancel_code(String cancel_code) { this.cancel_code = cancel_code; }
	public void setProc_date(String proc_date) { this.proc_date = proc_date; }
	public void setProc_id(String proc_id) { this.proc_id = proc_id; }
	public void setReproc_date(String reproc_date) { this.reproc_date = reproc_date; }
	public void setReturn_yn(String return_yn) { this.return_yn = return_yn; }
	public void setReturn_date(String return_date) { this.return_date = return_date; }
	public void setReturn_code(String return_code) { this.return_code = return_code; }
	public void setOrder_no(String order_no) { this.order_no = order_no; }
	public void setSlip_i_no(String slip_i_no) { this.slip_i_no = slip_i_no; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
	public String getSamplereq_no() { return samplereq_no; }
	public String getSamplereq_date() { return samplereq_date; }
	public String getSamplereq_type() { return samplereq_type; }
    public String getAdd_yn() { return add_yn; }
	public String getCust_no() { return cust_no; }
	public String getReceiver_seq() { return receiver_seq; }
    public String getSample_no() { return sample_no; }
	public String getSend_way() { return send_way; }
	public String getSend_date() { return send_date; }
	public String getCancel_yn() { return cancel_yn; }
    public String getCancel_code() { return cancel_code; }
	public String getProc_date() { return proc_date; }
	public String getProc_id() { return proc_id; }
	public String getReproc_date() { return reproc_date; }
	public String getReturn_yn() { return return_yn; }
	public String getReturn_date() { return return_date; }
	public String getReturn_code() { return return_code; }
	public String getOrder_no() { return order_no; }
	public String getSlip_i_no() { return slip_i_no; }
	public String getInsert_id(){ return insert_id; }
	
	/** Extra property **/
    private String send_yn;
    public void setSend_yn( String send_yn ){ this.send_yn = send_yn; }
	public String getSend_yn(){ return send_yn; }

} 