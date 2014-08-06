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
@CDIView("OrderProcCustList3")
public class OrderProcCustList3View extends MVerticalLayout implements View {
//OrderProcCustList3Svc data=new OrderProcCustList3Svc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("orderinfo.xml");
sb.append(":");
sb.append("custcenter.orderreport.selectClaimProcCustList");
sb.append("by");
sb.append("OrderProcCustList3");
sb.append("*/");
sb.append("TO_CHAR(A.LAST_PROC_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("LAST_PROC_DATE,");
sb.append("SUBSTR(A.ORDER_NO,");
sb.append("1,");
sb.append("8)");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("SUBSTR(A.ORDER_NO,");
sb.append("9)");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("A.ORDER_G_SEQ");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("A.ORDER_D_SEQ");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("A.ORDER_W_SEQ");
sb.append("AS");
sb.append("ORDER_NO,");
sb.append("TCODE_NAME('J007',");
sb.append("A.CLAIM_GB)");
sb.append("AS");
sb.append("GUBUN,");
sb.append("A.GOODS_CODE");
sb.append("AS");
sb.append("GOODS_CODE,");
sb.append("D.CUST_NAME,");
sb.append("E.RECEIVER,");
sb.append("''");
sb.append("AS");
sb.append("MSG,");
sb.append("A.GOODSDT_CODE,");
sb.append("C.GOODS_NAME,");
sb.append("C.GOODSDT_INFO,");
sb.append("A.SYSLAST,");
sb.append("A.SYSLAST_AMT,");
sb.append("G.ENTP_NAME,");
sb.append("A.DO_FLAG");
sb.append("FROM");
sb.append("TCLAIMDT");
sb.append("A,");
sb.append("TGOODS");
sb.append("B,");
sb.append("TGOODSDT");
sb.append("C,");
sb.append("TCUSTOMER");
sb.append("D,");
sb.append("TRECEIVER");
sb.append("E,");
sb.append("TENTERPRISE");
sb.append("G,");
sb.append("TMD");
sb.append("H");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("C.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODSDT_CODE");
sb.append("=");
sb.append("C.GOODSDT_CODE");
sb.append("AND");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("D.CUST_NO");
sb.append("AND");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("E.CUST_NO");
sb.append("AND");
sb.append("A.RECEIVER_SEQ");
sb.append("=");
sb.append("E.RECEIVER_SEQ");
sb.append("AND");
sb.append("B.ENTP_CODE");
sb.append("=");
sb.append("G.ENTP_CODE");
sb.append("AND");
sb.append("B.MD_CODE");
sb.append("=");
sb.append("H.MD_CODE");
sb.append("AND");
sb.append("A.LAST_PROC_DATE");
sb.append(">=");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("/*");
sb.append("fromDate");
sb.append("*/");
sb.append("AND");
sb.append("A.LAST_PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("/*");
sb.append("toDate");
sb.append("*/");
sb.append("AND");
sb.append("A.WH_CODE");
sb.append("LIKE");
sb.append("'whCode'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.DO_FLAG");
sb.append("LIKE");
sb.append("'doFlag'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("B.ENTP_CODE");
sb.append("LIKE");
sb.append("'entpCode'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("LIKE");
sb.append("'goodsCode'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.CLAIM_GB");
sb.append("LIKE");
sb.append("'claimGb'");
sb.append("||");
sb.append("'%'");
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
                new Header("Customer Report for Return (OrderProcCustList3)"
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
