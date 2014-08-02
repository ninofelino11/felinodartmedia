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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("DepositDaily")
public class DepositDailyView extends MVerticalLayout implements View {
//DepositDailySvc data=new DepositDailySvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Process complete date <p:calendar />**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("deposit.xml");
sb.append(":");
sb.append("custcenter.deposit.selectDepositDaily");
sb.append("by");
sb.append("DepositDaily");
sb.append("*/");
sb.append("TO_CHAR(A.PROC_DATE,");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("PROC_DATE,");
sb.append("NVL(SUM(A.BASE_DEPOSIT),");
sb.append("0)");
sb.append("AS");
sb.append("BASE_DEPOSIT,");
sb.append("NVL(SUM(A.MGRANT_DEPOSIT),");
sb.append("0)");
sb.append("AS");
sb.append("MGRANT_DEPOSIT,");
sb.append("NVL(SUM(A.MUSE_DEPOSIT),");
sb.append("0)");
sb.append("AS");
sb.append("MUSE_DEPOSIT");
sb.append("FROM");
sb.append("(");
sb.append("SELECT");
sb.append("TRUNC(TO_DATE('2014/01/01','YYYY/MM/DD'))");
sb.append("AS");
sb.append("PROC_DATE,");
sb.append("SUM(DECODE(PROC_GB,'1',NVL(A.DEPOSITAMT,0),");
sb.append("NVL(A.DEPOSITAMT,0)");
sb.append("*");
sb.append("-1))");
sb.append("AS");
sb.append("BASE_DEPOSIT,");
sb.append("0");
sb.append("AS");
sb.append("MGRANT_DEPOSIT,");
sb.append("0");
sb.append("AS");
sb.append("MUSE_DEPOSIT");
sb.append("FROM");
sb.append("TDEPOSIT");
sb.append("A");
sb.append("WHERE");
sb.append("A.PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.PROC_YN");
sb.append("=");
sb.append("'1'");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("TRUNC(TO_DATE('2014/01/01','YYYY/MM/DD'))");
sb.append("AS");
sb.append("PROC_DATE,");
sb.append("SUM(DECODE(PROC_GB,'1',NVL(A.USE_AMT,0)");
sb.append("*");
sb.append("-1,");
sb.append("NVL(A.USE_AMT,0)))");
sb.append("AS");
sb.append("BASE_DEPOSIT,");
sb.append("0");
sb.append("AS");
sb.append("MGRANT_DEPOSIT,");
sb.append("0");
sb.append("AS");
sb.append("MUSE_DEPOSIT");
sb.append("FROM");
sb.append("TDEPOSITUSE");
sb.append("A");
sb.append("WHERE");
sb.append("A.PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.PROC_YN");
sb.append("=");
sb.append("'1'");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("TRUNC(A.PROC_DATE),");
sb.append("0,");
sb.append("DECODE(A.PROC_GB,");
sb.append("'1',");
sb.append("NVL(A.DEPOSITAMT,");
sb.append("0),");
sb.append("NVL(A.DEPOSITAMT,");
sb.append("0)");
sb.append("*");
sb.append("-1),");
sb.append("0");
sb.append("FROM");
sb.append("TDEPOSIT");
sb.append("A");
sb.append("WHERE");
sb.append("A.PROC_DATE");
sb.append(">=");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("A.PROC_YN");
sb.append("=");
sb.append("'1'");
sb.append("UNION");
sb.append("ALL");
sb.append("SELECT");
sb.append("TRUNC(A.PROC_DATE),");
sb.append("0,");
sb.append("0,");
sb.append("DECODE(A.PROC_GB,");
sb.append("'1',");
sb.append("NVL(A.USE_AMT,");
sb.append("0)");
sb.append("*");
sb.append("-1,");
sb.append("NVL(A.USE_AMT,");
sb.append("0))");
sb.append("FROM");
sb.append("TDEPOSITUSE");
sb.append("A");
sb.append("WHERE");
sb.append("A.PROC_DATE");
sb.append(">=");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.PROC_DATE");
sb.append("<");
sb.append("TO_DATE('2014/01/01',");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("A.PROC_YN");
sb.append("=");
sb.append("'1'");
sb.append(")");
sb.append("A");
sb.append("GROUP");
sb.append("BY");
sb.append("A.PROC_DATE");
sb.append("ORDER");
sb.append("BY");
sb.append("A.PROC_DATE");
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
                new Header("Deposit Report (DepositDaily)"
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
