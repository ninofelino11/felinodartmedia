
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TAREASHIPCOST_LGSTS
*
* @version 1.0, 2011/02/14
* @author Commerceware Ins.
*/
public class TareashipcostLgsts extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public TareashipcostLgsts(){ super();}

    private String dely_gb;
    private String area_gb;
    private String apply_date;
    private String weight;
    private String ship_fee;
    private String return_fee;
    private String exch_fee;
    private String as_fee;
    private String cod_yn;
    private String insert_id;
    private String insert_date;
    private String modify_id;
    private String modify_date;
    
	public String getDely_gb() {
		return dely_gb;
	}
	public void setDely_gb(String delyGb) {
		dely_gb = delyGb;
	}
	public String getArea_gb() {
		return area_gb;
	}
	public void setArea_gb(String areaGb) {
		area_gb = areaGb;
	}
	public String getApply_date() {
		return apply_date;
	}
	public void setApply_date(String applyDate) {
		apply_date = applyDate;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getShip_fee() {
		return ship_fee;
	}
	public void setShip_fee(String shipFee) {
		ship_fee = shipFee;
	}
	public String getReturn_fee() {
		return return_fee;
	}
	public void setReturn_fee(String returnFee) {
		return_fee = returnFee;
	}
	public String getExch_fee() {
		return exch_fee;
	}
	public void setExch_fee(String exchFee) {
		exch_fee = exchFee;
	}
	public String getAs_fee() {
		return as_fee;
	}
	public void setAs_fee(String asFee) {
		as_fee = asFee;
	}
	public String getCod_yn() {
		return cod_yn;
	}
	public void setCod_yn(String codYn) {
		cod_yn = codYn;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insertId) {
		insert_id = insertId;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insertDate) {
		insert_date = insertDate;
	}
	public String getModify_id() {
		return modify_id;
	}
	public void setModify_id(String modifyId) {
		modify_id = modifyId;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modifyDate) {
		modify_date = modifyDate;
	}
}
