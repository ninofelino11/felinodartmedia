
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 입고정보M
*
* @version 1.0, 2006/08/07
* @author Commerceware Ins.
*/
public class Tinm extends BaseEntity {

public Tinm(){ super();}

    private String in_no;
    private String balju_no;
    private String wh_code;
    private String entp_code;
    private String in_date;
    private String in_man_id;
    private String in_print_cnt;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setIn_no( String in_no ){ this.in_no = in_no; }
    public void setBalju_no( String balju_no ){ this.balju_no = balju_no; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setIn_date( String in_date ){ this.in_date = in_date; }
    public void setIn_man_id( String in_man_id ){ this.in_man_id = in_man_id; }
    public void setIn_print_cnt( String in_print_cnt ){ this.in_print_cnt = in_print_cnt; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getIn_no(){ return in_no; }
    public String getBalju_no(){ return balju_no; }
    public String getWh_code(){ return wh_code; }
    public String getEntp_code(){ return entp_code; }
    public String getIn_date(){ return in_date; }
    public String getIn_man_id(){ return in_man_id; }
    public String getIn_print_cnt(){ return in_print_cnt; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }

} 