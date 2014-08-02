package com.dartmedia.felino.form;
import com.dartmedia.felino.Treceiptsbank;
import com.dartmedia.felino.TreceiptsbankFacade;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("ReceiptBank")
public class ReceiptBankView extends MVerticalLayout implements View {
//ReceiptBankSvc data=new ReceiptBankSvc();
@Inject   TreceiptsbankFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Bank code <p:inputText />Bank name <p:inputText /><p:selectOneListbox/>            <f:selectItem itemLabel="All" itemValue="" />            <f:selectItem itemLabel="Use" itemValue="1" />            <f:selectItem itemLabel="Unused" itemValue="0" /> </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("/* Bank Code, Bank Name, Seq., Account No, Service Code, Branch Name, Use */");
sb.append("        SELECT /*payment.xml : custcenter.payment.selectReceiptBank by ReceiptBank*/");
sb.append("            BANK_CODE,");
sb.append("            BANK_SEQ,");
sb.append("            BANK_NAME,");
sb.append("            BANK_DEPOSIT_NO,");
sb.append("            SERV_CODE,");
sb.append("            BANK_ADDR,");
sb.append("            BANK_BRANCH,");
sb.append("            BANK_MAN_NAME,");
sb.append("            BANK_MAN_DDD,");
sb.append("            BANK_MAN_TEL1,");
sb.append("            BANK_MAN_TEL2,");
sb.append("            BANK_MAN_TEL3,");
sb.append("            USE_YN,");
sb.append("            REMARK,");
sb.append("            TO_CHAR(INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE,");
sb.append("            INSERT_ID,");
sb.append("            VACCOUNT_YN,");
sb.append("            TYPE");
sb.append("        FROM ");
sb.append("            TRECEIPTSBANK");
sb.append("        WHERE BANK_CODE LIKE '%%'");
sb.append("        AND USE_YN        LIKE '%1%' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Bank Code/Name"));
toolbar.addComponent(new CheckBox("ALL"));
toolbar.addComponent(new CheckBox("USE"));
toolbar.addComponent(new CheckBox("UNUSED"));



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
                new Header("Receipt Bank (ReceiptBank)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Treceiptsbank> findAll = cf.findAll();
MTable<Treceiptsbank> table=new MTable<Treceiptsbank>(Treceiptsbank.class);
//.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Treceiptsbank>() {
    @Override
   public void valueChange(MValueChangeEvent<Treceiptsbank> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
    });
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
