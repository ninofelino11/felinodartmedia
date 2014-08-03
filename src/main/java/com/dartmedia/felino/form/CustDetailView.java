package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CustDetail")
public class CustDetailView extends MVerticalLayout implements View {
//CustDetailSvc data=new CustDetailSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Customer<p:inputtext/>**/
StringBuffer sb = new StringBuffer();
sb.append("   SELECT /* customer.xml : custcenter.customer.selectCustomer by CustDetail */");
sb.append("               A.CUST_NO,");
sb.append("               A.CUST_NAME,");
sb.append("               A.CUST_NAME1,");
sb.append("               A.CUST_NAME2,");
sb.append("               A.CUST_NAME3,");
sb.append("               A.SEX,");
// sb.append("               CWARE_ENC_DEC(A.RESIDENT_NO, 'd') AS RESIDENT_NO,");
sb.append("               A.ENAME,");
sb.append("               A.COMP_NAME,");
sb.append("               A.COMP_DEPTNAME,");
sb.append("               A.BIRTHDAY_YN,");
sb.append("               A.BIRTHDAY,");
sb.append("               A.WEDDING_YN,");
sb.append("               TO_CHAR(A.WEDDING_DATE,'YYYYMMDD') WEDDING_DATE,");
sb.append("               A.JOB_CODE,");
sb.append("               A.EMAIL_ADDR,");
sb.append("               A.RECEIVE_METHOD,");
sb.append("               A.COUNTRY,");
sb.append("               A.SMS_YN,");
sb.append("               A.ORDER_EMAIL_YN,");
sb.append("               TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD') INSERT_DATE,");
sb.append("               '1' TABLE_GB,");
sb.append("               A.MEMB_NO,");
sb.append("               A.MEM_ID,");
sb.append("               A.PASSWD,");
sb.append("               A.WITHDRAWAL_YN,");
sb.append("               A.WITHDRAWAL_CODE,");
sb.append("               A.WITHDRAWAL_CONTENT,");
sb.append("               TO_CHAR(A.WITHDRAWAL_DATE, 'YYYYMMDD') WITHDRAWAL_DATE,");
sb.append("               A.NOMINATE_ID,");
sb.append("               TO_CHAR(A.ID_INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') ID_INSERT_DATE,");
sb.append("               A.PASSWD_HINT,");
sb.append("               A.PASSWD_ANSWER,");
sb.append("               A.EMAIL_YN,");
sb.append("               A.EM_YN,");
sb.append("               A.EM_NO,");
sb.append("               B.USER_NAME INSERT_NAME,");
sb.append("               C.USER_NAME MODIFY_NAME,");
sb.append("               A.INSERT_ID,");
sb.append("               A.MODIFY_ID,");
sb.append("               A.EMAIL_FLAG,");
sb.append("               TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE,");
sb.append("               (SELECT CONTENT FROM TCUSTSPINFO WHERE CUST_NO = A.CUST_NO AND TYPE = '70') JOIN_MOTIVE");
sb.append("          FROM TCUSTOMER A,");
sb.append("               TUSER     B,");
sb.append("               TUSER     C");
sb.append("         WHERE A.CUST_NO   = '201208007267'");
sb.append("           AND A.INSERT_ID = B.USER_ID(+)");
sb.append("           AND A.MODIFY_ID = C.USER_ID(+)");

//sb.append("");
//sb.append("SELECT /* customer.xml : custcenter.customer.selectReceiver by CustDetail */");
//sb.append("               A.RECEIVER_GB,");
//sb.append("               A.DEFAULT_YN,");
//sb.append("               A.RECEIVER_DDD,");
//sb.append("               A.RECEIVER_TEL1,");
//sb.append("               A.RECEIVER_TEL2,");
//sb.append("               A.RECEIVER_TEL3,");
//sb.append("               A.TEL,");
//sb.append("               A.RECEIVER_HP1,");
//sb.append("               A.RECEIVER_HP2,");
//sb.append("               A.RECEIVER_HP3,");
//sb.append("               A.RECEIVER_HP,");
//sb.append("               A.RECEIVER_HP1_SE,");
//sb.append("               A.RECEIVER_HP2_SE,");
//sb.append("               A.RECEIVER_HP3_SE,");
//sb.append("               A.RECEIVER_POST,");
//sb.append("               FUN_ADD_POSTADDR(A.RECEIVER_POST,A.RECEIVER_POST_SEQ,'') AS COMP_ADDR,");
//sb.append("               A.RECEIVER_ADDR,");
//sb.append("               A.RECEIVER_SEQ,");
//sb.append("               A.RECEIVER_POST_SEQ,");
//sb.append("               A.CUST_NO,");
//sb.append("               A.RECEIVER,");
//sb.append("               A.RECEIVER1,");
//sb.append("               A.RECEIVER2,");
//sb.append("               A.RECEIVER3,");
//sb.append("               A.INSERT_ID,");
//sb.append("               TO_CHAR(A.INSERT_DATE,'YYYY/MM/DD HH24:MI:SS') INSERT_DATE,");
//sb.append("               A.MODIFY_ID,");
//sb.append("               TO_CHAR(A.MODIFY_DATE,'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE");
//sb.append("          FROM TRECEIVER  A");
//sb.append("         WHERE A.CUST_NO           = '201208007267'");
//sb.append("           AND A.USE_YN            = '1'");
//sb.append("           AND A.RECEIVER_GB       = '00'");
//sb.append("         ORDER BY A.RECEIVER_GB,");
//sb.append("               A.RECEIVER_SEQ,");
//sb.append("               A.RECEIVER_POST");
//sb.append("");
//sb.append("SELECT /* customer.xml : custcenter.customer.selectCustsystem by CustDetail */");
//sb.append("               A.CUST_NO,");
//sb.append("               A.CUST_GB,");
//sb.append("               A.MEMB_GB,");
//sb.append("               A.CUST_WARNING,");
//sb.append("               A.CUST_CHAR,");
//sb.append("               A.USE_DEPOSIT,");
//sb.append("               A.USE_PB_DEPOSIT,");
//sb.append("               A.DM_NO_GB,");
//sb.append("               TO_CHAR(A.DM_NO_DATE, 'YYYY/MM/DD HH24:MI:SS') DM_NO_DATE,");
//sb.append("               A.DM_NO_ID,");
//sb.append("               TO_CHAR(A.FIRST_ORDER_DATE, 'YYYY/MM/DD HH24:MI:SS') FIRST_ORDER_DATE,");
//sb.append("               A.FIRST_MSALE_GB,");
//sb.append("               A.TOT_ORDER_CNT,");
//sb.append("               A.TOT_ORDER_AMT,");
//sb.append("               A.TOT_CANCEL_CNT,");
//sb.append("               A.TOT_CANCEL_AMT,");
//sb.append("               A.TOT_RETURN_CNT,");
//sb.append("               A.TOT_RETURN_AMT,                                          ");
//sb.append("               B.USER_NAME DM_NO_NAME,");
//sb.append("               A.MODIFY_ID,");
//sb.append("               A.DM_YN,");
//sb.append("               TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE");
//sb.append("          FROM TCUSTSYSTEM A,");
//sb.append("               TUSER       B");
//sb.append("         WHERE A.CUST_NO  = '201208007267'");
//sb.append("           AND A.DM_NO_ID = B.USER_ID(+)");
//sb.append("           AND ROWNUM     = 1");
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

toolmenu.addComponent(new Button("New"));
toolmenu.addComponent(new Button("Ordering"));
toolmenu.addComponent(new Button("Order"));
toolmenu.addComponent(new Button("Counsel Process"));
toolmenu.addComponent(new Button("Point"));
toolmenu.addComponent(new Button("Deposit"));
toolmenu.addComponent(new Button("CustInfo"));
toolbar.addComponent(new TextField("Custumer"));
toolbar.addComponent(new TextField(""));




        addComponents(
                new Header("Customer (CustDetail)"
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
              sb.toString(),connectionPool,"CUST_NO"));
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
