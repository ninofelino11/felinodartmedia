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
@CDIView("RefundDaysum")
public class RefundDaysumView extends MVerticalLayout implements View {
//RefundDaysumSvc data=new RefundDaysumSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* refund.xml : custcenter.refund.selectRefundDaysum by RefundDaysum */");
sb.append("                   TO_CHAR(REPAY_DATE,'YYYY/MM/DD') AS REPAY_DATE,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '01', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  CARD_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '02', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  CASH_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '03', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  COD_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '04', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  MOB_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '05', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  SAVE_AMT,");
sb.append("                SUM(NVL(DECODE(SETTLE_GB, '06', DECODE(#{repay_flag,jdbcType=VARCHAR},'1',REPAY_AMT,'2', REAL_REPAY_AMT, REPAY_FEE)),0))  DEPOSIT_AMT,");
sb.append("                SUM(CASE WHEN SETTLE_GB &lt;= '06' THEN 0");
sb.append("                         ELSE  REPAY_AMT");
sb.append("                    END)   ETC_AMT");
sb.append("           FROM VSDREFUND");
sb.append("          WHERE REPAY_DATE BETWEEN TO_DATE('2014-01-01', 'yyyy/mm/dd')");
sb.append("            AND TO_DATE('2014-01-01', 'yyyy/mm/dd')");
sb.append("       GROUP BY REPAY_DATE");
sb.append("       ORDER BY REPAY_DATE     ");
sb.append("       ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Refund Complete"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Refunf Amt"));


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
                new Header("Refund Report (RefundDaysum)"
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
