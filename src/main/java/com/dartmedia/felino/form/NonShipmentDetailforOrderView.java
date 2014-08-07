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
@CDIView("NonShipmentDetailforOrder")
public class NonShipmentDetailforOrderView extends MVerticalLayout implements View {
//NonShipmentDetailforOrderSvc data=new NonShipmentDetailforOrderSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Days that elapsed <p:selectOneListbox></p:selectOneListbox> ~ <p:selectOneListbox></p:selectOneListbox>Order Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Change return" />  <f:selectItem itemValue="All" />  </p:selectOneRadio>                               **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("/*");
sb.append("QUERYNYA");
sb.append("KOMPLEKS");
sb.append("JADI");
sb.append("SAYA");
sb.append("COPY");
sb.append("APA");
sb.append("ADANYA");
sb.append("AJA");
sb.append("YA");
sb.append("SELECT");
sb.append("/*");
sb.append("outreport.xml");
sb.append(":");
sb.append("logistics.outreport.selectNonShipmentDetailforOrder");
sb.append("by");
sb.append("NonShipmentDetailforOrder");
sb.append("*/");
sb.append("XA.ORDER_GB,");
sb.append("XA.ORDER_NO,");
sb.append("XA.CUST_NO,");
sb.append("XC.CUST_NAME,");
sb.append("XB.RECEIVER,");
sb.append("XB.TEL");
sb.append("AS");
sb.append("TEL_NO,");
sb.append("XB.RECEIVER_HP,");
sb.append("FUN_ADD_POSTADDR(XB.RECEIVER_POST,");
sb.append("XB.RECEIVER_POST_SEQ,");
sb.append("XB.RECEIVER_ADDR)");
sb.append("AS");
sb.append("CUST_ADDR_D");
sb.append("FROM");
sb.append("(");
sb.append("<if");
sb.append("test='use_code");
sb.append("==");
//sb.append(""1"");
sb.append("or");
sb.append("use_code");
sb.append("==");
//sb.append("""'>");
sb.append("SELECT");
sb.append("DISTINCT");
sb.append("TB.ORDER_GB,");
sb.append("TB.ORDER_NO,");
sb.append("TB.CUST_NO,");
sb.append("TB.RECEIVER_SEQ,");
sb.append("TRUNC(SYSDATE)");
sb.append("-");
sb.append("TRUNC(TC.PROC_DATE)");
sb.append("AS");
sb.append("DELAY_DAY,");
sb.append("TB.SYSLAST");
sb.append("FROM");
sb.append("TORDERDT");
sb.append("TB,");
sb.append("TORDERPROC");
sb.append("TC");
sb.append("WHERE");
sb.append("TB.ORDER_NO");
sb.append("=");
sb.append("TC.ORDER_NO");
sb.append("AND");
sb.append("TB.ORDER_G_SEQ");
sb.append("=");
sb.append("TC.ORDER_G_SEQ");
sb.append("AND");
sb.append("TB.ORDER_D_SEQ");
sb.append("=");
sb.append("TC.ORDER_D_SEQ");
sb.append("AND");
sb.append("TB.ORDER_W_SEQ");
sb.append("=");
sb.append("TC.ORDER_W_SEQ");
sb.append("AND");
sb.append("TC.DO_FLAG");
sb.append("=");
sb.append("'20'");
sb.append("AND");
sb.append("TB.SYSLAST");
sb.append(">");
sb.append("0");
sb.append("AND");
sb.append("TB.DO_FLAG");
sb.append(">");
sb.append("'10'");
sb.append("AND");
sb.append("TB.DO_FLAG");
sb.append("<![CDATA[<]]>'30'");
sb.append("AND");
sb.append("TRUNC(SYSDATE)");
sb.append("-");
sb.append("TRUNC(TC.PROC_DATE)");
sb.append(">=");
sb.append("#{from_elapsed,");
sb.append("jdbcType=VARCHAR}");
sb.append("<if");
//sb.append("test="to_elapsed");
//sb.append("= '10'">");
sb.append("                 AND TRUNC(SYSDATE) - TRUNC(TC.PROC_DATE) <");
sb.append("[CDATA[<]]>=");
sb.append("#{to_elapsed,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("<if");
//sb.append("test="wh_code");
//sb.append("= ''">");
sb.append("                  AND TB.WH_CODE = #{wh_code, jdbcType=VARCHAR}");
sb.append("             </if>");
sb.append("            </if>");
sb.append("");
//sb.append("            <if test="use_code == ''">");
sb.append("              UNION ALL");
//sb.append("            </if>");
sb.append("");
//sb.append("            <if test='use_code == "0" or use_code == ""'>");
sb.append("              SELECT DISTINCT  TB.CLAIM_GB AS ORDER_GB,");
sb.append("                     TB.ORDER_NO,");
sb.append("                     TB.CUST_NO,");
sb.append("                     TB.RECEIVER_SEQ,");
sb.append("                     TRUNC(SYSDATE) - TRUNC(TC.PROC_DATE) AS DELAY_DAY,");
sb.append("                     TB.SYSLAST");
sb.append("                FROM TCLAIMDT     TB, TORDERPROC  TC");
sb.append("               WHERE TB.ORDER_NO    = TC.ORDER_NO");
sb.append("                 AND TB.ORDER_G_SEQ = TC.ORDER_G_SEQ");
sb.append("                 AND TB.ORDER_D_SEQ = TC.ORDER_D_SEQ");
sb.append("                 AND TB.ORDER_W_SEQ = TC.ORDER_W_SEQ");
sb.append("                 AND TB.CLAIM_GB    = '40'");
sb.append("                 AND TC.DO_FLAG     = '20'");
sb.append("                 AND TB.SYSLAST     > 0");
sb.append("                 AND TB.DO_FLAG     > '10'");
sb.append("                 AND TB.DO_FLAG     <");
sb.append("[CDATA[<]]>");
sb.append("'30'");
sb.append("AND");
sb.append("TRUNC(SYSDATE)");
sb.append("-");
sb.append("TRUNC(TC.PROC_DATE)");
//sb.append(">=");
//sb.append("#{from_elapsed,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("<if");
//sb.append("test="to_elapsed");
//sb.append("= '10'">");
sb.append("                 AND TRUNC(SYSDATE) - TRUNC(TC.PROC_DATE) <");
//sb.append("[CDATA[<]]>=");
//sb.append("#{to_elapsed,");
//sb.append("jdbcType=VARCHAR}");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="wh_code");
//sb.append("= ''">");
sb.append("                  AND TB.WH_CODE = #{wh_code, jdbcType=VARCHAR}");
//sb.append("             </if>");
//sb.append("            </if>");
sb.append("                     ) XA,");
sb.append("                     TRECEIVER XB,");
sb.append("                     TCUSTOMER XC");
sb.append("               WHERE XA.CUST_NO      = XB.CUST_NO");
sb.append("                  AND XA.RECEIVER_SEQ = XB.RECEIVER_SEQ");
sb.append("                    AND XA.CUST_NO      = XC.CUST_NO                 ;");
sb.append("                                                                  ");
sb.append("              SELECT /* outreport.xml : logistics.outreport.selectNonShipmentDetailforOrderDetail by NonShipmentDetailforOrderDetail */");
sb.append("                     TA.GOODS_CODE,");
sb.append("                   TB.GOODS_NAME,");
sb.append("                   TA.GOODSDT_CODE,");
sb.append("                   TA.GOODSDT_INFO,");
sb.append("                   TA.SYSLAST");
//sb.append("            <if test="order_gb == '10'">");
sb.append("              FROM TORDERDT TA,");
sb.append("            </if>");
//sb.append("            <if test="order_gb == '40'">");
sb.append("              FROM TCLAIMDT TA,");
sb.append("            </if>");
sb.append("                   TGOODS   TB");
sb.append("             WHERE TA.ORDER_NO = #{order_no , jdbcType=VARCHAR}");
sb.append("               AND TA.GOODS_CODE = TB.GOODS_CODE");
sb.append("               AND TA.DO_FLAG     > '10'");
sb.append("               AND TA.DO_FLAG     <");
sb.append("[CDATA[<]]>");
sb.append("'30'");
sb.append("AND");
sb.append("TA.SYSLAST");
sb.append(">");
sb.append("0");
sb.append("<if");
//sb.append("test="order_gb");
sb.append("==");
//sb.append("'40'">");
sb.append("AND");
sb.append("TA.CLAIM_GB");
sb.append("=");
sb.append("'40'");
sb.append("</if>");
sb.append("*/");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new ComboBox("Days Elapse"));
toolbar.addComponent(new ComboBox("~"));
toolbar.addComponent(new CheckBox("Order Type"));
toolbar.addComponent(new CheckBox("Order"));
toolbar.addComponent(new CheckBox("Change ReturN"));
toolbar.addComponent(new CheckBox("All"));



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
                new Header("Non Shipment Detail for Order (NonShipmentDetailforOrder)"
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
             BaseEntity.user,BaseEntity.pass,2,5);
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
