package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* tplan
* @version 1.0, 2009/07/22
*/
public class Tplan  extends BaseEntity{

	public Tplan(){ super();}

    private String planclass_code;
    private String plan_code;
    private String plan_name;
    private String display_priority;
    private String display_yn;
    private String display_start_date;
    private String display_end_date;
    private String mgnt_id;
    private String plan_gb;
    private String content;
    private String image_url;
    private String target_url;
    private String template_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDisplay_end_date() {
		return display_end_date;
	}
	public void setDisplay_end_date(String display_end_date) {
		this.display_end_date = display_end_date;
	}
	public String getDisplay_priority() {
		return display_priority;
	}
	public void setDisplay_priority(String display_priority) {
		this.display_priority = display_priority;
	}
	public String getDisplay_start_date() {
		return display_start_date;
	}
	public void setDisplay_start_date(String display_start_date) {
		this.display_start_date = display_start_date;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public String getMgnt_id() {
		return mgnt_id;
	}
	public void setMgnt_id(String mgnt_id) {
		this.mgnt_id = mgnt_id;
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
	public String getPlan_gb() {
		return plan_gb;
	}
	public void setPlan_gb(String plan_gb) {
		this.plan_gb = plan_gb;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getPlanclass_code() {
		return planclass_code;
	}
	public void setPlanclass_code(String planclass_code) {
		this.planclass_code = planclass_code;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}
	public String getTemplate_code() {
		return template_code;
	}
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

}
