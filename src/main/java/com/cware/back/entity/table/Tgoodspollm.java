
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품투표M
*
* @version 1.0, 2007/02/20
* @author Commerceware Ins.
*/
public class Tgoodspollm extends BaseEntity {

    public Tgoodspollm(){ super();}	

    private String poll_no;
    private String poll_title;
    private String poll_image;
    private String start_date;
    private String end_date;
    private String display_yn;
    private String point_amt;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
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
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
	public void setPoint_amt(String point_amt) {
		this.point_amt = point_amt;
	}
	public void setPoll_image(String poll_image) {
		this.poll_image = poll_image;
	}
	public void setPoll_no(String poll_no) {
		this.poll_no = poll_no;
	}
	public void setPoll_title(String poll_title) {
		this.poll_title = poll_title;
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
	public String getModify_date() {
		return modify_date;
	}	
	public String getModify_id() {
		return modify_id;
	}	
	public String getPoint_amt() {
		return point_amt;
	}	
	public String getPoll_image() {
		return poll_image;
	}
	public String getPoll_no() {
		return poll_no;
	}
	public String getPoll_title() {
		return poll_title;
	}
	public String getStart_date() {
		return start_date;
	}
	public String getDisplay_yn() {
		return display_yn;
	}	
}
