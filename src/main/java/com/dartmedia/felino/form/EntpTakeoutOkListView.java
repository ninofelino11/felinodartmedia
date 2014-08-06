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
@CDIView("EntpTakeoutOkList")
public class EntpTakeoutOkListView extends MVerticalLayout implements View {
//EntpTakeoutOkListSvc data=new EntpTakeoutOkListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Term <p:calendar /> ~ <p:calendar />    Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Type <p:selectOneListbox id="basic"> </p:selectOneListbox>     Unit <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Take out no <p:inputText />                                                  Request no <p:inputText />         **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("      SELECT /* entpout.xml : logistics.entpout.selectEntpTakeoutOkList by EntpTakeoutOkList */");
sb.append("                   A.EOUT_NO,");
sb.append("                 A.WH_CODE,");
sb.append("                 A.EOUT_GB,");
sb.append("                 C.ENTP_CODE,");
sb.append("                 C.ENTP_NAME,");
sb.append("                 A.EOUT_MAN_ID,");
sb.append("                 A.EOUT_PROC_ID,");
sb.append("                 E.USER_NAME AS EOUT_MAN_ID_NAME,");
sb.append("                 F.USER_NAME AS EOUT_PROC_ID_NAME,");
sb.append("                 A.EOUT_RECEIVER,");
sb.append("                 A.EOUT_QUEST_NO,");
sb.append("                 A.EOUT_PRT_CNT,");
sb.append("                 0 AS OK_FLAG,");
sb.append("                 A.AUTO_YN");
sb.append("            FROM TENTPOUTM          A,");
sb.append("                 TENTPOUTQUESTM     B,");
sb.append("                 TENTERPRISE        C,");
sb.append("                 TUSER              E,");
sb.append("                 TUSER              F");
sb.append("           WHERE A.EOUT_QUEST_NO = B.EOUT_QUEST_NO");
sb.append("             AND B.ENTP_CODE     = C.ENTP_CODE");
sb.append("             AND A.EOUT_MAN_ID   = E.USER_ID");
sb.append("             AND A.EOUT_PROC_ID  = F.USER_ID");
sb.append("             AND A.EOUT_DATE BETWEEN TO_DATE('2013/11/01', 'YYYY/MM/DD') AND TO_DATE('2014/08/01', 'YYYY/MM/DD')");
sb.append("             AND A.WH_CODE     LIKE '%001%'");
sb.append("             AND B.ENTP_CODE   LIKE '%%'");
sb.append("             AND A.EOUT_MAN_ID LIKE '%%'");
sb.append("             AND A.EOUT_GB     LIKE '%%'   ");
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
                new Header("Take Out Report (EntpTakeoutOkList)"
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
