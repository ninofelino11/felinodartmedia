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
@CDIView("OrderProcCustList2")
public class OrderProcCustList2View extends MVerticalLayout implements View {
//OrderProcCustList2Svc data=new OrderProcCustList2Svc();
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
sb.append("custcenter.orderinfo.selectNoslipList");
sb.append("by");
sb.append("NoslipList");
sb.append("*/");
sb.append("TO_CHAR(CD.LAST_PROC_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("LAST_PROC_DATE,");
sb.append("SUBSTR(CD.ORDER_NO,");
sb.append("1,");
sb.append("8)");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("SUBSTR(CD.ORDER_NO,");
sb.append("9)");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("CD.ORDER_G_SEQ");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("CD.ORDER_D_SEQ");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("CD.ORDER_W_SEQ");
sb.append("AS");
sb.append("ORDER_NO,");
sb.append("'gubun'");
sb.append("AS");
sb.append("GUBUN,");
sb.append("CD.GOODS_CODE");
sb.append("AS");
sb.append("GOODS_CODE,");
sb.append("CM.CUST_NAME,");
sb.append("RV.RECEIVER,");
sb.append("'");
sb.append("'");
sb.append("AS");
sb.append("MSG,");
sb.append("CD.GOODSDT_CODE,");
sb.append("GD.GOODS_NAME,");
sb.append("GD.GOODSDT_INFO,");
sb.append("CD.CANCEL_QTY");
sb.append("AS");
sb.append("SYSLAST,");
sb.append("CD.RSALE_AMT");
sb.append("AS");
sb.append("SYSLAST_AMT,");
sb.append("EP.ENTP_NAME,");
sb.append("CD.DO_FLAG");
sb.append("FROM");
sb.append("TCANCELDT");
sb.append("CD,");
sb.append("TGOODS");
sb.append("GS,");
sb.append("TGOODSDT");
sb.append("GD,");
sb.append("TCUSTOMER");
sb.append("CM,");
sb.append("TRECEIVER");
sb.append("RV,");
sb.append("TENTERPRISE");
sb.append("EP,");
sb.append("TMD");
sb.append("MD");
sb.append("WHERE");
sb.append("CD.GOODS_CODE");
sb.append("=");
sb.append("GS.GOODS_CODE");
sb.append("AND");
sb.append("CD.GOODS_CODE");
sb.append("=");
sb.append("GD.GOODS_CODE");
sb.append("AND");
sb.append("CD.GOODSDT_CODE");
sb.append("=");
sb.append("GD.GOODSDT_CODE");
sb.append("AND");
sb.append("CD.CUST_NO");
sb.append("=");
sb.append("CM.CUST_NO");
sb.append("AND");
sb.append("CD.CUST_NO");
sb.append("=");
sb.append("RV.CUST_NO");
sb.append("AND");
sb.append("CD.RECEIVER_SEQ");
sb.append("=");
sb.append("RV.RECEIVER_SEQ");
sb.append("AND");
sb.append("GS.ENTP_CODE");
sb.append("=");
sb.append("EP.ENTP_CODE");
sb.append("AND");
sb.append("GS.MD_CODE");
sb.append("=");
sb.append("MD.MD_CODE");
sb.append("AND");
sb.append("CD.LAST_PROC_DATE");
sb.append(">=");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("/*");
sb.append("fromDate");
sb.append("*/");
sb.append("AND");
sb.append("CD.LAST_PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014-01-01',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("/*");
sb.append("toDate");
sb.append("*/");
sb.append("AND");
sb.append("CD.WH_CODE");
sb.append("LIKE");
sb.append("'wh_code'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("CD.DO_FLAG");
sb.append("LIKE");
sb.append("'do_flag'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("GS.ENTP_CODE");
sb.append("LIKE");
sb.append("'entp_code'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("CD.GOODS_CODE");
sb.append("LIKE");
sb.append("'goods_code'");
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
                new Header("Customer Report for Cancel (OrderProcCustList2)"
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
