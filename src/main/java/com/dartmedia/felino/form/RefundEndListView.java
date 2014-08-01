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
@CDIView("RefundEndList")
public class RefundEndListView extends MVerticalLayout implements View {
//RefundEndListSvc data=new RefundEndListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("SELECT /* refund.xml : custcenter.refund.selectRefundCompleteList by RefundEndList */");
sb.append("         R.REPAY_NO,");
sb.append("         R.RECEIPT_NO,");
sb.append("         R.CUST_NO,");
sb.append("         R.DO_FLAG,");
sb.append("         R.SETTLE_GB,");
sb.append("         R.CLAIM_CODE,");
sb.append("         R.BANK_CODE,");
sb.append("         CWARE_ENC_DEC(R.BANK_DEPOSIT_NO, 'd') AS BANK_DEPOSIT_NO,");
sb.append("         R.DEPOSITOR,");
sb.append("         R.REPAY_NOTE,");
sb.append("         R.REPAY_AMT,");
sb.append("         R.REAL_REPAY_AMT,");
sb.append("         R.REPAY_COMMISSION,");
sb.append("         TO_CHAR(R.REAL_DATE, 'YYYY/MM/DD HH24:MI:SS') AS REAL_DATE,");
sb.append("         TO_CHAR(R.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,");
sb.append("         R.INSERT_ID,");
sb.append("         TO_CHAR(R.TRANS_DATE, 'YYYY/MM/DD HH24:MI:SS') AS TRANS_DATE,");
sb.append("         R.TRANS_ID,");
sb.append("         TO_CHAR(R.END_DATE, 'YYYY/MM/DD') AS END_DATE,");
sb.append("         R.END_ID,");
sb.append("         C.CUST_NAME,");
sb.append("         B.BANK_NAME");
sb.append("          FROM TREFUND R, TCUSTOMER C, TBANK B");
sb.append("         WHERE R.CUST_NO = C.CUST_NO");
sb.append("           AND R.BANK_CODE = B.BANK_CODE");
sb.append("           AND R.DO_FLAG = 'procGb'");
sb.append("           AND R.TRANS_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD') ");
sb.append("           AND R.TRANS_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
sb.append("           AND R.BANK_CODE LIKE 'bankCode' || '%'");
sb.append("           AND R.SETTLE_GB LIKE 'settleGb' || '%'");
sb.append("         ORDER BY TO_CHAR(R.END_DATE, 'YYYY/MM/DD')   ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Proc. Step"));
toolbar.addComponent(new PopupDateField("Inquiry Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Refund Payment"));
toolbar.addComponent(new TextField("Bank"));


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
                new Header("Refund List (RefundEndList)"
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
