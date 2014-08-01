
package com.cware.back.entity.common;

import com.cware.back.common.BaseEntity;

/**
* Base ComboEntity class (code, codeName 으로 구성됨)
*
* @version 1.0, 2006/06/22
* @author Commerceware Ins.
*/
public class BaseCombo extends BaseEntity {

    public BaseCombo(){ super();}

    private String code ;
    private String codeName ;
    private String codeSName ;
    private String codeGroup ;
    private String remark ;
    private String remark1 ;
    private String remark2 ;

    /** Set Method **/
    public void setCode     ( String code     ){ this.code      = code     ; }
    public void setCodeName ( String codeName ){ this.codeName  = codeName ; }
    public void setCodeSName ( String codeSName ){ this.codeSName  = codeSName ; }
    public void setCodeGroup ( String codeGroup ){ this.codeGroup  = codeGroup ; }
    public void setRemark ( String remark ){ this.remark  = remark ; }
    public void setRemark1 ( String remark1 ){ this.remark1  = remark1 ; }
    public void setRemark2 ( String remark2 ){ this.remark2  = remark2 ; }

    /** Get Method **/
    public String getCode     (){ return code     ; }
    public String getCodeName (){ return codeName ; }
    public String getCodeSName (){ return codeSName ; }
    public String getCodeGroup (){ return codeGroup ; }
    public String getRemark (){ return remark ; }
    public String getRemark1 (){ return remark1 ; }
    public String getRemark2 (){ return remark2 ; }
}
