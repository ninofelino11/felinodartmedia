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
@CDIView("RefundEnd")
public class RefundEndView extends MVerticalLayout implements View {
//RefundEndSvc data=new RefundEndSvc();
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
sb.append("custcenter.refund.selectRefundCompleteList");
sb.append("by");
sb.append("RefundEndList");
sb.append("*/");
sb.append("R.REPAY_NO,");
sb.append("R.RECEIPT_NO,");
sb.append("R.CUST_NO,");
sb.append("R.DO_FLAG,");
sb.append("R.SETTLE_GB,");
sb.append("R.CLAIM_CODE,");
sb.append("R.BANK_CODE,");
sb.append("CWARE_ENC_DEC(R.BANK_DEPOSIT_NO,");
sb.append("'d')");
sb.append("AS");
sb.append("BANK_DEPOSIT_NO,");
sb.append("R.DEPOSITOR,");
sb.append("R.REPAY_NOTE,");
sb.append("R.REPAY_AMT,");
sb.append("R.REAL_REPAY_AMT,");
sb.append("R.REPAY_COMMISSION,");
sb.append("TO_CHAR(R.REAL_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("REAL_DATE,");
sb.append("TO_CHAR(R.INSERT_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("INSERT_DATE,");
sb.append("R.INSERT_ID,");
sb.append("TO_CHAR(R.TRANS_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("TRANS_DATE,");
sb.append("R.TRANS_ID,");
sb.append("TO_CHAR(R.END_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("END_DATE,");
sb.append("R.END_ID,");
sb.append("C.CUST_NAME,");
sb.append("B.BANK_NAME");
sb.append("FROM");
sb.append("TREFUND");
sb.append("R,");
sb.append("TCUSTOMER");
sb.append("C,");
sb.append("TBANK");
sb.append("B");
sb.append("WHERE");
sb.append("R.CUST_NO");
sb.append("=");
sb.append("C.CUST_NO");
sb.append("AND");
sb.append("R.BANK_CODE");
sb.append("=");
sb.append("B.BANK_CODE");
sb.append("AND");
sb.append("R.DO_FLAG");
sb.append("=");
sb.append("'procGb'");
sb.append("AND");
sb.append("R.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("R.INSERT_DATE");
sb.append("<");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("R.BANK_CODE");
sb.append("LIKE");
sb.append("'bankCode'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("R.SETTLE_GB");
sb.append("LIKE");
sb.append("'settleGb'");
sb.append("||");
sb.append("'%'");
sb.append("ORDER");
sb.append("BY");
sb.append("TO_CHAR(R.END_DATE,");
sb.append("'YYYY/MM/DD')");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Refund Approve"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Refund Payment"));
toolbar.addComponent(new Button("Select"));
toolbar.addComponent(new Button("Deselect"));
toolbar.addComponent(new TextField("Bank"));


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
                new Header("Refund Payment (RefundEnd)"
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
