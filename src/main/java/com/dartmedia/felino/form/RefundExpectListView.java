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
@CDIView("RefundExpectList")
public class RefundExpectListView extends MVerticalLayout implements View {
//RefundExpectListSvc data=new RefundExpectListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT /* refund.xml : custcenter.refund.selectRefundExpectEndList by RefundExpectList */");
sb.append("         R.REPAY_NO,");
sb.append("         R.RECEIPT_NO,");
sb.append("         R.CUST_NO,");
sb.append("         R.DO_FLAG,");
sb.append("         R.SETTLE_GB,");
sb.append("         R.CLAIM_CODE,");
sb.append("         R.BANK_CODE,");
sb.append("         CWARE_ENC_DEC(R.BANK_DEPOSIT_NO, 'd') AS BANK_DEPOSIT_NO,");
sb.append("         R.DEPOSITOR,");
sb.append("         R.REPAY_NOTE,");
sb.append("         R.REPAY_AMT,");
sb.append("         R.REAL_REPAY_AMT,");
sb.append("         R.REPAY_COMMISSION,");
sb.append("         TO_CHAR(R.REAL_DATE, 'YYYY/MM/DD HH24:MI:SS') AS REAL_DATE,");
sb.append("         TO_CHAR(R.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,");
sb.append("         R.INSERT_ID,");
sb.append("         TO_CHAR(R.TRANS_DATE, 'YYYY/MM/DD HH24:MI:SS') AS TRANS_DATE,");
sb.append("         R.TRANS_ID,");
sb.append("         TO_CHAR(R.END_DATE, 'YYYY/MM/DD HH24:MI:SS') AS END_DATE,");
sb.append("         R.END_ID,");
sb.append("         C.CUST_NAME,");
sb.append("         B.BANK_NAME");
sb.append("          FROM TREFUND R, TCUSTOMER C, TBANK B");
sb.append("         WHERE R.CUST_NO = C.CUST_NO");
sb.append("           AND R.BANK_CODE = B.BANK_CODE");
sb.append("           AND R.TRANS_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("           AND R.TRANS_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
sb.append("           AND R.DO_FLAG = '20'   ");
sb.append("           ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Refund Approved"));
toolbar.addComponent(new PopupDateField("~"));


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
                new Header("Refund Approve List (RefundExpectList)"
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
