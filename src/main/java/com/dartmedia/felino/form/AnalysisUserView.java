package com.dartmedia.felino.form;
import com.dartmedia.felino.Sqlfelino;
import com.dartmedia.felino.sqlFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TreeTable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("AnalysisUser")
public class AnalysisUserView extends MVerticalLayout implements View {
//AnalysisUserSvc data=new AnalysisUserSvc();
@Inject   sqlFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
sb.append("");
sb.append("SELECT /* orderreport.xml : custcenter.orderreport.selectAnalysisUser by AnalysisUser */");
sb.append("                 TO_CHAR(TRUNC(A.GATHER_DATE, 'MONTH'), 'YYYY/MM' )      AS GATHER_DATE");
sb.append("               , A.INSERT_ID                     AS INSERT_ID");
sb.append("               , C.USER_NAME                     AS USER_NAME");
sb.append("               , SUM(A.ORDER_QTY)                AS ORDER_QTY");
sb.append("               , SUM(A.ORDER_AMT)                AS ORDER_AMT");
sb.append("               , SUM(A.CANCEL_QTY)               AS CANCEL_QTY");
sb.append("               , SUM(A.CANCEL_AMT)               AS CANCEL_AMT");
sb.append("               , NVL(SUM(A.CLAIM_QTY), 0) - NVL(SUM(A.CLAIM_CAN_QTY), 0) AS CLAIM_QTY");
sb.append("               , NVL(SUM(A.CLAIM_AMT), 0) - NVL(SUM(A.CLAIM_CAN_AMT), 0) AS CLAIM_AMT");
sb.append("               , NVL(SUM(A.EXCH_QTY ), 0) - NVL(SUM(A.EXCH_CAN_QTY ), 0) AS EXCH_QTY");
sb.append("               , NVL(SUM(A.EXCH_AMT ), 0) - NVL(SUM(A.EXCH_CAN_AMT ), 0) AS EXCH_AMT");
sb.append("               , ( NVL(SUM(A.ORDER_QTY), 0) - NVL(SUM(A.CANCEL_QTY), 0) ) - ( NVL(SUM(A.CLAIM_QTY), 0) - NVL(SUM(A.CLAIM_CAN_QTY), 0) )  AS NET_ORDER_QTY");
sb.append("               , ( NVL(SUM(A.ORDER_AMT), 0) - NVL(SUM(A.CANCEL_AMT), 0) ) - ( NVL(SUM(A.CLAIM_AMT), 0) - NVL(SUM(A.CLAIM_CAN_AMT), 0) )  AS NET_ORDER_AMT");
sb.append("               , SUM(A.SHIPFEE_AMT)                                      AS SHIPFEE_AMT");
sb.append("               , SUM(A.SHIPFEE_CAN_AMT)                                  AS SHIPFEE_CAN_AMT");
sb.append("               , SUM(A.SHIPFEE_AMT) - SUM(A.SHIPFEE_CAN_AMT)             AS NET_SHIPFEE_AMT");
sb.append("            FROM TUSER  C");
sb.append("               , TMEDIA B");
sb.append("               , TSDTM  A");
sb.append("           WHERE C.USER_ID    = A.INSERT_ID");
sb.append("             AND B.MEDIA_CODE = A.MEDIA_CODE");
sb.append("             AND A.GATHER_DATE BETWEEN TO_DATE('2014-01-01', 'YYYY/MM/DD') AND TO_DATE('2014-01-01', 'YYYY/MM/DD')");
sb.append("             AND A.ORDER_MEDIA =    'order_media'");
sb.append("             AND A.MEDIA_CODE  =    'media_code'");
sb.append("             AND B.MEDIA_GB    =    'media_gb'");
sb.append("             AND C.USER_GB     =    'user_gb'");
sb.append("             AND A.INSERT_ID   =    'insert_id'");
sb.append("        GROUP BY TRUNC(A.GATHER_DATE, 'DDD'), A.INSERT_ID, C.USER_NAME");
sb.append("        ORDER BY TRUNC(A.GATHER_DATE, 'DDD'), A.INSERT_ID, C.USER_NAME");
sb.append("        GROUP BY TRUNC(A.GATHER_DATE, 'MONTH'), A.INSERT_ID, C.USER_NAME");
sb.append("        ORDER BY TRUNC(A.GATHER_DATE, 'MONTH'), A.INSERT_ID, C.USER_NAME");
sb.append("        GROUP BY TRUNC(A.GATHER_DATE, ''), A.INSERT_ID, C.USER_NAME");
sb.append("        ORDER BY TRUNC(A.GATHER_DATE, ''), A.INSERT_ID, C.USER_NAME  ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Term"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Order Media"));
toolbar.addComponent(new ComboBox("by Day"));
toolbar.addComponent(new ComboBox("User Type"));
toolbar.addComponent(new ComboBox("My ComboBox"));



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
                new Header("Agent Order Report (AnalysisUser)"
        ));
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------

String fsql=sb.toString();        
try{
            SimpleJDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
             "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521/XE",
             "dartmedia", "dartmedia",2,5);        
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              fsql,connectionPool,"BRAND_CODE"));
             // MTable table= new MTable("MENU",container);    
               TreeTable table = new TreeTable("Menu", container);
              addComponents(table);
 } catch (SQLException e) {
     e.printStackTrace();
}
       
        
        
        
        
        
//List<Sqlfelino> findAll = cf.findAll();
//MTable<Sqlfelino> table=new MTable<Sqlfelino>(Sqlfelino.class);
//.withProperties("entpName");
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
