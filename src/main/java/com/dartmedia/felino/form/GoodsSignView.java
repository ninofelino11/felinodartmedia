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
@CDIView("GoodsSign")
public class GoodsSignView extends MVerticalLayout implements View {
//GoodsSignSvc data=new GoodsSignSvc();
//@Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Apply Date <p:calendar id='fromDate'/> ~ <p:calendar id='toDate'/>Item <p:inputText/>//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from INDONESIA.TGOODS MD: <p:inputText/>//select md_code, md_name from tmdSale Type <p:inputText />//select code_name from tcode where code_lgroup = 'B032'Approval Type <p:inputText />//select code_name from tcode where code_lgroup = 'B024'**/
StringBuffer sb = new StringBuffer();
StringBuffer sb1 = new StringBuffer();
StringBuffer sb2 = new StringBuffer();
StringBuffer sb3 = new StringBuffer();
StringBuffer sb4 = new StringBuffer();

sb.append("SELECT /* goods.xml : manage.goods.selectGoodsSignList by GoodsSignController */");
sb.append("             A.GOODS_CODE,");
sb.append("             A.SIGN_SEQ,");
sb.append("             B.GOODS_NAME,");
sb.append("             B.SET_YN,");
sb.append("             B.GIFT_YN,");
sb.append("             B.SAMPLE_YN,");
sb.append("             B.DM_YN,");
sb.append("             TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,");
sb.append("             A.SIGN_GB,");
sb.append("             A.SIGN_DATE,");
sb.append("             A.SIGN_ID,");
sb.append("             A.SIGN_NO_CODE,");
sb.append("             A.SIGN_NO_NOTE,");
sb.append("             A.INSERT_ID,");
sb.append("             A.INSERT_DATE,");
sb.append("             B.SALE_GB,");
sb.append("             B.SQC_GB,");
sb.append("             B.BUY_MED,");
sb.append("             B.SIGN_GB AS TGOODS_SIGN_GB,");
sb.append("             B.STOCK_CHK_PLACE");
sb.append("        FROM TGOODSSIGN A,");
sb.append("             TGOODS B");
sb.append("       WHERE A.GOODS_CODE = B.GOODS_CODE");
//sb.append("         AND A.APPLY_DATE >= TO_DATE('2013-01-01', 'YYYY/MM/DD')");
//sb.append("         AND A.APPLY_DATE < TO_DATE('2014-01-01', 'YYYY/MM/DD') + 1");
//sb.append("         AND A.GOODS_CODE LIKE NVL('1000', '%')");
//sb.append("         AND B.MD_CODE LIKE NVL('0008', '%')");
//sb.append("         AND A.SIGN_GB = '80'");
//sb.append("         AND B.SALE_GB <> '19'");
//sb.append("         AND B.SALE_GB LIKE NVL('80', '%')");


sb1.append("SELECT /* goods.xml : manage.goods.selectGoodsSignDt1 by GoodsSignController */");
sb1.append("               A.GOODS_CODE       AS GOODS_CODE,");
sb1.append("             A.SIGN_SEQ         AS SIGN_SEQ,");
sb1.append("             A.SIGN_GB          AS SIGN_GB,");
sb1.append("             TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD') AS APPLY_DATE,");
sb1.append("             A.BUY_PRICE        AS BUY_PRICE,");
sb1.append("             A.BUY_COST         AS BUY_COST,");
sb1.append("             A.BUY_VAT          AS BUY_VAT,");
sb1.append("             A.BUY_VAT_RATE     AS BUY_VAT_RATE,");
sb1.append("             A.SALE_PRICE       AS SALE_PRICE,");
sb1.append("             A.SALE_VAT         AS SALE_VAT,");
sb1.append("             A.SALE_VAT_RATE    AS SALE_VAT_RATE,");
sb1.append("             A.CUST_PRICE       AS CUST_PRICE,");
sb1.append("             A.SAVEAMT_RATE     AS SAVEAMT_RATE,");
sb1.append("             A.SAVEAMT          AS SAVEAMT,");
sb1.append("             B.NOREST_ALLOT_MONTHS   AS NOREST_ALLOT_MONTHS,");
sb1.append("             B.DELY_TYPE        AS DELY_TYPE,");
sb1.append("             A.SALE_PRICE - A.SALE_VAT AS SALE_COST,");
sb1.append("             DECODE(A.CUST_PRICE, 0, 0, ROUND((A.CUST_PRICE - A.SALE_PRICE)/A.CUST_PRICE*100, 2)) AS DISCOUNT_RATE,");
sb1.append("             DECODE(A.SALE_PRICE, 0, 0, ROUND((A.SALE_PRICE - A.SALE_VAT - (A.BUY_PRICE - A.BUY_VAT))/(A.SALE_PRICE - A.SALE_VAT)*100, 2)) AS MARGIN_RATE");
sb1.append("        FROM TGOODSSIGN A,");
sb1.append("             TGOODS B");
sb1.append("       WHERE A.GOODS_CODE    = B.GOODS_CODE");
sb1.append("         AND A.GOODS_CODE    = '100011'");
sb1.append("         AND A.SIGN_SEQ      = '001'");
sb1.append("         AND A.SIGN_GB       = '80'   AND ROWCOUNT<10      ");

sb2.append("SELECT /* goods.xml : manage.goods.selectGoodsSignDt2List by GoodsSignController */");
sb2.append("               A.GOODS_CODE,");
sb2.append("               A.GOODSDT_CODE,");
sb2.append("               A.GOODSDT_INFO,");
sb2.append("               A.SALE_GB");
sb2.append("          FROM TGOODSDT A");
sb2.append("         WHERE A.GOODSDT_CODE > '000'");
sb2.append("           AND A.GOODS_CODE = '100011'");
sb2.append("          ");

sb3.append("SELECT /* goods.xml : manage.goods.selectGoodsSignDt3 by GoodsSignController */");
sb3.append("                 A.GOODS_CODE,");
sb3.append("                A.GOODS_NAME,");
sb3.append("                A.KEYWORD,");
sb3.append("                A.SALE_GB,");
sb3.append("                A.ENTP_CODE,");
sb3.append("                C.ENTP_NAME,");
sb3.append("                A.MD_CODE,");
sb3.append("                D.MD_NAME,");
sb3.append("                A.LGROUP,");
sb3.append("                B.LGROUP_NAME,");
sb3.append("                A.MGROUP,");
sb3.append("                B.MGROUP_NAME,");
sb3.append("                A.SGROUP,");
sb3.append("                B.SGROUP_NAME,");
sb3.append("                A.DGROUP,");
sb3.append("                B.DGROUP_NAME,");
sb3.append("                A.SIGN_GB,");
sb3.append("                A.BUY_MED,");
sb3.append("                A.TAX_YN,");
sb3.append("                A.COST_TAX_YN,");
sb3.append("                A.DELY_TYPE,");
sb3.append("                A.POST_YN,");
sb3.append("                A.NOREST_ALLOT_YN,");
sb3.append("                A.STOCK_CHK_PLACE,");
sb3.append("                A.ORDER_MIN_QTY,");
sb3.append("                A.SET_GOODS_YN,");
sb3.append("                A.GIFT_YN,");
sb3.append("                A.PAY_YN,");
sb3.append("                A.GIFT_RETURN_YN,");
sb3.append("                A.EXCH_YN,");
sb3.append("                A.RETURN_YN,");
sb3.append("                A.OUT_STOCK_YN,");
sb3.append("                A.GIFT_RETURN_YN,");
sb3.append("                A.CUST_DC_YN,");
sb3.append("                A.MAKECO_CODE,");
sb3.append("                E.MAKECO_NAME COMP_MAKECO_NAME,");
sb3.append("                A.BRAND_CODE,");
sb3.append("                F.BRAND_NAME,");
sb3.append("                A.ORIGIN_CODE,");
sb3.append("                G.CODE_NAME COMP_ORIGIN_NAME,");
sb3.append("                A.WH_CODE,");
sb3.append("                A.SET_YN,");
sb3.append("                A.ENTP_MAN_SEQ,");
sb3.append("                A.HANDLE_CODE,");
sb3.append("                A.MIXPACK_YN,");
sb3.append("                A.INSERT_DATE,");
sb3.append("                A.INSERT_ID,");
sb3.append("                A.MODIFY_DATE,");
sb3.append("                A.MODIFY_ID  ,");
sb3.append("                A.SQC_GB,");
sb3.append("                A.QC_LGROUP,");
sb3.append("                '' QC_LGROUP_NAME,");
sb3.append("                A.QC_MGROUP,");
sb3.append("                '' QC_MGROUP_NAME,");
sb3.append("                A.ORDER_MEDIA_ALL_YN,");
sb3.append("                A.ORDER_MEDIA,");
sb3.append("                A.ARS_NAME,");
sb3.append("                A.SAMPLE_YN,");
sb3.append("                A.DM_YN,");
sb3.append("                A.MASTER_CODE,");
sb3.append("                H.GOODS_NAME AS MASTER_NAME,");
sb3.append("                A.IN_UNIT,");
sb3.append("                A.IN_UNIT_QTY,");
sb3.append("                A.WMS_WIDTH,");
sb3.append("                A.WMS_LENGTH,");
sb3.append("                A.WMS_HEIGHT,");
sb3.append("                A.WMS_VOLUME,");
sb3.append("                A.V_VOLUME");
sb3.append("           FROM TGOODS A,");
sb3.append("                TGOODSKINDS B,");
sb3.append("                TENTERPRISE C,");
sb3.append("                TMD D,");
sb3.append("                TMAKECOMP E,");
sb3.append("                TBRAND F,");
sb3.append("                TCODE G,");
sb3.append("                TGOODS H");
sb3.append("          WHERE A.LGROUP = B.LGROUP");
sb3.append("            AND A.MGROUP = B.MGROUP");
sb3.append("            AND A.SGROUP = B.SGROUP");
sb3.append("            AND A.DGROUP = B.DGROUP");
sb3.append("            AND A.ENTP_CODE = C.ENTP_CODE");
sb3.append("            AND A.MD_CODE = D.MD_CODE");
sb3.append("            AND A.MAKECO_CODE = E.MAKECO_CODE");
sb3.append("            AND A.BRAND_CODE = F.BRAND_CODE");
sb3.append("            AND A.ORIGIN_CODE = G.CODE_MGROUP");
sb3.append("            AND G.CODE_LGROUP = 'B023'");
sb3.append("            AND A.MASTER_CODE = H.GOODS_CODE");
sb3.append("            AND A.GOODS_CODE = '100011'");
sb3.append("           ");
sb3.append("");
sb4.append("SELECT /* goods.xml : manage.goods.selectGoodsSignDt4List by GoodsSignController */");
sb4.append("               A.GOODS_CODE,");
sb4.append("               A.GOODSDT_CODE,");
sb4.append("               A.SEQ,");
sb4.append("               TO_CHAR(A.START_DATE, 'YYYY/MM/DD') AS START_DATE,");
sb4.append("               TO_CHAR(A.END_DATE, 'YYYY/MM/DD') AS END_DATE,");
sb4.append("               A.LEAD_TIME,");
sb4.append("               A.DAILY_CAPA_QTY,");
sb4.append("               A.INPLAN_QTY,");
sb4.append("               A.MAX_SALE_QTY");
sb4.append("          FROM TINPLANQTY A");
sb4.append("         WHERE A.GOODS_CODE =  '100011'");
sb4.append("           AND A.GOODSDT_CODE = '001'  ");
sb4.append("           ");


//String fsql = data.makeSql();
//gSqlContainer sumber=new gSqlContainer();
MHorizontalLayout sidebar = new MHorizontalLayout();
MHorizontalLayout isicontents=new MHorizontalLayout();
MHorizontalLayout toolbar = new MHorizontalLayout();

//TabSheet tabsheet = new TabSheet();
//-------------------- Header  ------------------------------
toolbar.addComponent(new PopupDateField("Apply Date"));
toolbar.addComponent(new PopupDateField("~"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("MD"));
toolbar.addComponent(new ComboBox("Sale Type"));
toolbar.addComponent(new ComboBox("Approval Type"));



toolbar.addComponent(new CheckBox("Indv.Query"));
toolbar.addComponent(new Button("Print"));


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
                new Header("Item Approval (GoodsSign)"
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
              sb.toString(),connectionPool,"GOODS_CODE"));
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
