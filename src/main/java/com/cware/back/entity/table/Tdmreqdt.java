
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdmreqdt extends BaseEntity {

    public Tdmreqdt(){ super();}
    
    private String dmreq_no;
    private String seq;
    private String goods_code;
    private String media_code;
    private String insert_date;
    private String insert_id;
    
	public String getDmreq_no() {
		return dmreq_no;
	}
	public void setDmreq_no(String dmreq_no) {
		this.dmreq_no = dmreq_no;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
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
	public String getMedia_code() {
		return media_code;
	}
	public void setMedia_code(String media_code) {
		this.media_code = media_code;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
    
    

 
} 