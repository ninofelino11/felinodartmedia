package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("WhTransferOkList")
public class WhTransferOkListView extends MVerticalLayout implements View {
//WhTransferOkListSvc data=new WhTransferOkListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Shipment warehouse <p:selectOneListbox>  </p:selectOneListbox>   Shipment term <p:calendar></p:calendar>Inquiry type <p:selectOneListbox></p:selectOneListbox>                                                                   **/
StringBuilder sb = new StringBuilder();
sb.append("       SELECT /* warehousemove.xml : logistics.warehousemove.selectWhTransferOkListHd by WhTransferOkList */");
sb.append("                   A.MOVE_OUT_SEQ AS MOVE_OUT_SEQ,                            ");
sb.append("                  B.WH_OUT_CODE  AS WH_OUT_CODE,                                 ");
sb.append("                  B.WH_IN_CODE   AS WH_IN_CODE,                                 ");
sb.append("                  TO_CHAR(B.REQUEST_DATE, 'YYYY/MM/DD') AS REQUEST_DATE,     ");
sb.append("                  TO_CHAR(B.OUT_DATE, 'YYYY/MM/DD')     AS OUT_DATE,         ");
sb.append("                  A.GOODS_CODE   AS GOODS_CODE,                                ");
sb.append("                  G.GOODS_NAME   AS GOODS_NAME,                                 ");
sb.append("                  A.GOODSDT_CODE AS GOODSDT_CODE,                               ");
sb.append("                  G.GOODSDT_INFO AS GOODSDT_INFO,                             ");
sb.append("                  A.OUT_AQTY     AS OUT_AQTY,                                ");
sb.append("                  A.OUT_BQTY     AS OUT_BQTY,                                ");
sb.append("                  NVL(H.IN_AQTY , 0) AS IN_AQTY,                             ");
sb.append("                  NVL(H.IN_BQTY , 0) AS IN_BQTY,                             ");
sb.append("                  A.OUT_AQTY - NVL(H.IN_AQTY, 0) AS D_AQTY,                     ");
sb.append("                  A.OUT_BQTY - NVL(H.IN_BQTY, 0) AS D_BQTY,");
sb.append("                  B.REQUEST_REASON AS REQUEST_REASON");
sb.append("             FROM TWHMOVEOUTDT A,                                               ");
sb.append("                  TWHMOVEOUTM  B,                                              ");
sb.append("                  TGOODSDT     G,                                             ");
sb.append("                 (SELECT AA.GOODS_CODE   AS GOODS_CODE,                        ");
sb.append("                         AA.GOODSDT_CODE AS GOODSDT_CODE,                      ");
sb.append("                         SUM(AA.IN_AQTY) AS IN_AQTY,                         ");
sb.append("                         SUM(AA.IN_BQTY) AS IN_BQTY,                         ");
sb.append("                         BB.MOVE_OUT_SEQ AS MOVE_OUT_SEQ                      ");
sb.append("                    FROM TWHMOVEINDT AA,                                     ");
sb.append("                         TWHMOVEINM  BB,                                         ");
sb.append("                         TWHMOVEOUTM CC                                        ");
sb.append("                   WHERE CC.MOVE_OUT_SEQ = BB.MOVE_OUT_SEQ                     ");
sb.append("                     AND AA.MOVE_IN_SEQ  = BB.MOVE_IN_SEQ                     ");
sb.append("                     AND CC.WH_OUT_CODE  ='001'                              ");
sb.append("                     AND CC.OUT_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD')          ");
sb.append("                                         AND TO_DATE('2013/11/30', 'YYYY/MM/DD')                    ");
sb.append("                GROUP BY AA.GOODS_CODE,                                      ");
sb.append("                         AA.GOODSDT_CODE,                                    ");
sb.append("                         BB.MOVE_OUT_SEQ                                     ");
sb.append("                  ) H                                                        ");
sb.append("             WHERE (A.MOVE_OUT_SEQ = H.MOVE_OUT_SEQ(+)                        ");
sb.append("               AND A.MOVE_OUT_SEQ = B.MOVE_OUT_SEQ                           ");
sb.append("               AND A.GOODS_CODE   = G.GOODS_CODE                             ");
sb.append("               AND A.GOODSDT_CODE = G.GOODSDT_CODE                           ");
sb.append("               AND A.GOODS_CODE   = H.GOODS_CODE(+)                          ");
sb.append("               AND A.GOODSDT_CODE = H.GOODSDT_CODE(+)                        ");
sb.append("               AND B.DO_FLAG      = '40'                                         ");
sb.append("               AND B.WH_OUT_CODE  = '001'                                         ");
sb.append("               AND B.OUT_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD')                       ");
sb.append("                                  AND TO_DATE('2013/11/30', 'YYYY/MM/DD') )                 ");
//sb.append(" /*        <if test='flag_gb == "2"'>");
sb.append("               AND (A.OUT_AQTY - NVL(H.IN_AQTY, 0)    > 0   ");
sb.append("                     OR A.OUT_BQTY - NVL(H.IN_BQTY, 0) > 0)     ");
sb.append("        </if>     */       ");
sb.append("             ORDER BY A.MOVE_OUT_SEQ,A.GOODS_CODE,A.GOODSDT_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Ware House"));
toolbar.addComponent(new PopupDateField("Shipment Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new Button("Print"));



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
                new Header("Transfer Out Report (WhTransferOkList)"
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
