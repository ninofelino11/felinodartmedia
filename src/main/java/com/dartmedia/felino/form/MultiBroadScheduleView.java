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
@CDIView("MultiBroadSchedule")
public class MultiBroadScheduleView extends MVerticalLayout implements View {
//MultiBroadScheduleSvc data=new MultiBroadScheduleSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT  /* schedule.xml : broadcast.schedule.selectManageProgramPlan by MultiBroadScheduleController */");
sb.append("                    TO_CHAR(A.BD_DATE, 'YYYY/MM/DD')  AS BD_DATE");
sb.append("                   ,TO_CHAR(A.BD_BTIME, 'YYYY/MM/DD HH24:MI') AS ORG_BD_BTIME");
sb.append("                   ,TO_CHAR(A.BD_BTIME, 'YYYY/MM/DD HH24:MI') AS BD_BTIME");
sb.append("                   ,TO_CHAR(A.BD_ETIME, 'YYYY/MM/DD HH24:MI') AS BD_ETIME");
sb.append("                   ,A.INSERT_DATE");
sb.append("                   ,A.MODIFY_DATE");
sb.append("                   ,A.PROG_CODE");
sb.append("                   ,C.PROG_NAME");
sb.append("                   ,A.INSERT_ID");
sb.append("                   ,A.MODIFY_ID");
sb.append("                   ,A.PLAN_AMT");
sb.append("                   ,A.PLAN_ORDER_AMT");
sb.append("                   ,A.PLAN_MARGIN_AMT");
sb.append("                   ,A.WEIGHTS_TIME");
sb.append("                   ,A.SEQ_FRAME_NO");
sb.append("                   ,'      '     FLAG");
sb.append("                   ,A.TAPE_CODE");
sb.append("                   ,D.TAPE_NAME");
sb.append("                   ,A.LIVE_FLAG");
sb.append("                   ,ROUND((A.BD_ETIME - A.BD_BTIME)*1440,0) AS RUNTIME");
sb.append("                   ,TO_CHAR(SYSDATE-1 , 'YYYY/MM/DD HH24:MI') AS CON_DATE");
sb.append("                   ,DECODE(SIGN(A.BD_BTIME - (SYSDATE + 10/1440)),-1,0,1) AS MODI_YN");
sb.append("                   ,(SELECT COUNT(*) FROM TMULTIDTBROAD B WHERE B.SEQ_FRAME_NO = A.SEQ_FRAME_NO) AS ITEM_CNT");
sb.append("         FROM TMULTIFRAMESCHE A,");
sb.append("              TPROGRAM   C,");
sb.append("              TPGMTAPE D");
sb.append("        WHERE A.PROG_CODE = C.PROG_CODE");
sb.append("          AND A.TAPE_CODE = D.TAPE_CODE (+)");
sb.append("          AND A.BD_DATE = TO_DATE('2014-01-01', 'YYYY/MM/DD') /* broad_date */");
sb.append("          AND A.MEDIA_CODE LIKE 'channel' || '%'");
sb.append("        ORDER BY A.BD_BTIME    ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("PGM Date"));
toolbar.addComponent(new ComboBox("Chanell"));
toolbar.addComponent(new Button("BroadCast Program Table Copying"));
toolbar.addComponent(new Button("Print"));


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
                new Header("Program Schedule (MultiBroadSchedule)"
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
