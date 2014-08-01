package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tdirectcost extends BaseEntity{
	
	private String yymm;
	private String cost_code;
	private String cost_flag;
	private double cost_value;
	private String remark;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	
	public Tdirectcost(){
		super();
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getCost_code() {
		return cost_code;
	}

	public void setCost_code(String costCode) {
		cost_code = costCode;
	}

	public String getCost_flag() {
		return cost_flag;
	}

	public void setCost_flag(String costFlag) {
		cost_flag = costFlag;
	}

	public double getCost_value() {
		return cost_value;
	}

	public void setCost_value(double costValue) {
		cost_value = costValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInsert_id() {
		return insert_id;
	}

	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}

	public String getModify_id() {
		return modify_id;
	}

	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	
	

}
