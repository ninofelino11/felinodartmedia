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
@CDIView("CustOrderList")
public class CustOrderListView extends MVerticalLayout implements View {
//CustOrderListSvc data=new CustOrderListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT   /* orderreport.xml : custcenter.orderreport.selectCustOrderList by CustOrderList */");
sb.append("                   B.ORDER_DATE,                                                                              ");
sb.append("                  B.ORDER_NO,                                                                                ");
sb.append("                  B.ORDER_G_SEQ,                                                                             ");
sb.append("                  B.ORDER_D_SEQ,                                                                             ");
sb.append("                  B.ORDER_W_SEQ,                                                                             ");
sb.append("                  B.CUST_NO,                                                                                 ");
sb.append("                  B.RECEIVER_SEQ,                                                                            ");
sb.append("                  C.GOODS_CODE   AS GOODS_CODE,                                                              ");
sb.append("                  C.GOODS_NAME,                                                                              ");
sb.append("                  B.GOODSDT_INFO,                                                                            ");
sb.append("                  B.SYSLAST     AS QTY,                                                                      ");
sb.append("                  B.SYSLAST_AMT AS AMT,                                                                      ");
sb.append("                  C.DELY_TYPE,                                                                               ");
sb.append("                  C.WH_CODE,                                                                                 ");
sb.append("                  C.ENTP_CODE,                                                                               ");
sb.append("                  D.CUST_NAME   AS CUST_NAME,                                                                ");
sb.append("                  E.RECEIVER,                                                                                  ");
sb.append("                  E.TEL         AS RECEIVER_TEL                                                                 ");
sb.append("           FROM ( SELECT  CUST_NO  AS  CUST_NO,                                                              ");
sb.append("                          SUM(SYSLAST),                                                                      ");
sb.append("                          SUM(SYSLAST_AMT)                                                                   ");
sb.append("                    FROM  TORDERDT                                                                           ");
sb.append("                   WHERE  ORDER_DATE    >= TO_DATE('2014-01-01' ||  '00:00:00', 'yyyy/mm/dd hh24:mi:ss') /* fromDate */                   ");
sb.append("                     AND  ORDER_DATE <= TO_DATE('2014-01-01' ||  '23:59:59', 'yyyy/mm/dd hh24:mi:ss') /* toDate */                   ");
sb.append("                     AND  SYSLAST     > 0                                                                    ");
sb.append("                   GROUP  BY  CUST_NO                                                                        ");
sb.append("                   HAVING SUM(SYSLAST)      >= 0 /* fromQty */                                                                ");
sb.append("                      AND SUM(SYSLAST)   <= 1    /* toQty */                                                              ");
sb.append("                      AND SUM(SYSLAST_AMT)    >= 0  /* fromAmt */                                                              ");
sb.append("                      AND SUM(SYSLAST_AMT) <= 1     /* toAmt */                                                           ");
sb.append("               ) A, TGOODS C, TCUSTOMER D, TRECEIVER E, TORDERDT B                                           ");
sb.append("          WHERE  A.CUST_NO     = B.CUST_NO                                                                   ");
sb.append("            AND  A.CUST_NO     = D.CUST_NO                                                                   ");
sb.append("            AND  B.ORDER_DATE    >= TO_DATE('2014-01-01', jdbcType=VARCHAR}   ||  '00:00:00', 'yyyy/mm/dd hh24:mi:ss') /* fromDate */                           ");
sb.append("            AND  B.ORDER_DATE <= TO_DATE('2014-01-01',   jdbcType=VARCHAR}   ||  '23:59:59', 'yyyy/mm/dd hh24:mi:ss') /* toDate */                                 ");
sb.append("            AND  B.SYSLAST     > 0                                                                           ");
sb.append("            AND  B.GOODS_CODE   = C.GOODS_CODE                                                               ");
sb.append("            AND  B.CUST_NO     = E.CUST_NO                                                                   ");
sb.append("            AND  B.RECEIVER_SEQ = E.RECEIVER_SEQ ");
sb.append("            ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Entry Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Order Date"));
toolbar.addComponent(new TextField("~"));
toolbar.addComponent(new TextField("Qty"));
toolbar.addComponent(new TextField("~"));


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
                new Header("Order Customer Report (CustOrderList)"
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
