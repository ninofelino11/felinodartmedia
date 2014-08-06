package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.dartmedia.felino.TbaljumFacade;
import com.dartmedia.felino.TbaljumForm;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("EntpBalju")
public class EntpBaljuView extends MVerticalLayout implements View {
 
   

//EntpBaljuSvc data=new EntpBaljuSvc();
@Inject   TbaljumFacade cf;
//@Inject   TbaljudtFacade cfdetail;

@Inject  TbaljumForm form;
    @PostConstruct
    public void initComponent() {
    
   
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("/* gak ada select di ibatis, adanya insert */");
sb.append("       ");
sb.append("/*    insert into TBALJUM (/* sql_logistics.entporder.base.xml : logistics.entporder.base.insertEntpBalju by EntpBalju */");
sb.append("                BALJU_NO,");
sb.append("                ENTP_CODE,");
sb.append("                WH_CODE,");
sb.append("                MD_CODE,");
sb.append("                ENTP_MAN_SEQ,");
sb.append("                BALJU_DATE,");
sb.append("                BALJU_DELY_DATE,");
sb.append("                BALJU_GB,");
sb.append("                IPGO_PLAN_DATE,");
sb.append("                IN_PLAN_DATE,");
sb.append("                CTRL_IN_DATE,");
sb.append("                OLD_BALJU_NO,");
sb.append("                ENTP_BALJU_SEQ,");
sb.append("                INSERT_ID,");
sb.append("                INSERT_DATE   )");
sb.append("       values (");
sb.append("              #{baljuNo,jdbcType=VARCHAR}");
sb.append("            , #{entpCode,jdbcType=VARCHAR}");
sb.append("            , #{whCode,jdbcType=VARCHAR}");
sb.append("            , #{mdCode,jdbcType=VARCHAR}");
sb.append("            , #{entpManSeq,jdbcType=VARCHAR}");
sb.append("            , to_date(to_char(SYSDATE,'yyyy/mm/dd'),'yyyy/mm/dd')");
sb.append("            , #{baljuDelyDate,jdbcType=DATE}");
sb.append("            , '20'");
sb.append("            , #{baljuDelyDate,jdbcType=DATE}");
sb.append("            , #{baljuDelyDate,jdbcType=DATE}");
sb.append("            , #{baljuDelyDate,jdbcType=DATE}");
sb.append("            , #{baljuNo,jdbcType=VARCHAR}");
sb.append("            , #{entpBaljuSeq,jdbcType=VARCHAR}");
sb.append("            , #{insertId,jdbcType=VARCHAR}");
sb.append("            , SYSDATE");
sb.append("              )        ");
sb.append("              */ ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();

MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();

//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------

    try {
        SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                "oracle.jdbc.OracleDriver",BaseEntity.jdbc ,                    
                "dartmedia", "dartmedia",2,5);
        // TableQuery whouse = new TableQuery("twarehouse", connectionPool);
        SQLContainer whouse = new SQLContainer(new FreeformQuery(
              "select WH_NAME from twarehouse",connectionPool,"WH_NAME"));
        
      //  whouse.setVersionColumn("VERSION");
      //  SQLContainer container = new SQLContainer(whouse);
        ComboBox whcombo=new ComboBox("Pantos");
       whcombo.setContainerDataSource(whouse);
        toolbar.addComponent(whcombo);
    } catch (SQLException ex) {
        Logger.getLogger(ex.getMessage()).log(Level.SEVERE, null, ex);
        Notification.show(ex.getMessage()+ex.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
    }
    


//Pantos,Bandung,Medan,Surabaya,Lampung
ComboBox buymethod=new ComboBox("Buy Method(hc)");
buymethod.addItem("Whosale");
buymethod.addItem("Consigment");
buymethod.addItem("WareHouse");



   
toolbar.addComponent(buymethod);

toolbar.addComponent(new MTextField("Vendor"));
Button button = new Button("Open Sub-Window");
button.addClickListener(new ClickListener() {

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Window subWindow = new Window("Sub-window");
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
});

toolbar.addComponent(button);

        // Add it to the root component
TextField md=new TextField("md");
md.setDescription("dari table tmd");
toolbar.addComponent(md);
final HorizontalLayout horizontalLayout = new HorizontalLayout(new Label("Vendor"),
                new TextField(""), new TextField());
toolbar.addComponent(horizontalLayout);

toolbar.addComponent(new TextField("Po No"));
toolbar.addComponent(new ComboBox("User Environment"));
// Item Stock,Safe Stock Target
//,Warehouse Info,Zone Management,PO create,C/S report
//form.setWidth(Sizeable.SIZE_UNDEFINED, 0); // Default
//form.setWidth("700px");
addComponent(buildTableControls());

GridLayout grid = new GridLayout(4,1);
addComponent(grid);

grid.addComponent(new PopupDateField("Expected Date"));
grid.addComponent(new MTextField("PO Total Amount"));
// ada garis
grid.addComponent(new Button("Clear"));
grid.addComponent(new Button("Search Condition"));

//MWindow pop=new MWindow();
//addComponent(pop);
//JPAContainer<Tdeposit> persons =
  //  JPAContainerFactory.make(Tdeposit.class, "com.ninofelino_ninofelino_war_1.0-SNAPSHOTPU");
//final ItemHorizontal item = new ItemHorizontal();
  //  item.setWidth(600, Unit.PIXELS);
//private JPAContainer<Tbaljum> baljum;
//JPAContainer<Tbaljum> persons; 
//persons=JPAContainerFactory.make(Tbaljum.class,"com.ninofelino_ninofelino_war_1.0-SNAPSHOTPU");

//Table table1 = new Table("", persons);
//table1.setContainerDataSource(persons);


//  "No","Item Code","Unit","Item Name","Unit Info","PO QTY","PO Unit Price","PO Amount"





//-------------------- Header  ------------------------------
MHorizontalLayout toolmenu;
toolmenu = new MHorizontalLayout();
        addComponents(
                new Header("PO Create (EntpBalju)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
addComponent(form);
Button popupButton = new Button("Open popup with MyPopupUI");

        BrowserWindowOpener popupOpener = new BrowserWindowOpener(MyPopupUI.class);
        popupOpener.setFeatures("height=300,width=300");
        popupOpener.extend(popupButton);
toolbar.addComponent(popupButton);



//        addComponents(table1);

//        addComponents(tabel1);
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
        addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
            }
        });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    private Component buildTableControls() {
    HorizontalLayout tableControls = new HorizontalLayout();
    Button ret = new Button("Ret");
    Button ins = new Button("Ins");
    Button del = new Button("Del");
    Button save = new Button("Save");
    Button print = new Button("Print");
    Button xls = new Button("XLS");
  
    NativeSelect sample = new NativeSelect("Select an option");
    for (int i = 0; i < 6; i++) {
            sample.addItem(i);
            sample.setItemCaption(i, "Option " + i);
        }
    
    sample.addValueChangeListener(new ValueChangeListener() {
        
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
         
        }
        });
    PopupView popup = new PopupView(new PopupTextFieldContent());
    tableControls.addComponent(sample);
    
    tableControls.addComponent(ret);
    
    tableControls.addComponent(del);
    tableControls.addComponent(save);
    tableControls.addComponent(print);
    tableControls.addComponent(del);
    
    return tableControls;
}
    
    public class PopupTextFieldContent implements PopupView.Content {
        private final TextField textField = new TextField(
                "Minimized HTML content", "Click to edit");

        @Override
        public final Component getPopupComponent() {
            return textField;
        }

        @Override
        public final String getMinimizedValueAsHTML() {
            return textField.getValue();
        }
    };

    
    
    
    public class MyPopupUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label("This is MyPopupUI where parameter foo="
                + request.getParameter("foo") + " and fragment is set to "
                + getPage().getUriFragment()));
    }
    
    
    }}
