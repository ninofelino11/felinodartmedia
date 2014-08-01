
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;


public class Teventdt extends BaseEntity {  

    private String event_no;                                                               
    private String event_seq;                                                             
    private String start_rank;                                                           
    private String end_rank;                                                             
    private String promo_flag;                                                             
    private String promo_title; 
    private String display_title;
    
	public String getDisplay_title() {
		return display_title;
	}
	public void setDisplay_title(String display_title) {
		this.display_title = display_title;
	}
	public String getEnd_rank() {
		return end_rank;
	}
	public void setEnd_rank(String end_rank) {
		this.end_rank = end_rank;
	}
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public String getEvent_seq() {
		return event_seq;
	}
	public void setEvent_seq(String event_seq) {
		this.event_seq = event_seq;
	}
	public String getPromo_flag() {
		return promo_flag;
	}
	public void setPromo_flag(String promo_flag) {
		this.promo_flag = promo_flag;
	}
	public String getPromo_title() {
		return promo_title;
	}
	public void setPromo_title(String promo_title) {
		this.promo_title = promo_title;
	}
	public String getStart_rank() {
		return start_rank;
	}
	public void setStart_rank(String start_rank) {
		this.start_rank = start_rank;
	}
    
    
}
