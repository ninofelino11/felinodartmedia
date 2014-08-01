
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* Tslippickingm 정보
*
* @version 1.0, 2006/07/31
* @author  commerceware.co.kr
*/
public class Tslippickingm extends BaseEntity {
    public Tslippickingm(){ super();}

    private String picking_seq;
    private String wh_code;
    private String picking_date;
    private String create_date;
    private String create_seq;
    private String dely_gb;
    private String picking_slip_gb;
    private String picking_gb;
    private String mixpack_flag;
    private String prt_yn;
    private String prt_date;
    private String prt_id;
    private String reprt_yn;
    private String reprt_date;
    private String reprt_id;
    private String remark;
    private String remark1;
    private String insert_date;
    private String insert_id;

    /** Set Method **/
    public void setPicking_seq     ( String picking_seq    ){ this.picking_seq     = picking_seq ; }
    public void setWh_code         ( String wh_code        ){ this.wh_code         = wh_code ; }
    public void setPicking_date    ( String picking_date   ){ this.picking_date    = picking_date ; }
    public void setCreate_date     ( String create_date    ){ this.create_date     = create_date ; }
    public void setCreate_seq      ( String create_seq     ){ this.create_seq      = create_seq ; }
    public void setDely_gb         ( String dely_gb        ){ this.dely_gb         = dely_gb ; }
    public void setPicking_slip_gb ( String picking_slip_gb){ this.picking_slip_gb = picking_slip_gb ; }
    public void setPicking_gb      ( String picking_gb     ){ this.picking_gb      = picking_gb ; }
    public void setMixpack_flag    ( String mixpack_flag   ){ this.mixpack_flag    = mixpack_flag ; }
    public void setPrt_yn          ( String prt_yn         ){ this.prt_yn          = prt_yn ; }
    public void setPrt_date        ( String prt_date       ){ this.prt_date        = prt_date ; }
    public void setPrt_id          ( String prt_id         ){ this.prt_id          = prt_id ; }
    public void setReprt_yn        ( String reprt_yn       ){ this.reprt_yn        = reprt_yn ; }
    public void setReprt_date      ( String reprt_date     ){ this.reprt_date      = reprt_date ; }
    public void setReprt_id        ( String reprt_id       ){ this.reprt_id        = reprt_id ; }
    public void setRemark          ( String remark         ){ this.remark          = remark ; }
    public void setRemark1         ( String remark1        ){ this.remark1         = remark1 ; }
    public void setInsert_date     ( String insert_date    ){ this.insert_date     = insert_date ; }
    public void setInsert_id       ( String insert_id      ){ this.insert_id       = insert_id ; }

    public String getPicking_seq  (){ return  picking_seq      ; }
    public String getWh_code      (){ return  wh_code       ; }
    public String getPicking_date (){ return  picking_date  ; }
    public String getCreate_date  (){ return  create_date      ; }
    public String getCreate_seq   (){ return  create_seq    ; }
    public String getDely_gb      (){ return  dely_gb       ; }
    public String getPicking_slip_gb(){ return  picking_slip_gb; }
    public String getPicking_gb   (){ return  picking_gb    ; }
    public String getMixpack_flag (){ return  mixpack_flag  ; }
    public String getPrt_yn       (){ return  prt_yn        ; }
    public String getPrt_date     (){ return  prt_date         ; }
    public String getPrt_id       (){ return  prt_id        ; }
    public String getReprt_yn     (){ return  reprt_yn         ; }
    public String getReprt_date   (){ return  reprt_date    ; }
    public String getReprt_id     (){ return  reprt_id         ; }
    public String getRemark       (){ return  remark        ; }
    public String getRemark1      (){ return  remark1       ; }
    public String getInsert_date  (){ return  insert_date      ; }
    public String getInsert_id    (){ return  insert_id     ; }

}