
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;


public class Teventapply extends BaseEntity { 
	
	public Teventapply(){ super();}                                                            
    
    private String apply_seq;                                                               
    private String event_no;                                                             
    private String cust_no;   
    private String memb_no;
    private String mem_id;
    private String order_no;
    private String ip_addr;
    private String remark;
    private String remark1_v;
    private String remark2_v;
    private String remark3_v;
    private String remark4_v;
    private String score;
    private String allow_yn;
    private String insert_date;
    
	public String getAllow_yn() {
		return allow_yn;
	}
	public void setAllow_yn(String allow_yn) {
		this.allow_yn = allow_yn;
	}
	public String getApply_seq() {
		return apply_seq;
	}
	public void setApply_seq(String apply_seq) {
		this.apply_seq = apply_seq;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMemb_no() {
		return memb_no;
	}
	public void setMemb_no(String memb_no) {
		this.memb_no = memb_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getRemark3_v() {
		return remark3_v;
	}
	public void setRemark3_v(String remark3_v) {
		this.remark3_v = remark3_v;
	}
	public String getRemark4_v() {
		return remark4_v;
	}
	public void setRemark4_v(String remark4_v) {
		this.remark4_v = remark4_v;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
