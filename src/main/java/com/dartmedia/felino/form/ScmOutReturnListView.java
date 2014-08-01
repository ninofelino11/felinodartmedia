package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("ScmOutReturnList")
public class ScmOutReturnListView extends MVerticalLayout implements View {
//ScmOutReturnListSvc data=new ScmOutReturnListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Vendor <p:inputText></p:inputText>              Term <p:calendar></p:calendar>  ~ <p:calendar></p:calendar>Item <p:inputText></p:inputText>   Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio>  Order Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Item" />  </p:selectOneRadio> Step <p:selectOneListbox></p:selectOneListbox>                                                                          **/
StringBuffer sb = new StringBuffer();
sb.append("/* HARUSNYA BENER TAPI KOQ GAK ADA RESULTNYA */");
sb.append("  SELECT /* entpdelivery.xml : logistics.entpdelivery.selectReturnOkChange by ScmReturnOk */");
sb.append("                   '' AS SELECTION,");
sb.append("                   C.ORDER_NO,      C.ORDER_G_SEQ,  C.ORDER_D_SEQ,   C.ORDER_W_SEQ,                      ");
sb.append("                 C.SYSLAST_AMT,   C.CUST_NO,      C.RECEIVER_SEQ,  C.WH_CODE,                          ");
sb.append("                 C.DO_FLAG,       C.GOODS_GB,     E.CUST_NAME,     C.DELY_TYPE,                        ");
sb.append("                 C.DELY_GB,       C.CLAIM_GB,     C.GOODS_CODE,    D.GOODS_NAME,                       ");
sb.append("                 C.GOODSDT_CODE,  C.GOODSDT_INFO, C.SYSLAST,       F.RECEIVER,                         ");
sb.append("                 F.RECEIVER_POST, F.TEL,          F.RECEIVER_HP,   G.CITY_NAME,                        ");
sb.append("                 G.GU_NAME,       G.DONG_NAME,    F.RECEIVER_ADDR, CA.CODE_NAME,                       ");
sb.append("                 FUN_ADD_POSTADDR(F.RECEIVER_POST, F.RECEIVER_POST_SEQ, F.RECEIVER_ADDR) AS ADDR,                     ");
sb.append("                 '' AS SLIP_NO,   C.SYSLAST_NET,  C.SYSLAST_VAT,   D.ENTP_CODE,                        ");
sb.append("                 H.GOODSDT_CODE AS CHG_GOODSDT_CODE,                                                   ");
sb.append("                 H.GOODSDT_INFO AS CHG_GOODSDT_INFO,                                                   ");
sb.append("                 H.SYSLAST      AS CHG_SYSLAST                                                         ");
sb.append("            FROM TCLAIMDT  C,                                                                          ");
sb.append("                 TGOODS    D,                                                                          ");
sb.append("                 TCUSTOMER E,                                                                          ");
sb.append("                 TRECEIVER F,                                                                          ");
sb.append("                 TPOST     G,                                                                          ");
sb.append("                 TCLAIMDT  H,                                                                          ");
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
sb.append("             AND C.ORDER_NO          = H.ORDER_NO                                                      ");
sb.append("             AND C.ORDER_G_SEQ       = H.ORDER_G_SEQ                                                   ");
sb.append("             AND C.ORDER_D_SEQ       = H.ORDER_D_SEQ                                                   ");
sb.append("             AND C.EXCH_PAIR         = H.EXCH_PAIR                                                     ");
sb.append("             AND H.CLAIM_GB          = '40'                                                            ");
sb.append("             AND C.CLAIM_DATE      >= TO_DATE('2014/01/01', 'YYYY/MM/DD')                                        ");
sb.append("             AND C.CLAIM_DATE        < TO_DATE('2014/01/31', 'YYYY/MM/DD') + 1;                                    ");
sb.append("/*             <if test=entp_code ");
sb.append("=");
sb.append("''>");
sb.append("AND");
sb.append("D.ENTP_CODE");
sb.append("=");
sb.append("#{entp_code,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("AND");
sb.append("C.CLAIM_GB");
sb.append("=");
sb.append("#{cs_gb,jdbcType=VARCHAR}");
sb.append("AND");
sb.append("C.DO_FLAG");
sb.append("=");
sb.append("#{do_flag,jdbcType=VARCHAR}");
sb.append("AND");
sb.append("C.GOODS_CODE");
sb.append("LIKE");
sb.append("#{goods_code,jdbcType=VARCHAR}");
sb.append("||");
sb.append("'%'");
sb.append("*/");
sb.append(";");
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
toolbar.addComponent(new CheckBox("Shipment"));
toolbar.addComponent(new CheckBox("Colection"));




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
                new Header("Entp Delivery/Return Confirm (ScmOutReturnList)"
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
