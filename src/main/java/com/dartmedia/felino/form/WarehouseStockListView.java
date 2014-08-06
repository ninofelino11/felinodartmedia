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
@CDIView("WarehouseStockList")
public class WarehouseStockListView extends MVerticalLayout implements View {
//WarehouseStockListSvc data=new WarehouseStockListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Item <p:inputText />                   **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* stock.xml : logistics.stock.selectWarehouseStockList by WarehouseStockList */");
sb.append("                A.WH_CODE             WH_CODE,");
sb.append("                D.ENTP_CODE          ENTP_CODE,  ");
sb.append("                D.ENTP_NAME          ENTP_NAME,  ");
sb.append("                C.LGROUP             LGROUP,  ");
sb.append("                C.MGROUP             MGROUP,  ");
sb.append("                A.GOODS_CODE         GOODS_CODE,  ");
sb.append("                A.GOODSDT_CODE       GOODSDT_CODE,  ");
sb.append("                B.GOODS_NAME         GOODS_NAME,  ");
sb.append("                B.GOODSDT_INFO       GOODSDT_INFO,  ");
sb.append("                PR.BUY_PRICE         BUY_PRICE,  ");
sb.append("                A.AQTY               AQTY,  ");
sb.append("                A.BQTY               BQTY,  ");
sb.append("                 NVL((SELECT G.RACK_QTY ");
sb.append("                    FROM TRACK G,");
sb.append("                         TCONFIG H ");
sb.append("                   WHERE G.RACK_CODE = A.WH_CODE||H.VAL");
sb.append("                     AND G.WH_CODE = A.WH_CODE");
sb.append("                     AND G.GOODS_CODE = A.GOODS_CODE");
sb.append("                     AND G.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                     AND H.ITEM = 'DEF_OUT_BRACK'), 0) NQTY,");
sb.append("                 NVL((SELECT I.RACK_QTY ");
sb.append("                    FROM TRACK I,");
sb.append("                         TCONFIG J ");
sb.append("                   WHERE I.RACK_CODE = A.WH_CODE||J.VAL");
sb.append("                     AND I.WH_CODE = A.WH_CODE");
sb.append("                     AND I.GOODS_CODE = A.GOODS_CODE");
sb.append("                     AND I.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                     AND J.ITEM = 'DEF_OUT_BRACK_DAMAGE'), 0) DQTY,");
sb.append("                 NVL((SELECT K.RACK_QTY ");
sb.append("                    FROM TRACK K,");
sb.append("                         TCONFIG L ");
sb.append("                   WHERE K.RACK_CODE = A.WH_CODE||L.VAL");
sb.append("                     AND K.WH_CODE = A.WH_CODE");
sb.append("                     AND K.GOODS_CODE = A.GOODS_CODE");
sb.append("                     AND K.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                     AND L.ITEM = 'DEF_OUT_BRACK_LOST'), 0) LQTY,");
sb.append("                F.ORDER_QTY + F.OUT_PLAN_QTY     ORDER_QTY,  ");
sb.append("                C.SALE_GB            SALE_GB,  ");
sb.append("                E.MD_CODE            MD_CODE,  ");
sb.append("                E.MD_NAME            MD_NAME  ");
sb.append("           FROM TSTOCK       A,  ");
sb.append("                TGOODSDT     B,  ");
sb.append("                TGOODS       C,  ");
sb.append("                TENTERPRISE  D,  ");
sb.append("                TGOODSPRICE  PR, ");
sb.append("                TMD          E,  ");
sb.append("                TORDERSTOCK  F   ");
sb.append("          WHERE A.GOODS_CODE     = B.GOODS_CODE  ");
sb.append("            AND A.GOODSDT_CODE   = B.GOODSDT_CODE  ");
sb.append("            AND A.GOODS_CODE     = C.GOODS_CODE  ");
sb.append("            AND C.ENTP_CODE      = D.ENTP_CODE  ");
sb.append("            AND C.MD_CODE        = E.MD_CODE  ");
sb.append("            AND C.GOODS_CODE     = PR.GOODS_CODE  ");
sb.append("            AND PR.APPLY_DATE    =  ");
sb.append("                          ( SELECT MAX(PR1.APPLY_DATE)  ");
sb.append("                              FROM TGOODSPRICE  PR1    ");
sb.append("                             WHERE PR.GOODS_CODE    = PR1.GOODS_CODE  ");
sb.append("                               AND PR1.APPLY_DATE  <= SYSDATE    ");
sb.append("                          )    ");
//sb.append("            AND ( A.AQTY ");
//sb.append("=");
//sb.append("0");
//sb.append("OR");
//sb.append("A.BQTY");
//sb.append("= 0 )  ");
sb.append("            AND B.GOODS_CODE     = F.GOODS_CODE  ");
sb.append("            AND B.GOODSDT_CODE   = F.GOODSDT_CODE  ");
sb.append("            AND A.WH_CODE        = F.WH_CODE  ");
sb.append("            AND A.WH_CODE     = '001'  ");
sb.append("            AND C.LGROUP      like '%%'     ");
sb.append("            AND C.MGROUP      like '%%'      ");
sb.append("            AND C.ENTP_CODE   = '100002'    ");
sb.append("            AND C.BUY_MED     like '%%'     ");
sb.append("            AND C.BUY_MED     IN ('11','21')    ");
sb.append("            AND C.GOODS_CODE  like '%%'  ");
sb.append("              ORDER BY A.WH_CODE , A.GOODS_CODE , A.GOODSDT_CODE ASC         ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new ComboBox("Buy Method"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new TextField("L Grp"));
toolbar.addComponent(new TextField("MPromo Name"));


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
                new Header("Stock Report (WarehouseStockList)"
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
