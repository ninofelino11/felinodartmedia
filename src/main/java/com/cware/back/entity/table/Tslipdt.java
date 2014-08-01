
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TSLIPDT
*
* @version 1.0, 2006/07/27
* @author Commerceware Ins.
*/
public class Tslipdt extends BaseEntity {

    public Tslipdt(){ super();}

    private String slip_i_no;
    private String slip_seq;
    private String goods_code;
    private String goodsdt_code;
    private String order_no;
    private String order_g_seq;
    private String order_d_seq;
    private String order_w_seq;
    private long   dely_qty;
    private double dely_amt;
    private String return_no;
    private long   return_qty;
    private String return_qc_code;
    private String picking_seq;
    private String rack_code;
    private String remark1_v;
    private String remark2_n;
    private String goods_type;
    private double dely_net;
    private double dely_vat;
	public String getSlip_i_no() {
		return slip_i_no;
	}
	public void setSlip_i_no(String slip_i_no) {
		this.slip_i_no = slip_i_no;
	}
	public String getSlip_seq() {
		return slip_seq;
	}
	public void setSlip_seq(String slip_seq) {
		this.slip_seq = slip_seq;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoodsdt_code() {
		return goodsdt_code;
	}
	public void setGoodsdt_code(String goodsdt_code) {
		this.goodsdt_code = goodsdt_code;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_g_seq() {
		return order_g_seq;
	}
	public void setOrder_g_seq(String order_g_seq) {
		this.order_g_seq = order_g_seq;
	}
	public String getOrder_d_seq() {
		return order_d_seq;
	}
	public void setOrder_d_seq(String order_d_seq) {
		this.order_d_seq = order_d_seq;
	}
	public String getOrder_w_seq() {
		return order_w_seq;
	}
	public void setOrder_w_seq(String order_w_seq) {
		this.order_w_seq = order_w_seq;
	}
	public long getDely_qty() {
		return dely_qty;
	}
	public void setDely_qty(long dely_qty) {
		this.dely_qty = dely_qty;
	}
	public double getDely_amt() {
		return dely_amt;
	}
	public void setDely_amt(double dely_amt) {
		this.dely_amt = dely_amt;
	}
	public String getReturn_no() {
		return return_no;
	}
	public void setReturn_no(String return_no) {
		this.return_no = return_no;
	}
	public long getReturn_qty() {
		return return_qty;
	}
	public void setReturn_qty(long return_qty) {
		this.return_qty = return_qty;
	}
	public String getReturn_qc_code() {
		return return_qc_code;
	}
	public void setReturn_qc_code(String return_qc_code) {
		this.return_qc_code = return_qc_code;
	}
	public String getPicking_seq() {
		return picking_seq;
	}
	public void setPicking_seq(String picking_seq) {
		this.picking_seq = picking_seq;
	}
	public String getRack_code() {
		return rack_code;
	}
	public void setRack_code(String rack_code) {
		this.rack_code = rack_code;
	}
	public String getRemark1_v() {
		return remark1_v;
	}
	public void setRemark1_v(String remark1_v) {
		this.remark1_v = remark1_v;
	}
	public String getRemark2_n() {
		return remark2_n;
	}
	public void setRemark2_n(String remark2_n) {
		this.remark2_n = remark2_n;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public double getDely_net() {
		return dely_net;
	}
	public void setDely_net(double dely_net) {
		this.dely_net = dely_net;
	}
	public double getDely_vat() {
		return dely_vat;
	}
	public void setDely_vat(double dely_vat) {
		this.dely_vat = dely_vat;
	}
} 