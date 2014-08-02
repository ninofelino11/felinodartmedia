package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("WarehouseStockList")
public class WarehouseStockListView extends MVerticalLayout implements View {
//WarehouseStockListSvc data=new WarehouseStockListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Item <p:inputText />                   **/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("stock.xml");
sb.append(":");
sb.append("logistics.stock.selectWarehouseStockList");
sb.append("by");
sb.append("WarehouseStockList");
sb.append("*/");
sb.append("A.WH_CODE");
sb.append("WH_CODE,");
sb.append("D.ENTP_CODE");
sb.append("ENTP_CODE,");
sb.append("D.ENTP_NAME");
sb.append("ENTP_NAME,");
sb.append("C.LGROUP");
sb.append("LGROUP,");
sb.append("C.MGROUP");
sb.append("MGROUP,");
sb.append("A.GOODS_CODE");
sb.append("GOODS_CODE,");
sb.append("A.GOODSDT_CODE");
sb.append("GOODSDT_CODE,");
sb.append("B.GOODS_NAME");
sb.append("GOODS_NAME,");
sb.append("B.GOODSDT_INFO");
sb.append("GOODSDT_INFO,");
sb.append("PR.BUY_PRICE");
sb.append("BUY_PRICE,");
sb.append("A.AQTY");
sb.append("AQTY,");
sb.append("A.BQTY");
sb.append("BQTY,");
sb.append("NVL((SELECT");
sb.append("G.RACK_QTY");
sb.append("FROM");
sb.append("TRACK");
sb.append("G,");
sb.append("TCONFIG");
sb.append("H");
sb.append("WHERE");
sb.append("G.RACK_CODE");
sb.append("=");
sb.append("A.WH_CODE||H.VAL");
sb.append("AND");
sb.append("G.WH_CODE");
sb.append("=");
sb.append("A.WH_CODE");
sb.append("AND");
sb.append("G.GOODS_CODE");
sb.append("=");
sb.append("A.GOODS_CODE");
sb.append("AND");
sb.append("G.GOODSDT_CODE");
sb.append("=");
sb.append("A.GOODSDT_CODE");
sb.append("AND");
sb.append("H.ITEM");
sb.append("=");
sb.append("'DEF_OUT_BRACK'),");
sb.append("0)");
sb.append("NQTY,");
sb.append("NVL((SELECT");
sb.append("I.RACK_QTY");
sb.append("FROM");
sb.append("TRACK");
sb.append("I,");
sb.append("TCONFIG");
sb.append("J");
sb.append("WHERE");
sb.append("I.RACK_CODE");
sb.append("=");
sb.append("A.WH_CODE||J.VAL");
sb.append("AND");
sb.append("I.WH_CODE");
sb.append("=");
sb.append("A.WH_CODE");
sb.append("AND");
sb.append("I.GOODS_CODE");
sb.append("=");
sb.append("A.GOODS_CODE");
sb.append("AND");
sb.append("I.GOODSDT_CODE");
sb.append("=");
sb.append("A.GOODSDT_CODE");
sb.append("AND");
sb.append("J.ITEM");
sb.append("=");
sb.append("'DEF_OUT_BRACK_DAMAGE'),");
sb.append("0)");
sb.append("DQTY,");
sb.append("NVL((SELECT");
sb.append("K.RACK_QTY");
sb.append("FROM");
sb.append("TRACK");
sb.append("K,");
sb.append("TCONFIG");
sb.append("L");
sb.append("WHERE");
sb.append("K.RACK_CODE");
sb.append("=");
sb.append("A.WH_CODE||L.VAL");
sb.append("AND");
sb.append("K.WH_CODE");
sb.append("=");
sb.append("A.WH_CODE");
sb.append("AND");
sb.append("K.GOODS_CODE");
sb.append("=");
sb.append("A.GOODS_CODE");
sb.append("AND");
sb.append("K.GOODSDT_CODE");
sb.append("=");
sb.append("A.GOODSDT_CODE");
sb.append("AND");
sb.append("L.ITEM");
sb.append("=");
sb.append("'DEF_OUT_BRACK_LOST'),");
sb.append("0)");
sb.append("LQTY,");
sb.append("F.ORDER_QTY");
sb.append("+");
sb.append("F.OUT_PLAN_QTY");
sb.append("ORDER_QTY,");
sb.append("C.SALE_GB");
sb.append("SALE_GB,");
sb.append("E.MD_CODE");
sb.append("MD_CODE,");
sb.append("E.MD_NAME");
sb.append("MD_NAME");
sb.append("FROM");
sb.append("TSTOCK");
sb.append("A,");
sb.append("TGOODSDT");
sb.append("B,");
sb.append("TGOODS");
sb.append("C,");
sb.append("TENTERPRISE");
sb.append("D,");
sb.append("TGOODSPRICE");
sb.append("PR,");
sb.append("TMD");
sb.append("E,");
sb.append("TORDERSTOCK");
sb.append("F");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODSDT_CODE");
sb.append("=");
sb.append("B.GOODSDT_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("C.GOODS_CODE");
sb.append("AND");
sb.append("C.ENTP_CODE");
sb.append("=");
sb.append("D.ENTP_CODE");
sb.append("AND");
sb.append("C.MD_CODE");
sb.append("=");
sb.append("E.MD_CODE");
sb.append("AND");
sb.append("C.GOODS_CODE");
sb.append("=");
sb.append("PR.GOODS_CODE");
sb.append("AND");
sb.append("PR.APPLY_DATE");
sb.append("=");
sb.append("(");
sb.append("SELECT");
sb.append("MAX(PR1.APPLY_DATE)");
sb.append("FROM");
sb.append("TGOODSPRICE");
sb.append("PR1");
sb.append("WHERE");
sb.append("PR.GOODS_CODE");
sb.append("=");
sb.append("PR1.GOODS_CODE");
sb.append("AND");
sb.append("PR1.APPLY_DATE");
sb.append("<=");
sb.append("SYSDATE");
sb.append(")");
sb.append("AND");
sb.append("(");
sb.append("A.AQTY");
sb.append("= 0 OR A.BQTY ");
sb.append("=");
sb.append("0");
sb.append(")");
sb.append("AND");
sb.append("B.GOODS_CODE");
sb.append("=");
sb.append("F.GOODS_CODE");
sb.append("AND");
sb.append("B.GOODSDT_CODE");
sb.append("=");
sb.append("F.GOODSDT_CODE");
sb.append("AND");
sb.append("A.WH_CODE");
sb.append("=");
sb.append("F.WH_CODE");
sb.append("AND");
sb.append("A.WH_CODE");
sb.append("=");
sb.append("'001'");
sb.append("AND");
sb.append("C.LGROUP");
sb.append("like");
sb.append("'%%'");
sb.append("AND");
sb.append("C.MGROUP");
sb.append("like");
sb.append("'%%'");
sb.append("AND");
sb.append("C.ENTP_CODE");
sb.append("=");
sb.append("'100002'");
sb.append("AND");
sb.append("C.BUY_MED");
sb.append("like");
sb.append("'%%'");
sb.append("AND");
sb.append("C.BUY_MED");
sb.append("IN");
sb.append("('11','21')");
sb.append("AND");
sb.append("C.GOODS_CODE");
sb.append("like");
sb.append("'%%'");
sb.append("ORDER");
sb.append("BY");
sb.append("A.WH_CODE");
sb.append(",");
sb.append("A.GOODS_CODE");
sb.append(",");
sb.append("A.GOODSDT_CODE");
sb.append("ASC");
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