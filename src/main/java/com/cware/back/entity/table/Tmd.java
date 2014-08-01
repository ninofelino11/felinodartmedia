
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* MD
*
* @version 1.0, 2006/07/13
* @author Commerceware Ins.
*/
public class Tmd extends BaseEntity {

    public Tmd(){ super();}

    private String md_code;
    private String md_name;
    private String use_yn;
    private String remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setMd_code( String md_code ){ this.md_code = md_code; }
    public void setMd_name( String md_name ){ this.md_name = md_name; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setUse_yn(String useYn) {
		use_yn = useYn;
	}

    /** Get Method **/
    public String getMd_code(){ return md_code; }
    public String getMd_name(){ return md_name; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
	public String getUse_yn() {
		return use_yn;
	}
	


}
