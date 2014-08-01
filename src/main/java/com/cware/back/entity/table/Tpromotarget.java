
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 상세 정보1
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tpromotarget extends BaseEntity {

    public Tpromotarget(){ super();}

    private String promo_no;
    private String promo_seq;
    private String goods_code;
    private String gift_amt;
    private String own_cost;
    private String entp_cost;
    private String entp_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    
	public String getPromo_no() {
		return promo_no;
	}
	public String getPromo_seq() {
		return promo_seq;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public String getGift_amt() {
		return gift_amt;
	}
	public String getOwn_cost() {
		return own_cost;
	}
	public String getEntp_cost() {
		return entp_cost;
	}
	public String getEntp_code() {
		return entp_code;
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
	
	
	public void setPromo_no(String promoNo) {
		promo_no = promoNo;
	}
	public void setPromo_seq(String promoSeq) {
		promo_seq = promoSeq;
	}
	public void setGoods_code(String goodsCode) {
		goods_code = goodsCode;
	}
	public void setGift_amt(String giftAmt) {
		gift_amt = giftAmt;
	}
	public void setOwn_cost(String ownCost) {
		own_cost = ownCost;
	}
	public void setEntp_cost(String entpCost) {
		entp_cost = entpCost;
	}
	public void setEntp_code(String entpCode) {
		entp_code = entpCode;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
    
}