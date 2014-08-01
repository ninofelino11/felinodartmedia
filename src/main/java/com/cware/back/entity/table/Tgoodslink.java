package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 연계상품
*
* @version 1.0, 2007/01/31
* @author Commerceware Ins.
*/


public class Tgoodslink extends BaseEntity {

    public Tgoodslink(){ super();}         
	
    private String link_gb;
    private String goods_code;
    private String link_goods_code;
    private String link_note;
    private String display_priority;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;        
	
    /** Set Method **/	
	public void setDisplay_priority(String display_priority) {
		this.display_priority = display_priority;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setLink_gb(String link_gb) {
		this.link_gb = link_gb;
	}
	public void setLink_goods_code(String link_goods_code) {
		this.link_goods_code = link_goods_code;
	}
	public void setLink_note(String link_note) {
		this.link_note = link_note;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}

    /** Get Method **/    
	public String getDisplay_priority() {
		return display_priority;
	}
	public String getGoods_code() {
		return goods_code;
	}	
	public String getInsert_date() {
		return insert_date;
	}	
	public String getInsert_id() {
		return insert_id;
	}	
	public String getLink_gb() {
		return link_gb;
	}	
	public String getLink_goods_code() {
		return link_goods_code;
	}	
	public String getLink_note() {
		return link_note;
	}	
	public String getModify_date() {
		return modify_date;
	}	
	public String getModify_id() {
		return modify_id;
	}		
	
}
