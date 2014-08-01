
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* ARS공제
*
* @version 1.0, 2006/07/27
* @author Commerceware Ins.
*/
public class Tarskongje extends BaseEntity {

    public Tarskongje(){ super();}

    private String goods_code;
    private String seq;
    private String ars_bdate;
    private String ars_edate;
    private double entp_amt;
    private double com_amt;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setSeq( String seq ){ this.seq = seq; }
    public void setArs_bdate( String ars_bdate ){ this.ars_bdate = ars_bdate; }
    public void setArs_edate( String ars_edate ){ this.ars_edate = ars_edate; }
    public void setEntp_amt( double entp_amt ){ this.entp_amt = entp_amt; }
    public void setCom_amt( double com_amt ){ this.com_amt = com_amt; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getSeq(){ return seq; }
    public String getArs_bdate(){ return ars_bdate; }
    public String getArs_edate(){ return ars_edate; }
    public double getEntp_amt(){ return entp_amt; }
    public double getCom_amt(){ return com_amt; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


} 