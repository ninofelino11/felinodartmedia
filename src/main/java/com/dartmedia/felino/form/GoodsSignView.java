package com.dartmedia.felino.form;
import com.dartmedia.felino.fgetsql;
import com.dartmedia.felino.gSqlContainer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
@CDIView("GoodsSign")
public class GoodsSignView extends MVerticalLayout implements View {
    
//GoodsSignSvc data=new GoodsSignSvc();
 //   @Inject   TenterpriseFacade cf;
//@Inject  TenterpriseForm form;
    @PostConstruct
    public void initComponent() {
/**Apply Date <p:calendar id='fromDate'/> ~ <p:calendar id='toDate'/>
Item <p:inputText/>
//select goods_code, goods_name, TCODE_NAME('B032', SALE_GB) AS SALE_NAME from INDONESIA.TGOODS 
MD: <p:inputText/>
//select md_code, md_name from tmd
Sale Type <p:inputText />
//select code_name from tcode where code_lgroup = 'B032'
Approval Type <p:inputText />
//select code_name from tcode where code_lgroup = 'B024'**/
StringBuffer sb = new StringBuffer();
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsSignList");
sb.append("by");
sb.append("GoodsSignController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("A.SIGN_SEQ");
sb.append("B.GOODS_NAME");
sb.append("B.SET_YN");
sb.append("B.GIFT_YN");
sb.append("B.SAMPLE_YN");
sb.append("B.DM_YN");
sb.append("TO_CHAR(A.APPLY_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("APPLY_DATE");
sb.append("A.SIGN_GB");
sb.append("A.SIGN_DATE");
sb.append("A.SIGN_ID");
sb.append("A.SIGN_NO_CODE");
sb.append("A.SIGN_NO_NOTE");
sb.append("A.INSERT_ID");
sb.append("A.INSERT_DATE");
sb.append("B.SALE_GB");
sb.append("B.SQC_GB");
sb.append("B.BUY_MED");
sb.append("B.SIGN_GB");
sb.append("AS");
sb.append("TGOODS_SIGN_GB");
sb.append("B.STOCK_CHK_PLACE");
sb.append("FROM");
sb.append("TGOODSSIGN");
sb.append("A");
sb.append("TGOODS");
sb.append("B");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.APPLY_DATE");
sb.append(">=");
sb.append("TO_DATE('2013-01-01'");
sb.append("'YYYY/MM/DD')");
sb.append("AND");
sb.append("A.APPLY_DATE");
sb.append("<");
sb.append("TO_DATE('2014-01-01'");
sb.append("'YYYY/MM/DD')");
sb.append("+");
sb.append("1");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("LIKE");
sb.append("NVL('1000'");
sb.append("'%')");
sb.append("AND");
sb.append("B.MD_CODE");
sb.append("LIKE");
sb.append("NVL('0008'");
sb.append("'%')");
sb.append("AND");
sb.append("A.SIGN_GB");
sb.append("=");
sb.append("'80'");
sb.append("AND");
sb.append("B.SALE_GB");
sb.append("<>");
sb.append("'19'");
sb.append("AND");
sb.append("B.SALE_GB");
sb.append("LIKE");
sb.append("NVL('80'");
sb.append("'%');");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsSignDt1");
sb.append("by");
sb.append("GoodsSignController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("AS");
sb.append("GOODS_CODE");
sb.append("A.SIGN_SEQ");
sb.append("AS");
sb.append("SIGN_SEQ");
sb.append("A.SIGN_GB");
sb.append("AS");
sb.append("SIGN_GB");
sb.append("TO_CHAR(A.APPLY_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("APPLY_DATE");
sb.append("A.BUY_PRICE");
sb.append("AS");
sb.append("BUY_PRICE");
sb.append("A.BUY_COST");
sb.append("AS");
sb.append("BUY_COST");
sb.append("A.BUY_VAT");
sb.append("AS");
sb.append("BUY_VAT");
sb.append("A.BUY_VAT_RATE");
sb.append("AS");
sb.append("BUY_VAT_RATE");
sb.append("A.SALE_PRICE");
sb.append("AS");
sb.append("SALE_PRICE");
sb.append("A.SALE_VAT");
sb.append("AS");
sb.append("SALE_VAT");
sb.append("A.SALE_VAT_RATE");
sb.append("AS");
sb.append("SALE_VAT_RATE");
sb.append("A.CUST_PRICE");
sb.append("AS");
sb.append("CUST_PRICE");
sb.append("A.SAVEAMT_RATE");
sb.append("AS");
sb.append("SAVEAMT_RATE");
sb.append("A.SAVEAMT");
sb.append("AS");
sb.append("SAVEAMT");
sb.append("B.NOREST_ALLOT_MONTHS");
sb.append("AS");
sb.append("NOREST_ALLOT_MONTHS");
sb.append("B.DELY_TYPE");
sb.append("AS");
sb.append("DELY_TYPE");
sb.append("A.SALE_PRICE");
sb.append("-");
sb.append("A.SALE_VAT");
sb.append("AS");
sb.append("SALE_COST");
sb.append("DECODE(A.CUST_PRICE");
sb.append("0");
sb.append("0");
sb.append("ROUND((A.CUST_PRICE");
sb.append("-");
sb.append("A.SALE_PRICE)/A.CUST_PRICE*100");
sb.append("2))");
sb.append("AS");
sb.append("DISCOUNT_RATE");
sb.append("DECODE(A.SALE_PRICE");
sb.append("0");
sb.append("0");
sb.append("ROUND((A.SALE_PRICE");
sb.append("-");
sb.append("A.SALE_VAT");
sb.append("-");
sb.append("(A.BUY_PRICE");
sb.append("-");
sb.append("A.BUY_VAT))/(A.SALE_PRICE");
sb.append("-");
sb.append("A.SALE_VAT)*100");
sb.append("2))");
sb.append("AS");
sb.append("MARGIN_RATE");
sb.append("FROM");
sb.append("TGOODSSIGN");
sb.append("A");
sb.append("TGOODS");
sb.append("B");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("B.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100011'");
sb.append("AND");
sb.append("A.SIGN_SEQ");
sb.append("=");
sb.append("'001'");
sb.append("AND");
sb.append("A.SIGN_GB");
sb.append("=");
sb.append("'80';");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsSignDt2List");
sb.append("by");
sb.append("GoodsSignController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("A.GOODSDT_CODE");
sb.append("A.GOODSDT_INFO");
sb.append("A.SALE_GB");
sb.append("FROM");
sb.append("TGOODSDT");
sb.append("A");
sb.append("WHERE");
sb.append("A.GOODSDT_CODE");
sb.append(">");
sb.append("'000'");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100011';");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsSignDt3");
sb.append("by");
sb.append("GoodsSignController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("A.GOODS_NAME");
sb.append("A.KEYWORD");
sb.append("A.SALE_GB");
sb.append("A.ENTP_CODE");
sb.append("C.ENTP_NAME");
sb.append("A.MD_CODE");
sb.append("D.MD_NAME");
sb.append("A.LGROUP");
sb.append("B.LGROUP_NAME");
sb.append("A.MGROUP");
sb.append("B.MGROUP_NAME");
sb.append("A.SGROUP");
sb.append("B.SGROUP_NAME");
sb.append("A.DGROUP");
sb.append("B.DGROUP_NAME");
sb.append("A.SIGN_GB");
sb.append("A.BUY_MED");
sb.append("A.TAX_YN");
sb.append("A.COST_TAX_YN");
sb.append("A.DELY_TYPE");
sb.append("A.POST_YN");
sb.append("A.NOREST_ALLOT_YN");
sb.append("A.STOCK_CHK_PLACE");
sb.append("A.ORDER_MIN_QTY");
sb.append("A.SET_GOODS_YN");
sb.append("A.GIFT_YN");
sb.append("A.PAY_YN");
sb.append("A.GIFT_RETURN_YN");
sb.append("A.EXCH_YN");
sb.append("A.RETURN_YN");
sb.append("A.OUT_STOCK_YN");
sb.append("A.GIFT_RETURN_YN");
sb.append("A.CUST_DC_YN");
sb.append("A.MAKECO_CODE");
sb.append("E.MAKECO_NAME");
sb.append("COMP_MAKECO_NAME");
sb.append("A.BRAND_CODE");
sb.append("F.BRAND_NAME");
sb.append("A.ORIGIN_CODE");
sb.append("G.CODE_NAME");
sb.append("COMP_ORIGIN_NAME");
sb.append("A.WH_CODE");
sb.append("A.SET_YN");
sb.append("A.ENTP_MAN_SEQ");
sb.append("A.HANDLE_CODE");
sb.append("A.MIXPACK_YN");
sb.append("A.INSERT_DATE");
sb.append("A.INSERT_ID");
sb.append("A.MODIFY_DATE");
sb.append("A.MODIFY_ID");
sb.append("A.SQC_GB");
sb.append("A.QC_LGROUP");
sb.append("''");
sb.append("QC_LGROUP_NAME");
sb.append("A.QC_MGROUP");
sb.append("''");
sb.append("QC_MGROUP_NAME");
sb.append("A.ORDER_MEDIA_ALL_YN");
sb.append("A.ORDER_MEDIA");
sb.append("A.ARS_NAME");
sb.append("A.SAMPLE_YN");
sb.append("A.DM_YN");
sb.append("A.MASTER_CODE");
sb.append("H.GOODS_NAME");
sb.append("AS");
sb.append("MASTER_NAME");
sb.append("A.IN_UNIT");
sb.append("A.IN_UNIT_QTY");
sb.append("A.WMS_WIDTH");
sb.append("A.WMS_LENGTH");
sb.append("A.WMS_HEIGHT");
sb.append("A.WMS_VOLUME");
sb.append("A.V_VOLUME");
sb.append("FROM");
sb.append("TGOODS");
sb.append("A");
sb.append("TGOODSKINDS");
sb.append("B");
sb.append("TENTERPRISE");
sb.append("C");
sb.append("TMD");
sb.append("D");
sb.append("TMAKECOMP");
sb.append("E");
sb.append("TBRAND");
sb.append("F");
sb.append("TCODE");
sb.append("G");
sb.append("TGOODS");
sb.append("H");
sb.append("WHERE");
sb.append("A.LGROUP");
sb.append("=");
sb.append("B.LGROUP");
sb.append("AND");
sb.append("A.MGROUP");
sb.append("=");
sb.append("B.MGROUP");
sb.append("AND");
sb.append("A.SGROUP");
sb.append("=");
sb.append("B.SGROUP");
sb.append("AND");
sb.append("A.DGROUP");
sb.append("=");
sb.append("B.DGROUP");
sb.append("AND");
sb.append("A.ENTP_CODE");
sb.append("=");
sb.append("C.ENTP_CODE");
sb.append("AND");
sb.append("A.MD_CODE");
sb.append("=");
sb.append("D.MD_CODE");
sb.append("AND");
sb.append("A.MAKECO_CODE");
sb.append("=");
sb.append("E.MAKECO_CODE");
sb.append("AND");
sb.append("A.BRAND_CODE");
sb.append("=");
sb.append("F.BRAND_CODE");
sb.append("AND");
sb.append("A.ORIGIN_CODE");
sb.append("=");
sb.append("G.CODE_MGROUP");
sb.append("AND");
sb.append("G.CODE_LGROUP");
sb.append("=");
sb.append("'B023'");
sb.append("AND");
sb.append("A.MASTER_CODE");
sb.append("=");
sb.append("H.GOODS_CODE");
sb.append("AND");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100011';");
sb.append("SELECT");
sb.append("/*");
sb.append("goods.xml");
sb.append(":");
sb.append("manage.goods.selectGoodsSignDt4List");
sb.append("by");
sb.append("GoodsSignController");
sb.append("*/");
sb.append("A.GOODS_CODE");
sb.append("A.GOODSDT_CODE");
sb.append("A.SEQ");
sb.append("TO_CHAR(A.START_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("START_DATE");
sb.append("TO_CHAR(A.END_DATE");
sb.append("'YYYY/MM/DD')");
sb.append("AS");
sb.append("END_DATE");
sb.append("A.LEAD_TIME");
sb.append("A.DAILY_CAPA_QTY");
sb.append("A.INPLAN_QTY");
sb.append("A.MAX_SALE_QTY");
sb.append("FROM");
sb.append("TINPLANQTY");
sb.append("A");
sb.append("WHERE");
sb.append("A.GOODS_CODE");
sb.append("=");
sb.append("'100011'");
sb.append("AND");
sb.append("A.GOODSDT_CODE");
sb.append("=");
sb.append("'001';");
//String fsql = data.makeSql();
gSqlContainer sumber=new gSqlContainer();
MTable table=new MTable();
MHorizontalLayout toolmenu;
MHorizontalLayout toolbar;
toolbar = new MHorizontalLayout();

//*-------------------Header---------------------------------------------
toolbar.addComponent(new PopupDateField("My Date"));
toolbar.addComponent(new TextField("Indv.Query"));
toolbar.addComponent(new TextField("Item"));
toolbar.addComponent(new TextField("Md"));
toolbar.addComponent(new TextField("Sale Type"));
toolbar.addComponent(new TextField("Approval Type"));

//*-----------------------------------------------------------------



/*//-------------------- Header Table ------------------------------*/
table.addContainerProperty("No", String.class,  null);
table.addContainerProperty("Item Code", String.class,  null);
table.addContainerProperty("Item Name", String.class,  null);
table.addContainerProperty("Seq", String.class,  null);
table.addContainerProperty("Sign", String.class,  null);


     
//-------------------- Header Table ------------------------------
TabSheet tabsheet = new TabSheet();
VerticalLayout tab1 = new VerticalLayout();
tabsheet.addTab(tab1, "Item Price Info",null);
tab1.addComponent(new TextField("Apply date"));
tab1.addComponent(new TextField("buy price"));
tab1.addComponent(new TextField("buy cost"));
tab1.addComponent(new TextField("Tax Rate%"));
tab1.addComponent(new TextField("Vat"));
tab1.addComponent(new TextField("Market Price"));
tab1.addComponent(new TextField("Dc Rate"));
tab1.addComponent(new TextField("Margin Rate"));
tab1.addComponent(new TextField("Sales Price"));
tab1.addComponent(new TextField("Sale Unit Price"));



        
VerticalLayout tab2 = new VerticalLayout();
tabsheet.addTab(tab2, "Unit Info",null);

VerticalLayout tab3 = new VerticalLayout();
tabsheet.addTab(tab3, "Item Information",null);

VerticalLayout tab4 = new VerticalLayout();
tabsheet.addTab(tab4, "Item Description",null);


toolmenu = new MHorizontalLayout();
toolmenu.addComponent(new Button("Ret"));
toolmenu.addComponent(new Button("Ins"));
toolmenu.addComponent(new Button("Save"));
toolmenu.addComponent(new Button("Print"));
toolmenu.addComponent(new Button("XLS"));



        addComponents( 
                new Header("GoodsSign")
        );
        addComponents(toolmenu);
        addComponents(toolbar);
        addComponents(table);
        addComponents(tabsheet);
        
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
