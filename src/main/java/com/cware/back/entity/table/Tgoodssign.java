
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품결재
*
* @version 1.0, 2006/07/21
* @author Commerceware Ins.
*/
public class Tgoodssign extends BaseEntity {

    public Tgoodssign(){ super();}

    private String goods_code;
    private String sign_seq;
    private String apply_date;
    private double buy_cost;
    private double buy_vat;
    private double buy_vat_rate;
    private double buy_price;
    private double sale_price;
    private double sale_vat;
    private double sale_vat_rate;
    private double cust_price;
    private double saveamt_rate;
    private double saveamt;
    private String sign_gb;
    private String sign_date;
    private String sign_id;
    private String sign_no_code;
    private String sign_no_note;
    private String sign_text;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setSign_seq( String sign_seq ){ this.sign_seq = sign_seq; }
    public void setApply_date( String apply_date ){ this.apply_date = apply_date; }
    public void setBuy_cost( double buy_cost ){ this.buy_cost = buy_cost; }
    public void setBuy_vat( double buy_vat ){ this.buy_vat = buy_vat; }
    public void setBuy_vat_rate( double buy_vat_rate ){ this.buy_vat_rate = buy_vat_rate; }
    public void setBuy_price( double buy_price ){ this.buy_price = buy_price; }
    public void setSale_price( double sale_price ){ this.sale_price = sale_price; }
    public void setSale_vat( double sale_vat ){ this.sale_vat = sale_vat; }
    public void setSale_vat_rate( double sale_vat_rate ){ this.sale_vat_rate = sale_vat_rate; }
    public void setCust_price( double cust_price ){ this.cust_price = cust_price; }
    public void setSaveamt_rate( double saveamt_rate ){ this.saveamt_rate = saveamt_rate; }
    public void setSaveamt( double saveamt ){ this.saveamt = saveamt; }
    public void setSign_gb( String sign_gb ){ this.sign_gb = sign_gb; }
    public void setSign_date( String sign_date ){ this.sign_date = sign_date; }
    public void setSign_id( String sign_id ){ this.sign_id = sign_id; }
    public void setSign_no_code( String sign_no_code ){ this.sign_no_code = sign_no_code; }
    public void setSign_no_note( String sign_no_note ){ this.sign_no_note = sign_no_note; }
    public void setSign_text( String sign_text ){ this.sign_text = sign_text; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getSign_seq(){ return sign_seq; }
    public String getApply_date(){ return apply_date; }
    public double getBuy_cost(){ return buy_cost; }
    public double getBuy_vat(){ return buy_vat; }
    public double getBuy_vat_rate(){ return buy_vat_rate; }
    public double getBuy_price(){ return buy_price; }
    public double getSale_price(){ return sale_price; }
    public double getSale_vat(){ return sale_vat; }
    public double getSale_vat_rate(){ return sale_vat_rate; }
    public double getCust_price(){ return cust_price; }
    public double getSaveamt_rate(){ return saveamt_rate; }
    public double getSaveamt(){ return saveamt; }
    public String getSign_gb(){ return sign_gb; }
    public String getSign_date(){ return sign_date; }
    public String getSign_id(){ return sign_id; }
    public String getSign_no_code(){ return sign_no_code; }
    public String getSign_no_note(){ return sign_no_note; }
    public String getSign_text(){ return sign_text; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }


}