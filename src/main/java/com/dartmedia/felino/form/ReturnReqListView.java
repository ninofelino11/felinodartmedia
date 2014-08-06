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
@CDIView("ReturnReqList")
public class ReturnReqListView extends MVerticalLayout implements View {
//ReturnReqListSvc data=new ReturnReqListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Return order date <p:calendar></p:calendar> Shipment type <p:selectOneListbox></p:selectOneListbox>                                                                    **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* slipreturn.xml : logistics.slipreturn.selectReturnReqList by ReturnReqList */");
sb.append("               A.ORDER_NO                       AS ORDER_NO,");
sb.append("               A.ORDER_G_SEQ ||'-'|| A.ORDER_D_SEQ ||'-'|| A.ORDER_W_SEQ      AS ORDER_SEQ,");
sb.append("               C.CUST_NAME,");
sb.append("               A.RECEIVER_SEQ                   AS RECEIVER_SEQ,");
sb.append("               D.RECEIVER,");
sb.append("               D.TEL                  AS RECEIVER_TEL,");
sb.append("               TCODE_NAME('J007',A.CLAIM_GB) AS CLAIM_GB,");
sb.append("               D.RECEIVER_POST AS POST_NO,");
sb.append("               FUN_ADD_POSTADDR(D.RECEIVER_POST, D.RECEIVER_POST_SEQ, '') AS RECEIVER_ADDR,");
sb.append("               E.GOODS_CODE,");
sb.append("               F.GOODS_NAME,");
sb.append("               F.GOODSDT_CODE,");
sb.append("               F.GOODSDT_INFO,");
sb.append("               A.SYSLAST                        AS SYSLAST,");
sb.append("               A.SYSLAST_AMT                    AS SYSLAST_AMT");
sb.append("          FROM TCLAIMDT     A,");
sb.append("               TCUSTOMER    C,");
sb.append("               TRECEIVER    D,");
sb.append("               TGOODS       E,");
sb.append("               TGOODSDT     F");
sb.append("         WHERE A.CUST_NO             = C.CUST_NO");
sb.append("           AND A.CUST_NO             = D.CUST_NO");
sb.append("           AND A.RECEIVER_SEQ        = D.RECEIVER_SEQ");
sb.append("           AND A.GOODS_CODE          = E.GOODS_CODE");
sb.append("           AND A.GOODS_CODE          = F.GOODS_CODE");
sb.append("           AND A.GOODSDT_CODE        = F.GOODSDT_CODE");
sb.append("           AND A.DELY_TYPE           = '10'");
sb.append("           AND A.WH_CODE             = '001'");
sb.append("           AND A.CLAIM_GB              IN ('30', '45')");
sb.append("           AND A.DO_FLAG               IN ('50', '55')     ");
sb.append("/*           AND A.DELY_GB             = #{dely_gb, jdbcType=VARCHAR}");
sb.append("           AND A.LAST_PROC_DATE     >= TO_DATE(#{return_date}, 'YYYY/MM/DD')");
sb.append("           AND A.LAST_PROC_DATE   &lt; TO_DATE(#{return_date}, 'YYYY/MM/DD') + 1 */");
sb.append("         ORDER BY ORDER_NO, RECEIVER_SEQ, ORDER_SEQ ");
sb.append("");
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
                new Header("Return Order List (ReturnReqList)"
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
