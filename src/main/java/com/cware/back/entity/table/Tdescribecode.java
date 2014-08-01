
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품기술서 기본정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tdescribecode extends BaseEntity {

public Tdescribecode(){ super();}

    private String describe_code;
    private String describe_title;
    private String web_flag;
    private String sort_seq;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String use_yn;

    /** Set Method **/
    public void setDescribe_code( String describe_code ){ this.describe_code = describe_code; }
    public void setDescribe_title( String describe_title ){ this.describe_title = describe_title; }
    public void setWeb_flag( String web_flag ){ this.web_flag = web_flag; }
    public void setSort_seq( String sort_seq ){ this.sort_seq = sort_seq; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getDescribe_code(){ return describe_code; }
    public String getDescribe_title(){ return describe_title; }
    public String getWeb_flag(){ return web_flag; }
    public String getSort_seq(){ return sort_seq; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

}