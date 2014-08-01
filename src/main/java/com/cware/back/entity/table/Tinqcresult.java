
package com.cware.back.entity.table;

import com.cware.back.common.BaseEntity;

/**
* 반입검사결과
*
* @version 1.0, 2006/07/20
* @author Commerceware Ins.
*/
public class Tinqcresult extends BaseEntity {

    public Tinqcresult(){ super();}

    private String inqc_no;
    private String inqc_seq;
    private String qc_gb_code;
    private String qc_dt_code;
    private String qc_check_code;
    private String qc_check_dt_code;
    private String inqc_bad_code;
    private String inqc_bad_note;
    private String inqc_note;

    /** Set Method **/
    public void setInqc_no         ( String inqc_no )         { this.inqc_no          = inqc_no; }
    public void setInqc_seq        ( String inqc_seq )        { this.inqc_seq         = inqc_seq; }
    public void setQc_gb_code      ( String qc_gb_code )      { this.qc_gb_code       = qc_gb_code; }
    public void setQc_dt_code      ( String qc_dt_code )      { this.qc_dt_code       = qc_dt_code; }
    public void setQc_check_code   ( String qc_check_code )   { this.qc_check_code    = qc_check_code; }
    public void setQc_check_dt_code( String qc_check_dt_code ){ this.qc_check_dt_code = qc_check_dt_code; }
    public void setInqc_bad_code   ( String inqc_bad_code )   { this.inqc_bad_code    = inqc_bad_code; }
    public void setInqc_bad_note   ( String inqc_bad_note )   { this.inqc_bad_note    = inqc_bad_note; }
    public void setInqc_note       ( String inqc_note )       { this.inqc_note        = inqc_note; }

    /** Get Method **/
    public String getInqc_no         (){ return inqc_no; }
    public String getInqc_seq        (){ return inqc_seq; }
    public String getQc_gb_code      (){ return qc_gb_code; }
    public String getQc_dt_code      (){ return qc_dt_code; }
    public String getQc_check_code   (){ return qc_check_code; }
    public String getQc_check_dt_code(){ return qc_check_dt_code; }
    public String getInqc_bad_code   (){ return inqc_bad_code; }
    public String getInqc_bad_note   (){ return inqc_bad_note; }
    public String getInqc_note       (){ return inqc_note; }


} 