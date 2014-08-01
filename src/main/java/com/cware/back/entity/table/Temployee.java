
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* User 정보
*
* @version 1.0, 2006/06/14
* @author commerceware.co.kr
*/
public class Temployee extends BaseEntity {

    public Temployee(){ super();}

    private String employee_id;
    private String employee_name;
    private String passwd;
    private String dept_code;
    private String sabun;
    private String start_date;
    private String end_date;
    private String user_gb;
    private double max_dc_rate;
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
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
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
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
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
	public double getMax_dc_rate() {
		return max_dc_rate;
	}
	public void setMax_dc_rate(double max_dc_rate) {
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
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
    
    
} 