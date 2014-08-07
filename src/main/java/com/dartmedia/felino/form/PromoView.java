package com.dartmedia.felino.form;
import com.cware.back.common.BaseEntity;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.navigator.View;
import com.vaadin.ui.RichTextArea;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Promo")
public class PromoView extends MVerticalLayout implements View {
//PromoSvc data=new PromoSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Promotion Code: <p:inputText />//select promo_no, promo_name from tpromom**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
StringBuffer sb2 = new StringBuffer();
StringBuffer sb3 = new StringBuffer();
StringBuffer sb4 = new StringBuffer();
sb.append("");
sb.append("SELECT /* promotion.xml : manage.promotion.selectPromoGoodsCode by Promo");
sb.append("Code, Promotion");
sb.append("*/");
sb.append("                 DISTINCT A.PROMO_NO,");
sb.append("                 A.PROMO_NAME,       ");
sb.append("                 A.USE_CODE");
sb.append("            FROM TPROMOM A,");
sb.append("                 TPROMOTARGET B");
sb.append("           WHERE A.PROMO_NO = B.PROMO_NO");
sb.append("             AND A.APP_TYPE < '30'");
sb.append("             AND A.DO_TYPE < '70'");
sb.append("             AND A.PROMO_NO LIKE '201406270001'||'%'");
sb.append("             AND ( ( A.PROMO_BDATE BETWEEN TO_DATE('2014/01/01', 'YYYY/MM/DD HH24:MI:SS')");
sb.append("                                         AND TO_DATE('2014/08/01', 'YYYY/MM/DD HH24:MI:SS')");
sb.append("                  OR A.PROMO_EDATE BETWEEN TO_DATE('2014/01/01', 'YYYY/MM/DD HH24:MI:SS')");
sb.append("                                         AND TO_DATE('2014/08/01', 'YYYY/MM/DD HH24:MI:SS') )");
sb.append("                  OR ( TO_DATE('2014/01/01', 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE");
sb.append("                  OR TO_DATE('2014/08/01', 'YYYY/MM/DD HH24:MI:SS') BETWEEN A.PROMO_BDATE AND A.PROMO_EDATE ) )");
//sb.append("             AND B.GOODS_CODE LIKE ''||'%'");
//sb.append("             AND A.USE_CODE LIKE ''||'%'");
//sb.append("             AND A.COUPON_YN LIKE ''||'%'");
//sb.append("             AND A.DO_TYPE LIKE ''||'%'");
//sb.append("             AND A.FIRST_ORDER_YN LIKE ''||'%' order by A.Promo_code        ");

sb2.append("SELECT /* promotion.xml : manage.promotion.selectPromoDt by Promo */");
sb2.append("                   A.PROMO_NO,");
sb2.append("                 A.PROMO_NAME,");
sb2.append("                 A.COUPON_YN,");
sb2.append("                 A.APP_TYPE,");
sb2.append("                 A.DO_TYPE,");
sb2.append("                 TO_CHAR(A.PROMO_BDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_BDATE,");
sb2.append("                 TO_CHAR(A.PROMO_EDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_EDATE,");
sb2.append("                 A.ORDER_MEDIA_ALL_YN,");
sb2.append("                 A.ORDER_MEDIA,");
sb2.append("                 FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME,");
sb2.append("                 A.MEDIA_CODE_ALL_YN,");
sb2.append("                 A.MEDIA_CODE,");
sb2.append("                 FUN_GET_MEDIA_NAME(A.MEDIA_CODE) AS MEDIA_NAME,");
sb2.append("                 A.LIMIT_YN,");
sb2.append("                 A.LIMIT_QTY,");
sb2.append("                 A.GOODS_ALL_YN,");
sb2.append("                 A.GROSS_NET_FLAG,");
sb2.append("                 A.APP_AMT,");
sb2.append("                 A.AMT_RATE_FLAG,");
sb2.append("                 A.DO_AMT ,");
sb2.append("                 A.SELECT_YN,");
sb2.append("                 A.SELECT_QTY,");
sb2.append("                 A.COUPON_PROMO_NO,");
sb2.append("                 B.PROMO_NAME AS COUPON_PROMO_NAME,");
sb2.append("                 A.LOTTERY_PROMO_NO,");
sb2.append("                 C.LOTTERY_PROMO_NAME,");
sb2.append("                 A.USE_CODE,");
sb2.append("                 A.DO_RATE,");
sb2.append("                 A.REMARK,");
sb2.append("                 A.FIRST_ORDER_YN,");
sb2.append("                 A.MEMB_GB_ALL_YN,");
sb2.append("                 A.MEMB_GB,");
sb2.append("                 NVL(A.COUPON_USE_FIX_YN, '0') AS COUPON_USE_FIX_YN,");
sb2.append("                 A.VALID_DAYS,");
sb2.append("                 A.COUPON_USE_DAY,");
sb2.append("                 A.INSERT_DATE,");
sb2.append("                 A.INSERT_ID,");
sb2.append("                 A.MODIFY_DATE,");
sb2.append("                 A.MODIFY_ID");
sb2.append("            FROM TPROMOM A ,");
sb2.append("                 TPROMOM B,");
sb2.append("                 TLOTTERYPROMOM C");
sb2.append("           WHERE A.COUPON_PROMO_NO = B.PROMO_NO(+)");
sb2.append("             AND A.LOTTERY_PROMO_NO = C.LOTTERY_PROMO_NO (+)");
sb2.append("             AND A.PROMO_NO = '201406270001'");
sb3.append(" ");
sb3.append("");
sb3.append("SELECT /* promotion.xml : manage.promotion.selectPromoGoods by Promo ");
sb3.append("Item Code, Item Name, Point/DC");
sb3.append("*/");
sb3.append("                 A.PROMO_NO,");
sb3.append("                 A.PROMO_SEQ,");
sb3.append("                 A.GOODS_CODE,");
sb3.append("                 B.GOODS_NAME,");
sb3.append("                 A.GIFT_AMT,");
sb3.append("                 A.OWN_COST,");
sb3.append("                 A.ENTP_COST,");
sb3.append("                 A.ENTP_CODE,");
sb3.append("                 C.ENTP_NAME,");
sb3.append("                 0 COMP_SALE_PRICE,");
sb3.append("                 A.INSERT_DATE,");
sb3.append("                 A.INSERT_ID,");
sb3.append("                 A.MODIFY_DATE,");
sb3.append("                 A.MODIFY_ID,");
sb3.append("                 B.TAX_YN");
sb3.append("            FROM TPROMOTARGET A,");
sb3.append("                 TGOODS B,");
sb3.append("                 TENTERPRISE C");
sb3.append("           WHERE A.GOODS_CODE = B.GOODS_CODE");
sb3.append("             AND A.ENTP_CODE = C.ENTP_CODE (+)");
sb3.append("             AND A.PROMO_NO = '201406270001'");
sb4.append("  ");
sb4.append("");
sb4.append("SELECT /* promotion.xml : manage.promotion.selectPromoGift by Promo ");
sb4.append("Gift Code, Gift Name");
sb4.append("*/");
sb4.append("                 A.PROMO_NO,");
sb4.append("                 A.PROMO_SEQ,");
sb4.append("                 A.GIFT_GOODS_CODE,");
sb4.append("                 B.GOODS_CODE,               ");
sb4.append("                 B.GOODS_NAME,");
sb4.append("                 A.GIFT_QTY,           ");
sb4.append("                 A.INSERT_DATE,");
sb4.append("                 A.INSERT_ID,");
sb4.append("                 A.MODIFY_DATE,");
sb4.append("                 A.MODIFY_ID");
sb4.append("            FROM TPROMOGIFT A,");
sb4.append("                 TGOODS B");
sb4.append("           WHERE A.GIFT_GOODS_CODE = B.GOODS_CODE");
sb4.append("             AND A.PROMO_NO = '201406270001' ");
//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();
toolbar.addComponent(new CheckBox("Indv.Query"));
//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new TextField("Promotion Code"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new ComboBox("Use"));
toolbar.addComponent(new Button("Copy"));
toolbar.addComponent(new PopupDateField("Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new ComboBox("Coupon"));
toolbar.addComponent(new ComboBox("First order"));
toolbar.addComponent(new ComboBox("Benefit"));








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
                new Header("Gift Promotion (Promo)"
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
             BaseEntity.user,BaseEntity.pass,2,5);
             SQLContainer container;
              container = new SQLContainer(new FreeformQuery(
              sb.toString(),connectionPool,"PROMO_NO"));
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
  Notification.show(e.getMessage());
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
