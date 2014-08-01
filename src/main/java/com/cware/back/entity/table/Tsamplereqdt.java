
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* A/S Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tsamplereqdt extends BaseEntity {

    public Tsamplereqdt(){ super();}

    private String samplereq_no;
    private String seq_no;
    private String goods_code;
    private String insert_id;
    private String insert_date;

    /** Set Method **/
	public void setSamplereq_no(String samplereq_no) { this.samplereq_no = samplereq_no; }
	public void setSeq_no(String seq_no) { this.seq_no = seq_no; }
	public void setGoods_code(String goods_code) { this.goods_code = goods_code; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }

    /** Get Method **/
	public String getSamplereq_no() { return samplereq_no; }
	public String getSeq_no() { return seq_no; }
	public String getGoods_code() { return goods_code; }
	public String getInsert_id(){ return insert_id; }
	public String getInsert_date(){ return insert_date; }
	
	/** extra property **/
    private String sample_no;
	public String getSample_no() { return sample_no; }
	public void setSample_no(String sample_no) { this.sample_no = sample_no; }
} 