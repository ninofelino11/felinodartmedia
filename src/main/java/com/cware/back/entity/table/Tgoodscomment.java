
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품평가
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Tgoodscomment extends BaseEntity {

    public Tgoodscomment(){ super();}

    private String comment_seq;
    private String goods_code;
    private String comment_title;
    private String comment_content;
    private String comment_score;
    private String display_yn;
    private String display_priority;
    private String delete_yn;
    private String search_cnt;
    private String reco_cnt;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    private String proc_date;
    private String proc_id;
    private String comment_score1;
    private String comment_score2;
    private String comment_score3;
    private String comment_score4;
    private String comment_score5;
    private String comment_score6;
    private String comment_score7;
    private String saveamt_yn;
    private String special_display_yn;
    private String skin_type;
    private String cust_no;

    /** Set Method **/
    public void setComment_seq( String comment_seq ){ this.comment_seq = comment_seq; }
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setComment_title( String comment_title ){ this.comment_title = comment_title; }
    public void setComment_content( String comment_content ){ this.comment_content = comment_content; }
    public void setComment_score( String comment_score ){ this.comment_score = comment_score; }
    public void setDisplay_yn( String display_yn ){ this.display_yn = display_yn; }
    public void setDisplay_priority( String display_priority ){ this.display_priority = display_priority; }
    public void setDelete_yn( String delete_yn ){ this.delete_yn = delete_yn; }
    public void setSearch_cnt( String search_cnt ){ this.search_cnt = search_cnt; }
    public void setReco_cnt( String reco_cnt ){ this.reco_cnt = reco_cnt; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setProc_id( String proc_id ){ this.proc_id = proc_id; }
    public void setComment_score1( String comment_score1 ){ this.comment_score1 = comment_score1; }
    public void setComment_score2( String comment_score2 ){ this.comment_score2 = comment_score2; }
    public void setComment_score3( String comment_score3 ){ this.comment_score3 = comment_score3; }
    public void setComment_score4( String comment_score4 ){ this.comment_score4 = comment_score4; }
    public void setComment_score5( String comment_score5 ){ this.comment_score5 = comment_score5; }
    public void setComment_score6( String comment_score6 ){ this.comment_score6 = comment_score6; }
    public void setComment_score7( String comment_score7 ){ this.comment_score7 = comment_score7; }
    public void setSaveamt_yn ( String saveamt_yn ){this.saveamt_yn = saveamt_yn; }
    public void setSpecial_display_yn ( String special_display_yn ){ this.special_display_yn = special_display_yn; }
    public void setSkin_type ( String skin_type ) { this.skin_type = skin_type ; }
    public void setCust_no ( String cust_no ) { this.cust_no = cust_no ; }


    /** Get Method **/
    public String getComment_seq(){ return comment_seq; }
    public String getGoods_code(){ return goods_code; }
    public String getComment_title(){ return comment_title; }
    public String getComment_content(){ return comment_content; }
    public String getComment_score(){ return comment_score; }
    public String getDisplay_yn(){ return display_yn; }
    public String getDisplay_priority(){ return display_priority; }
    public String getDelete_yn(){ return delete_yn; }
    public String getSearch_cnt(){ return search_cnt; }
    public String getReco_cnt(){ return reco_cnt; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }
    public String getProc_date(){ return proc_date; }
    public String getProc_id(){ return proc_id; }
    public String getComment_score1(){ return comment_score1; }
    public String getComment_score2(){ return comment_score2; }
    public String getComment_score3(){ return comment_score3; }
    public String getComment_score4(){ return comment_score4; }
    public String getComment_score5(){ return comment_score5; }
    public String getComment_score6(){ return comment_score6; }
    public String getComment_score7(){ return comment_score7; }
    public String getSaveamt_yn(){ return saveamt_yn; }
    public String getSpecial_display_yn() { return special_display_yn;}
    public String getSkin_type() { return skin_type; }
    public String getCust_no() { return cust_no; }
}