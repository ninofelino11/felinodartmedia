package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("BroadProgram")
public class BroadProgramView extends MVerticalLayout implements View {
//BroadProgramSvc data=new BroadProgramSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**Term<p:calendar/>**/
StringBuffer sb = new StringBuffer();
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
        addComponents(isicontents);
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("ITEM", String.class,  null);
table.addContainerProperty("No", String.class,  null);
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
    