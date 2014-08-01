
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 사용순서
*
* @version 1.0, 2006/07/21
* @author Commerceware Ins.
*/
public class Tgoodsstep extends BaseEntity {

    public Tgoodsstep(){ super();}

    private String skin_type;
    private String step;
    private String seq;
    private String goods_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
             

    /** Set Method **/
   

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public void setSkin_type(String skin_type) {
		this.skin_type = skin_type;
	}
	public void setStep(String step) {
		this.step = step;
	}

    /** Get Method **/
	public String getGoods_code() {
		return goods_code;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getModify_date() {
		return modify_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public String getSeq() {
		return seq;
	}
	public String getSkin_type() {
		return skin_type;
	}
	public String getStep() {
		return step;
	}    
} 