package com.dartmedia.felino.form;
import com.dartmedia.felino.Tbank;
import com.dartmedia.felino.TbankFacade;
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
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("BankCode")
public class BankCodeView extends MVerticalLayout implements View {
//BankCodeSvc data=new BankCodeSvc();
@Inject  TbankFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Bank code <p:inputText />Bank name <p:inputText /><p:selectOneListbox/>            <f:selectItem itemLabel="All" itemValue="" />            <f:selectItem itemLabel="Use" itemValue="1" />            <f:selectItem itemLabel="Unused" itemValue="0" /> </p:selectOneListbox>**/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("/* Bank Code, Bank Name, Use, Remark */");
sb.append("        SELECT");
sb.append("            BANK_CODE,");
sb.append("            BANK_NAME,");
sb.append("            USE_YN,");
sb.append("            REMARK,              ");
sb.append("            INSERT_DATE,");
sb.append("            TO_CHAR(INSERT_DATE,'YYYY/MM/DD') AS INSERT_DATE");
sb.append("        FROM       ");
sb.append("            TBANK");
sb.append("        WHERE BANK_CODE LIKE '%002%'          ");
sb.append("        AND BANK_NAME LIKE '%BANK%'");
sb.append("        AND USE_YN         LIKE '%1%'");
sb.append("        ORDER BY BANK_CODE ASC;    ");
sb.append(" ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Bank Code/ Name"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new CheckBox("All"));
toolbar.addComponent(new CheckBox("Use"));
toolbar.addComponent(new CheckBox("Unused"));


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
                new Header("Bank Code (BankCode)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tbank> findAll = cf.findAll();
MTable<Tbank> table=new MTable<Tbank>(Tbank.class);
        //.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tbank>() {
    @Override
    public void valueChange(MValueChangeEvent<Tbank> event) {
    Notification.show("ss");
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
