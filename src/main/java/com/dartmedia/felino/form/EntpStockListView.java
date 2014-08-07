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
@CDIView("EntpStockList")
public class EntpStockListView extends MVerticalLayout implements View {
//EntpStockListSvc data=new EntpStockListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Vendor   <p:inputText></p:inputText>Item <p:inputText></p:inputText>Type <p:selectOneRadio>   <f:selectItem itemValue="Item+unit" /><f:selectItem itemValue="Item" />  </p:selectOneRadio>   **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("entpdelivery.xml");
sb.append(":");
sb.append("logistics.entpdelivery.selectEntpStockListGoods");
sb.append("by");
sb.append("EntpStockList");
sb.append("*/");
sb.append("A.ENTP_CODE");
sb.append("AS");
sb.append("ENTP_CODE,");
sb.append("D.ENTP_NAME");
sb.append("AS");
sb.append("ENTP_NAME,");
sb.append("A.GOODS_CODE");
sb.append("AS");
sb.append("GOODS_CODE,");
sb.append("B.GOODS_NAME");
sb.append("AS");
sb.append("GOODS_NAME,");
sb.append("A.GOODSDT_CODE");
sb.append("AS");
sb.append("GOODSDT_CODE,");
sb.append("C.GOODSDT_INFO");
sb.append("AS");
sb.append("GOODSDT_INFO,");
sb.append("A.AQTY");
sb.append("AS");
sb.append("AQTY,");
sb.append("A.BQTY");
sb.append("AS");
sb.append("BQTY");
sb.append("FROM");
sb.append("TSCMSTOCK");
sb.append("A,");
sb.append("TGOODS");
sb.append("B,");
sb.append("TGOODSDT");
sb.append("C,");
sb.append("TENTERPRISE");
sb.append("D");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("C.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODSDT_CODE");
sb.append("=");
sb.append("C.GOODSDT_CODE");
sb.append("AND");
sb.append("A.ENTP_CODE");
sb.append("=");
sb.append("D.ENTP_CODE");
sb.append("AND");
sb.append("A.ENTP_CODE");
sb.append("LIKE");
sb.append("'%100002%'");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("LIKE");
sb.append("'%%'");
sb.append("ORDER");
sb.append("BY");
sb.append("A.ENTP_CODE,");
sb.append("A.GOODS_CODE,");
sb.append("A.GOODSDT_CODE");
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
                new Header("Entp Stock List (EntpStockList)"
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
