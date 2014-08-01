
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TBank 정보
*
* @version 1.0, 2007/03/10
* @author  commerceware.co.kr
*/
public class Tprohibitid extends BaseEntity {

    public Tprohibitid(){ super();}

    private String id;
    private String id_note;
    private String insert_date;
    private String insert_id;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_note() {
		return id_note;
	}
	public void setId_note(String id_note) {
		this.id_note = id_note;
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
    


}