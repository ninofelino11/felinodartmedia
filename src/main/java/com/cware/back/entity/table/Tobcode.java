
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* OUTBOUND코드
*
* @version 1.0, 2007/01/23
* @author Commerceware Ins.
*/
public class Tobcode extends BaseEntity {

	
	public Tobcode(){ super();}

    private String ob_gb;
    private String ob_seq;
    private String ob_title;
    private String ob_note;
    private String goods_code;
    private String start_date;
    private String end_date;
    private String select_date;
    private String select_id;
    private long   select_cnt;
    private String distr_date;
    private String distr_id;
    private String ob_sql;
    private String remark;

    //= User define
    private String new_ob_seq;

	public String getDistr_date() {
		return distr_date;
	}

	public void setDistr_date(String distr_date) {
		this.distr_date = distr_date;
	}

	public String getDistr_id() {
		return distr_id;
	}

	public void setDistr_id(String distr_id) {
		this.distr_id = distr_id;
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

	public String getNew_ob_seq() {
		return new_ob_seq;
	}

	public void setNew_ob_seq(String new_ob_seq) {
		this.new_ob_seq = new_ob_seq;
	}

	public String getOb_gb() {
		return ob_gb;
	}

	public void setOb_gb(String ob_gb) {
		this.ob_gb = ob_gb;
	}

	public String getOb_note() {
		return ob_note;
	}

	public void setOb_note(String ob_note) {
		this.ob_note = ob_note;
	}

	public String getOb_seq() {
		return ob_seq;
	}

	public void setOb_seq(String ob_seq) {
		this.ob_seq = ob_seq;
	}

	public String getOb_sql() {
		return ob_sql;
	}

	public void setOb_sql(String ob_sql) {
		this.ob_sql = ob_sql;
	}

	public String getOb_title() {
		return ob_title;
	}

	public void setOb_title(String ob_title) {
		this.ob_title = ob_title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getSelect_cnt() {
		return select_cnt;
	}

	public void setSelect_cnt(long select_cnt) {
		this.select_cnt = select_cnt;
	}

	public String getSelect_date() {
		return select_date;
	}

	public void setSelect_date(String select_date) {
		this.select_date = select_date;
	}

	public String getSelect_id() {
		return select_id;
	}

	public void setSelect_id(String select_id) {
		this.select_id = select_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

    
    
    
} 