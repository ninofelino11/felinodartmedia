
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카드Master
*
* @version 1.0, 2006/07/18
* @author Commerceware Ins.
*/
public class Tcardcode extends BaseEntity {

    public Tcardcode(){ super();}

    private String card_code;
    private String card_name;
    private String business_no;
    private String card_man_name;
    private String card_man_ddd;
    private String card_man_tel1;
    private String card_man_tel2;
    private String card_man_tel3;
    private String rest_no;
    private String rest_rate;
    private String norest_no;
    private String norest_rate;
    private String m_van_comp;
    private String s_van_comp;
    private String ins_van_comp;
    private String use_yn;
    private String remark;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setCard_code( String card_code ){ this.card_code = card_code; }
    public void setCard_name( String card_name ){ this.card_name = card_name; }
    public void setBusiness_no( String business_no ){ this.business_no = business_no; }
    public void setCard_man_name( String card_man_name ){ this.card_man_name = card_man_name; }
    public void setCard_man_ddd( String card_man_ddd ){ this.card_man_ddd = card_man_ddd; }
    public void setCard_man_tel1( String card_man_tel1 ){ this.card_man_tel1 = card_man_tel1; }
    public void setCard_man_tel2( String card_man_tel2 ){ this.card_man_tel2 = card_man_tel2; }
    public void setCard_man_tel3( String card_man_tel3 ){ this.card_man_tel3 = card_man_tel3; }
    public void setRest_no( String rest_no ){ this.rest_no = rest_no; }
    public void setRest_rate( String rest_rate ){ this.rest_rate = rest_rate; }
    public void setNorest_no( String norest_no ){ this.norest_no = norest_no; }
    public void setNorest_rate( String norest_rate ){ this.norest_rate = norest_rate; }
    public void setM_van_comp( String m_van_comp ){ this.m_van_comp = m_van_comp; }
    public void setS_van_comp( String s_van_comp ){ this.s_van_comp = s_van_comp; }
    public void setIns_van_comp( String ins_van_comp ){ this.ins_van_comp = ins_van_comp; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setRemark( String remark ){ this.remark = remark; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getCard_code(){ return card_code; }
    public String getCard_name(){ return card_name; }
    public String getBusiness_no(){ return business_no; }
    public String getCard_man_name(){ return card_man_name; }
    public String getCard_man_ddd(){ return card_man_ddd; }
    public String getCard_man_tel1(){ return card_man_tel1; }
    public String getCard_man_tel2(){ return card_man_tel2; }
    public String getCard_man_tel3(){ return card_man_tel3; }
    public String getRest_no(){ return rest_no; }
    public String getRest_rate(){ return rest_rate; }
    public String getNorest_no(){ return norest_no; }
    public String getNorest_rate(){ return norest_rate; }
    public String getM_van_comp(){ return m_van_comp; }
    public String getS_van_comp(){ return s_van_comp; }
    public String getIns_van_comp(){ return ins_van_comp; }
    public String getUse_yn(){ return use_yn; }
    public String getRemark(){ return remark; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


}