package com.dartmedia.felino.form;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("EntpStockList")
public class EntpStockListView extends MVerticalLayout implements View {
//EntpStockListSvc data=new EntpStockListSvc();
    @PostConstruct
    public void initComponent() {
/**Vendor   <p:inputText></p:inputText>
Item <p:inputText></p:inputText>
Type <p:selectOneRadio>   
<f:selectItem itemValue="Item+unit" />
<f:selectItem itemValue="Item" />  </p:selectOneRadio>   
**/
        addComponents( 
                new Header("EntpStockList")
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
