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
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("EntpInput")
public class EntpInputView extends MVerticalLayout implements View {
//EntpInputSvc data=new EntpInputSvc();
    @PostConstruct
 
    public void initComponent() {

/**Vendor <p:inputText />
//select a.entp_code, a.entp_name, a.s_idno, b.entp_man_name from tenterprise a, tentpuser b where a.entp_code = b.entp_code
**/
StringBuffer sb = new StringBuffer();
sb.append("select /*entp.xml:manage.entp.selectEntpList by EntpInput EntpList */");
sb.append("A.ENTP_CODE,A.ENTP_NAME,A.ENTP_GUBUN,A.S_IDNO,A.WORK_TYPE");
sb.append("A.WORK_KIND");
sb.append("A.ENTP_POST");
sb.append("A.ENTP_POST_SEQ");
sb.append("A.ENTP_ADDR");
sb.append("A.ENTP_DDD");
sb.append("A.ENTP_TEL1");
sb.append("A.ENTP_TEL2");
sb.append("A.ENTP_TEL3");
sb.append("A.ENTP_FAX1");
sb.append("A.ENTP_FAX2");
sb.append("A.ENTP_FAX3");
sb.append("A.OWNER_NAME");
sb.append("to_char(A.FIRST_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("as");
sb.append("FIRST_DATE");
sb.append("A.DISHONOR_YN");
sb.append("B.PASSWORD");
sb.append("AS");
sb.append("SECRET_NO");
sb.append("to_char(A.CLOSE_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("as");
sb.append("CLOSE_DATE");
sb.append("A.CLOSE_REASON");
sb.append("to_char(A.INSERT_DATE)");
sb.append("as");
sb.append("INSERT_DATE");
sb.append("A.INSERT_ID");
sb.append("to_char(A.MODIFY_DATE)");
sb.append("as");
sb.append("MODIFY_DATE");
sb.append("A.MODIFY_ID");
sb.append("A.ETC");
sb.append("A.EMAIL_ADDR");
sb.append("fun_add_postaddr(A.ENTP_POST");
sb.append("A.ENTP_POST_SEQ");
sb.append("'')");
sb.append("as");
sb.append("COMP_ADDR");
sb.append("NVL(A.TAX_RATE");
sb.append("0)");
sb.append("AS");
sb.append("TAX_RATE");
sb.append("A.DELY_GB");
sb.append("A.ACCOUNT_GB");
sb.append("A.FOREIGN_YN");
sb.append("A.SAP_VENDOR_CODE");
sb.append("RTRIM(A.PAYMENT_TERM");
sb.append("'D')");
sb.append("AS");
sb.append("PAYMENT_TERM");
sb.append("from");
sb.append("TENTERPRISE");
sb.append("A");

sb.append("TSCMUSER");
sb.append("B");
sb.append("where");
sb.append("A.ENTP_CODE");
sb.append("=");
sb.append("B.ENTP_CODE");
sb.append("and");
sb.append("B.USER_ID");
sb.append("=");
sb.append("'E'||B.ENTP_CODE");
sb.append("and");
sb.append("A.ENTP_CODE");
sb.append("like");
sb.append("'100004'");
sb.append("order");
sb.append("by");
sb.append("ENTP_CODE;");
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
                new Header("Vendor Management (EntpInput")
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
