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
sb.append("");
sb.append("SELECT /* orderinfo.xml : custcenter.orderinfo.selectNoslipList by NoslipList */");
sb.append("                TO_CHAR(CD.LAST_PROC_DATE, 'YYYY/MM/DD') AS LAST_PROC_DATE,");
sb.append("                SUBSTR(CD.ORDER_NO, 1, 8) || '-' ||");
sb.append("                SUBSTR(CD.ORDER_NO, 9)    || '-' ||");
sb.append("                CD.ORDER_G_SEQ || '-' ||");
sb.append("                CD.ORDER_D_SEQ || '-' ||");
sb.append("                CD.ORDER_W_SEQ AS ORDER_NO,");
sb.append("                'gubun' AS GUBUN,");
sb.append("                CD.GOODS_CODE AS GOODS_CODE,");
sb.append("                CM.CUST_NAME,");
sb.append("                RV.RECEIVER,");
sb.append("                ' ' AS MSG,");
sb.append("                CD.GOODSDT_CODE,");
sb.append("                GD.GOODS_NAME,");
sb.append("                GD.GOODSDT_INFO, ");
sb.append("                CD.CANCEL_QTY AS SYSLAST,");
sb.append("                CD.RSALE_AMT AS SYSLAST_AMT,");
sb.append("                EP.ENTP_NAME,");
sb.append("                CD.DO_FLAG");
sb.append("            FROM ");
sb.append("                TCANCELDT CD,");
sb.append("                TGOODS GS,");
sb.append("                TGOODSDT GD,");
sb.append("                TCUSTOMER CM,");
sb.append("                TRECEIVER RV, ");
sb.append("                TENTERPRISE EP,");
sb.append("                TMD MD");
sb.append("            WHERE ");
sb.append("                CD.GOODS_CODE        = GS.GOODS_CODE ");
sb.append("            AND CD.GOODS_CODE        = GD.GOODS_CODE");
sb.append("            AND CD.GOODSDT_CODE      = GD.GOODSDT_CODE");
sb.append("            AND CD.CUST_NO           = CM.CUST_NO ");
sb.append("            AND CD.CUST_NO           = RV.CUST_NO  ");
sb.append("            AND CD.RECEIVER_SEQ      = RV.RECEIVER_SEQ    ");
sb.append("            AND GS.ENTP_CODE         = EP.ENTP_CODE   ");
sb.append("            AND GS.MD_CODE           = MD.MD_CODE  ");
sb.append("            AND CD.LAST_PROC_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD') /* fromDate */");
sb.append("            AND CD.LAST_PROC_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1  /* toDate */");
sb.append("            AND CD.WH_CODE LIKE 'wh_code' || '%'");
sb.append("            AND CD.DO_FLAG LIKE 'do_flag' || '%'");
sb.append("            AND GS.ENTP_CODE LIKE 'entp_code' || '%'");
sb.append("            AND CD.GOODS_CODE LIKE 'goods_code' || '%'     ");
sb.append("            ");
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
