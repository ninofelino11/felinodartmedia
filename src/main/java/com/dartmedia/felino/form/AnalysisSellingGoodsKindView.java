package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisSellingGoodsKind")
public class AnalysisSellingGoodsKindView extends MVerticalLayout implements View {
//AnalysisSellingGoodsKindSvc data=new AnalysisSellingGoodsKindSvc();
    @PostConstruct
    public void initComponent() {
        addComponents( 
                new Header("AnalysisSellingGoodsKind")
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
