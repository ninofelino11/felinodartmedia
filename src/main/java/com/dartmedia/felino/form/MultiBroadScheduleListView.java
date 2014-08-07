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
@CDIView("MultiBroadScheduleList")
public class MultiBroadScheduleListView extends MVerticalLayout implements View {
//MultiBroadScheduleListSvc data=new MultiBroadScheduleListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("SELECT /* schedule.xml : broadcast.schedule.selectStaffMultiScheduleListPd by StaffMultiSchejuleList */");
sb.append("            A.PROG_CODE AS PROG_CODE,");
sb.append("             REPLACE(B.PROG_NAME, CHR(39), '') || ' / ' ||");
sb.append("              TCODE_NAME('C098', A.LIVE_FLAG) ||");
sb.append("              DECODE(A.TAPE_CODE, NULL, '', '(' || C.TAPE_NAME || ')') AS PROG_NAME,");
sb.append("             A.BD_BTIME                      AS BD_BTIME,");
sb.append("             TO_CHAR(A.BD_BTIME,'YYYY/MM/DD') AS BDATE,");
sb.append("             TO_CHAR(A.BD_BTIME,'D')          AS WEEK_DAY,");
sb.append("             SUBSTR(TO_CHAR(A.BD_BTIME,'DAY'), 1, 1) AS WEEK_DNAME,");
sb.append("             TO_CHAR(A.BD_BTIME,'HH24MI')      AS BTIME,");
sb.append("             TO_CHAR(A.BD_BTIME,'HH24')        AS BTIMEH,");
sb.append("             TO_CHAR(A.BD_BTIME,'MI')          AS BTIMEM,");
sb.append("             ROUND((NVL(A.BD_ETIME, (SELECT MIN(X.BD_BTIME) FROM TMULTIFRAMESCHE X WHERE X.BD_BTIME > A.BD_BTIME)) - BD_BTIME)  * 1440) AS RTIME,");
sb.append("             TO_CHAR(A.BD_ETIME,'YYYY/MM/DD HH24MI') AS ETIME,");
sb.append("             TO_NUMBER(PLAN_AMT)                        AS TARGET_AMT,");
sb.append("             NVL(FUN_GET_BROAD_GOODS_NAME(A.SEQ_FRAME_NO), chr(13) || '*No item') AS GOODS_NAMES");
sb.append("        FROM TMULTIFRAMESCHE A,");
sb.append("                TPROGRAM     B,");
sb.append("                TPGMTAPE     C");
sb.append("       WHERE ( A.PROG_CODE   = B.PROG_CODE");
sb.append("         AND   A.BD_BTIME   >= TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("         AND   A.BD_BTIME   <  TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
sb.append("         AND   A.MEDIA_CODE =  'media_code'  )");
sb.append("         AND  ( A.MAIN_PD = 'user_id' OR SUB_PD = 'user_id')");
sb.append("         AND A.TAPE_CODE = C.TAPE_CODE (+)");
sb.append("       ORDER BY A.BD_BTIME ");
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
                new Header("Program Report (MultiBroadScheduleList)"
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
