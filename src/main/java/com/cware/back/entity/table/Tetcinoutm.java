                                                                                                   
package com.cware.back.entity.table;                                                                    
                                                                                                   
import com.cware.back.common.BaseEntity;
                                                                                                   
/**                                                                                                
* 기타입출고마스터정보                                                                             
*                                                                                                  
* @version 1.0, 2006/08/17                                                                         
* @author Commerceware Ins.                                                                        
*/                                                                                                 
public class Tetcinoutm extends BaseEntity {                                                       
                                                                                                   
public Tetcinoutm(){ super();}                                                                     
                                                                                                   
    private String etcio_no;                                                                       
    private String wh_code;                                                                        
    private String etcio_job_code;                                                                 
    private String etcio_date;                                                                     
    private String etcio_code;                                                                     
    private String etcio_note;                                                                     
    private String etcio_quest_id;                                                                 
    private String etcio_proc_id;                                                                  
    private String etcio_man_id;                                                                   
                                                                                                   
    /** Set Method **/                                                                             
    public void setEtcio_no( String etcio_no ){ this.etcio_no = etcio_no; }                        
    public void setWh_code( String wh_code ){ this.wh_code = wh_code; }                            
    public void setEtcio_job_code( String etcio_job_code ){ this.etcio_job_code = etcio_job_code; }
    public void setEtcio_date( String etcio_date ){ this.etcio_date = etcio_date; }                
    public void setEtcio_code( String etcio_code ){ this.etcio_code = etcio_code; }                
    public void setEtcio_note( String etcio_note ){ this.etcio_note = etcio_note; }                
    public void setEtcio_quest_id( String etcio_quest_id ){ this.etcio_quest_id = etcio_quest_id; }
    public void setEtcio_proc_id( String etcio_proc_id ){ this.etcio_proc_id = etcio_proc_id; }    
    public void setEtcio_man_id( String etcio_man_id ){ this.etcio_man_id = etcio_man_id; }        
                                                                                                   
    /** Get Method **/                                                                             
    public String getEtcio_no(){ return etcio_no; }                                                
    public String getWh_code(){ return wh_code; }                                                  
    public String getEtcio_job_code(){ return etcio_job_code; }                                    
    public String getEtcio_date(){ return etcio_date; }                                            
    public String getEtcio_code(){ return etcio_code; }                                            
    public String getEtcio_note(){ return etcio_note; }                                            
    public String getEtcio_quest_id(){ return etcio_quest_id; }                                    
    public String getEtcio_proc_id(){ return etcio_proc_id; }                                      
    public String getEtcio_man_id(){ return etcio_man_id; }                                        
                                                                                                   
}                                                                                                  