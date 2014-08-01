
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;


/**
* 상품이미지
*
* @version 1.0, 2009/06/22
* @author Commerceware Ins.
*/
public class Tgoodsimage extends BaseEntity {

    public Tgoodsimage(){ super();}

    private String goods_code;
    private String image_no;
    private String image_l;
    private String image_m;
    private String image_s;
    private String image_d;
    private String image_ss;
    private String image_url;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getImage_d() {
		return image_d;
	}
	public void setImage_d(String image_d) {
		this.image_d = image_d;
	}
	public String getImage_l() {
		return image_l;
	}
	public void setImage_l(String image_l) {
		this.image_l = image_l;
	}
	public String getImage_m() {
		return image_m;
	}
	public void setImage_m(String image_m) {
		this.image_m = image_m;
	}
	public String getImage_no() {
		return image_no;
	}
	public void setImage_no(String image_no) {
		this.image_no = image_no;
	}
	public String getImage_s() {
		return image_s;
	}
	public void setImage_s(String image_s) {
		this.image_s = image_s;
	}
	public String getImage_ss() {
		return image_ss;
	}
	public void setImage_ss(String image_ss) {
		this.image_ss = image_ss;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
}