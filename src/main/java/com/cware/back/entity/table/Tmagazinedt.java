package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tmagazinedt extends BaseEntity {

	public Tmagazinedt(){ super();}
	
	private String magazine_no;
	private String magazine_page;
	private String display_gubun;
	private String magazine_page_title;
	private String magazine_page_name;
	private String display_priority;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	private String old_magazine_page;
	private String old_display_gubun;

	
	public String getDisplay_gubun() {
		return display_gubun;
	}
	public void setDisplay_gubun(String display_gubun) {
		this.display_gubun = display_gubun;
	}
	public String getDisplay_priority() {
		return display_priority;
	}
	public void setDisplay_priority(String display_priority) {
		this.display_priority = display_priority;
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
	public String getMagazine_no() {
		return magazine_no;
	}
	public void setMagazine_no(String magazine_no) {
		this.magazine_no = magazine_no;
	}
	public String getMagazine_page() {
		return magazine_page;
	}
	public void setMagazine_page(String magazine_page) {
		this.magazine_page = magazine_page;
	}
	public String getMagazine_page_name() {
		return magazine_page_name;
	}
	public void setMagazine_page_name(String magazine_page_name) {
		this.magazine_page_name = magazine_page_name;
	}
	public String getMagazine_page_title() {
		return magazine_page_title;
	}
	public void setMagazine_page_title(String magazine_page_title) {
		this.magazine_page_title = magazine_page_title;
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
	public String getOld_magazine_page() {
		return old_magazine_page;
	}
	public void setOld_magazine_page(String old_magazine_page) {
		this.old_magazine_page = old_magazine_page;
	}
	
	public String getOld_display_gubun() {
		return old_display_gubun;
	}
	public void setOld_display_gubun(String old_display_gubun) {
		this.old_display_gubun = old_display_gubun;
	}
	
	
	
}
