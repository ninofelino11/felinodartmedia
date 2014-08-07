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
@CDIView("NonShipmentReport")
public class NonShipmentReportView extends MVerticalLayout implements View {
//NonShipmentReportSvc data=new NonShipmentReportSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Vendor <p:inputText></p:inputText>              L grp <p:inputText></p:inputText>              Order Type <p:selectOneRadio>   <f:selectItem itemValue="Order" /><f:selectItem itemValue="Change return" />  <f:selectItem itemValue="All" />  </p:selectOneRadio>                      **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("/* QUERYNYA KOMPLEKS JADI SAYA COPY APA ADANYA AJA YA");
sb.append("        SELECT /* outreport.xml : logistics.outreport.selectNonShipmentReport by NonShipmentReport */");
sb.append("               XA.GOODS_CODE,");
sb.append("               XA.GOODS_NAME,");
sb.append("               XA.GOODSDT_CODE,");
sb.append("               XA.GOODSDT_INFO,");
sb.append("               (SELECT NVL(SUM(XB.AQTY), 0) ");
sb.append("                  FROM TSTOCK XB");
sb.append("                 WHERE XB.GOODS_CODE = XA.GOODS_CODE");
sb.append("                   AND XB.GOODSDT_CODE = XA.GOODSDT_CODE");
//sb.append("                  <if test="wh_code ");
//sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("XB.WH_CODE");
sb.append("=");
sb.append("#{wh_code");
sb.append(",");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append(")");
sb.append("AS");
sb.append("AQTY,");
sb.append("SUM(XA.SYSLAST)");
sb.append("AS");
sb.append("DELAY_QTY,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("1,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY1,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("2,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY2,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("3,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY3,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("4,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY4,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("5,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY5,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("6,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY6,");
sb.append("SUM(DECODE(XA.DELAY_DAY,");
sb.append("7,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY7,");
sb.append("SUM(DECODE(SIGN(7");
sb.append("-");
sb.append("XA.DELAY_DAY),");
sb.append("-1,");
sb.append("XA.SYSLAST,");
sb.append("0))");
sb.append("AS");
sb.append("DELAY_QTY99");
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
sb.append("TB.WH_CODE,");
sb.append("TD.GOODS_CODE,");
sb.append("TD.GOODS_NAME,");
sb.append("TB.GOODSDT_CODE,");
sb.append("TB.GOODSDT_INFO,");
sb.append("TRUNC(SYSDATE)");
sb.append("-");
sb.append("TRUNC(TC.PROC_DATE)");
sb.append("AS");
sb.append("DELAY_DAY,");
sb.append("TB.SYSLAST");
sb.append("FROM");
sb.append("TORDERM");
sb.append("TA,");
sb.append("TORDERDT");
sb.append("TB,");
sb.append("TORDERPROC");
sb.append("TC,");
sb.append("TGOODS");
sb.append("TD,");
sb.append("TENTERPRISE");
sb.append("TV,");
sb.append("TGOODSKINDS");
sb.append("TK");
sb.append("WHERE");
sb.append("TA.ORDER_NO");
sb.append("=");
sb.append("TB.ORDER_NO");
sb.append("AND");
sb.append("TB.GOODS_CODE");
sb.append("=");
sb.append("TD.GOODS_CODE");
sb.append("AND");
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
sb.append("TD.ENTP_CODE");
sb.append("=");
sb.append("TV.ENTP_CODE");
sb.append("AND");
sb.append("TD.LMSD_CODE");
sb.append("=");
sb.append("TK.LMSD_CODE");
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
sb.append("<![CDATA[<]]>");
sb.append("'30'");
sb.append("<if");
//sb.append("test="wh_code");
//sb.append("= ''">");
sb.append("                        AND TB.WH_CODE = #{wh_code  , jdbcType=VARCHAR}");
sb.append("                  </if>");
//sb.append("                  <if test="lgroup_code ");
sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("TD.LGROUP");
sb.append("=");
sb.append("#{lgroup_code");
sb.append(",");
sb.append("jdbcType=VARCHAR}");
sb.append("AND");
sb.append("TK.LGROUP");
sb.append("=");
sb.append("TD.LGROUP");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="entp_code");
//sb.append("= ''">");
sb.append("                     AND TD.ENTP_CODE = #{entp_code  , jdbcType=VARCHAR}");
sb.append("                     AND TV.ENTP_CODE = TD.ENTP_CODE");
//sb.append("                  </if>");
//sb.append("             </if>");
//sb.append("               <if test='use_code == ""'>");
sb.append("                UNION ALL");
//sb.append("               </if>");
//sb.append("               <if test='use_code == "0" or use_code == ""'>");
sb.append("                 SELECT TB.WH_CODE,");
sb.append("                         TD.GOODS_CODE,");
sb.append("                        TD.GOODS_NAME,");
sb.append("                        TB.GOODSDT_CODE,");
sb.append("                        TB.GOODSDT_INFO,");
sb.append("                        TRUNC(SYSDATE) - TRUNC(TC.PROC_DATE) AS DELAY_DAY,");
sb.append("                        TB.SYSLAST");
sb.append("                   FROM TORDERM     TA,");
sb.append("                        TCLAIMDT     TB,");
sb.append("                        TORDERPROC  TC,");
sb.append("                        TGOODS      TD,");
sb.append("                        TENTERPRISE TV,");
sb.append("                        TGOODSKINDS TK");
sb.append("                  WHERE TA.ORDER_NO    = TB.ORDER_NO");
sb.append("                    AND TB.GOODS_CODE  = TD.GOODS_CODE");
sb.append("                    AND TB.ORDER_NO    = TC.ORDER_NO");
sb.append("                    AND TB.ORDER_G_SEQ = TC.ORDER_G_SEQ");
sb.append("                    AND TB.ORDER_D_SEQ = TC.ORDER_D_SEQ");
sb.append("                    AND TB.ORDER_W_SEQ = TC.ORDER_W_SEQ");
sb.append("                    AND TD.ENTP_CODE   = TV.ENTP_CODE");
sb.append("                    AND TD.LMSD_CODE   = TK.LMSD_CODE");
sb.append("                    AND TB.CLAIM_GB = '40'");
sb.append("                    AND TC.DO_FLAG = '20'");
sb.append("                    AND TB.SYSLAST > 0");
sb.append("                    AND TB.DO_FLAG > '10'");
sb.append("                    AND TB.DO_FLAG <");
//sb.append("[CDATA[<]]>");
sb.append("'30'");
//sb.append("<if");
//sb.append("test="wh_code");
//sb.append("= ''">");
sb.append("                          AND TB.WH_CODE = #{wh_code  , jdbcType=VARCHAR}");
//sb.append("                      </if>");
//sb.append("                      <if test="lgroup_code ");
//sb.append("=");
//sb.append("''">");
sb.append("AND");
sb.append("TD.LGROUP");
sb.append("=");
//sb.append("#{lgroup_code");
sb.append(",");
//sb.append("jdbcType=VARCHAR}");
sb.append("AND");
sb.append("TK.LGROUP");
sb.append("=");
sb.append("TD.LGROUP");
//sb.append("</if>");
//sb.append("<if");
//sb.append("test="entp_code");
//sb.append("= ''">");
sb.append("                       AND TD.ENTP_CODE = #{entp_code  , jdbcType=VARCHAR}");
sb.append("                    AND TV.ENTP_CODE = TD.ENTP_CODE");
//sb.append("                      </if>");
//sb.append("                      </if>) XA");
sb.append("            , TSTOCK XB");
sb.append("        WHERE XA.WH_CODE      = XB.WH_CODE(+)");
sb.append("          AND XA.GOODS_CODE   = XB.GOODS_CODE(+)");
sb.append("          AND XA.GOODSDT_CODE = XB.GOODSDT_CODE(+)");
sb.append("     GROUP BY XA.GOODS_CODE,              ");
sb.append("              XA.GOODS_NAME,");
sb.append("              XA.GOODSDT_CODE,");
sb.append("              XA.GOODSDT_INFO");
sb.append("     ORDER BY XA.GOODS_CODE,");
sb.append("              XA.GOODSDT_CODE");
//sb.append("*/ ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new TextField("L grp"));

toolbar.addComponent(new CheckBox("Order Type"));
toolbar.addComponent(new CheckBox("Order"));
toolbar.addComponent(new CheckBox("Change Return"));
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
                new Header("Non Shipment Report (NonShipmentReport)"
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
