
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsampledt extends BaseEntity {

    public Tsampledt(){ super();}

    private String sample_no;
    private String goods_code;
    private String base_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
	public void setSample_no(String sample_no) { this.sample_no = sample_no; }
	public void setGoods_code(String goods_code) { this.goods_code = goods_code; }
	public void setBase_yn(String base_yn) { this.base_yn = base_yn; }
	public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
	public void setModify_date(String modify_date) { this.modify_date = modify_date; }
	public void setModify_id(String modify_id) { this.modify_id = modify_id; }

    /** Get Method **/
	public String getSample_no() { return sample_no; }
	public String getGoods_code() { return goods_code; }
	public String getBase_yn() { return base_yn; }
	public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
	public String getModify_date() { return modify_date; }
	public String getModify_id() { return modify_id; }

} 