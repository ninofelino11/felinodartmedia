
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 불만상세
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustspinfo extends BaseEntity implements Cloneable{

    public Tcustspinfo(){ super();}

    private String cust_no;
    private String seq;
    private String type;
    private String content;
    private String sp_bdate;
    private String sp_edate;
    private String msg;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSp_bdate() {
		return sp_bdate;
	}
	public void setSp_bdate(String sp_bdate) {
		this.sp_bdate = sp_bdate;
	}
	public String getSp_edate() {
		return sp_edate;
	}
	public void setSp_edate(String sp_edate) {
		this.sp_edate = sp_edate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
  

    


} 