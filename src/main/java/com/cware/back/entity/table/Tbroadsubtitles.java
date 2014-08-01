package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 브랜드정보
*
* @version 1.0, 2006/08/12
* @author Commerceware Ins.
*/
public class Tbroadsubtitles extends BaseEntity {

	public Tbroadsubtitles(){ super();}
	
	private String newYn;
    private String seq_frame_no;
    private String staff_flag;
    private String staff_opinion;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    
	public String getNewYn() {
		return newYn;
	}
	public void setNewYn(String newYn) {
		this.newYn = newYn;
	}
	public String getSeq_frame_no() {
		return seq_frame_no;
	}
	public void setSeq_frame_no(String seq_frame_no) {
		this.seq_frame_no = seq_frame_no;
	}
	public String getStaff_flag() {
		return staff_flag;
	}
	public void setStaff_flag(String staff_flag) {
		this.staff_flag = staff_flag;
	}
	public String getStaff_opinion() {
		return staff_opinion;
	}
	public void setStaff_opinion(String staff_opinion) {
		this.staff_opinion = staff_opinion;
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

}