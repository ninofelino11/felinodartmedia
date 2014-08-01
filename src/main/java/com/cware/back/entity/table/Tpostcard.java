
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 엽서후기 
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Tpostcard extends BaseEntity {

    public Tpostcard(){ super();}

    private String	postcard_no;
    private String	yyyymm;
    private String	display_priority;
    private String	display_yn;
    private String	point_yn;
    private String	cust_no;
    private String	goods_code;
    private String	use_term;
    private String	make_date;
    private String	content;
    private String	image_file;
    private String	image_file1;
    private String	insert_date;
    private String	insert_id;
    private String	modify_date;
    private String	modify_id;
    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getDisplay_priority() {
		return display_priority;
	}
	public void setDisplay_priority(String display_priority) {
		this.display_priority = display_priority;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getImage_file() {
		return image_file;
	}
	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	public String getImage_file1() {
		return image_file1;
	}
	public void setImage_file1(String image_file1) {
		this.image_file1 = image_file1;
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
	public String getMake_date() {
		return make_date;
	}
	public void setMake_date(String make_date) {
		this.make_date = make_date;
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
	public String getPoint_yn() {
		return point_yn;
	}
	public void setPoint_yn(String point_yn) {
		this.point_yn = point_yn;
	}
	public String getPostcard_no() {
		return postcard_no;
	}
	public void setPostcard_no(String postcard_no) {
		this.postcard_no = postcard_no;
	}
	public String getUse_term() {
		return use_term;
	}
	public void setUse_term(String use_term) {
		this.use_term = use_term;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}



} 