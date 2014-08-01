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
@CDIView("WarehouseStockItemList")
public class WarehouseStockItemListView extends MVerticalLayout implements View {
//WarehouseStockItemListSvc data=new WarehouseStockItemListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Buy method <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Item <p:inputText />                            **/
StringBuffer sb = new StringBuffer();
sb.append("  SELECT /* stock.xml : logistics.stock.selectWarehouseStockItemList by WarehouseStockItemList */");
sb.append("             D.ENTP_CODE AS ENTP_CODE,");
sb.append("             D.ENTP_NAME AS ENTP_NAME,");
sb.append("             C.LGROUP AS LGROUP,");
sb.append("             C.MGROUP AS MGROUP,");
sb.append("             A.GOODS_CODE GOODS_CODE,");
sb.append("             A.GOODSDT_CODE GOODSDT_CODE,");
sb.append("             B.GOODS_NAME GOODS_NAME,");
sb.append("             B.GOODSDT_INFO GOODSDT_INFO,");
sb.append("             PR.BUY_PRICE BUY_PRICE,");
sb.append("             SUM(A.AQTY ) AS AQTY ,");
sb.append("             SUM(A.BQTY ) AS BQTY ,");
sb.append("             SUM(NVL((SELECT G.RACK_QTY");
sb.append("                   FROM TRACK G, TCONFIG H");
sb.append("                  WHERE G.RACK_CODE = A.WH_CODE || H.VAL");
sb.append("                    AND G.WH_CODE = A.WH_CODE");
sb.append("                    AND G.GOODS_CODE = A.GOODS_CODE");
sb.append("                    AND G.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                    AND H.ITEM = 'DEF_OUT_BRACK'),");
sb.append("                 0)) NQTY,");
sb.append("             SUM(NVL((SELECT I.RACK_QTY");
sb.append("                   FROM TRACK I, TCONFIG J");
sb.append("                  WHERE I.RACK_CODE = A.WH_CODE || J.VAL");
sb.append("                    AND I.WH_CODE = A.WH_CODE");
sb.append("                    AND I.GOODS_CODE = A.GOODS_CODE");
sb.append("                    AND I.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                    AND J.ITEM = 'DEF_OUT_BRACK_DAMAGE'),");
sb.append("                 0)) DQTY,");
sb.append("             SUM(NVL((SELECT K.RACK_QTY");
sb.append("                   FROM TRACK K, TCONFIG L");
sb.append("                  WHERE K.RACK_CODE = A.WH_CODE || L.VAL");
sb.append("                    AND K.WH_CODE = A.WH_CODE");
sb.append("                    AND K.GOODS_CODE = A.GOODS_CODE");
sb.append("                    AND K.GOODSDT_CODE = A.GOODSDT_CODE");
sb.append("                    AND L.ITEM = 'DEF_OUT_BRACK_LOST'),");
sb.append("                 0)) LQTY,");
sb.append("             SUM(F.ORDER_QTY + F.OUT_PLAN_QTY) AS ORDER_QTY,");
sb.append("             C.SALE_GB AS SALE_GB,");
sb.append("             E.MD_CODE AS MD_CODE,");
sb.append("             E.MD_NAME AS MD_NAME");
sb.append("              FROM TSTOCK      A,");
sb.append("                   TGOODSDT    B,");
sb.append("                   TGOODS      C,");
sb.append("                   TENTERPRISE D,");
sb.append("                   TGOODSPRICE PR,");
sb.append("                   TMD         E,");
sb.append("                   TORDERSTOCK F");
sb.append("             WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("               AND A.GOODSDT_CODE = B.GOODSDT_CODE");
sb.append("               AND A.GOODS_CODE = C.GOODS_CODE");
sb.append("               AND C.ENTP_CODE = D.ENTP_CODE");
sb.append("               AND C.MD_CODE = E.MD_CODE");
sb.append("               AND C.GOODS_CODE = PR.GOODS_CODE");
sb.append("               AND PR.APPLY_DATE = (SELECT MAX(PR1.APPLY_DATE)");
sb.append("                                      FROM TGOODSPRICE PR1");
sb.append("                                     WHERE PR.GOODS_CODE = PR1.GOODS_CODE");
sb.append("                                       AND PR1.APPLY_DATE <= SYSDATE  )");
sb.append("               AND (A.AQTY ");
sb.append("=");
sb.append("0");
sb.append("OR");
sb.append("A.BQTY");
sb.append("= 0)");
sb.append("               AND B.GOODS_CODE = F.GOODS_CODE");
sb.append("               AND B.GOODSDT_CODE = F.GOODSDT_CODE");
sb.append("               AND A.WH_CODE = F.WH_CODE");
sb.append("               AND C.LGROUP      like '%%'      ");
sb.append("               AND C.MGROUP      like '%%'      ");
sb.append("               AND C.ENTP_CODE   = '100002'     ");
sb.append("               AND C.BUY_MED     like '%%'     ");
sb.append("               AND C.BUY_MED     IN ('11','21')    ");
sb.append("               AND C.GOODS_CODE  like '%%' ");
sb.append("          GROUP BY D.ENTP_CODE , D.ENTP_NAME, C.LGROUP , C.MGROUP, A.GOODS_CODE , A.GOODSDT_CODE ,  B.GOODS_NAME ,  B.GOODSDT_INFO ,PR.BUY_PRICE ,");
sb.append("                  C.SALE_GB ,  E.MD_CODE , E.MD_NAME");
sb.append("          ORDER BY  A.GOODS_CODE, A.GOODSDT_CODE ASC ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Buy Method"));
toolbar.addComponent(new TextField("L Grp"));
toolbar.addComponent(new TextField("M Grp"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new TextField("Item"));


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
                new Header("Stock Item Report (WarehouseStockItemList)"
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
