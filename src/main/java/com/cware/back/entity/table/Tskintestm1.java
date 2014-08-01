
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 피부진단M1
*
* @version 1.0, 2007/02/14
* @author Commerceware Ins.
*/
public class Tskintestm1 extends BaseEntity {

    public Tskintestm1(){ super();}

    private String skintest_no;
    private String question_no;
    private String question;
    private String question_image;
    private String insert_date;
    private String insert_id;
    
    
    /** Set Method **/       
    
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setQuestion_image(String question_image) {
		this.question_image = question_image;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
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
	public String getQuestion() {
		return question;
	}	
	public String getQuestion_image() {
		return question_image;
	}	
	public String getQuestion_no() {
		return question_no;
	}	
	public String getSkintest_no() {
		return skintest_no;
	}	

} 