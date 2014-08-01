package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * QA 검사상품분류
 *
 * @version 1.0, 2011/01/20
 * @author Commerceware Ins.
 */
public class Tinspect_goods_grp extends BaseEntity {

	public Tinspect_goods_grp() {
		super();
	}

	private String lmsd_code;
	private String inspect_lcode;
	private String inspect_mcode;
	private String use_yn;
	private String insert_date;
	private String insert_id;
	private String modify_date;
	private String modify_id;

	private String lgroup;
	private String mgroup;
	private String sgroup;
	private String dgroup;

	public String getLmsd_code() {
		return lmsd_code;
	}

	public void setLmsd_code(String lmsdCode) {
		lmsd_code = lmsdCode;
	}

	public String getInspect_lcode() {
		return inspect_lcode;
	}

	public void setInspect_lcode(String inspectLcode) {
		inspect_lcode = inspectLcode;
	}

	public String getInspect_mcode() {
		return inspect_mcode;
	}

	public void setInspect_mcode(String inspectMcode) {
		inspect_mcode = inspectMcode;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String useYn) {
		use_yn = useYn;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}

	public String getInsert_id() {
		return insert_id;
	}

	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}

	public String getModify_id() {
		return modify_id;
	}

	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}

	public String getLgroup() {
		return lgroup;
	}

	public void setLgroup(String lgroup) {
		this.lgroup = lgroup;
	}

	public String getMgroup() {
		return mgroup;
	}

	public void setMgroup(String mgroup) {
		this.mgroup = mgroup;
	}

	public String getSgroup() {
		return sgroup;
	}

	public void setSgroup(String sgroup) {
		this.sgroup = sgroup;
	}

	public String getDgroup() {
		return dgroup;
	}

	public void setDgroup(String dgroup) {
		this.dgroup = dgroup;
	}
}
