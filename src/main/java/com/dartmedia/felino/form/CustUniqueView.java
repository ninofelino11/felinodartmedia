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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CustUnique")
public class CustUniqueView extends MVerticalLayout implements View {
//CustUniqueSvc data=new CustUniqueSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Customer<p:inputtext/>**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("customer.xml");
sb.append(":");
sb.append("custcenter.customer.selectCustUnique2");
sb.append("by");
sb.append("CustUnique");
sb.append("*/");
sb.append("TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD')");
sb.append("AS");
sb.append("INSERT_DATE,");
sb.append("A.MEM_ID,");
sb.append("A.CUST_NAME,");
sb.append("B.CANCEL_YN,");
sb.append("B.RETURN_YN,");
sb.append("B.SEND_DATE,");
sb.append("DECODE(B.SEND_DATE,NULL,");
sb.append("C.CODE_NAME,");
sb.append("D.CODE_NAME)");
sb.append("AS");
sb.append("SAMPLE_RESULT");
sb.append("FROM");
sb.append("TCUSTOMER");
sb.append("A,");
sb.append("TSAMPLEREQM");
sb.append("B,");
sb.append("TCODE");
sb.append("C,");
sb.append("TCODE");
sb.append("D");
sb.append("WHERE");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("B.CUST_NO(+)");
sb.append("AND");
sb.append("(");
sb.append("A.NOMINATE_ID");
sb.append("=");
sb.append("(SELECT");
sb.append("MEM_ID");
sb.append("FROM");
sb.append("TCUSTOMER");
sb.append("WHERE");
sb.append("CUST_NO");
sb.append("=");
sb.append("'201402059961')");
sb.append("OR");
sb.append("A.NOMINATE_ID");
sb.append("=");
sb.append("(SELECT");
sb.append("MEMB_NO");
sb.append("FROM");
sb.append("TCUSTOMER");
sb.append("WHERE");
sb.append("CUST_NO");
sb.append("=");
sb.append("'201402059961'))");
sb.append("AND");
sb.append("B.SAMPLEREQ_TYPE(+)");
sb.append("=");
sb.append("'10'");
sb.append("AND");
sb.append("B.ADD_YN(+)");
sb.append("=");
sb.append("'0'");
sb.append("AND");
sb.append("B.CANCEL_CODE");
sb.append("=");
sb.append("C.CODE_MGROUP(+)");
sb.append("AND");
sb.append("C.CODE_LGROUP(+)");
sb.append("=");
sb.append("'C068'");
sb.append("AND");
sb.append("B.RETURN_CODE");
sb.append("=");
sb.append("D.CODE_MGROUP(+)");
sb.append("AND");
sb.append("D.CODE_LGROUP(+)");
sb.append("=");
sb.append("'C301'");
sb.append("SELECT");
sb.append("/*");
sb.append("customer.xml");
sb.append(":");
sb.append("custcenter.customer.selectCustUnique3");
sb.append("by");
sb.append("CustUnique");
sb.append("*/");
sb.append("A.CARD_BANK_CODE,");
sb.append("B.BANK_NAME,");
sb.append("CWARE_ENC_DEC(A.CARD_NO,");
sb.append("'d')");
sb.append("AS");
sb.append("CARD_NO,");
sb.append("A.DEPOSITOR,");
sb.append("A.FAMILY_GB,");
sb.append("A.USE_YN,");
sb.append("A.SETTLE_SEQ,");
sb.append("A.CUST_NO,");
sb.append("A.SETTLE_GB");
sb.append("FROM");
sb.append("TCUSTSETTLE");
sb.append("A,");
sb.append("TBANK");
sb.append("B");
sb.append("WHERE");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("'201402059961'");
sb.append("AND");
sb.append("A.CARD_BANK_CODE");
sb.append("=");
sb.append("B.BANK_CODE");
sb.append("AND");
sb.append("A.SETTLE_GB");
sb.append("=");
sb.append("'52'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MVerticalLayout isicontents=new MVerticalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
toolbar.addComponent(new TextField("Custumer"));
toolbar.addComponent(new TextField(""));

//-toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new Button("Custumer Specific detail"));
toolbar.addComponent(new Button("Coupon Grant"));

//-ComboBox pilih1=new ComboBox("Custumer Group");
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
                new Header("Customer Information (CustUnique")
        );
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Application Type", String.class,  null);
table.addContainerProperty("Apply Date", String.class,  null);
table.addContainerProperty("Before Level", String.class,  null);

MTable table1=new MTable("Custumer Bank List");
table1.addContainerProperty("No", String.class,  null);
table1.addContainerProperty("Bank Code", String.class,  null);
table1.addContainerProperty("Bank Name", String.class,  null);
table1.addContainerProperty("Account No", String.class,  null);
table1.addContainerProperty("Ac Payer", String.class,  null);
table1.addContainerProperty("Type", String.class,  null);
table1.addContainerProperty("Use CategORY", String.class,  null);




//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
   isicontents.addComponents(table1);
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
