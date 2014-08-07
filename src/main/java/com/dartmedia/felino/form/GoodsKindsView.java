package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
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
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**L Grp. Code <p:selectOneListbox id="basic" value="#{selectOneView.option}">        </p:selectOneListbox>//select distinct lgroup, lgroup_name from tgoodskinds; **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
StringBuffer sb2 = new StringBuffer();
sb.append(" select distinct mgroup, mgroup_name from tgoodskinds where lgroup='10' ");
sb1.append(" select distinct sgroup, sgroup_name from tgoodskinds where lgroup='10' and mgroup = '01'");
sb2.append(" select distinct dgroup, dgroup_name from tgoodskinds where lgroup='10' and mgroup = '01' and sgroup ='01' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
//-------------------- Header  ------------------------------



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
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             BaseEntity.user,BaseEntity.pass,2,5);
             SQLContainer container;
             container = new SQLContainer(new FreeformQuery(
             sb.toString(),connectionPool,"mgroup"));
             MTable table = new MTable();
             table.setContainerDataSource(container);
             table.setColumnHeaders("Code","Name Of Group");
             
             container = new SQLContainer(new FreeformQuery(
             sb1.toString(),connectionPool,"sgroup"));
             MTable table1 = new MTable();
             table1.setContainerDataSource(container);
             table1.setColumnHeaders("Code","Name Of Group");
             
             container = new SQLContainer(new FreeformQuery(
             sb2.toString(),connectionPool,"dgroup"));
             MTable table2 = new MTable();
             table2.setContainerDataSource(container);
             table2.setColumnHeaders("Code","Name Of Group");
             
             
            
            GridLayout gridtoolbar = new GridLayout(1, 4); 
            toolbar.addComponent(gridtoolbar);
            gridtoolbar.addComponent(new Label("L Group Code"));
            gridtoolbar.addComponent(new ComboBox());
            gridtoolbar.addComponent(new Button("Print All"));
            gridtoolbar.addComponent(new Button("Excell"));

               
    table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
              GridLayout grid = new GridLayout(4, 4);
               addComponents(grid);
              grid.addComponents(table);
              grid.addComponents(table1);
              grid.addComponents(table2);
 } catch (SQLException e) {
     e.printStackTrace();
  Notification.show(e.getMessage());
     RichTextArea rtarea = new RichTextArea();
     rtarea.setValue(sb.toString());
      addComponents(rtarea);
}
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
