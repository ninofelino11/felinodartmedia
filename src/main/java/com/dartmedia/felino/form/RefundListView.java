package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
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
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("RefundList")
public class RefundListView extends MVerticalLayout implements View {
//RefundListSvc data=new RefundListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("refund.xml");
sb.append(":");
sb.append("custcenter.refund.selectRefundList");
sb.append("by");
sb.append("RefundList");
sb.append("*/");
sb.append("A.REPAY_NO");
sb.append(",");
sb.append("A.CUST_NO");
sb.append(",");
sb.append("B.CUST_NAME");
sb.append(",");
sb.append("TO_CHAR(A.INSERT_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("INSERT_DATE");
sb.append(",");
sb.append("D.USER_NAME");
sb.append(",");
sb.append("A.DO_FLAG");
sb.append(",");
sb.append("A.SETTLE_GB");
sb.append(",");
sb.append("DECODE(A.CLAIM_CODE,NULL,'00',A.CLAIM_CODE)");
sb.append("CLAIM_CODE");
sb.append(",");
sb.append("C.BANK_NAME");
sb.append(",");
sb.append("CWARE_ENC_DEC(A.BANK_DEPOSIT_NO,");
sb.append("'d')");
sb.append("AS");
sb.append("BANK_DEPOSIT_NO");
sb.append(",");
sb.append("A.DEPOSITOR");
sb.append(",");
sb.append("A.REPAY_AMT");
sb.append(",");
sb.append("A.REAL_REPAY_AMT");
sb.append(",");
sb.append("A.RECEIPT_NO");
sb.append(",");
sb.append("TO_CHAR(A.TRANS_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("TRANS_DATE");
sb.append(",");
sb.append("DECODE(E.USER_NAME,NULL,NULL,E.USER_NAME)");
sb.append("USER_NAME1");
sb.append(",");
sb.append("TO_CHAR(A.END_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("END_DATE");
sb.append(",");
sb.append("DECODE(F.USER_NAME,NULL,NULL,F.USER_NAME)");
sb.append("USER_NAME2");
sb.append(",");
sb.append("A.REPAY_NOTE");
sb.append("FROM");
sb.append("TUSER");
sb.append("F,");
sb.append("TUSER");
sb.append("E,");
sb.append("TUSER");
sb.append("D,");
sb.append("TBANK");
sb.append("C,");
sb.append("TCUSTOMER");
sb.append("B,");
sb.append("TREFUND");
sb.append("A");
sb.append("WHERE");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("B.CUST_NO");
sb.append("AND");
sb.append("A.BANK_CODE");
sb.append("=");
sb.append("C.BANK_CODE");
sb.append("AND");
sb.append("A.DO_FLAG");
sb.append("=");
sb.append("'10'");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE(TO_CHAR('2014-01-01'),");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AND");
sb.append("TO_DATE(TO_CHAR('2014-01-01'),");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append(">=");
sb.append("A.INSERT_DATE");
sb.append("AND");
sb.append("A.INSERT_ID");
sb.append("=");
sb.append("D.USER_ID");
sb.append("AND");
sb.append("A.TRANS_ID");
sb.append("=");
sb.append("E.USER_ID(+)");
sb.append("AND");
sb.append("A.END_ID");
sb.append("=");
sb.append("F.USER_ID(+)");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Retun Complete Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Media"));
toolbar.addComponent(new ComboBox("Proc, Method"));
toolbar.addComponent(new Button("Return Processing"));
toolbar.addComponent(new Button("Ordering"));


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
                new Header("Refund Request List (RefundList)"
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
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable();
               table.setContainerDataSource(container);
table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
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
