
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Card Interface Log TCARDLOG
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcardlog extends BaseEntity {

    public Tcardlog(){ super();}

    private long   card_log_no;
    private String card_no;
    private String cvv;
    private String van_code;
    private String card_code;
    private String valid_date;
    private long   pay_month;
    private double quest_amt;
    private String pos_id;
    private String cust_no;
    private String order_no;
    private String receipt_no;
    private String trans_type;
    private String approve_code;
    private String trans_no;
    private String auth_rrpid;
    private String term_seq;
    private String insert_date;
    private String insert_id;
    private String save_yn;
    private String event_name;
    private String response_code;
    private String response_msg;

    private String protx_vendortxcode;
    private String protx_status;
    private String protx_statusdetail;
    private String protx_vpstxid;
    private String protx_securitykey;
    private String protx_txauthno;

    private String protx_related_vendortxcode;
    private String protx_related_vpstxid;
    private String protx_related_securitykey;
    private String protx_related_txauthno;

    private String issue_number;

    public String getIssue_number() {
		return issue_number;
	}
	public void setIssue_number(String issue_number) {
		this.issue_number = issue_number;
	}
	public String getProtx_securitykey() {
		return protx_securitykey;
	}
	public void setProtx_securitykey(String protx_securitykey) {
		this.protx_securitykey = protx_securitykey;
	}
	public String getProtx_status() {
		return protx_status;
	}
	public void setProtx_status(String protx_status) {
		this.protx_status = protx_status;
	}
	public String getProtx_statusdetail() {
		return protx_statusdetail;
	}
	public void setProtx_statusdetail(String protx_statusdetail) {
		this.protx_statusdetail = protx_statusdetail;
	}
	public String getProtx_txauthno() {
		return protx_txauthno;
	}
	public void setProtx_txauthno(String protx_txauthno) {
		this.protx_txauthno = protx_txauthno;
	}
	public String getProtx_vendortxcode() {
		return protx_vendortxcode;
	}
	public void setProtx_vendortxcode(String protx_vendortxcode) {
		this.protx_vendortxcode = protx_vendortxcode;
	}
	public String getProtx_vpstxid() {
		return protx_vpstxid;
	}
	public void setProtx_vpstxid(String protx_vpstxid) {
		this.protx_vpstxid = protx_vpstxid;
	}
	/** Set Method **/
    public void setCard_log_no( long   card_log_no ){ this.card_log_no = card_log_no; }
    public void setCard_no( String card_no ){ this.card_no = card_no; }
    public void setCvv( String cvv ){ this.cvv = cvv; }
    public void setVan_code( String van_code ){ this.van_code = van_code; }
    public void setCard_code( String card_code ){ this.card_code = card_code; }
    public void setValid_date( String valid_date ){ this.valid_date = valid_date; }
    public void setPay_month( long   pay_month ){ this.pay_month = pay_month; }
    public void setQuest_amt( double   quest_amt ){ this.quest_amt = quest_amt; }
    public void setPos_id( String pos_id ){ this.pos_id = pos_id; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setReceipt_no( String receipt_no ){ this.receipt_no = receipt_no; }
    public void setTrans_type( String trans_type ){ this.trans_type = trans_type; }
    public void setApprove_code( String approve_code ){ this.approve_code = approve_code; }
    public void setTrans_no( String trans_no ){ this.trans_no = trans_no; }
    public void setAuth_rrpid( String auth_rrpid ){ this.auth_rrpid = auth_rrpid; }
    public void setTerm_seq( String term_seq ){ this.term_seq = term_seq; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setSave_yn( String save_yn ){ this.save_yn = save_yn; }
    public void setEvent_name( String event_name ){ this.event_name = event_name; }
    public void setResponse_code( String response_code ){ this.response_code = response_code; }
    public void setResponse_msg( String response_msg ){ this.response_msg = response_msg; }

    /** Get Method **/
    public long   getCard_log_no(){ return card_log_no; }
    public String getCard_no(){ return card_no; }
    public String getCvv(){ return cvv; }
    public String getVan_code(){ return van_code; }
    public String getCard_code(){ return card_code; }
    public String getValid_date(){ return valid_date; }
    public long   getPay_month(){ return pay_month; }
    public double getQuest_amt(){ return quest_amt; }
    public String getPos_id(){ return pos_id; }
    public String getCust_no(){ return cust_no; }
    public String getOrder_no(){ return order_no; }
    public String getReceipt_no(){ return receipt_no; }
    public String getTrans_type(){ return trans_type; }
    public String getApprove_code(){ return approve_code; }
    public String getTrans_no(){ return trans_no; }
    public String getAuth_rrpid(){ return auth_rrpid; }
    public String getTerm_seq(){ return term_seq; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getSave_yn(){ return save_yn; }
    public String getEvent_name(){ return event_name; }
    public String getResponse_code(){ return response_code; }
    public String getResponse_msg(){ return response_msg; }
	public String getProtx_related_securitykey() {
		return protx_related_securitykey;
	}
	public void setProtx_related_securitykey(String protx_related_securitykey) {
		this.protx_related_securitykey = protx_related_securitykey;
	}
	public String getProtx_related_txauthno() {
		return protx_related_txauthno;
	}
	public void setProtx_related_txauthno(String protx_related_txauthno) {
		this.protx_related_txauthno = protx_related_txauthno;
	}
	public String getProtx_related_vendortxcode() {
		return protx_related_vendortxcode;
	}
	public void setProtx_related_vendortxcode(String protx_related_vendortxcode) {
		this.protx_related_vendortxcode = protx_related_vendortxcode;
	}
	public String getProtx_related_vpstxid() {
		return protx_related_vpstxid;
	}
	public void setProtx_related_vpstxid(String protx_related_vpstxid) {
		this.protx_related_vpstxid = protx_related_vpstxid;
	}


}