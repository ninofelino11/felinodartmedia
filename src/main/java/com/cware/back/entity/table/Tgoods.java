
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 상품정보
*
* @version 1.0, 2006/08/02
* @author Commerceware Ins.
*/
public class Tgoods extends BaseEntity {

public Tgoods(){ super();}

    private String goods_code;
    private String goods_name;
    private String lgroup;
    private String mgroup;
    private String sgroup;
    private String dgroup;
    private String qc_lgroup;
    private String qc_mgroup;
    private String sale_gb;
    private String sign_gb;
    private String entp_code;
    private String entp_man_seq;
    private String account_gb;
    private String md_code;
    private String keyword;
    private String buy_med;
    private String dely_type;
    private String wh_code;
    private String post_yn;
    private String tax_yn;
    private double vat_rate;
    private String cost_tax_yn;
    private double cost_vat_rate;
    private String makeco_code;
    private String origin_code;
    private String brand_code;
    private String handle_code;
    private String mixpack_yn;
    private long   pack_box;
    private String sqc_yn;
    private String sqc_gb;
    private String set_yn;
    private String set_goods_yn;
    private String gift_yn;
    private String pay_yn;
    private String gift_return_yn;
    private String exch_yn;
    private String return_yn;
    private String as_yn;
    private String out_stock_yn;
    private long   order_min_qty;
    private String stock_chk_place;
    private String first_broad_date;
    private String order_media_all_yn;
    private String order_media;
    private String ars_name;
    private String parent_goods_code;
    private String norest_allot_yn;
    private String norest_allot_months;
    private String cust_dc_yn;
    private String sample_yn;
    private String sale_start_date;
    private String v_volume;
    private String master_code;
    private String in_unit;
    private long   in_unit_qty;
    private long   comment_cnt;
    private String base_goods_code;
    private String weight;
    private String dm_yn;
    private String form_code;
    private String size_code;
    private String scm_sign_gb;
    private String scm_sign_note;
    private String scm_goods_code;
    private String confirm_date;
    private String confirm_goods_code;

    private String insert_date;
    private String insert_id;
    private String modify_date;
    private String modify_id;

    private String wh_fix_yn;
    private String direct_ship_yn;
    private String direct_return_yn;
    private String advc_refund_yn;
    private String ship_man_seq;
    private String return_man_seq;
    private String order_make_yn;
    private String install_yn;
    private String lmsd_code;
    private long dely_box_qty;

    public String getLmsd_code() {
		return lmsd_code;
	}
	public void setLmsd_code(String lmsdCode) {
		lmsd_code = lmsdCode;
	}
	public String getWh_fix_yn() {
		return wh_fix_yn;
	}
	public void setWh_fix_yn(String whFixYn) {
		wh_fix_yn = whFixYn;
	}
	public String getDirect_ship_yn() {
		return direct_ship_yn;
	}
	public void setDirect_ship_yn(String directShipYn) {
		direct_ship_yn = directShipYn;
	}
	public String getDirect_return_yn() {
		return direct_return_yn;
	}
	public void setDirect_return_yn(String directReturnYn) {
		direct_return_yn = directReturnYn;
	}
	public String getAdvc_refund_yn() {
		return advc_refund_yn;
	}
	public void setAdvc_refund_yn(String advcRefundYn) {
		advc_refund_yn = advcRefundYn;
	}
	public String getShip_man_seq() {
		return ship_man_seq;
	}
	public void setShip_man_seq(String shipManSeq) {
		ship_man_seq = shipManSeq;
	}
	public String getReturn_man_seq() {
		return return_man_seq;
	}
	public void setReturn_man_seq(String returnManSeq) {
		return_man_seq = returnManSeq;
	}
	public String getOrder_make_yn() {
		return order_make_yn;
	}
	public void setOrder_make_yn(String orderMakeYn) {
		order_make_yn = orderMakeYn;
	}
	public String getInstall_yn() {
		return install_yn;
	}
	public void setInstall_yn(String installYn) {
		install_yn = installYn;
	}

	/** Set Method **/
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }
    public void setGoods_name( String goods_name ){ this.goods_name = goods_name; }
    public void setLgroup( String lgroup ){ this.lgroup = lgroup; }
    public void setMgroup( String mgroup ){ this.mgroup = mgroup; }
    public void setSgroup( String sgroup ){ this.sgroup = sgroup; }
    public void setDgroup( String dgroup ){ this.dgroup = dgroup; }
    public void setQc_lgroup( String qc_lgroup ){ this.qc_lgroup = qc_lgroup; }
    public void setQc_mgroup( String qc_mgroup ){ this.qc_mgroup = qc_mgroup; }
    public void setSale_gb( String sale_gb ){ this.sale_gb = sale_gb; }
    public void setSign_gb( String sign_gb ){ this.sign_gb = sign_gb; }
    public void setEntp_code( String entp_code ){ this.entp_code = entp_code; }
    public void setEntp_man_seq( String entp_man_seq ){ this.entp_man_seq = entp_man_seq; }
    public void setAccount_gb( String account_gb ){ this.account_gb = account_gb; }
    public void setMd_code( String md_code ){ this.md_code = md_code; }
    public void setKeyword( String keyword ){ this.keyword = keyword; }
    public void setBuy_med( String buy_med ){ this.buy_med = buy_med; }
    public void setDely_type( String dely_type ){ this.dely_type = dely_type; }
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }
    public void setPost_yn( String post_yn ){ this.post_yn = post_yn; }
    public void setTax_yn( String tax_yn ){ this.tax_yn = tax_yn; }
    public void setVat_rate( double vat_rate ){ this.vat_rate = vat_rate; }
    public void setCost_tax_yn( String cost_tax_yn ){ this.cost_tax_yn = cost_tax_yn; }
    public void setCost_vat_rate( double cost_vat_rate ){ this.cost_vat_rate = cost_vat_rate; }
    public void setMakeco_code( String makeco_code ){ this.makeco_code = makeco_code; }
    public void setOrigin_code( String origin_code ){ this.origin_code = origin_code; }
    public void setBrand_code( String brand_code ){ this.brand_code = brand_code; }
    public void setHandle_code( String handle_code ){ this.handle_code = handle_code; }
    public void setMixpack_yn( String mixpack_yn ){ this.mixpack_yn = mixpack_yn; }
    public void setPack_box( long pack_box ){ this.pack_box = pack_box; }
    public void setSqc_yn( String sqc_yn ){ this.sqc_yn = sqc_yn; }
    public void setSqc_gb( String sqc_gb ){ this.sqc_gb = sqc_gb; }
    public void setSet_yn( String set_yn ){ this.set_yn = set_yn; }
    public void setSet_goods_yn( String set_goods_yn ){ this.set_goods_yn = set_goods_yn; }
    public void setGift_yn( String gift_yn ){ this.gift_yn = gift_yn; }
    public void setPay_yn( String pay_yn ){ this.pay_yn = pay_yn; }
    public void setGift_return_yn( String gift_return_yn ){ this.gift_return_yn = gift_return_yn; }
    public void setExch_yn( String exch_yn ){ this.exch_yn = exch_yn; }
    public void setReturn_yn( String return_yn ){ this.return_yn = return_yn; }
    public void setAs_yn( String as_yn ){ this.as_yn = as_yn; }
    public void setOut_stock_yn( String out_stock_yn ){ this.out_stock_yn = out_stock_yn; }
    public void setOrder_min_qty( long   order_min_qty ){ this.order_min_qty = order_min_qty; }
    public void setStock_chk_place( String stock_chk_place ){ this.stock_chk_place = stock_chk_place; }
    public void setFirst_broad_date( String first_broad_date ){ this.first_broad_date = first_broad_date; }

    public void setOrder_media_all_yn( String order_media_all_yn ){ this.order_media_all_yn = order_media_all_yn; }
    public void setOrder_media( String order_media ){ this.order_media = order_media; }

    /*
    public void setTv_yn( String tv_yn ){ this.tv_yn = tv_yn; }
    public void setInternet_yn( String internet_yn ){ this.internet_yn = internet_yn; }
    public void setCatalog_yn( String catalog_yn ){ this.catalog_yn = catalog_yn; }
    public void setArs_yn( String ars_yn ){ this.ars_yn = ars_yn; }
    */
    public void setArs_name( String ars_name ){ this.ars_name = ars_name; }
    public void setParent_goods_code( String parent_goods_code ){ this.parent_goods_code = parent_goods_code; }
    public void setNorest_allot_yn( String norest_allot_yn ){ this.norest_allot_yn = norest_allot_yn; }
    public void setNorest_allot_months( String norest_allot_months ){ this.norest_allot_months = norest_allot_months; }
    public void setCust_dc_yn( String cust_dc_yn ){ this.cust_dc_yn = cust_dc_yn; }
    public void setInsert_date( String insert_date ){ this.insert_date = insert_date; }
    public void setInsert_id( String insert_id ){ this.insert_id = insert_id; }
    public void setModify_date( String modify_date ){ this.modify_date = modify_date; }
    public void setModify_id( String modify_id ){ this.modify_id = modify_id; }

    public void setForm_code( String form_code ){ this.form_code = form_code; }
    public void setSize_code( String size_code ){ this.size_code = size_code; }

    /** Get Method **/
    public String getGoods_code(){ return goods_code; }
    public String getGoods_name(){ return goods_name; }
    public String getLgroup(){ return lgroup; }
    public String getMgroup(){ return mgroup; }
    public String getSgroup(){ return sgroup; }
    public String getDgroup(){ return dgroup; }
    public String getQc_lgroup(){ return qc_lgroup; }
    public String getQc_mgroup(){ return qc_mgroup; }
    public String getSale_gb(){ return sale_gb; }
    public String getSign_gb(){ return sign_gb; }
    public String getEntp_code(){ return entp_code; }
    public String getEntp_man_seq(){ return entp_man_seq; }
    public String getAccount_gb(){ return account_gb; }
    public String getMd_code(){ return md_code; }
    public String getKeyword(){ return keyword; }
    public String getBuy_med(){ return buy_med; }
    public String getDely_type(){ return dely_type; }
    public String getWh_code(){ return wh_code; }
    public String getPost_yn(){ return post_yn; }
    public String getTax_yn(){ return tax_yn; }
    public double getVat_rate(){ return vat_rate; }
    public String getCost_tax_yn(){ return cost_tax_yn; }
    public double getCost_vat_rate(){ return cost_vat_rate; }
    public String getMakeco_code(){ return makeco_code; }
    public String getOrigin_code(){ return origin_code; }
    public String getBrand_code(){ return brand_code; }
    public String getHandle_code(){ return handle_code; }
    public String getMixpack_yn(){ return mixpack_yn; }
    public long   getPack_box(){ return pack_box; }
    public String getSqc_yn(){ return sqc_yn; }
    public String getSqc_gb(){ return sqc_gb; }
    public String getSet_yn(){ return set_yn; }
    public String getSet_goods_yn(){ return set_goods_yn; }
    public String getGift_yn(){ return gift_yn; }
    public String getPay_yn(){ return pay_yn; }
    public String getGift_return_yn(){ return gift_return_yn; }
    public String getExch_yn(){ return exch_yn; }
    public String getReturn_yn(){ return return_yn; }
    public String getAs_yn(){ return as_yn; }
    public String getOut_stock_yn(){ return out_stock_yn; }
    public long   getOrder_min_qty(){ return order_min_qty; }
    public String getStock_chk_place(){ return stock_chk_place; }
    public String getFirst_broad_date(){ return first_broad_date; }

    public String getOrder_media_all_yn(){ return order_media_all_yn; }
    public String getOrder_media(){ return order_media; }

    /*
    public String getTv_yn(){ return tv_yn; }
    public String getInternet_yn(){ return internet_yn; }
    public String getCatalog_yn(){ return catalog_yn; }
    public String getArs_yn(){ return ars_yn; }
    */
    public String getArs_name(){ return ars_name; }
    public String getParent_goods_code(){ return parent_goods_code; }
    public String getNorest_allot_yn(){ return norest_allot_yn; }
    public String getNorest_allot_months(){ return norest_allot_months; }
    public String getCust_dc_yn(){ return cust_dc_yn; }
    public String getInsert_date(){ return insert_date; }
    public String getInsert_id(){ return insert_id; }
    public String getModify_date(){ return modify_date; }
    public String getModify_id(){ return modify_id; }

    public String getForm_code(){ return form_code; }
    public String getSize_code(){ return size_code; }
    /* extra property */

    private long   cnt_balju;
    public  void   setCnt_balju( long   cnt_balju ){ this.cnt_balju = cnt_balju; }
    public  long   getCnt_balju(){ return cnt_balju; }

    private long   cnt_order;
    public  void   setCnt_order( long   cnt_order ){ this.cnt_order = cnt_order; }
    public  long   getCnt_order(){ return cnt_order; }

    private long   cnt_sale;
    public  void   setCnt_sale( long   cnt_sale ){ this.cnt_sale = cnt_sale; }
    public  long   getCnt_sale(){ return cnt_sale; }
	public String getMaster_code() {
		return master_code;
	}
	public void setMaster_code(String master_code) {
		this.master_code = master_code;
	}
	public String getSale_start_date() {
		return sale_start_date;
	}
	public void setSale_start_date(String sale_start_date) {
		this.sale_start_date = sale_start_date;
	}
	public String getSample_yn() {
		return sample_yn;
	}
	public void setSample_yn(String sample_yn) {
		this.sample_yn = sample_yn;
	}
	public String getV_volume() {
		return v_volume;
	}
	public void setV_volume(String v_volume) {
		this.v_volume = v_volume;
	}
	public String getIn_unit() {
		return in_unit;
	}
	public void setIn_unit(String in_unit) {
		this.in_unit = in_unit;
	}
	public long getIn_unit_qty() {
		return in_unit_qty;
	}
	public void setIn_unit_qty(long in_unit_qty) {
		this.in_unit_qty = in_unit_qty;
	}
	public long getComment_cnt() {
		return comment_cnt;
	}
	public void setComment_cnt(long comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	public String getBase_goods_code() {
		return base_goods_code;
	}
	public void setBase_goods_code(String base_goods_code) {
		this.base_goods_code = base_goods_code;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDm_yn() {
		return dm_yn;
	}
	public void setDm_yn(String dm_yn) {
		this.dm_yn = dm_yn;
	}
	public String getScm_sign_gb() {
		return scm_sign_gb;
	}
	public void setScm_sign_gb(String scm_sign_gb) {
		this.scm_sign_gb = scm_sign_gb;
	}
	public String getScm_sign_note() {
		return scm_sign_note;
	}
	public void setScm_sign_note(String scm_sign_note) {
		this.scm_sign_note = scm_sign_note;
	}
	public String getScm_goods_code() {
		return scm_goods_code;
	}
	public void setScm_goods_code(String scm_goods_code) {
		this.scm_goods_code = scm_goods_code;
	}
	public String getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getConfirm_goods_code() {
		return confirm_goods_code;
	}
	public void setConfirm_goods_code(String confirm_goods_code) {
		this.confirm_goods_code = confirm_goods_code;
	}
	public long getDely_box_qty() {
		return dely_box_qty;
	}
	public void setDely_box_qty(long delyBoxQty) {
		dely_box_qty = delyBoxQty;
	}

}