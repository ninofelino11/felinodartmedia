package com.dartmedia.felino.form;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsDescribeBase")
public class GoodsDescribeBaseView extends MVerticalLayout implements View {
//GoodsDescribeBaseSvc data=new GoodsDescribeBaseSvc();
    @PostConstruct
    public void initComponent() {
/**Description Code/Title <p:inputText/>
//select describe_code, describe_title from TDESCRIBECODE**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("goodsbase.xml");
sb.append(":");
sb.append("manage.goodsbase.selectGoodsDescribeBaseList");
sb.append("by");
sb.append("GoodsDescribeBaseController");
sb.append("*/");
sb.append("A.DESCRIBE_CODE");
sb.append("A.DESCRIBE_TITLE");
sb.append("A.WEB_FLAG");
sb.append("A.REQUIRED_YN");
sb.append("TO_CHAR(A.INSERT_DATE");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("INSERT_DATE");
sb.append("A.INSERT_ID");
sb.append("TO_CHAR(A.MODIFY_DATE");
sb.append("'YYYY/MM/DD");
sb.append("HH24:MI:SS')");
sb.append("MODIFY_DATE");
sb.append("A.MODIFY_ID");
sb.append("A.SORT_SEQ");
sb.append("A.USE_YN");
sb.append("FROM");
sb.append("TDESCRIBECODE");
sb.append("A");
sb.append("WHERE");
sb.append("A.DESCRIBE_CODE");
sb.append("LIKE");
sb.append("'101'");
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
//MTable table=new MTable();














MHorizontalLayout sidebar = new MHorizontalLayout();

MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
//-toolbar.addComponent(new TextField("Indv.Query"));
//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
//-------------------- Header  ------------------------------
//-------------------- Header Table ---judul untuk table----------

//-------------------- Header Table ------------------------------ 
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));
        addComponents( 
                new Header("GoodsDescribeBase")
        );
        addComponents(toolmenu);
        addComponents(toolbar); 
        addComponents(sidebar);
        
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);        
       // gSqlContainer pool=new gSqlContainer();
             SQLContainer container;
            //  container = new SQLContainer(new FreeformQuery(
             // fsql,connectionPool,"BRAND_CODE"));
//             TreeTable table = new TreeTable("Menu", container);
            // table.setWidth("200px");
                 
  //             sidebar.addComponents(table); 
              //  sidebar.addComponents(form); 
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
