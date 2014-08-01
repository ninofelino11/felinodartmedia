
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tcallbackdt
*
* @version 1.0, 2008/06/26
* @author  commerceware.co.kr 
*/
public class Tcallbackdt extends BaseEntity {

    public Tcallbackdt(){ super();}

    private String callback_no;
    private long   proc_seq;
    private String do_flag;
    private String call_type;
    private String call_receiver;
    private String proc_id;
    private String proc_date;
    private String proc_note;
    
	public String getCall_receiver() {
		return call_receiver;
	}
	public void setCall_receiver(String call_receiver) {
		this.call_receiver = call_receiver;
	}
	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public String getCallback_no() {
		return callback_no;
	}
	public void setCallback_no(String callback_no) {
		this.callback_no = callback_no;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
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
	public String getProc_note() {
		return proc_note;
	}
	public void setProc_note(String proc_note) {
		this.proc_note = proc_note;
	}
	public long getProc_seq() {
		return proc_seq;
	}
	public void setProc_seq(long proc_seq) {
		this.proc_seq = proc_seq;
	}

    


}