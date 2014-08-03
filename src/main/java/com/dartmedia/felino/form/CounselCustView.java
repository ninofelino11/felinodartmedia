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
@CDIView("CounselCust")
public class CounselCustView extends MVerticalLayout implements View {
//CounselCustSvc data=new CounselCustSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /* counsel.xml : custcenter.counsel.selectCounselCustomersNumberList by CounselCust */");
sb.append("                 /*+ ORDERED */ ");
sb.append("                 A.DO_FLAG, ");
sb.append("                 A.CUST_NO, ");
sb.append("                 B.CUST_NAME, ");
sb.append("                 A.CLAIM_NO, ");
sb.append("                 A.DDD, ");
sb.append("                 A.TEL1, ");
sb.append("                 A.TEL2, ");
sb.append("                 A.TEL3, ");
sb.append("                 A.TEL,  ");
sb.append("                 A.GOODS_CODE GOODS_CODE, ");
sb.append("                 A.GOODSDT_CODE, ");
sb.append("                 GOODS.GOODS_NAME, ");
sb.append("                 C.GOODSDT_INFO, ");
sb.append("                 A.COUNSEL_SEQ, ");
sb.append("                 D.CODE_NAME OUT_LGROUP_NAME, ");
sb.append("                 E.CODE_NAME OUT_MGROUP_NAME, ");
sb.append("                 F.CUST_WARNING, ");
sb.append("                 A.REF_ID1,");
sb.append("                 A.REF_NO1, ");
sb.append("                 A.REF_NO2, ");
sb.append("                 A.REF_NO3, ");
sb.append("                 A.REF_NO4, ");
sb.append("                 TO_CHAR(A.HC_REQ_DATE, 'YYYY/MM/DD HH24:MI') HC_REQ_DATE, ");
sb.append("                 TO_CHAR(A.PROC_DATE,   'YYYY/MM/DD HH24:MI') PROC_DATE, ");
sb.append("                 A.WILD_YN, ");
//sb.append("                 CWARE_ENC_DEC(B.RESIDENT_NO, 'd') AS RESIDENT_NO, ");
sb.append("                 B.RECEIVE_METHOD, ");
sb.append("                 A.OUT_LGROUP_CODE, ");
sb.append("                 A.OUT_MGROUP_CODE, ");
sb.append("                 A.QUICK_YN, ");
sb.append("                 D.REMARK2 AS WEB_BACK_GB, ");
sb.append("                 NVL(A.EMAIL_YN, '0') AS EMAIL_YN, ");
sb.append("                 CASE WHEN A.OUT_LGROUP_CODE BETWEEN '81' AND '95' THEN 'WEB' ");
sb.append("                 WHEN A.OUT_LGROUP_CODE BETWEEN '01' AND '80' THEN 'CALL' ");
sb.append("                 END AS GUBUN  ");
sb.append("                 , A.SEND_ENTP_CODE    ");
sb.append("                 , (SELECT ENTP.ENTP_NAME FROM TENTERPRISE ENTP WHERE ENTP.ENTP_CODE = A.SEND_ENTP_CODE) AS SEND_ENTP_NAME    ");
sb.append("                 , A.CS_SEND_YN    ");
sb.append("                 , A.SEND_EMPNO");
sb.append("            FROM TCUSTCOUNSELM A, ");
sb.append("                 TCUSTOMER B, ");
sb.append("                 TGOODS GOODS, ");
sb.append("                 TGOODSDT C, ");
sb.append("                 TCODE D, ");
sb.append("                 TCODE E, ");
sb.append("                 TCUSTSYSTEM F ");
sb.append("           WHERE A.CUST_NO         = B.CUST_NO ");
sb.append("             AND A.CUST_NO         = F.CUST_NO ");
sb.append("             AND A.GOODS_CODE      = GOODS.GOODS_CODE(+) ");
sb.append("             AND A.GOODS_CODE      = C.GOODS_CODE(+) ");
sb.append("             AND A.GOODSDT_CODE    = C.GOODSDT_CODE(+) ");
sb.append("             AND A.OUT_LGROUP_CODE || ''  = D.CODE_MGROUP ");
sb.append("             AND D.CODE_LGROUP     = 'C031' ");
sb.append("             AND A.OUT_MGROUP_CODE = E.CODE_MGROUP ");
sb.append("             AND E.CODE_LGROUP     = D.REMARK ");
sb.append("             AND A.CUST_NO         = 'cust_no'");
sb.append("             AND A.CLAIM_NO     LIKE'claim_no' || '%'");
sb.append("          ORDER BY  A.COUNSEL_SEQ DESC ");
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
                new Header("Counsel (CounselCust)"
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
              sb.toString(),connectionPool,"AD_MENU_ID"));
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
