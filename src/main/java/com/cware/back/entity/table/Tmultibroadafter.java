package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

public class Tmultibroadafter extends BaseEntity {

	public Tmultibroadafter(){ super();}
  
    private String seq_frame_no;
    private String user_gb;
    private String after_code;
    private String after_code_org;
    private String after_info;
    private String goods_code;
    private String user_id;
    private String sysDateTime;

    /** Set Method **/    
    public void setUser_gb( String user_gb ){ this.user_gb = user_gb; }    
	public void setSeq_frame_no(String seqFrameNo) {seq_frame_no = seqFrameNo;}
	public void setAfter_code( String after_code ){ this.after_code = after_code; }
    public void setAfter_code_org( String after_code_org ){ this.after_code_org = after_code_org; }
    public void setAfter_info( String after_info ){ this.after_info = after_info; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }    
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public void setSysDateTime(String sysDateTime) {
		this.sysDateTime = sysDateTime;
	}
	/** Get Method **/
    public String getSeq_frame_no() {return seq_frame_no;}
    public String getUser_gb(){ return user_gb; }
    public String getAfter_code(){ return after_code; }
    public String getAfter_code_org(){ return after_code_org; }
    public String getAfter_info(){ return after_info; }
    public String getGoods_code(){ return goods_code; }
    public String getUser_id() {
		return user_id;
	}
	public String getSysDateTime() {
		return sysDateTime;
	}
	
}