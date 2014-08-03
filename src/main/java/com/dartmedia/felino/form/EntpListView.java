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
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("EntpList")
public class EntpListView extends MVerticalLayout implements View {
//EntpListSvc data=new EntpListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Reg Term <p:calendar id='from_date' /> ~ <p:calendar id='to_date'/>Vendor <p:inputText id='entp_code' />//select a.entp_code, a.entp_name, a.s_idno, b.entp_man_name from tenterprise a, tentpuser b where a.entp_code = b.entp_codeDeal <p:selectOneMenu id='close_yn'></p:selectOneMenu>//select code_mgroup, code_name from tcode where code_lgroup = 'B135'Vendor Type <p:inputText/>//select * from tcode where code_lgroup = 'B001'**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* entp.xml : manage.entp.selectEntpDetailList by EntpList */");
sb.append("                     A.ENTP_CODE,");
sb.append("                  A.ENTP_NAME,");
sb.append("                  A.ENTP_GUBUN,");
sb.append("                  TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') as INSERT_DATE,");
sb.append("                  A.WORK_TYPE,");
sb.append("                  A.WORK_KIND,");
sb.append("                  A.S_IDNO,");
sb.append("                  to_char(A.CLOSE_DATE, 'YYYY/MM/DD') as CLOSE_DATE,");
sb.append("                  A.DISHONOR_YN,");
sb.append("                  A.CLOSE_REASON,");
sb.append("                  NVL(A.OWNER_NAME, B.ENTP_MAN_NAME)  as OWNER_NAME,");
sb.append("                  B.ENTP_MAN_NAME,");
sb.append("                  A.ENTP_DDD || '-' || A.ENTP_TEL1 || '-' || A.ENTP_TEL2 as TEL ,");
sb.append("                  RTRIM(A.PAYMENT_TERM, 'D') AS PAYMENT_TERM,");
sb.append("                  (SELECT BA.BANK_NAME");
sb.append("                    FROM  TENTPACCOUNT EN, TBANK BA");
sb.append("                    WHERE  EN.USE_YN = '1'");
sb.append("                    AND    EN.DEFAULT_YN = '1'");
sb.append("                    AND    EN.ENTP_CODE  = A.ENTP_CODE");
sb.append("                    AND    EN.BANK_CODE  = BA.BANK_CODE) AS BANK_NAME");
//sb.append("                   ,(SELECT CWARE_ENC_DEC(ACCOUNT_NO,'D')");
sb1.append("                    FROM  TENTPACCOUNT");
sb1.append("                    WHERE  USE_YN      = '1'");
sb1.append("                    AND    DEFAULT_YN  = '1'");
sb1.append("                    AND    ENTP_CODE   = A.ENTP_CODE) AS ACCOUNT_NO,");
sb1.append("                   (SELECT BRANCH_NAME");
sb1.append("                     FROM  TENTPACCOUNT");
sb1.append("                    WHERE  USE_YN      = '1'");
sb1.append("                    AND    DEFAULT_YN  = '1'");
sb1.append("                    AND    ENTP_CODE   = A.ENTP_CODE) AS BRANCH_NAME");
sb.append("             FROM TENTERPRISE A,");
sb.append("                  TENTPUSER   B");
sb1.append("            WHERE A.ENTP_CODE     = B.ENTP_CODE(+)");
sb1.append("              AND B.DEFAULT_YN(+) = '1'");
sb1.append("              AND A.ENTP_CODE like '1' || '%'");
sb1.append("              AND A.INSERT_DATE >= TO_DATE('2013-07-01', 'YYYY/MM/DD')");
sb1.append("               AND A.INSERT_DATE < TO_DATE('2014-07-01', 'YYYY/MM/DD') + 1");
sb1.append("                 AND A.ENTP_GUBUN LIKE '1' || '%'");
//sb.append("              //kalau id close_yn==2");
sb1.append("              AND ( A.DISHONOR_YN = '2'  or A.CLOSE_DATE <= SYSDATE )");
//sb.append("              //kalau id close_yn <> ''");
sb1.append("              AND ( A.CLOSE_DATE IS NULL OR A.CLOSE_DATE &gt; SYSDATE )");
sb1.append("                    AND A.DISHONOR_YN = '1' ");
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
                new Header("Vendor List (EntpList)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"ENTP_CODE"));
             // MTable table= new MTable("MENU",container);
               TreeTable table = new TreeTable("Menu", container);
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
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
