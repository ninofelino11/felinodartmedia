package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tolivemagazinem extends BaseEntity  {
	
	
	public Tolivemagazinem(){ super();}
	
	private String magazine_no;                
	private String magazine_title;             
	private String magazine_content;           
	private String magazine_image_l;           
	private String magazine_image_m;           
	private String magazine_image_s;           
	private String magazine_month;             
	private String display_yn;                 
	private String display_begin_date;         
	private String display_end_date;           
	private String delete_yn;                  
	private String display_priority;           
	private String remark ;                    
	private String remark1_v;                  
	private String remark2_v;                  
	private String remark3_n;                  
	private String remark4_n;                  
	private String insert_id;                  
	private String insert_date;                
	private String modify_id    ;              
	private String modify_date   ;
	
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public String getDisplay_begin_date() {
		return display_begin_date;
	}
	public void setDisplay_begin_date(String display_begin_date) {
		this.display_begin_date = display_begin_date;
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
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
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
	public String getMagazine_content() {
		return magazine_content;
	}
	public void setMagazine_content(String magazine_content) {
		this.magazine_content = magazine_content;
	}
	public String getMagazine_image_l() {
		return magazine_image_l;
	}
	public void setMagazine_image_l(String magazine_image_l) {
		this.magazine_image_l = magazine_image_l;
	}
	public String getMagazine_image_m() {
		return magazine_image_m;
	}
	public void setMagazine_image_m(String magazine_image_m) {
		this.magazine_image_m = magazine_image_m;
	}
	public String getMagazine_image_s() {
		return magazine_image_s;
	}
	public void setMagazine_image_s(String magazine_image_s) {
		this.magazine_image_s = magazine_image_s;
	}
	public String getMagazine_month() {
		return magazine_month;
	}
	public void setMagazine_month(String magazine_month) {
		this.magazine_month = magazine_month;
	}
	public String getMagazine_no() {
		return magazine_no;
	}
	public void setMagazine_no(String magazine_no) {
		this.magazine_no = magazine_no;
	}
	public String getMagazine_title() {
		return magazine_title;
	}
	public void setMagazine_title(String magazine_title) {
		this.magazine_title = magazine_title;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark1_v() {
		return remark1_v;
	}
	public void setRemark1_v(String remark1_v) {
		this.remark1_v = remark1_v;
	}
	public String getRemark2_v() {
		return remark2_v;
	}
	public void setRemark2_v(String remark2_v) {
		this.remark2_v = remark2_v;
	}
	public String getRemark3_n() {
		return remark3_n;
	}
	public void setRemark3_n(String remark3_n) {
		this.remark3_n = remark3_n;
	}
	public String getRemark4_n() {
		return remark4_n;
	}
	public void setRemark4_n(String remark4_n) {
		this.remark4_n = remark4_n;
	}             

}
