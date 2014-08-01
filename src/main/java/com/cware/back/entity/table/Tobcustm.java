
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* OUTBOUND대상고객
*
* @version 1.0, 2007/01/22
* @author Commerceware Ins.
*/
public class Tobcustm extends BaseEntity {

    public Tobcustm(){ super();}

    private String ob_gb;
    private String ob_seq;
    private long   select_seq;   
    private String cust_no;
    private String ref_no1;
    private String ref_no2;
    private String ref_no3;
    private String ref_no4;
    private String home_tel;
    private String office_tel;
    private String hp;
    private String tm_id;
    private long try_cnt;
    private String do_flag;
    private String proc_date;

    //=user define
    private String new_ob_seq;
    private long   new_select_seq;
    
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
	}
	public String getHome_tel() {
		return home_tel;
	}
	public void setHome_tel(String home_tel) {
		this.home_tel = home_tel;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getNew_ob_seq() {
		return new_ob_seq;
	}
	public void setNew_ob_seq(String new_ob_seq) {
		this.new_ob_seq = new_ob_seq;
	}
	public long getNew_select_seq() {
		return new_select_seq;
	}
	public void setNew_select_seq(long new_select_seq) {
		this.new_select_seq = new_select_seq;
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
	public String getOffice_tel() {
		return office_tel;
	}
	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel;
	}
	public String getProc_date() {
		return proc_date;
	}
	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}
	public String getRef_no1() {
		return ref_no1;
	}
	public void setRef_no1(String ref_no1) {
		this.ref_no1 = ref_no1;
	}
	public String getRef_no2() {
		return ref_no2;
	}
	public void setRef_no2(String ref_no2) {
		this.ref_no2 = ref_no2;
	}
	public String getRef_no3() {
		return ref_no3;
	}
	public void setRef_no3(String ref_no3) {
		this.ref_no3 = ref_no3;
	}
	public String getRef_no4() {
		return ref_no4;
	}
	public void setRef_no4(String ref_no4) {
		this.ref_no4 = ref_no4;
	}
	public long getSelect_seq() {
		return select_seq;
	}
	public void setSelect_seq(long select_seq) {
		this.select_seq = select_seq;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	public long getTry_cnt() {
		return try_cnt;
	}
	public void setTry_cnt(long try_cnt) {
		this.try_cnt = try_cnt;
	}
    

    
    
} 