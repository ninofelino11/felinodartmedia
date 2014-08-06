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
@CDIView("SlipReqList")
public class SlipReqListView extends MVerticalLayout implements View {
//SlipReqListSvc data=new SlipReqListSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Warehouse <p:selectOneListbox></p:selectOneListbox> Shipping order date <p:calendar></p:calendar>  Shipment type <p:selectOneListbox></p:selectOneListbox>     <p:selectOneRadio>     <f:selectItem itemLabel="All" itemValue="1" />             <f:selectItem itemLabel="Single" itemValue="1" />      <f:selectItem itemLabel="Mix" itemValue="1" /></p:selectOneRadio>       Seq <p:inputText></p:inputText> ~ <p:inputText></p:inputText>       L Grp <p:inputText></p:inputText>M Grp <p:inputText></p:inputText>Item <p:inputText></p:inputText>                                                                           **/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("SELECT /* slipout.xml : logistics.slipout.selectSlipReqList by SlipReqList */");
sb.append("                TO_NUMBER(A.CREATE_SEQ) AS CREATE_SEQ,");
sb.append("               A.DELY_GB,");
sb.append("               A.MIXPACK_FLAG,");
sb.append("               E.LGROUP,");
sb.append("               E.MGROUP,");
sb.append("               E.GOODS_CODE           AS SORT_GOODS_CODE,");
sb.append("               F.GOODSDT_CODE         AS SORT_GOODSDT_CODE,");
sb.append("               A.SLIP_I_NO,");
sb.append("               B.ORDER_NO    ||");
sb.append("               B.ORDER_G_SEQ ||");
sb.append("               B.ORDER_D_SEQ          AS ORDER_NO,");
sb.append("               C.CUST_NAME,");
sb.append("               D.RECEIVER,");
sb.append("               D.TEL        AS RECEIVER_TEL,");
sb.append("               ");
sb.append("               DECODE(A.SLIP_GB, '102', ( SELECT DECODE(COUNT(*), 1, TCODE_NAME('J018', MAX(XA.OK_MED)), G.CODE_NAME )");
sb.append("                                            FROM TORDERRECEIPTS XA");
sb.append("                                           WHERE XA.ORDER_NO = B.ORDER_NO");
sb.append("                                             AND XA.SETTLE_GB = '03'");
sb.append("                                             AND XA.CANCEL_YN = '0'");
sb.append("                                         )");
sb.append("                                      , G.CODE_NAME ) AS SLIP_GB,");
sb.append("               ");
sb.append("               H.CODE_NAME            AS SLIP_PROC,");
sb.append("               E.GOODS_CODE,");
sb.append("               F.GOODS_NAME,");
sb.append("               F.GOODSDT_CODE,");
sb.append("               F.GOODSDT_INFO,");
sb.append("               B.DELY_QTY                                    AS DELY_QTY,");
sb.append("               DECODE(B.DELY_QTY, 0, 0, B.DELY_AMT)          AS DELY_AMT,");
sb.append("               DECODE(A.DELY_HOPE_YN, '1', A.DELY_HOPE_DATE) AS DELY_HOPE_DATE,");
sb.append("               DECODE(A.DELY_HOPE_YN, '1', A.DELY_HOPE_TIME) AS DELY_HOPE_TIME,");
sb.append("               A.MSG,");
sb.append("               (  SELECT SUBSTR(MIN(H.RACK_CODE), 4)");
sb.append("                    FROM TRACKCODE G,");
sb.append("                         TRACK     H");
sb.append("                   WHERE G.RACK_CODE  = H.RACK_CODE");
sb.append("                     AND G.RACK_GRADE = '1'");
sb.append("                     AND H.RACK_QTY   > 0");
sb.append("                     AND B.GOODS_CODE = H.GOODS_CODE");
sb.append("                     AND B.GOODSDT_CODE = H.GOODSDT_CODE) AS RACK_CODE,");
sb.append("               A.SLIP_NO,");
sb.append("               B.PICKING_SEQ");
sb.append("          FROM TSLIPM       A,");
sb.append("               TSLIPDT      B,");
sb.append("               TCUSTOMER    C,");
sb.append("               TRECEIVER    D,");
sb.append("               TGOODS       E,");
sb.append("               TGOODSDT     F,");
sb.append("               TCODE        G,   /* SLIP_GB */");
sb.append("               TCODE        H    /* SLIP_PROC */");
sb.append("         WHERE A.SLIP_I_NO                   = B.SLIP_I_NO");
sb.append("           AND A.CUST_NO                     = C.CUST_NO");
sb.append("           AND A.CUST_NO                     = D.CUST_NO");
sb.append("           AND A.RECEIVER_SEQ                = D.RECEIVER_SEQ");
sb.append("           AND B.GOODS_CODE                  = E.GOODS_CODE");
sb.append("           AND B.GOODS_CODE                  = F.GOODS_CODE");
sb.append("           AND B.GOODSDT_CODE                = F.GOODSDT_CODE");
sb.append("           AND A.SLIP_GB                     = G.CODE_MGROUP");
sb.append("           AND A.SLIP_PROC                   = H.CODE_MGROUP");
sb.append("           AND A.SLIP_FLAG                   = '1'");
sb.append("           AND A.DELY_TYPE                   = '10'");
sb.append("           AND A.REDELY_YN                   = '0'");
sb.append("           AND G.CODE_LGROUP                 = 'L021'");
sb.append("           AND H.CODE_LGROUP                 = 'L023'");
sb.append("           AND A.WH_CODE                     = '001'");
sb.append("           AND A.CREATE_DATE                 = TO_DATE('2013/11/01', 'YYYY/MM/DD')");
sb.append("           AND A.CREATE_SEQ   BETWEEN TO_NUMBER(1) AND TO_NUMBER(1)");
sb.append("           AND A.MIXPACK_FLAG               LIKE '%%'");
sb.append("           AND A.DELY_GB                    LIKE '%%'");
sb.append("           AND E.LGROUP                     LIKE '%%'");
sb.append("           AND E.MGROUP                     LIKE '%%'");
sb.append("           AND E.GOODS_CODE                 LIKE '%%'");
sb.append("         ORDER BY B.ORDER_NO, B.ORDER_G_SEQ, B.ORDER_D_SEQ, B.ORDER_W_SEQ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("Warehouse"));
toolbar.addComponent(new PopupDateField("Shipping Order Date"));
toolbar.addComponent(new ComboBox("Shipment Type"));
toolbar.addComponent(new CheckBox("All"));
toolbar.addComponent(new CheckBox("Single"));
toolbar.addComponent(new CheckBox("Mix"));
toolbar.addComponent(new TextField("Seq"));
toolbar.addComponent(new TextField("~"));
toolbar.addComponent(new TextField("L GRP"));
toolbar.addComponent(new TextField("M GRP"));
toolbar.addComponent(new TextField("Item"));


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
                new Header("Shipping Order List (SlipReqList)"
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
