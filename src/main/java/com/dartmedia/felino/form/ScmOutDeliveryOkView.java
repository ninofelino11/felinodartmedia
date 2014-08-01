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
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("ScmOutDeliveryOk")
public class ScmOutDeliveryOkView extends MVerticalLayout implements View {
//ScmOutDeliveryOkSvc data=new ScmOutDeliveryOkSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Vendor <p:inputText></p:inputText>              Term <p:calendar></p:calendar>  ~ <p:calendar></p:calendar>Item <p:inputText></p:inputText>   Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio> Step <p:selectOneListbox></p:selectOneListbox>                                                    **/
StringBuffer sb = new StringBuffer();
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
sb.append("SELECT /* entpdelivery.xml : logistics.entpdelivery.selectOrderDeliveryDetailOk by ScmOutDeliveryOk */    ");
sb.append("               OD.ORDER_NO,");
sb.append("               OD.ORDER_G_SEQ,");
sb.append("               OD.ORDER_D_SEQ,");
sb.append("               OD.ORDER_W_SEQ,");
sb.append("               OD.GOODS_CODE,");
sb.append("               GM.GOODS_NAME,");
sb.append("               OD.GOODSDT_CODE,");
sb.append("               OD.GOODSDT_INFO,");
sb.append("               OD.GOODS_GB,");
sb.append("               OD.SYSLAST,");
sb.append("               OD.SYSLAST_AMT,");
sb.append("               OD.SYSLAST_NET,");
sb.append("               OD.SYSLAST_VAT,");
sb.append("               SD.DELY_QTY,");
sb.append("               '' AS REMARK");
sb.append("          FROM TORDERDT  OD,");
sb.append("               TSLIPDT   SD,");
sb.append("               TSLIPM    SM,");
sb.append("               TGOODS    GM");
sb.append("         WHERE OD.ORDER_NO     = SD.ORDER_NO(+)");
sb.append("           AND OD.ORDER_G_SEQ  = SD.ORDER_G_SEQ(+)");
sb.append("           AND OD.ORDER_D_SEQ  = SD.ORDER_D_SEQ(+)");
sb.append("           AND OD.ORDER_W_SEQ  = SD.ORDER_W_SEQ(+)");
sb.append("           AND SD.SLIP_I_NO    = SM.SLIP_I_NO(+)");
sb.append("           AND OD.GOODS_CODE   = GM.GOODS_CODE");
sb.append("           AND OD.ORDER_NO     = 20140403032491");
sb.append("           AND NVL(SM.SLIP_I_NO, 'X') = NVL('10000000112407', 'X')");
sb.append("           AND OD.DO_FLAG        = '30'");
sb.append("           AND OD.DELY_TYPE      = '20'");
sb.append("           AND OD.SYSLAST        > 0");
sb.append("           AND GM.OUT_STOCK_YN   = '0'            ");
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
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("ITEM", String.class,  null);
table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
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
