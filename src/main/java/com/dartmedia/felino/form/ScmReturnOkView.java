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
@CDIView("ScmReturnOk")
public class ScmReturnOkView extends MVerticalLayout implements View {
//ScmReturnOkSvc data=new ScmReturnOkSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Vendor <p:inputText></p:inputText>              Term <p:calendar></p:calendar>  ~ <p:calendar></p:calendar>Item <p:inputText></p:inputText>   Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio>  Order Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio> Step <p:selectOneListbox></p:selectOneListbox>                                                    **/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /* entpdelivery.xml : logistics.entpdelivery.selectReturnOk by ScmReturnOk */");
sb.append("                   '' as SELECTION,");
sb.append("                   C.ORDER_NO,      C.ORDER_G_SEQ,  C.ORDER_D_SEQ,   C.ORDER_W_SEQ,                      ");
sb.append("                 C.SYSLAST_AMT,   C.CUST_NO,      C.RECEIVER_SEQ,  C.WH_CODE,                          ");
sb.append("                 C.DO_FLAG,       C.GOODS_GB,     E.CUST_NAME,     C.DELY_TYPE,                        ");
sb.append("                 C.DELY_GB,       C.CLAIM_GB,     C.GOODS_CODE,    D.GOODS_NAME,                       ");
sb.append("                 C.GOODSDT_CODE,  C.GOODSDT_INFO, C.SYSLAST,       F.RECEIVER,                         ");
sb.append("                 F.RECEIVER_POST, F.TEL,          F.RECEIVER_HP,   G.CITY_NAME,                        ");
sb.append("                 G.GU_NAME,       G.DONG_NAME,    F.RECEIVER_ADDR, CA.CODE_NAME,                       ");
sb.append("                 FUN_ADD_POSTADDR(F.RECEIVER_POST, F.RECEIVER_POST_SEQ, F.RECEIVER_ADDR) AS ADDR,                       ");
sb.append("                 '' AS SLIP_NO,   C.SYSLAST_NET,  C.SYSLAST_VAT,   D.ENTP_CODE                         ");
sb.append("            FROM TCLAIMDT  C,                                                                          ");
sb.append("                 TGOODS    D,                                                                          ");
sb.append("                 TCUSTOMER E,                                                                          ");
sb.append("                 TRECEIVER F,                                                                          ");
sb.append("                 TPOST     G,                                                                          ");
sb.append("                 TCODE     CA                                                                          ");
sb.append("           WHERE C.GOODS_CODE        = D.GOODS_CODE                                                    ");
sb.append("             AND C.CUST_NO           = E.CUST_NO                                                       ");
sb.append("             AND C.CUST_NO           = F.CUST_NO                                                       ");
sb.append("             AND C.RECEIVER_SEQ      = F.RECEIVER_SEQ                                                  ");
sb.append("             AND F.RECEIVER_POST     = G.POST_NO                                                       ");
sb.append("             AND F.RECEIVER_POST_SEQ = G.POST_SEQ                                                      ");
sb.append("             AND CA.CODE_LGROUP      = 'L015'                                                          ");
sb.append("             AND C.CLAIM_GB          = CA.CODE_MGROUP                                                  ");
sb.append("             AND C.SYSLAST           > 0                                                               ");
sb.append("             AND C.DELY_TYPE         = '20'                                                            ");
sb.append("             AND CLAIM_DATE         >= TO_DATE('2013/11/01', 'YYYY/MM/DD')                                        ");
sb.append("             AND CLAIM_DATE          < TO_DATE('2014/07/01', 'YYYY/MM/DD') + 1                                    ");
sb.append("                AND D.ENTP_CODE = '100075'");
sb.append("             AND C.CLAIM_GB          = '30'                                                               ");
sb.append("             AND C.DO_FLAG           = '10'                                                               ");
sb.append("             AND C.GOODS_CODE LIKE '%103006%'  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new ComboBox("Step"));
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new CheckBox("Type"));
toolbar.addComponent(new CheckBox("Order"));
toolbar.addComponent(new CheckBox("Exchange"));
toolbar.addComponent(new CheckBox("Order Typr"));
toolbar.addComponent(new CheckBox("Shipment"));
toolbar.addComponent(new CheckBox("Collection"));





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
                new Header("Entp Return Confirm (ScmReturnOk)"
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
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               TreeTable table = new TreeTable("Menu", container);
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
