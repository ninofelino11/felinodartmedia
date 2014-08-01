
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 주문재고정보
*
* @version 1.0, 2006/07/07
* @author Commerceware Ins.
*/
public class Tordershipcost extends BaseEntity {

public Tordershipcost(){ super();}

    private String order_no;
    private String seq;
    private String receiver_seq;
    private String type;
    private String shpfee_code;
    private double shpfee_cost;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setOrder_no( String order_no ){ this.order_no = order_no; }
    public void setSeq( String seq ){ this.seq = seq; }
    public void setReceiver_seq( String receiver_seq ){ this.receiver_seq = receiver_seq; }
    public void setType( String type ){ this.type = type; }
    public void setShpfee_code( String shpfee_code ){ this.shpfee_code = shpfee_code; }
    public void setShpfee_cost( double shpfee_cost ){ this.shpfee_cost = shpfee_cost; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getOrder_no(){ return order_no; }
    public String getSeq(){ return seq; }
    public String getReceiver_seq(){ return receiver_seq; }
    public String getType(){ return type; }
    public String getShpfee_code(){ return shpfee_code; }
    public double getShpfee_cost(){ return shpfee_cost; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


} 