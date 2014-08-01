
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsolink extends BaseEntity {

    public Tsolink(){ super();}

    private String post_no;
    private String post_seq;
    private String so_code;
    private String insert_id;
    private String insert_date;

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
	public String getPost_no() {
		return post_no;
	}
	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}
	public String getPost_seq() {
		return post_seq;
	}
	public void setPost_seq(String post_seq) {
		this.post_seq = post_seq;
	}
	public String getSo_code() {
		return so_code;
	}
	public void setSo_code(String so_code) {
		this.so_code = so_code;
	}

}