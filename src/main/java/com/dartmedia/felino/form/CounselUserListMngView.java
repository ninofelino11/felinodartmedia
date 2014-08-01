package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CounselUserListMng")
public class CounselUserListMngView extends MVerticalLayout implements View {
//CounselUserListMngSvc data=new CounselUserListMngSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("counsel.xml");
sb.append(":");
sb.append("custcenter.counsel.selectCounselUserList");
sb.append("by");
sb.append("CounselUserList");
sb.append("*/");
sb.append("A.CLOSE_YYYYMMDD");
sb.append("AS");
sb.append("CLOSE_YYYYMMDD,");
sb.append("A.PROC_ID,");
sb.append("NVL(AC.STATE_RECP,");
sb.append("0)");
sb.append("AS");
sb.append("STATE_RECP,");
sb.append("NVL(AC.STATE_PROC,");
sb.append("0)");
sb.append("AS");
sb.append("STATE_PROC,");
sb.append("NVL(AC.STATE_HC,");
sb.append("0)");
sb.append("AS");
sb.append("STATE_HC,");
sb.append("NVL(AC.STATE_END,");
sb.append("0)");
sb.append("AS");
sb.append("STATE_END,");
sb.append("NVL(AC.STATE_TRANS,0)");
sb.append("AS");
sb.append("STATE_TRANS,");
sb.append("A.TODAY_CLAIM,");
sb.append("A.PROC_JOB_CNT,");
sb.append("A.HC_JOB_CNT,");
sb.append("A.END_JOB_CNT,");
sb.append("A.TRANS_JOB_CNT,");
sb.append("C.USER_NAME");
sb.append("FROM");
sb.append("(/*");
sb.append("???");
sb.append("??");
sb.append("??");
sb.append("??");
sb.append("*/");
sb.append("SELECT");
sb.append("CLOSE_YYYYMMDD,");
sb.append("PROC_ID,");
sb.append("SUM(TODAY_CLAIM)");
sb.append("AS");
sb.append("TODAY_CLAIM,");
sb.append("SUM(PROC_JOB_CNT)");
sb.append("AS");
sb.append("PROC_JOB_CNT,");
sb.append("SUM(HC_JOB_CNT)");
sb.append("AS");
sb.append("HC_JOB_CNT,");
sb.append("SUM(END_JOB_CNT)");
sb.append("AS");
sb.append("END_JOB_CNT,");
sb.append("SUM(TRANS_JOB_CNT)");
sb.append("AS");
sb.append("TRANS_JOB_CNT");
sb.append("FROM");
sb.append("(");
sb.append("SELECT");
sb.append("TO_CHAR(TO_DATE(CLOSE_YYYYMMDD,'YYYYMMDD'),'YYYY/MM/DD')");
sb.append("AS");
sb.append("CLOSE_YYYYMMDD,");
sb.append("PROC_ID,");
sb.append("TODAY_CLAIM,");
sb.append("PROC_JOB_CNT,");
sb.append("HC_JOB_CNT,");
sb.append("END_JOB_CNT,");
sb.append("TRANS_JOB_CNT");
sb.append("FROM");
sb.append("TDPERCOUNSELCLOSE");
sb.append("WHERE");
sb.append("CLOSE_YYYYMMDD");
sb.append(">=");
sb.append("REPLACE('2014-01-01'");
sb.append(",");
sb.append("'/')");
sb.append("/*");
sb.append("#{fromDate}");
sb.append("*/");
sb.append("AND");
sb.append("CLOSE_YYYYMMDD");
sb.append("<=");
sb.append("REPLACE('2014-01-01'");
sb.append(",");
sb.append("'/')");
sb.append("/*");
sb.append("#{toDate}");
sb.append("*/");
sb.append("AND");
sb.append("PROC_ID");
sb.append("LIKE");
sb.append("'user_id'");
sb.append("||");
sb.append("'%'");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("TO_CHAR(PROC_DATE,'YYYY/MM/DD')");
sb.append("AS");
sb.append("CLOSE_YYYYMMDD,");
sb.append("PROC_ID");
sb.append("AS");
sb.append("PROC_ID,");
sb.append("SUM(DECODE(DO_FLAG,'10',1,0))");
sb.append("AS");
sb.append("TODAY_CLAIM,");
sb.append("SUM(DECODE(DO_FLAG,'20',1,0))");
sb.append("AS");
sb.append("PROC_JOB_CNT,");
sb.append("SUM(DECODE(DO_FLAG,'30',1,0))");
sb.append("AS");
sb.append("HC_JOB_CNT,");
sb.append("SUM(DECODE(DO_FLAG,'40',1,0))");
sb.append("AS");
sb.append("END_JOB_CNT,");
sb.append("SUM(DECODE(DO_FLAG,");
sb.append("'25',");
sb.append("1,'26','1','27','1',");
sb.append("0))");
sb.append("AS");
sb.append("TRAND_JOB_CNT");
sb.append("FROM");
sb.append("TCUSTCOUNSELDT");
sb.append("WHERE");
sb.append("PROC_DATE");
sb.append(">=");
sb.append("TRUNC(SYSDATE)");
sb.append("AND");
sb.append("PROC_DATE");
sb.append("<=");
sb.append("TO_DATE('2014-01-01'");
sb.append("||");
sb.append("'23:59:59',");
sb.append("'yyyy/mm/dd");
sb.append("hh24:mi:ss')");
sb.append("/*");
sb.append("#{toDate}");
sb.append("*/");
sb.append("AND");
sb.append("PROC_ID");
sb.append("LIKE");
sb.append("'user_id'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("DO_FLAG");
sb.append("<=");
sb.append("'41'");
sb.append("GROUP");
sb.append("BY");
sb.append("PROC_ID,");
sb.append("TO_CHAR(PROC_DATE,'YYYY/MM/DD')");
sb.append(")");
sb.append("GROUP");
sb.append("BY");
sb.append("CLOSE_YYYYMMDD,");
sb.append("PROC_ID");
sb.append(")");
sb.append("A,");
sb.append("TUSER");
sb.append("C,");
sb.append("(");
sb.append("/*");
sb.append("?????");
sb.append("STATES");
sb.append("??");
sb.append("??");
sb.append("*/");
sb.append("SELECT");
sb.append("C.INSERT_ID");
sb.append("AS");
sb.append("PROC_ID,");
sb.append("TO_CHAR(C.INSERT_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("PROC_DATE,");
sb.append("SUM(DECODE(C.DO_FLAG,'10',1,0))");
sb.append("AS");
sb.append("STATE_RECP,");
sb.append("SUM(DECODE(C.DO_FLAG,'20',1,0))");
sb.append("AS");
sb.append("STATE_PROC,");
sb.append("SUM(DECODE(C.DO_FLAG,'30',1,0))");
sb.append("AS");
sb.append("STATE_HC,");
sb.append("SUM(DECODE(C.DO_FLAG,'40',1,0))");
sb.append("AS");
sb.append("STATE_END,");
sb.append("SUM(DECODE(C.DO_FLAG,");
sb.append("'25',");
sb.append("1,'26','1','27','1',");
sb.append("0))");
sb.append("AS");
sb.append("STATE_TRANS");
sb.append("FROM");
sb.append("TCUSTCOUNSELM");
sb.append("C");
sb.append("WHERE");
sb.append("C.INSERT_DATE");
sb.append(">=");
sb.append("TO_DATE('2014-01-01',");
sb.append("'yyyy/mm/dd')");
sb.append("/*");
sb.append("#{fromDate}");
sb.append("*/");
sb.append("AND");
sb.append("C.INSERT_DATE");
sb.append("<=");
sb.append("TO_DATE('2014-01-01'");
sb.append("||");
sb.append("'23:59:59',");
sb.append("'yyyy/mm/dd");
sb.append("hh24:mi:ss')");
sb.append("/*");
sb.append("#{toDate}");
sb.append("*/");
sb.append("AND");
sb.append("C.INSERT_ID");
sb.append("LIKE");
sb.append("'user_id'");
sb.append("||");
sb.append("'%'");
sb.append("AND");
sb.append("C.DO_FLAG");
sb.append("<");
sb.append("'90'");
sb.append("GROUP");
sb.append("BY");
sb.append("C.INSERT_ID,");
sb.append("TO_CHAR(C.INSERT_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append(")");
sb.append("AC");
sb.append("WHERE");
sb.append("A.PROC_ID");
sb.append("=");
sb.append("C.USER_ID");
sb.append("AND");
sb.append("A.CLOSE_YYYYMMDD");
sb.append("=");
sb.append("AC.PROC_DATE(+)");
sb.append("AND");
sb.append("A.PROC_ID");
sb.append("=");
sb.append("AC.PROC_ID(+)");
sb.append("AND");
sb.append("A.PROC_ID");
sb.append("LIKE");
sb.append("'user_id'");
sb.append("||");
sb.append("'%'");
sb.append("ORDER");
sb.append("BY");
sb.append("A.CLOSE_YYYYMMDD");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();


toolbar.addComponent(new Button("Cust Detail"));
toolbar.addComponent(new Button("Ordering"));
toolbar.addComponent(new Button("C/S Management"));
toolbar.addComponent(new Button("Counsel Transfer"));
toolbar.addComponent(new Button("Counsel Receipt"));
toolbar.addComponent(new Button("Calling Up"));


toolbar.addComponent(new TextField("Indv.Query"));

ComboBox pilih1;
        pilih1 = new ComboBox("Proc Time");
DateField date = new DateField();
toolbar.addComponent(pilih1);
toolbar.addComponent(date);
toolbar.addComponent(new DateField("Until"));
toolbar.addComponent(new ComboBox("Proc Step"));
toolbar.addComponent(new ComboBox("~"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("Proc"));
toolbar.addComponent(new TextField("Person"));
toolbar.addComponent(new TextField("Large Group"));


//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-
//-
//-toolbar.addComponent(new Button("My ComboBox"));
//-ComboBox pilih1=new ComboBox("Custumer Group");
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
                new Header("Counsel Report by Agent[manager] (CounselUserListMng)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table-
//No Step L Grp M Grp Type CutName Order No Type Entry No. Tel Item Code Item Name Unit Unit Info
//Transfer,Transfer Value User Group 
        
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Step", String.class,  null);
table.addContainerProperty("L Grp", String.class,  null);
table.addContainerProperty("M Grp", String.class,  null);
table.addContainerProperty("Type", String.class,  null);
table.addContainerProperty("CustName", String.class,  null);
table.addContainerProperty("Order", String.class,  null);
table.addContainerProperty("Type", String.class,  null);
table.addContainerProperty("Entry No", String.class,  null);
table.addContainerProperty("Tel", String.class,  null);
table.addContainerProperty("Item Code", String.class,  null);
table.addContainerProperty("Item Name", String.class,  null);
table.addContainerProperty("Unit", String.class,  null);
table.addContainerProperty("Unit Info", String.class,  null);
table.addContainerProperty("Trasfer", String.class,  null);
table.addContainerProperty("Transfer Value", String.class,  null);
table.addContainerProperty("User Group", String.class,  null);









//-------------------- Header Table ------------------------------
   isicontents.addComponents(table);
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
