package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 테입정보
*
* @version 1.0, 2009/01/22
* @author Commerceware Ins.
*/
public class Tpgmtape extends BaseEntity {

public Tpgmtape(){ super();}

    private String tape_code;
    private String tape_name;
    private String tape_no;
    private String tape_barcode;
    private String make_date;
    private String sno;
    private String sno1;
    private String sno_rate;
    private String sno1_rate;
    private String runtime_fr;
    private String runtime_to;
    private String tape_type;
    private String review_type;
    private String tape_desc;
    private String review_desc;
    private String camera_team;
    private String sub_team;
    private String model;
    private String use_code;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    /** Set Method **/
    public void setTape_code( String tape_code ){ this.tape_code = tape_code; }
    public void setTape_name( String tape_name ){ this.tape_name = tape_name; }
    public void setTape_no( String tape_no ){ this.tape_no = tape_no; }
    public void setTape_barcode( String tape_barcode ){ this.tape_barcode = tape_barcode; }
    public void setMake_date( String make_date ){ this.make_date = make_date; }
    public void setSno( String sno ){ this.sno = sno; }
    public void setSno1( String sno1 ){ this.sno1 = sno1; }
    public void setSno_rate( String sno_rate ){ this.sno_rate = sno_rate; }
    public void setSno1_rate( String sno1_rate ){ this.sno1_rate = sno1_rate; }
    public void setRuntime_fr( String runtime_fr ){ this.runtime_fr = runtime_fr; }
    public void setRuntime_to( String runtime_to ){ this.runtime_to = runtime_to; }
    public void setTape_type( String tape_type ){ this.tape_type = tape_type; }
    public void setReview_type( String review_type ){ this.review_type = review_type; }
    public void setTape_desc( String tape_desc ){ this.tape_desc = tape_desc; }
    public void setReview_desc( String review_desc ){ this.review_desc = review_desc; }
    public void setCamera_team( String camera_team ){ this.camera_team = camera_team; }
    public void setSub_team( String sub_team ){ this.sub_team = sub_team; }
    public void setModel( String model ){ this.model = model; }
    public void setUse_code( String use_code ){ this.use_code = use_code; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getTape_code(){ return tape_code; }
    public String getTape_name(){ return tape_name; }
    public String getTape_no(){ return tape_no; }
    public String getTape_barcode(){ return tape_barcode; }
    public String getMake_date(){ return make_date; }
    public String getSno(){ return sno; }
    public String getSno1(){ return sno1; }
    public String getSno_rate(){ return sno_rate; }
    public String getSno1_rate(){ return sno1_rate; }
    public String getRuntime_fr(){ return runtime_fr; }
    public String getRuntime_to(){ return runtime_to; }
    public String getTape_type(){ return tape_type; }
    public String getReview_type(){ return review_type; }
    public String getTape_desc(){ return tape_desc; }
    public String getReview_desc(){ return review_desc; }
    public String getCamera_team(){ return camera_team; }
    public String getSub_team(){ return sub_team; }
    public String getModel(){ return model; }
    public String getUse_code(){ return use_code; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

}