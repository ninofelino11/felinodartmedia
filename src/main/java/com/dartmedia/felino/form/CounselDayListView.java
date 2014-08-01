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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("CounselDayList")
public class CounselDayListView extends MVerticalLayout implements View {
//CounselDayListSvc data=new CounselDayListSvc();
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
sb.append("custcenter.counsel.selectCounselDayList");
sb.append("by");
sb.append("CounselDayList");
sb.append("TO_CHAR(TO_DATE(D.YYMMDD,'YYYY/MM/DD'),'YYYY/MM/DD')");
sb.append("AS");
sb.append("CLOSE_YYYYMMDD,");
sb.append("*/");
sb.append("TO_CHAR(D.YYMMDD,'YYYY/MM/DD')");
sb.append("AS");
sb.append("CLOSE_YYYYMMDD,");
sb.append("D.LGROUP_CODE");
sb.append("AS");
sb.append("OUT_LGROUP_CODE,");
sb.append("D.LGROUP_NAME");
sb.append("AS");
sb.append("LGROUP_NAME,");
sb.append("D.MGROUP_CODE");
sb.append("AS");
sb.append("OUT_MGROUP_CODE,");
sb.append("D.MGROUP_NAME");
sb.append("AS");
sb.append("MGROUP_NAME,");
sb.append("NVL(SUM(A.BASIC_CLAIM),");
sb.append("0)");
sb.append("AS");
sb.append("BASIC_CLAIM,");
sb.append("NVL(SUM(A.BASIC_PROC),");
sb.append("0)");
sb.append("AS");
sb.append("BASIC_PROC,");
sb.append("NVL(SUM(A.BASIC_HC),");
sb.append("0)");
sb.append("AS");
sb.append("BASIC_HC,");
sb.append("NVL(SUM(A.TODAY_CLAIM),");
sb.append("0)");
sb.append("AS");
sb.append("TODAY_CLAIM,");
sb.append("NVL(SUM(A.TODAY_PROC),");
sb.append("0)");
sb.append("AS");
sb.append("TODAY_PROC,");
sb.append("NVL(SUM(A.TODAY_HC),");
sb.append("0)");
sb.append("AS");
sb.append("TODAY_HC,");
sb.append("NVL(SUM(A.TODAY_END),");
sb.append("0)");
sb.append("AS");
sb.append("TODAY_END,");
sb.append("NVL(SUM(A.TODAY_TRANS),");
sb.append("0)");
sb.append("AS");
sb.append("TODAY_TRANS,");
sb.append("NVL(SUM(A.STOCK_CLAIM),");
sb.append("0)");
sb.append("AS");
sb.append("STOCK_CLAIM,");
sb.append("NVL(SUM(A.STOCK_PROC),");
sb.append("0)");
sb.append("AS");
sb.append("STOCK_PROC,");
sb.append("NVL(SUM(A.STOCK_HC),");
sb.append("0)");
sb.append("AS");
sb.append("STOCK_HC");
sb.append("FROM");
sb.append("(SELECT");
sb.append("C.YYMMDD,");
sb.append("A.CODE_MGROUP");
sb.append("AS");
sb.append("LGROUP_CODE,");
sb.append("B.CODE_MGROUP");
sb.append("AS");
sb.append("MGROUP_CODE,");
sb.append("A.CODE_NAME");
sb.append("AS");
sb.append("LGROUP_NAME,");
sb.append("B.CODE_NAME");
sb.append("AS");
sb.append("MGROUP_NAME");
sb.append("FROM");
sb.append("TCODE");
sb.append("A,");
sb.append("TCODE");
sb.append("B,");
sb.append("TDELYDAY");
sb.append("C");
sb.append("WHERE");
sb.append("A.REMARK");
sb.append("=");
sb.append("B.CODE_LGROUP");
sb.append("AND");
sb.append("A.CODE_LGROUP");
sb.append("=");
sb.append("'C031'");
sb.append("AND");
sb.append("A.USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("B.USE_YN");
sb.append("=");
sb.append("'1'");
sb.append("AND");
sb.append("C.DELY_GB");
sb.append("=");
sb.append("'10'");
sb.append("AND");
sb.append("C.YYMMDD");
sb.append(">=");
sb.append("TO_DATE('20140101',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("C.YYMMDD");
sb.append("<");
sb.append("TO_DATE('20140101',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1)");
sb.append("D,");
sb.append("TDCOUNSELCLOSE");
sb.append("A");
sb.append("WHERE");
sb.append("A.OUT_LGROUP_CODE(+)");
sb.append("=");
sb.append("D.LGROUP_CODE");
sb.append("AND");
sb.append("A.OUT_MGROUP_CODE(+)");
sb.append("=");
sb.append("D.MGROUP_CODE");
sb.append("AND");
sb.append("A.CLOSE_YYYYMMDD(+)");
sb.append("=");
sb.append("TO_CHAR(D.YYMMDD,");
sb.append("'YYYYMMDD')");
sb.append("AND");
sb.append("D.LGROUP_CODE||D.MGROUP_CODE");
sb.append("BETWEEN");
sb.append("'out_lgroup_fr'");
sb.append("||");
sb.append("'out_mgroup_fr'");
sb.append("AND");
sb.append("'out_lgroup_to'");
sb.append("||");
sb.append("'out_mgroup_to'");
sb.append("GROUP");
sb.append("BY");
sb.append("D.YYMMDD,");
sb.append("D.LGROUP_CODE,");
sb.append("D.LGROUP_NAME,");
sb.append("D.MGROUP_CODE,");
sb.append("D.MGROUP_NAME");
sb.append("ORDER");
sb.append("BY");
sb.append("D.YYMMDD,");
sb.append("D.LGROUP_CODE,");
sb.append("D.LGROUP_NAME,");
sb.append("D.MGROUP_CODE,");
sb.append("D.MGROUP_NAME");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("Button"));
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
                new Header("Counsel Report by Code (CounselDayList)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Close Date", String.class,  null);
table.addContainerProperty("L GRP", String.class,  null);
table.addContainerProperty("L GRP Name", String.class,  null);
table.addContainerProperty("M GRP", String.class,  null);
table.addContainerProperty("M GRP Name", String.class,  null);
table.addContainerProperty("Entry", String.class,  null);
table.addContainerProperty("Process", String.class,  null);


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
