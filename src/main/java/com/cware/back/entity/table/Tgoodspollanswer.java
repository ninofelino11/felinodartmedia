
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품투표dt
*
* @version 1.0, 2007/02/20
* @author Commerceware Ins.
*/
public class Tgoodspollanswer extends BaseEntity {

    public Tgoodspollanswer(){ super();}	

    private String memb_no;
    private String poll_no;
    private String question_no;
    private String answer;
    private String insert_date;
    
    /** Set Method **/    

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setMemb_no(String memb_no) {
		this.memb_no = memb_no;
	}
	public void setPoll_no(String poll_no) {
		this.poll_no = poll_no;
	}
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}

	
    /** Get Method **/	
    
	public String getAnswer() {
		return answer;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getMemb_no() {
		return memb_no;
	}	
	public String getPoll_no() {
		return poll_no;
	}	
	public String getQuestion_no() {
		return question_no;
	}	
}
