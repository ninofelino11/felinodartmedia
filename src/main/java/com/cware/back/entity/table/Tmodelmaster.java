package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Boradcast Model Master
*
* @version 1.0, 2011/01/28
* @author Commerceware Ins.
*/
public class Tmodelmaster extends BaseEntity{
	
	public Tmodelmaster(){
		super();
	}
	
	private String model_no;
	private String model_name;
	private String model_eng_name;
	private String model_flag;
	private String entp_code;
	private String country_code;
	private String remark;
	private String use_yn;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	
	public void setModel_no(String modelNo) {
		model_no = modelNo;
	}
	public void setModel_name(String modelName) {
		model_name = modelName;
	}
	public void setModel_eng_name(String modelEngName) {
		model_eng_name = modelEngName;
	}
	public void setModel_flag(String modelFlag) {
		model_flag = modelFlag;
	}
	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
	}
	public void setCountry_code(String countryCode) {
		country_code = countryCode;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setUse_yn(String useYn) {
		use_yn = useYn;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	
	public String getModel_no() {
		return model_no;
	}
	public String getModel_name() {
		return model_name;
	}
	public String getModel_eng_name() {
		return model_eng_name;
	}
	public String getModel_flag() {
		return model_flag;
	}
	public String getEntp_code() {
		return entp_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public String getRemark() {
		return remark;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public String getModify_date() {
		return modify_date;
	}

	
}
