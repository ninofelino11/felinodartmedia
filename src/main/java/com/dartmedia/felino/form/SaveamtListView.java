package com.dartmedia.felino.form;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("SaveamtList")
public class SaveamtListView extends MVerticalLayout implements View {
//SaveamtListSvc data=new SaveamtListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Usable point <p:inputText /> ~ <p:inputText />**/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("SELECT /* saveamt.xml : custcenter.save.selectSaveamtList by SaveamtList */");
sb.append("                 A.CUST_NO        AS CUST_NO,  ");
sb.append("                C.RECEIVER       AS CUST_NAME,  ");
sb.append("                C.TEL AS TEL,  ");
sb.append("                B.USABLE_SAVEAMT AS USABLE_AMT,  ");
sb.append("                B.GRANT_SAVEAMT  AS GRANT_AMT,  ");
sb.append("                B.RETURN_SAVEAMT AS RETURN_AMT,  ");
sb.append("                A.USE_SAVEAMT    AS USE_AMT    ");
sb.append("          FROM  TCUSTSYSTEM  A,  ");
sb.append("                VSAVEGET     B,  ");
sb.append("                TRECEIVER    C  ");
sb.append("          WHERE A.CUST_NO        = B.CUST_NO  ");
sb.append("            AND A.CUST_NO        = C.CUST_NO  ");
sb.append("            AND C.DEFAULT_YN     = '1'  ");
sb.append("            AND B.USABLE_SAVEAMT BETWEEN TO_NUMBER(0) AND TO_NUMBER(10000)  ");
sb.append("            AND B.USABLE_SAVEAMT > 0  ");
sb.append("          ORDER BY A.CUST_NO ");
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
                new Header("Customer Point (SaveamtList)"
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
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"AD_MENU_ID"));
             // MTable table= new MTable("MENU",container);
               TreeTable table = new TreeTable("Menu", container);
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
