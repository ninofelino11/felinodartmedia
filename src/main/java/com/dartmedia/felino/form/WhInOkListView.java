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
@CDIView("WhInOkList")
public class WhInOkListView extends MVerticalLayout implements View {
//WhInOkListSvc data=new WhInOkListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Shipment warehouse <p:selectOneListbox>  </p:selectOneListbox>   Shipment term <p:calendar></p:calendar>Inquiry type <p:selectOneListbox></p:selectOneListbox>                                            **/
StringBuffer sb = new StringBuffer();
sb.append("/* DETAIL */");
sb.append("           SELECT /* warehousemove.xml : logistics.warehousemove.selectWhInOkList by WhInOkList */ distinct");
sb.append("                     A.MOVE_IN_SEQ  AS MOVE_IN_SEQ,                        ");
sb.append("                  B.WH_IN_CODE   AS WH_IN_CODE,                         ");
sb.append("                  B.WH_OUT_CODE  AS WH_OUT_CODE,                           ");
sb.append("                  TO_CHAR(B.IN_DATE,     'YYYY/MM/DD') AS IN_DATE,      ");
sb.append("                  TO_CHAR(D.OUT_DATE,    'YYYY/MM/DD') AS OUT_DATE,        ");
sb.append("                  TO_CHAR(D.REQUEST_DATE,'YYYY/MM/DD') AS REQUEST_DATE, ");
sb.append("                  B.MOVE_OUT_SEQ AS MOVE_OUT_SEQ,                       ");
sb.append("                  A.GOODS_CODE   AS GOODS_CODE,                         ");
sb.append("                  G.GOODS_NAME   AS GOODS_NAME,                         ");
sb.append("                  A.GOODSDT_CODE AS GOODSDT_CODE,                       ");
sb.append("                  G.GOODSDT_INFO AS GOODSDT_INFO,                       ");
sb.append("                  C.OUT_AQTY     AS OUT_AQTY,                           ");
sb.append("                  C.OUT_BQTY     AS OUT_BQTY,                             ");
sb.append("                  A.IN_AQTY      AS IN_AQTY,                            ");
sb.append("                  A.IN_BQTY      AS IN_BQTY,");
sb.append("                  D.REQUEST_REASON AS REQUEST_REASON                                ");
sb.append("             FROM TWHMOVEINDT  A,                                        ");
sb.append("                  TWHMOVEINM   B,                                       ");
sb.append("                  TWHMOVEOUTDT C,                                       ");
sb.append("                  TWHMOVEOUTM  D,                                       ");
sb.append("                  TGOODSDT     G                                           ");
sb.append("            WHERE A.MOVE_IN_SEQ   = B.MOVE_IN_SEQ                       ");
sb.append("              AND B.MOVE_OUT_SEQ  = C.MOVE_OUT_SEQ                        ");
sb.append("              AND C.MOVE_OUT_SEQ  = D.MOVE_OUT_SEQ                      ");
sb.append("              AND A.GOODS_CODE    = G.GOODS_CODE                        ");
sb.append("              AND A.GOODSDT_CODE  = G.GOODSDT_CODE                      ");
sb.append("              AND A.GOODS_CODE    = C.GOODS_CODE                        ");
sb.append("              AND A.GOODSDT_CODE  = C.GOODSDT_CODE                       ");
sb.append("              AND B.WH_IN_CODE = '001'                                 ");
sb.append("              AND B.IN_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD')              ");
sb.append("                                AND TO_DATE('2013/11/05', 'YYYY/MM/DD')              ");
sb.append("           ORDER BY A.MOVE_IN_SEQ,A.GOODS_CODE,A.GOODSDT_CODE;");
sb.append(" ");
sb.append("          ");
sb.append("/* SUMMARY */");
sb.append("                     ");
sb.append("  SELECT /* warehousemove.xml : logistics.warehousemove.selectWhInOkListDetail by WhInOkList */");
sb.append("                   B.WH_IN_CODE   AS WH_IN_CODE,                 ");
sb.append("                      B.WH_OUT_CODE  AS WH_OUT_CODE,                ");
sb.append("                   A.GOODS_CODE   AS GOODS_CODE,                 ");
sb.append("                   G.GOODS_NAME   AS GOODS_NAME,                 ");
sb.append("                   A.GOODSDT_CODE AS GOODSDT_CODE,               ");
sb.append("                   G.GOODSDT_INFO AS GOODSDT_INFO,               ");
sb.append("                   SUM(A.IN_AQTY) AS IN_AQTY,                    ");
sb.append("                   SUM(A.IN_BQTY) AS IN_BQTY                     ");
sb.append("              FROM TWHMOVEINDT A,                                ");
sb.append("                   TWHMOVEINM  B,                                ");
sb.append("                   TGOODSDT    G                                 ");
sb.append("             WHERE A.MOVE_IN_SEQ  = B.MOVE_IN_SEQ                ");
sb.append("               AND A.GOODS_CODE   = G.GOODS_CODE                 ");
sb.append("               AND A.GOODSDT_CODE = G.GOODSDT_CODE               ");
sb.append("              AND B.WH_IN_CODE = '001'                                 ");
sb.append("              AND B.IN_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD')              ");
sb.append("                                AND TO_DATE('2013/11/05', 'YYYY/MM/DD')              ");
sb.append("            GROUP BY B.WH_IN_CODE,                               ");
sb.append("                     A.GOODS_CODE,                               ");
sb.append("                     G.GOODS_NAME,                               ");
sb.append("                     A.GOODSDT_CODE,                             ");
sb.append("                     G.GOODSDT_INFO,                             ");
sb.append("                     B.WH_OUT_CODE                               ");
sb.append("            ORDER BY B.WH_IN_CODE, A.GOODS_CODE, A.GOODSDT_CODE     ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Shipment Warehouse"));
toolbar.addComponent(new PopupDateField("Shipment Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new Button("Print"));
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new CheckBox("Indv.Query"));


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
                new Header("Transfer In Report (WhInOkList)"
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
