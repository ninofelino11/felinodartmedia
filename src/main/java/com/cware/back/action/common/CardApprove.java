
package com.cware.back.action.common;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cware.back.common.Construct;
import com.cware.back.common.DBUtils;
import com.cware.back.common.DateUtils;
import com.cware.back.common.Message;
import com.cware.back.common.StoreException;
import com.cware.back.entity.table.Tcardlog;
import com.cware.back.service.common.CommonDBSvc;

/**
 * Card Approve class
 *
 * @version 1.0, 2006/07/19
 * @author commerceware.co.kr
 */
public class CardApprove {

    private static Log log      = LogFactory.getLog(Construct.LOG_BASE);

    public CardApprove()   { }

    /**
    * <PRE>
    * Desc : 카드 승인(Card Approve)
    * reference : Netshopping8 gf_button_ok()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message cardApprove(Message msg) throws StoreException {
        
        Connection con  = null;
        int executedRtn = 0;
        long   card_log_no = 0;
        String sysDateTime = "";
        String approveResult = "0000";
        Tcardlog tcardlog = null;
        
        try{            
            
            /**
            카드 승인시 인수로 넘어오는 Data
            msg.getString("ca_i_target");   // 주문시 : OrderInput
            msg.getString("ca_i_order_no");
            msg.getString("ca_i_cust_no");             
            msg.getString("ca_i_card_no");
            msg.getString("ca_i_card_bank_code");            
            msg.getString("ca_i_cvv");
            msg.getString("ca_i_valid_date");
            msg.getString("ca_i_quest_amt");
            msg.getString("ca_i_pay_month");
            msg.getString("ca_i_insert_id");
            **/

            con = DBUtils.getConnection(Construct.DB_POOL_NAME);           
            
            con.rollback();
            
            CommonDBSvc svc = new CommonDBSvc();            

            //= 카드 승인 데이터 valiadtion check
            
            
            sysDateTime = DBUtils.getSysDateTime(con,DateUtils.CWARE_DB_DATETIME_FORMAT);
            
            //= 밴사를 통한 카드 승인 처리
            String van_num = "";            
            
                        
            tcardlog = new Tcardlog();
            
            if(Math.round(Math.random()*10)%10 < 2){
                approveResult = "9998";        
            }            

            if(approveResult.equals("0000")){

                msg.put("ca_o_ok_no",Long.toString(Math.round(Math.random()*100000000)));
                msg.put("ca_o_ok_med","100");   
                msg.put("ca_o_ok_date",sysDateTime);                               
                msg.put("ca_o_van_comp","01");
                msg.put("ca_o_ok_error_code","0000");    
                msg.put("ca_o_do_flag","20");  
                msg.put("ca_o_end_yn","1");  
                msg.put("ca_o_receipt_date",sysDateTime); 
                msg.put("ca_o_receipt_amt",msg.getString("ca_i_quest_amt"));
                msg.put("ca_o_card_bank_code",msg.getString("ca_i_card_bank_code")); 
                if(msg.getString("ca_o_card_bank_code").equals("")) msg.put("ca_o_card_bank_code","02"); 
                
                tcardlog.setTrans_type    ( "10" );
            }else{
                msg.put("ca_o_ok_no","");
                msg.put("ca_o_ok_med","000");   
                msg.put("ca_o_ok_date","");                               
                msg.put("ca_o_van_comp","01");
                msg.put("ca_o_ok_error_code",approveResult);
                msg.put("ca_o_ok_error_msg","Approve Error : ...");  
                msg.put("ca_o_do_flag","10");  
                msg.put("ca_o_end_yn","0");  
                msg.put("ca_o_receipt_date",""); 
                msg.put("ca_o_receipt_amt","0");
                msg.put("ca_o_card_bank_code",msg.getString("ca_i_card_bank_code")); 
                if(msg.getString("ca_o_card_bank_code").equals("")) msg.put("ca_o_card_bank_code","02");  
                
                tcardlog.setTrans_type    ( "11" );   
            }
            
            msg.put("ca_o_remark1_v","");
            msg.put("ca_o_remark2_v",van_num);
                                        
           
            //= 카드로그 처리
            card_log_no = svc.retrieveCardLogNo(con);
            if(card_log_no > 0){
                tcardlog.setCard_log_no   ( card_log_no                             );
                tcardlog.setCard_no       ( msg.getString("ca_i_card_no")           );
                tcardlog.setCvv           ( msg.getString("ca_i_cvv")               );
                tcardlog.setVan_code      ( msg.getString("ca_o_van_comp")          );
                tcardlog.setCard_code     ( msg.getString("ca_o_card_bank_code") );
                tcardlog.setValid_date    ( msg.getString("ca_i_valid_date")        );
                tcardlog.setPay_month     ( msg.getLong  ("ca_i_pay_month")         );
                tcardlog.setQuest_amt     ( msg.getDouble("ca_i_quest_amt")         );
                tcardlog.setPos_id        ( msg.getString("ven ..")                 );
                tcardlog.setCust_no       ( msg.getString("ca_i_cust_no")           );
                tcardlog.setOrder_no      ( msg.getString("ca_i_order_no")          );
                tcardlog.setReceipt_no    ( ""                                      );
                tcardlog.setApprove_code  ( msg.getString("ca_o_ok_no")             );
                tcardlog.setTrans_no      ( van_num                                 );
                tcardlog.setAuth_rrpid    ( ""                                      );
                tcardlog.setTerm_seq      ( ""                                      );
                tcardlog.setInsert_id     ( msg.getString("ca_i_insert_id")         );
                tcardlog.setEvent_name    ( msg.getString("ca_i_target")            );
                tcardlog.setResponse_code ( msg.getString("ca_o_ok_error_code")     );
//                tcardlog.setResponse_code ( msg.getString("ca_o_card_bank_code")     );
                tcardlog.setResponse_msg  ( msg.getString("ca_o_ok_error_msg")      );                
                executedRtn = svc.insertTcardlog(con, tcardlog);    
            }

            if( executedRtn > 0 ) {
                msg.put("ca_o_card_log_no",card_log_no);    
            } else {
            	// 만약 오류가 발생한다면 ... cardCancel(msg)을 실행해야 한다.
            	throw new StoreException("fail insert tcardlog");
            }
            
            con.commit();
            
        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(se);
            msg.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(e);
            msg.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return msg;
    }

    /**
    * <PRE>
    * Desc : 카드 취소
    * reference : Netshopping8 gf_card_cancel()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message cardCancel(Message msg) throws StoreException {
        
        Connection con           = null;
        int        executedRtn   = 0;
        long       card_log_no   = 0;
        String     approveResult = "0000";
        Tcardlog   tcardlog      = null;
        
        try{            
            
            /**
            카드 취소 승인시 인수로 넘어오는 Data
            msg.getString("ca_i_target");   // C/S : OrderClaim
            msg.getString("ca_i_order_no");
            msg.getString("ca_i_cust_no");             
            msg.getString("ca_i_card_no");
            msg.getString("ca_i_card_bank_code");            
            msg.getString("ca_i_cvv");
            msg.getString("ca_i_valid_date");
            msg.getString("ca_i_quest_amt");
            msg.getString("ca_i_pay_month");
            msg.getString("ca_i_insert_id");
            msg.getString("ca_i_van_comp");
            msg.getString("ca_i_ok_no");       // A_CODE
            msg.getString("ca_i_ok_date");     // S_DATE
            msg.getString("ca_i_remark1_v");   // POS_ID
            msg.getString("ca_i_remark2_v");   // arg_hubrrn                                                                    
            
            **/
            
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);           
            
            con.rollback();
            
            CommonDBSvc svc = new CommonDBSvc();                        
                        
            String van_comp = "99";  // Test.. 실제 카드취소시에 막아야함.          
                                    
            tcardlog = new Tcardlog();
            
            if(Math.round(Math.random()*10)%10 < 2){
                approveResult = "9998";        
            }            

            if(approveResult.equals("0000")){

                msg.put("ca_o_ok_med","100");                               
                msg.put("ca_o_ok_error_code","0000");      
                msg.put("ca_o_ok_error_msg","");                    
                tcardlog.setTrans_type    ( "20" );
            }else{
                msg.put("ca_o_ok_med","000");                               
                msg.put("ca_o_ok_error_code","0000");    
                msg.put("ca_o_ok_error_msg","Approve Error : No Message");                    
                tcardlog.setTrans_type    ( "21" );
            }                                                    
           
            //= 카드로그 처리
            card_log_no = svc.retrieveCardLogNo(con);
            if(card_log_no > 0){
                tcardlog.setCard_log_no   ( card_log_no                             );
                tcardlog.setCard_no       ( msg.getString("ca_i_card_no")           );
                tcardlog.setCvv           ( msg.getString("ca_i_cvv")               );
                tcardlog.setVan_code      ( msg.getString("ca_o_van_comp")          );
                tcardlog.setCard_code     ( msg.getString("ca_o_card_bank_code") );
                tcardlog.setValid_date    ( msg.getString("ca_i_valid_date")        );
                tcardlog.setPay_month     ( msg.getLong  ("ca_i_pay_month")         );
                tcardlog.setQuest_amt     ( msg.getDouble("ca_i_quest_amt")         );
                tcardlog.setPos_id        ( msg.getString("ca_i_remark1_v")         );
                tcardlog.setCust_no       ( msg.getString("ca_i_cust_no")           );
                tcardlog.setOrder_no      ( msg.getString("ca_i_order_no")          );
                tcardlog.setReceipt_no    ( ""                                      );
                tcardlog.setApprove_code  ( ""                                      );
                tcardlog.setTrans_no      ( msg.getString("ca_i_remark2_v")         );
                tcardlog.setAuth_rrpid    ( ""                                      );
                tcardlog.setTerm_seq      ( ""                                      );
                tcardlog.setInsert_id     ( msg.getString("ca_i_insert_id")         );
                tcardlog.setEvent_name    ( msg.getString("ca_i_target")            );
                tcardlog.setResponse_code ( msg.getString("ca_o_ok_error_code")     );
                tcardlog.setResponse_msg  ( msg.getString("ca_o_ok_error_msg")      );                
                executedRtn = svc.insertTcardlog(con, tcardlog);    
            }

            if( executedRtn > 0 ) {
                msg.put("ca_o_card_log_no",card_log_no);    
            } else {
            	// 만약 오류가 발생한다면 ... cardCancel(msg)을 실행해야 한다.
            	throw new StoreException("fail insert tcardlog");
            }
            
            con.commit();
            
        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(se);
            msg.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(e);
            msg.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return msg;
    }

    /**
    * <PRE>
    * Desc : 카드 취소
    * reference : Netshopping8 gf_card_refund()
    * </PRE>
    * @param   Message
    * @return  Message
    */
    public Message cardRefund(Message msg) throws StoreException {
        
        Connection con           = null;
        int        executedRtn   = 0;
        long       card_log_no   = 0;
        String     approveResult = "0000";
        Tcardlog   tcardlog      = null;
        
        try{            
            
            /**
            카드 승인시 인수로 넘어오는 Data
            msg.getString("ca_i_target");   // C/S : OrderClaim
            msg.getString("ca_i_order_no");
            msg.getString("ca_i_cust_no");             
            msg.getString("ca_i_card_no");
            msg.getString("ca_i_card_bank_code");            
            msg.getString("ca_i_cvv");
            msg.getString("ca_i_valid_date");
            msg.getString("ca_i_quest_amt");
            msg.getString("ca_i_pay_month");
            msg.getString("ca_i_insert_id");

            msg.getString("ca_i_van_comp");
            msg.getString("ca_i_ok_no");
            msg.getString("ca_i_ok_date");     // ok_date
            msg.getString("ca_i_remark1_v");   // POS_ID
            msg.getString("ca_i_remark2_v");   // TID                                                              
            
            **/
            
            con = DBUtils.getConnection(Construct.DB_POOL_NAME);           
            
            con.rollback();
            
            CommonDBSvc svc = new CommonDBSvc();                        
                        
            String van_comp = "99";  // Test.. 실제 카드취소시에 막아야함.          
                                    
            tcardlog = new Tcardlog();
            
            if(Math.round(Math.random()*10)%10 < 2){
                approveResult = "9998";        
            }            

            if(approveResult.equals("0000")){

                msg.put("ca_o_ok_med","000");                               
                msg.put("ca_o_ok_error_code","0000");    
                msg.put("ca_o_ok_error_msg","");                    
                tcardlog.setTrans_type    ( "30" );
            }else{
                msg.put("ca_o_ok_med","100");                               
                msg.put("ca_o_ok_error_code","0000");    
                msg.put("ca_o_ok_error_msg","Approve Error : No Message");                    
                tcardlog.setTrans_type    ( "31" );
            }                                                    
           
            //= 카드로그 처리
            card_log_no = svc.retrieveCardLogNo(con);
            if(card_log_no > 0){
                tcardlog.setCard_log_no   ( card_log_no                             );
                tcardlog.setCard_no       ( msg.getString("ca_i_card_no")           );
                tcardlog.setCvv           ( msg.getString("ca_i_cvv")               );
                tcardlog.setVan_code      ( msg.getString("ca_o_van_comp")          );
                tcardlog.setCard_code     ( msg.getString("ca_o_card_bank_code") );
                tcardlog.setValid_date    ( msg.getString("ca_i_valid_date")        );
                tcardlog.setPay_month     ( msg.getLong  ("ca_i_pay_month")         );
                tcardlog.setQuest_amt     ( msg.getDouble("ca_i_quest_amt")         );
                tcardlog.setPos_id        ( msg.getString("ca_i_remark1_v")         );
                tcardlog.setCust_no       ( msg.getString("ca_i_cust_no")           );
                tcardlog.setOrder_no      ( msg.getString("ca_i_order_no")          );
                tcardlog.setReceipt_no    ( ""                                      );
                tcardlog.setApprove_code  ( msg.getString("ca_i_ok_no")             );
                tcardlog.setTrans_no      ( msg.getString("ca_i_remark2_v")         );
                tcardlog.setAuth_rrpid    ( ""                                      );
                tcardlog.setTerm_seq      ( ""                                      );
                tcardlog.setInsert_id     ( msg.getString("ca_i_insert_id")         );
                tcardlog.setEvent_name    ( msg.getString("ca_i_target")            );
                tcardlog.setResponse_code ( msg.getString("ca_o_ok_error_code")     );
                tcardlog.setResponse_msg  ( msg.getString("ca_o_ok_error_msg")      );  
                              
                executedRtn = svc.insertTcardlog(con, tcardlog);    
            }

            if( executedRtn > 0 ) {
                msg.put("ca_o_card_log_no",card_log_no);    
            } 
            
            con.commit();
            
        }catch(StoreException se){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(se);
            msg.put("ERROR_MESSAGE",se.getMessage());
        }catch(Exception e){
            if ( con != null ) try{con.rollback();}catch(Exception sec){}
            log.error(e);
            msg.put("ERROR_MESSAGE",e.getMessage());
        }finally {
            DBUtils.freeConnection(con);
        }
        return msg;
    }

}
 