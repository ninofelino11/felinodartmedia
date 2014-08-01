package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("WhTransferCancel")
public class WhTransferCancelView extends MVerticalLayout implements View {
//WhTransferCancelSvc data=new WhTransferCancelSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Move out no <p:inputText />                                                     Move order date <p:calendar></p:calendar>**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("warehousemove.xml");
sb.append(":");
sb.append("logistics.warehousemove.selectWhTransferCancelHd");
sb.append("by");
sb.append("WhTransferCancel");
sb.append("*/");
sb.append("MOVE_OUT_SEQ,");
sb.append("WH_IN_CODE,");
sb.append("WH_OUT_CODE,");
sb.append("TO_CHAR(REQUEST_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("REQUEST_DATE,");
sb.append("REQUEST_REASON,");
sb.append("CANCEL_REASON,");
sb.append("DO_FLAG,");
sb.append("'0'");
sb.append("AS");
sb.append("CANCEL_YN");
sb.append("FROM");
sb.append("TWHMOVEOUTM");
sb.append("WHERE");
sb.append("1");
sb.append("=");
sb.append("1");
sb.append("/*");
sb.append("<if");
sb.append("test='request_no");
sb.append("= null and request_no ");
sb.append("=");
//sb.append("""'>");
sb.append("AND");
sb.append("MOVE_OUT_SEQ");
sb.append("=");
sb.append("#{request_no,");
sb.append("jdbcType=VARCHAR}");
sb.append("</if>");
sb.append("<if");
sb.append("test='request_date_fr");
sb.append("= null and request_date_fr ");
sb.append("=");
//sb.append("""'>");
sb.append("*/");
sb.append("AND");
sb.append("REQUEST_DATE");
sb.append(">=");
sb.append("TO_DATE('2013/11/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("REQUEST_DATE");
sb.append("<");
sb.append("TO_DATE('2013/11/30',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("DO_FLAG");
sb.append("IN");
sb.append("('15')");
sb.append("/*");
sb.append("</if>");
sb.append("*/");
sb.append("ORDER");
sb.append("BY");
sb.append("MOVE_OUT_SEQ");
sb.append("DESC,");
sb.append("WH_OUT_CODE");
sb.append("SELECT");
sb.append("/*");
sb.append("warehousemove.xml");
sb.append(":");
sb.append("logistics.warehousemove.selectWhTransferCancel");
sb.append("by");
sb.append("WhTransferCancel");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("AS");
sb.append("GOODS_CODE,");
sb.append("A.GOODSDT_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("B.GOODSDT_INFO,");
sb.append("A.REQUEST_AQTY,");
sb.append("A.REQUEST_BQTY");
sb.append("FROM");
sb.append("TWHMOVEOUTDT");
sb.append("A,");
sb.append("TGOODSDT");
sb.append("B");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODSDT_CODE");
sb.append("=");
sb.append("B.GOODSDT_CODE");
sb.append("AND");
sb.append("A.MOVE_OUT_SEQ");
sb.append("=");
sb.append("201311040023");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));


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
                new Header("Wh Transfer Order Cancel (WhTransferCancel)"
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
