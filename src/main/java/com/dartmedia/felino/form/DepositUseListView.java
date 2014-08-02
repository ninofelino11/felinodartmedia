package com.dartmedia.felino.form;
import com.dartmedia.felino.Tdeposituse;
import com.dartmedia.felino.TdeposituseFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("DepositUseList")
public class DepositUseListView extends MVerticalLayout implements View {
//DepositUseListSvc data=new DepositUseListSvc();
@Inject   TdeposituseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Use date <p:calendar /> ~ <p:calendar />Customer <p:inputText />**/
StringBuilder sb = new StringBuilder();
sb.append(" SELECT /* deposit.xml : custcenter.deposit.selectDepositUse by DepositUseList */");
sb.append("                   A.CUST_NO,   ");
sb.append("                 B.CUST_NAME,   ");
sb.append("                 A.USE_SEQ,   ");
sb.append("                 A.DEPOSITAMT_GB,   ");
sb.append("                 A.PROC_GB,   ");
sb.append("                 A.PROC_YN,   ");
sb.append("                 DECODE(A.PROC_GB, '1', A.USE_AMT, '0' -1*A.USE_AMT)  AS USE_AMT,   ");
sb.append("                 TO_CHAR(A.PROC_DATE, 'yyyy/mm/dd') AS PROC_DATE,   ");
sb.append("                 C.USER_NAME,   ");
sb.append("                 A.ORDER_NO,   ");
sb.append("                 A.TRANS_CUST_NO   ");
sb.append("            FROM TDEPOSITUSE A,   ");
sb.append("                 TCUSTOMER   B,   ");
sb.append("                 TUSER       C   ");
sb.append("           WHERE A.PROC_ID = C.USER_ID                          ");
sb.append("             AND A.CUST_NO = B.CUST_NO   ");
sb.append("             AND A.PROC_YN = '1'   ");
sb.append("             AND A.USE_AMT <> 0   ");
sb.append("            AND A.PROC_DATE BETWEEN TO_DATE('2013/06/01' ||' 00:00:00', 'YYYY/MM/DD hh24 ");
sb.append(":");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Customer"));


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
                new Header("Customer Deposit Use (DepositUseList)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tdeposituse> findAll = cf.findAll();
MTable<Tdeposituse> table=new MTable<Tdeposituse>(Tdeposituse.class).withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tdeposituse>() {
    @Override
    public void valueChange(MValueChangeEvent<Tdeposituse> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
addComponents(table);
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
