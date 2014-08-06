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
@CDIView("ReturnWhOkOptional")
public class ReturnWhOkOptionalView extends MVerticalLayout implements View {
//ReturnWhOkOptionalSvc data=new ReturnWhOkOptionalSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Order type <p:selectOneListbox></p:selectOneListbox> Warehouse <p:selectOneListbox></p:selectOneListbox> Proc step <p:selectOneListbox></p:selectOneListbox> Term <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Order no <p:inputText></p:inputText>Item <p:inputText></p:inputText>Customer <p:inputText></p:inputText>       **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /* slipreturn.xml : logistics.slipreturn.selectWhOkOptional by ReturnWhOkOptional */");
sb.append("               '0' AS CHECK_YN,");
sb.append("               '' AS SLIP_NO,");
sb.append("               A.ORDER_NO||'-'||A.ORDER_G_SEQ||'-'||A.ORDER_D_SEQ||'-'||A.ORDER_W_SEQ AS CS_NO,");
sb.append("               TO_CHAR(A.CLAIM_DATE, 'YYYY/MM/DD HH24:MI:SS') CLAIM_DATE,");
sb.append("               A.GOODS_CODE,");
sb.append("               E.GOODS_NAME,");
sb.append("               A.GOODSDT_CODE,");
sb.append("               A.GOODSDT_INFO,");
sb.append("               A.SYSLAST,");
sb.append("               C.RECEIVER,");
sb.append("               NVL(C.TEL, C.RECEIVER_HP) AS RECEIVER_TEL,");
sb.append("               FUN_ADD_POSTADDR(C.RECEIVER_POST, C.RECEIVER_POST_SEQ, C.RECEIVER_ADDR) AS RECEIVER_ADDR,");
sb.append("               A.MSG,");
sb.append("               A.ORDER_NO,");
sb.append("                 A.ORDER_G_SEQ,");
sb.append("                 A.ORDER_D_SEQ,");
sb.append("                 A.ORDER_W_SEQ,");
sb.append("               A.CUST_NO,");
sb.append("               A.RECEIVER_SEQ,");
sb.append("               A.WH_CODE,");
sb.append("                A.DELY_TYPE,");
sb.append("               A.DELY_GB,");
sb.append("                A.CLAIM_GB,");
sb.append("                A.DO_FLAG,");
sb.append("                A.SYSLAST_AMT AS DELY_AMT,");
sb.append("                A.SYSLAST_NET AS DELY_NET,");
sb.append("                A.SYSLAST_VAT AS DELY_VAT,");
sb.append("                A.GOODS_GB");
sb.append("          FROM TCLAIMDT    A,");
sb.append("               TCUSTOMER   B,");
sb.append("               TRECEIVER   C,");
sb.append("                 TPOST       D,");
sb.append("                 TGOODSDT    E");
sb.append("         WHERE A.CUST_NO             = B.CUST_NO");
sb.append("           AND A.CUST_NO             = C.CUST_NO");
sb.append("             AND A.RECEIVER_SEQ      = C.RECEIVER_SEQ");
sb.append("           AND C.RECEIVER_POST       = D.POST_NO");
sb.append("             AND C.RECEIVER_POST_SEQ = D.POST_SEQ");
sb.append("             AND A.GOODS_CODE             = E.GOODS_CODE");
sb.append("             AND A.GOODSDT_CODE         = E.GOODSDT_CODE");
sb.append("           AND TRUNC(A.LAST_PROC_DATE)    >= TO_DATE('2013/11/01' , 'YYYY/MM/DD')");
sb.append("           AND TRUNC(A.LAST_PROC_DATE)    <  TO_DATE('2014/11/01' , 'YYYY/MM/DD') + 1   ;  ");
sb.append("/* LANJUTKAN... KRITERIANYA KEPANJANGAN           ");
sb.append("           AND A.CLAIM_GB          = #{order_gb,jdbcType=VARCHAR}");
sb.append("             AND A.DO_FLAG           = #{do_flag,jdbcType=VARCHAR}");
sb.append("           AND A.SYSLAST           > 0");
sb.append("           AND A.WH_CODE           = #{wh_code,jdbcType=VARCHAR}");
sb.append("           AND A.ORDER_NO LIKE #{order_no,jdbcType=VARCHAR}||'%'");
sb.append("           AND ( A.GOODS_CODE LIKE #{goods_code,jdbcType=VARCHAR}||'%'  OR  A.SET_GOODS_CODE LIKE #{goods_code,jdbcType=VARCHAR}||'%' )");
sb.append("           AND A.CUST_NO LIKE #{cust_no,jdbcType=VARCHAR}||'%'  ");
sb.append("*/");
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
                new Header("Retrun Confirm [Manual] (ReturnWhOkOptional)"
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
