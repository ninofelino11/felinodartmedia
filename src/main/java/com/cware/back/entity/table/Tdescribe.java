
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품기술서
*
* @version 1.0, 2009/06/08
* @author Commerceware Ins.
*/
public class Tdescribe extends BaseEntity {

    public Tdescribe(){ super();}

    private String goods_code;
    private String describe_code;
    private String describe_title;
    private String describe_note;
    private String describe_ext;
    private String web_flag;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getDescribe_code() {
		return describe_code;
	}
	public void setDescribe_code(String describe_code) {
		this.describe_code = describe_code;
	}
	public String getDescribe_ext() {
		return describe_ext;
	}
	public void setDescribe_ext(String describe_ext) {
		this.describe_ext = describe_ext;
	}
	public String getDescribe_note() {
		return describe_note;
	}
	public void setDescribe_note(String describe_note) {
		this.describe_note = describe_note;
	}
	public String getDescribe_title() {
		return describe_title;
	}
	public void setDescribe_title(String describe_title) {
		this.describe_title = describe_title;
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
	public String getWeb_flag() {
		return web_flag;
	}
	public void setWeb_flag(String web_flag) {
		this.web_flag = web_flag;
	}




}