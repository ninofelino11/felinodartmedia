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
@CDIView("SafeStockManage")
public class SafeStockManageView extends MVerticalLayout implements View {
//SafeStockManageSvc data=new SafeStockManageSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Sale type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Item <p:inputText /> **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT *       ");
sb.append("          FROM (");
sb.append("                SELECT TC.WH_CODE");
sb.append("                , TA.SALE_GB");
sb.append("                     , TA.GOODS_CODE");
sb.append("                     , TA.GOODS_NAME");
sb.append("                     , TB.GOODSDT_CODE");
sb.append("                     , TB.GOODSDT_INFO");
sb.append("                     , DECODE(TD.GOODS_CODE, NULL, 'I', 'U') AS DATA_GB");
sb.append("                     , NVL(TD.SAFE_QTY,0) AS SAFE_QTY");
sb.append("                     , NVL(TD.AQTY, 0) AS REAL_AQTY");
sb.append("                     , TC.ORDER_QTY");
sb.append("                     , TC.OUT_PLAN_QTY");
sb.append("                     , (");
sb.append("                         SELECT NVL(SUM(BDT.BALJU_QTY), 0)");
sb.append("                           FROM TBALJUM  BM");
sb.append("                              , TBALJUDT BDT");
sb.append("                          WHERE BM.BALJU_NO      = BDT.BALJU_NO");
sb.append("                            AND BM.WH_CODE       = TC.WH_CODE");
sb.append("                            AND BDT.GOODS_CODE   = TC.GOODS_CODE");
sb.append("                            AND BDT.GOODSDT_CODE = TC.GOODSDT_CODE");
sb.append("                            AND BM.IN_END_YN     = '0'");
sb.append("                            AND BM.CANCEL_YN     = '0'");
sb.append("                       ) AS BALJU_QTY");
sb.append("                     , ( SELECT NVL(SUM(OM.DO_FLAG), 0)");
sb.append("                           FROM TWHMOVEOUTM   OM");
sb.append("                              , TWHMOVEOUTDT  ODT");
sb.append("                          WHERE OM.MOVE_OUT_SEQ = ODT.MOVE_OUT_SEQ");
sb.append("                            AND OM.DO_FLAG      < '40'");
sb.append("                            AND OM.WH_OUT_CODE  = TC.WH_CODE");
sb.append("                            AND ODT.GOODS_CODE  = TC.GOODS_CODE");
sb.append("                            AND ODT.GOODSDT_CODE= TC.GOODSDT_CODE");
sb.append("                       ) AS M_OUT_PLAN_AQTY ");
sb.append("                     , ( SELECT NVL(SUM(OUT_AQTY), 0) ");
sb.append("                           FROM TWHMOVEOUTM   OM");
sb.append("                              , TWHMOVEOUTDT  ODT");
sb.append("                          WHERE OM.MOVE_OUT_SEQ = ODT.MOVE_OUT_SEQ");
sb.append("                            AND OM.DO_FLAG      < '80'");
sb.append("                            AND OM.WH_IN_CODE   = TC.WH_CODE");
sb.append("                            AND ODT.GOODS_CODE  = TC.GOODS_CODE");
sb.append("                            AND ODT.GOODSDT_CODE= TC.GOODSDT_CODE");
sb.append("                       ) AS M_IN_PLAN_AQTY ");
sb.append("                  FROM TGOODS       TA");
sb.append("                     , TGOODSDT     TB");
sb.append("                     , TORDERSTOCK  TC");
sb.append("                     , TSTOCK       TD");
sb.append("                 WHERE TA.GOODS_CODE   = TB.GOODS_CODE");
sb.append("                   AND TB.GOODS_CODE   = TC.GOODS_CODE");
sb.append("                   AND TB.GOODSDT_CODE = TC.GOODSDT_CODE");
sb.append("                   AND TC.WH_CODE      = TD.WH_CODE(+)");
sb.append("                   AND TC.GOODS_CODE   = TD.GOODS_CODE(+)");
sb.append("                   AND TC.GOODSDT_CODE = TD.GOODSDT_CODE(+)");
sb.append("                     AND TC.WH_CODE    = '001'");
sb.append("                     AND TA.SALE_GB = '00'");
sb.append("/*                     AND TA.GOODS_CODE = '101800' */");
sb.append("               ) XA ");
sb.append("         WHERE 1=1  ");
//sb.append("/*         <if test='checkVal == "1"'> */");
sb.append("           AND XA.SAFE_QTY < XA.REAL_AQTY - ( XA.ORDER_QTY + XA.OUT_PLAN_QTY ) + BALJU_QTY - M_OUT_PLAN_AQTY + M_IN_PLAN_AQTY");
//sb.append("/*         </if> */");
sb.append("        ORDER BY WH_CODE, GOODS_CODE, GOODSDT_CODE          ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new Button("Print"));
toolbar.addComponent(new CheckBox("Safe Stock Target"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new Button("Pp Create"));
toolbar.addComponent(new Button("Wh Transfer"));


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
                new Header("Safe Stock Manage (SafeStockManage)"
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
