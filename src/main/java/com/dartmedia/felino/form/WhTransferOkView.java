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
@CDIView("WhTransferOk")
public class WhTransferOkView extends MVerticalLayout implements View {
//WhTransferOkSvc data=new WhTransferOkSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Request no <p:inputText />                                       **/
StringBuffer sb = new StringBuffer();
sb.append("/*");
sb.append("MASIH");
sb.append("SALAH");
sb.append("*/");
sb.append("SELECT");
sb.append("/*");
sb.append("warehousemove.xml");
sb.append(":");
sb.append("logistics.warehousemove.selectWhTransferSearchDetail");
sb.append("by");
sb.append("WhTransferSearch");
sb.append("*/");
sb.append("OD.GOODS_CODE,");
sb.append("OD.GOODSDT_CODE,");
sb.append("G.GOODS_NAME,");
sb.append("G.GOODSDT_INFO,");
sb.append("OD.REQUEST_AQTY,");
sb.append("OD.REQUEST_BQTY,");
sb.append("NVL(OD.OUT_AQTY,");
sb.append("0)");
sb.append("AS");
sb.append("OUT_AQTY,");
sb.append("NVL(OD.OUT_BQTY,");
sb.append("0)");
sb.append("AS");
sb.append("OUT_BQTY,");
sb.append("NVL(ID.IN_AQTY");
sb.append(",");
sb.append("0)");
sb.append("AS");
sb.append("IN_AQTY,");
sb.append("NVL(ID.IN_BQTY");
sb.append(",");
sb.append("0)");
sb.append("AS");
sb.append("IN_BQTY,");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("FROM");
sb.append("TWHMOVEOUTDT");
sb.append("OD,");
sb.append("TGOODSDT");
sb.append("G,");
sb.append("(");
sb.append("SELECT");
sb.append("AA.GOODS_CODE,");
sb.append("AA.GOODSDT_CODE,");
sb.append("AA.MOVE_IN_SEQ,");
sb.append("SUM(AA.IN_AQTY)");
sb.append("AS");
sb.append("IN_AQTY,");
sb.append("SUM(AA.IN_BQTY)");
sb.append("AS");
sb.append("IN_BQTY,");
sb.append("BB.MOVE_OUT_SEQ");
sb.append("FROM");
sb.append("TWHMOVEINDT");
sb.append("AA,");
sb.append("TWHMOVEINM");
sb.append("BB");
sb.append("WHERE");
sb.append("AA.MOVE_IN_SEQ");
sb.append("=");
sb.append("BB.MOVE_IN_SEQ");
sb.append("AND");
sb.append("BB.MOVE_IN_SEQ");
sb.append("=");
sb.append("201212060215");
sb.append("GROUP");
sb.append("BY");
sb.append("AA.GOODS_CODE,");
sb.append("AA.GOODSDT_CODE,");
sb.append("AA.MOVE_IN_SEQ,");
sb.append("BB.MOVE_OUT_SEQ");
sb.append(")");
sb.append("ID");
sb.append("WHERE");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("=");
sb.append("Id.MOVE_OUT_SEQ(+)");
sb.append("AND");
sb.append("OD.GOODS_CODE");
sb.append("=");
sb.append("G.GOODS_CODE");
sb.append("AND");
sb.append("OD.GOODSDT_CODE");
sb.append("=");
sb.append("G.GOODSDT_CODE");
sb.append("AND");
sb.append("ID.GOODS_CODE(+)");
sb.append("=");
sb.append("OD.GOODS_CODE");
sb.append("AND");
sb.append("ID.GOODSDT_CODE(+)");
sb.append("=");
sb.append("OD.GOODSDT_CODE");
sb.append("AND");
sb.append("OD.MOVE_OUT_SEQ");
sb.append("=");
sb.append("201212060215");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------

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
                new Header("Wh Transfer Out (WhTransferOk)"
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
