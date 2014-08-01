
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 설문등록dt
*
* @version 1.0, 2007/02/21
* @author Commerceware Ins.
*/
public class Tresearchdt extends BaseEntity {

    public Tresearchdt(){ super();}

    private String research_no;
    private String question_no;
    private String answer_no;
    private String answer;    
    private String br_yn;
    private String link_question_no;
    private String objective_yn;
    private String answer_cnt;
    private String insert_date;
    private String insert_id;
    
    /** Set Method **/        
    
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAnswer_cnt(String answer_cnt) {
		this.answer_cnt = answer_cnt;
	}
	public void setAnswer_no(String answer_no) {
		this.answer_no = answer_no;
	}
	public void setBr_yn(String br_yn) {
		this.br_yn = br_yn;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setLink_question_no(String link_question_no) {
		this.link_question_no = link_question_no;
	}
	public void setObjective_yn(String objective_yn) {
		this.objective_yn = objective_yn;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	public void setResearch_no(String research_no) {
		this.research_no = research_no;
	}    

    /** Get Method **/
    
	public String getAnswer() {
		return answer;
	}	
	public String getAnswer_cnt() {
		return answer_cnt;
	}
	public String getAnswer_no() {
		return answer_no;
	}	
	public String getBr_yn() {
		return br_yn;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getLink_question_no() {
		return link_question_no;
	}	
	public String getObjective_yn() {
		return objective_yn;
	}	
	public String getQuestion_no() {
		return question_no;
	}	
	public String getResearch_no() {
		return research_no;
	}	
	
} 