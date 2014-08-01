package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CustDetail")
public class CustDetailView extends MVerticalLayout implements View {
//CustDetailSvc data=new CustDetailSvc();
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
sb.append("custcenter.customer.selectCustomer");
sb.append("by");
sb.append("CustDetail");
sb.append("*/");
sb.append("A.CUST_NO,");
sb.append("A.CUST_NAME,");
sb.append("A.CUST_NAME1,");
sb.append("A.CUST_NAME2,");
sb.append("A.CUST_NAME3,");
sb.append("A.SEX,");
sb.append("CWARE_ENC_DEC(A.RESIDENT_NO,");
sb.append("'d')");
sb.append("AS");
sb.append("RESIDENT_NO,");
sb.append("A.ENAME,");
sb.append("A.COMP_NAME,");
sb.append("A.COMP_DEPTNAME,");
sb.append("A.BIRTHDAY_YN,");
sb.append("A.BIRTHDAY,");
sb.append("A.WEDDING_YN,");
sb.append("TO_CHAR(A.WEDDING_DATE,'YYYYMMDD')");
sb.append("WEDDING_DATE,");
sb.append("A.JOB_CODE,");
sb.append("A.EMAIL_ADDR,");
sb.append("A.RECEIVE_METHOD,");
sb.append("A.COUNTRY,");
sb.append("A.SMS_YN,");
sb.append("A.ORDER_EMAIL_YN,");
sb.append("TO_CHAR(A.INSERT_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("INSERT_DATE,");
sb.append("'1'");
sb.append("TABLE_GB,");
sb.append("A.MEMB_NO,");
sb.append("A.MEM_ID,");
sb.append("A.PASSWD,");
sb.append("A.WITHDRAWAL_YN,");
sb.append("A.WITHDRAWAL_CODE,");
sb.append("A.WITHDRAWAL_CONTENT,");
sb.append("TO_CHAR(A.WITHDRAWAL_DATE,");
sb.append("'YYYYMMDD')");
sb.append("WITHDRAWAL_DATE,");
sb.append("A.NOMINATE_ID,");
sb.append("TO_CHAR(A.ID_INSERT_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("ID_INSERT_DATE,");
sb.append("A.PASSWD_HINT,");
sb.append("A.PASSWD_ANSWER,");
sb.append("A.EMAIL_YN,");
sb.append("A.EM_YN,");
sb.append("A.EM_NO,");
sb.append("B.USER_NAME");
sb.append("INSERT_NAME,");
sb.append("C.USER_NAME");
sb.append("MODIFY_NAME,");
sb.append("A.INSERT_ID,");
sb.append("A.MODIFY_ID,");
sb.append("A.EMAIL_FLAG,");
sb.append("TO_CHAR(A.MODIFY_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("MODIFY_DATE,");
sb.append("(SELECT");
sb.append("CONTENT");
sb.append("FROM");
sb.append("TCUSTSPINFO");
sb.append("WHERE");
sb.append("CUST_NO");
sb.append("=");
sb.append("A.CUST_NO");
sb.append("AND");
sb.append("TYPE");
sb.append("=");
sb.append("'70')");
sb.append("JOIN_MOTIVE");
sb.append("FROM");
sb.append("TCUSTOMER");
sb.append("A,");
sb.append("TUSER");
sb.append("B,");
sb.append("TUSER");
sb.append("C");
sb.append("WHERE");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("'201208007267'");
sb.append("AND");
sb.append("A.INSERT_ID");
sb.append("=");
sb.append("B.USER_ID(+)");
sb.append("AND");
sb.append("A.MODIFY_ID");
sb.append("=");
sb.append("C.USER_ID(+)");
sb.append("SELECT");
sb.append("/*");
sb.append("customer.xml");
sb.append(":");
sb.append("custcenter.customer.selectReceiver");
sb.append("by");
sb.append("CustDetail");
sb.append("*/");
sb.append("A.RECEIVER_GB,");
sb.append("A.DEFAULT_YN,");
sb.append("A.RECEIVER_DDD,");
sb.append("A.RECEIVER_TEL1,");
sb.append("A.RECEIVER_TEL2,");
sb.append("A.RECEIVER_TEL3,");
sb.append("A.TEL,");
sb.append("A.RECEIVER_HP1,");
sb.append("A.RECEIVER_HP2,");
sb.append("A.RECEIVER_HP3,");
sb.append("A.RECEIVER_HP,");
sb.append("A.RECEIVER_HP1_SE,");
sb.append("A.RECEIVER_HP2_SE,");
sb.append("A.RECEIVER_HP3_SE,");
sb.append("A.RECEIVER_POST,");
sb.append("FUN_ADD_POSTADDR(A.RECEIVER_POST,A.RECEIVER_POST_SEQ,'')");
sb.append("AS");
sb.append("COMP_ADDR,");
sb.append("A.RECEIVER_ADDR,");
sb.append("A.RECEIVER_SEQ,");
sb.append("A.RECEIVER_POST_SEQ,");
sb.append("A.CUST_NO,");
sb.append("A.RECEIVER,");
sb.append("A.RECEIVER1,");
sb.append("A.RECEIVER2,");
sb.append("A.RECEIVER3,");
sb.append("A.INSERT_ID,");
sb.append("TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("INSERT_DATE,");
sb.append("A.MODIFY_ID,");
sb.append("TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("MODIFY_DATE");
sb.append("FROM");
sb.append("TRECEIVER");
sb.append("A");
sb.append("WHERE");
sb.append("A.CUST_NO='201208007267' AND A.USE_YN='1'");
sb.append("AND");
sb.append("A.RECEIVER_GB");
sb.append("=");
sb.append("'00'");
sb.append("ORDER BY A.RECEIVER_GB,A.RECEIVER_SEQ,A.RECEIVER_POST");

sb.append("SELECT");
sb.append("/*");
sb.append("customer.xml");
sb.append(":");
sb.append("custcenter.customer.selectCustsystem");
sb.append("by");
sb.append("CustDetail");
sb.append("*/");
sb.append("A.CUST_NO,");
sb.append("A.CUST_GB,");
sb.append("A.MEMB_GB,");
sb.append("A.CUST_WARNING,");
sb.append("A.CUST_CHAR,");
sb.append("A.USE_DEPOSIT,");
sb.append("A.USE_PB_DEPOSIT,");
sb.append("A.DM_NO_GB,");
sb.append("TO_CHAR(A.DM_NO_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("DM_NO_DATE,");
sb.append("A.DM_NO_ID,");
sb.append("TO_CHAR(A.FIRST_ORDER_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("FIRST_ORDER_DATE,");
sb.append("A.FIRST_MSALE_GB,");
sb.append("A.TOT_ORDER_CNT,");
sb.append("A.TOT_ORDER_AMT,");
sb.append("A.TOT_CANCEL_CNT,");
sb.append("A.TOT_CANCEL_AMT,");
sb.append("A.TOT_RETURN_CNT,");
sb.append("A.TOT_RETURN_AMT,");
sb.append("B.USER_NAME");
sb.append("DM_NO_NAME,");
sb.append("A.MODIFY_ID,");
sb.append("A.DM_YN,");
sb.append("TO_CHAR(A.MODIFY_DATE,");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("MODIFY_DATE");
sb.append("FROM");
sb.append("TCUSTSYSTEM");
sb.append("A,");
sb.append("TUSER");
sb.append("B");
sb.append("WHERE");
sb.append("A.CUST_NO");
sb.append("=");
sb.append("'201208007267'");
sb.append("AND");
sb.append("A.DM_NO_ID");
sb.append("=");
sb.append("B.USER_ID(+)");
sb.append("AND");
sb.append("ROWNUM");
sb.append("=");
sb.append("1");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
MHorizontalLayout toolbar1 = new MHorizontalLayout();
//toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
toolbar1.addComponent(new Button("New"));
toolbar1.addComponent(new Button("Ordering"));
toolbar1.addComponent(new Button("Order"));
toolbar1.addComponent(new Button("Counsel Proccess"));
toolbar1.addComponent(new Button("Point"));
toolbar1.addComponent(new Button("Deposit"));
toolbar1.addComponent(new Button("Cust Info"));




toolbar.addComponent(new TextField("Custumer"));
toolbar.addComponent(new TextField("Custumer No"));
toolbar.addComponent(new TextField("Cust Name"));
ComboBox pilih=new ComboBox("My ComboBox");
pilih.addItem("male");
pilih.addItem("famale");
toolbar.addComponent(new TextField("Bhirtday"));

toolbar.addComponent(new TextField("Member No"));
toolbar.addComponent(new TextField("KTP/SIM/PAS"));
ComboBox pilih1=new ComboBox("Custumer Group");
pilih1.addItem("All");
pilih1.addItem("famale");

ComboBox pilih2=new ComboBox("Custumer Sort");
pilih2.addItem("Member");
pilih2.addItem("Non Member");
toolbar.addComponent(new TextField("Password Hint"));
toolbar.addComponent(new TextField("MemberID"));
toolbar.addComponent(new TextField("Password Answer"));
toolbar1.addComponent(new Button("Password"));
toolbar.addComponent(new TextField("Password    "));
toolbar.addComponent(new TextField("Member Join"));
toolbar.addComponent(new TextField("Recomendation ID"));

toolbar.addComponent(new TextField("Password Answer"));












toolbar.addComponent(pilih);
toolbar.addComponent(pilih2);
//-toolbar.addComponent(new ComboBox("My ComboBox"));

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
                new Header("Customer (CustDetail")
        );
        addComponents(toolmenu);
        addComponents(toolbar1);
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
