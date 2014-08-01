package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * QA 검사분류
 *
 * @version 1.0, 2011/01/20
 * @author Commerceware Ins.
 */
public class Tinspect_goods extends BaseEntity {

	public Tinspect_goods() {
		super();
	}

	private String inspect_lcode;
	private String inspect_mcode;
	private String inspect_mname;
	private String use_yn;
	private String insert_date;
	private String insert_id;
	private String modify_date;
	private String modify_id;

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

	public String getInspect_mname() {
		return inspect_mname;
	}

	public void setInspect_mname(String inspectMname) {
		inspect_mname = inspectMname;
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

}
