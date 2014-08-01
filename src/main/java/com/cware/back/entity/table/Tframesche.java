
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 방송편성프로그램
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tframesche extends BaseEntity {

	public Tframesche(){ super();}

    private String bd_date;
    private String prog_code;
    private String bd_btime;
    private String bd_etime;
    private String sno;
    private String sno1;
    private String pc_userid;
    private String camera_team;
    private String sub_team;
    private String record_yn;
    private String live_yn;
    private String modify_yn;
    private String pdmodify_yn;
    private double plan_amt;
    private double sno_rate;
    private double sno1_rate;
    private String model;
    private String remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    //= user modify
    private String org_bd_btime;
    private String new_goods_code;
    private String sale_end_time;

    /** Set Method **/
    public void setBd_date( String bd_date ){ this.bd_date = bd_date; }
    public void setProg_code( String prog_code ){ this.prog_code = prog_code; }
    public void setBd_btime( String bd_btime ){ this.bd_btime = bd_btime; }
    public void setBd_etime( String bd_etime ){ this.bd_etime = bd_etime; }
    public void setSno( String sno ){ this.sno = sno; }
    public void setSno1( String sno1 ){ this.sno1 = sno1; }
    public void setPc_userid( String pc_userid ){ this.pc_userid = pc_userid; }
    public void setCamera_team( String camera_team ){ this.camera_team = camera_team; }
    public void setSub_team( String sub_team ){ this.sub_team = sub_team; }
    public void setRecord_yn( String record_yn ){ this.record_yn = record_yn; }
    public void setLive_yn( String live_yn ){ this.live_yn = live_yn; }
    public void setModify_yn( String modify_yn ){ this.modify_yn = modify_yn; }
    public void setPdmodify_yn( String pdmodify_yn ){ this.pdmodify_yn = pdmodify_yn; }
    public void setPlan_amt( double plan_amt ){ this.plan_amt = plan_amt; }
    public void setSno_rate( double sno_rate ){ this.sno_rate = sno_rate; }
    public void setSno1_rate( double sno1_rate ){ this.sno1_rate = sno1_rate; }
    public void setModel( String model ){ this.model = model; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    /** Get Method **/
    public String getBd_date(){ return bd_date; }
    public String getProg_code(){ return prog_code; }
    public String getBd_btime(){ return bd_btime; }
    public String getBd_etime(){ return bd_etime; }
    public String getSno(){ return sno; }
    public String getSno1(){ return sno1; }
    public String getPc_userid(){ return pc_userid; }
    public String getCamera_team(){ return camera_team; }
    public String getSub_team(){ return sub_team; }
    public String getRecord_yn(){ return record_yn; }
    public String getLive_yn(){ return live_yn; }
    public String getModify_yn(){ return modify_yn; }
    public String getPdmodify_yn(){ return pdmodify_yn; }
    public double getPlan_amt(){ return plan_amt; }
    public double getSno_rate(){ return sno_rate; }
    public double getSno1_rate(){ return sno1_rate; }
    public String getModel(){ return model; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
	public String getNew_goods_code() {
		return new_goods_code;
	}
	public void setNew_goods_code(String new_goods_code) {
		this.new_goods_code = new_goods_code;
	}
	public String getOrg_bd_btime() {
		return org_bd_btime;
	}
	public void setOrg_bd_btime(String org_bd_btime) {
		this.org_bd_btime = org_bd_btime;
	}
	public String getSale_end_time() {
		return sale_end_time;
	}
	public void setSale_end_time(String sale_end_time) {
		this.sale_end_time = sale_end_time;
	}


}