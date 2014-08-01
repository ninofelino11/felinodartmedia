
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 설문등록m1
*
* @version 1.0, 2007/02/21
* @author Commerceware Ins.
*/
public class Tresearchm1 extends BaseEntity {

    public Tresearchm1(){ super();}

    private String research_no;
    private String question_no;
    private String question;
    private String objective_yn;
    private String insert_date;
    private String insert_id;
        
    /** Set Method **/    

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setObjective_yn(String objective_yn) {
		this.objective_yn = objective_yn;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	public void setResearch_no(String research_no) {
		this.research_no = research_no;
	}

    /** Get Method **/

	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getObjective_yn() {
		return objective_yn;
	}
	public String getQuestion() {
		return question;
	}	
	public String getQuestion_no() {
		return question_no;
	}   
	public String getResearch_no() {
		return research_no;
	}	
	
} 