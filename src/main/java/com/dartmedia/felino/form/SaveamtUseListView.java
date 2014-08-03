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
@CDIView("SaveamtUseList")
public class SaveamtUseListView extends MVerticalLayout implements View {
//SaveamtUseListSvc data=new SaveamtUseListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Customer <p:inputText />Completion date <p:calendar /> ~ <p:calendar />**/
StringBuffer sb = new StringBuffer();
sb.append("    SELECT  /* saveamt.xml : custcenter.save.selectSaveamtUseList by SaveamtUseList */");
sb.append("                  TO_CHAR(A.PROC_DATE, 'YYYY/MM/DD') PROC_DATE,  ");
sb.append("                 E.MEMB_NO,  ");
sb.append("                 F.CUST_NO,  ");
sb.append("                 F.RECEIVER,  ");
sb.append("                 A.PROC_GB,  ");
sb.append("                 DECODE(PROC_GB, '0', -1*A.USE_AMT, '1', A.USE_AMT ) AS USE_AMT,  ");
sb.append("                 A.TRANS_CUST_NO,  ");
sb.append("                 D.USER_NAME,  ");
sb.append("                 A.SAVEAMT_GB,  ");
sb.append("                 A.ORDER_NO,  ");
sb.append("                 A.RECEIPT_NO  ");
sb.append("           FROM  TSAVEUSE    A,  ");
sb.append("                 TUSER       D,  ");
sb.append("                 TCUSTOMER   E,  ");
sb.append("                 TRECEIVER   F  ");
sb.append("          WHERE A.PROC_DATE BETWEEN TO_DATE('2012/11/01 00:00:00', 'yyyy/mm/dd hh24:mi:ss')  ");
sb.append("                                AND TO_DATE('2014/12/01 23:59:59', 'yyyy/mm/dd hh24:mi:ss')  ");
sb.append("            AND  A.PROC_YN = '1'  ");
sb.append("            AND  A.PROC_ID = D.USER_ID  ");
sb.append("            AND  A.USE_AMT <> 0  ");
sb.append("            AND  A.CUST_NO = E.CUST_NO  ");
sb.append("            AND  A.CUST_NO = F.CUST_NO  ");
sb.append("            AND  A.CUST_NO = 201308045442      ");
sb.append("            AND  F.DEFAULT_YN  =  '1'  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Custumer"));

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
                new Header("Customer Point Use (SaveamtUseList)"
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
