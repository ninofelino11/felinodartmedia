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
@CDIView("SlipDetailsSearch")
public class SlipDetailsSearchView extends MVerticalLayout implements View {
//SlipDetailsSearchSvc data=new SlipDetailsSearchSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Waybill <p:inputText></p:inputText>        **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT  /* outreport.xml : logistics.outreport.selectM by SlipDetailsSearch */");
sb.append("            A.SLIP_I_NO,");
sb.append("            A.CUST_NO,");
sb.append("            A.WH_CODE,");
sb.append("            A.SLIP_NO,");
sb.append("            A.SLIP_GB,");
sb.append("            A.DELY_TYPE,");
sb.append("            A.DELY_GB,");
sb.append("            A.MSG,");
sb.append("            A.MSG_NOTE,");
sb.append("            A.MIXPACK_FLAG,");
sb.append("            TO_CHAR(A.DELY_HOPE_DATE, 'YYYY/MM/DD') AS DELY_HOPE_DATE,");
sb.append("            A.DELY_HOPE_YN,");
sb.append("            A.DELY_HOPE_TIME,");
sb.append("            TO_CHAR(A.CREATE_DATE, 'YYYY/MM/DD') AS CREATE_DATE,");
sb.append("            A.CREATE_SEQ,");
sb.append("            TO_CHAR(A.OUT_CLOSE_DATE, 'YYYY/MM/DD') AS OUT_CLOSE_DATE,");
sb.append("            A.PACK_YN,");
sb.append("            DECODE(SIGN(SLIP_FLAG - '7'), 1,(");
sb.append("                SELECT D.ENTP_NAME");
sb.append("                FROM   TENTERPRISE D");
sb.append("                WHERE  D.ENTP_CODE = A.CUST_NO)");
sb.append("            , B.CUST_NAME) AS CUST_NAME,");
sb.append("            C.RECEIVER,");
sb.append("            C.TEL,");
sb.append("            C.RECEIVER_POST,");
sb.append("            FUN_ADD_POSTADDR(C.RECEIVER_POST,C.RECEIVER_POST_SEQ,C.RECEIVER_ADDR) AS RECEIVER_ADDR,");
sb.append("            A.REAL_RECEIVER,");
sb.append("            TO_CHAR(A.REAL_DELY_DATE, 'YYYY/MM/DD') AS REAL_DELY_DATE");
sb.append("        FROM");
sb.append("            TSLIPM A,                        ");
sb.append("            TCUSTOMER B,");
sb.append("            TRECEIVER C");
sb.append("        WHERE A.CUST_NO           = B.CUST_NO(+)");
sb.append("        AND   A.CUST_NO           = C.CUST_NO(+)");
sb.append("        AND   A.RECEIVER_SEQ      = C.RECEIVER_SEQ(+)");
sb.append("        AND   A.SLIP_I_NO         = 10000000111779");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Waybill No."));
toolbar.addComponent(new TextField("Shipping Order No"));
toolbar.addComponent(new TextField("Waybill Type"));
toolbar.addComponent(new TextField("Shipment Type"));


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
                new Header("Waybill Information (SlipDetailsSearch)"
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
