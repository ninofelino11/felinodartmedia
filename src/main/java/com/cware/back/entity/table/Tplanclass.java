package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* tplanclass
* @version 1.0, 2009/07/22
*/
public class Tplanclass  extends BaseEntity{

	public Tplanclass(){ super();}

    private String planclass_code;
    private String planclass_name;
    private String category_code;
    private String use_yn;
    private String image_url;
    private String target_url;
    private String template_code;
    private String content;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getPlanclass_code() {
		return planclass_code;
	}
	public void setPlanclass_code(String planclass_code) {
		this.planclass_code = planclass_code;
	}
	public String getPlanclass_name() {
		return planclass_name;
	}
	public void setPlanclass_name(String planclass_name) {
		this.planclass_name = planclass_name;
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
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}


}
