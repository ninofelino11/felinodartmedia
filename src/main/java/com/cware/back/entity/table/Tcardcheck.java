
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 카드Prefix
*
* @version 1.0, 2006/07/10
* @author Commerceware Ins.
*/
public class Tcardcheck extends BaseEntity {

    public Tcardcheck(){ super();}

    private String card_no;
    private String card_no_old;
    private String card_code;
    private String card_name;
    private String card_no_len;
    private String norest_yn;
    private String card_type;
    private String use_yn;
    private String insert_id;
    private String insert_date;

    /** Set Method **/
    public void setCard_no( String card_no ){ this.card_no = card_no; }
    public void setCard_no_old( String card_no_old ){ this.card_no_old = card_no_old; }     //= 카드구분길이 update용
    public void setCard_code( String card_code ){ this.card_code = card_code; }
    public void setCard_name( String card_name ){ this.card_name = card_name; }
    public void setCard_no_len( String card_no_len ){ this.card_no_len = card_no_len; }
    public void setNorest_yn( String norest_yn ){ this.norest_yn = norest_yn; }
    public void setCard_type( String card_type ){ this.card_type = card_type; }
    public void setUse_yn( String use_yn ){ this.use_yn = use_yn; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }

    /** Get Method **/
    public String getCard_no(){ return card_no; }
    public String getCard_no_old(){ return card_no_old; }
    public String getCard_code(){ return card_code; }
    public String getCard_name(){ return card_name; }
    public String getCard_no_len(){ return card_no_len; }
    public String getNorest_yn(){ return norest_yn; }
    public String getCard_type(){ return card_type; }
    public String getUse_yn(){ return use_yn; }
    public String getInsert_id(){ return insert_id; }
    public String getInsert_date(){ return insert_date; }


} 