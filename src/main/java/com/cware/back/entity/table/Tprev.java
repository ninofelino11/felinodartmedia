
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tprev 정보
*
* @version 1.0, 2007/03/14
* @author  commerceware.co.kr
*/
public class Tprev extends BaseEntity {

    public Tprev(){ super();}

    private String prev_no;
    private String prev_gb;
    private String prev_bdate;
    private String prev_edate;
    private String prev_title;
    private String prev_note;
    private String display_yn;
    private String insert_id;
    private String insert_date;
    private String modify_id;
    private String modify_date;
    
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
	public String getPrev_bdate() {
		return prev_bdate;
	}
	public void setPrev_bdate(String prev_bdate) {
		this.prev_bdate = prev_bdate;
	}
	public String getPrev_edate() {
		return prev_edate;
	}
	public void setPrev_edate(String prev_edate) {
		this.prev_edate = prev_edate;
	}
	public String getPrev_gb() {
		return prev_gb;
	}
	public void setPrev_gb(String prev_gb) {
		this.prev_gb = prev_gb;
	}
	public String getPrev_no() {
		return prev_no;
	}
	public void setPrev_no(String prev_no) {
		this.prev_no = prev_no;
	}
	public String getPrev_note() {
		return prev_note;
	}
	public void setPrev_note(String prev_note) {
		this.prev_note = prev_note;
	}
	public String getPrev_title() {
		return prev_title;
	}
	public void setPrev_title(String prev_title) {
		this.prev_title = prev_title;
	}
    
    
    


}