
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 기초코드
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcode extends BaseEntity {

	public Tcode(){ super();}

    private String code_lgroup;
    private String code_mgroup;
    private String code_name;
    private String code_sname;
    private String code_group;
    private String remark;
    private String remark1;
    private String remark2;
    private String use_yn;
    private String content;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setCode_lgroup( String code_lgroup ){ this.code_lgroup = code_lgroup; }
    public void setCode_mgroup( String code_mgroup ){ this.code_mgroup = code_mgroup; }
    public void setCode_name( String code_name ){ this.code_name = code_name; }
    public void setCode_sname( String code_sname ){ this.code_sname = code_sname; }
    public void setCode_group( String code_group ){ this.code_group = code_group; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setRemark1( String remark1 ){ this.remark1 = remark1; }
    public void setRemark2( String remark2 ){ this.remark2 = remark2; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setContent( String content ){ this.content = content; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getCode_lgroup(){ return code_lgroup; }
    public String getCode_mgroup(){ return code_mgroup; }
    public String getCode_name(){ return code_name; }
    public String getCode_sname(){ return code_sname; }
    public String getCode_group(){ return code_group; }
    public String getRemark(){ return remark; }
    public String getRemark1(){ return remark1; }
    public String getRemark2(){ return remark2; }
    public String getUse_yn(){ return use_yn; }
    public String getContent(){ return content; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}