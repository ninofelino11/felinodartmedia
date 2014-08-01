
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsamplem extends BaseEntity {

    public Tsamplem(){ super();}

    private String sample_no;
    private String sample_name;
    private String sample_type;
    private String start_date;
    private String end_date;
    private String memb_gb;
    private String sex;
    private long request_qty;
    private long month_dup_qty;
    private long choice_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
	public void setSample_no(String sample_no) { this.sample_no = sample_no; }
	public void setSample_name(String sample_name) { this.sample_name = sample_name; }
	public void setSample_type(String sample_type) { this.sample_type = sample_type; }
	public void setStart_date(String start_date) { this.start_date = start_date; }
	public void setEnd_date(String end_date) { this.end_date = end_date; }
	public void setMemb_gb(String memb_gb) { this.memb_gb = memb_gb; }
	public void setSex(String sex) { this.sex = sex; }
	public void setRequest_qty(long request_qty) { this.request_qty = request_qty; }
	public void setMonth_dup_qty(long month_dup_qty) { this.month_dup_qty = month_dup_qty; }
	public void setChoice_qty(long choice_qty) { this.choice_qty = choice_qty; }
	public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
	public void setModify_date(String modify_date) { this.modify_date = modify_date; }
	public void setModify_id(String modify_id) { this.modify_id = modify_id; }

    /** Get Method **/
	public String getSample_no() { return sample_no; }
	public String getSample_name() { return sample_name; }
	public String getSample_type() { return sample_type; }
	public String getStart_date() { return start_date; }
	public String getEnd_date() { return end_date; }
	public String getMemb_gb() { return memb_gb; }
	public String getSex() { return sex; }
	public long getRequest_qty() { return request_qty; }
	public long getMonth_dup_qty() { return month_dup_qty; }
	public long getChoice_qty() { return choice_qty; }
	public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
	public String getModify_date() { return modify_date; }
	public String getModify_id() { return modify_id; }

} 