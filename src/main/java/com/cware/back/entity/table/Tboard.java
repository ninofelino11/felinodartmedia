package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* tboard
* @version 1.0, 2008/07/30
*/
public class Tboard  extends BaseEntity{

	public Tboard(){ super();}

    private long   board_seq;
    private String board_title;
    private String board_short_content;
    private String board_content;
    private long   search_cnt;
    private long   reco_cnt;
    private long   board_ref;
    private long   board_step;
    private long   board_level;
    private long   p_board_seq;
    private long   p_board_step;
    private long   p_board_level;
    private String p_reply_yn;
    private String p_delete_yn;
    private String p_sign_yn;
    private String ip_addr;
    private String cust_no;
    private String board_lkinds;
    private String board_mkinds;
    private String email_addr;
    private String email_ret_yn;
    private String reply_yn;
    private String state_flag;
    private String sign_yn;
    private String sign_date;
    private String sign_id;
    private String delete_yn;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public long getBoard_level() {
		return board_level;
	}
	public void setBoard_level(long board_level) {
		this.board_level = board_level;
	}
	public String getBoard_lkinds() {
		return board_lkinds;
	}
	public void setBoard_lkinds(String board_lkinds) {
		this.board_lkinds = board_lkinds;
	}
	public String getBoard_mkinds() {
		return board_mkinds;
	}
	public void setBoard_mkinds(String board_mkinds) {
		this.board_mkinds = board_mkinds;
	}
	public long getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(long board_ref) {
		this.board_ref = board_ref;
	}
	public long getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(long board_seq) {
		this.board_seq = board_seq;
	}
	public String getBoard_short_content() {
		return board_short_content;
	}
	public void setBoard_short_content(String board_short_content) {
		this.board_short_content = board_short_content;
	}
	public long getBoard_step() {
		return board_step;
	}
	public void setBoard_step(long board_step) {
		this.board_step = board_step;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getEmail_ret_yn() {
		return email_ret_yn;
	}
	public void setEmail_ret_yn(String email_ret_yn) {
		this.email_ret_yn = email_ret_yn;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public long getReco_cnt() {
		return reco_cnt;
	}
	public void setReco_cnt(long reco_cnt) {
		this.reco_cnt = reco_cnt;
	}
	public String getReply_yn() {
		return reply_yn;
	}
	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}
	public long getSearch_cnt() {
		return search_cnt;
	}
	public void setSearch_cnt(long search_cnt) {
		this.search_cnt = search_cnt;
	}
	public String getSign_date() {
		return sign_date;
	}
	public void setSign_date(String sign_date) {
		this.sign_date = sign_date;
	}
	public String getSign_id() {
		return sign_id;
	}
	public void setSign_id(String sign_id) {
		this.sign_id = sign_id;
	}
	public String getSign_yn() {
		return sign_yn;
	}
	public void setSign_yn(String sign_yn) {
		this.sign_yn = sign_yn;
	}
	public String getState_flag() {
		return state_flag;
	}
	public void setState_flag(String state_flag) {
		this.state_flag = state_flag;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modify_id) {
		this.modify_id = modify_id;
	}
	public long getP_board_level() {
		return p_board_level;
	}
	public void setP_board_level(long p_board_level) {
		this.p_board_level = p_board_level;
	}
	public long getP_board_step() {
		return p_board_step;
	}
	public void setP_board_step(long p_board_step) {
		this.p_board_step = p_board_step;
	}
	public long getP_board_seq() {
		return p_board_seq;
	}
	public void setP_board_seq(long p_board_seq) {
		this.p_board_seq = p_board_seq;
	}
	public String getP_delete_yn() {
		return p_delete_yn;
	}
	public void setP_delete_yn(String p_delete_yn) {
		this.p_delete_yn = p_delete_yn;
	}
	public String getP_reply_yn() {
		return p_reply_yn;
	}
	public void setP_reply_yn(String p_reply_yn) {
		this.p_reply_yn = p_reply_yn;
	}
	public String getP_sign_yn() {
		return p_sign_yn;
	}
	public void setP_sign_yn(String p_sign_yn) {
		this.p_sign_yn = p_sign_yn;
	}





}
