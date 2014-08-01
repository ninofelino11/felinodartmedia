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
@CDIView("CashList")
public class CashListView extends MVerticalLayout implements View {
//CashListSvc data=new CashListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Pay receiving day <p:calendar /> ~ <p:calendar />Pay receiving bank <p:inputText />**/
StringBuffer sb = new StringBuffer();
sb.append("      SELECT /* cash.xml : custcenter.cash.selectCashList by CashList */");
sb.append("                 TO_CHAR(D.PROC_DATE,'yyyy/mm/dd') AS PROC_DATE, ");
sb.append("                   A.RECEIPT_NO,  ");
sb.append("                   A.ORDER_NO,    ");
sb.append("                   A.CUST_NO,     ");
sb.append("                   B.CUST_NAME,   ");
sb.append("                   C.BANK_NAME AS BANK_NAME,   ");
sb.append("                   CWARE_ENC_DEC(A.CARD_NO, 'd') AS CARD_NO,     ");
sb.append("                   A.DEPOSITOR,   ");
sb.append("                   A.QUEST_AMT ,  ");
sb.append("                   A.RECEIPT_AMT PROC_AMT, ");
sb.append("                   CASE WHEN A.QUEST_AMT    > A.RECEIPT_AMT THEN  A.QUEST_AMT - A.RECEIPT_AMT ELSE 0 END AS CHARGE, ");
sb.append("                   CASE WHEN A.QUEST_AMT < A.RECEIPT_AMT THEN  A.RECEIPT_AMT  - A.QUEST_AMT ELSE 0 END  AS OVER_AMT, ");
sb.append("                   E.CODE_NAME AS FLAG_NAME,   ");
sb.append("                   D.DO_FLAG      ");
sb.append("           FROM TORDERRECEIPTS      A, ");
sb.append("                   TCUSTOMER           B, ");
sb.append("                   TRECEIPTSBANK       C, ");
sb.append("                   TORDERRECEIPTSPROC  D, ");
sb.append("                   TCODE               E  ");
sb.append("          WHERE D.DO_FLAG        IN ('20','21')   ");
sb.append("            AND D.RECEIPT_NO     = A.RECEIPT_NO   ");
sb.append("            AND A.CUST_NO        = B.CUST_NO      ");
sb.append("            AND A.CARD_BANK_CODE = C.BANK_CODE    ");
sb.append("            AND A.BANK_SEQ       = C.BANK_SEQ     ");
sb.append("            AND E.CODE_LGROUP    = 'J015'         ");
sb.append("            AND E.CODE_MGROUP    = D.DO_FLAG      ");
sb.append("            AND A.SETTLE_GB      = '02'           ");
sb.append("            AND A.CARD_BANK_CODE LIKE '%%'     ");
sb.append("            AND D.PROC_DATE      BETWEEN TO_DATE('2014/01/01' || '000000', 'YYYY/MM/DD HH24MISS') AND ");
sb.append("            TO_DATE('2014/08/01' || '235959', 'YYYY/MM/DD HH24MISS')  ");
sb.append("       ORDER BY PROC_DATE DESC, A.RECEIPT_NO DESC  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Pay Receiving Day"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Pay Receiving Bank"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new Button("Ordering"));


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
                new Header("Money Transfer Report (CashList)"
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
