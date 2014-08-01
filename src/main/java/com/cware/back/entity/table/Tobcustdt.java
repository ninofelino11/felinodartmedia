
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2007/01/22
* @author Commerceware Ins.
*/
public class Tobcustdt extends BaseEntity {

    public Tobcustdt(){ super();}

    private String ob_gb;
    private String ob_seq;
    private long select_seq;
    private long proc_seq;
    private String do_flag;
    private String proc_date;
    private String call_gb;
    private String call_receiver;
    private String proc_note;
    private String proc_id;
    
	public String getCall_gb() {
		return call_gb;
	}
	public void setCall_gb(String call_gb) {
		this.call_gb = call_gb;
	}
	public String getCall_receiver() {
		return call_receiver;
	}
	public void setCall_receiver(String call_receiver) {
		this.call_receiver = call_receiver;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
	}
	public String getOb_gb() {
		return ob_gb;
	}
	public void setOb_gb(String ob_gb) {
		this.ob_gb = ob_gb;
	}
	public String getOb_seq() {
		return ob_seq;
	}
	public void setOb_seq(String ob_seq) {
		this.ob_seq = ob_seq;
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
	public long getSelect_seq() {
		return select_seq;
	}
	public void setSelect_seq(long select_seq) {
		this.select_seq = select_seq;
	}
    

} 