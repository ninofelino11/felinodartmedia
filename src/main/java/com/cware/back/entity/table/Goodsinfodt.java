
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tgoodsinfodt 정보
*
* @version 1.0, 2008/04/01
* @author  commerceware.co.kr [oh jungmin; jumgmin@commerceware.co.kr]
*/
public class Goodsinfodt extends BaseEntity {

    public Goodsinfodt(){ super();}

    private String cspf_group;
    private String cspf_code;
    private String cspf_name;
    private String use_yn;
    private String insert_date;
    private String insert_id;

	public String getCspf_code() {
		return cspf_code;
	}
	public void setCspf_code(String cspf_code) {
		this.cspf_code = cspf_code;
	}
	public String getCspf_group() {
		return cspf_group;
	}
	public void setCspf_group(String cspf_group) {
		this.cspf_group = cspf_group;
	}
	public String getCspf_name() {
		return cspf_name;
	}
	public void setCspf_name(String cspf_name) {
		this.cspf_name = cspf_name;
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