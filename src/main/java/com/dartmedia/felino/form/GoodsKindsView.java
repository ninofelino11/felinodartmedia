package com.dartmedia.felino.form;
import com.dartmedia.felino.Tgoodskinds;
import com.dartmedia.felino.TgoodskindsFacade;
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
@CDIView("GoodsKinds")
public class GoodsKindsView extends MVerticalLayout implements View {
//GoodsKindsSvc data=new GoodsKindsSvc();
@Inject   TgoodskindsFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**L Grp. Code <p:selectOneListbox id="basic" value="#{selectOneView.option}">        </p:selectOneListbox>//select distinct lgroup, lgroup_name from tgoodskinds; **/
StringBuffer sb = new StringBuffer();
sb.append(" select distinct mgroup, mgroup_name from tgoodskinds where lgroup='10' ");
sb.append(" select distinct sgroup, sgroup_name from tgoodskinds where lgroup='10' and mgroup = '01'");
sb.append(" select distinct dgroup, dgroup_name from tgoodskinds where lgroup='10' and mgroup = '01' and sgroup ='01' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("L Group Code"));
toolbar.addComponent(new Button("Print All"));



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
                new Header("Item Group Code (GoodsKinds)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tgoodskinds> findAll = cf.findAll();
MTable<Tgoodskinds> table=new MTable<Tgoodskinds>(Tgoodskinds.class);
//.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tgoodskinds>() {
    @Override
    public void valueChange(MValueChangeEvent<Tgoodskinds> event) {
    Notification.show("ss");
 //   form.setEntity(event.getValue());
    }
    });
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
