
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tstockcheckcode 정보 (실사코드)
*
* @version 1.0, 2006/11/14
* @author  commerceware.co.kr [kim sungtaek; webzest@commerceware.co.kr]
*/
public class Tstockcheckcode extends BaseEntity implements Cloneable{

    public Tstockcheckcode(){ super();}

    private String check_code;
    private String check_gb;
    private String stock_check_note;
    private String check_date;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getCheck_gb() {
		return check_gb;
	}
	public void setCheck_gb(String check_gb) {
		this.check_gb = check_gb;
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
	public String getStock_check_note() {
		return stock_check_note;
	}
	public void setStock_check_note(String stock_check_note) {
		this.stock_check_note = stock_check_note;
	}

}