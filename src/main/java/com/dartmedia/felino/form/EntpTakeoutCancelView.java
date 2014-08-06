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
@CDIView("EntpTakeoutCancel")
public class EntpTakeoutCancelView extends MVerticalLayout implements View {
//EntpTakeoutCancelSvc data=new EntpTakeoutCancelSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Request no <p:inputText />    Term <p:calendar /> ~ <p:calendar />Vendor <p:inputText />                   Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* entpout.xml : logistics.entpout.selectTakeoutCancel by EntpTakeoutCancel */");
sb.append("                   A.EOUT_QUEST_NO,");
sb.append("                 B.ENTP_NAME,");
sb.append("                 A.EOUT_GB,");
sb.append("                 TO_CHAR(A.EOUT_CTRL_OUT_DATE, 'YYYY/MM/DD') AS EOUT_CTRL_OUT_DATE,");
sb.append("                 TO_CHAR(A.EOUT_CONFIRM_DATE, 'YYYY/MM/DD') AS EOUT_CONFIRM_DATE,");
sb.append("                 A.EOUT_CANCEL_CODE,");
sb.append("                 A.EOUT_CANCEL_NOTE,");
sb.append("                 C.ENTP_MAN_NAME,");
sb.append("                 C.ENTP_MAN_DDD,");
sb.append("                 C.ENTP_MAN_TEL1,");
sb.append("                 C.ENTP_MAN_TEL2,");
sb.append("                 C.ENTP_MAN_TEL3,");
sb.append("                 A.EOUT_CANCEL_YN,");
sb.append("                 A.EOUT_CANCEL_ID,");
sb.append("                 TO_CHAR(A.EOUT_CANCEL_DATE, 'YYYY/MM/DD') AS EOUT_CANCEL_DATE,");
sb.append("                 A.EOUT_END_YN,");
sb.append("                 A.WH_CODE");
sb.append("            FROM TENTPOUTQUESTM  A,");
sb.append("                 TENTERPRISE     B,");
sb.append("                 TENTPUSER       C");
sb.append("           WHERE A.ENTP_CODE       = B.ENTP_CODE");
sb.append("             AND A.ENTP_CODE       = C.ENTP_CODE");
sb.append("             AND A.ENTP_MAN_SEQ    = C.ENTP_MAN_SEQ");
sb.append("             AND A.EOUT_CANCEL_YN  = '0' AND A.EOUT_END_YN = '0'");
sb.append("             AND A.EOUT_QUEST_DATE BETWEEN TO_DATE('2014/01/01','YYYY/MM/DD') AND TO_DATE('2014/08/01','YYYY/MM/DD')");
sb.append("             AND A.ENTP_CODE  LIKE '%%'");
sb.append("             AND A.EOUT_GB    LIKE '%%' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("Request Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Vendor"));
toolbar.addComponent(new CheckBox("Warehouse TCode"));
toolbar.addComponent(new ComboBox("Take Out Type "));


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
                new Header("Take Out Cancel (EntpTakeoutCancel)"
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
              sb.toString(),connectionPool,"EOUT_QUEST_NO"));
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
