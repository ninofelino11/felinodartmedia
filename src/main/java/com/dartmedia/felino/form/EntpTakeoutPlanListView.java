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
@CDIView("EntpTakeoutPlanList")
public class EntpTakeoutPlanListView extends MVerticalLayout implements View {
//EntpTakeoutPlanListSvc data=new EntpTakeoutPlanListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Expected date <p:calendar /> ~ <p:calendar />Warehouse <p:selectOneListbox id="basic"> </p:selectOneListbox>     Vendor <p:inputText />                   Type <p:selectOneListbox id="basic"> </p:selectOneListbox>    Item <p:inputText />                     **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* entpout.xml : logistics.entpout.selectEntpTakeoutPlanList by EntpTakeoutPlanList */");
sb.append("                   TO_CHAR(A.EOUT_CTRL_OUT_DATE, 'YYYY/MM/DD') EOUT_CTRL_OUT_DATE,");
sb.append("                 A.EOUT_QUEST_NO,");
sb.append("                    TO_CHAR(A.EOUT_CONFIRM_DATE, 'YYYY/MM/DD') EOUT_CONFIRM_DATE,");
sb.append("                    D.ENTP_NAME,");
sb.append("                 E.LGROUP,");
sb.append("                 E.MGROUP,");
sb.append("                 B.GOODS_CODE AS GOODS_CODE,");
sb.append("                 B.GOODSDT_CODE,");
sb.append("                 C.GOODS_NAME,");
sb.append("                 C.GOODSDT_INFO,");
sb.append("                 B.EOUT_QUEST_AQTY,");
sb.append("                 B.EOUT_QUEST_BQTY,");
sb.append("                 B.EOUT_CODE,");
sb.append("                 B.EOUT_NOTE, A.EOUT_GB");
sb.append("            FROM TENTPOUTQUESTM  A,");
sb.append("                 TENTPOUTQUESTDT B,");
sb.append("                 TGOODSDT        C,");
sb.append("                 TENTERPRISE     D,");
sb.append("                 TGOODS          E");
sb.append("           WHERE A.EOUT_CTRL_OUT_DATE   >= TO_DATE('2013/11/01' , 'YYYY/MM/DD')");
sb.append("                AND A.EOUT_CTRL_OUT_DATE   <  TO_DATE('2014/08/01' , 'YYYY/MM/DD') + 1");
sb.append("                AND A.WH_CODE              =  '001'");
sb.append("             AND A.ENTP_CODE          LIKE '%%'");
sb.append("             AND A.EOUT_GB              =  '0'");
sb.append("             AND E.LGROUP             LIKE '%%'");
sb.append("             AND E.MGROUP             LIKE '%%'");
sb.append("             AND E.SGROUP             LIKE '%%'");
sb.append("             AND E.DGROUP             LIKE '%%'");
sb.append("             AND A.EOUT_END_YN          =  '0'");
sb.append("             AND A.EOUT_CANCEL_YN       =  '0'");
sb.append("             AND A.EOUT_QUEST_NO        =  B.EOUT_QUEST_NO");
sb.append("             AND A.ENTP_CODE            =  D.ENTP_CODE");
sb.append("             AND B.GOODS_CODE           =  C.GOODS_CODE");
sb.append("             AND B.GOODSDT_CODE         =  C.GOODSDT_CODE");
sb.append("             AND B.GOODS_CODE           =  E.GOODS_CODE  ");
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
                new Header("Expected Take Out Report (EntpTakeoutPlanList)"
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
