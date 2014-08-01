package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* tdtbroad
* @version 1.0, 2009/01/13
*/
public class Tdtbroad  extends BaseEntity{

	public Tdtbroad(){ super();}

	private String Tape_code;
	private String Bd_date;
	private String Bd_btime;
	private String Prog_code;
	private String Goods_code;
	private long   Seqno;
	private String Btime;
	private String Etime;
	private String Run_time;
	private String Befor_yn;
	private String Guest;
	private long   Sale_tg_qty;
	private String Spec_sh;
	private String Spec_pd;
	private String Spec_frm;
	private String Promo_id;
	private String Spare_yn;
	private String Userid;
	private String Modifydate;
	private long   Counsel_qty;
	private String Send_flag;
	private long   Order_qty;
	private String Hope_date;
	private String Special_yn;
	private String Broad_yn;
	private String Pdin_yn;
	private String Sale_end_time;
	private String Goods_seq;
	private String Bd_flag;
	private long   Stime_seq;
	private long   Samp_qty;
	private String Broadprice;

	//= user modify
	private String Prev_bd_btime; //= MAX(BD_BTIME)
	private String Next_bd_btime; //= MIN(BD_BTIME)
	private String Temp_sno;
	private String Temp_sno1;
	private double Temp_sno_rate;
	private double Temp_sno1_rate;

	public String getBd_btime() {
		return Bd_btime;
	}
	public void setBd_btime(String bd_btime) {
		Bd_btime = bd_btime;
	}
	public String getBd_date() {
		return Bd_date;
	}
	public void setBd_date(String bd_date) {
		Bd_date = bd_date;
	}
	public String getBd_flag() {
		return Bd_flag;
	}
	public void setBd_flag(String bd_flag) {
		Bd_flag = bd_flag;
	}
	public String getBefor_yn() {
		return Befor_yn;
	}
	public void setBefor_yn(String befor_yn) {
		Befor_yn = befor_yn;
	}
	public String getBroad_yn() {
		return Broad_yn;
	}
	public void setBroad_yn(String broad_yn) {
		Broad_yn = broad_yn;
	}
	public String getBroadprice() {
		return Broadprice;
	}
	public void setBroadprice(String broadprice) {
		Broadprice = broadprice;
	}
	public String getBtime() {
		return Btime;
	}
	public void setBtime(String btime) {
		Btime = btime;
	}
	public long getCounsel_qty() {
		return Counsel_qty;
	}
	public void setCounsel_qty(long counsel_qty) {
		Counsel_qty = counsel_qty;
	}
	public String getEtime() {
		return Etime;
	}
	public void setEtime(String etime) {
		Etime = etime;
	}
	public String getGoods_code() {
		return Goods_code;
	}
	public void setGoods_code(String goods_code) {
		Goods_code = goods_code;
	}
	public String getGoods_seq() {
		return Goods_seq;
	}
	public void setGoods_seq(String goods_seq) {
		Goods_seq = goods_seq;
	}
	public String getGuest() {
		return Guest;
	}
	public void setGuest(String guest) {
		Guest = guest;
	}
	public String getHope_date() {
		return Hope_date;
	}
	public void setHope_date(String hope_date) {
		Hope_date = hope_date;
	}
	public String getModifydate() {
		return Modifydate;
	}
	public void setModifydate(String modifydate) {
		Modifydate = modifydate;
	}
	public long getOrder_qty() {
		return Order_qty;
	}
	public void setOrder_qty(long order_qty) {
		Order_qty = order_qty;
	}
	public String getPdin_yn() {
		return Pdin_yn;
	}
	public void setPdin_yn(String pdin_yn) {
		Pdin_yn = pdin_yn;
	}
	public String getProg_code() {
		return Prog_code;
	}
	public void setProg_code(String prog_code) {
		Prog_code = prog_code;
	}
	public String getPromo_id() {
		return Promo_id;
	}
	public void setPromo_id(String promo_id) {
		Promo_id = promo_id;
	}
	public String getRun_time() {
		return Run_time;
	}
	public void setRun_time(String run_time) {
		Run_time = run_time;
	}
	public String getSale_end_time() {
		return Sale_end_time;
	}
	public void setSale_end_time(String sale_end_time) {
		Sale_end_time = sale_end_time;
	}
	public long getSale_tg_qty() {
		return Sale_tg_qty;
	}
	public void setSale_tg_qty(long sale_tg_qty) {
		Sale_tg_qty = sale_tg_qty;
	}
	public long getSamp_qty() {
		return Samp_qty;
	}
	public void setSamp_qty(long samp_qty) {
		Samp_qty = samp_qty;
	}
	public String getSend_flag() {
		return Send_flag;
	}
	public void setSend_flag(String send_flag) {
		Send_flag = send_flag;
	}
	public long getSeqno() {
		return Seqno;
	}
	public void setSeqno(long seqno) {
		Seqno = seqno;
	}
	public String getSpare_yn() {
		return Spare_yn;
	}
	public void setSpare_yn(String spare_yn) {
		Spare_yn = spare_yn;
	}
	public String getSpec_frm() {
		return Spec_frm;
	}
	public void setSpec_frm(String spec_frm) {
		Spec_frm = spec_frm;
	}
	public String getSpec_pd() {
		return Spec_pd;
	}
	public void setSpec_pd(String spec_pd) {
		Spec_pd = spec_pd;
	}
	public String getSpec_sh() {
		return Spec_sh;
	}
	public void setSpec_sh(String spec_sh) {
		Spec_sh = spec_sh;
	}
	public String getSpecial_yn() {
		return Special_yn;
	}
	public void setSpecial_yn(String special_yn) {
		Special_yn = special_yn;
	}
	public long getStime_seq() {
		return Stime_seq;
	}
	public void setStime_seq(long stime_seq) {
		Stime_seq = stime_seq;
	}
	public String getUserid() {
		return Userid;
	}
	public void setUserid(String userid) {
		Userid = userid;
	}
	public String getNext_bd_btime() {
		return Next_bd_btime;
	}
	public void setNext_bd_btime(String next_bd_btime) {
		Next_bd_btime = next_bd_btime;
	}
	public String getPrev_bd_btime() {
		return Prev_bd_btime;
	}
	public void setPrev_bd_btime(String prev_bd_btime) {
		Prev_bd_btime = prev_bd_btime;
	}
	public String getTape_code() {
		return Tape_code;
	}
	public void setTape_code(String tape_code) {
		Tape_code = tape_code;
	}
	public String getTemp_sno() {
		return Temp_sno;
	}
	public void setTemp_sno(String temp_sno) {
		Temp_sno = temp_sno;
	}
	public double getTemp_sno_rate() {
		return Temp_sno_rate;
	}
	public void setTemp_sno_rate(double temp_sno_rate) {
		Temp_sno_rate = temp_sno_rate;
	}
	public double getTemp_sno1_rate() {
		return Temp_sno1_rate;
	}
	public void setTemp_sno1_rate(double temp_sno1_rate) {
		Temp_sno1_rate = temp_sno1_rate;
	}
	public String getTemp_sno1() {
		return Temp_sno1;
	}
	public void setTemp_sno1(String temp_sno1) {
		Temp_sno1 = temp_sno1;
	}




}
