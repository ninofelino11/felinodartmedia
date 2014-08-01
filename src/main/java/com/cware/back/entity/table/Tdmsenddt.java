
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdmsenddt extends BaseEntity {

    public Tdmsenddt(){ super();}
    
    private String dm_send_no;
    private String dm_code;
    private String dm_year;
    private String send_seq;
    private String cust_no;
    private String receiver_seq;
    private String dm_quest_yn;
    private String dm_proc_yn;
    private String dm_send_date;
    private String dm_quest_code;
    private String post_gb;
    private String dm_return_yn;
    private String dm_return_date;
    private String dm_return_id;
    private String dm_return_code;
    private String insert_date;
    private String insert_id;
    private String cust_gb;
    private String memb_gb;
    private String country;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getDm_code() {
		return dm_code;
	}
	public void setDm_code(String dm_code) {
		this.dm_code = dm_code;
	}
	public String getDm_proc_yn() {
		return dm_proc_yn;
	}
	public void setDm_proc_yn(String dm_proc_yn) {
		this.dm_proc_yn = dm_proc_yn;
	}
	public String getDm_quest_code() {
		return dm_quest_code;
	}
	public void setDm_quest_code(String dm_quest_code) {
		this.dm_quest_code = dm_quest_code;
	}
	public String getDm_quest_yn() {
		return dm_quest_yn;
	}
	public void setDm_quest_yn(String dm_quest_yn) {
		this.dm_quest_yn = dm_quest_yn;
	}
	public String getDm_return_code() {
		return dm_return_code;
	}
	public void setDm_return_code(String dm_return_code) {
		this.dm_return_code = dm_return_code;
	}
	public String getDm_return_date() {
		return dm_return_date;
	}
	public void setDm_return_date(String dm_return_date) {
		this.dm_return_date = dm_return_date;
	}
	public String getDm_return_id() {
		return dm_return_id;
	}
	public void setDm_return_id(String dm_return_id) {
		this.dm_return_id = dm_return_id;
	}
	public String getDm_return_yn() {
		return dm_return_yn;
	}
	public void setDm_return_yn(String dm_return_yn) {
		this.dm_return_yn = dm_return_yn;
	}
	public String getDm_send_date() {
		return dm_send_date;
	}
	public void setDm_send_date(String dm_send_date) {
		this.dm_send_date = dm_send_date;
	}
	public String getDm_send_no() {
		return dm_send_no;
	}
	public void setDm_send_no(String dm_send_no) {
		this.dm_send_no = dm_send_no;
	}
	public String getDm_year() {
		return dm_year;
	}
	public void setDm_year(String dm_year) {
		this.dm_year = dm_year;
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
	public String getPost_gb() {
		return post_gb;
	}
	public void setPost_gb(String post_gb) {
		this.post_gb = post_gb;
	}
	public String getReceiver_seq() {
		return receiver_seq;
	}
	public void setReceiver_seq(String receiver_seq) {
		this.receiver_seq = receiver_seq;
	}
	public String getSend_seq() {
		return send_seq;
	}
	public void setSend_seq(String send_seq) {
		this.send_seq = send_seq;
	}
  
} 