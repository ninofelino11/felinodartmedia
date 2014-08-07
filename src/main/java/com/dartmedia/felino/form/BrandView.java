package com.dartmedia.felino.form;
import com.cware.back.service.manage.BrandSvc;
import com.dartmedia.felino.fgetsql;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Brand")
public class BrandView extends MVerticalLayout implements View {
@Inject 
  TbrandForm form; 

    BrandSvc data=new BrandSvc();

    @PostConstruct
    public void initComponent() {
/**Brand Code/Name: <p:inputText/>
->select brand_code,brand_name from tbrand**/
StringBuilder sb = new StringBuilder();
sb.append("SELECT ");
sb.append("/*goodsbase.xml");
sb.append("manage.goodsbase.selectBrandList");
sb.append("by sb.append(BrandController*/");
sb.append(" BRAND_CODE,BRAND_NAME,BRAND_IMAGE,BRAND_DESC,URL");
sb.append(" INSERT_ID TO_CHAR(INSERT_DATE('YYYY/MM/DDHH24:MI:SS')");
sb.append(" INSERT_DATE,MODIFY_ID,TO_CHAR(MODIFY_DATE,'YYYY/MM/DD,'HH24:MI:SS')");
sb.append(" MODIFY_DATE AS IMAGE ");
//sb.append(" AS IMAGE_SHOP");
sb.append(" FROM TBRAND WHERE BRAND_CODE >'0000' ");
//sb.append("AND BRAND_CODE <  '9999' AND BRAND_CODE LIKE '0001' || '%' AND BRAND_NAME LIKE ''|| '%'");
String fsql = "select BRAND_CODE,BRAND_NAME,BRAND_IMAGE,BRAND_DESC,URL FROM TBRAND WHERE BRAND_CODE >'0000'";

//




//MTable table=new MTable();
MHorizontalLayout toolbar = new MHorizontalLayout();
//toolbar.addComponent(new CheckBox("Indv.Query"



//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
toolbar.addComponent(new TextField("Brand Code Name"));
toolbar.addComponent(new TextField(""));
toolbar.addComponent(new TextField("select brand_code,brand_name "));

toolbar.addComponent(new ComboBox("ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
//-------------------- Header  ------------------------------
//-------------------- Header Table ---judul untuk table----------
//table.addContainerProperty("No", String.class,  null);
//table.addContainerProperty("Brand Code", String.class,  null);
//table.addContainerProperty("Brand Name", String.class,  null);
//-------------------- Header Table ------------------------------ 
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Del"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));

  


addComponents( 
                new Header("Brand Management(Brand)")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        MHorizontalLayout sidebar = new MHorizontalLayout();
        addComponents(sidebar);
 try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);        
       // gSqlContainer pool=new gSqlContainer();
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              fsql,connectionPool,"BRAND_CODE"));
             MTable table = new MTable();
             table.setContainerDataSource(container);
            // table.setWidth("200px");
                 
               sidebar.addComponents(table); 
                sidebar.addComponents(form); 
 } catch (SQLException e) {
     e.printStackTrace();
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
