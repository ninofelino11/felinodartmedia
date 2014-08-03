package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("DelyDiary")
public class DelyDiaryView extends MVerticalLayout implements View {
//DelyDiarySvc data=new DelyDiarySvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("  select /* sql_logistics_base_code.xml : logistics.base.code.selectDelyDiary by DelyDiary */");
sb.append("                  'DAY_' || ROWNUM AS CELL_ID,");
sb.append("                DAYS,");
sb.append("                DATE_D,");
sb.append("                NVL(DELY_GB, #{dely_gb}) AS DELY_GB,");
sb.append("                NVL(TO_CHAR( YYMMDD, 'YYYY/MM/DD'), TO_CHAR(ALL_DATE, 'YYYY/MM/DD')) AS YYMMDD,");
sb.append("                NVL(DAY_GB, '00' || DAYS) AS DAY_GB,");
sb.append("                NVL(WORK_YN,DECODE(DAYS, '1', '0', '7', '0' ,'1')) AS WORK_YN,");
sb.append("                NVL(OFF_NAME, '') AS OFF_NAME,");
sb.append("                INSERT_ID,");
sb.append("                TO_CHAR( INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INSERT_DATE,");
sb.append("                MODIFY_ID,");
sb.append("                TO_CHAR( MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') AS MODIFY_DATE");
sb.append("          from ( SELECT DAYS, DATE_D, WEEK_GRP, WEEK_GRP + (DAYS -1) AS ALL_DATE");
sb.append("                   FROM (");
sb.append("                         SELECT TO_CHAR (TRUNC (BASE_MON, 'd') + LEVEL - 1, 'd') DAYS,");
sb.append("                                TO_CHAR (TRUNC (BASE_MON, 'd') + LEVEL - 1, 'fmdd') DATE_D,");
sb.append("                                TRUNC (TRUNC (BASE_MON, 'd') + LEVEL - 1, 'd') WEEK_GRP");
sb.append("                           FROM (SELECT TO_DATE (#{yymmdd}, 'YYYYMM') BASE_MON");
sb.append("                                   FROM   DUAL)");
sb.append("             CONNECT BY TRUNC (BASE_MON, 'd') + LEVEL - 1 &lt; = TRUNC (LAST_DAY (BASE_MON), 'd') + 6");
sb.append("            )) A,");
sb.append("            TDELYDAY B");
sb.append("          where A.ALL_DATE = B.YYMMDD(+)");
sb.append("            and B.DELY_GB(+) = 301");
sb.append("        order by YYMMDD     ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new PopupDateField("~"));
//- table.setColumnHeaders( new String[] {"Property 1", "Property2"} );
addComponents(new Calendar());


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
                new Header("Holiday Management (DelyDiary)"
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
