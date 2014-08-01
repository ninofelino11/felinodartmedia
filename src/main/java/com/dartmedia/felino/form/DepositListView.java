package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("DepositList")
public class DepositListView extends MVerticalLayout implements View {
//DepositListSvc data=new DepositListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Deposit amount <p:inputText /> ~ <p:inputText />**/
StringBuffer sb = new StringBuffer();
sb.append("    SELECT /* deposit.xml : custcenter.deposit.selectDepositList by DepositList */");
sb.append("                   A.CUST_NO        AS CUST_NO, ");
sb.append("                 C.RECEIVER       AS CUST_NAME, ");
sb.append("                 C.TEL,");
sb.append("                 A.USE_PB_DEPOSIT AS USE_PB_DEPOSIT, ");
sb.append("                 A.USE_DEPOSIT    AS USE_DEPOSIT ");
sb.append("            FROM TCUSTSYSTEM A, ");
sb.append("                 TRECEIVER   C ");
sb.append("           WHERE A.CUST_NO        = C.CUST_NO ");
sb.append("             AND C.DEFAULT_YN     = '1' ");
sb.append("             AND A.USE_PB_DEPOSIT BETWEEN TO_NUMBER(0) AND TO_NUMBER(1000000) ");
sb.append("             AND A.USE_PB_DEPOSIT > 0  ");
sb.append("           ORDER BY A.CUST_NO ;        ");
sb.append("       ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));


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
                new Header("Deposit List (DepositList)"
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
