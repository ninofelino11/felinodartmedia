
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tgoodsinfom 정보
*
* @version 1.0, 2009/04/01
* @author  commerceware.co.kr [oh jungmin; jumgmin@commerceware.co.kr]
*/
public class Goodsinfom extends BaseEntity {

    public Goodsinfom(){ super();}

    private String cspf_flag;
    private String cspf_group;
    private String cspf_desc;
    private String use_yn;
    private String insert_date;
    private String insert_id;

	public String getCspf_desc() {
		return cspf_desc;
	}
	public void setCspf_desc(String cspf_desc) {
		this.cspf_desc = cspf_desc;
	}
	public String getCspf_flag() {
		return cspf_flag;
	}
	public void setCspf_flag(String cspf_flag) {
		this.cspf_flag = cspf_flag;
	}
	public String getCspf_group() {
		return cspf_group;
	}
	public void setCspf_group(String cspf_group) {
		this.cspf_group = cspf_group;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}



}