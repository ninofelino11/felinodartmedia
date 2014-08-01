package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("SalesNoGoods")
public class SalesNoGoodsView extends MVerticalLayout implements View {
//SalesNoGoodsSvc data=new SalesNoGoodsSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Regterm <p:calendar id='fromDate'/> ~ <p:calendar id='toDate'/>Item <p:inputText/>//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from INDONESIA.TGOODS MD: <p:inputText/>//select md_code, md_name from tmdVendor: <p:inputText/>//select a.entp_code, a.entp_name, a.s_idno, b.entp_man_name from tenterprise a, tentpuser b where a.entp_code = b.entp_code**/
StringBuffer sb = new StringBuffer();
sb.append("select");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectSalesNoGoodsList");
sb.append("by");
sb.append("SalesNoGoods");
sb.append("*/");
sb.append("B.GOODS_CODE,");
sb.append("A.GOODS_CODE,");
sb.append("A.SET_YN,");
sb.append("B.GOODSDT_CODE,");
sb.append("B.GOODS_NAME,");
sb.append("B.GOODSDT_INFO,");
sb.append("''");
sb.append("SALE_NO_SEQ,");
sb.append("B.SALE_GB,");
sb.append("''");
sb.append("SALE_NO_CODE,");
sb.append("''");
sb.append("SALE_NO_NOTE,");
sb.append("TO_CHAR(B.MODIFY_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("AS");
sb.append("MODIFY_DATE,");
sb.append("B.MODIFY_ID,");
sb.append("'0'");
sb.append("AS");
sb.append("CHECK_YN");
sb.append("FROM");
sb.append("TGOODS");
sb.append("A,");
sb.append("TGOODSDT");
sb.append("B");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("B.GOODSDT_CODE");
sb.append(">");
sb.append("'000'");
sb.append("AND");
sb.append("A.MD_CODE");
sb.append("=");
sb.append("'0006'");
sb.append("AND");
sb.append("A.ENTP_CODE");
sb.append("=");
sb.append("'100003'");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100004'");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE('2014-06-13',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append("<");
sb.append("TO_DATE('2014-06-13',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("ORDER");
sb.append("BY");
sb.append("B.GOODS_CODE,");
sb.append("B.GOODSDT_CODE");
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
                new Header("Sales Suspend (SalesNoGoods)"
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
