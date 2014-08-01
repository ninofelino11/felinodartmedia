package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* tplangoods
* @version 1.0, 2009/07/22
*/
public class Tplangoods  extends BaseEntity{

	public Tplangoods(){ super();}

    private String planclass_code;
    private String plan_code;
    private String goods_code;
    private String display_priority;
    private String display_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getDisplay_priority() {
		return display_priority;
	}
	public void setDisplay_priority(String display_priority) {
		this.display_priority = display_priority;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
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
	public String getPlan_code() {
		return plan_code;
	}
	public void setPlan_code(String plan_code) {
		this.plan_code = plan_code;
	}
	public String getPlanclass_code() {
		return planclass_code;
	}
	public void setPlanclass_code(String planclass_code) {
		this.planclass_code = planclass_code;
	}


}
