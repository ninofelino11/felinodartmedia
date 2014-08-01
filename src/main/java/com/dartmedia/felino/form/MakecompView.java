package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.dartmedia.felino.koneksi;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Makecomp")
public class MakecompView extends MVerticalLayout implements View {
//MakecompSvc data=new MakecompSvc();
    @PostConstruct
    public void initComponent() {
        try {
            /**Manufacturer Code/Name <p:inputText />
             * //select makeco_code, makeco_name from tmakecomp
             **/
            StringBuffer sb = new StringBuffer();
            sb.append("select");
            sb.append("/*");
            sb.append("entp.xml");
            sb.append(":");
            sb.append("manage.entp.selectMakecompList");
            sb.append("by");
            sb.append("Makecomp");
            sb.append("*/");
            sb.append("A.MAKECO_CODE");
            sb.append("A.MAKECO_NAME");
            sb.append("A.MAKECO_POST");
            sb.append("A.MAKECO_POST_SEQ");
            sb.append("FUN_ADD_POSTADDR(A.MAKECO_POST");
            sb.append("A.MAKECO_POST_SEQ");
            sb.append("'')");
            sb.append("as");
            sb.append("ADDR");
            sb.append("A.MAKECO_ADDR");
            sb.append("A.MAKECO_DDD");
            sb.append("A.MAKECO_TEL1");
            sb.append("A.MAKECO_TEL2");
            sb.append("A.MAKECO_TEL3");
            sb.append("A.MAKECO_FAX1");
            sb.append("A.MAKECO_FAX2");
            sb.append("A.MAKECO_FAX3");
            sb.append("A.MAKECO_REMARK");
            sb.append("A.INSERT_DATE");
            sb.append("A.INSERT_ID");
            sb.append("A.MODIFY_DATE");
            sb.append("A.MODIFY_ID");
            sb.append("from");
            sb.append("TMAKECOMP");
            sb.append("A");
            sb.append("where");
            sb.append("A.MAKECO_CODE");
            sb.append(">");
            sb.append("'0000'");
            sb.append("and");
            sb.append("A.MAKECO_CODE");
            sb.append("<");
            sb.append("'9999'");
            sb.append("and");
            sb.append("A.MAKECO_CODE");
            sb.append("LIKE");
            sb.append("'0001';");
//String fsql = data.makeSql();
            
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

//gSqlContainer sumber=new gSqlContainer();
           SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                    "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@172.29.32.36/GSSHOP",
                    "dartmedia", "dartmedia",2,5);
             
            SQLContainer container = null;
            try {
            container = new SQLContainer(new FreeformQuery("select * from dartmedia.AD_MENU",connectionPool,"AD_MENU_ID"));
           // container = new SQLContainer(new TableQuery("AD_MENU",connectionPool));
            
            MTable table;
            table = new MTable("Contoh",container);
            
            table.addContainerProperty("Ad_MENU_ID", String.class,  null);
            addComponents(table);
            table.setSelectable(true);

            }
            catch(SQLException e) {
    if (container != null) {
      container.rollback();
    }
    e.printStackTrace();
  }
            
            
//-------------------- Header Table ------------------------------
            MHorizontalLayout toolmenu;
            toolmenu = new MHorizontalLayout();
            toolmenu.addComponent(new Button("Ret"));
            toolmenu.addComponent(new Button("Ins"));
            toolmenu.addComponent(new Button("Save"));
            toolmenu.addComponent(new Button("Print"));
            toolmenu.addComponent(new Button("XLS"));
            addComponents(
                    new Header("Makecomp")
            );
            addComponents(toolmenu);
            addComponents(toolbar);
            addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(MakecompView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
