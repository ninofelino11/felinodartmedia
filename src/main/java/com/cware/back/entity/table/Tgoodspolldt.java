
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품투표dt
*
* @version 1.0, 2007/02/20
* @author Commerceware Ins.
*/
public class Tgoodspolldt extends BaseEntity {

    public Tgoodspolldt(){ super();}	

    private String poll_no;
    private String question_no;
    private String question;
    private String question_image;
    private String goods_code;
    private String goodsdt_code;
    private String poll_cnt;    
    private String insert_date;
    private String insert_id;
    

    
    /** Set Method **/    

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setPoll_cnt(String poll_cnt) {
		this.poll_cnt = poll_cnt;
	}
	public void setPoll_no(String poll_no) {
		this.poll_no = poll_no;
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
	
    /** Get Method **/	
	    
	public String getGoods_code() {
		return goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public String getInsert_date() {
		return insert_date;
	}	
	public String getInsert_id() {
		return insert_id;
	}
	public String getPoll_cnt() {
		return poll_cnt;
	}
	public String getPoll_no() {
		return poll_no;
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
	
	
}
