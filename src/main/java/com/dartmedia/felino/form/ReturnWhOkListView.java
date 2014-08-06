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
@CDIView("ReturnWhOkList")
public class ReturnWhOkListView extends MVerticalLayout implements View {
//ReturnWhOkListSvc data=new ReturnWhOkListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Confirm date <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Warehouse <p:selectOneListbox></p:selectOneListbox> C/S type <p:selectOneListbox></p:selectOneListbox> Order no <p:inputText></p:inputText>Item <p:inputText></p:inputText>L grp<p:inputText></p:inputText>                 **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("      SELECT /*slipreturn.xml : logistics.slipreturn.selectReturnWhOkList by ReturnWhOkList*/");
sb.append("            TO_CHAR(A.CREATE_DATE, 'YYYY/MM/DD') AS CREATE_DATE,");
sb.append("            C.LGROUP,");
sb.append("            C.MGROUP,");
sb.append("            C.GOODS_CODE,");
sb.append("            C.GOODS_NAME,");
sb.append("            D.GOODSDT_CODE,");
sb.append("            D.GOODSDT_INFO,");
sb.append("            B.DELY_QTY,");
sb.append("            B.RETURN_QTY,");
sb.append("            A.SLIP_PROC_ID,");
sb.append("            E.USER_NAME,");
sb.append("            TO_CHAR(A.SLIP_PROC_DATE, 'YYYYMMDD') AS SEQ,");
sb.append("            F.ENTP_NAME,");
sb.append("            A.SLIP_GB,");
sb.append("            B.ORDER_NO || B.ORDER_G_SEQ || B.ORDER_D_SEQ || B.ORDER_W_SEQ AS ORDER_NO,");
sb.append("            A.SLIP_NO,");
sb.append("            A.DELY_GB,");
sb.append("            A.SLIP_I_NO,");
sb.append("            G.SALE_PRICE");
sb.append("        FROM");
sb.append("            TSLIPM      A,");
sb.append("             TSLIPDT     B,");
sb.append("            TGOODS      C,");
sb.append("            TGOODSDT    D,");
sb.append("            VUSER       E,");
sb.append("            TENTERPRISE F,");
sb.append("            TGOODSPRICE G");
sb.append("         WHERE A.SLIP_I_NO   = B.SLIP_I_NO");
sb.append("           AND B.GOODS_CODE     = C.GOODS_CODE");
sb.append("         AND B.GOODS_CODE     = D.GOODS_CODE");
sb.append("        AND B.GOODSDT_CODE     = D.GOODSDT_CODE");
sb.append("        AND B.GOODS_CODE     = G.GOODS_CODE");
sb.append("        AND G.APPLY_DATE     = (SELECT MAX(APPLY_DATE)");
sb.append("                               FROM TGOODSPRICE");
sb.append("                               WHERE GOODS_CODE = B.GOODS_CODE)");
sb.append("        AND A.SLIP_PROC_ID  = E.USER_ID");
sb.append("        AND C.ENTP_CODE     = F.ENTP_CODE");
sb.append("        AND A.SLIP_FLAG     = '2'");
sb.append("        AND A.DELY_TYPE     = '10'");
sb.append("        AND A.CREATE_DATE     >= TO_DATE('2014/04/01', 'YYYY/MM/DD')");
sb.append("        AND TO_DATE('2014/08/01', 'YYYY/MM/DD') + 1 > A.CREATE_DATE");
sb.append("        AND A.WH_CODE         = '001'");
sb.append("/*        AND C.LGROUP         LIKE #{lgroup, jdbcType=VARCHAR}         || '%'");
sb.append("        AND C.GOODS_CODE     LIKE #{goods_code, jdbcType=VARCHAR}     || '%'");
sb.append("        AND A.SLIP_PROC_ID     LIKE #{slip_proc_id, jdbcType=VARCHAR}     || '%'");
sb.append("        AND A.SLIP_GB           IN (#{slip_gb_claim, jdbcType=VARCHAR}, #{slip_gb_exch, jdbcType=VARCHAR}) */");
sb.append("         ORDER BY");
sb.append("             E.USER_NAME,");
sb.append("             CREATE_DATE, SEQ       ");
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
                new Header("Retrun Confirm Detail Info (ReturnWhOkList)"
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
