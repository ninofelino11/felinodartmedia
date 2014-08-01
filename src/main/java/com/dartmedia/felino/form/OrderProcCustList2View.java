package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("OrderProcCustList2")
public class OrderProcCustList2View extends MVerticalLayout implements View {
//OrderProcCustList2Svc data=new OrderProcCustList2Svc();
    @PostConstruct
    public void initComponent() {
        addComponents( 
                new Header("OrderProcCustList2")
        );
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
