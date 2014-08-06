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
@CDIView("WarehousingInput")
public class WarehousingInputView extends MVerticalLayout implements View {
//WarehousingInputSvc data=new WarehousingInputSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/****/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
sb.append("");
sb.append("/* Item Code, Item Name, Unit, Unit Info, PO Price, Tax Rate, Purchasing, PO Cost, VAT, PO (stock) Amt */");
sb.append("          SELECT /* entpin.xml : logistics.entpin.selectWarehousing by WarehousingInput */");
sb.append("                   SUBSTR(A.IN_NO,1,8)||'-'||SUBSTR(A.IN_NO,9,12) AS IN_NO                                                                                            ");
sb.append("                 , TO_CHAR(A.IN_DATE,'YYYY/MM/DD') AS IN_DATE                                                ");
sb.append("                 , A.ENTP_CODE                                                                                ");
sb.append("                 , SUBSTR(C.S_IDNO,1,3)||'-'||SUBSTR(C.S_IDNO,4,2)||'-'||SUBSTR(C.S_IDNO,6,5) AS I_SNO     ");
sb.append("                 , C.ENTP_NAME                                                                                ");
sb.append("                 , C.OWNER_NAME                                                                            ");
sb.append("                 , FUN_ADD_POSTADDR(C.ENTP_POST,C.ENTP_POST_SEQ, '') AS ADDR                                ");
sb.append("                 , C.ENTP_ADDR                                                                                ");
sb.append("                 , C.WORK_TYPE                                                                             ");
sb.append("                 , C.WORK_KIND                                                                               ");
sb.append("                 , B.GOODS_CODE||'-'||B.GOODSDT_CODE AS CODE                                                ");
sb.append("                 , D.GOODS_NAME                                                                            ");
sb.append("                 , D.BUY_MED AS MED                                                                        ");
sb.append("                 , E.GOODSDT_INFO                                                                            ");
sb.append("                 , B.IN_QTY                                                                                ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'S_ID_NO' )    AS BUSIN_ID                          ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'COMP_NAME' )  AS COM_NAME                          ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'OWNER_NAME' ) AS DELIGATER                      ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'COMP_ADDR1' ) AS ADDRESS1                          ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'COMP_ADDR2' ) AS ADDRESS2                          ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'WORK_TYPE' )  AS UPTAE                          ");
sb.append("                  , ( SELECT VAL FROM TCONFIG WHERE ITEM = 'WORK_KIND' )  AS UPJONG                          ");
sb.append("                 , FUN_GET_GOODS_PRICE(B.GOODS_CODE,SYSDATE, '1') AS BUY_COST                                ");
sb.append("                 , FUN_GET_GOODS_PRICE(B.GOODS_CODE,SYSDATE, '2') AS BUY_VAT                                ");
sb.append("            FROM TINM            A                                                                        ");
sb.append("                 , TINDT           B                                                                        ");
sb.append("                 , TENTERPRISE        C                                                                        ");
sb.append("                 , TGOODS          D                                                                        ");
sb.append("                 , TGOODSDT          E                                                                        ");
sb.append("           WHERE A.IN_NO            = B.IN_NO                                                            ");
sb.append("             AND A.ENTP_CODE        = C.ENTP_CODE                                                        ");
sb.append("             AND B.GOODS_CODE       = D.GOODS_CODE                                                        ");
sb.append("             AND B.GOODS_CODE       = E.GOODS_CODE                                                        ");
sb.append("             AND B.GOODSDT_CODE     = E.GOODSDT_CODE                                                        ");
sb.append("             AND B.IN_QTY           > 0                                                                    ");
sb.append("             AND A.IN_NO            = 1 ");
sb.append("             ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new TextField("Promo Name"));
toolbar.addComponent(new Button("Print"));
toolbar.addComponent(new ComboBox("My ComboBox"));
toolbar.addComponent(new ComboBox("My ComboBox"));
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
                new Header("Goods Receipts[manual] (WarehousingInput)"
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
