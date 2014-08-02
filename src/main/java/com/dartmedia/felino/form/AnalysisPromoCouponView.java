package com.dartmedia.felino.form;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisPromoCoupon")
public class AnalysisPromoCouponView extends MVerticalLayout implements View {
//AnalysisPromoCouponSvc data=new AnalysisPromoCouponSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* popup.xml : system.common.popup.selectCustCouponList by PopupController ");
sb.append("Application Type, Benefit, Promo Code, Promo Name, Suspension Reason, Order No, Paid Date, Use, Use Order No, Cancel, Paid Cancel Date");
sb.append("*/");
sb.append("                 B.PROMO_NO");
sb.append("               , A.PROMO_NAME");
sb.append("               , A.APP_TYPE");
sb.append("               , A.DO_TYPE");
sb.append("               , A.USE_CODE");
sb.append("               , A.REMARK");
sb.append("               , A.PROMO_BDATE");
sb.append("               , A.PROMO_EDATE");
sb.append("               , B.CUST_NO");
sb.append("               , B.GET_ORDER_NO");
sb.append("               , B.USE_YN");
sb.append("               , B.USE_ORDER_NO");
sb.append("               , TO_CHAR(B.INSERT_DATE, 'YYYY/MM/DD') ISSUE_DATE");
sb.append("               , B.CANCEL_YN   ISSUE_CANCEL_YN");
sb.append("               , DECODE(B.CANCEL_YN, '1',TO_CHAR(B.MODIFY_DATE, 'YYYY/MM/DD'), '' ) ISSUE_CANCEL_DATE");
sb.append("               , TO_CHAR(B.USE_START_DATE, 'YYYY/MM/DD HH24:MI:SS') USE_START_DATE");
sb.append("               , TO_CHAR(B.USE_END_DATE, 'YYYY/MM/DD HH24:MI:SS') USE_END_DATE");
sb.append("          FROM TPROMOM      A,");
sb.append("               TCOUPONISSUE B");
sb.append("         WHERE B.CUST_NO like '201302025649' || '%'");
sb.append("           AND B.PROMO_NO = A.PROMO_NO ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("Promotion Code/Name"));




//-------------------- Header  ------------------------------
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents(
                new Header("Promotion Coupon Report (AnalysisPromoCoupon)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
//        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
   //         @Override
    //        public void layoutClick(LayoutEvents.LayoutClickEvent event) {
     //       }
    //    });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
