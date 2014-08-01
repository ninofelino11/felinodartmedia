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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("EntpList")
public class EntpListView extends MVerticalLayout implements View {
//EntpListSvc data=new EntpListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Reg Term <p:calendar id='from_date' /> ~ <p:calendar id='to_date'/>
Vendor <p:inputText id='entp_code' />
//select a.entp_code, a.entp_name, a.s_idno, b.entp_man_name from tenterprise a, tentpuser b where a.entp_code = b.entp_code
Deal <p:selectOneMenu id='close_yn'>
</p:selectOneMenu>
//select code_mgroup, code_name from tcode where code_lgroup = 'B135'
Vendor Type <p:inputText/>
//select * from tcode where code_lgroup = 'B001'
**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("entp.xml");
sb.append(":");
sb.append("manage.entp.selectEntpDetailList");
sb.append("by");
sb.append("EntpList");
sb.append("*/");
sb.append("A.ENTP_CODE");
sb.append("A.ENTP_NAME");
sb.append("A.ENTP_GUBUN");
sb.append("TO_CHAR(A.INSERT_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("as");
sb.append("INSERT_DATE");
sb.append("A.WORK_TYPE");
sb.append("A.WORK_KIND");
sb.append("A.S_IDNO");
sb.append("to_char(A.CLOSE_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("as");
sb.append("CLOSE_DATE");
sb.append("A.DISHONOR_YN");
sb.append("A.CLOSE_REASON");
sb.append("NVL(A.OWNER_NAME");
sb.append("B.ENTP_MAN_NAME)");
sb.append("as");
sb.append("OWNER_NAME");
sb.append("B.ENTP_MAN_NAME");
sb.append("A.ENTP_DDD");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("A.ENTP_TEL1");
sb.append("||");
sb.append("'-'");
sb.append("||");
sb.append("A.ENTP_TEL2");
sb.append("as");
sb.append("TEL");
sb.append("RTRIM(A.PAYMENT_TERM");
sb.append("'D')");
sb.append("AS");
sb.append("PAYMENT_TERM");
sb.append("(SELECT");
sb.append("BA.BANK_NAME");
sb.append("FROM");
sb.append("TENTPACCOUNT");
sb.append("EN");
sb.append("TBANK");
sb.append("BA");
sb.append("WHERE");
sb.append("EN.USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("EN.DEFAULT_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("EN.ENTP_CODE");
sb.append("=");
sb.append("A.ENTP_CODE");
sb.append("AND");
sb.append("EN.BANK_CODE");
sb.append("=");
sb.append("BA.BANK_CODE)");
sb.append("AS");
sb.append("BANK_NAME");
sb.append("(SELECT");
sb.append("CWARE_ENC_DEC(ACCOUNT_NO");
sb.append("'D')");
sb.append("FROM");
sb.append("TENTPACCOUNT");
sb.append("WHERE");
sb.append("USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("DEFAULT_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("ENTP_CODE");
sb.append("=");
sb.append("A.ENTP_CODE)");
sb.append("AS");
sb.append("ACCOUNT_NO");
sb.append("(SELECT");
sb.append("BRANCH_NAME");
sb.append("FROM");
sb.append("TENTPACCOUNT");
sb.append("WHERE");
sb.append("USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("DEFAULT_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("ENTP_CODE");
sb.append("=");
sb.append("A.ENTP_CODE)");
sb.append("AS");
sb.append("BRANCH_NAME");
sb.append("FROM");
sb.append("TENTERPRISE");
sb.append("A");
sb.append("TENTPUSER");
sb.append("B");
sb.append("WHERE");
sb.append("A.ENTP_CODE");
sb.append("=");
sb.append("B.ENTP_CODE(+)");
sb.append("AND");
sb.append("B.DEFAULT_YN(+)");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("A.ENTP_CODE");
sb.append("like");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE('2013-07-01'");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.INSERT_DATE");
sb.append("<");
sb.append("TO_DATE('2014-07-01'");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("A.ENTP_GUBUN");
sb.append("LIKE");
sb.append("'1'");
sb.append("||");
sb.append("'%'");
sb.append("//kalau");
sb.append("id");
sb.append("close_yn==2");
sb.append("AND");
sb.append("(");
sb.append("A.DISHONOR_YN");
sb.append("=");
sb.append("'2'");
sb.append("or");
sb.append("A.CLOSE_DATE");
sb.append("<=");
sb.append("SYSDATE");
sb.append(")");
sb.append("//kalau");
sb.append("id");
sb.append("close_yn");
sb.append("<>");
sb.append("''");
sb.append("AND");
sb.append("(");
sb.append("A.CLOSE_DATE");
sb.append("IS");
sb.append("NULL");
sb.append("OR");
sb.append("A.CLOSE_DATE");
sb.append("&gt;");
sb.append("SYSDATE");
sb.append(")");
sb.append("AND");
sb.append("A.DISHONOR_YN");
sb.append("=");
sb.append("'1';");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
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
                new Header("Vendor List (EntpList")
        );
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
