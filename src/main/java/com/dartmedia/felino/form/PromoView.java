package com.dartmedia.felino.form;


import com.dartmedia.felino.Tpromom;
import com.dartmedia.felino.TpromomFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("Promo")
public class PromoView extends MVerticalLayout implements View {
//PromoSvc data=new PromoSvc();
@Inject   TpromomFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Promotion Code: <p:inputText />//select promo_no, promo_name from tpromom**/
StringBuilder sb = new StringBuilder();
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
sb.append("             AND B.GOODS_CODE LIKE ''||'%'");
sb.append("             AND A.USE_CODE LIKE ''||'%'");
sb.append("             AND A.COUPON_YN LIKE ''||'%'");
sb.append("             AND A.DO_TYPE LIKE ''||'%'");
sb.append("             AND A.FIRST_ORDER_YN LIKE ''||'%' order by A.Promo_code;         ");
sb.append("");
sb.append("SELECT /* promotion.xml : manage.promotion.selectPromoDt by Promo */");
sb.append("                   A.PROMO_NO,");
sb.append("                 A.PROMO_NAME,");
sb.append("                 A.COUPON_YN,");
sb.append("                 A.APP_TYPE,");
sb.append("                 A.DO_TYPE,");
sb.append("                 TO_CHAR(A.PROMO_BDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_BDATE,");
sb.append("                 TO_CHAR(A.PROMO_EDATE, 'yyyy/mm/dd hh24:mi:ss') AS PROMO_EDATE,");
sb.append("                 A.ORDER_MEDIA_ALL_YN,");
sb.append("                 A.ORDER_MEDIA,");
sb.append("                 FUN_GET_ORDER_MEDIA_NAME('J001',A.ORDER_MEDIA) AS ORDER_MEDIA_NAME,");
sb.append("                 A.MEDIA_CODE_ALL_YN,");
sb.append("                 A.MEDIA_CODE,");
sb.append("                 FUN_GET_MEDIA_NAME(A.MEDIA_CODE) AS MEDIA_NAME,");
sb.append("                 A.LIMIT_YN,");
sb.append("                 A.LIMIT_QTY,");
sb.append("                 A.GOODS_ALL_YN,");
sb.append("                 A.GROSS_NET_FLAG,");
sb.append("                 A.APP_AMT,");
sb.append("                 A.AMT_RATE_FLAG,");
sb.append("                 A.DO_AMT ,");
sb.append("                 A.SELECT_YN,");
sb.append("                 A.SELECT_QTY,");
sb.append("                 A.COUPON_PROMO_NO,");
sb.append("                 B.PROMO_NAME AS COUPON_PROMO_NAME,");
sb.append("                 A.LOTTERY_PROMO_NO,");
sb.append("                 C.LOTTERY_PROMO_NAME,");
sb.append("                 A.USE_CODE,");
sb.append("                 A.DO_RATE,");
sb.append("                 A.REMARK,");
sb.append("                 A.FIRST_ORDER_YN,");
sb.append("                 A.MEMB_GB_ALL_YN,");
sb.append("                 A.MEMB_GB,");
sb.append("                 NVL(A.COUPON_USE_FIX_YN, '0') AS COUPON_USE_FIX_YN,");
sb.append("                 A.VALID_DAYS,");
sb.append("                 A.COUPON_USE_DAY,");
sb.append("                 A.INSERT_DATE,");
sb.append("                 A.INSERT_ID,");
sb.append("                 A.MODIFY_DATE,");
sb.append("                 A.MODIFY_ID");
sb.append("            FROM TPROMOM A ,");
sb.append("                 TPROMOM B,");
sb.append("                 TLOTTERYPROMOM C");
sb.append("           WHERE A.COUPON_PROMO_NO = B.PROMO_NO(+)");
sb.append("             AND A.LOTTERY_PROMO_NO = C.LOTTERY_PROMO_NO (+)");
sb.append("             AND A.PROMO_NO = '201406270001';");
sb.append(" ");
sb.append("");
sb.append("SELECT /* promotion.xml : manage.promotion.selectPromoGoods by Promo ");
sb.append("Item Code, Item Name, Point/DC");
sb.append("*/");
sb.append("                 A.PROMO_NO,");
sb.append("                 A.PROMO_SEQ,");
sb.append("                 A.GOODS_CODE,");
sb.append("                 B.GOODS_NAME,");
sb.append("                 A.GIFT_AMT,");
sb.append("                 A.OWN_COST,");
sb.append("                 A.ENTP_COST,");
sb.append("                 A.ENTP_CODE,");
sb.append("                 C.ENTP_NAME,");
sb.append("                 0 COMP_SALE_PRICE,");
sb.append("                 A.INSERT_DATE,");
sb.append("                 A.INSERT_ID,");
sb.append("                 A.MODIFY_DATE,");
sb.append("                 A.MODIFY_ID,");
sb.append("                 B.TAX_YN");
sb.append("            FROM TPROMOTARGET A,");
sb.append("                 TGOODS B,");
sb.append("                 TENTERPRISE C");
sb.append("           WHERE A.GOODS_CODE = B.GOODS_CODE");
sb.append("             AND A.ENTP_CODE = C.ENTP_CODE (+)");
sb.append("             AND A.PROMO_NO = '201406270001';");
sb.append("  ");
sb.append("");
sb.append("SELECT /* promotion.xml : manage.promotion.selectPromoGift by Promo ");
sb.append("Gift Code, Gift Name");
sb.append("*/");
sb.append("                 A.PROMO_NO,");
sb.append("                 A.PROMO_SEQ,");
sb.append("                 A.GIFT_GOODS_CODE,");
sb.append("                 B.GOODS_CODE,               ");
sb.append("                 B.GOODS_NAME,");
sb.append("                 A.GIFT_QTY,           ");
sb.append("                 A.INSERT_DATE,");
sb.append("                 A.INSERT_ID,");
sb.append("                 A.MODIFY_DATE,");
sb.append("                 A.MODIFY_ID");
sb.append("            FROM TPROMOGIFT A,");
sb.append("                 TGOODS B");
sb.append("           WHERE A.GIFT_GOODS_CODE = B.GOODS_CODE");
sb.append("             AND A.PROMO_NO = '201406270001' ");
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
        addComponents(isicontents);
//MTable table=new MTable();
//-------------------- Header Table ---judul untuk table----------
List<Tpromom> findAll = cf.findAll();
MTable<Tpromom> table=new MTable<Tpromom>(Tpromom.class);
//.withProperties("entpName");
table.setBeans(findAll);
table.addMValueChangeListener(new MValueChangeListener<Tpromom>() {
    @Override
    public void valueChange(MValueChangeEvent<Tpromom> event) {
     Notification.show("ss");
//    form.setEntity(event.getValue());
    }
    });
   addComponents(table);
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
