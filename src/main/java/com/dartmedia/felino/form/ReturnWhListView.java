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
@CDIView("ReturnWhList")
public class ReturnWhListView extends MVerticalLayout implements View {
//ReturnWhListSvc data=new ReturnWhListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Confirm date <p:calendar></p:calendar>     ~ <p:calendar></p:calendar>Warehouse <p:selectOneListbox></p:selectOneListbox> C/S type <p:selectOneListbox></p:selectOneListbox> Order no <p:inputText></p:inputText>Item <p:inputText></p:inputText>L grp<p:inputText></p:inputText>        **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append(" SELECT /*slipreturn.xml : logistics.slipreturn.selectReturnWhList by ReturnWhList*/");
sb.append("              M.SLIP_I_NO,");
sb.append("              OD.ORDER_NO || '-' || OD.ORDER_G_SEQ || '-' || OD.ORDER_D_SEQ || '-' ||");
sb.append("              OD.ORDER_W_SEQ AS ORDER_NO,");
sb.append("              M.SLIP_NO,");
sb.append("              TCODE_NAME('L021', M.SLIP_GB) AS SLIP_GB,");
sb.append("              TCODE_NAME('J004',");
sb.append("                         (SELECT MAX(DO_FLAG)");
sb.append("                          FROM TORDERPROC");
sb.append("                          WHERE ORDER_NO = OD.ORDER_NO");
sb.append("                          AND   ORDER_G_SEQ = OD.ORDER_G_SEQ");
sb.append("                          AND   ORDER_D_SEQ = OD.ORDER_D_SEQ");
sb.append("                          AND   ORDER_W_SEQ = OD.ORDER_W_SEQ)) AS SLIP_PROC,");
sb.append("              TO_CHAR(OD.CLAIM_DATE, 'YYYY/MM/DD') AS ORDER_DATE,");
sb.append("              FUN_GET_PROC_DATE(OD.ORDER_NO,");
sb.append("                                OD.ORDER_G_SEQ,");
sb.append("                                OD.ORDER_D_SEQ,");
sb.append("                                OD.ORDER_W_SEQ,");
sb.append("                                '50') AS PROC_50_DATE,");
sb.append("              FUN_GET_PROC_DATE(OD.ORDER_NO,");
sb.append("                                OD.ORDER_G_SEQ,");
sb.append("                                OD.ORDER_D_SEQ,");
sb.append("                                OD.ORDER_W_SEQ,");
sb.append("                                '58') AS PROC_58_DATE,");
sb.append("              FUN_GET_PROC_DATE(OD.ORDER_NO,");
sb.append("                                OD.ORDER_G_SEQ,");
sb.append("                                OD.ORDER_D_SEQ,");
sb.append("                                OD.ORDER_W_SEQ,");
sb.append("                                '60') AS PROC_60_DATE,");
sb.append("              C.CUST_NAME,");
sb.append("              R.RECEIVER,");
sb.append("              GD.GOODS_CODE,");
sb.append("              GD.GOODSDT_CODE,");
sb.append("              GD.GOODS_NAME,");
sb.append("              GD.GOODSDT_INFO,");
sb.append("              OD.SYSLAST,");
sb.append("              M.DELY_QTY");
sb.append("        FROM (SELECT");
sb.append("                  SM.SLIP_I_NO,");
sb.append("                  SD.ORDER_NO,");
sb.append("                  SD.ORDER_G_SEQ,");
sb.append("                  SD.ORDER_D_SEQ,");
sb.append("                  SD.ORDER_W_SEQ,");
sb.append("                  SD.GOODS_CODE,");
sb.append("                  SM.SLIP_NO,");
sb.append("                  SM.SLIP_GB,");
sb.append("                  SD.DELY_QTY + SD.RETURN_QTY AS DELY_QTY,");
sb.append("                  SD.DELY_AMT");
sb.append("               FROM");
sb.append("                     TSLIPM SM,");
sb.append("                     TSLIPDT SD");
sb.append("              WHERE SM.SLIP_I_NO = SD.SLIP_I_NO");
sb.append("                AND   SM.SLIP_FLAG = '2'");
sb.append("                AND   SM.REDELY_YN = '0'");
sb.append("                AND   SD.DELY_QTY + SD.RETURN_QTY > 0");
sb.append("                )             M,");
sb.append("              TCUSTOMER     C,");
sb.append("              TRECEIVER     R,");
sb.append("              TCLAIMDT         OD,");
sb.append("              TORDERPROC     OP,");
sb.append("              TGOODSDT         GD,");
sb.append("              TGOODS         G");
sb.append("        WHERE OD.CUST_NO         = C.CUST_NO");
sb.append("        AND   OD.CUST_NO         = R.CUST_NO");
sb.append("        AND   OD.RECEIVER_SEQ     = R.RECEIVER_SEQ");
sb.append("        AND   OD.GOODS_CODE     = GD.GOODS_CODE");
sb.append("        AND   OD.GOODSDT_CODE     = GD.GOODSDT_CODE");
sb.append("        AND   OD.GOODS_CODE     = G.GOODS_CODE");
sb.append("        AND   M.ORDER_NO(+)     = OD.ORDER_NO");
sb.append("        AND   M.ORDER_G_SEQ(+)     = OD.ORDER_G_SEQ");
sb.append("        AND   M.ORDER_D_SEQ(+)     = OD.ORDER_D_SEQ");
sb.append("        AND   M.ORDER_W_SEQ(+)     = OD.ORDER_W_SEQ");
sb.append("        AND   M.GOODS_CODE(+)     = OD.GOODS_CODE");
sb.append("        AND   OD.ORDER_NO         = OP.ORDER_NO");
sb.append("        AND   OD.ORDER_G_SEQ     = OP.ORDER_G_SEQ");
sb.append("        AND   OD.ORDER_D_SEQ     = OP.ORDER_D_SEQ");
sb.append("        AND   OD.ORDER_W_SEQ     = OP.ORDER_W_SEQ");
sb.append("        AND   OD.WH_CODE         ='001'");
sb.append("        AND   OP.PROC_DATE         >= TO_DATE('2014/04/01', 'YYYY/MM/DD')");
sb.append("        AND   OP.PROC_DATE         <  TO_DATE('2014/04/05', 'YYYY/MM/DD') + 1");
sb.append("/*        AND   G.ENTP_CODE         LIKE #{entp_code, jdbcType=VARCHAR} || '%' ");
sb.append("        AND   OP.DO_FLAG         = #{flag, jdbcType=VARCHAR}");
sb.append("        AND   G.GOODS_CODE         LIKE #{goods_code, jdbcType=VARCHAR} || '%'");
sb.append("        AND   OD.CLAIM_GB         = #{claim_gb, jdbcType=VARCHAR}             ");
sb.append("        AND  (#{do_flag, jdbcType=VARCHAR} = '99' OR ");
sb.append("              #{do_flag, jdbcType=VARCHAR} = (SELECT ");
sb.append("                                                      DO_FLAG");
sb.append("                                                 FROM TORDERPROC IOP");
sb.append("                                                 WHERE ORDER_NO      = OP.ORDER_NO");
sb.append("                                                 AND   ORDER_G_SEQ = OP.ORDER_G_SEQ");
sb.append("                                                 AND   ORDER_D_SEQ = OP.ORDER_D_SEQ");
sb.append("                                               AND   ORDER_W_SEQ = OP.ORDER_W_SEQ");
sb.append("                                               AND   ROWNUM = 1)");
sb.append("              ) ");
sb.append("*/              ");
sb.append("        AND   OD.SYSLAST > 0");
sb.append("        ORDER BY ORDER_NO  ");
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
                new Header("Return Report by Order no (ReturnWhList)"
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
