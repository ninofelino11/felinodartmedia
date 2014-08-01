package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
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
@CDIView("GoodsList")
public class GoodsListView extends MVerticalLayout implements View {
//GoodsListSvc data=new GoodsListSvc();
    @PostConstruct
    public void initComponent() {
        addComponents( 
                new Header("GoodsList")
        );
         MTable table= new MTable();
        table.addContainerProperty("No", String.class,  null);
        table.addContainerProperty("Item Code",  String.class,  null);
        table.addContainerProperty("Item Name",       Integer.class, null); 
        table.addContainerProperty("Master Code",       Integer.class, null); 
        table.addContainerProperty("Gift",       Integer.class, null); 
        table.addContainerProperty("Paid",       Integer.class, null);
        table.addContainerProperty("Sale",       Integer.class, null);
        table.addContainerProperty("Type",       Integer.class, null);
        table.addContainerProperty("Shipment",       Integer.class, null);
        table.addContainerProperty("LGRP",       Integer.class, null);
        table.addContainerProperty("MGRP",       Integer.class, null);
        table.addContainerProperty("SGRP",       Integer.class, null);
        table.addContainerProperty("DGRP",       Integer.class, null);
        
        table.setColumnHeader("lastname", "Name");
   
        MHorizontalLayout toolmenu; 
        toolmenu = new MHorizontalLayout();
        toolmenu.addComponent(new Button("Ret"));
        toolmenu.addComponent(new Button("Ins"));
        toolmenu.addComponent(new Button("Save"));
        toolmenu.addComponent(new Button("Print"));
        toolmenu.addComponent(new Button("XLS"));
        
        
        
        
        MHorizontalLayout toolbar; 
        
        toolbar = new MHorizontalLayout();
        toolbar.addComponent(new CheckBox("Indv.Query"));
        toolbar.addComponent(new TextField("Item Code"));
        toolbar.addComponent(new CheckBox("Item Code"));
        toolbar.addComponent(new TextField("Item Name"));
        toolbar.addComponent(new TextField("Master Code"));
        toolbar.addComponent(new CheckBox("Item Name"));
        toolbar.addComponent(new CheckBox("Master Code"));
        
        addComponent(toolmenu);
        addComponent(toolbar);
        
        addComponent(table);
        
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
