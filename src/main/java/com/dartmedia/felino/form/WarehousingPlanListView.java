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
@CDIView("WarehousingPlanList")
public class WarehousingPlanListView extends MVerticalLayout implements View {
//WarehousingPlanListSvc data=new WarehousingPlanListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Term <p:calendar /> ~ <p:calendar />                               Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />             Receipt No <p:inputText />             PO No <p:inputText />                  **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* entpin.xml : logistics.entpin.selectWarehousingPlanList by WarehousingPlanList */");
sb.append("                   TO_CHAR(A.CTRL_IN_DATE, 'YYYY/MM/DD') CTRL_IN_DATE, ");
sb.append("                 D.ENTP_NAME, ");
sb.append("                 F.LGROUP, ");
sb.append("                 F.MGROUP, ");
sb.append("                 F.GOODS_CODE, ");
sb.append("                 C.GOODS_NAME, ");
sb.append("                 B.GOODSDT_CODE, ");
sb.append("                 C.GOODSDT_INFO, ");
sb.append("                 B.BALJU_QTY, ");
sb.append("                 B.ENTP_CONF_QTY, ");
sb.append("                 E.MD_NAME, ");
sb.append("                 A.BALJU_NO, ");
sb.append("                 TO_CHAR(A.LOOK_DATE, 'YYYY/MM/DD') LOOK_DATE  ");
sb.append("            FROM TBALJUM     A, ");
sb.append("                 TBALJUDT    B, ");
sb.append("                 TGOODSDT    C, ");
sb.append("                 TENTERPRISE D, ");
sb.append("                 TMD         E, ");
sb.append("                 TGOODS      F ");
sb.append("           WHERE A.CTRL_IN_DATE  >= TO_DATE('2014/01/01', 'YYYY/MM/DD') ");
sb.append("             AND A.CTRL_IN_DATE  <  TO_DATE('2014/08/01', 'YYYY/MM/DD') + 1 ");
sb.append("             AND A.WH_CODE       =  '001' ");
sb.append("             AND A.ENTP_CODE  =  '100002' ");
sb.append("             AND F.LGROUP      LIKE '%%' ");
sb.append("             AND F.MGROUP      LIKE '%%' ");
sb.append("             AND F.SGROUP      LIKE '%%' ");
sb.append("             AND F.DGROUP      LIKE '%%' ");
sb.append("             AND A.IN_END_YN     =  '0' ");
sb.append("             AND A.CANCEL_YN     =  '0' ");
sb.append("             AND A.BALJU_NO      =  B.BALJU_NO ");
sb.append("             AND A.ENTP_CODE     =  D.ENTP_CODE ");
sb.append("             AND B.GOODS_CODE    =  C.GOODS_CODE ");
sb.append("             AND B.GOODSDT_CODE  =  C.GOODSDT_CODE ");
sb.append("             AND B.GOODS_CODE    =  F.GOODS_CODE ");
sb.append("             AND A.MD_CODE         =  E.MD_CODE               ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new Button("Receipt Form Internal"));
toolbar.addComponent(new Button("Receipt Form Vendor"));


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
                new Header("Expected Receipt Report (WarehousingPlanList)"
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
             "dartmedia", "dartmedia",2,5);
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
