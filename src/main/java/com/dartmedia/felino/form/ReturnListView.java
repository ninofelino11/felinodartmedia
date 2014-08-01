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
@CDIView("ReturnList")
public class ReturnListView extends MVerticalLayout implements View {
//ReturnListSvc data=new ReturnListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /* refund.xml : custcenter.refund.selectReturnList by ReturnList */");
sb.append("        /*+ ORDERED USE_NL(A B C D E) */");
sb.append("         A.ORDER_NO,");
sb.append("         TO_CHAR(A.CLAIM_DATE, 'yyyy/mm/dd hh24:mi:ss') CLAIM_DATE,");
sb.append("         A.CUST_NO,");
sb.append("         B.CUST_NAME,");
sb.append("         A.GOODS_CODE,");
sb.append("         C.GOODS_NAME,");
sb.append("         A.SALE_PRICE,");
sb.append("         A.SYSLAST,");
sb.append("         A.SYSLAST_DC,");
sb.append("         A.SALE_PRICE * A.SYSLAST AS ORDER_AMT,");
sb.append("         A.SYSLAST_AMT,");
sb.append("         TO_CHAR(A.LAST_PROC_DATE, 'yyyy/mm/dd hh24:mi:ss') LAST_PROC_DATE,");
sb.append("         D.SETTLE_GB,");
sb.append("         A.ORDER_G_SEQ,");
sb.append("         A.ORDER_D_SEQ,");
sb.append("         A.ORDER_W_SEQ,");
sb.append("         E.INVOICE_NO");
sb.append("          FROM TCLAIMDT A, TCUSTOMER B, TGOODS C, TREFUNDINFO D, TRECEIPTREQ E");
sb.append("         WHERE A.CUST_NO = B.CUST_NO");
sb.append("           AND A.GOODS_CODE = C.GOODS_CODE");
sb.append("           AND A.CUST_NO = D.CUST_NO(+)");
sb.append("           AND A.CLAIM_DATE = D.CLAIM_DATE(+)");
sb.append("           AND A.ORDER_NO = E.ORDER_NO(+)");
sb.append("           AND A.LAST_PROC_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("           AND A.LAST_PROC_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
sb.append("           ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Return Complete Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Media"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new Button("Refund Processing"));
toolbar.addComponent(new Button("Ordering"));
toolbar.addComponent(new CheckBox("Order no."));




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
                new Header("Customer Refund (ReturnList)"
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
