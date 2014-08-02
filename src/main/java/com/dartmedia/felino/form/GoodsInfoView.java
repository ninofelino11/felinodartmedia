package com.dartmedia.felino.form;
import com.dartmedia.felino.Tgoodsinfom;
import com.dartmedia.felino.TgoodsinfomFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
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
@CDIView("GoodsInfo")
public class GoodsInfoView extends MVerticalLayout implements View {
//GoodsInfoSvc data=new GoodsInfoSvc();
@Inject   TgoodsinfomFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Category  <p:selectOneListbox>                          <f:selectItem itemlabel="Size"/>                          <f:selectItem itemlabel="Color"/>                          <f:selectItem itemlabel="Form"/>                          <f:selectItem itemlabel="Pattern"/>                 </p:selectOneListbox>//category dropdown: hardcode (S:Size, C:Color, F:Form, P:Pattern)    Sort Code <p:inputText/>Category <p:selectOneListbox>                  <f:selectItem itemlabel="SiZE"/>                 </p:selectOneListbox>Sort Code <p:inputText/>UseCategory Name<p:button/>Unit Info.InputDelete**/
StringBuffer sb = new StringBuffer();
sb.append(" SELECT    /* goods.xml : manage.goods.selectGoodsInfoList by GoodsInfoController */");
sb.append("                 A.CSPF_FLAG,");
sb.append("                SUBSTR(A.CSPF_GROUP,2,3) CSPF_GROUP ,");
sb.append("                A.CSPF_DESC,");
sb.append("                A.USE_YN,");
sb.append("                TO_CHAR(A.INSERT_DATE, 'YYYY/MM/DD HH24:MI:SS') INSERT_DATE,");
sb.append("                A.INSERT_ID");
sb.append("           FROM TGOODSINFOM A");
sb.append("          WHERE A.CSPF_FLAG LIKE 'S'");
sb.append("             AND A.CSPF_GROUP LIKE 'S400' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Category"));
toolbar.addComponent(new TextField("Sort Code"));
toolbar.addComponent(new ComboBox("Category"));
toolbar.addComponent(new TextField("Sort Code"));
toolbar.addComponent(new CheckBox("Use"));
toolbar.addComponent(new TextField("Category Name"));
toolbar.addComponent(new Button("Chose Color/Pattern"));

toolbar.addComponent(new Button("Input"));
toolbar.addComponent(new Button("Delete"));







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
                new Header("Unit Attribute Code (GoodsInfo)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//-------------------- Header Table ---judul untuk table----------
List<Tgoodsinfom> findAll = cf.findAll();
MTable<Tgoodsinfom> table=new MTable<Tgoodsinfom>(Tgoodsinfom.class);
//.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tgoodsinfom>() {
    @Override
    public void valueChange(MValueChangeEvent<Tgoodsinfom> event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
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
