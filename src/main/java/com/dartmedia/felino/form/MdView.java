package com.dartmedia.felino.form;
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
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Md")
public class MdView extends MVerticalLayout implements View {
//MdSvc data=new MdSvc(); 
// ada service    
    @PostConstruct
    public void initComponent() {
// @Inject
//TbrandForm form;
/**MD <p:inputText />
//select md_code, md_name from tmd order by md_code**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT /*goodsbase.xml:manage.goodsbase.selectMdList by MdController*/");
sb.append("A.MD_CODE,A.MD_NAME,A.USE_YN FROM TMD A");
sb.append("WHERE A.MD_CODE LIKE ''");
sb.append("||");
sb.append("'%';");

sb.append(" SELECT /* goodsbase.xml:manage.goodsbase.selectMdlinkList by MdController*/");
sb.append(" A.USER_ID,B.USER_NAME,A.MD_CODE,TO_CHAR(A.START_DATE)");
sb.append(" 'YYYY/MM/DD')");
sb.append(" AS");
sb.append(" START_DATE");
sb.append(" TO_CHAR(A.END_DATE");
sb.append(" 'YYYY/MM/DD')");
sb.append(" AS");
sb.append(" END_DATE");
sb.append(" A.MAIN_YN");
sb.append(" A.USE_YN");
sb.append(" A.RATE");
sb.append(" FROM");
sb.append(" TMDLINK");
sb.append(" A");
sb.append(" TUSER B");
sb.append(" WHERE");
sb.append(" A.USER_ID");
sb.append(" =");
sb.append(" B.USER_ID");
sb.append(" AND");
sb.append(" A.MD_CODE");
sb.append(" =");
sb.append(" '0006'");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
//toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
//-toolbar.addComponent(new PopupDateField("My Date"));
toolbar.addComponent(new TextField("MD"));
toolbar.addComponent(new TextField(""));

//-toolbar.addComponent(new ComboBox("My ComboBox"));
//-toolbar.addComponent(new Button("My ComboBox"));
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
                new Header("MD Management (Md")
        );
        addComponents(toolmenu);
        MHorizontalLayout isicontents;
        isicontents = new MHorizontalLayout();
        addComponents(isicontents);
        

        
MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("MD Code", String.class,  null);
table.addContainerProperty("Md Name", String.class,  null);
table.addContainerProperty("Use", String.class,  null);


MTable table1=new MTable("Md Link Sheet");
//-------------------- Header Table ---judul untuk table----------
table1.addContainerProperty("No", String.class,  null);
table1.addContainerProperty("UserId", String.class,  null);
table1.addContainerProperty("UserName", String.class,  null);
table1.addContainerProperty("MainMd", String.class,  null);
table1.addContainerProperty("uSE", String.class,  null);





//-------------------- Header Table ------------------------------ 
        isicontents.addComponents(table);  
        isicontents.addComponents(table1);  
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
