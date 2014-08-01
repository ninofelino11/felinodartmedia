package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
 * 사전검사상세
 *
 * @version 1.0, 2011/01/25
 * @author Commerceware Ins.
 */
public class Tbeforeqadt extends BaseEntity {

	public Tbeforeqadt() {
		super();
	}

	private String seq_qa_no;
	private String inspect_lcode;
	private String inspect_mcode;
	private String needs_note;
	private String needs_yn;
	private String action_yn;
	private String action_opinion;
	private String action_date;
	private String conf_yn;
	private String conf_date;
	private String insert_id;
	private String insert_date;
	private String modify_id;
	private String modify_date;
	private boolean exist = false;

	public String getSeq_qa_no() {
		return seq_qa_no;
	}

	public void setSeq_qa_no(String seqQaNo) {
		seq_qa_no = seqQaNo;
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

	public String getNeeds_note() {
		return needs_note;
	}

	public void setNeeds_note(String needsNote) {
		needs_note = needsNote;
	}

	public String getNeeds_yn() {
		return needs_yn;
	}

	public void setNeeds_yn(String needsYn) {
		needs_yn = needsYn;
	}

	public String getAction_yn() {
		return action_yn;
	}

	public void setAction_yn(String actionYn) {
		action_yn = actionYn;
	}

	public String getAction_opinion() {
		return action_opinion;
	}

	public void setAction_opinion(String actionOpinion) {
		action_opinion = actionOpinion;
	}

	public String getAction_date() {
		return action_date;
	}

	public void setAction_date(String actionDate) {
		action_date = actionDate;
	}

	public String getConf_yn() {
		return conf_yn;
	}

	public void setConf_yn(String confYn) {
		conf_yn = confYn;
	}

	public String getConf_date() {
		return conf_date;
	}

	public void setConf_date(String confDate) {
		conf_date = confDate;
	}

	public String getInsert_id() {
		return insert_id;
	}

	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}

	public String getModify_id() {
		return modify_id;
	}

	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}


}