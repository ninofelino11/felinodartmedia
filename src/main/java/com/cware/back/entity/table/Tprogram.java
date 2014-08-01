
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 방송프로그램 기본정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tprogram extends BaseEntity {

    public Tprogram(){ super();}

    private String prog_code;
    private String prog_name;
    private String bdate;
    private String edate;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
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
	public String getProg_code() {
		return prog_code;
	}
	public void setProg_code(String prog_code) {
		this.prog_code = prog_code;
	}
	public String getProg_name() {
		return prog_name;
	}
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

}