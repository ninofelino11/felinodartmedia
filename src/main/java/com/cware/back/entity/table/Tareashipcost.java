
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* MD연결
*
* @version 1.0, 2006/07/13
* @author Commerceware Ins.
*/
public class Tareashipcost extends BaseEntity {

    public Tareashipcost(){ super();}

    private String apply_date;
    private String area_gb;
    private String weight;
    private String ship_fee;
    private String slip_flag;
    private String cod_yn;

    /** Set Method **/
    public void setApply_date(String apply_date){ this.apply_date = apply_date; }
    public void setArea_gb   (String area_gb   ){ this.area_gb    = area_gb;    }
    public void setWeight    (String weight    ){ this.weight     = weight;     }
    public void setShip_fee  (String ship_fee  ){ this.ship_fee   = ship_fee;   }
    public void setSlip_flag (String slip_flag ){ this.slip_flag  = slip_flag;  }
    public void setCod_yn    (String cod_yn    ){ this.cod_yn     = cod_yn;     }

    /** Get Method **/
    public String getApply_date(){ return apply_date; }
    public String getArea_gb()   { return area_gb;    }
    public String getWeight()    { return weight;     }
    public String getShip_fee()  { return ship_fee;   }
    public String getSlip_flag() { return slip_flag;  }
    public String getCod_yn()    { return cod_yn;     }


}
