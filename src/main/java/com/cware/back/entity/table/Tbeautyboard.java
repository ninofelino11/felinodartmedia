
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 테마별 상품정보  
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Tbeautyboard extends BaseEntity {

    public Tbeautyboard(){ super();}

    private String beauty_no;    
    private String beauty_title; 
    private String beauty_content; 
    private String beauty_gubun; 
    private String beautyboard_image; 
    private String display_yn; 
    private String display_begin_date; 
    private String display_end_date; 
    private String main_display_yn; 
    private String sub_display_yn; 
    private String delete_yn; 
    private String beauty_type; 
    private String beauty_source; 
    private String beautyboard_pop_image; 
    private String display_priority ;
    private String remark ;
    private String remark1_v; 
    private String remark2_v; 
    private String remark3_n;
    private String remark4_n; 
    private String insert_id; 
    private String insert_date; 
    private String modify_id; 
    private String modify_date;
    
	public String getBeauty_content() {
		return beauty_content;
	}
	public void setBeauty_content(String beauty_content) {
		this.beauty_content = beauty_content;
	}
	public String getBeauty_gubun() {
		return beauty_gubun;
	}
	public void setBeauty_gubun(String beauty_gubun) {
		this.beauty_gubun = beauty_gubun;
	}
	public String getBeauty_no() {
		return beauty_no;
	}
	public void setBeauty_no(String beauty_no) {
		this.beauty_no = beauty_no;
	}
	public String getBeauty_source() {
		return beauty_source;
	}
	public void setBeauty_source(String beauty_source) {
		this.beauty_source = beauty_source;
	}
	public String getBeauty_title() {
		return beauty_title;
	}
	public void setBeauty_title(String beauty_title) {
		this.beauty_title = beauty_title;
	}
	public String getBeauty_type() {
		return beauty_type;
	}
	public void setBeauty_type(String beauty_type) {
		this.beauty_type = beauty_type;
	}
	public String getBeautyboard_image() {
		return beautyboard_image;
	}
	public void setBeautyboard_image(String beautyboard_image) {
		this.beautyboard_image = beautyboard_image;
	}
	public String getBeautyboard_pop_image() {
		return beautyboard_pop_image;
	}
	public void setBeautyboard_pop_image(String beautyboard_pop_image) {
		this.beautyboard_pop_image = beautyboard_pop_image;
	}
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
	public String getMain_display_yn() {
		return main_display_yn;
	}
	public void setMain_display_yn(String main_display_yn) {
		this.main_display_yn = main_display_yn;
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
	public String getSub_display_yn() {
		return sub_display_yn;
	}
	public void setSub_display_yn(String sub_display_yn) {
		this.sub_display_yn = sub_display_yn;
	}
}	
   
