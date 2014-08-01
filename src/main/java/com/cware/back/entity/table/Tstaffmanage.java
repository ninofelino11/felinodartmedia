package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 쇼호스트정보
*
* @version 1.0, 2006/08/12
* @author Commerceware Ins.
*/
public class Tstaffmanage extends BaseEntity {

public Tstaffmanage(){ super();}

	private String seq_frame_no;
    private String bd_date;
    private String bd_btime;
    private String prog_code;
    private String staff_sno;
    private String staff_sno_org;
    private String modifydate;
    private String userid;
	private String staff_sno_rate;
	private String staff_flag;
    public String getStaff_flag() {
		return staff_flag;
	}
	public void setStaff_flag(String staffFlag) {
		staff_flag = staffFlag;
	}
	public String getSeq_frame_no() {
		return seq_frame_no;
	}
	public void setSeq_frame_no(String seqFrameNo) {
		seq_frame_no = seqFrameNo;
	}
	public String getBd_date() {
		return bd_date;
	}
	public void setBd_date(String bdDate) {
		bd_date = bdDate;
	}
	public String getBd_btime() {
		return bd_btime;
	}
	public void setBd_btime(String bdBtime) {
		bd_btime = bdBtime;
	}
	public String getProg_code() {
		return prog_code;
	}
	public void setProg_code(String progCode) {
		prog_code = progCode;
	}
	public String getStaff_sno() {
		return staff_sno;
	}
	public void setStaff_sno(String staffSno) {
		staff_sno = staffSno;
	}
	public String getStaff_sno_org() {
		return staff_sno_org;
	}
	public void setStaff_sno_org(String staffSnoOrg) {
		staff_sno_org = staffSnoOrg;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStaff_sno_rate() {
		return staff_sno_rate;
	}
	public void setStaff_sno_rate(String staffSnoRate) {
		staff_sno_rate = staffSnoRate;
	}



}