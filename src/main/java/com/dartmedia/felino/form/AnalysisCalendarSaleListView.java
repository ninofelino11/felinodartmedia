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
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisCalendarSaleList")
public class AnalysisCalendarSaleListView extends MVerticalLayout implements View {
//AnalysisCalendarSaleListSvc data=new AnalysisCalendarSaleListSvc();
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/****/
StringBuffer sb = new StringBuffer();
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Month"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new CheckBox("Order"));
toolbar.addComponent(new CheckBox("Net Order"));
toolbar.addComponent(new CheckBox("Delivery Complete"));
toolbar.addComponent(new ComboBox("Order Media"));
toolbar.addComponent(new ComboBox("Ad. Type"));
toolbar.addComponent(new TextField("Ad Media"));
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
                new Header("Sales Report by Media[Calendar] (AnalysisCalendarSaleList)"
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
