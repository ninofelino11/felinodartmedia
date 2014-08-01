
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* User 정보
*
* @version 1.0, 2006/06/14
* @author commerceware.co.kr
*/
public class Tuser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Tuser(){ super();}

    private String user_id;
    private String user_name;
    private String passwd;
    private String dept_code;
    private String sabun;
    private String start_date;
    private String end_date;
    private String user_gb;
    private String max_dc_rate;
    private String resident_no;
    private String sex;
    private String major_group;
    private String minor_group;
    private String icd_no;
    private String agent_level;
    private String agent_skill1;
    private String agent_skill2;
    private String agent_skill3;
    private String pbx_login_id;
    private String worker;
    private String dh_wk;
    private String remark;
    private String locale;
    private String locale_name;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /* add Shop info 200706.04 */
    private String shop_code;
    private String shop_name;
    private String wh_code;
    private String shop_cust_no;
    private String post_no;
    private String post_seq;
    private String addr;
    private String order_media;
    private String dely_gb;
    private String receiver_gb;
    private String bank_code;
    private String bank_seq;

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAgent_level() {
		return agent_level;
	}
	public void setAgent_level(String agent_level) {
		this.agent_level = agent_level;
	}
	public String getAgent_skill1() {
		return agent_skill1;
	}
	public void setAgent_skill1(String agent_skill1) {
		this.agent_skill1 = agent_skill1;
	}
	public String getAgent_skill2() {
		return agent_skill2;
	}
	public void setAgent_skill2(String agent_skill2) {
		this.agent_skill2 = agent_skill2;
	}
	public String getAgent_skill3() {
		return agent_skill3;
	}
	public void setAgent_skill3(String agent_skill3) {
		this.agent_skill3 = agent_skill3;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_seq() {
		return bank_seq;
	}
	public void setBank_seq(String bank_seq) {
		this.bank_seq = bank_seq;
	}
	public String getDely_gb() {
		return dely_gb;
	}
	public void setDely_gb(String dely_gb) {
		this.dely_gb = dely_gb;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getDh_wk() {
		return dh_wk;
	}
	public void setDh_wk(String dh_wk) {
		this.dh_wk = dh_wk;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getIcd_no() {
		return icd_no;
	}
	public void setIcd_no(String icd_no) {
		this.icd_no = icd_no;
	}
	public String getLocale() {
		return this.locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getLocaleName() {
		return this.locale_name;
	}
	public void setLocaleName(String locale_name) {
		this.locale_name = locale_name;
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
	public String getMajor_group() {
		return major_group;
	}
	public void setMajor_group(String major_group) {
		this.major_group = major_group;
	}
	public String getMax_dc_rate() {
		return max_dc_rate;
	}
	public void setMax_dc_rate(String max_dc_rate) {
		this.max_dc_rate = max_dc_rate;
	}
	public String getMinor_group() {
		return minor_group;
	}
	public void setMinor_group(String minor_group) {
		this.minor_group = minor_group;
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
	public String getOrder_media() {
		return order_media;
	}
	public void setOrder_media(String order_media) {
		this.order_media = order_media;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPbx_login_id() {
		return pbx_login_id;
	}
	public void setPbx_login_id(String pbx_login_id) {
		this.pbx_login_id = pbx_login_id;
	}
	public String getPost_no() {
		return post_no;
	}
	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}
	public String getPost_seq() {
		return post_seq;
	}
	public void setPost_seq(String post_seq) {
		this.post_seq = post_seq;
	}
	public String getReceiver_gb() {
		return receiver_gb;
	}
	public void setReceiver_gb(String receiver_gb) {
		this.receiver_gb = receiver_gb;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResident_no() {
		return resident_no;
	}
	public void setResident_no(String resident_no) {
		this.resident_no = resident_no;
	}
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getShop_code() {
		return shop_code;
	}
	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}
	public String getShop_cust_no() {
		return shop_cust_no;
	}
	public void setShop_cust_no(String shop_cust_no) {
		this.shop_cust_no = shop_cust_no;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getUser_gb() {
		return user_gb;
	}
	public void setUser_gb(String user_gb) {
		this.user_gb = user_gb;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getWh_code() {
		return wh_code;
	}
	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
}