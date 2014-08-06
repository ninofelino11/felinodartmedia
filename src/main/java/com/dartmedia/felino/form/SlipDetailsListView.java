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
@CDIView("SlipDetailsList")
public class SlipDetailsListView extends MVerticalLayout implements View {
//SlipDetailsListSvc data=new SlipDetailsListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Proc date <p:calendar></p:calendar>  ~ <p:calendar></p:calendar>Warehouse <p:selectOneListbox></p:selectOneListbox>  Proc step <p:selectOneListbox></p:selectOneListbox> Vendor <p:inputText></p:inputText> Waybill type    <p:selectOneListbox></p:selectOneListbox> Item <p:inputText></p:inputText> Shipment type  <p:selectOneListbox></p:selectOneListbox>                        **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* outreport.xml : logistics.outreport.selectSlipDetailsList by SlipDetailsList */");
sb.append("            DISTINCT TO_CHAR(A.SLIP_PROC_DATE, 'yyyy/mm/dd') AS SLIP_PROC_DATE,");
sb.append("            A.SLIP_GB,");
sb.append("            A.SLIP_PROC,");
sb.append("            A.SLIP_NO,");
sb.append("            TO_CHAR(A.OUT_CLOSE_DATE, 'yyyy/mm/dd') AS OUT_CLOSE_DATE,");
sb.append("            A.DELY_GB,");
sb.append("            B.CUST_NAME,");
sb.append("            C.RECEIVER,");
sb.append("            A.SLIP_I_NO,");
sb.append("            D.SLIP_SEQ,");
sb.append("            A.DELY_TYPE,");
sb.append("            D.GOODS_CODE,");
sb.append("             D.GOODSDT_CODE,");
sb.append("             G.GOODS_NAME,");
sb.append("            G.GOODSDT_INFO,");
sb.append("            D.RETURN_QTY + D.DELY_QTY AS DELY_QTY,");
sb.append("            D.DELY_AMT,");
sb.append("            D.ORDER_NO ||D.ORDER_G_SEQ||D.ORDER_D_SEQ||D.ORDER_W_SEQ AS ORDER_NO");
sb.append("        FROM");
sb.append("            TSLIPM    A,");
sb.append("            TCUSTOMER B,");
sb.append("            TRECEIVER C,");
sb.append("            TSLIPDT   D,");
sb.append("            TSLIPPROC P,");
sb.append("            TGOODSDT  G,");
sb.append("            TGOODS    H");
sb.append("        WHERE A.CUST_NO           = B.CUST_NO");
sb.append("        AND   A.CUST_NO           = C.CUST_NO");
sb.append("        AND   A.RECEIVER_SEQ      = C.RECEIVER_SEQ");
sb.append("        AND A.SLIP_I_NO           = P.SLIP_I_NO");
sb.append("        AND A.SLIP_I_NO           = D.SLIP_I_NO");
sb.append("        AND D.GOODS_CODE          = G.GOODS_CODE");
sb.append("        AND D.GOODSDT_CODE        = G.GOODSDT_CODE");
sb.append("        AND D.GOODS_CODE          = H.GOODS_CODE");
sb.append("        AND A.WH_CODE          ='001'");
sb.append("        AND P.SLIP_PROC_DATE BETWEEN TO_DATE('2013/11/01', 'yyyy/mm/dd')");
sb.append("        AND TO_DATE('2013/11/01', 'yyyy/mm/dd') + 1");
sb.append("        AND A.SLIP_PROC           = '80'");
sb.append("        AND A.DELY_GB          LIKE '%%'");
sb.append("        AND A.SLIP_GB          LIKE '%%'");
sb.append("        AND G.GOODS_CODE       LIKE '%%'");
sb.append("        AND H.ENTP_CODE        LIKE '%%'");
sb.append("        ORDER BY D.ORDER_NO ||D.ORDER_G_SEQ||D.ORDER_D_SEQ||D.ORDER_W_SEQ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Proc. Date"));
toolbar.addComponent(new PopupDateField("Ware House"));
toolbar.addComponent(new ComboBox("Proc Step"));
toolbar.addComponent(new ComboBox("Waybill Type"));
toolbar.addComponent(new TextField("Shipment Type"));


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
                new Header("Shipment Report (SlipDetailsList)"
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
