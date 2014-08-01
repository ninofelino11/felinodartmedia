
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* treat goods
*
* @version 1.0, 2007/01/15
* @author Commerceware Ins.
*/
public class Tmanualgoods extends BaseEntity {

    public Tmanualgoods(){ super();}

    private String goods_code;
    private String goodsdt_code;
    private long   in_goods_seq;
    private String in_goods_code;
    private String in_goodsdt_code;
    private long   in_goods_qty;
    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;
    
    private String flag;
    private String fix_rack;
    private String fix_rack_in;
    private long   work_qty;
    
    
    /** Set Method **/
    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }
    public void setGoodsdt_code(String goodsdt_code) {
        this.goodsdt_code = goodsdt_code;
    }
    public void setIn_goods_code(String in_goods_code) {
        this.in_goods_code = in_goods_code;
    }
    public void setIn_goods_qty(long in_goods_qty) {
        this.in_goods_qty = in_goods_qty;
    }
    public void setIn_goods_seq(long in_goods_seq) {
        this.in_goods_seq = in_goods_seq;
    }
    public void setIn_goodsdt_code(String in_goodsdt_code) {
        this.in_goodsdt_code = in_goodsdt_code;
    }
    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }
    public void setInsert_id(String insert_id) {
        this.insert_id = insert_id;
    }
    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }
    public void setModify_id(String modify_id) {
        this.modify_id = modify_id;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public void setFix_rack(String fix_rack) {
        this.fix_rack = fix_rack;
    }
    public void setFix_rack_in(String fix_rack_in) {
        this.fix_rack_in = fix_rack_in;
    }
    public void setWork_qty(long work_qty) {
        this.work_qty = work_qty;
    }
    
    
    
    /** Get Method **/
    public String getGoods_code() {
        return goods_code;
    }
    public String getGoodsdt_code() {
        return goodsdt_code;
    }
    public String getIn_goods_code() {
        return in_goods_code;
    }
    public long getIn_goods_qty() {
        return in_goods_qty;
    }
    public long getIn_goods_seq() {
        return in_goods_seq;
    }
    public String getIn_goodsdt_code() {
        return in_goodsdt_code;
    }
    public String getInsert_date() {
        return insert_date;
    }
    public String getInsert_id() {
        return insert_id;
    }
    public String getModify_date() {
        return modify_date;
    }
    public String getModify_id() {
        return modify_id;
    }
    
    public String getFlag() {
        return flag;
    }
    public String getFix_rack() {
        return fix_rack;
    }
    public String getFix_rack_in() {
        return fix_rack_in;
    }
    public long getWork_qty() {
        return work_qty;
    }

}
