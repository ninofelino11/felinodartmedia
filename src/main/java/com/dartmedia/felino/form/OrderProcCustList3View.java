package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
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
@CDIView("OrderProcCustList3")
public class OrderProcCustList3View extends MVerticalLayout implements View {
//OrderProcCustList3Svc data=new OrderProcCustList3Svc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT /* orderinfo.xml : custcenter.orderreport.selectClaimProcCustList by OrderProcCustList3 */");
sb.append("         TO_CHAR(A.LAST_PROC_DATE, 'YYYY/MM/DD') AS LAST_PROC_DATE,");
sb.append("         SUBSTR(A.ORDER_NO, 1, 8) || '-' || SUBSTR(A.ORDER_NO, 9) || '-' ||");
sb.append("         A.ORDER_G_SEQ || '-' || A.ORDER_D_SEQ || '-' || A.ORDER_W_SEQ AS ORDER_NO,");
sb.append("         TCODE_NAME('J007', A.CLAIM_GB) AS GUBUN,");
sb.append("         A.GOODS_CODE AS GOODS_CODE,");
sb.append("         D.CUST_NAME,");
sb.append("         E.RECEIVER,");
sb.append("         '' AS MSG,");
sb.append("         A.GOODSDT_CODE,");
sb.append("         C.GOODS_NAME,");
sb.append("         C.GOODSDT_INFO,");
sb.append("         A.SYSLAST,");
sb.append("         A.SYSLAST_AMT,");
sb.append("         G.ENTP_NAME,");
sb.append("         A.DO_FLAG");
sb.append("          FROM TCLAIMDT    A,");
sb.append("               TGOODS      B,");
sb.append("               TGOODSDT    C,");
sb.append("               TCUSTOMER   D,");
sb.append("               TRECEIVER   E,");
sb.append("               TENTERPRISE G,");
sb.append("               TMD         H");
sb.append("         WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("           AND A.GOODS_CODE = C.GOODS_CODE");
sb.append("           AND A.GOODSDT_CODE = C.GOODSDT_CODE");
sb.append("           AND A.CUST_NO = D.CUST_NO");
sb.append("           AND A.CUST_NO = E.CUST_NO");
sb.append("           AND A.RECEIVER_SEQ = E.RECEIVER_SEQ");
sb.append("           AND B.ENTP_CODE = G.ENTP_CODE");
sb.append("           AND B.MD_CODE = H.MD_CODE");
sb.append("           AND A.LAST_PROC_DATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD') /* fromDate */");
sb.append("           AND A.LAST_PROC_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1  /* toDate */");
sb.append("           AND A.WH_CODE LIKE 'whCode' || '%'");
sb.append("           AND A.DO_FLAG LIKE 'doFlag' || '%'");
sb.append("           AND B.ENTP_CODE LIKE 'entpCode' || '%'");
sb.append("           AND A.GOODS_CODE LIKE 'goodsCode' || '%'");
sb.append("           AND A.CLAIM_GB LIKE 'claimGb' || '%'    ");
sb.append("           ");
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
                new Header("Customer Report for Return (OrderProcCustList3)"
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
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable();
               table.setContainerDataSource(container);
table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
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
