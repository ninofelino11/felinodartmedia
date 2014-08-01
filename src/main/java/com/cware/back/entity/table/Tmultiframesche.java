
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 방송편성프로그램
*
* @version 1.0, 2011/01/31
* @author Commerceware Ins.
*/
public class Tmultiframesche extends BaseEntity {

	public Tmultiframesche(){ super();}
	
	private String bd_date;
    private String seq_frame_no;
    private String media_code;
    private String prog_code;
    private String tape_code;
    private String bd_btime;
    private String bd_etime;
    private String main_pd;
    private String sub_pd;
    private String camera_team;
    private String sub_team;
    private String live_flag;
    private double main_pd_rate;
    private double sub_pd_rate;
    private double plan_amt;
    private String model;
    private String guest;
    private String remark;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String org_bd_btime;
    

    public String getBd_date() {
		return bd_date;
	}
	public void setBd_date(String bdDate) {
		bd_date = bdDate;
	}
	public String getSeq_frame_no() {
		return seq_frame_no;
	}
	public void setSeq_frame_no(String seqFrameNo) {
		seq_frame_no = seqFrameNo;
	}
	public String getMedia_code() {
		return media_code;
	}
	public void setMedia_code(String mediaCode) {
		media_code = mediaCode;
	}
	public String getProg_code() {
		return prog_code;
	}
	public void setProg_code(String progCode) {
		prog_code = progCode;
	}
	public String getTape_code() {
		return tape_code;
	}
	public void setTape_code(String tapeCode) {
		tape_code = tapeCode;
	}
	public String getBd_btime() {
		return bd_btime;
	}
	public void setBd_btime(String bdBtime) {
		bd_btime = bdBtime;
	}
	public String getBd_etime() {
		return bd_etime;
	}
	public void setBd_etime(String bdEtime) {
		bd_etime = bdEtime;
	}
	public String getMain_pd() {
		return main_pd;
	}
	public void setMain_pd(String mainPd) {
		main_pd = mainPd;
	}
	public String getSub_pd() {
		return sub_pd;
	}
	public void setSub_pd(String subPd) {
		sub_pd = subPd;
	}
	public String getCamera_team() {
		return camera_team;
	}
	public void setCamera_team(String cameraTeam) {
		camera_team = cameraTeam;
	}
	public String getSub_team() {
		return sub_team;
	}
	public void setSub_team(String subTeam) {
		sub_team = subTeam;
	}
	public String getLive_flag() {
		return live_flag;
	}
	public void setLive_flag(String liveFlag) {
		live_flag = liveFlag;
	}
	public double getMain_pd_rate() {
		return main_pd_rate;
	}
	public void setMain_pd_rate(double mainPdRate) {
		main_pd_rate = mainPdRate;
	}
	public double getSub_pd_rate() {
		return sub_pd_rate;
	}
	public void setSub_pd_rate(double subPdRate) {
		sub_pd_rate = subPdRate;
	}
	public double getPlan_amt() {
		return plan_amt;
	}
	public void setPlan_amt(double planAmt) {
		plan_amt = planAmt;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public String getOrg_bd_btime() {
		return org_bd_btime;
	}
	public void setOrg_bd_btime(String orgBdBtime) {
		org_bd_btime = orgBdBtime;
	}

}