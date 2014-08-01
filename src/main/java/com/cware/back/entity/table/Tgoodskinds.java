
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품분류
*
* @version 1.0, 2006/07/19
* @author Commerceware Ins.
*/
public class Tgoodskinds extends BaseEntity {

    public Tgoodskinds(){ super();}

    private String lgroup;
    private String mgroup;
    private String sgroup;
    private String dgroup;
    private String lgroup_name;
    private String mgroup_name;
    private String sgroup_name;
    private String dgroup_name;
    private String qc_lgroup;
    private String qc_mgroup;
    private String size_lgroup;
    private String lmsd_code;
    private double def_vat_rate;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setLgroup( String lgroup ){ this.lgroup = lgroup; }
    public void setMgroup( String mgroup ){ this.mgroup = mgroup; }
    public void setSgroup( String sgroup ){ this.sgroup = sgroup; }
    public void setDgroup( String dgroup ){ this.dgroup = dgroup; }
    public void setLgroup_name( String lgroup_name ){ this.lgroup_name = lgroup_name; }
    public void setMgroup_name( String mgroup_name ){ this.mgroup_name = mgroup_name; }
    public void setSgroup_name( String sgroup_name ){ this.sgroup_name = sgroup_name; }
    public void setDgroup_name( String dgroup_name ){ this.dgroup_name = dgroup_name; }
    public void setQc_lgroup( String qc_lgroup ){ this.qc_lgroup = qc_lgroup; }
    public void setQc_mgroup( String qc_mgroup ){ this.qc_mgroup = qc_mgroup; }
    public void setSize_lgroup( String size_lgroup ){ this.size_lgroup = size_lgroup; }
    public void setLmsd_code( String lmsd_code ){ this.lmsd_code = lmsd_code; }
    public void setDef_vat_rate( double def_vat_rate ){ this.def_vat_rate = def_vat_rate; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getLgroup(){ return lgroup; }
    public String getMgroup(){ return mgroup; }
    public String getSgroup(){ return sgroup; }
    public String getDgroup(){ return dgroup; }
    public String getLgroup_name(){ return lgroup_name; }
    public String getMgroup_name(){ return mgroup_name; }
    public String getSgroup_name(){ return sgroup_name; }
    public String getDgroup_name(){ return dgroup_name; }
    public String getQc_lgroup(){ return qc_lgroup; }
    public String getQc_mgroup(){ return qc_mgroup; }
    public String getSize_lgroup(){ return size_lgroup; }
    public String getLmsd_code(){ return lmsd_code; }
    public double getDef_vat_rate(){ return def_vat_rate; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }


}