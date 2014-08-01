
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 피부진단DT
*
* @version 1.0, 2007/02/14
* @author Commerceware Ins.
*/
public class Tskintestdt extends BaseEntity {

    public Tskintestdt(){ super();}

    private String skintest_no;
    private String question_no;
    private String answer_no;
    private String answer;
    private String answer_type;
    private String link_question_no;
    private String link_answer_no;
    private String insert_date;
    private String insert_id;
    
    
    /** Set Method **/       

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAnswer_no(String answer_no) {
		this.answer_no = answer_no;
	}
	public void setAnswer_type(String answer_type) {
		this.answer_type = answer_type;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setLink_answer_no(String link_answer_no) {
		this.link_answer_no = link_answer_no;
	}
	public void setLink_question_no(String link_question_no) {
		this.link_question_no = link_question_no;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	public void setSkintest_no(String skintest_no) {
		this.skintest_no = skintest_no;
	}    

    
    /** Get Method **/

	public String getAnswer() {
		return answer;
	}	
	public String getAnswer_no() {
		return answer_no;
	}	
	public String getAnswer_type() {
		return answer_type;
	}	
	public String getInsert_date() {
		return insert_date;
	}	
	public String getInsert_id() {
		return insert_id;
	}	
	public String getLink_answer_no() {
		return link_answer_no;
	}	
	public String getLink_question_no() {
		return link_question_no;
	}	
	public String getQuestion_no() {
		return question_no;
	}	
	public String getSkintest_no() {
		return skintest_no;
	}	
	
} 