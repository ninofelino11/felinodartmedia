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
@CDIView("RefundExpect")
public class RefundExpectView extends MVerticalLayout implements View {
//RefundExpectSvc data=new RefundExpectSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT /* refund.xml : custcenter.refund.selectRefundExpectList by RefundExpect */");
sb.append("                  '0'   AS REFUND ,");
sb.append("                  TO_CHAR(A.INSERT_DATE,'yyyy/mm/dd') AS INSERT_DATE,");
sb.append("                  A.REPAY_NO        ,");
sb.append("                  A.RECEIPT_NO      ,");
sb.append("                  C.ORDER_NO        ,");
sb.append("                  A.CUST_NO         ,");
sb.append("                  B.CUST_NAME       ,");
sb.append("                  A.SETTLE_GB       ,");
sb.append("                  TBANK_NAME(A.BANK_CODE) AS BANK_NAME,");
sb.append("                  CWARE_ENC_DEC(A.BANK_DEPOSIT_NO, 'd') AS BANK_DEPOSIT_NO ,");
sb.append("                  A.DEPOSITOR       ,");
sb.append("                  A.REPAY_AMT       ,");
sb.append("                  A.REAL_REPAY_AMT  ,");
sb.append("                  A.REPAY_COMMISSION,");
sb.append("                  TO_CHAR(A.TRANS_DATE,'yyyy/mm/dd') AS RANS_DATE,");
sb.append("                  A.REPAY_NOTE      ,");
sb.append("                  A.CLAIM_CODE      ,");
sb.append("                  A.BANK_CODE       ,");
sb.append("                  A.DO_FLAG         ,");
sb.append("                  A.INSERT_ID         ,");
sb.append("                  A.TRANS_ID");
sb.append("             FROM TREFUND        A,");
sb.append("                  TCUSTOMER      B,");
sb.append("                  TORDERRECEIPTS C");
sb.append("            WHERE A.CUST_NO     = B.CUST_NO");
sb.append("              AND A.DO_FLAG     = '10'");
sb.append("              AND A.RECEIPT_NO  = C.RECEIPT_NO(+)");
sb.append("              AND A.INSERT_DATE &gt;  TO_DATE('2014-01-01' ,'yyyy/mm/dd')");
sb.append("              AND A.INSERT_DATE &lt;  TO_DATE('2014-01-01' ,'yyyy/mm/dd') + 1");
sb.append("              AND A.SETTLE_GB   LIKE 'settle_gb'||'%'");
sb.append("              AND A.BANK_CODE   LIKE 'bank_code'||'%'         ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Refund Request"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new Button("Select"));
toolbar.addComponent(new Button("Deselect"));
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
                new Header("Refund Approve (RefundExpect)"
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
