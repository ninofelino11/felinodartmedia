
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* TSKINTESTRECIPE
*
* @version 1.0, 2007/02/26
* @author Commerceware Ins.
*/
public class Tskintestrecipe extends BaseEntity {

    public Tskintestrecipe(){ super();}

    private String skin_type;
    private String skin_feature;
    private String skin_feature_man;
    private String skin_trouble;
    private String skin_trouble_man;
    private String skin_cleansing;
    private String skin_shaving_man;
    private String skin_care;
    private String skin_care_man;
    private String season_service;
    private String season_service_man;
    private String insert_date;
    private String insert_id;
    

    /** Set Method **/

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public void setSeason_service(String season_service) {
		this.season_service = season_service;
	}
	public void setSeason_service_man(String season_service_man) {
		this.season_service_man = season_service_man;
	}
	public void setSkin_care(String skin_care) {
		this.skin_care = skin_care;
	}
	public void setSkin_care_man(String skin_care_man) {
		this.skin_care_man = skin_care_man;
	}
	public void setSkin_cleansing(String skin_cleansing) {
		this.skin_cleansing = skin_cleansing;
	}
	public void setSkin_feature(String skin_feature) {
		this.skin_feature = skin_feature;
	}
	public void setSkin_feature_man(String skin_feature_man) {
		this.skin_feature_man = skin_feature_man;
	}
	public void setSkin_shaving_man(String skin_shaving_man) {
		this.skin_shaving_man = skin_shaving_man;
	}
	public void setSkin_trouble(String skin_trouble) {
		this.skin_trouble = skin_trouble;
	}
	public void setSkin_trouble_man(String skin_trouble_man) {
		this.skin_trouble_man = skin_trouble_man;
	}
	public void setSkin_type(String skin_type) {
		this.skin_type = skin_type;
	}

    /** Get Method **/

	public String getInsert_date() {
		return insert_date;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public String getSeason_service() {
		return season_service;
	}
	public String getSeason_service_man() {
		return season_service_man;
	}
	public String getSkin_care() {
		return skin_care;
	}
	public String getSkin_care_man() {
		return skin_care_man;
	}
	public String getSkin_cleansing() {
		return skin_cleansing;
	}
	public String getSkin_feature() {
		return skin_feature;
	}
	public String getSkin_feature_man() {
		return skin_feature_man;
	}
	public String getSkin_shaving_man() {
		return skin_shaving_man;
	}
	public String getSkin_trouble() {
		return skin_trouble;
	}
	public String getSkin_trouble_man() {
		return skin_trouble_man;
	}
	public String getSkin_type() {
		return skin_type;
	}
} 