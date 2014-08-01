package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tareazone extends BaseEntity {
	
	public Tareazone() {
		super();
	}
	
	private String area_gb;
	private String area_name;
	private String dely_gb;
	private String wh_code;
	private String main_wh_code;
	private String remark;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	
	public void setArea_gb(String areaGb) {
		area_gb = areaGb;
	}
	public void setArea_name(String areaName) {
		area_name = areaName;
	}
	public void setDely_gb(String delyGb) {
		dely_gb = delyGb;
	}
	public void setWh_code(String whCode) {
		wh_code = whCode;
	}
	public void setMain_wh_code(String mainWhCode) {
		main_wh_code = mainWhCode;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
	
	public String getArea_gb() {
		return area_gb;
	}
	public String getArea_name() {
		return area_name;
	}
	public String getDely_gb() {
		return dely_gb;
	}
	public String getWh_code() {
		return wh_code;
	}
	public String getMain_wh_code() {
		return main_wh_code;
	}
	public String getRemark() {
		return remark;
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
