
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 프로모션 기본정보
*
* @version 1.0, 2006/07/18
* @author Commerceware Ins.
*/
public class Tcarddt extends BaseEntity {


    public Tcarddt(){ super();}

    private String card_code;
    private String proc_date;
    private String month;
    private String norest_rate;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setCard_code( String card_code ){ this.card_code = card_code; }
    public void setProc_date( String proc_date ){ this.proc_date = proc_date; }
    public void setMonth( String month ){ this.month = month; }
    public void setNorest_rate( String norest_rate ){ this.norest_rate = norest_rate; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getCard_code(){ return card_code; }
    public String getProc_date(){ return proc_date; }
    public String getMonth(){ return month; }
    public String getNorest_rate(){ return norest_rate; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


}