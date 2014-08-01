package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("OrderInput")
public class OrderInputView extends MVerticalLayout implements View {
//OrderInputSvc data=new OrderInputSvc();
    @PostConstruct
    public void initComponent() {

StringBuffer sb = new StringBuffer();
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
MTable table=new MTable();
MTable table1=new MTable();
MHorizontalLayout toolbar = new MHorizontalLayout();

toolbar.addComponent(new Button("Custumer"));
toolbar.addComponent(new Button("Order"));
toolbar.addComponent(new Button("Reorder"));
toolbar.addComponent(new Button("Ordering"));
toolbar.addComponent(new Button("C/S Management"));
toolbar.addComponent(new Button("Ordering"));
toolbar.addComponent(new Button("Counsel Receipt"));
toolbar.addComponent(new Button("Coupon"));



/**|<p:separator/>

<p:commandButton value="Input"/>
<p:commandButton value="Delete"/>
<p:commandButton value="PGM Item"/>
|<p:separator/>
Message <p:inputText/>



**/



//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
//-------------------- Header  ------------------------------
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Item Code", String.class,  null);
table.addContainerProperty("Item Name", String.class,  null);
table.addContainerProperty("Sale Price", String.class,  null);
table.addContainerProperty("Qty", String.class,  null);
table.addContainerProperty("Order Amt", String.class,  null);
table.addContainerProperty("Dc%", String.class,  null);
table.addContainerProperty("DC", String.class,  null);
table.addContainerProperty("Charge", String.class,  null);
table.addContainerProperty("Coupon", String.class,  null);

//-------------------- Header Table ------------------------------ 
table1.addContainerProperty("No", String.class,  null);
table1.addContainerProperty("Item Type", String.class,  null);
table1.addContainerProperty("Item Name", String.class,  null);
table1.addContainerProperty("Unit Info", String.class,  null);
table1.addContainerProperty("Weight", String.class,  null);
table1.addContainerProperty("Qty", String.class,  null);
table1.addContainerProperty("Point", String.class,  null);
table1.addContainerProperty("Delivery", String.class,  null);
table1.addContainerProperty("Shipment Ty", String.class,  null);
table1.addContainerProperty("Wh Code", String.class,  null);









MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents( 
                new Header("OrderInput")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        addComponents(table);
        addComponents(new TextField("Message"));
        
        addComponents(table1);
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
