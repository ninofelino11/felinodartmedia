
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 피부진단M
*
* @version 1.0, 2007/02/14
* @author Commerceware Ins.
*/
public class Tskintestm extends BaseEntity {

    public Tskintestm(){ super();}

    private String skintest_no;
    private String skintest_name;
    private String insert_date;
    private String insert_id;
    
    
    /** Set Method **/    
    

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setSkintest_name(String skintest_name) {
		this.skintest_name = skintest_name;
	}
	public void setSkintest_no(String skintest_no) {
		this.skintest_no = skintest_no;
	}
	

    /** Get Method **/
	public String getInsert_date() {
		return insert_date;
	}	
	public String getInsert_id() {
		return insert_id;	
	}
	public String getSkintest_name() {
		return skintest_name;
	}	
	public String getSkintest_no() {
		return skintest_no;
	}	
	    
} 