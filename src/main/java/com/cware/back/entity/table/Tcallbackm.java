
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tcallbackm
*
* @version 1.0, 2008/06/26
* @author  commerceware.co.kr 
*/
public class Tcallbackm extends BaseEntity {

    public Tcallbackm(){ super();}

    private String callback_no;
    private String callback_type;
    private String do_flag;
    private String tel;
    private String proc_id;
    private String proc_date;
    private String assign_id;
    private String assign_date;
    private String insert_id;
    private String insert_date;
    
	public String getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(String assign_date) {
		this.assign_date = assign_date;
	}
	public String getAssign_id() {
		return assign_id;
	}
	public void setAssign_id(String assign_id) {
		this.assign_id = assign_id;
	}
	public String getCallback_no() {
		return callback_no;
	}
	public void setCallback_no(String callback_no) {
		this.callback_no = callback_no;
	}
	public String getCallback_type() {
		return callback_type;
	}
	public void setCallback_type(String callback_type) {
		this.callback_type = callback_type;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
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
	public String getProc_date() {
		return proc_date;
	}
	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}
	public String getProc_id() {
		return proc_id;
	}
	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

    


}