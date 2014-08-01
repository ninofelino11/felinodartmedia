
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상담접수 Detail
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustcounseldt extends BaseEntity {

    public Tcustcounseldt(){ super();}

    private String counsel_seq;
    private String counsel_dt_seq;
    private String do_flag;
    private String title;
    private String display_yn;
    private String proc_note;
    private String proc_date;
    private String proc_id;
    private String end_date;
    private String goods_code;
    private String goodsdt_code;
    private String cs_send_yn;
    private String send_entp_code;

	public String getCounsel_dt_seq() {
		return counsel_dt_seq;
	}
	public void setCounsel_dt_seq(String counsel_dt_seq) {
		this.counsel_dt_seq = counsel_dt_seq;
	}
	public String getCounsel_seq() {
		return counsel_seq;
	}
	public void setCounsel_seq(String counsel_seq) {
		this.counsel_seq = counsel_seq;
	}
	public String getCs_send_yn() {
		return cs_send_yn;
	}
	public void setCs_send_yn(String cs_send_yn) {
		this.cs_send_yn = cs_send_yn;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getDo_flag() {
		return do_flag;
	}
	public void setDo_flag(String do_flag) {
		this.do_flag = do_flag;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
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
	public String getSend_entp_code() {
		return send_entp_code;
	}
	public void setSend_entp_code(String send_entp_code) {
		this.send_entp_code = send_entp_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}