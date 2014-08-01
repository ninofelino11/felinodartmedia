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
@CDIView("WhWorkLoadReport")
public class WhWorkLoadReportView extends MVerticalLayout implements View {
//WhWorkLoadReportSvc data=new WhWorkLoadReportSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Item <p:inputText></p:inputText>                                        **/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* outreport.xml : logistics.outreport.selectWhWorkDayReport by WhWorkDayReport */");
sb.append("               B.WH_CODE,");
sb.append("               G.LGROUP,");
sb.append("               B.GOODS_CODE,");
sb.append("               G.GOODS_NAME,");
sb.append("               B.GOODSDT_CODE,");
sb.append("               D.GOODSDT_INFO,");
sb.append("               NVL(S.AQTY,0)   STOCK_QTY,");
sb.append("               NVL(P.CREATE_QTY,0) AS CREATE_QTY,");
sb.append("               NVL(S.AQTY,0)-NVL(P.CREATE_QTY,0) AS POSS_QTY,");
sb.append("               B.ORD_QTY,");
sb.append("               B.EXCH_QTY");
sb.append("        FROM (");
sb.append("                SELECT A.WH_CODE,");
sb.append("                       A.GOODS_CODE,");
sb.append("                       A.GOODSDT_CODE,");
sb.append("                       SUM(ORD_QTY) AS ORD_QTY,");
sb.append("                       SUM(EXCH_QTY) AS EXCH_QTY");
sb.append("                FROM (");
sb.append("                         SELECT WH_CODE,");
sb.append("                                GOODS_CODE,");
sb.append("                                GOODSDT_CODE,");
sb.append("                                SUM(SYSLAST)     AS ORD_QTY,");
sb.append("                                0                AS EXCH_QTY");
sb.append("                         FROM   TORDERDT");
sb.append("                         WHERE  DO_FLAG  <> '30'");
sb.append("                         AND    SYSLAST  > 0");
sb.append("                         AND    WH_CODE  = '001'");
sb.append("                         AND    GOODS_CODE = '100011'");
sb.append("                         GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE");
sb.append("                         UNION ALL");
sb.append("                         SELECT WH_CODE,");
sb.append("                                GOODS_CODE,");
sb.append("                                GOODSDT_CODE,");
sb.append("                                0                AS ORD_QTY,");
sb.append("                                SUM(SYSLAST)     AS EXCH_QTY");
sb.append("                         FROM   TCLAIMDT");
sb.append("                         WHERE  DO_FLAG    <> '30'");
sb.append("                         AND    SYSLAST    > 0");
sb.append("                         AND    CLAIM_GB   = '40'");
sb.append("                         AND    WH_CODE  = '001'");
sb.append("                         AND    GOODS_CODE = '100011'");
sb.append("                         GROUP BY WH_CODE, GOODS_CODE, GOODSDT_CODE");
sb.append("                         )A");
sb.append("                         GROUP BY A.WH_CODE,A.GOODS_CODE, A.GOODSDT_CODE");
sb.append("                )B,");
sb.append("         TGOODS   G,");
sb.append("         TGOODSDT D,");
sb.append("         TSTOCK   S,");
sb.append("         TSLIPCREATE P");
sb.append("        WHERE B.GOODS_CODE   = G.GOODS_CODE");
sb.append("        AND   B.GOODS_CODE   = D.GOODS_CODE");
sb.append("        AND   B.GOODSDT_CODE = D.GOODSDT_CODE");
sb.append("        AND   B.WH_CODE      = S.WH_CODE(+)");
sb.append("        AND   B.GOODS_CODE   = S.GOODS_CODE(+)");
sb.append("        AND   B.GOODSDT_CODE = S.GOODSDT_CODE(+)");
sb.append("        AND   B.WH_CODE      = P.WH_CODE(+)");
sb.append("        AND   B.GOODS_CODE   = P.GOODS_CODE(+)");
sb.append("        AND   B.GOODSDT_CODE = P.GOODSDT_CODE(+)");
sb.append("        ORDER BY B.WH_CODE, G.LGROUP,B.GOODS_CODE, B.GOODSDT_CODE ");
sb.append("");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new CheckBox("Ware House"));
toolbar.addComponent(new ComboBox("Pantos"));
toolbar.addComponent(new CheckBox("Item"));
toolbar.addComponent(new TextField(""));


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
                new Header("WhWorkLoadReport (WhWorkLoadReport)"
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
