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
@CDIView("ScmOutDeliveryOk")
public class ScmOutDeliveryOkView extends MVerticalLayout implements View {
//ScmOutDeliveryOkSvc data=new ScmOutDeliveryOkSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Vendor <p:inputText></p:inputText>              Term <p:calendar></p:calendar>  ~ <p:calendar></p:calendar>Item <p:inputText></p:inputText>   Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio> Step <p:selectOneListbox></p:selectOneListbox>                                                    **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* entpdelivery.xml : logistics.entpdelivery.selectOrderDeliveryOk by ScmOutDeliveryOk */");
sb.append("                   DISTINCT '0' AS CHECK_YN,                                                                             ");
sb.append("                 DECODE(OD.DO_FLAG, '20', '', NVL(SM.SLIP_NO, ''))       AS SLIP_NO,                          ");
sb.append("                 DECODE(OD.DO_FLAG, '20', '', NVL(SM.DELY_GB, ''))       AS DELY_GB,                          ");
sb.append("                 DECODE(OD.DO_FLAG, '20', '', NVL(SM.REAL_RECEIVER, '')) AS REAL_RECEIVER,                    ");
sb.append("                 OD.ORDER_NO,                                                                                 ");
sb.append("                 OD.DO_FLAG,");
sb.append("                 OD.WH_CODE,                                                                                  ");
sb.append("                 DECODE(OD.DO_FLAG, '20', '', SM.SLIP_I_NO)     AS SLIP_I_NO,                                 ");
sb.append("                 DECODE(OD.DO_FLAG, '20', '', SM.SLIP_GB)       AS SLIP_GB,                                   ");
sb.append("                 TC.CUST_NO,                                                                                  ");
sb.append("                 TC.CUST_NAME,                                                                                ");
sb.append("                 TR.RECEIVER,                                                                                 ");
sb.append("                 TR.RECEIVER_POST,                                                                            ");
sb.append("                 FUN_ADD_POSTADDR(TR.RECEIVER_POST, TR.RECEIVER_POST_SEQ, TR.RECEIVER_ADDR) AS RECEIVER_ADDR, ");
sb.append("                 TR.RECEIVER_DDD||TR.RECEIVER_TEL1||TR.RECEIVER_TEL2 AS TEL1,                                 ");
sb.append("                 TR.RECEIVER_HP1||TR.RECEIVER_HP2||TR.RECEIVER_HP3 AS TEL2 , OD.DO_FLAG                                   ");
sb.append("            FROM TORDERDT       OD,                                                                           ");
sb.append("                 TSLIPDT        SD,                                                                           ");
sb.append("                 TSLIPM         SM,                                                                           ");
sb.append("                 TCUSTOMER      TC,                                                                           ");
sb.append("                 TRECEIVER      TR,                                                                           ");
sb.append("                 TGOODS         GM                                                                            ");
sb.append("           WHERE OD.CUST_NO          = TC.CUST_NO                                                             ");
sb.append("             AND OD.CUST_NO          = TR.CUST_NO                                                             ");
sb.append("             AND OD.ORDER_NO         = SD.ORDER_NO(+)                                                         ");
sb.append("             AND OD.ORDER_G_SEQ      = SD.ORDER_G_SEQ(+)                                                      ");
sb.append("             AND OD.ORDER_D_SEQ      = SD.ORDER_D_SEQ(+)                                                      ");
sb.append("             AND OD.ORDER_W_SEQ      = SD.ORDER_W_SEQ(+)                                                      ");
sb.append("             AND SD.SLIP_I_NO        = SM.SLIP_I_NO(+)                                                        ");
sb.append("             AND OD.RECEIVER_SEQ     = TR.RECEIVER_SEQ                                                        ");
sb.append("             AND OD.GOODS_CODE       = GM.GOODS_CODE");
sb.append("                  AND GM.ENTP_CODE = '100002'");
sb.append("             AND OD.LAST_PROC_DATE  >= TO_DATE('2013/11/01', 'YYYY/MM/DD')                                               ");
sb.append("             AND OD.LAST_PROC_DATE   <  TO_DATE('2014/07/01', 'YYYY/MM/DD') + 1                                          ");
sb.append("             AND OD.DO_FLAG          = '30'                                                                     ");
sb.append("             AND OD.DELY_TYPE        = '20'                                                                   ");
sb.append("             AND OD.SYSLAST          > 0 ");
sb.append("            ");

sb1.append("SELECT /* entpdelivery.xml : logistics.entpdelivery.selectOrderDeliveryDetailOk by ScmOutDeliveryOk */    ");
sb1.append("               OD.ORDER_NO,");
sb1.append("               OD.ORDER_G_SEQ,");
sb1.append("               OD.ORDER_D_SEQ,");
sb1.append("               OD.ORDER_W_SEQ,");
sb1.append("               OD.GOODS_CODE,");
sb1.append("               GM.GOODS_NAME,");
sb1.append("               OD.GOODSDT_CODE,");
sb1.append("               OD.GOODSDT_INFO,");
sb1.append("               OD.GOODS_GB,");
sb1.append("               OD.SYSLAST,");
sb1.append("               OD.SYSLAST_AMT,");
sb1.append("               OD.SYSLAST_NET,");
sb1.append("               OD.SYSLAST_VAT,");
sb1.append("               SD.DELY_QTY,");
sb1.append("               '' AS REMARK");
sb1.append("          FROM TORDERDT  OD,");
sb1.append("               TSLIPDT   SD,");
sb1.append("               TSLIPM    SM,");
sb1.append("               TGOODS    GM");
sb1.append("         WHERE OD.ORDER_NO     = SD.ORDER_NO(+)");
sb1.append("           AND OD.ORDER_G_SEQ  = SD.ORDER_G_SEQ(+)");
sb1.append("           AND OD.ORDER_D_SEQ  = SD.ORDER_D_SEQ(+)");
sb1.append("           AND OD.ORDER_W_SEQ  = SD.ORDER_W_SEQ(+)");
sb1.append("           AND SD.SLIP_I_NO    = SM.SLIP_I_NO(+)");
sb1.append("           AND OD.GOODS_CODE   = GM.GOODS_CODE");
sb1.append("           AND OD.ORDER_NO     = 20140403032491");
sb1.append("           AND NVL(SM.SLIP_I_NO, 'X') = NVL('10000000112407', 'X')");
sb1.append("           AND OD.DO_FLAG        = '30'");
sb1.append("           AND OD.DELY_TYPE      = '20'");
sb1.append("           AND OD.SYSLAST        > 0");
sb1.append("           AND GM.OUT_STOCK_YN   = '0'            ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new CheckBox("Type"));
toolbar.addComponent(new CheckBox("Order"));
toolbar.addComponent(new CheckBox("Exchange"));
toolbar.addComponent(new ComboBox("Step"));
toolbar.addComponent(new Button("Select"));
toolbar.addComponent(new Button("Deselect"));
toolbar.addComponent(new Button("Waybil No Copy"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new Button("Delivery Company"));
toolbar.addComponent(new ComboBox("Other"));








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
                new Header("Entp Delivery Confirm (ScmOutDeliveryOk)"
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
