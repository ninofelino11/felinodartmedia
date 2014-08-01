
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 설문등록m
*
* @version 1.0, 2007/02/21
* @author Commerceware Ins.
*/
public class Tresearchm extends BaseEntity {

    public Tresearchm(){ super();}

    private String research_no;
    private String research_title;
    private String research_image;
    private String start_date;
    private String end_date;
    private String display_yn;
    private String point_amt;
    private String research_cnt;
    private String split_yn;
    private String insert_date;
    private String insert_id;
    
    
    /** Set Method **/    

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setPoint_amt(String point_amt) {
		this.point_amt = point_amt;
	}
	public void setResearch_cnt(String research_cnt) {
		this.research_cnt = research_cnt;
	}
	public void setResearch_image(String research_image) {
		this.research_image = research_image;
	}
	public void setResearch_no(String research_no) {
		this.research_no = research_no;
	}
	public void setResearch_title(String research_title) {
		this.research_title = research_title;
	}
	public void setSplit_yn(String split_yn) {
		this.split_yn = split_yn;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}	


    /** Get Method **/
    
	public String getEnd_date() {
		return end_date;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getPoint_amt() {
		return point_amt;
	}
	public String getResearch_cnt() {
		return research_cnt;
	}
	public String getResearch_image() {
		return research_image;
	}
	public String getResearch_no() {
		return research_no;
	}
	public String getResearch_title() {
		return research_title;
	}	
	public String getSplit_yn() {
		return split_yn;
	}
	public String getStart_date() {
		return start_date;
	}
	public String getDisplay_yn() {
		return display_yn;
	}	

} 