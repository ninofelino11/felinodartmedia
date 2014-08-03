/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.example.util;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import org.vaadin.maddon.button.MButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;


@SessionScoped
public class ViewMenu implements Serializable {
//final Label selection = new Label("-");    
    @Inject
    BeanManager beanManager;
    
    public Set<Bean<?>> getAvailableViews() {
        Set<Bean<?>> all = beanManager.getBeans(View.class,
                new AnnotationLiteral<Any>() {
                });
        // TODO check if accessible for current user
        return all;
    }
    
    public Component getBasicMenu() {
                    MHorizontalLayout hor=new MHorizontalLayout();
    MenuBar menubar = new MenuBar();
    
    MenuBar.MenuItem item= menubar.addItem("Item",null,null);
    MenuBar.MenuItem promotion= menubar.addItem("Promotion",null,null);
    MenuBar.MenuItem broadcast= menubar.addItem("Broadcast",null,null);
    MenuBar.MenuItem payment= menubar.addItem("Payment",null,null);
    MenuBar.MenuItem customer= menubar.addItem("Customer",null,null);
    MenuBar.MenuItem delivery= menubar.addItem("Delivery",null,null);
    MenuBar.MenuItem order= menubar.addItem("Order",null,null);

    MenuBar.MenuItem consignlogistics= menubar.addItem("Consign Logistics",null,null);
    MenuBar.MenuItem analysisreport= menubar.addItem("Analysis Report",null,null);
    MenuBar.MenuItem system= menubar.addItem("System",null,null);
    
    MenuBar.MenuItem codeitem=item.addItem("Code",null);
    MenuBar.MenuItem iteminquiryitem=item.addItem("Item Inquiry",null);
    MenuBar.MenuItem itemitem=item.addItem("Item",null);
    MenuBar.MenuItem vendoritem=item.addItem("Vendor",null);
    MenuBar.MenuItem couponpromotion=promotion.addItem("Coupon",null);
    MenuBar.MenuItem promotionpromotion=promotion.addItem("Promotion",null);
    MenuBar.MenuItem reportpromotion=promotion.addItem("Report",null);
    MenuBar.MenuItem inquirybroadcast=broadcast.addItem("Inquiry",null);
    MenuBar.MenuItem programbroadcast=broadcast.addItem("Program",null);
    MenuBar.MenuItem reportbroadcast=broadcast.addItem("Report",null);
    MenuBar.MenuItem schedulebroadcast=broadcast.addItem("Schedule",null);
    MenuBar.MenuItem agentreportcustomerorder=order.addItem("Agent Report",null);
    MenuBar.MenuItem csreportcustomerorder=order.addItem("CS Report",null);
    MenuBar.MenuItem ordercustomerorder=order.addItem("Order",null);
    MenuBar.MenuItem orderprogresscustomerorder=order.addItem("Order Progress",null);
    MenuBar.MenuItem orderreportcustomerorder=order.addItem("Order Report",null);
    MenuBar.MenuItem refundcustomerorder=order.addItem("Refund",null);
    MenuBar.MenuItem afterpaypayment=payment.addItem("After Pay",null);
    MenuBar.MenuItem codepayment=payment.addItem("Code",null);
    MenuBar.MenuItem creditcardpayment=payment.addItem("Credit Card",null);
    MenuBar.MenuItem depositpayment=payment.addItem("Deposit",null);
    MenuBar.MenuItem pointpayment=payment.addItem("Point",null);
    MenuBar.MenuItem transferpayment=payment.addItem("Transfer",null);
    MenuBar.MenuItem counselcustomer=customer.addItem("Counsel",null);
    MenuBar.MenuItem customercustomer=customer.addItem("Customer",null);
    MenuBar.MenuItem reportcustomer=customer.addItem("Report",null);
    MenuBar.MenuItem edidelivery=delivery.addItem("EDI",null);
    MenuBar.MenuItem entpdeliverydelivery=delivery.addItem("Entp Delivery",null);   
    MenuBar.MenuItem entpstockdelivery=delivery.addItem("Entp Stock",null);
    MenuBar.MenuItem returndelivery=delivery.addItem("Return",null);
    MenuBar.MenuItem returnreportdelivery=delivery.addItem("Return Report",null);
    MenuBar.MenuItem shippingorderdelivery=delivery.addItem("Shipping Order",null);
    MenuBar.MenuItem shippingreportdelivery=delivery.addItem("Shipping Report",null);
    MenuBar.MenuItem shippingsummarydelivery=delivery.addItem("Shipping Summary",null);
    MenuBar.MenuItem basecodeconsignlogistics=consignlogistics.addItem("Base Code",null);
    MenuBar.MenuItem purchaseorderconsignlogistics=consignlogistics.addItem("Purchase Order",null);
    MenuBar.MenuItem receiptitemconsignlogistics=consignlogistics.addItem("Receipt Item",null);
    MenuBar.MenuItem returntovendorconsignlogistics=consignlogistics.addItem("Return to Vendor",null);
    MenuBar.MenuItem stockcheckconsignlogistics=consignlogistics.addItem("Stock Check",null);
    MenuBar.MenuItem stockcontrolconsignlogistics=consignlogistics.addItem("Stock Control",null);
    MenuBar.MenuItem stockinquiryconsignlogistics=consignlogistics.addItem("Stock Inquiry",null);
    MenuBar.MenuItem warehousetransferconsignlogistics=consignlogistics.addItem("Warehouse Transfer",null);
    MenuBar.MenuItem areareportanalysisreport=analysisreport.addItem("Area Report",null);
    MenuBar.MenuItem customerreportanalysisreport=analysisreport.addItem("Customer Report",null);
    MenuBar.MenuItem dailyreportanalysisreport=analysisreport.addItem("Daily Report",null);
    MenuBar.MenuItem itemreportanalysisreport=analysisreport.addItem("Item Report",null);
    MenuBar.MenuItem progressreportanalysisreport=analysisreport.addItem("Progress Report",null);
    MenuBar.MenuItem authoritysystem=system.addItem("Authority",null);
    MenuBar.MenuItem batchjobsystem=system.addItem("Batch Job",null);
    MenuBar.MenuItem configurationsystem=system.addItem("Configuration",null);
    MenuBar.MenuItem monitoringsystem=system.addItem("Monitoring",null);
    
  codeitem.addItem("Brand Management",null,mycommand).setDescription("Brand");
 codeitem.addItem("Item Description Code",null,mycommand).setDescription("GoodsDescribeBase");
 codeitem.addItem("Item Group Code",null,mycommand).setDescription("GoodsKinds");
 codeitem.addItem("MD Management",null,mycommand).setDescription("Md");
 codeitem.addItem("MD Transfer",null,mycommand).setDescription("GoodsMdTransfer");
 codeitem.addItem("Unit Attribute Code",null,mycommand).setDescription("GoodsInfo");
 itemitem.addItem("Item Approval",null,mycommand).setDescription("GoodsSign");
 itemitem.addItem("Item Description",null,mycommand).setDescription("GoodsDescribe");
 itemitem.addItem("Item Group Code",null,mycommand).setDescription("GoodsModify");
 itemitem.addItem("Item Management",null,mycommand).setDescription("Goods");
 itemitem.addItem("Multiple Item (new)",null,mycommand).setDescription("GoodsXsis");
 itemitem.addItem("Sales Suspend",null,mycommand).setDescription("SalesNoGoods");
 iteminquiryitem.addItem("Item List",null,mycommand).setDescription("GoodsList");
 vendoritem.addItem("Manufacturer",null,mycommand).setDescription("Makecomp");
 vendoritem.addItem("Vendor List",null,mycommand).setDescription("EntpList");
 vendoritem.addItem("Vendor Management",null,mycommand).setDescription("EntpInput");
 promotionpromotion.addItem("Gift Promotion",null,mycommand).setDescription("Promo");
 couponpromotion.addItem("Coupon Management",null,mycommand).setDescription("CouponIssue");
 couponpromotion.addItem("Coupon Report",null,mycommand).setDescription("CustCouponList");
 reportpromotion.addItem("Promotion Coupon Report",null,mycommand).setDescription("AnalysisPromoCoupon");
 reportpromotion.addItem("Promotion Discount Report",null,mycommand).setDescription("AnalysisPromoDiscount");
 reportpromotion.addItem("Promotion Gift Report",null,mycommand).setDescription("AnalysisPromoGift");
 reportpromotion.addItem("Promotion Order Report ",null,mycommand).setDescription("AnalysisPromo");
 schedulebroadcast.addItem("Approve Schedule",null,mycommand).setDescription("MultiBroadAgree");
 schedulebroadcast.addItem("Item Management(Live)",null,mycommand).setDescription("MultiBroadGoods");
 schedulebroadcast.addItem("Item Management(Tape)",null,mycommand).setDescription("MultiBroadTape");
 schedulebroadcast.addItem("Program Code",null,mycommand).setDescription("BroadProgram");
 schedulebroadcast.addItem("Program Inquiry",null,mycommand).setDescription("ProgramDtManage");
 schedulebroadcast.addItem("Program Schedule",null,mycommand).setDescription("MultiBroadSchedule");
 schedulebroadcast.addItem("Tape Management",null,mycommand).setDescription("BroadTapeManage");
 inquirybroadcast.addItem("Program Item Report",null,mycommand).setDescription("MultiGoodsBroadList");
 inquirybroadcast.addItem("Program Report",null,mycommand).setDescription("MultiBroadScheduleList");
 inquirybroadcast.addItem("Schedule for Staff",null,mycommand).setDescription("StaffMultiSchedule");
 inquirybroadcast.addItem("Weekly Report for Staff",null,mycommand).setDescription("StaffMultiScheduleList");
 inquirybroadcast.addItem("Weekly Report",null,mycommand).setDescription("MultiScheduleManage");
 programbroadcast.addItem("Insert Management",null,mycommand).setDescription("VideoManage");
 programbroadcast.addItem("Model Management",null,mycommand).setDescription("BroadModelManage");
 reportbroadcast.addItem("Item Sales Report",null,mycommand).setDescription("GoodsBroadSaleList");
 reportbroadcast.addItem("Program Order[Live]",null,mycommand).setDescription("MultiBroadRealTimeGraph");
 reportbroadcast.addItem("Program Result",null,mycommand).setDescription("MultiBroadAfter");
 reportbroadcast.addItem("Staff Sales Report",null,mycommand).setDescription("BroadStaffSaleList");
 ordercustomerorder.addItem("C/S Mangement",null,mycommand).setDescription("OrderClaim");
 ordercustomerorder.addItem("Delivery Fail History",null,mycommand).setDescription("DeliveryFail");
 ordercustomerorder.addItem("Make Order",null,mycommand).setDescription("OrderInput");
 ordercustomerorder.addItem("Order Monitoring",null,mycommand).setDescription("OrderProcXsis");
 ordercustomerorder.addItem("Order Progress",null,mycommand).setDescription("OrderProc");
 orderprogresscustomerorder.addItem("Customer Report for Cancel",null,mycommand).setDescription("OrderProcCustList2");
 orderprogresscustomerorder.addItem("Customer Report for Order",null,mycommand).setDescription("OrderProcCustList");
 orderprogresscustomerorder.addItem("Customer Report for Return",null,mycommand).setDescription("OrderProcCustList3");
 orderprogresscustomerorder.addItem("Order Detail",null,mycommand).setDescription("OrderReport");
 orderreportcustomerorder.addItem("Order Customer Report",null,mycommand).setDescription("CustOrderList");
 orderreportcustomerorder.addItem("Order Item Report",null,mycommand).setDescription("GoodsOrderList");
 orderreportcustomerorder.addItem("Order Payment Report",null,mycommand).setDescription("OrderSettlementReport");
 orderreportcustomerorder.addItem("Order Report",null,mycommand).setDescription("OrderDetailsList");
 orderreportcustomerorder.addItem("Recipient Inquiry",null,mycommand).setDescription("ReceiverSearch");
 refundcustomerorder.addItem("Customer Refund",null,mycommand).setDescription("ReturnList");
 refundcustomerorder.addItem("Refund Approve List",null,mycommand).setDescription("RefundExpectList");
 refundcustomerorder.addItem("Refund Approve",null,mycommand).setDescription("RefundExpect");
 refundcustomerorder.addItem("Refund List",null,mycommand).setDescription("RefundEndList");
 refundcustomerorder.addItem("Refund Payment",null,mycommand).setDescription("RefundEnd");
 refundcustomerorder.addItem("Refund Report",null,mycommand).setDescription("RefundDaysum");
 refundcustomerorder.addItem("Refund Request List",null,mycommand).setDescription("RefundList");
 csreportcustomerorder.addItem("C/S Collection Report",null,mycommand).setDescription("ClaimAcceptanceReport");
 csreportcustomerorder.addItem("C/S Item Report",null,mycommand).setDescription("GoodsClaimList");
 csreportcustomerorder.addItem("C/S Report",null,mycommand).setDescription("ClaimItemizeSearch");
 csreportcustomerorder.addItem("Cancel Report",null,mycommand).setDescription("CancelReport");
 csreportcustomerorder.addItem("Return Report",null,mycommand).setDescription("ReturnGoodsList");
 agentreportcustomerorder.addItem("Agent Order List",null,mycommand).setDescription("UserOrderList");
 agentreportcustomerorder.addItem("Agent Order Report",null,mycommand).setDescription("AnalysisUser");
 agentreportcustomerorder.addItem("Agent Work Record",null,mycommand).setDescription("MyWorkList");
 agentreportcustomerorder.addItem("Agent Work Report",null,mycommand).setDescription("AgentWorkReport");
 codepayment.addItem("Bank Code",null,mycommand).setDescription("BankCode");
 codepayment.addItem("Credit Card Code",null,mycommand).setDescription("CardCode");
 codepayment.addItem("Receipt Bank",null,mycommand).setDescription("ReceiptBank");
 transferpayment.addItem("CCOD Receipt Report",null,mycommand).setDescription("CCODReceiptXsis");
 transferpayment.addItem("Discrepancy Report",null,mycommand).setDescription("CashListDiscrepancyXsis");
 transferpayment.addItem("Money Transfer List",null,mycommand).setDescription("CashReceiptsList");
 transferpayment.addItem("Money Transfer Mismatch",null,mycommand).setDescription("CashMissMatch");
 transferpayment.addItem("Money Transfer Report",null,mycommand).setDescription("CashList");
 transferpayment.addItem("Money Transfer",null,mycommand).setDescription("CashReceipts");
 transferpayment.addItem("Unapproved Payment",null,mycommand).setDescription("UnapprovedPayment");
 transferpayment.addItem("Upload MDR (New)",null,mycommand).setDescription("UploadMDRXsis");
 depositpayment.addItem("Customer Deposit Use",null,mycommand).setDescription("DepositUseList");
 depositpayment.addItem("Customer Deposit",null,mycommand).setDescription("DepositGetList");
 depositpayment.addItem("Deposit List",null,mycommand).setDescription("DepositList");
 depositpayment.addItem("Deposit Management",null,mycommand).setDescription("DepositProc");
 depositpayment.addItem("Deposit Report",null,mycommand).setDescription("DepositDaily");
 pointpayment.addItem("Customer Point Report",null,mycommand).setDescription("SaveamtGrantList");
 pointpayment.addItem("Customer Point Use",null,mycommand).setDescription("SaveamtUseList");
 pointpayment.addItem("Customer Point",null,mycommand).setDescription("SaveamtList");
 pointpayment.addItem("Point Management",null,mycommand).setDescription("SaveamtProc");
 pointpayment.addItem("Point Report",null,mycommand).setDescription("SaveamtDaily");
 pointpayment.addItem("Remain Point",null,mycommand).setDescription("SaveamtNouseList");
 afterpaypayment.addItem("COD Deposit Report",null,mycommand).setDescription("DeferredDepositList");
 afterpaypayment.addItem("COD Report by Order",null,mycommand).setDescription("DeferredOrderDepositList");
 afterpaypayment.addItem("COD Report by Waybill",null,mycommand).setDescription("DeferredApproveList");
 afterpaypayment.addItem("COD Shipment Report",null,mycommand).setDescription("DeferredDeliveryList");
 customercustomer.addItem("Customer Information",null,mycommand).setDescription("CustUnique");
 customercustomer.addItem("Customer",null,mycommand).setDescription("CustDetail");
 reportcustomer.addItem("Customer Sales Report",null,mycommand).setDescription("AnalysisCustSelling");
 counselcustomer.addItem("Counsel Report by Agent[manager]",null,mycommand).setDescription("CounselUserListMng");
 counselcustomer.addItem("Counsel Report by Code",null,mycommand).setDescription("CounselDayList");
 counselcustomer.addItem("Counsel",null,mycommand).setDescription("CounselCust");
 shippingorderdelivery.addItem("Shipment [Manual]",null,mycommand).setDescription("OutDeliveryOk");
 shippingorderdelivery.addItem("Shipping Order List",null,mycommand).setDescription("SlipReqList");
 shippingorderdelivery.addItem("Shipping Order",null,mycommand).setDescription("SlipCreateBatch");
 entpstockdelivery.addItem("Entp Stock Control",null,mycommand).setDescription("ScmStockCtrl");
 entpstockdelivery.addItem("Entp Stock List",null,mycommand).setDescription("EntpStockList");
 entpdeliverydelivery.addItem("Entp Delivery Confirm",null,mycommand).setDescription("ScmOutDeliveryOk");
 entpdeliverydelivery.addItem("Entp Delivery/Return Confirm",null,mycommand).setDescription("ScmOutReturnList");
 entpdeliverydelivery.addItem("Entp Return Confirm",null,mycommand).setDescription("ScmReturnOk");
 shippingreportdelivery.addItem("COD Delivery Report",null,mycommand).setDescription("CodSlipDetailsList");
 shippingreportdelivery.addItem("Delivery Report by Order No",null,mycommand).setDescription("OrderSlipDetailsList");
 shippingreportdelivery.addItem("Non Shipment Detail for Order",null,mycommand).setDescription("NonShipmentDetailforOrder");
 shippingreportdelivery.addItem("Non Shipment Report",null,mycommand).setDescription("NonShipmentReport");
 shippingreportdelivery.addItem("Shipment Item Summary",null,mycommand).setDescription("OutOkReport");
 shippingreportdelivery.addItem("Shipment Report by Item",null,mycommand).setDescription("OutReport");
 shippingreportdelivery.addItem("Shipment Report",null,mycommand).setDescription("SlipDetailsList");
 shippingreportdelivery.addItem("Waybill Information",null,mycommand).setDescription("SlipDetailsSearch");
 shippingreportdelivery.addItem("WhWorkLoadReport",null,mycommand).setDescription("WhWorkLoadReport");
 shippingsummarydelivery.addItem("Delivery Report",null,mycommand).setDescription("WhOutReport");
 shippingsummarydelivery.addItem("Process Term Report",null,mycommand).setDescription("ReturnOkRequireReport");
 shippingsummarydelivery.addItem("Waybill Report",null,mycommand).setDescription("SlipTotalCount");
 returndelivery.addItem("Retrun Confirm [Manual]",null,mycommand).setDescription("ReturnWhOkOptional");
 returndelivery.addItem("Return Order List",null,mycommand).setDescription("ReturnReqList");
 returndelivery.addItem("Return Order",null,mycommand).setDescription("ReturnOrdersBatch");
 returnreportdelivery.addItem("Retrun Confirm Detail Info",null,mycommand).setDescription("ReturnWhOkList");
 returnreportdelivery.addItem("Return Report by Order no",null,mycommand).setDescription("ReturnWhList");
 returnreportdelivery.addItem("Return Report by item",null,mycommand).setDescription("GoodsReturnReport");
 returnreportdelivery.addItem("Return Summary Report",null,mycommand).setDescription("ClaimProcList");
 edidelivery.addItem("EDI File Receive",null,mycommand).setDescription("DelivOkReceive");
 edidelivery.addItem("EDI File Send",null,mycommand).setDescription("SlipSend");
 basecodeconsignlogistics.addItem("Holiday Management",null,mycommand).setDescription("DelyDiary");
 basecodeconsignlogistics.addItem("Ship Cost Management by Area",null,mycommand).setDescription("AreaShipcost");
 basecodeconsignlogistics.addItem("Warehouse Info",null,mycommand).setDescription("WhCode");
 basecodeconsignlogistics.addItem("Zone Management",null,mycommand).setDescription("AreaZoneManage");
 purchaseorderconsignlogistics.addItem("PO Cancel",null,mycommand).setDescription("BaljuCancel");
 purchaseorderconsignlogistics.addItem("PO Create",null,mycommand).setDescription("EntpBalju");
 purchaseorderconsignlogistics.addItem("PO Process",null,mycommand).setDescription("BaljuSearch");
 receiptitemconsignlogistics.addItem("Expected Receipt Report",null,mycommand).setDescription("WarehousingPlanList");
 receiptitemconsignlogistics.addItem("Goods Receipt by PO [Manual]",null,mycommand).setDescription("WarehousingOk");
 receiptitemconsignlogistics.addItem("Goods Receipts[manual]",null,mycommand).setDescription("WarehousingInput");
 receiptitemconsignlogistics.addItem("Receipt Report",null,mycommand).setDescription("WarehousingSearch");
 receiptitemconsignlogistics.addItem("Receipt/Take Out Report",null,mycommand).setDescription("InoutReport");
 returntovendorconsignlogistics.addItem("Expected Take Out Report",null,mycommand).setDescription("EntpTakeoutPlanList");
 returntovendorconsignlogistics.addItem("Return Confirm [Manual]",null,mycommand).setDescription("EntpTakeoutOk");
 returntovendorconsignlogistics.addItem("Take Out Cancel",null,mycommand).setDescription("EntpTakeoutCancel");
 returntovendorconsignlogistics.addItem("Take Out Create",null,mycommand).setDescription("EntpTakeoutRequestOk");
 returntovendorconsignlogistics.addItem("Take Out Process",null,mycommand).setDescription("EntpTakeoutSearch");
 returntovendorconsignlogistics.addItem("Take Out Report",null,mycommand).setDescription("EntpTakeoutOkList");
 stockcontrolconsignlogistics.addItem("Safe Stock Manage",null,mycommand).setDescription("SafeStockManage");
 stockcontrolconsignlogistics.addItem("Stock Control Report",null,mycommand).setDescription("StockControlListWh");
 stockcontrolconsignlogistics.addItem("Stock Control",null,mycommand).setDescription("StockControlReg");
 stockinquiryconsignlogistics.addItem("Stock Item Report",null,mycommand).setDescription("WarehouseStockItemList");
 stockinquiryconsignlogistics.addItem("Stock Report",null,mycommand).setDescription("WarehouseStockList");
 stockcheckconsignlogistics.addItem("Check Stock Report",null,mycommand).setDescription("ExamList");
 warehousetransferconsignlogistics.addItem("Transfer In Report",null,mycommand).setDescription("WhInOkList");
 warehousetransferconsignlogistics.addItem("Transfer Out Report",null,mycommand).setDescription("WhTransferOkList");
 warehousetransferconsignlogistics.addItem("Wh Transfer In",null,mycommand).setDescription("WhInConfirm");
 warehousetransferconsignlogistics.addItem("Wh Transfer Order Cancel",null,mycommand).setDescription("WhTransferCancel");
 warehousetransferconsignlogistics.addItem("Wh Transfer Order(CDCtoRDC)",null,mycommand).setDescription("WhTransferOrder");
 warehousetransferconsignlogistics.addItem("Wh Transfer Order(RDCtoCDC)",null,mycommand).setDescription("WhTransferRequest");
 warehousetransferconsignlogistics.addItem("Wh Transfer Out",null,mycommand).setDescription("WhTransferOk");
 warehousetransferconsignlogistics.addItem("Wh Transfer Report",null,mycommand).setDescription("WhTransferSearch");
 dailyreportanalysisreport.addItem("Compare Sales Report[Calendar]",null,mycommand).setDescription("AnalysisCalendarMonthSelling");
 dailyreportanalysisreport.addItem("Daily Report",null,mycommand).setDescription("AnalysisSellingTotal");
 dailyreportanalysisreport.addItem("Net Sales Report",null,mycommand).setDescription("AnalysisNetSalesReport");
 dailyreportanalysisreport.addItem("Order Report by Time",null,mycommand).setDescription("AnalysisTimeDailyOrder");
 dailyreportanalysisreport.addItem("Payment Method Report",null,mycommand).setDescription("AnalysisMedSelling");
 dailyreportanalysisreport.addItem("Sales Report by Media[Calendar]",null,mycommand).setDescription("AnalysisCalendarSaleList");
 dailyreportanalysisreport.addItem("Sales Report by OrderNo (New)",null,mycommand).setDescription("AnalysisSellingGubunbyOrderXsis");
 dailyreportanalysisreport.addItem("Sales Report by Type",null,mycommand).setDescription("AnalysisSellingGubun");
 dailyreportanalysisreport.addItem("Sales Report[Calendar]",null,mycommand).setDescription("AnalysisCalendarSellingGubun");
 itemreportanalysisreport.addItem("Item Order Report",null,mycommand).setDescription("AnalysisGoodsSaleSelling");
 itemreportanalysisreport.addItem("Order Report by Item(TOP XX)",null,mycommand).setDescription("AnalysisGoodsTopxx");
 itemreportanalysisreport.addItem("Order Report by Time",null,mycommand).setDescription("AnalysisOrderTime");
 itemreportanalysisreport.addItem("Sales Report by Item Group",null,mycommand).setDescription("AnalysisSellingGoodsKind");
 progressreportanalysisreport.addItem("Order Report per Day",null,mycommand).setDescription("AnalysisDailySelling");
 progressreportanalysisreport.addItem("Order Report per Month",null,mycommand).setDescription("AnalysisMonthSelling");
 areareportanalysisreport.addItem("Sales Report by Area",null,mycommand).setDescription("AnalysisAreaSelling");
 customerreportanalysisreport.addItem("Basic CRM",null,mycommand).setDescription("AnalysisBasicCRM");
 customerreportanalysisreport.addItem("Customer Join",null,mycommand).setDescription("AnalysisCustJoin");
 customerreportanalysisreport.addItem("Customer Loyalty",null,mycommand).setDescription("AnalysisReBuyCust");
 customerreportanalysisreport.addItem("Grade Order Report ",null,mycommand).setDescription("AnalysisCustMonthSelling");
 customerreportanalysisreport.addItem("Order Summary[Grade]",null,mycommand).setDescription("AnalysisCustAgeSelling");
 customerreportanalysisreport.addItem("Transaction CRM",null,mycommand).setDescription("AnalysisTransactionCRM");
// .addItem("Accounting EDI File Send",null,mycommand).setDescription("AccountingEDIFileSend");
 configurationsystem.addItem("Customer Grade",null,mycommand).setDescription("Membgb");
 configurationsystem.addItem("Shipment Fee from Vendor & Warehouse",null,mycommand).setDescription("ShipmentFee");
 configurationsystem.addItem("System Code",null,mycommand).setDescription("CodeInput");
 configurationsystem.addItem("System Configuration",null,mycommand).setDescription("Config");
 configurationsystem.addItem("User Management",null,mycommand).setDescription("UserReg");
 configurationsystem.addItem("Zip Code",null,mycommand).setDescription("Post");
 authoritysystem.addItem("Program Authority per User Group",null,mycommand).setDescription("UserGroupProgramConsent");
 authoritysystem.addItem("Program Authority per User",null,mycommand).setDescription("UserProgramConsent");
 authoritysystem.addItem("Program Function for User",null,mycommand).setDescription("UserProgramCompetence");
 authoritysystem.addItem("Program Function",null,mycommand).setDescription("ProgramCompetence");
 authoritysystem.addItem("User Authority per Program",null,mycommand).setDescription("ProgramUserConsent");
 authoritysystem.addItem("User Group Authority per Program",null,mycommand).setDescription("ProgramUserGroupConsent");
 batchjobsystem.addItem("Batch Job Report",null,mycommand).setDescription("BatchCheck");
 batchjobsystem.addItem("Batch Job[Manual]",null,mycommand).setDescription("BatchExecute");
 monitoringsystem.addItem("Logistics FTP Monitoring",null,mycommand).setDescription("MonitoringPantos");
 monitoringsystem.addItem("Simple CRUD",null,mycommand).setDescription("Employee");


//fgetsql fel=new fgetsql();
            String judul;
         hor.setWidth("100%");   
         hor.addComponent(menubar);
    //        hor.addComponent(new MHorizontalLayout(getAsLinkButtons(getAvailableViews())));
          return hor;
          // return new MHorizontalLayout(getAsLinkButtons(getAvailableViews())).
            //        withMargin(false);
        
   
    }
    
    MenuBar.Command mycommand = (MenuItem selectedItem) -> {
     
          Notification.show(selectedItem.getDescription());
           UI.getCurrent().getNavigator().navigateTo(selectedItem.getDescription());
           System.out.print(selectedItem.getDescription());
             System.out.println("cLASS NOT FOUND");
        
  
    };
    
    
    private Component[] getAsLinkButtons(
            Set<Bean<?>> availableViews) {
        ArrayList<Button> buttons = new ArrayList();
        for (Bean<?> viewBean : availableViews) {
            Class<?> beanClass = viewBean.getBeanClass();
            if (beanClass.getAnnotation(CDIView.class) != null) {
                buttons.add(getButtonFor(beanClass));
            }
        }
        return buttons.toArray(new Button[0]);
    }
    
    private MButton getButtonFor(Class<?> beanClass) {
        final MButton button = new MButton(getNameFor(beanClass)).withStyleName(
                "link");
          return button;
    }
    
    protected String getNameFor(Class<?> viewType) {
        return viewType.getSimpleName();
    }

    private void getItemMenu(MenuBar.MenuItem ItemMenu) {
    //     fgetsql fel=new fgetsql();
        String judul;   
//        judul = fel.listmenu().getString("LMENU_NAME");
        ItemMenu.addItem("judul",null,null);
    }
}
