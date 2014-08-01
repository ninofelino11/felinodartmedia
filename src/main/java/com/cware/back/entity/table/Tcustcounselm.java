
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상담접수 Master
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tcustcounselm extends BaseEntity {

    public Tcustcounselm(){ super();}

    private String counsel_seq;
    private String cust_no;
    private String do_flag;
    private String ref_no1;
    private String ref_no2;
    private String ref_no3;
    private String ref_no4;
    private String out_lgroup_code;
    private String out_mgroup_code;
    private String goods_code;
    private String goodsdt_code;
    private String claim_no;
    private String ddd;
    private String tel1;
    private String tel2;
    private String tel3;
    private String tel;
    private String wild_yn;
    private String quick_yn;
    private String hc_req_date;
    private String autofax_yn;
    private String email_yn;
    private String from_email_addr;
    private String ip_addr;
    private long   search_cnt;
    private long   reco_cnt;
    private String delete_yn;
    private String remark;
    private String ref_id1;
    private String ref_id2;
    private String cs_send_yn;
    private String quick_end_yn;
    private String insert_id;
    private String insert_date;
    private String proc_id;
    private String proc_date;
    private String old_counsel_seq;
    private String old_out_lgroup_code;
    private String old_out_mgroup_code;
    
    /** Set Method **/
    public void setCounsel_seq( String counsel_seq ){ this.counsel_seq = counsel_seq; }
    public void setCust_no( String cust_no ){ this.cust_no = cust_no; }
    public void setDo_flag( String do_flag ){ this.do_flag = do_flag; }
    public void setRef_no1( String ref_no1 ){ this.ref_no1 = ref_no1; }
    public void setRef_no2( String ref_no2 ){ this.ref_no2 = ref_no2; }
    public void setRef_no3( String ref_no3 ){ this.ref_no3 = ref_no3; }
    public void setRef_no4( String ref_no4 ){ this.ref_no4 = ref_no4; }
    public void setOut_lgroup_code( String out_lgroup_code ){ this.out_lgroup_code = out_lgroup_code; }
    public void setOut_mgroup_code( String out_mgroup_code ){ this.out_mgroup_code = out_mgroup_code; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setClaim_no( String claim_no ){ this.claim_no = claim_no; }
    public void setDdd( String ddd ){ this.ddd = ddd; }
    public void setTel1( String tel1 ){ this.tel1 = tel1; }
    public void setTel2( String tel2 ){ this.tel2 = tel2; }
    public void setTel3( String tel3 ){ this.tel3 = tel3; }
    public void setTel( String tel ){ this.tel = tel; }
    public void setWild_yn( String wild_yn ){ this.wild_yn = wild_yn; }
    public void setQuick_yn( String quick_yn ){ this.quick_yn = quick_yn; }
    public void setHc_req_date( String hc_req_date ){ this.hc_req_date = hc_req_date; }
    public void setAutofax_yn( String autofax_yn ){ this.autofax_yn = autofax_yn; }
    public void setEmail_yn( String email_yn ){ this.email_yn = email_yn; }
    public void setFrom_email_addr( String from_email_addr ){ this.from_email_addr = from_email_addr; }
    public void setIp_addr( String ip_addr ){ this.ip_addr = ip_addr; }
    public void setSearch_cnt( long search_cnt ){ this.search_cnt = search_cnt; }
    public void setReco_cnt( long reco_cnt ){ this.reco_cnt = reco_cnt; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setRef_id1( String ref_id1 ){ this.ref_id1 = ref_id1; }
    public void setRef_id2( String ref_id2 ){ this.ref_id2 = ref_id2; }
    public void setCs_send_yn( String cs_send_yn ){ this.cs_send_yn = cs_send_yn; }
    public void setQuick_end_yn( String quick_end_yn ){ this.quick_end_yn = quick_end_yn; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setOld_counsel_seq( String old_counsel_seq ){ this.old_counsel_seq = old_counsel_seq; }
    public void setOld_out_lgroup_code( String old_out_lgroup_code ){ this.old_out_lgroup_code = old_out_lgroup_code; }
    public void setOld_out_mgroup_code( String old_out_mgroup_code ){ this.old_out_mgroup_code = old_out_mgroup_code; }

    /** Get Method **/
    public String getCounsel_seq(){ return counsel_seq; }
    public String getCust_no(){ return cust_no; }
    public String getDo_flag(){ return do_flag; }
    public String getRef_no1(){ return ref_no1; }
    public String getRef_no2(){ return ref_no2; }
    public String getRef_no3(){ return ref_no3; }
    public String getRef_no4(){ return ref_no4; }
    public String getOut_lgroup_code(){ return out_lgroup_code; }
    public String getOut_mgroup_code(){ return out_mgroup_code; }
    public String getGoods_code(){ return goods_code; }
    public String getGoodsdt_code(){ return goodsdt_code; }
    public String getClaim_no(){ return claim_no; }
    public String getDdd(){ return ddd; }
    public String getTel1(){ return tel1; }
    public String getTel2(){ return tel2; }
    public String getTel3(){ return tel3; }
    public String getTel(){ return tel; }
    public String getWild_yn(){ return wild_yn; }
    public String getQuick_yn(){ return quick_yn; }
    public String getHc_req_date(){ return hc_req_date; }
    public String getAutofax_yn(){ return autofax_yn; }
    public String getEmail_yn(){ return email_yn; }
    public String getFrom_email_addr(){ return from_email_addr; }
    public String getIp_addr(){ return ip_addr; }
    public long   getSearch_cnt(){ return search_cnt; }
    public long   getReco_cnt(){ return reco_cnt; }
    public String getDelete_yn(){ return delete_yn; }
    public String getRemark(){ return remark; }
    public String getRef_id1(){ return ref_id1; }
    public String getRef_id2(){ return ref_id2; }
    public String getCs_send_yn(){ return cs_send_yn; }
    public String getQuick_end_yn(){ return quick_end_yn; }
    public String getInsert_id(){ return insert_id; }
    public String getInsert_date(){ return insert_date; }
    public String getProc_id(){ return proc_id; }
    public String getProc_date(){ return proc_date; }
    public String getOld_counsel_seq(){ return old_counsel_seq; }
    public String getOld_out_lgroup_code(){ return old_out_lgroup_code; }
    public String getOld_out_mgroup_code(){ return old_out_mgroup_code; }

}                                                                                                      