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
@CDIView("ReturnOkRequireReport")
public class ReturnOkRequireReportView extends MVerticalLayout implements View {
//ReturnOkRequireReportSvc data=new ReturnOkRequireReportSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**<p:selectOneRadio><f:selectItem itemValue="Shipping order-Delivery complete" /><f:selectItem itemValue="Return order-Return confirm" />  </p:selectOneRadio>Shipping order date <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Warehouse <p:selectOneListbox></p:selectOneListbox> Zone type <p:selectOneListbox></p:selectOneListbox> L grp <p:inputText></p:inputText> M grp <p:inputText></p:inputText> Item <p:inputText></p:inputText>             **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* outreport.xml : logistics.outreport.selectSliporder by ReturnOkRequireReport */");
sb.append("                 C.LGROUP,");
sb.append("                    C.MGROUP,");
sb.append("                    D.GOODS_CODE,");
sb.append("                    D.GOODSDT_CODE,");
sb.append("                    D.GOODS_NAME,");
sb.append("                    D.GOODSDT_INFO,");
sb.append("                    SUM(DECODE(WORK_DAY,1,QTY,0)) AS QTY1,");
sb.append("                    SUM(DECODE(WORK_DAY,2,QTY,0)) AS QTY2,");
sb.append("                    SUM(DECODE(WORK_DAY,3,QTY,0)) AS QTY3,");
sb.append("                    SUM(DECODE(WORK_DAY,4,QTY,0)) AS QTY4,");
sb.append("                    SUM(DECODE(WORK_DAY,5,QTY,0)) AS QTY5,");
sb.append("                    SUM(DECODE(WORK_DAY,6,QTY,0)) AS QTY6,");
sb.append("                    SUM(DECODE(WORK_DAY,7,QTY,0)) AS QTY7,");
sb.append("                    SUM(DECODE(WORK_DAY,8,QTY,0,QTY,0)) AS QTY8,");
sb.append("                    SUM(QTY) AS QTY_SUM,");
sb.append("                    E.ENTP_CODE,");
sb.append("                    E.ENTP_NAME,");
sb.append("                    F.MD_NAME");
sb.append("               FROM (SELECT A.CUST_NO      AS CUST_NO,");
sb.append("                            A.RECEIVER_SEQ AS RECEIVER_SEQ,");
sb.append("                            B.GOODS_CODE   AS GOODS_CODE,");
sb.append("                            B.GOODSDT_CODE AS GOODSDT_CODE,");
sb.append("                            FUN_GET_DELYDAY_CNT(A.DELY_GB,A.CREATE_DATE,A.SLIP_PROC_DATE,'1') AS WORK_DAY,");
sb.append("                            B.DELY_QTY + B.RETURN_QTY AS QTY");
sb.append("                       FROM TSLIPM    A,");
sb.append("                            TSLIPDT   B");
sb.append("                      WHERE A.SLIP_I_NO    = B.SLIP_I_NO");
sb.append("                        AND A.OUT_CLOSE_YN     = '1'");
sb.append("                        AND A.SLIP_FLAG        = '1'");
sb.append("                        AND A.SLIP_PROC        = '80'");
sb.append("                        AND B.DELY_QTY         > 0");
sb.append("                        AND A.CREATE_DATE    BETWEEN TO_DATE('2013/11/01','YYYY/MM/DD') AND TO_DATE('2013/11/01','YYYY/MM/DD')");
sb.append("                        AND A.WH_CODE       ='001'");
sb.append("                    ) AA,");
sb.append("                      TGOODS                C,");
sb.append("                    TGOODSDT            D,");
sb.append("                    TENTERPRISE          E,");
sb.append("                    TMD                  F,");
sb.append("                    TRECEIVER            H,");
sb.append("                    TPOST                I");
sb.append("           WHERE AA.GOODS_CODE        = D.GOODS_CODE");
sb.append("                    AND AA.GOODSDT_CODE      = D.GOODSDT_CODE");
sb.append("                    AND AA.GOODS_CODE        = C.GOODS_CODE");
sb.append("                    AND C.ENTP_CODE         = E.ENTP_CODE");
sb.append("                     AND C.MD_CODE           = F.MD_CODE");
sb.append("                    AND H.RECEIVER_POST     = I.POST_NO");
sb.append("                    AND H.RECEIVER_POST_SEQ = I.POST_SEQ");
sb.append("                    AND AA.CUST_NO           = H.CUST_NO");
sb.append("                    AND AA.RECEIVER_SEQ      = H.RECEIVER_SEQ");
sb.append("                    AND C.LGROUP            LIKE '%%'");
sb.append("                    AND C.MGROUP            LIKE '%%'");
sb.append("                    AND C.GOODS_CODE        LIKE '%%'");
sb.append("                    AND I.POST_GB           LIKE '%%'");
sb.append("                GROUP BY C.LGROUP,");
sb.append("                               C.MGROUP,");
sb.append("                               D.GOODS_CODE,");
sb.append("                               D.GOODSDT_CODE,");
sb.append("                               D.GOODS_NAME,");
sb.append("                               D.GOODSDT_INFO,");
sb.append("                               E.ENTP_CODE,");
sb.append("                               E.ENTP_NAME,");
sb.append("                               F.MD_NAME");
sb.append("                ORDER BY C.LGROUP,");
sb.append("                               C.MGROUP,");
sb.append("                               D.GOODS_CODE,");
sb.append("                               D.GOODSDT_CODE   ");
sb.append("");
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
                new Header("Process Term Report (ReturnOkRequireReport)"
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
