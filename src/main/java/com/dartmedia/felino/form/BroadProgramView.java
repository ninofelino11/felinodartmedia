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
@CDIView("BroadProgram")
public class BroadProgramView extends MVerticalLayout implements View {
//BroadProgramSvc data=new BroadProgramSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Term<p:calendar/>**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT        /* schedule.xml : broadcast.schedule.selectRegisterBroadcast by RegisterBroadcastInput */");
sb.append("                        A.PROG_CODE");
sb.append("                      , A.PROG_NAME");
sb.append("                      , DECODE(");
sb.append("                              SIGN(TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) - TO_NUMBER(TO_CHAR(A.EDATE,'YYYYMMDD'))) ,");
sb.append("                              -1, '1', '0') AS USE_YN");
sb.append("                      , TO_CHAR(A.BDATE, 'YYYY/MM/DD') AS BDATE");
sb.append("                      , TO_CHAR(A.EDATE, 'YYYY/MM/DD') AS EDATE");
sb.append("                      , TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE");
sb.append("                      , A.INSERT_ID");
sb.append("                      , TO_CHAR(A.MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') MODIFY_DATE");
sb.append("                      , A.MODIFY_ID");
sb.append("         FROM TPROGRAM A");
sb.append("        WHERE UPPER(A.PROG_NAME) LIKE UPPER('program_name') || '%'");
sb.append("          AND A.PROG_CODE <>  '000000'");
sb.append("          AND ( A.BDATE    <= TO_DATE('2014-01-01', 'YYYY/MM/DD') /* gather_date_to */");
sb.append("                   AND A.EDATE >= TO_DATE('2014-01-01', 'YYYY/MM/DD') ) /* gather_date_fr */");
sb.append("        ORDER BY A.PROG_CODE ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Program Name"));


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
                new Header("Program Code (BroadProgram)"
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
             BaseEntity.user,BaseEntity.pass,2,5);
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
