package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 쇼호스트정보
*
* @version 1.0, 2006/08/12
* @author Commerceware Ins.
*/
public class Tshmanage extends BaseEntity {

public Tshmanage(){ super();}

    private String bd_date;
    private String bd_btime;
    private String prog_code;
    private String sno;
    private String sno_org;
    private String modifydate;
    private String userid;
    private String sno_rate;

    /** Set Method **/
    public void setBd_date( String bd_date ){ this.bd_date = bd_date; }
    public void setBd_btime( String bd_btime ){ this.bd_btime = bd_btime; }
    public void setProg_code( String prog_code ){ this.prog_code = prog_code; }
    public void setSno( String sno ){ this.sno = sno; }
    public void setSno_org( String sno_org ){ this.sno_org = sno_org; }
    public void setModifydate( String modifydate ){ this.modifydate = modifydate; }
    public void setUserid( String userid ){ this.userid = userid; }
    public void setSno_rate( String sno_rate ){ this.sno_rate = sno_rate; }

    /** Get Method **/
    public String getBd_date(){ return bd_date; }
    public String getBd_btime(){ return bd_btime; }
    public String getProg_code(){ return prog_code; }
    public String getSno(){ return sno; }
    public String getSno_org(){ return sno_org; }
    public String getModifydate(){ return modifydate; }
    public String getUserid(){ return userid; }
    public String getSno_rate(){ return sno_rate; }

}