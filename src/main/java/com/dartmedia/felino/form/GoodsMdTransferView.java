package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import org.vaadin.maddon.fields.MTable;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsMdTransfer")
public class GoodsMdTransferView extends MVerticalLayout implements View {
//GoodsMdTransferSvc data=new GoodsMdTransferSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**L Group: <p:inputText />  //select distinct lgroup, lgroup_name from tgoodskinds order by lgroup M Group: <p:inputText />//select distinct mgroup, mgroup_name from tgoodskinds where LGROUP = '15' order by mgroupS Group: <p:inputText />//select distinct sgroup, sgroup_name from tgoodskinds where LGROUP = '15' and MGROUP = '01' order by sgroupD Group: <p:inputText />//select distinct dgroup, dgroup_name from tgoodskinds where LGROUP = '15' and MGROUP = '01' and SGROUP = '04' order by dgroupItem: <p:inputText />//select goods_code,goods_name, md_code from tgoods where LGROUP = '15' and MGROUP = '01' and SGROUP = '04' and DGROUP = 01 order by goods_nameMD: <p:inputText />//select md_code, md_name from tmd where md_code = '0002'MD: <p:inputText />//select md_code, md_name from tmd**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("select");
sb.append("distinct");
sb.append("lgroup,");
sb.append("lgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("order");
sb.append("by");
sb.append("lgroup");
sb.append("select");
sb.append("distinct");
sb.append("mgroup,");
sb.append("mgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("order");
sb.append("by");
sb.append("mgroup");
sb.append("select");
sb.append("distinct");
sb.append("sgroup,");
sb.append("sgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("order");
sb.append("by");
sb.append("sgroup");
sb.append("select");
sb.append("distinct");
sb.append("dgroup,");
sb.append("dgroup_name");
sb.append("from");
sb.append("tgoodskinds");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("and");
sb.append("SGROUP");
sb.append("=");
sb.append("'04'");
sb.append("order");
sb.append("by");
sb.append("dgroup");
sb.append("select");
sb.append("goods_code,goods_name,");
sb.append("md_code");
sb.append("from");
sb.append("tgoods");
sb.append("where");
sb.append("LGROUP");
sb.append("=");
sb.append("'15'");
sb.append("and");
sb.append("MGROUP");
sb.append("=");
sb.append("'01'");
sb.append("and");
sb.append("SGROUP");
sb.append("=");
sb.append("'04'");
sb.append("and");
sb.append("DGROUP");
sb.append("=");
sb.append("01");
sb.append("order");
sb.append("by");
sb.append("goods_name");
sb.append("select");
sb.append("md_code,");
sb.append("md_name");
sb.append("from");
sb.append("tmd");
sb.append("where");
sb.append("md_code");
sb.append("=");
sb.append("'0002'");
sb.append("select");
sb.append("md_code,");
sb.append("md_name");
sb.append("from");
sb.append("tmd");
sb.append("where");
sb.append("md_code");
sb.append("=");
sb.append("'0002'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
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
                new Header("MD Transfer (GoodsMdTransfer)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
//        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
//List<Tenterprise> findAll = cf.findAll();
//MTable<Tenterprise> table=new MTable<Tenterprise>(Tenterprise.class).withProperties("entpName");
//table.setBeans(findAll);
//table.addMValueChangeListener(new MValueChangeListener<Tdescribecode>() {
//    @Override
//    public void valueChange(MValueChangeEvent<Tdescribecode> event) {
//    Notification.show("ss");
//    form.setEntity(event.getValue());
//    }
//    });
//table.addContainerProperty("No", String.class,  null);
//-------------------- Header Table ------------------------------
//   isicontents.addComponents(table);
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver",BaseEntity.jdbc,
             BaseEntity.user,BaseEntity.pass,2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               MTable table = new MTable();
               table.setContainerDataSource(container);
table.addMValueChangeListener(new MValueChangeListener() {
    @Override
    public void valueChange(MValueChangeEvent event) {
    Notification.show("ss");
//    form.setEntity(event.getValue());
    }
   });
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
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
