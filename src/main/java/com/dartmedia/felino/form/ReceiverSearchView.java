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
@CDIView("ReceiverSearch")
public class ReceiverSearchView extends MVerticalLayout implements View {
//ReceiverSearchSvc data=new ReceiverSearchSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* orderreport.xml : custcenter.orderreport.selectReceiverSearchList by ReceiverSearch */");
sb.append("         A.CUST_NO,");
sb.append("         A.CUST_NAME,");
sb.append("         B.RECEIVER_SEQ,");
sb.append("         B.RECEIVER,");
sb.append("         B.TEL,");
sb.append("         B.RECEIVER_POST,");
sb.append("         B.RECEIVER_POST_SEQ,");
sb.append("         B.RECEIVER_ADDR,");
sb.append("         FUN_ADD_POSTADDR(B.RECEIVER_POST, B.RECEIVER_POST_SEQ, B.RECEIVER_ADDR) AS ADDR");
sb.append("         ");
sb.append("          FROM TCUSTOMER A, TRECEIVER B, TSLIPM D");
sb.append("         WHERE A.CUST_NO = B.CUST_NO");
sb.append("           AND B.CUST_NO = D.CUST_NO");
sb.append("           AND D.SLIP_NO = 'slipNo'");
sb.append("         ");
sb.append("          FROM TCUSTOMER A, TRECEIVER B");
sb.append("         WHERE A.CUST_NO = B.CUST_NO");
sb.append("           AND (UPPER(B.RECEIVER1) LIKE UPPER('receiver') || '%' OR");
sb.append("                UPPER(B.RECEIVER2) LIKE UPPER('receiver') || '%' OR");
sb.append("                UPPER(B.RECEIVER3) LIKE UPPER('receiver') || '%' OR");
sb.append("                UPPER(B.RECEIVER) LIKE UPPER('receiver') || '%')");
sb.append("             ");
sb.append("          FROM TCUSTOMER A, TRECEIVER B");
sb.append("         WHERE A.CUST_NO = B.CUST_NO            ");
sb.append("           AND (B.TEL = 'tel' OR B.RECEIVER_HP = 'tel')");
sb.append("            ");
sb.append("  ");
sb.append("       ");
sb.append("SELECT /* orderreport.xml : custcenter.orderreport.selectReceiverSearchDetailList by ReceiverSearch */");
sb.append("         TO_CHAR(A.ORDER_DATE, 'YYYY/MM/DD') AS ORDER_DATE,");
sb.append("         A.ORDER_NO,");
sb.append("         A.GOODS_CODE,");
sb.append("         B.GOODS_NAME");
sb.append("          FROM TORDERGOODS A,");
sb.append("               TGOODS B,");
sb.append("               (SELECT DISTINCT Z.ORDER_NO AS ORDER_NO");
sb.append("                  FROM TORDERDT Z");
sb.append("                 WHERE Z.CUST_NO = 'custNo'");
sb.append("                   AND Z.RECEIVER_SEQ = 'receiverSeq') C");
sb.append("         WHERE A.ORDER_NO = C.ORDER_NO");
sb.append("           AND A.GOODS_CODE = B.GOODS_CODE  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------

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
                new Header("Recipient Inquiry (ReceiverSearch)"
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
        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
            }
        });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
