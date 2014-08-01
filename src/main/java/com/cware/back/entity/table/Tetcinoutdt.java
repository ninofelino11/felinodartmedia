                                                                                           
package com.cware.back.entity.table;                                                            
                                                                                           
import com.cware.back.common.BaseEntity;
                                                                                           
/**                                                                                        
* 기타입출고 Detail정보                                                                    
*                                                                                          
* @version 1.0, 2006/08/17                                                                 
* @author Commerceware Ins.                                                                
*/                                                                                         
public class Tetcinoutdt extends BaseEntity {                                              
                                                                                           
public Tetcinoutdt(){ super();}                                                            
                                                                                           
    private String etcio_no;                                                               
    private String goods_code;                                                             
    private String goodsdt_code;                                                           
    private long   etcio_aqty;                                                             
    private long   etcio_bqty;                                                             
    private String a_rack_code;                                                             
    private String b_rack_code;                                                           
                                                                                           
    /** Set Method **/                                                                     
    public void setEtcio_no( String etcio_no ){ this.etcio_no = etcio_no; }                
    public void setGoods_code( String goods_code ){ this.goods_code = goods_code; }        
    public void setGoodsdt_code( String goodsdt_code ){ this.goodsdt_code = goodsdt_code; }
    public void setEtcio_aqty( long etcio_aqty ){ this.etcio_aqty = etcio_aqty; }        
    public void setEtcio_bqty( long etcio_bqty ){ this.etcio_bqty = etcio_bqty; }               
    public void setA_rack_code( String a_rack_code ){ this.a_rack_code = a_rack_code; }        
    public void setB_rack_code( String b_rack_code ){ this.b_rack_code = b_rack_code; } 
                                                                                           
    /** Get Method **/                                                                     
    public String getEtcio_no(){ return etcio_no; }                                        
    public String getGoods_code(){ return goods_code; }                                    
    public String getGoodsdt_code(){ return goodsdt_code; }                                
    public long getEtcio_aqty(){ return etcio_aqty; }                                    
    public long getEtcio_bqty(){ return etcio_bqty; }                                      
    public String getA_rack_code(){ return a_rack_code; }                                    
    public String getB_rack_code(){ return b_rack_code; }                                    
                                                                                           
}                                                                                          